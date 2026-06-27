package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import co.edu.unbosque.model.Participacion;
import co.edu.unbosque.model.ParticipacionDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las participaciones registradas dentro del sistema GreenBuilding Manager.
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a la participacion de residentes en campañas ambientales y
 * actividades del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Participacion y ParticipacionDTO. La informacion es almacenada tanto en
 * archivos de texto como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ParticipacionDTO deben contener informacion valida antes
 * de ser registrados. <br>
 * <b>post</b> Las participaciones quedan almacenadas y disponibles para
 * procesos de consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ParticipacionDAO implements DAO<Participacion, ParticipacionDTO> {

	/**
	 * Lista que almacena las participaciones registradas en el sistema.
	 */
	private ArrayList<Participacion> listaParticipaciones;

	/**
	 * Ruta del archivo serializado de participaciones.
	 */
	private final String URL_SERIALIZADO = "participaciones.dat";

	/**
	 * Ruta del archivo de texto de participaciones.
	 */
	private final String URL_TEXTO = "participaciones.csv";

	/**
	 * Constructor por defecto de la clase ParticipacionDAO.
	 * 
	 * Inicializa la lista de participaciones y carga la informacion almacenada en
	 * el archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de participaciones queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ParticipacionDAO() {
		listaParticipaciones = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de participaciones almacenadas.
	 * 
	 * <b>pre</b> El objeto ParticipacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Participacion registrados
	 */
	public ArrayList<Participacion> getListaParticipaciones() {
		return listaParticipaciones;
	}

	/**
	 * Asigna una nueva lista de participaciones.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaParticipaciones queda actualizado con la nueva
	 * lista. <br>
	 * 
	 * @param listaParticipaciones Nueva lista de participaciones
	 */
	public void setListaParticipaciones(ArrayList<Participacion> listaParticipaciones) {
		this.listaParticipaciones = listaParticipaciones;
	}

	/**
	 * Crea y registra una nueva participacion en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La nueva participacion queda agregada a la lista y almacenada en
	 * los archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ParticipacionDTO con la informacion de la nueva
	 *                  participacion
	 */
	@Override
	public void crear(ParticipacionDTO nuevoDato) {
		listaParticipaciones.add(DataMapper.convertirParticipacionDTOAParticipacion(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una participacion de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de participaciones. <br>
	 * <b>post</b> La participacion es eliminada de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion de la participacion a eliminar
	 * @return true si la participacion fue eliminada correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {

		if (index < 0 || index >= listaParticipaciones.size()) {
			return false;
		} else {
			listaParticipaciones.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de una participacion existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> La participacion almacenada es reemplazada por la nueva
	 * informacion y los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion de la participacion a actualizar
	 * @param datoActualizado Nuevo objeto ParticipacionDTO con informacion
	 *                        actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ParticipacionDTO datoActualizado) {

		if (index < 0 || index >= listaParticipaciones.size()) {
			return false;
		} else {
			listaParticipaciones.set(index, DataMapper.convertirParticipacionDTOAParticipacion(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las participaciones registradas en formato texto.
	 * 
	 * <b>pre</b> La lista de participaciones debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las participaciones registradas
	 */
	@Override
	public String mostrar() {

		String datos = "";
		int posicion = 0;

		for (Participacion p : listaParticipaciones) {

			datos += posicion + " ";
			datos += p.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de una participacion especifica.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion de la participacion
	 * @return Representacion textual de la participacion solicitada
	 */
	@Override
	public String mostrar(int index) {
		return listaParticipaciones.get(index).toString();
	}

	/**
	 * Retorna todas las participaciones convertidas a objetos DTO.
	 * 
	 * <b>pre</b> La lista de participaciones debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ParticipacionDTO
	 */
	@Override
	public ArrayList<ParticipacionDTO> mostrarTodo() {
		return DataMapper.convertirListaParticipacionAListaParticipacionDTO(listaParticipaciones);
	}

	/**
	 * Escribe la informacion de las participaciones en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de participaciones debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de las
	 * participaciones. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Participacion p : listaParticipaciones) {

			sb.append(p.getId()).append(";");
			sb.append(p.getFechaParticipacion()).append(";");
			sb.append(p.getObservacion()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de participaciones desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Las participaciones leidas son agregadas a la lista de
	 * participaciones. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Participacion nuevo = new Participacion();

			nuevo.setId(columnas[0]);
			nuevo.setFechaParticipacion(LocalDate.parse(columnas[1]));
			nuevo.setObservacion(columnas[2]);

			listaParticipaciones.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de participaciones en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de participaciones debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * las participaciones. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaParticipaciones);
	}

	/**
	 * Lee la informacion de participaciones almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de participaciones queda cargada con la informacion
	 * recuperada del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaParticipaciones = new ArrayList<>();
		} else {
			listaParticipaciones = (ArrayList<Participacion>) contenido;
		}
	}

	/**
	 * Busca las participaciones asociadas a una campaña especifica.
	 * 
	 * <b>pre</b> El identificador de la campaña no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las participaciones encontradas. <br>
	 * 
	 * @param idCampana Identificador de la campaña ambiental
	 * @return Lista de participaciones asociadas a la campaña
	 */
	public ArrayList<Participacion> buscarPorCampana(String idCampana) {

		ArrayList<Participacion> resultado = new ArrayList<>();

		for (Participacion p : listaParticipaciones) {

			if (p.getCampana() != null && p.getCampana().getId().equals(idCampana)) {
				resultado.add(p);
			}
		}

		return resultado;
	}

	/**
	 * Busca las participaciones asociadas a un residente especifico.
	 * 
	 * <b>pre</b> El identificador del residente no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las participaciones encontradas. <br>
	 * 
	 * @param idResidente Identificador del residente
	 * @return Lista de participaciones asociadas al residente
	 */
	public ArrayList<Participacion> buscarPorResidente(String idResidente) {

		ArrayList<Participacion> resultado = new ArrayList<>();

		for (Participacion p : listaParticipaciones) {

			if (p.getResidente() != null && p.getResidente().getId().equals(idResidente)) {
				resultado.add(p);
			}
		}

		return resultado;
	}

	/**
	 * Busca las participaciones asociadas a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las participaciones encontradas. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de participaciones asociadas al apartamento
	 */
	public ArrayList<Participacion> buscarPorApartamento(String idApartamento) {

		ArrayList<Participacion> resultado = new ArrayList<>();

		for (Participacion p : listaParticipaciones) {

			if (p.getApartamento() != null && p.getApartamento().getId().equals(idApartamento)) {
				resultado.add(p);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de participaciones.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de participaciones a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Participacion> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}