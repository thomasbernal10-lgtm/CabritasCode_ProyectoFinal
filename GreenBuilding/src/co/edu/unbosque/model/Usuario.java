package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase que representa un usuario del sistema GreenBuilding Manager. Contiene
 * las credenciales de acceso, el rol asignado y la información de vinculación
 * con el conjunto residencial y el residente correspondiente. <br>
 * <b>pre</b> El username y el rol no deben ser null ni vacíos. <br>
 * <b>post</b> El objeto Usuario queda disponible para ser persistido o
 * transferido entre las capas del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Usuario implements Serializable {

	/** Identificador de versión para la serialización de la clase. */
	private static final long serialVersionUID = 7542836229990252814L;

	/** Constante para el rol de super administrador. */
	private final String ROL_SUPER_ADMIN = "SUPER_ADMIN";
	/** Constante para el rol de administrador. */
	private final String ROL_ADMINISTRADOR = "ADMINISTRADOR";
	/** Constante para el rol de vigilante. */
	private final String ROL_VIGILANTE = "VIGILANTE";
	/** Constante para el rol de propietario. */
	private final String ROL_PROPIETARIO = "PROPIETARIO";
	/** Constante para el rol de arrendatario. */
	private final String ROL_ARRENDATARIO = "ARRENDATARIO";
	/** Constante para el rol de consejo. */
	private final String ROL_CONSEJO = "CONSEJO";

	/** Identificador único del usuario en el sistema. */
	private String id;
	/** Nombre de usuario utilizado para iniciar sesión. */
	private String username;
	/** Contraseña de acceso del usuario. */
	private String contrasena;
	/** Rol del usuario en el sistema. */
	private String rol;
	/** Conjunto residencial vinculado al usuario. Puede ser null. */
	private Conjunto conjunto;
	/** Residente del conjunto asociado al usuario. Puede ser null. */
	private Residente residente;
	/** Indica si la cuenta del usuario está activa o bloqueada. */
	private boolean activo;
	/** Contador de intentos fallidos de inicio de sesión. */
	private int intentoFallido;

	/**
	 * Constructor por defecto. Crea un Usuario sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Usuario con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Usuario() {
	}

	/**
	 * Constructor completo. Crea un Usuario con todos sus atributos inicializados.
	 * <br>
	 * <b>pre</b> Los parámetros id, username y rol no deben ser null ni vacíos.
	 * <br>
	 * <b>post</b> Se crea un objeto Usuario completamente inicializado con el
	 * contador de intentos fallidos en cero. <br>
	 *
	 * @param id         Identificador único del usuario. id != null, id != ""
	 * @param username   Nombre de usuario. username != null, username != ""
	 * @param contrasena Contraseña de acceso. contrasena != null, contrasena != ""
	 * @param rol        Rol del usuario en el sistema. rol != null, rol != ""
	 * @param conjunto   Conjunto residencial vinculado. Puede ser null
	 * @param residente  Residente asociado al usuario. Puede ser null
	 * @param activo     true si la cuenta está activa; false si está bloqueada
	 */
	public Usuario(String id, String username, String contrasena, String rol, Conjunto conjunto, Residente residente,
			boolean activo) {
		this.id = id;
		this.username = username;
		this.contrasena = contrasena;
		this.rol = rol;
		this.conjunto = conjunto;
		this.residente = residente;
		this.activo = activo;
		this.intentoFallido = 0;
	}

	/** @return Constante del rol SUPER_ADMIN. */
	public String getROL_SUPER_ADMIN() {
		return ROL_SUPER_ADMIN;
	}

	/** @return Constante del rol ADMINISTRADOR. */
	public String getROL_ADMINISTRADOR() {
		return ROL_ADMINISTRADOR;
	}

	/** @return Constante del rol VIGILANTE. */
	public String getROL_VIGILANTE() {
		return ROL_VIGILANTE;
	}

	/** @return Constante del rol PROPIETARIO. */
	public String getROL_PROPIETARIO() {
		return ROL_PROPIETARIO;
	}

	/** @return Constante del rol ARRENDATARIO. */
	public String getROL_ARRENDATARIO() {
		return ROL_ARRENDATARIO;
	}

	/** @return Constante del rol CONSEJO. */
	public String getROL_CONSEJO() {
		return ROL_CONSEJO;
	}

	/** @return Identificador único del usuario. */
	public String getId() {
		return id;
	}

	/** @param id Nuevo identificador del usuario. id != null, id != "" */
	public void setId(String id) {
		this.id = id;
	}

	/** @return Nombre de usuario. */
	public String getUsername() {
		return username;
	}

	/** @param username Nuevo nombre de usuario. username != null, username != "" */
	public void setUsername(String username) {
		this.username = username;
	}

	/** @return Contraseña del usuario. */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena Nueva contraseña del usuario. contrasena != null,
	 *                   contrasena != ""
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/** @return Rol asignado al usuario. */
	public String getRol() {
		return rol;
	}

	/** @param rol Nuevo rol del usuario. rol != null, rol != "" */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/** @return Conjunto residencial vinculado. */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/** @param conjunto Conjunto a vincular. Puede ser null */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/** @return Residente asociado al usuario. */
	public Residente getResidente() {
		return residente;
	}

	/** @param residente Residente a asociar. Puede ser null */
	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	/** @return true si la cuenta está activa; false si está bloqueada. */
	public boolean isActivo() {
		return activo;
	}

	/** @param activo true para activar la cuenta; false para bloquearla */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/** @return Número de intentos fallidos de inicio de sesión. */
	public int getIntentosFallidos() {
		return intentoFallido;
	}

	/**
	 * @param intentosFallido Nuevo número de intentos fallidos. intentosFallido >=
	 *                        0
	 */
	public void setIntentosFallidos(int intentosFallido) {
		this.intentoFallido = intentosFallido;
	}

	/**
	 * Retorna una representación en texto del Usuario con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto Usuario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, username, rol y estado activo
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", rol=" + rol + ", activo=" + activo + "]";
	}
}
