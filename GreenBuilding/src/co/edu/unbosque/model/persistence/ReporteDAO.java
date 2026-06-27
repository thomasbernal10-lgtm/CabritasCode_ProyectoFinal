package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.model.ReporteDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los reportes generados dentro del sistema GreenBuilding Manager.
 * 
 * Permite crear, actualizar, eliminar, consultar y almacenar informacion
 * asociada a reportes administrativos, ambientales y estadisticos generados
 * dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Reporte y ReporteDTO. La informacion es almacenada tanto en archivos de texto
 * como en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos ReporteDTO deben contener informacion valida antes de
 * ser registrados. <br>
 * <b>post</b> Los reportes quedan almacenados y disponibles para procesos de
 * consulta, exportacion y seguimiento dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ReporteDAO implements DAO<Reporte, ReporteDTO> {

	/**
	 * Lista que almacena los reportes registrados en el sistema.
	 */
	private ArrayList<Reporte> listaReportes;

	/**
	 * Ruta del archivo serializado de reportes.
	 */
	private final String URL_SERIALIZADO = "reportes.dat";

	/**
	 * Ruta del archivo de texto de reportes.
	 */
	private final String URL_TEXTO = "reportes.csv";

	/**
	 * Constructor por defecto de la clase ReporteDAO.
	 * 
	 * Inicializa la lista de reportes y carga la informacion almacenada en el
	 * archivo serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de reportes queda inicializada con la informacion
	 * cargada desde persistencia. <br>
	 */
	public ReporteDAO() {
		listaReportes = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de reportes almacenados.
	 * 
	 * <b>pre</b> El objeto ReporteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Reporte registrados
	 */
	public ArrayList<Reporte> getListaReportes() {
		return listaReportes;
	}

	/**
	 * Asigna una nueva lista de reportes.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaReportes queda actualizado con la nueva lista.
	 * <br>
	 * 
	 * @param listaReportes Nueva lista de reportes
	 */
	public void setListaReportes(ArrayList<Reporte> listaReportes) {
		this.listaReportes = listaReportes;
	}

	/**
	 * Crea y registra un nuevo reporte en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo reporte queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto ReporteDTO con la informacion del nuevo reporte
	 */
	@Override
	public void crear(ReporteDTO nuevoDato) {
		listaReportes.add(DataMapper.convertirReporteDTOAReporte(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un reporte de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de reportes. <br>
	 * <b>post</b> El reporte es eliminado de la lista y los archivos son
	 * actualizados. <br>
	 * 
	 * @param index Posicion del reporte a eliminar
	 * @return true si el reporte fue eliminado correctamente, false en caso
	 *         contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaReportes.size()) {
			return false;
		} else {
			listaReportes.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un reporte existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El reporte almacenado es reemplazado por la nueva informacion y
	 * los archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del reporte a actualizar
	 * @param datoActualizado Nuevo objeto ReporteDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ReporteDTO datoActualizado) {
		if (index < 0 || index >= listaReportes.size()) {
			return false;
		} else {
			listaReportes.set(index, DataMapper.convertirReporteDTOAReporte(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los reportes registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de reportes debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los reportes registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Reporte r : listaReportes) {
			datos += posicion + " ";
			datos += r.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna la informacion de un reporte especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del reporte
	 * @return Representacion textual del reporte solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaReportes.get(index).toString();
	}

	/**
	 * Retorna todos los reportes convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de reportes debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos ReporteDTO
	 */
	@Override
	public ArrayList<ReporteDTO> mostrarTodo() {
		return DataMapper.convertirListaReporteAListaReporteDTO(listaReportes);
	}

	/**
	 * Escribe la informacion de los reportes en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de reportes debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * reportes. <br>
	 */
	public void escribirArchivo() {

		StringBuilder sb = new StringBuilder();

		for (Reporte r : listaReportes) {

			sb.append(r.getId()).append(";");
			sb.append(r.getTipo()).append(";");
			sb.append(r.getTitulo()).append(";");
			sb.append(r.getContenido()).append(";");
			sb.append(r.getFechaGeneracion()).append(";");
			sb.append(r.getGeneradoPor()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de reportes desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los reportes leidos son agregados a la lista de reportes. <br>
	 */
	public void leerArchivo() {

		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {

			String[] columnas = fila.split(";");

			Reporte nuevo = new Reporte();

			nuevo.setId(columnas[0]);
			nuevo.setTipo(columnas[1]);
			nuevo.setTitulo(columnas[2]);
			nuevo.setContenido(columnas[3]);
			nuevo.setFechaGeneracion(LocalDateTime.parse(columnas[4]));
			nuevo.setGeneradoPor(columnas[5]);

			listaReportes.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de reportes en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de reportes debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los reportes. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaReportes);
	}

	/**
	 * Lee la informacion de reportes almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de reportes queda cargada con la informacion recuperada
	 * del archivo. <br>
	 */
	public void leerArchivoSerializado() {

		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaReportes = new ArrayList<>();
		} else {
			listaReportes = (ArrayList<Reporte>) contenido;
		}
	}

	/**
	 * Ordena ascendentemente la lista de reportes.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de reportes a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Reporte> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

	/**
	 * Exporta un reporte especifico en formato PDF.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de reportes. <br>
	 * <b>post</b> Se genera un archivo PDF con la informacion del reporte
	 * seleccionado. <br>
	 * 
	 * @param index Posicion del reporte a exportar
	 */
	public void exportarReportePDF(int index) {

		Reporte r = listaReportes.get(index);

		String contenidoPDF = "ID: " + r.getId() + "\n" + "Tipo: " + r.getTipo() + "\n" + "Generado por: "
				+ r.getGeneradoPor() + "\n" + "Fecha: " + r.getFechaGeneracion() + "\n\n" + r.getContenido();

		FileHandler.exportarPDF(r.getId() + "_reporte.pdf", r.getTitulo(), contenidoPDF);
	}

}