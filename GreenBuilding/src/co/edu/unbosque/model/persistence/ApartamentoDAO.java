package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Apartamento;
import co.edu.unbosque.model.ApartamentoDTO;

/**
 * Clase DAO encargada de gestionar la persistencia de los apartamentos dentro
 * del sistema GreenBuilding Manager.
 * 
 * Esta clase permite realizar operaciones CRUD sobre los apartamentos,
 * administrar archivos serializados y de texto, asi como realizar busquedas
 * especificas relacionadas con torres y propietarios.
 * 
 * Implementa la interfaz DAO para garantizar la estructura basica de acceso a
 * datos.
 * 
 * <b>pre</b> Los archivos de persistencia deben existir o poder ser creados por
 * el sistema. <br>
 * <b>post</b> Los apartamentos quedan correctamente almacenados, actualizados,
 * eliminados y consultados dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ApartamentoDAO implements DAO<Apartamento, ApartamentoDTO> {

	/** Lista principal de apartamentos almacenados en memoria. */
	private ArrayList<Apartamento> listaApartamentos;

	/** Ruta del archivo serializado de apartamentos. */
	private final String URL_SERIALIZADO = "apartamentos.dat";

	/** Ruta del archivo de texto de apartamentos. */
	private final String URL_TEXTO = "apartamentos.csv";

	/**
	 * Constructor principal de la clase ApartamentoDAO.
	 * 
	 * Inicializa la lista de apartamentos y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado puede existir o no existir. <br>
	 * <b>post</b> La lista de apartamentos queda inicializada con los datos
	 * almacenados previamente. <br>
	 */
	public ApartamentoDAO() {
		listaApartamentos = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de apartamentos almacenados.
	 * 
	 * <b>pre</b> El objeto ApartamentoDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de apartamentos almacenados
	 */
	public ArrayList<Apartamento> getListaApartamentos() {
		return listaApartamentos;
	}

	/**
	 * Modifica la lista de apartamentos almacenados.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> La lista interna de apartamentos queda actualizada. <br>
	 * 
	 * @param listaApartamentos Nueva lista de apartamentos
	 */
	public void setListaApartamentos(ArrayList<Apartamento> listaApartamentos) {
		this.listaApartamentos = listaApartamentos;
	}

	/**
	 * Crea un nuevo apartamento dentro del sistema.
	 * 
	 * <b>pre</b> El objeto ApartamentoDTO recibido no debe ser null. <br>
	 * <b>post</b> El apartamento queda almacenado dentro de la lista y persistido
	 * en archivos. <br>
	 * 
	 * @param nuevoDato Nuevo apartamento a registrar
	 */
	@Override
	public void crear(ApartamentoDTO nuevoDato) {
		listaApartamentos.add(DataMapper.convertirApartamentoDTOAApartamento(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un apartamento de la lista.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> El apartamento es eliminado y los cambios quedan persistidos.
	 * <br>
	 * 
	 * @param index Posicion del apartamento a eliminar
	 * @return true si el apartamento fue eliminado correctamente
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaApartamentos.size()) {
			return false;
		} else {
			listaApartamentos.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza un apartamento existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto actualizado no debe ser null.
	 * <br>
	 * <b>post</b> El apartamento queda actualizado y persistido. <br>
	 * 
	 * @param index           Posicion del apartamento a actualizar
	 * @param datoActualizado Nuevo objeto actualizado
	 * @return true si la actualizacion fue exitosa
	 */
	@Override
	public boolean actualizar(int index, ApartamentoDTO datoActualizado) {
		if (index < 0 || index >= listaApartamentos.size()) {
			return false;
		} else {
			listaApartamentos.set(index, DataMapper.convertirApartamentoDTOAApartamento(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Muestra todos los apartamentos almacenados.
	 * 
	 * <b>pre</b> El objeto debe estar inicializado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los apartamentos registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Apartamento a : listaApartamentos) {
			datos += posicion + " ";
			datos += a.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Muestra un apartamento especifico segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del apartamento
	 * @return Informacion del apartamento solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaApartamentos.get(index).toString();
	}

	/**
	 * Retorna todos los apartamentos convertidos a DTO.
	 * 
	 * <b>pre</b> La lista debe encontrarse inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Lista de apartamentos en formato DTO
	 */
	@Override
	public ArrayList<ApartamentoDTO> mostrarTodo() {
		return DataMapper.convertirListaApartamentoAListaApartamentoDTO(listaApartamentos);
	}

	/**
	 * Escribe la informacion de apartamentos en el archivo de texto.
	 * 
	 * <b>pre</b> La lista debe encontrarse inicializada. <br>
	 * <b>post</b> La informacion queda almacenada en el archivo CSV. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Apartamento a : listaApartamentos) {
			sb.append(a.getId()).append(";");
			sb.append(a.getNumero()).append(";");
			sb.append(a.getPiso()).append(";");
			sb.append(a.getEstado()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion almacenada en el archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o encontrarse vacio. <br>
	 * <b>post</b> La lista de apartamentos queda cargada con la informacion del
	 * archivo. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Apartamento nuevo = new Apartamento();

			nuevo.setId(columnas[0]);
			nuevo.setNumero(columnas[1]);
			nuevo.setPiso(Integer.parseInt(columnas[2]));
			nuevo.setEstado(columnas[3]);

			listaApartamentos.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de apartamentos en un archivo serializado.
	 * 
	 * <b>pre</b> La lista debe encontrarse inicializada. <br>
	 * <b>post</b> La informacion queda serializada correctamente. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaApartamentos);
	}

	/**
	 * Lee la informacion almacenada en el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado puede existir o no existir. <br>
	 * <b>post</b> La lista queda cargada con la informacion almacenada. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaApartamentos = new ArrayList<>();
		} else {
			listaApartamentos = (ArrayList<Apartamento>) contenido;
		}
	}

	/**
	 * Busca apartamentos asociados a una torre especifica.
	 * 
	 * <b>pre</b> El id de la torre no debe ser null. <br>
	 * <b>post</b> Retorna una lista con los apartamentos encontrados. <br>
	 * 
	 * @param idTorre Identificador de la torre
	 * @return Lista de apartamentos asociados a la torre
	 */
	public ArrayList<Apartamento> buscarPorTorre(String idTorre) {

		ArrayList<Apartamento> resultado = new ArrayList<>();

		for (Apartamento a : listaApartamentos) {

			if (a.getTorre() != null && a.getTorre().getId().equals(idTorre)) {
				resultado.add(a);
			}
		}

		return resultado;
	}

	/**
	 * Busca apartamentos asociados a un propietario especifico.
	 * 
	 * <b>pre</b> El id del propietario no debe ser null. <br>
	 * <b>post</b> Retorna una lista con los apartamentos encontrados. <br>
	 * 
	 * @param idPropietario Identificador del propietario
	 * @return Lista de apartamentos asociados al propietario
	 */
	public ArrayList<Apartamento> buscarPorPropietario(String idPropietario) {

		ArrayList<Apartamento> resultado = new ArrayList<>();

		for (Apartamento a : listaApartamentos) {

			if (a.getPropietario() != null && a.getPropietario().getId().equals(idPropietario)) {
				resultado.add(a);
			}
		}

		return resultado;
	}

	/**
	 * Ordena la lista de apartamentos de manera ascendente.
	 * 
	 * <b>pre</b> La lista debe encontrarse inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente. <br>
	 * 
	 * @param listaAOrdenar Lista de apartamentos a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Apartamento> listaAOrdenar) {
		// TODO Auto-generated method stub
	}

	/**
	 * Busca un apartamento segun su identificador.
	 * 
	 * <b>pre</b> El id no debe ser null. <br>
	 * <b>post</b> Retorna el apartamento encontrado o null si no existe. <br>
	 * 
	 * @param id Identificador del apartamento
	 * @return Apartamento encontrado o null
	 */
	public Apartamento buscarPorId(String id) {

		for (Apartamento a : listaApartamentos) {

			if (a.getId().equals(id)) {
				return a;
			}
		}

		return null;
	}

	/**
	 * Busca un apartamento segun su numero.
	 * 
	 * <b>pre</b> El numero no debe ser null. <br>
	 * <b>post</b> Retorna el apartamento encontrado o null si no existe. <br>
	 * 
	 * @param numero Numero del apartamento
	 * @return Apartamento encontrado o null
	 */
	public Apartamento buscarPorNumero(String numero) {

		for (Apartamento a : listaApartamentos) {

			if (a.getNumero().equals(numero)) {
				return a;
			}
		}

		return null;
	}
}