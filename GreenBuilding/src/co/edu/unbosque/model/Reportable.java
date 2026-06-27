package co.edu.unbosque.model;

/**
 * Interfaz que define el contrato para las entidades del sistema capaces de
 * generar un resumen de sus datos en formato de texto. Las clases que
 * implementen esta interfaz podran ser incluidas en reportes administrativos
 * del conjunto residencial. <br>
 * <b>pre</b> La clase implementadora debe tener datos suficientes para
 * construir un resumen significativo. <br>
 * <b>post</b> Se obtiene una representacion textual resumida de la entidad.
 * <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public interface Reportable {

	/**
	 * Genera un resumen en texto de los datos relevantes de la entidad. <br>
	 * <b>pre</b> El objeto implementador debe estar instanciado y con datos
	 * cargados. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el resumen de la entidad listo para incluir en reportes
	 */
	public String generarResumen();
}