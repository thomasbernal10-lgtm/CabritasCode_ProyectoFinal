package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

/**
 * Panel de la vista que representa la pestana de gestion de residentes dentro
 * de la ventana del Administrador. Agrupa en sub-pestanas los formularios y
 * listas para el registro y consulta de propietarios, arrendatarios, residentes
 * adicionales, vehiculos, mascotas, vigilantes y miembros del consejo de
 * administracion. <b>pre</b> Los colores recibidos en el constructor no deben
 * ser null para que la interfaz se construya correctamente. <br>
 * <b>post</b> El panel queda completamente construido con todos sus
 * sub-paneles, campos, botones y areas de lista listos para ser usados por el
 * controlador. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PestanaResidente extends JPanel {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 358476465978237279L;

	/** Campo de texto para el nombre completo del propietario. */
	private JTextField txtPropNombre;

	/** Campo de texto para la cedula del propietario. */
	private JTextField txtPropCedula;

	/** Campo de texto para el correo electronico del propietario. */
	private JTextField txtPropCorreo;

	/** Campo de texto para el telefono de contacto del propietario. */
	private JTextField txtPropTelefono;

	/** Campo de texto para el numero del apartamento asociado al propietario. */
	private JTextField txtPropApto;

	/** Boton para registrar un nuevo propietario en el sistema. */
	private JButton btnPropCrear;

	/** Boton para limpiar el formulario de propietario. */
	private JButton btnPropLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de
	 * propietario.
	 */
	private JLabel lblPropMensaje;

	/** Area de texto que muestra la lista de propietarios registrados. */
	private JTextArea areaPropLista;

	/** Boton para refrescar la lista de propietarios. */
	private JButton btnPropRefrescar;

	/** Campo de texto para el nombre del contacto de emergencia del propietario. */
	private JTextField txtPropContactoEmergencia;

	/**
	 * Campo de texto para el telefono del contacto de emergencia del propietario.
	 */
	private JTextField txtPropTelefonoEmergencia;

	/** Campo de texto para la direccion de correspondencia del propietario. */
	private JTextField txtPropDireccionCorrespondencia;

	/**
	 * Casilla de verificacion que indica si el propietario reside en el
	 * apartamento.
	 */
	private JCheckBox chkPropEsResidente;

	/** Campo de texto para el nombre completo del arrendatario. */
	private JTextField txtArrNombre;

	/** Campo de texto para la cedula del arrendatario. */
	private JTextField txtArrCedula;

	/** Campo de texto para el correo electronico del arrendatario. */
	private JTextField txtArrCorreo;

	/** Campo de texto para el telefono de contacto del arrendatario. */
	private JTextField txtArrTelefono;

	/** Campo de texto para el numero del apartamento asociado al arrendatario. */
	private JTextField txtArrApto;

	/** Boton para registrar un nuevo arrendatario en el sistema. */
	private JButton btnArrCrear;

	/** Boton para limpiar el formulario de arrendatario. */
	private JButton btnArrLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de
	 * arrendatario.
	 */
	private JLabel lblArrMensaje;

	/** Area de texto que muestra la lista de arrendatarios registrados. */
	private JTextArea areaArrLista;

	/**
	 * Campo de texto para el nombre del contacto de emergencia del arrendatario.
	 */
	private JTextField txtArrContactoEmergencia;

	/**
	 * Campo de texto para el telefono del contacto de emergencia del arrendatario.
	 */
	private JTextField txtArrTelefonoEmergencia;

	/**
	 * Campo de texto para la fecha de inicio del contrato de arrendamiento
	 * (AAAA-MM-DD).
	 */
	private JTextField txtArrFechaInicio;

	/**
	 * Campo de texto para la fecha de fin del contrato de arrendamiento
	 * (AAAA-MM-DD).
	 */
	private JTextField txtArrFechaFin;

	/** Boton para refrescar la lista de arrendatarios. */
	private JButton btnArrRefrescar;

	/** Campo de texto para el nombre completo del residente adicional. */
	private JTextField txtResNombre;

	/** Campo de texto para la cedula del residente adicional. */
	private JTextField txtResCedula;

	/** Campo de texto para el correo electronico del residente adicional. */
	private JTextField txtResCorreo;

	/** Campo de texto para el telefono de contacto del residente adicional. */
	private JTextField txtResTelefono;

	/**
	 * Campo de texto para el numero del apartamento asociado al residente
	 * adicional.
	 */
	private JTextField txtResApto;

	/**
	 * Combo que permite seleccionar el tipo de residente adicional (Familiar,
	 * Empleado Domestico).
	 */
	private JComboBox<String> comboResTipo;

	/** Boton para registrar un nuevo residente adicional en el sistema. */
	private JButton btnResCrear;

	/** Boton para limpiar el formulario de residente adicional. */
	private JButton btnResLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de residente.
	 */
	private JLabel lblResMensaje;

	/** Area de texto que muestra la lista de residentes adicionales registrados. */
	private JTextArea areaResLista;

	/** Boton para refrescar la lista de residentes adicionales. */
	private JButton btnResRefrescar;

	/**
	 * Campo de texto para el nombre del contacto de emergencia del residente
	 * adicional.
	 */
	private JTextField txtResContactoEmergencia;

	/**
	 * Campo de texto para el telefono del contacto de emergencia del residente
	 * adicional.
	 */
	private JTextField txtResTelefonoEmergencia;

	/** Campo de texto para la placa o codigo del vehiculo o bicicleta. */
	private JTextField txtVehPlaca;

	/** Campo de texto para la marca del vehiculo. */
	private JTextField txtVehMarca;

	/** Campo de texto para el modelo del vehiculo. */
	private JTextField txtVehModelo;

	/**
	 * Campo de texto para el numero del apartamento al que pertenece el vehiculo.
	 */
	private JTextField txtVehApto;

	/**
	 * Combo que permite seleccionar el tipo de vehiculo (Automovil, Moto,
	 * Bicicleta, Electrico, Otro).
	 */
	private JComboBox<String> comboVehTipo;

	/** Boton para registrar un nuevo vehiculo en el sistema. */
	private JButton btnVehCrear;

	/** Boton para limpiar el formulario de vehiculo. */
	private JButton btnVehLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de vehiculo.
	 */
	private JLabel lblVehMensaje;

	/** Area de texto que muestra la lista de vehiculos registrados. */
	private JTextArea areaVehLista;

	/** Boton para refrescar la lista de vehiculos. */
	private JButton btnVehRefrescar;

	/** Campo de texto para el color del vehiculo. */
	private JTextField txtVehColor;

	/** Campo de texto para el nombre de la mascota. */
	private JTextField txtMasNombre;

	/** Campo de texto para la especie de la mascota. */
	private JTextField txtMasEspecie;

	/** Campo de texto para la raza de la mascota. */
	private JTextField txtMasRaza;

	/**
	 * Campo de texto para el numero del apartamento al que pertenece la mascota.
	 */
	private JTextField txtMasApto;

	/** Campo de texto para el color o descripcion del pelaje de la mascota. */
	private JTextField txtMasColor;

	/**
	 * Campo de texto para la fecha de la ultima vacunacion de la mascota
	 * (AAAA-MM-DD).
	 */
	private JTextField txtMasFechaVacunacion;

	/**
	 * Casilla de verificacion que indica si la mascota tiene las vacunas al dia.
	 */
	private JCheckBox chkMasVacunasAlDia;

	/** Campo de texto para el nombre completo del vigilante. */
	private JTextField txtVigNombre;

	/** Campo de texto para el correo electronico del vigilante. */
	private JTextField txtVigCorreo;

	/** Boton para crear un nuevo vigilante y enviar sus credenciales por correo. */
	private JButton btnVigCrear;

	/** Boton para limpiar el formulario de vigilante. */
	private JButton btnVigLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de vigilante.
	 */
	private JLabel lblVigMensaje;

	/** Area de texto que muestra la lista de vigilantes creados. */
	private JTextArea areaVigLista;

	/** Boton para refrescar la lista de vigilantes. */
	private JButton btnVigRefrescar;

	/** Boton para registrar una nueva mascota en el sistema. */
	private JButton btnMasCrear;

	/** Boton para limpiar el formulario de mascota. */
	private JButton btnMasLimpiar;

	/**
	 * Etiqueta que muestra mensajes de exito o error en el formulario de mascota.
	 */
	private JLabel lblMasMensaje;

	/** Area de texto que muestra la lista de mascotas registradas. */
	private JTextArea areaMasLista;

	/** Boton para refrescar la lista de mascotas. */
	private JButton btnMasRefrescar;

	/**
	 * Campo de texto para ingresar el ID del propietario a designar o remover del
	 * consejo.
	 */
	private JTextField txtConsejoIdPropietario;

	/**
	 * Boton para designar a un propietario como miembro del consejo de
	 * administracion.
	 */
	private JButton btnDesignarConsejo;

	/** Boton para remover a un propietario del consejo de administracion. */
	private JButton btnQuitarConsejo;

	/** Boton para refrescar la lista de propietarios y su estado en el consejo. */
	private JButton btnRefrescarConsejoPropietario;

	/** Etiqueta que muestra mensajes de exito o error en la gestion del consejo. */
	private JLabel lblMensajeConsejo;

	/**
	 * Area de texto que muestra el listado de propietarios y su condicion en el
	 * consejo.
	 */
	private JTextArea areaConsejoLista;

	/** Color de fondo principal del panel. */
	private Color colorPanel;

	/** Color verde oscuro usado para textos, bordes y botones secundarios. */
	private Color colorVerdeOscuro;

	/** Color verde principal usado en botones de accion y elementos destacados. */
	private Color colorVerde;

	/** Color suave usado para etiquetas de texto descriptivo. */
	private Color colorTextoSuave;

	/** Color de fondo de los campos de texto y combos. */
	private Color colorCampoFondo;

	/** Color de borde de los campos de texto y combos. */
	private Color colorCampoBorde;

	/** Color de borde de los paneles y contenedores. */
	private Color colorBorde;

	/** Color blanco usado para texto sobre botones de accion. */
	private Color colorBlanco;

	/** Color rojo usado para mensajes de error. */
	private Color colorRojo;

	/**
	 * Constructor completo. Inicializa la paleta de colores del panel y dispara la
	 * construccion de todos los sub-paneles de la pestana. <b>pre</b> Ninguno de
	 * los parametros de color debe ser null. <br>
	 * <b>post</b> El panel queda completamente construido con todas sus
	 * sub-pestanas, formularios, botones y areas de lista listos para ser usados.
	 * <br>
	 *
	 * @param colorPanel       Color de fondo principal del panel. colorPanel !=
	 *                         null
	 * @param colorVerdeOscuro Color verde oscuro para textos y bordes.
	 *                         colorVerdeOscuro != null
	 * @param colorVerde       Color verde principal para botones de accion.
	 *                         colorVerde != null
	 * @param colorTextoSuave  Color para etiquetas descriptivas. colorTextoSuave !=
	 *                         null
	 * @param colorCampoFondo  Color de fondo de los campos de entrada.
	 *                         colorCampoFondo != null
	 * @param colorCampoBorde  Color de borde de los campos de entrada.
	 *                         colorCampoBorde != null
	 * @param colorBorde       Color de borde de los contenedores. colorBorde !=
	 *                         null
	 * @param colorBlanco      Color blanco para texto sobre botones. colorBlanco !=
	 *                         null
	 * @param colorRojo        Color rojo para mensajes de error. colorRojo != null
	 */
	public PestanaResidente(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
			Color colorCampoFondo, Color colorCampoBorde, Color colorBorde, Color colorBlanco, Color colorRojo) {
		this.colorPanel = colorPanel;
		this.colorVerdeOscuro = colorVerdeOscuro;
		this.colorVerde = colorVerde;
		this.colorTextoSuave = colorTextoSuave;
		this.colorCampoFondo = colorCampoFondo;
		this.colorCampoBorde = colorCampoBorde;
		this.colorBorde = colorBorde;
		this.colorBlanco = colorBlanco;
		this.colorRojo = colorRojo;
		construir();
	}

	/**
	 * Construye el layout principal del panel y agrega todas las sub-pestanas de la
	 * gestion de residentes: Propietarios, Arrendatarios, Residentes, Vehiculos,
	 * Mascotas, Vigilantes y Consejo. <b>pre</b> Los colores deben haber sido
	 * asignados correctamente en el constructor. <br>
	 * <b>post</b> El JTabbedPane con todas las sub-pestanas queda agregado al panel
	 * principal. <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Propietarios", crearSubPanelPropietarios());
		tabs.addTab("Arrendatarios", crearSubPanelArrendatarios());
		tabs.addTab("Residentes", crearSubPanelResidentes());
		tabs.addTab("Vehiculos y Bicicletas", crearSubPanelVehiculos());
		tabs.addTab("Mascotas", crearSubPanelMascotas());
		tabs.addTab("Vigilantes", crearSubPanelVigilantes());
		tabs.addTab("Consejo", crearPanelConsejo());
		add(tabs);
	}

	/**
	 * Crea y retorna el sub-panel de registro y consulta de propietarios. Contiene
	 * el formulario de registro con todos los campos del propietario y el area de
	 * lista de propietarios registrados. <b>pre</b> Los colores deben estar
	 * inicializados. <br>
	 * <b>post</b> El sub-panel de propietarios queda construido con formulario,
	 * botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de propietarios
	 */
	public JPanel crearSubPanelPropietarios() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Propietario", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtPropNombre = crearCampo();
		txtPropCedula = crearCampo();
		txtPropCorreo = crearCampo();
		txtPropTelefono = crearCampo();
		txtPropContactoEmergencia = crearCampo();
		txtPropTelefonoEmergencia = crearCampo();
		txtPropDireccionCorrespondencia = crearCampo();
		txtPropApto = crearCampo();
		agregarCampo(formulario, "Nombre completo:", txtPropNombre);
		agregarCampo(formulario, "Cedula:", txtPropCedula);
		agregarCampo(formulario, "Correo:", txtPropCorreo);
		agregarCampo(formulario, "Telefono:", txtPropTelefono);
		agregarCampo(formulario, "Contacto emergencia:", txtPropContactoEmergencia);
		agregarCampo(formulario, "Tel. emergencia:", txtPropTelefonoEmergencia);
		agregarCampo(formulario, "Direccion correspondencia:", txtPropDireccionCorrespondencia);
		agregarCampo(formulario, "N\u00b0 Apartamento (ej: 402):", txtPropApto);
		chkPropEsResidente = crearCheckBox("Vive en el apartamento");
		formulario.add(chkPropEsResidente);
		formulario.add(Box.createVerticalStrut(10));
		JPanel botones = panelBotones();
		btnPropCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnPropLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnPropCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnPropLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblPropMensaje = crearLblMensaje();
		formulario.add(lblPropMensaje);
		JScrollPane scrollFormulario = new JScrollPane(formulario);
		scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollFormulario.setBorder(null);
		scrollFormulario.setMinimumSize(new Dimension(340, 0));
		scrollFormulario.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
		scrollFormulario.setPreferredSize(new Dimension(340, 0));
		JPanel lista = crearPanelLista("Propietarios registrados");
		areaPropLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnPropRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnPropRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(scrollFormulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el sub-panel de registro y consulta de arrendatarios. Contiene
	 * el formulario de registro con todos los campos del arrendatario, incluyendo
	 * fechas de inicio y fin del contrato, y el area de lista. <b>pre</b> Los
	 * colores deben estar inicializados. <br>
	 * <b>post</b> El sub-panel de arrendatarios queda construido con formulario,
	 * botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de arrendatarios
	 */
	public JPanel crearSubPanelArrendatarios() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Arrendatario",
						0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtArrNombre = crearCampo();
		txtArrCedula = crearCampo();
		txtArrCorreo = crearCampo();
		txtArrTelefono = crearCampo();
		txtArrContactoEmergencia = crearCampo();
		txtArrTelefonoEmergencia = crearCampo();
		txtArrApto = crearCampo();
		txtArrFechaInicio = crearCampo();
		txtArrFechaFin = crearCampo();
		agregarCampo(formulario, "Nombre completo:", txtArrNombre);
		agregarCampo(formulario, "Cedula:", txtArrCedula);
		agregarCampo(formulario, "Correo:", txtArrCorreo);
		agregarCampo(formulario, "Telefono:", txtArrTelefono);
		agregarCampo(formulario, "Contacto emergencia:", txtArrContactoEmergencia);
		agregarCampo(formulario, "Tel. emergencia:", txtArrTelefonoEmergencia);
		agregarCampo(formulario, "N\u00b0 Apartamento (ej: 402):", txtArrApto);
		agregarCampo(formulario, "Inicio contrato (AAAA-MM-DD):", txtArrFechaInicio);
		agregarCampo(formulario, "Fin contrato (AAAA-MM-DD):", txtArrFechaFin);
		JPanel botones = panelBotones();
		btnArrCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnArrLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnArrCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnArrLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblArrMensaje = crearLblMensaje();
		formulario.add(lblArrMensaje);
		JScrollPane scrollFormulario = new JScrollPane(formulario);
		scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollFormulario.setBorder(null);
		scrollFormulario.setMinimumSize(new Dimension(340, 0));
		scrollFormulario.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
		scrollFormulario.setPreferredSize(new Dimension(340, 0));
		JPanel lista = crearPanelLista("Arrendatarios registrados");
		areaArrLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnArrRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnArrRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(scrollFormulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el sub-panel de creacion de vigilantes. Contiene un formulario
	 * con nombre y correo, y muestra la lista de vigilantes creados. Las
	 * credenciales de acceso se envian automaticamente al correo registrado.
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> El sub-panel de vigilantes queda construido con formulario,
	 * botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de vigilantes
	 */
	public JPanel crearSubPanelVigilantes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setMaximumSize(new Dimension(330, Integer.MAX_VALUE));
		formulario.setPreferredSize(new Dimension(330, 0));
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Crear Vigilante", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtVigNombre = crearCampo();
		txtVigCorreo = crearCampo();
		agregarCampo(formulario, "Nombre completo:", txtVigNombre);
		agregarCampo(formulario, "Correo electronico:", txtVigCorreo);
		JLabel nota = new JLabel("* Las credenciales se envian al correo");
		nota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		nota.setForeground(colorTextoSuave);
		nota.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(nota);
		formulario.add(Box.createVerticalStrut(10));
		JPanel botones = panelBotones();
		btnVigCrear = crearBoton("Crear Vigilante", colorVerde, colorBlanco);
		btnVigLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnVigCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnVigLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblVigMensaje = crearLblMensaje();
		formulario.add(lblVigMensaje);
		formulario.add(Box.createVerticalGlue());
		JPanel lista = crearPanelLista("Vigilantes creados");
		areaVigLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnVigRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnVigRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el sub-panel de registro y consulta de residentes adicionales
	 * (familiares o empleados domesticos) asociados a un apartamento. <b>pre</b>
	 * Los colores deben estar inicializados. <br>
	 * <b>post</b> El sub-panel de residentes adicionales queda construido con
	 * formulario, combo de tipo, botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de residentes adicionales
	 */
	public JPanel crearSubPanelResidentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		formulario.setPreferredSize(new Dimension(320, 0));
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Residente", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtResNombre = crearCampo();
		txtResCedula = crearCampo();
		txtResCorreo = crearCampo();
		txtResTelefono = crearCampo();
		txtResContactoEmergencia = crearCampo();
		txtResTelefonoEmergencia = crearCampo();
		txtResApto = crearCampo();
		comboResTipo = new JComboBox<>(new String[] { "Familiar", "Empleado Domestico" });
		estilizarCombo(comboResTipo);
		agregarCampo(formulario, "Nombre completo:", txtResNombre);
		agregarCampo(formulario, "Cedula:", txtResCedula);
		agregarCampo(formulario, "Correo:", txtResCorreo);
		agregarCampo(formulario, "Telefono:", txtResTelefono);
		agregarCampo(formulario, "Contacto emergencia:", txtResContactoEmergencia);
		agregarCampo(formulario, "Tel. emergencia:", txtResTelefonoEmergencia);
		agregarCampo(formulario, "N\u00b0 Apartamento (ej: 402):", txtResApto);
		JLabel lblTipo = crearEtiqueta("Tipo:");
		formulario.add(lblTipo);
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboResTipo);
		formulario.add(Box.createVerticalStrut(10));
		JPanel botones = panelBotones();
		btnResCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnResLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnResCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnResLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblResMensaje = crearLblMensaje();
		formulario.add(lblResMensaje);
		JPanel lista = crearPanelLista("Residentes registrados");
		areaResLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnResRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnResRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el sub-panel de registro y consulta de vehiculos y bicicletas.
	 * Permite registrar el vehiculo con placa, marca, modelo, color, apartamento y
	 * tipo. <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> El sub-panel de vehiculos queda construido con formulario, combo
	 * de tipo, botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de vehiculos
	 */
	public JPanel crearSubPanelVehiculos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		formulario.setPreferredSize(new Dimension(320, 0));
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Registrar Vehiculo / Bicicleta", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtVehPlaca = crearCampo();
		txtVehMarca = crearCampo();
		txtVehModelo = crearCampo();
		txtVehColor = crearCampo();
		txtVehApto = crearCampo();
		comboVehTipo = new JComboBox<>(new String[] { "Automovil", "Moto", "Bicicleta", "Electrico", "Otro" });
		estilizarCombo(comboVehTipo);
		agregarCampo(formulario, "Placa / Codigo:", txtVehPlaca);
		agregarCampo(formulario, "Marca:", txtVehMarca);
		agregarCampo(formulario, "Modelo:", txtVehModelo);
		agregarCampo(formulario, "Color:", txtVehColor);
		agregarCampo(formulario, "N\u00b0 Apartamento (ej: 402):", txtVehApto);
		JLabel lblTipo = crearEtiqueta("Tipo:");
		formulario.add(lblTipo);
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboVehTipo);
		formulario.add(Box.createVerticalStrut(10));
		JPanel botones = panelBotones();
		btnVehCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnVehLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnVehCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnVehLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblVehMensaje = crearLblMensaje();
		formulario.add(lblVehMensaje);
		JPanel lista = crearPanelLista("Vehiculos registrados");
		areaVehLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnVehRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnVehRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el sub-panel de registro y consulta de mascotas. Permite
	 * registrar la mascota con nombre, especie, raza, color, fecha de vacunacion,
	 * apartamento y estado de vacunas. <b>pre</b> Los colores deben estar
	 * inicializados. <br>
	 * <b>post</b> El sub-panel de mascotas queda construido con formulario,
	 * checkbox de vacunas, botones, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de mascotas
	 */
	public JPanel crearSubPanelMascotas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		formulario.setPreferredSize(new Dimension(320, 0));
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Mascota", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtMasNombre = crearCampo();
		txtMasEspecie = crearCampo();
		txtMasRaza = crearCampo();
		txtMasColor = crearCampo();
		txtMasFechaVacunacion = crearCampo();
		txtMasApto = crearCampo();
		chkMasVacunasAlDia = new JCheckBox("Vacunas al dia");
		chkMasVacunasAlDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chkMasVacunasAlDia.setForeground(colorVerdeOscuro);
		chkMasVacunasAlDia.setBackground(colorPanel);
		chkMasVacunasAlDia.setAlignmentX(LEFT_ALIGNMENT);
		agregarCampo(formulario, "Nombre:", txtMasNombre);
		agregarCampo(formulario, "Especie:", txtMasEspecie);
		agregarCampo(formulario, "Raza:", txtMasRaza);
		agregarCampo(formulario, "Color:", txtMasColor);
		agregarCampo(formulario, "Fecha vacunacion (AAAA-MM-DD):", txtMasFechaVacunacion);
		agregarCampo(formulario, "N\u00b0 Apartamento (ej: 402):", txtMasApto);
		formulario.add(chkMasVacunasAlDia);
		formulario.add(Box.createVerticalStrut(10));
		JPanel botones = panelBotones();
		btnMasCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnMasLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnMasCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnMasLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblMasMensaje = crearLblMensaje();
		formulario.add(lblMasMensaje);
		JPanel lista = crearPanelLista("Mascotas registradas");
		areaMasLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnMasRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnMasRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea y retorna el panel de gestion del consejo de administracion. Permite
	 * designar o remover propietarios del consejo mediante su ID, y muestra la
	 * lista de propietarios con su estado en el consejo. <b>pre</b> Los colores
	 * deben estar inicializados. <br>
	 * <b>post</b> El panel del consejo queda construido con campo de ID, botones de
	 * designar y remover, mensaje y lista. <br>
	 *
	 * @return JPanel con el formulario y la lista de miembros del consejo
	 */
	public JPanel crearPanelConsejo() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Gestion Consejo de Administracion", 0, 0, new Font("SansSerif", Font.BOLD, 12),
						colorVerdeOscuro), BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtConsejoIdPropietario = crearCampo();
		agregarCampo(panel, "ID del Propietario:", txtConsejoIdPropietario);
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setOpaque(false);
		panelBotones.setAlignmentX(LEFT_ALIGNMENT);
		btnDesignarConsejo = crearBoton("Designar como Consejo", colorVerde, colorBlanco);
		btnQuitarConsejo = crearBoton("Quitar del Consejo", colorCampoFondo, colorVerdeOscuro);
		btnRefrescarConsejoPropietario = crearBoton("Refrescar lista", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnDesignarConsejo);
		panelBotones.add(Box.createHorizontalStrut(8));
		panelBotones.add(btnQuitarConsejo);
		panelBotones.add(Box.createHorizontalStrut(8));
		panelBotones.add(btnRefrescarConsejoPropietario);
		panel.add(panelBotones);
		panel.add(Box.createVerticalStrut(8));
		lblMensajeConsejo = new JLabel(" ");
		lblMensajeConsejo.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensajeConsejo.setForeground(colorRojo);
		lblMensajeConsejo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblMensajeConsejo);
		panel.add(Box.createVerticalStrut(10));
		JLabel lblLista = new JLabel("Propietarios (ID - Nombre - EsConsejo):");
		lblLista.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblLista.setForeground(colorTextoSuave);
		lblLista.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblLista);
		panel.add(Box.createVerticalStrut(4));
		areaConsejoLista = new JTextArea(6, 0);
		areaConsejoLista.setEditable(false);
		areaConsejoLista.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaConsejoLista.setBackground(colorCampoFondo);
		areaConsejoLista.setForeground(colorVerdeOscuro);
		JScrollPane scroll = new JScrollPane(areaConsejoLista);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		return panel;
	}

	/**
	 * Crea un JPanel contenedor con titulo y un JTextArea de solo lectura en fuente
	 * monoespaciada, utilizado como lista de elementos registrados. <b>pre</b> El
	 * titulo no debe ser null. <br>
	 * <b>post</b> Se retorna un panel con el titulo y el area de texto lista para
	 * mostrar informacion. <br>
	 *
	 * @param titulo Texto que se muestra como titulo del borde del panel. titulo !=
	 *               null
	 * @return JPanel con el area de texto encapsulada en un JScrollPane
	 */
	public JPanel crearPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), titulo, 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 12));
		area.setBackground(colorCampoFondo);
		area.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scroll = new JScrollPane(area);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		return panel;
	}

	/**
	 * Agrega una etiqueta y un campo de texto al panel dado, con el espaciado y
	 * alineacion estandar del formulario. <b>pre</b> El panel, la etiqueta y el
	 * campo no deben ser null. <br>
	 * <b>post</b> La etiqueta y el campo quedan agregados al panel con el espaciado
	 * vertical estandar. <br>
	 *
	 * @param panel    JPanel al que se agrega el campo. panel != null
	 * @param etiqueta Texto descriptivo de la etiqueta del campo. etiqueta != null
	 * @param campo    JTextField que se agrega al formulario. campo != null
	 */
	public void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
		JLabel lbl = crearEtiqueta(etiqueta);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(4));
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		campo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(campo);
		panel.add(Box.createVerticalStrut(10));
	}

	/**
	 * Crea y retorna un JLabel con el estilo estandar de etiquetas de formulario:
	 * fuente SansSerif negrita 12, color suave y alineacion izquierda. <b>pre</b>
	 * El texto no debe ser null. <br>
	 * <b>post</b> Se retorna un JLabel con el estilo estandar listo para agregarse
	 * a un formulario. <br>
	 *
	 * @param texto Texto que se muestra en la etiqueta. texto != null
	 * @return JLabel con el estilo estandar de etiqueta de formulario
	 */
	public JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Crea y retorna un JTextField con el estilo estandar de campos de entrada:
	 * fuente SansSerif 13, fondo de campo, color verde oscuro y borde con relleno
	 * interno. <b>pre</b> Los colores de la clase deben estar inicializados. <br>
	 * <b>post</b> Se retorna un JTextField con el estilo estandar listo para
	 * agregarse a un formulario. <br>
	 *
	 * @return JTextField estilizado con la paleta de colores del panel
	 */
	public JTextField crearCampo() {
		JTextField tf = new JTextField();
		tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf.setBackground(colorCampoFondo);
		tf.setForeground(colorVerdeOscuro);
		tf.setCaretColor(colorVerde);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde, 1),
				BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		return tf;
	}

	/**
	 * Crea y retorna un JButton con el texto, fondo y color de letra indicados,
	 * aplicando el estilo estandar de botones del panel. <b>pre</b> El texto y los
	 * colores no deben ser null. <br>
	 * <b>post</b> Se retorna un JButton estilizado listo para agregarse al
	 * formulario y asignarle un ActionListener. <br>
	 *
	 * @param texto Texto que se muestra en el boton. texto != null
	 * @param fondo Color de fondo del boton. fondo != null
	 * @param letra Color del texto del boton. letra != null
	 * @return JButton con el estilo estandar y los colores indicados
	 */
	public JButton crearBoton(String texto, Color fondo, Color letra) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("SansSerif", Font.BOLD, 12));
		btn.setBackground(fondo);
		btn.setForeground(letra);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setOpaque(true);
		return btn;
	}

	/**
	 * Crea y retorna un JCheckBox con el texto indicado y el estilo estandar del
	 * panel: fuente SansSerif 12, color verde oscuro y alineacion izquierda.
	 * <b>pre</b> El texto no debe ser null. Los colores de la clase deben estar
	 * inicializados. <br>
	 * <b>post</b> Se retorna un JCheckBox estilizado listo para agregarse al
	 * formulario. <br>
	 *
	 * @param texto Texto descriptivo que acompana la casilla. texto != null
	 * @return JCheckBox con el estilo estandar del panel
	 */
	public JCheckBox crearCheckBox(String texto) {
		JCheckBox chk = new JCheckBox(texto);
		chk.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chk.setForeground(colorVerdeOscuro);
		chk.setBackground(colorPanel);
		chk.setAlignmentX(LEFT_ALIGNMENT);
		return chk;
	}

	/**
	 * Crea y retorna un JPanel horizontal transparente con alineacion izquierda,
	 * utilizado como contenedor de botones en los formularios. <b>pre</b> No hay
	 * precondiciones. <br>
	 * <b>post</b> Se retorna un JPanel horizontal vacio listo para recibir botones.
	 * <br>
	 *
	 * @return JPanel horizontal transparente para agrupar botones
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea y retorna un JLabel de mensaje con texto vacio inicial, fuente SansSerif
	 * 11 y color rojo, utilizado para mostrar retroalimentacion al usuario tras una
	 * operacion. <b>pre</b> Los colores de la clase deben estar inicializados. <br>
	 * <b>post</b> Se retorna un JLabel de mensaje listo para ser actualizado por
	 * setMensaje* segun el resultado de la operacion. <br>
	 *
	 * @return JLabel con texto vacio y estilo de mensaje de error
	 */
	public JLabel crearLblMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Aplica el estilo estandar de la paleta de colores a un JComboBox: fuente
	 * SansSerif 13, fondo de campo, color verde oscuro, altura maxima de 32px y
	 * alineacion izquierda. <b>pre</b> El combo no debe ser null. Los colores de la
	 * clase deben estar inicializados. <br>
	 * <b>post</b> El JComboBox queda estilizado con la paleta del panel. <br>
	 *
	 * @param combo JComboBox al que se aplica el estilo. combo != null
	 */
	public void estilizarCombo(JComboBox<String> combo) {
		combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		combo.setBackground(colorCampoFondo);
		combo.setForeground(colorVerdeOscuro);
		combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		combo.setAlignmentX(LEFT_ALIGNMENT);
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de propietario, coloreandolo
	 * en rojo si es un error o en verde oscuro si es un exito. <b>pre</b> El
	 * mensaje no debe ser null. El label debe haber sido inicializado. <br>
	 * <b>post</b> El lblPropMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeProp(String msg, boolean esError) {
		if (esError) {
			lblPropMensaje.setForeground(colorRojo);
		} else {
			lblPropMensaje.setForeground(colorVerdeOscuro);
		}
		lblPropMensaje.setText(msg);
	}

	/**
	 * Limpia todos los campos del formulario de propietario y oculta el mensaje de
	 * retroalimentacion. <b>pre</b> Todos los campos del formulario de propietario
	 * deben haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de propietario quedan vacios y
	 * el mensaje queda en blanco. <br>
	 */
	public void limpiarProp() {
		txtPropNombre.setText("");
		txtPropCedula.setText("");
		txtPropCorreo.setText("");
		txtPropTelefono.setText("");
		txtPropApto.setText("");
		lblPropMensaje.setText(" ");
		txtPropContactoEmergencia.setText("");
		txtPropTelefonoEmergencia.setText("");
		txtPropDireccionCorrespondencia.setText("");
		chkPropEsResidente.setSelected(false);
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de arrendatario,
	 * coloreandolo en rojo si es un error o en verde oscuro si es un exito.
	 * <b>pre</b> El mensaje no debe ser null. El label debe haber sido
	 * inicializado. <br>
	 * <b>post</b> El lblArrMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeArr(String msg, boolean esError) {
		if (esError) {
			lblArrMensaje.setForeground(colorRojo);
		} else {
			lblArrMensaje.setForeground(colorVerdeOscuro);
		}
		lblArrMensaje.setText(msg);
	}

	/**
	 * Limpia todos los campos del formulario de arrendatario y oculta el mensaje de
	 * retroalimentacion. <b>pre</b> Todos los campos del formulario de arrendatario
	 * deben haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de arrendatario quedan vacios
	 * y el mensaje queda en blanco. <br>
	 */
	public void limpiarArr() {
		txtArrNombre.setText("");
		txtArrCedula.setText("");
		txtArrCorreo.setText("");
		txtArrTelefono.setText("");
		txtArrApto.setText("");
		lblArrMensaje.setText(" ");
		txtArrContactoEmergencia.setText("");
		txtArrTelefonoEmergencia.setText("");
		txtArrFechaInicio.setText("");
		txtArrFechaFin.setText("");
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de residente adicional,
	 * coloreandolo en rojo si es un error o en verde oscuro si es un exito.
	 * <b>pre</b> El mensaje no debe ser null. El label debe haber sido
	 * inicializado. <br>
	 * <b>post</b> El lblResMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeRes(String msg, boolean esError) {
		if (esError) {
			lblResMensaje.setForeground(colorRojo);
		} else {
			lblResMensaje.setForeground(colorVerdeOscuro);
		}
		lblResMensaje.setText(msg);
	}

	/**
	 * Limpia todos los campos del formulario de residente adicional y oculta el
	 * mensaje de retroalimentacion. <b>pre</b> Todos los campos del formulario de
	 * residente deben haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de residente quedan vacios y
	 * el mensaje queda en blanco. <br>
	 */
	public void limpiarRes() {
		txtResNombre.setText("");
		txtResCedula.setText("");
		txtResCorreo.setText("");
		txtResTelefono.setText("");
		txtResApto.setText("");
		lblResMensaje.setText(" ");
		txtResContactoEmergencia.setText("");
		txtResTelefonoEmergencia.setText("");
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de vehiculo, coloreandolo en
	 * rojo si es un error o en verde oscuro si es un exito. <b>pre</b> El mensaje
	 * no debe ser null. El label debe haber sido inicializado. <br>
	 * <b>post</b> El lblVehMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeVeh(String msg, boolean esError) {
		if (esError) {
			lblVehMensaje.setForeground(colorRojo);
		} else {
			lblVehMensaje.setForeground(colorVerdeOscuro);
		}
		lblVehMensaje.setText(msg);
	}

	/**
	 * Limpia todos los campos del formulario de vehiculo y oculta el mensaje de
	 * retroalimentacion. <b>pre</b> Todos los campos del formulario de vehiculo
	 * deben haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de vehiculo quedan vacios y el
	 * mensaje queda en blanco. <br>
	 */
	public void limpiarVeh() {
		txtVehPlaca.setText("");
		txtVehMarca.setText("");
		txtVehModelo.setText("");
		txtVehApto.setText("");
		lblVehMensaje.setText(" ");
		txtVehColor.setText("");
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de mascota, coloreandolo en
	 * rojo si es un error o en verde oscuro si es un exito. <b>pre</b> El mensaje
	 * no debe ser null. El label debe haber sido inicializado. <br>
	 * <b>post</b> El lblMasMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeMas(String msg, boolean esError) {
		if (esError) {
			lblMasMensaje.setForeground(colorRojo);
		} else {
			lblMasMensaje.setForeground(colorVerdeOscuro);
		}
		lblMasMensaje.setText(msg);
	}

	/**
	 * Limpia todos los campos del formulario de mascota y oculta el mensaje de
	 * retroalimentacion. <b>pre</b> Todos los campos del formulario de mascota
	 * deben haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de mascota quedan vacios, el
	 * checkbox de vacunas se desmarca y el mensaje queda en blanco. <br>
	 */
	public void limpiarMas() {
		txtMasNombre.setText("");
		txtMasEspecie.setText("");
		txtMasRaza.setText("");
		txtMasApto.setText("");
		lblMasMensaje.setText(" ");
		txtMasColor.setText("");
		txtMasFechaVacunacion.setText("");
		chkMasVacunasAlDia.setSelected(false);
	}

	/**
	 * Limpia los campos del formulario de vigilante y oculta el mensaje de
	 * retroalimentacion. <b>pre</b> Los campos del formulario de vigilante deben
	 * haber sido inicializados. <br>
	 * <b>post</b> Los campos de texto del formulario de vigilante quedan vacios y
	 * el mensaje queda en blanco. <br>
	 */
	public void limpiarVig() {
		txtVigNombre.setText("");
		txtVigCorreo.setText("");
		lblVigMensaje.setText(" ");
	}

	/**
	 * Muestra un mensaje en la etiqueta del panel de gestion del consejo,
	 * coloreandolo en rojo si es un error o en verde oscuro si es un exito.
	 * <b>pre</b> El mensaje no debe ser null. El label debe haber sido
	 * inicializado. <br>
	 * <b>post</b> El lblMensajeConsejo queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeConsejo(String msg, boolean esError) {
		if (esError) {
			lblMensajeConsejo.setForeground(colorRojo);
		} else {
			lblMensajeConsejo.setForeground(colorVerdeOscuro);
		}
		lblMensajeConsejo.setText(msg);
	}

	/**
	 * Muestra un mensaje en la etiqueta del formulario de vigilante, coloreandolo
	 * en rojo si es un error o en verde oscuro si es un exito. <b>pre</b> El
	 * mensaje no debe ser null. El label debe haber sido inicializado. <br>
	 * <b>post</b> El lblVigMensaje queda actualizado con el texto y color
	 * correspondiente. <br>
	 *
	 * @param msg     Texto del mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es un error, false si es un exito
	 */
	public void setMensajeVig(String msg, boolean esError) {
		if (esError) {
			lblVigMensaje.setForeground(colorRojo);
		} else {
			lblVigMensaje.setForeground(colorVerdeOscuro);
		}
		lblVigMensaje.setText(msg);
	}

	//Getters

	/**
	 * Retorna el campo de texto del nombre del propietario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del nombre del propietario
	 */
	public JTextField getTxtPropNombre() {
		return txtPropNombre;
	}

	/**
	 * Retorna el campo de texto de la cedula del propietario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la cedula del propietario
	 */
	public JTextField getTxtPropCedula() {
		return txtPropCedula;
	}

	/**
	 * Retorna el campo de texto del correo del propietario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del correo del propietario
	 */
	public JTextField getTxtPropCorreo() {
		return txtPropCorreo;
	}

	/**
	 * Retorna el campo de texto del telefono del propietario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono del propietario
	 */
	public JTextField getTxtPropTelefono() {
		return txtPropTelefono;
	}

	/**
	 * Retorna el campo de texto del numero de apartamento del propietario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento del propietario
	 */
	public JTextField getTxtPropApto() {
		return txtPropApto;
	}

	/**
	 * Retorna el boton de registrar propietario. <b>pre</b> El objeto debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para registrar un propietario
	 */
	public JButton getBtnPropCrear() {
		return btnPropCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de propietario. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de propietario
	 */
	public JButton getBtnPropLimpiar() {
		return btnPropLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de propietarios registrados. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de propietarios
	 */
	public JTextArea getAreaPropLista() {
		return areaPropLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de propietarios. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de propietarios
	 */
	public JButton getBtnPropRefrescar() {
		return btnPropRefrescar;
	}

	/**
	 * Retorna el campo de texto del nombre del arrendatario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del nombre del arrendatario
	 */
	public JTextField getTxtArrNombre() {
		return txtArrNombre;
	}

	/**
	 * Retorna el campo de texto de la cedula del arrendatario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la cedula del arrendatario
	 */
	public JTextField getTxtArrCedula() {
		return txtArrCedula;
	}

	/**
	 * Retorna el campo de texto del correo del arrendatario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del correo del arrendatario
	 */
	public JTextField getTxtArrCorreo() {
		return txtArrCorreo;
	}

	/**
	 * Retorna el campo de texto del telefono del arrendatario. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono del arrendatario
	 */
	public JTextField getTxtArrTelefono() {
		return txtArrTelefono;
	}

	/**
	 * Retorna el campo de texto del numero de apartamento del arrendatario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento del arrendatario
	 */
	public JTextField getTxtArrApto() {
		return txtArrApto;
	}

	/**
	 * Retorna el boton de registrar arrendatario. <b>pre</b> El objeto debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para registrar un arrendatario
	 */
	public JButton getBtnArrCrear() {
		return btnArrCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de arrendatario. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de arrendatario
	 */
	public JButton getBtnArrLimpiar() {
		return btnArrLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de arrendatarios registrados.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de arrendatarios
	 */
	public JTextArea getAreaArrLista() {
		return areaArrLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de arrendatarios. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de arrendatarios
	 */
	public JButton getBtnArrRefrescar() {
		return btnArrRefrescar;
	}

	/**
	 * Retorna el campo de texto del nombre del residente adicional. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del nombre del residente adicional
	 */
	public JTextField getTxtResNombre() {
		return txtResNombre;
	}

	/**
	 * Retorna el campo de texto de la cedula del residente adicional. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la cedula del residente adicional
	 */
	public JTextField getTxtResCedula() {
		return txtResCedula;
	}

	/**
	 * Retorna el campo de texto del correo del residente adicional. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del correo del residente adicional
	 */
	public JTextField getTxtResCorreo() {
		return txtResCorreo;
	}

	/**
	 * Retorna el campo de texto del telefono del residente adicional. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono del residente adicional
	 */
	public JTextField getTxtResTelefono() {
		return txtResTelefono;
	}

	/**
	 * Retorna el campo de texto del numero de apartamento del residente adicional.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento del residente adicional
	 */
	public JTextField getTxtResApto() {
		return txtResApto;
	}

	/**
	 * Retorna el combo de seleccion del tipo de residente adicional. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JComboBox con las opciones de tipo de residente
	 */
	public JComboBox<String> getComboResTipo() {
		return comboResTipo;
	}

	/**
	 * Retorna el boton de registrar residente adicional. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para registrar un residente adicional
	 */
	public JButton getBtnResCrear() {
		return btnResCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de residente adicional. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de residente adicional
	 */
	public JButton getBtnResLimpiar() {
		return btnResLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de residentes adicionales. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de residentes adicionales
	 */
	public JTextArea getAreaResLista() {
		return areaResLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de residentes adicionales. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de residentes adicionales
	 */
	public JButton getBtnResRefrescar() {
		return btnResRefrescar;
	}

	/**
	 * Retorna el campo de texto de la placa del vehiculo. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la placa del vehiculo
	 */
	public JTextField getTxtVehPlaca() {
		return txtVehPlaca;
	}

	/**
	 * Retorna el campo de texto de la marca del vehiculo. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la marca del vehiculo
	 */
	public JTextField getTxtVehMarca() {
		return txtVehMarca;
	}

	/**
	 * Retorna el campo de texto del modelo del vehiculo. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del modelo del vehiculo
	 */
	public JTextField getTxtVehModelo() {
		return txtVehModelo;
	}

	/**
	 * Retorna el campo de texto del numero de apartamento del vehiculo. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento del vehiculo
	 */
	public JTextField getTxtVehApto() {
		return txtVehApto;
	}

	/**
	 * Retorna el combo de seleccion del tipo de vehiculo. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JComboBox con las opciones de tipo de vehiculo
	 */
	public JComboBox<String> getComboVehTipo() {
		return comboVehTipo;
	}

	/**
	 * Retorna el boton de registrar vehiculo. <b>pre</b> El objeto debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para registrar un vehiculo
	 */
	public JButton getBtnVehCrear() {
		return btnVehCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de vehiculo. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de vehiculo
	 */
	public JButton getBtnVehLimpiar() {
		return btnVehLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de vehiculos registrados. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de vehiculos
	 */
	public JTextArea getAreaVehLista() {
		return areaVehLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de vehiculos. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de vehiculos
	 */
	public JButton getBtnVehRefrescar() {
		return btnVehRefrescar;
	}

	/**
	 * Retorna el campo de texto del nombre de la mascota. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del nombre de la mascota
	 */
	public JTextField getTxtMasNombre() {
		return txtMasNombre;
	}

	/**
	 * Retorna el campo de texto de la especie de la mascota. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la especie de la mascota
	 */
	public JTextField getTxtMasEspecie() {
		return txtMasEspecie;
	}

	/**
	 * Retorna el campo de texto de la raza de la mascota. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la raza de la mascota
	 */
	public JTextField getTxtMasRaza() {
		return txtMasRaza;
	}

	/**
	 * Retorna el campo de texto del numero de apartamento de la mascota. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento de la mascota
	 */
	public JTextField getTxtMasApto() {
		return txtMasApto;
	}

	/**
	 * Retorna el boton de registrar mascota. <b>pre</b> El objeto debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para registrar una mascota
	 */
	public JButton getBtnMasCrear() {
		return btnMasCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de mascota. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de mascota
	 */
	public JButton getBtnMasLimpiar() {
		return btnMasLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de mascotas registradas. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de mascotas
	 */
	public JTextArea getAreaMasLista() {
		return areaMasLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de mascotas. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de mascotas
	 */
	public JButton getBtnMasRefrescar() {
		return btnMasRefrescar;
	}

	/**
	 * Retorna el campo de texto del nombre del vigilante. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del nombre del vigilante
	 */
	public JTextField getTxtVigNombre() {
		return txtVigNombre;
	}

	/**
	 * Retorna el campo de texto del correo del vigilante. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del correo del vigilante
	 */
	public JTextField getTxtVigCorreo() {
		return txtVigCorreo;
	}

	/**
	 * Retorna el boton de crear vigilante. <b>pre</b> El objeto debe estar
	 * instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para crear un vigilante
	 */
	public JButton getBtnVigCrear() {
		return btnVigCrear;
	}

	/**
	 * Retorna el boton de limpiar el formulario de vigilante. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para limpiar el formulario de vigilante
	 */
	public JButton getBtnVigLimpiar() {
		return btnVigLimpiar;
	}

	/**
	 * Retorna el area de texto con la lista de vigilantes creados. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de vigilantes
	 */
	public JTextArea getAreaVigLista() {
		return areaVigLista;
	}

	/**
	 * Retorna el boton de refrescar la lista de vigilantes. <b>pre</b> El objeto
	 * debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de vigilantes
	 */
	public JButton getBtnVigRefrescar() {
		return btnVigRefrescar;
	}

	/**
	 * Retorna el campo de texto del ID del propietario en la gestion del consejo.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del ID del propietario para el consejo
	 */
	public JTextField getTxtConsejoIdPropietario() {
		return txtConsejoIdPropietario;
	}

	/**
	 * Retorna el boton de designar a un propietario como miembro del consejo.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para designar un propietario al consejo
	 */
	public JButton getBtnDesignarConsejo() {
		return btnDesignarConsejo;
	}

	/**
	 * Retorna el boton de remover a un propietario del consejo. <b>pre</b> El
	 * objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para quitar un propietario del consejo
	 */
	public JButton getBtnQuitarConsejo() {
		return btnQuitarConsejo;
	}

	/**
	 * Retorna el boton de refrescar la lista de propietarios en el consejo.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton para refrescar la lista de propietarios del consejo
	 */
	public JButton getBtnRefrescarConsejoPropietarios() {
		return btnRefrescarConsejoPropietario;
	}

	/**
	 * Retorna el area de texto con el listado de propietarios y su estado en el
	 * consejo. <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea con la lista de propietarios del consejo
	 */
	public JTextArea getAreaConsejoLista() {
		return areaConsejoLista;
	}

	/**
	 * Retorna el campo de texto del contacto de emergencia del propietario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del contacto de emergencia del propietario
	 */
	public JTextField getTxtPropContactoEmergencia() {
		return txtPropContactoEmergencia;
	}

	/**
	 * Retorna el campo de texto del telefono de emergencia del propietario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono de emergencia del propietario
	 */
	public JTextField getTxtPropTelefonoEmergencia() {
		return txtPropTelefonoEmergencia;
	}

	/**
	 * Retorna el campo de texto de la direccion de correspondencia del propietario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la direccion de correspondencia del propietario
	 */
	public JTextField getTxtPropDireccionCorrespondencia() {
		return txtPropDireccionCorrespondencia;
	}

	/**
	 * Retorna la casilla de verificacion que indica si el propietario reside en el
	 * apartamento. <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JCheckBox de residencia del propietario
	 */
	public JCheckBox getChkPropEsResidente() {
		return chkPropEsResidente;
	}

	/**
	 * Retorna el campo de texto del contacto de emergencia del arrendatario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del contacto de emergencia del arrendatario
	 */
	public JTextField getTxtArrContactoEmergencia() {
		return txtArrContactoEmergencia;
	}

	/**
	 * Retorna el campo de texto del telefono de emergencia del arrendatario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono de emergencia del arrendatario
	 */
	public JTextField getTxtArrTelefonoEmergencia() {
		return txtArrTelefonoEmergencia;
	}

	/**
	 * Retorna el campo de texto de la fecha de inicio del contrato del
	 * arrendatario. <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la fecha de inicio del contrato
	 */
	public JTextField getTxtArrFechaInicio() {
		return txtArrFechaInicio;
	}

	/**
	 * Retorna el campo de texto de la fecha de fin del contrato del arrendatario.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la fecha de fin del contrato
	 */
	public JTextField getTxtArrFechaFin() {
		return txtArrFechaFin;
	}

	/**
	 * Retorna el campo de texto del contacto de emergencia del residente adicional.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del contacto de emergencia del residente adicional
	 */
	public JTextField getTxtResContactoEmergencia() {
		return txtResContactoEmergencia;
	}

	/**
	 * Retorna el campo de texto del telefono de emergencia del residente adicional.
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del telefono de emergencia del residente adicional
	 */
	public JTextField getTxtResTelefonoEmergencia() {
		return txtResTelefonoEmergencia;
	}

	/**
	 * Retorna el campo de texto del color del vehiculo. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del color del vehiculo
	 */
	public JTextField getTxtVehColor() {
		return txtVehColor;
	}

	/**
	 * Retorna el campo de texto del color de la mascota. <b>pre</b> El objeto debe
	 * estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del color de la mascota
	 */
	public JTextField getTxtMasColor() {
		return txtMasColor;
	}

	/**
	 * Retorna el campo de texto de la fecha de vacunacion de la mascota. <b>pre</b>
	 * El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField de la fecha de vacunacion de la mascota
	 */
	public JTextField getTxtMasFechaVacunacion() {
		return txtMasFechaVacunacion;
	}

	/**
	 * Retorna la casilla de verificacion que indica si la mascota tiene las vacunas
	 * al dia. <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JCheckBox del estado de vacunacion de la mascota
	 */
	public JCheckBox getChkMasVacunasAlDia() {
		return chkMasVacunasAlDia;
	}
}