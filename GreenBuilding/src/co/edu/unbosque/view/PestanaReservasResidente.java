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
 * Panel grafico encargado de gestionar reservas de zonas comunes realizadas por
 * los residentes del conjunto residencial.
 * 
 * Permite visualizar zonas disponibles, crear nuevas reservas y cancelar
 * reservas previamente registradas.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaReservasResidente extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 7239401823740192837L;

	/**
	 * Area de texto utilizada para mostrar las zonas comunes disponibles.
	 */
	private JTextArea areaZonasLista;

	/**
	 * Boton utilizado para refrescar la lista de zonas comunes.
	 */
	private JButton btnZonasRefrescar;

	/**
	 * ComboBox utilizado para seleccionar la zona comun.
	 */
	private JComboBox<String> comboZona;

	/**
	 * Campo de texto utilizado para ingresar la fecha de la reserva.
	 */
	private JTextField txtFecha;

	/**
	 * Campo de texto utilizado para ingresar la hora de inicio.
	 */
	private JTextField txtHoraInicio;

	/**
	 * Campo de texto utilizado para ingresar la hora de finalizacion.
	 */
	private JTextField txtHoraFin;

	/**
	 * Boton utilizado para crear una nueva reserva.
	 */
	private JButton btnCrearReserva;

	/**
	 * Boton utilizado para limpiar el formulario de reservas.
	 */
	private JButton btnLimpiarReserva;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con reservas.
	 */
	private JLabel lblMensajeReserva;

	/**
	 * Area de texto utilizada para mostrar las reservas del residente.
	 */
	private JTextArea areaMisReserva;

	/**
	 * Boton utilizado para refrescar la lista de reservas.
	 */
	private JButton btnRefrescarMisReserva;

	/**
	 * Campo de texto utilizado para ingresar el ID de la reserva a cancelar.
	 */
	private JTextField txtIdCancelar;

	/**
	 * Campo de texto utilizado para ingresar el motivo de cancelacion.
	 */
	private JTextField txtMotivoCancelar;

	/**
	 * Boton utilizado para cancelar reservas.
	 */
	private JButton btnCancelarReserva;

	/**
	 * Etiqueta utilizada para mostrar mensajes de cancelacion.
	 */
	private JLabel lblMensajeCancelar;

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
	 * Construye la pestana de reservas para residentes.
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
	public PestanaReservasResidente(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		tabs.addTab("Zonas Disponibles", crearSubPanelZonas());
		tabs.addTab("Hacer Reserva", crearSubPanelHacerReserva());
		tabs.addTab("Mis Reservas", crearSubPanelMisReservas());
		add(tabs);
	}

	/**
	 * Crea el subpanel correspondiente a las zonas disponibles.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente a zonas comunes
	 */
	public JPanel crearSubPanelZonas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Zonas comunes disponibles en el conjunto.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Zonas Comunes");
		areaZonasLista = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnZonasRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnZonasRefrescar);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a la creacion de reservas.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al formulario de reservas
	 */
	public JPanel crearSubPanelHacerReserva() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel izq = new JPanel();
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		izq.setBackground(colorPanel);
		izq.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
		JLabel titulo = new JLabel("Nueva Reserva");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
		titulo.setForeground(colorVerdeOscuro);
		titulo.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(titulo);
		izq.add(Box.createVerticalStrut(15));
		izq.add(crearEtiqueta("Zona Comun:"));
		comboZona = new JComboBox<>();
		comboZona.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboZona.setMaximumSize(new Dimension(350, 28));
		comboZona.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(comboZona);
		izq.add(Box.createVerticalStrut(8));
		izq.add(crearEtiqueta("Fecha (YYYY-MM-DD):"));
		txtFecha = crearCampo("Ej: 2025-07-15");
		izq.add(txtFecha);
		izq.add(Box.createVerticalStrut(8));
		izq.add(crearEtiqueta("Hora Inicio (HH:MM):"));
		txtHoraInicio = crearCampo("Ej: 09:00");
		izq.add(txtHoraInicio);
		izq.add(Box.createVerticalStrut(8));
		izq.add(crearEtiqueta("Hora Fin (HH:MM):"));
		txtHoraFin = crearCampo("Ej: 11:00");
		izq.add(txtHoraFin);
		izq.add(Box.createVerticalStrut(12));
		JPanel panelBtn = panelBotones();
		btnCrearReserva = crearBoton("Reservar", colorVerde, colorBlanco);
		btnLimpiarReserva = crearBoton("Limpiar", colorVerdeOscuro, colorBlanco);
		panelBtn.add(btnCrearReserva);
		panelBtn.add(Box.createHorizontalStrut(8));
		panelBtn.add(btnLimpiarReserva);
		izq.add(panelBtn);
		izq.add(Box.createVerticalStrut(8));
		lblMensajeReserva = new JLabel(" ");
		lblMensajeReserva.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMensajeReserva.setAlignmentX(LEFT_ALIGNMENT);
		izq.add(lblMensajeReserva);
		panel.add(izq);
		panel.add(Box.createHorizontalGlue());
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a las reservas registradas.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente a mis reservas
	 */
	public JPanel crearSubPanelMisReservas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Historial de sus reservas en el conjunto.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Mis Reservas");
		areaMisReserva = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelCancelar = new JPanel();
		panelCancelar.setLayout(new BoxLayout(panelCancelar, BoxLayout.X_AXIS));
		panelCancelar.setBackground(colorPanel);
		panelCancelar.setAlignmentX(LEFT_ALIGNMENT);
		panelCancelar.add(crearEtiqueta("ID Reserva:"));
		panelCancelar.add(Box.createHorizontalStrut(5));
		txtIdCancelar = new JTextField();
		txtIdCancelar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIdCancelar.setBackground(colorCampoFondo);
		txtIdCancelar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
		txtIdCancelar.setMaximumSize(new Dimension(150, 28));
		panelCancelar.add(txtIdCancelar);
		panelCancelar.add(Box.createHorizontalStrut(10));
		panelCancelar.add(crearEtiqueta("Motivo:"));
		panelCancelar.add(Box.createHorizontalStrut(5));
		txtMotivoCancelar = new JTextField();
		txtMotivoCancelar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMotivoCancelar.setBackground(colorCampoFondo);
		txtMotivoCancelar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
		txtMotivoCancelar.setMaximumSize(new Dimension(200, 28));
		panelCancelar.add(txtMotivoCancelar);
		panelCancelar.add(Box.createHorizontalStrut(10));
		btnCancelarReserva = crearBoton("Cancelar Reserva", colorRojo, colorBlanco);
		panelCancelar.add(btnCancelarReserva);
		panelCancelar.add(Box.createHorizontalStrut(10));
		btnRefrescarMisReserva = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelCancelar.add(btnRefrescarMisReserva);
		panel.add(panelCancelar);
		panel.add(Box.createVerticalStrut(6));
		lblMensajeCancelar = new JLabel(" ");
		lblMensajeCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMensajeCancelar.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lblMensajeCancelar);
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
	 * Crea y configura un campo de texto personalizado.
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
	 * Crea un panel generico para visualizar listas.
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
		JTextArea area = new JTextArea(8, 50);
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
	 * Crea un panel horizontal para organizar botones.
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
	 * Establece un mensaje relacionado con la reserva.
	 * 
	 * @param msg   Mensaje a mostrar
	 * @param error true si representa un error
	 */
	public void setMensajeReserva(String msg, boolean error) {
		lblMensajeReserva.setText(msg);
		if (error) {
			lblMensajeReserva.setForeground(colorRojo);
		} else {
			lblMensajeReserva.setForeground(colorVerde);
		}
	}

	/**
	 * Establece un mensaje relacionado con la cancelacion.
	 * 
	 * @param msg   Mensaje a mostrar
	 * @param error true si representa un error
	 */
	public void setMensajeCancelar(String msg, boolean error) {
		lblMensajeCancelar.setText(msg);
		if (error) {
			lblMensajeCancelar.setForeground(colorRojo);
		} else {
			lblMensajeCancelar.setForeground(colorVerde);
		}
	}

	/**
	 * Limpia el formulario de reservas.
	 */
	public void limpiarFormularioReserva() {
		txtFecha.setText("");
		txtHoraInicio.setText("");
		txtHoraFin.setText("");
		lblMensajeReserva.setText(" ");
	}

	/**
	 * Retorna el area de zonas disponibles.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaZonasLista() {
		return areaZonasLista;
	}

	/**
	 * Retorna el boton de refrescar zonas.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnZonasRefrescar() {
		return btnZonasRefrescar;
	}

	/**
	 * Retorna el combo de zonas comunes.
	 * 
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboZona() {
		return comboZona;
	}

	/**
	 * Retorna el campo de fecha.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtFecha() {
		return txtFecha;
	}

	/**
	 * Retorna el campo de hora de inicio.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtHoraInicio() {
		return txtHoraInicio;
	}

	/**
	 * Retorna el campo de hora de finalizacion.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtHoraFin() {
		return txtHoraFin;
	}

	/**
	 * Retorna el boton de crear reserva.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnCrearReserva() {
		return btnCrearReserva;
	}

	/**
	 * Retorna el boton de limpiar formulario.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnLimpiarReserva() {
		return btnLimpiarReserva;
	}

	/**
	 * Retorna el area de reservas registradas.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaMisReservas() {
		return areaMisReserva;
	}

	/**
	 * Retorna el boton de refrescar reservas.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarMisReservas() {
		return btnRefrescarMisReserva;
	}

	/**
	 * Retorna el campo del ID de reserva.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIdCancelar() {
		return txtIdCancelar;
	}

	/**
	 * Retorna el campo del motivo de cancelacion.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtMotivoCancelar() {
		return txtMotivoCancelar;
	}

	/**
	 * Retorna el boton de cancelar reserva.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnCancelarReserva() {
		return btnCancelarReserva;
	}
}