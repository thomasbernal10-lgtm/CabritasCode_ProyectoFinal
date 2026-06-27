package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 * Panel correspondiente a las funcionalidades del consejo administrativo del
 * conjunto. Permite generar reportes, administrar cuotas de administracion y
 * visualizar incidentes globales registrados en el sistema.
 * 
 * @author GreenBuilding
 */
public class PestanaConsejo extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -1649153079885991596L;

	private Color colorPanel;
	private Color colorVerdeOscuro;
	private Color colorVerde;
	private Color colorTextoSuave;
	private Color colorCampoFondo;
	private Color colorCampoBorde;
	private Color colorBorde;
	private Color colorBlanco;
	private Color colorRojo;
	private JComboBox<String> comboTipoReporte;
	private JButton btnGenerarReporte;
	private JTextArea areaReporte;
	private JLabel lblMensajeReporte;
	private JTextField txtMontoCuota;
	private JButton btnGenerarCuota;
	private JLabel lblMensajeCuota;
	private JTextArea areaMoroso;
	private JButton btnRefrescarMoroso;
	private JTextArea areaIncidente;
	private JButton btnRefrescarIncidente;

	/**
	 * Construye la pestaña del consejo administrativo e inicializa los colores y
	 * componentes graficos del panel.
	 * 
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> La interfaz grafica de la pestaña queda construida e
	 * inicializada. <br>
	 * 
	 * @param colorPanel       Color de fondo principal del panel
	 * @param colorVerdeOscuro Color utilizado para textos y bordes principales
	 * @param colorVerde       Color utilizado para botones principales
	 * @param colorTextoSuave  Color utilizado para textos secundarios
	 * @param colorCampoFondo  Color de fondo de los campos de texto
	 * @param colorCampoBorde  Color del borde de los campos
	 * @param colorBorde       Color de los bordes de paneles
	 * @param colorBlanco      Color utilizado para texto claro
	 * @param colorRojo        Color utilizado para mensajes de error
	 */
	public PestanaConsejo(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * Construye la estructura principal de la pestaña agregando las diferentes
	 * secciones del consejo administrativo.
	 * 
	 * <b>pre</b> Los atributos graficos del panel deben estar inicializados. <br>
	 * <b>post</b> Se agregan todas las pestañas internas al panel principal. <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Reportes del Conjunto", crearSubPanelReportes());
		tabs.addTab("Cuotas y Morosos", crearSubPanelCuotas());
		tabs.addTab("Incidentes Globales", crearSubPanelIncidentes());
		add(tabs);
	}

	/**
	 * Crea el subpanel encargado de la generacion de reportes del conjunto.
	 * 
	 * <b>pre</b> Los colores y componentes deben estar inicializados. <br>
	 * <b>post</b> Se retorna un panel con las opciones de reportes y su area de
	 * visualizacion. <br>
	 * 
	 * @return JPanel con el contenido de reportes
	 */
	public JPanel crearSubPanelReportes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		izq.setBackground(colorPanel);
		izq.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
		izq.setPreferredSize(new Dimension(300, 0));
		izq.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Generar Reporte", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		comboTipoReporte = new JComboBox<>(new String[] { "Ocupacion de apartamentos", "Residentes activos e inactivos",
				"Vehiculos registrados", "Reservas de zonas comunes", "Mantenimientos abiertos",
				"Incidentes por tipo y gravedad", "Pagos realizados", "Pagos pendientes", "Multas generadas",
				"Uso de parqueaderos", "Indicadores de sostenibilidad" });
		estilizarCombo(comboTipoReporte);
		izq.add(crearEtiqueta("Tipo de reporte:"));
		izq.add(Box.createVerticalStrut(4));
		izq.add(comboTipoReporte);
		izq.add(Box.createVerticalStrut(12));
		JPanel botones = panelBotones();
		btnGenerarReporte = crearBoton("Generar y Exportar PDF", colorVerde, colorBlanco);
		botones.add(btnGenerarReporte);
		izq.add(botones);
		izq.add(Box.createVerticalStrut(8));
		lblMensajeReporte = crearLblMensaje();
		izq.add(lblMensajeReporte);
		izq.add(Box.createVerticalGlue());
		JPanel der = crearPanelLista("Contenido del reporte");
		areaReporte = (JTextArea) ((JScrollPane) der.getComponent(0)).getViewport().getView();
		panel.add(izq);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(der);
		return panel;
	}

	/**
	 * Crea el subpanel encargado de la generacion de cuotas y visualizacion de
	 * apartamentos morosos.
	 * 
	 * <b>pre</b> Los componentes graficos deben estar inicializados. <br>
	 * <b>post</b> Se retorna un panel funcional para cuotas y morosos. <br>
	 * 
	 * @return JPanel con la informacion de cuotas y morosos
	 */
	public JPanel crearSubPanelCuotas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		izq.setBackground(colorPanel);
		izq.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
		izq.setPreferredSize(new Dimension(300, 0));
		izq.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Generar Cuotas Mensuales",
						0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtMontoCuota = crearCampo();
		JLabel lblNota = new JLabel("* Genera cuota a todos los aptos ocupados");
		lblNota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNota.setForeground(colorTextoSuave);
		lblNota.setAlignmentX(LEFT_ALIGNMENT);
		agregarCampo(izq, "Monto de cuota ($):", txtMontoCuota);
		izq.add(lblNota);
		izq.add(Box.createVerticalStrut(12));
		JPanel botones = panelBotones();
		btnGenerarCuota = crearBoton("Generar Cuotas", colorVerde, colorBlanco);
		botones.add(btnGenerarCuota);
		izq.add(botones);
		izq.add(Box.createVerticalStrut(8));
		lblMensajeCuota = crearLblMensaje();
		izq.add(lblMensajeCuota);
		izq.add(Box.createVerticalGlue());
		JPanel der = crearPanelLista("Apartamentos morosos");
		areaMoroso = (JTextArea) ((JScrollPane) der.getComponent(0)).getViewport().getView();
		btnRefrescarMoroso = crearBoton("Refrescar morosos", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnRefrescarMoroso);
		der.add(Box.createVerticalStrut(8));
		der.add(panelRef);
		panel.add(izq);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(der);
		return panel;
	}

	/**
	 * Crea el subpanel encargado de visualizar los incidentes globales del
	 * conjunto.
	 * 
	 * <b>pre</b> Los componentes deben estar inicializados. <br>
	 * <b>post</b> Se retorna un panel de solo lectura con los incidentes
	 * registrados. <br>
	 * 
	 * @return JPanel con la lista de incidentes
	 */
	public JPanel crearSubPanelIncidentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lblInfo = new JLabel("Incidentes reportados en el conjunto (solo lectura):");
		lblInfo.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblInfo.setForeground(colorTextoSuave);
		lblInfo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblInfo);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Todos los incidentes del conjunto");
		areaIncidente = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(8));
		JPanel panelRef = panelBotones();
		btnRefrescarIncidente = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRef.add(btnRefrescarIncidente);
		panel.add(panelRef);
		return panel;
	}

	/**
	 * Crea un panel reutilizable que contiene un area de texto desplazable.
	 * 
	 * <b>pre</b> El titulo no debe ser null. <br>
	 * <b>post</b> Se retorna un panel con un JTextArea y JScrollPane asociados.
	 * <br>
	 * 
	 * @param titulo Titulo del panel
	 * @return JPanel configurado para mostrar informacion
	 */
	public JPanel crearPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), titulo, 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 11));
		area.setBackground(colorCampoFondo);
		area.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scroll = new JScrollPane(area);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		return panel;
	}

	/**
	 * Agrega un campo de texto con su respectiva etiqueta a un panel.
	 * 
	 * <b>pre</b> El panel, la etiqueta y el campo no deben ser null. <br>
	 * <b>post</b> El campo y su etiqueta quedan agregados y organizados dentro del
	 * panel. <br>
	 * 
	 * @param panel    Panel donde se agregara el campo
	 * @param etiqueta Texto descriptivo de la etiqueta
	 * @param campo    Campo de texto asociado
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
	 * Crea una etiqueta estilizada para ser utilizada en formularios.
	 * 
	 * <b>pre</b> El texto recibido no debe ser null. <br>
	 * <b>post</b> Se retorna una etiqueta configurada con el estilo definido. <br>
	 * 
	 * @param texto Texto que tendra la etiqueta
	 * @return JLabel configurado
	 */
	public JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Crea y configura un campo de texto con el estilo visual del sistema.
	 * 
	 * <b>pre</b> Los colores de configuracion deben estar inicializados. <br>
	 * <b>post</b> Se retorna un JTextField estilizado y listo para usar. <br>
	 * 
	 * @return JTextField configurado
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
	 * Crea un boton personalizado con colores y estilo definidos.
	 * 
	 * <b>pre</b> El texto y colores no deben ser null. <br>
	 * <b>post</b> Se retorna un JButton estilizado. <br>
	 * 
	 * @param texto Texto que mostrara el boton
	 * @param fondo Color de fondo del boton
	 * @param letra Color del texto del boton
	 * @return JButton configurado
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
	 * Crea un panel auxiliar para organizar botones horizontalmente.
	 * 
	 * <b>pre</b> No requiere precondiciones. <br>
	 * <b>post</b> Se retorna un panel transparente configurado con BoxLayout
	 * horizontal. <br>
	 * 
	 * @return JPanel para organizar botones
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea una etiqueta vacia para mostrar mensajes al usuario.
	 * 
	 * <b>pre</b> El color de error debe estar inicializado. <br>
	 * <b>post</b> Se retorna una etiqueta preparada para mensajes informativos o de
	 * error. <br>
	 * 
	 * @return JLabel para mensajes
	 */
	public JLabel crearLblMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Aplica estilos visuales personalizados a un JComboBox.
	 * 
	 * <b>pre</b> El combo recibido no debe ser null. <br>
	 * <b>post</b> El JComboBox queda estilizado con la apariencia definida. <br>
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
	 * Actualiza el mensaje mostrado en la seccion de reportes.
	 * 
	 * <b>pre</b> La etiqueta de mensaje debe estar inicializada. <br>
	 * <b>post</b> Se actualiza el texto y el color del mensaje dependiendo si es un
	 * error o una confirmacion. <br>
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si es un mensaje de error, false en caso contrario
	 */
	public void setMensajeReporte(String msg, boolean esError) {
		if (esError) {
			lblMensajeReporte.setForeground(colorRojo);
		} else {
			lblMensajeReporte.setForeground(colorVerdeOscuro);
		}
		lblMensajeReporte.setText(msg);
	}

	/**
	 * Actualiza el mensaje mostrado en la seccion de cuotas.
	 * 
	 * <b>pre</b> La etiqueta de mensaje debe estar inicializada. <br>
	 * <b>post</b> Se actualiza el contenido y color del mensaje. <br>
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si el mensaje corresponde a un error
	 */
	public void setMensajeCuota(String msg, boolean esError) {
		if (esError) {
			lblMensajeCuota.setForeground(colorRojo);
		} else {
			lblMensajeCuota.setForeground(colorVerdeOscuro);
		}
		lblMensajeCuota.setText(msg);
	}

	/**
	 * Obtiene el combo box con los tipos de reporte disponibles.
	 * 
	 * <b>pre</b> El combo debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del JComboBox de reportes. <br>
	 * 
	 * @return JComboBox con los tipos de reporte
	 */
	public JComboBox<String> getComboTipoReporte() {
		return comboTipoReporte;
	}

	/**
	 * Obtiene el boton encargado de generar reportes.
	 * 
	 * <b>pre</b> El boton debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del boton de generar reporte. <br>
	 * 
	 * @return JButton de generar reporte
	 */
	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	/**
	 * Obtiene el area de texto donde se visualiza el reporte generado.
	 * 
	 * <b>pre</b> El area de texto debe estar inicializada. <br>
	 * <b>post</b> Se retorna la referencia del area de reporte. <br>
	 * 
	 * @return JTextArea del reporte
	 */
	public JTextArea getAreaReporte() {
		return areaReporte;
	}

	/**
	 * Obtiene el campo donde se ingresa el monto de la cuota.
	 * 
	 * <b>pre</b> El campo debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del campo de monto de cuota. <br>
	 * 
	 * @return JTextField del monto de cuota
	 */
	public JTextField getTxtMontoCuota() {
		return txtMontoCuota;
	}

	/**
	 * Obtiene el boton encargado de generar cuotas.
	 * 
	 * <b>pre</b> El boton debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del boton de generar cuotas. <br>
	 * 
	 * @return JButton de generar cuotas
	 */
	public JButton getBtnGenerarCuotas() {
		return btnGenerarCuota;
	}

	/**
	 * Obtiene el area donde se muestran los apartamentos morosos.
	 * 
	 * <b>pre</b> El area debe estar inicializada. <br>
	 * <b>post</b> Se retorna la referencia del area de morosos. <br>
	 * 
	 * @return JTextArea de morosos
	 */
	public JTextArea getAreaMorosos() {
		return areaMoroso;
	}

	/**
	 * Obtiene el boton utilizado para refrescar la lista de morosos.
	 * 
	 * <b>pre</b> El boton debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del boton de refrescar morosos. <br>
	 * 
	 * @return JButton de refrescar morosos
	 */
	public JButton getBtnRefrescarMorosos() {
		return btnRefrescarMoroso;
	}

	/**
	 * Obtiene el area de texto donde se muestran los incidentes globales.
	 * 
	 * <b>pre</b> El area debe estar inicializada. <br>
	 * <b>post</b> Se retorna la referencia del area de incidentes. <br>
	 * 
	 * @return JTextArea de incidentes
	 */
	public JTextArea getAreaIncidentes() {
		return areaIncidente;
	}

	/**
	 * Obtiene el boton utilizado para refrescar la lista de incidentes.
	 * 
	 * <b>pre</b> El boton debe estar inicializado. <br>
	 * <b>post</b> Se retorna la referencia del boton de refrescar incidentes. <br>
	 * 
	 * @return JButton de refrescar incidentes
	 */
	public JButton getBtnRefrescarIncidentes() {
		return btnRefrescarIncidente;
	}
}