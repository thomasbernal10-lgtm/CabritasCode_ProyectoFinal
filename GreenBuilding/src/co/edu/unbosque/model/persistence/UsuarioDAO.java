package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD y la persistencia de
 * los objetos Usuario.
 * <p>
 * Permite crear, actualizar, eliminar, mostrar y almacenar informacion
 * relacionada con los usuarios del sistema GreenBuilding Manager. La
 * informacion se almacena en archivos de texto y archivos serializados.
 * </p>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class UsuarioDAO implements DAO<Usuario, UsuarioDTO> {

	/**
	 * Lista que almacena los usuarios registrados en el sistema.
	 */
	private ArrayList<Usuario> listaUsuarios;

	/**
	 * Ruta del archivo serializado de usuarios.
	 */
	private final String URL_SERIALIZADO = "usuarios.dat";

	/**
	 * Ruta del archivo de texto de usuarios.
	 */
	private final String URL_TEXTO = "usuarios.csv";

	/**
	 * Constructor por defecto de la clase UsuarioDAO.
	 * <p>
	 * Inicializa la lista de usuarios y carga la informacion almacenada en el
	 * archivo serializado.
	 * </p>
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de usuarios queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public UsuarioDAO() {
		listaUsuarios = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de usuarios registrados.
	 * 
	 * <b>pre</b> El objeto UsuarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Usuario registrados
	 */
	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Asigna una nueva lista de usuarios.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaUsuarios queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaUsuarios Nueva lista de usuarios
	 */
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Crea y registra un nuevo usuario en el sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo usuario queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto UsuarioDTO con la informacion del nuevo usuario
	 */
	@Override
	public void crear(UsuarioDTO nuevoDato) {
		listaUsuarios.add(DataMapper.convertirUsuarioDTOAUsuario(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un usuario de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de usuarios. <br>
	 * <b>post</b> El usuario es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del usuario a eliminar
	 * @return true si el usuario fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaUsuarios.size()) {
			return false;
		} else {
			listaUsuarios.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un usuario existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El usuario almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del usuario a actualizar
	 * @param datoActualizado Nuevo objeto UsuarioDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, UsuarioDTO datoActualizado) {

		if (index < 0 || index >= listaUsuarios.size()) {
			return false;
		} else {
			listaUsuarios.set(index, DataMapper.convertirUsuarioDTOAUsuario(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los usuarios registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de usuarios debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los usuarios registrados
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Usuario u : listaUsuarios) {

			datos += posicion + " ";
			datos += u.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un usuario especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del usuario
	 * @return Representacion textual del usuario solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaUsuarios.get(index).toString();
	}

	/**
	 * Retorna todos los usuarios convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de usuarios debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos UsuarioDTO
	 */
	@Override
	public ArrayList<UsuarioDTO> mostrarTodo() {
		return DataMapper.convertirListaUsuarioAListaUsuarioDTO(listaUsuarios);
	}

	/**
	 * Escribe la informacion de los usuarios en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de usuarios debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * usuarios. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Usuario u : listaUsuarios) {

			sb.append(u.getId()).append(";");
			sb.append(u.getUsername()).append(";");
			sb.append(u.getContrasena()).append(";");
			sb.append(u.getRol()).append(";");
			sb.append(u.isActivo()).append(";");
			sb.append(u.getIntentosFallidos()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de los usuarios desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los usuarios leidos son agregados a la lista de usuarios. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Usuario nuevo = new Usuario();

			nuevo.setId(columnas[0]);
			nuevo.setUsername(columnas[1]);
			nuevo.setContrasena(columnas[2]);
			nuevo.setRol(columnas[3]);
			nuevo.setActivo(Boolean.parseBoolean(columnas[4]));
			nuevo.setIntentosFallidos(Integer.parseInt(columnas[5]));

			listaUsuarios.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de usuarios en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de usuarios debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los usuarios. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaUsuarios);
	}

	/**
	 * Lee la informacion de los usuarios almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de usuarios queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaUsuarios = new ArrayList<>();
		} else {
			listaUsuarios = (ArrayList<Usuario>) contenido;
		}
	}

	/**
	 * Busca usuarios segun el rol asignado.
	 * 
	 * <b>pre</b> El rol no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los usuarios encontrados. <br>
	 * 
	 * @param rol Rol de los usuarios a buscar
	 * @return Lista de usuarios con el rol especificado
	 */
	public ArrayList<Usuario> buscarPorRol(String rol) {

		ArrayList<Usuario> resultado = new ArrayList<>();

		for (Usuario u : listaUsuarios)

			if (u.getRol().equals(rol))
				resultado.add(u);

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de usuarios.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de usuarios a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Usuario> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}