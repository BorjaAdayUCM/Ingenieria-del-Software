package conexionSQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Swing.Observador;
import Swing.ObservadorSimulador;
import Swing.Utils;
import main.Main;
import objetos.Cliente;

/**
 * Clase ClienteDB.
 */
public class ClientesDB implements FachadaClientesDB, Observador<ObservadorSimulador> {

	/** Lista de Observadores */
	private List<ObservadorSimulador> observadores;

	/**
	 * Instancia un clientesDB.
	 */
	public ClientesDB() {
		this.observadores = new ArrayList<ObservadorSimulador>();
	}

	@Override
	public ArrayList<Cliente> listarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery("SELECT * FROM CLIENTES ORDER BY NOMBRE_CLIENTE");
			while (rs.next()) {
				ResultSet rs1 = Main.cnx.createStatement().executeQuery(
						"SELECT ID_RESERVA FROM RESERVAS WHERE ID_CLIENTE = " + "'" + rs.getString("ID_CLIENTE") + "'");
				ArrayList<String> reservas = new ArrayList<>();
				while (rs1.next())
					reservas.add(rs1.getString("ID_RESERVA"));
				Cliente cliente = new Cliente(rs.getString("ID_CLIENTE"), rs.getString("NOMBRE_CLIENTE"),
						rs.getInt("TELEFONO_CLIENTE"), rs.getString("EMAIL_CLIENTE"), reservas);
				clientes.add(cliente);
			}
			this.notificaListar(clientes);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar los clientes.");
			this.notificaError(e);
		}
		return clientes;
	}

	@Override
	public ArrayList<Cliente> buscarCliente(String idCliente) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM CLIENTES WHERE ID_CLIENTE = " + "'" + idCliente + "'");
			while (rs.next()) {
				ResultSet rs1 = Main.cnx.createStatement()
						.executeQuery("SELECT ID_RESERVA FROM RESERVAS WHERE ID_CLIENTE = " + "'" + idCliente
								+ "' ORDER BY ID_RESERVA");
				ArrayList<String> servicios = new ArrayList<>();
				while (rs1.next())
					servicios.add(rs1.getString("ID_RESERVA"));
				Cliente cliente = new Cliente(rs.getString("ID_CLIENTE"), rs.getString("NOMBRE_CLIENTE"),
						rs.getInt("TELEFONO_CLIENTE"), rs.getString("EMAIL_CLIENTE"), servicios);
				clientes.add(cliente);
			}
			if (clientes.isEmpty())
				Utils.dialogoError("No se encuentra el cliente deseado.");
			this.notificaListar(clientes);
		} catch (SQLException e) {
			Utils.dialogoError("Error al buscar el cliente.");
			this.notificaError(e);
		}
		return clientes;
	}

	@Override
	public void altaCliente(String idCliente, String nombre, String telefono, String email) {
		try {
			Main.cnx.createStatement().executeQuery("INSERT INTO CLIENTES values (" + "'" + idCliente + "'" + ", '"
					+ nombre + "' ," + Integer.parseInt(telefono) + ", '" + email + "')");
			this.notificaAlta(idCliente);
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de alta el cliente.");
			this.notificaError(e);
		}
	}

	@Override
	public void bajaCliente(String idCliente) {
		try {
			Main.cnx.createStatement().executeQuery("DELETE CLIENTES WHERE ID_CLIENTE = " + "'" + idCliente + "'");
			this.notificaBaja();
		} catch (SQLException e) {
			Utils.dialogoError("Error en la baja del cliente");
			this.notificaError(e);
		}
	}

	@Override
	public void actualizaCliente(String idCliente, String nombre, String telefono, String email) {
		try {
			if (!idCliente.isEmpty()) {
				if (!nombre.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE CLIENTES SET NOMBRE_CLIENTE = " + "'" + nombre + "'"
							+ " WHERE ID_CLIENTE = " + "'" + idCliente + "'");
				if (!email.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE CLIENTES SET EMAIL_CLIENTE = " + "'" + email + "'"
							+ " WHERE ID_CLIENTE = " + "'" + idCliente + "'");
				if (!telefono.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE CLIENTES SET TELEFONO_CLIENTE = "
							+ Integer.parseInt(telefono) + " WHERE ID_CLIENTE = " + "'" + idCliente + "'");
				this.notificaActualiza();
			}
		} catch (SQLException e) {
			Utils.dialogoError("Error al actualizar el cliente.");
			this.notificaError(e);
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
	public boolean reservasPendientes(String idCliente) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * RESERVAS WHERE ID_CLIENTE = " + "'" + idCliente + "'").next();
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
	public void notificaError(Exception err) {
		for (ObservadorSimulador o : this.observadores)
			o.errorSimulador(this.listarClientes(), err);
	}

	@Override
	public void notificaAlta(String idCliente) {
		ArrayList<Cliente> list = this.buscarCliente(idCliente);
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaBaja() {
		ArrayList<Cliente> list = this.listarClientes();
		for (ObservadorSimulador o : this.observadores)
			o.baja(list);
	}

	@Override
	public void notificaActualiza() {
		ArrayList<Cliente> list = this.listarClientes();
		for (ObservadorSimulador o : this.observadores)
			o.actualiza(list);
	}

	@Override
	public void notificaBusca(ArrayList<Cliente> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.buscar(clientes);
	}

	@Override
	public void notificaListar(ArrayList<Cliente> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.listar(clientes);
	}

}
