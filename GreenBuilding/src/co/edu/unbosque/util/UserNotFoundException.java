package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta autenticar o recuperar un usuario
 * que no existe en el sistema. Aplica en el proceso de login cuando el nombre
 * de usuario ingresado no corresponde a ningun registro, permitiendo distinguir
 * este caso del de contrasena incorrecta (IncorrectPasswordException).
 * <b>pre</b> Debe haberse buscado el nombre de usuario en la lista de usuarios
 * registrados del sistema antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe el proceso de autenticacion y se notifica al usuario
 * que el nombre de usuario ingresado no existe en el sistema. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class UserNotFoundException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -2486085483963584637L;

	/**
	 * Construye una nueva excepcion de usuario no encontrado con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo UserNotFoundException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que el usuario buscado no existe en el sistema. message != null, message != ""
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
