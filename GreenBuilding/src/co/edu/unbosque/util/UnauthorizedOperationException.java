package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un usuario intenta realizar una operacion
 * para la que no tiene permisos segun su rol en el sistema. Aplica cuando
 * un residente intenta acceder a funciones administrativas, o cuando un
 * administrador intenta ejecutar operaciones reservadas para el superadmin.
 * Refuerza el control de acceso basado en roles del sistema.
 * <b>pre</b> El usuario debe estar autenticado en el sistema y debe haberse
 * verificado su rol antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que no
 * tiene permisos suficientes para ejecutar la accion solicitada. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class UnauthorizedOperationException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 653516465986721237L;

	/**
	 * Construye una nueva excepcion de operacion no autorizada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo UnauthorizedOperationException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que operacion no esta permitida para el rol del usuario. message != null, message != ""
	 */
	public UnauthorizedOperationException(String message) {
		super(message);
	}
}
