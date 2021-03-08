package conexionSQLDB;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Reserva;


/**
 * @author BorjaAday
 *
 */
public interface FachadaReservasDB {

	/**
	 * Lista las reservas de la DataBase.
	 *
	 * @return Las reservas
	 */
	ArrayList<Reserva> listarReservas();

	/**
	 * Busca una reserva en la DataBase.
	 *
	 * @param idReserva
	 *            de la reserva
	 * @param idCliente
	 *            de la reserva
	 * @return La reserva
	 */
	ArrayList<Reserva> buscarReserva(String idReserva, String idCliente);

	/**
	 * Da de alta a una reserva en la DataBase.
	 * 
	 * @param idCliente
	 *            de la reserva
	 * @param numPersonas
	 *            de la reserva
	 * @param numNoches
	 *            de la reserva
	 * @param idServicios
	 *            de la reserva
	 **/
	void altaReserva(String idCliente, String numPersonas, String numNoches, ArrayList<String> idServicios);

	/**
	 * Da de baja a una reserva en la DataBase.
	 * 
	 * @param idReserva
	 *            de la reserva
	 */
	void bajaReserva(String idReserva);

	/**
	 * Actualiza una reserva de la DataBase.
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
	void actualizaReserva(String idReserva, String idCliente, String numPersonas, String numNoches,
			ArrayList<String> idServicios);

	/**
	 * Comprueba si existe una reserva en la DataBase.
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @return true, si existe la reserva.
	 */
	boolean existeReserva(String idReserva);

	/**
	 * Comprueba si existe un cliente en la DataBase;
	 * 
	 * @param idCliente
	 *            del cliente
	 * @return true,si existe la reserva.
	 */
	boolean existeCliente(String idCliente);

	/**
	 * Comprueba si quedan habitaciones libres.
	 *
	 * @return true,si quedan habitaciones libres.
	 */
	boolean quedanHabitaciones();

	/**
	 * Get Servicios Reserva
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @return Los servicios usados por la reserva
	 */
	ArrayList<String> getServiciosReserva(String idReserva);

	/**
	 * Añade un observador a la lista de observadores.
	 * 
	 * @param o
	 *            , observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador a la lista de observadores.
	 * 
	 * @param o
	 *            - Observador
	 */
	void removeObservador(ObservadorSimulador o);

	/**
	 * Notifica error a los observadores.
	 * 
	 * @param err
	 *            , error
	 */
	void notificaError(Exception err);

	/**
	 * Notifica alta a los observadores.
	 */
	void notificaAlta();

	/**
	 * Notifica baja a los observadores.
	 */
	void notificaBaja();

	/**
	 * Notifica actualiza a los observadores.
	 */
	void notificaActualiza();

	/**
	 * Calcula el precio de una reserva
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @return precio final de una reserva
	 */
	float precioReserva(String idReserva);

	/**
	 * Notifica busca a los observadores.
	 *
	 * @param reservas
	 *            , resultado de la busqueda
	 */
	void notificaBusca(ArrayList<Reserva> reservas);

	/**
	 * Notifica listar a los observadores.
	 *
	 * @param reservas
	 *            , resultado de listar
	 */
	void notificaListar(ArrayList<Reserva> reservas);

}
