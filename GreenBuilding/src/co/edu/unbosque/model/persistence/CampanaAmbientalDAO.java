package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import co.edu.unbosque.model.CampanaAmbiental;
import co.edu.unbosque.model.CampanaAmbientalDTO;

/**
 * Objeto de acceso a datos (DAO) para la entidad CampanaAmbiental. Gestiona
 * la persistencia de las campanas ambientales del conjunto residencial en
 * archivo serializado y en archivo de texto CSV. Implementa la interfaz
 * generica DAO con CampanaAmbiental como entidad de dominio y
 * CampanaAmbientalDTO como objeto de transferencia. <br>
 * <b>pre</b> La carpeta de archivos del sistema debe estar disponible para
 * lectura y escritura. <br>
 * <b>post</b> La lista de campanas queda cargada desde el archivo serializado
 * al instanciar el DAO. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class CampanaAmbientalDAO implements DAO<CampanaAmbiental, CampanaAmbientalDTO> {

	/** Lista en memoria de campanas ambientales gestionadas por este DAO. */
	private ArrayList<CampanaAmbiental> listaCampanas;

	/** Nombre del archivo serializado para persistencia binaria. */
	private final String URL_SERIALIZADO = "campanas.dat";

	/** Nombre del archivo CSV para persistencia en texto plano. */
	private final String URL_TEXTO = "campanas.csv";

	/**
	 * Constructor por defecto. Inicializa la lista de campanas y carga los datos
	 * desde el archivo serializado si existe. <br>
	 * <b>pre</b> La carpeta de archivos del sistema debe estar disponible. <br>
	 * <b>post</b> La lista de campanas queda inicializada con los datos
	 * persistidos, o vacia si no existe archivo previo. <br>
	 */
	public CampanaAmbientalDAO() {
		listaCampanas = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de campanas ambientales en memoria. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return ArrayList con todas las campanas en memoria
	 */
	public ArrayList<CampanaAmbiental> getListaCampanas() {
		return listaCampanas;
	}

	/**
	 * Asigna la lista de campanas ambientales en memoria. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> El atributo listaCampanas queda actualizado con la lista
	 * recibida. <br>
	 *
	 * @param listaCampanas Nueva lista de campanas. listaCampanas != null
	 */
	public void setListaCampanas(ArrayList<CampanaAmbiental> listaCampanas) {
		this.listaCampanas = listaCampanas;
	}

	/**
	 * Crea una nueva campana ambiental a partir del DTO recibido, la agrega a la
	 * lista y persiste los cambios en ambos formatos de archivo. <br>
	 * <b>pre</b> El DTO no debe ser null y debe tener datos validos. <br>
	 * <b>post</b> La campana queda agregada a la lista y persistida en archivo. <br>
	 *
	 * @param nuevoDato DTO con los datos de la nueva campana. nuevoDato != null
	 */
	@Override
	public void crear(CampanaAmbientalDTO nuevoDato) {
		listaCampanas.add(DataMapper.convertirCampanaDTOACampana(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina la campana ambiental en la posicion indicada y persiste los cambios.
	 * <br>
	 * <b>pre</b> El index debe ser un valor valido dentro del rango de la lista.
	 * <br>
	 * <b>post</b> La campana queda eliminada de la lista y los archivos quedan
	 * actualizados. <br>
	 *
	 * @param index Posicion de la campana a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa; false si el index es invalido
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaCampanas.size()) {
			return false;
		} else {
			listaCampanas.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la campana ambiental en la posicion indicada con los datos del DTO
	 * recibido y persiste los cambios. <br>
	 * <b>pre</b> El index debe ser un valor valido y el DTO no debe ser null. <br>
	 * <b>post</b> La campana queda actualizada en la lista y los archivos quedan
	 * sincronizados. <br>
	 *
	 * @param index          Posicion de la campana a actualizar. index >= 0
	 * @param datoActualizado DTO con los nuevos datos. datoActualizado != null
	 * @return true si la actualizacion fue exitosa; false si el index es invalido
	 */
	@Override
	public boolean actualizar(int index, CampanaAmbientalDTO datoActualizado) {
		if (index < 0 || index >= listaCampanas.size()) {
			return false;
		} else {
			listaCampanas.set(index, DataMapper.convertirCampanaDTOACampana(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna una representacion en texto de todas las campanas con su posicion en
	 * la lista. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el indice y toString de cada campana
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;
		for (CampanaAmbiental c : listaCampanas) {
			datos += posicion + " ";
			datos += c.toString() + "\n";
			posicion++;
		}
		return datos;
	}

	/**
	 * Retorna la representacion en texto de la campana en la posicion indicada. <br>
	 * <b>pre</b> El index debe ser un valor valido dentro del rango de la lista.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @param index Posicion de la campana. index >= 0
	 * @return String con el toString de la campana indicada
	 */
	@Override
	public String mostrar(int index) {
		return listaCampanas.get(index).toString();
	}

	/**
	 * Retorna la lista completa de campanas convertida a DTOs. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return ArrayList de CampanaAmbientalDTO con todas las campanas
	 */
	@Override
	public ArrayList<CampanaAmbientalDTO> mostrarTodo() {
		return DataMapper.convertirListaCampanaAListaCampanaDTO(listaCampanas);
	}

	/**
	 * Escribe la lista de campanas en el archivo CSV de texto plano. Cada campana
	 * se guarda en una fila con sus campos separados por punto y coma. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> El archivo CSV queda actualizado con el estado actual de la
	 * lista. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();
		for (CampanaAmbiental c : listaCampanas) {
			sb.append(c.getId()).append(";");
			sb.append(c.getNombre()).append(";");
			sb.append(c.getDescripcion()).append(";");
			sb.append(c.getTipo()).append(";");
			sb.append(c.getFechaInicio()).append(";");
			sb.append(c.getFechaFin()).append(";");
			sb.append(c.getMeta()).append(";");
			sb.append(c.getEstado()).append(";");
			sb.append(c.getResultados()).append("\n");
		}
		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee el archivo CSV de texto plano y carga las campanas en la lista en
	 * memoria. Si el archivo esta vacio o no existe, no realiza ninguna accion. <br>
	 * <b>pre</b> El archivo CSV debe existir o poder ser creado por el
	 * FileHandler. <br>
	 * <b>post</b> La lista queda poblada con las campanas leidas del archivo, o
	 * sin cambios si el archivo esta vacio. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);
		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;
		String[] filas = contenido.split("\n");
		for (String fila : filas) {
			String[] columnas = fila.split(";");
			CampanaAmbiental nuevo = new CampanaAmbiental();
			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setDescripcion(columnas[2]);
			nuevo.setTipo(columnas[3]);
			nuevo.setFechaInicio(LocalDate.parse(columnas[4]));
			nuevo.setFechaFin(LocalDate.parse(columnas[5]));
			nuevo.setMeta(columnas[6]);
			nuevo.setEstado(columnas[7]);
			nuevo.setResultados(columnas[8]);
			listaCampanas.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de campanas en el archivo serializado binario. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> El archivo serializado queda actualizado con el estado actual de
	 * la lista. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaCampanas);
	}

	/**
	 * Lee el archivo serializado binario y carga la lista de campanas en memoria.
	 * Si el archivo no existe o no puede ser leido, inicializa la lista vacia. <br>
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado por el
	 * FileHandler. <br>
	 * <b>post</b> La lista queda cargada desde el archivo, o inicializada vacia si
	 * no hay datos previos. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);
		if (contenido == null) {
			listaCampanas = new ArrayList<>();
		} else {
			listaCampanas = (ArrayList<CampanaAmbiental>) contenido;
		}
	}

	/**
	 * Busca y retorna todas las campanas que tienen el tipo indicado. <br>
	 * <b>pre</b> El parametro tipo no debe ser null ni vacio. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @param tipo Tipo de campana a filtrar. tipo != null, tipo != ""
	 * @return ArrayList con las campanas que tienen el tipo indicado
	 */
	public ArrayList<CampanaAmbiental> buscarPorTipo(String tipo) {
		ArrayList<CampanaAmbiental> resultado = new ArrayList<>();
		for (CampanaAmbiental c : listaCampanas)
			if (c.getTipo().equals(tipo))
				resultado.add(c);
		return resultado;
	}

	/**
	 * Ordena la lista de campanas ascendentemente. Metodo pendiente de
	 * implementacion. <br>
	 * <b>pre</b> El objeto CampanaAmbientalDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto en esta version. <br>
	 *
	 * @param listaAOrdenar Lista de campanas a ordenar. listaAOrdenar != null
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<CampanaAmbiental> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}
