package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase abstracta que representa un registro de consumo de recursos en las
 * zonas comunes del conjunto residencial. Sirve como base para los registros
 * especificos de energia, agua y reciclaje, agrupando los atributos comunes a
 * todos ellos como el periodo, la fecha de registro y el conjunto al que
 * pertenecen. <br>
 * <b>pre</b> El conjunto al que pertenece el registro debe estar registrado en
 * el sistema. <br>
 * <b>post</b> La clase queda disponible como base para ser extendida por los
 * tipos de consumo especificos. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public abstract class RegistroConsumo implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 7566952520275546282L;

	/** Identificador unico del registro de consumo. */
	private String id;

	/** Conjunto residencial al que pertenece este registro. */
	private Conjunto conjunto;

	/** Periodo al que corresponde el registro (por ejemplo, "2026-04"). */
	private String periodo;

	/** Fecha en la que se realizo el registro en el sistema. */
	private LocalDate fechaRegistro;

	/** Identificador del usuario que realizo el registro. */
	private String registradoPor;

	/**
	 * Constructor por defecto. Crea un RegistroConsumo sin datos inicializados.
	 * <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumo con todos sus atributos en null
	 * o valores por defecto. <br>
	 */
	public RegistroConsumo() {
	}

	/**
	 * Constructor completo. Crea un RegistroConsumo con todos sus atributos
	 * inicializados. <br>
	 * <b>pre</b> Los parametros id, periodo y registradoPor no deben ser null ni
	 * vacios. El conjunto debe estar registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumo completamente inicializado.
	 * <br>
	 *
	 * @param id            Identificador unico del registro. id != null, id != ""
	 * @param conjunto      Conjunto residencial al que pertenece. conjunto != null
	 * @param periodo       Periodo del registro (ej: "2026-04"). periodo != null,
	 *                      periodo != ""
	 * @param fechaRegistro Fecha en que se realizo el registro. fechaRegistro !=
	 *                      null
	 * @param registradoPor Identificador del usuario que registra. registradoPor !=
	 *                      null
	 */
	public RegistroConsumo(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		this.id = id;
		this.conjunto = conjunto;
		this.periodo = periodo;
		this.fechaRegistro = fechaRegistro;
		this.registradoPor = registradoPor;
	}

	/**
	 * Retorna el identificador unico del registro de consumo. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id del registro
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del registro de consumo. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el conjunto residencial al que pertenece el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Conjunto asociado al registro, o null si no tiene
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto residencial al que pertenece el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param conjunto Conjunto al que pertenece el registro. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el periodo al que corresponde el registro de consumo. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el periodo del registro (ej: "2026-04")
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Asigna el periodo al que corresponde el registro de consumo. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> El atributo periodo queda actualizado con el valor recibido. <br>
	 *
	 * @param periodo Nuevo periodo del registro. periodo != null, periodo != ""
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Retorna la fecha en que se realizo el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de registro
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Asigna la fecha en que se realizo el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaRegistro queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param fechaRegistro Nueva fecha de registro. fechaRegistro != null
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Retorna el identificador del usuario que realizo el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el identificador del usuario registrador
	 */
	public String getRegistradoPor() {
		return registradoPor;
	}

	/**
	 * Asigna el identificador del usuario que realizo el registro. <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> El atributo registradoPor queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param registradoPor Identificador del usuario. registradoPor != null,
	 *                      registradoPor != ""
	 */
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

	/**
	 * Retorna una representacion en texto del registro con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto RegistroConsumo debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, conjunto, periodo, fecha de registro y registrador
	 */
	@Override
	public String toString() {
		return "RegistroConsumo [id=" + id + ", conjunto=" + conjunto + ", periodo=" + periodo + ", fechaRegistro="
				+ fechaRegistro + ", registradoPor=" + registradoPor + "]";
	}
}