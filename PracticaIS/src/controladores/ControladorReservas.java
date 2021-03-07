package controladores;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Reserva;
import serviciosAplicacion.FachadaSAReserva;

/**
 * Clase ControladorReservas.
 */
public class ControladorReservas {

	/** Fachada SAReserva. */
	private FachadaSAReserva fachadaSAReserva;

	/**
	 * Instancia un controladorReservas.
	 *
	 * @param fachadaSAReserva
	 *            del controlador
	 */
	public ControladorReservas(FachadaSAReserva fachadaSAReserva) {
		this.fachadaSAReserva = fachadaSAReserva;
	}

	/**
	 * Da de alta una reserva.
	 * 
	 * @param idCliente
	 *            de la reserva
	 * @param numPersonas
	 *            de la reserva
	 * @param numNoches
	 *            de la reserva
	 * @param idServicios
	 *            de la reserva
	 */
	public void alta(String idCliente, String numPersonas, String numNoches, ArrayList<String> idServicios) {
		this.fachadaSAReserva.altaReserva(idCliente, numPersonas, numNoches, idServicios);
	}

	/**
	 * Da de baja una reserva
	 *
	 * @param idReserva
	 *            de la reserva
	 */
	public void baja(String idReserva) {
		this.fachadaSAReserva.bajaReserva(idReserva);
	}

	/**
	 * Busca una reserva.
	 *
	 * @param idReserva
	 *            de la reserva
	 * @param idCliente
	 *            de la reserva
	 * @return La reserva de la reserva
	 */
	public ArrayList<Reserva> busca(String idReserva, String idCliente) {
		return this.fachadaSAReserva.buscarReserva(idReserva, idCliente);
	}

	/**
	 * Lista las reservas.
	 *
	 * @return La lista
	 */
	public ArrayList<Reserva> listar() {
		return this.fachadaSAReserva.listarReservas();
	}

	/**
	 * Actualiza una reserva.
	 *
	 * @param idReserva
	 *            de la reserva
	 * @param idCliente
	 *            de la reserva
	 * @param numPersonas
	 *            de la reserva
	 * @param numNoches
	 *            de la reserva
	 * @param idServicios
	 *            de la reserva
	 */
	public void actualiza(String idReserva, String idCliente, String numPersonas, String numNoches,
			ArrayList<String> idServicios) {
		this.fachadaSAReserva.actualizaReserva(idReserva, idCliente, numPersonas, numNoches, idServicios);
	}

	/**
	 * Getter de Servicios Reserva
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @return Lista de servicios usados en una reserva
	 */
	public ArrayList<String> getServiciosReserva(String idReserva) {
		return this.fachadaSAReserva.getServiciosReserva(idReserva);
	}

	/**
	 * Añade el observador.
	 *
	 * @param o
	 *            , el observador.
	 */
	public void addObserver(ObservadorSimulador o) {
		this.fachadaSAReserva.addObservador(o);
	}

	/**
	 * Elimina el observador.
	 *
	 * @param o
	 *            , el observador.
	 */
	public void removeObserver(ObservadorSimulador o) {
		this.fachadaSAReserva.removeObservador(o);
	}
}