package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa un reporte generado dentro del sistema GreenBuilding Manager. Esta
 * clase almacena informacion relacionada con diferentes tipos de reportes
 * administrativos, operativos, financieros, ambientales y de convivencia
 * asociados a un conjunto residencial.
 * 
 * Los reportes permiten consolidar informacion importante para procesos de
 * gestion, control y seguimiento dentro del sistema. <b>pre</b> El conjunto
 * asociado debe existir previamente en el sistema. El tipo de reporte debe
 * corresponder a una de las categorias definidas. <br>
 * <b>post</b> El reporte queda disponible para procesos de consulta,
 * administracion y generacion de informacion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Reporte implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 938313717680154579L;

	/** Tipo de reporte administrativo. */
	private final String TIPO_ADMINISTRATIVO = "ADMINISTRATIVO";

	/** Tipo de reporte operativo. */
	private final String TIPO_OPERATIVO = "OPERATIVO";

	/** Tipo de reporte financiero. */
	private final String TIPO_FINANCIERO = "FINANCIERO";

	/** Tipo de reporte ambiental. */
	private final String TIPO_AMBIENTAL = "AMBIENTAL";

	/** Tipo de reporte de convivencia. */
	private final String TIPO_CONVIVENCIA = "CONVIVENCIA";

	/** Identificador unico del reporte. */
	private String id;

	/** Tipo del reporte generado. */
	private String tipo;

	/** Titulo del reporte. */
	private String titulo;

	/** Contenido detallado del reporte. */
	private String contenido;

	/** Fecha y hora de generacion del reporte. */
	private LocalDateTime fechaGeneracion;

	/** Nombre de la persona que genero el reporte. */
	private String generadoPor;

	/** Conjunto asociado al reporte. */
	private Conjunto conjunto;

	/**
	 * Constructor por defecto. Crea un objeto Reporte sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Reporte con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Reporte() {
	}

	/**
	 * Constructor completo. Crea un objeto Reporte con todos sus atributos
	 * inicializados. <b>pre</b> El id, tipo, titulo, contenido, fechaGeneracion,
	 * generadoPor y conjunto no deben ser null. <br>
	 * <b>post</b> Se crea un objeto Reporte completamente inicializado y listo para
	 * ser utilizado dentro del sistema. <br>
	 * 
	 * @param id              Identificador unico del reporte. id != null, id != ""
	 * @param tipo            Tipo del reporte. tipo != null, tipo != ""
	 * @param titulo          Titulo del reporte. titulo != null, titulo != ""
	 * @param contenido       Contenido detallado del reporte. contenido != null,
	 *                        contenido != ""
	 * @param fechaGeneracion Fecha y hora de generacion del reporte.
	 *                        fechaGeneracion != null
	 * @param generadoPor     Persona que genero el reporte. generadoPor != null,
	 *                        generadoPor != ""
	 * @param conjunto        Conjunto asociado al reporte. conjunto != null
	 */
	public Reporte(String id, String tipo, String titulo, String contenido, LocalDateTime fechaGeneracion,
			String generadoPor, Conjunto conjunto) {
		this.id = id;
		this.tipo = tipo;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fechaGeneracion = fechaGeneracion;
		this.generadoPor = generadoPor;
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el tipo de reporte administrativo. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo administrativo
	 */
	public String getTIPO_ADMINISTRATIVO() {
		return TIPO_ADMINISTRATIVO;
	}

	/**
	 * Retorna el tipo de reporte operativo. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo operativo
	 */
	public String getTIPO_OPERATIVO() {
		return TIPO_OPERATIVO;
	}

	/**
	 * Retorna el tipo de reporte financiero. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo financiero
	 */
	public String getTIPO_FINANCIERO() {
		return TIPO_FINANCIERO;
	}

	/**
	 * Retorna el tipo de reporte ambiental. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo ambiental
	 */
	public String getTIPO_AMBIENTAL() {
		return TIPO_AMBIENTAL;
	}

	/**
	 * Retorna el tipo de reporte de convivencia. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo convivencia
	 */
	public String getTIPO_CONVIVENCIA() {
		return TIPO_CONVIVENCIA;
	}

	/**
	 * Retorna el identificador unico del reporte. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del reporte
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del reporte. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del reporte. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el tipo del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo del reporte
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 * 
	 * @param tipo Nuevo tipo del reporte. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el titulo del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el titulo del reporte
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Asigna el titulo del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo titulo queda actualizado con el valor recibido. <br>
	 * 
	 * @param titulo Nuevo titulo del reporte. titulo != null, titulo != ""
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Retorna el contenido del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el contenido del reporte
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Asigna el contenido del reporte. <b>pre</b> El objeto Reporte debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo contenido queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param contenido Nuevo contenido del reporte. contenido != null, contenido !=
	 *                  ""
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Retorna la fecha y hora de generacion del reporte. <b>pre</b> El objeto
	 * Reporte debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la fecha de generacion
	 */
	public LocalDateTime getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Asigna la fecha y hora de generacion del reporte. <b>pre</b> El objeto
	 * Reporte debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaGeneracion queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaGeneracion Nueva fecha y hora de generacion. fechaGeneracion !=
	 *                        null
	 */
	public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	/**
	 * Retorna el nombre de la persona que genero el reporte. <b>pre</b> El objeto
	 * Reporte debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre de quien genero el reporte
	 */
	public String getGeneradoPor() {
		return generadoPor;
	}

	/**
	 * Asigna el nombre de la persona que genero el reporte. <b>pre</b> El objeto
	 * Reporte debe estar instanciado. <br>
	 * <b>post</b> El atributo generadoPor queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param generadoPor Nuevo nombre de quien genero el reporte. generadoPor !=
	 *                    null, generadoPor != ""
	 */
	public void setGeneradoPor(String generadoPor) {
		this.generadoPor = generadoPor;
	}

	/**
	 * Retorna el conjunto asociado al reporte. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Conjunto asociado al reporte
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto asociado al reporte. <b>pre</b> El objeto Reporte debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param conjunto Nuevo conjunto asociado. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna una representacion en texto del reporte con sus datos principales.
	 * <b>pre</b> El objeto Reporte debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, tipo, titulo, fecha de generacion y persona que
	 *         genero el reporte
	 */
	@Override
	public String toString() {
		return "Reporte [id=" + id + ", tipo=" + tipo + ", titulo=" + titulo + ", fechaGeneracion=" + fechaGeneracion
				+ ", generadoPor=" + generadoPor + "]";
	}
}