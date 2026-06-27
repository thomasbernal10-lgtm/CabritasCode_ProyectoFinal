package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando una fecha ingresada en el sistema no tiene
 * un formato valido o no puede ser interpretada como una fecha real. Aplica
 * cuando el usuario ingresa una cadena que no sigue el patron esperado
 * (yyyy-MM-dd) o representa una fecha inexistente como 2026-02-30.
 * <b>pre</b> Debe haberse intentado parsear o validar una cadena de texto
 * como fecha antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que la fecha
 * ingresada no tiene un formato valido. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InvalidDateException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -4838154701901925393L;

	/**
	 * Construye una nueva excepcion de fecha invalida con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InvalidDateException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que la fecha ingresada no es valida. message != null, message != ""
	 */
	public InvalidDateException(String message) {
		super(message);
	}
}
