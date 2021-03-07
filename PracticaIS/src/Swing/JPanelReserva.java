package Swing;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controladores.ControladorReservas;
import objetos.Reserva;

@SuppressWarnings("serial")
public class JPanelReserva extends JPanel {

	private JToolBar toolBar;
	private JTextField AreaIdReserva;
	private JTextField AreaIdCliente;
	private JTextField AreaNumNoches;
	private JTextField AreaNumPersonas;
	private PanelTabla<Reserva> tabla;
	private ModeloTablaReserva modelo;
	private PanelListaServicios panelListaServicios;
	static private final String[] columnIdReservas = { "IdReserva", "IdCliente", "NumNoches", "NumPersonas",
			"Servicios contratados" };

	public JPanelReserva(ControladorReservas ctrl, PanelListaServicios panelListaServicios) {
		this.setPanelListaServicios(panelListaServicios);
		this.createToolBar(ctrl);
		this.setLayout(new BorderLayout());
		this.add(this.toolBar, BorderLayout.PAGE_START);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.WEST);
		this.add(panelListaServicios, BorderLayout.EAST);

		this.AreaIdReserva = new JTextField(20);
		JLabel infoIdReserva = new JLabel("IdReserva");
		this.AreaIdCliente = new JTextField(20);
		JLabel infoIdCliente = new JLabel("IdCliente");
		this.AreaNumNoches = new JTextField(20);
		JLabel infoNumNoches = new JLabel("NumNoches");
		this.AreaNumPersonas = new JTextField(20);
		JLabel infoNumPersonas = new JLabel("NumPersonas");

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		panelFormulario.add(infoIdReserva, c);

		c.gridx = 1;
		c.gridy = 0;
		panelFormulario.add(this.AreaIdReserva, c);

		c.gridx = 0;
		c.gridy = 1;
		panelFormulario.add(infoIdCliente, c);

		c.gridx = 1;
		c.gridy = 1;
		panelFormulario.add(this.AreaIdCliente, c);

		c.gridx = 0;
		c.gridy = 2;
		panelFormulario.add(infoNumNoches, c);

		c.gridx = 1;
		c.gridy = 2;
		panelFormulario.add(this.AreaNumNoches, c);

		c.gridx = 0;
		c.gridy = 3;
		panelFormulario.add(infoNumPersonas, c);

		c.gridx = 1;
		c.gridy = 3;
		panelFormulario.add(this.AreaNumPersonas, c);

		this.modelo = new ModeloTablaReserva(columnIdReservas, ctrl);
		this.tabla = new PanelTabla<Reserva>("Reserva", this.modelo);
		this.add(this.tabla, BorderLayout.SOUTH);

		this.tabla.getTabla().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				AreaIdReserva.setText(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
				AreaIdCliente.setText(modelo.getValueAt(tabla.getSelectedRow(), 1).toString());
				AreaNumNoches.setText(modelo.getValueAt(tabla.getSelectedRow(), 2).toString());
				AreaNumPersonas.setText(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());
				panelListaServicios.seleccionaServicios(
						ctrl.getServiciosReserva(modelo.getValueAt(tabla.getSelectedRow(), 0).toString()));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
	}

	private void createToolBar(ControladorReservas ctrl) {

		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		
		File file = new File("iconos");
		
		JButton botonAlta = new JButton();
		botonAlta.setToolTipText("Dar de alta una Reserva");
		botonAlta.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta.jpg"));
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.alta(AreaIdCliente.getText(), AreaNumPersonas.getText(), AreaNumNoches.getText(),
						panelListaServicios.getIdServicios());
			}
		});
		this.toolBar.add(botonAlta);

		JButton botonActualiza = new JButton();
		botonActualiza.setToolTipText("Actualiza una Reserva");
		botonActualiza
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Actualiza.png"));
		botonActualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.actualiza(AreaIdReserva.getText(), AreaIdCliente.getText(), AreaNumPersonas.getText(),
						AreaNumNoches.getText(), panelListaServicios.getIdServicios());
			}
		});
		this.toolBar.add(botonActualiza);

		JButton botonBusca = new JButton();
		botonBusca.setToolTipText("Busca una Reserva");
		botonBusca.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Buscar.png"));
		botonBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.busca(AreaIdReserva.getText(), AreaIdCliente.getText());
			}
		});
		this.toolBar.add(botonBusca);

		JButton botonLista = new JButton();
		botonLista.setToolTipText("Lista  una Reserva");
		botonLista.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Listar.jpg"));
		botonLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.listar();
			}
		});
		this.toolBar.add(botonLista);

		JButton botonBaja = new JButton();
		botonBaja.setToolTipText("Dar de baja una Reserva");
		botonBaja.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Baja.jpg"));
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.baja(AreaIdReserva.getText());
			}
		});
		this.toolBar.add(botonBaja);
	}

	public PanelListaServicios getPanelListaServicios() {
		return panelListaServicios;
	}

	public void setPanelListaServicios(PanelListaServicios panelListaServicios) {
		this.panelListaServicios = panelListaServicios;
	}
}
