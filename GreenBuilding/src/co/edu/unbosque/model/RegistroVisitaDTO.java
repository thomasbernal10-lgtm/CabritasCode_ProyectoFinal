package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa un objeto de transferencia de datos (DTO) para los registros de
 * visitas dentro del sistema GreenBuilding Manager. Esta clase permite
 * transportar informacion relacionada con visitantes, horarios de ingreso y
 * salida, y vigilantes encargados del registro entre las diferentes capas de la
 * aplicacion sin exponer directamente la logica de negocio.
 * 
 * Los objetos RegistroVisitaDTO son utilizados para procesos de persistencia,
 * consultas y visualizacion de informacion relacionada con el control de acceso
 * al conjunto residencial. <b>pre</b> El visitante debe estar registrado
 * previamente en el sistema. Las fechas de entrada y salida deben ser validas.
 * <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia y
 * manejo administrativo de visitas dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class RegistroVisitaDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 6231229057256755119L;

	/** Identificador unico del registro de visita. */
	private String id;

	/** Visitante asociado al registro. */
	private Visitante visitante;

	/** Fecha y hora de entrada del visitante. */
	private LocalDateTime horaEntrada;

	/** Fecha y hora de salida del visitante. */
	private LocalDateTime horaSalida;

	/** Nombre del vigilante encargado del registro. */
	private String vigilanteRegistro;

	/**
	 * Constructor por defecto. Crea un objeto RegistroVisitaDTO sin datos
	 * inicializados. <b>pre</b> No existen precondiciones para este constructor.
	 * <br>
	 * <b>post</b> Se crea un objeto RegistroVisitaDTO con todos sus atributos en
	 * null o valores por defecto. <br>
	 */
	public RegistroVisitaDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto RegistroVisitaDTO con todos sus
	 * atributos inicializados. <b>pre</b> El id, visitante, horaEntrada y
	 * vigilanteRegistro no deben ser null. <br>
	 * <b>post</b> Se crea un objeto RegistroVisitaDTO completamente inicializado y
	 * listo para ser utilizado dentro del sistema. <br>
	 * 
	 * @param id                Identificador unico del registro. id != null, id !=
	 *                          ""
	 * @param visitante         Visitante asociado al registro. visitante != null
	 * @param horaEntrada       Fecha y hora de entrada del visitante. horaEntrada
	 *                          != null
	 * @param horaSalida        Fecha y hora de salida del visitante. Puede ser null
	 * @param vigilanteRegistro Nombre del vigilante encargado del registro.
	 *                          vigilanteRegistro != null, vigilanteRegistro != ""
	 */
	public RegistroVisitaDTO(String id, Visitante visitante, LocalDateTime horaEntrada, LocalDateTime horaSalida,
			String vigilanteRegistro) {
		this.id = id;
		this.visitante = visitante;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.vigilanteRegistro = vigilanteRegistro;
	}

	/**
	 * Retorna el identificador unico del registro de visita. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del registro de visita
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del registro de visita. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del registro. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el visitante asociado al registro. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Visitante asociado al registro
	 */
	public Visitante getVisitante() {
		return visitante;
	}

	/**
	 * Asigna el visitante asociado al registro. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo visitante queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param visitante Nuevo visitante asociado. visitante != null
	 */
	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	/**
	 * Retorna la fecha y hora de entrada del visitante. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la hora de entrada
	 */
	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	/**
	 * Asigna la fecha y hora de entrada del visitante. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo horaEntrada queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param horaEntrada Nueva fecha y hora de entrada. horaEntrada != null
	 */
	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	/**
	 * Retorna la fecha y hora de salida del visitante. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDateTime con la hora de salida
	 */
	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}

	/**
	 * Asigna la fecha y hora de salida del visitante. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo horaSalida queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param horaSalida Nueva fecha y hora de salida. Puede ser null
	 */
	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	/**
	 * Retorna el nombre del vigilante encargado del registro. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el nombre del vigilante encargado
	 */
	public String getVigilanteRegistro() {
		return vigilanteRegistro;
	}

	/**
	 * Asigna el nombre del vigilante encargado del registro. <b>pre</b> El objeto
	 * RegistroVisitaDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo vigilanteRegistro queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param vigilanteRegistro Nuevo nombre del vigilante encargado.
	 *                          vigilanteRegistro != null, vigilanteRegistro != ""
	 */
	public void setVigilanteRegistro(String vigilanteRegistro) {
		this.vigilanteRegistro = vigilanteRegistro;
	}

	/**
	 * Retorna una representacion en texto del registro de visita con sus datos
	 * principales. <b>pre</b> El objeto RegistroVisitaDTO debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, visitante, hora de entrada, hora de salida y
	 *         vigilante encargado
	 */
	@Override
	public String toString() {
		return "RegistroVisita [id=" + id + ", visitante=" + visitante + ", horaEntrada=" + horaEntrada
				+ ", horaSalida=" + horaSalida + ", vigilanteRegistro=" + vigilanteRegistro + "]";
	}

}