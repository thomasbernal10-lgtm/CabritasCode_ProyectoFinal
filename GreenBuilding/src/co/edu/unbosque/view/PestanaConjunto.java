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

/**
 * Panel encargado de la gestion de conjuntos residenciales dentro del sistema.
 * Permite registrar, visualizar y eliminar conjuntos.
 * 
 * @author GreenBuilding
 */
public class PestanaConjunto extends JPanel {

	/**
	 * Serial version UID de la clase.
	 */
	private static final long serialVersionUID = 597449200105797119L;

	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtCiudad;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JButton btnCrear;
	private JButton btnLimpiar;
	private JLabel lblMensaje;
	private JTextArea areaLista;
	private JTextField txtIndex;
	private JButton btnEliminar;
	private JButton btnRefrescar;
	private Color colorPanel;
	private Color colorVerdeOscuro;
	private Color colorVerde;
	private Color colorTextoSuave;
	private Color colorCampoFondo;
	private Color colorCampoBorde;
	private Color colorBorde;
	private Color colorBlanco;
	private Color colorRojo;

	/**
	 * Construye la pestana de gestion de conjuntos. <b>pre</b> Los colores
	 * recibidos no deben ser null. <br>
	 * <b>post</b> Se inicializa la interfaz grafica de la pestana. <br>
	 *
	 * @param colorPanel       Color de fondo principal
	 * @param colorVerdeOscuro Color verde oscuro utilizado en textos
	 * @param colorVerde       Color verde principal
	 * @param colorTextoSuave  Color para textos secundarios
	 * @param colorCampoFondo  Color de fondo de los campos
	 * @param colorCampoBorde  Color del borde de los campos
	 * @param colorBorde       Color general de bordes
	 * @param colorBlanco      Color blanco utilizado en botones
	 * @param colorRojo        Color rojo utilizado en mensajes de error
	 */
	public PestanaConjunto(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
	 * Construye la estructura principal de la pestana. <b>pre</b> Los componentes
	 * graficos deben estar inicializados. <br>
	 * <b>post</b> Se agregan los paneles de formulario y lista al panel principal.
	 * <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(crearPanelFormulario());
		add(Box.createHorizontalStrut(20));
		add(crearPanelLista());
	}

	/**
	 * Crea el panel del formulario para registrar conjuntos. <b>pre</b> Los colores
	 * y fuentes deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel del formulario configurado. <br>
	 *
	 * @return JPanel con el formulario de registro
	 */
	public JPanel crearPanelFormulario() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
		panel.setPreferredSize(new Dimension(340, 0));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nuevo Conjunto", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtNombre = crearCampo();
		txtDireccion = crearCampo();
		txtCiudad = crearCampo();
		txtTelefono = crearCampo();
		txtCorreo = crearCampo();

		agregarCampo(panel, "Nombre:", txtNombre);
		agregarCampo(panel, "Direccion:", txtDireccion);
		agregarCampo(panel, "Ciudad:", txtCiudad);
		agregarCampo(panel, "Telefono:", txtTelefono);
		agregarCampo(panel, "Correo:", txtCorreo);

		panel.add(Box.createVerticalStrut(15));

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setOpaque(false);
		panelBotones.setAlignmentX(LEFT_ALIGNMENT);

