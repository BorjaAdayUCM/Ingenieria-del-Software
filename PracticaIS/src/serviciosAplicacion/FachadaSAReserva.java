package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Reserva;

// TODO: Auto-generated Javadoc
/**
 * The Interface FachadaSAReserva.
 */
public interface FachadaSAReserva {

	/**
	 * Alta reserva.
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
	void altaReserva(String idCliente, String numPersonas, String numNoches, ArrayList<String> idServicios);

	/**
	 * Baja reserva.
	 *
	 * @param idReserva
	 *            de la reserva
	 */
	void bajaReserva(String idReserva);

	/**
	 * Buscar reserva.
	 *
	 * @param idReserva
	 *            de la reserva
	 * @param idCliente
	 *            de la reserva
	 * @return Reserva
	 */
	ArrayList<Reserva> buscarReserva(String idReserva, String idCliente);

	/**
	 * Listar reservas.
	 *
	 * @return Lista de reservas
	 */
	ArrayList<Reserva> listarReservas();

	/**
	 * Actualiza reserva.
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
	 *            usados por la reserva
	 */
	void actualizaReserva(String idReserva, String idCliente, String numPersonas, String numNoches,
			ArrayList<String> idServicios);

	/**
	 * Getter de servicios usados por una Reserva
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @return Lista de servicios usados por una reserva
	 */
	ArrayList<String> getServiciosReserva(String idReserva);

	/**
	 * Añade un observador.
	 *
	 * @param o
	 *            ,observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador.
	 *
	 * @param o
	 *            , observador
	 */
	void removeObservador(ObservadorSimulador o);

}
