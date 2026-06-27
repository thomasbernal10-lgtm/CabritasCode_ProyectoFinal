package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import co.edu.unbosque.model.Pago;
import co.edu.unbosque.model.PagoDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * los pagos del sistema GreenBuilding Manager. Permite crear, actualizar,
 * eliminar, consultar y almacenar informacion de pagos asociados a apartamentos
 * dentro del conjunto residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Pago y PagoDTO. La informacion es almacenada tanto en archivos de texto como
 * en archivos serializados.
 * 
 * <b>pre</b> La carpeta principal de archivos debe existir previamente en el
 * sistema. Los objetos PagoDTO deben contener informacion valida antes de ser
 * registrados. <br>
 * <b>post</b> Los pagos quedan almacenados y disponibles para procesos de
 * consulta, administracion y persistencia dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PagoDAO implements DAO<Pago, PagoDTO> {

	/**
	 * Lista que almacena los pagos registrados en el sistema.
	 */
	private ArrayList<Pago> listaPagos;

	/**
	 * Ruta del archivo serializado de pagos.
	 */
	private final String URL_SERIALIZADO = "pagos.dat";

	/**
	 * Ruta del archivo de texto de pagos.
	 */
	private final String URL_TEXTO = "pagos.csv";

	/**
	 * Constructor por defecto de la clase PagoDAO.
	 * 
	 * Inicializa la lista de pagos y carga la informacion almacenada en el archivo
	 * serializado.
	 * 
	 * <b>pre</b> No existen precondiciones para ejecutar el constructor. <br>
	 * <b>post</b> La lista de pagos queda inicializada con la informacion cargada
	 * desde persistencia. <br>
	 */
	public PagoDAO() {
		listaPagos = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de pagos almacenados.
	 * 
	 * <b>pre</b> El objeto PagoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Pago registrados
	 */
	public ArrayList<Pago> getListaPagos() {
		return listaPagos;
	}

	/**
	 * Asigna una nueva lista de pagos.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaPagos queda actualizado con la nueva lista. <br>
	 * 
	 * @param listaPagos Nueva lista de pagos
	 */
	public void setListaPagos(ArrayList<Pago> listaPagos) {
		this.listaPagos = listaPagos;
	}

	/**
	 * Crea y registra un nuevo pago en el sistema.
	 * 
	 * El objeto DTO es convertido a entidad antes de almacenarse.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> El nuevo pago queda agregado a la lista y almacenado en los
	 * archivos de persistencia. <br>
	 * 
	 * @param nuevoDato Objeto PagoDTO con la informacion del nuevo pago
	 */
	@Override
	public void crear(PagoDTO nuevoDato) {
		listaPagos.add(DataMapper.convertirPagoDTOAPago(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina un pago de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista de pagos. <br>
	 * <b>post</b> El pago es eliminado de la lista y los archivos son actualizados.
	 * <br>
	 * 
	 * @param index Posicion del pago a eliminar
	 * @return true si el pago fue eliminado correctamente, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPagos.size()) {
			return false;
		} else {
			listaPagos.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza la informacion de un pago existente.
	 * 
	 * <b>pre</b> El indice debe existir y el objeto datoActualizado debe contener
	 * informacion valida. <br>
	 * <b>post</b> El pago almacenado es reemplazado por la nueva informacion y los
	 * archivos son actualizados. <br>
	 * 
	 * @param index           Posicion del pago a actualizar
	 * @param datoActualizado Nuevo objeto PagoDTO con informacion actualizada
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, PagoDTO datoActualizado) {
		if (index < 0 || index >= listaPagos.size()) {
			return false;
		} else {
			listaPagos.set(index, DataMapper.convertirPagoDTOAPago(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todos los pagos registrados en formato texto.
	 * 
	 * <b>pre</b> La lista de pagos debe estar inicializada. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return Cadena con todos los pagos registrados
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;
		for (Pago p : listaPagos) {
			datos += posicion + " ";
			datos += p.toString() + "\n";
			posicion++;
		}
		return datos;
	}

	/**
	 * Retorna la informacion de un pago especifico.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del pago
	 * @return Representacion textual del pago solicitado
	 */
	@Override
	public String mostrar(int index) {
		return listaPagos.get(index).toString();
	}

	/**
	 * Retorna todos los pagos convertidos a objetos DTO.
	 * 
	 * <b>pre</b> La lista de pagos debe estar inicializada. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos PagoDTO
	 */
	@Override
	public ArrayList<PagoDTO> mostrarTodo() {
		return DataMapper.convertirListaPagoAListaPagoDTO(listaPagos);
	}

	/**
	 * Escribe la informacion de los pagos en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de pagos debe estar inicializada. <br>
	 * <b>post</b> El archivo de texto queda actualizado con la informacion de los
	 * pagos. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();
		for (Pago p : listaPagos) {
			sb.append(p.getId()).append(";");
			sb.append(p.getMonto()).append(";");
			sb.append(p.getFechaPago()).append(";");
			sb.append(p.getMedioPago()).append(";");
			sb.append(p.getReferencia()).append(";");
			sb.append(p.getRegistradoPor()).append("\n");
		}
		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee la informacion de pagos desde un archivo de texto.
	 * 
	 * <b>pre</b> El archivo de texto debe existir o poder ser creado. <br>
	 * <b>post</b> Los pagos leidos son agregados a la lista de pagos. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);
		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {
			String[] columnas = fila.split(";");

			Pago nuevo = new Pago();
			nuevo.setId(columnas[0]);
			nuevo.setMonto(Double.parseDouble(columnas[1]));
			nuevo.setFechaPago(LocalDate.parse(columnas[2]));
			nuevo.setMedioPago(columnas[3]);
			nuevo.setReferencia(columnas[4]);
			nuevo.setRegistradoPor(columnas[5]);

			listaPagos.add(nuevo);
		}
	}

	/**
	 * Escribe la lista de pagos en un archivo serializado.
	 * 
	 * <b>pre</b> La lista de pagos debe estar inicializada. <br>
	 * <b>post</b> El archivo serializado queda actualizado con la informacion de
	 * los pagos. <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaPagos);
	}

	/**
	 * Lee la informacion de pagos almacenada en un archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista de pagos queda cargada con la informacion recuperada del
	 * archivo. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaPagos = new ArrayList<>();
		} else {
			listaPagos = (ArrayList<Pago>) contenido;
		}
	}

	/**
	 * Busca todos los pagos asociados a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con los pagos encontrados. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de pagos asociados al apartamento
	 */
	public ArrayList<Pago> buscarPorApartamento(String idApartamento) {
		ArrayList<Pago> resultado = new ArrayList<>();

		for (Pago p : listaPagos) {
			if (p.getApartamento() != null && p.getApartamento().getId().equals(idApartamento)) {
				resultado.add(p);
			}
		}

		return resultado;
	}

	/**
	 * Ordena ascendentemente la lista de pagos.
	 * 
	 * <b>pre</b> La lista a ordenar debe estar inicializada. <br>
	 * <b>post</b> La lista queda ordenada ascendentemente segun el criterio
	 * implementado. <br>
	 * 
	 * @param listaAOrdenar Lista de pagos a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Pago> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}