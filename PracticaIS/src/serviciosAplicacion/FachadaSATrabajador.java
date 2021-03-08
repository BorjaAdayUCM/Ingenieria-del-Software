package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.trabajadores.Trabajador;

/**
 * The Interface FachadaSATrabajador.
 */
public interface FachadaSATrabajador {

	/**
	 * Alta administrador.
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
	 * Alta recepcionista.
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
	 * Baja trabajador.
	 *
	 * @param idTrabajador
	 *            del trabajador
	 */
	void bajaTrabajador(String idTrabajador);

	/**
	 * Actualiza trabajador.
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
	 * @param idiomas
	 *            del trabajador
	 */
	void actualizaTrabajador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad, String idiomas);

	/**
	 * Buscar trabajador.
	 *
	 * @param idTrabajador
	 *            del trabajador
	 * @return trabajador
	 */
	ArrayList<Trabajador> buscarTrabajador(String idTrabajador);

	/**
	 * Listar trabajadores.
	 *
	 * @return Lista de trabajadores
	 */
	ArrayList<Trabajador> listarTrabajadores();

	/**
	 * Listar recepcionistas.
	 *
	 * @return Lista de recepcionista
	 */
	ArrayList<Trabajador> listarRecepcionistas();

	/**
	 * Listar administradores.
	 *
	 * @return Lista de administradores
	 */
	ArrayList<Trabajador> listarAdministradores();

	/**
	 * Añade un observador.
	 *
	 * @param o
	 *            , observadores
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador.
	 *
	 * @param o
	 *            ,observadores
	 */
	void removeObservador(ObservadorSimulador o);
}
