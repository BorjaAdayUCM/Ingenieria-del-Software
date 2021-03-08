package objetos.trabajadores;

import objetos.ObjetoSimulacion;

// TODO: Auto-generated Javadoc
/**
 * The Class Trabajador.
 */
public class Trabajador extends ObjetoSimulacion {

	/** Nombre del trabajador. */
	private String nombre;

	/** Jornada del trabajador. */
	private int jornada;

	/** Email del trabajador. */
	private String email;

	/** Telefono del trabajador. */
	private int telefono;

	/** Estado del trabajador. */
	private int activo;

	/** Tipo de trabajador. */
	private String tipo;

	/**
	 * Instancia un trabajador.
	 *
	 * @param dni
	 *            del trabajador
	 * @param nombre
	 *            del trabajador
	 * @param jornada
	 *            del trabajador
	 * @param email
	 *            del trabajador
	 * @param telefono
	 *            del trabajador
	 * @param disponible
	 *            del trabajador
	 * @param tipo
	 *            de trabajador
	 */
	public Trabajador(String dni, String nombre, int jornada, String email, int telefono, int disponible, String tipo) {
		super(dni);
		this.jornada = jornada;
		this.email = email;
		this.telefono = telefono;
		this.nombre = nombre;
		this.activo = disponible;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public boolean isActivo() {
		if (this.activo == 1)
			return true;
		else
			return false;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
