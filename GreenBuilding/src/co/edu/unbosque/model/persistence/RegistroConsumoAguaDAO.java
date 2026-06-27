package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import co.edu.unbosque.model.RegistroConsumoAgua;
import co.edu.unbosque.model.RegistroConsumoAguaDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los registros de consumo de agua almacenados dentro del sistema GreenBuilding
 * Manager. Permite crear, actualizar, eliminar, consultar y almacenar
 * informacion asociada al consumo de agua del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * RegistroConsumoAgua y RegistroConsumoAguaDTO. La informacion es almacenada
 * tanto en archivos de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos RegistroConsumoAguaDTO deben contener informacion valida
 * antes de ser registrados. <br>
 * <b>post</b> Los registros de consumo de agua quedan almacenados y disponibles
 * para procesos de consulta, seguimiento y administracion dentro del sistema.
 * <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroConsumoAguaDAO implements DAO<RegistroConsumoAgua, RegistroConsumoAguaDTO> {

	/**
	 * Lista que almacena los registros de consumo de agua registrados en el
	 * sistema.
	 */
	private ArrayList<RegistroConsumoAgua> listaRegistros;

	/**
	 * Ruta del archivo serializado de registros de agua.
	 */
	private final String URL_SERIALIZADO = "consumoagua.dat";

	/**
	 * Ruta del archivo de texto de registros de agua.
	 */
	private final String URL_TEXTO = "consumoagua.csv";

	/**
	 * Constructor por defecto de la clase RegistroConsumoAguaDAO.
	 * 
	 * Inicializa la lista de registros de consumo de agua y carga la informacion
	 * almacenada en el archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de registros queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public RegistroConsumoAguaDAO() {
		listaRegistros = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de registros de consumo de agua almacenados.
	 * 
	 * <b>pre</b> El objeto RegistroConsumoAguaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos RegistroConsumoAgua registrados
	 */
	public ArrayList<RegistroConsumoAgua> getListaRegistros() {
		return listaRegistros;
	}

	/**
	 * Asigna una nueva lista de registros de consumo de agua.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaRegistros queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaRegistros Nueva lista de registros de consumo de agua
	 */
	public void setListaRegistros(ArrayList<RegistroConsumoAgua> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}

	/**
	 * Crea y registra un nuevo registro de consumo de agua en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo registro queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto RegistroConsumoAguaDTO con la informacion del nuevo
	 *                  registro
	 */
	@Override
	public void crear(RegistroConsumoAguaDTO nuevoDato) {
		listaRegistros.add(DataMapper.convertirAguaDTOAAgua(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un registro de consumo de agua de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de registros. <br>
	 * <b>post</b> El registro es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del registro a eliminar
	 * @return true si el registro fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaRegistros.size()) {
			return false;
		} else {
			listaRegistros.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un registro de consumo de agua existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El registro almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del registro a actualizar
	 * @param datoActualizado Nuevo objeto RegistroConsumoAguaDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, RegistroConsumoAguaDTO datoActualizado) {

		if (index < 0 || index >= listaRegistros.size()) {
			return false;
		} else {
			listaRegistros.set(index, DataMapper.convertirAguaDTOAAgua(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los registros de consumo de agua registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los registros registrados
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (RegistroConsumoAgua r : listaRegistros) {

			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un registro de consumo de agua especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del registro
	 * @return Representacion textual del registro solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaRegistros.get(index).toString();
	}

	/**
	 * Retorna todos los registros convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos RegistroConsumoAguaDTO
	 */
	@Override
	public ArrayList<RegistroConsumoAguaDTO> mostrarTodo() {
		return DataMapper.convertirListaAguaAListaAguaDTO(listaRegistros);
	}

	/**
	 * Escribe la informacion de los registros de consumo de agua en un archivo de
	 * texto.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * registros. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (RegistroConsumoAgua r : listaRegistros) {

			sb.append(r.getId()).append(";");
			sb.append(r.getPeriodo()).append(";");
			sb.append(r.getFechaRegistro()).append(";");
			sb.append(r.getRegistradoPor()).append(";");
			sb.append(r.getConsumoMtCubico()).append(";");
			sb.append(r.getAguaLluviaMtCubico()).append(";");
			sb.append(r.getConsumoNetoMtCubico()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de registros de consumo de agua desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los registros leidos son agregados a la lista de registros. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			RegistroConsumoAgua nuevo = new RegistroConsumoAgua();

			nuevo.setId(columnas[0]);
			nuevo.setPeriodo(columnas[1]);
			nuevo.setFechaRegistro(LocalDate.parse(columnas[2]));
			nuevo.setRegistradoPor(columnas[3]);
			nuevo.setConsumoMtCubico(Double.parseDouble(columnas[4]));
			nuevo.setAguaLluviaMtCubico(Double.parseDouble(columnas[5]));
			nuevo.setConsumoNetoMtCubico(Double.parseDouble(columnas[6]));

			listaRegistros.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de registros de consumo de agua en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los registros. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaRegistros);
	}

	/**
	 * Lee la informacion de registros de consumo de agua almacenada en un archivo
	 * serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de registros queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaRegistros = new ArrayList<>();
		} else {
			listaRegistros = (ArrayList<RegistroConsumoAgua>) contenido;
		}
	}

	/**
	 * Ordena ascendentemente la lista de registros de consumo de agua.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de registros de consumo de agua a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<RegistroConsumoAgua> listaAOrdenar) {
		// TODO Auto-generated method stub

	}
}