package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa una obligacion economica asociada a un apartamento dentro del
 * conjunto residencial. Una obligacion puede corresponder a cuotas de
 * administracion, cuotas extraordinarias, multas, daños en zonas comunes,
 * parqueaderos adicionales o alquiler de zonas comunes. <b>pre</b> El
 * apartamento al que se asocia la obligacion debe estar registrado en el
 * sistema antes de crear el objeto. <br>
 * <b>post</b> La obligacion queda disponible para ser consultada, actualizada y
 * utilizada como base para el control de cartera y generacion de reportes
 * financieros del conjunto. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Obligacion implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 239822564433107614L;

	/**
	 * Constante que representa una obligacion por cuota mensual de administracion.
	 */
	private final String TIPO_ADMINISTRACION = "ADMINISTRACION";

	/**
	 * Constante que representa una obligacion por cuota extraordinaria aprobada por
	 * la asamblea.
	 */
	private final String TIPO_CUOTA_EXTRAORDINARIA = "CUOTA_EXTRAORDINARIA";

	/**
	 * Constante que representa una obligacion generada por una multa por
	 * incumplimiento de normas.
	 */
	private final String TIPO_MULTA = "MULTA";

	/**
	 * Constante que representa una obligacion generada por daños causados en zonas
	 * comunes.
	 */
	private final String TIPO_DANO = "DANO";

	/**
	 * Constante que representa una obligacion por uso o asignacion de parqueadero
	 * adicional.
	 */
	private final String TIPO_PARQUEADERO = "PARQUEADERO";

	/**
	 * Constante que representa una obligacion por alquiler de una zona comun del
	 * conjunto.
	 */
	private final String TIPO_ALQUILER_ZONA = "ALQUILER_ZONA";

	/**
	 * Constante que representa el estado de una obligacion aun no cancelada y
	 * dentro del plazo.
	 */
	private final String ESTADO_PENDIENTE = "PENDIENTE";

	/**
	 * Constante que representa el estado de una obligacion que ha sido cancelada
	 * exitosamente.
	 */
	private final String ESTADO_PAGADA = "PAGADA";

	/**
	 * Constante que representa el estado de una obligacion que supero su fecha
	 * limite sin ser pagada.
	 */
	private final String ESTADO_VENCIDA = "VENCIDA";

	/**
	 * Constante que representa el estado de una obligacion que fue anulada por la
	 * administracion.
	 */
	private final String ESTADO_ANULADA = "ANULADA";

	/** Identificador unico de la obligacion. */
	private String id;

	/** Apartamento del conjunto residencial al que pertenece esta obligacion. */
	private Apartamento apartamento;

	/**
	 * Tipo de obligacion (ADMINISTRACION, CUOTA_EXTRAORDINARIA, MULTA, DANO,
	 * PARQUEADERO, ALQUILER_ZONA).
	 */
	private String tipo;

	/** Valor monetario de la obligacion expresado en la moneda local. */
	private double monto;

	/** Fecha en que fue generada o registrada la obligacion en el sistema. */
	private LocalDate fechaGeneracion;

	/**
	 * Fecha maxima para realizar el pago de la obligacion antes de que quede
	 * vencida.
	 */
	private LocalDate fechaLimite;

	/** Estado actual de la obligacion (PENDIENTE, PAGADA, VENCIDA, ANULADA). */
	private String estado;

	/** Descripcion o detalle del concepto por el cual se genero la obligacion. */
	private String concepto;

	/** Nombre o identificador del usuario o proceso que genero la obligacion. */
	private String generadaPor;

	/**
	 * Constructor por defecto. Crea una obligacion sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Obligacion con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Obligacion() {
	}

	/**
	 * Constructor completo. Crea una obligacion con todos sus atributos
	 * inicializados. <b>pre</b> Los parametros id, tipo, estado y concepto no deben
	 * ser null ni vacios. El apartamento debe estar registrado en el sistema. El
	 * monto debe ser mayor a cero. <br>
	 * <b>post</b> Se crea un objeto Obligacion completamente inicializado y listo
	 * para ser registrado y gestionado en el sistema. <br>
	 *
	 * @param id              Identificador unico de la obligacion. id != null, id
	 *                        != ""
	 * @param apartamento     Apartamento al que pertenece la obligacion.
	 *                        apartamento != null
	 * @param tipo            Tipo de obligacion. tipo != null, tipo != ""
	 * @param monto           Valor monetario de la obligacion. monto > 0
	 * @param fechaGeneracion Fecha en que se genero la obligacion. fechaGeneracion
	 *                        != null
	 * @param fechaLimite     Fecha maxima de pago. fechaLimite != null, fechaLimite
	 *                        >= fechaGeneracion
	 * @param estado          Estado inicial de la obligacion. estado != null,
	 *                        estado != ""
	 * @param concepto        Descripcion del concepto de la obligacion. concepto !=
	 *                        null, concepto != ""
	 * @param generadaPor     Nombre o id de quien genero la obligacion. generadaPor
	 *                        != null, generadaPor != ""
	 */
	public Obligacion(String id, Apartamento apartamento, String tipo, double monto, LocalDate fechaGeneracion,
			LocalDate fechaLimite, String estado, String concepto, String generadaPor) {
		this.id = id;
		this.apartamento = apartamento;
		this.tipo = tipo;
		this.monto = monto;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaLimite = fechaLimite;
		this.estado = estado;
		this.concepto = concepto;
		this.generadaPor = generadaPor;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por administracion.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ADMINISTRACION"
	 */
	public String getTIPO_ADMINISTRACION() {
		return TIPO_ADMINISTRACION;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por cuota
	 * extraordinaria. <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "CUOTA_EXTRAORDINARIA"
	 */
	public String getTIPO_CUOTA_EXTRAORDINARIA() {
		return TIPO_CUOTA_EXTRAORDINARIA;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por multa.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MULTA"
	 */
	public String getTIPO_MULTA() {
		return TIPO_MULTA;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por daño en zona
	 * comun. <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "DANO"
	 */
	public String getTIPO_DANO() {
		return TIPO_DANO;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por parqueadero.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PARQUEADERO"
	 */
	public String getTIPO_PARQUEADERO() {
		return TIPO_PARQUEADERO;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por alquiler de
	 * zona comun. <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ALQUILER_ZONA"
	 */
	public String getTIPO_ALQUILER_ZONA() {
		return TIPO_ALQUILER_ZONA;
	}

	/**
	 * Retorna la constante que representa el estado pendiente de una obligacion.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PENDIENTE"
	 */
	public String getESTADO_PENDIENTE() {
		return ESTADO_PENDIENTE;
	}

	/**
	 * Retorna la constante que representa el estado pagada de una obligacion.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PAGADA"
	 */
	public String getESTADO_PAGADA() {
		return ESTADO_PAGADA;
	}

	/**
	 * Retorna la constante que representa el estado vencida de una obligacion.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "VENCIDA"
	 */
	public String getESTADO_VENCIDA() {
		return ESTADO_VENCIDA;
	}

	/**
	 * Retorna la constante que representa el estado anulada de una obligacion.
	 * <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ANULADA"
	 */
	public String getESTADO_ANULADA() {
		return ESTADO_ANULADA;
	}

	/**
	 * Retorna el identificador unico de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la obligacion
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador de la obligacion. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el apartamento asociado a la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Apartamento asociado a la obligacion, o null si no fue
	 *         asignado
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado a la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param apartamento Apartamento a asociar a la obligacion. apartamento != null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna el tipo de la obligacion. <b>pre</b> El objeto Obligacion debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el tipo de la obligacion (ADMINISTRACION, MULTA, DANO,
	 *         etc.)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la obligacion. <b>pre</b> El objeto Obligacion debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 *
	 * @param tipo Nuevo tipo de la obligacion. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el monto de la obligacion. <b>pre</b> El objeto Obligacion debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el valor monetario de la obligacion
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * Asigna el monto de la obligacion. <b>pre</b> El objeto Obligacion debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo monto queda actualizado con el valor recibido. <br>
	 *
	 * @param monto Nuevo valor monetario de la obligacion. monto > 0
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * Retorna la fecha en que fue generada la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de generacion de la obligacion
	 */
	public LocalDate getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Asigna la fecha en que fue generada la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaGeneracion queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param fechaGeneracion Nueva fecha de generacion. fechaGeneracion != null
	 */
	public void setFechaGeneracion(LocalDate fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	/**
	 * Retorna la fecha limite para el pago de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha maxima de pago de la obligacion
	 */
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * Asigna la fecha limite para el pago de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaLimite queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param fechaLimite Nueva fecha maxima de pago. fechaLimite != null,
	 *                    fechaLimite >= fechaGeneracion
	 */
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * Retorna el estado actual de la obligacion. <b>pre</b> El objeto Obligacion
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el estado de la obligacion (PENDIENTE, PAGADA, VENCIDA,
	 *         ANULADA)
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual de la obligacion. <b>pre</b> El objeto Obligacion
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 *
	 * @param estado Nuevo estado de la obligacion. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el concepto o descripcion de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el concepto de la obligacion
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Asigna el concepto o descripcion de la obligacion. <b>pre</b> El objeto
	 * Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo concepto queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param concepto Nuevo concepto de la obligacion. concepto != null, concepto
	 *                 != ""
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Retorna el nombre o identificador de quien genero la obligacion. <b>pre</b>
	 * El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre o id de quien genero la obligacion
	 */
	public String getGeneradaPor() {
		return generadaPor;
	}

	/**
	 * Asigna el nombre o identificador de quien genero la obligacion. <b>pre</b> El
	 * objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> El atributo generadaPor queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param generadaPor Nombre o id del generador de la obligacion. generadaPor !=
	 *                    null, generadaPor != ""
	 */
	public void setGeneradaPor(String generadaPor) {
		this.generadaPor = generadaPor;
	}

	/**
	 * Retorna una representacion en texto de la obligacion con sus datos
	 * principales. <b>pre</b> El objeto Obligacion debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, tipo, monto, estado y fechaLimite de la obligacion
	 */
	@Override
	public String toString() {
		return "Obligacion [id=" + id + ", tipo=" + tipo + ", monto=" + monto + ", estado=" + estado + ", fechaLimite="
				+ fechaLimite + "]";
	}
}