package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando existe un conflicto entre fechas u horas
 * ingresadas en el sistema. Aplica en escenarios como reservas donde la hora
 * de fin es anterior o igual a la hora de inicio, o cuando se intenta crear
 * una reserva en un rango que se solapa con otra ya existente.
 * <b>pre</b> Deben existir dos o mas valores de fecha u hora que sean comparables
 * entre si dentro del contexto de la operacion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario sobre el
 * conflicto de fechas detectado. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class DateConflictException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 1502070715483968811L;

	/**
	 * Construye una nueva excepcion de conflicto de fechas con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo DateConflictException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica el conflicto de fechas detectado. message != null, message != ""
	 */
	public DateConflictException(String message) {
		super(message);
	}
}
