package Swing;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objetos.Servicio;

public abstract class Utils {
	public static ImageIcon loadImage(String s) {
		ImageIcon iconLoad = new ImageIcon(s);
		Image imagen1 = iconLoad.getImage();
		Image otraimg1 = imagen1.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(otraimg1);
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static ArrayList<Servicio> parseServicio(String s) {
		return null;
	}

	public static void dialogoError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void dialogo(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Precio final", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String List(String texto) {
		return texto.substring(1, texto.length() - 1);
	}

	public static boolean esEntero(String numero) {
		try {
			Integer.parseInt(numero);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean esReal(String numero) {
		try {
			Float.parseFloat(numero);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void quit() {
		File file = new File("iconos");
		if (JOptionPane.showOptionDialog(null, "¿Quieres salir?", "Confirmar salida.", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, Utils.loadImage(file.getAbsolutePath() + "/exit.png"), new Object[] { "Si", "No" }, "Si") == 0) {
			System.exit(0);
		}
	}

	public static Image imageIconToImage(ImageIcon imageIcon) {
		Image imgReturn = (Image) imageIcon.getImage();
		return imgReturn;
	}

}