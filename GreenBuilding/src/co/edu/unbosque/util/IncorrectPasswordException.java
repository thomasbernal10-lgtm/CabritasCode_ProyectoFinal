package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un usuario intenta iniciar sesion con una
 * contrasena incorrecta. El usuario existe en el sistema y su cuenta esta activa,
 * pero la contrasena ingresada no coincide con la registrada. Esta excepcion
 * permite diferenciar entre un usuario que no existe (UserNotFoundException)
 * y un usuario que existe pero ingreso mal su contrasena.
 * <b>pre</b> El nombre de usuario debe existir en el sistema y la cuenta debe
 * estar activa antes de verificar la contrasena. <br>
 * <b>post</b> Se interrumpe el proceso de autenticacion y se notifica al usuario
 * que la contrasena ingresada es incorrecta. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class IncorrectPasswordException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3661811510276009781L;

	/**
	 * Construye una nueva excepcion de contrasena incorrecta con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo IncorrectPasswordException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que la contrasena ingresada no es valida. message != null, message != ""
	 */
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
