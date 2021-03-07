package conexionSQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Swing.Observador;
import Swing.ObservadorSimulador;
import Swing.Utils;
import excepciones.ErrorDeSimulacion;
import main.Main;
import objetos.Reserva;

/**
 * Clase ReservasDB.
 */
public class ReservasDB implements FachadaReservasDB, Observador<ObservadorSimulador> {

	/** Lista de observadores. */
	private List<ObservadorSimulador> observadores;

	/**
	 * Instancia un reservasDB.
	 */
	public ReservasDB() {
		this.observadores = new ArrayList<ObservadorSimulador>();
	}

	@Override
	public ArrayList<Reserva> listarReservas() {
		ArrayList<Reserva> reservas = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery("SELECT * FROM RESERVAS ORDER BY ID_RESERVA");
			while (rs.next()) {
				ResultSet rs1 = Main.cnx.createStatement()
						.executeQuery("SELECT ID_SERVICIO FROM SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'"
								+ rs.getString("ID_RESERVA") + "'");
				ArrayList<String> servicios = new ArrayList<>();
				while (rs1.next())
					servicios.add(rs1.getString("ID_SERVICIO"));
				Reserva reserva = new Reserva(rs.getString("ID_RESERVA"), rs.getString("ID_HABITACION"),
						rs.getString("ID_CLIENTE"), rs.getInt("NUM_NOCHES_RESERVA"), rs.getInt("NUM_PERSONAS_RESERVA"),
						servicios);
				reservas.add(reserva);
			}
			this.notificaListar(reservas);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar las reservas.");
		}
		return reservas;
	}

	@Override
	public ArrayList<Reserva> buscarReserva(String idReserva, String idCliente) {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
			ResultSet rs;
			if (!idReserva.isEmpty())
				rs = Main.cnx.createStatement()
						.executeQuery("SELECT * FROM RESERVAS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			else
				rs = Main.cnx.createStatement()
						.executeQuery("SELECT * FROM RESERVAS WHERE ID_CLIENTE = " + "'" + idCliente + "'");
			while (rs.next()) {
				ResultSet rs1 = Main.cnx.createStatement().executeQuery(
						"SELECT ID_SERVICIO FROM SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'" + idReserva + "'");
				ArrayList<String> servicios = new ArrayList<>();
				while (rs1.next())
					servicios.add(rs1.getString("ID_SERVICIO"));
				Reserva reserva = new Reserva(rs.getString("ID_RESERVA"), rs.getString("ID_HABITACION"),
						rs.getString("ID_CLIENTE"), rs.getInt("NUM_PERSONAS_RESERVA"), rs.getInt("NUM_NOCHES_RESERVA"),
						servicios);
				reservas.add(reserva);
			}
			if (reservas.isEmpty())
				Utils.dialogoError("No se ha encontrado la reserva deseada.");
			this.notificaBusca(reservas);
		} catch (SQLException e) {
			Utils.dialogoError("Error en la busqueda de la reserva.");
		}
		return reservas;
	}

	@Override
	public void altaReserva(String idCliente, String numPersonas, String numNoches, ArrayList<String> idServicios) {
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT ID_HABITACION FROM HABITACIONES WHERE DISPONIBILIDAD_HABITACION = 1");
			rs.next();
			Main.cnx.createStatement()
					.executeQuery("INSERT INTO RESERVAS VALUES (" + "(SELECT * FROM NUMERO_RESERVA)" + ", "
							+ rs.getInt("ID_HABITACION") + ", '" + idCliente + "', " + Integer.parseInt(numPersonas)
							+ ", " + Integer.parseInt(numNoches) + ")");
			for (int i = 0; i < idServicios.size(); i++)
				Main.cnx.createStatement().executeQuery("INSERT INTO SERVICIOS_CONTRATADOS VALUES ("
						+ "(SELECT * FROM NUMERO_RESERVA) " + ", " + "'" + idServicios.get(i) + "')");
			Main.cnx.createStatement()
					.executeQuery("UPDATE NUMERO_RESERVA SET NUMERO_RESERVA = ((SELECT * FROM NUMERO_RESERVA) + 1)");
			Main.cnx.createStatement()
					.executeQuery("UPDATE HABITACIONES SET DISPONIBILIDAD_HABITACION = 0 WHERE ID_HABITACION = "
							+ rs.getInt("ID_HABITACION"));
			Main.cnx.createStatement().executeQuery("UPDATE HABITACIONES SET ID_CLIENTE = " + "'" + idCliente + "'"
					+ " WHERE ID_HABITACION = " + rs.getInt("ID_HABITACION"));
			this.notificaAlta();
		} catch (SQLException e) {
			e.printStackTrace();
			Utils.dialogoError("Error al dar de alta la reserva");
			this.notificaError(e);
		}
	}

	@Override
	public void bajaReserva(String idReserva) {
		try {
			float precioFinal = this.precioReserva(idReserva);
			Main.cnx.createStatement()
					.executeQuery("DELETE SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			Main.cnx.createStatement().executeQuery(
					"UPDATE HABITACIONES SET DISPONIBILIDAD_HABITACION = 1 WHERE ID_HABITACION = (SELECT ID_HABITACION FROM RESERVAS WHERE ID_RESERVA = "
							+ "'" + idReserva + "')");
			Main.cnx.createStatement().executeQuery(
					"UPDATE HABITACIONES SET ID_CLIENTE = '' WHERE ID_HABITACION = (SELECT ID_HABITACION FROM RESERVAS WHERE ID_RESERVA = "
							+ "'" + idReserva + "')");
			Main.cnx.createStatement().executeQuery("DELETE RESERVAS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			Utils.dialogo("El precio final a pagar es de: " + precioFinal + "€");
			this.notificaBaja();
		} catch (SQLException e) {
			this.notificaError(new ErrorDeSimulacion("Error al dar de baja la reserva."));
		}
	}

	@Override
	public void actualizaReserva(String idReserva, String idCliente, String numPersonas, String numNoches,
			ArrayList<String> idServicios) {
		try {
			if (!idReserva.isEmpty()) {
				if (!idCliente.isEmpty()) {
					Main.cnx.createStatement().executeQuery("UPDATE RESERVAS SET ID_CLIENTE = " + "'" + idCliente + "'"
							+ " WHERE ID_RESERVA = " + "'" + idReserva + "'");
					Main.cnx.createStatement()
							.executeQuery("UPDATE HABITACIONES SET ID_CLIENTE = " + "'" + idCliente + "'"
									+ " WHERE ID_HABITACION = (SELECT ID_HABITACION FROM RESERVAS WHERE ID_RESERVA = "
									+ "'" + idReserva + "')");
				}
				if (!numPersonas.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE RESERVAS SET NUM_PERSONAS_RESERVA = "
							+ Integer.parseInt(numPersonas) + " WHERE ID_RESERVA = " + "'" + idReserva + "'");
				if (!numNoches.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE RESERVAS SET NUM_NOCHES_RESERVA = "
							+ Integer.parseInt(numNoches) + " WHERE ID_RESERVA = " + idReserva);
				Main.cnx.createStatement()
						.executeQuery("DELETE SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'" + idReserva + "'");
				for (int i = 0; i < idServicios.size(); i++)
					Main.cnx.createStatement().executeQuery("INSERT INTO SERVICIOS_CONTRATADOS VALUES (" + "'"
							+ idReserva + "'" + " ," + "'" + idServicios.get(i) + "')");
			}
			this.notificaActualiza();
		} catch (NumberFormatException | SQLException e) {
			if (e instanceof NumberFormatException)
				Utils.dialogoError("Debe introducir un número de personas y noches válido.");
			else
				Utils.dialogoError("Error al actualizar la reserva.");
			this.notificaError(e);
		}
	}

	@Override
	public boolean existeReserva(String idReserva) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM RESERVAS WHERE ID_RESERVA = " + "'" + idReserva + "'").next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean existeCliente(String idCliente) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM CLIENTES WHERE ID_CLIENTE = " + "'" + idCliente + "'").next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public float precioReserva(String idReserva) {
		try {
			Float precioFinal = (float) 0;
			ResultSet precioHabitacion, idServicios, numNoches, precioServicios;
			precioHabitacion = Main.cnx.createStatement()
					.executeQuery("SELECT PRECIO_HABITACION FROM HABITACIONES WHERE ID_HABITACION = "
							+ "(SELECT ID_HABITACION FROM RESERVAS WHERE ID_RESERVA = '" + idReserva + "')");
			precioHabitacion.next();
			numNoches = Main.cnx.createStatement().executeQuery(
					"SELECT NUM_NOCHES_RESERVA FROM RESERVAS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			numNoches.next();
			precioFinal += (precioHabitacion.getFloat("PRECIO_HABITACION") * numNoches.getInt("NUM_NOCHES_RESERVA"));
			idServicios = Main.cnx.createStatement().executeQuery(
					"SELECT ID_SERVICIO FROM SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			while (idServicios.next()) {
				precioServicios = Main.cnx.createStatement()
						.executeQuery("SELECT PRECIO_SERVICIO FROM SERVICIOS WHERE ID_SERVICIO = " + "'"
								+ idServicios.getString("ID_SERVICIO") + "'");
				precioServicios.next();
				precioFinal += precioServicios.getFloat("PRECIO_SERVICIO");
			}
			return precioFinal;
		} catch (SQLException e) {
			e.printStackTrace();
			Utils.dialogoError("Error al calcular el precio final.");
			return 0;
		}
	}

	@Override
	public boolean quedanHabitaciones() {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM HABITACIONES WHERE DISPONIBILIDAD_HABITACION = 1").next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public ArrayList<String> getServiciosReserva(String idReserva) {
		ArrayList<String> idServicios = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery(
					"SELECT ID_SERVICIO FROM SERVICIOS_CONTRATADOS WHERE ID_RESERVA = " + "'" + idReserva + "'");
			while (rs.next()) {
				idServicios.add(rs.getString("ID_SERVICIO"));
			}
		} catch (SQLException e) {
			Utils.dialogoError("Error inesperado.");
		}
		return idServicios;
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		if (o != null && !this.observadores.contains(o))
			observadores.add(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		if (o != null && this.observadores.contains(o))
			observadores.remove(o);
	}

	@Override
	public void notificaError(Exception e) {
		for (ObservadorSimulador o : this.observadores)
			o.errorSimulador(this.listarReservas(), e);
	}

	@Override
	public void notificaAlta() {
		ArrayList<Reserva> list = this.listarReservas();
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaBaja() {
		ArrayList<Reserva> list = this.listarReservas();
		for (ObservadorSimulador o : this.observadores)
			o.baja(list);
	}

	@Override
	public void notificaActualiza() {
		ArrayList<Reserva> list = this.listarReservas();
		for (ObservadorSimulador o : this.observadores)
			o.actualiza(list);
	}

	@Override
	public void notificaBusca(ArrayList<Reserva> reservas) {
		for (ObservadorSimulador o : this.observadores)
			o.buscar(reservas);
	}

	@Override
	public void notificaListar(ArrayList<Reserva> reservas) {
		for (ObservadorSimulador o : this.observadores)
			o.listar(reservas);
	}

}