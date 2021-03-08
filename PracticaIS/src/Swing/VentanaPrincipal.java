package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controladores.ControladorClientes;
import controladores.ControladorHabitaciones;
import controladores.ControladorReservas;
import controladores.ControladorServicios;
import controladores.ControladorTrabajadores;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	// MODEL PART - VIEW CONTROLLER MODEL
	private ControladorReservas controladorReservas;
	private ControladorServicios controladorServicios;
	private ControladorClientes controladorClientes;
	private ControladorHabitaciones controladorHabitaciones;
	private ControladorTrabajadores controladorTrabajadores;
	public static Border bordePorDefecto = BorderFactory.createLineBorder(Color.black, 2);

	// MENU AND TOOL BAR
	private ToolBar toolbar;
	// STATUS BAR (INFO AT THE BOTTOM OF THE WINDOW)
	private PanelBarraEstado panelBarraEstado;

	// Paneles aux
	private JPanel panelAux;
	private JPanel panelCliente;
	private JPanel panelHabitacion;
	private JPanel panelReservas;
	private JPanel panelServicio;
	private JPanel panelTrabajador;

	public VentanaPrincipal(ControladorClientes controladorClientes, ControladorServicios controladorServicios,
			ControladorHabitaciones controladorHabitaciones, ControladorReservas controladorReservas,
			ControladorTrabajadores controladorTrabajadores, Boolean accesoTrabajador) throws IOException {
		super("NetHotels");
		this.controladorReservas = controladorReservas;
		this.controladorServicios = controladorServicios;
		this.controladorClientes = controladorClientes;
		this.controladorHabitaciones = controladorHabitaciones;
		this.controladorHabitaciones = controladorHabitaciones;
		this.controladorTrabajadores = controladorTrabajadores;
		this.initGUI(accesoTrabajador);
	}

	private void initGUI(boolean accesoTrabajador) throws IOException {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		File file = new File("iconos");
		this.setIconImage(Utils.imageIconToImage(Utils.loadImage(file.getAbsolutePath() + "/logotipo.png")));

		this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			};

			@Override
			public void windowClosed(WindowEvent arg0) {
			};

			@Override
			public void windowClosing(WindowEvent arg0) {
				Utils.quit();
			};

			@Override
			public void windowDeactivated(WindowEvent e) {
			};

			@Override
			public void windowDeiconified(WindowEvent e) {
			};

			@Override
			public void windowIconified(WindowEvent e) {
			};

			@Override
			public void windowOpened(WindowEvent e) {
			};
		});

		JPanel panelPrincipal = this.creaPanelPrincipal();
		this.setContentPane(panelPrincipal);

		// BARRA DE ESTADO INFERIOR
		// (contiene una JLabel para mostrar el estado del programa)
		this.addBarraEstado(panelPrincipal);

		// BARRA DE HERRAMIENTAS
		this.addToolBar(panelPrincipal, accesoTrabajador);

		// FILE CHOOSER

		this.panelAux = this.createPanelAux(accesoTrabajador);
		panelPrincipal.add(this.panelAux, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	private void addToolBar(JPanel panelPrincipal, Boolean accesoAdmin) {
		this.toolbar = new ToolBar(this, accesoAdmin);
		this.toolbar.setFloatable(false);
		panelPrincipal.add(this.toolbar, BorderLayout.WEST);
	}

	private void addBarraEstado(JPanel panelPrincipal) {
		this.panelBarraEstado = new PanelBarraEstado("Bienvenido al simulador !", this);
		panelPrincipal.add(this.panelBarraEstado, BorderLayout.PAGE_END);
	}

	private JPanel creaPanelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		return panelPrincipal;
	}

	private JPanel createPanelAux(boolean accesoTrabajador) {
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		this.panelCliente = new JPanelCliente(this.controladorClientes);
		this.panelServicio = new JPanelServicio(this.controladorServicios, accesoTrabajador);
		this.panelHabitacion = new JPanelHabitacion(this.controladorHabitaciones, accesoTrabajador);
		this.panelReservas = new JPanelReserva(this.controladorReservas,
				new PanelListaServicios("Servicios", this.controladorServicios));
		this.panelTrabajador = new JPanelTrabajador(this.controladorTrabajadores);
		panelAux.add(this.panelCliente);
		this.controladorClientes.listar();
		return panelAux;
	}

	public void creaObservador(ObservadorSimulador o) {
		this.controladorClientes.addObserver(o);
		this.controladorServicios.addObserver(o);
		this.controladorHabitaciones.addObserver(o);
		this.controladorReservas.addObserver(o);
		this.controladorTrabajadores.addObserver(o);
	}

	public void setMensaje(String string) {
		this.panelBarraEstado.setMensaje(string);
	}

	public void modoCliente() {
		this.panelAux.removeAll();
		this.panelAux.add(this.panelCliente);
		this.controladorClientes.listar();
		this.panelAux.revalidate();
		this.panelAux.repaint();
	}

	public void modoHabitacion() {
		this.panelAux.removeAll();
		this.panelAux.add(this.panelHabitacion);
		this.controladorHabitaciones.listarHabitaciones();
		this.panelAux.revalidate();
		this.panelAux.repaint();
	}

	public void modoReservas() {
		this.panelAux.removeAll();
		this.panelAux.add(this.panelReservas);
		this.controladorServicios.listarServicios();
		this.controladorReservas.listar();
		this.panelAux.revalidate();
		this.panelAux.repaint();
	}

	public void modoServicio() {
		this.panelAux.removeAll();
		this.panelAux.add(this.panelServicio);
		this.controladorServicios.listarServicios();
		this.panelAux.revalidate();
		this.panelAux.repaint();
	}

	public void modoTrabajador() {
		this.panelAux.removeAll();
		this.panelAux.add(this.panelTrabajador);
		this.controladorTrabajadores.listarTrabajadores();
		this.panelAux.revalidate();
		this.panelAux.repaint();
	}

}