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

/**
 * Panel grafico encargado de generar reportes administrativos y visualizar
 * notificaciones generales del sistema.
 * 
 * Permite seleccionar distintos tipos de reportes relacionados con la gestion
 * del conjunto residencial, exportarlos en PDF y consultar alertas o mensajes
 * importantes generados por el sistema.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaReportesAdmin extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 864655616297004299L;

	/**
	 * Lista desplegable de tipos de reportes disponibles.
	 */
	private JComboBox<String> comboTipoReporte;

	/**
	 * Boton utilizado para generar un reporte.
	 */
	private JButton btnGenerarReporte;

	/**
	 * Boton utilizado para exportar reportes en formato PDF.
	 */
	private JButton btnExportarPDF;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con reportes.
	 */
	private JLabel lblReporteMensaje;

	/**
	 * Area de texto utilizada para visualizar el contenido del reporte generado.
	 */
	private JTextArea areaReporte;

	/**
	 * Area de texto utilizada para mostrar notificaciones del sistema.
	 */
	private JTextArea areaNotificacion;

	/**
	 * Boton utilizado para refrescar las notificaciones.
	 */
	private JButton btnRefrescarNotif;

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
	 * Construye la pestana de reportes administrativos.
	 * 
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> El panel queda completamente inicializado.
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
	public PestanaReportesAdmin(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * Construye la estructura principal del panel.
	 * 
	 * <b>post</b> El panel queda configurado con sus secciones.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(crearPanelIzquierda());
		add(Box.createHorizontalStrut(20));
		add(crearPanelNotificaciones());
	}

	/**
	 * Crea el panel izquierdo correspondiente a la gestion de reportes.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de reportes
	 */
	public JPanel crearPanelIzquierda() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));
		panel.setPreferredSize(new Dimension(360, 0));
		JPanel formReporte = new JPanel();
		formReporte.setLayout(new BoxLayout(formReporte, BoxLayout.Y_AXIS));
		formReporte.setBackground(colorPanel);
		formReporte.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Generar Reporte", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		comboTipoReporte = new JComboBox<>(new String[] { "Ocupacion de apartamentos", "Residentes activos e inactivos",
				"Vehiculos registrados", "Visitantes frecuentes", "Reservas de zonas comunes",
				"Zonas comunes mas utilizadas", "Reservas canceladas o incumplidas", "Mantenimientos abiertos",
				"Mantenimientos cerrados", "Mantenimientos vencidos", "Incidentes por tipo y gravedad",
				"Apartamentos con mayor numero de reportes", "Pagos realizados", "Pagos pendientes", "Multas generadas",
				"Uso de parqueaderos", "Paquetes recibidos y entregados", "Indicadores de sostenibilidad",
				"Campañas ambientales y participacion", "Comportamientos recurrentes" });

		comboTipoReporte.setFont(new Font("SansSerif", Font.PLAIN, 13));
		comboTipoReporte.setBackground(colorCampoFondo);
		comboTipoReporte.setForeground(colorVerdeOscuro);
		comboTipoReporte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		comboTipoReporte.setAlignmentX(LEFT_ALIGNMENT);

		JLabel lblTipo = new JLabel("Tipo de reporte:");
		lblTipo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTipo.setForeground(colorTextoSuave);
		lblTipo.setAlignmentX(LEFT_ALIGNMENT);
		formReporte.add(lblTipo);
		formReporte.add(Box.createVerticalStrut(6));
		formReporte.add(comboTipoReporte);
		formReporte.add(Box.createVerticalStrut(14));

		JPanel botones = new JPanel();
		botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
		botones.setOpaque(false);
		botones.setAlignmentX(LEFT_ALIGNMENT);

		btnGenerarReporte = crearBoton("Generar", colorVerde, colorBlanco);
		btnExportarPDF = crearBoton("Exportar PDF", colorVerdeOscuro, colorBlanco);
		botones.add(btnGenerarReporte);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnExportarPDF);
		formReporte.add(botones);
		formReporte.add(Box.createVerticalStrut(8));
		lblReporteMensaje = new JLabel(" ");
		lblReporteMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblReporteMensaje.setForeground(colorRojo);
		lblReporteMensaje.setAlignmentX(LEFT_ALIGNMENT);
		formReporte.add(lblReporteMensaje);
		JPanel panelResultado = new JPanel();
		panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.Y_AXIS));
		panelResultado.setBackground(colorPanel);
		panelResultado.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Resultado del reporte", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		areaReporte = new JTextArea();
		areaReporte.setEditable(false);
		areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
		areaReporte.setBackground(colorCampoFondo);
		areaReporte.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollReporte = new JScrollPane(areaReporte);
		scrollReporte.setAlignmentX(LEFT_ALIGNMENT);
		panelResultado.add(scrollReporte);
		panel.add(formReporte);
		panel.add(Box.createVerticalStrut(15));
		panel.add(panelResultado);
		return panel;
	}

	/**
	 * Crea el panel correspondiente a las notificaciones del sistema.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de notificaciones
	 */
	public JPanel crearPanelNotificaciones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Notificaciones del sistema", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		areaNotificacion = new JTextArea();
		areaNotificacion.setEditable(false);
		areaNotificacion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		areaNotificacion.setBackground(colorCampoFondo);
		areaNotificacion.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollNotif = new JScrollPane(areaNotificacion);
		scrollNotif.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scrollNotif);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelRef = new JPanel();
		panelRef.setLayout(new BoxLayout(panelRef, BoxLayout.X_AXIS));
		panelRef.setOpaque(false);
		panelRef.setAlignmentX(LEFT_ALIGNMENT);
		btnRefrescarNotif = crearBoton("Refrescar notificaciones", colorVerde, colorBlanco);
		panelRef.add(btnRefrescarNotif);
		panel.add(panelRef);
		return panel;
	}

	/**
	 * Crea y configura un boton personalizado.
	 * 
	 * @param texto Texto del boton
	 * @param fondo Color de fondo
	 * @param letra Color del texto
	 * 
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
	 * Establece un mensaje relacionado con la generacion de reportes.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeReporte(String msg, boolean esError) {
		if (esError) {
			lblReporteMensaje.setForeground(colorRojo);
		} else {
			lblReporteMensaje.setForeground(colorVerdeOscuro);
		}
		lblReporteMensaje.setText(msg);
	}

	/**
	 * Retorna el combo de tipos de reporte.
	 * 
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboTipoReporte() {
		return comboTipoReporte;
	}

	/**
	 * Retorna el boton de generar reporte.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	/**
	 * Retorna el boton de exportar PDF.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnExportarPDF() {
		return btnExportarPDF;
	}

	/**
	 * Retorna el area de texto de reportes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaReporte() {
		return areaReporte;
	}

	/**
	 * Retorna el area de texto de notificaciones.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaNotificaciones() {
		return areaNotificacion;
	}

	/**
	 * Retorna el boton de refrescar notificaciones.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarNotif() {
		return btnRefrescarNotif;
	}
}