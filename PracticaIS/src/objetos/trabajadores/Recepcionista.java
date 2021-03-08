package objetos.trabajadores;

/**
 * Clase Recepcionista.
 */
public class Recepcionista extends Trabajador {

	/** Idiomas del recepcionista */
	private String idiomas;

	/**
	 * Instancia un recepcionista.
	 *
	 * @param dni
	 *            del recepcionista
	 * @param nombre
	 *            del recepcionista
	 * @param jornada
	 *            del recepcionista
	 * @param tipo
	 *            del recepcionista
	 * @param email
	 *            del recepcionista
	 * @param telefono
	 *            del recepcionista
	 * @param activa
	 *            del recepcionista
	 * @param idiomas
	 *            del recepcionista
	 */
	public Recepcionista(String dni, String nombre, int jornada, String tipo, String email, int telefono, int activa,
			String idiomas) {
		super(dni, nombre, jornada, email, telefono, activa, "Recepcionista");
		this.idiomas = idiomas;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdioma(String idioma) {
		this.idiomas = idioma;
	}

}
