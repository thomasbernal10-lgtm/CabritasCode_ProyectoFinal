package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Conjunto;
import co.edu.unbosque.model.ConjuntoDTO;

/**
 * Clase DAO encargada de gestionar la persistencia de los objetos Conjunto
 * dentro del sistema GreenBuilding Manager. Permite realizar operaciones CRUD,
 * lectura y escritura de archivos de texto y serializados, asi como consultas
 * especificas sobre los conjuntos residenciales registrados.
 * 
 * Implementa la interfaz DAO para garantizar las operaciones basicas de acceso
 * y manipulacion de datos.
 * 
 * <b>pre</b> Los datos utilizados deben cumplir con la estructura definida en
 * los objetos Conjunto y ConjuntoDTO. <br>
 * <b>post</b> Los cambios realizados sobre la informacion quedan almacenados en
 * archivos de persistencia del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ConjuntoDAO implements DAO<Conjunto, ConjuntoDTO> {

	/**
	 * Lista de conjuntos almacenados en memoria.
	 */
	private ArrayList<Conjunto> listaConjuntos;

	/**
	 * Ruta del archivo serializado de conjuntos.
	 */
	private final String URL_SERIALIZADO = "conjuntos.dat";

	/**
	 * Ruta del archivo de texto de conjuntos.
	 */
	private final String URL_TEXTO = "conjuntos.csv";

	/**
	 * Constructor por defecto de la clase ConjuntoDAO.
	 * 
	 * Inicializa la lista de conjuntos y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> El sistema debe tener permisos de lectura sobre el archivo
	 * serializado. <br>
	 * <b>post</b> La lista de conjuntos queda inicializada con la informacion
	 * almacenada. <br>
	 */
	public ConjuntoDAO() {
		listaConjuntos = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de conjuntos almacenados.
	 * 
	 * <b>pre</b> El objeto ConjuntoDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de conjuntos registrados
	 */
	public ArrayList<Conjunto> getListaConjuntos() {
		return listaConjuntos;
	}

	/**
	 * Asigna una nueva lista de conjuntos.
	 * 
	 * <b>pre</b> listaConjuntos != null <br>
	 * <b>post</b> La lista interna queda actualizada. <br>
	 * 
	 * @param listaConjuntos Nueva lista de conjuntos
	 */
	public void setListaConjuntos(ArrayList<Conjunto> listaConjuntos) {
		this.listaConjuntos = listaConjuntos;
	}

	/**
	 * Crea un nuevo conjunto dentro del sistema.
	 * 
	 * <b>pre</b> nuevoDato != null <br>
	 * <b>post</b> El conjunto queda agregado y almacenado en los archivos de
	 * persistencia. <br>
	 * 
	 * @param nuevoDato DTO del conjunto a registrar
	 */
	@Override
	public void crear(ConjuntoDTO nuevoDato) {
		listaConjuntos.add(DataMapper.convertirConjuntoDTOAConjunto(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un conjunto de la lista segun su posicion.
	 * 
	 * <b>pre</b> index >= 0 && index < listaConjuntos.size() <br>
	 * <b>post</b> El conjunto es eliminado de la lista y de los archivos de
	 * persistencia. <br>
	 * 
	 * @param index Posicion del conjunto a eliminar
	 * @return true si el conjunto fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaConjuntos.size()) {
			return false;
		} else {
			listaConjuntos.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza un conjunto existente en una posicion determinada.
	 * 
	 * <b>pre</b> index valido dentro de la lista y datoActualizado != null <br>
	 * <b>post</b> El conjunto queda actualizado y almacenado en persistencia. <br>
	 * 
	 * @param index           Posicion del conjunto a actualizar
	 * @param datoActualizado Nuevo DTO con la informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ConjuntoDTO datoActualizado) {
		if (index < 0 || index >= listaConjuntos.size()) {
			return false;
		} else {
			listaConjuntos.set(index, DataMapper.convertirConjuntoDTOAConjunto(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna una representacion en texto de todos los conjuntos registrados.
	 * 
	 * <b>pre</b> El objeto ConjuntoDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return String con todos los conjuntos registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;
		for (Conjunto c : listaConjuntos) {
			datos += posicion + " ";
			datos += c.toString() + "\n";
			posicion++;
		}
		return datos;
	}

	/**
	 * Retorna la informacion de un conjunto especifico.
	 * 
	 * <b>pre</b> index valido dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del conjunto
	 * @return Representacion en texto del conjunto solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaConjuntos.get(index).toString();
	}

	/**
	 * Retorna todos los conjuntos en formato DTO.
	 * 
	 * <b>pre</b> El objeto ConjuntoDAO debe estar instanciado. <br>
	 * <b>post</b> Se genera una lista DTO equivalente a la lista de conjuntos. <br>
	 * 
	 * @return Lista de objetos ConjuntoDTO
	 */
	@Override
	public ArrayList<ConjuntoDTO> mostrarTodo() {
		return DataMapper.convertirListaConjuntoAListaConjuntoDTO(listaConjuntos);
	}

	/**
	 * Escribe la informacion de los conjuntos en un archivo CSV.
	 * 
	 * <b>pre</b> La lista de conjuntos debe estar inicializada. <br>
	 * <b>post</b> El archivo CSV queda actualizado con la informacion actual. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();
		for (Conjunto c : listaConjuntos) {
			sb.append(c.getId()).append(";");
			sb.append(c.getNombre()).append(";");
			sb.append(c.getDireccion()).append(";");
			sb.append(c.getCiudad()).append(";");
			sb.append(c.getTelefono()).append(";");
			sb.append(c.getCorreo()).append("\n");
		}
		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de conjuntos desde el archivo CSV.
	 * 
	 * <b>pre</b> El archivo CSV debe existir o estar vacio. <br>
	 * <b>post</b> La lista de conjuntos queda cargada con la informacion leida.
	 * <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);
		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {
			String[] columnas = fila.split(";");

			Conjunto nuevo = new Conjunto();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setDireccion(columnas[2]);
			nuevo.setCiudad(columnas[3]);
			nuevo.setTelefono(columnas[4]);
			nuevo.setCorreo(columnas[5]);

			listaConjuntos.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de conjuntos en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de conjuntos debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaConjuntos);
	}

	/**
	 * Lee la informacion de conjuntos desde el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado puede existir o no. <br>
	 * <b>post</b> La lista queda cargada con la informacion recuperada o vacia si
	 * no existe informacion previa. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaConjuntos = new ArrayList<>();
		} else {
			listaConjuntos = (ArrayList<Conjunto>) contenido;
		}
	}

	/**
	 * Metodo pendiente para ordenar la lista de conjuntos de forma ascendente.
	 * 
	 * <b>pre</b> listaAOrdenar != null <br>
	 * <b>post</b> Actualmente no realiza modificaciones. <br>
	 * 
	 * @param listaAOrdenar Lista de conjuntos a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Conjunto> listaAOrdenar) {
		// TODO Auto-generated method stub
	}

	/**
	 * Busca un conjunto segun su identificador.
	 * 
	 * <b>pre</b> id != null && id != "" <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param id Identificador del conjunto a buscar
	 * @return Objeto Conjunto encontrado o null si no existe
	 */
	public Conjunto buscarPorId(String id) {
		for (Conjunto c : listaConjuntos) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

}