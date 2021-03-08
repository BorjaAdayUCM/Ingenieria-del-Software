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

import controladores.ControladorHabitaciones;
import objetos.Habitacion;

@SuppressWarnings("serial")
public class JPanelHabitacion extends JPanel {

	private JToolBar toolbar;
	private JTextField AreaPrecio;
	private PanelTabla<Habitacion> tabla;
	private ModeloTablaHabitacion modelo;
	private JTextField AreaId;
	static private final String[] columnIdHabitacion = { "NumHabitacion", "Precio", "Disponible", "Cliente actual" };

	public JPanelHabitacion(ControladorHabitaciones ctrl, boolean accesoTrabajador) {
		this.createToolBar(ctrl, accesoTrabajador);
		this.setLayout(new BorderLayout());
		this.add(this.toolbar, BorderLayout.PAGE_START);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.WEST);

		this.AreaId = new JTextField(20);
		JLabel infoId = new JLabel("ID");
		this.AreaPrecio = new JTextField(20);
		JLabel infoPrecio = new JLabel("Precio");

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 1;
		panelFormulario.add(infoId, c);

		c.gridx = 1;
		c.gridy = 1;
		panelFormulario.add(this.AreaId, c);

		c.gridx = 0;
		c.gridy = 2;
		panelFormulario.add(infoPrecio, c);

		c.gridx = 1;
		c.gridy = 2;
		panelFormulario.add(this.AreaPrecio, c);

		this.modelo = new ModeloTablaHabitacion(columnIdHabitacion, ctrl);
		this.tabla = new PanelTabla<Habitacion>("Habitacion", this.modelo);
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
				AreaId.setText(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
				AreaPrecio.setText(modelo.getValueAt(tabla.getSelectedRow(), 1).toString());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
	}

	private void createToolBar(ControladorHabitaciones ctrl, boolean accesoTrabajador) {

		this.toolbar = new JToolBar();
		this.toolbar.setFloatable(false);
		
		File file = new File("iconos");
		
		JButton botonAlta = new JButton();
		botonAlta.setToolTipText("Dar de alta una Habitacion");
		botonAlta.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta.jpg"));
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.altaHabitacion(AreaId.getText(), AreaPrecio.getText());
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.toolbar.add(botonAlta);

		JButton botonActualiza = new JButton();
		botonActualiza.setToolTipText("Actualiza una Habitacion");
		botonActualiza
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Actualiza.png"));
		botonActualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.actualizaHabitacion(AreaId.getText(), AreaPrecio.getText());
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");

			}
		});
		this.toolbar.add(botonActualiza);

		JButton botonBusca = new JButton();
		botonBusca.setToolTipText("Busca una Habitación");
		botonBusca.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Buscar.png"));
		botonBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.buscaHabitacion(AreaId.getText());
			}
		});
		this.toolbar.add(botonBusca);

		JButton botonLista = new JButton();
		botonLista.setToolTipText("Lista  una Habitacion");
		botonLista.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Listar.jpg"));
		botonLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.listarHabitaciones();
			}
		});
		this.toolbar.add(botonLista);

		JButton botonBaja = new JButton();
		botonBaja.setToolTipText("Dar de baja una Habitacion");
		botonBaja.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Baja.jpg"));
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.bajaHabitacion(AreaId.getText());
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.toolbar.add(botonBaja);
	}

}
