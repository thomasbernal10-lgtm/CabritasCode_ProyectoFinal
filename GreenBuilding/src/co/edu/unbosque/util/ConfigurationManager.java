package co.edu.unbosque.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gestor centralizado de configuracion del sistema GreenBuilding.
 * Carga los parametros operativos desde el archivo config.properties ubicado
 * en la carpeta files/ del proyecto. Si el archivo no existe, genera uno nuevo
 * con valores por defecto que permiten al sistema operar sin configuracion previa.
 * Los parametros gestionados incluyen credenciales de correo, montos de multas,
 * dias limite de solicitudes, y credenciales del superadmin.
 * Este archivo puede ser editado con un bloc de notas sin necesidad de modificar
 * el codigo fuente, cumpliendo el requisito de parametrizacion del sistema.
 * <b>pre</b> La carpeta files/ debe existir en el directorio raiz del proyecto
 * para que el archivo de propiedades pueda ser leido o creado correctamente. <br>
 * <b>post</b> Los parametros del sistema quedan disponibles para ser consultados
 * por cualquier clase del sistema a traves de los metodos estaticos de esta clase. <br>
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ConfigurationManager {

	/**
	 * Contenedor de propiedades cargadas desde el archivo config.properties.
	 */
	private static final Properties props = new Properties();

	/**
	 * Bloque estatico de inicializacion. Intenta cargar el archivo config.properties
	 * al momento en que la clase es cargada por la JVM. Si el archivo no existe o
	 * no puede ser leido, se generan y persisten los valores por defecto.
	 * <b>pre</b> La carpeta files/ debe existir en el directorio de ejecucion. <br>
	 * <b>post</b> El objeto props queda cargado con los parametros del sistema,
	 * ya sea desde el archivo existente o desde los valores por defecto generados. <br>
	 */
	static {
		try (InputStream input = new FileInputStream("files/config.properties")) {
			props.load(input);
		} catch (IOException e) {
			crearPropertiesPorDefecto();
		}
	}

	/**
	 * Crea el archivo config.properties con valores por defecto y lo persiste en disco.
	 * Este metodo se invoca automaticamente cuando el archivo de configuracion no existe.
	 * <b>pre</b> La carpeta files/ debe existir en el directorio de ejecucion. <br>
	 * <b>post</b> Se genera el archivo config.properties con los parametros por defecto
	 * del sistema y el objeto props queda cargado con dichos valores. <br>
	 */
	private static void crearPropertiesPorDefecto() {
		props.setProperty("email.origen", "greenbuildingcontact@gmail.com");
		props.setProperty("email.password", "");
		props.setProperty("email.smtp.host", "smtp.gmail.com");
		props.setProperty("email.smtp.port", "587");
		props.setProperty("multa.monto.defecto", "150000");
		props.setProperty("cuota.admin.dias.limite", "10");
		props.setProperty("solicitud.alta.dias.limite", "3");
		props.setProperty("solicitud.media.dias.limite", "7");
		props.setProperty("solicitud.baja.dias.limite", "15");
		props.setProperty("superadmin.username", "superadmin");
		props.setProperty("superadmin.contrasena", "GreenBuilding2026*");
		props.setProperty("superadmin.id", "USR-0");

		try (FileOutputStream output = new FileOutputStream("files/config.properties")) {
			props.store(output, "Configuracion GreenBuilding - editar con bloc de notas");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna el valor de una propiedad de configuracion como String.
	 * Si la clave no existe en el archivo de propiedades, retorna una cadena vacia.
	 * <b>pre</b> El objeto props debe estar inicializado con los valores del sistema. <br>
	 * <b>post</b> No se modifica el estado de las propiedades. <br>
	 * @param clave Nombre de la propiedad a consultar. clave != null, clave != ""
	 * @return String con el valor asociado a la clave, o "" si la clave no existe
	 */
	public static String get(String clave) {
		return props.getProperty(clave, "");
	}

	/**
	 * Retorna el valor de una propiedad de configuracion como entero.
	 * Si la clave no existe o su valor no es un numero entero valido, retorna 0.
	 * <b>pre</b> El objeto props debe estar inicializado con los valores del sistema. <br>
	 * <b>post</b> No se modifica el estado de las propiedades. <br>
	 * @param clave Nombre de la propiedad a consultar. clave != null, clave != ""
	 * @return int con el valor entero de la propiedad, o 0 si no existe o no es parseable
	 */
	public static int getInt(String clave) {
		try {
			return Integer.parseInt(props.getProperty(clave, "0"));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Retorna el valor de una propiedad de configuracion como numero decimal.
	 * Si la clave no existe o su valor no es un numero decimal valido, retorna 0.0.
	 * <b>pre</b> El objeto props debe estar inicializado con los valores del sistema. <br>
	 * <b>post</b> No se modifica el estado de las propiedades. <br>
	 * @param clave Nombre de la propiedad a consultar. clave != null, clave != ""
	 * @return double con el valor decimal de la propiedad, o 0.0 si no existe o no es parseable
	 */
	public static double getDouble(String clave) {
		try {
			return Double.parseDouble(props.getProperty(clave, "0"));
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
}
