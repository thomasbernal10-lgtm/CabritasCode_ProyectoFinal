package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JComboBox;
import co.edu.unbosque.model.Apartamento;
import co.edu.unbosque.model.Arrendatario;
import co.edu.unbosque.model.CampanaAmbiental;
import co.edu.unbosque.model.Conjunto;
import co.edu.unbosque.model.Incidente;
import co.edu.unbosque.model.Mascota;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.Obligacion;
import co.edu.unbosque.model.Pago;
import co.edu.unbosque.model.Parqueadero;
import co.edu.unbosque.model.Propietario;
import co.edu.unbosque.model.RegistroConsumoAgua;
import co.edu.unbosque.model.RegistroConsumoEnergia;
import co.edu.unbosque.model.RegistroReciclaje;
import co.edu.unbosque.model.Torre;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.ZonaComun;
import co.edu.unbosque.model.persistence.DataMapper;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.util.AccountBlockedException;
import co.edu.unbosque.util.AlreadyProcessedException;
import co.edu.unbosque.util.DateConflictException;
import co.edu.unbosque.util.DuplicateValueException;
import co.edu.unbosque.util.EmptyFieldException;
import co.edu.unbosque.util.EntityAlreadyAssignedException;
import co.edu.unbosque.util.EntityBlockedException;
import co.edu.unbosque.util.EntityNotFoundException;
import co.edu.unbosque.util.FileOperationException;
import co.edu.unbosque.util.InactiveUserException;
import co.edu.unbosque.util.IncorrectPasswordException;
import co.edu.unbosque.util.InvalidDateException;
import co.edu.unbosque.util.InvalidFormatException;
import co.edu.unbosque.util.InvalidIndexException;
import co.edu.unbosque.util.InvalidLengthException;
import co.edu.unbosque.util.PastDateException;
import co.edu.unbosque.util.PaymentException;
import co.edu.unbosque.util.UnauthorizedOperationException;
import co.edu.unbosque.util.UserNotFoundException;
import co.edu.unbosque.util.NegativeValueException;
import co.edu.unbosque.util.InsufficientDataException;
import co.edu.unbosque.util.InvalidStateException;
import co.edu.unbosque.util.MaxCapacityException;
import co.edu.unbosque.util.CapacityExceededException;
import co.edu.unbosque.view.ViewFacade;
import co.edu.unbosque.model.Paquete;
import co.edu.unbosque.model.Visitante;
import co.edu.unbosque.model.Vehiculo;
import co.edu.unbosque.model.RegistroVisita;
import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.model.Reserva;
import co.edu.unbosque.model.Residente;
import co.edu.unbosque.model.SolicitudMantenimiento;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Controlador principal del sistema GreenBuilding Manager. Implementa el patron
 * MVC actuando como intermediario entre la capa de modelo y la capa de vista.
 * Gestiona todos los eventos generados por la interfaz grafica, delega la
 * logica de negocio al modelo y actualiza las vistas con los resultados
 * obtenidos. Soporta multiples roles de usuario: SuperAdmin, Administrador,
 * Vigilante, Propietario, Arrendatario y Consejo de Administracion. <b>pre</b>
 * El ModelFacade y el ViewFacade deben poder ser instanciados correctamente al
 * momento de crear el controlador. <br>
 * <b>post</b> El controlador queda listo para gestionar eventos de la interfaz
 * y coordinar las operaciones del sistema. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class Controller implements ActionListener {

	/**
	 * Fachada del modelo que centraliza el acceso a la logica de negocio y
	 * persistencia.
	 */
	private ModelFacade model;

	/**
	 * Fachada de la vista que centraliza el acceso a todas las ventanas del
	 * sistema.
	 */
	private ViewFacade view;

	/** Usuario que tiene sesion activa en el sistema en un momento dado. */
	private Usuario usuarioEnSesion;

	/**
	 * Constructor del controlador. Inicializa las capas de modelo y vista, crea la
	 * carpeta principal de datos y asigna los oyentes de la ventana de bienvenida.
	 * <b>pre</b> Las clases ModelFacade y ViewFacade deben estar disponibles y
	 * correctamente implementadas. <br>
	 * <b>post</b> El controlador queda inicializado con modelo, vista y oyentes de
	 * bienvenida configurados. <br>
	 */
	public Controller() {
		FileHandler.crearCarpetaPrincipal();
		model = new ModelFacade();
		view = new ViewFacade();
		asignarOyentesBienvenida();
	}
	
	public void nuevoMetodo() {
		
	}

	/**
	 * Hace visible la ventana de bienvenida para iniciar la interaccion con el
	 * usuario. <b>pre</b> El controlador debe haber sido inicializado
	 * correctamente. <br>
	 * <b>post</b> La ventana de bienvenida queda visible en pantalla. <br>
	 */
	public void iniciar() {
		view.getVentanaBienvenida().setVisible(true);
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana de bienvenida.
	 * <b>pre</b> La ventana de bienvenida debe estar inicializada en el ViewFacade.
	 * <br>
	 * <b>post</b> Los botones de la ventana de bienvenida quedan asociados a sus
	 * respectivos comandos de accion. <br>
	 */
	public void asignarOyentesBienvenida() {
		view.getVentanaBienvenida().getBtnIniciarSesion().addActionListener(this);
		view.getVentanaBienvenida().getBtnIniciarSesion().setActionCommand("BtnIniciarSesion");
		view.getVentanaBienvenida().getBtnSalir().addActionListener(this);
		view.getVentanaBienvenida().getBtnSalir().setActionCommand("BtnSalir");
		view.getVentanaBienvenida().getBtnEntrarLogin().addActionListener(this);
		view.getVentanaBienvenida().getBtnEntrarLogin().setActionCommand("BtnEntrarLogin");
		view.getVentanaBienvenida().getBtnVolverLogin().addActionListener(this);
		view.getVentanaBienvenida().getBtnVolverLogin().setActionCommand("BtnVolverLogin");
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del SuperAdmin.
	 * Cubre los modulos de conjuntos, administradores, usuarios, torres,
	 * apartamentos, zonas comunes y parqueaderos. <b>pre</b> La ventana del
	 * SuperAdmin debe estar inicializada en el ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana SuperAdmin quedan asociados a sus
	 * respectivos comandos de accion. <br>
	 */
	public void asignarOyentesSuperAdmin() {
		view.getVentanaSuperAdmin().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaSuperAdmin().getBtnCerrarSesion().setActionCommand("SA_CerrarSesion");
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnCrear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnCrear().setActionCommand("SA_CrearConjunto");
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnLimpiar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnLimpiar().setActionCommand("SA_LimpiarConjunto");
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnEliminar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnEliminar().setActionCommand("SA_EliminarConjunto");
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnRefrescar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConjuntos().getBtnRefrescar().setActionCommand("SA_RefrescarConjuntos");
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnCrear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnCrear().setActionCommand("SA_CrearAdmin");
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnLimpiar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnLimpiar().setActionCommand("SA_LimpiarAdmin");
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnBloquear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnBloquear().setActionCommand("SA_BloquearAdmin");
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnDesbloquear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnDesbloquear()
				.setActionCommand("SA_DesbloquearAdmin");
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnRefrescar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaAdministradores().getBtnRefrescar()
				.setActionCommand("SA_RefrescarAdmins");
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnBloquear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnBloquear().setActionCommand("SA_BloquearUsuario");
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnDesbloquear().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnDesbloquear().setActionCommand("SA_DesbloquearUsuario");
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnRefrescar().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaUsuarios().getBtnRefrescar().setActionCommand("SA_RefrescarUsuarios");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearTorre().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearTorre().setActionCommand("SA_CrearTorre");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarTorre().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarTorre()
				.setActionCommand("SA_LimpiarTorre");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarTorre().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarTorre()
				.setActionCommand("SA_EliminarTorre");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarTorre().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarTorre()
				.setActionCommand("SA_RefrescarTorres");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearApto().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearApto().setActionCommand("SA_CrearApto");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarApto().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarApto()
				.setActionCommand("SA_LimpiarApto");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarApto().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarApto()
				.setActionCommand("SA_EliminarApto");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarApto().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarApto()
				.setActionCommand("SA_RefrescarAptos");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearZona().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearZona().setActionCommand("SA_CrearZona");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarZona().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarZona()
				.setActionCommand("SA_LimpiarZona");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarZona().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarZona()
				.setActionCommand("SA_EliminarZona");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarZona().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarZona()
				.setActionCommand("SA_RefrescarZonas");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearParq().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnCrearParq().setActionCommand("SA_CrearParq");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarParq().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnLimpiarParq()
				.setActionCommand("SA_LimpiarParq");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarParq().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnEliminarParq()
				.setActionCommand("SA_EliminarParq");
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarParq().addActionListener(this);
		view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getBtnRefrescarParq()
				.setActionCommand("SA_RefrescarParqs");
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del Administrador.
	 * Cubre los modulos de residentes, zonas, parqueaderos, reservas, operativa,
	 * finanzas, sostenibilidad, reportes, vigilantes y consejo. <b>pre</b> La
	 * ventana del Administrador debe estar inicializada en el ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana Admin quedan asociados a sus
	 * respectivos comandos de accion. <br>
	 */
	public void asignarOyentesAdmin() {
		view.getVentanaAdmin().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaAdmin().getBtnCerrarSesion().setActionCommand("AD_CerrarSesion");
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropCrear().setActionCommand("AD_CrearPropietario");
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropLimpiar().setActionCommand("AD_LimpiarPropietario");
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnPropRefrescar()
				.setActionCommand("AD_RefrescarPropietarios");
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrCrear().setActionCommand("AD_CrearArrendatario");
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrLimpiar().setActionCommand("AD_LimpiarArrendatario");
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnArrRefrescar()
				.setActionCommand("AD_RefrescarArrendatarios");
		view.getVentanaAdmin().getPestanaResidentes().getBtnResCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnResCrear().setActionCommand("AD_CrearResidente");
		view.getVentanaAdmin().getPestanaResidentes().getBtnResLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnResLimpiar().setActionCommand("AD_LimpiarResidente");
		view.getVentanaAdmin().getPestanaResidentes().getBtnResRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnResRefrescar().setActionCommand("AD_RefrescarResidentes");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehCrear().setActionCommand("AD_CrearVehiculo");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehLimpiar().setActionCommand("AD_LimpiarVehiculo");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVehRefrescar().setActionCommand("AD_RefrescarVehiculos");
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasCrear().setActionCommand("AD_CrearMascota");
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasLimpiar().setActionCommand("AD_LimpiarMascota");
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnMasRefrescar().setActionCommand("AD_RefrescarMascotas");
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqCrear().setActionCommand("AD_CrearParqueadero");
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqLimpiar()
				.setActionCommand("AD_LimpiarParqueadero");
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnParqRefrescar()
				.setActionCommand("AD_RefrescarParqueaderos");
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnResCancelar().addActionListener(this);
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnResCancelar().setActionCommand("AD_CancelarReserva");
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnResRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getBtnResRefrescar()
				.setActionCommand("AD_RefrescarReservas");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnVisRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnVisRefrescar()
				.setActionCommand("AD_RefrescarVisitantes");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnPaqRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnPaqRefrescar()
				.setActionCommand("AD_RefrescarPaquetes");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManCrear().setActionCommand("AD_CrearMantenimiento");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManLimpiar()
				.setActionCommand("AD_LimpiarMantenimiento");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManActualizar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManActualizar()
				.setActionCommand("AD_ActualizarMantenimiento");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnManRefrescar()
				.setActionCommand("AD_RefrescarMantenimientos");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncCrear().setActionCommand("AD_CrearIncidente");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncLimpiar().setActionCommand("AD_LimpiarIncidente");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncRefrescar()
				.setActionCommand("AD_RefrescarIncidentes");
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncGestionar().addActionListener(this);
		view.getVentanaAdmin().getPestanaGestionOperativa().getBtnIncGestionar()
				.setActionCommand("AD_GestionarIncidente");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnOblCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnOblCrear()
				.setActionCommand("AD_CrearObligacion");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnOblLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnOblLimpiar()
				.setActionCommand("AD_LimpiarObligacion");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnPagoRegistrar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnPagoRegistrar()
				.setActionCommand("AD_RegistrarPago");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnPagoLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnPagoLimpiar()
				.setActionCommand("AD_LimpiarPago");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnFinRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnFinRefrescar()
				.setActionCommand("AD_RefrescarFinanzas");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnVerEstadoCuenta().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnVerEstadoCuenta()
				.setActionCommand("AD_VerEstadoCuenta");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampCrear().setActionCommand("AD_CrearCampana");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampLimpiar()
				.setActionCommand("AD_LimpiarCampana");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnCampRefrescar()
				.setActionCommand("AD_RefrescarCampanas");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsRegistrar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsRegistrar()
				.setActionCommand("AD_RegistrarConsumo");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsLimpiar()
				.setActionCommand("AD_LimpiarConsumo");
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getBtnConsRefrescar()
				.setActionCommand("AD_RefrescarConsumos");
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnGenerarReporte().addActionListener(this);
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnGenerarReporte().setActionCommand("AD_GenerarReporte");
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnExportarPDF().addActionListener(this);
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnExportarPDF().setActionCommand("AD_ExportarPDF");
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnRefrescarNotif().addActionListener(this);
		view.getVentanaAdmin().getPestanaReportesAdmin().getBtnRefrescarNotif()
				.setActionCommand("AD_RefrescarNotificaciones");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigCrear().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigCrear().setActionCommand("AD_CrearVigilante");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigLimpiar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigLimpiar().setActionCommand("AD_LimpiarVigilante");
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigRefrescar().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnVigRefrescar().setActionCommand("AD_RefrescarVigilantes");
		view.getVentanaAdmin().getPestanaResidentes().getBtnDesignarConsejo().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnDesignarConsejo().setActionCommand("AD_DesignarConsejo");
		view.getVentanaAdmin().getPestanaResidentes().getBtnQuitarConsejo().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnQuitarConsejo().setActionCommand("AD_QuitarConsejo");
		view.getVentanaAdmin().getPestanaResidentes().getBtnRefrescarConsejoPropietarios().addActionListener(this);
		view.getVentanaAdmin().getPestanaResidentes().getBtnRefrescarConsejoPropietarios()
				.setActionCommand("AD_RefrescarConsejoPropietarios");
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del Vigilante.
	 * Cubre los modulos de visitantes, vehiculos y paquetes. <b>pre</b> La ventana
	 * del Vigilante debe estar inicializada en el ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana Vigilante quedan asociados a sus
	 * respectivos comandos de accion. <br>
	 */
	public void asignarOyentesVigilante() {
		view.getVentanaVigilante().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaVigilante().getBtnCerrarSesion().setActionCommand("VIG_CerrarSesion");
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRegistrarEntrada().addActionListener(this);
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRegistrarEntrada()
				.setActionCommand("VIG_RegistrarEntrada");
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRegistrarSalida().addActionListener(this);
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRegistrarSalida()
				.setActionCommand("VIG_RegistrarSalida");
		view.getVentanaVigilante().getPestanaVisitantes().getBtnLimpiar().addActionListener(this);
		view.getVentanaVigilante().getPestanaVisitantes().getBtnLimpiar().setActionCommand("VIG_LimpiarVisitante");
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRefrescar().addActionListener(this);
		view.getVentanaVigilante().getPestanaVisitantes().getBtnRefrescar().setActionCommand("VIG_RefrescarVisitantes");
		view.getVentanaVigilante().getPestanaVehiculos().getBtnConsultarPlaca().addActionListener(this);
		view.getVentanaVigilante().getPestanaVehiculos().getBtnConsultarPlaca().setActionCommand("VIG_ConsultarPlaca");
		view.getVentanaVigilante().getPestanaVehiculos().getBtnRefrescar().addActionListener(this);
		view.getVentanaVigilante().getPestanaVehiculos().getBtnRefrescar().setActionCommand("VIG_RefrescarVehiculos");
		view.getVentanaVigilante().getPestanaPaquetes().getBtnRegistrarPaquete().addActionListener(this);
		view.getVentanaVigilante().getPestanaPaquetes().getBtnRegistrarPaquete()
				.setActionCommand("VIG_RegistrarPaquete");
		view.getVentanaVigilante().getPestanaPaquetes().getBtnLimpiarRegistro().addActionListener(this);
		view.getVentanaVigilante().getPestanaPaquetes().getBtnLimpiarRegistro().setActionCommand("VIG_LimpiarPaquete");
		view.getVentanaVigilante().getPestanaPaquetes().getBtnMarcarEntregado().addActionListener(this);
		view.getVentanaVigilante().getPestanaPaquetes().getBtnMarcarEntregado().setActionCommand("VIG_EntregarPaquete");
		view.getVentanaVigilante().getPestanaPaquetes().getBtnDevolver().addActionListener(this);
		view.getVentanaVigilante().getPestanaPaquetes().getBtnDevolver().setActionCommand("VIG_DevolverPaquete");
		view.getVentanaVigilante().getPestanaPaquetes().getBtnRefrescar().addActionListener(this);
		view.getVentanaVigilante().getPestanaPaquetes().getBtnRefrescar().setActionCommand("VIG_RefrescarPaquetes");
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del Arrendatario.
	 * Cubre los modulos de reservas, apartamento, incidentes y mantenimiento.
	 * <b>pre</b> La ventana del Arrendatario debe estar inicializada en el
	 * ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana Arrendatario quedan asociados a
	 * sus respectivos comandos de accion. <br>
	 */
	public void asignarOyentesArrendatario() {
		view.getVentanaArrendatario().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaArrendatario().getBtnCerrarSesion().setActionCommand("ARR_CerrarSesion");
		view.getVentanaArrendatario().getPestanaReservas().getBtnZonasRefrescar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaReservas().getBtnZonasRefrescar()
				.setActionCommand("ARR_RefrescarZonas");
		view.getVentanaArrendatario().getPestanaReservas().getBtnCrearReserva().addActionListener(this);
		view.getVentanaArrendatario().getPestanaReservas().getBtnCrearReserva().setActionCommand("ARR_CrearReserva");
		view.getVentanaArrendatario().getPestanaReservas().getBtnLimpiarReserva().addActionListener(this);
		view.getVentanaArrendatario().getPestanaReservas().getBtnLimpiarReserva()
				.setActionCommand("ARR_LimpiarReserva");
		view.getVentanaArrendatario().getPestanaReservas().getBtnRefrescarMisReservas().addActionListener(this);
		view.getVentanaArrendatario().getPestanaReservas().getBtnRefrescarMisReservas()
				.setActionCommand("ARR_RefrescarMisReservas");
		view.getVentanaArrendatario().getPestanaReservas().getBtnCancelarReserva().addActionListener(this);
		view.getVentanaArrendatario().getPestanaReservas().getBtnCancelarReserva()
				.setActionCommand("ARR_CancelarReserva");
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarVisitantes().addActionListener(this);
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarVisitantes()
				.setActionCommand("ARR_RefrescarVisitantes");
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarPaquetes().addActionListener(this);
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarPaquetes()
				.setActionCommand("ARR_RefrescarPaquetes");
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarResidentes().addActionListener(this);
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarResidentes()
				.setActionCommand("ARR_RefrescarResidentes");
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarVehiculos().addActionListener(this);
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarVehiculos()
				.setActionCommand("ARR_RefrescarVehiculos");
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarMascotas().addActionListener(this);
		view.getVentanaArrendatario().getPestanaVisitantes().getBtnRefrescarMascotas()
				.setActionCommand("ARR_RefrescarMascotas");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncReportar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncReportar()
				.setActionCommand("ARR_ReportarIncidente");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncLimpiar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncLimpiar().setActionCommand("ARR_LimpiarIncidente");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncRefrescar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnIncRefrescar()
				.setActionCommand("ARR_RefrescarIncidentes");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManCrear().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManCrear().setActionCommand("ARR_CrearMantenimiento");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManLimpiar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManLimpiar()
				.setActionCommand("ARR_LimpiarMantenimiento");
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManRefrescar().addActionListener(this);
		view.getVentanaArrendatario().getPestanaOperativa().getBtnManRefrescar()
				.setActionCommand("ARR_RefrescarMantenimientos");
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del Propietario.
	 * Cubre los modulos de reservas, apartamento, operativa y financieros.
	 * <b>pre</b> La ventana del Propietario debe estar inicializada en el
	 * ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana Propietario quedan asociados a
	 * sus respectivos comandos de accion. <br>
	 */
	public void asignarOyentesPropietario() {
		view.getVentanaPropietario().getBtnCerrarSesion().setActionCommand("PROP_CerrarSesion");
		view.getVentanaPropietario().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaPropietario().getPestanaReservas().getBtnZonasRefrescar()
				.setActionCommand("PROP_RefrescarZonas");
		view.getVentanaPropietario().getPestanaReservas().getBtnZonasRefrescar().addActionListener(this);
		view.getVentanaPropietario().getPestanaReservas().getBtnCrearReserva().setActionCommand("PROP_CrearReserva");
		view.getVentanaPropietario().getPestanaReservas().getBtnCrearReserva().addActionListener(this);
		view.getVentanaPropietario().getPestanaReservas().getBtnLimpiarReserva()
				.setActionCommand("PROP_LimpiarReserva");
		view.getVentanaPropietario().getPestanaReservas().getBtnLimpiarReserva().addActionListener(this);
		view.getVentanaPropietario().getPestanaReservas().getBtnRefrescarMisReservas()
				.setActionCommand("PROP_RefrescarMisReservas");
		view.getVentanaPropietario().getPestanaReservas().getBtnRefrescarMisReservas().addActionListener(this);
		view.getVentanaPropietario().getPestanaReservas().getBtnCancelarReserva()
				.setActionCommand("PROP_CancelarReserva");
		view.getVentanaPropietario().getPestanaReservas().getBtnCancelarReserva().addActionListener(this);
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarVisitantes()
				.setActionCommand("PROP_RefrescarVisitantes");
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarVisitantes().addActionListener(this);
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarPaquetes()
				.setActionCommand("PROP_RefrescarPaquetes");
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarPaquetes().addActionListener(this);
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarResidentes()
				.setActionCommand("PROP_RefrescarResidentes");
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarResidentes().addActionListener(this);
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarVehiculos()
				.setActionCommand("PROP_RefrescarVehiculos");
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarVehiculos().addActionListener(this);
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarMascotas()
				.setActionCommand("PROP_RefrescarMascotas");
		view.getVentanaPropietario().getPestanaApartamento().getBtnRefrescarMascotas().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncReportar()
				.setActionCommand("PROP_ReportarIncidente");
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncReportar().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncLimpiar().setActionCommand("PROP_LimpiarIncidente");
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncLimpiar().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncRefrescar()
				.setActionCommand("PROP_RefrescarIncidentes");
		view.getVentanaPropietario().getPestanaOperativa().getBtnIncRefrescar().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnManCrear().setActionCommand("PROP_CrearMantenimiento");
		view.getVentanaPropietario().getPestanaOperativa().getBtnManCrear().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnManLimpiar()
				.setActionCommand("PROP_LimpiarMantenimiento");
		view.getVentanaPropietario().getPestanaOperativa().getBtnManLimpiar().addActionListener(this);
		view.getVentanaPropietario().getPestanaOperativa().getBtnManRefrescar()
				.setActionCommand("PROP_RefrescarMantenimientos");
		view.getVentanaPropietario().getPestanaOperativa().getBtnManRefrescar().addActionListener(this);
		view.getVentanaPropietario().getPestanaFinancieros().getBtnVerEstadoCuenta()
				.setActionCommand("PROP_VerEstadoCuenta");
		view.getVentanaPropietario().getPestanaFinancieros().getBtnVerEstadoCuenta().addActionListener(this);
		view.getVentanaPropietario().getPestanaFinancieros().getBtnRefrescarFinanzas()
				.setActionCommand("PROP_RefrescarFinanzas");
		view.getVentanaPropietario().getPestanaFinancieros().getBtnRefrescarFinanzas().addActionListener(this);
	}

	/**
	 * Asigna los oyentes de eventos a los botones de la ventana del Consejo de
	 * Administracion. Reutiliza comandos del propietario para funcionalidades
	 * compartidas y agrega comandos exclusivos del consejo como generacion de
	 * reportes y cuotas. <b>pre</b> La ventana del Consejo debe estar inicializada
	 * en el ViewFacade. <br>
	 * <b>post</b> Todos los botones de la ventana Consejo quedan asociados a sus
	 * respectivos comandos de accion. <br>
	 */
	public void asignarOyentesConsejo() {
		view.getVentanaConsejo().getBtnCerrarSesion().setActionCommand("CONS_CerrarSesion");
		view.getVentanaConsejo().getBtnCerrarSesion().addActionListener(this);
		view.getVentanaConsejo().getPestanaReservas().getBtnZonasRefrescar().setActionCommand("PROP_RefrescarZonas");
		view.getVentanaConsejo().getPestanaReservas().getBtnZonasRefrescar().addActionListener(this);
		view.getVentanaConsejo().getPestanaReservas().getBtnCrearReserva().setActionCommand("PROP_CrearReserva");
		view.getVentanaConsejo().getPestanaReservas().getBtnCrearReserva().addActionListener(this);
		view.getVentanaConsejo().getPestanaReservas().getBtnLimpiarReserva().setActionCommand("PROP_LimpiarReserva");
		view.getVentanaConsejo().getPestanaReservas().getBtnLimpiarReserva().addActionListener(this);
		view.getVentanaConsejo().getPestanaReservas().getBtnRefrescarMisReservas()
				.setActionCommand("PROP_RefrescarMisReservas");
		view.getVentanaConsejo().getPestanaReservas().getBtnRefrescarMisReservas().addActionListener(this);
		view.getVentanaConsejo().getPestanaReservas().getBtnCancelarReserva().setActionCommand("PROP_CancelarReserva");
		view.getVentanaConsejo().getPestanaReservas().getBtnCancelarReserva().addActionListener(this);
		view.getVentanaConsejo().getPestanaApartamento().getBtnRefrescarVisitantes()
				.setActionCommand("PROP_RefrescarVisitantes");
		view.getVentanaConsejo().getPestanaApartamento().getBtnRefrescarVisitantes().addActionListener(this);
		view.getVentanaConsejo().getPestanaApartamento().getBtnRefrescarPaquetes()
				.setActionCommand("PROP_RefrescarPaquetes");
		view.getVentanaConsejo().getPestanaApartamento().getBtnRefrescarPaquetes().addActionListener(this);
		view.getVentanaConsejo().getPestanaFinancieros().getBtnVerEstadoCuenta()
				.setActionCommand("PROP_VerEstadoCuenta");
		view.getVentanaConsejo().getPestanaFinancieros().getBtnVerEstadoCuenta().addActionListener(this);
		view.getVentanaConsejo().getPestanaFinancieros().getBtnRefrescarFinanzas()
				.setActionCommand("PROP_RefrescarFinanzas");
		view.getVentanaConsejo().getPestanaFinancieros().getBtnRefrescarFinanzas().addActionListener(this);
		view.getVentanaConsejo().getPestanaConsejo().getBtnGenerarReporte().setActionCommand("CONS_GenerarReporte");
		view.getVentanaConsejo().getPestanaConsejo().getBtnGenerarReporte().addActionListener(this);
		view.getVentanaConsejo().getPestanaConsejo().getBtnGenerarCuotas().setActionCommand("CONS_GenerarCuotas");
		view.getVentanaConsejo().getPestanaConsejo().getBtnGenerarCuotas().addActionListener(this);
		view.getVentanaConsejo().getPestanaConsejo().getBtnRefrescarMorosos().setActionCommand("CONS_RefrescarMorosos");
		view.getVentanaConsejo().getPestanaConsejo().getBtnRefrescarMorosos().addActionListener(this);
		view.getVentanaConsejo().getPestanaConsejo().getBtnRefrescarIncidentes()
				.setActionCommand("CONS_RefrescarIncidentes");
		view.getVentanaConsejo().getPestanaConsejo().getBtnRefrescarIncidentes().addActionListener(this);
	}

	/**
	 * Metodo principal de manejo de eventos. Recibe todos los eventos de accion
	 * generados por los componentes de la interfaz grafica y los despacha al bloque
	 * de logica correspondiente segun el comando de accion. <b>pre</b> El evento
	 * recibido debe provenir de un componente con ActionCommand registrado en
	 * alguno de los metodos asignarOyentes. <br>
	 * <b>post</b> La accion correspondiente al comando es ejecutada, actualizando
	 * el modelo y la vista segun corresponda. <br>
	 *
	 * @param e Evento de accion generado por la interfaz grafica. e != null
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String origen = e.getActionCommand();
		switch (origen) {
		case "BtnIniciarSesion": {
			view.getVentanaBienvenida().limpiarLogin();
			view.getVentanaBienvenida().mostrarPantallaLogin();
			break;
		}
		case "BtnSalir": {
			view.getVentanaBienvenida().dispose();
			break;
		}
		case "BtnEntrarLogin": {
			String usuario = view.getVentanaBienvenida().getLoginUsuario();
			String contrasena = view.getVentanaBienvenida().getLoginContrasena();
			try {
				if (usuario.isEmpty())
					throw new EmptyFieldException("El usuario es obligatorio.");
				if (contrasena.isEmpty())
					throw new EmptyFieldException("La contraseña es obligatoria.");
				if (contrasena.length() < 6)
					throw new InvalidLengthException("La contraseña debe tener al menos 6 caracteres.");
				boolean usuarioExiste = false;
				boolean usuarioInactivo = false;
				for (Usuario us : model.obtenerTodosLosUsuarios()) {
					if (us.getUsername().equals(usuario)) {
						usuarioExiste = true;
						if (!us.isActivo())
							usuarioInactivo = true;
						break;
					}
				}
				if (usuarioExiste && usuarioInactivo)
					throw new InactiveUserException("Esta cuenta esta inactiva. Contacte al administrador.");
				if (usuarioExiste && !usuarioInactivo) {
					for (Usuario us : model.obtenerTodosLosUsuarios()) {
						if (us.getUsername().equals(usuario) && !us.isActivo()) {
							throw new AccountBlockedException("Esta cuenta esta bloqueada. Contacte al administrador.");
						}
					}
				}
				Usuario u = model.login(usuario, contrasena);
				if (u == null) {
					if (usuarioExiste)
						throw new IncorrectPasswordException("La contraseña ingresada es incorrecta.");
					else
						throw new UserNotFoundException("No existe un usuario con ese nombre de usuario.");
				}
				usuarioEnSesion = u;
				view.getVentanaBienvenida().limpiarLogin();
				view.getVentanaBienvenida().setVisible(false);
				abrirVentanaPorRol(u);
			} catch (EmptyFieldException | InvalidLengthException | InactiveUserException | AccountBlockedException
					| IncorrectPasswordException | UserNotFoundException ex) {
				view.getVentanaBienvenida().mostrarError(ex.getMessage());
			}
			break;
		}
		case "BtnVolverLogin": {
			view.getVentanaBienvenida().limpiarLogin();
			view.getVentanaBienvenida().mostrarPantallaPrincipal();
			break;
		}
		case "SA_CerrarSesion": {
			view.getVentanaSuperAdmin().dispose();
			view.getVentanaBienvenida().mostrarPantallaPrincipal();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "SA_CrearConjunto": {
			String nombre = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtNombre().getText().trim();
			String direccion = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtDireccion().getText().trim();
			String ciudad = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtCiudad().getText().trim();
			String telefono = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtTelefono().getText().trim();
			String correo = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtCorreo().getText().trim();
			try {
				if (nombre.isEmpty())
					throw new EmptyFieldException("El nombre del conjunto es obligatorio.");
				if (direccion.isEmpty())
					throw new EmptyFieldException("La direccion es obligatoria.");
				if (ciudad.isEmpty())
					throw new EmptyFieldException("La ciudad es obligatoria.");
				if (!correo.isEmpty())
					validarCorreo(correo);
				validarTelefono(telefono);
				Conjunto c = new Conjunto();
				c.setNombre(nombre);
				c.setDireccion(direccion);
				c.setCiudad(ciudad);
				c.setTelefono(telefono);
				c.setCorreo(correo);
				model.registrarConjunto(c);
				view.getVentanaSuperAdmin().getPestanaConjuntos().setMensaje("Conjunto creado correctamente.", false);
				view.getVentanaSuperAdmin().getPestanaConjuntos().limpiar();
				refrescarListaConjuntos();
				recargarCombosConjunto();
				actualizarComboConjuntos();
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException ex) {
				view.getVentanaSuperAdmin().getPestanaConjuntos().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarConjunto": {
			view.getVentanaSuperAdmin().getPestanaConjuntos().limpiar();
			break;
		}
		case "SA_EliminarConjunto": {
			String indexStr = view.getVentanaSuperAdmin().getPestanaConjuntos().getTxtIndex().getText().trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				boolean ok = model.eliminarConjunto(index);
				if (ok) {
					view.getVentanaSuperAdmin().getPestanaConjuntos().setMensaje("Conjunto eliminado.", false);
					refrescarListaConjuntos();
					recargarCombosConjunto();
					actualizarComboConjuntos();
				} else {
					throw new EntityNotFoundException("No se encontro un conjunto con ese indice.");
				}
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaConjuntos().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarConjuntos": {
			refrescarListaConjuntos();
			actualizarComboConjuntos();
			break;
		}
		case "SA_CrearAdmin": {
			String nombre = view.getVentanaSuperAdmin().getPestanaAdministradores().getTxtNombre().getText().trim();
			String correo = view.getVentanaSuperAdmin().getPestanaAdministradores().getTxtCorreo().getText().trim();
			String idConjunto = view.getVentanaSuperAdmin().getPestanaAdministradores().getIdConjuntoSeleccionado();
			try {
				validarNombre(nombre);
				validarCorreo(correo);
				if (idConjunto == null || idConjunto.isEmpty())
					throw new EntityNotFoundException("Debes seleccionar un conjunto.");
				model.crearAdministrador(nombre, correo, idConjunto);
				view.getVentanaSuperAdmin().getPestanaAdministradores()
						.setMensaje("Admin creado. Credenciales enviadas al correo.", false);
				view.getVentanaSuperAdmin().getPestanaAdministradores().limpiar();
				refrescarListaAdmins();
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException
					| EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaAdministradores().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarAdmin": {
			view.getVentanaSuperAdmin().getPestanaAdministradores().limpiar();
			break;
		}
		case "SA_BloquearAdmin": {
			String username = view.getVentanaSuperAdmin().getPestanaAdministradores().getTxtUsernameAccion().getText()
					.trim();
			try {
				if (username.isEmpty())
					throw new EmptyFieldException("Ingresa el username.");
				boolean ok = model.bloquearUsuario(username);
				if (!ok)
					throw new UserNotFoundException("No se encontro un usuario con ese username.");
				view.getVentanaSuperAdmin().getPestanaAdministradores().setMensajeAccion("Usuario bloqueado.", false);
				refrescarListaAdmins();
			} catch (EmptyFieldException | UserNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaAdministradores().setMensajeAccion(ex.getMessage(), true);
			}
			break;
		}
		case "SA_DesbloquearAdmin": {
			String username = view.getVentanaSuperAdmin().getPestanaAdministradores().getTxtUsernameAccion().getText()
					.trim();
			try {
				if (username.isEmpty())
					throw new EmptyFieldException("Ingresa el username.");
				boolean ok = model.desbloquearUsuario(username);
				if (!ok)
					throw new UserNotFoundException("No se encontro un usuario con ese username.");
				view.getVentanaSuperAdmin().getPestanaAdministradores().setMensajeAccion("Usuario desbloqueado.",
						false);
				refrescarListaAdmins();
			} catch (EmptyFieldException | UserNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaAdministradores().setMensajeAccion(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarAdmins": {
			refrescarListaAdmins();
			break;
		}
		case "SA_BloquearUsuario": {
			String username = view.getVentanaSuperAdmin().getPestanaUsuarios().getTxtUsernameAccion().getText().trim();
			try {
				if (username.isEmpty())
					throw new EmptyFieldException("Ingresa el username.");
				boolean ok = model.bloquearUsuario(username);
				if (!ok)
					throw new UserNotFoundException("No se encontro un usuario con ese username.");
				view.getVentanaSuperAdmin().getPestanaUsuarios().setMensaje("Usuario bloqueado.", false);
				refrescarListaUsuarios();
			} catch (EmptyFieldException | UserNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaUsuarios().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "SA_DesbloquearUsuario": {
			String username = view.getVentanaSuperAdmin().getPestanaUsuarios().getTxtUsernameAccion().getText().trim();
			try {
				if (username.isEmpty())
					throw new EmptyFieldException("Ingresa el username.");
				boolean ok = model.desbloquearUsuario(username);
				if (!ok)
					throw new UserNotFoundException("No se encontro un usuario con ese username.");
				view.getVentanaSuperAdmin().getPestanaUsuarios().setMensaje("Usuario desbloqueado.", false);
				refrescarListaUsuarios();
			} catch (EmptyFieldException | UserNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaUsuarios().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarUsuarios": {
			refrescarListaUsuarios();
			break;
		}
		case "SA_CrearTorre": {
			String idConjuntoSel = obtenerIdConjuntoSeleccionado(view.getVentanaSuperAdmin()
					.getPestanaConfigurarConjunto().getComboConjuntoTorre().getSelectedItem());
			String nombreTorre = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtTorreNombre()
					.getText().trim();
			String numPisosStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtTorreNumPisos()
					.getText().trim();
			try {
				if (idConjuntoSel == null)
					throw new EntityNotFoundException("Selecciona un conjunto.");
				if (nombreTorre.isEmpty())
					throw new EmptyFieldException("El nombre de la torre es obligatorio.");
				if (numPisosStr.isEmpty())
					throw new EmptyFieldException("El numero de pisos es obligatorio.");
				int numPisos;
				try {
					numPisos = Integer.parseInt(numPisosStr);
				} catch (Exception ex) {
					throw new InvalidFormatException("El numero de pisos debe ser un entero.");
				}
				if (numPisos <= 0)
					throw new InvalidFormatException("El numero de pisos debe ser mayor a 0.");
				if (numPisos > 50)
					throw new InvalidFormatException("El numero de pisos no puede superar 50.");
				String idTorre = "TOR-" + model.getContadorIds();
				model.incrementarContadorIds();
				Torre t = new Torre();
				t.setId(idTorre);
				t.setNombre(nombreTorre);
				t.setNumeroPisos(numPisos);
				model.registrarTorre(t, idConjuntoSel);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto()
						.setMensajeTorre("Torre creada correctamente.", false);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarTorre();
				refrescarListaTorres();
				recargarComboTorres();
			} catch (EntityNotFoundException | EmptyFieldException | InvalidFormatException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeTorre(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarTorre": {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarTorre();
			break;
		}
		case "SA_EliminarTorre": {
			String indexStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtTorreIndex().getText()
					.trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				boolean ok = model.eliminarTorre(index);
				if (!ok)
					throw new EntityNotFoundException("No se encontro una torre con ese indice.");
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeTorre("Torre eliminada.", false);
				refrescarListaTorres();
				recargarComboTorres();
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeTorre(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarTorres": {
			refrescarListaTorres();
			break;
		}
		case "SA_CrearApto": {
			String numeroStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtAptoNumero().getText()
					.trim();
			String idTorreSeleccionada = obtenerIdTorreSeleccionada(
					view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboTorreApto().getSelectedItem());
			String estado = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboAptoEstado()
					.getSelectedItem().toString();
			try {
				if (numeroStr.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				if (idTorreSeleccionada == null)
					throw new EmptyFieldException("Seleccione una torre.");
				int piso;
				try {
					piso = (Integer.parseInt(numeroStr) % 1000) / 100;
				} catch (NumberFormatException e2) {
					throw new InvalidFormatException("El numero de apartamento debe ser numerico (ej: 401).");
				}
				for (Apartamento a : model.obtenerTodosLosApartamentos()) {
					if (a.getNumero().equals(numeroStr) && a.getTorre() != null
							&& a.getTorre().getId().equals(idTorreSeleccionada))
						throw new DuplicateValueException("Ya existe un apartamento con ese numero en esta torre.");
				}
				String idApto = "APT-" + model.getContadorIds();
				model.incrementarContadorIds();
				Apartamento apto = new Apartamento();
				apto.setId(idApto);
				apto.setNumero(numeroStr);
				apto.setPiso(piso);
				apto.setEstado(estado);
				model.registrarApartamento(apto, idTorreSeleccionada);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeApto("Apartamento creado.", false);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarApto();
				refrescarListaAptos();
			} catch (EmptyFieldException | InvalidFormatException | DuplicateValueException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeApto(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarApto": {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarApto();
			break;
		}
		case "SA_EliminarApto": {
			String indexStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtAptoIndex().getText()
					.trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				boolean ok = model.eliminarApartamento(index);
				if (!ok)
					throw new EntityNotFoundException("No se encontro un apartamento con ese indice.");
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeApto("Apartamento eliminado.",
						false);
				refrescarListaAptos();
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeApto(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarAptos": {
			refrescarListaAptos();
			break;
		}
		case "SA_CrearZona": {
			String idConjSeleccionado = obtenerIdConjuntoSeleccionado(view.getVentanaSuperAdmin()
					.getPestanaConfigurarConjunto().getComboConjuntoZona().getSelectedItem());
			String tipo = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboZonaTipo()
					.getSelectedItem().toString();
			String estado = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboZonaEstado()
					.getSelectedItem().toString();
			String aforoStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtZonaAforo().getText()
					.trim();
			String costoStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtZonaCosto().getText()
					.trim();
			String horaAp = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtZonaHoraApertura()
					.getText().trim();
			String horaCi = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtZonaHoraCierre().getText()
					.trim();
			boolean requiere = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getChkZonaRequiereReserva()
					.isSelected();
			try {
				if (idConjSeleccionado == null) {
					throw new EmptyFieldException("Seleccione un conjunto.");
				}
				if (aforoStr.isEmpty()) {
					throw new EmptyFieldException("El aforo es obligatorio.");
				}
				int aforo;
				try {
					aforo = Integer.parseInt(aforoStr);
					if (aforo <= 0) {
						throw new InvalidFormatException("El aforo debe ser mayor a cero.");
					}
				} catch (NumberFormatException e2) {
					throw new InvalidFormatException("El aforo debe ser un numero entero.");
				}
				double costo = 0;
				if (!costoStr.isEmpty()) {
					try {
						costo = Double.parseDouble(costoStr);
						if (costo < 0) {
							throw new InvalidFormatException("El costo no puede ser negativo.");
						}
					} catch (NumberFormatException e2) {
						throw new InvalidFormatException("El costo debe ser un numero valido.");
					}
				}
				String idZona = "ZC-" + model.getContadorIds();
				model.incrementarContadorIds();
				Conjunto conj = model.buscarConjuntoPorId(idConjSeleccionado);
				ZonaComun zona = new ZonaComun();
				zona.setId(idZona);
				zona.setNombre(tipo.replace("_", " "));
				zona.setTipo(tipo);
				zona.setEstado(estado);
				zona.setConjunto(conj);
				zona.setAforoMaximo(aforo);
				zona.setCostoReserva(costo);
				try {
					if (horaAp.isEmpty()) {
						zona.setHoraApertura(null);
					} else {
						zona.setHoraApertura(LocalTime.parse(horaAp));
					}
				} catch (Exception e2) {
					throw new InvalidFormatException("Hora apertura invalida. Use formato HH:mm (ej: 08:00).");
				}
				try {
					if (horaCi.isEmpty()) {
						zona.setHoraCierre(null);
					} else {
						zona.setHoraCierre(LocalTime.parse(horaCi));
					}
				} catch (Exception e2) {
					throw new InvalidFormatException("Hora cierre invalida. Use formato HH:mm (ej: 22:00).");
				}
				zona.setRequiereReserva(requiere);
				model.registrarZonaComun(zona);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeZona("Zona comun creada.", false);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarZona();
				refrescarListaZonas();
			} catch (EmptyFieldException | InvalidFormatException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeZona(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarZona": {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarZona();
			break;
		}
		case "SA_EliminarZona": {
			String indexStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtZonaIndex().getText()
					.trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				boolean ok = model.eliminarZonaComun(index);
				if (!ok)
					throw new EntityNotFoundException("No se encontro una zona con ese indice.");
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeZona("Zona comun eliminada.",
						false);
				refrescarListaZonas();
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeZona(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarZonas": {
			refrescarListaZonas();
			break;
		}
		case "SA_CrearParq": {
			String idConjSeleccionado = obtenerIdConjuntoSeleccionado(view.getVentanaSuperAdmin()
					.getPestanaConfigurarConjunto().getComboConjuntoParq().getSelectedItem());
			String numeroParq = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtParqNumero().getText()
					.trim();
			String tipo = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboParqTipo()
					.getSelectedItem().toString();
			String estado = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboParqEstado()
					.getSelectedItem().toString();
			try {
				if (idConjSeleccionado == null)
					throw new EmptyFieldException("Seleccione un conjunto.");
				if (numeroParq.isEmpty())
					throw new EmptyFieldException("El numero de parqueadero es obligatorio.");
				for (Parqueadero p : model.obtenerTodosLosParqueaderos()) {
					if (p.getNumero().equals(numeroParq) && p.getConjunto() != null
							&& p.getConjunto().getId().equals(idConjSeleccionado))
						throw new DuplicateValueException("Ya existe un parqueadero con ese numero en este conjunto.");
				}
				String idParq = "PQ-" + model.getContadorIds();
				model.incrementarContadorIds();
				Parqueadero parq = new Parqueadero();
				parq.setId(idParq);
				parq.setNumero(numeroParq);
				parq.setTipo(tipo);
				parq.setEstado(estado);
				model.registrarParqueadero(parq, idConjSeleccionado);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeParq("Parqueadero creado.", false);
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarParq();
				refrescarListaParqs();
			} catch (EmptyFieldException | DuplicateValueException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeParq(ex.getMessage(), true);
			}
			break;
		}
		case "SA_LimpiarParq": {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().limpiarParq();
			break;
		}
		case "SA_EliminarParq": {
			String indexStr = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getTxtParqIndex().getText()
					.trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				boolean ok = model.eliminarParqueadero(index);
				if (!ok)
					throw new EntityNotFoundException("No se encontro un parqueadero con ese indice.");
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeParq("Parqueadero eliminado.",
						false);
				refrescarListaParqs();
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException ex) {
				view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().setMensajeParq(ex.getMessage(), true);
			}
			break;
		}
		case "SA_RefrescarParqs": {
			refrescarListaParqs();
			break;
		}
		case "AD_CerrarSesion": {
			view.getVentanaAdmin().dispose();
			view.getVentanaBienvenida().mostrarPantallaPrincipal();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "AD_CrearPropietario": {
			String nombre = view.getVentanaAdmin().getPestanaResidentes().getTxtPropNombre().getText().trim();
			String cedula = view.getVentanaAdmin().getPestanaResidentes().getTxtPropCedula().getText().trim();
			String correo = view.getVentanaAdmin().getPestanaResidentes().getTxtPropCorreo().getText().trim();
			String telefono = view.getVentanaAdmin().getPestanaResidentes().getTxtPropTelefono().getText().trim();
			String contactoEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtPropContactoEmergencia()
					.getText().trim();
			String telEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtPropTelefonoEmergencia()
					.getText().trim();
			String direccion = view.getVentanaAdmin().getPestanaResidentes().getTxtPropDireccionCorrespondencia()
					.getText().trim();
			boolean esResidente = view.getVentanaAdmin().getPestanaResidentes().getChkPropEsResidente().isSelected();
			String idApto = view.getVentanaAdmin().getPestanaResidentes().getTxtPropApto().getText().trim();
			try {
				validarNombre(nombre);
				validarCedula(cedula);
				if (!correo.isEmpty())
					validarCorreo(correo);
				validarTelefono(telefono);
				validarTelefono(telEmergencia);
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				boolean cedulaDuplicada = false;
				for (Propietario pp : model.obtenerTodosLosPropietarios()) {
					if (pp.getCedula().equals(cedula)) {
						cedulaDuplicada = true;
						break;
					}
				}
				if (!cedulaDuplicada) {
					for (Arrendatario aa : model.obtenerTodosLosArrendatarios()) {
						if (aa.getCedula().equals(cedula)) {
							cedulaDuplicada = true;
							break;
						}
					}
				}
				if (cedulaDuplicada)
					throw new DuplicateValueException("Ya existe una persona registrada con esa cedula.");
				Apartamento aptoResuelto = model.buscarApartamentoPorNumero(idApto);
				if (aptoResuelto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				if (aptoResuelto.getPropietario() != null)
					throw new EntityAlreadyAssignedException("Este apartamento ya tiene un propietario asignado.");
				String idAptoResuelto = aptoResuelto.getId();
				Propietario p = new Propietario();
				p.setId("PROP-" + model.getContadorIds());
				model.incrementarContadorIds();
				p.setNombre(nombre);
				p.setCedula(cedula);
				p.setCorreo(correo);
				p.setTelefono(telefono);
				p.setContactoEmergencia(contactoEmergencia);
				p.setTelefonoEmergencia(telEmergencia);
				p.setDireccionCorrespondencia(direccion);
				p.setEsResidente(esResidente);
				p.setResponsablePago(true);
				boolean ok = model.registrarPropietario(p, idAptoResuelto);
				if (ok) {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeProp("Propietario registrado.", false);
					view.getVentanaAdmin().getPestanaResidentes().limpiarProp();
					refrescarAdminResidentes();
				} else {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeProp("No se pudo registrar el propietario.",
							true);
				}
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException | DuplicateValueException
					| EntityNotFoundException | EntityAlreadyAssignedException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeProp(ex.getMessage(), true);
			}
			break;
		}
		case "AD_RefrescarPropietarios": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaPropLista().setText(model.mostrarPropietarios());
			break;
		}
		case "AD_LimpiarPropietario": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarProp();
			break;
		}
		case "AD_CrearArrendatario": {
			String nombre = view.getVentanaAdmin().getPestanaResidentes().getTxtArrNombre().getText().trim();
			String cedula = view.getVentanaAdmin().getPestanaResidentes().getTxtArrCedula().getText().trim();
			String correo = view.getVentanaAdmin().getPestanaResidentes().getTxtArrCorreo().getText().trim();
			String telefono = view.getVentanaAdmin().getPestanaResidentes().getTxtArrTelefono().getText().trim();
			String contactoEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtArrContactoEmergencia()
					.getText().trim();
			String telEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtArrTelefonoEmergencia().getText()
					.trim();
			String fechaInicioStr = view.getVentanaAdmin().getPestanaResidentes().getTxtArrFechaInicio().getText()
					.trim();
			String fechaFinStr = view.getVentanaAdmin().getPestanaResidentes().getTxtArrFechaFin().getText().trim();
			String idApto = view.getVentanaAdmin().getPestanaResidentes().getTxtArrApto().getText().trim();
			try {
				validarNombre(nombre);
				validarCedula(cedula);
				if (!correo.isEmpty())
					validarCorreo(correo);
				validarTelefono(telefono);
				validarTelefono(telEmergencia);
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				boolean cedulaDuplicada = false;
				for (Arrendatario aa : model.obtenerTodosLosArrendatarios()) {
					if (aa.getCedula().equals(cedula)) {
						cedulaDuplicada = true;
						break;
					}
				}
				if (!cedulaDuplicada) {
					for (Propietario pp : model.obtenerTodosLosPropietarios()) {
						if (pp.getCedula().equals(cedula)) {
							cedulaDuplicada = true;
							break;
						}
					}
				}
				if (cedulaDuplicada)
					throw new DuplicateValueException("Ya existe una persona registrada con esa cedula.");
				Apartamento aptoResuelto = model.buscarApartamentoPorNumero(idApto);
				if (aptoResuelto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				if (aptoResuelto.getArrendatario() != null)
					throw new EntityAlreadyAssignedException("Este apartamento ya tiene un arrendatario asignado.");
				Arrendatario a = new Arrendatario();
				a.setId("ARR-" + model.getContadorIds());
				model.incrementarContadorIds();
				a.setNombre(nombre);
				a.setCedula(cedula);
				a.setCorreo(correo);
				a.setTelefono(telefono);
				a.setContactoEmergencia(contactoEmergencia);
				a.setTelefonoEmergencia(telEmergencia);
				if (!fechaInicioStr.isEmpty()) {
					try {
						a.setFechaInicioContrato(LocalDate.parse(fechaInicioStr));
					} catch (Exception e2) {
						throw new InvalidDateException("Fecha inicio invalida. Use AAAA-MM-DD.");
					}
				}
				if (!fechaFinStr.isEmpty()) {
					try {
						LocalDate fin = LocalDate.parse(fechaFinStr);
						if (a.getFechaInicioContrato() != null && !fin.isAfter(a.getFechaInicioContrato()))
							throw new DateConflictException("La fecha fin debe ser posterior a la fecha inicio.");
						a.setFechaFinContrato(fin);
					} catch (DateConflictException e2) {
						throw e2;
					} catch (Exception e2) {
						throw new InvalidDateException("Fecha fin invalida. Use AAAA-MM-DD.");
					}
				}
				boolean ok = model.registrarArrendatario(a, aptoResuelto.getId());
				if (ok) {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeArr("Arrendatario registrado.", false);
					view.getVentanaAdmin().getPestanaResidentes().limpiarArr();
					refrescarAdminResidentes();
				} else {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeArr("No se pudo registrar el arrendatario.",
							true);
				}
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException | DuplicateValueException
					| EntityNotFoundException | EntityAlreadyAssignedException | InvalidDateException
					| DateConflictException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeArr(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarArrendatario": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarArr();
			break;
		}
		case "AD_RefrescarArrendatarios": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaArrLista().setText(model.mostrarArrendatarios());
			break;
		}
		case "AD_CrearResidente": {
			String nombre = view.getVentanaAdmin().getPestanaResidentes().getTxtResNombre().getText().trim();
			String cedula = view.getVentanaAdmin().getPestanaResidentes().getTxtResCedula().getText().trim();
			String correo = view.getVentanaAdmin().getPestanaResidentes().getTxtResCorreo().getText().trim();
			String telefono = view.getVentanaAdmin().getPestanaResidentes().getTxtResTelefono().getText().trim();
			String contactoEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtResContactoEmergencia()
					.getText().trim();
			String telEmergencia = view.getVentanaAdmin().getPestanaResidentes().getTxtResTelefonoEmergencia().getText()
					.trim();
			String idApto = view.getVentanaAdmin().getPestanaResidentes().getTxtResApto().getText().trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaResidentes().getComboResTipo().getSelectedItem();
			try {
				validarNombre(nombre);
				validarCedula(cedula);
				if (!correo.isEmpty())
					validarCorreo(correo);
				validarTelefono(telefono);
				validarTelefono(telEmergencia);
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				Apartamento aptoResuelto = model.buscarApartamentoPorNumero(idApto);
				if (aptoResuelto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				Residente r = new Residente();
				r.setId("RES-" + model.getContadorIds());
				model.incrementarContadorIds();
				r.setNombre(nombre);
				r.setCedula(cedula);
				r.setCorreo(correo);
				r.setTelefono(telefono);
				r.setContactoEmergencia(contactoEmergencia);
				r.setTelefonoEmergencia(telEmergencia);
				r.setTipo(tipo);
				boolean ok = model.registrarResidente(r, aptoResuelto.getId());
				if (ok) {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeRes("Residente registrado.", false);
					view.getVentanaAdmin().getPestanaResidentes().limpiarRes();
					refrescarAdminResidentes();
				} else {
					view.getVentanaAdmin().getPestanaResidentes().setMensajeRes("No se pudo registrar el residente.",
							true);
				}
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException
					| EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeRes(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarResidente": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarRes();
			break;
		}
		case "AD_RefrescarResidentes": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaResLista().setText(model.mostrarResidentes());
			break;
		}
		case "AD_CrearVigilante": {
			String nombre = view.getVentanaAdmin().getPestanaResidentes().getTxtVigNombre().getText().trim();
			String correo = view.getVentanaAdmin().getPestanaResidentes().getTxtVigCorreo().getText().trim();
			String idConjunto = usuarioEnSesion.getConjunto().getId();
			try {
				validarNombre(nombre);
				validarCorreo(correo);
				model.crearVigilante(nombre, correo, idConjunto);
				view.getVentanaAdmin().getPestanaResidentes()
						.setMensajeVig("Vigilante creado. Credenciales enviadas al correo.", false);
				view.getVentanaAdmin().getPestanaResidentes().limpiarVig();
				view.getVentanaAdmin().getPestanaResidentes().getAreaVigLista().setText(mostrarVigilantes());
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeVig(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarVigilante": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarVig();
			break;
		}
		case "AD_RefrescarVigilantes": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaVigLista().setText(mostrarVigilantes());
			break;
		}
		case "AD_CrearVehiculo": {
			String placa = view.getVentanaAdmin().getPestanaResidentes().getTxtVehPlaca().getText().trim()
					.toUpperCase();
			String marca = view.getVentanaAdmin().getPestanaResidentes().getTxtVehMarca().getText().trim();
			String modelo = view.getVentanaAdmin().getPestanaResidentes().getTxtVehModelo().getText().trim();
			String color = view.getVentanaAdmin().getPestanaResidentes().getTxtVehColor().getText().trim();
			String idApto = view.getVentanaAdmin().getPestanaResidentes().getTxtVehApto().getText().trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaResidentes().getComboVehTipo().getSelectedItem();
			try {
				validarPlaca(placa);
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				boolean placaDuplicada = false;
				for (Vehiculo vv : model.obtenerTodosLosVehiculos()) {
					if (vv.getPlaca().equalsIgnoreCase(placa)) {
						placaDuplicada = true;
						break;
					}
				}
				if (placaDuplicada)
					throw new DuplicateValueException("Ya existe un vehiculo registrado con la placa: " + placa);
				Apartamento aptoResuelto = model.buscarApartamentoPorNumero(idApto);
				if (aptoResuelto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				Vehiculo v = new Vehiculo();
				v.setPlaca(placa);
				v.setMarca(marca);
				v.setModelo(modelo);
				v.setColor(color);
				v.setTipo(tipo);
				model.registrarVehiculo(v, aptoResuelto.getId());
				view.getVentanaAdmin().getPestanaResidentes().setMensajeVeh("Vehiculo registrado.", false);
				view.getVentanaAdmin().getPestanaResidentes().limpiarVeh();
				refrescarAdminResidentes();
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException | DuplicateValueException
					| EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeVeh(ex.getMessage(), true);
			}
			break;
		}
		case "AD_CrearMascota": {
			String nombre = view.getVentanaAdmin().getPestanaResidentes().getTxtMasNombre().getText().trim();
			String especie = view.getVentanaAdmin().getPestanaResidentes().getTxtMasEspecie().getText().trim();
			String raza = view.getVentanaAdmin().getPestanaResidentes().getTxtMasRaza().getText().trim();
			String color = view.getVentanaAdmin().getPestanaResidentes().getTxtMasColor().getText().trim();
			String fechaVacStr = view.getVentanaAdmin().getPestanaResidentes().getTxtMasFechaVacunacion().getText()
					.trim();
			boolean vacunasAlDia = view.getVentanaAdmin().getPestanaResidentes().getChkMasVacunasAlDia().isSelected();
			String idApto = view.getVentanaAdmin().getPestanaResidentes().getTxtMasApto().getText().trim();
			try {
				if (nombre.isEmpty())
					throw new EmptyFieldException("El nombre de la mascota es obligatorio.");
				if (vacunasAlDia && fechaVacStr.isEmpty())
					throw new InsufficientDataException(
							"Si las vacunas estan al dia, debe ingresar la fecha de vacunacion.");
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				Apartamento aptoResuelto = model.buscarApartamentoPorNumero(idApto);
				if (aptoResuelto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				Mascota m = new Mascota();
				m.setNombre(nombre);
				m.setEspecie(especie);
				m.setRaza(raza);
				m.setColor(color);
				m.setVacunasAlDia(vacunasAlDia);
				if (!fechaVacStr.isEmpty()) {
					try {
						LocalDate fv = LocalDate.parse(fechaVacStr);
						if (fv.isAfter(LocalDate.now()))
							throw new InvalidDateException("La fecha de vacunacion no puede ser futura.");
						m.setFechaVacunacion(fv);
					} catch (InvalidDateException e2) {
						throw e2;
					} catch (Exception e2) {
						throw new InvalidDateException("Formato de fecha invalido. Use AAAA-MM-DD.");
					}
				}
				model.registrarMascota(m, aptoResuelto.getId());
				view.getVentanaAdmin().getPestanaResidentes().setMensajeMas("Mascota registrada.", false);
				view.getVentanaAdmin().getPestanaResidentes().limpiarMas();
				refrescarAdminResidentes();
			} catch (EmptyFieldException | EntityNotFoundException | InvalidDateException
					| InsufficientDataException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeMas(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarMascota": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarMas();
			break;
		}
		case "AD_RefrescarMascotas": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaMasLista().setText(model.mostrarMascotas());
			break;
		}
		case "AD_CrearParqueadero": {
			String codigo = view.getVentanaAdmin().getPestanaZonasParqueaderos().getTxtParqCodigo().getText().trim();
			String idConjunto = usuarioEnSesion.getConjunto().getId();
			String tipo = view.getVentanaAdmin().getPestanaZonasParqueaderos().getComboParqTipo().getSelectedItem()
					.toString();
			try {
				if (codigo.isEmpty())
					throw new EmptyFieldException("El numero de parqueadero es obligatorio.");
				if (idConjunto.isEmpty())
					throw new EmptyFieldException("El ID del conjunto es obligatorio.");
				if (model.buscarConjuntoPorId(idConjunto) == null)
					throw new EntityNotFoundException("No se encontro un conjunto con ese ID: " + idConjunto);
				Parqueadero parq = new Parqueadero();
				parq.setNumero(codigo);
				parq.setTipo(tipo);
				parq.setEstado("DISPONIBLE");
				model.registrarParqueadero(parq, idConjunto);
				view.getVentanaAdmin().getPestanaZonasParqueaderos().setMensajeParq("Parqueadero registrado.", false);
				view.getVentanaAdmin().getPestanaZonasParqueaderos().limpiarParq();
				view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaParqLista()
						.setText(model.mostrarParqueaderos());
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaZonasParqueaderos().setMensajeParq(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarVehiculo": {
			view.getVentanaAdmin().getPestanaResidentes().limpiarVeh();
			break;
		}
		case "AD_RefrescarVehiculos": {
			view.getVentanaAdmin().getPestanaResidentes().getAreaVehLista().setText(model.mostrarVehiculos());
			break;
		}
		case "AD_LimpiarParqueadero": {
			view.getVentanaAdmin().getPestanaZonasParqueaderos().limpiarParq();
			break;
		}
		case "AD_RefrescarParqueaderos": {
			view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaParqLista()
					.setText(model.mostrarParqueaderos());
			break;
		}
		case "AD_CancelarReserva": {
			String idStr = view.getVentanaAdmin().getPestanaZonasParqueaderos().getTxtResIdCancelar().getText().trim();
			String motivo = view.getVentanaAdmin().getPestanaZonasParqueaderos().getTxtResMotivoCancelar().getText()
					.trim();
			try {
				validarIndice(idStr);
				int idx = Integer.parseInt(idStr);
				if (idx >= model.obtenerTodasLasReservas().size()) {
					throw new EntityNotFoundException("No se encontro una reserva con ese indice.");
				}
				Reserva rsv = model.obtenerTodasLasReservas().get(idx);
				if ("CANCELADA".equals(rsv.getEstado()) || "COMPLETADA".equals(rsv.getEstado())) {
					throw new AlreadyProcessedException(
							"Esta reserva ya fue " + rsv.getEstado().toLowerCase() + " y no puede cancelarse.");
				}
				String motivoFinal;
				if (motivo.isEmpty()) {
					motivoFinal = "Cancelada por administrador";
				} else {
					motivoFinal = motivo;
				}
				boolean ok = model.cancelarReserva(idx, motivoFinal);
				if (ok) {
					view.getVentanaAdmin().getPestanaZonasParqueaderos().setMensajeRes("Reserva cancelada.", false);
					view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaResLista()
							.setText(model.mostrarReservas());
				} else {
					view.getVentanaAdmin().getPestanaZonasParqueaderos().setMensajeRes("No se pudo cancelar.", true);
				}
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException
					| AlreadyProcessedException ex) {
				view.getVentanaAdmin().getPestanaZonasParqueaderos().setMensajeRes(ex.getMessage(), true);
			}
			break;
		}
		case "AD_RefrescarReservas": {
			view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaResLista().setText(model.mostrarReservas());
			break;
		}
		case "AD_RefrescarVisitantes": {
			view.getVentanaAdmin().getPestanaGestionOperativa().getAreaVisLista()
					.setText(model.mostrarRegistrosVisita());
			break;
		}
		case "AD_EntregarPaquete": {
			view.getVentanaAdmin().getPestanaGestionOperativa()
					.setMensajePaq("La entrega de paquetes se gestiona desde la cuenta del vigilante.", true);
			break;
		}
		case "AD_CrearMantenimiento": {
			String desc = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtManDescripcion().getText().trim();
			String ubic = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtManUbicacion().getText().trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboManTipo()
					.getSelectedItem();
			String prioridad = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboManPrioridad()
					.getSelectedItem();
			try {
				if (desc.isEmpty())
					throw new EmptyFieldException("La descripcion de la solicitud es obligatoria.");
				SolicitudMantenimiento sol = new SolicitudMantenimiento();
				sol.setDescripcion(desc);
				sol.setObservaciones(ubic);
				sol.setTipo(tipo);
				sol.setPrioridad(prioridad);
				model.crearSolicitudMantenimiento(sol);
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeMan("Solicitud creada.", false);
				view.getVentanaAdmin().getPestanaGestionOperativa().limpiarMan();
				view.getVentanaAdmin().getPestanaGestionOperativa().getAreaManLista()
						.setText(model.mostrarSolicitudesMantenimiento());
			} catch (EmptyFieldException ex) {
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeMan(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarMantenimiento": {
			view.getVentanaAdmin().getPestanaGestionOperativa().limpiarMan();
			break;
		}
		case "AD_ActualizarMantenimiento": {
			String idStr = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtManIdActualizar().getText().trim();
			String nuevoEstado = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboManEstadoUpdate()
					.getSelectedItem();
			String tecnico = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtManTecnico().getText().trim();
			try {
				if (nuevoEstado == null || nuevoEstado.trim().isEmpty())
					throw new InvalidStateException("Debe seleccionar un estado valido para actualizar.");
				validarIndice(idStr);
				int idx = Integer.parseInt(idStr);
				boolean ok = false;
				if (!tecnico.isEmpty()) {
					model.asignarTecnico(idx, tecnico);
					ok = true;
				}
				if ("EN_PROGRESO".equals(nuevoEstado)) {
					ok = model.iniciarSolicitudMantenimiento(idx);
				} else if ("COMPLETADA".equals(nuevoEstado)) {
					ok = model.cerrarSolicitudMantenimiento(idx, "Completada por admin");
				} else if ("RECHAZADA".equals(nuevoEstado)) {
					ok = model.rechazarSolicitudMantenimiento(idx, "Rechazada por admin");
				}
				if (ok) {
					view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeMan("Solicitud actualizada.", false);
					view.getVentanaAdmin().getPestanaGestionOperativa().getAreaManLista()
							.setText(model.mostrarSolicitudesMantenimiento());
				} else {
					view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeMan("No se pudo actualizar.", true);
				}
			} catch (EmptyFieldException | InvalidIndexException | InvalidStateException ex) {
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeMan(ex.getMessage(), true);
			}
			break;
		}
		case "AD_CrearIncidente": {
			String desc = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtIncDescripcion().getText().trim();
			String idApto = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtIncApto().getText().trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboIncTipo()
					.getSelectedItem();
			String gravedad = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboIncGravedad()
					.getSelectedItem();
			try {
				if (desc.isEmpty()) {
					throw new EmptyFieldException("La descripcion del incidente es obligatoria.");
				}
				Apartamento aptoInc;
				if (idApto.isEmpty()) {
					aptoInc = null;
				} else {
					aptoInc = model.buscarApartamentoPorNumero(idApto);
				}
				Incidente inc = new Incidente();
				inc.setDescripcion(desc);
				inc.setApartamentoInvolucrado(aptoInc);
				inc.setTipo(tipo);
				inc.setGravedad(gravedad);
				if ("Multa".equals(gravedad)) {
					inc.setGeneraMulta(true);
				} else {
					inc.setGeneraMulta(false);
				}
				model.reportarIncidente(inc);
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeInc("Incidente reportado.", false);
				view.getVentanaAdmin().getPestanaGestionOperativa().limpiarInc();
				view.getVentanaAdmin().getPestanaGestionOperativa().getAreaIncLista()
						.setText(model.mostrarIncidentes());
			} catch (EmptyFieldException ex) {
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeInc(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarIncidente": {
			view.getVentanaAdmin().getPestanaGestionOperativa().limpiarInc();
			break;
		}
		case "AD_RefrescarIncidentes": {
			view.getVentanaAdmin().getPestanaGestionOperativa().getAreaIncLista().setText(model.mostrarIncidentes());
			break;
		}
		case "AD_GestionarIncidente": {
			String idxStr = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtIncIdGestionar().getText().trim();
			String resolucion = view.getVentanaAdmin().getPestanaGestionOperativa().getTxtIncResolucion().getText()
					.trim();
			String accion = (String) view.getVentanaAdmin().getPestanaGestionOperativa().getComboIncAccion()
					.getSelectedItem();
			try {
				if (accion == null)
					throw new InvalidStateException("Debe seleccionar una accion para gestionar el incidente.");
				if ((accion.equals("RESUELTO") || accion.equals("ARCHIVADO")) && resolucion.isEmpty())
					throw new InsufficientDataException(
							"Debe ingresar una resolucion para marcar el incidente como " + accion + ".");
				validarIndice(idxStr);
				int idx = Integer.parseInt(idxStr);
				boolean ok = false;
				switch (accion) {
				case "EN_REVISION":
					ok = model.ponerIncidenteEnRevision(idx);
					break;
				case "RESUELTO":
					String resolucionFinal;
					if (resolucion.isEmpty()) {
						resolucionFinal = "Resuelto por admin";
					} else {
						resolucionFinal = resolucion;
					}
					ok = model.resolverIncidente(idx, resolucionFinal);
					break;
				case "ARCHIVADO":
					ok = model.archivarIncidente(idx);
					break;
				}
				if (ok) {
					view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeIncGestion("Incidente actualizado.",
							false);
					view.getVentanaAdmin().getPestanaGestionOperativa().getAreaIncLista()
							.setText(model.mostrarIncidentes());
				} else {
					view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeIncGestion("No se pudo actualizar.",
							true);
				}
			} catch (EmptyFieldException | InvalidIndexException | InvalidStateException
					| InsufficientDataException ex) {
				view.getVentanaAdmin().getPestanaGestionOperativa().setMensajeIncGestion(ex.getMessage(), true);
			}
			break;
		}
		case "AD_CrearObligacion": {
			String idApto = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtOblApto().getText().trim();
			String concepto = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtOblConcepto().getText()
					.trim();
			String montoStr = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtOblMonto().getText()
					.trim();
			String fechaVence = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtOblFechaVence()
					.getText().trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getComboOblTipo()
					.getSelectedItem();
			try {
				if (idApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				if (concepto.isEmpty())
					throw new EmptyFieldException("El concepto es obligatorio.");
				validarMonto(montoStr);
				Apartamento aptoObl = model.buscarApartamentoPorNumero(idApto);
				if (aptoObl == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + idApto);
				double monto = Double.parseDouble(montoStr);
				Obligacion obl = new Obligacion();
				obl.setApartamento(aptoObl);
				obl.setConcepto(concepto);
				obl.setMonto(monto);
				if (!fechaVence.isEmpty()) {
					try {
						LocalDate fv = LocalDate.parse(fechaVence);
						if (fv.isBefore(LocalDate.now()))
							throw new PastDateException("La fecha de vencimiento no puede ser anterior a hoy.");
						obl.setFechaLimite(fv);
					} catch (PastDateException e2) {
						throw e2;
					} catch (Exception e2) {
						throw new InvalidDateException("Formato de fecha invalido. Use AAAA-MM-DD.");
					}
				} else {
					obl.setFechaLimite(LocalDate.now().plusMonths(1));
				}
				obl.setTipo(tipo);
				obl.setEstado("PENDIENTE");
				model.registrarObligacion(obl);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeObl("Obligacion creada.", false);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarObl();
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaFinLista()
						.setText(model.mostrarObligaciones() + "\n" + model.mostrarPagos());
			} catch (EmptyFieldException | InvalidFormatException | EntityNotFoundException | PastDateException
					| InvalidDateException ex) {
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeObl(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarObligacion": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarObl();
			break;
		}
		case "AD_RegistrarPago": {
			String numeroApto = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtPagoApto().getText()
					.trim();
			String montoStr = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtPagoMonto().getText()
					.trim();
			try {
				if (numeroApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				validarMonto(montoStr);
				double montoPago = Double.parseDouble(montoStr);
				if (montoPago < 0)
					throw new NegativeValueException("El monto del pago no puede ser un valor negativo.");
				Apartamento apto = model.buscarApartamentoPorNumero(numeroApto);
				if (apto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + numeroApto);
				boolean tieneObligaciones = model.obtenerObligacionesPendientes(apto.getId()).size() > 0;
				if (!tieneObligaciones)
					throw new PaymentException("Este apartamento no tiene obligaciones pendientes.");
				double monto = Double.parseDouble(montoStr);
				Pago pago = new Pago();
				pago.setMonto(monto);
				pago.setMedioPago("EFECTIVO");
				pago.setFechaPago(LocalDate.now());
				String resultado = model.registrarPago(pago, apto.getId());
				if ("OK".equals(resultado)) {
					view.getVentanaAdmin().getPestanaFinanzasSostenibilidad()
							.setMensajePago("Pago registrado correctamente.", false);
					view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarPago();
					view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaEstadoCuenta()
							.setText(model.obtenerEstadoCuenta(apto.getId()));
					refrescarAdminFinanzas();
				} else {
					throw new PaymentException(resultado);
				}
			} catch (EmptyFieldException | InvalidFormatException | EntityNotFoundException | PaymentException
					| NegativeValueException ex) {
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajePago(ex.getMessage(), true);
			}
			break;
		}
		case "AD_VerEstadoCuenta": {
			String numeroApto = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtPagoApto().getText()
					.trim();
			try {
				if (numeroApto.isEmpty())
					throw new EmptyFieldException("Ingresa el numero de apartamento.");
				Apartamento apto = model.buscarApartamentoPorNumero(numeroApto);
				if (apto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + numeroApto);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaEstadoCuenta()
						.setText(model.obtenerEstadoCuenta(apto.getId()));
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajePago(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarPago": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarPago();
			break;
		}
		case "AD_RefrescarFinanzas": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaFinLista()
					.setText(model.mostrarObligaciones() + "\n" + model.mostrarPagos());
			break;
		}
		case "AD_CrearCampana": {
			String nombre = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtCampNombre().getText()
					.trim();
			String desc = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtCampDescripcion().getText()
					.trim();
			String fIni = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtCampFechaInicio().getText()
					.trim();
			String fFin = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtCampFechaFin().getText()
					.trim();
			try {
				if (nombre.isEmpty())
					throw new EmptyFieldException("El nombre de la campaña es obligatorio.");
				CampanaAmbiental camp = new CampanaAmbiental();
				camp.setNombre(nombre);
				camp.setDescripcion(desc);
				camp.setEstado("PROGRAMADA");
				LocalDate inicio = LocalDate.now();
				LocalDate fin = LocalDate.now().plusMonths(1);
				if (!fIni.isEmpty()) {
					try {
						inicio = LocalDate.parse(fIni);
					} catch (Exception e2) {
						throw new InvalidDateException("Formato de fecha inicio invalido. Use AAAA-MM-DD.");
					}
				}
				if (!fFin.isEmpty()) {
					try {
						fin = LocalDate.parse(fFin);
					} catch (Exception e2) {
						throw new InvalidDateException("Formato de fecha fin invalido. Use AAAA-MM-DD.");
					}
				}
				if (!fin.isAfter(inicio))
					throw new DateConflictException("La fecha fin debe ser posterior a la fecha inicio.");
				camp.setFechaInicio(inicio);
				camp.setFechaFin(fin);
				model.crearCampana(camp);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeCamp("Campaña creada.", false);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarCamp();
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaCampLista()
						.setText(model.mostrarCampanas());
			} catch (EmptyFieldException | InvalidDateException | DateConflictException ex) {
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeCamp(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarCampana": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarCamp();
			break;
		}
		case "AD_RefrescarCampanas": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaCampLista()
					.setText(model.mostrarCampanas());
			break;
		}
		case "AD_RegistrarConsumo": {
			String fecha = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtConsFecha().getText().trim();
			String valorStr = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtConsValor().getText()
					.trim();
			String obs = view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getTxtConsObservacion().getText()
					.trim();
			String tipo = (String) view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getComboCONSTipo()
					.getSelectedItem();
			try {
				if (fecha.isEmpty()) {
					throw new EmptyFieldException("La fecha es obligatoria.");
				}
				validarMonto(valorStr);
				LocalDate fechaConsumo;
				try {
					fechaConsumo = LocalDate.parse(fecha);
				} catch (Exception e2) {
					throw new InvalidDateException("Formato de fecha invalido. Use AAAA-MM-DD.");
				}
				double valor = Double.parseDouble(valorStr);
				String registradoPor;
				if (obs.isEmpty()) {
					registradoPor = "Admin";
				} else {
					registradoPor = obs;
				}
				if ("Agua".equals(tipo)) {
					RegistroConsumoAgua rca = new RegistroConsumoAgua();
					rca.setFechaRegistro(fechaConsumo);
					rca.setConsumoMtCubico(valor);
					rca.setRegistradoPor(registradoPor);
					model.registrarConsumoAgua(rca);
				} else if ("Energia".equals(tipo)) {
					RegistroConsumoEnergia rce = new RegistroConsumoEnergia();
					rce.setFechaRegistro(fechaConsumo);
					rce.setConsumoKwh(valor);
					rce.setRegistradoPor(registradoPor);
					model.registrarConsumoEnergia(rce);
				} else {
					RegistroReciclaje rr = new RegistroReciclaje();
					rr.setFechaRegistro(fechaConsumo);
					rr.setTotalKg(valor);
					rr.setRegistradoPor(registradoPor);
					model.registrarReciclaje(rr);
				}
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeCons("Consumo registrado.", false);
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarCons();
				refrescarAdminFinanzas();
			} catch (EmptyFieldException | InvalidFormatException | InvalidDateException ex) {
				view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().setMensajeCons(ex.getMessage(), true);
			}
			break;
		}
		case "AD_LimpiarConsumo": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().limpiarCons();
			break;
		}
		case "AD_RefrescarConsumos": {
			view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaConsLista()
					.setText(model.mostrarRegistrosAgua() + "\n" + model.mostrarRegistrosEnergia() + "\n"
							+ model.mostrarRegistrosReciclaje());
			break;
		}
		case "AD_GenerarReporte": {
			String seleccion = (String) view.getVentanaAdmin().getPestanaReportesAdmin().getComboTipoReporte()
					.getSelectedItem();
			try {
				String tipo = mapearTipoReporte(seleccion);
				String idConj = "";
				if (usuarioEnSesion != null && usuarioEnSesion.getConjunto() != null) {
					idConj = usuarioEnSesion.getConjunto().getId();
				}
				Reporte rep = model.generarReporte(tipo, idConj, "Admin");
				if (rep == null) {
					throw new EntityNotFoundException("No se pudo generar el reporte.");
				}
				view.getVentanaAdmin().getPestanaReportesAdmin()
						.setMensajeReporte("Reporte generado y exportado a PDF.", false);
				view.getVentanaAdmin().getPestanaReportesAdmin().getAreaReporte().setText(rep.getContenido());
			} catch (EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaReportesAdmin().setMensajeReporte(ex.getMessage(), true);
			}
			break;
		}
		case "AD_ExportarPDF": {
			try {
				int lastIdx = model.obtenerTodosLosReportes().size() - 1;
				if (lastIdx < 0)
					throw new EntityNotFoundException("No hay reportes generados para exportar.");
				try {
					model.exportarReportePDF(lastIdx);
				} catch (Exception ex) {
					throw new FileOperationException(
							"Error al exportar el PDF. Verifique permisos de escritura: " + ex.getMessage());
				}
				view.getVentanaAdmin().getPestanaReportesAdmin().setMensajeReporte("PDF exportado correctamente.",
						false);
			} catch (EntityNotFoundException | FileOperationException ex) {
				view.getVentanaAdmin().getPestanaReportesAdmin().setMensajeReporte(ex.getMessage(), true);
			}
			break;
		}
		case "AD_RefrescarNotificaciones": {
			view.getVentanaAdmin().getPestanaReportesAdmin().getAreaNotificaciones()
					.setText(model.mostrarNotificaciones());
			break;
		}
		case "AD_DesignarConsejo": {
			String idProp = view.getVentanaAdmin().getPestanaResidentes().getTxtConsejoIdPropietario().getText().trim();
			try {
				if (idProp.isEmpty())
					throw new EmptyFieldException("Ingrese el ID del propietario.");
				boolean ok = model.designarComoConsejo(idProp);
				if (!ok)
					throw new EntityNotFoundException("No se encontro el propietario con ID: " + idProp);
				view.getVentanaAdmin().getPestanaResidentes().setMensajeConsejo("Propietario designado como Consejo.",
						false);
				refrescarListaPropietariosConsejo();
				view.getVentanaAdmin().getPestanaResidentes().getTxtConsejoIdPropietario().setText("");
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeConsejo(ex.getMessage(), true);
			}
			break;
		}
		case "AD_QuitarConsejo": {
			String idProp = view.getVentanaAdmin().getPestanaResidentes().getTxtConsejoIdPropietario().getText().trim();
			try {
				if (idProp.isEmpty())
					throw new EmptyFieldException("Ingrese el ID del propietario.");
				boolean ok = model.quitarDeConsejo(idProp);
				if (!ok)
					throw new EntityNotFoundException("No se encontro el propietario con ID: " + idProp);
				view.getVentanaAdmin().getPestanaResidentes().setMensajeConsejo("Propietario removido del Consejo.",
						false);
				refrescarListaPropietariosConsejo();
				view.getVentanaAdmin().getPestanaResidentes().getTxtConsejoIdPropietario().setText("");
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaAdmin().getPestanaResidentes().setMensajeConsejo(ex.getMessage(), true);
			}
			break;
		}
		case "AD_RefrescarConsejoPropietarios": {
			refrescarListaPropietariosConsejo();
			break;
		}
		case "VIG_CerrarSesion": {
			view.getVentanaVigilante().dispose();
			view.getVentanaBienvenida().mostrarPantallaPrincipal();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "VIG_RegistrarEntrada": {
			String cedula = view.getVentanaVigilante().getPestanaVisitantes().getTxtCedula().getText().trim();
			String nombre = view.getVentanaVigilante().getPestanaVisitantes().getTxtNombre().getText().trim();
			String telefono = view.getVentanaVigilante().getPestanaVisitantes().getTxtTelefono().getText().trim();
			String aptoDestino = view.getVentanaVigilante().getPestanaVisitantes().getTxtApartamentoDestino().getText()
					.trim();
			try {
				validarCedula(cedula);
				if (nombre.isEmpty())
					throw new EmptyFieldException("El nombre del visitante es obligatorio.");
				if (!nombre
						.matches("[a-zA-Z\u00f1\u00d1\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da ]+"))
					throw new InvalidFormatException("El nombre no puede contener numeros ni caracteres especiales.");
				validarTelefono(telefono);
				if (aptoDestino.isEmpty())
					throw new EmptyFieldException("El apartamento destino es obligatorio.");
				Apartamento apto = model.getApartamentoDAO().buscarPorNumero(aptoDestino);
				if (apto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + aptoDestino);
				boolean yaAdentro = false;
				for (RegistroVisita rv : model.obtenerVisitasActivas()) {
					if (rv.getVisitante() != null && rv.getVisitante().getCedula().equals(cedula)) {
						yaAdentro = true;
						break;
					}
				}
				if (yaAdentro)
					throw new EntityAlreadyAssignedException("Ya existe una visita activa registrada con esa cedula.");
				Visitante v = new Visitante();
				v.setId("VIS-" + model.getContadorIds());
				model.incrementarContadorIds();
				v.setNombre(nombre);
				v.setCedula(cedula);
				v.setTelefono(telefono);
				String tipo = (String) view.getVentanaVigilante().getPestanaVisitantes().getComboTipoVisitante()
						.getSelectedItem();
				v.setTipo(tipo);
				v.setApartamentoDestino(apto);
				v.setAutorizadoPor(usuarioEnSesion.getUsername());
				v.setFechaAutorizacion(LocalDateTime.now());
				v.setActivo(true);
				model.registrarEntradaVisitante(v, apto.getId());
				view.getVentanaVigilante().getPestanaVisitantes().setMensaje("Entrada registrada correctamente.",
						false);
				view.getVentanaVigilante().getPestanaVisitantes().limpiar();
				refrescarVisitantes();
			} catch (EmptyFieldException | InvalidFormatException | InvalidLengthException | EntityNotFoundException
					| EntityAlreadyAssignedException ex) {
				view.getVentanaVigilante().getPestanaVisitantes().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "VIG_RegistrarSalida": {
			String cedula = view.getVentanaVigilante().getPestanaVisitantes().getTxtCedula().getText().trim();
			try {
				if (cedula.isEmpty())
					throw new EmptyFieldException("Ingresa la cedula del visitante.");
				boolean ok = model.registrarSalidaVisitante(cedula);
				if (!ok)
					throw new EntityNotFoundException("No se encontro visita activa con cedula: " + cedula);
				view.getVentanaVigilante().getPestanaVisitantes().setMensaje("Salida registrada correctamente.", false);
				view.getVentanaVigilante().getPestanaVisitantes().limpiar();
				refrescarVisitasActivas();
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaVigilante().getPestanaVisitantes().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "VIG_LimpiarVisitante": {
			view.getVentanaVigilante().getPestanaVisitantes().limpiar();
			break;
		}
		case "VIG_RefrescarVisitantes": {
			refrescarVisitasActivas();
			refrescarTodosVisitantes();
			break;
		}
		case "VIG_ConsultarPlaca": {
			String placa = view.getVentanaVigilante().getPestanaVehiculos().getTxtConsultarPlaca().getText().trim()
					.toUpperCase();
			try {
				if (placa.isEmpty()) {
					throw new EmptyFieldException("Ingrese una placa para consultar.");
				}
				Vehiculo vehiculo = model.getVehiculoDAO().buscarPorPlaca(placa);
				if (vehiculo == null) {
					view.getVentanaVigilante().getPestanaVehiculos().setResultadoConsulta(
							"NO REGISTRADO - Puede ser visitante, domiciliario o proveedor.", false);
				} else if (vehiculo.isTieneRestriccion()) {
					String aptoR;
					if (vehiculo.getApartamento() != null) {
						aptoR = vehiculo.getApartamento().getNumero();
					} else {
						aptoR = "-";
					}
					view.getVentanaVigilante().getPestanaVehiculos()
							.setResultadoConsulta("RESTRINGIDO - Vehiculo con restriccion. Apto: " + aptoR, false);
				} else if (!vehiculo.isAutorizado()) {
					view.getVentanaVigilante().getPestanaVehiculos()
							.setResultadoConsulta("NO AUTORIZADO - Vehiculo no habilitado.", false);
				} else {
					String apto;
					if (vehiculo.getApartamento() != null) {
						apto = vehiculo.getApartamento().getNumero();
					} else {
						apto = "N/A";
					}
					view.getVentanaVigilante().getPestanaVehiculos().setResultadoConsulta(
							"AUTORIZADO - Apto: " + apto + " | Tipo: " + vehiculo.getTipo(), true);
				}
			} catch (EmptyFieldException ex) {
				view.getVentanaVigilante().getPestanaVehiculos().setResultadoConsulta(ex.getMessage(), false);
			}
			break;
		}
		case "VIG_RefrescarVehiculos": {
			refrescarVehiculos();
			break;
		}
		case "VIG_RegistrarPaquete": {
			String descripcion = view.getVentanaVigilante().getPestanaPaquetes().getTxtDescripcion().getText().trim();
			String remitente = view.getVentanaVigilante().getPestanaPaquetes().getTxtRemitente().getText().trim();
			String numeroApto = view.getVentanaVigilante().getPestanaPaquetes().getTxtNumeroApartamento().getText()
					.trim();
			try {
				if (descripcion.isEmpty())
					throw new EmptyFieldException("La descripcion del paquete es obligatoria.");
				if (numeroApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				Apartamento apto = model.getApartamentoDAO().buscarPorNumero(numeroApto);
				if (apto == null)
					throw new EntityNotFoundException("No se encontro un apartamento con el numero: " + numeroApto);
				Paquete p = new Paquete();
				p.setId("PAQ-" + model.getContadorIds());
				model.incrementarContadorIds();
				p.setDescripcion(descripcion);
				p.setRemitente(remitente);
				p.setApartamentoDestino(apto);
				p.setVigilanteRecibio(usuarioEnSesion.getUsername());
				model.registrarLlegadaPaquete(p);
				view.getVentanaVigilante().getPestanaPaquetes()
						.setMensajeRegistro("Paquete registrado. Notificacion enviada.", false);
				view.getVentanaVigilante().getPestanaPaquetes().limpiarRegistro();
				refrescarPaquetes();
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaVigilante().getPestanaPaquetes().setMensajeRegistro(ex.getMessage(), true);
			}
			break;
		}
		case "VIG_LimpiarPaquete": {
			view.getVentanaVigilante().getPestanaPaquetes().limpiarRegistro();
			break;
		}
		case "VIG_EntregarPaquete": {
			String indexStr = view.getVentanaVigilante().getPestanaPaquetes().getTxtIndexEntrega().getText().trim();
			String persona = view.getVentanaVigilante().getPestanaPaquetes().getTxtPersonaRecibio().getText().trim();
			try {
				validarIndice(indexStr);
				if (persona.isEmpty())
					throw new EmptyFieldException("El nombre de quien recibio es obligatorio.");
				int index = Integer.parseInt(indexStr);
				if (index >= model.getPaqueteDAO().getListaPaquetes().size())
					throw new EntityNotFoundException("No se encontro un paquete con ese indice.");
				Paquete pq = model.getPaqueteDAO().getListaPaquetes().get(index);
				if ("ENTREGADO".equals(pq.getEstado()) || "DEVUELTO".equals(pq.getEstado()))
					throw new AlreadyProcessedException("Este paquete ya fue " + pq.getEstado().toLowerCase() + ".");
				boolean ok = model.marcarPaqueteEntregado(index, persona);
				if (ok) {
					view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega("Paquete marcado como entregado.",
							false);
					view.getVentanaVigilante().getPestanaPaquetes().limpiarEntrega();
					refrescarPaquetesPendientes();
					refrescarTodosPaquetes();
				} else {
					view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega("No se pudo procesar la entrega.",
							true);
				}
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException
					| AlreadyProcessedException ex) {
				view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega(ex.getMessage(), true);
			}
			break;
		}
		case "VIG_DevolverPaquete": {
			String indexStr = view.getVentanaVigilante().getPestanaPaquetes().getTxtIndexEntrega().getText().trim();
			try {
				validarIndice(indexStr);
				int index = Integer.parseInt(indexStr);
				if (index >= model.getPaqueteDAO().getListaPaquetes().size())
					throw new EntityNotFoundException("No se encontro un paquete con ese indice.");
				Paquete pq = model.getPaqueteDAO().getListaPaquetes().get(index);
				if ("ENTREGADO".equals(pq.getEstado()) || "DEVUELTO".equals(pq.getEstado()))
					throw new AlreadyProcessedException("Este paquete ya fue " + pq.getEstado().toLowerCase() + ".");
				boolean ok = model.devolverPaquete(index);
				if (ok) {
					view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega("Paquete marcado como devuelto.",
							false);
					view.getVentanaVigilante().getPestanaPaquetes().limpiarEntrega();
					refrescarPaquetesPendientes();
					refrescarTodosPaquetes();
				} else {
					view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega("No se pudo devolver el paquete.",
							true);
				}
			} catch (EmptyFieldException | InvalidIndexException | EntityNotFoundException
					| AlreadyProcessedException ex) {
				view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega(ex.getMessage(), true);
			}
			break;
		}
		case "VIG_RefrescarPaquetes": {
			refrescarPaquetesPendientes();
			refrescarTodosPaquetes();
			break;
		}
		case "VIG_MarcarEntregado": {
			String numeroApto = view.getVentanaVigilante().getPestanaPaquetes().getTxtNumeroAptoEntrega().getText()
					.trim();
			String personaRecibio = view.getVentanaVigilante().getPestanaPaquetes().getTxtPersonaRecibio().getText()
					.trim();
			try {
				if (numeroApto.isEmpty())
					throw new EmptyFieldException("El numero de apartamento es obligatorio.");
				if (personaRecibio.isEmpty())
					throw new EmptyFieldException("Ingrese el nombre de quien recibio el paquete.");
				int index = -1;
				for (int i = 0; i < model.getPaqueteDAO().getListaPaquetes().size(); i++) {
					Paquete p = model.getPaqueteDAO().getListaPaquetes().get(i);
					if (p.getApartamentoDestino() != null && p.getApartamentoDestino().getNumero().equals(numeroApto)
							&& p.getEstado().equals("RECIBIDO")) {
						index = i;
						break;
					}
				}
				if (index == -1)
					throw new EntityNotFoundException("No hay paquetes pendientes para el apartamento: " + numeroApto);
				Paquete p = model.getPaqueteDAO().getListaPaquetes().get(index);
				p.setEstado("ENTREGADO");
				p.setFechaEntrega(LocalDateTime.now());
				p.setPersonaRecibio(personaRecibio);
				model.getPaqueteDAO().actualizar(index, DataMapper.convertirPaqueteAPaqueteDTO(p));
				view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega("Paquete marcado como entregado.",
						false);
				view.getVentanaVigilante().getPestanaPaquetes().limpiarEntrega();
				refrescarPaquetes();
			} catch (EmptyFieldException | EntityNotFoundException ex) {
				view.getVentanaVigilante().getPestanaPaquetes().setMensajeEntrega(ex.getMessage(), true);
			}
			break;
		}
		case "ARR_CerrarSesion": {
			view.getVentanaArrendatario().dispose();
			usuarioEnSesion = null;
			view.getVentanaBienvenida().limpiarLogin();
			view.getVentanaBienvenida().mostrarPantallaLogin();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "ARR_RefrescarZonas": {
			view.getVentanaArrendatario().getPestanaReservas().getAreaZonasLista().setText(model.mostrarZonasComunes());
			break;
		}
		case "ARR_CrearReserva": {
			String zonaItem = (String) view.getVentanaArrendatario().getPestanaReservas().getComboZona()
					.getSelectedItem();
			String fechaStr = view.getVentanaArrendatario().getPestanaReservas().getTxtFecha().getText().trim();
			String horaIniStr = view.getVentanaArrendatario().getPestanaReservas().getTxtHoraInicio().getText().trim();
			String horaFinStr = view.getVentanaArrendatario().getPestanaReservas().getTxtHoraFin().getText().trim();
			try {
				if (zonaItem == null)
					throw new EmptyFieldException("Debes seleccionar una zona comun.");
				validarFecha(fechaStr);
				validarHora(horaIniStr, "inicio");
				validarHora(horaFinStr, "fin");
				LocalDate fecha = LocalDate.parse(fechaStr);
				LocalTime horaIni = LocalTime.parse(horaIniStr);
				LocalTime horaFin = LocalTime.parse(horaFinStr);
				if (!horaFin.isAfter(horaIni))
					throw new DateConflictException("La hora de fin debe ser posterior a la hora de inicio.");
				String idZona = zonaItem.split(" - ")[0];
				ZonaComun zona = model.obtenerZonaComunPorId(idZona);
				if (zona != null && ("MANTENIMIENTO".equals(zona.getEstado()) || "BLOQUEADA".equals(zona.getEstado())))
					throw new EntityBlockedException(
							"La zona comun esta " + zona.getEstado().toLowerCase() + " y no acepta reservas.");
				if (zona != null && zona.getAforoMaximo() > 0) {
					int reservasActivas = 0;
					for (Reserva r : model.obtenerTodasLasReservas()) {
						if (r.getZona() != null && idZona.equals(r.getZona().getId()) && fecha.equals(r.getFecha())
								&& ("CONFIRMADA".equals(r.getEstado()) || "PENDIENTE".equals(r.getEstado()))) {
							reservasActivas++;
						}
					}
					if (reservasActivas >= zona.getAforoMaximo())
						throw new MaxCapacityException(
								"La zona comun ha alcanzado su capacidad maxima de reservas para esa fecha.");
				}
				String idApto = null;
				for (Arrendatario a : model.obtenerTodosLosArrendatarios()) {
					if (a.getId().equals(usuarioEnSesion.getId())) {
						if (a.getApartamento() != null)
							idApto = a.getApartamento().getId();
						break;
					}
				}
				if (idApto == null)
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				String resultado = model.crearReserva(idZona, idApto, fecha, horaIni, horaFin);
				if ("OK".equals(resultado)) {
					view.getVentanaArrendatario().getPestanaReservas().setMensajeReserva("Reserva confirmada.", false);
					view.getVentanaArrendatario().getPestanaReservas().limpiarFormularioReserva();
					refrescarArrendatario(idApto);
				} else {
					view.getVentanaArrendatario().getPestanaReservas().setMensajeReserva(resultado, true);
				}
			} catch (EmptyFieldException | InvalidDateException | PastDateException | InvalidFormatException
					| DateConflictException | EntityBlockedException | EntityNotFoundException
					| MaxCapacityException ex) {
				view.getVentanaArrendatario().getPestanaReservas().setMensajeReserva(ex.getMessage(), true);
			}
			break;
		}
		case "ARR_LimpiarReserva": {
			view.getVentanaArrendatario().getPestanaReservas().limpiarFormularioReserva();
			break;
		}
		case "ARR_RefrescarMisReservas": {
			String idApto = obtenerIdAptoArrendatarioEnSesion();
			if (idApto != null)
				view.getVentanaArrendatario().getPestanaReservas().getAreaMisReservas()
						.setText(model.obtenerReservasPorApartamento(idApto).toString());
			break;
		}
		case "ARR_RefrescarVisitantes": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (RegistroVisita rv : model.obtenerTodosLosRegistrosVisita()) {
					if (rv.getVisitante() != null && rv.getVisitante().getApartamentoDestino() != null
							&& idApto.equals(rv.getVisitante().getApartamentoDestino().getId())) {
						sb.append(rv.toString()).append("\n\n");
					}
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaMisVisitantes().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaMisVisitantes()
							.setText("No hay visitantes registrados.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaVisitantes().getAreaMisVisitantes().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarPaquetes": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Paquete p : model.obtenerPaquetesPorApartamento(idApto)) {
					if ("PENDIENTE".equals(p.getEstado())) {
						sb.append(p.toString()).append("\n\n");
					}
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaPaquetes().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaPaquetes()
							.setText("No hay paquetes pendientes.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaVisitantes().getAreaPaquetes().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarResidentes": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Residente r : model.obtenerResidentesPorApartamento(idApto)) {
					sb.append(r.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaResidentes().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaResidentes()
							.setText("No hay residentes registrados.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaVisitantes().getAreaResidentes().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarVehiculos": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Vehiculo v : model.obtenerVehiculosPorApartamento(idApto)) {
					sb.append(v.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaVehiculos().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaVehiculos()
							.setText("No hay vehiculos registrados.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaVisitantes().getAreaVehiculos().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarMascotas": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Mascota m : model.obtenerMascotasPorApartamento(idApto)) {
					sb.append(m.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaMascotas().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaVisitantes().getAreaMascotas()
							.setText("No hay mascotas registradas.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaVisitantes().getAreaMascotas().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarIncidentes": {
			try {
				String idApto = obtenerIdAptoArrendatarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Incidente inc : model.obtenerIncidentesPorApartamento(idApto)) {
					sb.append(inc.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaArrendatario().getPestanaOperativa().getAreaIncLista().setText(sb.toString());
				} else {
					view.getVentanaArrendatario().getPestanaOperativa().getAreaIncLista()
							.setText("No hay incidentes reportados.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaArrendatario().getPestanaOperativa().getAreaIncLista().setText(ex.getMessage());
			}
			break;
		}
		case "ARR_RefrescarMantenimientos": {
			view.getVentanaArrendatario().getPestanaOperativa().getAreaManLista()
					.setText(model.mostrarSolicitudesMantenimiento());
			break;
		}
		case "ARR_CancelarReserva": {
			String idRsvStr = view.getVentanaArrendatario().getPestanaReservas().getTxtIdCancelar().getText().trim();
			String motivo = view.getVentanaArrendatario().getPestanaReservas().getTxtMotivoCancelar().getText().trim();
			try {
				if (idRsvStr.isEmpty()) {
					throw new EmptyFieldException("El ID de la reserva es obligatorio.");
				}
				String idAptoCxl = obtenerIdAptoArrendatarioEnSesion();
				int idxRsv = -1;
				ArrayList<Reserva> todasRsv = model.obtenerTodasLasReservas();
				for (int i = 0; i < todasRsv.size(); i++) {
					if (todasRsv.get(i).getId().equals(idRsvStr)) {
						idxRsv = i;
						break;
					}
				}
				if (idxRsv < 0) {
					throw new EntityNotFoundException("No se encontro una reserva con el ID: " + idRsvStr);
				}
				Reserva rsv = todasRsv.get(idxRsv);
				if ("CANCELADA".equals(rsv.getEstado()) || "COMPLETADA".equals(rsv.getEstado())) {
					throw new AlreadyProcessedException("Esta reserva ya fue " + rsv.getEstado().toLowerCase() + ".");
				}
				if (rsv.getApartamento() != null && idAptoCxl != null
						&& !rsv.getApartamento().getId().equals(idAptoCxl)) {
					throw new UnauthorizedOperationException(
							"No puedes cancelar una reserva que no pertenece a tu apartamento.");
				}
				String motivoFinal;
				if (motivo.isEmpty()) {
					motivoFinal = "Cancelada por arrendatario";
				} else {
					motivoFinal = motivo;
				}
				boolean ok = model.cancelarReserva(idxRsv, motivoFinal);
				if (ok) {
					view.getVentanaArrendatario().getPestanaReservas().setMensajeCancelar("Reserva cancelada.", false);
					refrescarArrendatario(idAptoCxl);
				} else {
					view.getVentanaArrendatario().getPestanaReservas().setMensajeCancelar("No se pudo cancelar.", true);
				}
			} catch (EmptyFieldException | EntityNotFoundException | AlreadyProcessedException
					| UnauthorizedOperationException ex) {
				view.getVentanaArrendatario().getPestanaReservas().setMensajeCancelar(ex.getMessage(), true);
			}
			break;
		}
		case "ARR_ReportarIncidente": {
			String desc = view.getVentanaArrendatario().getPestanaOperativa().getTxtIncDescripcion().getText().trim();
			String ubic = view.getVentanaArrendatario().getPestanaOperativa().getTxtIncUbicacion().getText().trim();
			String tipo = (String) view.getVentanaArrendatario().getPestanaOperativa().getComboIncTipo()
					.getSelectedItem();
			String gravedad = (String) view.getVentanaArrendatario().getPestanaOperativa().getComboIncGravedad()
					.getSelectedItem();
			if (desc.isEmpty()) {
				view.getVentanaArrendatario().getPestanaOperativa()
						.setMensajeIncidente("La descripcion es obligatoria.", true);
			} else {
				String idAptoInc = null;
				Apartamento aptoInc = null;
				for (Arrendatario a : model.obtenerTodosLosArrendatarios()) {
					if (a.getId().equals(usuarioEnSesion.getId())) {
						aptoInc = a.getApartamento();
						if (aptoInc != null) {
							idAptoInc = aptoInc.getId();
						}
						break;
					}
				}
				String descripcionFinal;
				if (ubic.isEmpty()) {
					descripcionFinal = desc;
				} else {
					descripcionFinal = desc + " - Ubicacion: " + ubic;
				}
				Incidente inc = new Incidente();
				inc.setDescripcion(descripcionFinal);
				inc.setApartamentoInvolucrado(aptoInc);
				inc.setTipo(tipo);
				inc.setGravedad(gravedad);
				inc.setGeneraMulta(false);
				model.reportarIncidente(inc);
				view.getVentanaArrendatario().getPestanaOperativa()
						.setMensajeIncidente("Incidente reportado correctamente.", false);
				view.getVentanaArrendatario().getPestanaOperativa().limpiarFormularioIncidente();
				refrescarArrendatario(idAptoInc);
			}
			break;
		}
		case "ARR_LimpiarIncidente": {
			view.getVentanaArrendatario().getPestanaOperativa().limpiarFormularioIncidente();
			break;
		}
		case "ARR_CrearMantenimiento": {
			String desc = view.getVentanaArrendatario().getPestanaOperativa().getTxtManDescripcion().getText().trim();
			String ubic = view.getVentanaArrendatario().getPestanaOperativa().getTxtManUbicacion().getText().trim();
			String tipo = (String) view.getVentanaArrendatario().getPestanaOperativa().getComboManTipo()
					.getSelectedItem();
			String prioridad = (String) view.getVentanaArrendatario().getPestanaOperativa().getComboManPrioridad()
					.getSelectedItem();
			try {
				if (desc.isEmpty())
					throw new EmptyFieldException("La descripcion de la solicitud es obligatoria.");
				SolicitudMantenimiento sol = new SolicitudMantenimiento();
				sol.setDescripcion(desc);
				sol.setObservaciones(ubic);
				sol.setTipo(tipo);
				sol.setPrioridad(prioridad);
				model.crearSolicitudMantenimiento(sol);
				view.getVentanaArrendatario().getPestanaOperativa()
						.setMensajeMantenimiento("Solicitud creada correctamente.", false);
				view.getVentanaArrendatario().getPestanaOperativa().limpiarFormularioMantenimiento();
				String idAptoMan = null;
				for (Arrendatario a : model.obtenerTodosLosArrendatarios()) {
					if (a.getId().equals(usuarioEnSesion.getId())) {
						if (a.getApartamento() != null)
							idAptoMan = a.getApartamento().getId();
						break;
					}
				}
				refrescarArrendatario(idAptoMan);
			} catch (EmptyFieldException ex) {
				view.getVentanaArrendatario().getPestanaOperativa().setMensajeMantenimiento(ex.getMessage(), true);
			}
			break;
		}
		case "ARR_LimpiarMantenimiento": {
			view.getVentanaArrendatario().getPestanaOperativa().limpiarFormularioMantenimiento();
			break;
		}
		case "PROP_CerrarSesion": {
			view.getVentanaPropietario().dispose();
			usuarioEnSesion = null;
			view.getVentanaBienvenida().limpiarLogin();
			view.getVentanaBienvenida().mostrarPantallaLogin();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "PROP_RefrescarZonas": {
			view.getVentanaPropietario().getPestanaReservas().getAreaZonasLista().setText(model.mostrarZonasComunes());
			break;
		}
		case "PROP_CrearReserva": {
			String zonaItem = (String) view.getVentanaPropietario().getPestanaReservas().getComboZona()
					.getSelectedItem();
			String fechaStr = view.getVentanaPropietario().getPestanaReservas().getTxtFecha().getText().trim();
			String horaIniStr = view.getVentanaPropietario().getPestanaReservas().getTxtHoraInicio().getText().trim();
			String horaFinStr = view.getVentanaPropietario().getPestanaReservas().getTxtHoraFin().getText().trim();
			try {
				if (zonaItem == null)
					throw new EmptyFieldException("Debes seleccionar una zona comun.");
				validarFecha(fechaStr);
				validarHora(horaIniStr, "inicio");
				validarHora(horaFinStr, "fin");
				LocalDate fecha = LocalDate.parse(fechaStr);
				LocalTime horaIni = LocalTime.parse(horaIniStr);
				LocalTime horaFin = LocalTime.parse(horaFinStr);
				if (!horaFin.isAfter(horaIni))
					throw new DateConflictException("La hora de fin debe ser posterior a la hora de inicio.");
				String idZona = zonaItem.split(" - ")[0];
				ZonaComun zona = model.obtenerZonaComunPorId(idZona);
				if (zona != null && ("MANTENIMIENTO".equals(zona.getEstado()) || "BLOQUEADA".equals(zona.getEstado())))
					throw new EntityBlockedException(
							"La zona comun esta " + zona.getEstado().toLowerCase() + " y no acepta reservas.");
				if (zona != null && zona.getAforoMaximo() > 0) {
					int reservasActivas = 0;
					for (Reserva r : model.obtenerTodasLasReservas()) {
						if (r.getZona() != null && idZona.equals(r.getZona().getId()) && fecha.equals(r.getFecha())
								&& ("CONFIRMADA".equals(r.getEstado()) || "PENDIENTE".equals(r.getEstado()))) {
							reservasActivas++;
						}
					}
					if (reservasActivas >= zona.getAforoMaximo())
						throw new MaxCapacityException(
								"La zona comun ha alcanzado su capacidad maxima de reservas para esa fecha.");
				}
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null)
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				String resultado = model.crearReserva(idZona, idApto, fecha, horaIni, horaFin);
				if ("OK".equals(resultado)) {
					view.getVentanaPropietario().getPestanaReservas().setMensajeReserva("Reserva confirmada.", false);
					view.getVentanaPropietario().getPestanaReservas().limpiarFormularioReserva();
					refrescarPropietario(idApto);
				} else {
					view.getVentanaPropietario().getPestanaReservas().setMensajeReserva(resultado, true);
				}
			} catch (EmptyFieldException | InvalidDateException | PastDateException | InvalidFormatException
					| DateConflictException | EntityBlockedException | EntityNotFoundException
					| MaxCapacityException ex) {
				view.getVentanaPropietario().getPestanaReservas().setMensajeReserva(ex.getMessage(), true);
			}
			break;
		}
		case "PROP_LimpiarReserva": {
			view.getVentanaPropietario().getPestanaReservas().limpiarFormularioReserva();
			break;
		}
		case "PROP_RefrescarMisReservas": {
			String idApto = obtenerIdAptoPropietarioEnSesion();
			if (idApto != null)
				view.getVentanaPropietario().getPestanaReservas().getAreaMisReservas()
						.setText(model.obtenerReservasPorApartamento(idApto).toString());
			break;
		}
		case "PROP_CancelarReserva": {
			String idRes = view.getVentanaPropietario().getPestanaReservas().getTxtIdCancelar().getText().trim();
			String motivo = view.getVentanaPropietario().getPestanaReservas().getTxtMotivoCancelar().getText().trim();
			try {
				if (idRes.isEmpty()) {
					throw new EmptyFieldException("El ID de la reserva es obligatorio.");
				}
				String idAptoProp = obtenerIdAptoPropietarioEnSesion();
				int index = -1;
				ArrayList<Reserva> todas = model.obtenerTodasLasReservas();
				for (int i = 0; i < todas.size(); i++) {
					if (todas.get(i).getId().equals(idRes)) {
						index = i;
						break;
					}
				}
				if (index == -1) {
					throw new EntityNotFoundException("No se encontro una reserva con el ID: " + idRes);
				}
				Reserva rsv = todas.get(index);
				if ("CANCELADA".equals(rsv.getEstado()) || "COMPLETADA".equals(rsv.getEstado())) {
					throw new AlreadyProcessedException("Esta reserva ya fue " + rsv.getEstado().toLowerCase() + ".");
				}
				if (rsv.getApartamento() != null && idAptoProp != null
						&& !rsv.getApartamento().getId().equals(idAptoProp)) {
					throw new UnauthorizedOperationException(
							"No puedes cancelar una reserva que no pertenece a tu apartamento.");
				}
				String motivoFinal;
				if (motivo.isEmpty()) {
					motivoFinal = "Cancelada por propietario";
				} else {
					motivoFinal = motivo;
				}
				boolean ok = model.cancelarReserva(index, motivoFinal);
				if (ok) {
					view.getVentanaPropietario().getPestanaReservas()
							.setMensajeCancelar("Reserva cancelada correctamente.", false);
				} else {
					view.getVentanaPropietario().getPestanaReservas()
							.setMensajeCancelar("No se pudo cancelar la reserva.", true);
				}
			} catch (EmptyFieldException | EntityNotFoundException | AlreadyProcessedException
					| UnauthorizedOperationException ex) {
				view.getVentanaPropietario().getPestanaReservas().setMensajeCancelar(ex.getMessage(), true);
			}
			break;
		}
		case "PROP_RefrescarVisitantes": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null)
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				StringBuilder sb = new StringBuilder();
				for (RegistroVisita rv : model.obtenerTodosLosRegistrosVisita()) {
					if (rv.getVisitante() != null && rv.getVisitante().getApartamentoDestino() != null
							&& idApto.equals(rv.getVisitante().getApartamentoDestino().getId()))
						sb.append(rv.toString()).append("\n\n");
				}
				view.getVentanaPropietario().getPestanaApartamento().getAreaMisVisitantes()
						.setText(sb.length() > 0 ? sb.toString() : "No hay visitantes registrados.");
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaApartamento().getAreaMisVisitantes().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_RefrescarPaquetes": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Paquete p : model.obtenerPaquetesPorApartamento(idApto)) {
					if ("PENDIENTE".equals(p.getEstado())) {
						sb.append(p.toString()).append("\n\n");
					}
				}
				if (sb.length() > 0) {
					view.getVentanaPropietario().getPestanaApartamento().getAreaPaquetes().setText(sb.toString());
				} else {
					view.getVentanaPropietario().getPestanaApartamento().getAreaPaquetes()
							.setText("No hay paquetes pendientes.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaApartamento().getAreaPaquetes().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_RefrescarResidentes": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Residente r : model.obtenerResidentesPorApartamento(idApto)) {
					sb.append(r.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaPropietario().getPestanaApartamento().getAreaResidentes().setText(sb.toString());
				} else {
					view.getVentanaPropietario().getPestanaApartamento().getAreaResidentes()
							.setText("No hay residentes.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaApartamento().getAreaResidentes().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_RefrescarVehiculos": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Vehiculo v : model.obtenerVehiculosPorApartamento(idApto)) {
					sb.append(v.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaPropietario().getPestanaApartamento().getAreaVehiculos().setText(sb.toString());
				} else {
					view.getVentanaPropietario().getPestanaApartamento().getAreaVehiculos()
							.setText("No hay vehiculos.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaApartamento().getAreaVehiculos().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_RefrescarMascotas": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Mascota m : model.obtenerMascotasPorApartamento(idApto)) {
					sb.append(m.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaPropietario().getPestanaApartamento().getAreaMascotas().setText(sb.toString());
				} else {
					view.getVentanaPropietario().getPestanaApartamento().getAreaMascotas().setText("No hay mascotas.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaApartamento().getAreaMascotas().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_ReportarIncidente": {
			String desc = view.getVentanaPropietario().getPestanaOperativa().getTxtIncDescripcion().getText().trim();
			String ubic = view.getVentanaPropietario().getPestanaOperativa().getTxtIncUbicacion().getText().trim();
			String tipo = (String) view.getVentanaPropietario().getPestanaOperativa().getComboIncTipo()
					.getSelectedItem();
			String gravedad = (String) view.getVentanaPropietario().getPestanaOperativa().getComboIncGravedad()
					.getSelectedItem();
			try {
				if (desc.isEmpty()) {
					throw new EmptyFieldException("La descripcion del incidente es obligatoria.");
				}
				Apartamento aptoInc = null;
				String idAptoInc = null;
				for (Apartamento apto : model.obtenerTodosLosApartamentos()) {
					if (apto.getPropietario() != null
							&& apto.getPropietario().getId().equals(usuarioEnSesion.getId())) {
						aptoInc = apto;
						idAptoInc = apto.getId();
						break;
					}
				}
				String descripcionFinal;
				if (ubic.isEmpty()) {
					descripcionFinal = desc;
				} else {
					descripcionFinal = desc + " - Ubicacion: " + ubic;
				}
				Incidente inc = new Incidente();
				inc.setDescripcion(descripcionFinal);
				inc.setApartamentoInvolucrado(aptoInc);
				inc.setTipo(tipo);
				inc.setGravedad(gravedad);
				if ("Multa".equals(gravedad)) {
					inc.setGeneraMulta(true);
				} else {
					inc.setGeneraMulta(false);
				}
				model.reportarIncidente(inc);
				view.getVentanaPropietario().getPestanaOperativa().setMensajeIncidente("Incidente reportado.", false);
				view.getVentanaPropietario().getPestanaOperativa().limpiarFormularioIncidente();
				refrescarPropietario(idAptoInc);
			} catch (EmptyFieldException ex) {
				view.getVentanaPropietario().getPestanaOperativa().setMensajeIncidente(ex.getMessage(), true);
			}
			break;
		}
		case "PROP_LimpiarIncidente": {
			view.getVentanaPropietario().getPestanaOperativa().limpiarFormularioIncidente();
			break;
		}
		case "PROP_RefrescarIncidentes": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder sb = new StringBuilder();
				for (Incidente inc : model.obtenerIncidentesPorApartamento(idApto)) {
					sb.append(inc.toString()).append("\n\n");
				}
				if (sb.length() > 0) {
					view.getVentanaPropietario().getPestanaOperativa().getAreaIncLista().setText(sb.toString());
				} else {
					view.getVentanaPropietario().getPestanaOperativa().getAreaIncLista().setText("No hay incidentes.");
				}
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaOperativa().getAreaIncLista().setText(ex.getMessage());
			}
			break;
		}
		case "PROP_CrearMantenimiento": {
			String desc = view.getVentanaPropietario().getPestanaOperativa().getTxtManDescripcion().getText().trim();
			String ubic = view.getVentanaPropietario().getPestanaOperativa().getTxtManUbicacion().getText().trim();
			String tipo = (String) view.getVentanaPropietario().getPestanaOperativa().getComboManTipo()
					.getSelectedItem();
			String prioridad = (String) view.getVentanaPropietario().getPestanaOperativa().getComboManPrioridad()
					.getSelectedItem();
			try {
				if (desc.isEmpty())
					throw new EmptyFieldException("La descripcion de la solicitud es obligatoria.");
				SolicitudMantenimiento sol = new SolicitudMantenimiento();
				sol.setDescripcion(desc);
				sol.setObservaciones(ubic);
				sol.setTipo(tipo);
				sol.setPrioridad(prioridad);
				model.crearSolicitudMantenimiento(sol);
				view.getVentanaPropietario().getPestanaOperativa()
						.setMensajeMantenimiento("Solicitud creada correctamente.", false);
				view.getVentanaPropietario().getPestanaOperativa().limpiarFormularioMantenimiento();
				String idAptoMan = null;
				for (Apartamento apto : model.obtenerTodosLosApartamentos()) {
					if (apto.getPropietario() != null
							&& apto.getPropietario().getId().equals(usuarioEnSesion.getId())) {
						idAptoMan = apto.getId();
						break;
					}
				}
				refrescarPropietario(idAptoMan);
			} catch (EmptyFieldException ex) {
				view.getVentanaPropietario().getPestanaOperativa().setMensajeMantenimiento(ex.getMessage(), true);
			}
			break;
		}
		case "PROP_LimpiarMantenimiento": {
			view.getVentanaPropietario().getPestanaOperativa().limpiarFormularioMantenimiento();
			break;
		}
		case "PROP_RefrescarMantenimientos": {
			view.getVentanaPropietario().getPestanaOperativa().getAreaManLista()
					.setText(model.mostrarSolicitudesMantenimiento());
			break;
		}
		case "PROP_VerEstadoCuenta": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null)
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				view.getVentanaPropietario().getPestanaFinancieros().getAreaEstadoCuenta()
						.setText(model.obtenerEstadoCuenta(idApto));
				view.getVentanaPropietario().getPestanaFinancieros().setMensaje("Estado de cuenta cargado.", false);
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaFinancieros().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "PROP_RefrescarFinanzas": {
			try {
				String idApto = obtenerIdAptoPropietarioEnSesion();
				if (idApto == null) {
					throw new EntityNotFoundException("No se encontro apartamento asociado a tu cuenta.");
				}
				StringBuilder oblig = new StringBuilder();
				for (Obligacion o : model.obtenerObligacionesPendientes(idApto)) {
					oblig.append(o.toString()).append("\n\n");
				}
				if (oblig.length() > 0) {
					view.getVentanaPropietario().getPestanaFinancieros().getAreaObligaciones()
							.setText(oblig.toString());
				} else {
					view.getVentanaPropietario().getPestanaFinancieros().getAreaObligaciones()
							.setText("No hay obligaciones pendientes.");
				}
				StringBuilder pagos = new StringBuilder();
				for (Pago p : model.obtenerPagosPorApartamento(idApto)) {
					pagos.append(p.toString()).append("\n\n");
				}
				if (pagos.length() > 0) {
					view.getVentanaPropietario().getPestanaFinancieros().getAreaPagos().setText(pagos.toString());
				} else {
					view.getVentanaPropietario().getPestanaFinancieros().getAreaPagos()
							.setText("No hay pagos registrados.");
				}
				view.getVentanaPropietario().getPestanaFinancieros().getAreaEstadoCuenta()
						.setText(model.obtenerEstadoCuenta(idApto));
			} catch (EntityNotFoundException ex) {
				view.getVentanaPropietario().getPestanaFinancieros().setMensaje(ex.getMessage(), true);
			}
			break;
		}
		case "CONS_CerrarSesion": {
			view.getVentanaConsejo().dispose();
			view.getVentanaBienvenida().mostrarPantallaPrincipal();
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		case "CONS_GenerarReporte": {
			String seleccion = (String) view.getVentanaConsejo().getPestanaConsejo().getComboTipoReporte()
					.getSelectedItem();
			try {
				String tipo = mapearTipoReporte(seleccion);
				String idConj = "";
				if (usuarioEnSesion != null && usuarioEnSesion.getConjunto() != null)
					idConj = usuarioEnSesion.getConjunto().getId();
				Reporte rep = model.generarReporte(tipo, idConj, usuarioEnSesion.getUsername());
				if (rep == null)
					throw new EntityNotFoundException("No se pudo generar el reporte.");
				view.getVentanaConsejo().getPestanaConsejo().setMensajeReporte("Reporte generado y exportado a PDF.",
						false);
				view.getVentanaConsejo().getPestanaConsejo().getAreaReporte().setText(rep.getContenido());
			} catch (EntityNotFoundException ex) {
				view.getVentanaConsejo().getPestanaConsejo().setMensajeReporte(ex.getMessage(), true);
			}
			break;
		}
		case "CONS_GenerarCuotas": {
			String montoStr = view.getVentanaConsejo().getPestanaConsejo().getTxtMontoCuota().getText().trim();
			try {
				validarMonto(montoStr);
				double monto = Double.parseDouble(montoStr);
				if (monto > 10000000)
					throw new CapacityExceededException(
							"El monto de la cuota supera el limite permitido de $10.000.000.");
				model.generarCuotasMensuales(monto);
				view.getVentanaConsejo().getPestanaConsejo()
						.setMensajeCuota("Cuotas generadas para todos los apartamentos ocupados.", false);
				view.getVentanaConsejo().getPestanaConsejo().getTxtMontoCuota().setText("");
				view.getVentanaConsejo().getPestanaConsejo().getAreaMorosos().setText(mostrarMorosos());
			} catch (EmptyFieldException | InvalidFormatException | CapacityExceededException ex) {
				view.getVentanaConsejo().getPestanaConsejo().setMensajeCuota(ex.getMessage(), true);
			}
			break;
		}
		case "CONS_RefrescarMorosos": {
			view.getVentanaConsejo().getPestanaConsejo().getAreaMorosos().setText(mostrarMorosos());
			break;
		}
		case "CONS_RefrescarIncidentes": {
			view.getVentanaConsejo().getPestanaConsejo().getAreaIncidentes().setText(model.mostrarIncidentes());
			break;
		}
		default: {
			break;
		}
		}
	}

	/**
	 * Abre la ventana correspondiente al rol del usuario que inicio sesion y carga
	 * la informacion inicial necesaria para cada perfil. <b>pre</b> El usuario
	 * recibido debe tener un rol valido reconocido por el sistema. <br>
	 * <b>post</b> La ventana del rol correspondiente queda visible con sus oyentes
	 * asignados y sus datos iniciales cargados. <br>
	 *
	 * @param u Usuario autenticado cuyo rol determina la ventana a abrir. u != null
	 */
	public void abrirVentanaPorRol(Usuario u) {
		switch (u.getRol()) {
		case "SUPER_ADMIN": {
			view.iniciarVentanaSuperAdmin(u.getUsername());
			asignarOyentesSuperAdmin();
			refrescarListaConjuntos();
			refrescarListaAdmins();
			refrescarListaUsuarios();
			recargarCombosConjunto();
			actualizarComboConjuntos();
			recargarComboTorres();
			refrescarListaTorres();
			refrescarListaAptos();
			refrescarListaZonas();
			refrescarListaParqs();
			view.getVentanaSuperAdmin().setVisible(true);
			break;
		}
		case "ADMINISTRADOR": {
			usuarioEnSesion = u;
			String nombreConj;
			if (u.getConjunto() != null) {
				nombreConj = u.getConjunto().getNombre();
			} else {
				nombreConj = "Sin conjunto";
			}
			view.iniciarVentanaAdmin(u.getUsername(), nombreConj);
			asignarOyentesAdmin();
			refrescarAdminResidentes();
			refrescarAdminZonasParq();
			refrescarAdminOperativa();
			refrescarAdminFinanzas();
			refrescarAdminReportes();
			view.getVentanaAdmin().setVisible(true);
			model.verificarReservasProximas();
			view.getVentanaBienvenida().setVisible(false);
			break;
		}
		case "VIGILANTE": {
			usuarioEnSesion = u;
			view.iniciarVentanaVigilante(u.getUsername());
			asignarOyentesVigilante();
			refrescarPaquetes();
			refrescarVisitantes();
			view.getVentanaVigilante().setVisible(true);
			view.getVentanaBienvenida().setVisible(false);
			break;
		}
		case "PROPIETARIO": {
			usuarioEnSesion = u;
			String numApto = "Sin apartamento";
			String idApto = null;
			for (Apartamento apto : model.obtenerTodosLosApartamentos()) {
				if (apto.getPropietario() != null && apto.getPropietario().getId().equals(u.getId())) {
					numApto = apto.getNumero();
					idApto = apto.getId();
					break;
				}
			}
			view.iniciarVentanaPropietario(u.getUsername(), numApto);
			asignarOyentesPropietario();
			refrescarPropietario(idApto);
			view.getVentanaPropietario().setVisible(true);
			view.getVentanaBienvenida().setVisible(false);
			break;
		}
		case "CONSEJO": {
			usuarioEnSesion = u;
			String numApto = "Sin apartamento";
			String idApto = null;
			for (Apartamento apto : model.obtenerTodosLosApartamentos()) {
				if (apto.getPropietario() != null && apto.getPropietario().getId().equals(u.getId())) {
					numApto = apto.getNumero();
					idApto = apto.getId();
					break;
				}
			}
			view.iniciarVentanaConsejo(u.getUsername(), numApto);
			asignarOyentesConsejo();
			refrescarPropietarioEnConsejo(idApto);
			view.getVentanaConsejo().setVisible(true);
			view.getVentanaBienvenida().setVisible(false);
			break;
		}
		case "ARRENDATARIO": {
			usuarioEnSesion = u;
			Arrendatario arrLogueado = null;
			for (Arrendatario a : model.obtenerTodosLosArrendatarios()) {
				if (a.getId().equals(u.getId())) {
					arrLogueado = a;
					break;
				}
			}
			String numApto = "Sin apartamento";
			String idApto = null;
			if (arrLogueado != null && arrLogueado.getApartamento() != null) {
				numApto = arrLogueado.getApartamento().getNumero();
				idApto = arrLogueado.getApartamento().getId();
			}
			view.iniciarVentanaArrendatario(u.getUsername(), numApto);
			asignarOyentesArrendatario();
			refrescarArrendatario(idApto);
			view.getVentanaArrendatario().setVisible(true);
			view.getVentanaBienvenida().setVisible(false);
			break;
		}
		default: {
			view.getVentanaBienvenida().mostrarError("Rol no reconocido.");
			view.getVentanaBienvenida().setVisible(true);
			break;
		}
		}
	}

	/**
	 * Recarga los combos de seleccion de conjunto en las secciones de torres, zonas
	 * y parqueaderos de la ventana SuperAdmin, y actualiza el combo de conjuntos en
	 * la pestana de administradores. <b>pre</b> La ventana SuperAdmin debe estar
	 * inicializada. <br>
	 * <b>post</b> Los JComboBox de conjunto quedan actualizados con los conjuntos
	 * registrados en el sistema. <br>
	 */
	public void recargarCombosConjunto() {
		JComboBox<String> comboTorre = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto()
				.getComboConjuntoTorre();
		JComboBox<String> comboZona = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboConjuntoZona();
		JComboBox<String> comboParq = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboConjuntoParq();
		comboTorre.removeAllItems();
		comboZona.removeAllItems();
		comboParq.removeAllItems();
		comboTorre.addItem("-- Seleccione Conjunto --");
		comboZona.addItem("-- Seleccione Conjunto --");
		comboParq.addItem("-- Seleccione Conjunto --");
		for (Conjunto c : model.obtenerTodosLosConjuntos()) {
			String item = c.getId() + " - " + c.getNombre();
			comboTorre.addItem(item);
			comboZona.addItem(item);
			comboParq.addItem(item);
		}
		view.getVentanaSuperAdmin().getPestanaAdministradores().actualizarConjuntos(model.obtenerTodosLosConjuntos());
	}

	/**
	 * Recarga el combo de seleccion de torre en la seccion de apartamentos de la
	 * ventana SuperAdmin con las torres registradas en el sistema. <b>pre</b> La
	 * ventana SuperAdmin debe estar inicializada. <br>
	 * <b>post</b> El JComboBox de torres queda actualizado con todas las torres
	 * disponibles y su conjunto asociado. <br>
	 */
	public void recargarComboTorres() {
		JComboBox<String> comboApto = view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getComboTorreApto();
		comboApto.removeAllItems();
		comboApto.addItem("-- Seleccione Torre --");
		for (Torre t : model.obtenerTodosLasTorres()) {
			String conjNombre;
			if (t.getConjunto() != null) {
				conjNombre = t.getConjunto().getNombre();
			} else {
				conjNombre = "?";
			}
			comboApto.addItem(t.getId() + " - " + t.getNombre() + " (" + conjNombre + ")");
		}
	}

	/**
	 * Extrae el identificador del conjunto a partir del item seleccionado en un
	 * JComboBox. <b>pre</b> El item seleccionado debe tener el formato "ID -
	 * Nombre" o ser el placeholder de seleccion. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param selectedItem Objeto seleccionado en el JComboBox. Puede ser null
	 * @return String con el ID del conjunto, o null si no hay seleccion valida
	 */
	public String obtenerIdConjuntoSeleccionado(Object selectedItem) {
		if (selectedItem == null)
			return null;
		String item = selectedItem.toString();
		if (item.startsWith("--"))
			return null;
		return item.split(" - ")[0].trim();
	}

	/**
	 * Extrae el identificador de la torre a partir del item seleccionado en un
	 * JComboBox. <b>pre</b> El item seleccionado debe tener el formato "ID - Nombre
	 * (Conjunto)" o ser el placeholder de seleccion. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param selectedItem Objeto seleccionado en el JComboBox. Puede ser null
	 * @return String con el ID de la torre, o null si no hay seleccion valida
	 */
	public String obtenerIdTorreSeleccionada(Object selectedItem) {
		if (selectedItem == null)
			return null;
		String item = selectedItem.toString();
		if (item.startsWith("--"))
			return null;
		return item.split(" - ")[0].trim();
	}

	/**
	 * Actualiza el area de lista de conjuntos en la ventana SuperAdmin con todos
	 * los conjuntos registrados en el sistema. <b>pre</b> La ventana SuperAdmin
	 * debe estar inicializada. <br>
	 * <b>post</b> El area de lista de conjuntos queda actualizada en la vista. <br>
	 */
	public void refrescarListaConjuntos() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Conjunto c : model.obtenerTodosLosConjuntos()) {
			sb.append("[").append(i++).append("] ").append(c.getId()).append(" | ").append(c.getNombre()).append(" | ")
					.append(c.getCiudad()).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaConjuntos().getAreaLista().setText("No hay conjuntos registrados.");
		} else {
			view.getVentanaSuperAdmin().getPestanaConjuntos().getAreaLista().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de administradores en la ventana SuperAdmin con
	 * todos los usuarios con rol ADMINISTRADOR. <b>pre</b> La ventana SuperAdmin
	 * debe estar inicializada. <br>
	 * <b>post</b> El area de lista de administradores queda actualizada en la
	 * vista. <br>
	 */
	public void refrescarListaAdmins() {
		StringBuilder sb = new StringBuilder();
		for (Usuario u : model.obtenerUsuariosPorRol("ADMINISTRADOR")) {
			sb.append("Username: ").append(u.getUsername());
			if (u.isActivo()) {
				sb.append(" | Activo: Si");
			} else {
				sb.append(" | Activo: No");
			}
			if (u.getConjunto() != null) {
				sb.append(" | Conjunto: ").append(u.getConjunto().getNombre());
			} else {
				sb.append(" | Conjunto: -");
			}
			sb.append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaAdministradores().getAreaLista()
					.setText("No hay administradores registrados.");
		} else {
			view.getVentanaSuperAdmin().getPestanaAdministradores().getAreaLista().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de usuarios en la ventana SuperAdmin con todos los
	 * usuarios registrados en el sistema, independientemente de su rol. <b>pre</b>
	 * La ventana SuperAdmin debe estar inicializada. <br>
	 * <b>post</b> El area de lista de usuarios queda actualizada en la vista. <br>
	 */
	public void refrescarListaUsuarios() {
		StringBuilder sb = new StringBuilder();
		for (Usuario u : model.obtenerTodosLosUsuarios()) {
			sb.append("Username: ").append(u.getUsername()).append(" | Rol: ").append(u.getRol()).append(" | Activo: ");
			if (u.isActivo()) {
				sb.append("Si");
			} else {
				sb.append("No");
			}
			sb.append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaUsuarios().getAreaLista().setText("No hay usuarios registrados.");
		} else {
			view.getVentanaSuperAdmin().getPestanaUsuarios().getAreaLista().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de torres en la ventana SuperAdmin con todas las
	 * torres registradas en el sistema. <b>pre</b> La ventana SuperAdmin debe estar
	 * inicializada. <br>
	 * <b>post</b> El area de lista de torres queda actualizada en la vista. <br>
	 */
	public void refrescarListaTorres() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Torre t : model.obtenerTodosLasTorres()) {
			String conjNombre;
			if (t.getConjunto() != null) {
				conjNombre = t.getConjunto().getNombre();
			} else {
				conjNombre = "-";
			}
			sb.append("[").append(i++).append("] ").append(t.getId()).append(" | ").append(t.getNombre()).append(" | ")
					.append(t.getNumeroPisos()).append(" pisos | Conjunto: ").append(conjNombre).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaTorres()
					.setText("No hay torres registradas.");
		} else {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaTorres().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de apartamentos en la ventana SuperAdmin con todos
	 * los apartamentos registrados en el sistema. <b>pre</b> La ventana SuperAdmin
	 * debe estar inicializada. <br>
	 * <b>post</b> El area de lista de apartamentos queda actualizada en la vista.
	 * <br>
	 */
	public void refrescarListaAptos() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Apartamento a : model.obtenerTodosLosApartamentos()) {
			String torreNombre;
			if (a.getTorre() != null) {
				torreNombre = a.getTorre().getNombre();
			} else {
				torreNombre = "-";
			}
			sb.append("[").append(i++).append("] ").append(a.getId()).append(" | Apto ").append(a.getNumero())
					.append(" | Piso ").append(a.getPiso()).append(" | ").append(a.getEstado()).append(" | Torre: ")
					.append(torreNombre).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaAptos()
					.setText("No hay apartamentos registrados.");
		} else {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaAptos().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de zonas comunes en la ventana SuperAdmin con
	 * todas las zonas registradas en el sistema. <b>pre</b> La ventana SuperAdmin
	 * debe estar inicializada. <br>
	 * <b>post</b> El area de lista de zonas comunes queda actualizada en la vista.
	 * <br>
	 */
	public void refrescarListaZonas() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (ZonaComun z : model.obtenerTodasLasZonasComunes()) {
			String conjNombre;
			if (z.getConjunto() != null) {
				conjNombre = z.getConjunto().getNombre();
			} else {
				conjNombre = "-";
			}
			sb.append("[").append(i++).append("] ").append(z.getId()).append(" | ").append(z.getNombre()).append(" | ")
					.append(z.getTipo()).append(" | ").append(z.getEstado()).append(" | Aforo: ")
					.append(z.getAforoMaximo()).append(" | ").append(z.getHoraApertura()).append("-")
					.append(z.getHoraCierre()).append(" | Reserva: ");
			if (z.isRequiereReserva()) {
				sb.append("Si");
			} else {
				sb.append("No");
			}
			sb.append(" | Conjunto: ").append(conjNombre).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaZonas()
					.setText("No hay zonas comunes registradas.");
		} else {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaZonas().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de lista de parqueaderos en la ventana SuperAdmin con todos
	 * los parqueaderos registrados en el sistema. <b>pre</b> La ventana SuperAdmin
	 * debe estar inicializada. <br>
	 * <b>post</b> El area de lista de parqueaderos queda actualizada en la vista.
	 * <br>
	 */
	public void refrescarListaParqs() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Parqueadero p : model.obtenerTodosLosParqueaderos()) {
			String conjNombre;
			if (p.getConjunto() != null) {
				conjNombre = p.getConjunto().getNombre();
			} else {
				conjNombre = "-";
			}
			sb.append("[").append(i++).append("] ").append(p.getId()).append(" | # ").append(p.getNumero())
					.append(" | ").append(p.getTipo()).append(" | ").append(p.getEstado()).append(" | Conjunto: ")
					.append(conjNombre).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaParq()
					.setText("No hay parqueaderos registrados.");
		} else {
			view.getVentanaSuperAdmin().getPestanaConfigurarConjunto().getAreaListaParq().setText(sb.toString());
		}
	}

	/**
	 * Actualiza todas las areas de lista de la pestana de residentes en la ventana
	 * Admin: propietarios, arrendatarios, residentes, vehiculos y mascotas.
	 * <b>pre</b> La ventana Admin debe estar inicializada. <br>
	 * <b>post</b> Todas las listas de residentes quedan actualizadas en la vista.
	 * <br>
	 */
	public void refrescarAdminResidentes() {
		view.getVentanaAdmin().getPestanaResidentes().getAreaPropLista().setText(model.mostrarPropietarios());
		view.getVentanaAdmin().getPestanaResidentes().getAreaArrLista().setText(model.mostrarArrendatarios());
		view.getVentanaAdmin().getPestanaResidentes().getAreaResLista().setText(model.mostrarResidentes());
		view.getVentanaAdmin().getPestanaResidentes().getAreaVehLista().setText(model.mostrarVehiculos());
		view.getVentanaAdmin().getPestanaResidentes().getAreaMasLista().setText(model.mostrarMascotas());
	}

	/**
	 * Actualiza las listas de parqueaderos y reservas en la pestana de zonas y
	 * parqueaderos de la ventana Admin. <b>pre</b> La ventana Admin debe estar
	 * inicializada. <br>
	 * <b>post</b> Las listas de parqueaderos y reservas quedan actualizadas en la
	 * vista. <br>
	 */
	public void refrescarAdminZonasParq() {
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaParqLista().setText(model.mostrarParqueaderos());
		view.getVentanaAdmin().getPestanaZonasParqueaderos().getAreaResLista().setText(model.mostrarReservas());
	}

	/**
	 * Actualiza las listas de visitantes, paquetes, mantenimientos e incidentes en
	 * la pestana de gestion operativa de la ventana Admin. <b>pre</b> La ventana
	 * Admin debe estar inicializada. <br>
	 * <b>post</b> Todas las listas operativas quedan actualizadas en la vista. <br>
	 */
	public void refrescarAdminOperativa() {
		view.getVentanaAdmin().getPestanaGestionOperativa().getAreaVisLista().setText(model.mostrarRegistrosVisita());
		view.getVentanaAdmin().getPestanaGestionOperativa().getAreaPaqLista().setText(model.mostrarPaquetes());
		view.getVentanaAdmin().getPestanaGestionOperativa().getAreaManLista()
				.setText(model.mostrarSolicitudesMantenimiento());
		view.getVentanaAdmin().getPestanaGestionOperativa().getAreaIncLista().setText(model.mostrarIncidentes());
	}

	/**
	 * Actualiza las listas de obligaciones, pagos, campanas y consumos en la
	 * pestana de finanzas y sostenibilidad de la ventana Admin. <b>pre</b> La
	 * ventana Admin debe estar inicializada. <br>
	 * <b>post</b> Todas las listas financieras y ambientales quedan actualizadas en
	 * la vista. <br>
	 */
	public void refrescarAdminFinanzas() {
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaFinLista()
				.setText(model.mostrarObligaciones() + "\n" + model.mostrarPagos());
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaCampLista().setText(model.mostrarCampanas());
		view.getVentanaAdmin().getPestanaFinanzasSostenibilidad().getAreaConsLista()
				.setText(model.mostrarRegistrosAgua() + "\n" + model.mostrarRegistrosEnergia() + "\n"
						+ model.mostrarRegistrosReciclaje());
	}

	/**
	 * Actualiza el area de notificaciones en la pestana de reportes de la ventana
	 * Admin. <b>pre</b> La ventana Admin debe estar inicializada. <br>
	 * <b>post</b> El area de notificaciones queda actualizada en la vista. <br>
	 */
	public void refrescarAdminReportes() {
		view.getVentanaAdmin().getPestanaReportesAdmin().getAreaNotificaciones().setText(model.mostrarNotificaciones());
	}

	/**
	 * Actualiza el combo de conjuntos en la pestana de administradores de la
	 * ventana SuperAdmin con los conjuntos actuales del sistema. <b>pre</b> La
	 * ventana SuperAdmin debe estar inicializada. <br>
	 * <b>post</b> El JComboBox de conjuntos en la pestana de administradores queda
	 * actualizado. <br>
	 */
	public void actualizarComboConjuntos() {
		view.getVentanaSuperAdmin().getPestanaAdministradores().actualizarConjuntos(model.obtenerTodosLosConjuntos());
	}

	/**
	 * Actualiza el area de visitas activas en la ventana del Vigilante con los
	 * registros de visita que aun no tienen hora de salida. <b>pre</b> La ventana
	 * del Vigilante debe estar inicializada. <br>
	 * <b>post</b> El area de visitas activas queda actualizada en la vista. <br>
	 */
	public void refrescarVisitasActivas() {
		StringBuilder sb = new StringBuilder();
		for (RegistroVisita r : model.obtenerVisitasActivas()) {
			String nombre;
			String cedula;
			if (r.getVisitante() != null) {
				nombre = r.getVisitante().getNombre();
				cedula = r.getVisitante().getCedula();
			} else {
				nombre = "-";
				cedula = "-";
			}
			sb.append("Cedula: ").append(cedula).append(" | Nombre: ").append(nombre).append(" | Entrada: ")
					.append(r.getHoraEntrada()).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaVisitasActivas()
					.setText("No hay visitas activas.");
		} else {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaVisitasActivas().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de todos los visitantes en la ventana del Vigilante con el
	 * listado completo de visitantes registrados. <b>pre</b> La ventana del
	 * Vigilante debe estar inicializada. <br>
	 * <b>post</b> El area de todos los visitantes queda actualizada en la vista.
	 * <br>
	 */
	public void refrescarTodosVisitantes() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Visitante v : model.obtenerTodosLosVisitantes()) {
			sb.append("[").append(i++).append("] ").append(v.getCedula()).append(" | ").append(v.getNombre())
					.append(" | ").append(v.getTipo()).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaTodosVisitantes()
					.setText("No hay visitantes registrados.");
		} else {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaTodosVisitantes().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de vehiculos en la ventana del Vigilante con el listado
	 * completo de vehiculos registrados y su estado de autorizacion. <b>pre</b> La
	 * ventana del Vigilante debe estar inicializada. <br>
	 * <b>post</b> El area de vehiculos queda actualizada en la vista. <br>
	 */
	public void refrescarVehiculos() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Vehiculo v : model.obtenerTodosLosVehiculos()) {
			String apto;
			if (v.getApartamento() != null) {
				apto = v.getApartamento().getNumero();
			} else {
				apto = "-";
			}
			sb.append("[").append(i++).append("] ").append(v.getPlaca()).append(" | ").append(v.getTipo()).append(" | ")
					.append(v.getMarca()).append(" ").append(v.getModelo()).append(" | Apto: ").append(apto);
			if (v.isAutorizado()) {
				sb.append(" | AUTORIZADO");
			} else {
				sb.append(" | NO AUTORIZADO");
			}
			if (v.isTieneRestriccion()) {
				sb.append(" | RESTRINGIDO");
			}
			sb.append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaVigilante().getPestanaVehiculos().getAreaVehiculos()
					.setText("No hay vehiculos registrados.");
		} else {
			view.getVentanaVigilante().getPestanaVehiculos().getAreaVehiculos().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de paquetes pendientes en la ventana del Vigilante con los
	 * paquetes que aun no han sido entregados ni devueltos. <b>pre</b> La ventana
	 * del Vigilante debe estar inicializada. <br>
	 * <b>post</b> El area de paquetes pendientes queda actualizada en la vista.
	 * <br>
	 */
	public void refrescarPaquetesPendientes() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Paquete p : model.obtenerPaquetesPendientes()) {
			String apto;
			if (p.getApartamentoDestino() != null) {
				apto = p.getApartamentoDestino().getNumero();
			} else {
				apto = "-";
			}
			sb.append("[").append(i++).append("] ").append(p.getId()).append(" | ").append(p.getDescripcion())
					.append(" | Apto: ").append(apto).append(" | ").append(p.getEstado()).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaPaquetesPendientes()
					.setText("No hay paquetes pendientes.");
		} else {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaPaquetesPendientes().setText(sb.toString());
		}
	}

	/**
	 * Actualiza el area de todos los paquetes en la ventana del Vigilante con el
	 * historial completo de paquetes registrados. <b>pre</b> La ventana del
	 * Vigilante debe estar inicializada. <br>
	 * <b>post</b> El area de todos los paquetes queda actualizada en la vista. <br>
	 */
	public void refrescarTodosPaquetes() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Paquete p : model.obtenerTodosLosPaquetes()) {
			String apto;
			if (p.getApartamentoDestino() != null) {
				apto = p.getApartamentoDestino().getNumero();
			} else {
				apto = "-";
			}
			sb.append("[").append(i++).append("] ").append(p.getId()).append(" | ").append(p.getDescripcion())
					.append(" | Apto: ").append(apto).append(" | ").append(p.getEstado()).append("\n");
		}
		if (sb.length() == 0) {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaTodosPaquetes()
					.setText("No hay paquetes registrados.");
		} else {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaTodosPaquetes().setText(sb.toString());
		}
	}

	/**
	 * Actualiza simultaneamente las areas de paquetes pendientes y todos los
	 * paquetes en la ventana del Vigilante. <b>pre</b> La ventana del Vigilante
	 * debe estar inicializada. <br>
	 * <b>post</b> Ambas areas de paquetes quedan actualizadas en la vista. <br>
	 */
	public void refrescarPaquetes() {
		StringBuilder sbPendientes = new StringBuilder();
		StringBuilder sbTodos = new StringBuilder();
		for (Paquete p : model.obtenerTodosLosPaquetes()) {
			String destino;
			if (p.getApartamentoDestino() != null) {
				destino = p.getApartamentoDestino().getNumero();
			} else {
				destino = "-";
			}
			sbTodos.append(p.getId()).append(" | Destino: ").append(destino).append(" | Estado: ").append(p.getEstado())
					.append("\n");
			if ("PENDIENTE".equalsIgnoreCase(p.getEstado())) {
				sbPendientes.append(p.getId()).append(" | Destino: ").append(destino).append(" | Recibido: ")
						.append(p.getFechaRecepcion()).append("\n");
			}
		}
		if (sbPendientes.length() == 0) {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaPaquetesPendientes()
					.setText("No hay paquetes pendientes.");
		} else {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaPaquetesPendientes()
					.setText(sbPendientes.toString());
		}
		if (sbTodos.length() == 0) {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaTodosPaquetes()
					.setText("No hay paquetes registrados.");
		} else {
			view.getVentanaVigilante().getPestanaPaquetes().getAreaTodosPaquetes().setText(sbTodos.toString());
		}
	}

	/**
	 * Actualiza simultaneamente las areas de visitas activas y todos los visitantes
	 * en la ventana del Vigilante. <b>pre</b> La ventana del Vigilante debe estar
	 * inicializada. <br>
	 * <b>post</b> Ambas areas de visitantes quedan actualizadas en la vista. <br>
	 */
	public void refrescarVisitantes() {
		StringBuilder sbActivos = new StringBuilder();
		StringBuilder sbTodos = new StringBuilder();
		for (Visitante v : model.obtenerTodosLosVisitantes()) {
			String destino;
			if (v.getApartamentoDestino() != null) {
				destino = v.getApartamentoDestino().getNumero();
			} else {
				destino = "-";
			}
			sbTodos.append(v.getId()).append(" | ").append(v.getNombre()).append(" | Cedula: ").append(v.getCedula())
					.append(" | Destino: ").append(destino).append(" | Activo: ");
			if (v.isActivo()) {
				sbTodos.append("Si");
			} else {
				sbTodos.append("No");
			}
			sbTodos.append("\n");
			if (v.isActivo()) {
				sbActivos.append(v.getId()).append(" | ").append(v.getNombre()).append(" | Cedula: ")
						.append(v.getCedula()).append(" | Telefono: ").append(v.getTelefono()).append(" | Destino: ")
						.append(destino).append("\n");
			}
		}
		if (sbActivos.length() == 0) {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaVisitasActivas()
					.setText("No hay visitas activas.");
		} else {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaVisitasActivas().setText(sbActivos.toString());
		}
		if (sbTodos.length() == 0) {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaTodosVisitantes()
					.setText("No hay visitantes registrados.");
		} else {
			view.getVentanaVigilante().getPestanaVisitantes().getAreaTodosVisitantes().setText(sbTodos.toString());
		}
	}

	/**
	 * Mapea la descripcion de un tipo de reporte seleccionado en el combo de la
	 * vista al tipo interno utilizado por el modelo para generar el reporte.
	 * <b>pre</b> La seleccion debe ser un String no nulo reconocido por el metodo.
	 * <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param seleccion Descripcion del tipo de reporte seleccionada en el combo.
	 *                  Puede ser null
	 * @return String con el tipo interno del reporte (ADMINISTRATIVO, OPERATIVO,
	 *         FINANCIERO, CONVIVENCIA o AMBIENTAL)
	 */
	public String mapearTipoReporte(String seleccion) {
		if (seleccion == null)
			return "ADMINISTRATIVO";
		switch (seleccion) {
		case "Ocupacion de apartamentos":
		case "Residentes activos e inactivos":
		case "Vehiculos registrados":
		case "Uso de parqueaderos":
			return "ADMINISTRATIVO";
		case "Reservas de zonas comunes":
		case "Zonas comunes mas utilizadas":
		case "Reservas canceladas o incumplidas":
		case "Mantenimientos abiertos":
		case "Mantenimientos cerrados":
		case "Mantenimientos vencidos":
		case "Visitantes frecuentes":
		case "Paquetes recibidos y entregados":
			return "OPERATIVO";
		case "Pagos realizados":
		case "Pagos pendientes":
		case "Multas generadas":
			return "FINANCIERO";
		case "Incidentes por tipo y gravedad":
		case "Apartamentos con mayor numero de reportes":
		case "Comportamientos recurrentes":
			return "CONVIVENCIA";
		case "Indicadores de sostenibilidad":
		case "Campanas ambientales y participacion":
			return "AMBIENTAL";
		default:
			return "ADMINISTRATIVO";
		}
	}

	/**
	 * Construye y retorna un String con el listado de vigilantes registrados en el
	 * sistema y su estado de activacion. <b>pre</b> El modelo debe estar
	 * inicializado correctamente. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return String con el listado de vigilantes, o mensaje indicando que no hay
	 *         ninguno
	 */
	public String mostrarVigilantes() {
		StringBuilder sb = new StringBuilder();
		for (Usuario u : model.obtenerUsuariosPorRol("VIGILANTE")) {
			sb.append("Username: ").append(u.getUsername()).append(" | Activo: ");
			if (u.isActivo()) {
				sb.append("Si");
			} else {
				sb.append("No");
			}
			sb.append(" | Conjunto: ");
			if (u.getConjunto() != null) {
				sb.append(u.getConjunto().getNombre());
			} else {
				sb.append("-");
			}
			sb.append("\n");
		}
		if (sb.length() == 0) {
			return "No hay vigilantes registrados.";
		} else {
			return sb.toString();
		}
	}

	/**
	 * Actualiza todas las areas de informacion de la ventana del Arrendatario con
	 * los datos del apartamento especificado: reservas, visitantes, paquetes,
	 * residentes, vehiculos, mascotas, incidentes y mantenimientos. <b>pre</b> La
	 * ventana del Arrendatario debe estar inicializada. <br>
	 * <b>post</b> Todas las areas de informacion de la ventana arrendatario quedan
	 * actualizadas con los datos del apartamento indicado. <br>
	 *
	 * @param idApto Identificador del apartamento del arrendatario en sesion. Si es
	 *               null, solo se actualiza el combo de zonas y se retorna.
	 */
	public void refrescarArrendatario(String idApto) {
		view.getVentanaArrendatario().getPestanaReservas().getAreaZonasLista().setText(model.mostrarZonasComunes());
		view.getVentanaArrendatario().getPestanaReservas().getComboZona().removeAllItems();
		for (ZonaComun z : model.obtenerZonasDisponibles()) {
			view.getVentanaArrendatario().getPestanaReservas().getComboZona()
					.addItem(z.getId() + " - " + z.getNombre());
		}
		if (idApto == null) {
			return;
		}
		StringBuilder sbRes = new StringBuilder();
		for (Reserva r : model.obtenerReservasPorApartamento(idApto)) {
			sbRes.append(r.toString()).append("\n\n");
		}
		if (sbRes.length() > 0) {
			view.getVentanaArrendatario().getPestanaReservas().getAreaMisReservas().setText(sbRes.toString());
		} else {
			view.getVentanaArrendatario().getPestanaReservas().getAreaMisReservas()
					.setText("No hay reservas registradas.");
		}
		StringBuilder sbVis = new StringBuilder();
		for (RegistroVisita rv : model.obtenerTodosLosRegistrosVisita()) {
			if (rv.getVisitante() != null && rv.getVisitante().getApartamentoDestino() != null
					&& idApto.equals(rv.getVisitante().getApartamentoDestino().getId())) {
				sbVis.append(rv.toString()).append("\n\n");
			}
		}
		if (sbVis.length() > 0) {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaMisVisitantes().setText(sbVis.toString());
		} else {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaMisVisitantes()
					.setText("No hay visitantes registrados.");
		}
		StringBuilder sbPaq = new StringBuilder();
		for (Paquete p : model.obtenerPaquetesPorApartamento(idApto)) {
			if ("PENDIENTE".equals(p.getEstado())) {
				sbPaq.append(p.toString()).append("\n\n");
			}
		}
		if (sbPaq.length() > 0) {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaPaquetes().setText(sbPaq.toString());
		} else {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaPaquetes()
					.setText("No hay paquetes pendientes.");
		}
		StringBuilder sbRes2 = new StringBuilder();
		for (Residente r : model.obtenerResidentesPorApartamento(idApto)) {
			sbRes2.append(r.toString()).append("\n\n");
		}
		if (sbRes2.length() > 0) {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaResidentes().setText(sbRes2.toString());
		} else {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaResidentes()
					.setText("No hay residentes registrados.");
		}
		StringBuilder sbVeh = new StringBuilder();
		for (Vehiculo v : model.obtenerVehiculosPorApartamento(idApto)) {
			sbVeh.append(v.toString()).append("\n\n");
		}
		if (sbVeh.length() > 0) {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaVehiculos().setText(sbVeh.toString());
		} else {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaVehiculos()
					.setText("No hay vehiculos registrados.");
		}
		StringBuilder sbMas = new StringBuilder();
		for (Mascota m : model.obtenerMascotasPorApartamento(idApto)) {
			sbMas.append(m.toString()).append("\n\n");
		}
		if (sbMas.length() > 0) {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaMascotas().setText(sbMas.toString());
		} else {
			view.getVentanaArrendatario().getPestanaVisitantes().getAreaMascotas()
					.setText("No hay mascotas registradas.");
		}
		StringBuilder sbInc = new StringBuilder();
		for (Incidente inc : model.obtenerIncidentesPorApartamento(idApto)) {
			sbInc.append(inc.toString()).append("\n\n");
		}
		if (sbInc.length() > 0) {
			view.getVentanaArrendatario().getPestanaOperativa().getAreaIncLista().setText(sbInc.toString());
		} else {
			view.getVentanaArrendatario().getPestanaOperativa().getAreaIncLista()
					.setText("No hay incidentes reportados.");
		}
		view.getVentanaArrendatario().getPestanaOperativa().getAreaManLista()
				.setText(model.mostrarSolicitudesMantenimiento());
	}

	/**
	 * Retorna el identificador del apartamento asociado al arrendatario que tiene
	 * sesion activa en el sistema. <b>pre</b> usuarioEnSesion no debe ser null y
	 * debe corresponder a un arrendatario registrado. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return String con el ID del apartamento del arrendatario, o null si no tiene
	 *         apartamento asignado o no se encuentra en la lista
	 */
	public String obtenerIdAptoArrendatarioEnSesion() {
		for (Arrendatario a : model.obtenerTodosLosArrendatarios()) {
			if (a.getId().equals(usuarioEnSesion.getId())) {
				if (a.getApartamento() != null) {
					return a.getApartamento().getId();
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * Obtiene el identificador del apartamento asociado al propietario que tiene
	 * sesion activa en el sistema. <b>pre</b> usuarioEnSesion no debe ser null y
	 * debe corresponder a un propietario registrado. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return String con el ID del apartamento del propietario en sesion, o null si
	 *         no tiene apartamento asociado
	 */
	public String obtenerIdAptoPropietarioEnSesion() {
		for (Apartamento apto : model.obtenerTodosLosApartamentos()) {
			if (apto.getPropietario() != null && apto.getPropietario().getId().equals(usuarioEnSesion.getId())) {
				return apto.getId();
			}
		}
		return null;
	}

	/**
	 * Actualiza toda la informacion visual del propietario en sesion, incluyendo
	 * reservas, visitantes, paquetes, residentes, vehiculos, mascotas, incidentes,
	 * mantenimientos y estado financiero. <b>pre</b> El ID del apartamento puede
	 * ser null. <br>
	 * <b>post</b> Las areas y componentes graficos de la ventana del propietario
	 * quedan actualizados con la informacion correspondiente. <br>
	 *
	 * @param idApto ID del apartamento asociado al propietario en sesion
	 */
	public void refrescarPropietario(String idApto) {
		view.getVentanaPropietario().getPestanaReservas().getAreaZonasLista().setText(model.mostrarZonasComunes());
		view.getVentanaPropietario().getPestanaReservas().getComboZona().removeAllItems();
		for (ZonaComun z : model.obtenerZonasDisponibles()) {
			view.getVentanaPropietario().getPestanaReservas().getComboZona().addItem(z.getId() + " - " + z.getNombre());
		}
		if (idApto == null) {
			return;
		}

		StringBuilder sbRes = new StringBuilder();
		for (Reserva r : model.obtenerReservasPorApartamento(idApto)) {
			sbRes.append(r.toString()).append("\n\n");
		}
		if (sbRes.length() > 0) {
			view.getVentanaPropietario().getPestanaReservas().getAreaMisReservas().setText(sbRes.toString());
		} else {
			view.getVentanaPropietario().getPestanaReservas().getAreaMisReservas()
					.setText("No hay reservas registradas.");
		}

		StringBuilder sbVis = new StringBuilder();
		for (RegistroVisita rv : model.obtenerTodosLosRegistrosVisita()) {
			if (rv.getVisitante() != null && rv.getVisitante().getApartamentoDestino() != null
					&& idApto.equals(rv.getVisitante().getApartamentoDestino().getId())) {
				sbVis.append(rv.toString()).append("\n\n");
			}
		}
		if (sbVis.length() > 0) {
			view.getVentanaPropietario().getPestanaApartamento().getAreaMisVisitantes().setText(sbVis.toString());
		} else {
			view.getVentanaPropietario().getPestanaApartamento().getAreaMisVisitantes()
					.setText("No hay visitantes registrados.");
		}

		StringBuilder sbPaq = new StringBuilder();
		for (Paquete p : model.obtenerPaquetesPorApartamento(idApto)) {
			if ("PENDIENTE".equals(p.getEstado())) {
				sbPaq.append(p.toString()).append("\n\n");
			}
		}
		if (sbPaq.length() > 0) {
			view.getVentanaPropietario().getPestanaApartamento().getAreaPaquetes().setText(sbPaq.toString());
		} else {
			view.getVentanaPropietario().getPestanaApartamento().getAreaPaquetes()
					.setText("No hay paquetes pendientes.");
		}

		StringBuilder sbRes2 = new StringBuilder();
		for (Residente r : model.obtenerResidentesPorApartamento(idApto)) {
			sbRes2.append(r.toString()).append("\n\n");
		}
		if (sbRes2.length() > 0) {
			view.getVentanaPropietario().getPestanaApartamento().getAreaResidentes().setText(sbRes2.toString());
		} else {
			view.getVentanaPropietario().getPestanaApartamento().getAreaResidentes()
					.setText("No hay residentes registrados.");
		}

		StringBuilder sbVeh = new StringBuilder();
		for (Vehiculo v : model.obtenerVehiculosPorApartamento(idApto)) {
			sbVeh.append(v.toString()).append("\n\n");
		}
		if (sbVeh.length() > 0) {
			view.getVentanaPropietario().getPestanaApartamento().getAreaVehiculos().setText(sbVeh.toString());
		} else {
			view.getVentanaPropietario().getPestanaApartamento().getAreaVehiculos()
					.setText("No hay vehiculos registrados.");
		}

		StringBuilder sbMas = new StringBuilder();
		for (Mascota m : model.obtenerMascotasPorApartamento(idApto)) {
			sbMas.append(m.toString()).append("\n\n");
		}
		if (sbMas.length() > 0) {
			view.getVentanaPropietario().getPestanaApartamento().getAreaMascotas().setText(sbMas.toString());
		} else {
			view.getVentanaPropietario().getPestanaApartamento().getAreaMascotas()
					.setText("No hay mascotas registradas.");
		}

		StringBuilder sbInc = new StringBuilder();
		for (Incidente inc : model.obtenerIncidentesPorApartamento(idApto)) {
			sbInc.append(inc.toString()).append("\n\n");
		}
		if (sbInc.length() > 0) {
			view.getVentanaPropietario().getPestanaOperativa().getAreaIncLista().setText(sbInc.toString());
		} else {
			view.getVentanaPropietario().getPestanaOperativa().getAreaIncLista()
					.setText("No hay incidentes reportados.");
		}

		view.getVentanaPropietario().getPestanaOperativa().getAreaManLista()
				.setText(model.mostrarSolicitudesMantenimiento());
		view.getVentanaPropietario().getPestanaFinancieros().getAreaEstadoCuenta()
				.setText(model.obtenerEstadoCuenta(idApto));

		StringBuilder oblig = new StringBuilder();
		for (Obligacion o : model.obtenerObligacionesPendientes(idApto)) {
			oblig.append(o.toString()).append("\n\n");
		}
		if (oblig.length() > 0) {
			view.getVentanaPropietario().getPestanaFinancieros().getAreaObligaciones().setText(oblig.toString());
		} else {
			view.getVentanaPropietario().getPestanaFinancieros().getAreaObligaciones()
					.setText("No hay obligaciones pendientes.");
		}

		StringBuilder pagos = new StringBuilder();
		for (Pago p : model.obtenerPagosPorApartamento(idApto)) {
			pagos.append(p.toString()).append("\n\n");
		}
		if (pagos.length() > 0) {
			view.getVentanaPropietario().getPestanaFinancieros().getAreaPagos().setText(pagos.toString());
		} else {
			view.getVentanaPropietario().getPestanaFinancieros().getAreaPagos().setText("No hay pagos registrados.");
		}
	}

	/**
	 * Actualiza la informacion visible del propietario desde la ventana del
	 * consejo, incluyendo estado de cuenta, incidentes y apartamentos morosos.
	 * <b>pre</b> El ID del apartamento puede ser null. <br>
	 * <b>post</b> La informacion mostrada en la ventana del consejo queda
	 * actualizada. <br>
	 *
	 * @param idApto ID del apartamento del propietario
	 */
	public void refrescarPropietarioEnConsejo(String idApto) {
		if (idApto != null) {
			view.getVentanaConsejo().getPestanaFinancieros().getAreaEstadoCuenta()
					.setText(model.obtenerEstadoCuenta(idApto));
		}
		view.getVentanaConsejo().getPestanaConsejo().getAreaMorosos().setText(mostrarMorosos());
		view.getVentanaConsejo().getPestanaConsejo().getAreaIncidentes().setText(model.mostrarIncidentes());
	}

	/**
	 * Genera un listado de los apartamentos que poseen obligaciones pendientes.
	 * <b>pre</b> Deben existir apartamentos registrados en el sistema. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return String con la lista de apartamentos morosos o un mensaje indicando
	 *         que no existen morosos registrados
	 */
	public String mostrarMorosos() {
		StringBuilder sb = new StringBuilder();
		for (Apartamento a : model.obtenerApartamentosMorosos()) {
			sb.append("Apto ").append(a.getNumero()).append(" | Torre: ");
			if (a.getTorre() != null) {
				sb.append(a.getTorre().getNombre());
			} else {
				sb.append("-");
			}
			sb.append("\n");
		}
		if (sb.length() == 0) {
			return "No hay apartamentos morosos.";
		} else {
			return sb.toString();
		}
	}

	/**
	 * Actualiza la lista visual de propietarios que pertenecen al consejo
	 * administrativo. <b>pre</b> Deben existir propietarios registrados en el
	 * sistema. <br>
	 * <b>post</b> El area de consejo en la ventana de administrador queda
	 * actualizada. <br>
	 */
	public void refrescarListaPropietariosConsejo() {
		StringBuilder sb = new StringBuilder();
		for (Propietario p : model.obtenerTodosLosPropietarios()) {
			if (p.isEsConsejo()) {
				sb.append("ID: ").append(p.getId()).append(" | ").append(p.getNombre()).append("\n");
			}
		}
		if (sb.length() > 0) {
			view.getVentanaAdmin().getPestanaResidentes().getAreaConsejoLista().setText(sb.toString());
		} else {
			view.getVentanaAdmin().getPestanaResidentes().getAreaConsejoLista()
					.setText("No hay miembros del consejo registrados.");
		}
	}

	/**
	 * Valida que el nombre ingresado cumpla con las reglas establecidas por el
	 * sistema. <b>pre</b> El parametro nombre puede venir vacio o null. <br>
	 * <b>post</b> Si el nombre es valido, no se realiza ninguna accion adicional.
	 * <br>
	 *
	 * @param nombre Nombre a validar
	 * @throws EmptyFieldException    Si el nombre esta vacio
	 * @throws InvalidFormatException Si el nombre contiene numeros o caracteres
	 *                                especiales
	 * @throws InvalidLengthException Si el nombre tiene menos de 6 caracteres
	 */
	public void validarNombre(String nombre)
			throws EmptyFieldException, InvalidFormatException, InvalidLengthException {
		if (nombre == null || nombre.isEmpty())
			throw new EmptyFieldException("El nombre es obligatorio.");
		if (nombre.length() < 6)
			throw new InvalidLengthException("El nombre debe tener al menos 6 caracteres.");
		if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+"))
			throw new InvalidFormatException("El nombre no puede contener numeros ni caracteres especiales.");
	}

	/**
	 * Valida que la cedula ingresada cumpla con el formato permitido por el
	 * sistema. <b>pre</b> El parametro cedula puede venir vacio o null. <br>
	 * <b>post</b> Si la cedula es valida, no se modifica ningun estado del sistema.
	 * <br>
	 *
	 * @param cedula Cedula a validar
	 * @throws EmptyFieldException    Si la cedula esta vacia
	 * @throws InvalidFormatException Si la cedula contiene caracteres invalidos o
	 *                                tiene menos de 6 digitos
	 */
	public void validarCedula(String cedula) throws EmptyFieldException, InvalidFormatException {
		if (cedula == null || cedula.isEmpty())
			throw new EmptyFieldException("La cedula es obligatoria.");
		if (!cedula.matches("\\d+") || cedula.contains(" "))
			throw new InvalidFormatException(
					"La cedula solo debe contener numeros sin espacios ni caracteres especiales.");
		if (cedula.length() < 6)
			throw new InvalidFormatException("La cedula debe tener al menos 6 digitos.");
	}

	/**
	 * Valida que el correo electronico tenga un formato valido y cumpla con las
	 * restricciones establecidas. <b>pre</b> El parametro correo puede venir vacio
	 * o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param correo Correo electronico a validar
	 * @throws EmptyFieldException    Si el correo esta vacio
	 * @throws InvalidFormatException Si el correo tiene un formato invalido
	 * @throws InvalidLengthException Si el correo tiene menos de 10 caracteres
	 */
	public void validarCorreo(String correo)
			throws EmptyFieldException, InvalidFormatException, InvalidLengthException {
		if (correo == null || correo.isEmpty())
			throw new EmptyFieldException("El correo es obligatorio.");
		if (correo.length() < 10)
			throw new InvalidLengthException("El correo debe tener al menos 10 caracteres.");
		if (!correo.contains("@"))
			throw new InvalidFormatException("El correo debe contener '@'.");
		if (correo.contains(" "))
			throw new InvalidFormatException("El correo no puede contener espacios.");
		String[] partes = correo.split("@");
		if (partes.length != 2 || partes[1].isEmpty() || !partes[1].contains("."))
			throw new InvalidFormatException("El correo debe tener un dominio valido (ej: correo@dominio.com).");
	}

	/**
	 * Valida que el telefono ingresado contenga solo numeros y tenga una longitud
	 * valida. <b>pre</b> El telefono puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param telefono Numero telefonico a validar
	 * @throws InvalidFormatException Si el telefono contiene caracteres no
	 *                                numericos
	 * @throws InvalidLengthException Si el telefono no tiene entre 7 y 15 digitos
	 */
	public void validarTelefono(String telefono) throws InvalidFormatException, InvalidLengthException {
		if (telefono == null || telefono.isEmpty())
			return;
		if (!telefono.matches("\\d+"))
			throw new InvalidFormatException("El telefono solo debe contener numeros.");
		if (telefono.length() < 7 || telefono.length() > 15)
			throw new InvalidLengthException("El telefono debe tener entre 7 y 15 digitos.");
	}

	/**
	 * Valida que la placa de un vehiculo cumpla con el formato requerido por el
	 * sistema. <b>pre</b> El parametro placa puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param placa Placa del vehiculo a validar
	 * @throws EmptyFieldException    Si la placa esta vacia
	 * @throws InvalidFormatException Si la placa contiene caracteres invalidos
	 * @throws InvalidLengthException Si la placa tiene menos de 4 caracteres
	 */
	public void validarPlaca(String placa) throws EmptyFieldException, InvalidFormatException, InvalidLengthException {
		if (placa == null || placa.isEmpty())
			throw new EmptyFieldException("La placa es obligatoria.");
		if (placa.length() < 4)
			throw new InvalidLengthException("La placa debe tener al menos 4 caracteres.");
		if (!placa.matches("[a-zA-Z0-9]+"))
			throw new InvalidFormatException("La placa no puede contener caracteres especiales ni espacios.");
	}

	/**
	 * Valida que la fecha ingresada tenga un formato valido y no sea anterior a la
	 * fecha actual. <b>pre</b> El parametro fecha puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param fecha Fecha a validar en formato AAAA-MM-DD
	 * @throws EmptyFieldException  Si la fecha esta vacia
	 * @throws InvalidDateException Si el formato de la fecha es invalido
	 * @throws PastDateException    Si la fecha es anterior al dia actual
	 */
	public void validarFecha(String fecha) throws EmptyFieldException, InvalidDateException, PastDateException {
		if (fecha == null || fecha.isEmpty())
			throw new EmptyFieldException("La fecha es obligatoria.");
		try {
			LocalDate d = LocalDate.parse(fecha);
			if (d.isBefore(LocalDate.now()))
				throw new PastDateException("La fecha no puede ser anterior a hoy.");
		} catch (PastDateException e) {
			throw e;
		} catch (Exception e) {
			throw new InvalidDateException("Formato de fecha invalido. Use AAAA-MM-DD.");
		}
	}

	/**
	 * Valida que la fecha ingresada tenga un formato correcto sin importar si es
	 * pasada o futura. <b>pre</b> El parametro fecha puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param fecha Fecha a validar en formato AAAA-MM-DD
	 * @throws InvalidDateException Si el formato de la fecha es invalido
	 */
	public void validarFechaNeutral(String fecha) throws InvalidDateException {
		if (fecha == null || fecha.isEmpty())
			return;
		try {
			LocalDate.parse(fecha);
		} catch (Exception e) {
			throw new InvalidDateException("Formato de fecha invalido. Use AAAA-MM-DD.");
		}
	}

	/**
	 * Valida que la hora ingresada tenga un formato valido para el campo
	 * especificado. <b>pre</b> El parametro hora puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param hora  Hora a validar en formato HH:mm
	 * @param campo Nombre del campo asociado a la hora
	 * @throws EmptyFieldException    Si la hora esta vacia
	 * @throws InvalidFormatException Si la hora tiene un formato invalido
	 */
	public void validarHora(String hora, String campo) throws EmptyFieldException, InvalidFormatException {
		if (hora == null || hora.isEmpty())
			throw new EmptyFieldException("La hora de " + campo + " es obligatoria.");
		try {
			LocalTime.parse(hora);
		} catch (Exception e) {
			throw new InvalidFormatException("Formato de hora invalido para " + campo + ". Use HH:mm.");
		}
	}

	/**
	 * Valida que el monto ingresado sea numerico y mayor a cero. <b>pre</b> El
	 * parametro montoStr puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param montoStr Monto a validar
	 * @throws EmptyFieldException    Si el monto esta vacio
	 * @throws InvalidFormatException Si el monto no es numerico o es menor o igual
	 *                                a cero
	 */
	public void validarMonto(String montoStr) throws EmptyFieldException, InvalidFormatException {
		if (montoStr == null || montoStr.isEmpty())
			throw new EmptyFieldException("El monto es obligatorio.");
		try {
			double m = Double.parseDouble(montoStr);
			if (m <= 0)
				throw new InvalidFormatException("El monto debe ser mayor a cero.");
		} catch (InvalidFormatException e) {
			throw e;
		} catch (Exception e) {
			throw new InvalidFormatException("El monto debe ser un valor numerico valido.");
		}
	}

	/**
	 * Valida que el indice ingresado sea un numero entero valido y no negativo.
	 * <b>pre</b> El parametro indexStr puede venir vacio o null. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @param indexStr Indice a validar
	 * @throws EmptyFieldException   Si el indice esta vacio
	 * @throws InvalidIndexException Si el indice no es valido o es negativo
	 */
	public void validarIndice(String indexStr) throws EmptyFieldException, InvalidIndexException {
		if (indexStr == null || indexStr.isEmpty())
			throw new EmptyFieldException("El indice es obligatorio.");
		try {
			int i = Integer.parseInt(indexStr);
			if (i < 0)
				throw new InvalidIndexException("El indice no puede ser negativo.");
		} catch (InvalidIndexException e) {
			throw e;
		} catch (Exception e) {
			throw new InvalidIndexException("El indice debe ser un numero entero valido.");
		}
	}
}