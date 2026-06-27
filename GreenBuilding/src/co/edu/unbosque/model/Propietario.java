package co.edu.unbosque.model;

/**
 * Representa al propietario de un apartamento dentro del conjunto residencial.
 * Extiende la clase Persona e implementa las interfaces Notificable y
 * Reportable, lo que permite enviarle notificaciones por correo y generar
 * resumenes de su informacion para reportes administrativos. <br>
 * <b>pre</b> El propietario debe tener un id, nombre y cedula validos. <br>
 * <b>post</b> El propietario queda disponible para ser asociado a un
 * apartamento y gestionado dentro del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Propietario extends Persona implements Notificable, Reportable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -2411129815071384631L;

	/** Direccion de correspondencia fisica del propietario. */
	private String direccionCorrespondencia;

	/**
	 * Indica si el propietario reside actualmente en el apartamento que posee.
	 */
	private boolean esResidente;

	/**
	 * Indica si el propietario es el responsable del pago de la cuota de
	 * administracion.
	 */
	private boolean responsablePago;

	/**
	 * Indica si el propietario hace parte del consejo de administracion del
	 * conjunto.
	 */
	private boolean esConsejo;

	/**
	 * Constructor por defecto. Crea un propietario sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Propietario con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Propietario() {
	}

	/**
	 * Constructor parcial. Crea un propietario con sus atributos propios
	 * inicializados, sin datos de Persona. <br>
	 * <b>pre</b> No hay precondiciones especiales. <br>
	 * <b>post</b> Se crea un objeto Propietario con los atributos propios
	 * inicializados y los heredados en null. <br>
	 *
	 * @param direccionCorrespondencia Direccion fisica de correspondencia. Puede
	 *                                 ser null
	 * @param esResidente              true si el propietario reside en el
	 *                                 apartamento
	 * @param responsablePago          true si el propietario es responsable del
	 *                                 pago
	 * @param esConsejo                true si el propietario pertenece al consejo
	 */
	public Propietario(String direccionCorrespondencia, boolean esResidente, boolean responsablePago,
			boolean esConsejo) {
		super();
		this.direccionCorrespondencia = direccionCorrespondencia;
		this.esResidente = esResidente;
		this.responsablePago = responsablePago;
		this.esConsejo = esConsejo;
	}

	/**
	 * Constructor completo. Crea un propietario con todos sus atributos
	 * inicializados, incluyendo los heredados de Persona. <br>
	 * <b>pre</b> Los parametros id, nombre y cedula no deben ser null ni vacios.
	 * <br>
	 * <b>post</b> Se crea un objeto Propietario completamente inicializado y listo
	 * para ser registrado en el sistema. <br>
	 *
	 * @param id                       Identificador unico. id != null, id != ""
	 * @param nombre                   Nombre completo. nombre != null, nombre != ""
	 * @param cedula                   Numero de cedula. cedula != null, cedula !=
	 *                                 ""
	 * @param telefono                 Telefono de contacto. Puede ser null
	 * @param correo                   Correo electronico. Puede ser null
	 * @param contactoEmergencia       Nombre del contacto de emergencia. Puede ser
	 *                                 null
	 * @param telefonoEmergencia       Telefono del contacto de emergencia. Puede
	 *                                 ser null
	 * @param direccionCorrespondencia Direccion fisica de correspondencia. Puede
	 *                                 ser null
	 * @param esResidente              true si reside actualmente en el apartamento
	 * @param responsablePago          true si es responsable del pago de
	 *                                 administracion
	 * @param esConsejo                true si pertenece al consejo de
	 *                                 administracion
	 */
	public Propietario(String id, String nombre, String cedula, String telefono, String correo,
			String contactoEmergencia, String telefonoEmergencia, String direccionCorrespondencia, boolean esResidente,
			boolean responsablePago, boolean esConsejo) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
		this.direccionCorrespondencia = direccionCorrespondencia;
		this.esResidente = esResidente;
		this.responsablePago = responsablePago;
		this.esConsejo = esConsejo;
	}

	/**
	 * Constructor con datos basicos de persona. Crea un propietario con los datos
	 * heredados de Persona sin los atributos propios del propietario. <br>
	 * <b>pre</b> Los parametros id, nombre y cedula no deben ser null ni vacios.
	 * <br>
	 * <b>post</b> Se crea un objeto Propietario con los atributos de Persona
	 * inicializados y los propios en false o null. <br>
	 *
	 * @param id                 Identificador unico. id != null, id != ""
	 * @param nombre             Nombre completo. nombre != null, nombre != ""
	 * @param cedula             Numero de cedula. cedula != null, cedula != ""
	 * @param telefono           Telefono de contacto. Puede ser null
	 * @param correo             Correo electronico. Puede ser null
	 * @param contactoEmergencia Nombre del contacto de emergencia. Puede ser null
	 * @param telefonoEmergencia Telefono del contacto de emergencia. Puede ser null
	 */
	public Propietario(String id, String nombre, String cedula, String telefono, String correo,
			String contactoEmergencia, String telefonoEmergencia) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
	}

	/**
	 * Retorna la direccion de correspondencia del propietario. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la direccion de correspondencia, o null si no tiene
	 */
	public String getDireccionCorrespondencia() {
		return direccionCorrespondencia;
	}

	/**
	 * Asigna la direccion de correspondencia del propietario. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> El atributo direccionCorrespondencia queda actualizado. <br>
	 *
	 * @param direccionCorrespondencia Nueva direccion de correspondencia. Puede ser
	 *                                 null
	 */
	public void setDireccionCorrespondencia(String direccionCorrespondencia) {
		this.direccionCorrespondencia = direccionCorrespondencia;
	}

	/**
	 * Indica si el propietario reside actualmente en su apartamento. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si el propietario reside en el apartamento; false en caso
	 *         contrario
	 */
	public boolean isEsResidente() {
		return esResidente;
	}

	/**
	 * Asigna si el propietario reside actualmente en su apartamento. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> El atributo esResidente queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param esResidente true si reside en el apartamento; false en caso contrario
	 */
	public void setEsResidente(boolean esResidente) {
		this.esResidente = esResidente;
	}

	/**
	 * Indica si el propietario es el responsable del pago de administracion. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si es responsable del pago; false en caso contrario
	 */
	public boolean isResponsablePago() {
		return responsablePago;
	}

	/**
	 * Asigna si el propietario es responsable del pago de administracion. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> El atributo responsablePago queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param responsablePago true si es responsable del pago; false en caso
	 *                        contrario
	 */
	public void setResponsablePago(boolean responsablePago) {
		this.responsablePago = responsablePago;
	}

	/**
	 * Indica si el propietario pertenece al consejo de administracion. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si pertenece al consejo; false en caso contrario
	 */
	public boolean isEsConsejo() {
		return esConsejo;
	}

	/**
	 * Asigna si el propietario pertenece al consejo de administracion. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> El atributo esConsejo queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param esConsejo true si pertenece al consejo; false en caso contrario
	 */
	public void setEsConsejo(boolean esConsejo) {
		this.esConsejo = esConsejo;
	}

	/**
	 * Retorna una representacion en texto del propietario con sus datos
	 * principales. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con los datos de Persona y los atributos propios del
	 *         propietario
	 */
	@Override
	public String toString() {
		return "Propietario [" + super.toString() + ", direccionCorrespondencia=" + direccionCorrespondencia
				+ ", esResidente=" + esResidente + ", responsablePago=" + responsablePago + ", esConsejo=" + esConsejo
				+ "]";
	}

	/**
	 * Retorna el correo electronico del propietario como destinatario de
	 * notificaciones. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado y tener correo
	 * registrado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el correo electronico del propietario
	 */
	@Override
	public String getDestinatario() {
		return getCorreo();
	}

	/**
	 * Retorna el nombre del propietario para usarlo en el encabezado de
	 * notificaciones. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre del propietario
	 */
	@Override
	public String getNombreNotificacion() {
		return getNombre();
	}

	/**
	 * Genera un resumen en texto con los datos relevantes del propietario para
	 * incluir en reportes administrativos. <br>
	 * <b>pre</b> El objeto Propietario debe estar instanciado con datos cargados.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre, cedula, correo, estado de residencia,
	 *         responsabilidad de pago y direccion del propietario
	 */
	@Override
	public String generarResumen() {
		return "Propietario: " + getNombre() + " | Cedula: " + getCedula() + " | Correo: " + getCorreo()
				+ " | Residente: " + esResidente + " | Responsable de pago: " + responsablePago + " | Direccion: "
				+ direccionCorrespondencia;
	}
}