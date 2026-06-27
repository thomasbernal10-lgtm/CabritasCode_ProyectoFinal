package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta registrar un valor que ya existe
 * en el sistema y debe ser unico. Aplica en escenarios como registrar un usuario
 * con un nombre de usuario ya existente, una cedula ya registrada, o una placa
 * de vehiculo duplicada.
 * <b>pre</b> Debe existir al menos un registro previo en el sistema con el mismo
 * valor que se intenta ingresar. <br>
 * <b>post</b> Se interrumpe el registro y se notifica al usuario que el valor
 * ingresado ya existe en el sistema. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class DuplicateValueException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -1560359256800682316L;

	/**
	 * Construye una nueva excepcion de valor duplicado con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo DuplicateValueException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que valor ya se encuentra registrado. message != null, message != ""
	 */
	public DuplicateValueException(String message) {
		super(message);
	}
}
