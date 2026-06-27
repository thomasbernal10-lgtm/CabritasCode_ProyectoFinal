package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un campo ingresado en el sistema no cumple
 * con el formato esperado segun las reglas de validacion. Aplica en campos
 * como correos electronicos que no siguen el patron usuario@dominio.ext,
 * numeros de telefono con caracteres no numericos, o placas de vehiculos
 * que no respetan el formato establecido.
 * <b>pre</b> Debe haberse intentado validar el formato de un campo de entrada
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el campo
 * ingresado no tiene el formato correcto. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InvalidFormatException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 149135977400692496L;

	/**
	 * Construye una nueva excepcion de formato invalido con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InvalidFormatException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que campo tiene formato incorrecto y cual es el esperado. message != null, message != ""
	 */
	public InvalidFormatException(String message) {
		super(message);
	}
}
