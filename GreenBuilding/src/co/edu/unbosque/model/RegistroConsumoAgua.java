package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Representa un registro de consumo de agua en las zonas comunes del conjunto
 * residencial. Extiende RegistroConsumo e incluye el consumo bruto, el volumen
 * de agua lluvia aprovechada y el consumo neto resultante. Permite a la
 * administracion hacer seguimiento al uso eficiente del agua y evaluar el
 * impacto de los sistemas de recoleccion de aguas lluvias. <br>
 * <b>pre</b> El conjunto al que pertenece el registro debe estar registrado en
 * el sistema. <br>
 * <b>post</b> El registro queda disponible para consulta y generacion de
 * reportes ambientales. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroConsumoAgua extends RegistroConsumo {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -7384339755268303623L;

	/** Consumo total de agua en metros cubicos durante el periodo. */
	private double consumoMtCubico;

	/** Volumen de agua lluvia aprovechada en metros cubicos durante el periodo. */
	private double aguaLluviaMtCubico;

	/** Consumo neto de agua en metros cubicos (consumo bruto menos agua lluvia). */
	private double consumoNetoMtCubico;

	/**
	 * Constructor por defecto. Crea un RegistroConsumoAgua sin datos inicializados.
	 * <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAgua con todos sus atributos en
	 * cero o null. <br>
	 */
	public RegistroConsumoAgua() {
	}

	/**
	 * Constructor con valores de consumo. Crea un RegistroConsumoAgua con los
	 * valores de agua sin datos de RegistroConsumo. <br>
	 * <b>pre</b> Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAgua con los valores de consumo
	 * inicializados y los atributos heredados en null. <br>
	 *
	 * @param consumoMtCubico     Consumo bruto de agua en m3. consumoMtCubico >= 0
	 * @param aguaLluviaMtCubico  Agua lluvia aprovechada en m3. aguaLluviaMtCubico
	 *                            >= 0
	 * @param consumoNetoMtCubico Consumo neto de agua en m3. consumoNetoMtCubico >=
	 *                            0
	 */
	public RegistroConsumoAgua(double consumoMtCubico, double aguaLluviaMtCubico, double consumoNetoMtCubico) {
		super();
		this.consumoMtCubico = consumoMtCubico;
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
		this.consumoNetoMtCubico = consumoNetoMtCubico;
	}

	/**
	 * Constructor completo. Crea un RegistroConsumoAgua con todos sus atributos
	 * inicializados. El consumo neto se calcula automaticamente como la diferencia
	 * entre el consumo bruto y el agua lluvia. <br>
	 * <b>pre</b> Los parametros id, periodo y registradoPor no deben ser null ni
	 * vacios. Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAgua completamente inicializado
	 * con el consumo neto calculado automaticamente. <br>
	 *
	 * @param id                 Identificador unico del registro. id != null, id !=
	 *                           ""
	 * @param conjunto           Conjunto residencial al que pertenece. conjunto !=
	 *                           null
	 * @param periodo            Periodo del registro. periodo != null, periodo !=
	 *                           ""
	 * @param fechaRegistro      Fecha del registro. fechaRegistro != null
	 * @param registradoPor      Identificador del usuario que registra.
	 *                           registradoPor != null
	 * @param consumoMtCubico    Consumo bruto de agua en m3. consumoMtCubico >= 0
	 * @param aguaLluviaMtCubico Agua lluvia aprovechada en m3. aguaLluviaMtCubico
	 *                           >= 0
	 */
	public RegistroConsumoAgua(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor, double consumoMtCubico, double aguaLluviaMtCubico) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
		this.consumoMtCubico = consumoMtCubico;
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
		this.consumoNetoMtCubico = consumoMtCubico - aguaLluviaMtCubico;
	}

	/**
	 * Constructor con datos base. Crea un RegistroConsumoAgua con los datos
	 * heredados de RegistroConsumo sin los valores de consumo. <br>
	 * <b>pre</b> Los parametros id, periodo y registradoPor no deben ser null ni
	 * vacios. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAgua con los datos de
	 * RegistroConsumo inicializados y los de consumo en cero. <br>
	 *
	 * @param id            Identificador unico del registro. id != null, id != ""
	 * @param conjunto      Conjunto residencial al que pertenece. conjunto != null
	 * @param periodo       Periodo del registro. periodo != null, periodo != ""
	 * @param fechaRegistro Fecha del registro. fechaRegistro != null
	 * @param registradoPor Identificador del usuario que registra. registradoPor !=
	 *                      null
	 */
	public RegistroConsumoAgua(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
	}

	/**
	 * Retorna el consumo bruto de agua en metros cubicos. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el consumo bruto de agua en m3
	 */
	public double getConsumoMtCubico() {
		return consumoMtCubico;
	}

	/**
	 * Asigna el consumo bruto de agua en metros cubicos. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> El atributo consumoMtCubico queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param consumoMtCubico Nuevo consumo bruto en m3. consumoMtCubico >= 0
	 */
	public void setConsumoMtCubico(double consumoMtCubico) {
		this.consumoMtCubico = consumoMtCubico;
	}

	/**
	 * Retorna el volumen de agua lluvia aprovechada en metros cubicos. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el volumen de agua lluvia aprovechada en m3
	 */
	public double getAguaLluviaMtCubico() {
		return aguaLluviaMtCubico;
	}

	/**
	 * Asigna el volumen de agua lluvia aprovechada en metros cubicos. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> El atributo aguaLluviaMtCubico queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param aguaLluviaMtCubico Nuevo volumen de agua lluvia en m3.
	 *                           aguaLluviaMtCubico >= 0
	 */
	public void setAguaLluviaMtCubico(double aguaLluviaMtCubico) {
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
	}

	/**
	 * Retorna el consumo neto de agua calculado como la diferencia entre el consumo
	 * bruto y el agua lluvia aprovechada. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el consumo neto de agua en m3
	 */
	public double getConsumoNetoMtCubico() {
		return consumoMtCubico - aguaLluviaMtCubico;
	}

	/**
	 * Asigna el consumo neto de agua en metros cubicos. <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> El atributo consumoNetoMtCubico queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param consumoNetoMtCubico Nuevo consumo neto en m3. consumoNetoMtCubico >= 0
	 */
	public void setConsumoNetoMtCubico(double consumoNetoMtCubico) {
		this.consumoNetoMtCubico = consumoNetoMtCubico;
	}

	/**
	 * Retorna una representacion en texto del registro con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto RegistroConsumoAgua debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con los datos de RegistroConsumo y los valores de agua
	 */
	@Override
	public String toString() {
		return "RegistroConsumoAgua [" + super.toString() + ", consumoMtCubico=" + consumoMtCubico
				+ ", aguaLluviaMtCubico=" + aguaLluviaMtCubico + ", consumoNetoMtCubico=" + consumoNetoMtCubico + "]";
	}
}