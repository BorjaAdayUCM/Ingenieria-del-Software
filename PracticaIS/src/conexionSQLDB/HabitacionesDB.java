package conexionSQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Swing.Observador;
import Swing.ObservadorSimulador;
import Swing.Utils;
import main.Main;
import objetos.Habitacion;

// TODO: Auto-generated Javadoc
/**
 * Clase HabitacionesDB.
 */
public class HabitacionesDB implements FachadaHabitacionesDB, Observador<ObservadorSimulador> {

	/** Lista de observadores. */
	private List<ObservadorSimulador> observadores;

	/**
	 * Instancia un HabitacionesDB.
	 */
	public HabitacionesDB() {
		this.observadores = new ArrayList<ObservadorSimulador>();
	}

	@Override
	public ArrayList<Habitacion> listarHabitaciones() {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery("SELECT * FROM HABITACIONES ORDER BY ID_HABITACION");
			while (rs.next()) {
				Habitacion habitacion = new Habitacion(rs.getString("ID_HABITACION"), rs.getFloat("PRECIO_HABITACION"),
						rs.getInt("DISPONIBILIDAD_HABITACION"), rs.getString("ID_CLIENTE"));
				habitaciones.add(habitacion);
			}
			this.notificaListar(habitaciones);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar las habitaciones.");
		}
		return habitaciones;
	}

	@Override
	public ArrayList<Habitacion> buscarHabitacion(String idHabitacion) {
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM HABITACIONES WHERE ID_HABITACION = " + "'" + idHabitacion + "'");
			while (rs.next()) {
				Habitacion habitacion = new Habitacion(rs.getString("ID_HABITACION"), rs.getFloat("PRECIO_HABITACION"),
						rs.getInt("DISPONIBILIDAD_HABITACION"), rs.getString("ID_CLIENTE"));
				habitaciones.add(habitacion);
			}
			if (habitaciones.isEmpty())
				Utils.dialogoError("No se ha encontrado la habitación deseada.");
			this.notificaBusca(habitaciones);
		} catch (SQLException e) {
			Utils.dialogoError("Error en la busqueda de la habitacion.");
			this.notificaError(e);
		}
		return habitaciones;
	}

	@Override
	public void altaHabitacion(String idHabitacion, String precio) {
		try {
			Main.cnx.createStatement().executeQuery("INSERT INTO HABITACIONES values (" + "'" + idHabitacion + "'"
					+ ", " + Float.parseFloat(precio) + ", " + 1 + ", '')");
			this.notificaAlta(idHabitacion);
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de alta la habitación.");
			this.notificaError(e);
		}
	}

	@Override
	public void bajaHabitacion(String idHabitacion) {
		try {
			Main.cnx.createStatement()
					.executeQuery("DELETE HABITACIONES WHERE ID_HABITACION = " + "'" + idHabitacion + "'");
			this.notificaBaja();
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de baja la habitacion");
			this.notificaError(e);
		}
	}

	@Override
	public void actualizaHabitacion(String idHabitacion, String precio) {
		try {
			if (!idHabitacion.isEmpty()) {
				if (!precio.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE HABITACIONES SET PRECIO_HABITACION = "
							+ Float.parseFloat(precio) + " WHERE ID_HABITACION = " + "'" + idHabitacion + "'");
			}
			this.notificaActualiza();
		} catch (SQLException e) {
			Utils.dialogoError("Error al actualizar la habitación.");
			this.notificaError(e);
		}
	}

	@Override
	public boolean existeHabitacion(String idHabitacion) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM HABITACIONES WHERE ID_HABITACION = " + "'" + idHabitacion + "'")
					.next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean reservasPendientes(String idHabitacion) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * RESERVAS WHERE ID_HABITACION = " + "'" + idHabitacion + "'").next();
		} catch (SQLException e) {
			return false;
		}
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
			o.errorSimulador(this.listarHabitaciones(), e);
	}

	@Override
	public void notificaAlta(String idHabitacion) {
		ArrayList<Habitacion> list = this.buscarHabitacion(idHabitacion);
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaBaja() {
		ArrayList<Habitacion> list = this.listarHabitaciones();
		for (ObservadorSimulador o : this.observadores)
			o.baja(list);
	}

	@Override
	public void notificaActualiza() {
		ArrayList<Habitacion> list = this.listarHabitaciones();
		for (ObservadorSimulador o : this.observadores)
			o.actualiza(list);
	}

	@Override
	public void notificaBusca(ArrayList<Habitacion> habitaciones) {
		for (ObservadorSimulador o : this.observadores)
			o.buscar(habitaciones);
	}

	@Override
	public void notificaListar(ArrayList<Habitacion> habitaciones) {
		for (ObservadorSimulador o : this.observadores)
			o.listar(habitaciones);
	}
}