package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Representa una zona comun registrada dentro del sistema GreenBuilding
 * Manager. Esta clase permite gestionar la informacion relacionada con los
 * espacios compartidos disponibles en el conjunto residencial.
 * 
 * Una zona comun puede corresponder a espacios como salon, gimnasio, terraza,
 * cancha, coworking, entre otros.
 * 
 * <b>pre</b> Los datos principales de la zona comun deben ser validos y el
 * conjunto asociado debe existir previamente en el sistema. <br>
 * <b>post</b> La zona comun queda registrada y disponible para procesos de
 * administracion y reservas dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ZonaComun implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3497784056615061373L;

	/** Tipo salon. */
	private final String TIPO_SALON = "SALON";

	/** Tipo gimnasio. */
	private final String TIPO_GIMNASIO = "GIMNASIO";

	/** Tipo terraza. */
	private final String TIPO_TERRAZA = "TERRAZA";

	/** Tipo cancha. */
	private final String TIPO_CANCHA = "CANCHA";

	/** Tipo coworking. */
	private final String TIPO_COWORKING = "COWORKING";

	/** Tipo zona infantil. */
	private final String TIPO_ZONA_INFANTIL = "ZONA_INFANTIL";

	/** Tipo zona mascotas. */
	private final String TIPO_ZONA_MASCOTAS = "ZONA_MASCOTAS";

	/** Tipo biciclettero. */
	private final String TIPO_BICICLETERO = "BICICLETERO";

	/** Tipo deposito. */
	private final String TIPO_DEPOSITO = "DEPOSITO";

	/** Tipo cuarto reciclaje. */
	private final String TIPO_CUARTO_RECICLAJE = "CUARTO_RECICLAJE";

	/** Estado disponible. */
	private final String ESTADO_DISPONIBLE = "DISPONIBLE";

	/** Estado mantenimiento. */
	private final String ESTADO_MANTENIMIENTO = "MANTENIMIENTO";

	/** Estado bloqueada. */
	private final String ESTADO_BLOQUEADA = "BLOQUEADA";

	/** Identificador unico de la zona comun. */
	private String id;

	/** Nombre de la zona comun. */
	private String nombre;

	/** Tipo de zona comun. */
	private String tipo;

	/** Estado de la zona comun. */
	private String estado;

	/** Conjunto al que pertenece la zona comun. */
	private Conjunto conjunto;

	/** Aforo maximo permitido. */
	private int aforoMaximo;

	/** Costo de reserva de la zona comun. */
	private double costoReserva;

	/** Hora de apertura de la zona comun. */
	private LocalTime horaApertura;

	/** Hora de cierre de la zona comun. */
	private LocalTime horaCierre;

	/** Indica si la zona requiere reserva. */
	private boolean requiereReserva;

	/**
	 * Constructor por defecto. Crea un objeto ZonaComun sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ZonaComun con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public ZonaComun() {
	}

	/**
	 * Constructor completo. Crea un objeto ZonaComun con toda la informacion
	 * necesaria.
	 * 
	 * <b>pre</b> El id, nombre y tipo no deben ser null. <br>
	 * <b>post</b> Se crea un objeto ZonaComun inicializado con la informacion
	 * suministrada. <br>
	 * 
	 * @param id              Identificador unico de la zona comun.
	 * @param nombre          Nombre de la zona comun.
	 * @param tipo            Tipo de zona comun.
	 * @param estado          Estado de la zona comun.
	 * @param conjunto        Conjunto asociado.
	 * @param aforoMaximo     Aforo maximo permitido.
	 * @param costoReserva    Costo de reserva.
	 * @param horaApertura    Hora de apertura.
	 * @param horaCierre      Hora de cierre.
	 * @param requiereReserva Indica si requiere reserva.
	 */
	public ZonaComun(String id, String nombre, String tipo, String estado, Conjunto conjunto, int aforoMaximo,
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
	 * Retorna el tipo salon.
	 * 
	 * @return String con el tipo salon
	 */
	public String getTIPO_SALON() {
		return TIPO_SALON;
	}

	/**
	 * Retorna el tipo gimnasio.
	 * 
	 * @return String con el tipo gimnasio
	 */
	public String getTIPO_GIMNASIO() {
		return TIPO_GIMNASIO;
	}

	/**
	 * Retorna el tipo terraza.
	 * 
	 * @return String con el tipo terraza
	 */
	public String getTIPO_TERRAZA() {
		return TIPO_TERRAZA;
	}

	/**
	 * Retorna el tipo cancha.
	 * 
	 * @return String con el tipo cancha
	 */
	public String getTIPO_CANCHA() {
		return TIPO_CANCHA;
	}

	/**
	 * Retorna el tipo coworking.
	 * 
	 * @return String con el tipo coworking
	 */
	public String getTIPO_COWORKING() {
		return TIPO_COWORKING;
	}

	/**
	 * Retorna el tipo zona infantil.
	 * 
	 * @return String con el tipo zona infantil
	 */
	public String getTIPO_ZONA_INFANTIL() {
		return TIPO_ZONA_INFANTIL;
	}

	/**
	 * Retorna el tipo zona mascotas.
	 * 
	 * @return String con el tipo zona mascotas
	 */
	public String getTIPO_ZONA_MASCOTAS() {
		return TIPO_ZONA_MASCOTAS;
	}

	/**
	 * Retorna el tipo biciclettero.
	 * 
	 * @return String con el tipo biciclettero
	 */
	public String getTIPO_BICICLETERO() {
		return TIPO_BICICLETERO;
	}

	/**
	 * Retorna el tipo deposito.
	 * 
	 * @return String con el tipo deposito
	 */
	public String getTIPO_DEPOSITO() {
		return TIPO_DEPOSITO;
	}

	/**
	 * Retorna el tipo cuarto reciclaje.
	 * 
	 * @return String con el tipo cuarto reciclaje
	 */
	public String getTIPO_CUARTO_RECICLAJE() {
		return TIPO_CUARTO_RECICLAJE;
	}

	/**
	 * Retorna el estado disponible.
	 * 
	 * @return String con el estado disponible
	 */
	public String getESTADO_DISPONIBLE() {
		return ESTADO_DISPONIBLE;
	}

	/**
	 * Retorna el estado mantenimiento.
	 * 
	 * @return String con el estado mantenimiento
	 */
	public String getESTADO_MANTENIMIENTO() {
		return ESTADO_MANTENIMIENTO;
	}

	/**
	 * Retorna el estado bloqueada.
	 * 
	 * @return String con el estado bloqueada
	 */
	public String getESTADO_BLOQUEADA() {
		return ESTADO_BLOQUEADA;
	}

	/**
	 * Retorna el identificador de la zona comun.
	 * 
	 * @return Identificador de la zona comun
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador de la zona comun.
	 * 
	 * @param id Nuevo identificador
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre de la zona comun.
	 * 
	 * @return Nombre de la zona comun
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el nombre de la zona comun.
	 * 
	 * @param nombre Nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el tipo de la zona comun.
	 * 
	 * @return Tipo de la zona comun
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la zona comun.
	 * 
	 * @param tipo Nuevo tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el estado de la zona comun.
	 * 
	 * @return Estado de la zona comun
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado de la zona comun.
	 * 
	 * @param estado Nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el conjunto asociado.
	 * 
	 * @return Conjunto asociado
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto asociado.
	 * 
	 * @param conjunto Nuevo conjunto asociado
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el aforo maximo.
	 * 
	 * @return Aforo maximo permitido
	 */
	public int getAforoMaximo() {
		return aforoMaximo;
	}

	/**
	 * Asigna el aforo maximo.
	 * 
	 * @param aforoMaximo Nuevo aforo maximo
	 */
	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}

	/**
	 * Retorna el costo de reserva.
	 * 
	 * @return Costo de reserva
	 */
	public double getCostoReserva() {
		return costoReserva;
	}

	/**
	 * Asigna el costo de reserva.
	 * 
	 * @param costoReserva Nuevo costo de reserva
	 */
	public void setCostoReserva(double costoReserva) {
		this.costoReserva = costoReserva;
	}

	/**
	 * Retorna la hora de apertura.
	 * 
	 * @return Hora de apertura
	 */
	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	/**
	 * Asigna la hora de apertura.
	 * 
	 * @param horaApertura Nueva hora de apertura
	 */
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * Retorna la hora de cierre.
	 * 
	 * @return Hora de cierre
	 */
	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	/**
	 * Asigna la hora de cierre.
	 * 
	 * @param horaCierre Nueva hora de cierre
	 */
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}

	/**
	 * Retorna si la zona requiere reserva.
	 * 
	 * @return true si requiere reserva, false en caso contrario
	 */
	public boolean isRequiereReserva() {
		return requiereReserva;
	}

	/**
	 * Asigna si la zona requiere reserva.
	 * 
	 * @param requiereReserva Nuevo estado de reserva
	 */
	public void setRequiereReserva(boolean requiereReserva) {
		this.requiereReserva = requiereReserva;
	}

	/**
	 * Retorna una representacion en texto de la zona comun.
	 * 
	 * @return String con la informacion principal de la zona comun
	 */
	@Override
	public String toString() {
		return "ZonaComun [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + ", aforoMaximo="
				+ aforoMaximo + ", costoReserva=" + costoReserva + "]";
	}
}