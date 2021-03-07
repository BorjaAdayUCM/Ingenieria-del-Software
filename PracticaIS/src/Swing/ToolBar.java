package Swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {

	public ToolBar(VentanaPrincipal mainWindow, boolean accesoTrabajador) {
		super(JToolBar.VERTICAL);

		this.addSeparator(new Dimension(0, 60));
		
		File file = new File("iconos");
		System.out.println(file.getAbsolutePath());
		JButton botonCliente = new JButton();
		botonCliente.setToolTipText("Carga el modo Clientes");
		botonCliente.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Clientes.png"));
		botonCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.modoCliente();
			}
		});
		this.add(botonCliente);

		JButton botonHabitacion = new JButton();
		botonHabitacion.setToolTipText("Carga el modo Habitaciones");
		botonHabitacion
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Habitaciones.png"));
		botonHabitacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.modoHabitacion();
			}
		});
		this.add(botonHabitacion);

		JButton botonReserva = new JButton();
		botonReserva.setToolTipText("Carga el modo Reserva");
		botonReserva.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Reservas.jpg"));
		botonReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.modoReservas();
			}
		});
		this.add(botonReserva);

		JButton botonServicio = new JButton();
		botonServicio.setToolTipText("Carga el modo Servicios");
		botonServicio.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Servicios.png"));
		botonServicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.modoServicio();
			}
		});
		this.add(botonServicio);

		JButton botonTrabajadores = new JButton();
		botonTrabajadores.setToolTipText("Carga el modo Trabajadores");
		botonTrabajadores
				.setIcon(Utils.loadImage(file.getAbsolutePath() + "/Trabajadores.jpg"));
		botonTrabajadores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accesoTrabajador)
					mainWindow.modoTrabajador();
				else
					Utils.dialogoError("Necesitas permisos de Administrador.");
			}
		});
		this.add(botonTrabajadores);

	}

}