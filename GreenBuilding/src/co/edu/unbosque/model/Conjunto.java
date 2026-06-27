package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Representa un conjunto residencial administrado por GreenBuilding Group.
 * Contiene la informacion de contacto e identificacion del conjunto, y sirve
 * como entidad raiz a la que se asocian torres, apartamentos, zonas comunes y
 * demas recursos del sistema. <b>pre</b> El conjunto debe tener un
 * identificador unico antes de ser registrado en el sistema. <br>
 * <b>post</b> El conjunto queda disponible para ser asociado a torres,
 * residentes, zonas comunes y otros modulos del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Conjunto implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 93501396661294076L;

	/** Identificador unico del conjunto residencial. */
	private String id;

	/** Nombre del conjunto residencial. */
	private String nombre;

	/** Direccion fisica del conjunto residencial. */
	private String direccion;

	/** Ciudad en la que se encuentra ubicado el conjunto residencial. */
	private String ciudad;

	/** Numero de telefono de contacto del conjunto residencial. */
	private String telefono;

	/** Correo electronico de contacto del conjunto residencial. */
	private String correo;

	/**
	 * Constructor por defecto. Crea un conjunto sin datos inicializados. <b>pre</b>
	 * No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Conjunto con todos sus atributos en null. <br>
	 */
	public Conjunto() {
	}

	/**
	 * Constructor completo. Crea un conjunto con todos sus atributos inicializados.
	 * <b>pre</b> Los parametros id y nombre no deben ser null ni vacios. <br>
	 * <b>post</b> Se crea un objeto Conjunto completamente inicializado y listo
	 * para ser registrado en el sistema. <br>
	 *
	 * @param id        Identificador unico del conjunto. id != null, id != ""
	 * @param nombre    Nombre del conjunto residencial. nombre != null, nombre !=
	 *                  ""
	 * @param direccion Direccion fisica del conjunto. direccion != null, direccion
	 *                  != ""
	 * @param ciudad    Ciudad donde se ubica el conjunto. ciudad != null, ciudad !=
	 *                  ""
	 * @param telefono  Telefono de contacto del conjunto. Puede ser null si no se
	 *                  tiene
	 * @param correo    Correo electronico de contacto. Puede ser null si no se
	 *                  tiene
	 */
	public Conjunto(String id, String nombre, String direccion, String ciudad, String telefono, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.correo = correo;
	}

	/**
	 * Retorna el identificador unico del conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id del conjunto
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador del conjunto. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre del conjunto residencial. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre del conjunto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre del conjunto residencial. <b>pre</b> El objeto Conjunto debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 *
	 * @param nombre Nuevo nombre del conjunto. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la direccion fisica del conjunto. <b>pre</b> El objeto Conjunto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la direccion del conjunto
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna la direccion fisica del conjunto. <b>pre</b> El objeto Conjunto debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo direccion queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param direccion Nueva direccion del conjunto. direccion != null, direccion
	 *                  != ""
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna la ciudad donde se ubica el conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la ciudad del conjunto
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Asigna la ciudad donde se ubica el conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo ciudad queda actualizado con el valor recibido. <br>
	 *
	 * @param ciudad Nueva ciudad del conjunto. ciudad != null, ciudad != ""
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Retorna el telefono de contacto del conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el telefono del conjunto, o null si no fue asignado
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Asigna el telefono de contacto del conjunto. <b>pre</b> El objeto Conjunto
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo telefono queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param telefono Nuevo telefono de contacto. Puede ser null si no se tiene
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Retorna el correo electronico de contacto del conjunto. <b>pre</b> El objeto
	 * Conjunto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el correo del conjunto, o null si no fue asignado
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Asigna el correo electronico de contacto del conjunto. <b>pre</b> El objeto
	 * Conjunto debe estar instanciado. <br>
	 * <b>post</b> El atributo correo queda actualizado con el valor recibido. <br>
	 *
	 * @param correo Nuevo correo electronico de contacto. Puede ser null si no se
	 *               tiene
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Retorna una representacion en texto del conjunto con sus datos principales.
	 * <b>pre</b> El objeto Conjunto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, nombre, direccion, ciudad, telefono y correo del
	 *         conjunto
	 */
	@Override
	public String toString() {
		return "Conjunto [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}