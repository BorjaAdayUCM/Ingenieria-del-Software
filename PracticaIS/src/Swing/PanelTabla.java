package Swing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoSimulacion;

@SuppressWarnings("serial")
public class PanelTabla<T extends ObjetoSimulacion> extends JPanel {

	private JTable tabla;
	private DefaultTableModel model;

	public PanelTabla(String bordeId, DefaultTableModel modelo) {
		this.setLayout(new GridLayout(1, 1));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), bordeId));
		this.model = modelo;
		this.tabla = new JTable(modelo);
		this.add(new JScrollPane(tabla));
	}

	public void setModel(String bordeId, DefaultTableModel model) {
		this.removeAll();
		this.model = model;
		this.tabla = new JTable(this.model);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), bordeId));
		this.add(new JScrollPane(tabla));
		this.revalidate();
		this.repaint();
	}

	public DefaultTableModel getModel() {
		return this.model;
	}

	public int getSelectedRow() {
		return this.tabla.getSelectedRow();
	}

	public JTable getTabla() {
		return this.tabla;
	}
}
