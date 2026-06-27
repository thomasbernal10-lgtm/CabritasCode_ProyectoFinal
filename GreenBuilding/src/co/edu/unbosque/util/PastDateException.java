package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando una fecha ingresada en el sistema corresponde
 * a una fecha en el pasado y la operacion requiere una fecha presente o futura.
 * Aplica en escenarios como intentar crear una reserva para una fecha que ya paso,
 * programar un mantenimiento en una fecha anterior a hoy, o registrar una campana
 * con fecha de inicio vencida.
 * <b>pre</b> Debe haberse comparado la fecha ingresada con la fecha actual del sistema
 * antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion y se notifica al usuario que la fecha
 * ingresada pertenece al pasado y no es valida para la operacion. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PastDateException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3265153354604117764L;

	/**
	 * Construye una nueva excepcion de fecha en el pasado con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo PastDateException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que indica que la fecha ingresada ya paso. message != null, message != ""
	 */
	public PastDateException(String message) {
		super(message);
	}
}
