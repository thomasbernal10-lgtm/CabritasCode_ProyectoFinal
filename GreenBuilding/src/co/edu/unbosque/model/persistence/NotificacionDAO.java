package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;

import co.edu.unbosque.model.Notificacion;
import co.edu.unbosque.model.NotificacionDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las notificaciones del sistema GreenBuilding Manager. Permite crear,
 * actualizar, eliminar, consultar y almacenar informacion relacionada con las
 * notificaciones enviadas a residentes, administradores o usuarios del sistema.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Notificacion y NotificacionDTO. Utiliza archivos de texto y serializacion
 * para mantener la persistencia de los datos.
 * 
 * <b>pre</b> Los objetos NotificacionDTO utilizados en las operaciones deben
 * contener informacion valida y correctamente inicializada. <br>
 * <b>post</b> Las notificaciones quedan almacenadas y disponibles para procesos
 * de consulta, actualizacion y gestion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class NotificacionDAO implements DAO<Notificacion, NotificacionDTO> {

	/**
	 * Lista que almacena las notificaciones registradas en el sistema.
	 */
	private ArrayList<Notificacion> listaNotificaciones;

	/**
	 * Ruta del archivo serializado de notificaciones.
	 */
	private final String URL_SERIALIZADO = "notificaciones.dat";

	/**
	 * Ruta del archivo de texto de notificaciones.
	 */
	private final String URL_TEXTO = "notificaciones.csv";

	/**
	 * Constructor por defecto de la clase NotificacionDAO.
	 * 
	 * <b>pre</b> No existen precondiciones para la construccion del objeto. <br>
	 * <b>post</b> Se inicializa la lista de notificaciones y se cargan los datos
	 * almacenados en el archivo serializado. <br>
	 */
	public NotificacionDAO() {
		listaNotificaciones = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de notificaciones almacenadas.
	 * 
	 * <b>pre</b> El objeto NotificacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Notificacion
	 */
	public ArrayList<Notificacion> getListaNotificaciones() {
		return listaNotificaciones;
	}

	/**
	 * Asigna una nueva lista de notificaciones.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaNotificaciones queda actualizado. <br>
	 * 
	 * @param listaNotificaciones Nueva lista de notificaciones
	 */
	public void setListaNotificaciones(ArrayList<Notificacion> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}

	/**
	 * Crea una nueva notificacion dentro del sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe ser valido y diferente de null. <br>
	 * <b>post</b> La notificacion queda agregada y almacenada en los archivos de
	 * persistencia. <br>
	 * 
	 * @param nuevoDato DTO con la informacion de la notificacion
	 */
	@Override
	public void crear(NotificacionDTO nuevoDato) {
		listaNotificaciones.add(DataMapper.convertirNotificacionDTOANotificacion(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una notificacion de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe corresponder a una posicion valida dentro de la
	 * lista. <br>
	 * <b>post</b> La notificacion es eliminada si el indice es valido. <br>
	 * 
	 * @param index Posicion de la notificacion a eliminar
	 * @return true si la eliminacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaNotificaciones.size()) {
			return false;
		} else {
			listaNotificaciones.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza una notificacion existente.
	 * 
	 * <b>pre</b> El indice debe existir y el DTO debe contener informacion valida.
	 * <br>
	 * <b>post</b> La notificacion queda actualizada en memoria y persistencia. <br>
	 * 
	 * @param index           Posicion de la notificacion a actualizar
	 * @param datoActualizado DTO con la nueva informacion
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, NotificacionDTO datoActualizado) {
		if (index < 0 || index >= listaNotificaciones.size()) {
			return false;
		} else {
			listaNotificaciones.set(index, DataMapper.convertirNotificacionDTOANotificacion(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las notificaciones en formato String.
	 * 
	 * <b>pre</b> El objeto debe estar correctamente inicializado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return String con todas las notificaciones registradas
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;
		for (Notificacion n : listaNotificaciones) {
			datos += posicion + " ";
			datos += n.toString() + "\n";
			posicion++;
		}
		return datos;
	}

	/**
	 * Retorna una notificacion especifica segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica el contenido de la lista. <br>
	 * 
	 * @param index Posicion de la notificacion
	 * @return String con la informacion de la notificacion
	 */
	@Override
	public String mostrar(int index) {
		return listaNotificaciones.get(index).toString();
	}

	/**
	 * Retorna todas las notificaciones convertidas a DTO.
	 * 
	 * <b>pre</b> La lista de notificaciones debe estar inicializada. <br>
	 * <b>post</b> Se retorna una lista DTO equivalente. <br>
	 * 
	 * @return Lista de objetos NotificacionDTO
	 */
	@Override
	public ArrayList<NotificacionDTO> mostrarTodo() {
		return DataMapper.convertirListaNotificacionAListaNotificacionDTO(listaNotificaciones);
	}

	/**
	 * Escribe las notificaciones en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de notificaciones debe existir. <br>
	 * <b>post</b> La informacion queda almacenada en el archivo CSV. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();
		for (Notificacion n : listaNotificaciones) {
			sb.append(n.getId()).append(";");
			sb.append(n.getDestinatario()).append(";");
			sb.append(n.getAsunto()).append(";");
			sb.append(n.getTipo()).append(";");
			sb.append(n.getFechaEnvio()).append(";");
			sb.append(n.isEnviada()).append(";");
			sb.append(n.getErrorEnvio()).append("\n");
		}
		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee las notificaciones almacenadas en el archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o poder ser creado. <br>
	 * <b>post</b> Las notificaciones leidas quedan cargadas en memoria. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);
		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {
			String[] columnas = fila.split(";");

			Notificacion nuevo = new Notificacion();

			nuevo.setId(columnas[0]);
			nuevo.setDestinatario(columnas[1]);
			nuevo.setAsunto(columnas[2]);
			nuevo.setTipo(columnas[3]);
			nuevo.setFechaEnvio(LocalDateTime.parse(columnas[4]));
			nuevo.setEnviada(Boolean.parseBoolean(columnas[5]));
			nuevo.setErrorEnvio(columnas[6]);

			listaNotificaciones.add(nuevo);
		}
	}

	/**
	 * Guarda las notificaciones utilizando serializacion.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Las notificaciones quedan almacenadas en el archivo serializado.
	 * <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaNotificaciones);
	}

	/**
	 * Lee las notificaciones desde el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista queda cargada con las notificaciones almacenadas. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaNotificaciones = new ArrayList<>();
		} else {
			listaNotificaciones = (ArrayList<Notificacion>) contenido;
		}
	}

	/**
	 * Busca notificaciones segun el destinatario indicado.
	 * 
	 * <b>pre</b> El destinatario no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las coincidencias encontradas. <br>
	 * 
	 * @param destinatario Destinatario de la notificacion
	 * @return Lista de notificaciones asociadas al destinatario
	 */
	public ArrayList<Notificacion> buscarPorDestinatario(String destinatario) {
		ArrayList<Notificacion> resultado = new ArrayList<>();

		for (Notificacion n : listaNotificaciones)
			if (n.getDestinatario() != null && n.getDestinatario().equals(destinatario))
				resultado.add(n);

		return resultado;
	}

	/**
	 * Busca las notificaciones pendientes de envio.
	 * 
	 * <b>pre</b> La lista de notificaciones debe existir. <br>
	 * <b>post</b> Se retorna una lista con las notificaciones no enviadas. <br>
	 * 
	 * @return Lista de notificaciones pendientes
	 */
	public ArrayList<Notificacion> buscarPendientes() {
		ArrayList<Notificacion> resultado = new ArrayList<>();

		for (Notificacion n : listaNotificaciones)
			if (!n.isEnviada())
				resultado.add(n);

		return resultado;
	}

	/**
	 * Ordena ascendentemente una lista de notificaciones.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Actualmente el metodo no posee implementacion. <br>
	 * 
	 * @param listaAOrdenar Lista de notificaciones a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Notificacion> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}