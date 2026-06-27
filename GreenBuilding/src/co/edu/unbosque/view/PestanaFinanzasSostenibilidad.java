package co.edu.unbosque.view;

import java.awt.BorderLayout;
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
 * Panel grafico encargado de la gestion financiera y de sostenibilidad del
 * conjunto residencial.
 *
 * Agrupa en subpestanas el registro de obligaciones y pagos, la creacion de
 * campanas ambientales y el registro de consumos de recursos, permitiendo al
 * administrador gestionar estos modulos desde una unica interfaz.
 *
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaFinanzasSostenibilidad extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 2540446149841783069L;

	/**
	 * Campo de texto para ingresar el numero de apartamento de la obligacion.
	 */
	private JTextField txtOblApto;
	/**
	 * Campo de texto para ingresar el concepto de la obligacion.
	 */
	private JTextField txtOblConcepto;

	/**
	 * Campo de texto para ingresar el monto de la obligacion.
	 */
	private JTextField txtOblMonto;

	/**
	 * Campo de texto para ingresar la fecha de vencimiento de la obligacion.
	 */
	private JTextField txtOblFechaVence;

	/**
	 * ComboBox para seleccionar el tipo de obligacion o multa.
	 */
	private JComboBox<String> comboOblTipo;

	/**
	 * Boton para registrar una nueva obligacion o multa.
	 */
	private JButton btnOblCrear;

	/**
	 * Boton para limpiar el formulario de obligaciones.
	 */
	private JButton btnOblLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con obligaciones.
	 */
	private JLabel lblOblMensaje;

	/**
	 * Area de texto que muestra el listado de obligaciones y pagos registrados.
	 */
	private JTextArea areaFinLista;

	/**
	 * Boton para refrescar la lista de obligaciones y pagos.
	 */
	private JButton btnFinRefrescar;

	/**
	 * Campo de texto para ingresar el numero de apartamento del pago.
	 */
	private JTextField txtPagoApto;

	/**
	 * Campo de texto para ingresar el monto a pagar.
	 */
	private JTextField txtPagoMonto;

	/**
	 * Boton para consultar el estado de cuenta de un apartamento.
	 */
	private JButton btnVerEstadoCuenta;

	/**
	 * Boton para registrar un pago.
	 */
	private JButton btnPagoRegistrar;

	/**
	 * Boton para limpiar el formulario de pagos.
	 */
	private JButton btnPagoLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con pagos.
	 */
	private JLabel lblPagoMensaje;

	/**
	 * Area de texto que muestra el estado de cuenta del apartamento consultado.
	 */
	private JTextArea areaEstadoCuenta;

	/**
	 * Campo de texto para ingresar el nombre de la campana ambiental.
	 */
	private JTextField txtCampNombre;

	/**
	 * Campo de texto para ingresar la descripcion de la campana ambiental.
	 */
	private JTextField txtCampDescripcion;

	/**
	 * Campo de texto para ingresar la fecha de inicio de la campana ambiental.
	 */
	private JTextField txtCampFechaInicio;

	/**
	 * Campo de texto para ingresar la fecha de fin de la campana ambiental.
	 */
	private JTextField txtCampFechaFin;

	/**
	 * Boton para crear una nueva campana ambiental.
	 */
	private JButton btnCampCrear;

	/**
	 * Boton para limpiar el formulario de campanas ambientales.
	 */
	private JButton btnCampLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con campanas ambientales.
	 */
	private JLabel lblCampMensaje;

	/**
	 * Area de texto que muestra el listado de campanas ambientales registradas.
	 */
	private JTextArea areaCampLista;

	/**
	 * Boton para refrescar la lista de campanas ambientales.
	 */
	private JButton btnCampRefrescar;

	/**
	 * Campo de texto para ingresar la fecha del registro de consumo.
	 */
	private JTextField txtConsFecha;

	/**
	 * Campo de texto para ingresar el valor o cantidad del consumo.
	 */
	private JTextField txtConsValor;

	/**
	 * Campo de texto para ingresar la observacion del consumo.
	 */
	private JTextField txtConsObservacion;

	/**
	 * ComboBox para seleccionar el tipo de consumo a registrar.
	 */
	private JComboBox<String> comboCONSTipo;

	/**
	 * Boton para registrar un nuevo consumo.
	 */
	private JButton btnConsRegistrar;

	/**
	 * Boton para limpiar el formulario de consumos.
	 */
	private JButton btnConsLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con consumos.
	 */
	private JLabel lblConsMensaje;

	/**
	 * Area de texto que muestra el listado de consumos registrados.
	 */
	private JTextArea areaConsLista;

	/**
	 * Boton para refrescar la lista de consumos.
	 */
	private JButton btnConsRefrescar;

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
	 * Construye la pestana de finanzas y sostenibilidad.
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
	public PestanaFinanzasSostenibilidad(Color colorPanel, Color colorVerdeOscuro, Color colorVerde,
			Color colorTextoSuave, Color colorCampoFondo, Color colorCampoBorde, Color colorBorde, Color colorBlanco,
			Color colorRojo) {
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
	 * Obligaciones y Pagos, Campanas Ambientales y Registro de Consumos.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Obligaciones y Pagos", crearSubPanelFinanzas());
		tabs.addTab("Campañas Ambientales", crearSubPanelCampanas());
		tabs.addTab("Registro de Consumos", crearSubPanelConsumos());
		add(tabs);
	}

	/**
	 * Crea el subpanel de gestion de obligaciones y pagos.
	 *
	 * Incluye un formulario para registrar nuevas obligaciones o multas, un
	 * formulario para registrar pagos con consulta de estado de cuenta y una lista
	 * con todas las obligaciones y pagos registrados.
	 *
	 * <b>post</b> Se inicializan todos los campos, combos y botones relacionados
	 * con finanzas.
	 *
	 * @return JPanel configurado con los formularios y lista de finanzas
	 */
	public JPanel crearSubPanelFinanzas() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel izquierda = new JPanel();
		izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
		izquierda.setBackground(colorPanel);

		JPanel formObl = new JPanel();
		formObl.setLayout(new BoxLayout(formObl, BoxLayout.Y_AXIS));
		formObl.setBackground(colorPanel);
		formObl.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nueva Obligacion / Multa",
						0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtOblApto = crearCampo();
		txtOblConcepto = crearCampo();
		txtOblMonto = crearCampo();
		txtOblFechaVence = crearCampo();
		comboOblTipo = new JComboBox<>(new String[] { "Cuota administracion", "Cuota extraordinaria", "Multa",
				"Dano zona comun", "Parqueadero adicional", "Otro" });
		estilizarCombo(comboOblTipo);

		agregarCampo(formObl, "N\u00b0 Apartamento (ej: 402):", txtOblApto);
		agregarCampo(formObl, "Concepto:", txtOblConcepto);
		agregarCampo(formObl, "Monto ($):", txtOblMonto);
		agregarCampo(formObl, "Fecha vencimiento (YYYY-MM-DD):", txtOblFechaVence);
		formObl.add(crearEtiqueta("Tipo:"));
		formObl.add(Box.createVerticalStrut(4));
		formObl.add(comboOblTipo);
		formObl.add(Box.createVerticalStrut(10));

		JPanel botObl = panelBotones();
		btnOblCrear = crearBoton("Registrar", colorVerde, colorBlanco);
		btnOblLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botObl.add(btnOblCrear);
		botObl.add(Box.createHorizontalStrut(10));
		botObl.add(btnOblLimpiar);
		formObl.add(botObl);
		formObl.add(Box.createVerticalStrut(8));
		lblOblMensaje = crearLblMensaje();
		formObl.add(lblOblMensaje);

		JPanel formPago = new JPanel();
		formPago.setLayout(new BoxLayout(formPago, BoxLayout.Y_AXIS));
		formPago.setBackground(colorPanel);
		formPago.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Pago", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtPagoApto = crearCampo();
		txtPagoMonto = crearCampo();

		agregarCampo(formPago, "N\u00b0 Apartamento (ej: 402):", txtPagoApto);

		JPanel botEstado = panelBotones();
		btnVerEstadoCuenta = crearBoton("Ver estado de cuenta", colorVerdeOscuro, colorBlanco);
		botEstado.add(btnVerEstadoCuenta);
		formPago.add(botEstado);
		formPago.add(Box.createVerticalStrut(8));

		JLabel lblEstado = new JLabel("Obligaciones pendientes:");
		lblEstado.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblEstado.setForeground(colorTextoSuave);
		lblEstado.setAlignmentX(LEFT_ALIGNMENT);
		formPago.add(lblEstado);
		formPago.add(Box.createVerticalStrut(4));

		areaEstadoCuenta = new JTextArea(4, 1);
		areaEstadoCuenta.setEditable(false);
		areaEstadoCuenta.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaEstadoCuenta.setBackground(colorCampoFondo);
		areaEstadoCuenta.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollEstado = new JScrollPane(areaEstadoCuenta);
		scrollEstado.setAlignmentX(LEFT_ALIGNMENT);
		scrollEstado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		formPago.add(scrollEstado);
		formPago.add(Box.createVerticalStrut(8));

		agregarCampo(formPago, "Monto a pagar ($):", txtPagoMonto);

		JPanel botPago = panelBotones();
		btnPagoRegistrar = crearBoton("Registrar Pago", colorVerde, colorBlanco);
		btnPagoLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botPago.add(btnPagoRegistrar);
		botPago.add(Box.createHorizontalStrut(10));
		botPago.add(btnPagoLimpiar);
		formPago.add(botPago);
		formPago.add(Box.createVerticalStrut(8));
		lblPagoMensaje = crearLblMensaje();
		formPago.add(lblPagoMensaje);

		izquierda.add(formObl);
		izquierda.add(Box.createVerticalStrut(15));
		izquierda.add(formPago);
		izquierda.add(Box.createVerticalGlue());

		JScrollPane scrollIzq = new JScrollPane(izquierda);
		scrollIzq.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollIzq.setBorder(BorderFactory.createEmptyBorder());
		scrollIzq.setPreferredSize(new Dimension(370, 0));

		JPanel lista = crearPanelLista("Obligaciones y pagos registrados");
		areaFinLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnFinRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnFinRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);

		panel.add(scrollIzq, BorderLayout.WEST);
		panel.add(lista, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Crea el subpanel de gestion de campanas ambientales.
	 *
	 * Incluye un formulario para crear nuevas campanas y una lista con todas las
	 * campanas registradas.
	 *
	 * <b>post</b> Se inicializan todos los campos y botones relacionados con
	 * campanas ambientales.
	 *
	 * @return JPanel configurado con el formulario y lista de campanas ambientales
	 */
	public JPanel crearSubPanelCampanas() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Crear Campaña Ambiental",
						0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtCampNombre = crearCampo();
		txtCampDescripcion = crearCampo();
		txtCampFechaInicio = crearCampo();
		txtCampFechaFin = crearCampo();

		agregarCampo(formulario, "Nombre campaña:", txtCampNombre);
		agregarCampo(formulario, "Descripcion:", txtCampDescripcion);
		agregarCampo(formulario, "Fecha inicio (YYYY-MM-DD):", txtCampFechaInicio);
		agregarCampo(formulario, "Fecha fin (YYYY-MM-DD):", txtCampFechaFin);

		JPanel botones = panelBotones();
		btnCampCrear = crearBoton("Crear Campaña", colorVerde, colorBlanco);
		btnCampLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnCampCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnCampLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblCampMensaje = crearLblMensaje();
		formulario.add(lblCampMensaje);
		formulario.add(Box.createVerticalGlue());

		JPanel lista = crearPanelLista("Campañas ambientales");
		areaCampLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnCampRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnCampRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);

		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea el subpanel de registro de consumos de recursos.
	 *
	 * Incluye un formulario para registrar consumos de energia, agua o reciclaje y
	 * una lista con todos los consumos registrados.
	 *
	 * <b>post</b> Se inicializan todos los campos, combos y botones relacionados
	 * con consumos.
	 *
	 * @return JPanel configurado con el formulario y lista de consumos
	 */
	public JPanel crearSubPanelConsumos() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Registrar Consumo", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtConsFecha = crearCampo();
		txtConsValor = crearCampo();
		txtConsObservacion = crearCampo();
		comboCONSTipo = new JComboBox<>(new String[] { "Energia (kWh)", "Agua (m3)", "Reciclaje (kg)" });
		estilizarCombo(comboCONSTipo);

		agregarCampo(formulario, "Fecha registro (YYYY-MM-DD):", txtConsFecha);
		agregarCampo(formulario, "Valor / Cantidad:", txtConsValor);
		agregarCampo(formulario, "Observacion:", txtConsObservacion);
		formulario.add(crearEtiqueta("Tipo de consumo:"));
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboCONSTipo);
		formulario.add(Box.createVerticalStrut(10));

		JPanel botones = panelBotones();
		btnConsRegistrar = crearBoton("Registrar", colorVerde, colorBlanco);
		btnConsLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnConsRegistrar);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnConsLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblConsMensaje = crearLblMensaje();
		formulario.add(lblConsMensaje);
		formulario.add(Box.createVerticalGlue());

		JPanel lista = crearPanelLista("Consumos registrados");
		areaConsLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnConsRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnConsRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);

		panel.add(formulario);
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
	 * Establece un mensaje informativo en el panel de obligaciones.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeObl(String msg, boolean esError) {
		if (esError) {
			lblOblMensaje.setForeground(colorRojo);
		} else {
			lblOblMensaje.setForeground(colorVerdeOscuro);
		}
		lblOblMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de obligaciones.
	 *
	 * <b>post</b> Los campos de apartamento, concepto, monto, fecha y el mensaje
	 * quedan vacios.
	 */
	public void limpiarObl() {
		txtOblApto.setText("");
		txtOblConcepto.setText("");
		txtOblMonto.setText("");
		txtOblFechaVence.setText("");
		lblOblMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de pagos.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajePago(String msg, boolean esError) {
		if (esError) {
			lblPagoMensaje.setForeground(colorRojo);
		} else {
			lblPagoMensaje.setForeground(colorVerdeOscuro);
		}
		lblPagoMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de pagos.
	 *
	 * <b>post</b> Los campos de apartamento, monto, el area de estado de cuenta y
	 * el mensaje quedan vacios.
	 */
	public void limpiarPago() {
		txtPagoApto.setText("");
		txtPagoMonto.setText("");
		areaEstadoCuenta.setText("");
		lblPagoMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de campanas ambientales.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeCamp(String msg, boolean esError) {
		if (esError) {
			lblCampMensaje.setForeground(colorRojo);
		} else {
			lblCampMensaje.setForeground(colorVerdeOscuro);
		}
		lblCampMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de campanas ambientales.
	 *
	 * <b>post</b> Los campos de nombre, descripcion, fechas y el mensaje quedan
	 * vacios.
	 */
	public void limpiarCamp() {
		txtCampNombre.setText("");
		txtCampDescripcion.setText("");
		txtCampFechaInicio.setText("");
		txtCampFechaFin.setText("");
		lblCampMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo en el panel de consumos.
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeCons(String msg, boolean esError) {
		if (esError) {
			lblConsMensaje.setForeground(colorRojo);
		} else {
			lblConsMensaje.setForeground(colorVerdeOscuro);
		}
		lblConsMensaje.setText(msg);
	}

	/**
	 * Limpia los campos del formulario de consumos.
	 *
	 * <b>post</b> Los campos de fecha, valor, observacion y el mensaje quedan
	 * vacios.
	 */
	public void limpiarCons() {
		txtConsFecha.setText("");
		txtConsValor.setText("");
		txtConsObservacion.setText("");
		lblConsMensaje.setText(" ");
	}

	/**
	 * Retorna el campo de apartamento de la obligacion.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtOblApto() {
		return txtOblApto;
	}

	/**
	 * Retorna el campo de concepto de la obligacion.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtOblConcepto() {
		return txtOblConcepto;
	}

	/**
	 * Retorna el campo de monto de la obligacion.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtOblMonto() {
		return txtOblMonto;
	}

	/**
	 * Retorna el campo de fecha de vencimiento de la obligacion.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtOblFechaVence() {
		return txtOblFechaVence;
	}

	/**
	 * Retorna el combo de tipo de obligacion.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboOblTipo() {
		return comboOblTipo;
	}

	/**
	 * Retorna el boton de crear obligacion.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnOblCrear() {
		return btnOblCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario de obligaciones.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnOblLimpiar() {
		return btnOblLimpiar;
	}

	/**
	 * Retorna el area de texto del listado de finanzas.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaFinLista() {
		return areaFinLista;
	}

	/**
	 * Retorna el boton de refrescar finanzas.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnFinRefrescar() {
		return btnFinRefrescar;
	}

	/**
	 * Retorna el campo de apartamento del pago.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtPagoApto() {
		return txtPagoApto;
	}

	/**
	 * Retorna el campo de monto del pago.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtPagoMonto() {
		return txtPagoMonto;
	}

	/**
	 * Retorna el boton de consultar estado de cuenta.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnVerEstadoCuenta() {
		return btnVerEstadoCuenta;
	}

	/**
	 * Retorna el boton de registrar pago.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnPagoRegistrar() {
		return btnPagoRegistrar;
	}

	/**
	 * Retorna el boton de limpiar formulario de pagos.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnPagoLimpiar() {
		return btnPagoLimpiar;
	}

	/**
	 * Retorna el area de texto del estado de cuenta del apartamento.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaEstadoCuenta() {
		return areaEstadoCuenta;
	}

	/**
	 * Retorna el campo de nombre de la campana ambiental.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtCampNombre() {
		return txtCampNombre;
	}

	/**
	 * Retorna el campo de descripcion de la campana ambiental.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtCampDescripcion() {
		return txtCampDescripcion;
	}

	/**
	 * Retorna el campo de fecha de inicio de la campana ambiental.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtCampFechaInicio() {
		return txtCampFechaInicio;
	}

	/**
	 * Retorna el campo de fecha de fin de la campana ambiental.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtCampFechaFin() {
		return txtCampFechaFin;
	}

	/**
	 * Retorna el boton de crear campana ambiental.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnCampCrear() {
		return btnCampCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario de campanas.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnCampLimpiar() {
		return btnCampLimpiar;
	}

	/**
	 * Retorna el area de texto del listado de campanas ambientales.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaCampLista() {
		return areaCampLista;
	}

	/**
	 * Retorna el boton de refrescar campanas ambientales.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnCampRefrescar() {
		return btnCampRefrescar;
	}

	/**
	 * Retorna el campo de fecha del consumo.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtConsFecha() {
		return txtConsFecha;
	}

	/**
	 * Retorna el campo de valor o cantidad del consumo.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtConsValor() {
		return txtConsValor;
	}

	/**
	 * Retorna el campo de observacion del consumo.
	 *
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtConsObservacion() {
		return txtConsObservacion;
	}

	/**
	 * Retorna el combo de tipo de consumo.
	 *
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboCONSTipo() {
		return comboCONSTipo;
	}

	/**
	 * Retorna el boton de registrar consumo.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnConsRegistrar() {
		return btnConsRegistrar;
	}

	/**
	 * Retorna el boton de limpiar formulario de consumos.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnConsLimpiar() {
		return btnConsLimpiar;
	}

	/**
	 * Retorna el area de texto del listado de consumos.
	 *
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaConsLista() {
		return areaConsLista;
	}

	/**
	 * Retorna el boton de refrescar consumos.
	 *
	 * @return JButton correspondiente
	 */
	public JButton getBtnConsRefrescar() {
		return btnConsRefrescar;
	}
}