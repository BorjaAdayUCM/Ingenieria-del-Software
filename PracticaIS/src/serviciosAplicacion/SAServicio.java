package serviciosAplicacion;

import java.util.ArrayList;

import Swing.ObservadorSimulador;
import Swing.Utils;
import conexionSQLDB.FachadaServiciosDB;
import objetos.Servicio;

// TODO: Auto-generated Javadoc
/**
 * Clase SAServicio.
 */
public class SAServicio implements FachadaSAServicio {

	/** Fachada serviciosDB. */
	private FachadaServiciosDB fachadaServiciosDB;

	/**
	 * Instancia una SAServicio.
	 *
	 * @param fachadaServiciosDB
	 *            de la SA
	 */
	public SAServicio(FachadaServiciosDB fachadaServiciosDB) {
		this.fachadaServiciosDB = fachadaServiciosDB;
	}

	@Override
	public void altaServicio(String idServicio, String precio, String duracion) {
		if (idServicio.isEmpty())
			Utils.dialogoError("El id de servicio no puede estar vacío.");
		else if (this.fachadaServiciosDB.existeServicio(idServicio))
			Utils.dialogoError("El servicio ya está registrado.");
		else if (precio.isEmpty() || !Utils.esReal(precio))
			Utils.dialogoError("Debe introducir un precio válido.");
		else if (duracion.isEmpty() || !Utils.esEntero(duracion))
			Utils.dialogoError("Debe introducir una duración válida.");
		else
			this.fachadaServiciosDB.altaServicio(idServicio, precio, duracion);
	}

	@Override
	public void bajaServicio(String idServicio) {
		if (idServicio.isEmpty())
			Utils.dialogoError("El id de servicio no puede estar vacío.");
		else if (this.fachadaServiciosDB.servicioContratado(idServicio))
			Utils.dialogoError("El servicio está contratado actualmente.");
		else if (!this.fachadaServiciosDB.existeServicio(idServicio))
			Utils.dialogoError("El servicio que se quiere dar de baja no está registrado.");
		else
			this.fachadaServiciosDB.bajaServicio(idServicio);
	}

	@Override
	public ArrayList<Servicio> buscarServicio(String idServicio) {
		return this.fachadaServiciosDB.buscarServicio(idServicio);
	}

	@Override
	public ArrayList<Servicio> listarServicios() {
		return this.fachadaServiciosDB.listarServicios();
	}

	@Override
	public void actualizaServicio(String idServicio, String precio, String duracion) {
		if (idServicio.isEmpty())
			Utils.dialogoError("El id de servicio no puede estar vacío.");
		else if (!this.fachadaServiciosDB.existeServicio(idServicio))
			Utils.dialogoError("El servicio que desea actualizar no está registrado.");
		else if (precio.isEmpty() || !Utils.esReal(precio))
			Utils.dialogoError("Debe introducir un precio válido.");
		else if (duracion.isEmpty() || !Utils.esEntero(duracion))
			Utils.dialogoError("Debe introducir una duración válida.");
		else
			this.fachadaServiciosDB.actualizaServicio(idServicio, precio, duracion);
	}

	@Override
	public void addObservador(ObservadorSimulador o) {
		this.fachadaServiciosDB.addObservador(o);
	}

	@Override
	public void removeObservador(ObservadorSimulador o) {
		this.fachadaServiciosDB.removeObservador(o);
	}

}
