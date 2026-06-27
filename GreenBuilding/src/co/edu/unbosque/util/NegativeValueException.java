package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un valor numerico ingresado en el sistema
 * es negativo y la logica de negocio requiere que sea mayor o igual a cero.
 * Aplica en campos como montos de pagos, multas, cuotas de administracion
 * o cualquier valor monetario o de cantidad que no puede ser negativo.
 * <b>pre</b> Debe haberse leido y evaluado el valor numerico del campo
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el
 * valor ingresado no puede ser negativo. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class NegativeValueException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 2134567890123456789L;

	/**
	 * Construye una nueva excepcion de valor negativo con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo NegativeValueException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que campo contiene un valor negativo no permitido. message != null, message != ""
	 */
	public NegativeValueException(String message) {
		super(message);
	}
}
