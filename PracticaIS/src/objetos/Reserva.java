package objetos;

import java.util.ArrayList;

/**
 * Clase Reserva.
 */
public class Reserva extends ObjetoSimulacion {

	/** IdCliente de la Reserva. */
	private String idCliente;

	/** Idhabitacion de la Reserva */
	private String idHabitacion;

	/** NumPersonas de la Reserva */
	private int numPersonas;

	/** NumNoches de la Reserva */
	private int numNoches;

	/** Lista de Servicio contratados por la Reserva */
	private ArrayList<String> idServicios;

	/**
	 * Instacia una Reserva.
	 *
	 * @param idReserva
	 *            de la Reserva.
	 * @param idHabitacion
	 *            de la Reserva.
	 * @param idCliente
	 *            de la Reserva.
	 * @param numNoches
	 *            de la Reserva.
	 * @param numPersonas
	 *            de la Reserva.
	 * @param idServicios
	 *            de la Reserva.
	 */
	public Reserva(String idReserva, String idHabitacion, String idCliente, int numNoches, int numPersonas,
			ArrayList<String> idServicios) {
		super(idReserva);
		this.idHabitacion = idHabitacion;
		this.idCliente = idCliente;
		this.numNoches = numNoches;
		this.numPersonas = numPersonas;
		this.idServicios = idServicios;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumNoches() {
		return numNoches;
	}

	public void setNumNoches(int numNoches) {
		this.numNoches = numNoches;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public ArrayList<String> getServicios() {
		return idServicios;
	}

	public void setServicios(ArrayList<String> servicios) {
		this.idServicios = servicios;
	}

	public String getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
}
