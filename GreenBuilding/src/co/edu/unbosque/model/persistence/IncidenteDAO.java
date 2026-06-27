package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;

import co.edu.unbosque.model.Incidente;
import co.edu.unbosque.model.IncidenteDTO;

/**
 * Clase DAO encargada de gestionar la persistencia de los objetos Incidente
 * dentro del sistema GreenBuilding Manager.
 * 
 * Permite realizar operaciones CRUD sobre la lista de incidentes, asi como la
 * lectura y escritura de archivos de texto y serializados relacionados con los
 * incidentes registrados en el conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones estandar de acceso a
 * datos. <br>
 * 
 * <b>pre</b> Los datos de incidentes deben cumplir con la estructura definida
 * en las clases Incidente e IncidenteDTO. <br>
 * <b>post</b> Los incidentes quedan almacenados, actualizados, eliminados o
 * consultados correctamente dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class IncidenteDAO implements DAO<Incidente, IncidenteDTO> {

	/**
	 * Lista que almacena los incidentes del sistema.
	 */
	private ArrayList<Incidente> listaIncidentes;

	/**
	 * Ruta del archivo serializado de incidentes.
	 */
	private final String URL_SERIALIZADO = "incidentes.dat";

	/**
	 * Ruta del archivo de texto de incidentes.
	 */
	private final String URL_TEXTO = "incidentes.csv";

	/**
	 * Constructor por defecto de la clase IncidenteDAO.
	 * 
	 * Inicializa la lista de incidentes y carga la informacion almacenada en el
	 * archivo serializado. <br>
	 * 
	 * <b>pre</b> El sistema de archivos debe estar disponible. <br>
	 * <b>post</b> La lista de incidentes queda inicializada. <br>
	 */
	public IncidenteDAO() {
		listaIncidentes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de incidentes almacenados.
	 * 
	 * <b>pre</b> El objeto IncidenteDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de incidentes
	 */
	public ArrayList<Incidente> getListaIncidentes() {
		return listaIncidentes;
	}

	/**
	 * Asigna una nueva lista de incidentes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> La lista de incidentes queda actualizada. <br>
	 * 
	 * @param listaIncidentes Nueva lista de incidentes
	 */
	public void setListaIncidentes(ArrayList<Incidente> listaIncidentes) {
		this.listaIncidentes = listaIncidentes;
	}

	/**
	 * Crea un nuevo incidente dentro del sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato no debe ser null. <br>
	 * <b>post</b> El incidente queda agregado y almacenado en los archivos. <br>
	 * 
	 * @param nuevoDato DTO con la informacion del nuevo incidente
	 */
	@Override
	public void crear(IncidenteDTO nuevoDato) {
		listaIncidentes.add(DataMapper.convertirIncidenteDTOAIncidente(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un incidente de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> El incidente es eliminado del sistema si el indice es valido.
	 * <br>
	 * 
	 * @param index Posicion del incidente a eliminar
	 * @return true si el incidente fue eliminado, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaIncidentes.size()) {
			return false;
		} else {
			listaIncidentes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza un incidente existente dentro del sistema.
	 * 
	 * <b>pre</b> El indice debe existir y el DTO no debe ser null. <br>
	 * <b>post</b> El incidente queda actualizado correctamente. <br>
	 * 
	 * @param index           Posicion del incidente a actualizar
	 * @param datoActualizado DTO con los nuevos datos
	 * @return true si el incidente fue actualizado, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, IncidenteDTO datoActualizado) {
		if (index < 0 || index >= listaIncidentes.size()) {
			return false;
		} else {
			listaIncidentes.set(index, DataMapper.convertirIncidenteDTOAIncidente(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los incidentes almacenados en formato String.
	 * 
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los incidentes registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Incidente i : listaIncidentes) {
			datos += posicion + " ";
			datos += i.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna un incidente especifico segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica el objeto. <br>
	 * 
	 * @param index Posicion del incidente
	 * @return Representacion en texto del incidente
	 */
	@Override
	public String mostrar(int index) {
		return listaIncidentes.get(index).toString();
	}

	/**
	 * Retorna todos los incidentes convertidos a DTO.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Lista de incidentes en formato DTO
	 */
	@Override
	public ArrayList<IncidenteDTO> mostrarTodo() {
		return DataMapper.convertirListaIncidenteAListaIncidenteDTO(listaIncidentes);
	}

	/**
	 * Escribe los incidentes en un archivo de texto CSV.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Los datos quedan almacenados en el archivo de texto. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();

		for (Incidente i : listaIncidentes) {
			sb.append(i.getId()).append(";");
			sb.append(i.getTipo()).append(";");
			sb.append(i.getGravedad()).append(";");
			sb.append(i.getDescripcion()).append(";");
			sb.append(i.getReportadoPor()).append(";");
			sb.append(i.getFechaOcurrencia()).append(";");
			sb.append(i.getEstado()).append(";");
			sb.append(i.isGeneraMulta()).append(";");
			sb.append(i.getResolucion()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee los incidentes almacenados en el archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o poder ser creado. <br>
	 * <b>post</b> Los incidentes leidos son agregados a la lista. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank()) {
			return;
		}

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Incidente nuevo = new Incidente();

			nuevo.setId(columnas[0]);
			nuevo.setTipo(columnas[1]);
			nuevo.setGravedad(columnas[2]);
			nuevo.setDescripcion(columnas[3]);
			nuevo.setReportadoPor(columnas[4]);
			nuevo.setFechaOcurrencia(LocalDateTime.parse(columnas[5]));
			nuevo.setEstado(columnas[6]);
			nuevo.setGeneraMulta(Boolean.parseBoolean(columnas[7]));
			nuevo.setResolucion(columnas[8]);

			listaIncidentes.add(nuevo);
		}
	}

	/**
	 * Guarda la lista de incidentes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaIncidentes);
	}

	/**
	 * Lee la lista de incidentes desde el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder crearse. <br>
	 * <b>post</b> La lista queda cargada con los datos almacenados. <br>
	 */

	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaIncidentes = new ArrayList<>();
		} else {
			listaIncidentes = (ArrayList<Incidente>) contenido;
		}
	}

	/**
	 * Busca incidentes asociados a un apartamento especifico.
	 * 
	 * <b>pre</b> El id del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los incidentes encontrados. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de incidentes asociados al apartamento
	 */
	public ArrayList<Incidente> buscarPorApartamento(String idApartamento) {

		ArrayList<Incidente> resultado = new ArrayList<>();

		for (Incidente i : listaIncidentes) {

			if (i.getApartamentoInvolucrado() != null && i.getApartamentoInvolucrado().getId().equals(idApartamento)) {

				resultado.add(i);
			}
		}

		return resultado;
	}

	/**
	 * Busca incidentes segun su tipo.
	 * 
	 * <b>pre</b> El tipo no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los incidentes encontrados. <br>
	 * 
	 * @param tipo Tipo de incidente
	 * @return Lista de incidentes encontrados
	 */
	public ArrayList<Incidente> buscarPorTipo(String tipo) {

		ArrayList<Incidente> resultado = new ArrayList<>();

		for (Incidente i : listaIncidentes) {

			if (i.getTipo().equals(tipo)) {
				resultado.add(i);
			}
		}

		return resultado;
	}

	/**
	 * Busca incidentes segun su gravedad.
	 * 
	 * <b>pre</b> La gravedad no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los incidentes encontrados. <br>
	 * 
	 * @param gravedad Nivel de gravedad del incidente
	 * @return Lista de incidentes encontrados
	 */
	public ArrayList<Incidente> buscarPorGravedad(String gravedad) {

		ArrayList<Incidente> resultado = new ArrayList<>();

		for (Incidente i : listaIncidentes) {

			if (i.getGravedad().equals(gravedad)) {
				resultado.add(i);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de incidentes.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Actualmente no se realiza ninguna accion debido a que el metodo
	 * no esta implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de incidentes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Incidente> listaAOrdenar) {
		// TODO Auto-generated method stub
	}
}