package objetos;

// TODO: Auto-generated Javadoc
/**
 * Clase Servicio
 */
public class Servicio extends ObjetoSimulacion {

	/** Duracion del servicio */
	private int duracion;

	/** Precio del servicio */
	private float precio;

	/**
	 * Instancia un servicio.
	 *
	 * @param nombre
	 *            del servicio
	 * @param duracion
	 *            del servicio
	 * @param precio
	 *            del servicio
	 */
	public Servicio(String nombre, int duracion, float precio) {
		super(nombre);
		this.duracion = duracion;
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
