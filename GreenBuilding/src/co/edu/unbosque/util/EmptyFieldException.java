package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un campo obligatorio del formulario o de la
 * operacion se encuentra vacio o es null. Es la excepcion mas frecuente del
 * sistema y aplica a cualquier campo de entrada que sea requerido para completar
 * un registro, busqueda, actualizacion o cualquier operacion del Controller.
 * <b>pre</b> El campo evaluado debe haber sido leido desde la interfaz grafica
 * o recibido como parametro antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que debe
 * completar el campo indicado. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class EmptyFieldException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -1257238101513485235L;

	/**
	 * Construye una nueva excepcion de campo vacio con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo EmptyFieldException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que campo esta vacio o es obligatorio. message != null, message != ""
	 */
	public EmptyFieldException(String message) {
		super(message);
	}
}
