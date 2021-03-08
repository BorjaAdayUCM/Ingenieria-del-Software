package objetos;

// TODO: Auto-generated Javadoc
/**
 * Clase ObjetoSimulacion.
 */
public abstract class ObjetoSimulacion {

	/** El id del objeto. */
	public String id;

	/**
	 * Instancia un objeto de la simulacion.
	 *
	 * @param id
	 *            del objeto
	 */
	public ObjetoSimulacion(String id) {
		this.id = id;
	}

	/**
	 * Getter de id
	 *
	 * @return El id
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.id;
	}
}
