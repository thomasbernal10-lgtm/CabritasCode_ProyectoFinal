package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta realizar una operacion sobre una
 * entidad que se encuentra bloqueada o en un estado que no permite dicha accion.
 * Aplica principalmente cuando un residente intenta reservar una zona comun que
 * esta en estado MANTENIMIENTO o BLOQUEADA por decision administrativa.
 * <b>pre</b> La entidad sobre la que se intenta operar debe existir en el sistema
 * y su estado debe ser uno que impida la operacion solicitada. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que la entidad
 * no esta disponible por estar bloqueada. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class EntityBlockedException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 6034851381171431571L;

	/**
	 * Construye una nueva excepcion de entidad bloqueada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo EntityBlockedException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que entidad esta bloqueada y por que razon. message != null, message != ""
	 */
	public EntityBlockedException(String message) {
		super(message);
	}
}
