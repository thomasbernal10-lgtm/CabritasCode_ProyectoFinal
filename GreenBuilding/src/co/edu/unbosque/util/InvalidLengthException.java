package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un campo de texto no cumple con la longitud
 * minima o maxima permitida por las reglas del sistema. Aplica en campos como
 * contrasenas que deben tener al menos 6 caracteres, nombres que no pueden
 * superar cierto limite, o codigos que deben tener una longitud exacta.
 * <b>pre</b> Debe haberse leido el valor del campo y verificado su longitud
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el campo
 * no cumple con la longitud requerida. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InvalidLengthException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3026988520527419251L;

	/**
	 * Construye una nueva excepcion de longitud invalida con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InvalidLengthException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que campo no cumple con la longitud requerida. message != null, message != ""
	 */
	public InvalidLengthException(String message) {
		super(message);
	}
}
