package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import co.edu.unbosque.model.Reserva;
import co.edu.unbosque.model.ReservaDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las reservas registradas dentro del sistema GreenBuilding Manager.
 * 
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a reservas de zonas comunes dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Reserva y ReservaDTO. La informacion es almacenada tanto en archivos de texto
 * como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ReservaDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Las reservas quedan almacenadas y disponibles para procesos de
 * consulta, seguimiento y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ReservaDAO implements DAO<Reserva, ReservaDTO> {

	/**
	 * Lista que almacena las reservas registradas en el sistema.
	 */
	private ArrayList<Reserva> listaReservas;

	/**
	 * Ruta del archivo serializado de reservas.
	 */
	private final String URL_SERIALIZADO = "reservas.dat";

	/**
	 * Ruta del archivo de texto de reservas.
	 */
	private final String URL_TEXTO = "reservas.csv";

	/**
	 * Constructor por defecto de la clase ReservaDAO.
	 * 
	 * Inicializa la lista de reservas y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de reservas queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ReservaDAO() {
		listaReservas = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de reservas almacenadas.
	 * 
	 * <b>pre</b> El objeto ReservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Reserva registrados
	 */
	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	/**
	 * Asigna una nueva lista de reservas.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaReservas queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaReservas Nueva lista de reservas
	 */
	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	/**
	 * Crea y registra una nueva reserva en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La nueva reserva queda agregada a la lista y almacenada en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ReservaDTO con la informacion de la nueva reserva
	 */
	@Override
	public void crear(ReservaDTO nuevoDato) {
		listaReservas.add(DataMapper.convertirReservaDTOAReserva(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una reserva de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de reservas. <br>
	 * <b>post</b> La reserva es eliminada de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion de la reserva a eliminar
	 * @return true si la reserva fue eliminada correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaReservas.size()) {
			return false;
		} else {
			listaReservas.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de una reserva existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> La reserva almacenada es reemplazada por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion de la reserva a actualizar
	 * @param datoActualizado Nuevo objeto ReservaDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ReservaDTO datoActualizado) {
		if (index < 0 || index >= listaReservas.size()) {
			return false;
		} else {
			listaReservas.set(index, DataMapper.convertirReservaDTOAReserva(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las reservas registradas en formato texto.
	 * 
	 * <b>pre</b> La lista de reservas debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todas las reservas registradas
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Reserva r : listaReservas) {
			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de una reserva especifica.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion de la reserva
	 * @return Representacion textual de la reserva solicitada
	 */
	@Override
	public String mostrar(int index) {
		return listaReservas.get(index).toString();
	}

	/**
	 * Retorna todas las reservas convertidas a objetos DTO.
	 * 
	 * <b>pre</b> La lista de reservas debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ReservaDTO
	 */
	@Override
	public ArrayList<ReservaDTO> mostrarTodo() {
		return DataMapper.convertirListaReservaAListaReservaDTO(listaReservas);
	}

	/**
	 * Escribe la informacion de las reservas en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de reservas debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de las
	 * reservas. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Reserva r : listaReservas) {

			sb.append(r.getId()).append(";");
			sb.append(r.getFecha()).append(";");
			sb.append(r.getHoraInicio()).append(";");
			sb.append(r.getHoraFin()).append(";");
			sb.append(r.getEstado()).append(";");
			sb.append(r.getFechaSolicitud()).append(";");

			if (r.getMotivoCancelacion() != null) {
				sb.append(r.getMotivoCancelacion()).append("\n");
			} else {
				sb.append("null").append("\n");
			}
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de reservas desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Las reservas leidas son agregadas a la lista de reservas. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Reserva nuevo = new Reserva();

			nuevo.setId(columnas[0]);
			nuevo.setFecha(LocalDate.parse(columnas[1]));
			nuevo.setHoraInicio(LocalTime.parse(columnas[2]));
			nuevo.setHoraFin(LocalTime.parse(columnas[3]));
			nuevo.setEstado(columnas[4]);
			nuevo.setFechaSolicitud(LocalDateTime.parse(columnas[5]));

			if (!columnas[6].equals("null")) {
				nuevo.setMotivoCancelacion(columnas[6]);
			}

			listaReservas.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de reservas en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de reservas debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * las reservas. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaReservas);
	}

	/**
	 * Lee la informacion de reservas almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de reservas queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaReservas = new ArrayList<>();
		} else {
			listaReservas = (ArrayList<Reserva>) contenido;
		}
	}

	/**
	 * Busca reservas segun su estado.
	 * 
	 * <b>pre</b> El estado no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las reservas encontradas. <br>
	 * 
	 * @param estado Estado de las reservas a buscar
	 * @return Lista de reservas que coinciden con el estado indicado
	 */
	public ArrayList<Reserva> buscarPorEstado(String estado) {

		ArrayList<Reserva> resultado = new ArrayList<>();

		for (Reserva r : listaReservas)

			if (r.getEstado().equals(estado))
				resultado.add(r);

		return resultado;
	}

	/**
	 * Busca reservas asociadas a un apartamento.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las reservas encontradas. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de reservas asociadas al apartamento
	 */
	public ArrayList<Reserva> buscarPorApartamento(String idApartamento) {

		ArrayList<Reserva> resultado = new ArrayList<>();

		for (Reserva r : listaReservas)

			if (r.getApartamento() != null && r.getApartamento().getId().equals(idApartamento))
				resultado.add(r);

		return resultado;
	}

	/**
	 * Busca reservas registradas en una fecha especifica.
	 * 
	 * <b>pre</b> La fecha no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las reservas encontradas. <br>
	 * 
	 * @param fecha Fecha de las reservas a buscar
	 * @return Lista de reservas registradas en la fecha indicada
	 */
	public ArrayList<Reserva> buscarPorFecha(LocalDate fecha) {

		ArrayList<Reserva> resultado = new ArrayList<>();

		for (Reserva r : listaReservas)

			if (r.getFecha() != null && r.getFecha().equals(fecha))
				resultado.add(r);

		return resultado;
	}

	/**
	 * Busca reservas asociadas a una zona comun.
	 * 
	 * <b>pre</b> El identificador de la zona no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las reservas encontradas. <br>
	 * 
	 * @param idZona Identificador de la zona comun
	 * @return Lista de reservas asociadas a la zona
	 */
	public ArrayList<Reserva> buscarPorZona(String idZona) {

		ArrayList<Reserva> resultado = new ArrayList<>();

		for (Reserva r : listaReservas)

			if (r.getZona() != null && r.getZona().getId().equals(idZona))
				resultado.add(r);

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de reservas.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de reservas a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Reserva> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}