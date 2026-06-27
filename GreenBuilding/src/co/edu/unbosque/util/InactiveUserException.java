package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un usuario intenta iniciar sesion pero su cuenta
 * se encuentra inactiva en el sistema. Una cuenta inactiva es aquella que fue
 * dada de baja por decision administrativa, diferente a una cuenta bloqueada
 * temporalmente. El usuario inactivo no puede operar en el sistema hasta que
 * un administrador reactive su cuenta.
 * <b>pre</b> El usuario debe existir en el sistema y su atributo activo debe
 * tener el valor false al momento de intentar autenticarse. <br>
 * <b>post</b> Se interrumpe el proceso de autenticacion y se notifica al usuario
 * que su cuenta esta inactiva y debe contactar al administrador. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class InactiveUserException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 8948535689593669341L;

	/**
	 * Construye una nueva excepcion de usuario inactivo con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo InactiveUserException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que la cuenta esta inactiva. message != null, message != ""
	 */
	public InactiveUserException(String message) {
		super(message);
	}
}
