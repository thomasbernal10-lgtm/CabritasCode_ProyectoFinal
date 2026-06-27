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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel grafico encargado de administrar zonas comunes, parqueaderos y reservas
 * del conjunto residencial.
 * 
 * Permite asignar parqueaderos, visualizar registros existentes y gestionar
 * cancelaciones de reservas realizadas por residentes.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaZonasParqueadero extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -984146283289402200L;

	/**
	 * Campo de texto para ingresar el codigo del parqueadero.
	 */
	private JTextField txtParqCodigo;

	/**
	 * Lista desplegable de tipos de parqueadero.
	 */
	private JComboBox<String> comboParqTipo;

	/**
	 * Campo de texto para ingresar el apartamento asignado.
	 */
	private JTextField txtParqApto;

	/**
	 * Boton utilizado para asignar un parqueadero.
	 */
	private JButton btnParqCrear;

	/**
	 * Boton utilizado para limpiar el formulario de parqueaderos.
	 */
	private JButton btnParqLimpiar;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con parqueaderos.
	 */
	private JLabel lblParqMensaje;

	/**
	 * Area de texto utilizada para mostrar la lista de parqueaderos.
	 */
	private JTextArea areaParqLista;

	/**
	 * Boton utilizado para refrescar la lista de parqueaderos.
	 */
	private JButton btnParqRefrescar;

	/**
	 * Campo de texto para ingresar el indice de la reserva a cancelar.
	 */
	private JTextField txtResIdCancelar;

	/**
	 * Campo de texto para ingresar el motivo de cancelacion.
	 */
	private JTextField txtResMotivoCancelar;

	/**
	 * Boton utilizado para cancelar reservas.
	 */
	private JButton btnResCancelar;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con reservas.
	 */
	private JLabel lblResMensaje;

	/**
	 * Area de texto utilizada para mostrar la lista de reservas.
	 */
	private JTextArea areaResLista;

	/**
	 * Boton utilizado para refrescar la lista de reservas.
	 */
	private JButton btnResRefrescar;

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
	 * Construye la pestana de zonas y parqueaderos.
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
	public PestanaZonasParqueadero(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		tabs.addTab("Parqueaderos", crearSubPanelParqueaderos());
		tabs.addTab("Gestion de Reservas", crearSubPanelReservas());
		add(tabs);
	}

	/**
	 * Crea el subpanel correspondiente a la gestion de parqueaderos.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de parqueaderos
	 */
	public JPanel crearSubPanelParqueaderos() {
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Asignar Parqueadero", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtParqCodigo = crearCampo();
		txtParqApto = crearCampo();
		comboParqTipo = new JComboBox<>(new String[] { "CARRO", "MOTO", "BICICLETA", "VISITANTE" });
		estilizarCombo(comboParqTipo);
		agregarCombo(formulario, "Tipo:", comboParqTipo);
		agregarCampo(formulario, "Numero (ej: P01):", txtParqCodigo);
		agregarCampo(formulario, "N° Apartamento asignado (ej: 402):", txtParqApto);
		JPanel botones = panelBotones();
		btnParqCrear = crearBoton("Asignar", colorVerde, colorBlanco);
		btnParqLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botones.add(btnParqCrear);
		botones.add(Box.createHorizontalStrut(10));
		botones.add(btnParqLimpiar);
		formulario.add(botones);
		formulario.add(Box.createVerticalStrut(8));
		lblParqMensaje = crearLblMensaje();
		formulario.add(lblParqMensaje);
		formulario.add(Box.createVerticalGlue());
		JPanel lista = crearPanelLista("Parqueaderos registrados");
		areaParqLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnParqRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnParqRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(formulario);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a la gestion de reservas.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de reservas
	 */
	public JPanel crearSubPanelReservas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel izquierda = new JPanel();
		izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));
		izquierda.setBackground(colorPanel);
		izquierda.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		izquierda.setPreferredSize(new Dimension(320, 0));
		JPanel panelCancelar = new JPanel();
		panelCancelar.setLayout(new BoxLayout(panelCancelar, BoxLayout.Y_AXIS));
		panelCancelar.setBackground(colorPanel);
		panelCancelar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Cancelar Reserva", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtResIdCancelar = crearCampo();
		txtResMotivoCancelar = crearCampo();
		agregarCampo(panelCancelar, "Index de la reserva (ver lista):", txtResIdCancelar);
		agregarCampo(panelCancelar, "Motivo de cancelacion:", txtResMotivoCancelar);
		JLabel lblNota = new JLabel("* Las reservas las crean los residentes desde su cuenta");
		lblNota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNota.setForeground(colorTextoSuave);
		lblNota.setAlignmentX(LEFT_ALIGNMENT);
		panelCancelar.add(lblNota);
		panelCancelar.add(Box.createVerticalStrut(12));
		JPanel botCancelar = panelBotones();
		btnResCancelar = crearBoton("Cancelar Reserva", colorRojo, colorBlanco);
		botCancelar.add(btnResCancelar);
		panelCancelar.add(botCancelar);
		panelCancelar.add(Box.createVerticalStrut(8));
		lblResMensaje = crearLblMensaje();
		panelCancelar.add(lblResMensaje);
		izquierda.add(panelCancelar);
		izquierda.add(Box.createVerticalGlue());
		JPanel lista = crearPanelLista("Reservas registradas");
		areaResLista = (JTextArea) ((JScrollPane) lista.getComponent(0)).getViewport().getView();
		btnResRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		JPanel panelRef = panelBotones();
		panelRef.add(btnResRefrescar);
		lista.add(Box.createVerticalStrut(10));
		lista.add(panelRef);
		panel.add(izquierda);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(lista);
		return panel;
	}

	/**
	 * Crea un panel generico para visualizar listas.
	 * 
	 * <b>pre</b> El titulo no debe ser null. <br>
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @param titulo Titulo del panel
	 * 
	 * @return JPanel correspondiente al panel de lista
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
	 * Crea un panel horizontal para organizar botones.
	 * 
	 * @return JPanel configurado para botones
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea una etiqueta utilizada para mensajes informativos.
	 * 
	 * @return JLabel configurado
	 */
	public JLabel crearLblMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	/**
	 * Agrega un campo de texto con su etiqueta correspondiente.
	 * 
	 * @param panel    Panel donde se agregara el campo
	 * @param etiqueta Texto de la etiqueta
	 * @param campo    Campo de texto
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
	 * Agrega un JComboBox con su etiqueta correspondiente.
	 * 
	 * @param panel    Panel donde se agregara el combo
	 * @param etiqueta Texto de la etiqueta
	 * @param combo    ComboBox a agregar
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
	 * Crea y configura un campo de texto personalizado.
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
	 * Establece un mensaje relacionado con parqueaderos.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeParq(String msg, boolean esError) {
		if (esError) {
			lblParqMensaje.setForeground(colorRojo);
		} else {
			lblParqMensaje.setForeground(colorVerdeOscuro);
		}

		lblParqMensaje.setText(msg);
	}

	/**
	 * Limpia el formulario de parqueaderos.
	 */
	public void limpiarParq() {
		txtParqCodigo.setText("");
		comboParqTipo.setSelectedIndex(0);
		txtParqApto.setText("");
		lblParqMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje relacionado con reservas.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
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
	 * Limpia el formulario de reservas.
	 */
	public void limpiarRes() {
		txtResIdCancelar.setText("");
		txtResMotivoCancelar.setText("");
		lblResMensaje.setText(" ");
	}

	/**
	 * Aplica estilo visual personalizado a un JComboBox.
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
	 * Retorna el campo de codigo del parqueadero.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtParqCodigo() {
		return txtParqCodigo;
	}

	/**
	 * Retorna el combo de tipos de parqueadero.
	 * 
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboParqTipo() {
		return comboParqTipo;
	}

	/**
	 * Retorna el campo del apartamento asignado.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtParqApto() {
		return txtParqApto;
	}

	/**
	 * Retorna el boton de asignar parqueadero.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnParqCrear() {
		return btnParqCrear;
	}

	/**
	 * Retorna el boton de limpiar parqueaderos.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnParqLimpiar() {
		return btnParqLimpiar;
	}

	/**
	 * Retorna el area de lista de parqueaderos.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaParqLista() {
		return areaParqLista;
	}

	/**
	 * Retorna el boton de refrescar parqueaderos.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnParqRefrescar() {
		return btnParqRefrescar;
	}

	/**
	 * Retorna el campo del indice de reserva.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtResIdCancelar() {
		return txtResIdCancelar;
	}

	/**
	 * Retorna el campo del motivo de cancelacion.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtResMotivoCancelar() {
		return txtResMotivoCancelar;
	}

	/**
	 * Retorna el boton de cancelar reserva.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnResCancelar() {
		return btnResCancelar;
	}

	/**
	 * Retorna el area de lista de reservas.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaResLista() {
		return areaResLista;
	}

	/**
	 * Retorna el boton de refrescar reservas.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnResRefrescar() {
		return btnResRefrescar;
	}
}