package co.edu.unbosque.model;

import java.time.LocalDateTime;

/**
 * Representa un visitante DTO registrado dentro del sistema GreenBuilding
 * Manager. Esta clase almacena la informacion relacionada con las personas
 * externas autorizadas para ingresar al conjunto residencial.
 * 
 * Un visitante puede pertenecer a diferentes categorias como ocasional,
 * frecuente, domiciliario, proveedor o contratista.
 * 
 * <b>pre</b> Los datos principales del visitante deben ser validos y el
 * apartamento destino debe existir previamente en el sistema. <br>
 * <b>post</b> El visitante queda disponible para procesos de transferencia de
 * informacion y control de acceso dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class VisitanteDTO extends Persona {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3560497942647256027L;

	/** Tipo de visitante ocasional. */
	private final String TIPO_OCASIONAL = "OCASIONAL";

	/** Tipo de visitante frecuente. */
	private final String TIPO_FRECUENTE = "FRECUENTE";

	/** Tipo de visitante domiciliario. */
	private final String TIPO_DOMICILIARIO = "DOMICILIARIO";

	/** Tipo de visitante proveedor. */
	private final String TIPO_PROVEEDOR = "PROVEEDOR";

	/** Tipo de visitante contratista. */
	private final String TIPO_CONTRATISTA = "CONTRATISTA";

	/** Tipo de visitante. */
	private String tipo;

	/** Apartamento destino del visitante. */
	private Apartamento apartamentoDestino;

	/** Persona que autorizo el ingreso del visitante. */
	private String autorizadoPor;

	/** Fecha y hora de autorizacion del visitante. */
	private LocalDateTime fechaAutorizacion;

	/** Fecha y hora de vencimiento de la autorizacion. */
	private LocalDateTime fechaVencimiento;

	/** Estado del visitante dentro del sistema. */
	private boolean activo;

	/**
	 * Constructor por defecto. Crea un objeto VisitanteDTO sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto VisitanteDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public VisitanteDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto VisitanteDTO con toda la informacion
	 * necesaria.
	 * 
	 * <b>pre</b> El id, nombre, cedula y tipo no deben ser null. <br>
	 * <b>post</b> Se crea un objeto VisitanteDTO inicializado con la informacion
	 * suministrada. <br>
	 * 
	 * @param id                 Identificador unico del visitante. id != null, id
	 *                           != ""
	 * @param nombre             Nombre del visitante. nombre != null, nombre != ""
	 * @param cedula             Cedula del visitante. cedula != null, cedula != ""
	 * @param telefono           Telefono del visitante.
	 * @param tipo               Tipo de visitante.
	 * @param apartamentoDestino Apartamento destino del visitante.
	 * @param autorizadoPor      Persona que autorizo el ingreso.
	 * @param fechaAutorizacion  Fecha y hora de autorizacion.
	 * @param fechaVencimiento   Fecha y hora de vencimiento.
	 * @param activo             Estado del visitante.
	 */
	public VisitanteDTO(String id, String nombre, String cedula, String telefono, String tipo,
			Apartamento apartamentoDestino, String autorizadoPor, LocalDateTime fechaAutorizacion,
			LocalDateTime fechaVencimiento, boolean activo) {
		super(id, nombre, cedula, telefono, null, null, null);
		this.tipo = tipo;
		this.apartamentoDestino = apartamentoDestino;
		this.autorizadoPor = autorizadoPor;
		this.fechaAutorizacion = fechaAutorizacion;
		this.fechaVencimiento = fechaVencimiento;
		this.activo = activo;
	}

	/**
	 * Retorna el tipo ocasional.
	 * 
	 * @return String con el tipo ocasional
	 */
	public String getTIPO_OCASIONAL() {
		return TIPO_OCASIONAL;
	}

	/**
	 * Retorna el tipo frecuente.
	 * 
	 * @return String con el tipo frecuente
	 */
	public String getTIPO_FRECUENTE() {
		return TIPO_FRECUENTE;
	}

	/**
	 * Retorna el tipo domiciliario.
	 * 
	 * @return String con el tipo domiciliario
	 */
	public String getTIPO_DOMICILIARIO() {
		return TIPO_DOMICILIARIO;
	}

	/**
	 * Retorna el tipo proveedor.
	 * 
	 * @return String con el tipo proveedor
	 */
	public String getTIPO_PROVEEDOR() {
		return TIPO_PROVEEDOR;
	}

	/**
	 * Retorna el tipo contratista.
	 * 
	 * @return String con el tipo contratista
	 */
	public String getTIPO_CONTRATISTA() {
		return TIPO_CONTRATISTA;
	}

	/**
	 * Retorna el tipo del visitante.
	 * 
	 * @return Tipo del visitante
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo del visitante.
	 * 
	 * @param tipo Nuevo tipo del visitante
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el apartamento destino del visitante.
	 * 
	 * @return Apartamento destino
	 */
	public Apartamento getApartamentoDestino() {
		return apartamentoDestino;
	}

	/**
	 * Asigna el apartamento destino del visitante.
	 * 
	 * @param apartamentoDestino Nuevo apartamento destino
	 */
	public void setApartamentoDestino(Apartamento apartamentoDestino) {
		this.apartamentoDestino = apartamentoDestino;
	}

	/**
	 * Retorna la persona que autorizo el ingreso.
	 * 
	 * @return Persona que autorizo el ingreso
	 */
	public String getAutorizadoPor() {
		return autorizadoPor;
	}

	/**
	 * Asigna la persona que autorizo el ingreso.
	 * 
	 * @param autorizadoPor Nueva persona autorizadora
	 */
	public void setAutorizadoPor(String autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}

	/**
	 * Retorna la fecha de autorizacion.
	 * 
	 * @return Fecha y hora de autorizacion
	 */
	public LocalDateTime getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	/**
	 * Asigna la fecha de autorizacion.
	 * 
	 * @param fechaAutorizacion Nueva fecha de autorizacion
	 */
	public void setFechaAutorizacion(LocalDateTime fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	/**
	 * Retorna la fecha de vencimiento.
	 * 
	 * @return Fecha y hora de vencimiento
	 */
	public LocalDateTime getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Asigna la fecha de vencimiento.
	 * 
	 * @param fechaVencimiento Nueva fecha de vencimiento
	 */
	public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Retorna el estado del visitante.
	 * 
	 * @return true si el visitante esta activo, false en caso contrario
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Asigna el estado del visitante.
	 * 
	 * @param activo Nuevo estado del visitante
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Retorna una representacion en texto del visitante.
	 * 
	 * @return String con la informacion principal del visitante
	 */
	@Override
	public String toString() {
		return "Visitante [id=" + getId() + ", nombre=" + getNombre() + ", cedula=" + getCedula() + ", tipo=" + tipo
				+ ", activo=" + activo + "]";
	}
}