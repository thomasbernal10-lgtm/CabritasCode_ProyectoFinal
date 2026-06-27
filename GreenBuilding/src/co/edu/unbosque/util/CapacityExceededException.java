package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando un valor numerico supera la capacidad
 * maxima permitida por una regla de negocio del sistema. Se usa en contextos
 * como validacion de montos de cuotas extraordinarias que superan el limite
 * establecido por la asamblea de copropietarios.
 * <b>pre</b> Debe existir un limite de capacidad definido en el sistema contra
 * el cual se pueda comparar el valor ingresado. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el valor
 * supera el limite permitido. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class CapacityExceededException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 9012345678901234567L;

	/**
	 * Construye una nueva excepcion de capacidad excedida con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo CapacityExceededException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica que limite fue superado. message != null, message != ""
	 */
	public CapacityExceededException(String message) {
		super(message);
	}
}
