package Swing;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorTrabajadores;
import objetos.trabajadores.Administrador;

@SuppressWarnings("serial")
public class ModeloTablaAdministrador extends ModeloTabla<Administrador> implements ObservadorSimulador {

	public ModeloTablaAdministrador(String[] columnIdEventos, ControladorTrabajadores ctrl) {
		super(columnIdEventos);
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
			s = this.lista.get(indiceFil).getJornada();
			break;
		case 3:
			s = this.lista.get(indiceFil).getEmail();
			break;
		case 4:
			s = this.lista.get(indiceFil).getTelefono();
			break;
		case 5:
			s = this.lista.get(indiceFil).getAntiguedad();
			break;
		case 6:
			s = this.lista.get(indiceFil).isActivo() ? "Empleado" : "Desempleado";
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
		this.lista = (List<Administrador>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void baja(ArrayList<T> lista) {
		this.lista = (List<Administrador>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.lista = (List<Administrador>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.lista = (List<Administrador>) lista;
		this.fireTableStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.lista = (List<Administrador>) lista;
		this.fireTableStructureChanged();
	}

}
