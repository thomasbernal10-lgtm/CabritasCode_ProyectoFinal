package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa un objeto de transferencia de datos (DTO) para los paquetes o
 * correspondencia registrados dentro del sistema GreenBuilding Manager. Permite
 * transportar informacion relacionada con paquetes entre las diferentes capas
 * de la aplicacion sin exponer directamente la logica de negocio de la entidad
 * Paquete.
 * 
 * Cada objeto PaqueteDTO almacena informacion relacionada con el remitente, el
 * apartamento destino, el estado del paquete, las fechas de recepcion y
 * entrega, y las personas involucradas en el proceso de recepcion y entrega.
 * <b>pre</b> El apartamento destino debe estar registrado previamente en el
 * sistema. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * persistencia y visualizacion de informacion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PaqueteDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -7794089979029077282L;

	/**
	 * Constante que representa el estado de paquete recibido en porteria.
	 */
	private final String ESTADO_RECIBIDO = "RECIBIDO";

	/**
	 * Constante que representa el estado de paquete notificado al residente.
	 */
	private final String ESTADO_NOTIFICADO = "NOTIFICADO";

	/**
	 * Constante que representa el estado de paquete entregado.
	 */
	private final String ESTADO_ENTREGADO = "ENTREGADO";

	/**
	 * Constante que representa el estado de paquete devuelto.
	 */
	private final String ESTADO_DEVUELTO = "DEVUELTO";

	/** Identificador unico del paquete. */
	private String id;

	/** Descripcion general del paquete o correspondencia. */
	private String descripcion;

	/** Nombre de la persona o empresa remitente del paquete. */
	private String remitente;

	/** Apartamento destino al que pertenece el paquete recibido. */
	private Apartamento apartamentoDestino;

	/** Fecha y hora en la que el paquete fue recibido en porteria. */
	private LocalDateTime fechaRecepcion;

	/** Nombre del vigilante que recibio el paquete. */
	private String vigilanteRecibio;

	/** Estado actual del paquete dentro del sistema. */
	private String estado;

	/** Fecha y hora en la que el paquete fue entregado al residente. */
	private LocalDateTime fechaEntrega;

	/** Persona que recibio finalmente el paquete. */
	private String personaRecibio;

	/**
	 * Constructor por defecto. Crea un objeto PaqueteDTO sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto PaqueteDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public PaqueteDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto PaqueteDTO con sus atributos principales
	 * inicializados. <b>pre</b> Los parametros id, descripcion, remitente y estado
	 * no deben ser null ni vacios. El apartamento destino debe existir previamente
	 * en el sistema. <br>
	 * <b>post</b> Se crea un objeto PaqueteDTO completamente inicializado y listo
	 * para ser utilizado en procesos de transferencia de datos. <br>
	 * 
	 * @param id                 Identificador unico del paquete. id != null, id !=
	 *                           ""
	 * @param descripcion        Descripcion general del paquete. descripcion !=
	 *                           null, descripcion != ""
	 * @param remitente          Nombre del remitente del paquete. remitente !=
	 *                           null, remitente != ""
	 * @param apartamentoDestino Apartamento destino asociado al paquete.
	 *                           apartamentoDestino != null
	 * @param fechaRecepcion     Fecha y hora de recepcion del paquete.
	 *                           fechaRecepcion != null
	 * @param vigilanteRecibio   Vigilante que recibio el paquete. vigilanteRecibio
	 *                           != null, vigilanteRecibio != ""
	 * @param estado             Estado inicial del paquete. estado != null, estado
	 *                           != ""
	 */
	public PaqueteDTO(String id, String descripcion, String remitente, Apartamento apartamentoDestino,
			LocalDateTime fechaRecepcion, String vigilanteRecibio, String estado) {
		this.id = id;
		this.descripcion = descripcion;
		this.remitente = remitente;
		this.apartamentoDestino = apartamentoDestino;
		this.fechaRecepcion = fechaRecepcion;
		this.vigilanteRecibio = vigilanteRecibio;
		this.estado = estado;
	}

	/**
	 * Retorna la constante correspondiente al estado RECIBIDO. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "RECIBIDO"
	 */
	public String getESTADO_RECIBIDO() {
		return ESTADO_RECIBIDO;
	}

	/**
	 * Retorna la constante correspondiente al estado NOTIFICADO. <b>pre</b> El
	 * objeto PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "NOTIFICADO"
	 */
	public String getESTADO_NOTIFICADO() {
		return ESTADO_NOTIFICADO;
	}

	/**
	 * Retorna la constante correspondiente al estado ENTREGADO. <b>pre</b> El
	 * objeto PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ENTREGADO"
	 */
	public String getESTADO_ENTREGADO() {
		return ESTADO_ENTREGADO;
	}

	/**
	 * Retorna la constante correspondiente al estado DEVUELTO. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "DEVUELTO"
	 */
	public String getESTADO_DEVUELTO() {
		return ESTADO_DEVUELTO;
	}

	/**
	 * Retorna el identificador unico del paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del paquete
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del paquete. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna la descripcion general del paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la descripcion del paquete
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion general del paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo descripcion queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param descripcion Nueva descripcion del paquete. descripcion != null,
	 *                    descripcion != ""
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el nombre del remitente del paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el remitente del paquete
	 */
	public String getRemitente() {
		return remitente;
	}

	/**
	 * Asigna el remitente del paquete. <b>pre</b> El objeto PaqueteDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo remitente queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param remitente Nuevo remitente del paquete. remitente != null, remitente !=
	 *                  ""
	 */
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	/**
	 * Retorna el apartamento destino asociado al paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento destino del paquete
	 */
	public Apartamento getApartamentoDestino() {
		return apartamentoDestino;
	}

	/**
	 * Asigna el apartamento destino asociado al paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamentoDestino queda actualizado con el objeto
	 * recibido. <br>
	 * 
	 * @param apartamentoDestino Nuevo apartamento destino. apartamentoDestino !=
	 *                           null
	 */
	public void setApartamentoDestino(Apartamento apartamentoDestino) {
		this.apartamentoDestino = apartamentoDestino;
	}

	/**
	 * Retorna la fecha y hora de recepcion del paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la fecha de recepcion
	 */
	public LocalDateTime getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * Asigna la fecha y hora de recepcion del paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaRecepcion queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaRecepcion Nueva fecha de recepcion. fechaRecepcion != null
	 */
	public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * Retorna el nombre del vigilante que recibio el paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre del vigilante
	 */
	public String getVigilanteRecibio() {
		return vigilanteRecibio;
	}

	/**
	 * Asigna el vigilante que recibio el paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo vigilanteRecibio queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param vigilanteRecibio Nuevo vigilante receptor. vigilanteRecibio != null,
	 *                         vigilanteRecibio != ""
	 */
	public void setVigilanteRecibio(String vigilanteRecibio) {
		this.vigilanteRecibio = vigilanteRecibio;
	}

	/**
	 * Retorna el estado actual del paquete. <b>pre</b> El objeto PaqueteDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado actual del paquete
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual del paquete. <b>pre</b> El objeto PaqueteDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado del paquete. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna la fecha y hora de entrega del paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la fecha de entrega
	 */
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Asigna la fecha y hora de entrega del paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaEntrega queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param fechaEntrega Nueva fecha de entrega. Puede ser null
	 */
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Retorna la persona que recibio finalmente el paquete. <b>pre</b> El objeto
	 * PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre de la persona que recibio el paquete
	 */
	public String getPersonaRecibio() {
		return personaRecibio;
	}

	/**
	 * Asigna la persona que recibio el paquete. <b>pre</b> El objeto PaqueteDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo personaRecibio queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param personaRecibio Nueva persona que recibio el paquete. Puede ser null
	 */
	public void setPersonaRecibio(String personaRecibio) {
		this.personaRecibio = personaRecibio;
	}

	/**
	 * Retorna una representacion en texto del paquete con sus datos principales.
	 * <b>pre</b> El objeto PaqueteDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, descripcion, remitente, estado y fecha de recepcion
	 *         del paquete
	 */
	@Override
	public String toString() {
		return "Paquete [id=" + id + ", descripcion=" + descripcion + ", remitente=" + remitente + ", estado=" + estado
				+ ", fechaRecepcion=" + fechaRecepcion + "]";
	}
}