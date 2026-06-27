package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta ejecutar una operacion sobre
 * un elemento que ya fue procesado anteriormente y no puede ser procesado
 * de nuevo. Aplica en escenarios como cancelar una reserva ya cancelada,
 * entregar un paquete que ya fue entregado, o cerrar una solicitud ya cerrada.
 * <b>pre</b> El elemento sobre el que se intenta operar debe existir en el sistema
 * y debe tener un estado que indique que ya fue procesado. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que el elemento
 * ya fue procesado previamente. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class AlreadyProcessedException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 4611738588094236586L;

	/**
	 * Construye una nueva excepcion de elemento ya procesado con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo AlreadyProcessedException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica que operacion ya fue procesada. message != null, message != ""
	 */
	public AlreadyProcessedException(String message) {
		super(message);
	}
}
