package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Representa a una persona que habita un apartamento del conjunto residencial
 * bajo un contrato de arrendamiento. El arrendatario extiende de Persona e
 * implementa las interfaces Notificable y Sancionable, lo que le permite
 * recibir notificaciones del sistema y ser sujeto de sanciones por
 * incumplimiento de normas del conjunto. <b>pre</b> El apartamento asociado
 * debe estar registrado en el sistema y su estado debe ser compatible con el
 * arrendamiento. <br>
 * <b>post</b> El arrendatario queda disponible para realizar reservas,
 * registrar visitantes, recibir notificaciones y ser vinculado a obligaciones e
 * incidentes dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Arrendatario extends Persona implements Notificable, Sancionable {

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
	 * Constructor por defecto. Crea un arrendatario sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Arrendatario con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Arrendatario() {
	}

	/**
	 * Constructor parcial. Crea un arrendatario con datos del contrato pero sin
	 * datos personales. <b>pre</b> El apartamento no debe ser null y debe estar
	 * registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto Arrendatario con los datos del contrato
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
	public Arrendatario(Apartamento apartamento, LocalDate fechaInicioContrato, LocalDate fechaFinContrato,
			boolean activo) {
		super();
		this.apartamento = apartamento;
		this.fechaInicioContrato = fechaInicioContrato;
		this.fechaFinContrato = fechaFinContrato;
		this.activo = activo;
	}

	/**
	 * Constructor completo. Crea un arrendatario con todos sus datos personales y
	 * del contrato. <b>pre</b> Los parametros id, nombre, cedula y correo no deben
	 * ser null ni vacios. El apartamento debe estar registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto Arrendatario completamente inicializado y listo
	 * para ser registrado y operar dentro del sistema. <br>
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
	public Arrendatario(String id, String nombre, String cedula, String telefono, String correo,
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
	 * registrar un arrendatario antes de asignarle apartamento y contrato.
	 * <b>pre</b> Los parametros id, nombre, cedula y correo no deben ser null ni
	 * vacios. <br>
	 * <b>post</b> Se crea un objeto Arrendatario con datos personales
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
	public Arrendatario(String id, String nombre, String cedula, String telefono, String correo,
			String contactoEmergencia, String telefonoEmergencia) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
	}

	/**
	 * Retorna el apartamento que ocupa el arrendatario. <b>pre</b> El objeto
	 * Arrendatario debe estar instanciado. <br>
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
	 * Arrendatario debe estar instanciado. El apartamento debe estar registrado en
	 * el sistema. <br>
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
	 * objeto Arrendatario debe estar instanciado. <br>
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
	 * Arrendatario debe estar instanciado. <br>
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
	 * <b>pre</b> El objeto Arrendatario debe estar instanciado. <br>
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
	 * <b>pre</b> El objeto Arrendatario debe estar instanciado. <br>
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
	 * vigente. <b>pre</b> El objeto Arrendatario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return true si el contrato esta activo, false en caso contrario
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Asigna el estado de actividad del contrato del arrendatario. <b>pre</b> El
	 * objeto Arrendatario debe estar instanciado. <br>
	 * <b>post</b> El atributo activo queda actualizado con el valor recibido. <br>
	 * 
	 * @param activo true para marcar el contrato como activo, false para marcarlo
	 *               como inactivo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Retorna una representacion en texto del arrendatario con sus datos personales
	 * y de contrato. <b>pre</b> El objeto Arrendatario debe estar instanciado. <br>
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

	/**
	 * Retorna el correo electronico del arrendatario como destinatario de
	 * notificaciones. Implementacion del metodo definido en la interfaz
	 * Notificable. <b>pre</b> El objeto Arrendatario debe estar instanciado y tener
	 * un correo asignado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el correo electronico del arrendatario
	 */
	@Override
	public String getDestinatario() {
		return getCorreo();
	}

	/**
	 * Retorna el nombre del arrendatario para ser usado en notificaciones del
	 * sistema. Implementacion del metodo definido en la interfaz Notificable.
	 * <b>pre</b> El objeto Arrendatario debe estar instanciado y tener un nombre
	 * asignado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre completo del arrendatario
	 */
	@Override
	public String getNombreNotificacion() {
		return getNombre();
	}

	/**
	 * Determina si el arrendatario tiene sanciones activas por contrato vencido. Se
	 * considera sancionado si tiene fecha de fin de contrato definida, dicha fecha
	 * ya paso y aun figura como activo en el sistema. Implementacion del metodo
	 * definido en la interfaz Sancionable. <b>pre</b> El objeto Arrendatario debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return true si el contrato esta vencido y el arrendatario sigue activo,
	 *         false en caso contrario
	 */
	@Override
	public boolean tieneSancionesActivas() {
		return fechaFinContrato != null && LocalDate.now().isAfter(fechaFinContrato) && activo;
	}

	/**
	 * Retorna el identificador unico del arrendatario como responsable en procesos
	 * de sancion. Implementacion del metodo definido en la interfaz Sancionable.
	 * <b>pre</b> El objeto Arrendatario debe estar instanciado y tener un id
	 * asignado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del arrendatario
	 */
	@Override
	public String getIdResponsable() {
		return getId();
	}
}