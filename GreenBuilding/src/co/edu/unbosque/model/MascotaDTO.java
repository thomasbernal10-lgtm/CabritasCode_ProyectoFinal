package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Objeto de transferencia de datos (DTO) que representa una mascota registrada
 * dentro del conjunto residencial. Se utiliza para transportar la informacion
 * de la mascota entre las capas del sistema sin exponer directamente la entidad
 * del modelo. <b>pre</b> El DTO debe ser construido con datos validos
 * provenientes de la capa de persistencia o de la interfaz grafica. <br>
 * <b>post</b> El objeto MascotaDTO queda disponible para ser transferido entre
 * las capas de modelo, control y vista del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class MascotaDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 4088368220503590363L;

	/** Identificador unico de la mascota. */
	private String id;

	/** Nombre de la mascota. */
	private String nombre;

	/** Especie de la mascota (por ejemplo, perro, gato, ave). */
	private String especie;

	/** Raza de la mascota. Puede ser null si es mestiza o se desconoce. */
	private String raza;

	/** Color o descripcion del pelaje o plumaje de la mascota. */
	private String color;

	/** Apartamento del conjunto residencial al que pertenece la mascota. */
	private Apartamento apartamento;

	/** Fecha de la ultima vacunacion registrada de la mascota. */
	private LocalDate fechaVacunacion;

	/** Indica si la mascota tiene sus vacunas al dia al momento del registro. */
	private boolean vacunasAlDia;

	/**
	 * Constructor por defecto. Crea un MascotaDTO sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto MascotaDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public MascotaDTO() {
	}

	/**
	 * Constructor completo. Crea un MascotaDTO con todos sus atributos
	 * inicializados. <b>pre</b> Los parametros id, nombre y especie no deben ser
	 * null ni vacios. El apartamento debe estar registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto MascotaDTO completamente inicializado y listo
	 * para ser transferido entre capas del sistema. <br>
	 *
	 * @param id              Identificador unico de la mascota. id != null, id !=
	 *                        ""
	 * @param nombre          Nombre de la mascota. nombre != null, nombre != ""
	 * @param especie         Especie de la mascota. especie != null, especie != ""
	 * @param raza            Raza de la mascota. Puede ser null si se desconoce
	 * @param color           Color o descripcion del pelaje. Puede ser null si se
	 *                        desconoce
	 * @param apartamento     Apartamento al que pertenece la mascota. apartamento
	 *                        != null
	 * @param fechaVacunacion Fecha de la ultima vacunacion. Puede ser null si no se
	 *                        tiene registro
	 * @param vacunasAlDia    true si las vacunas estan al dia, false en caso
	 *                        contrario
	 */
	public MascotaDTO(String id, String nombre, String especie, String raza, String color, Apartamento apartamento,
			LocalDate fechaVacunacion, boolean vacunasAlDia) {
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.color = color;
		this.apartamento = apartamento;
		this.fechaVacunacion = fechaVacunacion;
		this.vacunasAlDia = vacunasAlDia;
	}

	/**
	 * Retorna el identificador unico de la mascota. <b>pre</b> El objeto MascotaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la mascota
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la mascota. <b>pre</b> El objeto MascotaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador de la mascota. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre de la mascota
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 *
	 * @param nombre Nuevo nombre de la mascota. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la especie de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la especie de la mascota
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Asigna la especie de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo especie queda actualizado con el valor recibido. <br>
	 *
	 * @param especie Nueva especie de la mascota. especie != null, especie != ""
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Retorna la raza de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la raza de la mascota, o null si no fue registrada
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Asigna la raza de la mascota. <b>pre</b> El objeto MascotaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo raza queda actualizado con el valor recibido. <br>
	 *
	 * @param raza Nueva raza de la mascota. Puede ser null si se desconoce
	 */
	public void setRaza(String raza) {
		this.raza = raza;
	}

	/**
	 * Retorna el color o descripcion del pelaje de la mascota. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el color de la mascota, o null si no fue registrado
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Asigna el color o descripcion del pelaje de la mascota. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo color queda actualizado con el valor recibido. <br>
	 *
	 * @param color Nuevo color de la mascota. Puede ser null si se desconoce
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Retorna el apartamento al que pertenece la mascota. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Apartamento asociado a la mascota, o null si no fue asignado
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento al que pertenece la mascota. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param apartamento Apartamento a asociar a la mascota. apartamento != null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna la fecha de la ultima vacunacion registrada de la mascota. <b>pre</b>
	 * El objeto MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de vacunacion, o null si no fue registrada
	 */
	public LocalDate getFechaVacunacion() {
		return fechaVacunacion;
	}

	/**
	 * Asigna la fecha de la ultima vacunacion de la mascota. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaVacunacion queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param fechaVacunacion Nueva fecha de vacunacion. Puede ser null si no se
	 *                        tiene registro
	 */
	public void setFechaVacunacion(LocalDate fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}

	/**
	 * Indica si la mascota tiene sus vacunas al dia. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si las vacunas estan al dia, false en caso contrario
	 */
	public boolean isVacunasAlDia() {
		return vacunasAlDia;
	}

	/**
	 * Asigna si la mascota tiene sus vacunas al dia. <b>pre</b> El objeto
	 * MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo vacunasAlDia queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param vacunasAlDia true si las vacunas estan al dia, false en caso contrario
	 */
	public void setVacunasAlDia(boolean vacunasAlDia) {
		this.vacunasAlDia = vacunasAlDia;
	}

	/**
	 * Retorna una representacion en texto del MascotaDTO con sus datos principales.
	 * <b>pre</b> El objeto MascotaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, nombre, especie, raza, color, fechaVacunacion y
	 *         vacunasAlDia de la mascota
	 */
	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", raza=" + raza + ", color="
				+ color + ", fechaVacunacion=" + fechaVacunacion + ", vacunasAlDia=" + vacunasAlDia + "]";
	}
}