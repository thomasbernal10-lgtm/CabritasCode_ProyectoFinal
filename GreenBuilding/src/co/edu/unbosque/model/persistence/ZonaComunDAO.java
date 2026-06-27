package co.edu.unbosque.model.persistence;

import java.time.LocalTime;
import java.util.ArrayList;
import co.edu.unbosque.model.ZonaComun;
import co.edu.unbosque.model.ZonaComunDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las zonas comunes registradas dentro del sistema GreenBuilding Manager.
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a las zonas comunes disponibles en el conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * ZonaComun y ZonaComunDTO. La informacion es almacenada tanto en archivos de
 * texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ZonaComunDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Las zonas comunes quedan almacenadas y disponibles para procesos
 * de consulta, administracion y reservas dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ZonaComunDAO implements DAO<ZonaComun, ZonaComunDTO> {

	/**
	 * Lista que almacena las zonas comunes registradas en el sistema.
	 */
	private ArrayList<ZonaComun> listaZonasComunes;

	/**
	 * Ruta del archivo serializado de zonas comunes.
	 */
	private final String URL_SERIALIZADO = "zonascomunes.dat";

	/**
	 * Ruta del archivo de texto de zonas comunes.
	 */
	private final String URL_TEXTO = "zonascomunes.csv";

	/**
	 * Constructor por defecto de la clase ZonaComunDAO.
	 * 
	 * Inicializa la lista de zonas comunes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de zonas comunes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ZonaComunDAO() {
		listaZonasComunes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de zonas comunes almacenadas.
	 * 
	 * <b>pre</b> El objeto ZonaComunDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ZonaComun registrados
	 */
	public ArrayList<ZonaComun> getListaZonasComunes() {
		return listaZonasComunes;
	}

	/**
	 * Asigna una nueva lista de zonas comunes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaZonasComunes queda actualizado con la nueva
	 * lista. <br>
	 * 
	 * @param listaZonasComunes Nueva lista de zonas comunes
	 */
	public void setListaZonasComunes(ArrayList<ZonaComun> listaZonasComunes) {
		this.listaZonasComunes = listaZonasComunes;
	}

	/**
	 * Crea y registra una nueva zona comun en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La nueva zona comun queda agregada a la lista y almacenada en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ZonaComunDTO con la informacion de la nueva zona
	 *                  comun
	 */
	@Override
	public void crear(ZonaComunDTO nuevoDato) {
		listaZonasComunes.add(DataMapper.convertirZonaComunDTOAZonaComun(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una zona comun de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de zonas comunes. <br>
	 * <b>post</b> La zona comun es eliminada de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion de la zona comun a eliminar
	 * @return true si la zona comun fue eliminada correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaZonasComunes.size()) {
			return false;
		} else {
			listaZonasComunes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de una zona comun existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> La zona comun almacenada es reemplazada por la nueva informacion
	 * y los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion de la zona comun a actualizar
	 * @param datoActualizado Nuevo objeto ZonaComunDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ZonaComunDTO datoActualizado) {
		if (index < 0 || index >= listaZonasComunes.size()) {
			return false;
		} else {
			listaZonasComunes.set(index, DataMapper.convertirZonaComunDTOAZonaComun(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las zonas comunes registradas en formato texto.
	 * 
	 * <b>pre</b> La lista de zonas comunes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las zonas comunes registradas
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (ZonaComun z : listaZonasComunes) {
			datos += posicion + " ";
			datos += z.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de una zona comun especifica.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion de la zona comun
	 * @return Representacion textual de la zona comun solicitada
	 */
	@Override
	public String mostrar(int index) {
		return listaZonasComunes.get(index).toString();
	}

	/**
	 * Retorna todas las zonas comunes convertidas a objetos DTO.
	 * 
	 * <b>pre</b> La lista de zonas comunes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ZonaComunDTO
	 */
	@Override
	public ArrayList<ZonaComunDTO> mostrarTodo() {
		return DataMapper.convertirListaZonaComunAListaZonaComunDTO(listaZonasComunes);
	}

	/**
	 * Escribe la informacion de las zonas comunes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de zonas comunes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de las
	 * zonas comunes. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (ZonaComun z : listaZonasComunes) {

			sb.append(z.getId()).append(";");
			sb.append(z.getNombre()).append(";");
			sb.append(z.getTipo()).append(";");
			sb.append(z.getEstado()).append(";");
			sb.append(z.getAforoMaximo()).append(";");
			sb.append(z.getCostoReserva()).append(";");
			sb.append(z.getHoraApertura()).append(";");
			sb.append(z.getHoraCierre()).append(";");
			sb.append(z.isRequiereReserva()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de zonas comunes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Las zonas comunes leidas son agregadas a la lista de zonas
	 * comunes. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			ZonaComun nuevo = new ZonaComun();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setTipo(columnas[2]);
			nuevo.setEstado(columnas[3]);
			nuevo.setAforoMaximo(Integer.parseInt(columnas[4]));
			nuevo.setCostoReserva(Double.parseDouble(columnas[5]));
			nuevo.setHoraApertura(LocalTime.parse(columnas[6]));
			nuevo.setHoraCierre(LocalTime.parse(columnas[7]));
			nuevo.setRequiereReserva(Boolean.parseBoolean(columnas[8]));

			listaZonasComunes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de zonas comunes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de zonas comunes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * las zonas comunes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaZonasComunes);
	}

	/**
	 * Lee la informacion de zonas comunes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de zonas comunes queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaZonasComunes = new ArrayList<>();
		} else {
			listaZonasComunes = (ArrayList<ZonaComun>) contenido;
		}
	}

	/**
	 * Busca las zonas comunes asociadas a un conjunto residencial.
	 * 
	 * <b>pre</b> El identificador del conjunto no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las zonas comunes encontradas. <br>
	 * 
	 * @param idConjunto Identificador del conjunto residencial
	 * @return Lista de zonas comunes asociadas al conjunto
	 */
	public ArrayList<ZonaComun> buscarPorConjunto(String idConjunto) {

		ArrayList<ZonaComun> resultado = new ArrayList<>();

		for (ZonaComun z : listaZonasComunes) {

			if (z.getConjunto() != null && z.getConjunto().getId().equals(idConjunto)) {
				resultado.add(z);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de zonas comunes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de zonas comunes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<ZonaComun> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

	/**
	 * Busca una zona comun segun su identificador.
	 * 
	 * <b>pre</b> El identificador no debe ser null. <br>
	 * <b>post</b> Se retorna la zona comun encontrada o null si no existe. <br>
	 * 
	 * @param id Identificador de la zona comun
	 * @return Objeto ZonaComun encontrado o null si no existe
	 */
	public ZonaComun buscarPorId(String id) {

		for (ZonaComun z : listaZonasComunes) {

			if (z.getId().equals(id)) {
				return z;
			}
		}

		return null;
	}

}