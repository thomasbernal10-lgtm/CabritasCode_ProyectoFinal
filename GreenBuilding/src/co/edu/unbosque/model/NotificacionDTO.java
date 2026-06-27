package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Objeto de transferencia de datos (DTO) que representa una notificacion
 * generada por el sistema. Se utiliza para transportar la informacion de la
 * notificacion entre las capas del sistema sin exponer directamente la entidad
 * del modelo. <b>pre</b> El DTO debe ser construido con datos validos
 * provenientes de la capa de persistencia o de la interfaz grafica. <br>
 * <b>post</b> El objeto NotificacionDTO queda disponible para ser transferido
 * entre las capas de modelo, control y vista del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class NotificacionDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -8473291671923639638L;

	/**
	 * Constante que representa una notificacion relacionada con una reserva de zona
	 * comun.
	 */
	private final String TIPO_RESERVA = "RESERVA";

	/**
	 * Constante que representa una notificacion relacionada con un paquete recibido
	 * en porteria.
	 */
	private final String TIPO_PAQUETE = "PAQUETE";

	/**
	 * Constante que representa una notificacion relacionada con un pago o
	 * obligacion pendiente.
	 */
	private final String TIPO_PAGO = "PAGO";

	/**
	 * Constante que representa una notificacion relacionada con la generacion de
	 * una multa.
	 */
	private final String TIPO_MULTA = "MULTA";

	/**
	 * Constante que representa una notificacion relacionada con una solicitud de
	 * mantenimiento.
	 */
	private final String TIPO_MANTENIMIENTO = "MANTENIMIENTO";

	/**
	 * Constante que representa una notificacion relacionada con un incidente de
	 * convivencia.
	 */
	private final String TIPO_INCIDENTE = "INCIDENTE";

	/**
	 * Constante que representa una notificacion relacionada con una campana
	 * ambiental o comunitaria.
	 */
	private final String TIPO_CAMPANA = "CAMPANA";

	/**
	 * Constante que representa una notificacion relacionada con la autorizacion de
	 * un visitante.
	 */
	private final String TIPO_VISITANTE = "VISITANTE";

	/** Identificador unico de la notificacion. */
	private String id;

	/** Correo electronico o identificador del destinatario de la notificacion. */
	private String destinatario;

	/** Asunto o titulo de la notificacion. */
	private String asunto;

	/** Contenido o mensaje detallado de la notificacion. */
	private String cuerpo;

	/**
	 * Tipo de notificacion (RESERVA, PAQUETE, PAGO, MULTA, MANTENIMIENTO,
	 * INCIDENTE, CAMPANA, VISITANTE).
	 */
	private String tipo;

	/** Fecha y hora en que se envio o se programo el envio de la notificacion. */
	private LocalDateTime fechaEnvio;

	/** Indica si la notificacion fue enviada exitosamente al destinatario. */
	private boolean enviada;

	/**
	 * Descripcion del error ocurrido al intentar enviar la notificacion. Puede ser
	 * null si no hubo error.
	 */
	private String errorEnvio;

	/**
	 * Constructor por defecto. Crea un NotificacionDTO sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto NotificacionDTO con todos sus atributos en null
	 * o valores por defecto. El atributo enviada queda en false. <br>
	 */
	public NotificacionDTO() {
	}

	/**
	 * Constructor completo. Crea un NotificacionDTO con sus atributos principales
	 * inicializados. <b>pre</b> Los parametros id, destinatario, asunto, cuerpo,
	 * tipo y fechaEnvio no deben ser null ni vacios. <br>
	 * <b>post</b> Se crea un objeto NotificacionDTO listo para ser transferido
	 * entre capas del sistema. El atributo enviada se inicializa en false y
	 * errorEnvio queda en null hasta que se intente el envio. <br>
	 *
	 * @param id           Identificador unico de la notificacion. id != null, id !=
	 *                     ""
	 * @param destinatario Correo o identificador del destinatario. destinatario !=
	 *                     null, destinatario != ""
	 * @param asunto       Asunto o titulo de la notificacion. asunto != null,
	 *                     asunto != ""
	 * @param cuerpo       Contenido detallado de la notificacion. cuerpo != null,
	 *                     cuerpo != ""
	 * @param tipo         Tipo de notificacion. tipo != null, tipo != ""
	 * @param fechaEnvio   Fecha y hora de envio programado. fechaEnvio != null
	 */
	public NotificacionDTO(String id, String destinatario, String asunto, String cuerpo, String tipo,
			LocalDateTime fechaEnvio) {
		this.id = id;
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.tipo = tipo;
		this.fechaEnvio = fechaEnvio;
		this.enviada = false;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por reserva.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "RESERVA"
	 */
	public String getTIPO_RESERVA() {
		return TIPO_RESERVA;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por paquete.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PAQUETE"
	 */
	public String getTIPO_PAQUETE() {
		return TIPO_PAQUETE;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por pago.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PAGO"
	 */
	public String getTIPO_PAGO() {
		return TIPO_PAGO;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por multa.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MULTA"
	 */
	public String getTIPO_MULTA() {
		return TIPO_MULTA;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por
	 * mantenimiento. <b>pre</b> El objeto NotificacionDTO debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MANTENIMIENTO"
	 */
	public String getTIPO_MANTENIMIENTO() {
		return TIPO_MANTENIMIENTO;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por incidente.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "INCIDENTE"
	 */
	public String getTIPO_INCIDENTE() {
		return TIPO_INCIDENTE;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por campana.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "CAMPANA"
	 */
	public String getTIPO_CAMPANA() {
		return TIPO_CAMPANA;
	}

	/**
	 * Retorna la constante que representa el tipo de notificacion por visitante.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "VISITANTE"
	 */
	public String getTIPO_VISITANTE() {
		return TIPO_VISITANTE;
	}

	/**
	 * Retorna el identificador unico de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la notificacion
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador de la notificacion. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el destinatario de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el correo o identificador del destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Asigna el destinatario de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo destinatario queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param destinatario Nuevo destinatario de la notificacion. destinatario !=
	 *                     null, destinatario != ""
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Retorna el asunto de la notificacion. <b>pre</b> El objeto NotificacionDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el asunto de la notificacion
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * Asigna el asunto de la notificacion. <b>pre</b> El objeto NotificacionDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo asunto queda actualizado con el valor recibido. <br>
	 *
	 * @param asunto Nuevo asunto de la notificacion. asunto != null, asunto != ""
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * Retorna el cuerpo o contenido detallado de la notificacion. <b>pre</b> El
	 * objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el contenido de la notificacion
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * Asigna el cuerpo o contenido detallado de la notificacion. <b>pre</b> El
	 * objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo cuerpo queda actualizado con el valor recibido. <br>
	 *
	 * @param cuerpo Nuevo contenido de la notificacion. cuerpo != null, cuerpo !=
	 *               ""
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * Retorna el tipo de la notificacion. <b>pre</b> El objeto NotificacionDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el tipo de la notificacion (RESERVA, PAQUETE, PAGO, etc.)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la notificacion. <b>pre</b> El objeto NotificacionDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 *
	 * @param tipo Nuevo tipo de la notificacion. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la fecha y hora de envio de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDateTime con la fecha y hora de envio, o null si no fue asignada
	 */
	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * Asigna la fecha y hora de envio de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaEnvio queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param fechaEnvio Nueva fecha y hora de envio. fechaEnvio != null
	 */
	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * Indica si la notificacion fue enviada exitosamente al destinatario.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si la notificacion fue enviada, false en caso contrario
	 */
	public boolean isEnviada() {
		return enviada;
	}

	/**
	 * Asigna el estado de envio de la notificacion. <b>pre</b> El objeto
	 * NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo enviada queda actualizado con el valor recibido. <br>
	 *
	 * @param enviada true si la notificacion fue enviada exitosamente, false en
	 *                caso contrario
	 */
	public void setEnviada(boolean enviada) {
		this.enviada = enviada;
	}

	/**
	 * Retorna la descripcion del error ocurrido al intentar enviar la notificacion.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el mensaje de error, o null si el envio fue exitoso
	 */
	public String getErrorEnvio() {
		return errorEnvio;
	}

	/**
	 * Asigna la descripcion del error ocurrido al intentar enviar la notificacion.
	 * <b>pre</b> El objeto NotificacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo errorEnvio queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param errorEnvio Descripcion del error de envio. Puede ser null si no hubo
	 *                   error
	 */
	public void setErrorEnvio(String errorEnvio) {
		this.errorEnvio = errorEnvio;
	}

	/**
	 * Retorna una representacion en texto del NotificacionDTO con sus datos
	 * principales. <b>pre</b> El objeto NotificacionDTO debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, destinatario, tipo, enviada y fechaEnvio de la
	 *         notificacion
	 */
	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", destinatario=" + destinatario + ", tipo=" + tipo + ", enviada=" + enviada
				+ ", fechaEnvio=" + fechaEnvio + "]";
	}
}