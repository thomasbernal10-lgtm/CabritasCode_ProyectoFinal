package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import co.edu.unbosque.model.RegistroVisita;
import co.edu.unbosque.model.RegistroVisitaDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los registros de visita almacenados dentro del sistema GreenBuilding Manager.
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a las visitas registradas en el conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * RegistroVisita y RegistroVisitaDTO. La informacion es almacenada tanto en
 * archivos de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos RegistroVisitaDTO deben contener informacion valida
 * antes de ser registrados. <br>
 * <b>post</b> Los registros de visita quedan almacenados y disponibles para
 * procesos de consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroVisitaDAO implements DAO<RegistroVisita, RegistroVisitaDTO> {

	/**
	 * Lista que almacena los registros de visita registrados en el sistema.
	 */
	private ArrayList<RegistroVisita> listaRegistros;

	/**
	 * Ruta del archivo serializado de registros de visita.
	 */
	private final String URL_SERIALIZADO = "registrosvisita.dat";

	/**
	 * Ruta del archivo de texto de registros de visita.
	 */
	private final String URL_TEXTO = "registrosvisita.csv";

	/**
	 * Constructor por defecto de la clase RegistroVisitaDAO.
	 * 
	 * Inicializa la lista de registros de visita y carga la informacion almacenada
	 * en el archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de registros queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public RegistroVisitaDAO() {
		listaRegistros = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de registros de visita almacenados.
	 * 
	 * <b>pre</b> El objeto RegistroVisitaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos RegistroVisita registrados
	 */
	public ArrayList<RegistroVisita> getListaRegistros() {
		return listaRegistros;
	}

	/**
	 * Asigna una nueva lista de registros de visita.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaRegistros queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaRegistros Nueva lista de registros de visita
	 */
	public void setListaRegistros(ArrayList<RegistroVisita> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}

	/**
	 * Crea y registra un nuevo registro de visita en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo registro queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto RegistroVisitaDTO con la informacion del nuevo
	 *                  registro
	 */
	@Override
	public void crear(RegistroVisitaDTO nuevoDato) {
		listaRegistros.add(DataMapper.convertirRegistroVisitaDTOARegistroVisita(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un registro de visita de la lista segun su posicion.
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
	 * Actualiza la informacion de un registro de visita existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El registro almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del registro a actualizar
	 * @param datoActualizado Nuevo objeto RegistroVisitaDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, RegistroVisitaDTO datoActualizado) {
		if (index < 0 || index >= listaRegistros.size()) {
			return false;
		} else {
			listaRegistros.set(index, DataMapper.convertirRegistroVisitaDTOARegistroVisita(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los registros de visita registrados en formato texto.
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

		for (RegistroVisita r : listaRegistros) {
			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un registro de visita especifico.
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
	 * @return Lista de objetos RegistroVisitaDTO
	 */
	@Override
	public ArrayList<RegistroVisitaDTO> mostrarTodo() {
		return DataMapper.convertirListaRegistroVisitaAListaRegistroVisitaDTO(listaRegistros);
	}

	/**
	 * Escribe la informacion de los registros de visita en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * registros. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (RegistroVisita r : listaRegistros) {

			sb.append(r.getId()).append(";");
			sb.append(r.getHoraEntrada()).append(";");
			sb.append(r.getHoraSalida()).append(";");
			sb.append(r.getVigilanteRegistro()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de registros de visita desde un archivo de texto.
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

			RegistroVisita nuevo = new RegistroVisita();

			nuevo.setId(columnas[0]);
			nuevo.setHoraEntrada(LocalDateTime.parse(columnas[1]));
			nuevo.setHoraSalida(LocalDateTime.parse(columnas[2]));
			nuevo.setVigilanteRegistro(columnas[3]);

			listaRegistros.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de registros de visita en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de registros debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los registros. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaRegistros);
	}

	/**
	 * Lee la informacion de registros de visita almacenada en un archivo
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
			listaRegistros = (ArrayList<RegistroVisita>) contenido;
		}
	}

	/**
	 * Busca registros de visita asociados a un visitante especifico.
	 * 
	 * <b>pre</b> El identificador del visitante no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los registros encontrados. <br>
	 * 
	 * @param idVisitante Identificador del visitante
	 * @return Lista de registros de visita asociados al visitante
	 */
	public ArrayList<RegistroVisita> buscarPorVisitante(String idVisitante) {

		ArrayList<RegistroVisita> resultado = new ArrayList<>();

		for (RegistroVisita r : listaRegistros) {

			if (r.getVisitante() != null && r.getVisitante().getId().equals(idVisitante)) {
				resultado.add(r);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de registros de visita.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de registros de visita a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<RegistroVisita> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}