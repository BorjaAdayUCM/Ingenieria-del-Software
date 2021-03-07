package objetos;

import java.util.ArrayList;

/**
 * La clase Cliente.
 */
public class Cliente extends ObjetoSimulacion {

	/** Nombre del Cliente. */
	private String nombre;

	/** Telefono del Cliente. */
	private int telefono;

	/** Email del Cliente. */
	private String email;

	/** Reservas del Cliente. */
	private ArrayList<String> idReservas;

	/***
	 * Instancia a un cliente.
	 * 
	 * @param dni
	 *            del Cliente
	 * @param nombre
	 *            del Cliente
	 * @param telefono
	 *            del Cliente
	 * @param email
	 *            del Cliente
	 * @param idReservas
	 *            del Cliente
	 */
	public Cliente(String dni, String nombre, int telefono, String email, ArrayList<String> idReservas) {
		super(dni);
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.idReservas = idReservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getIdReservas() {
		return idReservas;
	}

	public void setIdReservas(ArrayList<String> idReservas) {
		this.idReservas = idReservas;
	}
}