		btnCrear = crearBoton("Crear", colorVerde, colorBlanco);
		btnLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);

		panelBotones.add(btnCrear);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiar);

		panel.add(panelBotones);
		panel.add(Box.createVerticalStrut(8));

		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensaje.setForeground(colorRojo);
		lblMensaje.setAlignmentX(LEFT_ALIGNMENT);

		panel.add(lblMensaje);
		panel.add(Box.createVerticalGlue());

		return panel;
	}

	/**
	 * Crea el panel encargado de mostrar la lista de conjuntos registrados.
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel de lista configurado. <br>
	 *
	 * @return JPanel con la lista de conjuntos
	 */
	public JPanel crearPanelLista() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);

		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Conjuntos registrados", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		areaLista = new JTextArea();
		areaLista.setEditable(false);
		areaLista.setFont(new Font("Monospaced", Font.PLAIN, 12));
		areaLista.setBackground(colorCampoFondo);
		areaLista.setBorder(BorderFactory.createLineBorder(colorCampoBorde));

		JScrollPane scroll = new JScrollPane(areaLista);
		scroll.setAlignmentX(LEFT_ALIGNMENT);

		panel.add(scroll);
		panel.add(Box.createVerticalStrut(10));

		JPanel panelAcciones = new JPanel();
		panelAcciones.setLayout(new BoxLayout(panelAcciones, BoxLayout.X_AXIS));
		panelAcciones.setOpaque(false);
		panelAcciones.setAlignmentX(LEFT_ALIGNMENT);

		JLabel lblIndex = new JLabel("Index: ");
		lblIndex.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblIndex.setForeground(colorVerdeOscuro);

		txtIndex = crearCampo();
		txtIndex.setMaximumSize(new Dimension(60, 32));

		btnEliminar = crearBoton("Eliminar", colorRojo, colorBlanco);
		btnRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);

		panelAcciones.add(lblIndex);
		panelAcciones.add(txtIndex);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnEliminar);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnRefrescar);

		panel.add(panelAcciones);

		return panel;
	}

	/**
	 * Agrega un campo de texto con su etiqueta correspondiente al panel indicado.
	 * <b>pre</b> El panel y el campo no deben ser null. <br>
	 * <b>post</b> Se agrega el campo y su etiqueta al panel. <br>
	 *
	 * @param panel    Panel donde se agregara el campo
	 * @param etiqueta Texto de la etiqueta
	 * @param campo    Campo de texto a agregar
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
	 * Crea un campo de texto personalizado. <b>pre</b> Los colores deben estar
	 * inicializados. <br>
	 * <b>post</b> Se retorna un JTextField configurado. <br>
	 *
	 * @return JTextField personalizado
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
	 * Crea un boton personalizado. <b>pre</b> El texto y colores no deben ser null.
	 * <br>
	 * <b>post</b> Se retorna un JButton configurado. <br>
	 *
	 * @param texto Texto del boton
	 * @param fondo Color de fondo
	 * @param letra Color del texto
	 * @return JButton personalizado
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
	 * Limpia todos los campos del formulario. <b>pre</b> Los campos deben estar
	 * inicializados. <br>
	 * <b>post</b> Los campos quedan vacios y se limpia el mensaje. <br>
	 */
	public void limpiar() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtCiudad.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		lblMensaje.setText(" ");
	}

	/**
	 * Actualiza el mensaje mostrado al usuario. <b>pre</b> El mensaje no debe ser
	 * null. <br>
	 * <b>post</b> Se actualiza el texto y color del mensaje. <br>
	 *
	 * @param msg     Mensaje a mostrar
	 * @param esError true si es un mensaje de error, false en caso contrario
	 */
	public void setMensaje(String msg, boolean esError) {
		lblMensaje.setForeground(esError ? colorRojo : colorVerdeOscuro);
		lblMensaje.setText(msg);
	}

	/**
	 * Retorna el campo de texto del nombre. <b>pre</b> El componente debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField del nombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * Retorna el campo de texto de la direccion. <b>pre</b> El componente debe
	 * estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField de la direccion
	 */
	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	/**
	 * Retorna el campo de texto de la ciudad. <b>pre</b> El componente debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField de la ciudad
	 */
	public JTextField getTxtCiudad() {
		return txtCiudad;
	}

	/**
	 * Retorna el campo de texto del telefono. <b>pre</b> El componente debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField del telefono
	 */
	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	/**
	 * Retorna el campo de texto del correo. <b>pre</b> El componente debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField del correo
	 */
	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	/**
	 * Retorna el boton de crear conjunto. <b>pre</b> El boton debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JButton de crear
	 */
	public JButton getBtnCrear() {
		return btnCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario. <b>pre</b> El boton debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JButton de limpiar
	 */
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	/**
	 * Retorna el area de texto de la lista de conjuntos. <b>pre</b> El componente
	 * debe estar inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextArea con la lista de conjuntos
	 */
	public JTextArea getAreaLista() {
		return areaLista;
	}

	/**
	 * Retorna el campo de texto del indice. <b>pre</b> El componente debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JTextField del indice
	 */
	public JTextField getTxtIndex() {
		return txtIndex;
	}

	/**
	 * Retorna el boton de eliminar conjunto. <b>pre</b> El boton debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JButton de eliminar
	 */
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	/**
	 * Retorna el boton de refrescar lista. <b>pre</b> El boton debe estar
	 * inicializado. <br>
	 * <b>post</b> No se modifica el estado del sistema. <br>
	 *
	 * @return JButton de refrescar
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}
}