package conexionSQLDB;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Habitacion;

/**
 * The Interface FachadaHabitacionesDB.
 */
/**
 * @author BorjaAday
 *
 */
public interface FachadaHabitacionesDB {

	/**
	 * Lista las habitaciones de la DataBase.
	 *
	 * @return lista de habitaciones
	 */
	ArrayList<Habitacion> listarHabitaciones();

	/**
	 * Buscar una habitacion en la DataBase.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @return Una lista con la habitacion o vacia
	 */
	ArrayList<Habitacion> buscarHabitacion(String idHabitacion);

	/**
	 * Da de alta una habitacion en la DataBase.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	void altaHabitacion(String idHabitacion, String precio);

	/**
	 * Da de baja una habitacion de la DataBase.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 */
	void bajaHabitacion(String idHabitacion);

	/**
	 * Actualiza una habitacion de la dataBase.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	void actualizaHabitacion(String idHabitacion, String precio);

	/**
	 * Comprueba si existe una habitacion en la DataBase.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @return true, si existe la habitacion
	 */
	boolean existeHabitacion(String idHabitacion);

	/**
	 * Comprueba si una habitacion tiene reservas pendientes.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @return true, si la habitacion tiene reservas pendientes
	 */
	boolean reservasPendientes(String idHabitacion);

	/**
	 * Añade un observador a la lista de observadores
	 *
	 * @param o
	 *            - observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador de la lista de observadores.
	 *
	 * @param o
	 *            - observador
	 */
	void removeObservador(ObservadorSimulador o);

	/**
	 * Notifica error a los observadores.
	 *
	 * @param e
	 *            el error
	 */
	void notificaError(Exception e);

	/**
	 * Notifica alta a los observadores.
	 *
	 * @param idHabitacion
	 *            the id habitacion
	 */
	void notificaAlta(String idHabitacion);

	/**
	 * Notifica baja a los observadores.
	 */
	void notificaBaja();

	/**
	 * Notifica actualiza a los observadores.
	 */
	void notificaActualiza();

	/**
	 * Notifica busca a los observadores.
	 *
	 * @param habitaciones
	 *            ,resultado de la busqueda.
	 */
	void notificaBusca(ArrayList<Habitacion> habitaciones);

	/**
	 * Notifica listar a los observadores.
	 *
	 * @param habitaciones
	 *            , resultado de listar.
	 */
	void notificaListar(ArrayList<Habitacion> habitaciones);

}
