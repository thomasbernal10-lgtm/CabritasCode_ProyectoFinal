package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Representa un objeto de transferencia de datos (DTO) para las zonas comunes
 * dentro del sistema GreenBuilding Manager. Esta clase permite transportar
 * informacion relacionada con espacios compartidos del conjunto residencial
 * entre las diferentes capas de la aplicacion sin exponer directamente la
 * logica de negocio.
 * 
 * Una zona comun permite administrar informacion relacionada con horarios,
 * capacidad, disponibilidad, costos de reserva y tipos de espacios compartidos.
 * <b>pre</b> El conjunto asociado debe existir previamente en el sistema. Los
 * horarios de apertura y cierre deben corresponder a valores validos. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * consulta y administracion de zonas comunes dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ZonaComunDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3497784056615061373L;

	/** Tipo de zona comun salon. */
	private final String TIPO_SALON = "SALON";

	/** Tipo de zona comun gimnasio. */
	private final String TIPO_GIMNASIO = "GIMNASIO";

	/** Tipo de zona comun terraza. */
	private final String TIPO_TERRAZA = "TERRAZA";

	/** Tipo de zona comun cancha. */
	private final String TIPO_CANCHA = "CANCHA";

	/** Tipo de zona comun coworking. */
	private final String TIPO_COWORKING = "COWORKING";

	/** Tipo de zona comun infantil. */
	private final String TIPO_ZONA_INFANTIL = "ZONA_INFANTIL";

	/** Tipo de zona comun mascotas. */
	private final String TIPO_ZONA_MASCOTAS = "ZONA_MASCOTAS";

	/** Tipo de zona comun bicicletro. */
	private final String TIPO_BICICLETERO = "BICICLETERO";

	/** Tipo de zona comun deposito. */
	private final String TIPO_DEPOSITO = "DEPOSITO";

	/** Tipo de zona comun cuarto de reciclaje. */
	private final String TIPO_CUARTO_RECICLAJE = "CUARTO_RECICLAJE";

	/** Estado disponible de la zona comun. */
	private final String ESTADO_DISPONIBLE = "DISPONIBLE";

	/** Estado mantenimiento de la zona comun. */
	private final String ESTADO_MANTENIMIENTO = "MANTENIMIENTO";

	/** Estado bloqueada de la zona comun. */
	private final String ESTADO_BLOQUEADA = "BLOQUEADA";

	/** Identificador unico de la zona comun. */
	private String id;

	/** Nombre de la zona comun. */
	private String nombre;

	/** Tipo de la zona comun. */
	private String tipo;

	/** Estado actual de la zona comun. */
	private String estado;

	/** Conjunto al que pertenece la zona comun. */
	private Conjunto conjunto;

	/** Capacidad maxima permitida en la zona comun. */
	private int aforoMaximo;

	/** Costo asociado a la reserva de la zona comun. */
	private double costoReserva;

	/** Hora de apertura de la zona comun. */
	private LocalTime horaApertura;

	/** Hora de cierre de la zona comun. */
	private LocalTime horaCierre;

	/** Indica si la zona comun requiere reserva previa. */
	private boolean requiereReserva;

	/**
	 * Constructor por defecto. Crea un objeto ZonaComunDTO sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ZonaComunDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public ZonaComunDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto ZonaComunDTO con todos sus atributos
	 * inicializados. <b>pre</b> El id, nombre, tipo, estado y conjunto no deben ser
	 * null. <br>
	 * <b>post</b> Se crea un objeto ZonaComunDTO completamente inicializado y listo
	 * para ser utilizado dentro del sistema. <br>
	 * 
	 * @param id              Identificador unico de la zona comun. id != null, id
	 *                        != ""
	 * @param nombre          Nombre de la zona comun. nombre != null, nombre != ""
	 * @param tipo            Tipo de zona comun. tipo != null, tipo != ""
	 * @param estado          Estado actual de la zona comun. estado != null, estado
	 *                        != ""
	 * @param conjunto        Conjunto al que pertenece la zona comun. conjunto !=
	 *                        null
	 * @param aforoMaximo     Capacidad maxima permitida
	 * @param costoReserva    Costo asociado a la reserva
	 * @param horaApertura    Hora de apertura de la zona comun. horaApertura !=
	 *                        null
	 * @param horaCierre      Hora de cierre de la zona comun. horaCierre != null
	 * @param requiereReserva Indica si requiere reserva previa
	 */
	public ZonaComunDTO(String id, String nombre, String tipo, String estado, Conjunto conjunto, int aforoMaximo,
			double costoReserva, LocalTime horaApertura, LocalTime horaCierre, boolean requiereReserva) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
		this.conjunto = conjunto;
		this.aforoMaximo = aforoMaximo;
		this.costoReserva = costoReserva;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.requiereReserva = requiereReserva;
	}

	/**
	 * Retorna el tipo salon de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo salon
	 */
	public String getTIPO_SALON() {
		return TIPO_SALON;
	}

	/**
	 * Retorna el tipo gimnasio de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo gimnasio
	 */
	public String getTIPO_GIMNASIO() {
		return TIPO_GIMNASIO;
	}

	/**
	 * Retorna el tipo terraza de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo terraza
	 */
	public String getTIPO_TERRAZA() {
		return TIPO_TERRAZA;
	}

	/**
	 * Retorna el tipo cancha de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo cancha
	 */
	public String getTIPO_CANCHA() {
		return TIPO_CANCHA;
	}

	/**
	 * Retorna el tipo coworking de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo coworking
	 */
	public String getTIPO_COWORKING() {
		return TIPO_COWORKING;
	}

	/**
	 * Retorna el tipo zona infantil de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo zona infantil
	 */
	public String getTIPO_ZONA_INFANTIL() {
		return TIPO_ZONA_INFANTIL;
	}

	/**
	 * Retorna el tipo zona mascotas de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo zona mascotas
	 */
	public String getTIPO_ZONA_MASCOTAS() {
		return TIPO_ZONA_MASCOTAS;
	}

	/**
	 * Retorna el tipo bicicletro de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo bicicletro
	 */
	public String getTIPO_BICICLETERO() {
		return TIPO_BICICLETERO;
	}

	/**
	 * Retorna el tipo deposito de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo deposito
	 */
	public String getTIPO_DEPOSITO() {
		return TIPO_DEPOSITO;
	}

	/**
	 * Retorna el tipo cuarto reciclaje de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo cuarto reciclaje
	 */
	public String getTIPO_CUARTO_RECICLAJE() {
		return TIPO_CUARTO_RECICLAJE;
	}

	/**
	 * Retorna el estado disponible de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado disponible
	 */
	public String getESTADO_DISPONIBLE() {
		return ESTADO_DISPONIBLE;
	}

	/**
	 * Retorna el estado mantenimiento de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado mantenimiento
	 */
	public String getESTADO_MANTENIMIENTO() {
		return ESTADO_MANTENIMIENTO;
	}

	/**
	 * Retorna el estado bloqueada de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado bloqueada
	 */
	public String getESTADO_BLOQUEADA() {
		return ESTADO_BLOQUEADA;
	}

	/**
	 * Retorna el identificador unico de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id de la zona comun
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador de la zona comun. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre de la zona comun. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre de la zona comun
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre de la zona comun. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo nombre queda actualizado con el valor recibido. <br>
	 * 
	 * @param nombre Nuevo nombre de la zona comun. nombre != null, nombre != ""
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el tipo de la zona comun. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo de la zona comun
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la zona comun. <b>pre</b> El objeto ZonaComunDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 * 
	 * @param tipo Nuevo tipo de la zona comun. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el estado actual de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado de la zona comun
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado de la zona comun. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado de la zona comun. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el conjunto asociado a la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Conjunto asociado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto asociado a la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param conjunto Nuevo conjunto asociado. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el aforo maximo permitido. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return int con el aforo maximo
	 */
	public int getAforoMaximo() {
		return aforoMaximo;
	}

	/**
	 * Asigna el aforo maximo permitido. <b>pre</b> El objeto ZonaComunDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo aforoMaximo queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param aforoMaximo Nuevo aforo maximo
	 */
	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}

	/**
	 * Retorna el costo de reserva de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con el costo de reserva
	 */
	public double getCostoReserva() {
		return costoReserva;
	}

	/**
	 * Asigna el costo de reserva de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo costoReserva queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param costoReserva Nuevo costo de reserva
	 */
	public void setCostoReserva(double costoReserva) {
		this.costoReserva = costoReserva;
	}

	/**
	 * Retorna la hora de apertura de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalTime con la hora de apertura
	 */
	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	/**
	 * Asigna la hora de apertura de la zona comun. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo horaApertura queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param horaApertura Nueva hora de apertura. horaApertura != null
	 */
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * Retorna la hora de cierre de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalTime con la hora de cierre
	 */
	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	/**
	 * Asigna la hora de cierre de la zona comun. <b>pre</b> El objeto ZonaComunDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo horaCierre queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param horaCierre Nueva hora de cierre. horaCierre != null
	 */
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}

	/**
	 * Verifica si la zona comun requiere reserva previa. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return true si requiere reserva, false en caso contrario
	 */
	public boolean isRequiereReserva() {
		return requiereReserva;
	}

	/**
	 * Asigna si la zona comun requiere reserva previa. <b>pre</b> El objeto
	 * ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo requiereReserva queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param requiereReserva Nuevo estado de reserva
	 */
	public void setRequiereReserva(boolean requiereReserva) {
		this.requiereReserva = requiereReserva;
	}

	/**
	 * Retorna una representacion en texto de la zona comun con sus datos
	 * principales. <b>pre</b> El objeto ZonaComunDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con los datos principales de la zona comun
	 */
	@Override
	public String toString() {
		return "ZonaComun [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + ", aforoMaximo="
				+ aforoMaximo + ", costoReserva=" + costoReserva + "]";
	}
}