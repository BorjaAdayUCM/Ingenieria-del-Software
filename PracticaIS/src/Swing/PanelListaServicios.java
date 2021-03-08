package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controladores.ControladorServicios;
import objetos.Servicio;

@SuppressWarnings("serial")
public class PanelListaServicios extends JPanel implements ObservadorSimulador {

	private ListModel<Servicio> listModel;
	private JList<Servicio> objList;

	public PanelListaServicios(String titulo, ControladorServicios ctrlServicios) {
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), titulo));
		this.listModel = new ListModel<Servicio>();
		this.objList = new JList<Servicio>(this.listModel);
		this.objList.setFixedCellHeight(20);
		this.objList.setFixedCellWidth(300);
		addCleanSelectionListner(objList);
		this.add(new JScrollPane(objList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		ctrlServicios.addObserver(this);
	}

	private void addCleanSelectionListner(JList<?> list) {
		list.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 99) {
					list.clearSelection();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			};

			@Override
			public void keyReleased(KeyEvent arg0) {
			};
		});
	}

	public List<Servicio> getSelectedItems() {
		List<Servicio> l = new ArrayList<>();
		for (int i : this.objList.getSelectedIndices()) {
			l.add(listModel.getElementAt(i));
		}
		return l;
	}

	public ArrayList<String> getIdServicios() {
		ArrayList<String> l = new ArrayList<>();
		for (int i : this.objList.getSelectedIndices()) {
			l.add(listModel.getElementAt(i).getId());
		}
		return l;
	}

	public void seleccionaServicios(ArrayList<String> idServicios) {
		int[] indices = new int[idServicios.size()];
		for (int i = 0; i < idServicios.size(); i++) {
			for (int j = 0; j < this.listModel.getSize(); j++) {
				if (this.listModel.getElementAt(j).getId().equals(idServicios.get(i)))
					indices[i] = j;
			}
		}
		this.objList.setSelectedIndices(indices);
	}

	public void setList(List<Servicio> lista) {
		this.listModel.setList(lista);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void actualiza(ArrayList<T> lista) {
		this.listModel.setList((List<Servicio>) lista);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void baja(ArrayList<T> lista) {
		this.listModel.setList((List<Servicio>) lista);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.listModel.setList((List<Servicio>) lista);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.listModel.setList((List<Servicio>) lista);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.listModel.setList((List<Servicio>) lista);
	}

	@Override
	public <T> void errorSimulador(ArrayList<T> arrayList, Exception err) {
		// TODO Apéndice de método generado automáticamente

	}

}