package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando el indice ingresado para acceder a un elemento
 * de una lista no es valido. Aplica cuando el usuario ingresa un indice negativo,
 * un indice que supera el tamano de la lista, o un valor que no puede ser
 * interpretado como numero entero.
 * <b>pre</b> Debe existir una lista con al menos un elemento y debe haberse
 * intentado acceder a ella con un indice antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el indice
 * ingresado no corresponde a ningun elemento de la lista. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InvalidIndexException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 5437303841156810329L;

	/**
	 * Construye una nueva excepcion de indice invalido con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InvalidIndexException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que el indice ingresado no es valido. message != null, message != ""
	 */
	public InvalidIndexException(String message) {
		super(message);
	}
}
