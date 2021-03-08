package conexionSQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Swing.Observador;
import Swing.ObservadorSimulador;
import Swing.Utils;
import main.Main;
import objetos.Servicio;

// TODO: Auto-generated Javadoc
/**
 * Clase ServiciosDB.
 */
public class ServiciosDB implements FachadaServiciosDB, Observador<ObservadorSimulador> {

	/** Lista de observadores */
	private List<ObservadorSimulador> observadores;

	/**
	 * Instancia un serviciosDB.
	 */
	public ServiciosDB() {
		this.observadores = new ArrayList<ObservadorSimulador>();
	}

	@Override
	public ArrayList<Servicio> listarServicios() {
		ArrayList<Servicio> servicios = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery("SELECT * FROM SERVICIOS");
			while (rs.next()) {
				Servicio servicio = new Servicio(rs.getString("ID_SERVICIO"), rs.getInt("DURACION_SERVICIO"),
						rs.getFloat("PRECIO_SERVICIO"));
				servicios.add(servicio);
			}
			this.notificaListar(servicios);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar los clientes.");
			this.notificaError(e);
		}
		return servicios;
	}

	@Override
	public ArrayList<Servicio> buscarServicio(String idServicio) {
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM SERVICIOS WHERE ID_SERVICIO = " + "'" + idServicio + "'");
			while (rs.next()) {
				Servicio servicio = new Servicio(rs.getString("ID_SERVICIO"), rs.getInt("DURACION_SERVICIO"),
						rs.getFloat("PRECIO_SERVICIO"));
				servicios.add(servicio);
			}
			if (servicios.isEmpty())
				Utils.dialogoError("No se ha encontrado el servicio deseado.");
			this.notificaBusca(servicios);
		} catch (SQLException e) {
			Utils.dialogoError("Error en la busqueda del servicio.");
			this.notificaError(e);
		}
		return servicios;
	}

	@Override
	public void altaServicio(String idServicio, String precio, String duracion) {
		try {
			Main.cnx.createStatement().executeQuery("INSERT INTO SERVICIOS VALUES (" + "'" + idServicio + "'" + ", "
					+ Float.parseFloat(precio) + "," + Integer.parseInt(duracion) + ")");
			this.notificaAlta();
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de alta el servicio.");
			this.notificaError(e);
		}
	}

	@Override
	public void bajaServicio(String idServicio) {
		try {
			Main.cnx.createStatement().executeQuery("DELETE SERVICIOS WHERE ID_SERVICIO = " + "'" + idServicio + "'");
			this.notificaBaja();
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de baja el servicio.");
			this.notificaError(e);
		}
	}

	@Override
	public void actualizaServicio(String idServicio, String precio, String duracion) {
		try {
			if (!idServicio.isEmpty()) {
				if (!duracion.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE SERVICIOS SET DURACION_SERVICIO = "
							+ Integer.parseInt(duracion) + " WHERE ID_SERVICIO = " + "'" + idServicio + "'");
				if (!precio.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE SERVICIOS SET PRECIO_SERVICIO = "
							+ Float.parseFloat(precio) + " WHERE ID_SERVICIO = " + "'" + idServicio + "'");
			}
			this.notificaActualiza();
		} catch (SQLException e) {
			Utils.dialogoError("Error al actualizar el servicio.");
			this.notificaError(e);
		}
	}

	@Override
	public boolean existeServicio(String idServicio) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM SERVICIOS WHERE ID_SERVICIO = " + "'" + idServicio + "'").next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean servicioContratado(String idServicio) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM SERVICIOS_CONTRATADOS WHERE ID_SERVICIO = " + "'" + idServicio + "'")
					.next();
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
			o.errorSimulador(this.listarServicios(), e);
	}

	@Override
	public void notificaAlta() {
		ArrayList<Servicio> list = this.listarServicios();
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaBaja() {
		ArrayList<Servicio> list = this.listarServicios();
		for (ObservadorSimulador o : this.observadores)
			o.baja(list);
	}

	@Override
	public void notificaActualiza() {
		ArrayList<Servicio> list = this.listarServicios();
		for (ObservadorSimulador o : this.observadores)
			o.actualiza(list);
	}

	@Override
	public void notificaBusca(ArrayList<Servicio> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.buscar(clientes);
	}

	@Override
	public void notificaListar(ArrayList<Servicio> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.listar(clientes);
	}

}