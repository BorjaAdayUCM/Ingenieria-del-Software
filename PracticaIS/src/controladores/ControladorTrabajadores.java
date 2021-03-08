package controladores;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.trabajadores.Trabajador;
import serviciosAplicacion.FachadaSATrabajador;

/**
 * The Class ControladorTrabajadores.
 */
public class ControladorTrabajadores {

	/** Fachada SATrabajador. */
	private FachadaSATrabajador fachadaSaTrabajador;

	/**
	 * Instancia un controladorTrabajadores.
	 *
	 * @param fachadaSaTrabajador
	 *            del controlador
	 */
	public ControladorTrabajadores(FachadaSATrabajador fachadaSaTrabajador) {
		this.fachadaSaTrabajador = fachadaSaTrabajador;
	}

	/**
	 * Da de alta un administrador.
	 *
	 * @param idTrabajador
	 *            del trabajador.
	 * @param nombre
	 *            del trabajador.
	 * @param jornada
	 *            del trabajador.
	 * @param email
	 *            del trabajador.
	 * @param telefono
	 *            del trabajador.
	 * @param antiguedad
	 *            del trabajador.
	 */
	public void altaAdministrador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad) {
		this.fachadaSaTrabajador.altaAdministrador(idTrabajador, nombre, jornada, email, telefono, antiguedad);
	}

	/**
	 * Da de alta un recepcionista.
	 *
	 * @param idTrabajador
	 *            del trabajador.
	 * @param nombre
	 *            del trabajador.
	 * @param jornada
	 *            del trabajador.
	 * @param email
	 *            del trabajador.
	 * @param telefono
	 *            del trabajador.
	 * @param idiomas
	 *            del trabajador.
	 */
	public void altaRecepcionista(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String idiomas) {
		this.fachadaSaTrabajador.altaRecepcionista(idTrabajador, nombre, jornada, email, telefono, idiomas);
	}

	/**
	 * Da de baja a un trabajador.
	 *
	 * @param idTrabajador
	 *            del trabajador.
	 */
	public void baja(String idTrabajador) {
		this.fachadaSaTrabajador.bajaTrabajador(idTrabajador);
	}

	/**
	 * Busca un trabajador.
	 *
	 * @param idTrabajador
	 *            del trabajador.
	 * @return La Lista.
	 */
	public ArrayList<Trabajador> busca(String idTrabajador) {
		return this.fachadaSaTrabajador.buscarTrabajador(idTrabajador);
	}

	/**
	 * Lista los trabajadores.
	 *
	 * @return Lista de trabajadores
	 */
	public ArrayList<Trabajador> listarTrabajadores() {
		return this.fachadaSaTrabajador.listarTrabajadores();
	}

	/**
	 * Lista los administradores.
	 *
	 * @return Lista de administradores
	 */
	public ArrayList<Trabajador> listarAdministradores() {
		return this.fachadaSaTrabajador.listarAdministradores();
	}

	/**
	 * Lista los recepcionistas.
	 *
	 * @return Lista de recepcionistas
	 */
	public ArrayList<Trabajador> listarRecepcionistas() {
		return this.fachadaSaTrabajador.listarRecepcionistas();
	}

	/**
	 * Actualiza trabajadores .
	 *
	 * @param idTrabajador
	 *            del trabajador.
	 * @param nombre
	 *            del trabajador.
	 * @param jornada
	 *            del trabajador.
	 * @param email
	 *            del trabajador.
	 * @param telefono
	 *            del trabajador.
	 * @param antiguedad
	 *            del trabajador.
	 * @param idiomas
	 *            del trabajador.
	 */
	public void actualizaTrabajador(String idTrabajador, String nombre, String jornada, String email, String telefono,
			String antiguedad, String idiomas) {
		this.fachadaSaTrabajador.actualizaTrabajador(idTrabajador, nombre, jornada, email, telefono, antiguedad,
				idiomas);
	}

	/**
	 * Añade un observador.
	 *
	 * @param o
	 *            , el observador
	 */
	public void addObserver(ObservadorSimulador o) {
		this.fachadaSaTrabajador.addObservador(o);
	}

	/**
	 * Elimina un observer.
	 *
	 * @param o
	 *            , el observador
	 */
	public void removeObserver(ObservadorSimulador o) {
		this.fachadaSaTrabajador.removeObservador(o);
	}
}