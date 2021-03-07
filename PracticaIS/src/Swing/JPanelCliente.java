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
import controladores.ControladorClientes;
import objetos.Cliente;

@SuppressWarnings("serial")
public class JPanelCliente extends JPanel {

	private JToolBar toolBar;
	private JTextField areaDni;
	private JTextField areaNombre;
	private JTextField areaTlf;
	private JTextField areaEmail;
	private PanelTabla<Cliente> tabla;
	private ModeloTablaCliente modelo;
	static private final String[] columnIdCliente = { "DNI", "Nombre", "Telefono", "Email", "Reservas" };

	public JPanelCliente(ControladorClientes ctrl) {
		this.createToolBar(ctrl);
		this.setLayout(new BorderLayout());
		this.add(this.toolBar, BorderLayout.PAGE_START);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.WEST);

		this.areaDni = new JTextField(20);
		JLabel infoDni = new JLabel("DNI");
		this.areaNombre = new JTextField(20);
		JLabel infoNombre = new JLabel("Nombre");
		this.areaTlf = new JTextField(20);
		JLabel infoTlf = new JLabel("Telefono");
		this.areaEmail = new JTextField(20);
		JLabel infoEmail = new JLabel("Email");

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		panelFormulario.add(infoDni, c);

		c.gridx = 1;
		c.gridy = 0;
		panelFormulario.add(this.areaDni, c);

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

		this.modelo = new ModeloTablaCliente(columnIdCliente, ctrl);
		this.tabla = new PanelTabla<Cliente>("Cliente", this.modelo);

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
				areaDni.setText(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
				areaNombre.setText(modelo.getValueAt(tabla.getSelectedRow(), 1).toString());
				areaTlf.setText(modelo.getValueAt(tabla.getSelectedRow(), 2).toString());
				areaEmail.setText(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});

		this.add(this.tabla, BorderLayout.SOUTH);
	}

	private void createToolBar(ControladorClientes ctrl) {

		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		
		File file = new File("iconos");
		
		JButton botonAlta = new JButton();
		botonAlta.setToolTipText("Dar de alta un Cliente");
		botonAlta.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Alta.jpg"));
		botonAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.alta(areaDni.getText(), areaNombre.getText(), areaTlf.getText(), areaEmail.getText());
			}
		});
		this.toolBar.add(botonAlta);

		JButton botonActualiza = new JButton();
		botonActualiza.setToolTipText("Actualiza un Cliente");
		botonActualiza.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Actualiza.png"));
		botonActualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.actualiza(areaDni.getText(), areaNombre.getText(), areaTlf.getText(), areaEmail.getText());
			}
		});
		this.toolBar.add(botonActualiza);

		JButton botonBusca = new JButton();
		botonBusca.setToolTipText("Busca un Cliente");
		botonBusca.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Buscar.png"));
		botonBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.busca(areaDni.getText());
			}
		});
		this.toolBar.add(botonBusca);

		JButton botonLista = new JButton();
		botonLista.setToolTipText("Lista los Clientes");
		botonLista.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Listar.jpg"));
		botonLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.listar();
			}
		});
		this.toolBar.add(botonLista);

		JButton botonBaja = new JButton();
		botonBaja.setToolTipText("Dar de baja a un Cliente");
		botonBaja.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Baja.jpg"));
		botonBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.baja(areaDni.getText());
			}
		});
		this.toolBar.add(botonBaja);
	}
}
