package co.edu.unbosque.model;

/**
 * Interfaz que define el contrato para las entidades del sistema que pueden
 * tener sanciones activas. Las clases que la implementen permiten verificar si
 * un actor tiene restricciones vigentes, lo que puede afectar su acceso a zonas
 * comunes, reservas u otros beneficios del conjunto residencial. <br>
 * <b>pre</b> La clase implementadora debe gestionar un estado de sanciones
 * internamente. <br>
 * <b>post</b> El sistema puede consultar el estado sancionatorio de la entidad
 * de forma estandarizada. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public interface Sancionable {

	/**
	 * Indica si la entidad tiene sanciones activas en el momento de la consulta.
	 * <br>
	 * <b>pre</b> El objeto implementador debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si la entidad tiene al menos una sancion activa; false en caso
	 *         contrario
	 */
	public boolean tieneSancionesActivas();

	/**
	 * Retorna el identificador del responsable asociado a la entidad sancionable.
	 * <br>
	 * <b>pre</b> El objeto implementador debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el identificador del responsable, o null si no aplica
	 */
	public String getIdResponsable();
}