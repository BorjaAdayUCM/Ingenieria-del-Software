package objetos;

// TODO: Auto-generated Javadoc
/**
 * Clase Habitacion.
 */
public class Habitacion extends ObjetoSimulacion {

	/** El precio de la habitacion. */
	private float precio;

	/** Disponibilidad de la habitacion. */
	private int disponible;

	/** El idCliente de la habitacion. */
	private String idCliente;

	/**
	 * Instancia un habitacion.
	 *
	 * @param numero
	 *            de la habitacion.
	 * @param precio
	 *            de la habitacion.
	 * @param disponible
	 *            de la habitacion.
	 * @param idCliente
	 *            de la habitacion.
	 */
	public Habitacion(String numero, float precio, int disponible, String idCliente) {
		super(numero);
		this.precio = precio;
		this.disponible = disponible;
		this.setIdCliente(idCliente);
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Getter de disponibilidad
	 * 
	 * @return disponibilidad
	 */
	public int isDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

}
