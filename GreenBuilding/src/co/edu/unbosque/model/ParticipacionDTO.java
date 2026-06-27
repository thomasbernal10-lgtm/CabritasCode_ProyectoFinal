package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa un objeto de transferencia de datos (DTO) para las participaciones
 * registradas en campañas ambientales dentro del sistema GreenBuilding Manager.
 * Permite transportar informacion relacionada con la participacion de
 * residentes entre las diferentes capas de la aplicacion sin exponer
 * directamente la logica de negocio de la entidad Participacion.
 * 
 * Cada objeto ParticipacionDTO almacena informacion relacionada con la campaña
 * ambiental, el apartamento asociado, el residente participante, la fecha de
 * participacion y observaciones adicionales. <b>pre</b> La campaña ambiental,
 * el apartamento y el residente deben estar registrados previamente en el
 * sistema. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * persistencia y visualizacion de informacion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ParticipacionDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 6866146030888366410L;

	/** Identificador unico de la participacion. */
	private String id;

	/** Campaña ambiental asociada a la participacion. */
	private CampanaAmbiental campana;

	/** Apartamento relacionado con el residente participante. */
	private Apartamento apartamento;

	/** Residente que participa en la campaña ambiental. */
	private Residente residente;

	/** Fecha en la que se realizo la participacion. */
	private LocalDate fechaParticipacion;

	/** Observaciones adicionales relacionadas con la participacion. */
	private String observacion;

	/**
	 * Constructor por defecto. Crea un objeto ParticipacionDTO sin datos
	 * inicializados. <b>pre</b> No existen precondiciones para este constructor.
	 * <br>
	 * <b>post</b> Se crea un objeto ParticipacionDTO con todos sus atributos en
	 * null o valores por defecto. <br>
	 */
	public ParticipacionDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto ParticipacionDTO con todos sus atributos
	 * principales inicializados. <b>pre</b> Los parametros id, campana,
	 * apartamento, residente y fechaParticipacion no deben ser null. <br>
	 * <b>post</b> Se crea un objeto ParticipacionDTO completamente inicializado y
	 * listo para ser utilizado en procesos de transferencia de datos dentro del
	 * sistema. <br>
	 * 
	 * @param id                 Identificador unico de la participacion. id !=
	 *                           null, id != ""
	 * @param campana            Campaña ambiental asociada. campana != null
	 * @param apartamento        Apartamento relacionado con la participacion.
	 *                           apartamento != null
	 * @param residente          Residente participante. residente != null
	 * @param fechaParticipacion Fecha de participacion. fechaParticipacion != null
	 * @param observacion        Observaciones relacionadas con la participacion.
	 *                           Puede ser null
	 */
	public ParticipacionDTO(String id, CampanaAmbiental campana, Apartamento apartamento, Residente residente,
			LocalDate fechaParticipacion, String observacion) {
		this.id = id;
		this.campana = campana;
		this.apartamento = apartamento;
		this.residente = residente;
		this.fechaParticipacion = fechaParticipacion;
		this.observacion = observacion;
	}

	/**
	 * Retorna el identificador unico de la participacion. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id de la participacion
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico de la participacion. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador de la participacion. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna la campaña ambiental asociada a la participacion. <b>pre</b> El
	 * objeto ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto CampanaAmbiental asociado a la participacion
	 */
	public CampanaAmbiental getCampana() {
		return campana;
	}

	/**
	 * Asigna la campaña ambiental asociada a la participacion. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo campana queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param campana Nueva campaña ambiental. campana != null
	 */
	public void setCampana(CampanaAmbiental campana) {
		this.campana = campana;
	}

	/**
	 * Retorna el apartamento asociado a la participacion. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Apartamento asociado a la participacion
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado a la participacion. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamento queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param apartamento Nuevo apartamento asociado. apartamento != null
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna el residente participante en la campaña ambiental. <b>pre</b> El
	 * objeto ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Residente participante
	 */
	public Residente getResidente() {
		return residente;
	}

	/**
	 * Asigna el residente participante en la campaña ambiental. <b>pre</b> El
	 * objeto ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo residente queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param residente Nuevo residente participante. residente != null
	 */
	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	/**
	 * Retorna la fecha de participacion registrada. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto LocalDate con la fecha de participacion
	 */
	public LocalDate getFechaParticipacion() {
		return fechaParticipacion;
	}

	/**
	 * Asigna la fecha de participacion registrada. <b>pre</b> El objeto
	 * ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaParticipacion queda actualizado con el valor
	 * recibido. <br>
	 * 
	 * @param fechaParticipacion Nueva fecha de participacion. fechaParticipacion !=
	 *                           null
	 */
	public void setFechaParticipacion(LocalDate fechaParticipacion) {
		this.fechaParticipacion = fechaParticipacion;
	}

	/**
	 * Retorna las observaciones registradas sobre la participacion. <b>pre</b> El
	 * objeto ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con las observaciones de la participacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Asigna las observaciones relacionadas con la participacion. <b>pre</b> El
	 * objeto ParticipacionDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo observacion queda actualizado con el valor recibido.
	 * <br>
	 * 
	 * @param observacion Nueva observacion de la participacion. Puede ser null
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Retorna una representacion en texto de la participacion con sus datos
	 * principales. <b>pre</b> El objeto ParticipacionDTO debe estar instanciado.
	 * <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, fecha de participacion y observaciones registradas
	 */
	@Override
	public String toString() {
		return "Participacion [id=" + id + ", fechaParticipacion=" + fechaParticipacion + ", observacion=" + observacion
				+ "]";
	}

}