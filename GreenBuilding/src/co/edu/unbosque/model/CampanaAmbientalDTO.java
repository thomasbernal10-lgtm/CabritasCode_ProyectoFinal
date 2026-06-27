package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) que representa una campana ambiental dentro del
 * sistema GreenBuilding Manager. Contiene la informacion necesaria para
 * transferir datos de campanas ambientales entre las capas del sistema sin
 * exponer la logica de negocio. <b>pre</b> La clase Conjunto debe estar
 * inicializada antes de ser asignada a esta clase. <br>
 * <b>post</b> Se dispone de un objeto DTO con los datos de la campana ambiental
 * listos para ser transferidos. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class CampanaAmbientalDTO implements Serializable {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 3467352494445401398L;

	/**
	 * Constante que representa el tipo de campana de reciclaje.
	 */
	private final String TIPO_RECICLAJE = "RECICLAJE";

	/**
	 * Constante que representa el tipo de campana de ahorro de agua.
	 */
	private final String TIPO_AGUA = "AGUA";

	/**
	 * Constante que representa el tipo de campana de eficiencia energetica.
	 */
	private final String TIPO_ENERGIA = "ENERGIA";

	/**
	 * Constante que representa el tipo de campana de movilidad sostenible.
	 */
	private final String TIPO_MOVILIDAD = "MOVILIDAD";

	/**
	 * Constante que representa el tipo de campana de cuidado de zonas verdes.
	 */
	private final String TIPO_ZONAS_VERDES = "ZONAS_VERDES";

	/**
	 * Constante que representa el estado de campana programada pero no iniciada.
	 */
	private final String ESTADO_PROGRAMADA = "PROGRAMADA";

	/**
	 * Constante que representa el estado de campana actualmente en ejecucion.
	 */
	private final String ESTADO_ACTIVA = "ACTIVA";

	/**
	 * Constante que representa el estado de campana que ha concluido exitosamente.
	 */
	private final String ESTADO_FINALIZADA = "FINALIZADA";

	/**
	 * Constante que representa el estado de campana que fue cancelada antes de
	 * finalizar.
	 */
	private final String ESTADO_CANCELADA = "CANCELADA";

	/**
	 * Identificador unico de la campana ambiental.
	 */
	private String id;

	/**
	 * Conjunto residencial al que pertenece la campana ambiental.
	 */
	private Conjunto conjunto;

	/**
	 * Nombre descriptivo de la campana ambiental.
	 */
	private String nombre;

	/**
	 * Descripcion detallada del objetivo y actividades de la campana.
	 */
	private String descripcion;

	/**
	 * Tipo de campana ambiental. Debe corresponder a una de las constantes TIPO
	 * definidas en esta clase.
	 */
	private String tipo;

	/**
	 * Fecha de inicio de la campana ambiental.
	 */
	private LocalDate fechaInicio;

	/**
	 * Fecha de finalizacion de la campana ambiental.
	 */
	private LocalDate fechaFin;

	/**
	 * Meta u objetivo cuantificable que se espera alcanzar con la campana.
	 */
	private String meta;

	/**
	 * Estado actual de la campana. Debe corresponder a una de las constantes ESTADO
	 * definidas en esta clase.
	 */
	private String estado;

	/**
	 * Resultados obtenidos al finalizar la campana ambiental.
	 */
	private String resultado;

	/**
	 * Constructor vacio requerido para la serializacion y el uso con DataMapper.
	 * <b>pre</b> No se requiere ninguna condicion previa. <br>
	 * <b>post</b> Se crea un objeto CampanaAmbientalDTO con todos sus atributos en
	 * null. <br>
	 */
	public CampanaAmbientalDTO() {
	}

	/**
	 * Constructor completo que inicializa todos los atributos de la campana
	 * ambiental. <b>pre</b> La clase Conjunto referenciada debe existir y estar
	 * inicializada. <br>
	 * <b>post</b> Se crea un objeto CampanaAmbientalDTO con todos sus atributos
	 * inicializados con los valores proporcionados. <br>
	 *
	 * @param id          Identificador unico de la campana. id != null, id != ""
	 * @param conjunto    Conjunto residencial al que pertenece. conjunto != null
	 * @param nombre      Nombre de la campana. nombre != null, nombre != ""
	 * @param descripcion Descripcion de la campana. descripcion != null
	 * @param tipo        Tipo de campana. Debe ser uno de los valores de las
	 *                    constantes TIPO. tipo != null
	 * @param fechaInicio Fecha de inicio de la campana. fechaInicio != null
	 * @param fechaFin    Fecha de fin de la campana. fechaFin != null, fechaFin
	 *                    despues de fechaInicio
	 * @param meta        Meta de la campana. meta != null
	 * @param estado      Estado actual de la campana. Debe ser uno de los valores
	 *                    de las constantes ESTADO. estado != null
	 * @param resultado   Resultados de la campana al finalizar. Puede ser null si
	 *                    aun no ha finalizado
	 */
	public CampanaAmbientalDTO(String id, Conjunto conjunto, String nombre, String descripcion, String tipo,
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
	 * Retorna la constante del tipo de campana de reciclaje. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "RECICLAJE"
	 */
	public String getTIPO_RECICLAJE() {
		return TIPO_RECICLAJE;
	}

	/**
	 * Retorna la constante del tipo de campana de agua. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "AGUA"
	 */
	public String getTIPO_AGUA() {
		return TIPO_AGUA;
	}

	/**
	 * Retorna la constante del tipo de campana de energia. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ENERGIA"
	 */
	public String getTIPO_ENERGIA() {
		return TIPO_ENERGIA;
	}

	/**
	 * Retorna la constante del tipo de campana de movilidad. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MOVILIDAD"
	 */
	public String getTIPO_MOVILIDAD() {
		return TIPO_MOVILIDAD;
	}

	/**
	 * Retorna la constante del tipo de campana de zonas verdes. <b>pre</b> El
	 * objeto debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ZONAS_VERDES"
	 */
	public String getTIPO_ZONAS_VERDES() {
		return TIPO_ZONAS_VERDES;
	}

	/**
	 * Retorna la constante del estado programada. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PROGRAMADA"
	 */
	public String getESTADO_PROGRAMADA() {
		return ESTADO_PROGRAMADA;
	}

	/**
	 * Retorna la constante del estado activa. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ACTIVA"
	 */
	public String getESTADO_ACTIVA() {
		return ESTADO_ACTIVA;
	}

	/**
	 * Retorna la constante del estado finalizada. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "FINALIZADA"
	 */
	public String getESTADO_FINALIZADA() {
		return ESTADO_FINALIZADA;
	}

	/**
	 * Retorna la constante del estado cancelada. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "CANCELADA"
	 */
	public String getESTADO_CANCELADA() {
		return ESTADO_CANCELADA;
	}

	/**
	 * Retorna el identificador unico de la campana ambiental. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la campana. Puede ser null si no fue inicializado
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la campana ambiental. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Identificador unico a asignar. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el conjunto residencial asociado a la campana. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Conjunto asociado. Puede ser null si no fue asignado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto residencial al que pertenece la campana. <b>pre</b> El
	 * objeto debe estar inicializado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param conjunto Conjunto residencial a asignar. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el nombre de la campana ambiental. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre de la campana. Puede ser null si no fue
	 *         inicializado
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre de la campana ambiental. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 *
	 * @param nombre Nombre de la campana a asignar. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la descripcion de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la descripcion de la campana. Puede ser null si no fue
	 *         inicializado
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> El atributo descripcion queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param descripcion Descripcion de la campana a asignar. descripcion != null
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el tipo de la campana ambiental. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el tipo de la campana. Debe corresponder a una de las
	 *         constantes TIPO de esta clase
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la campana ambiental. <b>pre</b> El objeto debe estar
	 * inicializado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 *
	 * @param tipo Tipo de campana a asignar. Debe ser uno de los valores de las
	 *             constantes TIPO. tipo != null
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la fecha de inicio de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de inicio. Puede ser null si no fue
	 *         inicializado
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> El atributo fechaInicio queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param fechaInicio Fecha de inicio a asignar. fechaInicio != null,
	 *                    fechaInicio anterior a fechaFin
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna la fecha de finalizacion de la campana ambiental. <b>pre</b> El
	 * objeto debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de fin. Puede ser null si no fue inicializado
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Asigna la fecha de finalizacion de la campana ambiental. <b>pre</b> El objeto
	 * debe estar inicializado. <br>
	 * <b>post</b> El atributo fechaFin queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param fechaFin Fecha de fin a asignar. fechaFin != null, fechaFin posterior
	 *                 a fechaInicio
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Retorna la meta u objetivo de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la meta de la campana. Puede ser null si no fue
	 *         inicializado
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * Asigna la meta u objetivo de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> El atributo meta queda actualizado con el valor recibido. <br>
	 *
	 * @param meta Meta de la campana a asignar. meta != null
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * Retorna el estado actual de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el estado de la campana. Debe corresponder a una de las
	 *         constantes ESTADO de esta clase
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual de la campana ambiental. <b>pre</b> El objeto debe
	 * estar inicializado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 *
	 * @param estado Estado de la campana a asignar. Debe ser uno de los valores de
	 *               las constantes ESTADO. estado != null
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna los resultados obtenidos al finalizar la campana ambiental.
	 * <b>pre</b> El objeto debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con los resultados de la campana. Puede ser null si la campana
	 *         aun no ha finalizado
	 */
	public String getResultados() {
		return resultado;
	}

	/**
	 * Asigna los resultados obtenidos al finalizar la campana ambiental. <b>pre</b>
	 * El objeto debe estar inicializado. <br>
	 * <b>post</b> El atributo resultado queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param resultado Resultados de la campana a registrar. Puede ser null si la
	 *                  campana aun no ha finalizado
	 */
	public void setResultados(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Retorna una representacion en cadena de texto del objeto CampanaAmbientalDTO.
	 * <b>pre</b> El objeto debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con los atributos principales del objeto formateados
	 */
	@Override
	public String toString() {
		return "CampanaAmbiental [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
}