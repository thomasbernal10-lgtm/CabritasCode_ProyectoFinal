package co.edu.unbosque.model;

/**
 * Interfaz que define el contrato para las entidades del sistema que pueden
 * recibir notificaciones. Cualquier clase que implemente esta interfaz debe
 * proporcionar el destinatario y el nombre de la notificacion que se le
 * enviara. <b>pre</b> La clase que implemente esta interfaz debe tener
 * definidos los datos necesarios para identificar al destinatario y el tipo de
 * notificacion. <br>
 * <b>post</b> La clase implementadora queda habilitada para ser utilizada por
 * el sistema de notificaciones del conjunto residencial. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public interface Notificable {

	/**
	 * Retorna el destinatario al que se debe enviar la notificacion, generalmente
	 * un correo electronico o identificador de contacto. <b>pre</b> La clase
	 * implementadora debe tener un destinatario definido. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el destinatario de la notificacion
	 */
	public String getDestinatario();

	/**
	 * Retorna el nombre o asunto de la notificacion que se enviara al destinatario.
	 * <b>pre</b> La clase implementadora debe tener un nombre de notificacion
	 * definido. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre o asunto de la notificacion
	 */
	public String getNombreNotificacion();
}