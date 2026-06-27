package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un usuario intenta iniciar sesion
 * pero su cuenta ha sido bloqueada por el administrador del sistema.
 * Esta condicion es diferente a una cuenta inactiva: el bloqueo es
 * una accion administrativa explicita sobre una cuenta que aun existe.
 * <b>pre</b> El usuario debe existir en el sistema y su cuenta debe estar
 * marcada como bloqueada por un administrador. <br>
 * <b>post</b> Se interrumpe el proceso de autenticacion y se notifica al
 * usuario que su cuenta no esta disponible. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class AccountBlockedException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -3920547218175052731L;

	/**
	 * Construye una nueva excepcion de cuenta bloqueada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo AccountBlockedException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica por que la cuenta esta bloqueada. message != null, message != ""
	 */
	public AccountBlockedException(String message) {
		super(message);
	}
}
