package co.edu.unbosque.model;

import java.time.LocalDate;

/**
 * Representa un registro de reciclaje dentro del sistema GreenBuilding Manager.
 * Esta clase hereda de RegistroConsumo y permite almacenar informacion
 * relacionada con la cantidad de residuos reciclados por categoria dentro de un
 * conjunto residencial.
 * 
 * Cada registro almacena cantidades en kilogramos de papel, plastico, vidrio,
 * metal y residuos organicos, asi como el total general reciclado durante un
 * periodo especifico. <b>pre</b> El conjunto asociado debe estar registrado
 * previamente en el sistema. Las cantidades registradas no deben ser negativas.
 * <br>
 * <b>post</b> El registro de reciclaje queda disponible para consultas,
 * reportes ambientales y procesos administrativos relacionados con
 * sostenibilidad. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroReciclaje extends RegistroConsumo {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -860023716836111402L;

	/** Cantidad de papel reciclado en kilogramos. */
	private double kgPapel;

	/** Cantidad de plastico reciclado en kilogramos. */
	private double kgPlastico;

	/** Cantidad de vidrio reciclado en kilogramos. */
	private double kgVidrio;

	/** Cantidad de metal reciclado en kilogramos. */
	private double kgMetal;

	/** Cantidad de residuos organicos reciclados en kilogramos. */
	private double kgOrganico;

	/** Cantidad total reciclada en kilogramos. */
	private double totalKg;

	/**
	 * Constructor por defecto. Crea un objeto RegistroReciclaje sin datos
	 * inicializados. <b>pre</b> No existen precondiciones para este constructor.
	 * <br>
	 * <b>post</b> Se crea un objeto RegistroReciclaje con todos sus atributos en
	 * valores por defecto. <br>
	 */
	public RegistroReciclaje() {
	}

	/**
	 * Constructor parcial. Crea un objeto RegistroReciclaje inicializando las
	 * cantidades recicladas registradas. <b>pre</b> Las cantidades registradas no
	 * deben ser negativas. <br>
	 * <b>post</b> Se crea un objeto RegistroReciclaje con los datos de reciclaje
	 * inicializados. <br>
	 * 
	 * @param kgPapel    Cantidad de papel reciclado. kgPapel >= 0
	 * @param kgPlastico Cantidad de plastico reciclado. kgPlastico >= 0
	 * @param kgVidrio   Cantidad de vidrio reciclado. kgVidrio >= 0
	 * @param kgMetal    Cantidad de metal reciclado. kgMetal >= 0
	 * @param kgOrganico Cantidad de residuos organicos reciclados. kgOrganico >= 0
	 * @param totalKg    Cantidad total reciclada. totalKg >= 0
	 */
	public RegistroReciclaje(double kgPapel, double kgPlastico, double kgVidrio, double kgMetal, double kgOrganico,
			double totalKg) {
		super();
		this.kgPapel = kgPapel;
		this.kgPlastico = kgPlastico;
		this.kgVidrio = kgVidrio;
		this.kgMetal = kgMetal;
		this.kgOrganico = kgOrganico;
		this.totalKg = totalKg;
	}

	/**
	 * Constructor completo. Crea un objeto RegistroReciclaje con todos sus
	 * atributos inicializados. <b>pre</b> El id, conjunto, periodo, fechaRegistro y
	 * registradoPor no deben ser null. Las cantidades registradas no deben ser
	 * negativas. <br>
	 * <b>post</b> Se crea un objeto RegistroReciclaje completamente inicializado y
	 * listo para ser utilizado dentro del sistema. <br>
	 * 
	 * @param id            Identificador unico del registro. id != null, id != ""
	 * @param conjunto      Conjunto asociado al registro. conjunto != null
	 * @param periodo       Periodo correspondiente al registro. periodo != null,
	 *                      periodo != ""
	 * @param fechaRegistro Fecha de registro. fechaRegistro != null
	 * @param registradoPor Persona que realizo el registro. registradoPor != null,
	 *                      registradoPor != ""
	 * @param kgPapel       Cantidad de papel reciclado. kgPapel >= 0
	 * @param kgPlastico    Cantidad de plastico reciclado. kgPlastico >= 0
	 * @param kgVidrio      Cantidad de vidrio reciclado. kgVidrio >= 0
	 * @param kgMetal       Cantidad de metal reciclado. kgMetal >= 0
	 * @param kgOrganico    Cantidad de residuos organicos reciclados. kgOrganico >=
	 *                      0
	 * @param totalKg       Cantidad total reciclada. totalKg >= 0
	 */
	public RegistroReciclaje(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor, double kgPapel, double kgPlastico, double kgVidrio, double kgMetal, double kgOrganico,
			double totalKg) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
		this.kgPapel = kgPapel;
		this.kgPlastico = kgPlastico;
		this.kgVidrio = kgVidrio;
		this.kgMetal = kgMetal;
		this.kgOrganico = kgOrganico;
		this.totalKg = totalKg;
	}

	/**
	 * Constructor basico. Crea un objeto RegistroReciclaje heredando informacion
	 * general de RegistroConsumo. <b>pre</b> El id, conjunto, periodo,
	 * fechaRegistro y registradoPor no deben ser null. <br>
	 * <b>post</b> Se crea un objeto RegistroReciclaje con la informacion general
	 * inicializada. <br>
	 * 
	 * @param id            Identificador unico del registro. id != null, id != ""
	 * @param conjunto      Conjunto asociado al registro. conjunto != null
	 * @param periodo       Periodo correspondiente al registro. periodo != null,
	 *                      periodo != ""
	 * @param fechaRegistro Fecha de registro. fechaRegistro != null
	 * @param registradoPor Persona que realizo el registro. registradoPor != null,
	 *                      registradoPor != ""
	 */
	public RegistroReciclaje(String id, Conjunto conjunto, String periodo, LocalDate fechaRegistro,
			String registradoPor) {
		super(id, conjunto, periodo, fechaRegistro, registradoPor);
	}

	/**
	 * Retorna la cantidad de papel reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con los kilogramos de papel reciclado
	 */
	public double getKgPapel() {
		return kgPapel;
	}

	/**
	 * Asigna la cantidad de papel reciclado. <b>pre</b> El objeto RegistroReciclaje
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo kgPapel queda actualizado con el valor recibido. <br>
	 * 
	 * @param kgPapel Nueva cantidad de papel reciclado. kgPapel >= 0
	 */
	public void setKgPapel(double kgPapel) {
		this.kgPapel = kgPapel;
	}

	/**
	 * Retorna la cantidad de plastico reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con los kilogramos de plastico reciclado
	 */
	public double getKgPlastico() {
		return kgPlastico;
	}

	/**
	 * Asigna la cantidad de plastico reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> El atributo kgPlastico queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param kgPlastico Nueva cantidad de plastico reciclado. kgPlastico >= 0
	 */
	public void setKgPlastico(double kgPlastico) {
		this.kgPlastico = kgPlastico;
	}

	/**
	 * Retorna la cantidad de vidrio reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con los kilogramos de vidrio reciclado
	 */
	public double getKgVidrio() {
		return kgVidrio;
	}

	/**
	 * Asigna la cantidad de vidrio reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> El atributo kgVidrio queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param kgVidrio Nueva cantidad de vidrio reciclado. kgVidrio >= 0
	 */
	public void setKgVidrio(double kgVidrio) {
		this.kgVidrio = kgVidrio;
	}

	/**
	 * Retorna la cantidad de metal reciclado. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con los kilogramos de metal reciclado
	 */
	public double getKgMetal() {
		return kgMetal;
	}

	/**
	 * Asigna la cantidad de metal reciclado. <b>pre</b> El objeto RegistroReciclaje
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo kgMetal queda actualizado con el valor recibido. <br>
	 * 
	 * @param kgMetal Nueva cantidad de metal reciclado. kgMetal >= 0
	 */
	public void setKgMetal(double kgMetal) {
		this.kgMetal = kgMetal;
	}

	/**
	 * Retorna la cantidad total reciclada. <b>pre</b> El objeto RegistroReciclaje
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con la cantidad total reciclada
	 */
	public double getTotalKg() {
		return totalKg;
	}

	/**
	 * Asigna la cantidad total reciclada. <b>pre</b> El objeto RegistroReciclaje
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo totalKg queda actualizado con el valor recibido. <br>
	 * 
	 * @param totalKg Nueva cantidad total reciclada. totalKg >= 0
	 */
	public void setTotalKg(double totalKg) {
		this.totalKg = totalKg;
	}

	/**
	 * Retorna la cantidad de residuos organicos reciclados. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return double con los kilogramos de residuos organicos reciclados
	 */
	public double getKgOrganico() {
		return kgOrganico;
	}

	/**
	 * Asigna la cantidad de residuos organicos reciclados. <b>pre</b> El objeto
	 * RegistroReciclaje debe estar instanciado. <br>
	 * <b>post</b> El atributo kgOrganico queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param kgOrganico Nueva cantidad de residuos organicos reciclados. kgOrganico
	 *                   >= 0
	 */
	public void setKgOrganico(double kgOrganico) {
		this.kgOrganico = kgOrganico;
	}

	/**
	 * Retorna una representacion en texto del registro de reciclaje con sus datos
	 * principales. <b>pre</b> El objeto RegistroReciclaje debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con la informacion principal del registro de reciclaje
	 */
	@Override
	public String toString() {
		return "RegistroReciclaje [" + super.toString() + ", kgPapel=" + kgPapel + ", kgPlastico=" + kgPlastico
				+ ", kgVidrio=" + kgVidrio + ", kgMetal=" + kgMetal + ", kgOrganico=" + kgOrganico + ", totalKg="
				+ totalKg + "]";
	}

}