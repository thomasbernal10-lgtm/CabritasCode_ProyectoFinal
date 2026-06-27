package co.edu.unbosque.view;

/**
 * Fachada principal de la capa Vista (View en el patron MVC) del sistema
 * GreenBuilding Manager. Centraliza la creacion y el acceso a todas las
 * ventanas de la interfaz grafica, incluyendo la ventana de bienvenida y las
 * ventanas especificas de cada rol: SuperAdmin, Administrador, Vigilante,
 * Arrendatario, Propietario y Consejo de Administracion. <br>
 * <b>pre</b> El sistema debe estar correctamente inicializado antes de invocar
 * los metodos de inicio de ventanas. <br>
 * <b>post</b> La ventana de bienvenida queda creada al instanciar la fachada,
 * y las demas ventanas se crean bajo demanda segun el rol autenticado. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class ViewFacade {

    /** Ventana de bienvenida y login del sistema. */
    private VentanaBienvenida ventanaBienvenida;

    /** Ventana principal del rol Super Administrador. */
    private VentanaSuperAdmin ventanaSuperAdmin;

    /** Ventana principal del rol Administrador de conjunto. */
    private VentanaAdmin ventanaAdmin;

    /** Ventana principal del rol Vigilante. */
    private VentanaVigilante ventanaVigilante;

    /** Ventana principal del rol Arrendatario. */
    private VentanaArrendatario ventanaArrendatario;

    /** Ventana principal del rol Propietario. */
    private VentanaPropietario ventanaPropietario;

    /** Ventana principal del rol Consejo de Administracion. */
    private VentanaConsejo ventanaConsejo;

    /**
     * Constructor por defecto. Crea e inicializa la ventana de bienvenida del
     * sistema. <br>
     * <b>pre</b> No hay precondiciones especiales. <br>
     * <b>post</b> La ventana de bienvenida queda instanciada y disponible para
     * ser mostrada. <br>
     */
    public ViewFacade() {
        ventanaBienvenida = new VentanaBienvenida();
    }

    /**
     * Crea e inicializa la ventana del Super Administrador con el username
     * indicado. <br>
     * <b>pre</b> El username debe corresponder a un usuario SUPER_ADMIN
     * autenticado. <br>
     * <b>post</b> La ventana del Super Administrador queda instanciada y se
     * retorna para ser mostrada. <br>
     *
     * @param username Nombre del usuario super administrador. username != null
     * @return VentanaSuperAdmin lista para ser mostrada
     */
    public VentanaSuperAdmin iniciarVentanaSuperAdmin(String username) {
        ventanaSuperAdmin = new VentanaSuperAdmin(username);
        return ventanaSuperAdmin;
    }

    /**
     * Crea e inicializa la ventana del Administrador con el username y el
     * nombre del conjunto que administra. <br>
     * <b>pre</b> El username debe corresponder a un usuario ADMINISTRADOR
     * autenticado. <br>
     * <b>post</b> La ventana del Administrador queda instanciada y se retorna
     * para ser mostrada. <br>
     *
     * @param username        Nombre del usuario administrador. username != null
     * @param nombreConjunto  Nombre del conjunto que administra. nombreConjunto != null
     * @return VentanaAdmin lista para ser mostrada
     */
    public VentanaAdmin iniciarVentanaAdmin(String username, String nombreConjunto) {
        ventanaAdmin = new VentanaAdmin(username, nombreConjunto);
        return ventanaAdmin;
    }

    /**
     * Crea e inicializa la ventana del Vigilante con el username indicado. <br>
     * <b>pre</b> El username debe corresponder a un usuario VIGILANTE
     * autenticado. <br>
     * <b>post</b> La ventana del Vigilante queda instanciada y se retorna para
     * ser mostrada. <br>
     *
     * @param username Nombre del usuario vigilante. username != null
     * @return VentanaVigilante lista para ser mostrada
     */
    public VentanaVigilante iniciarVentanaVigilante(String username) {
        ventanaVigilante = new VentanaVigilante(username);
        return ventanaVigilante;
    }

    /**
     * Crea e inicializa la ventana del Arrendatario con el username y el
     * numero de apartamento correspondiente. <br>
     * <b>pre</b> El username debe corresponder a un usuario ARRENDATARIO
     * autenticado. <br>
     * <b>post</b> La ventana del Arrendatario queda instanciada y se retorna
     * para ser mostrada. <br>
     *
     * @param username          Nombre del usuario arrendatario. username != null
     * @param numeroApartamento Numero del apartamento del arrendatario. numeroApartamento != null
     * @return VentanaArrendatario lista para ser mostrada
     */
    public VentanaArrendatario iniciarVentanaArrendatario(String username, String numeroApartamento) {
        ventanaArrendatario = new VentanaArrendatario(username, numeroApartamento);
        return ventanaArrendatario;
    }

    /**
     * Crea e inicializa la ventana del Propietario con el username y el numero
     * de apartamento correspondiente. <br>
     * <b>pre</b> El username debe corresponder a un usuario PROPIETARIO
     * autenticado. <br>
     * <b>post</b> La ventana del Propietario queda instanciada y se retorna
     * para ser mostrada. <br>
     *
     * @param username          Nombre del usuario propietario. username != null
     * @param numeroApartamento Numero del apartamento del propietario. numeroApartamento != null
     * @return VentanaPropietario lista para ser mostrada
     */
    public VentanaPropietario iniciarVentanaPropietario(String username, String numeroApartamento) {
        ventanaPropietario = new VentanaPropietario(username, numeroApartamento);
        return ventanaPropietario;
    }

    /**
     * Crea e inicializa la ventana del Consejo de Administracion con el
     * username y el numero de apartamento correspondiente. <br>
     * <b>pre</b> El username debe corresponder a un usuario CONSEJO autenticado.
     * <br>
     * <b>post</b> La ventana del Consejo queda instanciada y se retorna para
     * ser mostrada. <br>
     *
     * @param username          Nombre del usuario del consejo. username != null
     * @param numeroApartamento Numero del apartamento del miembro del consejo. numeroApartamento != null
     * @return VentanaConsejo lista para ser mostrada
     */
    public VentanaConsejo iniciarVentanaConsejo(String username, String numeroApartamento) {
        ventanaConsejo = new VentanaConsejo(username, numeroApartamento);
        return ventanaConsejo;
    }

    /**
     * Retorna la ventana de bienvenida del sistema. <br>
     * <b>pre</b> El objeto ViewFacade debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaBienvenida del sistema
     */
    public VentanaBienvenida getVentanaBienvenida() {
        return ventanaBienvenida;
    }

    /**
     * Retorna la ventana del Super Administrador. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaSuperAdmin. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaSuperAdmin, o null si no ha sido inicializada
     */
    public VentanaSuperAdmin getVentanaSuperAdmin() {
        return ventanaSuperAdmin;
    }

    /**
     * Retorna la ventana del Administrador. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaAdmin. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaAdmin, o null si no ha sido inicializada
     */
    public VentanaAdmin getVentanaAdmin() {
        return ventanaAdmin;
    }
    
    /**
     * Retorna la ventana del Vigilante. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaVigilante. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaVigilante, o null si no ha sido inicializada
     */
    public VentanaVigilante getVentanaVigilante() {
        return ventanaVigilante;
    }

    /**
     * Retorna la ventana del Arrendatario. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaArrendatario. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaArrendatario, o null si no ha sido inicializada
     */
    public VentanaArrendatario getVentanaArrendatario() {
        return ventanaArrendatario;
    }

    /**
     * Retorna la ventana del Propietario. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaPropietario. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaPropietario, o null si no ha sido inicializada
     */
    public VentanaPropietario getVentanaPropietario() {
        return ventanaPropietario;
    }

    /**
     * Retorna la ventana del Consejo de Administracion. <br>
     * <b>pre</b> La ventana debe haber sido inicializada con iniciarVentanaConsejo. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return VentanaConsejo, o null si no ha sido inicializada
     */
    public VentanaConsejo getVentanaConsejo() {
        return ventanaConsejo;
    }
}
