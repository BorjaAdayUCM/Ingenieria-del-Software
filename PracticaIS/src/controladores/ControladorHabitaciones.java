package controladores;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Habitacion;
import serviciosAplicacion.FachadaSAHabitacion;

/**
 * Clase ControladorHabitaciones.
 */
public class ControladorHabitaciones {

	/** Fachada SAHabitacion. */
	private FachadaSAHabitacion fachadaSAHabitacion;

	/**
	 * Instancia un controladorHabitaciones.
	 *
	 * @param fachadaSAHabitacion
	 *            del controlador
	 */
	public ControladorHabitaciones(FachadaSAHabitacion fachadaSAHabitacion) {
		this.fachadaSAHabitacion = fachadaSAHabitacion;
	}

	/**
	 * Da de alta una habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	public void altaHabitacion(String idHabitacion, String precio) {
		this.fachadaSAHabitacion.altaHabitacion(idHabitacion, precio);
	}

	/**
	 * Da de baja una habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 */
	public void bajaHabitacion(String idHabitacion) {
		this.fachadaSAHabitacion.bajaHabitacion(idHabitacion);
	}

	/**
	 * Busca una habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @return La habitacion.
	 */
	public ArrayList<Habitacion> buscaHabitacion(String idHabitacion) {
		return this.fachadaSAHabitacion.buscarHabitacion(idHabitacion);
	}

	/**
	 * Listar las habitaciones.
	 *
	 * @return La lista.
	 */
	public ArrayList<Habitacion> listarHabitaciones() {
		return this.fachadaSAHabitacion.listarHabitaciones();
	}

	/**
	 * Actualiza una habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	public void actualizaHabitacion(String idHabitacion, String precio) {
		this.fachadaSAHabitacion.actualizaHabitacion(idHabitacion, precio);
	}

	/**
	 * Añade un observador.
	 *
	 * @param o
	 *            , observador
	 */
	public void addObserver(ObservadorSimulador o) {
		this.fachadaSAHabitacion.addObservador(o);
	}

	/**
	 * Elimina un observador.
	 *
	 * @param o
	 *            , observador
	 */
	public void removeObserver(ObservadorSimulador o) {
		this.fachadaSAHabitacion.removeObservador(o);
	}
}
