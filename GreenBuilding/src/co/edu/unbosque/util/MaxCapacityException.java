package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando se intenta crear una reserva en una zona comun
 * que ya ha alcanzado su aforo maximo de reservas para la fecha y franja horaria
 * solicitada. Esta excepcion protege la integridad de las reglas de uso de las
 * zonas comunes del conjunto residencial.
 * <b>pre</b> La zona comun debe tener un aforo maximo configurado mayor a cero
 * y debe haberse contado el numero de reservas activas en el rango solicitado
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la creacion de la reserva y se notifica al usuario
 * que la zona comun no tiene cupos disponibles para la fecha solicitada. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class MaxCapacityException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 7812345678901234567L;

	/**
	 * Construye una nueva excepcion de capacidad maxima alcanzada con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo MaxCapacityException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que zona comun alcanzo su capacidad maxima. message != null, message != ""
	 */
	public MaxCapacityException(String message) {
		super(message);
	}
}
