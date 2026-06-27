package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import co.edu.unbosque.model.SolicitudMantenimiento;
import co.edu.unbosque.model.SolicitudMantenimientoDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las solicitudes de mantenimiento registradas dentro del sistema GreenBuilding
 * Manager. Permite crear, actualizar, eliminar, consultar y almacenar
 * informacion asociada a solicitudes de mantenimiento realizadas dentro del
 * conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * SolicitudMantenimiento y SolicitudMantenimientoDTO. La informacion es
 * almacenada tanto en archivos de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos SolicitudMantenimientoDTO deben contener informacion
 * valida antes de ser registrados. <br>
 * <b>post</b> Las solicitudes quedan almacenadas y disponibles para procesos de
 * consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class SolicitudMantenimientoDAO implements DAO<SolicitudMantenimiento, SolicitudMantenimientoDTO> {

	/**
	 * Lista que almacena las solicitudes de mantenimiento registradas en el
	 * sistema.
	 */
	private ArrayList<SolicitudMantenimiento> listaSolicitudes;

	/**
	 * Ruta del archivo serializado de solicitudes.
	 */
	private final String URL_SERIALIZADO = "solicitudes.dat";

	/**
	 * Ruta del archivo de texto de solicitudes.
	 */
	private final String URL_TEXTO = "solicitudes.csv";

	/**
	 * Constructor por defecto de la clase SolicitudMantenimientoDAO.
	 * 
	 * Inicializa la lista de solicitudes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de solicitudes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public SolicitudMantenimientoDAO() {
		listaSolicitudes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de solicitudes almacenadas.
	 * 
	 * <b>pre</b> El objeto SolicitudMantenimientoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos SolicitudMantenimiento registrados
	 */
	public ArrayList<SolicitudMantenimiento> getListaSolicitudes() {
		return listaSolicitudes;
	}

	/**
	 * Asigna una nueva lista de solicitudes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaSolicitudes queda actualizado con la nueva
	 * lista. <br>
	 * 
	 * @param listaSolicitudes Nueva lista de solicitudes
	 */
	public void setListaSolicitudes(ArrayList<SolicitudMantenimiento> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	/**
	 * Crea y registra una nueva solicitud de mantenimiento en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La nueva solicitud queda agregada a la lista y almacenada en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto SolicitudMantenimientoDTO con la informacion de la
	 *                  nueva solicitud
	 */
	@Override
	public void crear(SolicitudMantenimientoDTO nuevoDato) {
		listaSolicitudes.add(DataMapper.convertirSolicitudDTOASolicitud(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una solicitud de mantenimiento de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de solicitudes. <br>
	 * <b>post</b> La solicitud es eliminada de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion de la solicitud a eliminar
	 * @return true si la solicitud fue eliminada correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaSolicitudes.size()) {
			return false;
		} else {
			listaSolicitudes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de una solicitud existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> La solicitud almacenada es reemplazada por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion de la solicitud a actualizar
	 * @param datoActualizado Nuevo objeto SolicitudMantenimientoDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, SolicitudMantenimientoDTO datoActualizado) {
		if (index < 0 || index >= listaSolicitudes.size()) {
			return false;
		} else {
			listaSolicitudes.set(index, DataMapper.convertirSolicitudDTOASolicitud(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las solicitudes registradas en formato texto.
	 * 
	 * <b>pre</b> La lista de solicitudes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las solicitudes registradas
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (SolicitudMantenimiento s : listaSolicitudes) {
			datos += posicion + " ";
			datos += s.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de una solicitud especifica.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion de la solicitud
	 * @return Representacion textual de la solicitud solicitada
	 */
	@Override
	public String mostrar(int index) {
		return listaSolicitudes.get(index).toString();
	}

	/**
	 * Retorna todas las solicitudes convertidas a objetos DTO.
	 * 
	 * <b>pre</b> La lista de solicitudes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos SolicitudMantenimientoDTO
	 */
	@Override
	public ArrayList<SolicitudMantenimientoDTO> mostrarTodo() {
		return DataMapper.convertirListaSolicitudAListaSolicitudDTO(listaSolicitudes);
	}

	/**
	 * Escribe la informacion de las solicitudes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de solicitudes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de las
	 * solicitudes. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (SolicitudMantenimiento s : listaSolicitudes) {

			sb.append(s.getId()).append(";");
			sb.append(s.getDescripcion()).append(";");
			sb.append(s.getTipo()).append(";");
			sb.append(s.getPrioridad()).append(";");
			sb.append(s.getEstado()).append(";");

			if (s.getTecnicoAsignado() != null) {
				sb.append(s.getTecnicoAsignado()).append(";");
			} else {
				sb.append("null").append(";");
			}

			sb.append(s.getFechaRegistro()).append(";");
			sb.append(s.getFechaLimite()).append(";");

			if (s.getFechaCierre() != null) {
				sb.append(s.getFechaCierre()).append(";");
			} else {
				sb.append("null").append(";");
			}

			if (s.getObservaciones() != null) {
				sb.append(s.getObservaciones()).append("\n");
			} else {
				sb.append("null").append("\n");
			}
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de solicitudes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Las solicitudes leidas son agregadas a la lista de solicitudes.
	 * <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			SolicitudMantenimiento nuevo = new SolicitudMantenimiento();

			nuevo.setId(columnas[0]);
			nuevo.setDescripcion(columnas[1]);
			nuevo.setTipo(columnas[2]);
			nuevo.setPrioridad(columnas[3]);
			nuevo.setEstado(columnas[4]);

			if (!columnas[5].equals("null")) {
				nuevo.setTecnicoAsignado(columnas[5]);
			}

			nuevo.setFechaRegistro(LocalDateTime.parse(columnas[6]));
			nuevo.setFechaLimite(LocalDate.parse(columnas[7]));

			if (!columnas[8].equals("null")) {
				nuevo.setFechaCierre(LocalDateTime.parse(columnas[8]));
			}

			if (!columnas[9].equals("null")) {
				nuevo.setObservaciones(columnas[9]);
			}

			listaSolicitudes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de solicitudes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de solicitudes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * las solicitudes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaSolicitudes);
	}

	/**
	 * Lee la informacion de solicitudes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de solicitudes queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaSolicitudes = new ArrayList<>();
		} else {
			listaSolicitudes = (ArrayList<SolicitudMantenimiento>) contenido;
		}
	}

	/**
	 * Busca solicitudes segun su prioridad.
	 * 
	 * <b>pre</b> La prioridad no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las solicitudes encontradas. <br>
	 * 
	 * @param prioridad Nivel de prioridad de la solicitud
	 * @return Lista de solicitudes con la prioridad indicada
	 */
	public ArrayList<SolicitudMantenimiento> buscarPorPrioridad(String prioridad) {

		ArrayList<SolicitudMantenimiento> resultado = new ArrayList<>();

		for (SolicitudMantenimiento s : listaSolicitudes)

			if (s.getPrioridad().equals(prioridad))
				resultado.add(s);

		return resultado;
	}

	/**
	 * Busca solicitudes vencidas que no han sido completadas ni rechazadas.
	 * 
	 * <b>pre</b> La lista de solicitudes debe estar inicializada. <br>
	 * <b>post</b> Se retorna una lista con las solicitudes vencidas encontradas.
	 * <br>
	 * 
	 * @return Lista de solicitudes vencidas
	 */
	public ArrayList<SolicitudMantenimiento> buscarVencidas() {

		ArrayList<SolicitudMantenimiento> resultado = new ArrayList<>();

		for (SolicitudMantenimiento s : listaSolicitudes)

			if (s.getFechaLimite() != null && s.getFechaLimite().isBefore(LocalDate.now())
					&& !s.getEstado().equals("COMPLETADA") && !s.getEstado().equals("RECHAZADA"))
				resultado.add(s);

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de solicitudes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de solicitudes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<SolicitudMantenimiento> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}