package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import Swing.Utils;
import conexionSQLDB.FachadaTrabajadoresDB;
import objetos.trabajadores.Trabajador;

/**
 * Clase SATrabajador.
 */
public class SATrabajador implements FachadaSATrabajador {

	/** Fachada trabajadores DB. */
	private FachadaTrabajadoresDB fachadaTrabajadoresDB;

	/**
	 * Instancia una SATrabajador.
	 *
	 * @param fachadaTrabajadoresDB
	 *            de la SA
	 */
	public SATrabajador(FachadaTrabajadoresDB fachadaTrabajadoresDB) {
		this.fachadaTrabajadoresDB = fachadaTrabajadoresDB;
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		this.fachadaTrabajadoresDB.addObservador(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		this.fachadaTrabajadoresDB.removeObservador(o);
	}

	@Override
	public void altaAdministrador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad) {
		if (idTrabajador.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (this.fachadaTrabajadoresDB.existeTrabajador(idTrabajador)) {
			this.fachadaTrabajadoresDB.emplearAdministrador(idTrabajador);
			Utils.dialogoError("Trabajador con DNI: " + idTrabajador + " contratado.");
		} else if (nombre.isEmpty())
			Utils.dialogoError("Debe introducir un nombre válido.");
		else if (telefono.isEmpty() || !Utils.esEntero(telefono))
			Utils.dialogoError("Debe introducir un telefono válido.");
		else if (!Utils.esEntero(jornada))
			Utils.dialogoError("Debe introducir una jornada numerica.");
		else if (!Utils.esEntero(antiguedad))
			Utils.dialogoError("Debe introducir una antiguedad numerica.");
		else
			this.fachadaTrabajadoresDB.altaAdministrador(idTrabajador, nombre, jornada, email, telefono, antiguedad);
	}

	@Override
	public void altaRecepcionista(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String idiomas) {
		if (idTrabajador.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (this.fachadaTrabajadoresDB.existeTrabajador(idTrabajador)) {
			this.fachadaTrabajadoresDB.emplearRecepcionista(idTrabajador);
			Utils.dialogoError("Trabajador con DNI: " + idTrabajador + " contratado.");
		} else if (nombre.isEmpty())
			Utils.dialogoError("Debe introducir un nombre válido.");
		else if (telefono.isEmpty() || !Utils.esEntero(telefono))
			Utils.dialogoError("Debe introducir un telefono válido.");
		else if (!Utils.esEntero(jornada))
			Utils.dialogoError("Debe introducir una jornada numerica.");
		else
			this.fachadaTrabajadoresDB.altaRecepcionista(idTrabajador, nombre, jornada, email, telefono, idiomas);
	}

	@Override
	public void bajaTrabajador(String idTrabajador) {
		if (idTrabajador.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (!this.fachadaTrabajadoresDB.existeTrabajador(idTrabajador))
			Utils.dialogoError("El trabajador que se quiere dar de baja no está registrado.");
		else
			this.fachadaTrabajadoresDB.bajaTrabajador(idTrabajador);
	}

	@Override
	public void actualizaTrabajador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad, String idiomas) {
		if (idTrabajador.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (!this.fachadaTrabajadoresDB.existeTrabajador(idTrabajador))
			Utils.dialogoError("El cliente que desea actualizar no está registrado.");
		else if (nombre.isEmpty())
			Utils.dialogoError("Debe introducir un nombre válido.");
		else if (telefono.isEmpty() || !Utils.esEntero(telefono))
			Utils.dialogoError("Debe introducir un número de teléfono válido.");
		else if (!Utils.esEntero(jornada))
			Utils.dialogoError("Debe introducir jornada numerica .");
		else if (!Utils.esEntero(antiguedad))
			Utils.dialogoError("Debe introducir antiguedad numerica .");
		else
			this.fachadaTrabajadoresDB.actualizaTrabajador(idTrabajador, nombre, jornada, email, telefono, antiguedad,
					idiomas);
	}

	@Override
	public ArrayList<Trabajador> buscarTrabajador(String idTrabajador) {
		return this.fachadaTrabajadoresDB.buscarTrabajador(idTrabajador);
	}

	@Override
	public ArrayList<Trabajador> listarTrabajadores() {
		return this.fachadaTrabajadoresDB.listarTrabajadores();
	}

	@Override
	public ArrayList<Trabajador> listarRecepcionistas() {
		return this.fachadaTrabajadoresDB.listarRecepcionistas();
	}

	@Override
	public ArrayList<Trabajador> listarAdministradores() {
		return this.fachadaTrabajadoresDB.listarAdministradores();
	}
}
