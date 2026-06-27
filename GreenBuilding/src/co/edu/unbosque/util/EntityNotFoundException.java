package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta acceder, modificar o eliminar una
 * entidad que no existe en el sistema. Es la segunda excepcion mas frecuente
 * del sistema y aplica en busquedas por indice, id o cualquier criterio que
 * no encuentre coincidencia en los datos registrados.
 * <b>pre</b> Debe haberse realizado una busqueda en el sistema con algun criterio
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que la entidad
 * buscada no existe en el sistema. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class EntityNotFoundException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3799686260137956294L;

	/**
	 * Construye una nueva excepcion de entidad no encontrada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo EntityNotFoundException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que entidad no fue encontrada. message != null, message != ""
	 */
	public EntityNotFoundException(String message) {
		super(message);
	}
}
