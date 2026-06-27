package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Representa un objeto de transferencia de datos (DTO) para reservas de zonas
 * comunes dentro del sistema GreenBuilding Manager. Esta clase permite
 * transportar informacion relacionada con solicitudes de reserva entre las
 * diferentes capas de la aplicacion sin exponer directamente la logica de
 * negocio.
 * 
 * Una reserva permite gestionar fechas, horarios, estados y motivos de
 * cancelacion asociados al uso de zonas comunes dentro del conjunto
 * residencial. <b>pre</b> El apartamento y la zona comun deben existir
 * previamente en el sistema. La fecha y las horas deben corresponder a valores
 * validos. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * consulta y administracion de reservas dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ReservaDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 4263476506300152373L;

	/** Estado pendiente de la reserva. */
	private final String ESTADO_PENDIENTE = "PENDIENTE";

	/** Estado confirmada de la reserva. */
	private final String ESTADO_CONFIRMADA = "CONFIRMADA";

	/** Estado cancelada de la reserva. */
	private final String ESTADO_CANCELADA = "CANCELADA";

	/** Estado incumplida de la reserva. */
	private final String ESTADO_INCUMPLIDA = "INCUMPLIDA";

	/** Estado completada de la reserva. */
	private final String ESTADO_COMPLETADA = "COMPLETADA";

	/** Identificador unico de la reserva. */
	private String id;

	/** Apartamento que realiza la reserva. */
	private Apartamento apartamento;

	/** Zona comun reservada. */
	private ZonaComun zona;

	/** Fecha de la reserva. */
	private LocalDate fecha;

	/** Hora de inicio de la reserva. */
	private LocalTime horaInicio;

	/** Hora de finalizacion de la reserva. */
	private LocalTime horaFin;

	/** Estado actual de la reserva. */
	private String estado;

	/** Fecha y hora en la que se realizo la solicitud. */
	private LocalDateTime fechaSolicitud;

	/** Motivo de cancelacion de la reserva. */
	private String motivoCancelacion;

	/**
	 * Constructor por defecto. Crea un objeto ReservaDTO sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ReservaDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public ReservaDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto ReservaDTO con todos sus atributos
	 * inicializados. <b>pre</b> El id, apartamento, zona, fecha, horaInicio,
	 * horaFin, estado y fechaSolicitud no deben ser null. <br>
	 * <b>post</b> Se crea un objeto ReservaDTO completamente inicializado y listo
	 * para ser utilizado dentro del sistema. <br>
	 * 
	 * @param id                Identificador unico de la reserva. id != null, id !=
	 *                          ""
	 * @param apartamento       Apartamento que realiza la reserva. apartamento !=
	 *                          null
	 * @param zona              Zona comun reservada. zona != null
	 * @param fecha             Fecha de la reserva. fecha != null
	 * @param horaInicio        Hora de inicio de la reserva. horaInicio != null
	 * @param horaFin           Hora de finalizacion de la reserva. horaFin != null
	 * @param estado            Estado actual de la reserva. estado != null, estado
	 *                          != ""
	 * @param fechaSolicitud    Fecha y hora de solicitud de la reserva.
	 *                          fechaSolicitud != null
	 * @param motivoCancelacion Motivo de cancelacion de la reserva
	 */
	public ReservaDTO(String id, Apartamento apartamento, ZonaComun zona, LocalDate fecha, LocalTime horaInicio,
			LocalTime horaFin, String estado, LocalDateTime fechaSolicitud, String motivoCancelacion) {
		this.id = id;
		this.apartamento = apartamento;
		this.zona = zona;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.estado = estado;
		this.fechaSolicitud = fechaSolicitud;
		this.motivoCancelacion = motivoCancelacion;
	}

	/**
	 * Retorna el estado pendiente de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado pendiente
	 */
	public String getESTADO_PENDIENTE() {
		return ESTADO_PENDIENTE;
	}

	/**
	 * Retorna el estado confirmada de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado confirmada
	 */
	public String getESTADO_CONFIRMADA() {
		return ESTADO_CONFIRMADA;
	}

	/**
	 * Retorna el estado cancelada de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado cancelada
	 */
	public String getESTADO_CANCELADA() {
		return ESTADO_CANCELADA;
	}

	/**
	 * Retorna el estado incumplida de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado incumplida
	 */
	public String getESTADO_INCUMPLIDA() {
		return ESTADO_INCUMPLIDA;
	}

	/**
	 * Retorna el estado completada de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado completada
	 */
	public String getESTADO_COMPLETADA() {
		return ESTADO_COMPLETADA;
	}

	/**
	 * Retorna el identificador unico de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id de la reserva
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador de la reserva. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el apartamento asociado a la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento asociado a la reserva
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado a la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param apartamento Nuevo apartamento asociado. apartamento != null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna la zona comun asociada a la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto ZonaComun asociado a la reserva
	 */
	public ZonaComun getZona() {
		return zona;
	}

	/**
	 * Asigna la zona comun asociada a la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo zona queda actualizado con el objeto recibido. <br>
	 * 
	 * @param zona Nueva zona comun asociada. zona != null
	 */
	public void setZona(ZonaComun zona) {
		this.zona = zona;
	}

	/**
	 * Retorna la fecha de la reserva. <b>pre</b> El objeto ReservaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDate con la fecha de la reserva
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Asigna la fecha de la reserva. <b>pre</b> El objeto ReservaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo fecha queda actualizado con el valor recibido. <br>
	 * 
	 * @param fecha Nueva fecha de la reserva. fecha != null
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna la hora de inicio de la reserva. <b>pre</b> El objeto ReservaDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalTime con la hora de inicio
	 */
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Asigna la hora de inicio de la reserva. <b>pre</b> El objeto ReservaDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo horaInicio queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param horaInicio Nueva hora de inicio. horaInicio != null
	 */
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Retorna la hora de finalizacion de la reserva. <b>pre</b> El objeto
	 * ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalTime con la hora de finalizacion
	 */
	public LocalTime getHoraFin() {
		return horaFin;
	}

	/**
	 * Asigna la hora de finalizacion de la reserva. <b>pre</b> El objeto ReservaDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo horaFin queda actualizado con el valor recibido. <br>
	 * 
	 * @param horaFin Nueva hora de finalizacion. horaFin != null
	 */
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * Retorna el estado de la reserva. <b>pre</b> El objeto ReservaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado de la reserva
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado de la reserva. <b>pre</b> El objeto ReservaDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado de la reserva. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna la fecha y hora de solicitud de la reserva. <b>pre</b> El objeto
	 * ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la fecha de solicitud
	 */
	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Asigna la fecha y hora de solicitud de la reserva. <b>pre</b> El objeto
	 * ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaSolicitud queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaSolicitud Nueva fecha y hora de solicitud. fechaSolicitud != null
	 */
	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Retorna el motivo de cancelacion de la reserva. <b>pre</b> El objeto
	 * ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el motivo de cancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	/**
	 * Asigna el motivo de cancelacion de la reserva. <b>pre</b> El objeto
	 * ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo motivoCancelacion queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param motivoCancelacion Nuevo motivo de cancelacion
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	/**
	 * Retorna una representacion en texto de la reserva con sus datos principales.
	 * <b>pre</b> El objeto ReservaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con los datos principales de la reserva
	 */
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", apartamento=" + apartamento + ", zona=" + zona + ", fecha=" + fecha
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", estado=" + estado + ", fechaSolicitud="
				+ fechaSolicitud + ", motivoCancelacion=" + motivoCancelacion + "]";
	}
}