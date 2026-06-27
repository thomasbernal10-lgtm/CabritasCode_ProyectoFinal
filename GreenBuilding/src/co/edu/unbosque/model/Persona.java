package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa a cualquier persona dentro del sistema de
 * administracion del conjunto residencial. Sirve como base para propietarios,
 * arrendatarios, residentes, visitantes y cualquier otro actor del sistema que
 * tenga datos personales. <br>
 * <b>pre</b> No hay precondiciones para instanciar esta clase abstracta. <br>
 * <b>post</b> La clase queda disponible como base para ser extendida por las
 * subclases del dominio. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public abstract class Persona implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3509342501116087002L;

	/** Identificador unico de la persona en el sistema. */
	private String id;

	/** Nombre completo de la persona. */
	private String nombre;

	/** Numero de cedula de la persona. */
	private String cedula;

	/** Numero de telefono de contacto de la persona. */
	private String telefono;

	/** Correo electronico de la persona. */
	private String correo;

	/** Nombre del contacto de emergencia asociado a la persona. */
	private String contactoEmergencia;

	/** Telefono del contacto de emergencia asociado a la persona. */
	private String telefonoEmergencia;

	/**
	 * Constructor por defecto. Crea una persona sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Persona con todos sus atributos en null. <br>
	 */
	public Persona() {
	}

	/**
	 * Constructor completo. Crea una persona con todos sus atributos inicializados.
	 * <br>
	 * <b>pre</b> Los parametros id, nombre y cedula no deben ser null ni vacios.
	 * <br>
	 * <b>post</b> Se crea un objeto Persona completamente inicializado. <br>
	 *
	 * @param id                 Identificador unico. id != null, id != ""
	 * @param nombre             Nombre completo. nombre != null, nombre != ""
	 * @param cedula             Numero de cedula. cedula != null, cedula != ""
	 * @param telefono           Telefono de contacto. Puede ser null
	 * @param correo             Correo electronico. Puede ser null
	 * @param contactoEmergencia Nombre del contacto de emergencia. Puede ser null
	 * @param telefonoEmergencia Telefono del contacto de emergencia. Puede ser null
	 */
	public Persona(String id, String nombre, String cedula, String telefono, String correo, String contactoEmergencia,
			String telefonoEmergencia) {
		this.id = id;
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.correo = correo;
		this.contactoEmergencia = contactoEmergencia;
		this.telefonoEmergencia = telefonoEmergencia;
	}

	/**
	 * Retorna el identificador unico de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la persona
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre completo de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre completo de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 *
	 * @param nombre Nuevo nombre. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el numero de cedula de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el numero de cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Asigna el numero de cedula de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo cedula queda actualizado con el valor recibido. <br>
	 *
	 * @param cedula Nueva cedula. cedula != null, cedula != ""
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Retorna el telefono de contacto de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el numero de telefono, o null si no tiene
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Asigna el telefono de contacto de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo telefono queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param telefono Nuevo numero de telefono. Puede ser null
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Retorna el correo electronico de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el correo electronico, o null si no tiene
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Asigna el correo electronico de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo correo queda actualizado con el valor recibido. <br>
	 *
	 * @param correo Nuevo correo electronico. Puede ser null
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Retorna el nombre del contacto de emergencia de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre del contacto de emergencia, o null si no tiene
	 */
	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	/**
	 * Asigna el nombre del contacto de emergencia de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo contactoEmergencia queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param contactoEmergencia Nombre del nuevo contacto de emergencia. Puede ser
	 *                           null
	 */
	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	/**
	 * Retorna el telefono del contacto de emergencia de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el telefono del contacto de emergencia, o null si no tiene
	 */
	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	/**
	 * Asigna el telefono del contacto de emergencia de la persona. <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> El atributo telefonoEmergencia queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param telefonoEmergencia Nuevo telefono del contacto de emergencia. Puede
	 *                           ser null
	 */
	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	/**
	 * Retorna una representacion en texto de la persona con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto Persona debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con id, nombre, cedula, telefono, correo y contacto de
	 *         emergencia
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", cedula=" + cedula + ", telefono=" + telefono
				+ ", correo=" + correo + ", contactoEmergencia=" + contactoEmergencia + ", telefonoEmergencia="
				+ telefonoEmergencia + "]";
	}
}