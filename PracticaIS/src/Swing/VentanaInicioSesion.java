package Swing;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;

@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame {

	private JTextField areaUsuario;
	private JTextField areaContrasena;

	public VentanaInicioSesion() {
		super();
		this.setLayout(new GridLayout(3, 0));
		
		this.setIconImage(Utils.imageIconToImage(Utils.loadImage(new File("iconos").getAbsolutePath() + "/iniciosesion.png")));
		
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());
		this.add(panelFormulario, BorderLayout.PAGE_START);

		this.areaUsuario = new JTextField(20);
		JLabel infoUsuario = new JLabel("Usuario: ");
		this.areaContrasena = new JPasswordField(20);
		JLabel infoContrasena = new JLabel("Contraseña: ");

		JButton botonIniciarSesion = new JButton();
		botonIniciarSesion.setText("Iniciar sesión");
		botonIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = Main.cnx.createStatement()
							.executeQuery("SELECT * FROM TRABAJADORES WHERE UPPER(ID_TRABAJADOR) = " + "UPPER('"
									+ areaUsuario.getText() + "')");
					if (rs.next() && rs.getInt("TELEFONO_TRABAJADOR") == Integer.parseInt(areaContrasena.getText())) {
						String s = rs.getString("TIPO_TRABAJADOR");
						if (s.equalsIgnoreCase("administrador"))
							Main.creaVentanaPrincipal(true);
						else
							Main.creaVentanaPrincipal(false);
						setNotVisible();
					} else {
						Utils.dialogoError("Usuario o contraseña incorrectos.");
					}
				} catch (SQLException | NumberFormatException ex) {
					if (ex instanceof NumberFormatException)
						Utils.dialogoError("La contraseña es el número de teléfono, debe ser numérica.");
					else
						Utils.dialogoError("Error al inicio de sesión.");
				}
			}
		});

		this.add(botonIniciarSesion);

		JButton botonCancelar = new JButton();
		botonCancelar.setText("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utils.quit();
			}
		});
		this.add(botonCancelar);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		panelFormulario.add(infoUsuario, c);

		c.gridx = 1;
		c.gridy = 0;
		panelFormulario.add(this.areaUsuario, c);

		c.gridx = 0;
		c.gridy = 1;
		panelFormulario.add(infoContrasena, c);

		c.gridx = 1;
		c.gridy = 1;
		panelFormulario.add(this.areaContrasena, c);

		this.setTitle("Inicio de sesión");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(500, 400);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();

	}

	private void setNotVisible() {
		this.setVisible(false);
	}

}
