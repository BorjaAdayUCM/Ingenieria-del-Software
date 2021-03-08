package serviciosAplicacion;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Habitacion;

/**
 * Interfaz FachadaSAHabitacion.
 */
public interface FachadaSAHabitacion {

	/**
	 * Alta habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	void altaHabitacion(String idHabitacion, String precio);

	/**
	 * Baja habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 */
	void bajaHabitacion(String idHabitacion);

	/**
	 * Buscar habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @return La habitacion
	 */
	ArrayList<Habitacion> buscarHabitacion(String idHabitacion);

	/**
	 * Listar habitaciones.
	 *
	 * @return Lista de habitaciones
	 */
	ArrayList<Habitacion> listarHabitaciones();

	/**
	 * Actualiza habitacion.
	 *
	 * @param idHabitacion
	 *            de la habitacion
	 * @param precio
	 *            de la habitacion
	 */
	void actualizaHabitacion(String idHabitacion, String precio);

	/**
	 * Añade un observador.
	 *
	 * @param o
	 *            , observador
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
