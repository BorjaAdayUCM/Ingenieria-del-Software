package Swing;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBarraEstado extends JPanel implements ObservadorSimulador {
	private JLabel infoEjecucion;

	public PanelBarraEstado(String mensaje, VentanaPrincipal mainWindow) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.infoEjecucion = new JLabel(mensaje);
		this.add(this.infoEjecucion);
		this.setBorder(BorderFactory.createBevelBorder(1));
		mainWindow.creaObservador(this);
	}

	public void setMensaje(String mensaje) {
		this.infoEjecucion.setText(mensaje);
	}
	// la ventana principal se comunica con el panel

	@Override
	public <T> void actualiza(ArrayList<T> lista) {
		this.infoEjecucion.setText("Actualizacion realizada");
	}

	@Override
	public <T> void baja(ArrayList<T> lista) {
		this.infoEjecucion.setText("Baja realizada");
	}

	@Override
	public <T> void alta(ArrayList<T> lista) {
		this.infoEjecucion.setText("Alta realizada");

	}

	@Override
	public <T> void listar(ArrayList<T> lista) {
		this.infoEjecucion.setText("Listado realizado");
	}

	@Override
	public <T> void buscar(ArrayList<T> lista) {
		this.infoEjecucion.setText("Busqueda realizada");
	}

	@Override
	public <T> void errorSimulador(ArrayList<T> arrayList, Exception err) {
	}

}