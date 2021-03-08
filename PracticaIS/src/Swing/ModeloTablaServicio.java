package Swing;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorServicios;
import objetos.Servicio;

@SuppressWarnings("serial")
public class ModeloTablaServicio extends ModeloTabla<Servicio> implements ObservadorSimulador {

	public ModeloTablaServicio(String[] nombreColumnas, ControladorServicios ctrl) {
		super(nombreColumnas);
		ctrl.addObserver(this);
	}

	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
		case 0:
			s = this.lista.get(indiceFil).getId();
			break;
		case 1:
			s = this.lista.get(indiceFil).getPrecio();
			break;
		case 2:
			s = this.lista.get(indiceFil).getDuracion();
			break;
		default:
			assert (false);
		}
		return s;
	}

	@Override
	public <T> void errorSimulador(ArrayList<T> lista, Exception error) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void actualiza(ArrayList<T> lista) {
		this.lista = (List<Servicio>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void baja(ArrayList<T> lista) {
		this.lista = (List<Servicio>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.lista = (List<Servicio>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.lista = (List<Servicio>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.lista = (List<Servicio>) lista;
		this.fireTableStructureChanged();
	}
}