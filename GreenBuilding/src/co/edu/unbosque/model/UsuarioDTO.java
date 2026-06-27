package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Objeto de transferencia de datos (DTO) que representa un usuario del sistema
 * GreenBuilding Manager. Contiene las credenciales de acceso, el rol asignado y
 * la informacion de vinculacion con el conjunto residencial y el residente
 * correspondiente. <br>
 * <b>pre</b> El username y el rol no deben ser null ni vacios. <br>
 * <b>post</b> El DTO queda disponible para ser persistido o transferido entre
 * las capas del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class UsuarioDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 7542836229990252814L;

	/** Identificador unico del usuario en el sistema. */
	private String id;

	/** Nombre de usuario utilizado para iniciar sesion. */
	private String username;

	/** Contrasena de acceso del usuario. */
	private String contrasena;

	/**
	 * Rol del usuario en el sistema (SUPER_ADMIN, ADMINISTRADOR, PROPIETARIO,
	 * etc.).
	 */
	private String rol;

	/** Conjunto residencial al que esta vinculado el usuario. Puede ser null. */
	private Conjunto conjunto;

	/** Residente del conjunto asociado al usuario. Puede ser null. */
	private Residente residente;

	/** Indica si la cuenta del usuario esta activa o bloqueada. */
	private boolean activo;

	/** Contador de intentos fallidos de inicio de sesion. */
	private int intentoFallido;

	/**
	 * Constructor por defecto. Crea un UsuarioDTO sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto UsuarioDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public UsuarioDTO() {
	}

	/**
	 * Constructor completo. Crea un UsuarioDTO con todos sus atributos
	 * inicializados. <br>
	 * <b>pre</b> Los parametros id, username y rol no deben ser null ni vacios.
	 * <br>
	 * <b>post</b> Se crea un objeto UsuarioDTO completamente inicializado con el
	 * contador de intentos fallidos en cero. <br>
	 *
	 * @param id         Identificador unico del usuario. id != null, id != ""
	 * @param username   Nombre de usuario. username != null, username != ""
	 * @param contrasena Contrasena de acceso. contrasena != null, contrasena != ""
	 * @param rol        Rol del usuario en el sistema. rol != null, rol != ""
	 * @param conjunto   Conjunto residencial vinculado. Puede ser null
	 * @param residente  Residente asociado al usuario. Puede ser null
	 * @param activo     true si la cuenta esta activa; false si esta bloqueada
	 */
	public UsuarioDTO(String id, String username, String contrasena, String rol, Conjunto conjunto, Residente residente,
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

	/**
	 * Retorna el identificador unico del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id del usuario
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador del usuario. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre de usuario del sistema. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el username del usuario
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Asigna el nombre de usuario del sistema. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo username queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param username Nuevo nombre de usuario. username != null, username != ""
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retorna la contrasena de acceso del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la contrasena del usuario
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Asigna la contrasena de acceso del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo contrasena queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param contrasena Nueva contrasena del usuario. contrasena != null,
	 *                   contrasena != ""
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Retorna el rol del usuario en el sistema. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el rol del usuario
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Asigna el rol del usuario en el sistema. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo rol queda actualizado con el valor recibido. <br>
	 *
	 * @param rol Nuevo rol del usuario. rol != null, rol != ""
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Retorna el conjunto residencial vinculado al usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Conjunto vinculado, o null si no tiene conjunto asignado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto residencial vinculado al usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param conjunto Conjunto a vincular. Puede ser null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el residente asociado al usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Residente asociado, o null si el usuario no es un residente
	 */
	public Residente getResidente() {
		return residente;
	}

	/**
	 * Asigna el residente asociado al usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo residente queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param residente Residente a asociar. Puede ser null
	 */
	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	/**
	 * Indica si la cuenta del usuario esta activa. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si la cuenta esta activa; false si esta bloqueada
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Asigna el estado de activacion de la cuenta del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo activo queda actualizado con el valor recibido. <br>
	 *
	 * @param activo true para activar la cuenta; false para bloquearla
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Retorna el numero de intentos fallidos de inicio de sesion del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return int con el numero de intentos fallidos acumulados
	 */
	public int getIntentosFallidos() {
		return intentoFallido;
	}

	/**
	 * Asigna el numero de intentos fallidos de inicio de sesion del usuario. <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo intentoFallido queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param intentosFallido Nuevo numero de intentos fallidos. intentosFallido >=
	 *                        0
	 */
	public void setIntentosFallidos(int intentosFallido) {
		this.intentoFallido = intentosFallido;
	}

	/**
	 * Retorna una representacion en texto del UsuarioDTO con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto UsuarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, username, rol, estado activo e intentos fallidos
	 */
	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", username=" + username + ", rol=" + rol + ", activo=" + activo
				+ ", intentosFallidos=" + intentoFallido + "]";
	}
}