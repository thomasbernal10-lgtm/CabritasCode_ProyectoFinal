package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import co.edu.unbosque.model.Paquete;
import co.edu.unbosque.model.PaqueteDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los paquetes registrados dentro del sistema GreenBuilding Manager. Permite
 * crear, actualizar, eliminar, consultar y almacenar informacion asociada a
 * paquetes recibidos y entregados en el conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Paquete y PaqueteDTO. La informacion es almacenada tanto en archivos de texto
 * como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos PaqueteDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Los paquetes quedan almacenados y disponibles para procesos de
 * consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PaqueteDAO implements DAO<Paquete, PaqueteDTO> {

	/**
	 * Lista que almacena los paquetes registrados en el sistema.
	 */
	private ArrayList<Paquete> listaPaquetes;

	/**
	 * Ruta del archivo serializado de paquetes.
	 */
	private final String URL_SERIALIZADO = "paquetes.dat";

	/**
	 * Ruta del archivo de texto de paquetes.
	 */
	private final String URL_TEXTO = "paquetes.csv";

	/**
	 * Constructor por defecto de la clase PaqueteDAO.
	 * 
	 * Inicializa la lista de paquetes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de paquetes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public PaqueteDAO() {
		listaPaquetes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de paquetes almacenados.
	 * 
	 * <b>pre</b> El objeto PaqueteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Paquete registrados
	 */
	public ArrayList<Paquete> getListaPaquetes() {
		return listaPaquetes;
	}

	/**
	 * Asigna una nueva lista de paquetes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaPaquetes queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaPaquetes Nueva lista de paquetes
	 */
	public void setListaPaquetes(ArrayList<Paquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}

	/**
	 * Crea y registra un nuevo paquete en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo paquete queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto PaqueteDTO con la informacion del nuevo paquete
	 */
	@Override
	public void crear(PaqueteDTO nuevoDato) {
		listaPaquetes.add(DataMapper.convertirPaqueteDTOAPaquete(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un paquete de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de paquetes. <br>
	 * <b>post</b> El paquete es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del paquete a eliminar
	 * @return true si el paquete fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPaquetes.size()) {
			return false;
		} else {
			listaPaquetes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un paquete existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El paquete almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del paquete a actualizar
	 * @param datoActualizado Nuevo objeto PaqueteDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, PaqueteDTO datoActualizado) {
		if (index < 0 || index >= listaPaquetes.size()) {
			return false;
		} else {
			listaPaquetes.set(index, DataMapper.convertirPaqueteDTOAPaquete(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los paquetes registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de paquetes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los paquetes registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Paquete p : listaPaquetes) {
			datos += posicion + " ";
			datos += p.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un paquete especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del paquete
	 * @return Representacion textual del paquete solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaPaquetes.get(index).toString();
	}

	/**
	 * Retorna todos los paquetes convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de paquetes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos PaqueteDTO
	 */
	@Override
	public ArrayList<PaqueteDTO> mostrarTodo() {
		return DataMapper.convertirListaPaqueteAListaPaqueteDTO(listaPaquetes);
	}

	/**
	 * Escribe la informacion de los paquetes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de paquetes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * paquetes. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();

		for (Paquete p : listaPaquetes) {
			sb.append(p.getId()).append(";");
			sb.append(p.getDescripcion()).append(";");
			sb.append(p.getRemitente()).append(";");
			sb.append(p.getFechaRecepcion()).append(";");
			sb.append(p.getVigilanteRecibio()).append(";");
			sb.append(p.getEstado()).append(";");

			if (p.getFechaEntrega() != null) {
				sb.append(p.getFechaEntrega()).append(";");
			} else {
				sb.append("null").append(";");
			}

			if (p.getPersonaRecibio() != null) {
				sb.append(p.getPersonaRecibio()).append("\n");
			} else {
				sb.append("null").append("\n");
			}
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de paquetes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los paquetes leidos son agregados a la lista de paquetes. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {
			String[] columnas = fila.split(";");

			Paquete nuevo = new Paquete();

			nuevo.setId(columnas[0]);
			nuevo.setDescripcion(columnas[1]);
			nuevo.setRemitente(columnas[2]);
			nuevo.setFechaRecepcion(LocalDateTime.parse(columnas[3]));
			nuevo.setVigilanteRecibio(columnas[4]);
			nuevo.setEstado(columnas[5]);

			if (!columnas[6].equals("null")) {
				nuevo.setFechaEntrega(LocalDateTime.parse(columnas[6]));
			}

			if (!columnas[7].equals("null")) {
				nuevo.setPersonaRecibio(columnas[7]);
			}

			listaPaquetes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de paquetes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de paquetes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los paquetes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaPaquetes);
	}

	/**
	 * Lee la informacion de paquetes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de paquetes queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaPaquetes = new ArrayList<>();
		} else {
			listaPaquetes = (ArrayList<Paquete>) contenido;
		}
	}

	/**
	 * Busca los paquetes asociados a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los paquetes encontrados. <br>
	 * 
	 * @param idApartamento Identificador del apartamento destino
	 * @return Lista de paquetes asociados al apartamento
	 */
	public ArrayList<Paquete> buscarPorApartamento(String idApartamento) {
		ArrayList<Paquete> resultado = new ArrayList<>();

		for (Paquete p : listaPaquetes)
			if (p.getApartamentoDestino() != null && p.getApartamentoDestino().getId().equals(idApartamento))
				resultado.add(p);

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de paquetes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de paquetes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Paquete> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}