package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando ocurre un error en el procesamiento de un pago
 * o una obligacion financiera dentro del sistema. Aplica en escenarios como
 * intentar registrar un pago con un monto invalido, registrar un pago para una
 * obligacion que ya fue saldada, o cuando la informacion financiera del apartamento
 * no es suficiente para completar la operacion de pago.
 * <b>pre</b> Debe existir una obligacion financiera registrada en el sistema y
 * debe haberse intentado procesar una operacion de pago sobre ella. <br>
 * <b>post</b> Se interrumpe la operacion de pago y se notifica al usuario sobre
 * el problema encontrado en el proceso. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PaymentException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -1437141009945411614L;

	/**
	 * Construye una nueva excepcion de pago con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo PaymentException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica el error ocurrido en el proceso de pago. message != null, message != ""
	 */
	public PaymentException(String message) {
		super(message);
	}
}
