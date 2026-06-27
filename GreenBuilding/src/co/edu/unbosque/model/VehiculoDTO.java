package co.edu.unbosque.model;

import java.io.Serializable;

/**
 * Representa un vehiculo DTO registrado dentro del sistema GreenBuilding
 * Manager. Esta clase permite transportar y gestionar la informacion
 * relacionada con los vehiculos asociados a los apartamentos del conjunto
 * residencial.
 * 
 * Un vehiculo puede ser autorizado o presentar restricciones de ingreso
 * dependiendo de las politicas del conjunto. <b>pre</b> Los datos principales
 * del vehiculo deben ser validos y el apartamento asociado debe existir
 * previamente en el sistema. <br>
 * <b>post</b> El vehiculo queda disponible para procesos de transferencia de
 * informacion y administracion dentro del sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class VehiculoDTO implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 9195430031625261756L;

	/** Tipo de vehiculo carro. */
	private final String TIPO_CARRO = "CARRO";

	/** Tipo de vehiculo moto. */
	private final String TIPO_MOTO = "MOTO";

	/** Tipo de vehiculo bicicleta. */
	private final String TIPO_BICICLETA = "BICICLETA";

	/** Identificador unico del vehiculo. */
	private String id;

	/** Placa del vehiculo. */
	private String placa;

	/** Tipo de vehiculo. */
	private String tipo;

	/** Marca del vehiculo. */
	private String marca;

	/** Modelo del vehiculo. */
	private String modelo;

	/** Color del vehiculo. */
	private String color;

	/** Apartamento asociado al vehiculo. */
	private Apartamento apartamento;

	/** Indica si el vehiculo esta autorizado. */
	private boolean autorizado;

	/** Indica si el vehiculo tiene restricciones. */
	private boolean tieneRestriccion;

	/**
	 * Constructor por defecto. Crea un objeto VehiculoDTO sin datos inicializados.
	 * <b>pre</b> No existen precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto VehiculoDTO con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public VehiculoDTO() {
	}

	/**
	 * Constructor completo. Crea un objeto VehiculoDTO con toda la informacion
	 * necesaria. <b>pre</b> El id, placa, tipo, marca, modelo y color no deben ser
	 * null. <br>
	 * <b>post</b> Se crea un objeto VehiculoDTO inicializado con la informacion
	 * suministrada. <br>
	 * 
	 * @param id               Identificador unico del vehiculo. id != null, id !=
	 *                         ""
	 * @param placa            Placa del vehiculo. placa != null, placa != ""
	 * @param tipo             Tipo de vehiculo. tipo != null, tipo != ""
	 * @param marca            Marca del vehiculo. marca != null, marca != ""
	 * @param modelo           Modelo del vehiculo. modelo != null, modelo != ""
	 * @param color            Color del vehiculo. color != null, color != ""
	 * @param apartamento      Apartamento asociado al vehiculo.
	 * @param autorizado       Estado de autorizacion del vehiculo.
	 * @param tieneRestriccion Estado de restriccion del vehiculo.
	 */
	public VehiculoDTO(String id, String placa, String tipo, String marca, String modelo, String color,
			Apartamento apartamento, boolean autorizado, boolean tieneRestriccion) {
		this.id = id;
		this.placa = placa;
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.apartamento = apartamento;
		this.autorizado = autorizado;
		this.tieneRestriccion = tieneRestriccion;
	}

	/**
	 * Retorna el tipo carro.
	 * 
	 * @return String con el tipo carro
	 */
	public String getTIPO_CARRO() {
		return TIPO_CARRO;
	}

	/**
	 * Retorna el tipo moto.
	 * 
	 * @return String con el tipo moto
	 */
	public String getTIPO_MOTO() {
		return TIPO_MOTO;
	}

	/**
	 * Retorna el tipo bicicleta.
	 * 
	 * @return String con el tipo bicicleta
	 */
	public String getTIPO_BICICLETA() {
		return TIPO_BICICLETA;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 * 
	 * @return Identificador del vehiculo
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador del vehiculo.
	 * 
	 * @param id Nuevo identificador del vehiculo
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna la placa del vehiculo.
	 * 
	 * @return Placa del vehiculo
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Asigna la placa del vehiculo.
	 * 
	 * @param placa Nueva placa del vehiculo
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Retorna el tipo del vehiculo.
	 * 
	 * @return Tipo del vehiculo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo del vehiculo.
	 * 
	 * @param tipo Nuevo tipo del vehiculo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la marca del vehiculo.
	 * 
	 * @return Marca del vehiculo
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Asigna la marca del vehiculo.
	 * 
	 * @param marca Nueva marca del vehiculo
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Retorna el modelo del vehiculo.
	 * 
	 * @return Modelo del vehiculo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Asigna el modelo del vehiculo.
	 * 
	 * @param modelo Nuevo modelo del vehiculo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Retorna el color del vehiculo.
	 * 
	 * @return Color del vehiculo
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Asigna el color del vehiculo.
	 * 
	 * @param color Nuevo color del vehiculo
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Retorna el apartamento asociado al vehiculo.
	 * 
	 * @return Apartamento asociado
	 */
	public Apartamento getApartamento() {
		return apartamento;
	}

	/**
	 * Asigna el apartamento asociado al vehiculo.
	 * 
	 * @param apartamento Nuevo apartamento asociado
	 */
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * Retorna el estado de autorizacion del vehiculo.
	 * 
	 * @return true si el vehiculo esta autorizado, false en caso contrario
	 */
	public boolean isAutorizado() {
		return autorizado;
	}

	/**
	 * Asigna el estado de autorizacion del vehiculo.
	 * 
	 * @param autorizado Nuevo estado de autorizacion
	 */
	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	/**
	 * Retorna si el vehiculo tiene restricciones.
	 * 
	 * @return true si el vehiculo tiene restricciones, false en caso contrario
	 */
	public boolean isTieneRestriccion() {
		return tieneRestriccion;
	}

	/**
	 * Asigna el estado de restriccion del vehiculo.
	 * 
	 * @param tieneRestriccion Nuevo estado de restriccion
	 */
	public void setTieneRestriccion(boolean tieneRestriccion) {
		this.tieneRestriccion = tieneRestriccion;
	}

	/**
	 * Retorna una representacion en texto del vehiculo.
	 * 
	 * @return String con la informacion principal del vehiculo
	 */
	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", placa=" + placa + ", tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo
				+ ", color=" + color + ", autorizado=" + autorizado + ", tieneRestriccion=" + tieneRestriccion + "]";
	}
}