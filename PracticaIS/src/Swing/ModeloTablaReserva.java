package Swing;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorReservas;
import objetos.Reserva;

@SuppressWarnings("serial")

public class ModeloTablaReserva extends ModeloTabla<Reserva> implements ObservadorSimulador {

	public ModeloTablaReserva(String[] nombreColumnas, ControladorReservas ctrl) {
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
			s = this.lista.get(indiceFil).getIdCliente();
			break;
		case 2:
			s = this.lista.get(indiceFil).getNumNoches();
			break;
		case 3:
			s = this.lista.get(indiceFil).getNumPersonas();
			break;
		case 4:
			s = Utils.List(this.lista.get(indiceFil).getServicios().toString());
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
		this.lista = (List<Reserva>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.lista = (List<Reserva>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.lista = (List<Reserva>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.lista = (List<Reserva>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void actualiza(ArrayList<T> lista) {
		this.lista = (List<Reserva>) lista;
		this.fireTableStructureChanged();
	}
}
