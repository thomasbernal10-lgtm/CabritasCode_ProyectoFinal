package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Ventana principal de bienvenida del sistema GreenBuilding Manager.
 * 
 * Permite visualizar la pantalla inicial del sistema y acceder al formulario de
 * inicio de sesion.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaBienvenida extends JFrame {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -5517366109059190692L;
	/**
	 * Boton utilizado para abrir la pantalla de inicio de sesion.
	 */
	private JButton btnIniciarSesion;
	/**
	 * Boton utilizado para cerrar la aplicacion.
	 */
	private JButton btnSalir;
	/**
	 * Campo de texto para ingresar el usuario.
	 */
	private JTextField txtLoginUsuario;
	/**
	 * Campo de contrasena para el login.
	 */
	private JPasswordField txtLoginContrasena;
	/**
	 * Boton utilizado para ingresar al sistema.
	 */
	private JButton btnEntrarLogin;
	/**
	 * Boton utilizado para volver a la pantalla principal.
	 */
	private JButton btnVolverLogin;
	/**
	 * Color de fondo principal.
	 */
	private Color colorFondo;
	/**
	 * Color principal de paneles.
	 */
	private Color colorPanel;
	/**
	 * Color verde principal.
	 */
	private Color colorVerde;
	/**
	 * Color verde oscuro.
	 */
	private Color colorVerdeOscuro;
	/**
	 * Color de bordes.
	 */
	private Color colorBorde;
	/**
	 * Color blanco.
	 */
	private Color colorBlanco;
	/**
	 * Color de texto suave.
	 */
	private Color colorTextoSuave;
	/**
	 * Color de fondo para campos.
	 */
	private Color colorCampoFondo;
	/**
	 * Color de borde para campos.
	 */
	private Color colorCampoBorde;
	/**
	 * Panel grafico izquierdo decorativo.
	 */
	private PanelIzquierdo panelIzquierdo;
	/**
	 * Panel principal de bienvenida.
	 */
	private PanelPrincipal panelPrincipal;
	/**
	 * Panel correspondiente al login.
	 */
	private PanelLogin panelLogin;
	/**
	 * Panel contenedor dinamico.
	 */
	private JPanel panelContenido;
	/**
	 * Construye la ventana de bienvenida inicializando colores y componentes.
	 * 
	 * <b>post</b> La ventana queda inicializada y visible.
	 */
	public VentanaBienvenida() {
		inicializarPaleta();
		inicializarComponentes();
	}

	/**
	 * Inicializa la paleta de colores utilizada en la interfaz.
	 * 
	 * <b>post</b> Los colores quedan configurados.
	 */
	public void inicializarPaleta() {
		colorFondo = new Color(240, 245, 240);
		colorPanel = Color.WHITE;
		colorVerde = new Color(74, 124, 89);
		colorVerdeOscuro = new Color(45, 90, 61);
		colorBorde = new Color(200, 220, 205);
		colorBlanco = Color.WHITE;
		colorTextoSuave = new Color(100, 120, 105);
		colorCampoFondo = new Color(245, 250, 246);
		colorCampoBorde = new Color(180, 210, 185);
	}

	/**
	 * Inicializa todos los componentes graficos de la ventana.
	 * 
	 * <b>post</b> La interfaz grafica queda completamente construida.
	 */
	public void inicializarComponentes() {
		setTitle("GreenBuilding Manager");
		URL urlIcono = getClass().getClassLoader().getResource("icono.png");
		if (urlIcono != null) {
			setIconImage(new ImageIcon(urlIcono).getImage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(980, 620);
		setMinimumSize(new Dimension(800, 520));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().setBackground(colorFondo);
		btnIniciarSesion = crearBoton("  Iniciar sesion", colorVerde, true);
		btnSalir = crearBoton("  Salir", colorBlanco, false);
		btnEntrarLogin = crearBoton("Ingresar", colorVerde, true);
		btnVolverLogin = crearBoton("Volver", colorBlanco, false);
		txtLoginUsuario = crearCampo();
		txtLoginContrasena = crearCampoClave();
		panelIzquierdo = new PanelIzquierdo();
		panelPrincipal = new PanelPrincipal(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave, btnIniciarSesion,
				btnSalir);
		panelLogin = new PanelLogin(colorPanel, colorVerdeOscuro, colorTextoSuave, txtLoginUsuario, txtLoginContrasena,
				btnEntrarLogin, btnVolverLogin);
		panelContenido = new JPanel(new BorderLayout());
		panelContenido.setBackground(colorPanel);
		panelContenido.setPreferredSize(new Dimension(440, 0));
		panelContenido.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, colorBorde));
		mostrarPantallaPrincipal();
		add(panelIzquierdo, BorderLayout.CENTER);
		add(panelContenido, BorderLayout.EAST);
	}

	/**
	 * Muestra la pantalla principal de bienvenida.
	 * 
	 * <b>post</b> El panel principal queda visible.
	 */
	public void mostrarPantallaPrincipal() {
		panelContenido.removeAll();
		panelContenido.add(panelPrincipal, BorderLayout.CENTER);
		panelContenido.revalidate();
		panelContenido.repaint();
	}

	/**
	 * Muestra la pantalla de inicio de sesion.
	 * 
	 * <b>post</b> El panel de login queda visible.
	 */
	public void mostrarPantallaLogin() {
		panelContenido.removeAll();
		panelContenido.add(panelLogin, BorderLayout.CENTER);
		panelContenido.revalidate();
		panelContenido.repaint();
	}

	/**
	 * Crea y configura un boton personalizado.
	 * 
	 * <b>pre</b> El texto y color no deben ser null. <br>
	 * <b>post</b> Se retorna el boton configurado.
	 * 
	 * @param texto      Texto del boton
	 * @param colorFondo Color de fondo
	 * @param esPrimario true si es boton principal
	 * 
	 * @return JButton configurado
	 */
	public JButton crearBoton(String texto, Color colorFondo, boolean esPrimario) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("SansSerif", Font.BOLD, 14));
		btn.setBackground(colorFondo);
		if (esPrimario) {
			btn.setForeground(colorBlanco);
		} else {
			btn.setForeground(colorVerdeOscuro);
		}
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorBorde, 1, true),
				BorderFactory.createEmptyBorder(8, 20, 8, 20)));
		btn.setOpaque(true);
		btn.setPreferredSize(new Dimension(320, 36));
		return btn;
	}

	/**
	 * Crea y configura un campo de texto personalizado.
	 * 
	 * <b>post</b> Se retorna el campo configurado.
	 * 
	 * @return JTextField configurado
	 */
	public JTextField crearCampo() {
		JTextField tf = new JTextField();
		tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf.setBackground(colorCampoFondo);
		tf.setForeground(new Color(50, 70, 55));
		tf.setCaretColor(colorVerde);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde, 1, true),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)));
		tf.setPreferredSize(new Dimension(320, 28));
		return tf;
	}

	/**
	 * Crea y configura un campo de contrasena personalizado.
	 * 
	 * <b>post</b> Se retorna el campo configurado.
	 * 
	 * @return JPasswordField configurado
	 */
	public JPasswordField crearCampoClave() {
		JPasswordField pf = new JPasswordField();
		pf.setFont(new Font("SansSerif", Font.PLAIN, 13));
		pf.setBackground(colorCampoFondo);
		pf.setForeground(new Color(50, 70, 55));
		pf.setCaretColor(colorVerde);
		pf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde, 1, true),
				BorderFactory.createEmptyBorder(6, 12, 6, 12)));
		pf.setPreferredSize(new Dimension(320, 28));
		return pf;
	}

	/**
	 * Limpia los campos correspondientes al login.
	 * 
	 * <b>post</b> Los campos quedan vacios.
	 */
	public void limpiarLogin() {
		if (txtLoginUsuario != null) {
			txtLoginUsuario.setText("");
		}
		if (txtLoginContrasena != null) {
			txtLoginContrasena.setText("");
		}
	}

	/**
	 * Muestra un mensaje de error.
	 * 
	 * @param msg Mensaje a mostrar
	 */
	public void mostrarError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Muestra un mensaje informativo.
	 * 
	 * @param msg Mensaje a mostrar
	 */
	public void mostrarInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Obtiene el usuario ingresado en el login.
	 * 
	 * @return String correspondiente al usuario
	 */
	public String getLoginUsuario() {
		if (txtLoginUsuario == null) {
			return "";
		}
		return txtLoginUsuario.getText().trim();
	}

	/**
	 * Obtiene la contrasena ingresada.
	 * 
	 * @return String correspondiente a la contrasena
	 */
	public String getLoginContrasena() {
		if (txtLoginContrasena == null) {
			return "";
		}
		return new String(txtLoginContrasena.getPassword());
	}

	/**
	 * Retorna el boton iniciar sesion.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	/**
	 * Modifica el boton iniciar sesion.
	 * 
	 * @param btnIniciarSesion Nuevo boton
	 */
	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}

	/**
	 * Retorna el boton salir.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnSalir() {
		return btnSalir;
	}

	/**
	 * Modifica el boton salir.
	 * 
	 * @param btnSalir Nuevo boton
	 */
	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	/**
	 * Retorna el boton ingresar.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnEntrarLogin() {
		return btnEntrarLogin;
	}

	/**
	 * Modifica el boton ingresar.
	 * 
	 * @param btnEntrarLogin Nuevo boton
	 */
	public void setBtnEntrarLogin(JButton btnEntrarLogin) {
		this.btnEntrarLogin = btnEntrarLogin;
	}

	/**
	 * Retorna el boton volver.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnVolverLogin() {
		return btnVolverLogin;
	}

	/**
	 * Modifica el boton volver.
	 * 
	 * @param btnVolverLogin Nuevo boton
	 */
	public void setBtnVolverLogin(JButton btnVolverLogin) {
		this.btnVolverLogin = btnVolverLogin;
	}

	/**
	 * Retorna el campo de usuario.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtLoginUsuario() {
		return txtLoginUsuario;
	}

	/**
	 * Retorna el campo de contrasena.
	 * 
	 * @return JPasswordField correspondiente
	 */
	public JPasswordField getTxtLoginContrasena() {
		return txtLoginContrasena;
	}

	/**
	 * Retorna el color verde principal.
	 * 
	 * @return Color verde
	 */
	public Color getColorVerde() {
		return colorVerde;
	}

	/**
	 * Retorna el color verde oscuro.
	 * 
	 * @return Color verde oscuro
	 */
	public Color getColorVerdeOscuro() {
		return colorVerdeOscuro;
	}

	/**
	 * Retorna el color blanco.
	 * 
	 * @return Color blanco
	 */
	public Color getColorBlanco() {
		return colorBlanco;
	}

	/**
	 * Retorna el color de bordes.
	 * 
	 * @return Color de bordes
	 */
	public Color getColorBorde() {
		return colorBorde;
	}
}