package co.edu.unbosque.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import co.edu.unbosque.model.persistence.ApartamentoDAO;
import co.edu.unbosque.model.persistence.ArrendatarioDAO;
import co.edu.unbosque.model.persistence.CampanaAmbientalDAO;
import co.edu.unbosque.model.persistence.ConjuntoDAO;
import co.edu.unbosque.model.persistence.DataMapper;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.model.persistence.IncidenteDAO;
import co.edu.unbosque.model.persistence.MascotaDAO;
import co.edu.unbosque.model.persistence.NotificacionDAO;
import co.edu.unbosque.model.persistence.ObligacionDAO;
import co.edu.unbosque.model.persistence.PagoDAO;
import co.edu.unbosque.model.persistence.PaqueteDAO;
import co.edu.unbosque.model.persistence.ParqueaderoDAO;
import co.edu.unbosque.model.persistence.ParticipacionDAO;
import co.edu.unbosque.model.persistence.PropietarioDAO;
import co.edu.unbosque.model.persistence.RegistroConsumoAguaDAO;
import co.edu.unbosque.model.persistence.RegistroConsumoEnergiaDAO;
import co.edu.unbosque.model.persistence.RegistroReciclajeDAO;
import co.edu.unbosque.model.persistence.RegistroVisitaDAO;
import co.edu.unbosque.model.persistence.ReporteDAO;
import co.edu.unbosque.model.persistence.ReservaDAO;
import co.edu.unbosque.model.persistence.ResidenteDAO;
import co.edu.unbosque.model.persistence.SolicitudMantenimientoDAO;
import co.edu.unbosque.model.persistence.TorreDAO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.model.persistence.VehiculoDAO;
import co.edu.unbosque.model.persistence.VisitanteDAO;
import co.edu.unbosque.model.persistence.ZonaComunDAO;
import co.edu.unbosque.util.ConfigurationManager;
import co.edu.unbosque.util.EmailSender;

