package controladores;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Cliente;
import serviciosAplicacion.FachadaSACliente;

/**
 * Clase ControladorClientes.
 */
public class ControladorClientes {

	/** FachadaSA Cliente. */
	private FachadaSACliente fachadaSACliente;

	/**
	 * Instancia un nuevo controlador clientes.
	 *
	 * @param fachadaSACliente
	 *            the fachada SA cliente
	 */
	public ControladorClientes(FachadaSACliente fachadaSACliente) {
		this.fachadaSACliente = fachadaSACliente;
	}

	/**
	 * Da de alta un cliente.
	 *
	 * @param idCliente
	 *            de cliente.
	 * @param nombre
	 *            de cliente.
	 * @param telefono
	 *            de cliente.
	 * @param email
	 *            de cliente.
	 */
	public void alta(String idCliente, String nombre, String telefono, String email) {
		this.fachadaSACliente.altaCliente(idCliente, nombre, telefono, email);
	}

	/**
	 * Da de baja un cliente.
	 *
	 * @param idCliente
	 *            del cliente
	 */
	public void baja(String idCliente) {
		this.fachadaSACliente.bajaCliente(idCliente);
	}

	/**
	 * Busca un cliente.
	 *
	 * @param idCliente
	 *            del cliente
	 * @return un cliente
	 */
	public ArrayList<Cliente> busca(String idCliente) {
		return this.fachadaSACliente.buscarCliente(idCliente);
	}

	/**
	 * Lista los clientes de la DataBase.
	 *
	 * @return La lista
	 */
	public ArrayList<Cliente> listar() {
		return this.fachadaSACliente.listarClientes();
	}

	/**
	 * Actualiza.
	 *
	 * @param idCliente
	 *            del cliente
	 * @param nombre
	 *            del cliente
	 * @param telefono
	 *            del cliente
	 * @param email
	 *            del cliente
	 */
	public void actualiza(String idCliente, String nombre, String telefono, String email) {
		this.fachadaSACliente.actualizaCliente(idCliente, nombre, telefono, email);
	}

	/**
	 * Añade un observador al DAO.
	 *
	 * @param o
	 *            ,observador
	 */
	public void addObserver(ObservadorSimulador o) {
		this.fachadaSACliente.addObservador(o);
	}

	/**
	 * Elimina un observador del DAO.
	 *
	 * @param o
	 *            , observador
	 */
	public void removeObserver(ObservadorSimulador o) {
		this.fachadaSACliente.removeObservador(o);
	}
}
