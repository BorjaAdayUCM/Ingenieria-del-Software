package main;

import java.io.IOException;
import java.sql.Connection;

import javax.swing.SwingUtilities;

import Swing.VentanaInicioSesion;
import Swing.Utils;
import Swing.VentanaPrincipal;
import conexionSQLDB.ClientesDB;
import conexionSQLDB.DataBaseConnect;
import conexionSQLDB.HabitacionesDB;
import conexionSQLDB.ReservasDB;
import conexionSQLDB.ServiciosDB;
import conexionSQLDB.TrabajadoresDB;
import controladores.ControladorClientes;
import controladores.ControladorHabitaciones;
import controladores.ControladorReservas;
import controladores.ControladorServicios;
import controladores.ControladorTrabajadores;
import serviciosAplicacion.SACliente;
import serviciosAplicacion.SAHabitacion;
import serviciosAplicacion.SAReserva;
import serviciosAplicacion.SAServicio;
import serviciosAplicacion.SATrabajador;

/**
 * Clase principal.
 */
public class Main {

	/** Coneccion con la DataBase accesible desde todo el programa */
	public static Connection cnx;

	/**
	 * Constructora de main.
	 * 
	 * @param args
	 *            en desuso
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		cnx = DataBaseConnect.getConnection();
		new VentanaInicioSesion();
	}

	/**
	 * Crea ventana principal.
	 *
	 * @param accesoTrabajador
	 *            , permisos de administrador
	 */
	public static void creaVentanaPrincipal(boolean accesoTrabajador) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new VentanaPrincipal(new ControladorClientes(new SACliente(new ClientesDB())),
							new ControladorServicios(new SAServicio(new ServiciosDB())),
							new ControladorHabitaciones(new SAHabitacion(new HabitacionesDB())),
							new ControladorReservas(new SAReserva(new ReservasDB())),
							new ControladorTrabajadores(new SATrabajador(new TrabajadoresDB())), accesoTrabajador);
				} catch (IOException e) {
					Utils.dialogoError("Error inesperado al inicio del programa");
				}
			}
		});
	}

}
