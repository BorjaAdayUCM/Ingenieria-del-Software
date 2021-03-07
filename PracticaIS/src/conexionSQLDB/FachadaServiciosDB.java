package conexionSQLDB;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Servicio;

/**
 * Interfaz FachadaServiciosDB.
 */
public interface FachadaServiciosDB {

	/**
	 * Lista los servicios.
	 *
	 * @return Los servicios.
	 */
	ArrayList<Servicio> listarServicios();

	/**
	 * Busca un servicio en la DataBase.
	 * 
	 * @param idServicio
	 *            del servicio
	 * @return El servicio.
	 */
	ArrayList<Servicio> buscarServicio(String idServicio);

	/**
	 * Da de alta un servicio en la Database.
	 * 
	 * @param idServicio
	 *            del servicio
	 * @param precio
	 *            del servicio
	 * @param duracion
	 *            del servicio
	 */
	void altaServicio(String idServicio, String precio, String duracion);

	/**
	 * Da de baja a un servicio de la DataBase.
	 * 
	 * @param idServicio
	 *            del servicio
	 */
	void bajaServicio(String idServicio);

	/**
	 * Actualiza un servicio de la DataBase.
	 * 
	 * @param idServicio
	 *            del servicio
	 * @param precio
	 *            del servicio
	 * @param duracion
	 *            del servicio
	 */
	void actualizaServicio(String idServicio, String precio, String duracion);

	/**
	 * Comprueba si existe un Servicio en la DataBase.
	 * 
	 * @param idServicio
	 *            del servicio
	 * @return true, si existe.
	 */
	boolean existeServicio(String idServicio);

	/**
	 * Comprueba si un Servicio esta contratado.
	 * 
	 * @param idServicio
	 *            del servicio
	 * @return true, si esta contratado.
	 */
	boolean servicioContratado(String idServicio);

	/**
	 * Añade un observador a la lista de observadores.
	 * 
	 * @param o
	 *            , observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador de la lista de observadores.
	 * 
	 * @param o
	 *            , observador
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
	 * Notifica busca a los observadores.
	 *
	 * @param servicios
	 *            ,resultado de la busqueda.
	 */
	void notificaBusca(ArrayList<Servicio> servicios);

	/**
	 * Notifica listar a los observadores.
	 *
	 * @param servicios
	 *            ,resultado de listar.
	 */
	void notificaListar(ArrayList<Servicio> servicios);

}
