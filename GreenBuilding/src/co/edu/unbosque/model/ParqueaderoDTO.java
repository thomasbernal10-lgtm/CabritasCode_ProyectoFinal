package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Representa un objeto de transferencia de datos (DTO) para los parqueaderos
 * registrados dentro del sistema GreenBuilding Manager. Permite transportar
 * informacion relacionada con los parqueaderos entre las diferentes capas de la
 * aplicacion sin exponer directamente la logica de negocio de la entidad
 * Parqueadero.
 * 
 * Cada objeto ParqueaderoDTO almacena informacion relacionada con el numero,
 * tipo, estado, conjunto al que pertenece y el vehiculo asignado al
 * parqueadero. Esto facilita el control y administracion de espacios de
 * estacionamiento dentro del conjunto residencial. <b>pre</b> El conjunto
 * asociado debe existir previamente en el sistema. <br>
 * <b>post</b> El objeto queda disponible para procesos de transferencia,
 * persistencia y visualizacion de informacion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ParqueaderoDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -6603166218560378339L;

	/**
	 * Constante que representa el tipo de parqueadero para carro.
	 */
	private final String TIPO_CARRO = "CARRO";

	/**
	 * Constante que representa el tipo de parqueadero para moto.
	 */
	private final String TIPO_MOTO = "MOTO";

	/**
	 * Constante que representa el tipo de parqueadero para bicicleta.
	 */
	private final String TIPO_BICICLETA = "BICICLETA";

	/**
	 * Constante que representa el tipo de parqueadero para visitantes.
	 */
	private final String TIPO_VISITANTE = "VISITANTE";

	/**
	 * Constante que representa el tipo de parqueadero electrico.
	 */
	private final String TIPO_ELECTRICO = "ELECTRICO";

	/**
	 * Constante que representa el estado disponible del parqueadero.
	 */
	private final String ESTADO_DISPONIBLE = "DISPONIBLE";

	/**
	 * Constante que representa el estado ocupado del parqueadero.
	 */
	private final String ESTADO_OCUPADO = "OCUPADO";

	/**
	 * Constante que representa el estado de mantenimiento del parqueadero.
	 */
	private final String ESTADO_MANTENIMIENTO = "MANTENIMIENTO";

	/** Identificador unico del parqueadero. */
	private String id;

	/** Numero identificador del parqueadero. */
	private String numero;

	/** Tipo de parqueadero registrado. */
	private String tipo;

	/** Estado actual del parqueadero. */
	private String estado;

	/** Conjunto residencial al que pertenece el parqueadero. */
	private Conjunto conjunto;

	/** Vehiculo actualmente asignado al parqueadero. */
	private Vehiculo vehiculoAsignado;

	/**
	 * Constructor por defecto. Crea un objeto ParqueaderoDTO sin datos
	 * inicializados. <b>pre</b> No existen precondiciones para este constructor.
	 * <br>
	 * <b>post</b> Se crea un objeto ParqueaderoDTO con todos sus atributos en null
	 * o valores por defecto. <br>
	 */
	public ParqueaderoDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto ParqueaderoDTO con sus atributos
	 * principales inicializados. <b>pre</b> Los parametros id, numero, tipo y
	 * estado no deben ser null ni vacios. El conjunto debe existir previamente en
	 * el sistema. <br>
	 * <b>post</b> Se crea un objeto ParqueaderoDTO completamente inicializado y
	 * listo para ser utilizado en procesos de transferencia de datos. <br>
	 * 
	 * @param id       Identificador unico del parqueadero. id != null, id != ""
	 * @param numero   Numero identificador del parqueadero. numero != null, numero
	 *                 != ""
	 * @param tipo     Tipo de parqueadero registrado. tipo != null, tipo != ""
	 * @param estado   Estado actual del parqueadero. estado != null, estado != ""
	 * @param conjunto Conjunto asociado al parqueadero. conjunto != null
	 */
	public ParqueaderoDTO(String id, String numero, String tipo, String estado, Conjunto conjunto) {
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.estado = estado;
		this.conjunto = conjunto;
		this.vehiculoAsignado = null;
	}

	/**
	 * Retorna la constante correspondiente al tipo de parqueadero CARRO. <b>pre</b>
	 * El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "CARRO"
	 */
	public String getTIPO_CARRO() {
		return TIPO_CARRO;
	}

	/**
	 * Retorna la constante correspondiente al tipo de parqueadero MOTO. <b>pre</b>
	 * El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "MOTO"
	 */
	public String getTIPO_MOTO() {
		return TIPO_MOTO;
	}

	/**
	 * Retorna la constante correspondiente al tipo de parqueadero BICICLETA.
	 * <b>pre</b> El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "BICICLETA"
	 */
	public String getTIPO_BICICLETA() {
		return TIPO_BICICLETA;
	}

	/**
	 * Retorna la constante correspondiente al tipo de parqueadero VISITANTE.
	 * <b>pre</b> El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "VISITANTE"
	 */
	public String getTIPO_VISITANTE() {
		return TIPO_VISITANTE;
	}

	/**
	 * Retorna la constante correspondiente al tipo de parqueadero ELECTRICO.
	 * <b>pre</b> El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "ELECTRICO"
	 */
	public String getTIPO_ELECTRICO() {
		return TIPO_ELECTRICO;
	}

	/**
	 * Retorna la constante correspondiente al estado DISPONIBLE. <b>pre</b> El
	 * objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "DISPONIBLE"
	 */
	public String getESTADO_DISPONIBLE() {
		return ESTADO_DISPONIBLE;
	}

	/**
	 * Retorna la constante correspondiente al estado OCUPADO. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "OCUPADO"
	 */
	public String getESTADO_OCUPADO() {
		return ESTADO_OCUPADO;
	}

	/**
	 * Retorna la constante correspondiente al estado MANTENIMIENTO. <b>pre</b> El
	 * objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el valor "MANTENIMIENTO"
	 */
	public String getESTADO_MANTENIMIENTO() {
		return ESTADO_MANTENIMIENTO;
	}

	/**
	 * Retorna el identificador unico del parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id del parqueadero
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 * 
	 * @param id Nuevo identificador del parqueadero. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el numero identificador del parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el numero del parqueadero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Asigna el numero identificador del parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo numero queda actualizado con el valor recibido. <br>
	 * 
	 * @param numero Nuevo numero del parqueadero. numero != null, numero != ""
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retorna el tipo del parqueadero. <b>pre</b> El objeto ParqueaderoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el tipo del parqueadero
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo del parqueadero. <b>pre</b> El objeto ParqueaderoDTO debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 * 
	 * @param tipo Nuevo tipo del parqueadero. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el estado actual del parqueadero. <b>pre</b> El objeto ParqueaderoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el estado del parqueadero
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual del parqueadero. <b>pre</b> El objeto ParqueaderoDTO
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 * 
	 * @param estado Nuevo estado del parqueadero. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el conjunto asociado al parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Conjunto asociado al parqueadero
	 */
	public Conjunto getConjunto() {
		return conjunto;
	}

	/**
	 * Asigna el conjunto asociado al parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo conjunto queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param conjunto Nuevo conjunto asociado. conjunto != null
	 */
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * Retorna el vehiculo asignado al parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return Objeto Vehiculo asignado al parqueadero
	 */
	public Vehiculo getVehiculoAsignado() {
		return vehiculoAsignado;
	}

	/**
	 * Asigna el vehiculo asociado al parqueadero. <b>pre</b> El objeto
	 * ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> El atributo vehiculoAsignado queda actualizado con el objeto
	 * recibido. <br>
	 * 
	 * @param vehiculoAsignado Nuevo vehiculo asignado. Puede ser null
	 */
	public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
		this.vehiculoAsignado = vehiculoAsignado;
	}

	/**
	 * Retorna una representacion en texto del parqueadero con sus datos
	 * principales. <b>pre</b> El objeto ParqueaderoDTO debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 * 
	 * @return String con el id, numero, tipo, estado y placa del vehiculo asignado
	 */
	@Override
	public String toString() {
		String placaVehiculo = "null";
		if (vehiculoAsignado != null) {
			placaVehiculo = vehiculoAsignado.getPlaca();
		}
		return "Parqueadero [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", estado=" + estado
				+ ", vehiculoAsignado=" + placaVehiculo + "]";
	}

}