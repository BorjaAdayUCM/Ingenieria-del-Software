package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Servicio;

/**
 * Interfaz FachadaSAServicio.
 */
public interface FachadaSAServicio {

	/**
	 * Alta servicio.
	 *
	 * @param idServicio
	 *            the id servicio
	 * @param precio
	 *            the precio
	 * @param duracion
	 *            the duracion
	 */
	void altaServicio(String idServicio, String precio, String duracion);

	/**
	 * Baja servicio.
	 *
	 * @param idServicio
	 *            the id servicio
	 */
	void bajaServicio(String idServicio);

	/**
	 * Buscar servicio.
	 *
	 * @param idServicio
	 *            the id servicio
	 * @return the array list
	 */
	ArrayList<Servicio> buscarServicio(String idServicio);

	/**
	 * Listar servicios.
	 *
	 * @return the array list
	 */
	ArrayList<Servicio> listarServicios();

	/**
	 * Actualiza servicio.
	 *
	 * @param idServicio
	 *            the id servicio
	 * @param precio
	 *            the precio
	 * @param duracion
	 *            the duracion
	 */
	void actualizaServicio(String idServicio, String precio, String duracion);

	/**
	 * Adds the observador.
	 *
	 * @param o
	 *            the o
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Removes the observador.
	 *
	 * @param o
	 *            the o
	 */
	void removeObservador(ObservadorSimulador o);
}
