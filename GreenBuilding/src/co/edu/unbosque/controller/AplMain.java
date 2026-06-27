package co.edu.unbosque.controller;

/**
 * Clase principal de ejecucion del sistema GreenBuilding Manager.
 * 
 * Esta clase contiene el metodo main, el cual permite iniciar la aplicacion
 * creando una instancia del controlador principal y ejecutando el flujo inicial
 * del sistema.
 * 
 * <b>pre</b> La clase Controller debe existir correctamente implementada dentro
 * del paquete controller. <br>
 * <b>post</b> El sistema inicia correctamente y queda disponible para la
 * interaccion con el usuario. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class AplMain {

	/**
	 * Metodo principal de ejecucion del programa.
	 * 
	 * <b>pre</b> No existen precondiciones para la ejecucion del metodo. <br>
	 * <b>post</b> Se crea una instancia de Controller y se ejecuta el metodo
	 * iniciar() para comenzar el funcionamiento del sistema. <br>
	 * 
	 * @param args Argumentos enviados desde la linea de comandos.
	 */
	public static void main(String[] args) {

		/**
		 * Instancia principal del controlador del sistema.
		 */
		Controller controller = new Controller();

		/**
		 * Inicia la ejecucion de la aplicacion.
		 */
		controller.iniciar();
	}
}