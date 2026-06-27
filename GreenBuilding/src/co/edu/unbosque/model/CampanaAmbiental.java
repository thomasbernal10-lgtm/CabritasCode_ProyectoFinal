package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa una campana ambiental organizada dentro de un conjunto
 * residencial. Las campanas ambientales son iniciativas promovidas por la
 * administracion para fomentar practicas sostenibles entre los residentes,
 * tales como reciclaje, ahorro de agua y energia, movilidad limpia y cuidado de
 * zonas verdes. <b>pre</b> El conjunto al que pertenece la campana debe estar
 * registrado en el sistema. <br>
 * <b>post</b> La campana queda disponible para ser programada, activada,
 * finalizada y consultada en los reportes ambientales del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class CampanaAmbiental implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3467352494445401398L;

	/** Constante que representa una campana de tipo reciclaje. */
	private final String TIPO_RECICLAJE = "RECICLAJE";

	/** Constante que representa una campana de tipo ahorro de agua. */
	private final String TIPO_AGUA = "AGUA";

	/** Constante que representa una campana de tipo ahorro de energia. */
	private final String TIPO_ENERGIA = "ENERGIA";

	/** Constante que representa una campana de tipo movilidad sostenible. */
	private final String TIPO_MOVILIDAD = "MOVILIDAD";

	/** Constante que representa una campana de tipo cuidado de zonas verdes. */
	private final String TIPO_ZONAS_VERDES = "ZONAS_VERDES";

	/**
	 * Constante que representa el estado de campana programada pero no iniciada.
	 */
	private final String ESTADO_PROGRAMADA = "PROGRAMADA";

	/** Constante que representa el estado de campana actualmente en ejecucion. */
	private final String ESTADO_ACTIVA = "ACTIVA";

	/** Constante que representa el estado de campana que ya concluyo. */
	private final String ESTADO_FINALIZADA = "FINALIZADA";

	/** Constante que representa el estado de campana que fue cancelada. */
	private final String ESTADO_CANCELADA = "CANCELADA";

	/** Identificador unico de la campana ambiental. */
	private String id;

	/**
	 * Conjunto residencial al que pertenece y en el que se desarrolla la campana.
	 */
	private Conjunto conjunto;

	/** Nombre descriptivo de la campana ambiental. */
	private String nombre;

	/** Descripcion detallada de los objetivos y actividades de la campana. */
	private String descripcion;

	/**
	 * Tipo de campana segun la categoria ambiental (RECICLAJE, AGUA, ENERGIA,
	 * MOVILIDAD, ZONAS_VERDES).
	 */
	private String tipo;

	/** Fecha en la que inicia la campana ambiental. */
	private LocalDate fechaInicio;

	/** Fecha en la que finaliza la campana ambiental. */
	private LocalDate fechaFin;

	/**
	 * Meta u objetivo cuantitativo o cualitativo que se espera alcanzar con la
	 * campana.
	 */
	private String meta;

	/** Estado actual de la campana (PROGRAMADA, ACTIVA, FINALIZADA o CANCELADA). */
	private String estado;

	/**
	 * Resultado o descripcion del impacto obtenido al finalizar la campana. Puede
	 * ser null si no ha concluido.
	 */
	private String resultado;

	/**
	 * Constructor por defecto. Crea una campana ambiental sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto CampanaAmbiental con todos sus atributos en
	 * null. <br>
	 */
	public CampanaAmbiental() {
	}

	/**
	 * Constructor completo. Crea una campana ambiental con todos sus atributos
	 * inicializados. <b>pre</b> Los parametros id, nombre, tipo y estado no deben
	 * ser null ni vacios. El conjunto debe estar registrado en el sistema. La
	 * fechaInicio no debe ser posterior a fechaFin. <br>
	 * <b>post</b> Se crea un objeto CampanaAmbiental completamente inicializado y
	 * listo para ser registrado y gestionado en el sistema. <br>
	 * 
	 * @param id          Identificador unico de la campana. id != null, id != ""
	 * @param conjunto    Conjunto residencial donde se realiza la campana. conjunto
	 *                    != null
	 * @param nombre      Nombre de la campana. nombre != null, nombre != ""
	 * @param descripcion Descripcion de objetivos y actividades. descripcion !=
	 *                    null
	 * @param tipo        Categoria ambiental de la campana. tipo != null, tipo !=
	 *                    ""
	 * @param fechaInicio Fecha de inicio de la campana. fechaInicio != null
	 * @param fechaFin    Fecha de finalizacion de la campana. fechaFin != null,
	 *                    fechaFin >= fechaInicio
	 * @param meta        Objetivo esperado de la campana. meta != null
	 * @param estado      Estado inicial de la campana. estado != null, estado != ""
	 * @param resultado   Resultado obtenido. Puede ser null si la campana no ha
	 *                    finalizado
	 */
	public CampanaAmbiental(String id, Conjunto conjunto, String nombre, String descripcion, String tipo,
			LocalDate fechaInicio, LocalDate fechaFin, String meta, String estado, String resultado) {
		super();
		this.id = id;
		this.conjunto = conjunto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.meta = meta;
		this.estado = estado;
		this.resultado = resultado;
	}

	/**
	 * Retorna la constante que representa el tipo de campana de reciclaje.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "RECICLAJE"
	 */
	public String getTIPO_RECICLAJE() {
		return TIPO_RECICLAJE;
	}

	/**
	 * Retorna la constante que representa el tipo de campana de ahorro de agua.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "AGUA"
	 */
	public String getTIPO_AGUA() {
		return TIPO_AGUA;
	}

	/**
	 * Retorna la constante que representa el tipo de campana de ahorro de energia.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ENERGIA"
	 */
	public String getTIPO_ENERGIA() {
		return TIPO_ENERGIA;
	}

	/**
	 * Retorna la constante que representa el tipo de campana de movilidad
	 * sostenible. <b>pre</b> El objeto CampanaAmbiental debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "MOVILIDAD"
	 */
	public String getTIPO_MOVILIDAD() {
		return TIPO_MOVILIDAD;
	}

	/**
	 * Retorna la constante que representa el tipo de campana de cuidado de zonas
	 * verdes. <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ZONAS_VERDES"
	 */
	public String getTIPO_ZONAS_VERDES() {
		return TIPO_ZONAS_VERDES;
	}

	/**
	 * Retorna la constante que representa el estado de campana programada.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "PROGRAMADA"
	 */
	public String getESTADO_PROGRAMADA() {
		return ESTADO_PROGRAMADA;
	}

	/**
	 * Retorna la constante que representa el estado de campana activa. <b>pre</b>
	 * El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ACTIVA"
	 */
	public String getESTADO_ACTIVA() {
		return ESTADO_ACTIVA;
	}

	/**
	 * Retorna la constante que representa el estado de campana finalizada.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "FINALIZADA"
	 */
	public String getESTADO_FINALIZADA() {
		return ESTADO_FINALIZADA;
	}

	/**
	 * Retorna la constante que representa el estado de campana cancelada.
	 * <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "CANCELADA"
	 */
	public String getESTADO_CANCELADA() {
		return ESTADO_CANCELADA;
	}

	/**
	 * Retorna el identificador unico de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id de la campana
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador de la campana. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el conjunto residencial al que pertenece la campana. <b>pre</b> El
	 * objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Conjunto asociado a la campana, o null si no tiene conjunto
	 *         asignado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto residencial al que pertenece la campana. <b>pre</b> El
	 * objeto CampanaAmbiental debe estar instanciado. El conjunto debe estar
	 * registrado en el sistema. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param conjunto Conjunto residencial a asignar. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el nombre de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre de la campana
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 * 
	 * @param nombre Nuevo nombre de la campana. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la descripcion de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la descripcion de objetivos y actividades de la campana
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo descripcion queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param descripcion Nueva descripcion de la campana. descripcion != null
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el tipo de campana ambiental. <b>pre</b> El objeto CampanaAmbiental
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo de campana (RECICLAJE, AGUA, ENERGIA, MOVILIDAD o
	 *         ZONAS_VERDES)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de campana ambiental. <b>pre</b> El objeto CampanaAmbiental
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 * 
	 * @param tipo Nuevo tipo de campana. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la fecha de inicio de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return LocalDate con la fecha de inicio de la campana, o null si no ha sido
	 *         asignada
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaInicio queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param fechaInicio Nueva fecha de inicio. fechaInicio != null, fechaInicio <=
	 *                    fechaFin
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna la fecha de finalizacion de la campana ambiental. <b>pre</b> El
	 * objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return LocalDate con la fecha de fin de la campana, o null si no ha sido
	 *         asignada
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Asigna la fecha de finalizacion de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaFin queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param fechaFin Nueva fecha de fin. fechaFin != null, fechaFin >= fechaInicio
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Retorna la meta u objetivo de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la meta definida para la campana
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * Asigna la meta u objetivo de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo meta queda actualizado con el valor recibido. <br>
	 * 
	 * @param meta Nueva meta de la campana. meta != null
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * Retorna el estado actual de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado actual (PROGRAMADA, ACTIVA, FINALIZADA o
	 *         CANCELADA)
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual de la campana ambiental. <b>pre</b> El objeto
	 * CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado de la campana. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el resultado o descripcion del impacto obtenido al finalizar la
	 * campana. <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con los resultados de la campana, o null si aun no ha
	 *         finalizado
	 */
	public String getResultados() {
		return resultado;
	}

	/**
	 * Asigna el resultado o descripcion del impacto obtenido al finalizar la
	 * campana. <b>pre</b> El objeto CampanaAmbiental debe estar instanciado. <br>
	 * <b>post</b> El atributo resultado queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param resultado Descripcion del resultado de la campana. Puede ser null si
	 *                  aun no ha finalizado
	 */
	public void setResultados(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Retorna una representacion en texto de la campana ambiental con sus datos
	 * principales. <b>pre</b> El objeto CampanaAmbiental debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, nombre, tipo, estado, fecha de inicio y fecha de
	 *         fin de la campana
	 */
	@Override
	public String toString() {
		return "CampanaAmbiental [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
}