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
import javax.swing.table.DefaultTableModel;

import controladores.ControladorTrabajadores;
import objetos.trabajadores.Trabajador;

@SuppressWarnings("serial")
public class JPanelTrabajador extends JPanel {

	private JToolBar toolBar;
	private JTextField areaId;
	private JTextField areaNombre;
	private JTextField areaJornada;
	private JTextField areaEmail;
	private JTextField areaTlf;
	private JTextField areaIdiomas;
	private JTextField areaAntiguedad;
	private PanelTabla<Trabajador> tabla;
	private ModeloTablaAdministrador modeloAdmin;
	private ModeloTablaRecepcionista modeloRecepcionista;
	private ModeloTablaTrabajador modeloTrabajador;

	static private final String[] columnIdTrabajador = { "ID", "Nombre", "Jornada", "Email", "Telefono", "Situación",
			"Tipo" };
	static private final String[] columnIdAdministrador = { "ID", "Nombre", "Jornada", "Email", "Telefono",
			"Antigüedad", "Situación" };
	static private final String[] columnIdRecepcionista = { "ID", "Nombre", "Jornada", "Email", "Telefono", "Idiomas",
			"Situación" };

	public JPanelTrabajador(ControladorTrabajadores ctrl) {
		this.createToolBar(ctrl);
		this.setLayout(new BorderLayout());
		this.add(this.toolBar, BorderLayout.PAGE_START);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.WEST);

		this.areaId = new JTextField(20);
		JLabel infoId = new JLabel("Dni");
		this.areaNombre = new JTextField(20);
		JLabel infoNombre = new JLabel("Nombre");
		this.areaJornada = new JTextField(20);
		JLabel infoJornada = new JLabel("Jornada");
		this.areaEmail = new JTextField(20);
		JLabel infoEmail = new JLabel("Email");
		this.areaTlf = new JTextField(20);
		JLabel infoTlf = new JLabel("      Telefono");
		this.areaAntiguedad = new JTextField(20);
		JLabel infoAntiguedad = new JLabel("      Antiguedad");
		this.areaIdiomas = new JTextField(20);
		JLabel infoIdiomas = new JLabel("      Idiomas");

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		panelFormulario.add(infoId, c);

		c.gridx = 1;
		c.gridy = 0;
		panelFormulario.add(this.areaId, c);

		c.gridx = 0;
		c.gridy = 1;
		panelFormulario.add(infoNombre, c);

		c.gridx = 1;
		c.gridy = 1;
		panelFormulario.add(this.areaNombre, c);

		c.gridx = 0;
		c.gridy = 2;
		panelFormulario.add(infoTlf, c);

		c.gridx = 1;
		c.gridy = 2;
		panelFormulario.add(this.areaTlf, c);

		c.gridx = 0;
		c.gridy = 3;
		panelFormulario.add(infoEmail, c);

		c.gridx = 1;
		c.gridy = 3;
		panelFormulario.add(this.areaEmail, c);

		c.gridx = 3;
		c.gridy = 0;
		panelFormulario.add(infoJornada, c);

		c.gridx = 4;
		c.gridy = 0;
		panelFormulario.add(this.areaJornada, c);

		c.gridx = 3;
		c.gridy = 1;
		panelFormulario.add(infoIdiomas, c);

		c.gridx = 4;
		c.gridy = 1;
		panelFormulario.add(this.areaIdiomas, c);

		c.gridx = 3;
		c.gridy = 2;
		panelFormulario.add(infoAntiguedad, c);

		c.gridx = 4;
		c.gridy = 2;
		panelFormulario.add(this.areaAntiguedad, c);

		this.modeloAdmin = new ModeloTablaAdministrador(columnIdAdministrador, ctrl);
		this.modeloRecepcionista = new ModeloTablaRecepcionista(columnIdRecepcionista, ctrl);
		this.modeloTrabajador = new ModeloTablaTrabajador(columnIdTrabajador, ctrl);

		this.tabla = new PanelTabla<Trabajador>("Trabajador", this.modeloTrabajador);

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
				DefaultTableModel m = tabla.getModel();
				areaId.setText(m.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaNombre.setText(m.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaJornada.setText(m.getValueAt(tabla.getSelectedRow(), 2).toString());
				areaEmail.setText(m.getValueAt(tabla.getSelectedRow(), 3).toString());
				areaTlf.setText(m.getValueAt(tabla.getSelectedRow(), 4).toString());
				areaAntiguedad.setText("");
				areaIdiomas.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});

