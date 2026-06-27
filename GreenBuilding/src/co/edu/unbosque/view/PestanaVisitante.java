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

/**
 * Panel grafico encargado de gestionar el registro de visitantes dentro del
 * conjunto residencial.
 * 
 * Permite registrar entradas y salidas de visitantes, visualizar visitas
 * activas y consultar el historial completo de visitantes registrados.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaVisitante extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -146035807655492119L;

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
	 * Campo de texto para ingresar la cedula del visitante.
	 */
	private JTextField txtCedula;

	/**
	 * Campo de texto para ingresar el nombre del visitante.
	 */
	private JTextField txtNombre;

	/**
	 * Campo de texto para ingresar el telefono del visitante.
	 */
	private JTextField txtTelefono;

	/**
	 * Campo de texto para ingresar el apartamento destino.
	 */
	private JTextField txtApartamentoDestino;

	/**
	 * Campo de texto para ingresar el motivo de la visita.
	 */
	private JTextField txtMotivo;

	/**
	 * Boton utilizado para registrar entradas de visitantes.
	 */
	private JButton btnRegistrarEntrada;

	/**
	 * Boton utilizado para registrar salidas de visitantes.
	 */
	private JButton btnRegistrarSalida;

	/**
	 * Boton utilizado para limpiar el formulario.
	 */
	private JButton btnLimpiar;

	/**
	 * Boton utilizado para refrescar las listas.
	 */
	private JButton btnRefrescar;

	/**
	 * Etiqueta utilizada para mostrar mensajes informativos.
	 */
	private JLabel lblMensaje;

	/**
	 * Area de texto utilizada para mostrar visitas activas.
	 */
	private JTextArea areasVisitasActiva;

	/**
	 * Area de texto utilizada para mostrar todos los visitantes.
	 */
	private JTextArea areaTodosVisitante;

	/**
	 * Lista desplegable de tipos de visitante.
	 */
	private JComboBox<String> comboTipoVisitante;

	/**
	 * Construye la pestana de visitantes.
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
	public PestanaVisitante(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		formulario.setPreferredSize(new Dimension(320, 0));
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Registrar Entrada / Salida", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtCedula = crearCampo();
		txtNombre = crearCampo();
		txtTelefono = crearCampo();
		txtApartamentoDestino = crearCampo();
		txtMotivo = crearCampo();
		agregarCampo(formulario, "Cedula:", txtCedula);
		agregarCampo(formulario, "Nombre:", txtNombre);
		agregarCampo(formulario, "Telefono:", txtTelefono);
		agregarCampo(formulario, "Apartamento destino:", txtApartamentoDestino);
		agregarCampo(formulario, "Motivo:", txtMotivo);
		JLabel lblTipo = new JLabel("Tipo de visitante:");
		lblTipo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTipo.setForeground(colorTextoSuave);
		lblTipo.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(lblTipo);
		formulario.add(Box.createVerticalStrut(4));
		comboTipoVisitante = new JComboBox<>(
				new String[] { "OCASIONAL", "FRECUENTE", "DOMICILIARIO", "PROVEEDOR", "CONTRATISTA" });
		comboTipoVisitante.setFont(new Font("SansSerif", Font.PLAIN, 13));
		comboTipoVisitante.setBackground(colorCampoFondo);
		comboTipoVisitante.setForeground(colorVerdeOscuro);
		comboTipoVisitante.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		comboTipoVisitante.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(comboTipoVisitante);
		formulario.add(Box.createVerticalStrut(10));
		JLabel lblNota = new JLabel("* Para registrar salida solo ingrese la cedula");
		lblNota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNota.setForeground(colorTextoSuave);
		lblNota.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(lblNota);
		formulario.add(Box.createVerticalStrut(10));
		JPanel panelBotones = crearPanelBotones();
		btnRegistrarEntrada = crearBoton("Registrar Entrada", colorVerde, colorBlanco);
		btnRegistrarSalida = crearBoton("Registrar Salida", colorVerdeOscuro, colorBlanco);
		btnLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		panelBotones.add(btnRegistrarEntrada);
		panelBotones.add(Box.createHorizontalStrut(8));
		panelBotones.add(btnRegistrarSalida);
		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(6));
		JPanel panelLimpiar = crearPanelBotones();
		panelLimpiar.add(btnLimpiar);
		formulario.add(panelLimpiar);
		formulario.add(Box.createVerticalStrut(8));
		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensaje.setForeground(colorRojo);
		lblMensaje.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(lblMensaje);
		formulario.add(Box.createVerticalGlue());
		JPanel panelDerecha = new JPanel();
		panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
		panelDerecha.setBackground(colorPanel);
		JPanel panelActivas = new JPanel();
		panelActivas.setLayout(new BoxLayout(panelActivas, BoxLayout.Y_AXIS));
		panelActivas.setBackground(colorPanel);
		panelActivas.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Visitas activas (sin salida)", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		areasVisitasActiva = new JTextArea();
		areasVisitasActiva.setEditable(false);
		areasVisitasActiva.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areasVisitasActiva.setBackground(colorCampoFondo);
		areasVisitasActiva.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollActivas = new JScrollPane(areasVisitasActiva);
		scrollActivas.setAlignmentX(LEFT_ALIGNMENT);
		panelActivas.add(scrollActivas);
		JPanel panelTodos = new JPanel();
		panelTodos.setLayout(new BoxLayout(panelTodos, BoxLayout.Y_AXIS));
		panelTodos.setBackground(colorPanel);
		panelTodos.setBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Todos los visitantes registrados", 0, 0, new Font("SansSerif", Font.BOLD, 12),
						colorVerdeOscuro), BorderFactory.createEmptyBorder(8, 10, 8, 10)));

		areaTodosVisitante = new JTextArea();
		areaTodosVisitante.setEditable(false);
		areaTodosVisitante.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaTodosVisitante.setBackground(colorCampoFondo);
		areaTodosVisitante.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollTodos = new JScrollPane(areaTodosVisitante);
		scrollTodos.setAlignmentX(LEFT_ALIGNMENT);
		panelTodos.add(scrollTodos);
		JPanel panelBotonRefrescar = crearPanelBotones();
		btnRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBotonRefrescar.add(btnRefrescar);
		panelDerecha.add(panelActivas);
		panelDerecha.add(Box.createVerticalStrut(10));
		panelDerecha.add(panelTodos);
		panelDerecha.add(Box.createVerticalStrut(8));
		panelDerecha.add(panelBotonRefrescar);
		add(formulario);
		add(Box.createHorizontalStrut(20));
		add(panelDerecha);
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
	 * Crea un panel horizontal para organizar botones.
	 * 
	 * @return JPanel configurado para botones
	 */
	public JPanel crearPanelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
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
	 * Establece un mensaje informativo en pantalla.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
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
	 * Limpia todos los campos del formulario.
	 */
	public void limpiar() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtApartamentoDestino.setText("");
		txtMotivo.setText("");

		lblMensaje.setText(" ");
	}

	/**
	 * Retorna el campo de cedula.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtCedula() {
		return txtCedula;
	}

	/**
	 * Retorna el campo de nombre.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * Retorna el campo de telefono.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	/**
	 * Retorna el campo de apartamento destino.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtApartamentoDestino() {
		return txtApartamentoDestino;
	}

	/**
	 * Retorna el campo de motivo.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtMotivo() {
		return txtMotivo;
	}

	/**
	 * Retorna el boton de registrar entrada.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRegistrarEntrada() {
		return btnRegistrarEntrada;
	}

	/**
	 * Retorna el boton de registrar salida.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRegistrarSalida() {
		return btnRegistrarSalida;
	}

	/**
	 * Retorna el boton de limpiar.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	/**
	 * Retorna el boton de refrescar.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	/**
	 * Retorna el area de visitas activas.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaVisitasActivas() {
		return areasVisitasActiva;
	}

	/**
	 * Retorna el area de todos los visitantes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaTodosVisitantes() {
		return areaTodosVisitante;
	}

	/**
	 * Retorna el combo de tipos de visitante.
	 * 
	 * @return JComboBox correspondiente
	 */
	public JComboBox<String> getComboTipoVisitante() {
		return comboTipoVisitante;
	}
}