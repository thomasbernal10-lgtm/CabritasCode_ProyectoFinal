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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel de configuracion del conjunto residencial para el SuperAdministrador.
 * Permite gestionar las torres, apartamentos, zonas comunes y parqueaderos de
 * cada conjunto registrado en el sistema. <br>
 * Cada categoria se presenta en una subpestana independiente con formulario de
 * creacion y lista de registros existentes. <br>
 * <b>pre</b> Los colores deben ser inicializados antes de construir el panel.
 * <br>
 * <b>post</b> El panel queda listo con las cuatro subpestanas configuradas.
 * <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PestanaConfigurarConjunto extends JPanel {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = -466534787452424258L;

	/** Color de fondo principal del panel. */
	private Color colorPanel;

	/** Color verde oscuro usado en titulos y elementos destacados. */
	private Color colorVerdeOscuro;

	/** Color verde usado en botones de accion principal. */
	private Color colorVerde;

	/** Color suave usado en etiquetas de texto. */
	private Color colorTextoSuave;

	/** Color de fondo de los campos de texto. */
	private Color colorCampoFondo;

	/** Color del borde de los campos de texto. */
	private Color colorCampoBorde;

	/** Color de los bordes de los paneles. */
	private Color colorBorde;

	/** Color blanco usado en texto sobre fondos oscuros. */
	private Color colorBlanco;

	/** Color rojo usado en mensajes de error y botones de eliminar. */
	private Color colorRojo;

	/** Combo para seleccionar el conjunto al que pertenece la torre. */
	private JComboBox<String> comboConjuntoTorre;

	/** Campo de texto para el nombre de la nueva torre. */
	private JTextField txtTorreNombre;

	/** Campo de texto para el numero de pisos de la torre. */
	private JTextField txtTorreNumPiso;

	/** Boton para crear una nueva torre. */
	private JButton btnCrearTorre;

	/** Boton para limpiar el formulario de torres. */
	private JButton btnLimpiarTorre;

	/** Boton para eliminar una torre por indice. */
	private JButton btnEliminarTorre;

	/** Boton para refrescar la lista de torres. */
	private JButton btnRefrescarTorre;

	/** Campo de texto para ingresar el indice de la torre a eliminar. */
	private JTextField txtTorreIndex;

	/** Area de texto donde se listan las torres registradas. */
	private JTextArea areaListaTorre;

	/** Etiqueta para mostrar mensajes de exito o error en la gestion de torres. */
	private JLabel lblMensajeTorre;

	/** Combo para seleccionar la torre a la que pertenece el apartamento. */
	private JComboBox<String> comboTorreApto;

	/** Campo de texto para el numero del nuevo apartamento. */
	private JTextField txtAptoNumero;

	/** Combo para seleccionar el estado inicial del apartamento. */
	private JComboBox<String> comboAptoEstado;

	/** Boton para crear un nuevo apartamento. */
	private JButton btnCrearApto;

	/** Boton para limpiar el formulario de apartamentos. */
	private JButton btnLimpiarApto;

	/** Boton para eliminar un apartamento por indice. */
	private JButton btnEliminarApto;

	/** Boton para refrescar la lista de apartamentos. */
	private JButton btnRefrescarApto;

	/** Campo de texto para ingresar el indice del apartamento a eliminar. */
	private JTextField txtAptoIndex;

	/** Area de texto donde se listan los apartamentos registrados. */
	private JTextArea areaListaApto;

	/**
	 * Etiqueta para mostrar mensajes de exito o error en la gestion de
	 * apartamentos.
	 */
	private JLabel lblMensajeApto;

	/** Combo para seleccionar el conjunto al que pertenece la zona comun. */
	private JComboBox<String> comboConjuntoZona;

	/** Combo para seleccionar el tipo de zona comun. */
	private JComboBox<String> comboZonaTipo;

	/** Combo para seleccionar el estado inicial de la zona comun. */
	private JComboBox<String> comboZonaEstado;

	/** Campo de texto para el aforo maximo de la zona comun. */
	private JTextField txtZonaAforo;

	/** Campo de texto para el costo de reserva de la zona comun. */
	private JTextField txtZonaCosto;

	/**
	 * Campo de texto para la hora de apertura de la zona comun en formato HH:mm.
	 */
	private JTextField txtZonaHoraApertura;

	/** Campo de texto para la hora de cierre de la zona comun en formato HH:mm. */
	private JTextField txtZonaHoraCierre;

	/** Checkbox para indicar si la zona comun requiere reserva previa. */
	private JCheckBox chkZonaRequiereReserva;

	/** Boton para crear una nueva zona comun. */
	private JButton btnCrearZona;

	/** Boton para limpiar el formulario de zonas comunes. */
	private JButton btnLimpiarZona;

	/** Boton para eliminar una zona comun por indice. */
	private JButton btnEliminarZona;

	/** Boton para refrescar la lista de zonas comunes. */
	private JButton btnRefrescarZona;

	/** Campo de texto para ingresar el indice de la zona a eliminar. */
	private JTextField txtZonaIndex;

	/** Area de texto donde se listan las zonas comunes registradas. */
	private JTextArea areaListaZona;

	/** Etiqueta para mostrar mensajes de exito o error en la gestion de zonas. */
	private JLabel lblMensajeZona;

	/** Combo para seleccionar el conjunto al que pertenece el parqueadero. */
	private JComboBox<String> comboConjuntoParq;

	/** Campo de texto para el numero del nuevo parqueadero. */
	private JTextField txtParqNumero;

	/** Combo para seleccionar el tipo de parqueadero. */
	private JComboBox<String> comboParqTipo;

	/** Combo para seleccionar el estado inicial del parqueadero. */
	private JComboBox<String> comboParqEstado;

	/** Boton para crear un nuevo parqueadero. */
	private JButton btnCrearParq;

	/** Boton para limpiar el formulario de parqueaderos. */
	private JButton btnLimpiarParq;

	/** Boton para eliminar un parqueadero por indice. */
	private JButton btnEliminarParq;

	/** Boton para refrescar la lista de parqueaderos. */
	private JButton btnRefrescarParq;

	/** Campo de texto para ingresar el indice del parqueadero a eliminar. */
	private JTextField txtParqIndex;

	/** Area de texto donde se listan los parqueaderos registrados. */
	private JTextArea areaListaParq;

	/**
	 * Etiqueta para mostrar mensajes de exito o error en la gestion de
	 * parqueaderos.
	 */
	private JLabel lblMensajeParq;

	/**
	 * Constructor de PestanaConfigurarConjunto. Inicializa la paleta de colores y
	 * construye la interfaz grafica de la pestana. <br>
	 * <b>pre</b> Todos los parametros de color deben ser distintos de null. <br>
	 * <b>post</b> La pestana queda construida con las subpestanas de torres,
	 * apartamentos, zonas comunes y parqueaderos. <br>
	 *
	 * @param colorPanel       color de fondo del panel
	 * @param colorVerdeOscuro color verde oscuro para titulos y elementos
	 *                         destacados
	 * @param colorVerde       color verde para botones de accion
	 * @param colorTextoSuave  color suave para etiquetas
	 * @param colorCampoFondo  color de fondo de campos de texto
	 * @param colorCampoBorde  color de borde de campos de texto
	 * @param colorBorde       color de bordes de paneles
	 * @param colorBlanco      color blanco para texto sobre fondo oscuro
	 * @param colorRojo        color rojo para errores y eliminar
	 */
	public PestanaConfigurarConjunto(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * Construye la estructura visual de la pestana con las subpestanas internas.
	 * <br>
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se agregan las subpestanas de torres, apartamentos, zonas comunes
	 * y parqueaderos al panel principal. <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		JLabel lblTitulo = new JLabel("Seleccione una categoría para configurar el conjunto:");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTitulo.setForeground(colorVerdeOscuro);
		lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
		add(lblTitulo);
		add(Box.createVerticalStrut(10));
		JTabbedPane tabsInternos = new JTabbedPane();
		tabsInternos.setFont(new Font("SansSerif", Font.BOLD, 12));
		tabsInternos.setBackground(colorCampoFondo);
		tabsInternos.setAlignmentX(LEFT_ALIGNMENT);

		tabsInternos.addTab("  Torres  ", construirTabTorres());
		tabsInternos.addTab("  Apartamentos  ", construirTabApartamentos());
		tabsInternos.addTab("  Zonas Comunes  ", construirTabZonasComunes());
		tabsInternos.addTab("  Parqueaderos  ", construirTabParqueaderos());
		add(tabsInternos);
	}

	/**
	 * Construye la subpestana de gestion de torres. Contiene un formulario para
	 * crear torres y una lista con las registradas. <br>
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel con el formulario y la lista de torres. <br>
	 *
	 * @return panel con la interfaz de gestion de torres
	 */
	public JPanel construirTabTorres() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nueva Torre", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		comboConjuntoTorre = crearCombo(new String[] { "-- Seleccione Conjunto --" });
		txtTorreNombre = crearCampo();
		txtTorreNumPiso = crearCampo();
		agregarCombo(formulario, "Conjunto:", comboConjuntoTorre);
		agregarCampo(formulario, "Nombre:", txtTorreNombre);
		agregarCampo(formulario, "Número de pisos:", txtTorreNumPiso);
		formulario.add(Box.createVerticalStrut(10));

		JPanel panelBotones = crearPanelBotones();
		btnCrearTorre = crearBoton("Crear Torre", colorVerde, colorBlanco);
		btnLimpiarTorre = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnCrearTorre);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiarTorre);
		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(8));

		lblMensajeTorre = crearLabelMensaje();
		formulario.add(lblMensajeTorre);
		formulario.add(Box.createVerticalGlue());
		JPanel panelLista = construirPanelLista("Torres registradas");
		areaListaTorre = extraerArea(panelLista);
		JPanel panelAcciones = crearPanelBotones();
		JLabel lblIdx = crearLabelIdx();
		txtTorreIndex = crearCampoIndex();
		btnEliminarTorre = crearBoton("Eliminar", colorRojo, colorBlanco);
		btnRefrescarTorre = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelAcciones.add(lblIdx);
		panelAcciones.add(txtTorreIndex);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnEliminarTorre);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnRefrescarTorre);
		panelLista.add(panelAcciones);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(panelLista);
		return panel;
	}

	/**
	 * Construye la subpestana de gestion de apartamentos. Contiene un formulario
	 * para crear apartamentos y una lista con los registrados. <br>
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel con el formulario y la lista de apartamentos.
	 * <br>
	 *
	 * @return panel con la interfaz de gestion de apartamentos
	 */
	public JPanel construirTabApartamentos() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nuevo Apartamento", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		comboTorreApto = crearCombo(new String[] { "-- Seleccione Torre --" });
		txtAptoNumero = crearCampo();
		comboAptoEstado = crearCombo(new String[] { "OCUPADO_PROPIETARIO", "ARRENDADO", "DESOCUPADO" });

		agregarCombo(formulario, "Torre:", comboTorreApto);
		agregarCampo(formulario, "Numero (ej: 1402 torre = 1, piso 4, apto 02):", txtAptoNumero);
		agregarCampo(formulario, "Número:", txtAptoNumero);
		agregarCombo(formulario, "Estado:", comboAptoEstado);

		JLabel lblNota = new JLabel("* Propietario/arrendatario se asignan luego");
		lblNota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNota.setForeground(colorTextoSuave);
		lblNota.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(lblNota);
		formulario.add(Box.createVerticalStrut(10));

		JPanel panelBotones = crearPanelBotones();
		btnCrearApto = crearBoton("Crear Apartamento", colorVerde, colorBlanco);
		btnLimpiarApto = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnCrearApto);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiarApto);
		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(8));

		lblMensajeApto = crearLabelMensaje();
		formulario.add(lblMensajeApto);
		formulario.add(Box.createVerticalGlue());

		JPanel panelLista = construirPanelLista("Apartamentos registrados");
		areaListaApto = extraerArea(panelLista);
		JPanel panelAcciones = crearPanelBotones();
		JLabel lblIdx = crearLabelIdx();
		txtAptoIndex = crearCampoIndex();
		btnEliminarApto = crearBoton("Eliminar", colorRojo, colorBlanco);
		btnRefrescarApto = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelAcciones.add(lblIdx);
		panelAcciones.add(txtAptoIndex);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnEliminarApto);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnRefrescarApto);
		panelLista.add(panelAcciones);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(panelLista);
		return panel;
	}

	/**
	 * Construye la subpestana de gestion de zonas comunes. Contiene un formulario
	 * para crear zonas con aforo, horarios y costo de reserva, y una lista con las
	 * registradas. <br>
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel con el formulario y la lista de zonas
	 * comunes. <br>
	 *
	 * @return panel con la interfaz de gestion de zonas comunes
	 */
	public JPanel construirTabZonasComunes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nueva Zona Comun", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		comboConjuntoZona = crearCombo(new String[] { "-- Seleccione Conjunto --" });
		comboZonaTipo = crearCombo(new String[] { "SALON", "GIMNASIO", "TERRAZA", "CANCHA", "COWORKING",
				"ZONA_INFANTIL", "ZONA_MASCOTAS", "BICICLETERO", "DEPOSITO", "CUARTO_RECICLAJE" });
		comboZonaEstado = crearCombo(new String[] { "DISPONIBLE", "MANTENIMIENTO", "BLOQUEADA" });
		txtZonaAforo = crearCampo();
		txtZonaCosto = crearCampo();
		txtZonaHoraApertura = crearCampo();
		txtZonaHoraCierre = crearCampo();

		chkZonaRequiereReserva = new JCheckBox("Requiere reserva previa");
		chkZonaRequiereReserva.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chkZonaRequiereReserva.setForeground(colorVerdeOscuro);
		chkZonaRequiereReserva.setBackground(colorPanel);
		chkZonaRequiereReserva.setAlignmentX(LEFT_ALIGNMENT);

		agregarCombo(formulario, "Conjunto:", comboConjuntoZona);
		agregarCombo(formulario, "Tipo:", comboZonaTipo);
		agregarCombo(formulario, "Estado inicial:", comboZonaEstado);
		agregarCampo(formulario, "Aforo maximo:", txtZonaAforo);
		agregarCampo(formulario, "Costo reserva ($):", txtZonaCosto);
		agregarCampo(formulario, "Hora apertura (HH:mm):", txtZonaHoraApertura);
		agregarCampo(formulario, "Hora cierre (HH:mm):", txtZonaHoraCierre);

		formulario.add(chkZonaRequiereReserva);
		formulario.add(Box.createVerticalStrut(12));

		JPanel panelBotones = crearPanelBotones();
		btnCrearZona = crearBoton("Crear Zona", colorVerde, colorBlanco);
		btnLimpiarZona = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnCrearZona);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiarZona);
		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(8));

		lblMensajeZona = crearLabelMensaje();
		formulario.add(lblMensajeZona);

		JScrollPane scrollFormulario = new JScrollPane(formulario);
		scrollFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollFormulario.setBorder(null);
		scrollFormulario.setMinimumSize(new Dimension(360, 0));
		scrollFormulario.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));
		scrollFormulario.setPreferredSize(new Dimension(360, 0));

		JPanel panelLista = construirPanelLista("Zonas comunes registradas");
		areaListaZona = extraerArea(panelLista);

		JPanel panelAcciones = crearPanelBotones();
		JLabel lblIdx = crearLabelIdx();
		txtZonaIndex = crearCampoIndex();
		btnEliminarZona = crearBoton("Eliminar", colorRojo, colorBlanco);
		btnRefrescarZona = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelAcciones.add(lblIdx);
		panelAcciones.add(txtZonaIndex);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnEliminarZona);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnRefrescarZona);
		panelLista.add(panelAcciones);

		panel.add(scrollFormulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(panelLista);
		return panel;
	}

	/**
	 * Construye la subpestana de gestion de parqueaderos. Contiene un formulario
	 * para crear parqueaderos y una lista con los registrados. <br>
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel con el formulario y la lista de parqueaderos.
	 * <br>
	 *
	 * @return panel con la interfaz de gestion de parqueaderos
	 */
	public JPanel construirTabParqueaderos() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nuevo Parqueadero", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		comboConjuntoParq = crearCombo(new String[] { "-- Seleccione Conjunto --" });
		txtParqNumero = crearCampo();
		comboParqTipo = crearCombo(new String[] { "CARRO", "MOTO", "BICICLETA", "VISITANTE", "ELECTRICO" });
		comboParqEstado = crearCombo(new String[] { "DISPONIBLE", "OCUPADO", "MANTENIMIENTO" });

		agregarCombo(formulario, "Conjunto:", comboConjuntoParq);
		agregarCampo(formulario, "Número:", txtParqNumero);
		agregarCombo(formulario, "Tipo:", comboParqTipo);
		agregarCombo(formulario, "Estado inicial:", comboParqEstado);

		formulario.add(Box.createVerticalStrut(10));

		JPanel panelBotones = crearPanelBotones();
		btnCrearParq = crearBoton("Crear Parqueadero", colorVerde, colorBlanco);
		btnLimpiarParq = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnCrearParq);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiarParq);
		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(8));

		lblMensajeParq = crearLabelMensaje();
		formulario.add(lblMensajeParq);
		formulario.add(Box.createVerticalGlue());

		JPanel panelLista = construirPanelLista("Parqueaderos registrados");
		areaListaParq = extraerArea(panelLista);

		JPanel panelAcciones = crearPanelBotones();
		JLabel lblIdx = crearLabelIdx();
		txtParqIndex = crearCampoIndex();
		btnEliminarParq = crearBoton("Eliminar", colorRojo, colorBlanco);
		btnRefrescarParq = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelAcciones.add(lblIdx);
		panelAcciones.add(txtParqIndex);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnEliminarParq);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnRefrescarParq);
		panelLista.add(panelAcciones);

		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(panelLista);
		return panel;
	}

	/**
	 * Construye un panel de lista con titulo y area de texto de solo lectura. <br>
	 * <b>pre</b> El titulo no debe ser null. <br>
	 * <b>post</b> Se retorna un panel listo para mostrar registros en texto. <br>
	 *
	 * @param titulo titulo del borde del panel de lista
	 * @return panel con el area de texto de lista
	 */
	public JPanel construirPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), titulo, 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 11));
		area.setBackground(colorCampoFondo);
		area.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scroll = new JScrollPane(area);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		panel.add(Box.createVerticalStrut(10));

		panel.putClientProperty("area", area);
		return panel;
	}

	/**
	 * Extrae el JTextArea almacenado como propiedad del panel de lista. <br>
	 * <b>pre</b> El panel debe haber sido creado con construirPanelLista. <br>
	 * <b>post</b> Se retorna el area de texto del panel. <br>
	 *
	 * @param panelLista panel del cual extraer el area de texto
	 * @return el JTextArea del panel de lista
	 */
	public JTextArea extraerArea(JPanel panelLista) {
		return (JTextArea) panelLista.getClientProperty("area");
	}

	/**
	 * Crea un panel horizontal para contener botones de accion. <br>
	 * <b>post</b> Se retorna un panel vacio listo para agregar botones. <br>
	 *
	 * @return panel horizontal transparente alineado a la izquierda
	 */
	public JPanel crearPanelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Agrega una etiqueta y un campo de texto al panel indicado. <br>
	 * <b>pre</b> El panel y el campo no deben ser null. <br>
	 * <b>post</b> La etiqueta y el campo quedan agregados al panel con espaciado.
	 * <br>
	 *
	 * @param panel    panel al que se agrega el campo
	 * @param etiqueta texto de la etiqueta del campo
	 * @param campo    campo de texto a agregar
	 */
	public void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(4));
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		campo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(campo);
		panel.add(Box.createVerticalStrut(10));
	}

	/**
	 * Agrega una etiqueta y un combo box al panel indicado. <br>
	 * <b>pre</b> El panel y el combo no deben ser null. <br>
	 * <b>post</b> La etiqueta y el combo quedan agregados al panel con espaciado.
	 * <br>
	 *
	 * @param panel    panel al que se agrega el combo
	 * @param etiqueta texto de la etiqueta del combo
	 * @param combo    combo box a agregar
	 */
	public void agregarCombo(JPanel panel, String etiqueta, JComboBox<String> combo) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(4));
		combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		combo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(combo);
		panel.add(Box.createVerticalStrut(10));
	}

	/**
	 * Crea un campo de texto con el estilo visual del sistema. <br>
	 * <b>post</b> Se retorna un JTextField estilizado listo para usar. <br>
	 *
	 * @return campo de texto con estilos aplicados
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
	 * Crea un campo de texto de tamano reducido para ingresar indices. <br>
	 * <b>post</b> Se retorna un JTextField con ancho maximo de 60px. <br>
	 *
	 * @return campo de texto para indices con tamano restringido
	 */
	public JTextField crearCampoIndex() {
		JTextField tf = crearCampo();
		tf.setMaximumSize(new Dimension(60, 32));
		return tf;
	}

	/**
	 * Crea un combo box con el estilo visual del sistema. <br>
	 * <b>pre</b> El arreglo de opciones no debe ser null ni vacio. <br>
	 * <b>post</b> Se retorna un JComboBox estilizado con las opciones dadas. <br>
	 *
	 * @param opciones arreglo de opciones a mostrar en el combo
	 * @return combo box estilizado con las opciones
	 */
	public JComboBox<String> crearCombo(String[] opciones) {
		JComboBox<String> combo = new JComboBox<>(opciones);
		combo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		combo.setBackground(colorCampoFondo);
		combo.setForeground(colorVerdeOscuro);
		combo.setMaximumRowCount(15);
		return combo;
	}

	/**
	 * Crea un boton con el estilo visual del sistema. <br>
	 * <b>pre</b> El texto, fondo y letra no deben ser null. <br>
	 * <b>post</b> Se retorna un JButton estilizado listo para usar. <br>
	 *
	 * @param texto texto a mostrar en el boton
	 * @param fondo color de fondo del boton
	 * @param letra color del texto del boton
	 * @return boton estilizado
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
	 * Crea una etiqueta para mostrar mensajes de exito o error. <br>
	 * <b>post</b> Se retorna un JLabel con espacio en blanco inicial. <br>
	 *
	 * @return etiqueta de mensaje inicializada con espacio en blanco
	 */
	public JLabel crearLabelMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Crea una etiqueta con el texto "Index:" para los campos de indice. <br>
	 * <b>post</b> Se retorna un JLabel con el texto "Index:" estilizado. <br>
	 *
	 * @return etiqueta con texto "Index:"
	 */
	public JLabel crearLabelIdx() {
		JLabel lbl = new JLabel("Index: ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setForeground(colorVerdeOscuro);
		return lbl;
	}

	/**
	 * Muestra un mensaje en la etiqueta de torres con el color segun el tipo. <br>
	 * <b>pre</b> lblMensajeTorre debe estar inicializado. <br>
	 * <b>post</b> El mensaje queda visible en la interfaz con el color
	 * correspondiente. <br>
	 *
	 * @param msg     mensaje a mostrar
	 * @param esError true para mostrar en rojo, false para mostrar en verde
	 */
	public void setMensajeTorre(String msg, boolean esError) {
		lblMensajeTorre.setForeground(esError ? colorRojo : colorVerdeOscuro);
		lblMensajeTorre.setText(msg);
	}

	/**
	 * Muestra un mensaje en la etiqueta de apartamentos con el color segun el tipo.
	 * <br>
	 * <b>pre</b> lblMensajeApto debe estar inicializado. <br>
	 * <b>post</b> El mensaje queda visible en la interfaz con el color
	 * correspondiente. <br>
	 *
	 * @param msg     mensaje a mostrar
	 * @param esError true para mostrar en rojo, false para mostrar en verde
	 */
	public void setMensajeApto(String msg, boolean esError) {
		lblMensajeApto.setForeground(esError ? colorRojo : colorVerdeOscuro);
		lblMensajeApto.setText(msg);
	}

	/**
	 * Muestra un mensaje en la etiqueta de zonas comunes con el color segun el
	 * tipo. <br>
	 * <b>pre</b> lblMensajeZona debe estar inicializado. <br>
	 * <b>post</b> El mensaje queda visible en la interfaz con el color
	 * correspondiente. <br>
	 *
	 * @param msg     mensaje a mostrar
	 * @param esError true para mostrar en rojo, false para mostrar en verde
	 */
	public void setMensajeZona(String msg, boolean esError) {
		lblMensajeZona.setForeground(esError ? colorRojo : colorVerdeOscuro);
		lblMensajeZona.setText(msg);
	}

	/**
	 * Muestra un mensaje en la etiqueta de parqueaderos con el color segun el tipo.
	 * <br>
	 * <b>pre</b> lblMensajeParq debe estar inicializado. <br>
	 * <b>post</b> El mensaje queda visible en la interfaz con el color
	 * correspondiente. <br>
	 *
	 * @param msg     mensaje a mostrar
	 * @param esError true para mostrar en rojo, false para mostrar en verde
	 */
	public void setMensajeParq(String msg, boolean esError) {
		lblMensajeParq.setForeground(esError ? colorRojo : colorVerdeOscuro);
		lblMensajeParq.setText(msg);
	}

	/**
	 * Limpia el formulario de torres y restablece los campos a su estado inicial.
	 * <br>
	 * <b>post</b> Todos los campos del formulario de torres quedan vacios. <br>
	 */
	public void limpiarTorre() {
		txtTorreNombre.setText("");
		txtTorreNumPiso.setText("");
		comboConjuntoTorre.setSelectedIndex(0);
		lblMensajeTorre.setText(" ");
	}

	/**
	 * Limpia el formulario de apartamentos y restablece los campos a su estado
	 * inicial. <br>
	 * <b>post</b> Todos los campos del formulario de apartamentos quedan vacios.
	 * <br>
	 */
	public void limpiarApto() {
		txtAptoNumero.setText("");
		comboTorreApto.setSelectedIndex(0);
		comboAptoEstado.setSelectedIndex(0);
		lblMensajeApto.setText(" ");
	}

	/**
	 * Limpia el formulario de zonas comunes y restablece los campos a su estado
	 * inicial. <br>
	 * <b>post</b> Todos los campos del formulario de zonas quedan vacios. <br>
	 */
	public void limpiarZona() {
		txtZonaAforo.setText("");
		txtZonaCosto.setText("");
		txtZonaHoraApertura.setText("");
		txtZonaHoraCierre.setText("");
		chkZonaRequiereReserva.setSelected(false);
		comboConjuntoZona.setSelectedIndex(0);
		comboZonaTipo.setSelectedIndex(0);
		comboZonaEstado.setSelectedIndex(0);
		lblMensajeZona.setText(" ");
	}

	/**
	 * Limpia el formulario de parqueaderos y restablece los campos a su estado
	 * inicial. <br>
	 * <b>post</b> Todos los campos del formulario de parqueaderos quedan vacios.
	 * <br>
	 */
	public void limpiarParq() {
		txtParqNumero.setText("");
		comboConjuntoParq.setSelectedIndex(0);
		comboParqTipo.setSelectedIndex(0);
		comboParqEstado.setSelectedIndex(0);
		lblMensajeParq.setText(" ");
	}

	/** @return combo de seleccion de conjunto para torres */
	public JComboBox<String> getComboConjuntoTorre() {
		return comboConjuntoTorre;
	}

	/** @return campo de texto del nombre de la torre */
	public JTextField getTxtTorreNombre() {
		return txtTorreNombre;
	}

	/** @return campo de texto del numero de pisos de la torre */
	public JTextField getTxtTorreNumPisos() {
		return txtTorreNumPiso;
	}

	/** @return boton para crear una nueva torre */
	public JButton getBtnCrearTorre() {
		return btnCrearTorre;
	}

	/** @return boton para limpiar el formulario de torres */
	public JButton getBtnLimpiarTorre() {
		return btnLimpiarTorre;
	}

	/** @return boton para eliminar una torre por indice */
	public JButton getBtnEliminarTorre() {
		return btnEliminarTorre;
	}

	/** @return boton para refrescar la lista de torres */
	public JButton getBtnRefrescarTorre() {
		return btnRefrescarTorre;
	}

	/** @return campo de texto para el indice de la torre a eliminar */
	public JTextField getTxtTorreIndex() {
		return txtTorreIndex;
	}

	/** @return area de texto con la lista de torres registradas */
	public JTextArea getAreaListaTorres() {
		return areaListaTorre;
	}

	/** @return combo de seleccion de torre para apartamentos */
	public JComboBox<String> getComboTorreApto() {
		return comboTorreApto;
	}

	/** @return campo de texto del numero del apartamento */
	public JTextField getTxtAptoNumero() {
		return txtAptoNumero;
	}

	/** @return combo de seleccion del estado del apartamento */
	public JComboBox<String> getComboAptoEstado() {
		return comboAptoEstado;
	}

	/** @return boton para crear un nuevo apartamento */
	public JButton getBtnCrearApto() {
		return btnCrearApto;
	}

	/** @return boton para limpiar el formulario de apartamentos */
	public JButton getBtnLimpiarApto() {
		return btnLimpiarApto;
	}

	/** @return boton para eliminar un apartamento por indice */
	public JButton getBtnEliminarApto() {
		return btnEliminarApto;
	}

	/** @return boton para refrescar la lista de apartamentos */
	public JButton getBtnRefrescarApto() {
		return btnRefrescarApto;
	}

	/** @return campo de texto para el indice del apartamento a eliminar */
	public JTextField getTxtAptoIndex() {
		return txtAptoIndex;
	}

	/** @return area de texto con la lista de apartamentos registrados */
	public JTextArea getAreaListaAptos() {
		return areaListaApto;
	}

	/** @return combo de seleccion de conjunto para zonas comunes */
	public JComboBox<String> getComboConjuntoZona() {
		return comboConjuntoZona;
	}

	/** @return combo de seleccion del tipo de zona comun */
	public JComboBox<String> getComboZonaTipo() {
		return comboZonaTipo;
	}

	/** @return combo de seleccion del estado de la zona comun */
	public JComboBox<String> getComboZonaEstado() {
		return comboZonaEstado;
	}

	/** @return campo de texto del aforo maximo de la zona */
	public JTextField getTxtZonaAforo() {
		return txtZonaAforo;
	}

	/** @return campo de texto del costo de reserva de la zona */
	public JTextField getTxtZonaCosto() {
		return txtZonaCosto;
	}

	/** @return campo de texto de la hora de apertura de la zona */
	public JTextField getTxtZonaHoraApertura() {
		return txtZonaHoraApertura;
	}

	/** @return campo de texto de la hora de cierre de la zona */
	public JTextField getTxtZonaHoraCierre() {
		return txtZonaHoraCierre;
	}

	/** @return checkbox que indica si la zona requiere reserva previa */
	public JCheckBox getChkZonaRequiereReserva() {
		return chkZonaRequiereReserva;
	}

	/** @return boton para crear una nueva zona comun */
	public JButton getBtnCrearZona() {
		return btnCrearZona;
	}

	/** @return boton para limpiar el formulario de zonas comunes */
	public JButton getBtnLimpiarZona() {
		return btnLimpiarZona;
	}

	/** @return boton para eliminar una zona comun por indice */
	public JButton getBtnEliminarZona() {
		return btnEliminarZona;
	}

	/** @return boton para refrescar la lista de zonas comunes */
	public JButton getBtnRefrescarZona() {
		return btnRefrescarZona;
	}

	/** @return campo de texto para el indice de la zona a eliminar */
	public JTextField getTxtZonaIndex() {
		return txtZonaIndex;
	}

	/** @return area de texto con la lista de zonas comunes registradas */
	public JTextArea getAreaListaZonas() {
		return areaListaZona;
	}

	/** @return combo de seleccion de conjunto para parqueaderos */
	public JComboBox<String> getComboConjuntoParq() {
		return comboConjuntoParq;
	}

	/** @return campo de texto del numero del parqueadero */
	public JTextField getTxtParqNumero() {
		return txtParqNumero;
	}

	/** @return combo de seleccion del tipo de parqueadero */
	public JComboBox<String> getComboParqTipo() {
		return comboParqTipo;
	}

	/** @return combo de seleccion del estado del parqueadero */
	public JComboBox<String> getComboParqEstado() {
		return comboParqEstado;
	}

	/** @return boton para crear un nuevo parqueadero */
	public JButton getBtnCrearParq() {
		return btnCrearParq;
	}

	/** @return boton para limpiar el formulario de parqueaderos */
	public JButton getBtnLimpiarParq() {
		return btnLimpiarParq;
	}

	/** @return boton para eliminar un parqueadero por indice */
	public JButton getBtnEliminarParq() {
		return btnEliminarParq;
	}

	/** @return boton para refrescar la lista de parqueaderos */
	public JButton getBtnRefrescarParq() {
		return btnRefrescarParq;
	}

	/** @return campo de texto para el indice del parqueadero a eliminar */
	public JTextField getTxtParqIndex() {
		return txtParqIndex;
	}

	/** @return area de texto con la lista de parqueaderos registrados */
	public JTextArea getAreaListaParq() {
		return areaListaParq;
	}
}