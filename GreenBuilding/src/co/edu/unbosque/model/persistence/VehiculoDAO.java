package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Vehiculo;
import co.edu.unbosque.model.VehiculoDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los vehiculos registrados dentro del sistema GreenBuilding Manager.
 * 
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a vehiculos autorizados dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Vehiculo y VehiculoDTO. La informacion es almacenada tanto en archivos de
 * texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos VehiculoDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Los vehiculos quedan almacenados y disponibles para procesos de
 * consulta, control y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class VehiculoDAO implements DAO<Vehiculo, VehiculoDTO> {

	/**
	 * Lista que almacena los vehiculos registrados en el sistema.
	 */
	private ArrayList<Vehiculo> listaVehiculos;

	/**
	 * Ruta del archivo serializado de vehiculos.
	 */
	private final String URL_SERIALIZADO = "vehiculos.dat";

	/**
	 * Ruta del archivo de texto de vehiculos.
	 */
	private final String URL_TEXTO = "vehiculos.csv";

	/**
	 * Constructor por defecto de la clase VehiculoDAO.
	 * 
	 * Inicializa la lista de vehiculos y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de vehiculos queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public VehiculoDAO() {
		listaVehiculos = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de vehiculos almacenados.
	 * 
	 * <b>pre</b> El objeto VehiculoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Vehiculo registrados
	 */
	public ArrayList<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	/**
	 * Asigna una nueva lista de vehiculos.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaVehiculos queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaVehiculos Nueva lista de vehiculos
	 */
	public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	/**
	 * Crea y registra un nuevo vehiculo en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo vehiculo queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto VehiculoDTO con la informacion del nuevo vehiculo
	 */
	@Override
	public void crear(VehiculoDTO nuevoDato) {
		listaVehiculos.add(DataMapper.convertirVehiculoDTOAVehiculo(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un vehiculo de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de vehiculos. <br>
	 * <b>post</b> El vehiculo es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del vehiculo a eliminar
	 * @return true si el vehiculo fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaVehiculos.size()) {
			return false;
		} else {
			listaVehiculos.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un vehiculo existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El vehiculo almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del vehiculo a actualizar
	 * @param datoActualizado Nuevo objeto VehiculoDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, VehiculoDTO datoActualizado) {

		if (index < 0 || index >= listaVehiculos.size()) {
			return false;
		} else {
			listaVehiculos.set(index, DataMapper.convertirVehiculoDTOAVehiculo(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los vehiculos registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de vehiculos debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los vehiculos registrados
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Vehiculo v : listaVehiculos) {

			datos += posicion + " ";
			datos += v.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un vehiculo especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del vehiculo
	 * @return Representacion textual del vehiculo solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaVehiculos.get(index).toString();
	}

	/**
	 * Retorna todos los vehiculos convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de vehiculos debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos VehiculoDTO
	 */
	@Override
	public ArrayList<VehiculoDTO> mostrarTodo() {
		return DataMapper.convertirListaVehiculoAListaVehiculoDTO(listaVehiculos);
	}

	/**
	 * Escribe la informacion de los vehiculos en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de vehiculos debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * vehiculos. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Vehiculo v : listaVehiculos) {

			sb.append(v.getId()).append(";");
			sb.append(v.getPlaca()).append(";");
			sb.append(v.getTipo()).append(";");
			sb.append(v.getMarca()).append(";");
			sb.append(v.getModelo()).append(";");
			sb.append(v.getColor()).append(";");
			sb.append(v.isAutorizado()).append(";");
			sb.append(v.isTieneRestriccion()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de vehiculos desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los vehiculos leidos son agregados a la lista de vehiculos. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Vehiculo nuevo = new Vehiculo();

			nuevo.setId(columnas[0]);
			nuevo.setPlaca(columnas[1]);
			nuevo.setTipo(columnas[2]);
			nuevo.setMarca(columnas[3]);
			nuevo.setModelo(columnas[4]);
			nuevo.setColor(columnas[5]);
			nuevo.setAutorizado(Boolean.parseBoolean(columnas[6]));
			nuevo.setTieneRestriccion(Boolean.parseBoolean(columnas[7]));

			listaVehiculos.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de vehiculos en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de vehiculos debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los vehiculos. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaVehiculos);
	}

	/**
	 * Lee la informacion de vehiculos almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de vehiculos queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaVehiculos = new ArrayList<>();
		} else {
			listaVehiculos = (ArrayList<Vehiculo>) contenido;
		}
	}

	/**
	 * Busca los vehiculos asociados a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los vehiculos encontrados. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de vehiculos asociados al apartamento
	 */
	public ArrayList<Vehiculo> buscarPorApartamento(String idApartamento) {

		ArrayList<Vehiculo> resultado = new ArrayList<>();

		for (Vehiculo v : listaVehiculos) {

			if (v.getApartamento() != null && v.getApartamento().getId().equals(idApartamento)) {
				resultado.add(v);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de vehiculos.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de vehiculos a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Vehiculo> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

	/**
	 * Busca un vehiculo segun su identificador.
	 * 
	 * <b>pre</b> El identificador no debe ser null. <br>
	 * <b>post</b> Se retorna el vehiculo encontrado o null si no existe. <br>
	 * 
	 * @param id Identificador del vehiculo
	 * @return Objeto Vehiculo encontrado o null si no existe
	 */
	public Vehiculo buscarPorId(String id) {

		for (Vehiculo v : listaVehiculos) {

			if (v.getId().equals(id)) {
				return v;
			}
		}

		return null;
	}

	/**
	 * Busca un vehiculo segun su placa.
	 * 
	 * <b>pre</b> La placa no debe ser null. <br>
	 * <b>post</b> Se retorna el vehiculo encontrado o null si no existe. <br>
	 * 
	 * @param placa Placa del vehiculo
	 * @return Objeto Vehiculo encontrado o null si no existe
	 */
	public Vehiculo buscarPorPlaca(String placa) {

		for (Vehiculo v : listaVehiculos) {

			if (v.getPlaca().equalsIgnoreCase(placa)) {
				return v;
			}
		}

		return null;
	}

}