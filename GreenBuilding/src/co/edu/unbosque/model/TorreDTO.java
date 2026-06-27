package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Objeto de transferencia de datos (DTO) que representa una torre dentro del
 * conjunto residencial. Se utiliza para transportar la informacion de la torre
 * entre las capas del sistema sin exponer la logica de negocio. <br>
 * <b>pre</b> El conjunto al que pertenece la torre debe estar registrado en el
 * sistema. <br>
 * <b>post</b> El DTO queda disponible para ser persistido o transferido entre
 * capas del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class TorreDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 887164228083982740L;

	/** Identificador unico de la torre. */
	private String id;

	/** Nombre o codigo de identificacion de la torre. */
	private String nombre;

	/** Numero total de pisos que tiene la torre. */
	private int numeroPiso;

	/** Conjunto residencial al que pertenece esta torre. */
	private Conjunto conjunto;

	/**
	 * Constructor por defecto. Crea un TorreDTO sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto TorreDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public TorreDTO() {
	}

	/**
	 * Constructor completo. Crea un TorreDTO con todos sus atributos inicializados.
	 * <br>
	 * <b>pre</b> Los parametros id y nombre no deben ser null ni vacios. El
	 * conjunto debe estar registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto TorreDTO completamente inicializado listo para
	 * ser transferido o persistido. <br>
	 *
	 * @param id         Identificador unico de la torre. id != null, id != ""
	 * @param nombre     Nombre o codigo de la torre. nombre != null, nombre != ""
	 * @param numeroPiso Numero total de pisos. numeroPiso >= 1
	 * @param conjunto   Conjunto residencial al que pertenece. conjunto != null
	 */
	public TorreDTO(String id, String nombre, int numeroPiso, Conjunto conjunto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroPiso = numeroPiso;
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el identificador unico de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la torre
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador de la torre. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre o codigo de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre de la torre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre o codigo de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 *
	 * @param nombre Nuevo nombre de la torre. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el numero total de pisos de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return int con el numero de pisos de la torre
	 */
	public int getNumeroPisos() {
		return numeroPiso;
	}

	/**
	 * Asigna el numero total de pisos de la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo numeroPiso queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param numeroPisos Nuevo numero de pisos. numeroPisos >= 1
	 */
	public void setNumeroPisos(int numeroPisos) {
		this.numeroPiso = numeroPisos;
	}

	/**
	 * Retorna el conjunto residencial al que pertenece la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Conjunto asociado, o null si no tiene conjunto asignado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto residencial al que pertenece la torre. <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param conjunto Conjunto al que pertenece la torre. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna una representacion en texto del TorreDTO con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto TorreDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, nombre y numero de pisos de la torre
	 */
	@Override
	public String toString() {
		return "Torre [id=" + id + ", nombre=" + nombre + ", numeroPiso=" + numeroPiso + "]";
	}
}