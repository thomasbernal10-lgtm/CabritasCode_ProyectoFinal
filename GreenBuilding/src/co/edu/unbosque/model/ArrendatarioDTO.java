package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Objeto de Transferencia de Datos (DTO) que representa a un arrendatario del
 * conjunto residencial. Esta clase se utiliza para transportar la informacion
 * de un arrendatario entre las capas del sistema (modelo, controlador, vista)
 * sin exponer directamente la entidad de dominio ni sus interfaces Notificable
 * y Sancionable. <b>pre</b> El apartamento referenciado debe estar registrado
 * en el sistema. <br>
 * <b>post</b> El DTO queda disponible para ser usado en operaciones de lectura,
 * creacion o actualizacion de arrendatarios en el sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ArrendatarioDTO extends Persona {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -7978087242524818513L;

	/** Apartamento del conjunto que ocupa el arrendatario bajo contrato. */
	private Apartamento apartamento;

	/** Fecha en la que inicio el contrato de arrendamiento. */
	private LocalDate fechaInicioContrato;

	/** Fecha en la que vence o termina el contrato de arrendamiento. */
	private LocalDate fechaFinContrato;

	/** Indica si el arrendatario tiene un contrato activo vigente en el sistema. */
	private boolean activo;

	/**
	 * Constructor por defecto. Crea un DTO de arrendatario sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ArrendatarioDTO con todos sus atributos en null
	 * o valores por defecto. <br>
	 */
	public ArrendatarioDTO() {
	}

	/**
	 * Constructor parcial. Crea un DTO de arrendatario con datos del contrato pero
	 * sin datos personales. <b>pre</b> El apartamento no debe ser null y debe estar
	 * registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto ArrendatarioDTO con los datos del contrato
	 * inicializados. Los datos personales heredados de Persona quedan en null. <br>
	 * 
	 * @param apartamento         Apartamento que ocupa el arrendatario. apartamento
	 *                            != null
	 * @param fechaInicioContrato Fecha de inicio del contrato. fechaInicioContrato
	 *                            != null
	 * @param fechaFinContrato    Fecha de fin del contrato. Puede ser null si es
	 *                            indefinido
	 * @param activo              true si el contrato esta vigente, false si ya no
	 *                            lo esta
	 */
	public ArrendatarioDTO(Apartamento apartamento, LocalDate fechaInicioContrato, LocalDate fechaFinContrato,
			boolean activo) {
		super();
		this.apartamento = apartamento;
		this.fechaInicioContrato = fechaInicioContrato;
		this.fechaFinContrato = fechaFinContrato;
		this.activo = activo;
	}

	/**
	 * Constructor completo. Crea un DTO de arrendatario con todos sus datos
	 * personales y del contrato. <b>pre</b> Los parametros id, nombre, cedula y
	 * correo no deben ser null ni vacios. El apartamento debe estar registrado en
	 * el sistema. <br>
	 * <b>post</b> Se crea un objeto ArrendatarioDTO completamente inicializado y
	 * listo para ser transferido entre capas del sistema. <br>
	 * 
	 * @param id                  Identificador unico del arrendatario. id != null,
	 *                            id != ""
	 * @param nombre              Nombre completo del arrendatario. nombre != null,
	 *                            nombre != ""
	 * @param cedula              Numero de cedula del arrendatario. cedula != null,
	 *                            cedula != ""
	 * @param telefono            Numero de telefono de contacto. telefono != null
	 * @param correo              Correo electronico del arrendatario. correo !=
	 *                            null, correo != ""
	 * @param contactoEmergencia  Nombre del contacto de emergencia.
	 *                            contactoEmergencia != null
	 * @param telefonoEmergencia  Telefono del contacto de emergencia.
	 *                            telefonoEmergencia != null
	 * @param apartamento         Apartamento que ocupa el arrendatario. apartamento
	 *                            != null
	 * @param fechaInicioContrato Fecha de inicio del contrato. fechaInicioContrato
	 *                            != null
	 * @param fechaFinContrato    Fecha de fin del contrato. Puede ser null si es
	 *                            indefinido
	 * @param activo              true si el contrato esta vigente, false si ya no
	 *                            lo esta
	 */
	public ArrendatarioDTO(String id, String nombre, String cedula, String telefono, String correo,
			String contactoEmergencia, String telefonoEmergencia, Apartamento apartamento,
			LocalDate fechaInicioContrato, LocalDate fechaFinContrato, boolean activo) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
		this.apartamento = apartamento;
		this.fechaInicioContrato = fechaInicioContrato;
		this.fechaFinContrato = fechaFinContrato;
		this.activo = activo;
	}

	/**
	 * Constructor con datos personales unicamente, sin datos de contrato. Util para
	 * construir el DTO antes de asignarle apartamento y contrato. <b>pre</b> Los
	 * parametros id, nombre, cedula y correo no deben ser null ni vacios. <br>
	 * <b>post</b> Se crea un objeto ArrendatarioDTO con datos personales
	 * inicializados. Los atributos de contrato quedan en null y activo en false por
	 * defecto. <br>
	 * 
	 * @param id                 Identificador unico del arrendatario. id != null,
	 *                           id != ""
	 * @param nombre             Nombre completo del arrendatario. nombre != null,
	 *                           nombre != ""
	 * @param cedula             Numero de cedula del arrendatario. cedula != null,
	 *                           cedula != ""
	 * @param telefono           Numero de telefono de contacto. telefono != null
	 * @param correo             Correo electronico del arrendatario. correo !=
	 *                           null, correo != ""
	 * @param contactoEmergencia Nombre del contacto de emergencia.
	 *                           contactoEmergencia != null
	 * @param telefonoEmergencia Telefono del contacto de emergencia.
	 *                           telefonoEmergencia != null
	 */
	public ArrendatarioDTO(String id, String nombre, String cedula, String telefono, String correo,
			String contactoEmergencia, String telefonoEmergencia) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
	}

	/**
	 * Retorna el apartamento que ocupa el arrendatario. <b>pre</b> El objeto
	 * ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento asociado al arrendatario, o null si no tiene
	 *         apartamento asignado
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento que ocupa el arrendatario. <b>pre</b> El objeto
	 * ArrendatarioDTO debe estar instanciado. El apartamento debe estar registrado
	 * en el sistema. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param apartamento Apartamento a asignar. Puede ser null para desasociar el
	 *                    apartamento actual
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna la fecha de inicio del contrato de arrendamiento. <b>pre</b> El
	 * objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return LocalDate con la fecha de inicio del contrato, o null si no ha sido
	 *         asignada
	 */
	public LocalDate getFechaInicioContrato() {
		return fechaInicioContrato;
	}

	/**
	 * Asigna la fecha de inicio del contrato de arrendamiento. <b>pre</b> El objeto
	 * ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaInicioContrato queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaInicioContrato Nueva fecha de inicio del contrato.
	 *                            fechaInicioContrato != null
	 */
	public void setFechaInicioContrato(LocalDate fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}

	/**
	 * Retorna la fecha de fin o vencimiento del contrato de arrendamiento.
	 * <b>pre</b> El objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return LocalDate con la fecha de fin del contrato, o null si el contrato es
	 *         indefinido
	 */
	public LocalDate getFechaFinContrato() {
		return fechaFinContrato;
	}

	/**
	 * Asigna la fecha de fin o vencimiento del contrato de arrendamiento.
	 * <b>pre</b> El objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaFinContrato queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaFinContrato Nueva fecha de fin del contrato. Puede ser null si el
	 *                         contrato es indefinido
	 */
	public void setFechaFinContrato(LocalDate fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}

	/**
	 * Indica si el arrendatario tiene un contrato de arrendamiento activo y
	 * vigente. <b>pre</b> El objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return true si el contrato esta activo, false en caso contrario
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Asigna el estado de actividad del contrato del arrendatario. <b>pre</b> El
	 * objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo activo queda actualizado con el valor recibido. <br>
	 * 
	 * @param activo true para marcar el contrato como activo, false para marcarlo
	 *               como inactivo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Retorna una representacion en texto del DTO con los datos del arrendatario.
	 * <b>pre</b> El objeto ArrendatarioDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con los datos personales heredados de Persona, las fechas de
	 *         contrato y el estado activo
	 */
	@Override
	public String toString() {
		return "Arrendatario [" + super.toString() + ", fechaInicioContrato=" + fechaInicioContrato
				+ ", fechaFinContrato=" + fechaFinContrato + ", activo=" + activo + "]";
	}
}