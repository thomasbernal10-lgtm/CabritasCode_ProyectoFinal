package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Objeto de transferencia de datos (DTO) que representa una obligacion
 * economica asociada a un apartamento del conjunto residencial. Una obligacion
 * puede corresponder a cuotas de administracion, cuotas extraordinarias,
 * multas, danos, parqueaderos o alquiler de zonas comunes. <br>
 * <b>pre</b> El apartamento asociado debe estar registrado en el sistema. <br>
 * <b>post</b> La obligacion queda disponible para ser gestionada, consultada y
 * vinculada a pagos dentro del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ObligacionDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 239822564433107614L;

	/**
	 * Constante que representa una obligacion por cuota de administracion mensual.
	 */
	private final String TIPO_ADMINISTRACION = "ADMINISTRACION";

	/**
	 * Constante que representa una obligacion por cuota extraordinaria aprobada por
	 * la asamblea o el consejo de administracion.
	 */
	private final String TIPO_CUOTA_EXTRAORDINARIA = "CUOTA_EXTRAORDINARIA";

	/**
	 * Constante que representa una obligacion generada por una multa impuesta al
	 * residente.
	 */
	private final String TIPO_MULTA = "MULTA";

	/**
	 * Constante que representa una obligacion por danos ocasionados en areas
	 * comunes o bienes del conjunto.
	 */
	private final String TIPO_DANO = "DANO";

	/**
	 * Constante que representa una obligacion por concepto de parqueadero adicional
	 * o temporal.
	 */
	private final String TIPO_PARQUEADERO = "PARQUEADERO";

	/**
	 * Constante que representa una obligacion por alquiler de una zona comun del
	 * conjunto.
	 */
	private final String TIPO_ALQUILER_ZONA = "ALQUILER_ZONA";

	/**
	 * Constante que representa el estado de una obligacion que aun no ha sido
	 * cancelada y se encuentra dentro del plazo.
	 */
	private final String ESTADO_PENDIENTE = "PENDIENTE";

	/**
	 * Constante que representa el estado de una obligacion que ha sido cancelada
	 * completamente.
	 */
	private final String ESTADO_PAGADA = "PAGADA";

	/**
	 * Constante que representa el estado de una obligacion que supero su fecha
	 * limite sin haber sido pagada.
	 */
	private final String ESTADO_VENCIDA = "VENCIDA";

	/**
	 * Constante que representa el estado de una obligacion que fue anulada por la
	 * administracion.
	 */
	private final String ESTADO_ANULADA = "ANULADA";

	/** Identificador unico de la obligacion. */
	private String id;

	/** Apartamento al cual se encuentra asociada esta obligacion. */
	private Apartamento apartamento;

	/**
	 * Tipo de obligacion (ADMINISTRACION, CUOTA_EXTRAORDINARIA, MULTA, DANO,
	 * PARQUEADERO o ALQUILER_ZONA).
	 */
	private String tipo;

	/** Valor monetario de la obligacion. */
	private double monto;

	/** Fecha en la que fue generada la obligacion. */
	private LocalDate fechaGeneracion;

	/** Fecha maxima para realizar el pago de la obligacion. */
	private LocalDate fechaLimite;

	/**
	 * Estado actual de la obligacion (PENDIENTE, PAGADA, VENCIDA o ANULADA).
	 */
	private String estado;

	/** Descripcion o justificacion del concepto que origino la obligacion. */
	private String concepto;

	/**
	 * Identificador del usuario o proceso que genero la obligacion dentro del
	 * sistema.
	 */
	private String generadaPor;

	/**
	 * Constructor por defecto. Crea una obligacion sin datos inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ObligacionDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public ObligacionDTO() {
	}

	/**
	 * Constructor completo. Crea una obligacion con todos sus atributos
	 * inicializados. <br>
	 * <b>pre</b> Los parametros id, tipo, estado y concepto no deben ser null ni
	 * vacios. El apartamento debe estar previamente registrado en el sistema. El
	 * monto debe ser mayor a cero. <br>
	 * <b>post</b> Se crea un objeto ObligacionDTO completamente inicializado y
	 * listo para ser registrado y gestionado en el sistema. <br>
	 *
	 * @param id              Identificador unico de la obligacion. id != null, id
	 *                        != ""
	 * @param apartamento     Apartamento asociado a la obligacion. apartamento !=
	 *                        null
	 * @param tipo            Tipo de obligacion segun las constantes definidas.
	 *                        tipo != null, tipo != ""
	 * @param monto           Valor monetario de la obligacion. monto > 0
	 * @param fechaGeneracion Fecha en que se genero la obligacion. fechaGeneracion
	 *                        != null
	 * @param fechaLimite     Fecha maxima de pago. fechaLimite != null, fechaLimite
	 *                        >= fechaGeneracion
	 * @param estado          Estado inicial de la obligacion. estado != null,
	 *                        estado != ""
	 * @param concepto        Descripcion del concepto que origina la obligacion.
	 *                        concepto != null, concepto != ""
	 * @param generadaPor     Identificador de quien genera la obligacion.
	 *                        generadaPor != null, generadaPor != ""
	 */
	public ObligacionDTO(String id, Apartamento apartamento, String tipo, double monto, LocalDate fechaGeneracion,
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
	 * <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ADMINISTRACION"
	 */
	public String getTIPO_ADMINISTRACION() {
		return TIPO_ADMINISTRACION;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por cuota
	 * extraordinaria. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "CUOTA_EXTRAORDINARIA"
	 */
	public String getTIPO_CUOTA_EXTRAORDINARIA() {
		return TIPO_CUOTA_EXTRAORDINARIA;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por multa. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MULTA"
	 */
	public String getTIPO_MULTA() {
		return TIPO_MULTA;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por dano en area
	 * comun. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "DANO"
	 */
	public String getTIPO_DANO() {
		return TIPO_DANO;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por parqueadero.
	 * <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PARQUEADERO"
	 */
	public String getTIPO_PARQUEADERO() {
		return TIPO_PARQUEADERO;
	}

	/**
	 * Retorna la constante que representa el tipo de obligacion por alquiler de
	 * zona comun. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ALQUILER_ZONA"
	 */
	public String getTIPO_ALQUILER_ZONA() {
		return TIPO_ALQUILER_ZONA;
	}

	/**
	 * Retorna la constante que representa el estado de obligacion pendiente de
	 * pago. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PENDIENTE"
	 */
	public String getESTADO_PENDIENTE() {
		return ESTADO_PENDIENTE;
	}

	/**
	 * Retorna la constante que representa el estado de obligacion pagada. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PAGADA"
	 */
	public String getESTADO_PAGADA() {
		return ESTADO_PAGADA;
	}

	/**
	 * Retorna la constante que representa el estado de obligacion vencida. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "VENCIDA"
	 */
	public String getESTADO_VENCIDA() {
		return ESTADO_VENCIDA;
	}

	/**
	 * Retorna la constante que representa el estado de obligacion anulada. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ANULADA"
	 */
	public String getESTADO_ANULADA() {
		return ESTADO_ANULADA;
	}

	/**
	 * Retorna el identificador unico de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id de la obligacion
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador de la obligacion. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el apartamento asociado a la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Apartamento vinculado a la obligacion, o null si no tiene
	 *         apartamento asignado
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado a la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 *
	 * @param apartamento Apartamento a vincular con la obligacion. apartamento !=
	 *                    null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna el tipo de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el tipo de la obligacion segun las constantes definidas
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 *
	 * @param tipo Nuevo tipo de la obligacion. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el monto de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el valor monetario de la obligacion
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * Asigna el monto de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo monto queda actualizado con el valor recibido. <br>
	 *
	 * @param monto Nuevo valor monetario de la obligacion. monto > 0
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * Retorna la fecha en que fue generada la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha de generacion de la obligacion
	 */
	public LocalDate getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Asigna la fecha de generacion de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaGeneracion queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param fechaGeneracion Nueva fecha de generacion. fechaGeneracion != null
	 */
	public void setFechaGeneracion(LocalDate fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	/**
	 * Retorna la fecha limite de pago de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDate con la fecha maxima para realizar el pago
	 */
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * Asigna la fecha limite de pago de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaLimite queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param fechaLimite Nueva fecha limite de pago. fechaLimite != null,
	 *                    fechaLimite >= fechaGeneracion
	 */
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * Retorna el estado actual de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el estado actual (PENDIENTE, PAGADA, VENCIDA o ANULADA)
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado de la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 *
	 * @param estado Nuevo estado de la obligacion. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el concepto que origino la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la descripcion del concepto de la obligacion
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Asigna el concepto que origina la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo concepto queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param concepto Nueva descripcion del concepto. concepto != null, concepto !=
	 *                 ""
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Retorna el identificador de quien genero la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el identificador del usuario o proceso que creo la
	 *         obligacion
	 */
	public String getGeneradaPor() {
		return generadaPor;
	}

	/**
	 * Asigna el identificador de quien genero la obligacion. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo generadaPor queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param generadaPor Identificador del generador de la obligacion. generadaPor
	 *                    != null, generadaPor != ""
	 */
	public void setGeneradaPor(String generadaPor) {
		this.generadaPor = generadaPor;
	}

	/**
	 * Retorna una representacion en texto de la obligacion con sus datos
	 * principales. <br>
	 * <b>pre</b> El objeto ObligacionDTO debe estar instanciado. <br>
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