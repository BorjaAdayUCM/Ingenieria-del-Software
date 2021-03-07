package Swing;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorClientes;
import objetos.Cliente;

@SuppressWarnings("serial")
public class ModeloTablaCliente extends ModeloTabla<Cliente> implements ObservadorSimulador {

	/**
	 * Instancia un modelo tabla cliente.
	 *
	 * @param nombreColumnas
	 * @param ctrl
	 *            de cliente.
	 */
	public ModeloTablaCliente(String[] nombreColumnas, ControladorClientes ctrl) {
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
			s = this.lista.get(indiceFil).getNombre();
			break;
		case 2:
			s = this.lista.get(indiceFil).getTelefono();
			break;
		case 3:
			s = this.lista.get(indiceFil).getEmail();
			break;
		case 4:
			s = Utils.List(this.lista.get(indiceFil).getIdReservas().toString());
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
	public <T> void baja(ArrayList<T> lista) {
		this.lista = (List<Cliente>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.lista = (List<Cliente>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.lista = (List<Cliente>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.lista = (List<Cliente>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void actualiza(ArrayList<T> lista) {
		this.lista = (List<Cliente>) lista;
		this.fireTableStructureChanged();
	}
}