/**
 * Fachada principal del modelo del sistema GreenBuilding Manager. Implementa el
 * patron Facade para centralizar el acceso a todos los DAOs y coordinar la
 * logica de negocio entre los diferentes modulos del sistema. Gestiona los
 * modulos de usuarios, conjuntos, torres, apartamentos, propietarios,
 * arrendatarios, residentes, vehiculos, mascotas, parqueaderos, visitantes,
 * paquetes, zonas comunes, reservas, solicitudes de mantenimiento, incidentes,
 * obligaciones, pagos, notificaciones, registros ambientales, campanas y
 * reportes. <b>pre</b> La carpeta files/ debe existir en el directorio raiz del
 * proyecto para que la persistencia de datos funcione correctamente. El archivo
 * config.properties debe estar disponible con los parametros del sistema. <br>
 * <b>post</b> El modelo queda completamente inicializado con todos los DAOs
 * cargados desde sus archivos de persistencia y el superadmin garantizado en el
 * sistema. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ModelFacade {

	/**
	 * Contador autoincremental usado para generar IDs unicos de todas las entidades
	 * del sistema.
	 */
	private int contadorIds;

	/** DAO para la gestion de conjuntos residenciales. */
	private ConjuntoDAO conjuntoDAO;

	/** DAO para la gestion de torres del conjunto. */
	private TorreDAO torreDAO;

	/** DAO para la gestion de apartamentos. */
	private ApartamentoDAO apartamentoDAO;

	/** DAO para la gestion de propietarios. */
	private PropietarioDAO propietarioDAO;

	/** DAO para la gestion de arrendatarios. */
	private ArrendatarioDAO arrendatarioDAO;

	/** DAO para la gestion de residentes adicionales. */
	private ResidenteDAO residenteDAO;

	/** DAO para la gestion de vehiculos registrados. */
	private VehiculoDAO vehiculoDAO;

	/** DAO para la gestion de mascotas registradas. */
	private MascotaDAO mascotaDAO;

	/** DAO para la gestion de parqueaderos del conjunto. */
	private ParqueaderoDAO parqueaderoDAO;

	/** DAO para la gestion de visitantes del conjunto. */
	private VisitanteDAO visitanteDAO;

	/** DAO para la gestion de registros de visita (entrada/salida). */
	private RegistroVisitaDAO registroVisitaDAO;

	/** DAO para la gestion de paquetes recibidos en porteria. */
	private PaqueteDAO paqueteDAO;

	/** DAO para la gestion de zonas comunes del conjunto. */
	private ZonaComunDAO zonaComunDAO;

	/** DAO para la gestion de reservas de zonas comunes. */
	private ReservaDAO reservaDAO;

	/** DAO para la gestion de pagos realizados por los residentes. */
	private PagoDAO pagoDAO;

	/** DAO para la gestion de obligaciones financieras pendientes. */
	private ObligacionDAO obligacionDAO;

	/** DAO para la gestion de incidentes de convivencia. */
	private IncidenteDAO incidenteDAO;

	/** DAO para la gestion de solicitudes de mantenimiento. */
	private SolicitudMantenimientoDAO solicitudMantenimientoDAO;

	/** DAO para la gestion de notificaciones enviadas por el sistema. */
	private NotificacionDAO notificacionDAO;

	/** DAO para la gestion de reportes administrativos generados. */
	private ReporteDAO reporteDAO;

	/** DAO para la gestion de registros de consumo de agua. */
	private RegistroConsumoAguaDAO registroConsumoAguaDAO;

	/** DAO para la gestion de registros de consumo de energia. */
	private RegistroConsumoEnergiaDAO registroConsumoEnergiaDAO;

	/** DAO para la gestion de registros de reciclaje. */
	private RegistroReciclajeDAO registroReciclajeDAO;

	/** DAO para la gestion de campanas ambientales. */
	private CampanaAmbientalDAO campanaAmbientalDAO;

	/** DAO para la gestion de participaciones en campanas ambientales. */
	private ParticipacionDAO participacionDAO;

	/** DAO para la gestion de usuarios del sistema. */
	private UsuarioDAO usuarioDAO;

	/**
	 * Constructor de la fachada del modelo. Inicializa todos los DAOs del sistema,
	 * crea la carpeta principal de persistencia y garantiza la existencia del
	 * superadmin. <b>pre</b> La carpeta files/ debe existir o poder ser creada en
	 * el directorio de ejecucion. <br>
	 * <b>post</b> Todos los DAOs quedan instanciados y cargados con sus datos
	 * persistidos. El superadmin queda garantizado en el sistema. <br>
	 */
	public ModelFacade() {
		contadorIds = 1;
		FileHandler.crearCarpetaPrincipal();
		conjuntoDAO = new ConjuntoDAO();
		torreDAO = new TorreDAO();
		apartamentoDAO = new ApartamentoDAO();
		propietarioDAO = new PropietarioDAO();
		arrendatarioDAO = new ArrendatarioDAO();
		residenteDAO = new ResidenteDAO();
		vehiculoDAO = new VehiculoDAO();
		mascotaDAO = new MascotaDAO();
		parqueaderoDAO = new ParqueaderoDAO();
		visitanteDAO = new VisitanteDAO();
		registroVisitaDAO = new RegistroVisitaDAO();
		paqueteDAO = new PaqueteDAO();
		zonaComunDAO = new ZonaComunDAO();
		reservaDAO = new ReservaDAO();
		pagoDAO = new PagoDAO();
		obligacionDAO = new ObligacionDAO();
		incidenteDAO = new IncidenteDAO();
		solicitudMantenimientoDAO = new SolicitudMantenimientoDAO();
		notificacionDAO = new NotificacionDAO();
		reporteDAO = new ReporteDAO();
		registroConsumoAguaDAO = new RegistroConsumoAguaDAO();
		registroConsumoEnergiaDAO = new RegistroConsumoEnergiaDAO();
		registroReciclajeDAO = new RegistroReciclajeDAO();
		campanaAmbientalDAO = new CampanaAmbientalDAO();
		participacionDAO = new ParticipacionDAO();
		usuarioDAO = new UsuarioDAO();
		inicializarSuperAdmin();
	}

	/**
	 * Verifica si ya existe un usuario con rol SUPER_ADMIN en el sistema y, si no
	 * existe, crea uno con las credenciales definidas en el archivo
	 * config.properties. Este metodo garantiza que siempre haya al menos un
	 * superadmin disponible. <b>pre</b> El usuarioDAO debe estar inicializado. El
	 * archivo config.properties debe contener las claves superadmin.id,
	 * superadmin.username y superadmin.contrasena. <br>
	 * <b>post</b> Si no existia un SUPER_ADMIN, queda creado y persistido. Si ya
	 * existia, no se modifica nada. <br>
	 */
	public void inicializarSuperAdmin() {
		for (Usuario u : usuarioDAO.getListaUsuarios()) {
			if (u.getRol().equals("SUPER_ADMIN")) {
				return;
			}
		}
		Usuario superAdmin = new Usuario();
		superAdmin.setId(ConfigurationManager.get("superadmin.id"));
		superAdmin.setUsername(ConfigurationManager.get("superadmin.username"));
		superAdmin.setContrasena(ConfigurationManager.get("superadmin.contrasena"));
		superAdmin.setRol("SUPER_ADMIN");
		superAdmin.setActivo(true);
		superAdmin.setIntentosFallidos(0);
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(superAdmin));
	}

	// Metodos USUARIO

	/**
	 * Autentica un usuario en el sistema verificando usuario, contrasena y estado
	 * activo. Si el usuario existe pero la contrasena es incorrecta, incrementa el
	 * contador de intentos fallidos y puede bloquear la cuenta al superar el limite
	 * configurado. <b>pre</b> El usuarioDAO debe estar inicializado con los
	 * usuarios del sistema. <br>
	 * <b>post</b> Si las credenciales son correctas, retorna el Usuario autenticado
	 * y resetea sus intentos fallidos. Si son incorrectas, incrementa intentos y
	 * puede desactivar la cuenta. Retorna null si no se autentica. <br>
	 * 
	 * @param username   Nombre de usuario ingresado. username != null, username !=
	 *                   ""
	 * @param contrasena Contrasena ingresada. contrasena != null, contrasena != ""
	 * @return El objeto Usuario autenticado, o null si las credenciales no son
	 *         validas
	 */
	public Usuario login(String username, String contrasena) {
		for (Usuario u : usuarioDAO.getListaUsuarios()) {
			if (u.getUsername().equals(username) && u.getContrasena().equals(contrasena) && u.isActivo()) {
				u.setIntentosFallidos(0);
				int idx = usuarioDAO.getListaUsuarios().indexOf(u);
				usuarioDAO.actualizar(idx, DataMapper.convertirUsuarioAUsuarioDTO(u));
				return u;
			} else if (u.getUsername().equals(username)) {
				u.setIntentosFallidos(u.getIntentosFallidos() + 1);
				if (u.getIntentosFallidos() >= ConfigurationManager.getInt("login.intentos.maximos")) {
					u.setActivo(false);
				}
				int idx = usuarioDAO.getListaUsuarios().indexOf(u);
				usuarioDAO.actualizar(idx, DataMapper.convertirUsuarioAUsuarioDTO(u));
			}
		}
		return null;
	}

	/**
	 * Registra un nuevo usuario en el sistema persistiendo su informacion.
	 * <b>pre</b> El objeto usuario debe estar completamente inicializado con id,
	 * username, contrasena y rol validos. <br>
	 * <b>post</b> El usuario queda registrado y persistido en el sistema. <br>
	 * 
	 * @param usuario Objeto Usuario a registrar. usuario != null
	 */
	public void registrarUsuario(Usuario usuario) {
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(usuario));
	}

	/**
	 * Bloquea la cuenta de un usuario desactivandola por nombre de usuario.
	 * <b>pre</b> El usuarioDAO debe estar inicializado. El username debe
	 * corresponder a un usuario existente en el sistema. <br>
	 * <b>post</b> Si se encuentra el usuario, su atributo activo queda en false y
	 * se persiste. Retorna true si se bloqueo, false si el usuario no fue
	 * encontrado. <br>
	 * 
	 * @param username Nombre de usuario a bloquear. username != null, username !=
	 *                 ""
	 * @return true si el usuario fue encontrado y bloqueado, false en caso
	 *         contrario
	 */
	public boolean bloquearUsuario(String username) {
		ArrayList<Usuario> lista = usuarioDAO.getListaUsuarios();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getUsername().equals(username)) {
				Usuario u = lista.get(i);
				u.setActivo(false);
				usuarioDAO.actualizar(i, DataMapper.convertirUsuarioAUsuarioDTO(u));
				return true;
			}
		}
		return false;
	}

	/**
	 * Desbloquea la cuenta de un usuario activandola y reseteando sus intentos
	 * fallidos. <b>pre</b> El usuarioDAO debe estar inicializado. El username debe
	 * corresponder a un usuario existente en el sistema. <br>
	 * <b>post</b> Si se encuentra el usuario, su atributo activo queda en true, sus
	 * intentos fallidos en cero y los cambios se persisten. Retorna true si se
	 * desbloqueo. <br>
	 * 
	 * @param username Nombre de usuario a desbloquear. username != null, username
	 *                 != ""
	 * @return true si el usuario fue encontrado y desbloqueado, false en caso
	 *         contrario
	 */
	public boolean desbloquearUsuario(String username) {
		ArrayList<Usuario> lista = usuarioDAO.getListaUsuarios();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getUsername().equals(username)) {
				Usuario u = lista.get(i);
				u.setActivo(true);
				u.setIntentosFallidos(0);
				usuarioDAO.actualizar(i, DataMapper.convertirUsuarioAUsuarioDTO(u));
				return true;
			}
		}
		return false;
	}

	/**
	 * Cambia la contrasena de un usuario verificando primero la contrasena actual.
	 * <b>pre</b> El usuarioDAO debe estar inicializado. El username y la
	 * contrasenaActual deben corresponder a un usuario existente y activo en el
	 * sistema. <br>
	 * <b>post</b> Si el usuario existe y la contrasena actual coincide, la
	 * contrasena queda actualizada con el nuevo valor y se persiste. Retorna true
	 * si fue exitoso. <br>
	 * 
	 * @param username         Nombre de usuario. username != null, username != ""
	 * @param contrasenaActual Contrasena vigente del usuario para validar la
	 *                         operacion. contrasenaActual != null
	 * @param contrasenaNueva  Nueva contrasena a establecer. contrasenaNueva !=
	 *                         null, contrasenaNueva != ""
	 * @return true si la contrasena fue cambiada exitosamente, false si el usuario
	 *         no existe o la contrasena actual no coincide
	 */
	public boolean cambiarContrasena(String username, String contrasenaActual, String contrasenaNueva) {
		ArrayList<Usuario> lista = usuarioDAO.getListaUsuarios();
		for (int i = 0; i < lista.size(); i++) {
			Usuario u = lista.get(i);
			if (u.getUsername().equals(username) && u.getContrasena().equals(contrasenaActual)) {
				u.setContrasena(contrasenaNueva);
				usuarioDAO.actualizar(i, DataMapper.convertirUsuarioAUsuarioDTO(u));
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna la lista completa de usuarios registrados en el sistema. <b>pre</b>
	 * El usuarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Usuario del sistema
	 */
	public ArrayList<Usuario> obtenerTodosLosUsuarios() {
		return usuarioDAO.getListaUsuarios();
	}

	/**
	 * Retorna la lista de usuarios filtrados por su rol en el sistema. <b>pre</b>
	 * El usuarioDAO debe estar inicializado. El rol debe ser un valor valido del
	 * sistema. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param rol Rol por el que se filtra (SUPER_ADMIN, ADMINISTRADOR, PROPIETARIO,
	 *            ARRENDATARIO, VIGILANTE, CONSEJO). rol != null, rol != ""
	 * @return ArrayList con los usuarios que tienen el rol indicado
	 */
	public ArrayList<Usuario> obtenerUsuariosPorRol(String rol) {
		return usuarioDAO.buscarPorRol(rol);
	}

	/**
	 * Retorna una representacion en texto de todos los usuarios del sistema.
	 * <b>pre</b> El usuarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los usuarios
	 */
	public String mostrarUsuarios() {
		return usuarioDAO.mostrar();
	}

	// Metodos CONJUNTO

	/**
	 * Registra un nuevo conjunto residencial en el sistema asignandole un ID unico.
	 * <b>pre</b> El objeto conjunto debe estar inicializado con nombre y direccion
	 * validos. <br>
	 * <b>post</b> El conjunto queda registrado con un ID generado automaticamente y
	 * persistido. <br>
	 * 
	 * @param conjunto Objeto Conjunto a registrar. conjunto != null
	 */
	public void registrarConjunto(Conjunto conjunto) {
		conjunto.setId("CONJ-" + contadorIds++);
		conjuntoDAO.crear(DataMapper.convertirConjuntoAConjuntoDTO(conjunto));
	}

	/**
	 * Actualiza los datos de un conjunto existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * conjuntos. El objeto conjunto debe estar inicializado. <br>
	 * <b>post</b> Si el indice es valido, los datos del conjunto quedan
	 * actualizados y persistidos. Retorna true si fue exitoso. <br>
	 * 
	 * @param index    Indice del conjunto a actualizar. index >= 0
	 * @param conjunto Objeto Conjunto con los nuevos datos. conjunto != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarConjunto(int index, Conjunto conjunto) {
		return conjuntoDAO.actualizar(index, DataMapper.convertirConjuntoAConjuntoDTO(conjunto));
	}

	/**
	 * Elimina un conjunto del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de conjuntos. <br>
	 * <b>post</b> Si el indice es valido, el conjunto queda eliminado del sistema y
	 * la persistencia se actualiza. Retorna true si fue exitoso. <br>
	 * 
	 * @param index Indice del conjunto a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarConjunto(int index) {
		return conjuntoDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de conjuntos registrados en el sistema. <b>pre</b>
	 * El conjuntoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Conjunto del sistema
	 */
	public ArrayList<Conjunto> obtenerTodosLosConjuntos() {
		return conjuntoDAO.getListaConjuntos();
	}

	/**
	 * Busca y retorna un conjunto por su identificador unico. <b>pre</b> El
	 * conjuntoDAO debe estar inicializado. El id debe ser un String valido. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador del conjunto a buscar. id != null, id != ""
	 * @return El objeto Conjunto con el id indicado, o null si no existe
	 */
	public Conjunto buscarConjuntoPorId(String id) {
		for (Conjunto c : conjuntoDAO.getListaConjuntos()) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Retorna una representacion en texto de todos los conjuntos del sistema.
	 * <b>pre</b> El conjuntoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los conjuntos
	 */
	public String mostrarConjuntos() {
		return conjuntoDAO.mostrar();
	}

	// Metodos TORRE

	/**
	 * Registra una nueva torre en el sistema asociandola al conjunto indicado.
	 * <b>pre</b> El objeto torre debe estar inicializado. El idConjunto debe
	 * corresponder a un conjunto existente en el sistema. <br>
	 * <b>post</b> La torre queda asociada al conjunto y persistida en el sistema.
	 * <br>
	 * 
	 * @param torre      Objeto Torre a registrar. torre != null
	 * @param idConjunto ID del conjunto al que pertenece la torre. idConjunto !=
	 *                   null, idConjunto != ""
	 */
	public void registrarTorre(Torre torre, String idConjunto) {
		Conjunto conjunto = buscarConjuntoPorId(idConjunto);
		if (conjunto != null) {
			torre.setConjunto(conjunto);
		}
		torreDAO.crear(DataMapper.convertirTorreATorreDTO(torre));
	}

	/**
	 * Actualiza los datos de una torre existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de torres.
	 * El objeto torre debe estar inicializado. <br>
	 * <b>post</b> Si el indice es valido, los datos de la torre quedan actualizados
	 * y persistidos. <br>
	 * 
	 * @param index Indice de la torre a actualizar. index >= 0
	 * @param torre Objeto Torre con los nuevos datos. torre != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarTorre(int index, Torre torre) {
		return torreDAO.actualizar(index, DataMapper.convertirTorreATorreDTO(torre));
	}

	/**
	 * Elimina una torre del sistema por su indice en la lista. <b>pre</b> El index
	 * debe ser un indice valido dentro de la lista de torres. <br>
	 * <b>post</b> Si el indice es valido, la torre queda eliminada y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice de la torre a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarTorre(int index) {
		return torreDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de torres registradas en el sistema. <b>pre</b> El
	 * torreDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Torre del sistema
	 */
	public ArrayList<Torre> obtenerTodosLasTorres() {
		return torreDAO.getListaTorres();
	}

	/**
	 * Retorna una torre por su identificador unico. <b>pre</b> El torreDAO debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador de la torre. id != null, id != ""
	 * @return El objeto Torre con el id indicado, o null si no existe
	 */
	public Torre obtenerTorrePorId(String id) {
		return buscarTorrePorId(id);
	}

	/**
	 * Retorna la lista de torres pertenecientes a un conjunto especifico.
	 * <b>pre</b> El torreDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idConjunto ID del conjunto del que se quieren obtener las torres.
	 *                   idConjunto != null, idConjunto != ""
	 * @return ArrayList con las torres del conjunto indicado
	 */
	public ArrayList<Torre> obtenerTorresPorConjunto(String idConjunto) {
		return torreDAO.buscarPorConjunto(idConjunto);
	}

	/**
	 * Retorna una representacion en texto de todas las torres del sistema.
	 * <b>pre</b> El torreDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las torres
	 */
	public String mostrarTorres() {
		return torreDAO.mostrar();
	}

	// Metodos APARTAMENTOS

	/**
	 * Registra un nuevo apartamento en el sistema asociandolo a la torre indicada.
	 * El apartamento se crea con estado DESOCUPADO por defecto. <b>pre</b> El
	 * objeto apartamento debe estar inicializado. El idTorre debe corresponder a
	 * una torre existente en el sistema. <br>
	 * <b>post</b> El apartamento queda asociado a la torre con estado DESOCUPADO y
	 * persistido. <br>
	 * 
	 * @param apartamento Objeto Apartamento a registrar. apartamento != null
	 * @param idTorre     ID de la torre a la que pertenece el apartamento. idTorre
	 *                    != null, idTorre != ""
	 */
	public void registrarApartamento(Apartamento apartamento, String idTorre) {
		Torre torre = buscarTorrePorId(idTorre);
		if (torre != null) {
			apartamento.setTorre(torre);
		}
		apartamento.setEstado("DESOCUPADO");
		apartamentoDAO.crear(DataMapper.convertirApartamentoAApartamentoDTO(apartamento));
	}

	/**
	 * Actualiza los datos de un apartamento existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * apartamentos. <br>
	 * <b>post</b> Si el indice es valido, los datos quedan actualizados y
	 * persistidos. <br>
	 * 
	 * @param index       Indice del apartamento a actualizar. index >= 0
	 * @param apartamento Objeto Apartamento con los nuevos datos. apartamento !=
	 *                    null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarApartamento(int index, Apartamento apartamento) {
		return apartamentoDAO.actualizar(index, DataMapper.convertirApartamentoAApartamentoDTO(apartamento));
	}

	/**
	 * Elimina un apartamento del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de apartamentos. <br>
	 * <b>post</b> Si el indice es valido, el apartamento queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del apartamento a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarApartamento(int index) {
		return apartamentoDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de apartamentos registrados en el sistema.
	 * <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Apartamento del sistema
	 */
	public ArrayList<Apartamento> obtenerTodosLosApartamentos() {
		return apartamentoDAO.getListaApartamentos();
	}

	/**
	 * Retorna un apartamento por su identificador unico. <b>pre</b> El
	 * apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador del apartamento. id != null, id != ""
	 * @return El objeto Apartamento con el id indicado, o null si no existe
	 */
	public Apartamento obtenerApartamentoPorId(String id) {
		return buscarApartamentoPorId(id);
	}

	/**
	 * Retorna la lista de apartamentos pertenecientes a una torre especifica.
	 * <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idTorre ID de la torre. idTorre != null, idTorre != ""
	 * @return ArrayList con los apartamentos de la torre indicada
	 */
	public ArrayList<Apartamento> obtenerApartamentosPorTorre(String idTorre) {
		return apartamentoDAO.buscarPorTorre(idTorre);
	}

	/**
	 * Retorna la lista de apartamentos que tienen un estado especifico. <b>pre</b>
	 * El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param estado Estado por el que se filtra (DESOCUPADO, OCUPADO_PROPIETARIO,
	 *               ARRENDADO). estado != null, estado != ""
	 * @return ArrayList con los apartamentos que tienen el estado indicado
	 */
	public ArrayList<Apartamento> obtenerApartamentosPorEstado(String estado) {
		ArrayList<Apartamento> resultado = new ArrayList<>();
		for (Apartamento a : apartamentoDAO.getListaApartamentos()) {
			if (a.getEstado().equals(estado)) {
				resultado.add(a);
			}
		}
		return resultado;
	}

	/**
	 * Busca y retorna un apartamento por su numero o codigo dentro del conjunto.
	 * <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param numero Numero o codigo del apartamento a buscar. numero != null,
	 *               numero != ""
	 * @return El objeto Apartamento con el numero indicado, o null si no existe
	 */
	public Apartamento buscarApartamentoPorNumero(String numero) {
		for (Apartamento a : apartamentoDAO.getListaApartamentos()) {
			if (a.getNumero() != null && a.getNumero().equals(numero)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de apartamentos asociados a un propietario especifico.
	 * <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idPropietario ID del propietario. idPropietario != null, idPropietario
	 *                      != ""
	 * @return ArrayList con los apartamentos del propietario indicado
	 */
	public ArrayList<Apartamento> obtenerApartamentosPorPropietario(String idPropietario) {
		return apartamentoDAO.buscarPorPropietario(idPropietario);
	}

	/**
	 * Genera y retorna un resumen completo de un apartamento incluyendo
	 * propietario, arrendatario, residentes, vehiculos y mascotas asociados.
	 * <b>pre</b> El idApartamento debe corresponder a un apartamento existente en
	 * el sistema. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento del que se genera el resumen.
	 *                      idApartamento != null, idApartamento != ""
	 * @return String con el resumen completo del apartamento, o mensaje de error si
	 *         no existe
	 */
	public String obtenerResumenApartamento(String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return "Apartamento no encontrado.";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("   APARTAMENTO ").append(apto.getNumero()).append("   \n");
		sb.append(apto.toString()).append("\n");
		if (apto.getPropietario() != null) {
			sb.append("Propietario: ").append(apto.getPropietario().toString()).append("\n");
		}
		if (apto.getArrendatario() != null) {
			sb.append("Arrendatario: ").append(apto.getArrendatario().toString()).append("\n");
		}
		for (Residente r : residenteDAO.buscarPorApartamento(idApartamento)) {
			sb.append("  Residente: ").append(r.toString()).append("\n");
		}
		for (Vehiculo v : vehiculoDAO.buscarPorApartamento(idApartamento)) {
			sb.append("  Vehiculo: ").append(v.toString()).append("\n");
		}
		for (Mascota m : mascotaDAO.buscarPorApartamento(idApartamento)) {
			sb.append("  Mascota: ").append(m.toString()).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Retorna una representacion en texto de todos los apartamentos del sistema.
	 * <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los apartamentos
	 */
	public String mostrarApartamentos() {
		return apartamentoDAO.mostrar();
	}

	// Metodos PROPIETARIO

	/**
	 * Registra un propietario en el sistema asociandolo al apartamento indicado,
	 * crea su usuario de acceso y envia sus credenciales por correo electronico.
	 * <b>pre</b> El objeto propietario debe estar inicializado con nombre, cedula y
	 * correo. El idApartamento debe corresponder a un apartamento existente en el
	 * sistema. <br>
	 * <b>post</b> El propietario queda registrado, el apartamento actualizado con
	 * el propietario, un usuario con rol PROPIETARIO creado y las credenciales
	 * enviadas por correo si tiene correo valido. Retorna false si el apartamento
	 * no existe. <br>
	 * 
	 * @param propietario   Objeto Propietario a registrar. propietario != null
	 * @param idApartamento ID del apartamento al que se asocia. idApartamento !=
	 *                      null, idApartamento != ""
	 * @return true si el registro fue exitoso, false si el apartamento no existe
	 */
	public boolean registrarPropietario(Propietario propietario, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return false;
		}
		propietarioDAO.crear(DataMapper.convertirPropietarioAPropietarioDTO(propietario));
		apto.setPropietario(propietario);
		if (apto.getEstado().equals("DESOCUPADO")) {
			apto.setEstado("OCUPADO_PROPIETARIO");
		}
		int idx = apartamentoDAO.getListaApartamentos().indexOf(apto);
		apartamentoDAO.actualizar(idx, DataMapper.convertirApartamentoAApartamentoDTO(apto));

		String username = generarUsername(propietario.getNombre(), propietario.getCedula());
		String contrasena = generarContrasenaAleatoria();

		Usuario u = new Usuario();
		u.setId(propietario.getId());
		u.setUsername(username);
		u.setContrasena(contrasena);
		u.setRol("PROPIETARIO");
		u.setActivo(true);
		u.setIntentosFallidos(0);
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(u));

		String correo = propietario.getCorreo();
		if (correo != null && !correo.isEmpty()) {
			String asunto = "Bienvenido a GreenBuilding Manager - Sus credenciales de acceso";
			String cuerpo = "Estimado/a " + propietario.getNombre() + ",\n\n"
					+ "Su registro como propietario del apartamento " + apto.getNumero() + " fue exitoso.\n\n"
					+ "Sus credenciales de acceso son:\n" + "Usuario: " + username + "\n" + "Contrase\u00f1a: "
					+ contrasena + "\n\n" + "Puede iniciar sesion en la aplicacion GreenBuilding Manager.\n\n"
					+ "Atentamente,\nGreenBuilding Group";

			Notificacion notif = new Notificacion();
			notif.setId("NOT-" + contadorIds++);
			notif.setDestinatario(correo);
			notif.setAsunto(asunto);
			notif.setCuerpo(cuerpo);
			notif.setTipo("SISTEMA");
			notif.setFechaEnvio(LocalDateTime.now());
			notif.setEnviada(false);
			notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

			boolean enviado = EmailSender.enviarCorreo(correo, asunto, cuerpo);
			if (enviado) {
				notif.setEnviada(true);
				int idxNotif = notificacionDAO.getListaNotificaciones().size() - 1;
				notificacionDAO.actualizar(idxNotif, DataMapper.convertirNotificacionANotificacionDTO(notif));
			}
		}

		return true;
	}

	/**
	 * Actualiza los datos de un propietario existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * propietarios. <br>
	 * <b>post</b> Si el indice es valido, los datos del propietario quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index       Indice del propietario a actualizar. index >= 0
	 * @param propietario Objeto Propietario con los nuevos datos. propietario !=
	 *                    null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarPropietario(int index, Propietario propietario) {
		return propietarioDAO.actualizar(index, DataMapper.convertirPropietarioAPropietarioDTO(propietario));
	}

	/**
	 * Elimina un propietario del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de propietarios. <br>
	 * <b>post</b> Si el indice es valido, el propietario queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del propietario a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarPropietario(int index) {
		return propietarioDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de propietarios registrados en el sistema.
	 * <b>pre</b> El propietarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Propietario del sistema
	 */
	public ArrayList<Propietario> obtenerTodosLosPropietarios() {
		return propietarioDAO.getListaPropietarios();
	}

	/**
	 * Retorna el propietario asociado a un apartamento especifico. <b>pre</b> El
	 * idApartamento debe corresponder a un apartamento existente. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return El objeto Propietario del apartamento, o null si el apartamento no
	 *         existe o no tiene propietario
	 */
	public Propietario obtenerPropietarioPorApartamento(String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return null;
		}
		return apto.getPropietario();
	}

	/**
	 * Retorna una representacion en texto de todos los propietarios del sistema.
	 * <b>pre</b> El propietarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los propietarios
	 */
	public String mostrarPropietarios() {
		return propietarioDAO.mostrar();
	}

	/**
	 * Genera y retorna un resumen consolidado de todos los propietarios del
	 * sistema. <b>pre</b> El propietarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con el resumen de todos los propietarios generado por su
	 *         metodo generarResumen()
	 */
	public String obtenerResumenPropietarios() {
		StringBuilder sb = new StringBuilder();
		sb.append("    RESUMEN DE PROPIETARIOS    \n\n");
		for (Propietario p : propietarioDAO.getListaPropietarios()) {
			sb.append(p.generarResumen()).append("\n---\n");
		}
		return sb.toString();
	}

	// Metodos ARRENDATARIO

	/**
	 * Registra un arrendatario en el sistema asociandolo al apartamento indicado,
	 * cambia el estado del apartamento a ARRENDADO, crea su usuario de acceso y
	 * envia sus credenciales por correo electronico. <b>pre</b> El objeto
	 * arrendatario debe estar inicializado con nombre, cedula y correo. El
	 * idApartamento debe corresponder a un apartamento en estado DESOCUPADO. <br>
	 * <b>post</b> El arrendatario queda registrado, el apartamento en estado
	 * ARRENDADO, un usuario con rol ARRENDATARIO creado y las credenciales enviadas
	 * por correo. Retorna false si el apartamento no existe o no esta desocupado.
	 * <br>
	 * 
	 * @param arrendatario  Objeto Arrendatario a registrar. arrendatario != null
	 * @param idApartamento ID del apartamento al que se asocia. idApartamento !=
	 *                      null, idApartamento != ""
	 * @return true si el registro fue exitoso, false si el apartamento no existe o
	 *         no esta disponible
	 */
	public boolean registrarArrendatario(Arrendatario arrendatario, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null)
			return false;
		if (!apto.getEstado().equals("DESOCUPADO"))
			return false;

		arrendatario.setApartamento(apto);
		arrendatarioDAO.crear(DataMapper.convertirArrendatarioAArrendatarioDTO(arrendatario));
		apto.setEstado("ARRENDADO");
		apto.setArrendatario(arrendatario);
		int idx = apartamentoDAO.getListaApartamentos().indexOf(apto);
		apartamentoDAO.actualizar(idx, DataMapper.convertirApartamentoAApartamentoDTO(apto));

		String username = generarUsername(arrendatario.getNombre(), arrendatario.getCedula());
		String contrasena = generarContrasenaAleatoria();
		Usuario u = new Usuario();
		u.setId(arrendatario.getId());
		u.setUsername(username);
		u.setContrasena(contrasena);
		u.setRol("ARRENDATARIO");
		u.setActivo(true);
		u.setIntentosFallidos(0);
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(u));

		String correo = arrendatario.getCorreo();
		if (correo != null && !correo.isEmpty()) {
			String asunto = "Bienvenido a GreenBuilding Manager - Sus credenciales de acceso";
			String cuerpo = "Estimado/a " + arrendatario.getNombre() + ",\n\n"
					+ "Su registro como arrendatario del apartamento " + apto.getNumero() + " fue exitoso.\n\n"
					+ "Sus credenciales de acceso son:\n" + "Usuario: " + username + "\n" + "Contrase\u00f1a: "
					+ contrasena + "\n\n" + "Puede iniciar sesion en la aplicacion GreenBuilding Manager.\n\n"
					+ "Atentamente,\nGreenBuilding Group";

			Notificacion notif = new Notificacion();
			notif.setId("NOT-" + contadorIds++);
			notif.setDestinatario(correo);
			notif.setAsunto(asunto);
			notif.setCuerpo(cuerpo);
			notif.setTipo("SISTEMA");
			notif.setFechaEnvio(LocalDateTime.now());
			notif.setEnviada(false);
			notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

			boolean enviado = EmailSender.enviarCorreo(correo, asunto, cuerpo);
			if (enviado) {
				notif.setEnviada(true);
				int idxNotif = notificacionDAO.getListaNotificaciones().size() - 1;
				notificacionDAO.actualizar(idxNotif, DataMapper.convertirNotificacionANotificacionDTO(notif));
			}
		}
		return true;
	}

	/**
	 * Da de baja a un arrendatario marcandolo como inactivo y liberando el
	 * apartamento asociado cambiando su estado a DESOCUPADO. <b>pre</b> El
	 * indexArrendatario debe ser un indice valido dentro de la lista de
	 * arrendatarios. <br>
	 * <b>post</b> El arrendatario queda inactivo, el apartamento en estado
	 * DESOCUPADO y sin arrendatario asociado. Retorna false si el indice no es
	 * valido. <br>
	 * 
	 * @param indexArrendatario Indice del arrendatario a dar de baja.
	 *                          indexArrendatario >= 0
	 * @return true si la operacion fue exitosa, false si el indice no es valido
	 */
	public boolean darDeBajaArrendatario(int indexArrendatario) {
		ArrayList<Arrendatario> lista = arrendatarioDAO.getListaArrendatarios();
		if (indexArrendatario < 0 || indexArrendatario >= lista.size()) {
			return false;
		}
		Arrendatario a = lista.get(indexArrendatario);
		a.setActivo(false);
		arrendatarioDAO.actualizar(indexArrendatario, DataMapper.convertirArrendatarioAArrendatarioDTO(a));
		if (a.getApartamento() != null) {
			Apartamento apto = buscarApartamentoPorId(a.getApartamento().getId());
			if (apto != null) {
				apto.setEstado("DESOCUPADO");
				apto.setArrendatario(null);
				int idx = apartamentoDAO.getListaApartamentos().indexOf(apto);
				apartamentoDAO.actualizar(idx, DataMapper.convertirApartamentoAApartamentoDTO(apto));
			}
		}
		return true;
	}

	/**
	 * Actualiza los datos de un arrendatario existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * arrendatarios. <br>
	 * <b>post</b> Si el indice es valido, los datos del arrendatario quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index        Indice del arrendatario a actualizar. index >= 0
	 * @param arrendatario Objeto Arrendatario con los nuevos datos. arrendatario !=
	 *                     null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarArrendatario(int index, Arrendatario arrendatario) {
		return arrendatarioDAO.actualizar(index, DataMapper.convertirArrendatarioAArrendatarioDTO(arrendatario));
	}

	/**
	 * Elimina un arrendatario del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de arrendatarios. <br>
	 * <b>post</b> Si el indice es valido, el arrendatario queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del arrendatario a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarArrendatario(int index) {
		return arrendatarioDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de arrendatarios registrados en el sistema.
	 * <b>pre</b> El arrendatarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Arrendatario del sistema
	 */
	public ArrayList<Arrendatario> obtenerTodosLosArrendatarios() {
		return arrendatarioDAO.getListaArrendatarios();
	}

	/**
	 * Retorna la lista de arrendatarios con contrato activo vigente en el sistema.
	 * <b>pre</b> El arrendatarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los arrendatarios cuyo atributo activo es true
	 */
	public ArrayList<Arrendatario> obtenerArrendatariosActivos() {
		ArrayList<Arrendatario> resultado = new ArrayList<>();
		for (Arrendatario a : arrendatarioDAO.getListaArrendatarios()) {
			if (a.isActivo()) {
				resultado.add(a);
			}
		}
		return resultado;
	}

	/**
	 * Retorna el arrendatario asociado a un apartamento especifico. <b>pre</b> El
	 * idApartamento debe corresponder a un apartamento existente. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return El objeto Arrendatario del apartamento, o null si no existe o no
	 *         tiene arrendatario
	 */
	public Arrendatario obtenerArrendatarioPorApartamento(String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return null;
		}
		return apto.getArrendatario();
	}

	/**
	 * Retorna una representacion en texto de todos los arrendatarios del sistema.
	 * <b>pre</b> El arrendatarioDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los arrendatarios
	 */
	public String mostrarArrendatarios() {
		return arrendatarioDAO.mostrar();
	}

	/**
	 * Retorna la lista de arrendatarios que tienen sanciones activas segun la
	 * logica de la interfaz Sancionable. <b>pre</b> El arrendatarioDAO debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los arrendatarios cuyo metodo tieneSancionesActivas()
	 *         retorna true
	 */
	public ArrayList<Arrendatario> obtenerArrendatariosConSanciones() {
		ArrayList<Arrendatario> resultado = new ArrayList<>();
		for (Arrendatario a : arrendatarioDAO.getListaArrendatarios()) {
			if (a.tieneSancionesActivas()) {
				resultado.add(a);
			}
		}
		return resultado;
	}

	// Metodos CREAR ROLES

	/**
	 * Crea un usuario con rol ADMINISTRADOR asociado a un conjunto residencial,
	 * genera sus credenciales y las envia por correo electronico. <b>pre</b> El
	 * idConjunto debe corresponder a un conjunto existente en el sistema. El nombre
	 * y correo no deben ser null. <br>
	 * <b>post</b> Un nuevo usuario con rol ADMINISTRADOR queda creado, persistido y
	 * notificado por correo. <br>
	 * 
	 * @param nombre     Nombre del administrador a crear. nombre != null, nombre !=
	 *                   ""
	 * @param correo     Correo electronico del administrador para recibir
	 *                   credenciales. correo != null, correo != ""
	 * @param idConjunto ID del conjunto al que se asigna el administrador.
	 *                   idConjunto != null, idConjunto != ""
	 * @return true siempre que la operacion se complete sin errores de sistema
	 */
	public boolean crearAdministrador(String nombre, String correo, String idConjunto) {
		String username = generarUsername(nombre, String.valueOf(contadorIds));
		String contrasena = generarContrasenaAleatoria();
		Usuario u = new Usuario();
		u.setId("USR-" + contadorIds++);
		u.setUsername(username);
		u.setContrasena(contrasena);
		u.setRol("ADMINISTRADOR");
		u.setConjunto(buscarConjuntoPorId(idConjunto));
		u.setActivo(true);
		u.setIntentosFallidos(0);
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(u));

		String asunto = "Cuenta de Administrador creada - GreenBuilding Manager";
		String cuerpo = "Estimado/a " + nombre + ",\n\n"
				+ "Se ha creado su cuenta de administrador en GreenBuilding Manager.\n\n"
				+ "Sus credenciales de acceso son:\n" + "Usuario: " + username + "\n" + "Contrase\u00f1a: " + contrasena
				+ "\n\n" + "Puede iniciar sesion en la aplicacion GreenBuilding Manager.\n\n"
				+ "Atentamente,\nGreenBuilding Group";
		Notificacion notif = new Notificacion();
		notif.setId("NOT-" + contadorIds++);
		notif.setDestinatario(correo);
		notif.setAsunto(asunto);
		notif.setCuerpo(cuerpo);
		notif.setTipo("SISTEMA");
		notif.setFechaEnvio(LocalDateTime.now());
		notif.setEnviada(false);
		notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));
		EmailSender.enviarCorreo(correo, asunto, cuerpo);

		return true;
	}

	/**
	 * Crea un usuario con rol VIGILANTE asociado a un conjunto residencial, genera
	 * sus credenciales y las envia por correo electronico. <b>pre</b> El idConjunto
	 * debe corresponder a un conjunto existente en el sistema. El nombre y correo
	 * no deben ser null. <br>
	 * <b>post</b> Un nuevo usuario con rol VIGILANTE queda creado, persistido y
	 * notificado por correo. <br>
	 * 
	 * @param nombre     Nombre del vigilante a crear. nombre != null, nombre != ""
	 * @param correo     Correo electronico del vigilante para recibir credenciales.
	 *                   correo != null, correo != ""
	 * @param idConjunto ID del conjunto al que se asigna el vigilante. idConjunto
	 *                   != null, idConjunto != ""
	 * @return true siempre que la operacion se complete sin errores de sistema
	 */
	public boolean crearVigilante(String nombre, String correo, String idConjunto) {
		String username = generarUsername(nombre, String.valueOf(contadorIds));
		String contrasena = generarContrasenaAleatoria();

		Usuario u = new Usuario();
		u.setId("USR-" + contadorIds++);
		u.setUsername(username);
		u.setContrasena(contrasena);
		u.setRol("VIGILANTE");
		u.setConjunto(buscarConjuntoPorId(idConjunto));
		u.setActivo(true);
		u.setIntentosFallidos(0);
		usuarioDAO.crear(DataMapper.convertirUsuarioAUsuarioDTO(u));

		String asunto = "Cuenta de Vigilante creada - GreenBuilding Manager";
		String cuerpo = "Estimado/a " + nombre + ",\n\n"
				+ "Se ha creado su cuenta de vigilante en GreenBuilding Manager.\n\n"
				+ "Sus credenciales de acceso son:\n" + "Usuario: " + username + "\n" + "Contrase\u00f1a: " + contrasena
				+ "\n\n" + "Puede iniciar sesion en la aplicacion GreenBuilding Manager.\n\n"
				+ "Atentamente,\nGreenBuilding Group";

		Notificacion notif = new Notificacion();
		notif.setId("NOT-" + contadorIds++);
		notif.setDestinatario(correo);
		notif.setAsunto(asunto);
		notif.setCuerpo(cuerpo);
		notif.setTipo("SISTEMA");
		notif.setFechaEnvio(LocalDateTime.now());
		notif.setEnviada(false);
		notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

		boolean enviado = EmailSender.enviarCorreo(correo, asunto, cuerpo);
		if (enviado) {
			notif.setEnviada(true);
			int idx = notificacionDAO.getListaNotificaciones().size() - 1;
			notificacionDAO.actualizar(idx, DataMapper.convertirNotificacionANotificacionDTO(notif));
		}

		return true;
	}

	// Metodos CONSEJO

	/**
	 * Designa a un propietario como miembro del Consejo de Administracion, cambia
	 * su rol de PROPIETARIO a CONSEJO y le notifica por correo. <b>pre</b> El
	 * idPropietario debe corresponder a un propietario existente con usuario activo
	 * y rol PROPIETARIO. <br>
	 * <b>post</b> El propietario queda marcado como consejo, su usuario cambia de
	 * rol a CONSEJO y recibe una notificacion por correo. Retorna false si no se
	 * encuentra. <br>
	 * 
	 * @param idPropietario ID del propietario a designar como consejo.
	 *                      idPropietario != null, idPropietario != ""
	 * @return true si la designacion fue exitosa, false si el propietario no fue
	 *         encontrado
	 */
	public boolean designarComoConsejo(String idPropietario) {
		ArrayList<Propietario> lista = propietarioDAO.getListaPropietarios();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idPropietario)) {
				Propietario p = lista.get(i);
				p.setEsConsejo(true);
				propietarioDAO.actualizar(i, DataMapper.convertirPropietarioAPropietarioDTO(p));
				for (int j = 0; j < usuarioDAO.getListaUsuarios().size(); j++) {
					Usuario u = usuarioDAO.getListaUsuarios().get(j);
					if (u.getId().equals(p.getId()) && u.getRol().equals("PROPIETARIO")) {
						u.setRol("CONSEJO");
						usuarioDAO.actualizar(j, DataMapper.convertirUsuarioAUsuarioDTO(u));
						break;
					}
				}
				String correo = p.getCorreo();
				if (correo != null && !correo.isEmpty()) {
					String asunto = "Designacion como miembro del Consejo de Administracion";
					String cuerpo = "Estimado/a " + p.getNombre() + ",\n\n"
							+ "Ha sido designado/a como miembro del Consejo de Administracion "
							+ "de Green Towers Residencial.\n\n"
							+ "A partir de ahora tendra acceso a las funciones del consejo "
							+ "en el sistema GreenBuilding Manager.\n\n" + "Atentamente,\nGreenBuilding Group";
					enviarNotificacionCorreo(correo, asunto, cuerpo, "SISTEMA");
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Retira a un propietario del Consejo de Administracion, cambia su rol de
	 * CONSEJO a PROPIETARIO y le notifica por correo. <b>pre</b> El idPropietario
	 * debe corresponder a un propietario existente con rol CONSEJO. <br>
	 * <b>post</b> El propietario queda desmarcado como consejo, su usuario regresa
	 * al rol PROPIETARIO y recibe una notificacion por correo. Retorna false si no
	 * se encuentra. <br>
	 * 
	 * @param idPropietario ID del propietario a retirar del consejo. idPropietario
	 *                      != null, idPropietario != ""
	 * @return true si la operacion fue exitosa, false si el propietario no fue
	 *         encontrado
	 */
	public boolean quitarDeConsejo(String idPropietario) {
		ArrayList<Propietario> lista = propietarioDAO.getListaPropietarios();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idPropietario)) {
				Propietario p = lista.get(i);
				p.setEsConsejo(false);
				propietarioDAO.actualizar(i, DataMapper.convertirPropietarioAPropietarioDTO(p));
				for (int j = 0; j < usuarioDAO.getListaUsuarios().size(); j++) {
					Usuario u = usuarioDAO.getListaUsuarios().get(j);
					if (u.getId().equals(p.getId()) && u.getRol().equals("CONSEJO")) {
						u.setRol("PROPIETARIO");
						usuarioDAO.actualizar(j, DataMapper.convertirUsuarioAUsuarioDTO(u));
						break;
					}
				}
				String correo = p.getCorreo();
				if (correo != null && !correo.isEmpty()) {
					String asunto = "Retiro del Consejo de Administracion";
					String cuerpo = "Estimado/a " + p.getNombre() + ",\n\n"
							+ "Le informamos que ha sido retirado/a del Consejo de Administracion "
							+ "de Green Towers Residencial.\n\n"
							+ "Su acceso al sistema vuelve al perfil de Propietario.\n\n"
							+ "Atentamente,\nGreenBuilding Group";
					enviarNotificacionCorreo(correo, asunto, cuerpo, "SISTEMA");
				}
				return true;
			}
		}
		return false;
	}

	// Metodos RESIDENTE

	/**
	 * Registra un residente adicional en el sistema asociandolo al apartamento
	 * indicado y envia una notificacion de bienvenida por correo. <b>pre</b> El
	 * objeto residente debe estar inicializado. El idApartamento debe corresponder
	 * a un apartamento existente en el sistema. <br>
	 * <b>post</b> El residente queda asociado al apartamento y persistido. Se envia
	 * correo si tiene correo valido. Retorna false si el apartamento no existe.
	 * <br>
	 * 
	 * @param residente     Objeto Residente a registrar. residente != null
	 * @param idApartamento ID del apartamento al que pertenece el residente.
	 *                      idApartamento != null, idApartamento != ""
	 * @return true si el registro fue exitoso, false si el apartamento no existe
	 */
	public boolean registrarResidente(Residente residente, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return false;
		}
		residente.setApartamento(apto);
		residenteDAO.crear(DataMapper.convertirResidenteAResidenteDTO(residente));

		String correo = residente.getCorreo();
		if (correo != null && !correo.isEmpty()) {
			String asunto = "Bienvenido a Green Towers Residencial";
			String cuerpo = "Estimado/a " + residente.getNombre() + ",\n\n"
					+ "Su registro como residente del apartamento " + apto.getNumero() + " ha sido exitoso.\n\n"
					+ "Bienvenido/a a la comunidad Green Towers Residencial.\n\n" + "Atentamente,\nGreenBuilding Group";

			Notificacion notif = new Notificacion();
			notif.setId("NOT-" + contadorIds++);
			notif.setDestinatario(correo);
			notif.setAsunto(asunto);
			notif.setCuerpo(cuerpo);
			notif.setTipo("SISTEMA");
			notif.setFechaEnvio(LocalDateTime.now());
			notif.setEnviada(false);
			notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

			boolean enviado = EmailSender.enviarCorreo(correo, asunto, cuerpo);
			if (enviado) {
				notif.setEnviada(true);
				int idxNotif = notificacionDAO.getListaNotificaciones().size() - 1;
				notificacionDAO.actualizar(idxNotif, DataMapper.convertirNotificacionANotificacionDTO(notif));
			}
		}

		return true;
	}

	/**
	 * Actualiza los datos de un residente existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * residentes. <br>
	 * <b>post</b> Si el indice es valido, los datos del residente quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index     Indice del residente a actualizar. index >= 0
	 * @param residente Objeto Residente con los nuevos datos. residente != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarResidente(int index, Residente residente) {
		return residenteDAO.actualizar(index, DataMapper.convertirResidenteAResidenteDTO(residente));
	}

	/**
	 * Elimina un residente del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de residentes. <br>
	 * <b>post</b> Si el indice es valido, el residente queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del residente a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarResidente(int index) {
		return residenteDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de residentes registrados en el sistema. <b>pre</b>
	 * El residenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Residente del sistema
	 */
	public ArrayList<Residente> obtenerTodosLosResidentes() {
		return residenteDAO.getListaResidentes();
	}

	/**
	 * Retorna la lista de residentes asociados a un apartamento especifico.
	 * <b>pre</b> El residenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con los residentes del apartamento indicado
	 */
	public ArrayList<Residente> obtenerResidentesPorApartamento(String idApartamento) {
		return residenteDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Retorna la lista de residentes con estado activo en el sistema. <b>pre</b> El
	 * residenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los residentes cuyo atributo activo es true
	 */
	public ArrayList<Residente> obtenerResidentesActivos() {
		ArrayList<Residente> resultado = new ArrayList<>();
		for (Residente r : residenteDAO.getListaResidentes()) {
			if (r.isActivo()) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todos los residentes del sistema.
	 * <b>pre</b> El residenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los residentes
	 */
	public String mostrarResidentes() {
		return residenteDAO.mostrar();
	}

	// Metodos VEHICULO

	/**
	 * Registra un vehiculo en el sistema asociandolo al apartamento indicado y
	 * marcandolo como autorizado por defecto. <b>pre</b> El objeto vehiculo debe
	 * estar inicializado con placa y tipo validos. El idApartamento debe
	 * corresponder a un apartamento existente. <br>
	 * <b>post</b> El vehiculo queda asociado al apartamento, marcado como
	 * autorizado y persistido. <br>
	 * 
	 * @param vehiculo      Objeto Vehiculo a registrar. vehiculo != null
	 * @param idApartamento ID del apartamento al que pertenece el vehiculo.
	 *                      idApartamento != null, idApartamento != ""
	 */
	public void registrarVehiculo(Vehiculo vehiculo, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto != null) {
			vehiculo.setApartamento(apto);
		}
		vehiculo.setAutorizado(true);
		vehiculoDAO.crear(DataMapper.convertirVehiculoAVehiculoDTO(vehiculo));
	}

	/**
	 * Actualiza los datos de un vehiculo existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * vehiculos. <br>
	 * <b>post</b> Si el indice es valido, los datos del vehiculo quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index    Indice del vehiculo a actualizar. index >= 0
	 * @param vehiculo Objeto Vehiculo con los nuevos datos. vehiculo != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarVehiculo(int index, Vehiculo vehiculo) {
		return vehiculoDAO.actualizar(index, DataMapper.convertirVehiculoAVehiculoDTO(vehiculo));
	}

	/**
	 * Elimina un vehiculo del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de vehiculos. <br>
	 * <b>post</b> Si el indice es valido, el vehiculo queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del vehiculo a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarVehiculo(int index) {
		return vehiculoDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de vehiculos registrados en el sistema. <b>pre</b>
	 * El vehiculoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Vehiculo del sistema
	 */
	public ArrayList<Vehiculo> obtenerTodosLosVehiculos() {
		return vehiculoDAO.getListaVehiculos();
	}

	/**
	 * Retorna la lista de vehiculos asociados a un apartamento especifico.
	 * <b>pre</b> El vehiculoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con los vehiculos del apartamento indicado
	 */
	public ArrayList<Vehiculo> obtenerVehiculosPorApartamento(String idApartamento) {
		return vehiculoDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Verifica si un vehiculo identificado por su placa esta autorizado para
	 * ingresar al conjunto. Un vehiculo esta autorizado si existe, su atributo
	 * autorizado es true y no tiene restriccion activa. <b>pre</b> El vehiculoDAO
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param placa Placa del vehiculo a verificar. placa != null, placa != ""
	 * @return true si el vehiculo esta autorizado y sin restriccion, false en caso
	 *         contrario
	 */
	public boolean vehiculoAutorizado(String placa) {
		for (Vehiculo v : vehiculoDAO.getListaVehiculos()) {
			if (v.getPlaca().equalsIgnoreCase(placa) && v.isAutorizado() && !v.isTieneRestriccion()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Aplica una restriccion de ingreso a un vehiculo identificado por su placa.
	 * <b>pre</b> El vehiculoDAO debe estar inicializado. La placa debe corresponder
	 * a un vehiculo existente. <br>
	 * <b>post</b> Si se encuentra el vehiculo, su atributo tieneRestriccion queda
	 * en true y se persiste. Retorna false si el vehiculo no existe. <br>
	 * 
	 * @param placa Placa del vehiculo a restringir. placa != null, placa != ""
	 * @return true si la restriccion fue aplicada, false si el vehiculo no fue
	 *         encontrado
	 */
	public boolean restringirVehiculo(String placa) {
		ArrayList<Vehiculo> lista = vehiculoDAO.getListaVehiculos();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPlaca().equalsIgnoreCase(placa)) {
				Vehiculo v = lista.get(i);
				v.setTieneRestriccion(true);
				vehiculoDAO.actualizar(i, DataMapper.convertirVehiculoAVehiculoDTO(v));
				return true;
			}
		}
		return false;
	}

	/**
	 * Levanta la restriccion de ingreso de un vehiculo identificado por su placa.
	 * <b>pre</b> El vehiculoDAO debe estar inicializado. La placa debe corresponder
	 * a un vehiculo existente. <br>
	 * <b>post</b> Si se encuentra el vehiculo, su atributo tieneRestriccion queda
	 * en false y se persiste. Retorna false si el vehiculo no existe. <br>
	 * 
	 * @param placa Placa del vehiculo al que se levanta la restriccion. placa !=
	 *              null, placa != ""
	 * @return true si la restriccion fue levantada, false si el vehiculo no fue
	 *         encontrado
	 */
	public boolean levantarRestriccionVehiculo(String placa) {
		ArrayList<Vehiculo> lista = vehiculoDAO.getListaVehiculos();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPlaca().equalsIgnoreCase(placa)) {
				Vehiculo v = lista.get(i);
				v.setTieneRestriccion(false);
				vehiculoDAO.actualizar(i, DataMapper.convertirVehiculoAVehiculoDTO(v));
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna una representacion en texto de todos los vehiculos del sistema.
	 * <b>pre</b> El vehiculoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los vehiculos
	 */
	public String mostrarVehiculos() {
		return vehiculoDAO.mostrar();
	}

	// Metodos MASCOTA

	/**
	 * Registra una mascota en el sistema asociandola al apartamento indicado.
	 * <b>pre</b> El objeto mascota debe estar inicializado. El idApartamento debe
	 * corresponder a un apartamento existente en el sistema. <br>
	 * <b>post</b> La mascota queda asociada al apartamento y persistida en el
	 * sistema. <br>
	 * 
	 * @param mascota       Objeto Mascota a registrar. mascota != null
	 * @param idApartamento ID del apartamento al que pertenece la mascota.
	 *                      idApartamento != null, idApartamento != ""
	 */
	public void registrarMascota(Mascota mascota, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto != null) {
			mascota.setApartamento(apto);
		}
		mascotaDAO.crear(DataMapper.convertirMascotaAMascotaDTO(mascota));
	}

	/**
	 * Actualiza los datos de una mascota existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de mascotas.
	 * <br>
	 * <b>post</b> Si el indice es valido, los datos de la mascota quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index   Indice de la mascota a actualizar. index >= 0
	 * @param mascota Objeto Mascota con los nuevos datos. mascota != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarMascota(int index, Mascota mascota) {
		return mascotaDAO.actualizar(index, DataMapper.convertirMascotaAMascotaDTO(mascota));
	}

	/**
	 * Elimina una mascota del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de mascotas. <br>
	 * <b>post</b> Si el indice es valido, la mascota queda eliminada y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice de la mascota a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarMascota(int index) {
		return mascotaDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de mascotas registradas en el sistema. <b>pre</b>
	 * El mascotaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Mascota del sistema
	 */
	public ArrayList<Mascota> obtenerTodasLasMascotas() {
		return mascotaDAO.getListaMascotas();
	}

	/**
	 * Retorna la lista de mascotas asociadas a un apartamento especifico.
	 * <b>pre</b> El mascotaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con las mascotas del apartamento indicado
	 */
	public ArrayList<Mascota> obtenerMascotasPorApartamento(String idApartamento) {
		return mascotaDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Retorna la lista de mascotas que no tienen sus vacunas al dia. <b>pre</b> El
	 * mascotaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las mascotas cuyo atributo vacunasAlDia es false
	 */
	public ArrayList<Mascota> obtenerMascotasSinVacunas() {
		ArrayList<Mascota> resultado = new ArrayList<>();
		for (Mascota m : mascotaDAO.getListaMascotas()) {
			if (!m.isVacunasAlDia()) {
				resultado.add(m);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todas las mascotas del sistema.
	 * <b>pre</b> El mascotaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las mascotas
	 */
	public String mostrarMascotas() {
		return mascotaDAO.mostrar();
	}

	// Metodos PARQUEADERO

	/**
	 * Registra un parqueadero en el sistema asociandolo al conjunto indicado y
	 * estableciendo su estado inicial como DISPONIBLE. <b>pre</b> El objeto
	 * parqueadero debe estar inicializado. El idConjunto debe corresponder a un
	 * conjunto existente. <br>
	 * <b>post</b> El parqueadero queda asociado al conjunto con estado DISPONIBLE y
	 * persistido. <br>
	 * 
	 * @param parqueadero Objeto Parqueadero a registrar. parqueadero != null
	 * @param idConjunto  ID del conjunto al que pertenece el parqueadero.
	 *                    idConjunto != null, idConjunto != ""
	 */
	public void registrarParqueadero(Parqueadero parqueadero, String idConjunto) {
		Conjunto conjunto = buscarConjuntoPorId(idConjunto);
		if (conjunto != null) {
			parqueadero.setConjunto(conjunto);
		}
		parqueadero.setEstado("DISPONIBLE");
		parqueaderoDAO.crear(DataMapper.convertirParqueaderoAParqueaderoDTO(parqueadero));
	}

	/**
	 * Actualiza los datos de un parqueadero existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * parqueaderos. <br>
	 * <b>post</b> Si el indice es valido, los datos del parqueadero quedan
	 * actualizados y persistidos. <br>
	 * 
	 * @param index       Indice del parqueadero a actualizar. index >= 0
	 * @param parqueadero Objeto Parqueadero con los nuevos datos. parqueadero !=
	 *                    null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarParqueadero(int index, Parqueadero parqueadero) {
		return parqueaderoDAO.actualizar(index, DataMapper.convertirParqueaderoAParqueaderoDTO(parqueadero));
	}

	/**
	 * Elimina un parqueadero del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de parqueaderos. <br>
	 * <b>post</b> Si el indice es valido, el parqueadero queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del parqueadero a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarParqueadero(int index) {
		return parqueaderoDAO.eliminar(index);
	}

	/**
	 * Asigna un vehiculo a un parqueadero disponible cambiando el estado del
	 * parqueadero a OCUPADO. <b>pre</b> El idParqueadero debe corresponder a un
	 * parqueadero en estado DISPONIBLE. El idVehiculo debe corresponder a un
	 * vehiculo existente. <br>
	 * <b>post</b> Si ambos existen y el parqueadero esta disponible, el vehiculo
	 * queda asignado y el parqueadero en estado OCUPADO. Retorna false si no es
	 * posible la asignacion. <br>
	 * 
	 * @param idParqueadero ID del parqueadero a asignar. idParqueadero != null,
	 *                      idParqueadero != ""
	 * @param idVehiculo    ID del vehiculo a asignar al parqueadero. idVehiculo !=
	 *                      null, idVehiculo != ""
	 * @return true si la asignacion fue exitosa, false si el parqueadero no esta
	 *         disponible o no se encuentran
	 */
	public boolean asignarParqueadero(String idParqueadero, String idVehiculo) {
		ArrayList<Parqueadero> lista = parqueaderoDAO.getListaParqueaderos();
		for (int i = 0; i < lista.size(); i++) {
			Parqueadero p = lista.get(i);
			if (p.getId().equals(idParqueadero)) {
				if (!p.getEstado().equals("DISPONIBLE")) {
					return false;
				}
				for (Vehiculo v : vehiculoDAO.getListaVehiculos()) {
					if (v.getId().equals(idVehiculo)) {
						p.setVehiculoAsignado(v);
						p.setEstado("OCUPADO");
						parqueaderoDAO.actualizar(i, DataMapper.convertirParqueaderoAParqueaderoDTO(p));
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Libera un parqueadero removiendo el vehiculo asignado y cambiando su estado a
	 * DISPONIBLE. <b>pre</b> El idParqueadero debe corresponder a un parqueadero
	 * existente en el sistema. <br>
	 * <b>post</b> Si se encuentra el parqueadero, su vehiculo asignado queda en
	 * null y su estado en DISPONIBLE. Retorna false si el parqueadero no fue
	 * encontrado. <br>
	 * 
	 * @param idParqueadero ID del parqueadero a liberar. idParqueadero != null,
	 *                      idParqueadero != ""
	 * @return true si el parqueadero fue liberado, false si no fue encontrado
	 */
	public boolean liberarParqueadero(String idParqueadero) {
		ArrayList<Parqueadero> lista = parqueaderoDAO.getListaParqueaderos();
		for (int i = 0; i < lista.size(); i++) {
			Parqueadero p = lista.get(i);
			if (p.getId().equals(idParqueadero)) {
				p.setVehiculoAsignado(null);
				p.setEstado("DISPONIBLE");
				parqueaderoDAO.actualizar(i, DataMapper.convertirParqueaderoAParqueaderoDTO(p));
				return true;
			}
		}
		return false;
	}

	/**
	 * Cambia el estado de un parqueadero a MANTENIMIENTO inhabilitandolo
	 * temporalmente. <b>pre</b> El idParqueadero debe corresponder a un parqueadero
	 * existente. <br>
	 * <b>post</b> Si se encuentra el parqueadero, su estado queda en MANTENIMIENTO
	 * y se persiste. Retorna false si no fue encontrado. <br>
	 * 
	 * @param idParqueadero ID del parqueadero a poner en mantenimiento.
	 *                      idParqueadero != null, idParqueadero != ""
	 * @return true si el cambio de estado fue exitoso, false si el parqueadero no
	 *         fue encontrado
	 */
	public boolean ponerParqueaderoEnMantenimiento(String idParqueadero) {
		ArrayList<Parqueadero> lista = parqueaderoDAO.getListaParqueaderos();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idParqueadero)) {
				Parqueadero p = lista.get(i);
				p.setEstado("MANTENIMIENTO");
				parqueaderoDAO.actualizar(i, DataMapper.convertirParqueaderoAParqueaderoDTO(p));
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna la lista completa de parqueaderos registrados en el sistema.
	 * <b>pre</b> El parqueaderoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Parqueadero del sistema
	 */
	public ArrayList<Parqueadero> obtenerTodosLosParqueaderos() {
		return parqueaderoDAO.getListaParqueaderos();
	}

	/**
	 * Retorna la lista de parqueaderos con estado DISPONIBLE. <b>pre</b> El
	 * parqueaderoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los parqueaderos disponibles
	 */
	public ArrayList<Parqueadero> obtenerParqueaderosDisponibles() {
		ArrayList<Parqueadero> resultado = new ArrayList<>();
		for (Parqueadero p : parqueaderoDAO.getListaParqueaderos()) {
			if (p.getEstado().equals("DISPONIBLE")) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de parqueaderos filtrados por tipo. <b>pre</b> El
	 * parqueaderoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param tipo Tipo de parqueadero a filtrar (CARRO, MOTO, BICICLETA, ELECTRICO,
	 *             VISITANTE). tipo != null, tipo != ""
	 * @return ArrayList con los parqueaderos del tipo indicado
	 */
	public ArrayList<Parqueadero> obtenerParqueaderosPorTipo(String tipo) {
		ArrayList<Parqueadero> resultado = new ArrayList<>();
		for (Parqueadero p : parqueaderoDAO.getListaParqueaderos()) {
			if (p.getTipo().equals(tipo)) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todos los parqueaderos del sistema.
	 * <b>pre</b> El parqueaderoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los parqueaderos
	 */
	public String mostrarParqueaderos() {
		return parqueaderoDAO.mostrar();
	}

	// Metodos ZONA COMUN

	/**
	 * Registra una nueva zona comun en el sistema. <b>pre</b> El objeto zona debe
	 * estar inicializado con nombre, tipo y conjunto validos. <br>
	 * <b>post</b> La zona comun queda persistida en el sistema. <br>
	 * 
	 * @param zona Objeto ZonaComun a registrar. zona != null
	 */
	public void registrarZonaComun(ZonaComun zona) {
		zonaComunDAO.crear(DataMapper.convertirZonaComunAZonaComunDTO(zona));
	}

	/**
	 * Actualiza los datos de una zona comun existente por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de zonas
	 * comunes. <br>
	 * <b>post</b> Si el indice es valido, los datos de la zona quedan actualizados
	 * y persistidos. <br>
	 * 
	 * @param index Indice de la zona comun a actualizar. index >= 0
	 * @param zona  Objeto ZonaComun con los nuevos datos. zona != null
	 * @return true si la actualizacion fue exitosa, false si el indice no es valido
	 */
	public boolean actualizarZonaComun(int index, ZonaComun zona) {
		return zonaComunDAO.actualizar(index, DataMapper.convertirZonaComunAZonaComunDTO(zona));
	}

	/**
	 * Elimina una zona comun del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de zonas comunes. <br>
	 * <b>post</b> Si el indice es valido, la zona queda eliminada y la persistencia
	 * actualizada. <br>
	 * 
	 * @param index Indice de la zona comun a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarZonaComun(int index) {
		return zonaComunDAO.eliminar(index);
	}

	/**
	 * Cambia el estado de una zona comun a BLOQUEADA impidiendo nuevas reservas.
	 * <b>pre</b> El idZona debe corresponder a una zona comun existente. <br>
	 * <b>post</b> Si se encuentra la zona, su estado queda en BLOQUEADA y se
	 * persiste. Retorna false si no fue encontrada. <br>
	 * 
	 * @param idZona ID de la zona comun a bloquear. idZona != null, idZona != ""
	 * @return true si el bloqueo fue exitoso, false si la zona no fue encontrada
	 */
	public boolean bloquearZona(String idZona) {
		ArrayList<ZonaComun> lista = zonaComunDAO.getListaZonasComunes();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idZona)) {
				ZonaComun z = lista.get(i);
				z.setEstado("BLOQUEADA");
				zonaComunDAO.actualizar(i, DataMapper.convertirZonaComunAZonaComunDTO(z));
				return true;
			}
		}
		return false;
	}

	/**
	 * Cambia el estado de una zona comun a DISPONIBLE habilitando nuevas reservas.
	 * <b>pre</b> El idZona debe corresponder a una zona comun existente. <br>
	 * <b>post</b> Si se encuentra la zona, su estado queda en DISPONIBLE y se
	 * persiste. Retorna false si no fue encontrada. <br>
	 * 
	 * @param idZona ID de la zona comun a desbloquear. idZona != null, idZona != ""
	 * @return true si el desbloqueo fue exitoso, false si la zona no fue encontrada
	 */
	public boolean desbloquearZona(String idZona) {
		ArrayList<ZonaComun> lista = zonaComunDAO.getListaZonasComunes();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idZona)) {
				ZonaComun z = lista.get(i);
				z.setEstado("DISPONIBLE");
				zonaComunDAO.actualizar(i, DataMapper.convertirZonaComunAZonaComunDTO(z));
				return true;
			}
		}
		return false;
	}

	/**
	 * Cambia el estado de una zona comun a MANTENIMIENTO y notifica a todos los
	 * residentes activos. <b>pre</b> El idZona debe corresponder a una zona comun
	 * existente. <br>
	 * <b>post</b> Si se encuentra la zona, su estado queda en MANTENIMIENTO, se
	 * persiste y se envia una notificacion por correo a todos los residentes
	 * activos con correo registrado. Retorna false si la zona no fue encontrada.
	 * <br>
	 * 
	 * @param idZona ID de la zona comun a poner en mantenimiento. idZona != null,
	 *               idZona != ""
	 * @return true si el cambio de estado fue exitoso, false si la zona no fue
	 *         encontrada
	 */
	public boolean ponerZonaEnMantenimiento(String idZona) {
		ArrayList<ZonaComun> lista = zonaComunDAO.getListaZonasComunes();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idZona)) {
				ZonaComun z = lista.get(i);
				z.setEstado("MANTENIMIENTO");
				zonaComunDAO.actualizar(i, DataMapper.convertirZonaComunAZonaComunDTO(z));

				String asunto = "Zona comun en mantenimiento: " + z.getNombre();
				String cuerpo = "Le informamos que la zona comun \"" + z.getNombre()
						+ "\" se encuentra temporalmente fuera de servicio por mantenimiento.\n\n"
						+ "Lamentamos los inconvenientes.\n\nAtentamente,\nGreenBuilding Group";
				for (Residente r : residenteDAO.getListaResidentes()) {
					if (r.isActivo()) {
						String correo = r.getCorreo();
						if (correo != null && !correo.isEmpty()) {
							enviarNotificacionCorreo(correo, asunto, cuerpo, "MANTENIMIENTO_ZONA");
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna la lista completa de zonas comunes registradas en el sistema.
	 * <b>pre</b> El zonaComunDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos ZonaComun del sistema
	 */
	public ArrayList<ZonaComun> obtenerTodasLasZonasComunes() {
		return zonaComunDAO.getListaZonasComunes();
	}

	/**
	 * Retorna una zona comun por su identificador unico. <b>pre</b> El zonaComunDAO
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador de la zona comun. id != null, id != ""
	 * @return El objeto ZonaComun con el id indicado, o null si no existe
	 */
	public ZonaComun obtenerZonaComunPorId(String id) {
		return buscarZonaComunPorId(id);
	}

	/**
	 * Retorna la lista de zonas comunes pertenecientes a un conjunto especifico.
	 * <b>pre</b> El zonaComunDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idConjunto ID del conjunto. idConjunto != null, idConjunto != ""
	 * @return ArrayList con las zonas comunes del conjunto indicado
	 */
	public ArrayList<ZonaComun> obtenerZonasComunesPorConjunto(String idConjunto) {
		ArrayList<ZonaComun> resultado = new ArrayList<>();
		for (ZonaComun z : zonaComunDAO.getListaZonasComunes()) {
			if (z.getConjunto() != null && z.getConjunto().getId().equals(idConjunto)) {
				resultado.add(z);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de zonas comunes con estado DISPONIBLE. <b>pre</b> El
	 * zonaComunDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las zonas comunes disponibles para reservar
	 */
	public ArrayList<ZonaComun> obtenerZonasDisponibles() {
		ArrayList<ZonaComun> resultado = new ArrayList<>();
		for (ZonaComun z : zonaComunDAO.getListaZonasComunes()) {
			if (z.getEstado().equals("DISPONIBLE")) {
				resultado.add(z);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todas las zonas comunes del sistema.
	 * <b>pre</b> El zonaComunDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las zonas comunes
	 */
	public String mostrarZonasComunes() {
		return zonaComunDAO.mostrar();
	}

	// Metodos RESERVA

	/**
	 * Crea una reserva de zona comun validando disponibilidad, obligaciones
	 * pendientes, sanciones, anticipacion minima, limite mensual de reservas y
	 * solapamiento de horarios. Si la reserva es exitosa, envia una notificacion
	 * por correo al responsable del apartamento. <b>pre</b> La zona comun debe
	 * estar en estado DISPONIBLE. El apartamento no debe tener obligaciones
	 * pendientes ni sanciones activas. La fecha no debe violar la anticipacion
	 * minima configurada ni el limite mensual de reservas. <br>
	 * <b>post</b> Si todas las validaciones pasan, la reserva queda creada con
	 * estado CONFIRMADA y el responsable del apartamento recibe una notificacion.
	 * Retorna "OK" si fue exitosa o un mensaje de error descriptivo si fallo alguna
	 * validacion. <br>
	 * 
	 * @param idZona        ID de la zona comun a reservar. idZona != null, idZona
	 *                      != ""
	 * @param idApartamento ID del apartamento que realiza la reserva. idApartamento
	 *                      != null, idApartamento != ""
	 * @param fecha         Fecha de la reserva. fecha != null, fecha >= hoy +
	 *                      anticipacion minima
	 * @param horaInicio    Hora de inicio de la reserva. horaInicio != null,
	 *                      horaInicio < horaFin
	 * @param horaFin       Hora de fin de la reserva. horaFin != null, horaFin >
	 *                      horaInicio
	 * @return "OK" si la reserva fue creada exitosamente, o un String con el motivo
	 *         del rechazo
	 */
	public String crearReserva(String idZona, String idApartamento, LocalDate fecha, LocalTime horaInicio,
			LocalTime horaFin) {
		ZonaComun zona = buscarZonaComunPorId(idZona);
		if (zona == null)
			return "Zona comun no encontrada.";
		if (!zona.getEstado().equals("DISPONIBLE"))
			return "La zona no esta disponible.";

		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null)
			return "Apartamento no encontrado.";
		if (!obligacionDAO.buscarPendientesPorApartamento(idApartamento).isEmpty())
			return "El apartamento tiene obligaciones pendientes de pago.";
		if (apto.getArrendatario() != null && apto.getArrendatario().tieneSancionesActivas())
			return "El arrendatario del apartamento tiene sanciones activas.";

		int horasAnticipacion = ConfigurationManager.getInt("reserva.horas.anticipacion.minima");
		LocalDateTime inicioDT = LocalDateTime.of(fecha, horaInicio);
		if (LocalDateTime.now().plusHours(horasAnticipacion).isAfter(inicioDT)) {
			return "La reserva debe hacerse con al menos " + horasAnticipacion + " hora(s) de anticipacion.";
		}

		int maxReservasMes = ConfigurationManager.getInt("reserva.max.por.mes");
		int reservasMesActual = 0;
		for (Reserva r : reservaDAO.getListaReservas()) {
			if (r.getApartamento() != null && r.getApartamento().getId().equals(idApartamento)
					&& r.getEstado().equals("CONFIRMADA") && r.getFecha().getMonth() == fecha.getMonth()
					&& r.getFecha().getYear() == fecha.getYear()) {
				reservasMesActual++;
			}
		}
		if (reservasMesActual >= maxReservasMes) {
			return "El apartamento ya alcanzo el maximo de " + maxReservasMes + " reserva(s) por mes.";
		}

		for (Reserva r : reservaDAO.getListaReservas()) {
			if (r.getZona() != null && r.getZona().getId().equals(idZona) && r.getFecha().equals(fecha)
					&& r.getEstado().equals("CONFIRMADA")
					&& horasSeSuperponen(horaInicio, horaFin, r.getHoraInicio(), r.getHoraFin())) {
				return "Ya existe una reserva confirmada en ese horario.";
			}
		}

		Reserva nueva = new Reserva();
		nueva.setId("RSV-" + contadorIds++);
		nueva.setZona(zona);
		nueva.setApartamento(apto);
		nueva.setFecha(fecha);
		nueva.setHoraInicio(horaInicio);
		nueva.setHoraFin(horaFin);
		nueva.setEstado("CONFIRMADA");
		nueva.setFechaSolicitud(LocalDateTime.now());
		reservaDAO.crear(DataMapper.convertirReservaAReservaDTO(nueva));

		String correoDestino = obtenerCorreoResponsableApartamento(apto);
		if (correoDestino != null && !correoDestino.isEmpty()) {
			String asunto = "Reserva confirmada - " + zona.getNombre();
			String cuerpo = "Su reserva ha sido confirmada.\n\n" + "Zona: " + zona.getNombre() + "\n" + "Fecha: "
					+ fecha + "\n" + "Horario: " + horaInicio + " - " + horaFin + "\n\n"
					+ "Recuerde respetar las normas de uso del espacio.\n\n" + "Atentamente,\nGreenBuilding Group";
			enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "RESERVA");
		}
		return "OK";
	}

	/**
	 * Cancela una reserva existente por su indice, registra el motivo de
	 * cancelacion, notifica al responsable del apartamento y puede generar una
	 * multa si la cancelacion es tardia segun la configuracion del sistema.
	 * <b>pre</b> El index debe ser un indice valido. La reserva no debe estar ya en
	 * estado CANCELADA o COMPLETADA. <br>
	 * <b>post</b> La reserva queda en estado CANCELADA, el responsable recibe
	 * notificacion y si la cancelacion es tardia se genera una obligacion de tipo
	 * MULTA. Retorna false si el indice no es valido o la reserva ya fue procesada.
	 * <br>
	 * 
	 * @param index  Indice de la reserva a cancelar. index >= 0
	 * @param motivo Descripcion del motivo de cancelacion. motivo != null
	 * @return true si la cancelacion fue exitosa, false en caso contrario
	 */
	public boolean cancelarReserva(int index, String motivo) {
		ArrayList<Reserva> lista = reservaDAO.getListaReservas();
		if (index < 0 || index >= lista.size())
			return false;

		Reserva r = lista.get(index);
		if (r.getEstado().equals("CANCELADA") || r.getEstado().equals("COMPLETADA"))
			return false;

		r.setEstado("CANCELADA");
		r.setMotivoCancelacion(motivo);
		reservaDAO.actualizar(index, DataMapper.convertirReservaAReservaDTO(r));

		if (r.getApartamento() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(r.getApartamento());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String zonaNombre;
				if (r.getZona() != null) {
					zonaNombre = r.getZona().getNombre();
				} else {
					zonaNombre = "zona comun";
				}

				int horasSinMulta = ConfigurationManager.getInt("reserva.horas.cancelacion.sin.multa");
				LocalDateTime inicioDT = LocalDateTime.of(r.getFecha(), r.getHoraInicio());
				boolean generaMulta = LocalDateTime.now().plusHours(horasSinMulta).isAfter(inicioDT);

				String asunto = "Reserva cancelada - " + zonaNombre;
				String cuerpo = "Su reserva ha sido cancelada.\n\n" + "Zona: " + zonaNombre + "\n" + "Fecha: "
						+ r.getFecha() + "\n" + "Horario: " + r.getHoraInicio() + " - " + r.getHoraFin() + "\n"
						+ "Motivo: " + motivo + "\n";

				if (generaMulta) {
					cuerpo += "\nAVISO: La cancelacion se realizo con menos de " + horasSinMulta
							+ " hora(s) de anticipacion. Puede generarse una multa.\n";
				} else {
					cuerpo += "\nNo se generara multa por esta cancelacion.\n";
				}

				cuerpo += "\nAtentamente,\nGreenBuilding Group";

				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "RESERVA");

				if (generaMulta && r.getApartamento() != null) {
					Obligacion multa = new Obligacion();
					multa.setId("MUL-" + contadorIds++);
					multa.setApartamento(r.getApartamento());
					multa.setConcepto("Multa por cancelacion tardia de reserva - " + zonaNombre);
					multa.setMonto(ConfigurationManager.getDouble("multa.monto.defecto"));
					multa.setFechaGeneracion(LocalDate.now());
					multa.setFechaLimite(LocalDate.now().plusDays(ConfigurationManager.getInt("multa.dias.limite")));
					multa.setEstado("PENDIENTE");
					multa.setTipo("MULTA");
					multa.setGeneradaPor("SISTEMA");
					obligacionDAO.crear(DataMapper.convertirObligacionAObligacionDTO(multa));
				}
			}

		}
		return true;
	}

	/**
	 * Marca una reserva como COMPLETADA si se encuentra en estado CONFIRMADA.
	 * <b>pre</b> El index debe ser un indice valido. La reserva debe estar en
	 * estado CONFIRMADA. <br>
	 * <b>post</b> Si las condiciones se cumplen, la reserva queda en estado
	 * COMPLETADA y se persiste. Retorna false si el indice no es valido o la
	 * reserva no esta confirmada. <br>
	 * 
	 * @param index Indice de la reserva a completar. index >= 0
	 * @return true si la operacion fue exitosa, false en caso contrario
	 */
	public boolean completarReserva(int index) {
		ArrayList<Reserva> lista = reservaDAO.getListaReservas();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Reserva r = lista.get(index);
		if (!r.getEstado().equals("CONFIRMADA")) {
			return false;
		}
		r.setEstado("COMPLETADA");
		reservaDAO.actualizar(index, DataMapper.convertirReservaAReservaDTO(r));
		return true;
	}

	/**
	 * Elimina una reserva del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de reservas. <br>
	 * <b>post</b> Si el indice es valido, la reserva queda eliminada y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice de la reserva a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarReserva(int index) {
		return reservaDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de reservas registradas en el sistema. <b>pre</b>
	 * El reservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Reserva del sistema
	 */
	public ArrayList<Reserva> obtenerTodasLasReservas() {
		return reservaDAO.getListaReservas();
	}

	/**
	 * Retorna la lista de reservas activas (estado CONFIRMADA) de una zona comun
	 * especifica. <b>pre</b> El reservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idZona ID de la zona comun. idZona != null, idZona != ""
	 * @return ArrayList con las reservas confirmadas de la zona indicada
	 */
	public ArrayList<Reserva> obtenerReservasActivasDeZona(String idZona) {
		ArrayList<Reserva> resultado = new ArrayList<>();
		for (Reserva r : reservaDAO.getListaReservas()) {
			if (r.getZona() != null && r.getZona().getId().equals(idZona) && r.getEstado().equals("CONFIRMADA")) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Recorre todas las reservas confirmadas para el dia siguiente y envia un
	 * recordatorio por correo al responsable del apartamento correspondiente.
	 * <b>pre</b> El reservaDAO y el notificacionDAO deben estar inicializados. <br>
	 * <b>post</b> Se envian correos de recordatorio a los responsables de reservas
	 * del dia siguiente. <br>
	 */
	public void verificarReservasProximas() {
		LocalDate manana = LocalDate.now().plusDays(1);
		for (Reserva r : reservaDAO.getListaReservas()) {
			if ("CONFIRMADA".equals(r.getEstado()) && r.getFecha() != null && r.getFecha().equals(manana)) {
				Apartamento apto = r.getApartamento();
				if (apto != null) {
					String correo = obtenerCorreoResponsableApartamento(apto);
					if (correo != null && !correo.isEmpty()) {
						String zonaNombre;
						if (r.getZona() != null) {
							zonaNombre = r.getZona().getNombre();
						} else {
							zonaNombre = "Zona comun";
						}

						String zonaNombreCuerpo;
						if (r.getZona() != null) {
							zonaNombreCuerpo = r.getZona().getNombre();
						} else {
							zonaNombreCuerpo = "N/A";
						}

						String asunto = "Recordatorio: Reserva mañana - " + zonaNombre;
						String cuerpo = "Le recordamos que tiene una reserva programada para mañana.\n\n" + "Zona: "
								+ zonaNombreCuerpo + "\n" + "Fecha: " + r.getFecha() + "\n" + "Hora: "
								+ r.getHoraInicio() + " - " + r.getHoraFin() + "\n\nAtentamente,\nGreenBuilding Group";

						enviarNotificacionCorreo(correo, asunto, cuerpo, "RECORDATORIO_RESERVA");
					}
				}
			}
		}
	}

	/**
	 * Retorna la lista de reservas asociadas a un apartamento especifico.
	 * <b>pre</b> El reservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con las reservas del apartamento indicado
	 */
	public ArrayList<Reserva> obtenerReservasPorApartamento(String idApartamento) {
		return reservaDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Retorna la lista de reservas para una fecha especifica. <b>pre</b> El
	 * reservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param fecha Fecha a consultar. fecha != null
	 * @return ArrayList con las reservas de la fecha indicada
	 */
	public ArrayList<Reserva> obtenerReservasPorFecha(LocalDate fecha) {
		return reservaDAO.buscarPorFecha(fecha);
	}

	/**
	 * Retorna la lista de reservas filtradas por estado. <b>pre</b> El reservaDAO
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param estado Estado de reserva a filtrar (CONFIRMADA, CANCELADA,
	 *               COMPLETADA). estado != null, estado != ""
	 * @return ArrayList con las reservas que tienen el estado indicado
	 */
	public ArrayList<Reserva> obtenerReservasPorEstado(String estado) {
		return reservaDAO.buscarPorEstado(estado);
	}

	/**
	 * Retorna una representacion en texto de todas las reservas del sistema.
	 * <b>pre</b> El reservaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las reservas
	 */
	public String mostrarReservas() {
		return reservaDAO.mostrar();
	}

	// Metodos SOLICITUD MANTENIMIENTO

	/**
	 * Crea una solicitud de mantenimiento en el sistema asignando automaticamente
	 * la fecha de registro, el estado REGISTRADA y la fecha limite segun la
	 * prioridad configurada en properties. <b>pre</b> El objeto solicitud debe
	 * estar inicializado con descripcion, tipo y prioridad validos. <br>
	 * <b>post</b> La solicitud queda registrada con estado REGISTRADA, fecha de
	 * registro actual y fecha limite calculada segun la prioridad (ALTA, MEDIA,
	 * BAJA) desde config.properties. <br>
	 * 
	 * @param solicitud Objeto SolicitudMantenimiento a crear. solicitud != null
	 */
	public void crearSolicitudMantenimiento(SolicitudMantenimiento solicitud) {
		solicitud.setFechaRegistro(LocalDateTime.now());
		switch (solicitud.getPrioridad()) {
		case "ALTA": {
			solicitud.setFechaLimite(
					LocalDate.now().plusDays(ConfigurationManager.getInt("solicitud.alta.dias.limite")));
			break;
		}
		case "MEDIA": {
			solicitud.setFechaLimite(
					LocalDate.now().plusDays(ConfigurationManager.getInt("solicitud.media.dias.limite")));
			break;
		}
		case "BAJA": {
			solicitud.setFechaLimite(
					LocalDate.now().plusDays(ConfigurationManager.getInt("solicitud.baja.dias.limite")));
			break;
		}
		default: {
			solicitud.setFechaLimite(
					LocalDate.now().plusDays(ConfigurationManager.getInt("solicitud.baja.dias.limite")));
			break;
		}
		}
		solicitud.setEstado("REGISTRADA");
		solicitudMantenimientoDAO.crear(DataMapper.convertirSolicitudASolicitudDTO(solicitud));
	}

	/**
	 * Asigna un tecnico a una solicitud de mantenimiento en estado REGISTRADA o
	 * ASIGNADA y cambia su estado a ASIGNADA. <b>pre</b> El index debe ser un
	 * indice valido. La solicitud debe estar en estado REGISTRADA o ASIGNADA. <br>
	 * <b>post</b> Si las condiciones se cumplen, el tecnico queda asignado y el
	 * estado cambia a ASIGNADA. Retorna false si el indice no es valido o el estado
	 * no permite la asignacion. <br>
	 * 
	 * @param index         Indice de la solicitud a la que se asigna el tecnico.
	 *                      index >= 0
	 * @param nombreTecnico Nombre del tecnico a asignar. nombreTecnico != null,
	 *                      nombreTecnico != ""
	 * @return true si la asignacion fue exitosa, false en caso contrario
	 */
	public boolean asignarTecnico(int index, String nombreTecnico) {
		ArrayList<SolicitudMantenimiento> lista = solicitudMantenimientoDAO.getListaSolicitudes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		SolicitudMantenimiento s = lista.get(index);
		if (!s.getEstado().equals("REGISTRADA") && !s.getEstado().equals("ASIGNADA")) {
			return false;
		}
		s.setTecnicoAsignado(nombreTecnico);
		s.setEstado("ASIGNADA");
		solicitudMantenimientoDAO.actualizar(index, DataMapper.convertirSolicitudASolicitudDTO(s));
		return true;
	}

	/**
	 * Cambia el estado de una solicitud de mantenimiento de ASIGNADA a EN_PROGRESO
	 * y notifica al responsable del apartamento solicitante. <b>pre</b> El index
	 * debe ser un indice valido. La solicitud debe estar en estado ASIGNADA. <br>
	 * <b>post</b> Si las condiciones se cumplen, el estado queda en EN_PROGRESO y
	 * se envia notificacion al responsable del apartamento. Retorna false si falla
	 * alguna condicion. <br>
	 * 
	 * @param index Indice de la solicitud a iniciar. index >= 0
	 * @return true si el inicio fue exitoso, false en caso contrario
	 */
	public boolean iniciarSolicitudMantenimiento(int index) {
		ArrayList<SolicitudMantenimiento> lista = solicitudMantenimientoDAO.getListaSolicitudes();
		if (index < 0 || index >= lista.size())
			return false;

		SolicitudMantenimiento s = lista.get(index);
		if (!s.getEstado().equals("ASIGNADA"))
			return false;

		s.setEstado("EN_PROGRESO");
		solicitudMantenimientoDAO.actualizar(index, DataMapper.convertirSolicitudASolicitudDTO(s));
		if (s.getAptSolicitante() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(s.getAptSolicitante());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String asunto = "Solicitud de mantenimiento en progreso";
				String cuerpo = "Su solicitud de mantenimiento ha iniciado.\n\n" + "Descripcion: " + s.getDescripcion()
						+ "\n" + "Tecnico asignado: "
						+ (s.getTecnicoAsignado() != null ? s.getTecnicoAsignado() : "Por asignar") + "\n"
						+ "Estado: EN PROGRESO\n\n" + "Atentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "MANTENIMIENTO");
			}
		}
		return true;
	}

	/**
	 * Cierra una solicitud de mantenimiento cambiando su estado a COMPLETADA,
	 * registra observaciones y fecha de cierre, y notifica al responsable del
	 * apartamento. <b>pre</b> El index debe ser un indice valido dentro de la lista
	 * de solicitudes. <br>
	 * <b>post</b> La solicitud queda en estado COMPLETADA con fecha de cierre y
	 * observaciones registradas. Se envia notificacion al responsable del
	 * apartamento si tiene correo. <br>
	 * 
	 * @param index         Indice de la solicitud a cerrar. index >= 0
	 * @param observaciones Observaciones del cierre o resultado del mantenimiento.
	 *                      observaciones != null
	 * @return true si el cierre fue exitoso, false si el indice no es valido
	 */
	public boolean cerrarSolicitudMantenimiento(int index, String observaciones) {
		ArrayList<SolicitudMantenimiento> lista = solicitudMantenimientoDAO.getListaSolicitudes();
		if (index < 0 || index >= lista.size())
			return false;

		SolicitudMantenimiento s = lista.get(index);
		s.setEstado("COMPLETADA");
		s.setFechaCierre(LocalDateTime.now());
		s.setObservaciones(observaciones);
		solicitudMantenimientoDAO.actualizar(index, DataMapper.convertirSolicitudASolicitudDTO(s));

		if (s.getAptSolicitante() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(s.getAptSolicitante());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String asunto = "Solicitud de mantenimiento completada";
				String cuerpo = "Su solicitud de mantenimiento ha sido completada.\n\n" + "Descripcion: "
						+ s.getDescripcion() + "\n" + "Observaciones: " + observaciones + "\n" + "Fecha de cierre: "
						+ s.getFechaCierre() + "\n\n" + "Atentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "MANTENIMIENTO");
			}
		}
		return true;
	}

	/**
	 * Rechaza una solicitud de mantenimiento cambiando su estado a RECHAZADA y
	 * registrando el motivo. <b>pre</b> El index debe ser un indice valido dentro
	 * de la lista de solicitudes. <br>
	 * <b>post</b> Si el indice es valido, la solicitud queda en estado RECHAZADA
	 * con el motivo registrado en observaciones y los cambios persistidos. Retorna
	 * false si el indice no es valido. <br>
	 * 
	 * @param index  Indice de la solicitud a rechazar. index >= 0
	 * @param motivo Motivo del rechazo de la solicitud. motivo != null
	 * @return true si el rechazo fue exitoso, false si el indice no es valido
	 */
	public boolean rechazarSolicitudMantenimiento(int index, String motivo) {
		ArrayList<SolicitudMantenimiento> lista = solicitudMantenimientoDAO.getListaSolicitudes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		SolicitudMantenimiento s = lista.get(index);
		s.setEstado("RECHAZADA");
		s.setObservaciones(motivo);
		solicitudMantenimientoDAO.actualizar(index, DataMapper.convertirSolicitudASolicitudDTO(s));
		return true;
	}

	/**
	 * Elimina una solicitud de mantenimiento del sistema por su indice en la lista.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * solicitudes. <br>
	 * <b>post</b> Si el indice es valido, la solicitud queda eliminada y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice de la solicitud a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarSolicitudMantenimiento(int index) {
		return solicitudMantenimientoDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de solicitudes de mantenimiento registradas en el
	 * sistema. <b>pre</b> El solicitudMantenimientoDAO debe estar inicializado.
	 * <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos SolicitudMantenimiento del sistema
	 */
	public ArrayList<SolicitudMantenimiento> obtenerTodasLasSolicitudes() {
		return solicitudMantenimientoDAO.getListaSolicitudes();
	}

	/**
	 * Retorna la lista de solicitudes de mantenimiento que no estan en estado
	 * COMPLETADA ni RECHAZADA. <b>pre</b> El solicitudMantenimientoDAO debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las solicitudes abiertas (REGISTRADA, ASIGNADA,
	 *         EN_PROGRESO)
	 */
	public ArrayList<SolicitudMantenimiento> obtenerSolicitudesAbiertas() {
		ArrayList<SolicitudMantenimiento> resultado = new ArrayList<>();
		for (SolicitudMantenimiento s : solicitudMantenimientoDAO.getListaSolicitudes()) {
			if (!s.getEstado().equals("COMPLETADA") && !s.getEstado().equals("RECHAZADA")) {
				resultado.add(s);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de solicitudes de mantenimiento filtradas por nivel de
	 * prioridad. <b>pre</b> El solicitudMantenimientoDAO debe estar inicializado.
	 * <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param prioridad Nivel de prioridad a filtrar (ALTA, MEDIA, BAJA). prioridad
	 *                  != null, prioridad != ""
	 * @return ArrayList con las solicitudes de la prioridad indicada
	 */
	public ArrayList<SolicitudMantenimiento> obtenerSolicitudesPorPrioridad(String prioridad) {
		return solicitudMantenimientoDAO.buscarPorPrioridad(prioridad);
	}

	/**
	 * Retorna la lista de solicitudes de mantenimiento cuya fecha limite ya paso y
	 * aun no fueron completadas. <b>pre</b> El solicitudMantenimientoDAO debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las solicitudes vencidas
	 */
	public ArrayList<SolicitudMantenimiento> obtenerSolicitudesVencidas() {
		return solicitudMantenimientoDAO.buscarVencidas();
	}

	/**
	 * Retorna una representacion en texto de todas las solicitudes de mantenimiento
	 * del sistema. <b>pre</b> El solicitudMantenimientoDAO debe estar inicializado.
	 * <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las solicitudes
	 */
	public String mostrarSolicitudesMantenimiento() {
		return solicitudMantenimientoDAO.mostrar();
	}

	// Metodos ENTRADA VISITANTE

	/**
	 * Registra la entrada de un visitante al conjunto, lo asocia al apartamento
	 * destino, crea el registro de visita con hora de entrada y notifica al
	 * responsable del apartamento. Si el visitante ya existe por cedula, no lo
	 * duplica en el sistema. Si la autorizacion del visitante ya vencio, no procesa
	 * la entrada. <b>pre</b> El visitante debe tener cedula valida. El
	 * idApartamentoDestino debe corresponder a un apartamento existente. La
	 * autorizacion del visitante no debe estar vencida. <br>
	 * <b>post</b> Si la autorizacion es vigente, el registro de visita queda creado
	 * con hora de entrada actual y el responsable del apartamento recibe una
	 * notificacion por correo. <br>
	 * 
	 * @param visitante            Objeto Visitante que ingresa al conjunto.
	 *                             visitante != null
	 * @param idApartamentoDestino ID del apartamento al que se dirige el visitante.
	 *                             idApartamentoDestino != null,
	 *                             idApartamentoDestino != ""
	 */
	public void registrarEntradaVisitante(Visitante visitante, String idApartamentoDestino) {
		if (visitante.getFechaVencimiento() != null && visitante.getFechaVencimiento().isBefore(LocalDateTime.now())) {
			return;
		}
		Apartamento apto = buscarApartamentoPorId(idApartamentoDestino);
		visitante.setApartamentoDestino(apto);
		boolean existe = false;
		for (Visitante v : visitanteDAO.getListaVisitantes()) {
			if (v.getCedula().equals(visitante.getCedula())) {
				existe = true;
				break;
			}
		}
		if (!existe) {
			visitanteDAO.crear(DataMapper.convertirVisitanteAVisitanteDTO(visitante));
		}
		RegistroVisita registro = new RegistroVisita();
		registro.setId("VIS-" + contadorIds++);
		registro.setVisitante(visitante);
		registro.setHoraEntrada(LocalDateTime.now());
		registroVisitaDAO.crear(DataMapper.convertirRegistroVisitaARegistroVisitaDTO(registro));
		if (apto != null) {
			String correoResponsable = obtenerCorreoResponsableApartamento(apto);
			if (correoResponsable != null && !correoResponsable.isEmpty()) {
				String asunto = "Visitante autorizado ingresando a su apartamento";
				String cuerpo = "Le informamos que el visitante " + visitante.getNombre() + " (CC: "
						+ visitante.getCedula() + ") ha ingresado al conjunto con destino al apartamento "
						+ apto.getNumero() + ".\n\nFecha y hora: " + LocalDateTime.now()
						+ "\n\nAtentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoResponsable, asunto, cuerpo, "VISITANTE");
			}
		}
	}

	/**
	 * Registra la salida de un visitante buscando el ultimo registro de visita
	 * activo (sin hora de salida) con la cedula indicada y actualizando su hora de
	 * salida. <b>pre</b> Debe existir al menos un registro de visita activo con la
	 * cedula indicada. <br>
	 * <b>post</b> Si se encuentra el registro, la hora de salida queda registrada
	 * con el momento actual. Retorna false si no se encuentra un registro activo
	 * para esa cedula. <br>
	 * 
	 * @param cedulaVisitante Cedula del visitante que sale del conjunto.
	 *                        cedulaVisitante != null, cedulaVisitante != ""
	 * @return true si la salida fue registrada, false si no se encontro un registro
	 *         activo
	 */
	public boolean registrarSalidaVisitante(String cedulaVisitante) {
		ArrayList<RegistroVisita> lista = registroVisitaDAO.getListaRegistros();
		for (int i = lista.size() - 1; i >= 0; i--) {
			RegistroVisita r = lista.get(i);
			if (r.getVisitante() != null && r.getVisitante().getCedula().equals(cedulaVisitante)
					&& r.getHoraSalida() == null) {
				r.setHoraSalida(LocalDateTime.now());
				registroVisitaDAO.actualizar(i, DataMapper.convertirRegistroVisitaARegistroVisitaDTO(r));
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna la lista completa de visitantes registrados en el sistema. <b>pre</b>
	 * El visitanteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Visitante del sistema
	 */
	public ArrayList<Visitante> obtenerTodosLosVisitantes() {
		return visitanteDAO.getListaVisitantes();
	}

	/**
	 * Retorna la lista de visitantes con estado activo en el sistema. <b>pre</b> El
	 * visitanteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los visitantes cuyo atributo activo es true
	 */
	public ArrayList<Visitante> obtenerVisitantesActivos() {
		ArrayList<Visitante> resultado = new ArrayList<>();
		for (Visitante v : visitanteDAO.getListaVisitantes()) {
			if (v.isActivo()) {
				resultado.add(v);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista completa de registros de visita (entradas y salidas) del
	 * sistema. <b>pre</b> El registroVisitaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos RegistroVisita del sistema
	 */
	public ArrayList<RegistroVisita> obtenerTodosLosRegistrosVisita() {
		return registroVisitaDAO.getListaRegistros();
	}

	/**
	 * Retorna la lista de visitas activas, es decir, registros con hora de entrada
	 * pero sin hora de salida. <b>pre</b> El registroVisitaDAO debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los registros de visita cuya horaSalida es null
	 */
	public ArrayList<RegistroVisita> obtenerVisitasActivas() {
		ArrayList<RegistroVisita> resultado = new ArrayList<>();
		for (RegistroVisita r : registroVisitaDAO.getListaRegistros()) {
			if (r.getHoraSalida() == null) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todos los registros de visita del
	 * sistema. <b>pre</b> El registroVisitaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los registros de visita
	 */
	public String mostrarRegistrosVisita() {
		return registroVisitaDAO.mostrar();
	}

	// Metodos LLEGADA PAQUETE

	/**
	 * Registra la llegada de un paquete a porteria, establece la fecha de recepcion
	 * actual, lo marca en estado RECIBIDO y envia una notificacion al responsable
	 * del apartamento destino. <b>pre</b> El objeto paquete debe estar inicializado
	 * con descripcion y apartamentoDestino. <br>
	 * <b>post</b> El paquete queda registrado con estado RECIBIDO y fecha de
	 * recepcion actual. Se envia notificacion al responsable del apartamento si
	 * tiene correo valido. <br>
	 * 
	 * @param paquete Objeto Paquete recibido en porteria. paquete != null
	 */
	public void registrarLlegadaPaquete(Paquete paquete) {
		paquete.setFechaRecepcion(LocalDateTime.now());
		paquete.setEstado("RECIBIDO");
		paqueteDAO.crear(DataMapper.convertirPaqueteAPaqueteDTO(paquete));

		if (paquete.getApartamentoDestino() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(paquete.getApartamentoDestino());
			String asunto = "Paquete recibido en porteria";
			String cuerpo = "Tiene un paquete en porteria.\n\n" + "Descripcion: " + paquete.getDescripcion() + "\n"
					+ "Fecha de recepcion: " + paquete.getFechaRecepcion() + "\n\n"
					+ "Por favor reclamelo en porteria.\n\n" + "Atentamente,\nGreenBuilding Group";

			Notificacion notif = new Notificacion();
			notif.setId("NOT-" + contadorIds++);
			notif.setAsunto(asunto);
			notif.setCuerpo(cuerpo);
			notif.setFechaEnvio(LocalDateTime.now());
			notif.setTipo("PAQUETE");
			notif.setDestinatario(paquete.getApartamentoDestino().getNumero());
			notif.setEnviada(false);
			notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

			if (correoDestino != null && !correoDestino.isEmpty()) {
				boolean enviado = EmailSender.enviarCorreo(correoDestino, asunto, cuerpo);
				if (enviado) {
					notif.setEnviada(true);
					int idx = notificacionDAO.getListaNotificaciones().size() - 1;
					notificacionDAO.actualizar(idx, DataMapper.convertirNotificacionANotificacionDTO(notif));
				}
			}
		}
	}

	/**
	 * Marca un paquete como entregado registrando la persona que lo recibio y la
	 * fecha de entrega. <b>pre</b> El index debe ser un indice valido dentro de la
	 * lista de paquetes. <br>
	 * <b>post</b> Si el indice es valido, el paquete queda en estado ENTREGADO con
	 * fecha y persona que recibio registrados. Retorna false si el indice no es
	 * valido. <br>
	 * 
	 * @param index          Indice del paquete a marcar como entregado. index >= 0
	 * @param personaRecibio Nombre de la persona que recibio el paquete.
	 *                       personaRecibio != null, personaRecibio != ""
	 * @return true si la entrega fue registrada, false si el indice no es valido
	 */
	public boolean marcarPaqueteEntregado(int index, String personaRecibio) {
		ArrayList<Paquete> lista = paqueteDAO.getListaPaquetes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Paquete p = lista.get(index);
		p.setEstado("ENTREGADO");
		p.setFechaEntrega(LocalDateTime.now());
		p.setPersonaRecibio(personaRecibio);
		paqueteDAO.actualizar(index, DataMapper.convertirPaqueteAPaqueteDTO(p));
		return true;
	}

	/**
	 * Marca un paquete como devuelto al remitente cambiando su estado a DEVUELTO.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de paquetes.
	 * <br>
	 * <b>post</b> Si el indice es valido, el paquete queda en estado DEVUELTO y se
	 * persiste. Retorna false si el indice no es valido. <br>
	 * 
	 * @param index Indice del paquete a devolver. index >= 0
	 * @return true si la devolucion fue registrada, false si el indice no es valido
	 */
	public boolean devolverPaquete(int index) {
		ArrayList<Paquete> lista = paqueteDAO.getListaPaquetes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Paquete p = lista.get(index);
		p.setEstado("DEVUELTO");
		paqueteDAO.actualizar(index, DataMapper.convertirPaqueteAPaqueteDTO(p));
		return true;
	}

	/**
	 * Elimina un paquete del sistema por su indice en la lista. <b>pre</b> El index
	 * debe ser un indice valido dentro de la lista de paquetes. <br>
	 * <b>post</b> Si el indice es valido, el paquete queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del paquete a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarPaquete(int index) {
		return paqueteDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de paquetes registrados en el sistema. <b>pre</b>
	 * El paqueteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Paquete del sistema
	 */
	public ArrayList<Paquete> obtenerTodosLosPaquetes() {
		return paqueteDAO.getListaPaquetes();
	}

	/**
	 * Retorna la lista de paquetes que aun no han sido entregados ni devueltos.
	 * <b>pre</b> El paqueteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los paquetes cuyo estado no es ENTREGADO ni DEVUELTO
	 */
	public ArrayList<Paquete> obtenerPaquetesPendientes() {
		ArrayList<Paquete> resultado = new ArrayList<>();
		for (Paquete p : paqueteDAO.getListaPaquetes()) {
			if (!p.getEstado().equals("ENTREGADO") && !p.getEstado().equals("DEVUELTO")) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de paquetes destinados a un apartamento especifico.
	 * <b>pre</b> El paqueteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento destinatario. idApartamento != null,
	 *                      idApartamento != ""
	 * @return ArrayList con los paquetes del apartamento indicado
	 */
	public ArrayList<Paquete> obtenerPaquetesPorApartamento(String idApartamento) {
		return paqueteDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Retorna una representacion en texto de todos los paquetes del sistema.
	 * <b>pre</b> El paqueteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los paquetes
	 */
	public String mostrarPaquetes() {
		return paqueteDAO.mostrar();
	}

	// Metodos INCIDENTES

	/**
	 * Reporta un incidente de convivencia en el sistema, lo registra con estado
	 * REPORTADO y fecha de ocurrencia actual. Si el incidente genera multa, crea la
	 * obligacion correspondiente y notifica al responsable del apartamento. Si no
	 * genera multa, notifica de todas formas. <b>pre</b> El objeto incidente debe
	 * estar inicializado con tipo, descripcion y gravedad validos. <br>
	 * <b>post</b> El incidente queda registrado. Si genera multa, la obligacion
	 * queda creada y el responsable notificado del monto. Si no genera multa, el
	 * responsable recibe notificacion informativa. <br>
	 * 
	 * @param incidente Objeto Incidente a reportar. incidente != null
	 */
	public void reportarIncidente(Incidente incidente) {
		incidente.setEstado("REPORTADO");
		incidente.setFechaOcurrencia(LocalDateTime.now());
		incidenteDAO.crear(DataMapper.convertirIncidenteAIncidenteDTO(incidente));

		if (incidente.isGeneraMulta() && incidente.getApartamentoInvolucrado() != null) {
			Obligacion multa = new Obligacion();
			multa.setId("MUL-" + contadorIds++);
			multa.setApartamento(incidente.getApartamentoInvolucrado());
			multa.setConcepto("Multa por incidente: " + incidente.getTipo() + " - " + incidente.getDescripcion());
			multa.setMonto(ConfigurationManager.getDouble("multa.monto.defecto"));
			multa.setFechaGeneracion(LocalDate.now());
			multa.setFechaLimite(LocalDate.now().plusDays(ConfigurationManager.getInt("multa.dias.limite")));
			multa.setEstado("PENDIENTE");
			multa.setTipo("MULTA");
			multa.setGeneradaPor("SISTEMA");
			obligacionDAO.crear(DataMapper.convertirObligacionAObligacionDTO(multa));
			String correoDestino = obtenerCorreoResponsableApartamento(incidente.getApartamentoInvolucrado());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String asunto = "Multa generada por incidente de convivencia";
				String cuerpo = "Se ha generado una multa en su apartamento.\n\n" + "Tipo de incidente: "
						+ incidente.getTipo() + "\n" + "Descripcion: " + incidente.getDescripcion() + "\n"
						+ "Monto de la multa: $" + multa.getMonto() + "\n" + "Fecha limite de pago: "
						+ multa.getFechaLimite() + "\n\n" + "Por favor regularice su situacion a la brevedad.\n\n"
						+ "Atentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "MULTA");
			}
		} else if (incidente.getApartamentoInvolucrado() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(incidente.getApartamentoInvolucrado());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String asunto = "Incidente reportado en su apartamento";
				String cuerpo = "Se ha registrado un incidente relacionado con su apartamento.\n\n" + "Tipo: "
						+ incidente.getTipo() + "\n" + "Descripcion: " + incidente.getDescripcion() + "\n"
						+ "Gravedad: " + incidente.getGravedad() + "\n\n" + "La administracion revisara el caso.\n\n"
						+ "Atentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "INCIDENTE");
			}
		}
	}

	/**
	 * Cambia el estado de un incidente a EN_REVISION para su analisis por parte de
	 * la administracion. <b>pre</b> El index debe ser un indice valido dentro de la
	 * lista de incidentes. <br>
	 * <b>post</b> Si el indice es valido, el estado del incidente queda en
	 * EN_REVISION y se persiste. Retorna false si el indice no es valido. <br>
	 * 
	 * @param index Indice del incidente a poner en revision. index >= 0
	 * @return true si el cambio fue exitoso, false si el indice no es valido
	 */
	public boolean ponerIncidenteEnRevision(int index) {
		ArrayList<Incidente> lista = incidenteDAO.getListaIncidentes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Incidente i = lista.get(index);
		i.setEstado("EN_REVISION");
		incidenteDAO.actualizar(index, DataMapper.convertirIncidenteAIncidenteDTO(i));
		return true;
	}

	/**
	 * Marca un incidente como RESUELTO registrando la descripcion de la resolucion
	 * aplicada. <b>pre</b> El index debe ser un indice valido dentro de la lista de
	 * incidentes. <br>
	 * <b>post</b> Si el indice es valido, el estado del incidente queda en RESUELTO
	 * y la resolucion queda registrada. Retorna false si el indice no es valido.
	 * <br>
	 * 
	 * @param index      Indice del incidente a resolver. index >= 0
	 * @param resolucion Descripcion de la medida tomada para resolver el incidente.
	 *                   resolucion != null
	 * @return true si el cambio fue exitoso, false si el indice no es valido
	 */
	public boolean resolverIncidente(int index, String resolucion) {
		ArrayList<Incidente> lista = incidenteDAO.getListaIncidentes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Incidente i = lista.get(index);
		i.setEstado("RESUELTO");
		i.setResolucion(resolucion);
		incidenteDAO.actualizar(index, DataMapper.convertirIncidenteAIncidenteDTO(i));
		return true;
	}

	/**
	 * Archiva un incidente cambiando su estado a ARCHIVADO para mantener el
	 * historial. <b>pre</b> El index debe ser un indice valido dentro de la lista
	 * de incidentes. <br>
	 * <b>post</b> Si el indice es valido, el estado del incidente queda en
	 * ARCHIVADO y se persiste. Retorna false si el indice no es valido. <br>
	 * 
	 * @param index Indice del incidente a archivar. index >= 0
	 * @return true si el archivado fue exitoso, false si el indice no es valido
	 */
	public boolean archivarIncidente(int index) {
		ArrayList<Incidente> lista = incidenteDAO.getListaIncidentes();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Incidente i = lista.get(index);
		i.setEstado("ARCHIVADO");
		incidenteDAO.actualizar(index, DataMapper.convertirIncidenteAIncidenteDTO(i));
		return true;
	}

	/**
	 * Elimina un incidente del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de incidentes. <br>
	 * <b>post</b> Si el indice es valido, el incidente queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del incidente a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarIncidente(int index) {
		return incidenteDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de incidentes registrados en el sistema. <b>pre</b>
	 * El incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Incidente del sistema
	 */
	public ArrayList<Incidente> obtenerTodosLosIncidentes() {
		return incidenteDAO.getListaIncidentes();
	}

	/**
	 * Retorna la lista de incidentes en estado REPORTADO o EN_REVISION que
	 * requieren atencion. <b>pre</b> El incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los incidentes pendientes de atencion
	 */
	public ArrayList<Incidente> obtenerIncidentesPendientes() {
		ArrayList<Incidente> resultado = new ArrayList<>();
		for (Incidente i : incidenteDAO.getListaIncidentes()) {
			if (i.getEstado().equals("REPORTADO") || i.getEstado().equals("EN_REVISION")) {
				resultado.add(i);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de incidentes relacionados con un apartamento especifico.
	 * <b>pre</b> El incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento involucrado. idApartamento != null,
	 *                      idApartamento != ""
	 * @return ArrayList con los incidentes del apartamento indicado
	 */
	public ArrayList<Incidente> obtenerIncidentesPorApartamento(String idApartamento) {
		return incidenteDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Retorna la lista de incidentes filtrados por tipo de incidente. <b>pre</b> El
	 * incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param tipo Tipo de incidente a filtrar. tipo != null, tipo != ""
	 * @return ArrayList con los incidentes del tipo indicado
	 */
	public ArrayList<Incidente> obtenerIncidentesPorTipo(String tipo) {
		return incidenteDAO.buscarPorTipo(tipo);
	}

	/**
	 * Retorna la lista de incidentes filtrados por nivel de gravedad. <b>pre</b> El
	 * incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param gravedad Nivel de gravedad a filtrar (BAJA, MEDIA, ALTA). gravedad !=
	 *                 null, gravedad != ""
	 * @return ArrayList con los incidentes del nivel de gravedad indicado
	 */
	public ArrayList<Incidente> obtenerIncidentesPorGravedad(String gravedad) {
		return incidenteDAO.buscarPorGravedad(gravedad);
	}

	/**
	 * Retorna una representacion en texto de todos los incidentes del sistema.
	 * <b>pre</b> El incidenteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los incidentes
	 */
	public String mostrarIncidentes() {
		return incidenteDAO.mostrar();
	}

	// Metodos OBLIGACIONES Y PAGOS

	/**
	 * Registra una obligacion financiera en el sistema y notifica al responsable
	 * del apartamento. <b>pre</b> El objeto obligacion debe estar inicializado con
	 * monto, concepto, tipo y fechaLimite. El apartamento asociado debe existir en
	 * el sistema. <br>
	 * <b>post</b> La obligacion queda registrada y persistida. El responsable del
	 * apartamento recibe una notificacion por correo si tiene correo valido. <br>
	 * 
	 * @param obligacion Objeto Obligacion a registrar. obligacion != null
	 */
	public void registrarObligacion(Obligacion obligacion) {
		obligacionDAO.crear(DataMapper.convertirObligacionAObligacionDTO(obligacion));

		if (obligacion.getApartamento() != null) {
			String correoDestino = obtenerCorreoResponsableApartamento(obligacion.getApartamento());
			if (correoDestino != null && !correoDestino.isEmpty()) {
				String asunto = "Nueva obligacion pendiente - " + obligacion.getTipo();
				String cuerpo = "Se ha registrado una nueva obligacion en su apartamento.\n\n" + "Concepto: "
						+ obligacion.getConcepto() + "\n" + "Monto: $" + obligacion.getMonto() + "\n" + "Fecha limite: "
						+ obligacion.getFechaLimite() + "\n\n"
						+ "Por favor realice el pago antes de la fecha indicada.\n\n"
						+ "Atentamente,\nGreenBuilding Group";
				enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "OBLIGACION");
			}
		}
	}

	/**
	 * Genera cuotas mensuales de administracion para todos los apartamentos
	 * ocupados (no DESOCUPADOS), con el monto indicado y la fecha limite calculada
	 * desde config.properties. Notifica al responsable de cada apartamento.
	 * <b>pre</b> El apartamentoDAO y el obligacionDAO deben estar inicializados. El
	 * monto debe ser mayor a cero. <br>
	 * <b>post</b> Se crea una obligacion de tipo ADMINISTRACION para cada
	 * apartamento no desocupado y se notifica al responsable de cada uno. <br>
	 * 
	 * @param monto Monto de la cuota de administracion mensual. monto > 0
	 */
	public void generarCuotasMensuales(double monto) {
		for (Apartamento a : apartamentoDAO.getListaApartamentos()) {
			if (!a.getEstado().equals("DESOCUPADO")) {
				Obligacion cuota = new Obligacion();
				cuota.setId("OBL-" + contadorIds++);
				cuota.setApartamento(a);
				cuota.setTipo("ADMINISTRACION");
				cuota.setMonto(monto);
				cuota.setConcepto("Cuota de administracion mensual");
				cuota.setFechaGeneracion(LocalDate.now());
				cuota.setFechaLimite(LocalDate.now().plusDays(ConfigurationManager.getInt("cuota.admin.dias.limite")));
				cuota.setEstado("PENDIENTE");
				cuota.setGeneradaPor("SISTEMA");
				obligacionDAO.crear(DataMapper.convertirObligacionAObligacionDTO(cuota));

				String correoDestino = obtenerCorreoResponsableApartamento(a);
				if (correoDestino != null && !correoDestino.isEmpty()) {
					String asunto = "Cuota de administracion mensual generada";
					String cuerpo = "Se ha generado la cuota de administracion del mes.\n\n" + "Apartamento: "
							+ a.getNumero() + "\n" + "Monto: $" + monto + "\n" + "Fecha limite de pago: "
							+ cuota.getFechaLimite() + "\n\n"
							+ "Por favor realice el pago antes de la fecha indicada.\n\n"
							+ "Atentamente,\nGreenBuilding Group";
					enviarNotificacionCorreo(correoDestino, asunto, cuerpo, "OBLIGACION");
				}
			}
		}
	}

	/**
	 * Registra un pago para un apartamento y aplica el monto pagado a las
	 * obligaciones pendientes en orden cronologico hasta agotar el monto
	 * disponible. <b>pre</b> El idApartamento debe corresponder a un apartamento
	 * existente. El pago debe tener monto mayor a cero y un medio de pago valido.
	 * <br>
	 * <b>post</b> El pago queda registrado. Las obligaciones pendientes del
	 * apartamento quedan marcadas como PAGADA en orden hasta consumir el monto del
	 * pago. Retorna "OK" si fue exitoso. <br>
	 * 
	 * @param pago          Objeto Pago a registrar. pago != null
	 * @param idApartamento ID del apartamento que realiza el pago. idApartamento !=
	 *                      null, idApartamento != ""
	 * @return "OK" si el pago fue registrado, o un mensaje de error si el
	 *         apartamento no existe
	 */
	public String registrarPago(Pago pago, String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return "Apartamento no encontrado.";
		}
		pago.setApartamento(apto);
		pago.setFechaPago(LocalDate.now());
		double montoRestante = pago.getMonto();
		for (int i = 0; i < obligacionDAO.getListaObligaciones().size(); i++) {
			Obligacion o = obligacionDAO.getListaObligaciones().get(i);
			if (o.getApartamento() != null && o.getApartamento().getId().equals(idApartamento)
					&& o.getEstado().equals("PENDIENTE") && montoRestante > 0) {
				if (montoRestante >= o.getMonto()) {
					montoRestante -= o.getMonto();
					o.setEstado("PAGADA");
					obligacionDAO.actualizar(i, DataMapper.convertirObligacionAObligacionDTO(o));
				}
			}
		}
		pagoDAO.crear(DataMapper.convertirPagoAPagoDTO(pago));
		return "OK";
	}

	/**
	 * Anula una obligacion existente cambiando su estado a ANULADA. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de obligaciones. <br>
	 * <b>post</b> Si el indice es valido, la obligacion queda en estado ANULADA y
	 * se persiste. Retorna false si el indice no es valido. <br>
	 * 
	 * @param index Indice de la obligacion a anular. index >= 0
	 * @return true si la anulacion fue exitosa, false si el indice no es valido
	 */
	public boolean anularObligacion(int index) {
		ArrayList<Obligacion> lista = obligacionDAO.getListaObligaciones();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Obligacion o = lista.get(index);
		o.setEstado("ANULADA");
		obligacionDAO.actualizar(index, DataMapper.convertirObligacionAObligacionDTO(o));
		return true;
	}

	/**
	 * Elimina un pago del sistema por su indice en la lista. <b>pre</b> El index
	 * debe ser un indice valido dentro de la lista de pagos. <br>
	 * <b>post</b> Si el indice es valido, el pago queda eliminado y la persistencia
	 * actualizada. <br>
	 * 
	 * @param index Indice del pago a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarPago(int index) {
		return pagoDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de obligaciones registradas en el sistema.
	 * <b>pre</b> El obligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Obligacion del sistema
	 */
	public ArrayList<Obligacion> obtenerTodasLasObligaciones() {
		return obligacionDAO.getListaObligaciones();
	}

	/**
	 * Retorna la lista de obligaciones pendientes de un apartamento especifico.
	 * <b>pre</b> El obligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con las obligaciones pendientes del apartamento indicado
	 */
	public ArrayList<Obligacion> obtenerObligacionesPendientes(String idApartamento) {
		return obligacionDAO.buscarPendientesPorApartamento(idApartamento);
	}

	/**
	 * Retorna la lista de obligaciones cuya fecha limite ya vencio y siguen
	 * pendientes. <b>pre</b> El obligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las obligaciones vencidas del sistema
	 */
	public ArrayList<Obligacion> obtenerObligacionesVencidas() {
		return obligacionDAO.buscarVencidas();
	}

	/**
	 * Retorna la lista de obligaciones filtradas por estado. <b>pre</b> El
	 * obligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param estado Estado de obligacion a filtrar (PENDIENTE, PAGADA, ANULADA,
	 *               VENCIDA). estado != null, estado != ""
	 * @return ArrayList con las obligaciones que tienen el estado indicado
	 */
	public ArrayList<Obligacion> obtenerObligacionesPorEstado(String estado) {
		return obligacionDAO.buscarPorEstado(estado);
	}

	/**
	 * Retorna la lista completa de pagos registrados en el sistema. <b>pre</b> El
	 * pagoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Pago del sistema
	 */
	public ArrayList<Pago> obtenerTodosLosPagos() {
		return pagoDAO.getListaPagos();
	}

	/**
	 * Retorna la lista de pagos realizados por un apartamento especifico.
	 * <b>pre</b> El pagoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idApartamento ID del apartamento. idApartamento != null, idApartamento
	 *                      != ""
	 * @return ArrayList con los pagos del apartamento indicado
	 */
	public ArrayList<Pago> obtenerPagosPorApartamento(String idApartamento) {
		return pagoDAO.buscarPorApartamento(idApartamento);
	}

	/**
	 * Genera y retorna el estado de cuenta de un apartamento mostrando obligaciones
	 * pendientes y pagos realizados, y exporta el resultado en formato PDF.
	 * <b>pre</b> El idApartamento debe corresponder a un apartamento existente en
	 * el sistema. <br>
	 * <b>post</b> No se modifica el estado financiero del sistema. Se genera un
	 * archivo PDF del estado de cuenta en la carpeta files/. <br>
	 * 
	 * @param idApartamento ID del apartamento del que se genera el estado de
	 *                      cuenta. idApartamento != null, idApartamento != ""
	 * @return String con el estado de cuenta detallado, o mensaje de error si el
	 *         apartamento no existe
	 */
	public String obtenerEstadoCuenta(String idApartamento) {
		Apartamento apto = buscarApartamentoPorId(idApartamento);
		if (apto == null) {
			return "Apartamento no encontrado.";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("   ESTADO DE CUENTA - Apartamento ").append(apto.getNumero()).append("    \n");
		double totalPendiente = 0;
		sb.append("\n OBLIGACIONES PENDIENTES:\n");
		for (Obligacion o : obligacionDAO.getListaObligaciones()) {
			if (o.getApartamento() != null && o.getApartamento().getId().equals(idApartamento)
					&& o.getEstado().equals("PENDIENTE")) {
				sb.append("  ").append(o.getConcepto()).append(" - $").append(o.getMonto()).append(" - Vence: ")
						.append(o.getFechaLimite()).append("\n");
				totalPendiente += o.getMonto();
			}
		}
		sb.append("TOTAL PENDIENTE: $").append(totalPendiente).append("\n");
		sb.append("\n PAGOS REALIZADOS:\n");
		for (Pago p : pagoDAO.getListaPagos()) {
			if (p.getApartamento() != null && p.getApartamento().getId().equals(idApartamento)) {
				sb.append("  ").append(p.getFechaPago()).append(" - $").append(p.getMonto()).append(" - ")
						.append(p.getMedioPago()).append("\n");
			}
		}
		String contenido = sb.toString();
		FileHandler.exportarPDF("estadocuenta_" + idApartamento + ".pdf", "Estado de Cuenta - Apto " + apto.getNumero(),
				contenido);
		return contenido;
	}

	/**
	 * Retorna la lista de apartamentos que tienen obligaciones pendientes con fecha
	 * limite vencida. <b>pre</b> El obligacionDAO y el apartamentoDAO deben estar
	 * inicializados. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con los apartamentos morosos (con obligaciones vencidas sin
	 *         pagar)
	 */
	public ArrayList<Apartamento> obtenerApartamentosMorosos() {
		ArrayList<Apartamento> resultado = new ArrayList<>();
		for (Obligacion o : obligacionDAO.getListaObligaciones()) {
			if (o.getEstado().equals("PENDIENTE") && o.getFechaLimite() != null
					&& o.getFechaLimite().isBefore(LocalDate.now())) {
				if (o.getApartamento() != null && !resultado.contains(o.getApartamento())) {
					resultado.add(o.getApartamento());
				}
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todas las obligaciones del sistema.
	 * <b>pre</b> El obligacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las obligaciones
	 */
	public String mostrarObligaciones() {
		return obligacionDAO.mostrar();
	}

	/**
	 * Retorna una representacion en texto de todos los pagos del sistema.
	 * <b>pre</b> El pagoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los pagos
	 */
	public String mostrarPagos() {
		return pagoDAO.mostrar();
	}

	// Metodos NOTIFICACIONES

	/**
	 * Envia una notificacion a una persona que implemente la interfaz Notificable,
	 * usando su correo y nombre para personalizar el mensaje, y persiste la
	 * notificacion en el sistema. <b>pre</b> El objeto persona no debe ser null y
	 * debe tener un destinatario (correo) valido. <br>
	 * <b>post</b> La notificacion queda persistida en el sistema. Si el correo fue
	 * enviado exitosamente, la notificacion queda marcada como enviada. <br>
	 * 
	 * @param persona Objeto que implementa Notificable con correo y nombre. persona
	 *                != null
	 * @param asunto  Asunto del correo de notificacion. asunto != null
	 * @param cuerpo  Contenido del mensaje a enviar. cuerpo != null
	 */
	public void notificarPersona(Notificable persona, String asunto, String cuerpo) {
		if (persona == null || persona.getDestinatario() == null) {
			return;
		}
		Notificacion notif = new Notificacion();
		notif.setId("NOT-" + contadorIds++);
		notif.setDestinatario(persona.getDestinatario());
		notif.setAsunto(asunto);
		notif.setCuerpo("Estimado/a " + persona.getNombreNotificacion() + ",\n\n" + cuerpo);
		notif.setTipo("SISTEMA");
		notif.setFechaEnvio(LocalDateTime.now());
		notif.setEnviada(false);
		NotificacionDTO dto = DataMapper.convertirNotificacionANotificacionDTO(notif);
		notificacionDAO.crear(dto);
		boolean enviado = EmailSender.enviarCorreo(persona.getDestinatario(), asunto, notif.getCuerpo());
		if (enviado) {
			notif.setEnviada(true);
			int idx = notificacionDAO.getListaNotificaciones().size() - 1;
			notificacionDAO.actualizar(idx, DataMapper.convertirNotificacionANotificacionDTO(notif));
		}
	}

	/**
	 * Envia la misma notificacion a una lista de personas que implementan la
	 * interfaz Notificable. <b>pre</b> La lista no debe ser null. Cada persona debe
	 * tener destinatario valido. <br>
	 * <b>post</b> Se invoca notificarPersona para cada elemento de la lista. <br>
	 * 
	 * @param lista  Lista de objetos Notificable a notificar. lista != null
	 * @param asunto Asunto del correo de notificacion. asunto != null
	 * @param cuerpo Contenido del mensaje a enviar. cuerpo != null
	 */
	public void notificarLista(ArrayList<Notificable> lista, String asunto, String cuerpo) {
		for (Notificable persona : lista) {
			notificarPersona(persona, asunto, cuerpo);
		}
	}

	/**
	 * Crea y persiste una notificacion en el sistema e intenta enviarla por correo
	 * electronico. A diferencia de enviarNotificacionCorreo, este metodo no
	 * actualiza el estado de envio. <b>pre</b> El destinatario debe ser una
	 * direccion de correo valida. <br>
	 * <b>post</b> La notificacion queda persistida en el sistema y se intenta el
	 * envio por correo. <br>
	 * 
	 * @param destinatario Direccion de correo del receptor. destinatario != null,
	 *                     destinatario != ""
	 * @param asunto       Asunto del correo. asunto != null
	 * @param cuerpo       Contenido del mensaje. cuerpo != null
	 * @param tipo         Tipo de notificacion (SISTEMA, RESERVA, PAQUETE,
	 *                     INCIDENTE, etc.). tipo != null, tipo != ""
	 */
	public void enviarNotificacion(String destinatario, String asunto, String cuerpo, String tipo) {
		Notificacion notif = new Notificacion();
		notif.setId("NOT-" + contadorIds++);
		notif.setDestinatario(destinatario);
		notif.setAsunto(asunto);
		notif.setCuerpo(cuerpo);
		notif.setTipo(tipo);
		notif.setFechaEnvio(LocalDateTime.now());
		notif.setEnviada(false);
		notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));
		EmailSender.enviarCorreo(destinatario, asunto, cuerpo);
	}

	/**
	 * Crea y persiste una notificacion en el sistema, la envia por correo
	 * electronico y, si el envio fue exitoso, actualiza su estado a enviada en la
	 * persistencia. <b>pre</b> El destinatario debe ser una direccion de correo
	 * valida. <br>
	 * <b>post</b> La notificacion queda persistida. Si el correo fue enviado, queda
	 * marcada como enviada. <br>
	 * 
	 * @param destinatario Direccion de correo del receptor. destinatario != null,
	 *                     destinatario != ""
	 * @param asunto       Asunto del correo. asunto != null
	 * @param cuerpo       Contenido del mensaje. cuerpo != null
	 * @param tipo         Tipo de notificacion (SISTEMA, RESERVA, PAQUETE,
	 *                     INCIDENTE, etc.). tipo != null, tipo != ""
	 */
	public void enviarNotificacionCorreo(String destinatario, String asunto, String cuerpo, String tipo) {
		Notificacion notif = new Notificacion();
		notif.setId("NOT-" + contadorIds++);
		notif.setDestinatario(destinatario);
		notif.setAsunto(asunto);
		notif.setCuerpo(cuerpo);
		notif.setTipo(tipo);
		notif.setFechaEnvio(LocalDateTime.now());
		notif.setEnviada(false);
		notificacionDAO.crear(DataMapper.convertirNotificacionANotificacionDTO(notif));

		boolean enviado = EmailSender.enviarCorreo(destinatario, asunto, cuerpo);
		if (enviado) {
			notif.setEnviada(true);
			int idx = notificacionDAO.getListaNotificaciones().size() - 1;
			notificacionDAO.actualizar(idx, DataMapper.convertirNotificacionANotificacionDTO(notif));
		}
	}

	/**
	 * Marca una notificacion como enviada por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de notificaciones. <br>
	 * <b>post</b> Si el indice es valido, la notificacion queda marcada como
	 * enviada y se persiste. Retorna false si el indice no es valido. <br>
	 * 
	 * @param index Indice de la notificacion a marcar como enviada. index >= 0
	 * @return true si la operacion fue exitosa, false si el indice no es valido
	 */
	public boolean marcarNotificacionEnviada(int index) {
		ArrayList<Notificacion> lista = notificacionDAO.getListaNotificaciones();
		if (index < 0 || index >= lista.size()) {
			return false;
		}
		Notificacion n = lista.get(index);
		n.setEnviada(true);
		notificacionDAO.actualizar(index, DataMapper.convertirNotificacionANotificacionDTO(n));
		return true;
	}

	/**
	 * Elimina una notificacion del sistema por su indice en la lista. <b>pre</b> El
	 * index debe ser un indice valido dentro de la lista de notificaciones. <br>
	 * <b>post</b> Si el indice es valido, la notificacion queda eliminada y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice de la notificacion a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarNotificacion(int index) {
		return notificacionDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de notificaciones registradas en el sistema.
	 * <b>pre</b> El notificacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Notificacion del sistema
	 */
	public ArrayList<Notificacion> obtenerTodasLasNotificaciones() {
		return notificacionDAO.getListaNotificaciones();
	}

	/**
	 * Retorna la lista de notificaciones que aun no han sido enviadas. <b>pre</b>
	 * El notificacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las notificaciones pendientes de envio
	 */
	public ArrayList<Notificacion> obtenerNotificacionesPendientes() {
		return notificacionDAO.buscarPendientes();
	}

	/**
	 * Retorna la lista de notificaciones asociadas a un destinatario especifico.
	 * <b>pre</b> El notificacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param destinatario Correo o identificador del destinatario. destinatario !=
	 *                     null, destinatario != ""
	 * @return ArrayList con las notificaciones del destinatario indicado
	 */
	public ArrayList<Notificacion> obtenerNotificacionesPorDestinatario(String destinatario) {
		return notificacionDAO.buscarPorDestinatario(destinatario);
	}

	/**
	 * Retorna una representacion en texto de todas las notificaciones del sistema.
	 * <b>pre</b> El notificacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las notificaciones
	 */
	public String mostrarNotificaciones() {
		return notificacionDAO.mostrar();
	}

	// Metodos REGISTROS CONSUMOS SERVICIOS

	/**
	 * Registra un nuevo registro de consumo de energia en el sistema. <b>pre</b> El
	 * objeto registro debe estar inicializado con periodo, conjunto y valores de
	 * consumo validos. <br>
	 * <b>post</b> El registro de consumo de energia queda persistido en el sistema.
	 * <br>
	 * 
	 * @param registro Objeto RegistroConsumoEnergia a registrar. registro != null
	 */
	public void registrarConsumoEnergia(RegistroConsumoEnergia registro) {
		registroConsumoEnergiaDAO.crear(DataMapper.convertirEnergiaAEnergiaDTO(registro));
	}

	/**
	 * Registra un nuevo registro de consumo de agua en el sistema. <b>pre</b> El
	 * objeto registro debe estar inicializado con periodo, conjunto y valores de
	 * consumo validos. <br>
	 * <b>post</b> El registro de consumo de agua queda persistido en el sistema.
	 * <br>
	 * 
	 * @param registro Objeto RegistroConsumoAgua a registrar. registro != null
	 */
	public void registrarConsumoAgua(RegistroConsumoAgua registro) {
		registroConsumoAguaDAO.crear(DataMapper.convertirAguaAAguaDTO(registro));
	}

	/**
	 * Registra un nuevo registro de reciclaje en el sistema. <b>pre</b> El objeto
	 * registro debe estar inicializado con periodo, conjunto y kilos validos. <br>
	 * <b>post</b> El registro de reciclaje queda persistido en el sistema. <br>
	 * 
	 * @param registro Objeto RegistroReciclaje a registrar. registro != null
	 */
	public void registrarReciclaje(RegistroReciclaje registro) {
		registroReciclajeDAO.crear(DataMapper.convertirReciclajeAReciclajeDTO(registro));
	}

	/**
	 * Retorna la lista completa de registros de consumo de energia del sistema.
	 * <b>pre</b> El registroConsumoEnergiaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos RegistroConsumoEnergia del sistema
	 */
	public ArrayList<RegistroConsumoEnergia> obtenerRegistrosEnergia() {
		return registroConsumoEnergiaDAO.getListaRegistros();
	}

	/**
	 * Retorna la lista completa de registros de consumo de agua del sistema.
	 * <b>pre</b> El registroConsumoAguaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos RegistroConsumoAgua del sistema
	 */
	public ArrayList<RegistroConsumoAgua> obtenerRegistrosAgua() {
		return registroConsumoAguaDAO.getListaRegistros();
	}

	/**
	 * Retorna la lista completa de registros de reciclaje del sistema. <b>pre</b>
	 * El registroReciclajeDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos RegistroReciclaje del sistema
	 */
	public ArrayList<RegistroReciclaje> obtenerRegistrosReciclaje() {
		return registroReciclajeDAO.getListaRegistros();
	}

	/**
	 * Retorna la lista de registros de consumo de energia filtrados por conjunto.
	 * <b>pre</b> El registroConsumoEnergiaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idConjunto ID del conjunto residencial. idConjunto != null, idConjunto
	 *                   != ""
	 * @return ArrayList con los registros de energia del conjunto indicado
	 */
	public ArrayList<RegistroConsumoEnergia> obtenerRegistrosEnergiaPorConjunto(String idConjunto) {
		ArrayList<RegistroConsumoEnergia> resultado = new ArrayList<>();
		for (RegistroConsumoEnergia r : registroConsumoEnergiaDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de registros de consumo de agua filtrados por conjunto.
	 * <b>pre</b> El registroConsumoAguaDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idConjunto ID del conjunto residencial. idConjunto != null, idConjunto
	 *                   != ""
	 * @return ArrayList con los registros de agua del conjunto indicado
	 */
	public ArrayList<RegistroConsumoAgua> obtenerRegistrosAguaPorConjunto(String idConjunto) {
		ArrayList<RegistroConsumoAgua> resultado = new ArrayList<>();
		for (RegistroConsumoAgua r : registroConsumoAguaDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de registros de reciclaje filtrados por conjunto. <b>pre</b>
	 * El registroReciclajeDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idConjunto ID del conjunto residencial. idConjunto != null, idConjunto
	 *                   != ""
	 * @return ArrayList con los registros de reciclaje del conjunto indicado
	 */
	public ArrayList<RegistroReciclaje> obtenerRegistrosReciclajeporConjunto(String idConjunto) {
		ArrayList<RegistroReciclaje> resultado = new ArrayList<>();
		for (RegistroReciclaje r : registroReciclajeDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todos los registros de consumo de
	 * energia del sistema. <b>pre</b> El registroConsumoEnergiaDAO debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los registros de
	 *         energia
	 */
	public String mostrarRegistrosEnergia() {
		return registroConsumoEnergiaDAO.mostrar();
	}

	/**
	 * Retorna una representacion en texto de todos los registros de consumo de agua
	 * del sistema. <b>pre</b> El registroConsumoAguaDAO debe estar inicializado.
	 * <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los registros de agua
	 */
	public String mostrarRegistrosAgua() {
		return registroConsumoAguaDAO.mostrar();
	}

	/**
	 * Retorna una representacion en texto de todos los registros de reciclaje del
	 * sistema. <b>pre</b> El registroReciclajeDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los registros de
	 *         reciclaje
	 */
	public String mostrarRegistrosReciclaje() {
		return registroReciclajeDAO.mostrar();
	}

	// Metodos CAMPANAS Y PARTICIPACION

	/**
	 * Crea una nueva campana ambiental en el sistema con estado PROGRAMADA.
	 * <b>pre</b> El objeto campana debe estar inicializado con nombre, tipo,
	 * conjunto y fechas validas. <br>
	 * <b>post</b> La campana queda persistida con estado PROGRAMADA. <br>
	 * 
	 * @param campana Objeto CampanaAmbiental a crear. campana != null
	 */
	public void crearCampana(CampanaAmbiental campana) {
		campana.setEstado("PROGRAMADA");
		campanaAmbientalDAO.crear(DataMapper.convertirCampanaACampanaDTO(campana));
	}

	/**
	 * Activa una campana ambiental cambiando su estado a ACTIVA y notificando a
	 * todos los residentes activos. <b>pre</b> El idCampana debe corresponder a una
	 * campana existente en estado PROGRAMADA. <br>
	 * <b>post</b> Si se encuentra la campana, su estado queda en ACTIVA y todos los
	 * residentes activos con correo valido reciben una notificacion. Retorna false
	 * si no se encuentra. <br>
	 * 
	 * @param idCampana ID de la campana a activar. idCampana != null, idCampana !=
	 *                  ""
	 * @return true si la activacion fue exitosa, false si la campana no fue
	 *         encontrada
	 */
	public boolean activarCampana(String idCampana) {
		ArrayList<CampanaAmbiental> lista = campanaAmbientalDAO.getListaCampanas();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idCampana)) {
				CampanaAmbiental c = lista.get(i);
				c.setEstado("ACTIVA");
				campanaAmbientalDAO.actualizar(i, DataMapper.convertirCampanaACampanaDTO(c));

				String asunto = "Nueva campa\u00f1a ambiental: " + c.getNombre();
				String cuerpo = "Le informamos que se ha activado una nueva campa\u00f1a ambiental.\n\n"
						+ "Campa\u00f1a: " + c.getNombre() + "\n" + "Tipo: " + c.getTipo() + "\n" + "Descripcion: "
						+ c.getDescripcion() + "\n\n"
						+ "Lo invitamos a participar y contribuir con nuestra comunidad.\n\n"
						+ "Atentamente,\nGreenBuilding Group";

				for (Residente r : residenteDAO.getListaResidentes()) {
					if (r.isActivo()) {
						String correo = r.getCorreo();
						if (correo != null && !correo.isEmpty()) {
							enviarNotificacionCorreo(correo, asunto, cuerpo, "CAMPANA");
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Finaliza una campana ambiental cambiando su estado a FINALIZADA y registrando
	 * los resultados obtenidos. <b>pre</b> El idCampana debe corresponder a una
	 * campana existente. <br>
	 * <b>post</b> Si se encuentra la campana, su estado queda en FINALIZADA y los
	 * resultados quedan registrados. Retorna false si la campana no fue encontrada.
	 * <br>
	 * 
	 * @param idCampana  ID de la campana a finalizar. idCampana != null, idCampana
	 *                   != ""
	 * @param resultados Descripcion de los resultados o impacto obtenidos.
	 *                   resultados != null
	 * @return true si la finalizacion fue exitosa, false si la campana no fue
	 *         encontrada
	 */
	public boolean finalizarCampana(String idCampana, String resultados) {
		ArrayList<CampanaAmbiental> lista = campanaAmbientalDAO.getListaCampanas();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idCampana)) {
				CampanaAmbiental c = lista.get(i);
				c.setEstado("FINALIZADA");
				c.setResultados(resultados);
				campanaAmbientalDAO.actualizar(i, DataMapper.convertirCampanaACampanaDTO(c));
				return true;
			}
		}
		return false;
	}

	/**
	 * Cancela una campana ambiental cambiando su estado a CANCELADA. <b>pre</b> El
	 * idCampana debe corresponder a una campana existente. <br>
	 * <b>post</b> Si se encuentra la campana, su estado queda en CANCELADA y se
	 * persiste. Retorna false si la campana no fue encontrada. <br>
	 * 
	 * @param idCampana ID de la campana a cancelar. idCampana != null, idCampana !=
	 *                  ""
	 * @return true si la cancelacion fue exitosa, false si la campana no fue
	 *         encontrada
	 */
	public boolean cancelarCampana(String idCampana) {
		ArrayList<CampanaAmbiental> lista = campanaAmbientalDAO.getListaCampanas();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(idCampana)) {
				CampanaAmbiental c = lista.get(i);
				c.setEstado("CANCELADA");
				campanaAmbientalDAO.actualizar(i, DataMapper.convertirCampanaACampanaDTO(c));
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna la lista completa de campanas ambientales registradas en el sistema.
	 * <b>pre</b> El campanaAmbientalDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos CampanaAmbiental del sistema
	 */
	public ArrayList<CampanaAmbiental> obtenerTodasLasCampanas() {
		return campanaAmbientalDAO.getListaCampanas();
	}

	/**
	 * Retorna la lista de campanas ambientales con estado ACTIVA. <b>pre</b> El
	 * campanaAmbientalDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con las campanas actualmente activas
	 */
	public ArrayList<CampanaAmbiental> obtenerCampanasActivas() {
		ArrayList<CampanaAmbiental> resultado = new ArrayList<>();
		for (CampanaAmbiental c : campanaAmbientalDAO.getListaCampanas()) {
			if (c.getEstado().equals("ACTIVA")) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	/**
	 * Retorna la lista de campanas ambientales filtradas por tipo. <b>pre</b> El
	 * campanaAmbientalDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param tipo Tipo de campana a filtrar (RECICLAJE, AGUA, ENERGIA, MOVILIDAD,
	 *             ZONAS_VERDES). tipo != null, tipo != ""
	 * @return ArrayList con las campanas del tipo indicado
	 */
	public ArrayList<CampanaAmbiental> obtenerCampanasPorTipo(String tipo) {
		return campanaAmbientalDAO.buscarPorTipo(tipo);
	}

	/**
	 * Inscribe a un residente en una campana ambiental activa, verificando que la
	 * campana exista, este activa y que el residente no este ya inscrito.
	 * <b>pre</b> La campana debe existir y estar en estado ACTIVA. El residente
	 * debe existir en el sistema. El residente no debe estar ya inscrito en la
	 * campana. <br>
	 * <b>post</b> Si todas las validaciones pasan, la participacion queda
	 * registrada y persistida. Retorna "OK" si fue exitoso o un mensaje de error
	 * descriptivo. <br>
	 * 
	 * @param idCampana     ID de la campana ambiental. idCampana != null, idCampana
	 *                      != ""
	 * @param idResidente   ID del residente a inscribir. idResidente != null,
	 *                      idResidente != ""
	 * @param idApartamento ID del apartamento del residente. idApartamento != null,
	 *                      idApartamento != ""
	 * @return "OK" si la inscripcion fue exitosa, o un String con el motivo del
	 *         error
	 */
	public String inscribirEnCampana(String idCampana, String idResidente, String idApartamento) {
		CampanaAmbiental campana = buscarCampanaPorId(idCampana);
		if (campana == null) {
			return "Campa\u00f1a no encontrada.";
		}
		if (!campana.getEstado().equals("ACTIVA")) {
			return "La campa\u00f1a no esta activa.";
		}
		for (Participacion p : participacionDAO.buscarPorCampana(idCampana)) {
			if (p.getResidente() != null && p.getResidente().getId().equals(idResidente)) {
				return "El residente ya esta inscrito en esta campa\u00f1a.";
			}
		}
		Residente residente = buscarResidentePorId(idResidente);
		if (residente == null) {
			return "Residente no encontrado.";
		}
		Participacion p = new Participacion();
		p.setId("PAR-" + contadorIds++);
		p.setCampana(campana);
		p.setResidente(residente);
		p.setApartamento(buscarApartamentoPorId(idApartamento));
		p.setFechaParticipacion(LocalDate.now());
		participacionDAO.crear(DataMapper.convertirParticipacionAParticipacionDTO(p));
		return "OK";
	}

	/**
	 * Retorna la lista de participaciones registradas en una campana especifica.
	 * <b>pre</b> El participacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idCampana ID de la campana. idCampana != null, idCampana != ""
	 * @return ArrayList con las participaciones de la campana indicada
	 */
	public ArrayList<Participacion> obtenerParticipacionesPorCampana(String idCampana) {
		return participacionDAO.buscarPorCampana(idCampana);
	}

	/**
	 * Retorna la lista de participaciones de un residente especifico en campanas
	 * ambientales. <b>pre</b> El participacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param idResidente ID del residente. idResidente != null, idResidente != ""
	 * @return ArrayList con las participaciones del residente indicado
	 */
	public ArrayList<Participacion> obtenerParticipacionesPorResidente(String idResidente) {
		return participacionDAO.buscarPorResidente(idResidente);
	}

	/**
	 * Retorna una representacion en texto de todas las participaciones en campanas
	 * del sistema. <b>pre</b> El participacionDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las participaciones
	 */
	public String mostrarParticipaciones() {
		return participacionDAO.mostrar();
	}

	/**
	 * Genera y retorna un resumen ambiental completo de un conjunto, incluyendo
	 * consumos de energia, agua, reciclaje y campanas, y exporta el resultado en
	 * formato PDF. <b>pre</b> El idConjunto debe corresponder a un conjunto
	 * existente en el sistema. <br>
	 * <b>post</b> No se modifica el estado del sistema. Se genera un archivo PDF
	 * del resumen ambiental en la carpeta files/. <br>
	 * 
	 * @param idConjunto ID del conjunto del que se genera el resumen ambiental.
	 *                   idConjunto != null, idConjunto != ""
	 * @return String con el resumen ambiental detallado del conjunto
	 */
	public String obtenerResumenAmbiental(String idConjunto) {
		StringBuilder sb = new StringBuilder();
		sb.append("RESUMEN AMBIENTAL - Conjunto ").append(idConjunto).append(" \n");
		sb.append("\n   ENERGIA   \n");
		for (RegistroConsumoEnergia r : registroConsumoEnergiaDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				sb.append("  Periodo: ").append(r.getPeriodo()).append(" | Consumo: ").append(r.getConsumoKwh())
						.append(" kWh").append(" | Solar: ").append(r.getGeneracionSolarKwh()).append(" kWh\n");
			}
		}
		sb.append("\n   AGUA   \n");
		for (RegistroConsumoAgua r : registroConsumoAguaDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				sb.append("  Periodo: ").append(r.getPeriodo()).append(" | Consumo: ").append(r.getConsumoMtCubico())
						.append(" m3").append(" | Lluvia: ").append(r.getAguaLluviaMtCubico()).append(" m3\n");
			}
		}
		sb.append("\n   RECICLAJE   \n");
		for (RegistroReciclaje r : registroReciclajeDAO.getListaRegistros()) {
			if (r.getConjunto() != null && r.getConjunto().getId().equals(idConjunto)) {
				sb.append("  Periodo: ").append(r.getPeriodo()).append(" | Total: ").append(r.getTotalKg())
						.append(" kg\n");
			}
		}
		sb.append("\n   CAMPA\u00d1AS   \n");
		for (CampanaAmbiental c : campanaAmbientalDAO.getListaCampanas()) {
			if (c.getConjunto() != null && c.getConjunto().getId().equals(idConjunto)) {
				sb.append("  ").append(c.getNombre()).append(" - ").append(c.getEstado()).append("\n");
			}
		}
		String contenido = sb.toString();
		FileHandler.exportarPDF("ambiental_" + idConjunto + ".pdf", "Resumen Ambiental - Conjunto " + idConjunto,
				contenido);
		return contenido;
	}

	/**
	 * Retorna una representacion en texto de todas las campanas ambientales del
	 * sistema. <b>pre</b> El campanaAmbientalDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todas las campanas
	 */
	public String mostrarCampanas() {
		return campanaAmbientalDAO.mostrar();
	}

	// Metodos REPORTES

	/**
	 * Genera un reporte del tipo indicado para un conjunto residencial,
	 * construyendo su contenido con datos actuales del sistema y exportandolo
	 * automaticamente en formato PDF. Los tipos soportados son: ADMINISTRATIVO,
	 * OPERATIVO, CONVIVENCIA, FINANCIERO y AMBIENTAL. <b>pre</b> El tipo debe ser
	 * uno de los tipos soportados. El idConjunto debe existir. El generadoPor debe
	 * identificar al usuario que genera el reporte. <br>
	 * <b>post</b> El reporte queda creado, persistido y exportado en PDF. Se
	 * retorna el objeto Reporte generado. <br>
	 * 
	 * @param tipo        Tipo de reporte a generar (ADMINISTRATIVO, OPERATIVO,
	 *                    CONVIVENCIA, FINANCIERO, AMBIENTAL). tipo != null, tipo !=
	 *                    ""
	 * @param idConjunto  ID del conjunto para el que se genera el reporte.
	 *                    idConjunto != null, idConjunto != ""
	 * @param generadoPor Identificador del usuario que genera el reporte.
	 *                    generadoPor != null, generadoPor != ""
	 * @return El objeto Reporte generado y persistido
	 */
	public Reporte generarReporte(String tipo, String idConjunto, String generadoPor) {
		Reporte reporte = new Reporte();
		reporte.setId("REP-" + contadorIds++);
		reporte.setTipo(tipo);
		reporte.setFechaGeneracion(LocalDateTime.now());
		reporte.setGeneradoPor(generadoPor);
		reporte.setConjunto(buscarConjuntoPorId(idConjunto));
		StringBuilder contenido = new StringBuilder();

		switch (tipo) {

		case "ADMINISTRATIVO": {
			contenido.append("    REPORTE ADMINISTRATIVO    \n\n");
			contenido.append("   OCUPACION DE APARTAMENTOS   \n");
			List<Apartamento> ocupadosProp = obtenerApartamentosPorEstado("OCUPADO_PROPIETARIO");
			List<Apartamento> arrendados = obtenerApartamentosPorEstado("ARRENDADO");
			List<Apartamento> desocupados = obtenerApartamentosPorEstado("DESOCUPADO");
			contenido.append("Ocupados por propietario: ").append(ocupadosProp.size()).append("\n");
			contenido.append("Arrendados: ").append(arrendados.size()).append("\n");
			contenido.append("Desocupados: ").append(desocupados.size()).append("\n");
			for (Apartamento a : apartamentoDAO.getListaApartamentos()) {
				contenido.append("  Apto ").append(a.getNumero()).append(" - ").append(a.getEstado());
				if (a.getPropietario() != null) {
					contenido.append(" | Prop: ").append(a.getPropietario().getNombre());
				}
				if (a.getArrendatario() != null) {
					contenido.append(" | Arr: ").append(a.getArrendatario().getNombre());
				}
				contenido.append("\n");
			}

			contenido.append("\n   RESIDENTES   \n");
			int activos = 0, inactivos = 0;
			for (Residente r : residenteDAO.getListaResidentes()) {
				if (r.isActivo()) {
					activos++;
				} else {
					inactivos++;
				}
			}
			contenido.append("Activos: ").append(activos).append("\n");
			contenido.append("Inactivos: ").append(inactivos).append("\n");
			contenido.append("\n   VEHICULOS REGISTRADOS   \n");
			for (Vehiculo v : vehiculoDAO.getListaVehiculos()) {
				contenido.append("  ").append(v.getPlaca()).append(" | ").append(v.getTipo()).append(" | ")
						.append(v.getMarca()).append(" | Autorizado: ");
				if (v.isAutorizado()) {
					contenido.append("SI");
				} else {
					contenido.append("NO");
				}
				contenido.append("\n");
			}

			contenido.append("\n   USO DE PARQUEADEROS   \n");
			int libres = 0, ocupados = 0;
			for (Parqueadero p : parqueaderoDAO.getListaParqueaderos()) {
				if ("DISPONIBLE".equals(p.getEstado())) {
					libres++;
				} else {
					ocupados++;
				}
				contenido.append("  ").append(p.getNumero()).append(" | ").append(p.getTipo()).append(" | ")
						.append(p.getEstado()).append("\n");
			}
			contenido.append("Libres: ").append(libres).append(" | Ocupados: ").append(ocupados).append("\n");

			reporte.setTitulo("Reporte Administrativo");
			break;
		}

		case "OPERATIVO": {
			contenido.append("    REPORTE OPERATIVO    \n\n");
			contenido.append("   RESERVAS DE ZONAS COMUNES   \n");
			int confirmadas = 0, canceladas = 0;
			List<String> zonas = new ArrayList<>();
			List<Integer> conteoZonas = new ArrayList<>();

			for (Reserva r : reservaDAO.getListaReservas()) {
				if ("CONFIRMADA".equals(r.getEstado()))
					confirmadas++;
				if ("CANCELADA".equals(r.getEstado()))
					canceladas++;
				if (r.getZona() != null) {
					String zona = r.getZona().getNombre();
					int index = zonas.indexOf(zona);
					if (index == -1) {
						zonas.add(zona);
						conteoZonas.add(1);
					} else {
						conteoZonas.set(index, conteoZonas.get(index) + 1);
					}
				}
			}
			contenido.append("Confirmadas: ").append(confirmadas).append("\n");
			contenido.append("Canceladas o incumplidas: ").append(canceladas).append("\n");

			contenido.append("\n   ZONAS COMUNES MAS UTILIZADAS   \n");
			for (int i = 0; i < zonas.size(); i++) {
				contenido.append("  ").append(zonas.get(i)).append(": ").append(conteoZonas.get(i))
						.append(" reservas\n");
			}

			contenido.append("\n   SOLICITUDES DE MANTENIMIENTO   \n");
			contenido.append("Abiertas: ").append(obtenerSolicitudesAbiertas().size()).append("\n");
			contenido.append("Vencidas: ").append(obtenerSolicitudesVencidas().size()).append("\n");
			int cerradas = 0;
			for (SolicitudMantenimiento s : solicitudMantenimientoDAO.getListaSolicitudes()) {
				if ("CERRADA".equals(s.getEstado()) || "COMPLETADA".equals(s.getEstado()))
					cerradas++;
			}
			contenido.append("Cerradas/Completadas: ").append(cerradas).append("\n");

			contenido.append("\n   VISITANTES FRECUENTES   \n");
			List<String> visitantes = new ArrayList<>();
			List<Integer> conteoVisitas = new ArrayList<>();
			for (RegistroVisita rv : registroVisitaDAO.getListaRegistros()) {
				if (rv.getVisitante() != null) {
					String nombre = rv.getVisitante().getNombre();
					int index = visitantes.indexOf(nombre);
					if (index == -1) {
						visitantes.add(nombre);
						conteoVisitas.add(1);
					} else {
						conteoVisitas.set(index, conteoVisitas.get(index) + 1);
					}
				}
			}
			for (int i = 0; i < visitantes.size(); i++) {
				if (conteoVisitas.get(i) > 1) {
					contenido.append("  ").append(visitantes.get(i)).append(": ").append(conteoVisitas.get(i))
							.append(" visitas\n");
				}
			}

			contenido.append("\n   PAQUETES   \n");
			int pendientes = 0, entregados = 0;
			for (Paquete p : paqueteDAO.getListaPaquetes()) {
				if ("PENDIENTE".equals(p.getEstado()))
					pendientes++;
				else if ("ENTREGADO".equals(p.getEstado()))
					entregados++;
			}
			contenido.append("Pendientes: ").append(pendientes).append("\n");
			contenido.append("Entregados: ").append(entregados).append("\n");

			reporte.setTitulo("Reporte Operativo");
			break;
		}
		case "CONVIVENCIA": {
			contenido.append("    REPORTE DE CONVIVENCIA    \n\n");
			contenido.append("   INCIDENTES POR TIPO   \n");
			List<String> tipos = new ArrayList<>();
			List<Integer> conteoTipos = new ArrayList<>();
			List<String> gravedades = new ArrayList<>();
			List<Integer> conteoGravedades = new ArrayList<>();
			List<String> apartamentos = new ArrayList<>();
			List<Integer> conteoApartamentos = new ArrayList<>();

			for (Incidente inc : incidenteDAO.getListaIncidentes()) {
				if (inc.getTipo() != null) {
					int idx = tipos.indexOf(inc.getTipo());
					if (idx == -1) {
						tipos.add(inc.getTipo());
						conteoTipos.add(1);
					} else {
						conteoTipos.set(idx, conteoTipos.get(idx) + 1);
					}
				}
				if (inc.getGravedad() != null) {
					int idx = gravedades.indexOf(inc.getGravedad());
					if (idx == -1) {
						gravedades.add(inc.getGravedad());
						conteoGravedades.add(1);
					} else {
						conteoGravedades.set(idx, conteoGravedades.get(idx) + 1);
					}
				}
				if (inc.getApartamentoInvolucrado() != null) {
					String apto = "Apto " + inc.getApartamentoInvolucrado().getNumero();
					int idx = apartamentos.indexOf(apto);
					if (idx == -1) {
						apartamentos.add(apto);
						conteoApartamentos.add(1);
					} else {
						conteoApartamentos.set(idx, conteoApartamentos.get(idx) + 1);
					}
				}
			}

			for (int i = 0; i < tipos.size(); i++) {
				contenido.append("  ").append(tipos.get(i)).append(": ").append(conteoTipos.get(i)).append("\n");
			}

			contenido.append("\n   INCIDENTES POR GRAVEDAD   \n");
			for (int i = 0; i < gravedades.size(); i++) {
				contenido.append("  ").append(gravedades.get(i)).append(": ").append(conteoGravedades.get(i))
						.append("\n");
			}

			contenido.append("\n   APARTAMENTOS CON MAS INCIDENTES   \n");
			for (int i = 0; i < apartamentos.size(); i++) {
				contenido.append("  ").append(apartamentos.get(i)).append(": ").append(conteoApartamentos.get(i))
						.append(" incidentes\n");
			}

			contenido.append("\n   COMPORTAMIENTOS RECURRENTES   \n");
			for (int i = 0; i < apartamentos.size(); i++) {
				if (conteoApartamentos.get(i) > 1) {
					contenido.append("  ").append(apartamentos.get(i)).append(": ").append(conteoApartamentos.get(i))
							.append(" reportes - REINCIDENTE\n");
				}
			}
			reporte.setTitulo("Reporte de Convivencia");
			break;
		}

		case "FINANCIERO": {
			contenido.append("    REPORTE FINANCIERO    \n\n");
			contenido.append("   PAGOS REALIZADOS   \n");
			double totalRecaudado = 0;
			for (Pago p : pagoDAO.getListaPagos()) {
				totalRecaudado += p.getMonto();
				String aptoNum = "N/A";
				if (p.getApartamento() != null) {
					aptoNum = p.getApartamento().getNumero();
				}
				contenido.append("  Apto ").append(aptoNum).append(" | $").append(p.getMonto()).append(" | ")
						.append(p.getFechaPago()).append("\n");
			}
			contenido.append("Total recaudado: $").append(totalRecaudado).append("\n");
			contenido.append("\n   OBLIGACIONES PENDIENTES   \n");
			double totalPendiente = 0;
			int multas = 0;
			for (Obligacion o : obligacionDAO.getListaObligaciones()) {
				if ("PENDIENTE".equals(o.getEstado())) {
					totalPendiente += o.getMonto();
					String aptoNum = "N/A";
					if (o.getApartamento() != null) {
						aptoNum = o.getApartamento().getNumero();
					}
					contenido.append("  Apto ").append(aptoNum).append(" | ").append(o.getTipo()).append(" | $")
							.append(o.getMonto()).append(" | Vence: ").append(o.getFechaLimite()).append("\n");
					if ("Multa".equals(o.getTipo()) || "MULTA".equals(o.getTipo())) {
						multas++;
					}
				}
			}
			contenido.append("Total pendiente: $").append(totalPendiente).append("\n");
			contenido.append("\n   MULTAS GENERADAS   \n");
			contenido.append("Total multas: ").append(multas).append("\n");
			contenido.append("Apartamentos morosos: ").append(obtenerApartamentosMorosos().size()).append("\n");

			reporte.setTitulo("Reporte Financiero");
			break;
		}

		case "AMBIENTAL": {
			contenido.append(obtenerResumenAmbiental(idConjunto));
			contenido.append("\n   PARTICIPACION EN CAMPA\u00d1AS COMUNITARIAS   \n");
			for (CampanaAmbiental c : campanaAmbientalDAO.getListaCampanas()) {
				List<Participacion> parts = obtenerParticipacionesPorCampana(c.getId());
				contenido.append("  ").append(c.getNombre()).append(" (").append(c.getEstado()).append(")").append(": ")
						.append(parts.size()).append(" participantes\n");
			}

			reporte.setTitulo("Reporte Ambiental");
			break;
		}
		default: {
			contenido.append("Tipo de reporte no reconocido.");
			reporte.setTitulo("Reporte General");
			break;
		}
		}
		reporte.setContenido(contenido.toString());
		reporteDAO.crear(DataMapper.convertirReporteAReporteDTO(reporte));
		int indexNuevo = reporteDAO.getListaReportes().size() - 1;
		exportarReportePDF(indexNuevo);
		return reporte;
	}

	/**
	 * Elimina un reporte del sistema por su indice en la lista. <b>pre</b> El index
	 * debe ser un indice valido dentro de la lista de reportes. <br>
	 * <b>post</b> Si el indice es valido, el reporte queda eliminado y la
	 * persistencia actualizada. <br>
	 * 
	 * @param index Indice del reporte a eliminar. index >= 0
	 * @return true si la eliminacion fue exitosa, false si el indice no es valido
	 */
	public boolean eliminarReporte(int index) {
		return reporteDAO.eliminar(index);
	}

	/**
	 * Retorna la lista completa de reportes generados en el sistema. <b>pre</b> El
	 * reporteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return ArrayList con todos los objetos Reporte del sistema
	 */
	public ArrayList<Reporte> obtenerTodosLosReportes() {
		return reporteDAO.getListaReportes();
	}

	/**
	 * Retorna la lista de reportes filtrados por tipo. <b>pre</b> El reporteDAO
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param tipo Tipo de reporte a filtrar (ADMINISTRATIVO, OPERATIVO,
	 *             CONVIVENCIA, FINANCIERO, AMBIENTAL). tipo != null, tipo != ""
	 * @return ArrayList con los reportes del tipo indicado
	 */
	public ArrayList<Reporte> obtenerReportes(String tipo) {
		ArrayList<Reporte> resultado = new ArrayList<>();
		for (Reporte r : reporteDAO.getListaReportes()) {
			if (r.getTipo().equals(tipo)) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/**
	 * Retorna una representacion en texto de todos los reportes del sistema.
	 * <b>pre</b> El reporteDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @return String con la informacion formateada de todos los reportes
	 */
	public String mostrarReportes() {
		return reporteDAO.mostrar();
	}

	/**
	 * Exporta un reporte existente en formato PDF usando el indice indicado.
	 * <b>pre</b> El index debe ser un indice valido dentro de la lista de reportes.
	 * <br>
	 * <b>post</b> Si el indice es valido, el archivo PDF del reporte queda generado
	 * en la carpeta files/. <br>
	 * 
	 * @param index Indice del reporte a exportar. index >= 0
	 */
	public void exportarReportePDF(int index) {
		reporteDAO.exportarReportePDF(index);
	}

	// METODOS EXTRA

	/**
	 * Busca y retorna un apartamento por su identificador unico recorriendo la
	 * lista completa. <b>pre</b> El apartamentoDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador del apartamento a buscar. id != null, id != ""
	 * @return El objeto Apartamento con el id indicado, o null si no existe
	 */
	public Apartamento buscarApartamentoPorId(String id) {
		for (Apartamento a : apartamentoDAO.getListaApartamentos()) {
			if (a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Busca y retorna una torre por su identificador unico recorriendo la lista
	 * completa. <b>pre</b> El torreDAO debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 * 
	 * @param id Identificador de la torre a buscar. id != null, id != ""
	 * @return El objeto Torre con el id indicado, o null si no existe
	 */
	public Torre buscarTorrePorId(String id) {
		for (Torre t : torreDAO.getListaTorres()) {
			if (t.getId().equals(id)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Busca una zona comun por su identificador unico.
	 * 
	 * @param id Identificador de la zona comun a buscar.
	 * @return La zona comun encontrada o null si no existe.
	 */
	public ZonaComun buscarZonaComunPorId(String id) {
		for (ZonaComun z : zonaComunDAO.getListaZonasComunes()) {
			if (z.getId().equals(id)) {
				return z;
			}
		}
		return null;
	}

	/**
	 * Busca un residente por su identificador unico.
	 * 
	 * @param id Identificador del residente.
	 * @return El residente encontrado o null si no existe.
	 */
	public Residente buscarResidentePorId(String id) {
		for (Residente r : residenteDAO.getListaResidentes()) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Busca una campana ambiental por su identificador unico.
	 * 
	 * @param id Identificador de la campana ambiental.
	 * @return La campana encontrada o null si no existe.
	 */
	public CampanaAmbiental buscarCampanaPorId(String id) {
		for (CampanaAmbiental c : campanaAmbientalDAO.getListaCampanas()) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Verifica si dos rangos de horas se superponen.
	 * 
	 * @param ini1 Hora inicial del primer rango.
	 * @param fin1 Hora final del primer rango.
	 * @param ini2 Hora inicial del segundo rango.
	 * @param fin2 Hora final del segundo rango.
	 * @return true si los horarios se cruzan, false en caso contrario.
	 */
	public boolean horasSeSuperponen(LocalTime ini1, LocalTime fin1, LocalTime ini2, LocalTime fin2) {
		return ini1.isBefore(fin2) && ini2.isBefore(fin1);
	}

	/**
	 * Incrementa el contador global de identificadores.
	 */
	public void incrementarContadorIds() {
		contadorIds++;
	}

	/**
	 * Genera un nombre de usuario a partir del nombre y la cedula.
	 * 
	 * @param nombre Nombre de la persona.
	 * @param cedula Cedula de la persona.
	 * @return Nombre de usuario generado automaticamente.
	 */
	public String generarUsername(String nombre, String cedula) {
		String[] partes = nombre.trim().toLowerCase().split(" ");
		String base = partes[0];
		String sufijo;

		if (cedula.length() >= 4) {
			sufijo = cedula.substring(cedula.length() - 4);
		} else {
			sufijo = cedula;
		}

		return base + sufijo;
	}

	/**
	 * Genera una contrasena aleatoria de 8 caracteres.
	 * 
	 * @return Contrasena generada aleatoriamente.
	 */
	public String generarContrasenaAleatoria() {
		String caracteres = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
		}
		return sb.toString();
	}

	/**
	 * Obtiene el correo del responsable principal de un apartamento. Prioriza el
	 * arrendatario activo y, si no existe, usa el propietario.
	 * 
	 * @param apto Apartamento del cual se desea obtener el correo.
	 * @return Correo electronico encontrado o null si no existe.
	 */
	public String obtenerCorreoResponsableApartamento(Apartamento apto) {
		if (apto == null)
			return null;
		if (apto.getArrendatario() != null && apto.getArrendatario().isActivo()) {
			String correo = apto.getArrendatario().getCorreo();
			if (correo != null && !correo.isEmpty())
				return correo;
		}
		if (apto.getPropietario() != null) {
			String correo = apto.getPropietario().getCorreo();
			if (correo != null && !correo.isEmpty())
				return correo;
		}
		return null;
	}

	/**
	 * Obtiene el valor actual del contador de identificadores.
	 * 
	 * @return Contador de IDs.
	 */
	public int getContadorIds() {
		return contadorIds;
	}

	/**
	 * Modifica el valor del contador de identificadores.
	 * 
	 * @param contadorIds Nuevo valor del contador.
	 */
	public void setContadorIds(int contadorIds) {
		this.contadorIds = contadorIds;
	}

	/**
	 * Obtiene el DAO de conjuntos.
	 * 
	 * @return DAO de conjuntos.
	 */
	public ConjuntoDAO getConjuntoDAO() {
		return conjuntoDAO;
	}

	/**
	 * Modifica el DAO de conjuntos.
	 * 
	 * @param conjuntoDAO Nuevo DAO de conjuntos.
	 */
	public void setConjuntoDAO(ConjuntoDAO conjuntoDAO) {
		this.conjuntoDAO = conjuntoDAO;
	}

	/**
	 * Obtiene el DAO de torres.
	 * 
	 * @return DAO de torres.
	 */
	public TorreDAO getTorreDAO() {
		return torreDAO;
	}

	/**
	 * Modifica el DAO de torres.
	 * 
	 * @param torreDAO Nuevo DAO de torres.
	 */
	public void setTorreDAO(TorreDAO torreDAO) {
		this.torreDAO = torreDAO;
	}

	/**
	 * Obtiene el DAO de apartamentos.
	 * 
	 * @return DAO de apartamentos.
	 */
	public ApartamentoDAO getApartamentoDAO() {
		return apartamentoDAO;
	}

	/**
	 * Modifica el DAO de apartamentos.
	 * 
	 * @param apartamentoDAO Nuevo DAO de apartamentos.
	 */
	public void setApartamentoDAO(ApartamentoDAO apartamentoDAO) {
		this.apartamentoDAO = apartamentoDAO;
	}

	/**
	 * Obtiene el DAO de propietarios.
	 * 
	 * @return DAO de propietarios.
	 */
	public PropietarioDAO getPropietarioDAO() {
		return propietarioDAO;
	}

	/**
	 * Modifica el DAO de propietarios.
	 * 
	 * @param propietarioDAO Nuevo DAO de propietarios.
	 */
	public void setPropietarioDAO(PropietarioDAO propietarioDAO) {
		this.propietarioDAO = propietarioDAO;
	}

	/**
	 * Obtiene el DAO de arrendatarios.
	 * 
	 * @return DAO de arrendatarios.
	 */
	public ArrendatarioDAO getArrendatarioDAO() {
		return arrendatarioDAO;
	}

	/**
	 * Modifica el DAO de arrendatarios.
	 * 
	 * @param arrendatarioDAO Nuevo DAO de arrendatarios.
	 */
	public void setArrendatarioDAO(ArrendatarioDAO arrendatarioDAO) {
		this.arrendatarioDAO = arrendatarioDAO;
	}

	/**
	 * Obtiene el DAO de residentes.
	 * 
	 * @return DAO de residentes.
	 */
	public ResidenteDAO getResidenteDAO() {
		return residenteDAO;
	}

	/**
	 * Modifica el DAO de residentes.
	 * 
	 * @param residenteDAO Nuevo DAO de residentes.
	 */
	public void setResidenteDAO(ResidenteDAO residenteDAO) {
		this.residenteDAO = residenteDAO;
	}

	/**
	 * Obtiene el DAO de vehiculos.
	 * 
	 * @return DAO de vehiculos.
	 */
	public VehiculoDAO getVehiculoDAO() {
		return vehiculoDAO;
	}

	/**
	 * Modifica el DAO de vehiculos.
	 * 
	 * @param vehiculoDAO Nuevo DAO de vehiculos.
	 */
	public void setVehiculoDAO(VehiculoDAO vehiculoDAO) {
		this.vehiculoDAO = vehiculoDAO;
	}

	/**
	 * Obtiene el DAO de mascotas.
	 * 
	 * @return DAO de mascotas.
	 */
	public MascotaDAO getMascotaDAO() {
		return mascotaDAO;
	}

	/**
	 * Modifica el DAO de mascotas.
	 * 
	 * @param mascotaDAO Nuevo DAO de mascotas.
	 */
	public void setMascotaDAO(MascotaDAO mascotaDAO) {
		this.mascotaDAO = mascotaDAO;
	}

	/**
	 * Obtiene el DAO de parqueaderos.
	 * 
	 * @return DAO de parqueaderos.
	 */
	public ParqueaderoDAO getParqueaderoDAO() {
		return parqueaderoDAO;
	}

	/**
	 * Modifica el DAO de parqueaderos.
	 * 
	 * @param parqueaderoDAO Nuevo DAO de parqueaderos.
	 */
	public void setParqueaderoDAO(ParqueaderoDAO parqueaderoDAO) {
		this.parqueaderoDAO = parqueaderoDAO;
	}

	/**
	 * Obtiene el DAO de visitantes.
	 * 
	 * @return DAO de visitantes.
	 */
	public VisitanteDAO getVisitanteDAO() {
		return visitanteDAO;
	}

	/**
	 * Modifica el DAO de visitantes.
	 * 
	 * @param visitanteDAO Nuevo DAO de visitantes.
	 */
	public void setVisitanteDAO(VisitanteDAO visitanteDAO) {
		this.visitanteDAO = visitanteDAO;
	}

	/**
	 * Obtiene el DAO de registros de visitas.
	 * 
	 * @return DAO de registros de visitas.
	 */
	public RegistroVisitaDAO getRegistroVisitaDAO() {
		return registroVisitaDAO;
	}

	/**
	 * Modifica el DAO de registros de visitas.
	 * 
	 * @param registroVisitaDAO Nuevo DAO de registros de visitas.
	 */
	public void setRegistroVisitaDAO(RegistroVisitaDAO registroVisitaDAO) {
		this.registroVisitaDAO = registroVisitaDAO;
	}

	/**
	 * Obtiene el DAO de paquetes.
	 * 
	 * @return DAO de paquetes.
	 */
	public PaqueteDAO getPaqueteDAO() {
		return paqueteDAO;
	}

	/**
	 * Modifica el DAO de paquetes.
	 * 
	 * @param paqueteDAO Nuevo DAO de paquetes.
	 */
	public void setPaqueteDAO(PaqueteDAO paqueteDAO) {
		this.paqueteDAO = paqueteDAO;
	}

	/**
	 * Obtiene el DAO de zonas comunes.
	 * 
	 * @return DAO de zonas comunes.
	 */
	public ZonaComunDAO getZonaComunDAO() {
		return zonaComunDAO;
	}

	/**
	 * Modifica el DAO de zonas comunes.
	 * 
	 * @param zonaComunDAO Nuevo DAO de zonas comunes.
	 */
	public void setZonaComunDAO(ZonaComunDAO zonaComunDAO) {
		this.zonaComunDAO = zonaComunDAO;
	}

	/**
	 * Obtiene el DAO de reservas.
	 * 
	 * @return DAO de reservas.
	 */
	public ReservaDAO getReservaDAO() {
		return reservaDAO;
	}

	/**
	 * Modifica el DAO de reservas.
	 * 
	 * @param reservaDAO Nuevo DAO de reservas.
	 */
	public void setReservaDAO(ReservaDAO reservaDAO) {
		this.reservaDAO = reservaDAO;
	}

	/**
	 * Obtiene el DAO de pagos.
	 * 
	 * @return DAO de pagos.
	 */
	public PagoDAO getPagoDAO() {
		return pagoDAO;
	}

	/**
	 * Modifica el DAO de pagos.
	 * 
	 * @param pagoDAO Nuevo DAO de pagos.
	 */
	public void setPagoDAO(PagoDAO pagoDAO) {
		this.pagoDAO = pagoDAO;
	}

	/**
	 * Obtiene el DAO de obligaciones.
	 * 
	 * @return DAO de obligaciones.
	 */
	public ObligacionDAO getObligacionDAO() {
		return obligacionDAO;
	}

	/**
	 * Modifica el DAO de obligaciones.
	 * 
	 * @param obligacionDAO Nuevo DAO de obligaciones.
	 */
	public void setObligacionDAO(ObligacionDAO obligacionDAO) {
		this.obligacionDAO = obligacionDAO;
	}

	/**
	 * Obtiene el DAO de incidentes.
	 * 
	 * @return DAO de incidentes.
	 */
	public IncidenteDAO getIncidenteDAO() {
		return incidenteDAO;
	}

	/**
	 * Modifica el DAO de incidentes.
	 * 
	 * @param incidenteDAO Nuevo DAO de incidentes.
	 */
	public void setIncidenteDAO(IncidenteDAO incidenteDAO) {
		this.incidenteDAO = incidenteDAO;
	}

	/**
	 * Obtiene el DAO de solicitudes de mantenimiento.
	 * 
	 * @return DAO de solicitudes de mantenimiento.
	 */
	public SolicitudMantenimientoDAO getSolicitudMantenimientoDAO() {
		return solicitudMantenimientoDAO;
	}

	/**
	 * Modifica el DAO de solicitudes de mantenimiento.
	 * 
	 * @param s Nuevo DAO de solicitudes de mantenimiento.
	 */
	public void setSolicitudMantenimientoDAO(SolicitudMantenimientoDAO s) {
		this.solicitudMantenimientoDAO = s;
	}

	/**
	 * Obtiene el DAO de notificaciones.
	 * 
	 * @return DAO de notificaciones.
	 */
	public NotificacionDAO getNotificacionDAO() {
		return notificacionDAO;
	}

	/**
	 * Modifica el DAO de notificaciones.
	 * 
	 * @param notificacionDAO Nuevo DAO de notificaciones.
	 */
	public void setNotificacionDAO(NotificacionDAO notificacionDAO) {
		this.notificacionDAO = notificacionDAO;
	}

	/**
	 * Obtiene el DAO de reportes.
	 * 
	 * @return DAO de reportes.
	 */
	public ReporteDAO getReporteDAO() {
		return reporteDAO;
	}

	/**
	 * Modifica el DAO de reportes.
	 * 
	 * @param reporteDAO Nuevo DAO de reportes.
	 */
	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}

	/**
	 * Obtiene el DAO de registros de consumo de agua.
	 * 
	 * @return DAO de consumo de agua.
	 */
	public RegistroConsumoAguaDAO getRegistroConsumoAguaDAO() {
		return registroConsumoAguaDAO;
	}

	/**
	 * Modifica el DAO de registros de consumo de agua.
	 * 
	 * @param r Nuevo DAO de consumo de agua.
	 */
	public void setRegistroConsumoAguaDAO(RegistroConsumoAguaDAO r) {
		this.registroConsumoAguaDAO = r;
	}

	/**
	 * Obtiene el DAO de registros de consumo de energia.
	 * 
	 * @return DAO de consumo de energia.
	 */
	public RegistroConsumoEnergiaDAO getRegistroConsumoEnergiaDAO() {
		return registroConsumoEnergiaDAO;
	}

	/**
	 * Modifica el DAO de registros de consumo de energia.
	 * 
	 * @param r Nuevo DAO de consumo de energia.
	 */
	public void setRegistroConsumoEnergiaDAO(RegistroConsumoEnergiaDAO r) {
		this.registroConsumoEnergiaDAO = r;
	}

	/**
	 * Obtiene el DAO de registros de reciclaje.
	 * 
	 * @return DAO de reciclaje.
	 */
	public RegistroReciclajeDAO getRegistroReciclajeDAO() {
		return registroReciclajeDAO;
	}

	/**
	 * Modifica el DAO de registros de reciclaje.
	 * 
	 * @param r Nuevo DAO de reciclaje.
	 */
	public void setRegistroReciclajeDAO(RegistroReciclajeDAO r) {
		this.registroReciclajeDAO = r;
	}

	/**
	 * Obtiene el DAO de campanas ambientales.
	 * 
	 * @return DAO de campanas ambientales.
	 */
	public CampanaAmbientalDAO getCampanaAmbientalDAO() {
		return campanaAmbientalDAO;
	}

	/**
	 * Modifica el DAO de campanas ambientales.
	 * 
	 * @param c Nuevo DAO de campanas ambientales.
	 */
	public void setCampanaAmbientalDAO(CampanaAmbientalDAO c) {
		this.campanaAmbientalDAO = c;
	}

	/**
	 * Obtiene el DAO de participaciones.
	 * 
	 * @return DAO de participaciones.
	 */
	public ParticipacionDAO getParticipacionDAO() {
		return participacionDAO;
	}

	/**
	 * Modifica el DAO de participaciones.
	 * 
	 * @param participacionDAO Nuevo DAO de participaciones.
	 */
	public void setParticipacionDAO(ParticipacionDAO participacionDAO) {
		this.participacionDAO = participacionDAO;
	}

	/**
	 * Obtiene el DAO de usuarios.
	 * 
	 * @return DAO de usuarios.
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Modifica el DAO de usuarios.
	 * 
	 * @param usuarioDAO Nuevo DAO de usuarios.
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}