		this.add(this.tabla, BorderLayout.SOUTH);
	}

	private void createToolBar(ControladorTrabajadores ctrl) {
		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		File file = new File("iconos");
		
		JButton botonAltaAdmin = new JButton();
		botonAltaAdmin.setToolTipText("Dar de alta un Administrador");
		botonAltaAdmin.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta.jpg"));
		botonAltaAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaAdmin();
				ctrl.altaAdministrador(areaId.getText(), areaNombre.getText(), areaJornada.getText(),
						areaEmail.getText(), areaTlf.getText(), areaAntiguedad.getText());
			}
		});
		this.toolBar.add(botonAltaAdmin);

		JButton botonAltaRecep = new JButton();
		botonAltaRecep.setToolTipText("Dar de alta un Recepcionista");
		botonAltaRecep.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta2.jpg"));
		botonAltaRecep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaRecep();
				ctrl.altaRecepcionista(areaId.getText(), areaNombre.getText(), areaJornada.getText(),
						areaEmail.getText(), areaTlf.getText(), areaIdiomas.getText());
			}
		});
		this.toolBar.add(botonAltaRecep);

		JButton botonActualiza = new JButton();
		botonActualiza.setToolTipText("Actualiza un trabajador");
		botonActualiza
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Actualiza.png"));
		botonActualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaTrabajador();
				ctrl.actualizaTrabajador(areaId.getText(), areaNombre.getText(), areaJornada.getText(),
						areaEmail.getText(), areaTlf.getText(), areaAntiguedad.getText(), areaIdiomas.getText());
			}
		});
		this.toolBar.add(botonActualiza);

		JButton botonBusca = new JButton();
		botonBusca.setToolTipText("Busca un Trabajador");
		botonBusca.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Buscar.png"));
		botonBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaTrabajador();
				ctrl.busca(areaId.getText());
			}
		});
		this.toolBar.add(botonBusca);

		JButton botonListaAdministrador = new JButton();
		botonListaAdministrador.setToolTipText("Lista los Administradores");
		botonListaAdministrador.setIcon(
				Utils.loadImage(file.getAbsolutePath() + "/Listar_Administradores.jpg"));
		botonListaAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaAdmin();
				ctrl.listarAdministradores();
			}
		});
		this.toolBar.add(botonListaAdministrador);

		JButton botonListaRecepcionista = new JButton();
		botonListaRecepcionista.setToolTipText("Lista los Recepcionistas");
		botonListaRecepcionista.setIcon(
				Utils.loadImage(file.getAbsolutePath() + "/Listar_Recepcionistas.jpg"));
		botonListaRecepcionista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaRecep();
				ctrl.listarRecepcionistas();
			}
		});
		this.toolBar.add(botonListaRecepcionista);

		JButton botonLista = new JButton();
		botonLista.setToolTipText("Lista los Trabajadores");
		botonLista.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Listar.jpg"));
		botonLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaTrabajador();
				ctrl.listarTrabajadores();
			}
		});
		this.toolBar.add(botonLista);

		JButton botonBaja = new JButton();
		botonBaja.setToolTipText("Dar de baja a un Trabajador");
		botonBaja.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Baja.jpg"));
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTablaTrabajador();
				ctrl.baja(areaId.getText());
			}
		});
		this.toolBar.add(botonBaja);
	}

	private void setTablaTrabajador() {
		this.tabla.setModel("Trabajador", this.modeloTrabajador);
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
				DefaultTableModel m = tabla.getModel();
				areaId.setText(m.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaNombre.setText(m.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaJornada.setText(m.getValueAt(tabla.getSelectedRow(), 2).toString());
				areaEmail.setText(m.getValueAt(tabla.getSelectedRow(), 3).toString());
				areaTlf.setText(m.getValueAt(tabla.getSelectedRow(), 4).toString());
				areaAntiguedad.setText("");
				areaIdiomas.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
	}

	private void setTablaAdmin() {
		this.tabla.setModel("Administrador", this.modeloAdmin);
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
				DefaultTableModel m = tabla.getModel();
				areaId.setText(m.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaNombre.setText(m.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaJornada.setText(m.getValueAt(tabla.getSelectedRow(), 2).toString());
				areaEmail.setText(m.getValueAt(tabla.getSelectedRow(), 3).toString());
				areaTlf.setText(m.getValueAt(tabla.getSelectedRow(), 4).toString());
				areaAntiguedad.setText(m.getValueAt(tabla.getSelectedRow(), 5).toString());
				areaIdiomas.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
	}

	private void setTablaRecep() {
		this.tabla.setModel("Recepcionista", this.modeloRecepcionista);
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
				DefaultTableModel m = tabla.getModel();
				areaId.setText(m.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaNombre.setText(m.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaJornada.setText(m.getValueAt(tabla.getSelectedRow(), 2).toString());
				areaEmail.setText(m.getValueAt(tabla.getSelectedRow(), 3).toString());
				areaTlf.setText(m.getValueAt(tabla.getSelectedRow(), 4).toString());
				areaIdiomas.setText(tabla.getModel().getValueAt(tabla.getSelectedRow(), 5).toString());
				areaAntiguedad.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
	}
}
