package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando los datos proporcionados para completar una
 * operacion son insuficientes para procesarla correctamente. A diferencia de
 * EmptyFieldException que aplica a un campo individual vacio, esta excepcion
 * aplica cuando el conjunto de datos disponibles no es suficiente para ejecutar
 * la logica de negocio requerida, por ejemplo, intentar generar un reporte sin
 * datos registrados o crear una obligacion sin un apartamento asociado.
 * <b>pre</b> Debe haberse intentado ejecutar una operacion que requiere un conjunto
 * minimo de datos que no esta disponible en el sistema. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que los datos
 * disponibles no son suficientes para completarla. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InsufficientDataException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 5678901234567890123L;

	/**
	 * Construye una nueva excepcion de datos insuficientes con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InsufficientDataException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que informacion falta para completar la operacion. message != null, message != ""
	 */
	public InsufficientDataException(String message) {
		super(message);
	}
}
