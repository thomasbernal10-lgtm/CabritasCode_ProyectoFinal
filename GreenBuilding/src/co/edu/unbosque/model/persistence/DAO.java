package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

/**
 * Interfaz generica DAO (Data Access Object) encargada de definir las
 * operaciones basicas de persistencia para las diferentes entidades del sistema
 * GreenBuilding Manager.
 * 
 * Esta interfaz establece los metodos CRUD y de consulta que deben implementar
 * todas las clases DAO encargadas de administrar informacion dentro del
 * sistema.
 * 
 * <b>pre</b> Las clases que implementen esta interfaz deben manejar entidades y
 * DTOs compatibles con las operaciones definidas. <br>
 * <b>post</b> Se garantiza una estructura comun para la gestion de datos en el
 * sistema. <br>
 * 
 * @param <E> Tipo de entidad del modelo
 * @param <D> Tipo DTO asociado a la entidad
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public interface DAO<E, D> {

	/**
	 * Crea y almacena un nuevo objeto dentro del sistema.
	 * 
	 * <b>pre</b> nuevoDato != null <br>
	 * <b>post</b> El nuevo objeto queda registrado en el sistema. <br>
	 * 
	 * @param nuevoDato DTO con la informacion del nuevo objeto
	 */
	public void crear(D nuevoDato);

	/**
	 * Elimina un objeto segun su posicion dentro de la lista.
	 * 
	 * <b>pre</b> index >= 0 <br>
	 * <b>post</b> El objeto es eliminado si el indice es valido. <br>
	 * 
	 * @param index Posicion del objeto a eliminar
	 * @return true si la eliminacion fue exitosa, false en caso contrario
	 */
	public boolean eliminar(int index);

	/**
	 * Actualiza la informacion de un objeto existente.
	 * 
	 * <b>pre</b> index valido y datoActualizado != null <br>
	 * <b>post</b> El objeto queda actualizado con la nueva informacion. <br>
	 * 
	 * @param index           Posicion del objeto a actualizar
	 * @param datoActualizado DTO con los nuevos datos
	 * @return true si la actualizacion fue exitosa, false en caso contrario
	 */
	public boolean actualizar(int index, D datoActualizado);

	/**
	 * Retorna una representacion en texto de todos los objetos almacenados.
	 * 
	 * <b>pre</b> La implementacion debe tener inicializada la lista de objetos.
	 * <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @return String con todos los datos registrados
	 */
	public String mostrar();

	/**
	 * Retorna la representacion en texto de un objeto especifico.
	 * 
	 * <b>pre</b> index valido dentro de la coleccion. <br>
	 * <b>post</b> No se modifica la informacion almacenada. <br>
	 * 
	 * @param index Posicion del objeto solicitado
	 * @return String con la informacion del objeto
	 */
	public String mostrar(int index);

	/**
	 * Retorna todos los objetos almacenados en formato DTO.
	 * 
	 * <b>pre</b> La lista de datos debe estar inicializada. <br>
	 * <b>post</b> Se genera una lista DTO equivalente a la lista de entidades. <br>
	 * 
	 * @return Lista de objetos DTO
	 */
	public ArrayList<D> mostrarTodo();

	/**
	 * Ordena de forma ascendente una lista de entidades.
	 * 
	 * <b>pre</b> listaAOrdenar != null <br>
	 * <b>post</b> La lista queda organizada ascendentemente segun el criterio de
	 * implementacion. <br>
	 * 
	 * @param listaAOrdenar Lista de entidades a ordenar
	 */
	public void ordenarAscendentemente(ArrayList<E> listaAOrdenar);
}