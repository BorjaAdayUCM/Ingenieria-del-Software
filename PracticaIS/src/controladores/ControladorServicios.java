package controladores;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Servicio;
import serviciosAplicacion.FachadaSAServicio;

/**
 * Clase ControladorServicios.
 */
public class ControladorServicios {

	/** Fachada SAServicio. */
	private FachadaSAServicio fachadaSAServicio;

	/**
	 * Instancia un controladorServicios.
	 *
	 * @param fachadaSAServicio
	 *            del controlador
	 */
	public ControladorServicios(FachadaSAServicio fachadaSAServicio) {
		this.fachadaSAServicio = fachadaSAServicio;
	}

	/**
	 * Da de alta servicio.
	 *
	 * @param idServicio
	 *            del servicio.
	 * @param precio
	 *            del servicio.
	 * @param duracion
	 *            del servicio.
	 */
	public void altaServicio(String idServicio, String precio, String duracion) {
		this.fachadaSAServicio.altaServicio(idServicio, precio, duracion);
	}

	/**
	 * Da de baja servicio.
	 *
	 * @param idCliente
	 *            del servicio.
	 */
	public void bajaServicio(String idCliente) {
		this.fachadaSAServicio.bajaServicio(idCliente);
	}

	/**
	 * Busca un servicio.
	 *
	 * @param idServicio
	 *            del servicio.
	 * @return El servicio
	 */
	public ArrayList<Servicio> buscaServicio(String idServicio) {
		return this.fachadaSAServicio.buscarServicio(idServicio);
	}

	/**
	 * Lista los servicios.
	 *
	 * @return Los servicio.
	 */
	public ArrayList<Servicio> listarServicios() {
		return this.fachadaSAServicio.listarServicios();
	}

	/**
	 * Actualiza un servicio.
	 *
	 * @param idServicio
	 *            del servicio.
	 * @param precio
	 *            del servicio.
	 * @param duracion
	 *            del servicio.
	 */
	public void actualizaServicio(String idServicio, String precio, String duracion) {
		this.fachadaSAServicio.actualizaServicio(idServicio, precio, duracion);
	}

	/**
	 * Añade un observador al DAO.
	 *
	 * @param o
	 *            , observador
	 */
	public void addObserver(ObservadorSimulador o) {
		this.fachadaSAServicio.addObservador(o);
	}

	/**
	 * Elimina un observador al DAO
	 *
	 * @param o
	 *            , observador
	 */
	public void removeObserver(ObservadorSimulador o) {
		this.fachadaSAServicio.removeObservador(o);
	}
}