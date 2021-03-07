package conexionSQLDB;

import java.util.ArrayList;
import Swing.ObservadorSimulador;
import objetos.Cliente;

/**
 * Interfaz FachadaClientesDB.
 */
public interface FachadaClientesDB {

	/**
	 * Busca en la DataBase los clientes existentes.
	 *
	 * @return La lista de clientes
	 */
	ArrayList<Cliente> listarClientes();

	/**
	 * Buscar una cliente en la DataBase.
	 *
	 * @param idCliente
	 *            del cliente
	 * @return Una lista con el cliente o vacia
	 */
	ArrayList<Cliente> buscarCliente(String idCliente);

	/**
	 * Da de alta un cliente en la DataBase.
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
	void altaCliente(String idCliente, String nombre, String telefono, String email);

	/**
	 * Da de baja a un cliente de la DataBase.
	 *
	 * @param idCliente
	 *            del cliente
	 */
	void bajaCliente(String idCliente);

	/**
	 * Actualiza cliente.
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
	void actualizaCliente(String idCliente, String nombre, String telefono, String email);

	/**
	 * Comprueba si existe un cliente .
	 *
	 * @param idCliente
	 *            del cliente
	 * @return true, si existe
	 */
	boolean existeCliente(String idCliente);

	/**
	 * Comprueba si un cliente tiene reservas pendientes.
	 *
	 * @param idCliente
	 *            del cliente
	 * @return true, si hay reservas pendientes
	 */
	boolean reservasPendientes(String idCliente);

	/**
	 * Añade el observador a la lista de observadores.
	 *
	 * @param o
	 *            - observador
	 */
	void addObservador(ObservadorSimulador o);

	/**
	 * Elimina el observador de la lista de observadores.
	 *
	 * @param o
	 *            , observador
	 */
	void removeObservador(ObservadorSimulador o);

	/**
	 * Notifica error a los observadores.
	 *
	 * @param err
	 *            , el error
	 */
	void notificaError(Exception err);

	/**
	 * Notifica alta a los observadores.
	 *
	 * @param idCliente
	 *            del Cliente
	 */
	void notificaAlta(String idCliente);

	/**
	 * Notifica baja a los observadores.
	 */
	void notificaBaja();

	/**
	 * Notifica actualiza a los observadores.
	 */
	void notificaActualiza();

	/**
	 * Notifica busca a los observadores.
	 *
	 * @param clientes
	 *            buscado
	 */
	void notificaBusca(ArrayList<Cliente> clientes);

	/**
	 * Notifica listar a los observadores.
	 *
	 * @param clientes
	 *            resultado de listar
	 */
	void notificaListar(ArrayList<Cliente> clientes);

}
