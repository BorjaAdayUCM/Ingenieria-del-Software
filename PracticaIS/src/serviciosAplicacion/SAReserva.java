package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import Swing.Utils;
import conexionSQLDB.FachadaReservasDB;
import objetos.Reserva;

// TODO: Auto-generated Javadoc
/**
 * Clase SAReserva.
 */
public class SAReserva implements FachadaSAReserva {

	/** Fachada reservasDB. */
	private FachadaReservasDB fachadaReservasDB;

	/**
	 * Instancia una SAReserva.
	 *
	 * @param fachadaReservasDB
	 *            de la SA
	 */
	public SAReserva(FachadaReservasDB fachadaReservasDB) {
		this.fachadaReservasDB = fachadaReservasDB;
	}

	@Override
	public void altaReserva(String idCliente, String numPersonas, String numNoches, ArrayList<String> idServicios) {
		if (!this.fachadaReservasDB.existeCliente(idCliente))
			Utils.dialogoError("El cliente al que se le asocia la reserva no está registrado.");
		else if (!this.fachadaReservasDB.quedanHabitaciones())
			Utils.dialogoError("No quedan habitaciones disponibles.");
		else if (numNoches.isEmpty() || !Utils.esEntero(numNoches))
			Utils.dialogoError("Debe introducir un número de noches válido.");
		else if (numPersonas.isEmpty() || !Utils.esEntero(numPersonas))
			Utils.dialogoError("Debe introducir un número de personas válido.");
		else
			this.fachadaReservasDB.altaReserva(idCliente, numPersonas, numNoches, idServicios);
	}

	@Override
	public void bajaReserva(String idReserva) {
		if (idReserva.isEmpty() || !Utils.esEntero(idReserva))
			Utils.dialogoError("El id de reserva debe ser numérico.");
		else if (!this.fachadaReservasDB.existeReserva(idReserva))
			Utils.dialogoError("La reserva que se quiere dar de baja no está registrada.");
		else
			this.fachadaReservasDB.bajaReserva(idReserva);
	}

	@Override
	public ArrayList<Reserva> buscarReserva(String idReserva, String idCliente) {
		return this.fachadaReservasDB.buscarReserva(idReserva, idCliente);
	}

	@Override
	public ArrayList<Reserva> listarReservas() {
		return this.fachadaReservasDB.listarReservas();
	}

	@Override
	public void actualizaReserva(String idReserva, String idCliente, String numPersonas, String numNoches,
			ArrayList<String> idServicios) {
		if (idReserva.isEmpty() || !Utils.esEntero(idReserva))
			Utils.dialogoError("El id de reserva debe ser numérico.");
		else if (!this.fachadaReservasDB.existeReserva(idReserva))
			Utils.dialogoError("La reserva que se quiere actualizar no está registrada.");
		else if (!this.fachadaReservasDB.existeCliente(idCliente))
			Utils.dialogoError("El cliente al que se le quiere asociar la reserva no está registrado.");
		else if (numNoches.isEmpty() || !Utils.esEntero(numNoches))
			Utils.dialogoError("Debe introducir un número de noches válido.");
		else if (numPersonas.isEmpty() || !Utils.esEntero(numPersonas))
			Utils.dialogoError("Debe introducir un número de personas válido.");
		else
			this.fachadaReservasDB.actualizaReserva(idReserva, idCliente, numPersonas, numNoches, idServicios);
	}

	@Override
	public ArrayList<String> getServiciosReserva(String idReserva) {
		return this.fachadaReservasDB.getServiciosReserva(idReserva);
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		this.fachadaReservasDB.addObservador(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		this.fachadaReservasDB.removeObservador(o);
	}

}
