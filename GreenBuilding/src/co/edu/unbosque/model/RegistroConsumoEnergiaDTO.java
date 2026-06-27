package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Objeto de transferencia de datos (DTO) que representa un registro de consumo
 * de energía eléctrica en el sistema GreenBuilding Manager. Extiende
 * {@link RegistroConsumo} e incluye el consumo bruto en kilovatios-hora, la
 * generación solar aprovechada y el consumo neto resultante. <br>
 * <b>pre</b> El conjunto al que pertenece el registro debe estar registrado en
 * el sistema. Los valores de consumo deben ser mayores o iguales a cero. <br>
 * <b>post</b> El DTO queda disponible para ser persistido o transferido entre
 * las capas del sistema para consulta y generación de reportes ambientales.
 * <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroConsumoEnergiaDTO extends RegistroConsumo {

	/** Identificador de versión para la serialización de la clase. */
	private static final long serialVersionUID = -5237251720552928872L;

	/** Consumo total de energía en kilovatios-hora durante el periodo. */
	private double consumoKwh;

	/**
	 * Energía generada por los paneles solares en kilovatios-hora durante el
	 * periodo.
	 */
	private double generacionSolarKwh;

	/** Consumo neto de energía (consumo bruto menos generación solar). */
	private double consumoNetoKwh;

	/**
	 * Constructor por defecto. Crea un RegistroConsumoEnergiaDTO sin datos
	 * inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergiaDTO con todos sus
	 * atributos en cero o null. <br>
	 */
	public RegistroConsumoEnergiaDTO() {
	}

	/**
	 * Constructor con valores de consumo. Crea un RegistroConsumoEnergiaDTO con los
	 * valores de energía sin datos de RegistroConsumo. <br>
	 * <b>pre</b> Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergiaDTO con los valores de
	 * consumo inicializados y los atributos heredados en null. <br>
	 *
	 * @param consumoKwh         Consumo bruto de energía en kWh. consumoKwh >= 0
	 * @param generacionSolarKwh Energía solar generada en kWh. generacionSolarKwh
	 *                           >= 0
	 * @param consumoNetoKwh     Consumo neto de energía en kWh. consumoNetoKwh >= 0
	 */
	public RegistroConsumoEnergiaDTO(double consumoKwh, double generacionSolarKwh, double consumoNetoKwh) {
		super();
		this.consumoKwh = consumoKwh;
		this.generacionSolarKwh = generacionSolarKwh;
		this.consumoNetoKwh = consumoNetoKwh;
	}

	/**
	 * Constructor completo. Crea un RegistroConsumoEnergiaDTO con todos sus
	 * atributos inicializados. El consumo neto se calcula automáticamente como la
	 * diferencia entre el consumo bruto y la generación solar. <br>
	 * <b>pre</b> Los parámetros id, periodo y registradoPor no deben ser null ni
	 * vacíos. Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergiaDTO completamente
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
	 * @param consumoKwh         Consumo bruto de energía en kWh. consumoKwh >= 0
	 * @param generacionSolarKwh Energía solar generada en kWh. generacionSolarKwh
	 *                           >= 0
	 */
	public RegistroConsumoEnergiaDTO(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor, double consumoKwh, double generacionSolarKwh) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
		this.consumoKwh = consumoKwh;
		this.generacionSolarKwh = generacionSolarKwh;
		this.consumoNetoKwh = consumoKwh - generacionSolarKwh;
	}

	/**
	 * Constructor con datos base. Crea un RegistroConsumoEnergiaDTO con los datos
	 * heredados de RegistroConsumo sin los valores de consumo. <br>
	 * <b>pre</b> Los parámetros id, periodo y registradoPor no deben ser null ni
	 * vacíos. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergiaDTO con los datos de
	 * RegistroConsumo inicializados y los de consumo en cero. <br>
	 *
	 * @param id            Identificador único del registro. id != null, id != ""
	 * @param conjunto      Conjunto residencial al que pertenece. conjunto != null
	 * @param periodo       Periodo del registro. periodo != null, periodo != ""
	 * @param fechaRegistro Fecha del registro. fechaRegistro != null
	 * @param registradoPor Identificador del usuario que registra. registradoPor !=
	 *                      null
	 */
	public RegistroConsumoEnergiaDTO(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
	}

	/** @return Consumo bruto de energía en kWh. */
	public double getConsumoKwh() {
		return consumoKwh;
	}

	/** @param consumoKwh Nuevo consumo bruto en kWh. consumoKwh >= 0 */
	public void setConsumoKwh(double consumoKwh) {
		this.consumoKwh = consumoKwh;
	}

	/** @return Energía solar generada en kWh. */
	public double getGeneracionSolarKwh() {
		return generacionSolarKwh;
	}

	/** @param generacionSolarKwh Nueva generación solar en kWh. >= 0 */
	public void setGeneracionSolarKwh(double generacionSolarKwh) {
		this.generacionSolarKwh = generacionSolarKwh;
	}

	/** @return Consumo neto de energía en kWh. */
	public double getConsumoNetoKwh() {
		return consumoNetoKwh;
	}

	/** @param consumoNetoKwh Nuevo consumo neto en kWh. >= 0 */
	public void setConsumoNetoKwh(double consumoNetoKwh) {
		this.consumoNetoKwh = consumoNetoKwh;
	}

	/**
	 * Retorna una representación en texto del registro con sus datos principales.
	 *
	 * @return String con los datos de RegistroConsumo y los valores de energía
	 */
	@Override
	public String toString() {
		return "RegistroConsumoEnergia [" + super.toString() + ", consumoKwh=" + consumoKwh + ", generacionSolarKwh="
				+ generacionSolarKwh + ", consumoNetoKwh=" + consumoNetoKwh + "]";
	}
}
