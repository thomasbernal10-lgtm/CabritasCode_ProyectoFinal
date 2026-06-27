package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Objeto de transferencia de datos (DTO) que representa un registro de consumo
 * de agua en el sistema GreenBuilding Manager. Extiende {@link RegistroConsumo}
 * e incluye el consumo bruto, el volumen de agua lluvia aprovechada y el
 * consumo neto resultante. <br>
 * <b>pre</b> El conjunto al que pertenece el registro debe estar registrado en
 * el sistema. Los valores de consumo deben ser mayores o iguales a cero. <br>
 * <b>post</b> El DTO queda disponible para ser persistido o transferido entre
 * las capas del sistema para consulta y generación de reportes ambientales.
 * <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroConsumoAguaDTO extends RegistroConsumo {

	/** Identificador de versión para la serialización de la clase. */
	private static final long serialVersionUID = -7384339755268303623L;

	/** Consumo total de agua en metros cúbicos durante el periodo. */
	private double consumoMtCubico;

	/** Volumen de agua lluvia aprovechada en metros cúbicos durante el periodo. */
	private double aguaLluviaMtCubico;

	/** Consumo neto de agua en metros cúbicos (consumo bruto menos agua lluvia). */
	private double consumoNetoMtCubico;

	/**
	 * Constructor por defecto. Crea un RegistroConsumoAguaDTO sin datos
	 * inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAguaDTO con todos sus atributos
	 * en cero o null. <br>
	 */
	public RegistroConsumoAguaDTO() {
	}

	/**
	 * Constructor con valores de consumo. Crea un RegistroConsumoAguaDTO con los
	 * valores de agua sin datos de RegistroConsumo. <br>
	 * <b>pre</b> Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAguaDTO con los valores de
	 * consumo inicializados y los atributos heredados en null. <br>
	 *
	 * @param consumoMtCubico     Consumo bruto de agua en m3. consumoMtCubico >= 0
	 * @param aguaLluviaMtCubico  Agua lluvia aprovechada en m3. aguaLluviaMtCubico
	 *                            >= 0
	 * @param consumoNetoMtCubico Consumo neto de agua en m3. consumoNetoMtCubico >=
	 *                            0
	 */
	public RegistroConsumoAguaDTO(double consumoMtCubico, double aguaLluviaMtCubico, double consumoNetoMtCubico) {
		super();
		this.consumoMtCubico = consumoMtCubico;
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
		this.consumoNetoMtCubico = consumoNetoMtCubico;
	}

	/**
	 * Constructor completo. Crea un RegistroConsumoAguaDTO con todos sus atributos
	 * inicializados. El consumo neto se calcula automáticamente como la diferencia
	 * entre el consumo bruto y el agua lluvia. <br>
	 * <b>pre</b> Los parámetros id, periodo y registradoPor no deben ser null ni
	 * vacíos. Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAguaDTO completamente
	 * inicializado con el consumo neto calculado automáticamente. <br>
	 *
	 * @param id                 Identificador único del registro. id != null, id !=
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
	public RegistroConsumoAguaDTO(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor, double consumoMtCubico, double aguaLluviaMtCubico) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
		this.consumoMtCubico = consumoMtCubico;
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
		this.consumoNetoMtCubico = consumoMtCubico - aguaLluviaMtCubico;
	}

	/**
	 * Constructor con datos base. Crea un RegistroConsumoAguaDTO con los datos
	 * heredados de RegistroConsumo sin los valores de consumo. <br>
	 * <b>pre</b> Los parámetros id, periodo y registradoPor no deben ser null ni
	 * vacíos. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoAguaDTO con los datos de
	 * RegistroConsumo inicializados y los de consumo en cero. <br>
	 *
	 * @param id            Identificador único del registro. id != null, id != ""
	 * @param conjunto      Conjunto residencial al que pertenece. conjunto != null
	 * @param periodo       Periodo del registro. periodo != null, periodo != ""
	 * @param fechaRegistro Fecha del registro. fechaRegistro != null
	 * @param registradoPor Identificador del usuario que registra. registradoPor !=
	 *                      null
	 */
	public RegistroConsumoAguaDTO(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
	}

	/** @return Consumo bruto de agua en m3. */
	public double getConsumoMtCubico() {
		return consumoMtCubico;
	}

	/** @param consumoMtCubico Nuevo consumo bruto en m3. consumoMtCubico >= 0 */
	public void setConsumoMtCubico(double consumoMtCubico) {
		this.consumoMtCubico = consumoMtCubico;
	}

	/** @return Volumen de agua lluvia aprovechada en m3. */
	public double getAguaLluviaMtCubico() {
		return aguaLluviaMtCubico;
	}

	/** @param aguaLluviaMtCubico Nuevo volumen de agua lluvia en m3. >= 0 */
	public void setAguaLluviaMtCubico(double aguaLluviaMtCubico) {
		this.aguaLluviaMtCubico = aguaLluviaMtCubico;
	}

	/**
	 * @return Consumo neto de agua en m3 calculado como consumo bruto - agua lluvia
	 */
	public double getConsumoNetoMtCubico() {
		return consumoMtCubico - aguaLluviaMtCubico;
	}

	/** @param consumoNetoMtCubico Nuevo consumo neto en m3. >= 0 */
	public void setConsumoNetoMtCubico(double consumoNetoMtCubico) {
		this.consumoNetoMtCubico = consumoNetoMtCubico;
	}

	/**
	 * Retorna una representación en texto del registro con sus datos principales.
	 *
	 * @return String con los datos de RegistroConsumo y los valores de agua
	 */
	@Override
	public String toString() {
		return "RegistroConsumoAgua [" + super.toString() + ", consumoMtCubico=" + consumoMtCubico
				+ ", aguaLluviaMtCubico=" + aguaLluviaMtCubico + ", consumoNetoMtCubico=" + consumoNetoMtCubico + "]";
	}
}
