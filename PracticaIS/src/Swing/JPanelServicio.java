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

import controladores.ControladorServicios;
import objetos.Servicio;

@SuppressWarnings("serial")
public class JPanelServicio extends JPanel {

	private JToolBar toolBar;
	private JTextField areaId;
	private JTextField areaPrecio;
	private JTextField areaDuracion;
	private PanelTabla<Servicio> tabla;
	private ModeloTablaServicio modelo;
	static private final String[] columnIdServicio = { "IdServicio", "Precio", "Duracion" };

	public JPanelServicio(ControladorServicios ctrl, boolean accesoTrabajador) {
		this.createToolBar(ctrl, accesoTrabajador);
		this.setLayout(new BorderLayout());
		this.add(this.toolBar, BorderLayout.PAGE_START);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.WEST);

		this.areaId = new JTextField(20);
		JLabel infoId = new JLabel("IdServicio:");
		this.areaPrecio = new JTextField(20);
		JLabel infoPrecio = new JLabel("Precio:");
		this.areaDuracion = new JTextField(20);
		JLabel infoDuracion = new JLabel("Duracion:");

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 1;
		panelFormulario.add(infoId, c);

		c.gridx = 1;
		c.gridy = 1;
		panelFormulario.add(this.areaId, c);

		c.gridx = 0;
		c.gridy = 2;
		panelFormulario.add(infoPrecio, c);

		c.gridx = 1;
		c.gridy = 2;
		panelFormulario.add(this.areaPrecio, c);

		c.gridx = 0;
		c.gridy = 3;
		panelFormulario.add(infoDuracion, c);

		c.gridx = 1;
		c.gridy = 3;
		panelFormulario.add(this.areaDuracion, c);

		this.modelo = new ModeloTablaServicio(columnIdServicio, ctrl);
		this.tabla = new PanelTabla<Servicio>("Servicio", this.modelo);

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
				areaId.setText(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaPrecio.setText(modelo.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaDuracion.setText(modelo.getValueAt(tabla.getSelectedRow(), 2).toString());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});

		this.add(this.tabla, BorderLayout.SOUTH);
	}

	private void createToolBar(ControladorServicios ctrl, boolean accesoTrabajador) {
		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		
		File file = new File("iconos");
		
		JButton botonAlta = new JButton();
		botonAlta.setToolTipText("Dar de alta un Servicio");
		botonAlta.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta.jpg"));
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.altaServicio(areaId.getText(), areaPrecio.getText(), areaDuracion.getText());
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.toolBar.add(botonAlta);

		JButton botonActualiza = new JButton();
		botonActualiza.setToolTipText("Actualiza un Servicio");
		botonActualiza
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Actualiza.png"));
		botonActualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.actualizaServicio(areaId.getText(), areaPrecio.getText(), areaDuracion.getText());
			}
		});
		this.toolBar.add(botonActualiza);

		JButton botonBusca = new JButton();
		botonBusca.setToolTipText("Busca un Servicio");
		botonBusca.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Buscar.png"));
		botonBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.buscaServicio(areaId.getText());
			}
		});
		this.toolBar.add(botonBusca);

		JButton botonLista = new JButton();
		botonLista.setToolTipText("Lista los Servicio");
		botonLista.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Listar.jpg"));
		botonLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.listarServicios();
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.toolBar.add(botonLista);

		JButton botonBaja = new JButton();
		botonBaja.setToolTipText("Dar de baja a un Servicio");
		botonBaja.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Baja.jpg"));
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					ctrl.bajaServicio(areaId.getText());
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.toolBar.add(botonBaja);
	}
}