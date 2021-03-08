package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import objetos.Cliente;

/**
 * Clase FachadaSACliente.
 */
public interface FachadaSACliente {

	/**
	 * Alta cliente.
	 *
	 * @param idCliente
	 *            del Cliente
	 * @param nombre
	 *            del Cliente
	 * @param telefono
	 *            del Cliente
	 * @param email
	 *            del Cliente
	 */
	void altaCliente(String idCliente, String nombre, String telefono, String email);

	/**
	 * Baja cliente.
	 *
	 * @param idCliente
	 *            del Cliente
	 */
	void bajaCliente(String idCliente);

	/**
	 * Buscar cliente.
	 *
	 * @param idCliente
	 *            del Cliente
	 * @return Cliente
	 */
	ArrayList<Cliente> buscarCliente(String idCliente);

	/**
	 * Listar clientes.
	 *
	 * @return Lista de Clientes
	 */
	ArrayList<Cliente> listarClientes();

	/**
	 * Actualiza cliente.
	 *
	 * @param idCliente
	 *            del Cliente
	 * @param nombre
	 *            del Cliente
	 * @param telefono
	 *            del Cliente
	 * @param email
	 *            del Cliente
	 */
	void actualizaCliente(String idCliente, String nombre, String telefono, String email);

	/**
	 * Añade un observador
	 *
	 * @param o
	 *            ,el observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina un observador
	 *
	 * @param o
	 *            ,el observador
	 */
	void removeObservador(ObservadorSimulador o);

}
