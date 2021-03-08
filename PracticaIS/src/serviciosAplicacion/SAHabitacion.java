package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import Swing.Utils;
import conexionSQLDB.FachadaHabitacionesDB;
import objetos.Habitacion;

/**
 * Clase SAHabitacion.
 */
public class SAHabitacion implements FachadaSAHabitacion {

	/** Fachada habitacionesDB. */
	private FachadaHabitacionesDB fachadaHabitacionesDB;

	/**
	 * Instancia una SAHabitacion.
	 *
	 * @param fachadaHabitacionesDB
	 *            del SA
	 */
	public SAHabitacion(FachadaHabitacionesDB fachadaHabitacionesDB) {
		this.fachadaHabitacionesDB = fachadaHabitacionesDB;
	}

	@Override
	public void altaHabitacion(String idHabitacion, String precio) {
		if (idHabitacion.isEmpty() || !Utils.esEntero(idHabitacion))
			Utils.dialogoError("El id de habitaci�n debe ser num�rico.");
		else if (this.fachadaHabitacionesDB.existeHabitacion(idHabitacion))
			Utils.dialogoError("La habitaci�n ya est� registrada.");
		else if (precio.isEmpty() || !Utils.esReal(precio))
			Utils.dialogoError("Debe introducir un precio v�lido.");
		else
			this.fachadaHabitacionesDB.altaHabitacion(idHabitacion, precio);
	}

	@Override
	public void bajaHabitacion(String idHabitacion) {
		if (idHabitacion.isEmpty() || !Utils.esEntero(idHabitacion))
			Utils.dialogoError("El id de habitaci�n debe ser num�rico.");
		else if (!this.fachadaHabitacionesDB.existeHabitacion(idHabitacion))
			Utils.dialogoError("La habitaci�n que se quiere dar de baja no est� registrada.");
		else if (this.fachadaHabitacionesDB.reservasPendientes(idHabitacion))
			Utils.dialogoError("La habitacion tiene reservas pendientes.");
		else
			this.fachadaHabitacionesDB.bajaHabitacion(idHabitacion);
	}

	@Override
	public ArrayList<Habitacion> buscarHabitacion(String idHabitacion) {
		return this.fachadaHabitacionesDB.buscarHabitacion(idHabitacion);
	}

	@Override
	public ArrayList<Habitacion> listarHabitaciones() {
		return this.fachadaHabitacionesDB.listarHabitaciones();
	}

	@Override
	public void actualizaHabitacion(String idHabitacion, String precio) {
		if (idHabitacion.isEmpty() || !Utils.esEntero(idHabitacion))
			Utils.dialogoError("El id de habitaci�n debe ser num�rico.");
		else if (!this.fachadaHabitacionesDB.existeHabitacion(idHabitacion))
			Utils.dialogoError("La habitacion que desea actualizar no est� registrada.");
		else if (precio.isEmpty() || !Utils.esReal(precio))
			Utils.dialogoError("Debe introducir un precio v�lido.");
		else
			this.fachadaHabitacionesDB.actualizaHabitacion(idHabitacion, precio);
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		this.fachadaHabitacionesDB.addObservador(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		this.fachadaHabitacionesDB.removeObservador(o);
	}

}
