package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import Swing.Utils;
import conexionSQLDB.FachadaClientesDB;
import objetos.Cliente;

/**
 * Clase SACliente.
 */
public class SACliente implements FachadaSACliente {

	/** Fachada clientesDB. */
	private FachadaClientesDB fachadaClientesDB;

	/**
	 * Instancia una SACliente.
	 *
	 * @param fachadaClientesDB
	 *            de la SA
	 */
	public SACliente(FachadaClientesDB fachadaClientesDB) {
		this.fachadaClientesDB = fachadaClientesDB;
	}

	@Override
	public void altaCliente(String idCliente, String nombre, String telefono, String email) {
		if (idCliente.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (this.fachadaClientesDB.existeCliente(idCliente))
			Utils.dialogoError("El cliente ya está registrado.");
		else if (nombre.isEmpty())
			Utils.dialogoError("Debe introducir un nombre válido.");
		else if (telefono.isEmpty() || !Utils.esEntero(telefono))
			Utils.dialogoError("Debe introducir un número de teléfono válido.");
		else
			this.fachadaClientesDB.altaCliente(idCliente, nombre, telefono, email);
	}

	@Override
	public void bajaCliente(String idCliente) {
		if (idCliente.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (!this.fachadaClientesDB.existeCliente(idCliente))
			Utils.dialogoError("El cliente que se quiere dar de baja no está registrado.");
		else if (this.fachadaClientesDB.reservasPendientes(idCliente))
			Utils.dialogoError("El cliente tiene reservas pendientes.");
		else
			this.fachadaClientesDB.bajaCliente(idCliente);
	}

	@Override
	public ArrayList<Cliente> buscarCliente(String idCliente) {
		return this.fachadaClientesDB.buscarCliente(idCliente);
	}

	@Override
	public ArrayList<Cliente> listarClientes() {
		return this.fachadaClientesDB.listarClientes();
	}

	@Override
	public void actualizaCliente(String idCliente, String nombre, String telefono, String email) {
		if (idCliente.isEmpty())
			Utils.dialogoError("Debe introducir un DNI válido.");
		else if (!this.fachadaClientesDB.existeCliente(idCliente))
			Utils.dialogoError("El cliente que desea actualizar no está registrado.");
		else if (nombre.isEmpty())
			Utils.dialogoError("Debe introducir un nombre válido.");
		else if (telefono.isEmpty() || !Utils.esEntero(telefono))
			Utils.dialogoError("Debe introducir un número de teléfono válido.");
		else
			this.fachadaClientesDB.actualizaCliente(idCliente, nombre, telefono, email);
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		this.fachadaClientesDB.addObservador(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		this.fachadaClientesDB.removeObservador(o);
	}
}
