package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa un incidente de convivencia registrado dentro del conjunto
 * residencial. Un incidente puede estar relacionado con ruido, daños, mascotas,
 * parqueaderos, residuos, seguridad o conflictos entre residentes, y puede
 * derivar en advertencias, multas o revision por parte del consejo de
 * administracion. <b>pre</b> El apartamento involucrado debe estar registrado
 * en el sistema antes de asociarlo al incidente. <br>
 * <b>post</b> El incidente queda disponible para ser consultado, actualizado y
 * utilizado como base para la generacion de multas o sanciones. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Incidente implements Serializable {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 2733419796197627651L;

	/** Constante que representa un incidente relacionado con ruido excesivo. */
	private final String TIPO_RUIDO = "RUIDO";

	/**
	 * Constante que representa un incidente relacionado con daños en zonas comunes.
	 */
	private final String TIPO_DANOS = "DANOS";

	/**
	 * Constante que representa un incidente relacionado con mascotas sin control.
	 */
	private final String TIPO_MASCOTAS = "MASCOTAS";

	/**
	 * Constante que representa un incidente relacionado con uso indebido de
	 * parqueaderos.
	 */
	private final String TIPO_PARQUEADERO = "PARQUEADERO";

	/**
	 * Constante que representa un incidente relacionado con disposicion incorrecta
	 * de residuos.
	 */
	private final String TIPO_RESIDUOS = "RESIDUOS";

	/**
	 * Constante que representa un incidente relacionado con situaciones de
	 * seguridad.
	 */
	private final String TIPO_SEGURIDAD = "SEGURIDAD";

	/**
	 * Constante que representa un incidente relacionado con conflictos entre
	 * residentes.
	 */
	private final String TIPO_CONFLICTO = "CONFLICTO";

	/** Constante que representa un incidente de gravedad leve. */
	private final String GRAVEDAD_LEVE = "LEVE";

	/** Constante que representa un incidente de gravedad moderada. */
	private final String GRAVEDAD_MODERADO = "MODERADO";

	/** Constante que representa un incidente de gravedad grave. */
	private final String GRAVEDAD_GRAVE = "GRAVE";

	/** Constante que representa un incidente de gravedad muy grave. */
	private final String GRAVEDAD_MUY_GRAVE = "MUY_GRAVE";

	/**
	 * Constante que representa el estado inicial del incidente al ser registrado.
	 */
	private final String ESTADO_REPORTADO = "REPORTADO";

	/**
	 * Constante que representa el estado del incidente cuando esta siendo
	 * analizado.
	 */
	private final String ESTADO_EN_REVISION = "EN_REVISION";

	/**
	 * Constante que representa el estado del incidente cuando ha sido atendido y
	 * cerrado.
	 */
	private final String ESTADO_RESUELTO = "RESUELTO";

	/**
	 * Constante que representa el estado del incidente cuando ha sido archivado sin
	 * resolucion activa.
	 */
	private final String ESTADO_ARCHIVADO = "ARCHIVADO";

	/** Identificador unico del incidente. */
	private String id;

	/**
	 * Tipo de incidente (RUIDO, DANOS, MASCOTAS, PARQUEADERO, RESIDUOS, SEGURIDAD,
	 * CONFLICTO).
	 */
	private String tipo;

	/** Nivel de gravedad del incidente (LEVE, MODERADO, GRAVE, MUY_GRAVE). */
	private String gravedad;

	/** Descripcion detallada de los hechos relacionados con el incidente. */
	private String descripcion;

	/** Apartamento involucrado o asociado al incidente reportado. */
	private Apartamento apartamentoInvolucrado;

	/** Identificador o nombre de la persona que reporto el incidente. */
	private String reportadoPor;

	/** Fecha y hora en que ocurrio el incidente. */
	private LocalDateTime fechaOcurrencia;

	/**
	 * Estado actual del incidente (REPORTADO, EN_REVISION, RESUELTO, ARCHIVADO).
	 */
	private String estado;

	/** Indica si el incidente genera una multa para el residente involucrado. */
	private boolean generaMulta;

	/**
	 * Descripcion de la resolucion tomada frente al incidente. Puede ser null si
	 * aun no se ha resuelto.
	 */
	private String resolucion;

	/**
	 * Constructor por defecto. Crea un incidente sin datos inicializados.
	 * <b>pre</b> No hay precondiciones para este constructor. <br>
	 * <b>post</b> Se crea un objeto Incidente con todos sus atributos en null o
	 * valores por defecto. <br>
	 */
	public Incidente() {
	}

	/**
	 * Constructor completo. Crea un incidente con sus atributos principales
	 * inicializados. <b>pre</b> Los parametros id, tipo, gravedad, estado y
	 * fechaOcurrencia no deben ser null. El apartamento involucrado debe estar
	 * registrado en el sistema. <br>
	 * <b>post</b> Se crea un objeto Incidente listo para ser registrado y
	 * gestionado en el sistema. El atributo resolucion queda en null hasta que el
	 * incidente sea atendido. <br>
	 *
	 * @param id                     Identificador unico del incidente. id != null,
	 *                               id != ""
	 * @param tipo                   Tipo de incidente. tipo != null, tipo != ""
	 * @param gravedad               Nivel de gravedad del incidente. gravedad !=
	 *                               null, gravedad != ""
	 * @param descripcion            Descripcion detallada del incidente.
	 *                               descripcion != null
	 * @param apartamentoInvolucrado Apartamento relacionado con el incidente.
	 *                               apartamentoInvolucrado != null
	 * @param reportadoPor           Nombre o identificador de quien reporta.
	 *                               reportadoPor != null, reportadoPor != ""
	 * @param fechaOcurrencia        Fecha y hora en que ocurrio el incidente.
	 *                               fechaOcurrencia != null
	 * @param estado                 Estado inicial del incidente. estado != null,
	 *                               estado != ""
	 * @param generaMulta            true si el incidente genera multa, false en
	 *                               caso contrario
	 */
	public Incidente(String id, String tipo, String gravedad, String descripcion, Apartamento apartamentoInvolucrado,
			String reportadoPor, LocalDateTime fechaOcurrencia, String estado, boolean generaMulta) {
		this.id = id;
		this.tipo = tipo;
		this.gravedad = gravedad;
		this.descripcion = descripcion;
		this.apartamentoInvolucrado = apartamentoInvolucrado;
		this.reportadoPor = reportadoPor;
		this.fechaOcurrencia = fechaOcurrencia;
		this.estado = estado;
		this.generaMulta = generaMulta;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por ruido.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "RUIDO"
	 */
	public String getTIPO_RUIDO() {
		return TIPO_RUIDO;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por daños.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "DANOS"
	 */
	public String getTIPO_DANOS() {
		return TIPO_DANOS;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por mascotas.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MASCOTAS"
	 */
	public String getTIPO_MASCOTAS() {
		return TIPO_MASCOTAS;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por parqueadero.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "PARQUEADERO"
	 */
	public String getTIPO_PARQUEADERO() {
		return TIPO_PARQUEADERO;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por residuos.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "RESIDUOS"
	 */
	public String getTIPO_RESIDUOS() {
		return TIPO_RESIDUOS;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por seguridad.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "SEGURIDAD"
	 */
	public String getTIPO_SEGURIDAD() {
		return TIPO_SEGURIDAD;
	}

	/**
	 * Retorna la constante que representa el tipo de incidente por conflicto.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "CONFLICTO"
	 */
	public String getTIPO_CONFLICTO() {
		return TIPO_CONFLICTO;
	}

	/**
	 * Retorna la constante que representa la gravedad leve de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "LEVE"
	 */
	public String getGRAVEDAD_LEVE() {
		return GRAVEDAD_LEVE;
	}

	/**
	 * Retorna la constante que representa la gravedad moderada de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MODERADO"
	 */
	public String getGRAVEDAD_MODERADO() {
		return GRAVEDAD_MODERADO;
	}

	/**
	 * Retorna la constante que representa la gravedad grave de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "GRAVE"
	 */
	public String getGRAVEDAD_GRAVE() {
		return GRAVEDAD_GRAVE;
	}

	/**
	 * Retorna la constante que representa la gravedad muy grave de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "MUY_GRAVE"
	 */
	public String getGRAVEDAD_MUY_GRAVE() {
		return GRAVEDAD_MUY_GRAVE;
	}

	/**
	 * Retorna la constante que representa el estado reportado de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "REPORTADO"
	 */
	public String getESTADO_REPORTADO() {
		return ESTADO_REPORTADO;
	}

	/**
	 * Retorna la constante que representa el estado en revision de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "EN_REVISION"
	 */
	public String getESTADO_EN_REVISION() {
		return ESTADO_EN_REVISION;
	}

	/**
	 * Retorna la constante que representa el estado resuelto de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "RESUELTO"
	 */
	public String getESTADO_RESUELTO() {
		return ESTADO_RESUELTO;
	}

	/**
	 * Retorna la constante que representa el estado archivado de un incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el valor "ARCHIVADO"
	 */
	public String getESTADO_ARCHIVADO() {
		return ESTADO_ARCHIVADO;
	}

	/**
	 * Retorna el identificador unico del incidente. <b>pre</b> El objeto Incidente
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id del incidente
	 */
	public String getId() {
		return id;
	}

	/**
	 * Asigna el identificador unico del incidente. <b>pre</b> El objeto Incidente
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo id queda actualizado con el valor recibido. <br>
	 *
	 * @param id Nuevo identificador del incidente. id != null, id != ""
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna el tipo del incidente. <b>pre</b> El objeto Incidente debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el tipo del incidente (RUIDO, DANOS, MASCOTAS, etc.)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna el tipo del incidente. <b>pre</b> El objeto Incidente debe estar
	 * instanciado. <br>
	 * <b>post</b> El atributo tipo queda actualizado con el valor recibido. <br>
	 *
	 * @param tipo Nuevo tipo del incidente. tipo != null, tipo != ""
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el nivel de gravedad del incidente. <b>pre</b> El objeto Incidente
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la gravedad del incidente (LEVE, MODERADO, GRAVE,
	 *         MUY_GRAVE)
	 */
	public String getGravedad() {
		return gravedad;
	}

	/**
	 * Asigna el nivel de gravedad del incidente. <b>pre</b> El objeto Incidente
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo gravedad queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param gravedad Nueva gravedad del incidente. gravedad != null, gravedad !=
	 *                 ""
	 */
	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	/**
	 * Retorna la descripcion detallada del incidente. <b>pre</b> El objeto
	 * Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la descripcion del incidente
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion detallada del incidente. <b>pre</b> El objeto Incidente
	 * debe estar instanciado. <br>
	 * <b>post</b> El atributo descripcion queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param descripcion Nueva descripcion del incidente. descripcion != null
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el apartamento involucrado en el incidente. <b>pre</b> El objeto
	 * Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return Objeto Apartamento asociado al incidente, o null si no fue asignado
	 */
	public Apartamento getApartamentoInvolucrado() {
		return apartamentoInvolucrado;
	}

	/**
	 * Asigna el apartamento involucrado en el incidente. <b>pre</b> El objeto
	 * Incidente debe estar instanciado. <br>
	 * <b>post</b> El atributo apartamentoInvolucrado queda actualizado con el
	 * objeto recibido. <br>
	 *
	 * @param apartamentoInvolucrado Apartamento a asociar al incidente.
	 *                               apartamentoInvolucrado != null
	 */
	public void setApartamentoInvolucrado(Apartamento apartamentoInvolucrado) {
		this.apartamentoInvolucrado = apartamentoInvolucrado;
	}

	/**
	 * Retorna el nombre o identificador de quien reporto el incidente. <b>pre</b>
	 * El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el nombre o id de quien reporto el incidente
	 */
	public String getReportadoPor() {
		return reportadoPor;
	}

	/**
	 * Asigna el nombre o identificador de quien reporto el incidente. <b>pre</b> El
	 * objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> El atributo reportadoPor queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param reportadoPor Nombre o id del reportante. reportadoPor != null,
	 *                     reportadoPor != ""
	 */
	public void setReportadoPor(String reportadoPor) {
		this.reportadoPor = reportadoPor;
	}

	/**
	 * Retorna la fecha y hora en que ocurrio el incidente. <b>pre</b> El objeto
	 * Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return LocalDateTime con la fecha y hora de ocurrencia del incidente
	 */
	public LocalDateTime getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	/**
	 * Asigna la fecha y hora en que ocurrio el incidente. <b>pre</b> El objeto
	 * Incidente debe estar instanciado. <br>
	 * <b>post</b> El atributo fechaOcurrencia queda actualizado con el valor
	 * recibido. <br>
	 *
	 * @param fechaOcurrencia Nueva fecha y hora de ocurrencia. fechaOcurrencia !=
	 *                        null
	 */
	public void setFechaOcurrencia(LocalDateTime fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	/**
	 * Retorna el estado actual del incidente. <b>pre</b> El objeto Incidente debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el estado del incidente (REPORTADO, EN_REVISION, RESUELTO,
	 *         ARCHIVADO)
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el estado actual del incidente. <b>pre</b> El objeto Incidente debe
	 * estar instanciado. <br>
	 * <b>post</b> El atributo estado queda actualizado con el valor recibido. <br>
	 *
	 * @param estado Nuevo estado del incidente. estado != null, estado != ""
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Indica si el incidente genera una multa para el residente involucrado.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return true si el incidente genera multa, false en caso contrario
	 */
	public boolean isGeneraMulta() {
		return generaMulta;
	}

	/**
	 * Asigna si el incidente genera una multa para el residente involucrado.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> El atributo generaMulta queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param generaMulta true si el incidente debe generar multa, false en caso
	 *                    contrario
	 */
	public void setGeneraMulta(boolean generaMulta) {
		this.generaMulta = generaMulta;
	}

	/**
	 * Retorna la descripcion de la resolucion tomada frente al incidente.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con la resolucion del incidente, o null si aun no ha sido
	 *         resuelto
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * Asigna la descripcion de la resolucion tomada frente al incidente. <b>pre</b>
	 * El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> El atributo resolucion queda actualizado con el valor recibido.
	 * <br>
	 *
	 * @param resolucion Descripcion de la resolucion aplicada. Puede ser null si
	 *                   aun no se ha resuelto
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Retorna una representacion en texto del incidente con sus datos principales.
	 * <b>pre</b> El objeto Incidente debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return String con el id, tipo, gravedad, estado, generaMulta y
	 *         fechaOcurrencia del incidente
	 */
	@Override
	public String toString() {
		return "Incidente [id=" + id + ", tipo=" + tipo + ", gravedad=" + gravedad + ", estado=" + estado
				+ ", generaMulta=" + generaMulta + ", fechaOcurrencia=" + fechaOcurrencia + "]";
	}
}