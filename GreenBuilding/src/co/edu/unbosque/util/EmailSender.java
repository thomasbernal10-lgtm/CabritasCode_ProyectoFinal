package co.edu.unbosque.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Clase utilitaria encargada del envio de correos electronicos del sistema GreenBuilding.
 * Utiliza el protocolo SMTP con autenticacion TLS para enviar notificaciones a residentes,
 * administradores y otros actores del conjunto residencial. Los parametros de conexion
 * (host, puerto, correo origen y contrasena) se leen desde el archivo config.properties
 * a traves del ConfigurationManager, lo que permite su modificacion sin tocar el codigo.
 * <b>pre</b> El archivo config.properties debe tener configurados los parametros de correo:
 * email.smtp.host, email.smtp.port, email.origen y email.password. <br>
 * <b>post</b> Si la configuracion es correcta, el correo es enviado al destinatario indicado.
 * Si la contrasena no esta configurada o ocurre un error de red, el metodo retorna false
 * sin lanzar excepcion. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class EmailSender {

	/**
	 * Envia un correo electronico al destinatario indicado usando la configuracion SMTP del sistema.
	 * Si la contrasena del correo origen no esta configurada en config.properties, el envio
	 * no se realiza y se registra un mensaje en consola.
	 * <b>pre</b> El destinatario debe ser una direccion de correo valida. El asunto y el cuerpo
	 * no deben ser null. El archivo config.properties debe tener los parametros SMTP configurados. <br>
	 * <b>post</b> Si el envio es exitoso, retorna true y el destinatario recibe el correo.
	 * Si ocurre algun error, retorna false y se registra el fallo en consola sin interrumpir
	 * la ejecucion del sistema. <br>
	 * @param destinatario Direccion de correo del receptor del mensaje. destinatario != null, destinatario != ""
	 * @param asunto Asunto del correo electronico. asunto != null
	 * @param cuerpo Contenido del mensaje a enviar. cuerpo != null
	 * @return true si el correo fue enviado correctamente, false si hubo un error o falta configuracion
	 */
	public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
		String host = ConfigurationManager.get("email.smtp.host");
		String port = ConfigurationManager.get("email.smtp.port");
		String origen = ConfigurationManager.get("email.origen");
		String password = ConfigurationManager.get("email.password");

		if (password.isEmpty()) {
			System.out.println("EmailSender: contrase\u00f1a de correo no configurada en config.properties");
			return false;
		}

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(origen, password);
			}
		});

		try {
			Message mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(origen));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			mensaje.setSubject(asunto);
			mensaje.setText(cuerpo);
			Transport.send(mensaje);
			return true;
		} catch (MessagingException e) {
			System.out.println("EmailSender: error al enviar correo a " + destinatario + " - " + e.getMessage());
			return false;
		}
	}
}
