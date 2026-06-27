package co.edu.unbosque.util;

/**
 * Excepcion que se lanza cuando ocurre un error durante una operacion de
 * lectura o escritura de archivos en el sistema. Aplica principalmente cuando
 * el sistema intenta exportar un reporte en formato PDF y la operacion falla
 * por permisos de escritura, ruta invalida u otro error de entrada/salida.
 * <b>pre</b> Debe haberse intentado realizar una operacion sobre el sistema de
 * archivos antes de lanzar esta excepcion. <br>
 * <b>post</b> Se interrumpe la operacion de archivo y se notifica al usuario
 * que no fue posible completar la lectura o escritura del archivo. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class FileOperationException extends Exception {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 4225328621559166361L;

	/**
	 * Construye una nueva excepcion de operacion de archivo con el mensaje indicado.
	 * <b>pre</b> El mensaje no debe ser null ni vacio. <br>
	 * <b>post</b> Se crea una excepcion de tipo FileOperationException lista
	 * para ser lanzada con el mensaje de error correspondiente. <br>
	 * @param message Mensaje descriptivo que explica el error de archivo ocurrido. message != null, message != ""
	 */
	public FileOperationException(String message) {
		super(message);
	}
}
