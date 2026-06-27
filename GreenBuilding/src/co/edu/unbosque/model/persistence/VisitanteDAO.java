package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import co.edu.unbosque.model.Visitante;
import co.edu.unbosque.model.VisitanteDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los visitantes registrados dentro del sistema GreenBuilding Manager.
 * 
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a visitantes autorizados dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Visitante y VisitanteDTO. La informacion es almacenada tanto en archivos de
 * texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos VisitanteDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Los visitantes quedan almacenados y disponibles para procesos de
 * consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class VisitanteDAO implements DAO<Visitante, VisitanteDTO> {

	/**
	 * Lista que almacena los visitantes registrados en el sistema.
	 */
	private ArrayList<Visitante> listaVisitantes;

	/**
	 * Ruta del archivo serializado de visitantes.
	 */
	private final String URL_SERIALIZADO = "visitantes.dat";

	/**
	 * Ruta del archivo de texto de visitantes.
	 */
	private final String URL_TEXTO = "visitantes.csv";

	/**
	 * Constructor por defecto de la clase VisitanteDAO.
	 * 
	 * Inicializa la lista de visitantes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de visitantes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public VisitanteDAO() {
		listaVisitantes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de visitantes almacenados.
	 * 
	 * <b>pre</b> El objeto VisitanteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Visitante registrados
	 */
	public ArrayList<Visitante> getListaVisitantes() {
		return listaVisitantes;
	}

	/**
	 * Asigna una nueva lista de visitantes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaVisitantes queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaVisitantes Nueva lista de visitantes
	 */
	public void setListaVisitantes(ArrayList<Visitante> listaVisitantes) {
		this.listaVisitantes = listaVisitantes;
	}

	/**
	 * Crea y registra un nuevo visitante en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo visitante queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto VisitanteDTO con la informacion del nuevo visitante
	 */
	@Override
	public void crear(VisitanteDTO nuevoDato) {
		listaVisitantes.add(DataMapper.convertirVisitanteDTOAVisitante(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un visitante de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de visitantes. <br>
	 * <b>post</b> El visitante es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del visitante a eliminar
	 * @return true si el visitante fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaVisitantes.size()) {
			return false;
		} else {
			listaVisitantes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un visitante existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El visitante almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del visitante a actualizar
	 * @param datoActualizado Nuevo objeto VisitanteDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, VisitanteDTO datoActualizado) {

		if (index < 0 || index >= listaVisitantes.size()) {
			return false;
		} else {
			listaVisitantes.set(index, DataMapper.convertirVisitanteDTOAVisitante(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los visitantes registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de visitantes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los visitantes registrados
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Visitante v : listaVisitantes) {

			datos += posicion + " ";
			datos += v.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un visitante especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del visitante
	 * @return Representacion textual del visitante solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaVisitantes.get(index).toString();
	}

	/**
	 * Retorna todos los visitantes convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de visitantes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos VisitanteDTO
	 */
	@Override
	public ArrayList<VisitanteDTO> mostrarTodo() {
		return DataMapper.convertirListaVisitanteAListaVisitanteDTO(listaVisitantes);
	}

	/**
	 * Escribe la informacion de los visitantes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de visitantes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * visitantes. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Visitante v : listaVisitantes) {

			sb.append(v.getId()).append(";");
			sb.append(v.getNombre()).append(";");
			sb.append(v.getCedula()).append(";");
			sb.append(v.getTelefono()).append(";");
			sb.append(v.getTipo()).append(";");
			sb.append(v.getAutorizadoPor()).append(";");
			sb.append(v.getFechaAutorizacion()).append(";");
			sb.append(v.getFechaVencimiento()).append(";");
			sb.append(v.isActivo()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de visitantes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los visitantes leidos son agregados a la lista de visitantes.
	 * <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Visitante nuevo = new Visitante();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setCedula(columnas[2]);
			nuevo.setTelefono(columnas[3]);
			nuevo.setTipo(columnas[4]);
			nuevo.setAutorizadoPor(columnas[5]);
			nuevo.setFechaAutorizacion(LocalDateTime.parse(columnas[6]));
			nuevo.setFechaVencimiento(LocalDateTime.parse(columnas[7]));
			nuevo.setActivo(Boolean.parseBoolean(columnas[8]));

			listaVisitantes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de visitantes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de visitantes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los visitantes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaVisitantes);
	}

	/**
	 * Lee la informacion de visitantes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de visitantes queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaVisitantes = new ArrayList<>();
		} else {
			listaVisitantes = (ArrayList<Visitante>) contenido;
		}
	}

	/**
	 * Ordena ascendentemente la lista de visitantes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de visitantes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Visitante> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}