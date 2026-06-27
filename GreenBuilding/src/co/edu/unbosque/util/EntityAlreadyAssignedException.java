package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta asignar una entidad a un recurso
 * que ya tiene dicha entidad asignada. Aplica en escenarios como asignar un
 * parqueadero que ya esta ocupado, asignar un propietario a un apartamento que
 * ya tiene uno, o asignar un vehiculo a un parqueadero que ya fue adjudicado.
 * <b>pre</b> La entidad que se intenta asignar y el recurso destino deben existir
 * en el sistema. El recurso destino debe tener ya una asignacion activa del mismo tipo. <br>
 * <b>post</b> Se interrumpe la operacion de asignacion y se notifica al usuario
 * que el recurso ya se encuentra asignado. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class EntityAlreadyAssignedException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -7580789024415132532L;

	/**
	 * Construye una nueva excepcion de entidad ya asignada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo EntityAlreadyAssignedException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que recurso ya tiene una asignacion activa. message != null, message != ""
	 */
	public EntityAlreadyAssignedException(String message) {
		super(message);
	}
}
