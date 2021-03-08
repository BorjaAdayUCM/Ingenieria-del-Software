package conexionSQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Swing.Observador;
import Swing.ObservadorSimulador;
import Swing.Utils;
import main.Main;
import objetos.trabajadores.Administrador;
import objetos.trabajadores.Recepcionista;
import objetos.trabajadores.Trabajador;

/**
 * Clase TrabajadoresDB.
 */
public class TrabajadoresDB implements FachadaTrabajadoresDB, Observador<ObservadorSimulador> {

	/** Lista de observadores. */
	private List<ObservadorSimulador> observadores;

	/**
	 * Instancia un trabajadoresDB.
	 */
	public TrabajadoresDB() {
		this.observadores = new ArrayList<ObservadorSimulador>();
	}

	@Override
	public ArrayList<Trabajador> listarAdministradores() {
		ArrayList<Trabajador> administradores = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM TRABAJADORES WHERE TIPO_TRABAJADOR = 'administrador'");
			while (rs.next()) {
				Administrador administrador = new Administrador(rs.getString("ID_TRABAJADOR"),
						rs.getString("NOMBRE_TRABAJADOR"), rs.getInt("JORNADA_TRABAJADOR"),
						rs.getString("EMAIL_TRABAJADOR"), rs.getInt("TELEFONO_TRABAJADOR"),
						rs.getInt("ACTIVO_TRABAJADOR"), rs.getInt("ANTIGUEDAD_TRABAJADOR"));
				if (!administrador.getId().equals("admin"))
					administradores.add(administrador);
			}
			this.notificaListar(administradores);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar los administradores.");
		}
		return administradores;
	}

	@Override
	public ArrayList<Trabajador> listarRecepcionistas() {
		ArrayList<Trabajador> recepcionistas = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM TRABAJADORES WHERE TIPO_TRABAJADOR = 'recepcionista'");
			while (rs.next()) {
				Recepcionista recepcionista = new Recepcionista(rs.getString("ID_TRABAJADOR"),
						rs.getString("NOMBRE_TRABAJADOR"), rs.getInt("JORNADA_TRABAJADOR"),
						rs.getString("TIPO_TRABAJADOR"), rs.getString("EMAIL_TRABAJADOR"),
						rs.getInt("TELEFONO_TRABAJADOR"), rs.getInt("ACTIVO_TRABAJADOR"),
						rs.getString("IDIOMAS_TRABAJADOR"));
				recepcionistas.add(recepcionista);
			}
			this.notificaListar(recepcionistas);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar los recepcionista.");
		}
		return recepcionistas;
	}

	@Override
	public ArrayList<Trabajador> listarTrabajadores() {
		ArrayList<Trabajador> trabajadores = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement().executeQuery("SELECT * FROM TRABAJADORES");
			while (rs.next()) {
				Trabajador trabajador = null;
				if (rs.getString("TIPO_TRABAJADOR").equals("administrador"))
					trabajador = new Administrador(rs.getString("ID_TRABAJADOR"), rs.getString("NOMBRE_TRABAJADOR"),
							rs.getInt("JORNADA_TRABAJADOR"), rs.getString("EMAIL_TRABAJADOR"),
							rs.getInt("TELEFONO_TRABAJADOR"), rs.getInt("ACTIVO_TRABAJADOR"),
							rs.getInt("ANTIGUEDAD_TRABAJADOR"));
				else if (rs.getString("TIPO_TRABAJADOR").equals("recepcionista"))
					trabajador = new Recepcionista(rs.getString("ID_TRABAJADOR"), rs.getString("NOMBRE_TRABAJADOR"),
							rs.getInt("JORNADA_TRABAJADOR"), rs.getString("TIPO_TRABAJADOR"),
							rs.getString("EMAIL_TRABAJADOR"), rs.getInt("TELEFONO_TRABAJADOR"),
							rs.getInt("ACTIVO_TRABAJADOR"), rs.getString("IDIOMAS_TRABAJADOR"));
				if (!trabajador.getId().equals("admin"))
					trabajadores.add(trabajador);
			}
			this.notificaListar(trabajadores);
		} catch (SQLException e) {
			Utils.dialogoError("Error al listar los trabajadores.");
		}
		return trabajadores;
	}

	@Override
	public ArrayList<Trabajador> buscarTrabajador(String idTrabajador) {
		ArrayList<Trabajador> trabajadores = new ArrayList<>();
		try {
			ResultSet rs = Main.cnx.createStatement()
					.executeQuery("SELECT * FROM TRABAJADORES WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
			while (rs.next()) {
				Trabajador trabajador = null;
				if (rs.getString("TIPO_TRABAJADOR").equals("administrador"))
					trabajador = new Administrador(rs.getString("ID_TRABAJADOR"), rs.getString("NOMBRE_TRABAJADOR"),
							rs.getInt("JORNADA_TRABAJADOR"), rs.getString("EMAIL_TRABAJADOR"),
							rs.getInt("TELEFONO_TRABAJADOR"), rs.getInt("ACTIVO_TRABAJADOR"),
							rs.getInt("ANTIGUEDAD_TRABAJADOR"));
				else if (rs.getString("TIPO_TRABAJADOR").equals("recepcionista"))
					trabajador = new Recepcionista(rs.getString("ID_TRABAJADOR"), rs.getString("NOMBRE_TRABAJADOR"),
							rs.getInt("JORNADA_TRABAJADOR"), rs.getString("TIPO_TRABAJADOR"),
							rs.getString("EMAIL_TRABAJADOR"), rs.getInt("TELEFONO_TRABAJADOR"),
							rs.getInt("ACTIVO_TRABAJADOR"), rs.getString("IDIOMAS_TRABAJADOR"));
				trabajadores.add(trabajador);
			}
			if (trabajadores.isEmpty())
				Utils.dialogoError("No se encuentra el trabajador deseado.");
			this.notificaListar(trabajadores);
		} catch (SQLException e) {
			Utils.dialogoError("Error al buscar el trabajador.");
		}
		return trabajadores;
	}

	@Override
	public void altaAdministrador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad) {
		try {
			Main.cnx.createStatement()
					.executeQuery("INSERT INTO TRABAJADORES values (" + "'" + idTrabajador + "', '" + "administrador"
							+ "', '" + nombre + "', " + Integer.parseInt(telefono) + ", '" + email + "',"
							+ Integer.parseInt(jornada) + ", " + antiguedad + ", " + 1 + ", '')");
			this.notificaAltaAdministrador();
		} catch (SQLException e) {
			Utils.dialogoError("Error al dar de alta un administrador.");
			this.notificaError(e);
		}
	}

	@Override
	public void altaRecepcionista(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String idiomas) {
		try {
			Main.cnx.createStatement()
					.executeQuery("INSERT INTO TRABAJADORES values (" + "'" + idTrabajador + "', '" + "recepcionista"
							+ "', '" + nombre + "', " + Integer.parseInt(telefono) + ", '" + email + "',"
							+ Integer.parseInt(jornada) + ", " + 0 + ", " + 1 + ", '" + idiomas + "')");
			this.notificaAltaRecepcionista();
		} catch (NumberFormatException | SQLException e) {
			Utils.dialogoError("Error al dar de alta un recepcionista.");
			this.notificaError(e);
		}
	}

	@Override
	public void bajaTrabajador(String idTrabajador) {
		try {
			Main.cnx.createStatement().executeQuery(
					"UPDATE  TRABAJADORES SET ACTIVO_TRABAJADOR = 0 WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
			this.notificaBaja();
		} catch (SQLException e) {
			Utils.dialogoError("Error en la baja del trabajador.");
			this.notificaError(e);
		}
	}

	@Override
	public void emplearRecepcionista(String idTrabajador) {
		try {
			Main.cnx.createStatement().executeQuery(
					"UPDATE  TRABAJADORES SET ACTIVO_TRABAJADOR = 1 WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
			this.listarRecepcionistas();
		} catch (SQLException e) {
			Utils.dialogoError("Error al contratar el trabajador.");
			this.notificaError(e);
		}
	}

	@Override
	public void emplearAdministrador(String idTrabajador) {
		try {
			Main.cnx.createStatement().executeQuery(
					"UPDATE  TRABAJADORES SET ACTIVO_TRABAJADOR = 1 WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
			this.listarAdministradores();
		} catch (SQLException e) {
			Utils.dialogoError("Error al contratar el trabajador.");
			this.notificaError(e);
		}
	}

	@Override
	public void actualizaTrabajador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad, String idiomas) {
		try {
			if (!idTrabajador.isEmpty()) {
				Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET NOMBRE_TRABAJADOR = " + "'" + nombre
						+ "'" + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				if (!email.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET EMAIL_TRABAJADOR = " + "'" + email
							+ "'" + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET TELEFONO_TRABAJADOR = "
						+ Integer.parseInt(telefono) + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				if (!jornada.isEmpty())
					Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET JORNADA_TRABAJADOR = "
							+ Integer.parseInt(jornada) + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				ResultSet rs = Main.cnx.createStatement().executeQuery(
						"SELECT TIPO_TRABAJADOR FROM TRABAJADORES WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				rs.next();
				if (rs.getString("TIPO_TRABAJADOR").equalsIgnoreCase("administrador"))
					if (!antiguedad.isEmpty())
						Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET ANTIGUEDAD_TRABAJADOR = "
								+ Integer.parseInt(antiguedad) + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
					else if (rs.getString("TIPO_TRABAJADOR").equalsIgnoreCase("recepcionista"))
						if (!idiomas.isEmpty())
							Main.cnx.createStatement().executeQuery("UPDATE TRABAJADORES SET IDIOMAS_TRABAJADOR = "
									+ "'" + idiomas + "'" + " WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'");
				this.notificaActualiza();
			}
		} catch (SQLException e) {
			Utils.dialogoError("Error al actualizar el cliente.");
			this.notificaError(e);
		}
	}

	@Override
	public boolean existeTrabajador(String idTrabajador) {
		try {
			return Main.cnx.createStatement()
					.executeQuery("SELECT * FROM TRABAJADORES WHERE ID_TRABAJADOR = " + "'" + idTrabajador + "'")
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
			o.errorSimulador(this.listarTrabajadores(), e);
	}

	@Override
	public void notificaAltaAdministrador() {
		ArrayList<Trabajador> list = this.listarAdministradores();
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaAltaRecepcionista() {
		ArrayList<Trabajador> list = this.listarRecepcionistas();
		for (ObservadorSimulador o : this.observadores)
			o.alta(list);
	}

	@Override
	public void notificaBaja() {
		ArrayList<Trabajador> list = this.listarTrabajadores();
		for (ObservadorSimulador o : this.observadores)
			o.baja(list);
	}

	@Override
	public void notificaActualiza() {
		ArrayList<Trabajador> list = this.listarTrabajadores();
		for (ObservadorSimulador o : this.observadores)
			o.actualiza(list);
	}

	@Override
	public void notificaBusca(ArrayList<Trabajador> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.buscar(clientes);
	}

	@Override
	public void notificaListar(ArrayList<Trabajador> clientes) {
		for (ObservadorSimulador o : this.observadores)
			o.listar(clientes);
	}

}