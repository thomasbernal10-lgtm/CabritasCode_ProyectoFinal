package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Apartamento;
import co.edu.unbosque.model.Residente;
import co.edu.unbosque.model.ResidenteDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los residentes registrados dentro del sistema GreenBuilding Manager. Permite
 * crear, actualizar, eliminar, consultar y almacenar informacion asociada a
 * residentes del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Residente y ResidenteDTO. La informacion es almacenada tanto en archivos de
 * texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ResidenteDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Los residentes quedan almacenados y disponibles para procesos de
 * consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ResidenteDAO implements DAO<Residente, ResidenteDTO> {

	/**
	 * Lista que almacena los residentes registrados en el sistema.
	 */
	private ArrayList<Residente> listaResidentes;

	/**
	 * Ruta del archivo serializado de residentes.
	 */
	private final String URL_SERIALIZADO = "residentes.dat";

	/**
	 * Ruta del archivo de texto de residentes.
	 */
	private final String URL_TEXTO = "residentes.csv";

	/**
	 * Constructor por defecto de la clase ResidenteDAO.
	 * 
	 * Inicializa la lista de residentes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de residentes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ResidenteDAO() {
		listaResidentes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de residentes almacenados.
	 * 
	 * <b>pre</b> El objeto ResidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Residente registrados
	 */
	public ArrayList<Residente> getListaResidentes() {
		return listaResidentes;
	}

	/**
	 * Asigna una nueva lista de residentes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaResidentes queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaResidentes Nueva lista de residentes
	 */
	public void setListaResidentes(ArrayList<Residente> listaResidentes) {
		this.listaResidentes = listaResidentes;
	}

	/**
	 * Crea y registra un nuevo residente en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo residente queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ResidenteDTO con la informacion del nuevo residente
	 */
	@Override
	public void crear(ResidenteDTO nuevoDato) {
		listaResidentes.add(DataMapper.convertirResidenteDTOAResidente(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un residente de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de residentes. <br>
	 * <b>post</b> El residente es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del residente a eliminar
	 * @return true si el residente fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaResidentes.size()) {
			return false;
		} else {
			listaResidentes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un residente existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El residente almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del residente a actualizar
	 * @param datoActualizado Nuevo objeto ResidenteDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ResidenteDTO datoActualizado) {
		if (index < 0 || index >= listaResidentes.size()) {
			return false;
		} else {
			listaResidentes.set(index, DataMapper.convertirResidenteDTOAResidente(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los residentes registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de residentes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los residentes registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Residente r : listaResidentes) {
			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un residente especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del residente
	 * @return Representacion textual del residente solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaResidentes.get(index).toString();
	}

	/**
	 * Retorna todos los residentes convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de residentes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ResidenteDTO
	 */
	@Override
	public ArrayList<ResidenteDTO> mostrarTodo() {
		return DataMapper.convertirListaResidenteAListaResidenteDTO(listaResidentes);
	}

	/**
	 * Escribe la informacion de los residentes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de residentes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * residentes. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Residente r : listaResidentes) {

			sb.append(r.getId()).append(";");
			sb.append(r.getNombre()).append(";");
			sb.append(r.getCedula()).append(";");
			sb.append(r.getTelefono()).append(";");
			sb.append(r.getCorreo()).append(";");
			sb.append(r.getContactoEmergencia()).append(";");
			sb.append(r.getTelefonoEmergencia()).append(";");
			sb.append(r.getTipo()).append(";");
			sb.append(r.isActivo()).append(";");
			if (r.getApartamento() != null) {
				sb.append(r.getApartamento().getId());
			} else {
				sb.append("");
			}
			sb.append("\n");

		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de residentes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los residentes leidos son agregados a la lista de residentes.
	 * <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Residente nuevo = new Residente();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setCedula(columnas[2]);
			nuevo.setTelefono(columnas[3]);
			nuevo.setCorreo(columnas[4]);
			nuevo.setContactoEmergencia(columnas[5]);
			nuevo.setTelefonoEmergencia(columnas[6]);
			nuevo.setTipo(columnas[7]);
			nuevo.setActivo(Boolean.parseBoolean(columnas[8]));
			if (columnas.length > 9 && !columnas[9].isBlank()) {
				Apartamento apto = new Apartamento();
				apto.setId(columnas[9]);
				nuevo.setApartamento(apto);
			}

			listaResidentes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de residentes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de residentes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los residentes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaResidentes);
	}

	/**
	 * Lee la informacion de residentes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de residentes queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaResidentes = new ArrayList<>();
		} else {
			listaResidentes = (ArrayList<Residente>) contenido;
		}
	}

	/**
	 * Busca residentes asociados a un apartamento.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los residentes encontrados. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de residentes asociados al apartamento
	 */
	public ArrayList<Residente> buscarPorApartamento(String idApartamento) {

		ArrayList<Residente> resultado = new ArrayList<>();

		for (Residente r : listaResidentes) {

			if (r.getApartamento() != null && r.getApartamento().getId().equals(idApartamento)) {
				resultado.add(r);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de residentes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de residentes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Residente> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}