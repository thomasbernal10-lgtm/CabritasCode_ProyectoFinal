package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa un objeto de transferencia de datos (DTO) para los pagos
 * registrados dentro del sistema GreenBuilding Manager. Permite transportar la
 * informacion relacionada con pagos entre las diferentes capas de la aplicacion
 * sin exponer directamente la logica de negocio de la entidad Pago.
 * 
 * Cada objeto PagoDTO almacena informacion como el apartamento asociado, el
 * monto pagado, la fecha del pago, el medio utilizado y la referencia de la
 * transaccion. <b>pre</b> El apartamento asociado debe existir previamente en
 * el sistema. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * persistencia y visualizacion de informacion financiera. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PagoDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3154900547222131127L;

	/**
	 * Constante que representa el medio de pago en efectivo.
	 */
	private final String MEDIO_EFECTIVO = "EFECTIVO";

	/**
	 * Constante que representa el medio de pago mediante transferencia bancaria.
	 */
	private final String MEDIO_TRANSFERENCIA = "TRANSFERENCIA";

	/**
	 * Constante que representa el medio de pago mediante cheque.
	 */
	private final String MEDIO_CHEQUE = "CHEQUE";

	/** Identificador unico del pago. */
	private String id;

	/** Apartamento asociado al pago realizado. */
	private Apartamento apartamento;

	/** Valor monetario correspondiente al pago realizado. */
	private double monto;

	/** Fecha en la que se realizo el pago. */
	private LocalDate fechaPago;

	/** Medio utilizado para realizar el pago. */
	private String medioPago;

	/** Referencia o comprobante asociado al pago. */
	private String referencia;

	/** Usuario o empleado que registro el pago en el sistema. */
	private String registradoPor;

	/**
	 * Constructor por defecto. Crea un objeto PagoDTO sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto PagoDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public PagoDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto PagoDTO con todos sus atributos
	 * inicializados. <b>pre</b> Los parametros id y medioPago no deben ser null ni
	 * vacios. El apartamento debe estar previamente registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto PagoDTO completamente inicializado y listo para
	 * ser utilizado en procesos de transferencia de datos. <br>
	 * 
	 * @param id            Identificador unico del pago. id != null, id != ""
	 * @param apartamento   Apartamento asociado al pago. apartamento != null
	 * @param monto         Valor monetario del pago. monto >= 0
	 * @param fechaPago     Fecha en la que se realizo el pago. fechaPago != null
	 * @param medioPago     Medio utilizado para realizar el pago. medioPago !=
	 *                      null, medioPago != ""
	 * @param referencia    Referencia o comprobante asociado al pago. Puede ser
	 *                      null
	 * @param registradoPor Usuario que registro el pago en el sistema.
	 *                      registradoPor != null, registradoPor != ""
	 */
	public PagoDTO(String id, Apartamento apartamento, double monto, LocalDate fechaPago, String medioPago,
			String referencia, String registradoPor) {
		super();
		this.id = id;
		this.apartamento = apartamento;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.medioPago = medioPago;
		this.referencia = referencia;
		this.registradoPor = registradoPor;
	}

	/**
	 * Retorna la constante correspondiente al medio de pago en efectivo. <b>pre</b>
	 * El objeto PagoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "EFECTIVO"
	 */
	public String getMEDIO_EFECTIVO() {
		return MEDIO_EFECTIVO;
	}

	/**
	 * Retorna la constante correspondiente al medio de pago por transferencia.
	 * <b>pre</b> El objeto PagoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "TRANSFERENCIA"
	 */
	public String getMEDIO_TRANSFERENCIA() {
		return MEDIO_TRANSFERENCIA;
	}

	/**
	 * Retorna la constante correspondiente al medio de pago por cheque. <b>pre</b>
	 * El objeto PagoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "CHEQUE"
	 */
	public String getMEDIO_CHEQUE() {
		return MEDIO_CHEQUE;
	}

	/**
	 * Retorna el identificador unico del pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del pago
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del pago. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el apartamento asociado al pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento asociado al pago
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado al pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param apartamento Apartamento asociado al pago. apartamento != null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna el valor monetario del pago. <b>pre</b> El objeto PagoDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con el valor del pago
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * Asigna el valor monetario del pago. <b>pre</b> El objeto PagoDTO debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo monto queda actualizado con el valor recibido. <br>
	 * 
	 * @param monto Nuevo valor del pago. monto >= 0
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * Retorna la fecha en la que se realizo el pago. <b>pre</b> El objeto PagoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDate con la fecha del pago
	 */
	public LocalDate getFechaPago() {
		return fechaPago;
	}

	/**
	 * Asigna la fecha en la que se realizo el pago. <b>pre</b> El objeto PagoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaPago queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param fechaPago Nueva fecha del pago. fechaPago != null
	 */
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Retorna el medio utilizado para realizar el pago. <b>pre</b> El objeto
	 * PagoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el medio de pago utilizado
	 */
	public String getMedioPago() {
		return medioPago;
	}

	/**
	 * Asigna el medio utilizado para realizar el pago. <b>pre</b> El objeto PagoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo medioPago queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param medioPago Nuevo medio de pago. medioPago != null, medioPago != ""
	 */
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * Retorna la referencia asociada al pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la referencia del pago
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Asigna la referencia asociada al pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo referencia queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param referencia Nueva referencia del pago. Puede ser null
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Retorna el usuario que registro el pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre del usuario que registro el pago
	 */
	public String getRegistradoPor() {
		return registradoPor;
	}

	/**
	 * Asigna el usuario que registro el pago. <b>pre</b> El objeto PagoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo registradoPor queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param registradoPor Nuevo usuario que registro el pago. registradoPor !=
	 *                      null, registradoPor != ""
	 */
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

	/**
	 * Retorna una representacion en texto del pago con sus datos principales.
	 * <b>pre</b> El objeto PagoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, monto, fecha, medio de pago y referencia del pago
	 */
	@Override
	public String toString() {
		return "Pago [id=" + id + ", monto=" + monto + ", fechaPago=" + fechaPago + ", medioPago=" + medioPago
				+ ", referencia=" + referencia + "]";
	}
}