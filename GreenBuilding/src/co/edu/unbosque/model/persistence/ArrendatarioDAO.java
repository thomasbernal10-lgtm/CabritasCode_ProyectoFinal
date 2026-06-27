package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.unbosque.model.Arrendatario;
import co.edu.unbosque.model.ArrendatarioDTO;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD de los arrendatarios
 * dentro del sistema GreenBuilding Manager.
 * 
 * Esta clase permite crear, actualizar, eliminar, consultar y persistir la
 * informacion relacionada con los arrendatarios tanto en archivos de texto como
 * serializados.
 * 
 * Implementa la interfaz DAO para garantizar el manejo estandarizado de datos.
 * <b>pre</b> Los objetos ArrendatarioDTO deben contener informacion valida
 * antes de ser almacenados. <br>
 * <b>post</b> Los datos de los arrendatarios quedan almacenados y disponibles
 * para consulta y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ArrendatarioDAO implements DAO<Arrendatario, ArrendatarioDTO> {

	/**
	 * Lista que almacena los arrendatarios del sistema.
	 */
	private ArrayList<Arrendatario> listaArrendatarios;

	/**
	 * Ruta del archivo serializado de arrendatarios.
	 */
	private final String URL_SERIALIZADO = "arrendatarios.dat";

	/**
	 * Ruta del archivo de texto de arrendatarios.
	 */
	private final String URL_TEXTO = "arrendatarios.csv";

	/**
	 * Constructor por defecto de la clase ArrendatarioDAO.
	 * 
	 * <b>pre</b> No existen precondiciones. <br>
	 * <b>post</b> Se inicializa la lista de arrendatarios y se cargan los datos
	 * almacenados en el archivo serializado. <br>
	 */
	public ArrendatarioDAO() {
		listaArrendatarios = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de arrendatarios almacenados.
	 * 
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de arrendatarios
	 */
	public ArrayList<Arrendatario> getListaArrendatarios() {
		return listaArrendatarios;
	}

	/**
	 * Asigna una nueva lista de arrendatarios.
	 * 
	 * <b>pre</b> listaArrendatarios != null <br>
	 * <b>post</b> La lista interna queda actualizada. <br>
	 * 
	 * @param listaArrendatarios Nueva lista de arrendatarios
	 */
	public void setListaArrendatarios(ArrayList<Arrendatario> listaArrendatarios) {
		this.listaArrendatarios = listaArrendatarios;
	}

	/**
	 * Crea un nuevo arrendatario dentro del sistema.
	 * 
	 * <b>pre</b> nuevoDato != null <br>
	 * <b>post</b> El arrendatario es agregado a la lista y almacenado en archivos.
	 * <br>
	 * 
	 * @param nuevoDato DTO con la informacion del nuevo arrendatario
	 */
	@Override
	public void crear(ArrendatarioDTO nuevoDato) {
		listaArrendatarios.add(DataMapper.convertirArrendatarioDTOAArrendatario(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un arrendatario segun su posicion en la lista.
	 * 
	 * <b>pre</b> index debe existir dentro de la lista. <br>
	 * <b>post</b> El arrendatario es eliminado y los cambios son persistidos. <br>
	 * 
	 * @param index Posicion del arrendatario a eliminar
	 * @return true si fue eliminado correctamente, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaArrendatarios.size()) {
			return false;
		} else {
			listaArrendatarios.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un arrendatario existente.
	 * 
	 * <b>pre</b> index valido y datoActualizado != null <br>
	 * <b>post</b> El arrendatario queda actualizado y almacenado en archivos. <br>
	 * 
	 * @param index           Posicion del arrendatario a actualizar
	 * @param datoActualizado Nueva informacion del arrendatario
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ArrendatarioDTO datoActualizado) {
		if (index < 0 || index >= listaArrendatarios.size()) {
			return false;
		} else {
			listaArrendatarios.set(index, DataMapper.convertirArrendatarioDTOAArrendatario(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Muestra todos los arrendatarios registrados en formato texto.
	 * 
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return String con todos los arrendatarios registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Arrendatario a : listaArrendatarios) {
			datos += posicion + " ";
			datos += a.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Muestra un arrendatario segun su posicion.
	 * 
	 * <b>pre</b> index valido dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del arrendatario
	 * @return Representacion en texto del arrendatario
	 */
	@Override
	public String mostrar(int index) {
		return listaArrendatarios.get(index).toString();
	}

	/**
	 * Retorna todos los arrendatarios convertidos a DTO.
	 * 
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Lista de ArrendatarioDTO
	 */
	@Override
	public ArrayList<ArrendatarioDTO> mostrarTodo() {
		return DataMapper.convertirListaArrendatarioAListaArrendatarioDTO(listaArrendatarios);
	}

	/**
	 * Escribe la informacion de los arrendatarios en un archivo de texto.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> La informacion queda almacenada en el archivo CSV. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();

		for (Arrendatario a : listaArrendatarios) {
			sb.append(a.getId()).append(";");
			sb.append(a.getNombre()).append(";");
			sb.append(a.getCedula()).append(";");
			sb.append(a.getTelefono()).append(";");
			sb.append(a.getCorreo()).append(";");
			sb.append(a.getContactoEmergencia()).append(";");
			sb.append(a.getTelefonoEmergencia()).append(";");
			sb.append(a.getFechaInicioContrato()).append(";");
			sb.append(a.getFechaFinContrato()).append(";");
			sb.append(a.isActivo()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de los arrendatarios desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o estar vacio. <br>
	 * <b>post</b> La lista queda cargada con la informacion encontrada. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Arrendatario nuevo = new Arrendatario();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setCedula(columnas[2]);
			nuevo.setTelefono(columnas[3]);
			nuevo.setCorreo(columnas[4]);
			nuevo.setContactoEmergencia(columnas[5]);
			nuevo.setTelefonoEmergencia(columnas[6]);
			nuevo.setFechaInicioContrato(LocalDate.parse(columnas[7]));
			nuevo.setFechaFinContrato(LocalDate.parse(columnas[8]));
			nuevo.setActivo(Boolean.parseBoolean(columnas[9]));

			listaArrendatarios.add(nuevo);
		}
	}

	/**
	 * Guarda la lista de arrendatarios en un archivo serializado.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> La informacion queda serializada correctamente. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaArrendatarios);
	}

	/**
	 * Lee la lista de arrendatarios desde un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado puede existir o no. <br>
	 * <b>post</b> La lista queda cargada con la informacion recuperada o vacia si
	 * no existe informacion previa. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaArrendatarios = new ArrayList<>();
		} else {
			listaArrendatarios = (ArrayList<Arrendatario>) contenido;
		}
	}

	/**
	 * Ordena ascendentemente la lista de arrendatarios.
	 * 
	 * <b>pre</b> listaAOrdenar != null <br>
	 * <b>post</b> Actualmente el metodo no contiene implementacion. <br>
	 * 
	 * @param listaAOrdenar Lista de arrendatarios a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Arrendatario> listaAOrdenar) {
		// TODO Auto-generated method stub
	}
}