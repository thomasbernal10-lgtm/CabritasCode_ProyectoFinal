package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Parqueadero;
import co.edu.unbosque.model.ParqueaderoDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los parqueaderos registrados dentro del sistema GreenBuilding Manager.
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a los parqueaderos del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Parqueadero y ParqueaderoDTO. La informacion es almacenada tanto en archivos
 * de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ParqueaderoDTO deben contener informacion valida antes
 * de ser registrados. <br>
 * <b>post</b> Los parqueaderos quedan almacenados y disponibles para procesos
 * de consulta y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ParqueaderoDAO implements DAO<Parqueadero, ParqueaderoDTO> {

	/**
	 * Lista que almacena los parqueaderos registrados en el sistema.
	 */
	private ArrayList<Parqueadero> listaParqueaderos;

	/**
	 * Ruta del archivo serializado de parqueaderos.
	 */
	private final String URL_SERIALIZADO = "parqueaderos.dat";

	/**
	 * Ruta del archivo de texto de parqueaderos.
	 */
	private final String URL_TEXTO = "parqueaderos.csv";

	/**
	 * Constructor por defecto de la clase ParqueaderoDAO.
	 * 
	 * Inicializa la lista de parqueaderos y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de parqueaderos queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ParqueaderoDAO() {
		listaParqueaderos = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de parqueaderos almacenados.
	 * 
	 * <b>pre</b> El objeto ParqueaderoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Parqueadero registrados
	 */
	public ArrayList<Parqueadero> getListaParqueaderos() {
		return listaParqueaderos;
	}

	/**
	 * Asigna una nueva lista de parqueaderos.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaParqueaderos queda actualizado con la nueva
	 * lista. <br>
	 * 
	 * @param listaParqueaderos Nueva lista de parqueaderos
	 */
	public void setListaParqueaderos(ArrayList<Parqueadero> listaParqueaderos) {
		this.listaParqueaderos = listaParqueaderos;
	}

	/**
	 * Crea y registra un nuevo parqueadero en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo parqueadero queda agregado a la lista y almacenado en
	 * los archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ParqueaderoDTO con la informacion del nuevo
	 *                  parqueadero
	 */
	@Override
	public void crear(ParqueaderoDTO nuevoDato) {
		listaParqueaderos.add(DataMapper.convertirParqueaderoDTOAParqueadero(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un parqueadero de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de parqueaderos. <br>
	 * <b>post</b> El parqueadero es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del parqueadero a eliminar
	 * @return true si el parqueadero fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaParqueaderos.size()) {
			return false;
		} else {
			listaParqueaderos.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un parqueadero existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El parqueadero almacenado es reemplazado por la nueva informacion
	 * y los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del parqueadero a actualizar
	 * @param datoActualizado Nuevo objeto ParqueaderoDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ParqueaderoDTO datoActualizado) {
		if (index < 0 || index >= listaParqueaderos.size()) {
			return false;
		} else {
			listaParqueaderos.set(index, DataMapper.convertirParqueaderoDTOAParqueadero(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los parqueaderos registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de parqueaderos debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los parqueaderos registrados
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Parqueadero p : listaParqueaderos) {
			datos += posicion + " ";
			datos += p.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un parqueadero especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del parqueadero
	 * @return Representacion textual del parqueadero solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaParqueaderos.get(index).toString();
	}

	/**
	 * Retorna todos los parqueaderos convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de parqueaderos debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ParqueaderoDTO
	 */
	@Override
	public ArrayList<ParqueaderoDTO> mostrarTodo() {
		return DataMapper.convertirListaParqueaderoAListaParqueaderoDTO(listaParqueaderos);
	}

	/**
	 * Escribe la informacion de los parqueaderos en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de parqueaderos debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * parqueaderos. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Parqueadero p : listaParqueaderos) {

			sb.append(p.getId()).append(";");
			sb.append(p.getNumero()).append(";");
			sb.append(p.getTipo()).append(";");
			sb.append(p.getEstado()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de parqueaderos desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los parqueaderos leidos son agregados a la lista de parqueaderos.
	 * <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Parqueadero nuevo = new Parqueadero();

			nuevo.setId(columnas[0]);
			nuevo.setNumero(columnas[1]);
			nuevo.setTipo(columnas[2]);
			nuevo.setEstado(columnas[3]);

			listaParqueaderos.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de parqueaderos en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de parqueaderos debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los parqueaderos. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaParqueaderos);
	}

	/**
	 * Lee la informacion de parqueaderos almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de parqueaderos queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaParqueaderos = new ArrayList<>();
		} else {
			listaParqueaderos = (ArrayList<Parqueadero>) contenido;
		}
	}

	/**
	 * Busca los parqueaderos asociados a un conjunto especifico.
	 * 
	 * <b>pre</b> El identificador del conjunto no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los parqueaderos encontrados. <br>
	 * 
	 * @param idConjunto Identificador del conjunto
	 * @return Lista de parqueaderos asociados al conjunto
	 */
	public ArrayList<Parqueadero> buscarPorConjunto(String idConjunto) {

		ArrayList<Parqueadero> resultado = new ArrayList<>();

		for (Parqueadero p : listaParqueaderos) {

			if (p.getConjunto() != null && p.getConjunto().getId().equals(idConjunto)) {
				resultado.add(p);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de parqueaderos.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de parqueaderos a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Parqueadero> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}