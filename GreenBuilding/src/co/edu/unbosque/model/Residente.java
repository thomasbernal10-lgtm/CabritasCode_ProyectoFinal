package co.edu.unbosque.model;

/**
 * Representa un residente dentro del sistema GreenBuilding Manager. Esta clase
 * hereda de Persona e implementa la interfaz Notificable, permitiendo gestionar
 * informacion relacionada con los habitantes del conjunto residencial y el
 * envio de notificaciones.
 * 
 * Un residente puede ser propietario, arrendatario, familiar o empleado
 * domestico asociado a un apartamento dentro del conjunto residencial.
 * <b>pre</b> El apartamento asociado debe existir previamente en el sistema.
 * Los datos personales del residente deben ser validos. <br>
 * <b>post</b> El residente queda disponible para procesos de administracion,
 * control y notificaciones dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Residente extends Persona implements Notificable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 363487462687157887L;

	/** Tipo de residente propietario. */
	private final String TIPO_PROPIETARIO = "PROPIETARIO";

	/** Tipo de residente arrendatario. */
	private final String TIPO_ARRENDATARIO = "ARRENDATARIO";

	/** Tipo de residente familiar. */
	private final String TIPO_FAMILIAR = "FAMILIAR";

	/** Tipo de residente empleado domestico. */
	private final String TIPO_EMPLEADO = "EMPLEADO_DOMESTICO";

	/** Apartamento asociado al residente. */
	private Apartamento apartamento;

	/** Tipo de residente. */
	private String tipo;

	/** Estado de actividad del residente. */
	private boolean activo;

	/**
	 * Constructor por defecto. Crea un objeto Residente sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Residente con todos sus atributos en null,
	 * false o valores por defecto. <br>
	 */
	public Residente() {
	}

	/**
	 * Constructor completo. Crea un objeto Residente con todos sus atributos
	 * inicializados. <b>pre</b> El id, nombre, cedula, telefono, correo,
	 * contactoEmergencia, telefonoEmergencia, apartamento y tipo no deben ser null.
	 * <br>
	 * <b>post</b> Se crea un objeto Residente completamente inicializado y listo
	 * para ser utilizado dentro del sistema. <br>
	 * 
	 * @param id                 Identificador unico del residente. id != null, id
	 *                           != ""
	 * @param nombre             Nombre completo del residente. nombre != null,
	 *                           nombre != ""
	 * @param cedula             Cedula del residente. cedula != null, cedula != ""
	 * @param telefono           Numero telefonico del residente. telefono != null,
	 *                           telefono != ""
	 * @param correo             Correo electronico del residente. correo != null,
	 *                           correo != ""
	 * @param contactoEmergencia Nombre del contacto de emergencia.
	 *                           contactoEmergencia != null, contactoEmergencia !=
	 *                           ""
	 * @param telefonoEmergencia Telefono del contacto de emergencia.
	 *                           telefonoEmergencia != null, telefonoEmergencia !=
	 *                           ""
	 * @param apartamento        Apartamento asociado al residente. apartamento !=
	 *                           null
	 * @param tipo               Tipo de residente. tipo != null, tipo != ""
	 * @param activo             Estado de actividad del residente
	 */
	public Residente(String id, String nombre, String cedula, String telefono, String correo, String contactoEmergencia,
			String telefonoEmergencia, Apartamento apartamento, String tipo, boolean activo) {
		super(id, nombre, cedula, telefono, correo, contactoEmergencia, telefonoEmergencia);
		this.apartamento = apartamento;
		this.tipo = tipo;
		this.activo = activo;
	}

	/**
	 * Retorna el tipo propietario. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo propietario
	 */
	public String getTIPO_PROPIETARIO() {
		return TIPO_PROPIETARIO;
	}

	/**
	 * Retorna el tipo arrendatario. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo arrendatario
	 */
	public String getTIPO_ARRENDATARIO() {
		return TIPO_ARRENDATARIO;
	}

	/**
	 * Retorna el tipo familiar. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo familiar
	 */
	public String getTIPO_FAMILIAR() {
		return TIPO_FAMILIAR;
	}

	/**
	 * Retorna el tipo empleado domestico. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo empleado domestico
	 */
	public String getTIPO_EMPLEADO() {
		return TIPO_EMPLEADO;
	}

	/**
	 * Retorna el apartamento asociado al residente. <b>pre</b> El objeto Residente
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento asociado al residente
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado al residente. <b>pre</b> El objeto Residente
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
	 * Retorna el tipo de residente. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo de residente
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de residente. <b>pre</b> El objeto Residente debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 * 
	 * @param tipo Nuevo tipo de residente. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el estado de actividad del residente. <b>pre</b> El objeto Residente
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return true si el residente esta activo, false en caso contrario
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Asigna el estado de actividad del residente. <b>pre</b> El objeto Residente
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo activo queda actualizado con el valor recibido. <br>
	 * 
	 * @param activo Nuevo estado de actividad del residente
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Retorna una representacion en texto del residente con sus datos principales.
	 * <b>pre</b> El objeto Residente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la informacion principal del residente
	 */
	@Override
	public String toString() {
		return super.toString() + " Residente [tipo=" + tipo + ", activo=" + activo + ", apartamento=" + apartamento
				+ "]";
	}

	/**
	 * Retorna el destinatario de la notificacion correspondiente al residente.
	 * <b>pre</b> El objeto Residente debe estar instanciado y el correo debe
	 * existir. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el correo electronico del residente
	 */
	@Override
	public String getDestinatario() {
		return getCorreo();
	}

	/**
	 * Retorna el nombre asociado a la notificacion del residente. <b>pre</b> El
	 * objeto Residente debe estar instanciado y el nombre debe existir. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre del residente
	 */
	@Override
	public String getNombreNotificacion() {
		return getNombre();
	}

}