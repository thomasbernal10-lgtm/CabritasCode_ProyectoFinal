package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Propietario;
import co.edu.unbosque.model.PropietarioDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los propietarios registrados dentro del sistema GreenBuilding Manager.
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a propietarios de apartamentos dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Propietario y PropietarioDTO. La informacion es almacenada tanto en archivos
 * de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos PropietarioDTO deben contener informacion valida antes
 * de ser registrados. <br>
 * <b>post</b> Los propietarios quedan almacenados y disponibles para procesos
 * de consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PropietarioDAO implements DAO<Propietario, PropietarioDTO> {

	/**
	 * Lista que almacena los propietarios registrados en el sistema.
	 */
	private ArrayList<Propietario> listaPropietarios;

	/**
	 * Ruta del archivo serializado de propietarios.
	 */
	private final String URL_SERIALIZADO = "propietarios.dat";

	/**
	 * Ruta del archivo de texto de propietarios.
	 */
	private final String URL_TEXTO = "propietarios.csv";

	/**
	 * Constructor por defecto de la clase PropietarioDAO.
	 * 
	 * Inicializa la lista de propietarios y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de propietarios queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public PropietarioDAO() {
		listaPropietarios = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de propietarios almacenados.
	 * 
	 * <b>pre</b> El objeto PropietarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Propietario registrados
	 */
	public ArrayList<Propietario> getListaPropietarios() {
		return listaPropietarios;
	}

	/**
	 * Asigna una nueva lista de propietarios.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaPropietarios queda actualizado con la nueva
	 * lista. <br>
	 * 
	 * @param listaPropietarios Nueva lista de propietarios
	 */
	public void setListaPropietarios(ArrayList<Propietario> listaPropietarios) {
		this.listaPropietarios = listaPropietarios;
	}

	/**
	 * Crea y registra un nuevo propietario en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo propietario queda agregado a la lista y almacenado en
	 * los archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto PropietarioDTO con la informacion del nuevo
	 *                  propietario
	 */
	@Override
	public void crear(PropietarioDTO nuevoDato) {
		listaPropietarios.add(DataMapper.convertirPropietarioDTOAPropietario(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un propietario de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de propietarios. <br>
	 * <b>post</b> El propietario es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del propietario a eliminar
	 * @return true si el propietario fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPropietarios.size()) {
			return false;
		} else {
			listaPropietarios.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un propietario existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El propietario almacenado es reemplazado por la nueva informacion
	 * y los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del propietario a actualizar
	 * @param datoActualizado Nuevo objeto PropietarioDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, PropietarioDTO datoActualizado) {
		if (index < 0 || index >= listaPropietarios.size()) {
			return false;
		} else {
			listaPropietarios.set(index, DataMapper.convertirPropietarioDTOAPropietario(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los propietarios registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de propietarios debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los propietarios registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Propietario p : listaPropietarios) {
			datos += posicion + " ";
			datos += p.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un propietario especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del propietario
	 * @return Representacion textual del propietario solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaPropietarios.get(index).toString();
	}

	/**
	 * Retorna todos los propietarios convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de propietarios debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos PropietarioDTO
	 */
	@Override
	public ArrayList<PropietarioDTO> mostrarTodo() {
		return DataMapper.convertirListaPropietarioAListaPropietarioDTO(listaPropietarios);
	}

	/**
	 * Escribe la informacion de los propietarios en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de propietarios debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * propietarios. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Propietario p : listaPropietarios) {

			sb.append(p.getId()).append(";");
			sb.append(p.getNombre()).append(";");
			sb.append(p.getCedula()).append(";");
			sb.append(p.getTelefono()).append(";");
			sb.append(p.getCorreo()).append(";");
			sb.append(p.getContactoEmergencia()).append(";");
			sb.append(p.getTelefonoEmergencia()).append(";");
			sb.append(p.getDireccionCorrespondencia()).append(";");
			sb.append(p.isEsResidente()).append(";");
			sb.append(p.isResponsablePago()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de propietarios desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los propietarios leidos son agregados a la lista de propietarios.
	 * <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Propietario nuevo = new Propietario();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setCedula(columnas[2]);
			nuevo.setTelefono(columnas[3]);
			nuevo.setCorreo(columnas[4]);
			nuevo.setContactoEmergencia(columnas[5]);
			nuevo.setTelefonoEmergencia(columnas[6]);
			nuevo.setDireccionCorrespondencia(columnas[7]);
			nuevo.setEsResidente(Boolean.parseBoolean(columnas[8]));
			nuevo.setResponsablePago(Boolean.parseBoolean(columnas[9]));

			listaPropietarios.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de propietarios en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de propietarios debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los propietarios. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaPropietarios);
	}

	/**
	 * Lee la informacion de propietarios almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de propietarios queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaPropietarios = new ArrayList<>();
		} else {
			listaPropietarios = (ArrayList<Propietario>) contenido;
		}
	}

	/**
	 * Busca un propietario segun su identificador.
	 * 
	 * <b>pre</b> El identificador no debe ser null. <br>
	 * <b>post</b> Se retorna el propietario encontrado o null si no existe. <br>
	 * 
	 * @param id Identificador del propietario
	 * @return Objeto Propietario encontrado o null si no existe
	 */
	public Propietario buscarPorId(String id) {

		for (Propietario p : listaPropietarios) {

			if (p.getId().equals(id)) {
				return p;
			}
		}

		return null;
	}

	/**
	 * Ordena ascendentemente la lista de propietarios.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de propietarios a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Propietario> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}