package objetos.trabajadores;

/**
 * Clase Administrador.
 */
public class Administrador extends Trabajador {

	/** Antiguedad de un trabajador. */
	private int antiguedad;

	/**
	 * Instancia un nuevo administrador.
	 * 
	 * @param dni
	 *            del Administrador
	 * @param nombre
	 *            del Administrador
	 * @param jornada
	 *            del Administrador
	 * @param email
	 *            del Administrador
	 * @param telefono
	 *            del Administrador
	 * @param activo
	 *            del Administrador
	 * @param antiguedad
	 *            del Administrador
	 */
	public Administrador(String dni, String nombre, int jornada, String email, int telefono, int activo,
			int antiguedad) {
		super(dni, nombre, jornada, email, telefono, activo, "Administrador");
		this.antiguedad = antiguedad;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

}
