package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa una solicitud de mantenimiento dentro del sistema GreenBuilding
 * Manager. Esta clase DTO permite transportar la informacion relacionada con
 * solicitudes de mantenimiento entre las diferentes capas de la aplicacion.
 * 
 * Una solicitud puede estar asociada a un apartamento o a una zona comun,
 * ademas de contar con un tipo, prioridad y estado especifico. <b>pre</b> Los
 * datos principales de la solicitud deben ser validos y las entidades
 * relacionadas deben existir previamente en el sistema. <br>
 * <b>post</b> La solicitud queda disponible para procesos de transferencia de
 * informacion y gestion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class SolicitudMantenimientoDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -8354365925067148389L;

	/** Tipo de mantenimiento hidraulico. */
	private final String TIPO_HIDRAULICO = "HIDRAULICO";

	/** Tipo de mantenimiento electrico. */
	private final String TIPO_ELECTRICO = "ELECTRICO";

	/** Tipo de mantenimiento estructural. */
	private final String TIPO_ESTRUCTURAL = "ESTRUCTURAL";

	/** Tipo de mantenimiento de aseo. */
	private final String TIPO_ASEO = "ASEO";

	/** Tipo de mantenimiento de jardineria. */
	private final String TIPO_JARDINERIA = "JARDINERIA";

	/** Tipo de mantenimiento de sostenibilidad. */
	private final String TIPO_SOSTENIBILIDAD = "SOSTENIBILIDAD";

	/** Prioridad alta para la solicitud. */
	private final String PRIORIDAD_ALTA = "ALTA";

	/** Prioridad media para la solicitud. */
	private final String PRIORIDAD_MEDIA = "MEDIA";

	/** Prioridad baja para la solicitud. */
	private final String PRIORIDAD_BAJA = "BAJA";

	/** Estado registrada para la solicitud. */
	private final String ESTADO_REGISTRADA = "REGISTRADA";

	/** Estado asignada para la solicitud. */
	private final String ESTADO_ASIGNADA = "ASIGNADA";

	/** Estado en progreso para la solicitud. */
	private final String ESTADO_EN_PROGRESO = "EN_PROGRESO";

	/** Estado completada para la solicitud. */
	private final String ESTADO_COMPLETADA = "COMPLETADA";

	/** Estado vencida para la solicitud. */
	private final String ESTADO_VENCIDA = "VENCIDA";

	/** Estado rechazada para la solicitud. */
	private final String ESTADO_RECHAZADA = "RECHAZADA";

	/** Identificador unico de la solicitud. */
	private String id;

	/** Descripcion de la solicitud de mantenimiento. */
	private String descripcion;

	/** Tipo de solicitud de mantenimiento. */
	private String tipo;

	/** Nivel de prioridad de la solicitud. */
	private String prioridad;

	/** Estado actual de la solicitud. */
	private String estado;

	/** Apartamento solicitante del mantenimiento. */
	private Apartamento aptSolicitante;

	/** Zona comun afectada por la solicitud. */
	private ZonaComun zonaAfectada;

	/** Tecnico asignado a la solicitud. */
	private String tecnicoAsignado;

	/** Fecha y hora de registro de la solicitud. */
	private LocalDateTime fechaRegistro;

	/** Fecha limite para resolver la solicitud. */
	private LocalDate fechaLimite;

	/** Fecha y hora de cierre de la solicitud. */
	private LocalDateTime fechaCierre;

	/** Observaciones adicionales de la solicitud. */
	private String observacion;

	/**
	 * Constructor por defecto. Crea un objeto SolicitudMantenimientoDTO sin datos
	 * inicializados. <b>pre</b> No existen precondiciones para este constructor.
	 * <br>
	 * <b>post</b> Se crea un objeto SolicitudMantenimientoDTO con todos sus
	 * atributos en null o valores por defecto. <br>
	 */
	public SolicitudMantenimientoDTO() {
	}

	/**
	 * Constructor parcial. Crea un objeto SolicitudMantenimientoDTO con los datos
	 * principales de la solicitud. <b>pre</b> El id, descripcion, tipo, prioridad,
	 * estado, fechaRegistro y fechaLimite no deben ser null. <br>
	 * <b>post</b> Se crea un objeto SolicitudMantenimientoDTO inicializado con la
	 * informacion principal de la solicitud. <br>
	 * 
	 * @param id             Identificador unico de la solicitud. id != null, id !=
	 *                       ""
	 * @param descripcion    Descripcion de la solicitud. descripcion != null,
	 *                       descripcion != ""
	 * @param tipo           Tipo de mantenimiento solicitado. tipo != null, tipo !=
	 *                       ""
	 * @param prioridad      Nivel de prioridad de la solicitud. prioridad != null,
	 *                       prioridad != ""
	 * @param estado         Estado actual de la solicitud. estado != null, estado
	 *                       != ""
	 * @param aptSolicitante Apartamento solicitante.
	 * @param zonaAfectada   Zona comun afectada.
	 * @param fechaRegistro  Fecha y hora de registro. fechaRegistro != null
	 * @param fechaLimite    Fecha limite para atender la solicitud. fechaLimite !=
	 *                       null
	 */
	public SolicitudMantenimientoDTO(String id, String descripcion, String tipo, String prioridad, String estado,
			Apartamento aptSolicitante, ZonaComun zonaAfectada, LocalDateTime fechaRegistro, LocalDate fechaLimite) {
		this.id = id;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.prioridad = prioridad;
		this.estado = estado;
		this.aptSolicitante = aptSolicitante;
		this.zonaAfectada = zonaAfectada;
		this.fechaRegistro = fechaRegistro;
		this.fechaLimite = fechaLimite;
	}

	/**
	 * Retorna el tipo hidraulico.
	 * 
	 * @return String con el tipo hidraulico
	 */
	public String getTIPO_HIDRAULICO() {
		return TIPO_HIDRAULICO;
	}

	/**
	 * Retorna el tipo electrico.
	 * 
	 * @return String con el tipo electrico
	 */
	public String getTIPO_ELECTRICO() {
		return TIPO_ELECTRICO;
	}

	/**
	 * Retorna el tipo estructural.
	 * 
	 * @return String con el tipo estructural
	 */
	public String getTIPO_ESTRUCTURAL() {
		return TIPO_ESTRUCTURAL;
	}

	/**
	 * Retorna el tipo aseo.
	 * 
	 * @return String con el tipo aseo
	 */
	public String getTIPO_ASEO() {
		return TIPO_ASEO;
	}

	/**
	 * Retorna el tipo jardineria.
	 * 
	 * @return String con el tipo jardineria
	 */
	public String getTIPO_JARDINERIA() {
		return TIPO_JARDINERIA;
	}

	/**
	 * Retorna el tipo sostenibilidad.
	 * 
	 * @return String con el tipo sostenibilidad
	 */
	public String getTIPO_SOSTENIBILIDAD() {
		return TIPO_SOSTENIBILIDAD;
	}

	/**
	 * Retorna la prioridad alta.
	 * 
	 * @return String con la prioridad alta
	 */
	public String getPRIORIDAD_ALTA() {
		return PRIORIDAD_ALTA;
	}

	/**
	 * Retorna la prioridad media.
	 * 
	 * @return String con la prioridad media
	 */
	public String getPRIORIDAD_MEDIA() {
		return PRIORIDAD_MEDIA;
	}

	/**
	 * Retorna la prioridad baja.
	 * 
	 * @return String con la prioridad baja
	 */
	public String getPRIORIDAD_BAJA() {
		return PRIORIDAD_BAJA;
	}

	/**
	 * Retorna el estado registrada.
	 * 
	 * @return String con el estado registrada
	 */
	public String getESTADO_REGISTRADA() {
		return ESTADO_REGISTRADA;
	}

	/**
	 * Retorna el estado asignada.
	 * 
	 * @return String con el estado asignada
	 */
	public String getESTADO_ASIGNADA() {
		return ESTADO_ASIGNADA;
	}

	/**
	 * Retorna el estado en progreso.
	 * 
	 * @return String con el estado en progreso
	 */
	public String getESTADO_EN_PROGRESO() {
		return ESTADO_EN_PROGRESO;
	}

	/**
	 * Retorna el estado completada.
	 * 
	 * @return String con el estado completada
	 */
	public String getESTADO_COMPLETADA() {
		return ESTADO_COMPLETADA;
	}

	/**
	 * Retorna el estado vencida.
	 * 
	 * @return String con el estado vencida
	 */
	public String getESTADO_VENCIDA() {
		return ESTADO_VENCIDA;
	}

	/**
	 * Retorna el estado rechazada.
	 * 
	 * @return String con el estado rechazada
	 */
	public String getESTADO_RECHAZADA() {
		return ESTADO_RECHAZADA;
	}

	/**
	 * Retorna el identificador de la solicitud.
	 * 
	 * @return Identificador de la solicitud
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador de la solicitud.
	 * 
	 * @param id Nuevo identificador de la solicitud
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna la descripcion de la solicitud.
	 * 
	 * @return Descripcion de la solicitud
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion de la solicitud.
	 * 
	 * @param descripcion Nueva descripcion de la solicitud
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el tipo de solicitud.
	 * 
	 * @return Tipo de solicitud
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de solicitud.
	 * 
	 * @param tipo Nuevo tipo de solicitud
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la prioridad de la solicitud.
	 * 
	 * @return Prioridad de la solicitud
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * Asigna la prioridad de la solicitud.
	 * 
	 * @param prioridad Nueva prioridad de la solicitud
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Retorna el estado de la solicitud.
	 * 
	 * @return Estado de la solicitud
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado de la solicitud.
	 * 
	 * @param estado Nuevo estado de la solicitud
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el apartamento solicitante.
	 * 
	 * @return Apartamento solicitante
	 */
	public Apartamento getAptSolicitante() {
		return aptSolicitante;
	}

	/**
	 * Asigna el apartamento solicitante.
	 * 
	 * @param aptSolicitante Nuevo apartamento solicitante
	 */
	public void setAptSolicitante(Apartamento aptSolicitante) {
		this.aptSolicitante = aptSolicitante;
	}

	/**
	 * Retorna la zona afectada.
	 * 
	 * @return Zona comun afectada
	 */
	public ZonaComun getZonaAfectada() {
		return zonaAfectada;
	}

	/**
	 * Asigna la zona afectada.
	 * 
	 * @param zonaAfectada Nueva zona comun afectada
	 */
	public void setZonaAfectada(ZonaComun zonaAfectada) {
		this.zonaAfectada = zonaAfectada;
	}

	/**
	 * Retorna el tecnico asignado.
	 * 
	 * @return Tecnico asignado
	 */
	public String getTecnicoAsignado() {
		return tecnicoAsignado;
	}

	/**
	 * Asigna el tecnico encargado.
	 * 
	 * @param tecnicoAsignado Nuevo tecnico asignado
	 */
	public void setTecnicoAsignado(String tecnicoAsignado) {
		this.tecnicoAsignado = tecnicoAsignado;
	}

	/**
	 * Retorna la fecha de registro.
	 * 
	 * @return Fecha y hora de registro
	 */
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Asigna la fecha de registro.
	 * 
	 * @param fechaRegistro Nueva fecha de registro
	 */
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Retorna la fecha limite.
	 * 
	 * @return Fecha limite de la solicitud
	 */
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * Asigna la fecha limite.
	 * 
	 * @param fechaLimite Nueva fecha limite
	 */
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * Retorna la fecha de cierre.
	 * 
	 * @return Fecha y hora de cierre
	 */
	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Asigna la fecha de cierre.
	 * 
	 * @param fechaCierre Nueva fecha de cierre
	 */
	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Retorna las observaciones de la solicitud.
	 * 
	 * @return Observaciones de la solicitud
	 */
	public String getObservaciones() {
		return observacion;
	}

	/**
	 * Asigna observaciones a la solicitud.
	 * 
	 * @param observacion Nuevas observaciones
	 */
	public void setObservaciones(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Retorna una representacion en texto de la solicitud de mantenimiento.
	 * 
	 * @return String con la informacion principal de la solicitud
	 */
	@Override
	public String toString() {
		return "SolicitudMantenimiento [id=" + id + ", tipo=" + tipo + ", prioridad=" + prioridad + ", estado=" + estado
				+ ", fechaRegistro=" + fechaRegistro + "]";
	}
}