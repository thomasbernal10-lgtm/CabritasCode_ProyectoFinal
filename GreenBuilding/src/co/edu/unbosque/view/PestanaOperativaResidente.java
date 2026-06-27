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
 * Panel grafico utilizado por los residentes para gestionar operaciones
 * relacionadas con incidentes y solicitudes de mantenimiento.
 * 
 * Permite reportar incidentes, crear solicitudes de mantenimiento y visualizar
 * historiales asociados al apartamento del residente.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaOperativaResidente extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 9182736450918273645L;

	/**
	 * Campo de texto para la descripcion del incidente.
	 */
	private JTextField txtIncDescripcion;

	/**
	 * Campo de texto para la ubicacion del incidente.
	 */
	private JTextField txtIncUbicacion;

	/**
	 * ComboBox para seleccionar el tipo de incidente.
	 */
	private JComboBox<String> comboIncTipo;

	/**
	 * ComboBox para seleccionar la gravedad del incidente.
	 */
	private JComboBox<String> comboIncGravedad;

	/**
	 * Boton para reportar incidentes.
	 */
	private JButton btnIncReportar;

	/**
	 * Boton para limpiar el formulario de incidentes.
	 */
	private JButton btnIncLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con incidentes.
	 */
	private JLabel lblIncMensaje;

	/**
	 * Area de texto para visualizar incidentes reportados.
	 */
	private JTextArea areaIncLista;

	/**
	 * Boton para refrescar la lista de incidentes.
	 */
	private JButton btnIncRefrescar;

	/**
	 * Area de texto para visualizar mantenimientos.
	 */
	private JTextArea areaManLista;

	/**
	 * Boton para refrescar la lista de mantenimientos.
	 */
	private JButton btnManRefrescar;

	/**
	 * Campo de texto para la descripcion del mantenimiento.
	 */
	private JTextField txtManDescripcion;

	/**
	 * Campo de texto para la ubicacion del mantenimiento.
	 */
	private JTextField txtManUbicacion;

	/**
	 * ComboBox para seleccionar el tipo de mantenimiento.
	 */
	private JComboBox<String> comboManTipo;

	/**
	 * ComboBox para seleccionar la prioridad del mantenimiento.
	 */
	private JComboBox<String> comboManPrioridad;

	/**
	 * Boton para crear solicitudes de mantenimiento.
	 */
	private JButton btnManCrear;

	/**
	 * Boton para limpiar el formulario de mantenimiento.
	 */
	private JButton btnManLimpiar;

	/**
	 * Etiqueta para mostrar mensajes relacionados con mantenimiento.
	 */
	private JLabel lblManMensaje;

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
	 * Construye la pestana operativa del residente.
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
	public PestanaOperativaResidente(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * <b>post</b> El panel principal queda configurado.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Reportar Incidente", crearSubPanelIncidentes());
		tabs.addTab("Solicitudes Mantenimiento", crearSubPanelMantenimiento());
		add(tabs);
	}

	/**
	 * Crea el subpanel utilizado para reportar incidentes.
	 * 
	 * @return JPanel configurado para incidentes
	 */
	public JPanel crearSubPanelIncidentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		izq.setBackground(colorPanel);
		izq.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

		JLabel titulo = new JLabel("Reportar Nuevo Incidente");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
		titulo.setForeground(colorVerdeOscuro);
		titulo.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(titulo);
		izq.add(Box.createVerticalStrut(15));

		izq.add(crearEtiqueta("Descripcion del incidente:"));
		txtIncDescripcion = crearCampo("Describa el incidente");
		izq.add(txtIncDescripcion);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Ubicacion:"));
		txtIncUbicacion = crearCampo("Ej: Pasillo piso 3, estacionamiento...");
		izq.add(txtIncUbicacion);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Tipo:"));
		comboIncTipo = new JComboBox<>(new String[] { "RUIDO", "Daños", "SEGURIDAD", "CONVIVENCIA", "OTRO" });
		comboIncTipo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboIncTipo.setMaximumSize(new Dimension(350, 28));
		comboIncTipo.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(comboIncTipo);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Gravedad:"));
		comboIncGravedad = new JComboBox<>(new String[] { "BAJA", "MEDIA", "ALTA", "CRITICA" });
		comboIncGravedad.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboIncGravedad.setMaximumSize(new Dimension(350, 28));
		comboIncGravedad.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(comboIncGravedad);
		izq.add(Box.createVerticalStrut(12));

		JPanel panelBtn = panelBotones();
		btnIncReportar = crearBoton("Reportar", colorRojo, colorBlanco);
		btnIncLimpiar = crearBoton("Limpiar", colorVerdeOscuro, colorBlanco);
		panelBtn.add(btnIncReportar);
		panelBtn.add(Box.createHorizontalStrut(8));
		panelBtn.add(btnIncLimpiar);
		izq.add(panelBtn);
		izq.add(Box.createVerticalStrut(8));

		lblIncMensaje = new JLabel(" ");
		lblIncMensaje.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblIncMensaje.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(lblIncMensaje);

		JPanel der = new JPanel();
		der.setLayout(new BoxLayout(der, BoxLayout.Y_AXIS));
		der.setBackground(colorPanel);
		der.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		JPanel panelLista = crearPanelLista("Incidentes Reportados por mi Apartamento");
		areaIncLista = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		der.add(panelLista);
		der.add(Box.createVerticalStrut(8));

		JPanel panelRefInc = panelBotones();
		btnIncRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRefInc.add(btnIncRefrescar);
		der.add(panelRefInc);

		panel.add(izq);
		panel.add(der);
		return panel;
	}

	/**
	 * Crea el subpanel utilizado para solicitudes de mantenimiento.
	 * 
	 * @return JPanel configurado para mantenimiento
	 */
	public JPanel crearSubPanelMantenimiento() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		izq.setBackground(colorPanel);
		izq.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

		JLabel titulo = new JLabel("Nueva Solicitud de Mantenimiento");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
		titulo.setForeground(colorVerdeOscuro);
		titulo.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(titulo);
		izq.add(Box.createVerticalStrut(15));

		izq.add(crearEtiqueta("Descripcion del problema:"));
		txtManDescripcion = crearCampo("Describa el problema de mantenimiento");
		izq.add(txtManDescripcion);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Ubicacion:"));
		txtManUbicacion = crearCampo("Ej: Baño, cocina, cuarto...");
		izq.add(txtManUbicacion);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Tipo:"));
		comboManTipo = new JComboBox<>(new String[] { "PLOMERIA", "ELECTRICO", "PINTURA", "CARPINTERIA", "OTRO" });
		comboManTipo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboManTipo.setMaximumSize(new Dimension(350, 28));
		comboManTipo.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(comboManTipo);
		izq.add(Box.createVerticalStrut(8));

		izq.add(crearEtiqueta("Prioridad:"));
		comboManPrioridad = new JComboBox<>(new String[] { "BAJA", "MEDIA", "ALTA", "URGENTE" });
		comboManPrioridad.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboManPrioridad.setMaximumSize(new Dimension(350, 28));
		comboManPrioridad.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(comboManPrioridad);
		izq.add(Box.createVerticalStrut(12));

		JPanel panelBtn = panelBotones();
		btnManCrear = crearBoton("Crear Solicitud", colorVerde, colorBlanco);
		btnManLimpiar = crearBoton("Limpiar", colorVerdeOscuro, colorBlanco);
		panelBtn.add(btnManCrear);
		panelBtn.add(Box.createHorizontalStrut(8));
		panelBtn.add(btnManLimpiar);
		izq.add(panelBtn);
		izq.add(Box.createVerticalStrut(8));

		lblManMensaje = new JLabel(" ");
		lblManMensaje.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblManMensaje.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(lblManMensaje);

		JPanel der = new JPanel();
		der.setLayout(new BoxLayout(der, BoxLayout.Y_AXIS));
		der.setBackground(colorPanel);
		der.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		JPanel panelLista = crearPanelLista("Solicitudes de Mantenimiento de mi Apartamento");
		areaManLista = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		der.add(panelLista);
		der.add(Box.createVerticalStrut(8));

		JPanel panelRefMan = panelBotones();
		btnManRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRefMan.add(btnManRefrescar);
		der.add(panelRefMan);

		panel.add(izq);
		panel.add(der);
		return panel;
	}

	/**
	 * Crea una etiqueta personalizada.
	 * 
	 * @param texto Texto de la etiqueta
	 * 
	 * @return JLabel configurado
	 */
	public JLabel crearEtiqueta(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);

		return lbl;
	}

	/**
	 * Crea un campo de texto personalizado.
	 * 
	 * @param placeholder Texto de ayuda del campo
	 * 
	 * @return JTextField configurado
	 */
	public JTextField crearCampo(String placeholder) {
		JTextField tf = new JTextField();
		tf.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tf.setBackground(colorCampoFondo);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde),
				BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		tf.setToolTipText(placeholder);
		tf.setMaximumSize(new Dimension(350, 30));
		tf.setAlignmentX(LEFT_ALIGNMENT);
		return tf;
	}

	/**
	 * Crea un panel que contiene listas y areas de texto.
	 * 
	 * @param titulo Titulo del panel
	 * 
	 * @return JPanel configurado
	 */
	public JPanel crearPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		JTextArea area = new JTextArea(12, 45);
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
	 * Crea un panel horizontal para botones.
	 * 
	 * @return JPanel configurado
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBackground(colorPanel);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea un boton personalizado.
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
	 * Establece un mensaje relacionado con incidentes.
	 * 
	 * @param msg   Mensaje a mostrar
	 * @param error true si es un mensaje de error
	 */
	public void setMensajeIncidente(String msg, boolean error) {
		lblIncMensaje.setText(msg);
		if (error) {
			lblIncMensaje.setForeground(colorRojo);
		} else {
			lblIncMensaje.setForeground(colorVerde);
		}
	}

	/**
	 * Establece un mensaje relacionado con mantenimiento.
	 * 
	 * @param msg   Mensaje a mostrar
	 * @param error true si es un mensaje de error
	 */
	public void setMensajeMantenimiento(String msg, boolean error) {
		lblManMensaje.setText(msg);
		if (error) {
			lblManMensaje.setForeground(colorRojo);
		} else {
			lblManMensaje.setForeground(colorVerde);
		}
	}

	/**
	 * Limpia el formulario de incidentes.
	 */
	public void limpiarFormularioIncidente() {
		txtIncDescripcion.setText("");
		txtIncUbicacion.setText("");
		comboIncTipo.setSelectedIndex(0);
		comboIncGravedad.setSelectedIndex(0);
		lblIncMensaje.setText(" ");
	}

	/**
	 * Limpia el formulario de mantenimiento.
	 */
	public void limpiarFormularioMantenimiento() {
		txtManDescripcion.setText("");
		txtManUbicacion.setText("");
		comboManTipo.setSelectedIndex(0);
		comboManPrioridad.setSelectedIndex(0);
		lblManMensaje.setText(" ");
	}

	/**
	 * Retorna el campo de descripcion del incidente.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncDescripcion() {
		return txtIncDescripcion;
	}

	/**
	 * Retorna el campo de ubicacion del incidente.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIncUbicacion() {
		return txtIncUbicacion;
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
	 * Retorna el boton para reportar incidentes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncReportar() {
		return btnIncReportar;
	}

	/**
	 * Retorna el boton para limpiar incidentes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncLimpiar() {
		return btnIncLimpiar;
	}

	/**
	 * Retorna el area de lista de incidentes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaIncLista() {
		return areaIncLista;
	}

	/**
	 * Retorna el boton para refrescar incidentes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnIncRefrescar() {
		return btnIncRefrescar;
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
	 * Retorna el boton de crear mantenimiento.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnManCrear() {
		return btnManCrear;
	}

	/**
	 * Retorna el boton de limpiar mantenimiento.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnManLimpiar() {
		return btnManLimpiar;
	}

	/**
	 * Retorna el area de lista de mantenimientos.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaManLista() {
		return areaManLista;
	}

	/**
	 * Retorna el boton de refrescar mantenimientos.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnManRefrescar() {
		return btnManRefrescar;
	}
}