package conexionSQLDB;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.trabajadores.Trabajador;

/**
 * The Interface FachadaTrabajadoresDB.
 */
public interface FachadaTrabajadoresDB {

	/**
	 * Lista los administradores de la DataBase.
	 *
	 * @return the array list
	 */
	ArrayList<Trabajador> listarAdministradores();

	/**
	 * Lista los recepcionistas de la DataBase.
	 *
	 * @return lista de administradores
	 */
	ArrayList<Trabajador> listarRecepcionistas();

	/**
	 * Lista los trabajadores de la DataBase.
	 *
	 * @return lista de recepcionistas
	 */
	ArrayList<Trabajador> listarTrabajadores();

	/**
	 * Busca un trabajador de la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 * @return lista de trabajadores
	 */
	ArrayList<Trabajador> buscarTrabajador(String idTrabajador);

	/**
	 * Da de alta a un administrador en la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 * @param nombre
	 *            del trabajador
	 * @param jornada
	 *            del trabajador
	 * @param email
	 *            del trabajador
	 * @param telefono
	 *            del trabajador
	 * @param antiguedad
	 *            del trabajador
	 */
	void altaAdministrador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad);

	/**
	 * Da de alta a un recepcionista en la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 * @param nombre
	 *            del trabajador
	 * @param jornada
	 *            del trabajador
	 * @param email
	 *            del trabajador
	 * @param telefono
	 *            del trabajador
	 * @param idiomas
	 *            del trabajador
	 */
	void altaRecepcionista(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String idiomas);

	/**
	 * Da de baja a un trabajador de la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 */
	void bajaTrabajador(String idTrabajador);

	/**
	 * Actualiza un trabajador de la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 * @param nombre
	 *            del trabajador
	 * @param jornada
	 *            del trabajador
	 * @param email
	 *            del trabajador
	 * @param telefono
	 *            del trabajador
	 * @param idiomas
	 *            del trabajador
	 * @param antiguedad
	 *            del trabajador
	 *
	 */
	void actualizaTrabajador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad, String idiomas);

	/**
	 * Añade un observador a lista de observadores.
	 * 
	 * @param o
	 *            , observador.
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador de la lista de observadores.
	 *
	 * @param o
	 *            , observador.
	 */
	void removeObservador(ObservadorSimulador o);

	/**
	 * Notifica error a los observadores.
	 *
	 * @param e
	 *            , excepcion
	 */
	void notificaError(Exception e);

	/**
	 * . Notifica alta administrador a los observadores.
	 */
	void notificaAltaAdministrador();

	/**
	 * Notifica alta recepcionista a los observadores.
	 */
	void notificaAltaRecepcionista();

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
	 * @param trabajador
	 *            de la busqueda
	 */
	void notificaBusca(ArrayList<Trabajador> trabajador);

	/**
	 * Notifica listar a los observadores.
	 * 
	 * @param trabajadores
	 *            del listar
	 */
	void notificaListar(ArrayList<Trabajador> trabajadores);

	/**
	 * Comprueba si existe un trabajador en la DataBase.
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 * @return true, si existe
	 */
	boolean existeTrabajador(String idTrabajador);

	/**
	 * Emplea a un trabajador y muestra los recepcionistas
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 */
	void emplearRecepcionista(String idTrabajador);

	/**
	 * Emplea a un trabajador y muestra los administradores
	 * 
	 * @param idTrabajador
	 *            del trabajador
	 */
	void emplearAdministrador(String idTrabajador);
}
