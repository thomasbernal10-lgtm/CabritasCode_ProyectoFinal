package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

/**
 * Pestana de finanzas del rol Propietario en el sistema GreenBuilding Manager.
 * Permite al propietario consultar el estado de cuenta de su apartamento,
 * visualizar sus obligaciones pendientes y revisar el historial de pagos
 * realizados. La informacion se organiza en tres subpestanas independientes.
 * <br>
 * <b>pre</b> Los colores deben estar inicializados antes de construir el panel.
 * <br>
 * <b>post</b> El panel queda construido con las tres subpestanas listas para
 * recibir datos desde el controlador. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PestanaFinancierosPropietario extends JPanel {

	/**
	 * Identificador de version para la serializacion de la clase.
	 */
	private static final long serialVersionUID = 3391827364509182736L;

	/** Campo de texto para ingresar el numero del apartamento a consultar. */
	private JTextField txtAptoConsulta;

	/** Boton para solicitar el estado de cuenta del apartamento. */
	private JButton btnVerEstadoCuenta;

	/** Boton para refrescar la informacion financiera mostrada. */
	private JButton btnRefrescarFinanza;

	/** Area de texto que muestra el estado de cuenta del apartamento. */
	private JTextArea areaEstadoCuenta;

	/** Etiqueta que muestra mensajes de resultado o error al usuario. */
	private JLabel lblMensaje;

	/** Area de texto que muestra las obligaciones pendientes del apartamento. */
	private JTextArea areaObligacion;

	/** Area de texto que muestra el historial de pagos realizados. */
	private JTextArea areaPago;

	/** Color de fondo de los paneles internos. */
	private Color colorPanel;

	/** Color verde oscuro para titulos y cabeceras. */
	private Color colorVerdeOscuro;

	/** Color verde principal del tema visual. */
	private Color colorVerde;

	/** Color de texto suave para etiquetas secundarias. */
	private Color colorTextoSuave;

	/** Color de fondo para campos de texto. */
	private Color colorCampoFondo;

	/** Color de borde para campos de texto. */
	private Color colorCampoBorde;

	/** Color para bordes y separadores. */
	private Color colorBorde;

	/** Color blanco para textos sobre fondos oscuros. */
	private Color colorBlanco;

	/** Color rojo para mensajes de error y alertas. */
	private Color colorRojo;

	/**
	 * Constructor completo. Inicializa la paleta de colores y construye todos los
	 * componentes graficos de la pestana de finanzas del propietario. <br>
	 * <b>pre</b> Ninguno de los parametros de color debe ser null. <br>
	 * <b>post</b> La pestana queda completamente construida con las tres
	 * subpestanas inicializadas. <br>
	 *
	 * @param colorPanel       Color de fondo de los paneles. colorPanel != null
	 * @param colorVerdeOscuro Color verde oscuro para cabeceras. colorVerdeOscuro
	 *                         != null
	 * @param colorVerde       Color verde principal. colorVerde != null
	 * @param colorTextoSuave  Color de texto secundario. colorTextoSuave != null
	 * @param colorCampoFondo  Color de fondo de campos. colorCampoFondo != null
	 * @param colorCampoBorde  Color de borde de campos. colorCampoBorde != null
	 * @param colorBorde       Color de bordes generales. colorBorde != null
	 * @param colorBlanco      Color blanco. colorBlanco != null
	 * @param colorRojo        Color rojo para errores. colorRojo != null
	 */
	public PestanaFinancierosPropietario(Color colorPanel, Color colorVerdeOscuro, Color colorVerde,
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
	 * Inicializa el layout del panel principal y agrega las tres subpestanas de
	 * estado de cuenta, obligaciones y pagos. <br>
	 * <b>pre</b> La paleta de colores debe estar inicializada. <br>
	 * <b>post</b> El panel queda con BoxLayout vertical y las tres subpestanas
	 * agregadas al JTabbedPane. <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Estado de Cuenta", crearSubPanelEstadoCuenta());
		tabs.addTab("Obligaciones", crearSubPanelObligaciones());
		tabs.addTab("Pagos Realizados", crearSubPanelPagos());
		add(tabs);
	}

	/**
	 * Crea el subpanel de consulta de estado de cuenta con el formulario de
	 * busqueda por numero de apartamento y el area de resultado. <br>
	 * <b>pre</b> La paleta de colores debe estar inicializada. <br>
	 * <b>post</b> Se retorna un JPanel con todos los componentes del estado de
	 * cuenta inicializados y listos para recibir datos. <br>
	 *
	 * @return JPanel con el subpanel de estado de cuenta
	 */
	public JPanel crearSubPanelEstadoCuenta() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel titulo = new JLabel("Consulta de Estado de Cuenta");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
		titulo.setForeground(colorVerdeOscuro);
		titulo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(titulo);
		panel.add(Box.createVerticalStrut(10));

		JLabel lbl = new JLabel(
				"Consulte el estado financiero de su apartamento: obligaciones pendientes, pagos realizados y saldo total.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(15));

		JPanel panelConsulta = new JPanel();
		panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.X_AXIS));
		panelConsulta.setBackground(colorPanel);
		panelConsulta.setAlignmentX(LEFT_ALIGNMENT);

		panelConsulta.add(crearEtiqueta("No. Apartamento:"));
		panelConsulta.add(Box.createHorizontalStrut(8));
		txtAptoConsulta = new JTextField();
		txtAptoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAptoConsulta.setBackground(colorCampoFondo);
		txtAptoConsulta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
		txtAptoConsulta.setMaximumSize(new Dimension(150, 28));
		panelConsulta.add(txtAptoConsulta);
		panelConsulta.add(Box.createHorizontalStrut(10));

		btnVerEstadoCuenta = crearBoton("Ver Estado de Cuenta", colorVerdeOscuro, colorBlanco);
		panelConsulta.add(btnVerEstadoCuenta);
		panelConsulta.add(Box.createHorizontalStrut(10));

		btnRefrescarFinanza = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelConsulta.add(btnRefrescarFinanza);

		panel.add(panelConsulta);
		panel.add(Box.createVerticalStrut(8));

		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMensaje.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblMensaje);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelLista = crearPanelLista("Estado de Cuenta - Mi Apartamento");
		areaEstadoCuenta = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);

		return panel;
	}

	/**
	 * Crea el subpanel de visualizacion de obligaciones pendientes y vencidas. <br>
	 * <b>pre</b> La paleta de colores debe estar inicializada. <br>
	 * <b>post</b> Se retorna un JPanel con el area de obligaciones lista para
	 * recibir datos. <br>
	 *
	 * @return JPanel con el subpanel de obligaciones
	 */
	public JPanel crearSubPanelObligaciones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel lbl = new JLabel("Obligaciones pendientes y vencidas de su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelLista = crearPanelLista("Mis Obligaciones");
		areaObligacion = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);

		return panel;
	}

	/**
	 * Crea el subpanel de visualizacion del historial de pagos realizados. <br>
	 * <b>pre</b> La paleta de colores debe estar inicializada. <br>
	 * <b>post</b> Se retorna un JPanel con el area de pagos lista para recibir
	 * datos. <br>
	 *
	 * @return JPanel con el subpanel de pagos realizados
	 */
	public JPanel crearSubPanelPagos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel lbl = new JLabel("Historial de pagos realizados para su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelLista = crearPanelLista("Mis Pagos Realizados");
		areaPago = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);

		return panel;
	}

	/**
	 * Crea una etiqueta con el estilo visual de la pestana. <br>
	 * <b>pre</b> El texto no debe ser null. <br>
	 * <b>post</b> Se retorna un JLabel con fuente, color y alineacion aplicados.
	 * <br>
	 *
	 * @param texto Texto a mostrar en la etiqueta. texto != null
	 * @return JLabel con el estilo corporativo aplicado
	 */
	public JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Crea un panel contenedor con un JTextArea dentro de un JScrollPane con
	 * titulo. Se usa para las tres areas de visualizacion de datos financieros.
	 * <br>
	 * <b>pre</b> El titulo no debe ser null. <br>
	 * <b>post</b> Se retorna un JPanel con el area de texto y el scroll
	 * configurados y listos. <br>
	 *
	 * @param titulo Titulo del borde del panel. titulo != null
	 * @return JPanel con JTextArea dentro de JScrollPane titulado
	 */
	public JPanel crearPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		JTextArea area = new JTextArea(12, 55);
		area.setFont(new Font("Monospaced", Font.PLAIN, 11));
		area.setBackground(colorCampoFondo);
		area.setForeground(colorVerdeOscuro);
		area.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), titulo, 0, 0,
				new Font("SansSerif", Font.BOLD, 11), colorVerdeOscuro));
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		return panel;
	}

	/**
	 * Crea un boton con el estilo visual del sistema GreenBuilding. <br>
	 * <b>pre</b> Los parametros no deben ser null. <br>
	 * <b>post</b> Se retorna un JButton configurado sin borde ni foco pintado. <br>
	 *
	 * @param texto Texto del boton. texto != null
	 * @param fondo Color de fondo. fondo != null
	 * @param letra Color del texto. letra != null
	 * @return JButton con el estilo corporativo aplicado
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
	 * Muestra un mensaje en la etiqueta de estado con color segun si es error o
	 * exito. <br>
	 * <b>pre</b> El lblMensaje debe estar inicializado. <br>
	 * <b>post</b> La etiqueta muestra el mensaje en rojo si es error o en verde
	 * oscuro si es exitoso. <br>
	 *
	 * @param msg     Mensaje a mostrar. msg != null
	 * @param esError true si el mensaje es de error; false si es de exito
	 */
	public void setMensaje(String msg, boolean esError) {
		if (esError) {
			lblMensaje.setForeground(colorRojo);
		} else {
			lblMensaje.setForeground(colorVerdeOscuro);
		}
		lblMensaje.setText(msg);
	}

	/**
	 * Retorna el campo de texto para ingresar el numero de apartamento. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextField del numero de apartamento
	 */
	public JTextField getTxtAptoConsulta() {
		return txtAptoConsulta;
	}

	/**
	 * Retorna el boton de ver estado de cuenta. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton de consulta de estado de cuenta
	 */
	public JButton getBtnVerEstadoCuenta() {
		return btnVerEstadoCuenta;
	}

	/**
	 * Retorna el boton de refrescar finanzas. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JButton de refrescar
	 */
	public JButton getBtnRefrescarFinanzas() {
		return btnRefrescarFinanza;
	}

	/**
	 * Retorna el area de texto del estado de cuenta. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea del estado de cuenta
	 */
	public JTextArea getAreaEstadoCuenta() {
		return areaEstadoCuenta;
	}

	/**
	 * Retorna el area de texto de las obligaciones. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea de obligaciones
	 */
	public JTextArea getAreaObligaciones() {
		return areaObligacion;
	}

	/**
	 * Retorna el area de texto de los pagos realizados. <br>
	 * <b>pre</b> El objeto debe estar instanciado. <br>
	 * <b>post</b> No se modifica el estado del objeto. <br>
	 *
	 * @return JTextArea de pagos realizados
	 */
	public JTextArea getAreaPagos() {
		return areaPago;
	}
}