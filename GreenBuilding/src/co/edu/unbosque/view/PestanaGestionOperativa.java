package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

/**
 * Panel grafico encargado de la gestion operativa del conjunto residencial.
 *
 * Agrupa en subpestanas la visualizacion y administracion de visitantes,
 * paquetes, solicitudes de mantenimiento e incidentes, permitiendo al
 * administrador consultar, registrar y actualizar cada modulo desde una unica
 * interfaz.
 *
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaGestionOperativa extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -8790955200126066592L;

	/**
	 * Area de texto que muestra el historial de visitantes registrados.
	 */
	private JTextArea areaVisLista;

	/**
	 * Boton para refrescar la lista de visitantes.
	 */
	private JButton btnVisRefrescar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con paquetes.
	 */
	private JLabel lblPaqMensaje;

	/**
	 * Area de texto que muestra el listado de paquetes.
	 */
	private JTextArea areaPaqLista;

	/**
	 * Boton para refrescar la lista de paquetes.
	 */
	private JButton btnPaqRefrescar;

	/**
	 * Campo de texto para ingresar la descripcion de una solicitud de
	 * mantenimiento.
	 */
	private JTextField txtManDescripcion;

	/**
	 * Campo de texto para ingresar la ubicacion de una solicitud de mantenimiento.
	 */
	private JTextField txtManUbicacion;

	/**
	 * ComboBox para seleccionar el tipo de mantenimiento.
	 */
	private JComboBox<String> comboManTipo;

	/**
	 * ComboBox para seleccionar la prioridad de la solicitud de mantenimiento.
	 */
	private JComboBox<String> comboManPrioridad;

	/**
	 * Boton para registrar una nueva solicitud de mantenimiento.
	 */
	private JButton btnManCrear;

	/**
	 * Boton para limpiar el formulario de mantenimiento.
	 */
	private JButton btnManLimpiar;

	/**
	 * Campo de texto para ingresar el index de la solicitud de mantenimiento a
	 * actualizar.
	 */
	private JTextField txtManIdActualizar;

	/**
	 * Campo de texto para ingresar el nombre del tecnico asignado.
	 */
	private JTextField txtManTecnico;

	/**
	 * ComboBox para seleccionar el nuevo estado de la solicitud de mantenimiento.
	 */
	private JComboBox<String> comboManEstadoUpdate;

	/**
	 * Boton para aplicar la actualizacion de una solicitud de mantenimiento.
	 */
	private JButton btnManActualizar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con mantenimiento.
	 */
	private JLabel lblManMensaje;

	/**
	 * Area de texto que muestra el listado de solicitudes de mantenimiento.
	 */
	private JTextArea areaManLista;

	/**
	 * Boton para refrescar la lista de solicitudes de mantenimiento.
	 */
	private JButton btnManRefrescar;

	/**
	 * Campo de texto para ingresar la descripcion de un incidente.
	 */
	private JTextField txtIncDescripcion;

	/**
	 * Campo de texto para ingresar el numero de apartamento involucrado en el
	 * incidente.
	 */
	private JTextField txtIncApto;

	/**
	 * ComboBox para seleccionar el tipo de incidente.
	 */
	private JComboBox<String> comboIncTipo;

	/**
	 * ComboBox para seleccionar la gravedad del incidente.
	 */
	private JComboBox<String> comboIncGravedad;

	/**
	 * Boton para registrar un nuevo incidente.
	 */
	private JButton btnIncCrear;

	/**
	 * Boton para limpiar el formulario de incidentes.
	 */
	private JButton btnIncLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con el registro de incidentes.
	 */
	private JLabel lblIncMensaje;

	/**
	 * Area de texto que muestra el listado de incidentes registrados.
	 */
	private JTextArea areaIncLista;

	/**
	 * Boton para refrescar la lista de incidentes.
	 */
	private JButton btnIncRefrescar;

	/**
	 * Campo de texto para ingresar el index del incidente a gestionar.
	 */
	private JTextField txtIncIdGestionar;

	/**
	 * Campo de texto para ingresar la resolucion u observacion del incidente.
	 */
	private JTextField txtIncResolucion;

	/**
	 * ComboBox para seleccionar la accion a aplicar sobre un incidente existente.
	 */
	private JComboBox<String> comboIncAccion;

	/**
	 * Boton para aplicar la accion de gestion sobre un incidente.
	 */
	private JButton btnIncGestionar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con la gestion de incidentes.
	 */
	private JLabel lblIncMensajeGestion;

	/**
	 * Color principal del panel.
	 */
	private Color colorPanel;

	/**
	 * Color verde oscuro utilizado en la interfaz.
	 */
	private Color colorVerdeOscuro;

	/**
	 * Color verde principal utilizado en la interfaz.
	 */
	private Color colorVerde;

	/**
	 * Color utilizado para textos suaves.
	 */
	private Color colorTextoSuave;

	/**
	 * Color de fondo de los campos.
	 */
	private Color colorCampoFondo;

	/**
	 * Color de borde de los campos.
	 */
	private Color colorCampoBorde;

	/**
	 * Color utilizado para bordes generales.
	 */
	private Color colorBorde;

	/**
	 * Color blanco utilizado en componentes graficos.
	 */
	private Color colorBlanco;

	/**
	 * Color rojo utilizado para errores y alertas.
	 */
	private Color colorRojo;

	/**
	 * Construye la pestana de gestion operativa.
	 *
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> El panel queda completamente inicializado con sus subpestanas.
	 *
	 * @param colorPanel       Color principal del panel
	 * @param colorVerdeOscuro Color verde oscuro
	 * @param colorVerde       Color verde principal
	 * @param colorTextoSuave  Color para textos suaves
	 * @param colorCampoFondo  Color de fondo de campos
	 * @param colorCampoBorde  Color de borde de campos
	 * @param colorBorde       Color de bordes generales
	 * @param colorBlanco      Color blanco
	 * @param colorRojo        Color rojo para errores
	 */
	public PestanaGestionOperativa(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * Construye la estructura principal del panel con sus subpestanas.
	 *
	 * <b>post</b> El panel principal queda configurado con las pestanas de
	 * Visitantes, Paquetes, Mantenimiento e Incidentes.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Visitantes", crearSubPanelVisitantes());
		tabs.addTab("Paquetes", crearSubPanelPaquetes());
		tabs.addTab("Mantenimiento", crearSubPanelMantenimiento());
		tabs.addTab("Incidentes", crearSubPanelIncidentes());
		add(tabs);
	}

	/**
	 * Crea el subpanel de visualizacion del historial de visitantes.
	 *
	 * <b>post</b> Se inicializa {@code areaVisLista} y {@code btnVisRefrescar}.
	 *
	 * @return JPanel configurado con la lista de visitantes y el boton de refresco
	 */
	public JPanel crearSubPanelVisitantes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel lblInfo = new JLabel("El registro de entradas y salidas lo realiza el Vigilante desde su cuenta.");
		lblInfo.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblInfo.setForeground(colorTextoSuave);
		lblInfo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblInfo);
		panel.add(Box.createVerticalStrut(12));

		JPanel panelLista = crearPanelLista("Historial de visitantes registrados");
		areaVisLista = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelRef = panelBotones();
		btnVisRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRef.add(btnVisRefrescar);
		panel.add(panelRef);

		return panel;
	}

	/**
	 * Crea el subpanel de visualizacion del historial de paquetes.
	 *
	 * <b>post</b> Se inicializa {@code areaPaqLista} y {@code btnPaqRefrescar}.
	 *
	 * @return JPanel configurado con la lista de paquetes y el boton de refresco
	 */
	public JPanel crearSubPanelPaquetes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel lblInfo = new JLabel("Historial de paquetes recibidos en el conjunto.");
		lblInfo.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblInfo.setForeground(colorTextoSuave);
		lblInfo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblInfo);
		panel.add(Box.createVerticalStrut(12));

		JPanel panelLista = crearPanelLista("Todos los paquetes");
		areaPaqLista = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelRef = panelBotones();
		btnPaqRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRef.add(btnPaqRefrescar);
		panel.add(panelRef);

		return panel;
	}

	/**
	 * Crea el subpanel de gestion de solicitudes de mantenimiento.
	 *
	 * Incluye un formulario para crear nuevas solicitudes, un formulario para
	 * actualizar solicitudes existentes y una lista con todas las solicitudes.
	 *
	 * <b>post</b> Se inicializan todos los campos, combos y botones relacionados
	 * con mantenimiento.
	 *
	 * @return JPanel configurado con los formularios y lista de mantenimiento
	 */
	public JPanel crearSubPanelMantenimiento() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel izquierda = new JPanel();
		izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
		izquierda.setBackground(colorPanel);
		izquierda.setMaximumSize(new Dimension(330, Integer.MAX_VALUE));
		izquierda.setPreferredSize(new Dimension(330, 0));

		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Nueva Solicitud de Mantenimiento", 0, 0, new Font("SansSerif", Font.BOLD, 12),
						colorVerdeOscuro), BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtManDescripcion = crearCampo();
		txtManUbicacion = crearCampo();
		comboManTipo = new JComboBox<>(new String[] { "Electrico", "Hidraulico", "Ascensor", "Jardineria", "Aseo",
				"Infraestructura", "Otro" });
		estilizarCombo(comboManTipo);
		comboManPrioridad = new JComboBox<>(new String[] { "ALTA", "MEDIA", "BAJA" });
		estilizarCombo(comboManPrioridad);

		agregarCampo(formulario, "Descripcion:", txtManDescripcion);
		agregarCampo(formulario, "Ubicacion:", txtManUbicacion);

		formulario.add(crearEtiqueta("Tipo:"));
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboManTipo);
		formulario.add(Box.createVerticalStrut(10));
		formulario.add(crearEtiqueta("Prioridad:"));
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboManPrioridad);
		formulario.add(Box.createVerticalStrut(10));

		JPanel botones = panelBotones();
		btnManCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnManLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnManCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnManLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblManMensaje = crearLblMensaje();
		formulario.add(lblManMensaje);

		JPanel panelActualizar = new JPanel();
		panelActualizar.setLayout(new BoxLayout(panelActualizar, BoxLayout.Y_AXIS));
		panelActualizar.setBackground(colorPanel);
		panelActualizar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Gestionar Solicitud", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtManIdActualizar = crearCampo();
		txtManTecnico = crearCampo();
		comboManEstadoUpdate = new JComboBox<>(new String[] { "EN_PROGRESO", "COMPLETADA", "RECHAZADA" });
		estilizarCombo(comboManEstadoUpdate);

		agregarCampo(panelActualizar, "Index solicitud (ver lista):", txtManIdActualizar);
		agregarCampo(panelActualizar, "Tecnico a asignar:", txtManTecnico);

		panelActualizar.add(crearEtiqueta("Nuevo estado:"));
		panelActualizar.add(Box.createVerticalStrut(4));
		panelActualizar.add(comboManEstadoUpdate);
		panelActualizar.add(Box.createVerticalStrut(10));

		JLabel lblNotaMan = new JLabel("* El index lo encuentras en la lista de solicitudes");
		lblNotaMan.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNotaMan.setForeground(colorTextoSuave);
		lblNotaMan.setAlignmentX(LEFT_ALIGNMENT);
		panelActualizar.add(lblNotaMan);

		JPanel botActualizar = panelBotones();
		btnManActualizar = crearBoton("Aplicar", colorVerde, colorBlanco);
		botActualizar.add(btnManActualizar);
		panelActualizar.add(botActualizar);

		JScrollPane scrollFormulario = new JScrollPane(formulario);
		scrollFormulario.setBorder(null);
		scrollFormulario.getVerticalScrollBar().setUnitIncrement(16);
		izquierda.add(scrollFormulario);
		izquierda.add(Box.createVerticalStrut(15));
		izquierda.add(panelActualizar);
		izquierda.add(Box.createVerticalGlue());

		JPanel lista = crearPanelLista("Solicitudes de mantenimiento");
		areaManLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnManRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnManRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);

		panel.add(izquierda);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea el subpanel de gestion de incidentes del conjunto.
	 *
	 * Incluye un formulario para registrar nuevos incidentes, un formulario para
	 * gestionar incidentes existentes y una lista con todos los incidentes.
	 *
	 * <b>post</b> Se inicializan todos los campos, combos y botones relacionados
	 * con incidentes.
	 *
	 * @return JPanel configurado con los formularios y lista de incidentes
	 */
	public JPanel crearSubPanelIncidentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel izquierda = new JPanel();
		izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
		izquierda.setBackground(colorPanel);
		izquierda.setMaximumSize(new Dimension(330, Integer.MAX_VALUE));
		izquierda.setPreferredSize(new Dimension(330, 0));

		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Incidente", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtIncDescripcion = crearCampo();
		txtIncApto = crearCampo();
		comboIncTipo = new JComboBox<>(new String[] { "Ruido", "Daños", "Mascota sin control", "Parqueadero",
				"Visitante no autorizado", "Residuos", "Conflicto", "Otro" });
		estilizarCombo(comboIncTipo);
		comboIncGravedad = new JComboBox<>(
				new String[] { "Observacion", "Advertencia", "Multa", "Suspension", "Consejo" });
		estilizarCombo(comboIncGravedad);

		agregarCampo(formulario, "Descripcion:", txtIncDescripcion);
		agregarCampo(formulario, "N\u00b0 Apartamento involucrado (ej: 402):", txtIncApto);
		formulario.add(crearEtiqueta("Tipo de incidente:"));
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboIncTipo);
		formulario.add(Box.createVerticalStrut(10));
		formulario.add(crearEtiqueta("Gravedad:"));
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboIncGravedad);
		formulario.add(Box.createVerticalStrut(10));

		JPanel botones = panelBotones();
		btnIncCrear = crearBoton("Registrar", colorRojo, colorBlanco);
		btnIncLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnIncCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnIncLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblIncMensaje = crearLblMensaje();
		formulario.add(lblIncMensaje);

		JPanel panelGestionar = new JPanel();
		panelGestionar.setLayout(new BoxLayout(panelGestionar, BoxLayout.Y_AXIS));
		panelGestionar.setBackground(colorPanel);
		panelGestionar.setAlignmentX(LEFT_ALIGNMENT);
		panelGestionar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Gestionar incidente", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));

		txtIncIdGestionar = crearCampo();
		txtIncResolucion = crearCampo();
		comboIncAccion = new JComboBox<>(new String[] { "EN_REVISION", "RESUELTO", "ARCHIVADO" });
		estilizarCombo(comboIncAccion);

		agregarCampo(panelGestionar, "Index del incidente:", txtIncIdGestionar);
		agregarCampo(panelGestionar, "Resolucion / observacion:", txtIncResolucion);
		JLabel lblAcc = crearEtiqueta("Accion:");
		panelGestionar.add(lblAcc);
		panelGestionar.add(Box.createVerticalStrut(4));
		panelGestionar.add(comboIncAccion);
		panelGestionar.add(Box.createVerticalStrut(10));

		JPanel botGest = panelBotones();
		btnIncGestionar = crearBoton("Aplicar accion", colorVerde, colorBlanco);
		botGest.add(btnIncGestionar);
		panelGestionar.add(botGest);
		panelGestionar.add(Box.createVerticalStrut(6));
		lblIncMensajeGestion = crearLblMensaje();
		panelGestionar.add(lblIncMensajeGestion);

		izquierda.add(formulario);
		izquierda.add(Box.createVerticalStrut(15));
		izquierda.add(panelGestionar);
		izquierda.add(Box.createVerticalGlue());

		JPanel lista = crearPanelLista("Incidentes registrados");
		areaIncLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnIncRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnIncRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);

		panel.add(izquierda);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea un panel de lista con titulo y area de texto dentro de un scroll.
	 *
	 * @param titulo Titulo que se mostrara en el borde del panel
	 *
	 * @return JPanel configurado con un JTextArea envuelto en un JScrollPane
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
	 * Agrega una etiqueta y un campo de texto al panel indicado.
	 *
	 * <b>post</b> El campo queda alineado y con tamano maximo configurado.
	 *
	 * @param panel    Panel al que se agrega el campo
	 * @param etiqueta Texto descriptivo del campo
	 * @param campo    JTextField a agregar
	 */
	public void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
		panel.add(crearEtiqueta(etiqueta));
		panel.add(Box.createVerticalStrut(4));
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		campo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(campo);
		panel.add(Box.createVerticalStrut(10));
	}

	/**
	 * Crea y configura una etiqueta con el estilo del panel.
	 *
	 * @param texto Texto a mostrar en la etiqueta
	 *
	 * @return JLabel configurado con fuente y color del panel
	 */
	public JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Crea y configura un campo de texto con el estilo del panel.
	 *
	 * @return JTextField configurado con fuente, colores y borde del panel
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
	 * Crea y configura un boton personalizado.
	 *
	 * @param texto Texto del boton
	 * @param fondo Color de fondo
	 * @param letra Color del texto
	 *
	 * @return JButton configurado con el estilo indicado
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
	 * Crea un panel contenedor horizontal para agrupar botones.
	 *
	 * @return JPanel transparente con layout horizontal alineado a la izquierda
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea una etiqueta de mensaje con estilo de error por defecto.
	 *
	 * @return JLabel con texto vacio, fuente pequena y color rojo
	 */
	public JLabel crearLblMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Aplica el estilo estandar del panel a un JComboBox.
	 *
	 * <b>post</b> El combo queda con fuente, colores y tamano maximo configurados.
	 *
	 * @param combo JComboBox a estilizar
	 */
	public void estilizarCombo(JComboBox<String> combo) {
		combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		combo.setBackground(colorCampoFondo);
		combo.setForeground(colorVerdeOscuro);
		combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		combo.setAlignmentX(LEFT_ALIGNMENT);
	}

	/**
	 * Establece un mensaje informativo en el panel de paquetes.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajePaq(String msg, boolean esError) {
		if (esError) {
			lblPaqMensaje.setForeground(colorRojo);
		} else {
			lblPaqMensaje.setForeground(colorVerdeOscuro);
		}
		lblPaqMensaje.setText(msg);
	}

	/**
	 * Limpia el mensaje del panel de paquetes.
	 *
	 * <b>post</b> La etiqueta de mensaje queda con texto vacio.
	 */
	public void limpiarPaq() {
		lblPaqMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de mantenimiento.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeMan(String msg, boolean esError) {
		if (esError) {
			lblManMensaje.setForeground(colorRojo);
		} else {
			lblManMensaje.setForeground(colorVerdeOscuro);
		}
		lblManMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de mantenimiento.
	 *
	 * <b>post</b> Los campos de descripcion, ubicacion y el mensaje quedan vacios.
	 */
	public void limpiarMan() {
		txtManDescripcion.setText("");
		txtManUbicacion.setText("");
		lblManMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de registro de incidentes.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeInc(String msg, boolean esError) {
		if (esError) {
			lblIncMensaje.setForeground(colorRojo);
		} else {
			lblIncMensaje.setForeground(colorVerdeOscuro);
		}
		lblIncMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de registro de incidentes.
	 *
	 * <b>post</b> Los campos de descripcion, apartamento y el mensaje quedan
	 * vacios.
	 */
	public void limpiarInc() {
		txtIncDescripcion.setText("");
		txtIncApto.setText("");
		lblIncMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de gestion de incidentes.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeIncGestion(String msg, boolean esError) {
		if (esError) {
			lblIncMensajeGestion.setForeground(colorRojo);
		} else {
			lblIncMensajeGestion.setForeground(colorVerdeOscuro);
		}
		lblIncMensajeGestion.setText(msg);
	}

	/**
	 * Retorna el area de texto del historial de visitantes.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaVisLista() {
		return areaVisLista;
	}

	/**
	 * Retorna el boton de refrescar visitantes.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnVisRefrescar() {
		return btnVisRefrescar;
	}

	/**
	 * Retorna el area de texto del listado de paquetes.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaPaqLista() {
		return areaPaqLista;
	}

	/**
	 * Retorna el boton de refrescar paquetes.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnPaqRefrescar() {
		return btnPaqRefrescar;
	}

	/**
	 * Retorna el campo de descripcion de mantenimiento.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtManDescripcion() {
		return txtManDescripcion;
	}

	/**
	 * Retorna el campo de ubicacion de mantenimiento.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtManUbicacion() {
		return txtManUbicacion;
	}

	/**
	 * Retorna el combo de tipo de mantenimiento.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboManTipo() {
		return comboManTipo;
	}

	/**
	 * Retorna el combo de prioridad de mantenimiento.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboManPrioridad() {
		return comboManPrioridad;
	}

	/**
	 * Retorna el boton de crear solicitud de mantenimiento.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnManCrear() {
		return btnManCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario de mantenimiento.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnManLimpiar() {
		return btnManLimpiar;
	}

	/**
	 * Retorna el campo de index de solicitud a actualizar.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtManIdActualizar() {
		return txtManIdActualizar;
	}

	/**
	 * Retorna el campo del tecnico a asignar.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtManTecnico() {
		return txtManTecnico;
	}

	/**
	 * Retorna el combo de estado de actualizacion de mantenimiento.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboManEstadoUpdate() {
		return comboManEstadoUpdate;
	}

	/**
	 * Retorna el boton de actualizar solicitud de mantenimiento.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnManActualizar() {
		return btnManActualizar;
	}

	/**
	 * Retorna el area de texto del listado de mantenimiento.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaManLista() {
		return areaManLista;
	}

	/**
	 * Retorna el boton de refrescar mantenimiento.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnManRefrescar() {
		return btnManRefrescar;
	}

	/**
	 * Retorna el campo de descripcion de incidente.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncDescripcion() {
		return txtIncDescripcion;
	}

	/**
	 * Retorna el campo de apartamento del incidente.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncApto() {
		return txtIncApto;
	}

	/**
	 * Retorna el combo de tipo de incidente.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboIncTipo() {
		return comboIncTipo;
	}

	/**
	 * Retorna el combo de gravedad del incidente.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboIncGravedad() {
		return comboIncGravedad;
	}

	/**
	 * Retorna el boton de crear incidente.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncCrear() {
		return btnIncCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario de incidentes.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncLimpiar() {
		return btnIncLimpiar;
	}

	/**
	 * Retorna el area de texto del listado de incidentes.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaIncLista() {
		return areaIncLista;
	}

	/**
	 * Retorna el boton de refrescar incidentes.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncRefrescar() {
		return btnIncRefrescar;
	}

	/**
	 * Retorna el campo de index del incidente a gestionar.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncIdGestionar() {
		return txtIncIdGestionar;
	}

	/**
	 * Retorna el campo de resolucion del incidente.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncResolucion() {
		return txtIncResolucion;
	}

	/**
	 * Retorna el combo de accion sobre el incidente.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboIncAccion() {
		return comboIncAccion;
	}

	/**
	 * Retorna el boton de gestionar incidente.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncGestionar() {
		return btnIncGestionar;
	}
}