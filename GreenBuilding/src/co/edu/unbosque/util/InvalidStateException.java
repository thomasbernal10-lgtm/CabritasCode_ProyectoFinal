package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta realizar una transicion de estado
 * no permitida sobre una entidad del sistema. Aplica cuando la logica de negocio
 * define una maquina de estados y se intenta mover una entidad a un estado
 * que no es valido desde su estado actual, por ejemplo, intentar cancelar una
 * solicitud de mantenimiento que ya fue cerrada, o activar una campana que ya
 * fue finalizada.
 * <b>pre</b> La entidad debe existir en el sistema y tener un estado definido
 * antes de intentar la transicion. <br>
 * <b>post</b> Se interrumpe la operacion de cambio de estado y se notifica al
 * usuario que la transicion solicitada no es valida desde el estado actual. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InvalidStateException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3456789012345678901L;

	/**
	 * Construye una nueva excepcion de estado invalido con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InvalidStateException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que transicion de estado no es permitida. message != null, message != ""
	 */
	public InvalidStateException(String message) {
		super(message);
	}
}
