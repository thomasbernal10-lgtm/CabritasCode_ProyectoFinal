package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.unbosque.model.Mascota;
import co.edu.unbosque.model.MascotaDTO;

/**
 * Clase DAO encargada de gestionar la persistencia de los objetos Mascota
 * dentro del sistema GreenBuilding Manager.
 * 
 * Permite realizar operaciones CRUD sobre la lista de mascotas, asi como la
 * lectura y escritura de archivos de texto y serializados relacionados con las
 * mascotas registradas en el conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones estandar de acceso a
 * datos. <br>
 * 
 * <b>pre</b> Los datos de las mascotas deben cumplir con la estructura definida
 * en las clases Mascota y MascotaDTO. <br>
 * <b>post</b> Las mascotas quedan almacenadas, actualizadas, eliminadas o
 * consultadas correctamente dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class MascotaDAO implements DAO<Mascota, MascotaDTO> {

	/**
	 * Lista que almacena las mascotas del sistema.
	 */
	private ArrayList<Mascota> listaMascotas;

	/**
	 * Ruta del archivo serializado de mascotas.
	 */
	private final String URL_SERIALIZADO = "mascotas.dat";

	/**
	 * Ruta del archivo de texto de mascotas.
	 */
	private final String URL_TEXTO = "mascotas.csv";

	/**
	 * Constructor por defecto de la clase MascotaDAO.
	 * 
	 * Inicializa la lista de mascotas y carga la informacion almacenada en el
	 * archivo serializado. <br>
	 * 
	 * <b>pre</b> El sistema de archivos debe estar disponible. <br>
	 * <b>post</b> La lista de mascotas queda inicializada. <br>
	 */
	public MascotaDAO() {
		listaMascotas = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de mascotas almacenadas.
	 * 
	 * <b>pre</b> El objeto MascotaDAO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de mascotas
	 */
	public ArrayList<Mascota> getListaMascotas() {
		return listaMascotas;
	}

	/**
	 * Asigna una nueva lista de mascotas.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> La lista de mascotas queda actualizada. <br>
	 * 
	 * @param listaMascotas Nueva lista de mascotas
	 */
	public void setListaMascotas(ArrayList<Mascota> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

	/**
	 * Crea una nueva mascota dentro del sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato no debe ser null. <br>
	 * <b>post</b> La mascota queda agregada y almacenada en los archivos. <br>
	 * 
	 * @param nuevoDato DTO con la informacion de la nueva mascota
	 */
	@Override
	public void crear(MascotaDTO nuevoDato) {
		listaMascotas.add(DataMapper.convertirMascotaDTOAMascota(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una mascota de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> La mascota es eliminada del sistema si el indice es valido. <br>
	 * 
	 * @param index Posicion de la mascota a eliminar
	 * @return true si la mascota fue eliminada, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaMascotas.size()) {
			return false;
		} else {
			listaMascotas.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza una mascota existente dentro del sistema.
	 * 
	 * <b>pre</b> El indice debe existir y el DTO no debe ser null. <br>
	 * <b>post</b> La mascota queda actualizada correctamente. <br>
	 * 
	 * @param index           Posicion de la mascota a actualizar
	 * @param datoActualizado DTO con los nuevos datos
	 * @return true si la mascota fue actualizada, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, MascotaDTO datoActualizado) {

		if (index < 0 || index >= listaMascotas.size()) {
			return false;
		} else {
			listaMascotas.set(index, DataMapper.convertirMascotaDTOAMascota(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las mascotas almacenadas en formato String.
	 * 
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las mascotas registradas
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Mascota m : listaMascotas) {
			datos += posicion + " ";
			datos += m.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna una mascota especifica segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica el objeto. <br>
	 * 
	 * @param index Posicion de la mascota
	 * @return Representacion en texto de la mascota
	 */
	@Override
	public String mostrar(int index) {
		return listaMascotas.get(index).toString();
	}

	/**
	 * Retorna todas las mascotas convertidas a DTO.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Lista de mascotas en formato DTO
	 */
	@Override
	public ArrayList<MascotaDTO> mostrarTodo() {
		return DataMapper.convertirListaMascotaAListaMascotaDTO(listaMascotas);
	}

	/**
	 * Escribe las mascotas en un archivo de texto CSV.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Los datos quedan almacenados en el archivo de texto. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Mascota m : listaMascotas) {
			sb.append(m.getId()).append(";");
			sb.append(m.getNombre()).append(";");
			sb.append(m.getEspecie()).append(";");
			sb.append(m.getRaza()).append(";");
			sb.append(m.getColor()).append(";");
			sb.append(m.getFechaVacunacion()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee las mascotas almacenadas en el archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o poder ser creado. <br>
	 * <b>post</b> Las mascotas leidas son agregadas a la lista. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank()) {
			return;
		}

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Mascota nuevo = new Mascota();

			nuevo.setId(columnas[0]);
			nuevo.setNombre(columnas[1]);
			nuevo.setEspecie(columnas[2]);
			nuevo.setRaza(columnas[3]);
			nuevo.setColor(columnas[4]);
			nuevo.setFechaVacunacion(LocalDate.parse(columnas[5]));

			listaMascotas.add(nuevo);
		}
	}

	/**
	 * Guarda la lista de mascotas en un archivo serializado.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaMascotas);
	}

	/**
	 * Lee la lista de mascotas desde el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder crearse. <br>
	 * <b>post</b> La lista queda cargada con los datos almacenados. <br>
	 */

	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaMascotas = new ArrayList<>();
		} else {
			listaMascotas = (ArrayList<Mascota>) contenido;
		}
	}

	/**
	 * Busca mascotas asociadas a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las mascotas encontradas. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de mascotas asociadas al apartamento
	 */
	public ArrayList<Mascota> buscarPorApartamento(String idApartamento) {

		ArrayList<Mascota> resultado = new ArrayList<>();

		for (Mascota m : listaMascotas) {

			if (m.getApartamento() != null && m.getApartamento().getId().equals(idApartamento)) {

				resultado.add(m);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de mascotas.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Actualmente no se realiza ninguna accion debido a que el metodo
	 * no esta implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de mascotas a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Mascota> listaAOrdenar) {
		// TODO Auto-generated method stub
	}

}