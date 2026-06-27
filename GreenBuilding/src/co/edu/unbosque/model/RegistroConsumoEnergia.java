package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Representa un registro de consumo de energia electrica en las zonas comunes
 * del conjunto residencial. Extiende RegistroConsumo e incluye el consumo bruto
 * en kilovatios-hora, la generacion solar aprovechada y el consumo neto
 * resultante. Permite a la administracion evaluar el impacto de los paneles
 * solares y el uso eficiente de la energia. <br>
 * <b>pre</b> El conjunto al que pertenece el registro debe estar registrado en
 * el sistema. <br>
 * <b>post</b> El registro queda disponible para consulta y generacion de
 * reportes ambientales. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroConsumoEnergia extends RegistroConsumo {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -5237251720552928872L;

	/** Consumo total de energia en kilovatios-hora durante el periodo. */
	private double consumoKwh;

	/**
	 * Energia generada por los paneles solares en kilovatios-hora durante el
	 * periodo.
	 */
	private double generacionSolarKwh;

	/** Consumo neto de energia (consumo bruto menos generacion solar). */
	private double consumoNetoKwh;

	/**
	 * Constructor por defecto. Crea un RegistroConsumoEnergia sin datos
	 * inicializados. <br>
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergia con todos sus atributos
	 * en cero o null. <br>
	 */
	public RegistroConsumoEnergia() {
	}

	/**
	 * Constructor con valores de consumo. Crea un RegistroConsumoEnergia con los
	 * valores de energia sin datos de RegistroConsumo. <br>
	 * <b>pre</b> Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergia con los valores de
	 * consumo inicializados y los atributos heredados en null. <br>
	 *
	 * @param consumoKwh         Consumo bruto de energia en kWh. consumoKwh >= 0
	 * @param generacionSolarKwh Energia solar generada en kWh. generacionSolarKwh
	 *                           >= 0
	 * @param consumoNetoKwh     Consumo neto de energia en kWh. consumoNetoKwh >= 0
	 */
	public RegistroConsumoEnergia(double consumoKwh, double generacionSolarKwh, double consumoNetoKwh) {
		super();
		this.consumoKwh = consumoKwh;
		this.generacionSolarKwh = generacionSolarKwh;
		this.consumoNetoKwh = consumoNetoKwh;
	}

	/**
	 * Constructor completo. Crea un RegistroConsumoEnergia con todos sus atributos
	 * inicializados. El consumo neto se calcula automaticamente. <br>
	 * <b>pre</b> Los parametros id, periodo y registradoPor no deben ser null ni
	 * vacios. Los valores de consumo deben ser mayores o iguales a cero. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergia completamente
	 * inicializado con el consumo neto calculado. <br>
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
	 * @param consumoKwh         Consumo bruto de energia en kWh. consumoKwh >= 0
	 * @param generacionSolarKwh Energia solar generada en kWh. generacionSolarKwh
	 *                           >= 0
	 */
	public RegistroConsumoEnergia(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor, double consumoKwh, double generacionSolarKwh) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
		this.consumoKwh = consumoKwh;
		this.generacionSolarKwh = generacionSolarKwh;
		this.consumoNetoKwh = consumoKwh - generacionSolarKwh;
	}

	/**
	 * Constructor con datos base. Crea un RegistroConsumoEnergia con los datos
	 * heredados de RegistroConsumo sin los valores de consumo. <br>
	 * <b>pre</b> Los parametros id, periodo y registradoPor no deben ser null ni
	 * vacios. <br>
	 * <b>post</b> Se crea un objeto RegistroConsumoEnergia con los datos de
	 * RegistroConsumo inicializados y los de consumo en cero. <br>
	 *
	 * @param id            Identificador unico del registro. id != null, id != ""
	 * @param conjunto      Conjunto residencial al que pertenece. conjunto != null
	 * @param periodo       Periodo del registro. periodo != null, periodo != ""
	 * @param fechaRegistro Fecha del registro. fechaRegistro != null
	 * @param registradoPor Identificador del usuario que registra. registradoPor !=
	 *                      null
	 */
	public RegistroConsumoEnergia(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
	}

	/**
	 * Retorna el consumo bruto de energia en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el consumo bruto de energia en kWh
	 */
	public double getConsumoKwh() {
		return consumoKwh;
	}

	/**
	 * Asigna el consumo bruto de energia en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> El atributo consumoKwh queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param consumoKwh Nuevo consumo bruto en kWh. consumoKwh >= 0
	 */
	public void setConsumoKwh(double consumoKwh) {
		this.consumoKwh = consumoKwh;
	}

	/**
	 * Retorna la energia generada por los paneles solares en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con la generacion solar en kWh
	 */
	public double getGeneracionSolarKwh() {
		return generacionSolarKwh;
	}

	/**
	 * Asigna la energia generada por los paneles solares en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> El atributo generacionSolarKwh queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param generacionSolarKwh Nueva generacion solar en kWh. generacionSolarKwh
	 *                           >= 0
	 */
	public void setGeneracionSolarKwh(double generacionSolarKwh) {
		this.generacionSolarKwh = generacionSolarKwh;
	}

	/**
	 * Retorna el consumo neto de energia en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return double con el consumo neto de energia en kWh
	 */
	public double getConsumoNetoKwh() {
		return consumoNetoKwh;
	}

	/**
	 * Asigna el consumo neto de energia en kilovatios-hora. <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> El atributo consumoNetoKwh queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param consumoNetoKwh Nuevo consumo neto en kWh. consumoNetoKwh >= 0
	 */
	public void setConsumoNetoKwh(double consumoNetoKwh) {
		this.consumoNetoKwh = consumoNetoKwh;
	}

	/**
	 * Retorna una representacion en texto del registro con sus datos principales.
	 * <br>
	 * <b>pre</b> El objeto RegistroConsumoEnergia debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con los datos de RegistroConsumo y los valores de energia
	 */
	@Override
	public String toString() {
		return "RegistroConsumoEnergia [" + super.toString() + ", consumoKwh=" + consumoKwh + ", generacionSolarKwh="
				+ generacionSolarKwh + ", consumoNetoKwh=" + consumoNetoKwh + "]";
	}
}