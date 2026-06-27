package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import co.edu.unbosque.model.RegistroReciclaje;
import co.edu.unbosque.model.RegistroReciclajeDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los registros de reciclaje almacenados dentro del sistema GreenBuilding
 * Manager. Permite crear, actualizar, eliminar, consultar y almacenar
 * informacion asociada a los procesos de reciclaje realizados en el conjunto
 * residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * RegistroReciclaje y RegistroReciclajeDTO. La informacion es almacenada tanto
 * en archivos de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos RegistroReciclajeDTO deben contener informacion valida
 * antes de ser registrados. <br>
 * <b>post</b> Los registros de reciclaje quedan almacenados y disponibles para
 * procesos de consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroReciclajeDAO implements DAO<RegistroReciclaje, RegistroReciclajeDTO> {

	/**
	 * Lista que almacena los registros de reciclaje registrados en el sistema.
	 */
	private ArrayList<RegistroReciclaje> listaRegistros;

	/**
	 * Ruta del archivo serializado de registros de reciclaje.
	 */
	private final String URL_SERIALIZADO = "reciclaje.dat";

	/**
	 * Ruta del archivo de texto de registros de reciclaje.
	 */
	private final String URL_TEXTO = "reciclaje.csv";

	/**
	 * Constructor por defecto de la clase RegistroReciclajeDAO.
	 * 
	 * Inicializa la lista de registros de reciclaje y carga la informacion
	 * almacenada en el archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de registros queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public RegistroReciclajeDAO() {
		listaRegistros = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de registros de reciclaje almacenados.
	 * 
	 * <b>pre</b> El objeto RegistroReciclajeDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos RegistroReciclaje registrados
	 */
	public ArrayList<RegistroReciclaje> getListaRegistros() {
		return listaRegistros;
	}

	/**
	 * Asigna una nueva lista de registros de reciclaje.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaRegistros queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaRegistros Nueva lista de registros de reciclaje
	 */
	public void setListaRegistros(ArrayList<RegistroReciclaje> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}

	/**
	 * Crea y registra un nuevo registro de reciclaje en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo registro queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto RegistroReciclajeDTO con la informacion del nuevo
	 *                  registro
	 */
	@Override
	public void crear(RegistroReciclajeDTO nuevoDato) {
		listaRegistros.add(DataMapper.convertirReciclajeDTOAReciclaje(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un registro de reciclaje de la lista segun su posicion.
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
	 * Actualiza la informacion de un registro de reciclaje existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El registro almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del registro a actualizar
	 * @param datoActualizado Nuevo objeto RegistroReciclajeDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, RegistroReciclajeDTO datoActualizado) {
		if (index < 0 || index >= listaRegistros.size()) {
			return false;
		} else {
			listaRegistros.set(index, DataMapper.convertirReciclajeDTOAReciclaje(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los registros de reciclaje registrados en formato texto.
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

		for (RegistroReciclaje r : listaRegistros) {
			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un registro de reciclaje especifico.
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
	 * @return Lista de objetos RegistroReciclajeDTO
	 */
	@Override
	public ArrayList<RegistroReciclajeDTO> mostrarTodo() {
		return DataMapper.convertirListaReciclajeAListaReciclajeDTO(listaRegistros);
	}

	/**
	 * Escribe la informacion de los registros de reciclaje en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * registros. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (RegistroReciclaje r : listaRegistros) {

			sb.append(r.getId()).append(";");
			sb.append(r.getPeriodo()).append(";");
			sb.append(r.getFechaRegistro()).append(";");
			sb.append(r.getRegistradoPor()).append(";");
			sb.append(r.getKgPapel()).append(";");
			sb.append(r.getKgPlastico()).append(";");
			sb.append(r.getKgVidrio()).append(";");
			sb.append(r.getKgMetal()).append(";");
			sb.append(r.getKgOrganico()).append(";");
			sb.append(r.getTotalKg()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de registros de reciclaje desde un archivo de texto.
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

			RegistroReciclaje nuevo = new RegistroReciclaje();

			nuevo.setId(columnas[0]);
			nuevo.setPeriodo(columnas[1]);
			nuevo.setFechaRegistro(LocalDate.parse(columnas[2]));
			nuevo.setRegistradoPor(columnas[3]);
			nuevo.setKgPapel(Double.parseDouble(columnas[4]));
			nuevo.setKgPlastico(Double.parseDouble(columnas[5]));
			nuevo.setKgVidrio(Double.parseDouble(columnas[6]));
			nuevo.setKgMetal(Double.parseDouble(columnas[7]));
			nuevo.setKgOrganico(Double.parseDouble(columnas[8]));
			nuevo.setTotalKg(Double.parseDouble(columnas[9]));

			listaRegistros.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de registros de reciclaje en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los registros. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaRegistros);
	}

	/**
	 * Lee la informacion de registros de reciclaje almacenada en un archivo
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
			listaRegistros = (ArrayList<RegistroReciclaje>) contenido;
		}
	}

	/**
	 * Ordena ascendentemente la lista de registros de reciclaje.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de registros de reciclaje a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<RegistroReciclaje> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}