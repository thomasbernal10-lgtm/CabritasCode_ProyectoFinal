package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Torre;
import co.edu.unbosque.model.TorreDTO;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD y la persistencia de
 * los objetos Torre.
 * <p>
 * Permite crear, actualizar, eliminar, mostrar y almacenar informacion
 * relacionada con las torres del conjunto residencial. La informacion se
 * almacena en archivos de texto y archivos serializados.
 * </p>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class TorreDAO implements DAO<Torre, TorreDTO> {

	/**
	 * Lista que almacena las torres registradas en el sistema.
	 */
	private ArrayList<Torre> listaTorres;

	/**
	 * Ruta del archivo serializado de torres.
	 */
	private final String URL_SERIALIZADO = "torres.dat";

	/**
	 * Ruta del archivo de texto de torres.
	 */
	private final String URL_TEXTO = "torres.csv";

	/**
	 * Constructor por defecto de la clase TorreDAO.
	 * <p>
	 * Inicializa la lista de torres y carga la informacion almacenada en el archivo
	 * serializado.
	 * </p>
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de torres queda inicializada con la informacion cargada
	 * desde persistencia. <br>
	 */
	public TorreDAO() {
		listaTorres = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de torres registradas.
	 * 
	 * <b>pre</b> El objeto TorreDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Torre registrados
	 */
	public ArrayList<Torre> getListaTorres() {
		return listaTorres;
	}

	/**
	 * Asigna una nueva lista de torres.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaTorres queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaTorres Nueva lista de torres
	 */
	public void setListaTorres(ArrayList<Torre> listaTorres) {
		this.listaTorres = listaTorres;
	}

	/**
	 * Crea y registra una nueva torre en el sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La nueva torre queda agregada a la lista y almacenada en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto TorreDTO con la informacion de la nueva torre
	 */
	@Override
	public void crear(TorreDTO nuevoDato) {
		listaTorres.add(DataMapper.convertirTorreDTOATorre(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una torre de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de torres. <br>
	 * <b>post</b> La torre es eliminada de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion de la torre a eliminar
	 * @return true si la torre fue eliminada correctamente, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaTorres.size()) {
			return false;
		} else {
			listaTorres.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de una torre existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> La torre almacenada es reemplazada por la nueva informacion y los
	 * archivos son actualizados. <br>
	 * 
	 * @param index           Posicion de la torre a actualizar
	 * @param datoActualizado Nuevo objeto TorreDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, TorreDTO datoActualizado) {

		if (index < 0 || index >= listaTorres.size()) {
			return false;
		} else {
			listaTorres.set(index, DataMapper.convertirTorreDTOATorre(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las torres registradas en formato texto.
	 * 
	 * <b>pre</b> La lista de torres debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las torres registradas
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Torre t : listaTorres) {

			datos += posicion + " ";
			datos += t.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de una torre especifica.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion de la torre
	 * @return Representacion textual de la torre solicitada
	 */
	@Override
	public String mostrar(int index) {
		return listaTorres.get(index).toString();
	}

	/**
	 * Retorna todas las torres convertidas a objetos DTO.
	 * 
	 * <b>pre</b> La lista de torres debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos TorreDTO
	 */
	@Override
	public ArrayList<TorreDTO> mostrarTodo() {
		return DataMapper.convertirListaTorreAListaTorreDTO(listaTorres);
	}

	/**
	 * Escribe la informacion de las torres en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de torres debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de las
	 * torres. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Torre t : listaTorres) {

			sb.append(t.getId()).append(";");
			sb.append(t.getNombre()).append(";");
			sb.append(t.getNumeroPisos()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de las torres desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Las torres leidas son agregadas a la lista de torres. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Torre nuevo = new Torre();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setNumeroPisos(Integer.parseInt(columnas[2]));

			listaTorres.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de torres en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de torres debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * las torres. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaTorres);
	}

	/**
	 * Lee la informacion de las torres almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de torres queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaTorres = new ArrayList<>();
		} else {
			listaTorres = (ArrayList<Torre>) contenido;
		}
	}

	/**
	 * Busca las torres asociadas a un conjunto residencial especifico.
	 * 
	 * <b>pre</b> El identificador del conjunto no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las torres encontradas. <br>
	 * 
	 * @param idConjunto Identificador del conjunto residencial
	 * @return Lista de torres asociadas al conjunto
	 */
	public ArrayList<Torre> buscarPorConjunto(String idConjunto) {

		ArrayList<Torre> resultado = new ArrayList<>();

		for (Torre t : listaTorres) {

			if (t.getConjunto() != null && t.getConjunto().getId().equals(idConjunto)) {
				resultado.add(t);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de torres.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de torres a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Torre> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

	/**
	 * Busca una torre segun su identificador.
	 * 
	 * <b>pre</b> El identificador no debe ser null. <br>
	 * <b>post</b> Se retorna la torre encontrada o null si no existe. <br>
	 * 
	 * @param id Identificador de la torre
	 * @return Objeto Torre encontrado o null si no existe
	 */
	public Torre buscarPorId(String id) {

		for (Torre t : listaTorres) {

			if (t.getId().equals(id)) {
				return t;
			}
		}

		return null;
	}
}