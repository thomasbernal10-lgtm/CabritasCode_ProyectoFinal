package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Objeto de Transferencia de Datos (DTO) que representa una unidad habitacional
 * dentro de una torre del conjunto residencial. Esta clase se utiliza para
 * transportar la informacion de un apartamento entre las capas del sistema
 * (modelo, controlador, vista) sin exponer directamente la entidad de dominio.
 * <b>pre</b> La torre referenciada debe estar registrada en el sistema. <br>
 * <b>post</b> El DTO queda disponible para ser usado en operaciones de lectura,
 * creacion o actualizacion de apartamentos en el sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ApartamentoDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 9212855924975847121L;

	/**
	 * Constante que representa el estado del apartamento cuando es habitado
	 * directamente por su propietario.
	 */
	private final String ESTADO_OCUPADO_PROPIETARIO = "OCUPADO_PROPIETARIO";

	/**
	 * Constante que representa el estado del apartamento cuando esta siendo
	 * arrendado.
	 */
	private final String ESTADO_ARRENDADO = "ARRENDADO";

	/**
	 * Constante que representa el estado del apartamento cuando no tiene ningun
	 * residente activo.
	 */
	private final String ESTADO_DESOCUPADO = "DESOCUPADO";

	/** Identificador unico del apartamento. */
	private String id;

	/** Numero o codigo del apartamento dentro de la torre. */
	private String numero;

	/** Numero de piso en el que se encuentra el apartamento. */
	private int piso;

	/** Torre del conjunto residencial a la que pertenece este apartamento. */
	private Torre torre;

	/**
	 * Estado actual del apartamento (OCUPADO_PROPIETARIO, ARRENDADO o DESOCUPADO).
	 */
	private String estado;

	/**
	 * Propietario registrado de este apartamento. Puede ser null si no esta
	 * asignado.
	 */
	private Propietario propietario;

	/** Arrendatario actual del apartamento. Puede ser null si no esta arrendado. */
	private Arrendatario arrendatario;

	/**
	 * Constructor por defecto. Crea un DTO de apartamento sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto ApartamentoDTO con todos sus atributos en null
	 * o valores por defecto. <br>
	 */
	public ApartamentoDTO() {
	}

	/**
	 * Constructor completo. Crea un DTO de apartamento con todos sus atributos
	 * inicializados. <b>pre</b> Los parametros id, numero y estado no deben ser
	 * null ni vacios. La torre debe estar previamente registrada en el sistema.
	 * <br>
	 * <b>post</b> Se crea un objeto ApartamentoDTO completamente inicializado y
	 * listo para ser transferido entre capas del sistema. <br>
	 * 
	 * @param id           Identificador unico del apartamento. id != null, id != ""
	 * @param numero       Numero o codigo del apartamento. numero != null, numero
	 *                     != ""
	 * @param piso         Numero de piso donde se ubica el apartamento. piso >= 1
	 * @param torre        Torre del conjunto a la que pertenece el apartamento.
	 *                     torre != null
	 * @param estado       Estado inicial del apartamento. estado != null, estado !=
	 *                     ""
	 * @param propietario  Propietario del apartamento. Puede ser null si no esta
	 *                     asignado aun
	 * @param arrendatario Arrendatario del apartamento. Puede ser null si no esta
	 *                     arrendado
	 */
	public ApartamentoDTO(String id, String numero, int piso, Torre torre, String estado, Propietario propietario,
			Arrendatario arrendatario) {
		super();
		this.id = id;
		this.numero = numero;
		this.piso = piso;
		this.torre = torre;
		this.estado = estado;
		this.propietario = propietario;
		this.arrendatario = arrendatario;
	}

	/**
	 * Retorna la constante que representa el estado de apartamento ocupado por su
	 * propietario. <b>pre</b> El objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "OCUPADO_PROPIETARIO"
	 */
	public String getESTADO_OCUPADO_PROPIETARIO() {
		return ESTADO_OCUPADO_PROPIETARIO;
	}

	/**
	 * Retorna la constante que representa el estado de apartamento arrendado.
	 * <b>pre</b> El objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ARRENDADO"
	 */
	public String getESTADO_ARRENDADO() {
		return ESTADO_ARRENDADO;
	}

	/**
	 * Retorna la constante que representa el estado de apartamento desocupado.
	 * <b>pre</b> El objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "DESOCUPADO"
	 */
	public String getESTADO_DESOCUPADO() {
		return ESTADO_DESOCUPADO;
	}

	/**
	 * Retorna el identificador unico del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del apartamento
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del apartamento. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el numero o codigo del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el numero del apartamento
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Asigna el numero o codigo del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo numero queda actualizado con el valor recibido. <br>
	 * 
	 * @param numero Nuevo numero del apartamento. numero != null, numero != ""
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retorna el numero de piso del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return int con el numero de piso
	 */
	public int getPiso() {
		return piso;
	}

	/**
	 * Asigna el numero de piso del apartamento. <b>pre</b> El objeto ApartamentoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo piso queda actualizado con el valor recibido. <br>
	 * 
	 * @param piso Nuevo numero de piso. piso >= 1
	 */
	public void setPiso(int piso) {
		this.piso = piso;
	}

	/**
	 * Retorna la torre del conjunto a la que pertenece el apartamento. <b>pre</b>
	 * El objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Torre asociado al apartamento, o null si no tiene torre
	 *         asignada
	 */
	public Torre getTorre() {
		return torre;
	}

	/**
	 * Asigna la torre del conjunto a la que pertenece el apartamento. <b>pre</b> El
	 * objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo torre queda actualizado con el objeto recibido. <br>
	 * 
	 * @param torre Torre a la que pertenece el apartamento. torre != null
	 */
	public void setTorre(Torre torre) {
		this.torre = torre;
	}

	/**
	 * Retorna el estado actual del apartamento. <b>pre</b> El objeto ApartamentoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado actual (OCUPADO_PROPIETARIO, ARRENDADO o
	 *         DESOCUPADO)
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual del apartamento. <b>pre</b> El objeto ApartamentoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado del apartamento. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el propietario registrado del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Propietario asociado, o null si no tiene propietario asignado
	 */
	public Propietario getPropietario() {
		return propietario;
	}

	/**
	 * Asigna el propietario del apartamento. <b>pre</b> El objeto ApartamentoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo propietario queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param propietario Propietario a asignar. Puede ser null para desasociar el
	 *                    propietario actual
	 */
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	/**
	 * Retorna el arrendatario actual del apartamento. <b>pre</b> El objeto
	 * ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Arrendatario asociado, o null si el apartamento no esta
	 *         arrendado
	 */
	public Arrendatario getArrendatario() {
		return arrendatario;
	}

	/**
	 * Asigna el arrendatario del apartamento. <b>pre</b> El objeto ApartamentoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo arrendatario queda actualizado con el objeto
	 * recibido. <br>
	 * 
	 * @param arrendatario Arrendatario a asignar. Puede ser null para desasociar el
	 *                     arrendatario actual
	 */
	public void setArrendatario(Arrendatario arrendatario) {
		this.arrendatario = arrendatario;
	}

	/**
	 * Retorna una representacion en texto del DTO con los datos principales del
	 * apartamento. <b>pre</b> El objeto ApartamentoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, numero, piso, torre y estado del apartamento
	 */
	@Override
	public String toString() {
		return "Apartamento [id=" + id + ", numero=" + numero + ", piso=" + piso + ", torre=" + torre + ", estado="
				+ estado + "]";
	}

}