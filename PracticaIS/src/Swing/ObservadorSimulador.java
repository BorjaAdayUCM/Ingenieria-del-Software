package Swing;

import java.util.ArrayList;

public interface ObservadorSimulador {
	public <T> void errorSimulador(ArrayList<T> arrayList, Exception err);

	public <T> void actualiza(ArrayList<T> lista);

	public <T> void baja(ArrayList<T> lista);

	public <T> void alta(ArrayList<T> lista);

	public <T> void listar(ArrayList<T> lista);

	public <T> void buscar(ArrayList<T> lista);
}
