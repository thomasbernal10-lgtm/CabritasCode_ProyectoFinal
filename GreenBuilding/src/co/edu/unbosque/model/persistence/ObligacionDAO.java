package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.unbosque.model.Obligacion;
import co.edu.unbosque.model.ObligacionDTO;

/**
 * Clase encargada de gestionar las operaciones de persistencia relacionadas con
 * las obligaciones economicas del sistema GreenBuilding Manager. Permite crear,
 * actualizar, eliminar, consultar y almacenar informacion relacionada con las
 * obligaciones financieras generadas para los apartamentos del conjunto
 * residencial.
 * 
 * Implementa la interfaz DAO para garantizar operaciones CRUD sobre objetos
 * Obligacion y ObligacionDTO. Utiliza archivos de texto y serializacion para
 * mantener la persistencia de los datos.
 * 
 * <b>pre</b> Los objetos ObligacionDTO utilizados en las operaciones deben
 * contener informacion valida y correctamente inicializada. <br>
 * <b>post</b> Las obligaciones quedan almacenadas y disponibles para procesos
 * de consulta, actualizacion y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ObligacionDAO implements DAO<Obligacion, ObligacionDTO> {

	/**
	 * Lista que almacena las obligaciones registradas en el sistema.
	 */
	private ArrayList<Obligacion> listaObligaciones;

	/**
	 * Ruta del archivo serializado de obligaciones.
	 */
	private final String URL_SERIALIZADO = "obligaciones.dat";

	/**
	 * Ruta del archivo de texto de obligaciones.
	 */
	private final String URL_TEXTO = "obligaciones.csv";

	/**
	 * Constructor por defecto de la clase ObligacionDAO.
	 * 
	 * <b>pre</b> No existen precondiciones para construir el objeto. <br>
	 * <b>post</b> Se inicializa la lista de obligaciones y se cargan los datos
	 * almacenados en el archivo serializado. <br>
	 */
	public ObligacionDAO() {
		listaObligaciones = new ArrayList<>();
		leerArchivoSerializado();
	}

	/**
	 * Retorna la lista de obligaciones almacenadas.
	 * 
	 * <b>pre</b> El objeto ObligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Lista de objetos Obligacion
	 */
	public ArrayList<Obligacion> getListaObligaciones() {
		return listaObligaciones;
	}

	/**
	 * Asigna una nueva lista de obligaciones.
	 * 
	 * <b>pre</b> La lista recibida no debe ser null. <br>
	 * <b>post</b> El atributo listaObligaciones queda actualizado. <br>
	 * 
	 * @param listaObligaciones Nueva lista de obligaciones
	 */
	public void setListaObligaciones(ArrayList<Obligacion> listaObligaciones) {
		this.listaObligaciones = listaObligaciones;
	}

	/**
	 * Crea una nueva obligacion dentro del sistema.
	 * 
	 * <b>pre</b> El objeto nuevoDato debe contener informacion valida y diferente
	 * de null. <br>
	 * <b>post</b> La obligacion queda almacenada en memoria y persistencia. <br>
	 * 
	 * @param nuevoDato DTO con la informacion de la obligacion
	 */
	@Override
	public void crear(ObligacionDTO nuevoDato) {
		listaObligaciones.add(DataMapper.convertirObligacionDTOAObligacion(nuevoDato));
		escribirArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Elimina una obligacion de la lista segun su posicion.
	 * 
	 * <b>pre</b> El indice debe corresponder a una posicion valida dentro de la
	 * lista. <br>
	 * <b>post</b> La obligacion es eliminada si el indice es valido. <br>
	 * 
	 * @param index Posicion de la obligacion a eliminar
	 * @return true si la eliminacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaObligaciones.size()) {
			return false;
		} else {
			listaObligaciones.remove(index);
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Actualiza una obligacion existente.
	 * 
	 * <b>pre</b> El indice debe existir y el DTO debe contener informacion valida.
	 * <br>
	 * <b>post</b> La obligacion queda actualizada en memoria y persistencia. <br>
	 * 
	 * @param index           Posicion de la obligacion a actualizar
	 * @param datoActualizado DTO con la nueva informacion
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	@Override
	public boolean actualizar(int index, ObligacionDTO datoActualizado) {
		if (index < 0 || index >= listaObligaciones.size()) {
			return false;
		} else {
			listaObligaciones.set(index, DataMapper.convertirObligacionDTOAObligacion(datoActualizado));
			escribirArchivo();
			escribirArchivoSerializado();
			return true;
		}
	}

	/**
	 * Retorna todas las obligaciones registradas en formato String.
	 * 
	 * <b>pre</b> La lista de obligaciones debe existir. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return String con todas las obligaciones registradas
	 */
	@Override
	public String mostrar() {
		String datos = "";
		int posicion = 0;

		for (Obligacion o : listaObligaciones) {
			datos += posicion + " ";
			datos += o.toString() + "\n";
			posicion++;
		}

		return datos;
	}

	/**
	 * Retorna una obligacion especifica segun su posicion.
	 * 
	 * <b>pre</b> El indice debe existir dentro de la lista. <br>
	 * <b>post</b> No se modifica el contenido almacenado. <br>
	 * 
	 * @param index Posicion de la obligacion
	 * @return String con la informacion de la obligacion
	 */
	@Override
	public String mostrar(int index) {
		return listaObligaciones.get(index).toString();
	}

	/**
	 * Retorna todas las obligaciones convertidas a DTO.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Se retorna una lista DTO equivalente. <br>
	 * 
	 * @return Lista de objetos ObligacionDTO
	 */
	@Override
	public ArrayList<ObligacionDTO> mostrarTodo() {
		return DataMapper.convertirListaObligacionAListaObligacionDTO(listaObligaciones);
	}

	/**
	 * Escribe las obligaciones en un archivo de texto.
	 * 
	 * <b>pre</b> La lista de obligaciones debe existir. <br>
	 * <b>post</b> La informacion queda almacenada en el archivo CSV. <br>
	 */
	public void escribirArchivo() {
		StringBuilder sb = new StringBuilder();

		for (Obligacion o : listaObligaciones) {
			sb.append(o.getId()).append(";");
			sb.append(o.getTipo()).append(";");
			sb.append(o.getMonto()).append(";");
			sb.append(o.getFechaGeneracion()).append(";");
			sb.append(o.getFechaLimite()).append(";");
			sb.append(o.getEstado()).append(";");
			sb.append(o.getConcepto()).append(";");
			sb.append(o.getGeneradaPor()).append("\n");
		}

		FileHandler.crearYEscribirArchivo(URL_TEXTO, sb.toString());
	}

	/**
	 * Lee las obligaciones almacenadas en el archivo de texto.
	 * 
	 * <b>pre</b> El archivo debe existir o poder ser creado. <br>
	 * <b>post</b> Las obligaciones leidas quedan cargadas en memoria. <br>
	 */
	public void leerArchivo() {
		String contenido = FileHandler.crearYLeerArchivo(URL_TEXTO);

		if (contenido == null || contenido.isEmpty() || contenido.isBlank())
			return;

		String[] filas = contenido.split("\n");

		for (String fila : filas) {
			String[] columnas = fila.split(";");

			Obligacion nuevo = new Obligacion();

			nuevo.setId(columnas[0]);
			nuevo.setTipo(columnas[1]);
			nuevo.setMonto(Double.parseDouble(columnas[2]));
			nuevo.setFechaGeneracion(LocalDate.parse(columnas[3]));
			nuevo.setFechaLimite(LocalDate.parse(columnas[4]));
			nuevo.setEstado(columnas[5]);
			nuevo.setConcepto(columnas[6]);
			nuevo.setGeneradaPor(columnas[7]);

			listaObligaciones.add(nuevo);
		}
	}

	/**
	 * Guarda las obligaciones utilizando serializacion.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Las obligaciones quedan almacenadas en el archivo serializado.
	 * <br>
	 */
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirArchivoSerializado(URL_SERIALIZADO, listaObligaciones);
	}

	/**
	 * Lee las obligaciones desde el archivo serializado.
	 * 
	 * <b>pre</b> El archivo serializado debe existir o poder ser creado. <br>
	 * <b>post</b> La lista queda cargada con las obligaciones almacenadas. <br>
	 */
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerArchivoSerializado(URL_SERIALIZADO);

		if (contenido == null) {
			listaObligaciones = new ArrayList<>();
		} else {
			listaObligaciones = (ArrayList<Obligacion>) contenido;
		}
	}

	/**
	 * Busca obligaciones asociadas a un apartamento especifico.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las obligaciones encontradas. <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de obligaciones asociadas al apartamento
	 */
	public ArrayList<Obligacion> buscarPorApartamento(String idApartamento) {
		ArrayList<Obligacion> resultado = new ArrayList<>();

		for (Obligacion o : listaObligaciones) {
			if (o.getApartamento() != null && o.getApartamento().getId().equals(idApartamento)) {
				resultado.add(o);
			}
		}

		return resultado;
	}

	/**
	 * Busca obligaciones pendientes asociadas a un apartamento.
	 * 
	 * <b>pre</b> El identificador del apartamento no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las obligaciones pendientes encontradas.
	 * <br>
	 * 
	 * @param idApartamento Identificador del apartamento
	 * @return Lista de obligaciones pendientes del apartamento
	 */
	public ArrayList<Obligacion> buscarPendientesPorApartamento(String idApartamento) {
		ArrayList<Obligacion> resultado = new ArrayList<>();

		for (Obligacion o : listaObligaciones) {
			if (o.getApartamento() != null && o.getApartamento().getId().equals(idApartamento)
					&& o.getEstado().equals("PENDIENTE")) {
				resultado.add(o);
			}
		}

		return resultado;
	}

	/**
	 * Busca obligaciones vencidas dentro del sistema.
	 * 
	 * <b>pre</b> La lista de obligaciones debe estar inicializada. <br>
	 * <b>post</b> Se retorna una lista con las obligaciones vencidas encontradas.
	 * <br>
	 * 
	 * @return Lista de obligaciones vencidas
	 */
	public ArrayList<Obligacion> buscarVencidas() {
		ArrayList<Obligacion> resultado = new ArrayList<>();

		for (Obligacion o : listaObligaciones)
			if (o.getEstado().equals("PENDIENTE") && o.getFechaLimite() != null
					&& o.getFechaLimite().isBefore(LocalDate.now()))
				resultado.add(o);

		return resultado;
	}

	/**
	 * Busca obligaciones segun su estado.
	 * 
	 * <b>pre</b> El estado no debe ser null. <br>
	 * <b>post</b> Se retorna una lista con las obligaciones encontradas. <br>
	 * 
	 * @param estado Estado de las obligaciones
	 * @return Lista de obligaciones con el estado indicado
	 */
	public ArrayList<Obligacion> buscarPorEstado(String estado) {
		ArrayList<Obligacion> resultado = new ArrayList<>();

		for (Obligacion o : listaObligaciones)
			if (o.getEstado().equals(estado))
				resultado.add(o);

		return resultado;
	}

	/**
	 * Ordena ascendentemente una lista de obligaciones.
	 * 
	 * <b>pre</b> La lista debe estar inicializada. <br>
	 * <b>post</b> Actualmente el metodo no posee implementacion. <br>
	 * 
	 * @param listaAOrdenar Lista de obligaciones a ordenar
	 */
	@Override
	public void ordenarAscendentemente(ArrayList<Obligacion> listaAOrdenar) {
		// TODO Auto-generated method stub

	}

}