package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Panel utilizado para el inicio de sesion de los usuarios en el sistema.
 * Contiene los campos de usuario y contraseña, junto con los botones de acceso
 * y regreso.
 * 
 * Permite gestionar la interfaz grafica relacionada con el proceso de
 * autenticacion de usuarios.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PanelLogin extends JPanel {

	/**
	 * Identificador unico de serializacion de la clase.
	 */
	private static final long serialVersionUID = 8238339040228671087L;

	/**
	 * Etiqueta principal del formulario de inicio de sesion.
	 */
	private JLabel lblTituloLogin;

	/**
	 * Etiqueta secundaria descriptiva del panel.
	 */
	private JLabel lblSub;

	/**
	 * Etiqueta correspondiente al campo de usuario.
	 */
	private JLabel lblUsuario;

	/**
	 * Etiqueta correspondiente al campo de contraseña.
	 */
	private JLabel lblContrasena;

	/**
	 * Campo de texto utilizado para ingresar el usuario.
	 */
	private JTextField txtLoginUsuario;

	/**
	 * Campo de contraseña utilizado para ingresar la clave de acceso.
	 */
	private JPasswordField txtLoginContrasena;

	/**
	 * Boton utilizado para iniciar sesion en el sistema.
	 */
	private JButton btnEntrarLogin;

	/**
	 * Boton utilizado para regresar a la ventana anterior.
	 */
	private JButton btnVolverLogin;

	/**
	 * Color de fondo principal del panel.
	 */
	private Color colorPanel;

	/**
	 * Color principal utilizado en titulos y elementos destacados.
	 */
	private Color colorVerdeOscuro;

	/**
	 * Color utilizado para los textos secundarios de la interfaz.
	 */
	private Color colorTextoSuave;

	/**
	 * Construye el panel de inicio de sesion e inicializa todos los componentes
	 * graficos necesarios.
	 * 
	 * <b>pre</b> Los colores, campos de texto y botones deben estar inicializados.
	 * <br>
	 * <b>post</b> Se crea e inicializa el panel de inicio de sesion. <br>
	 * 
	 * @param colorPanel         Color de fondo principal del panel
	 * @param colorVerdeOscuro   Color utilizado en titulos y componentes destacados
	 * @param colorTextoSuave    Color utilizado en etiquetas y textos secundarios
	 * @param txtLoginUsuario    Campo de texto para ingresar el usuario
	 * @param txtLoginContrasena Campo de contraseña para ingresar la clave
	 * @param btnEntrarLogin     Boton utilizado para iniciar sesion
	 * @param btnVolverLogin     Boton utilizado para regresar a la ventana anterior
	 */
	public PanelLogin(Color colorPanel, Color colorVerdeOscuro, Color colorTextoSuave, JTextField txtLoginUsuario,
			JPasswordField txtLoginContrasena, JButton btnEntrarLogin, JButton btnVolverLogin) {
		this.colorPanel = colorPanel;
		this.colorVerdeOscuro = colorVerdeOscuro;
		this.colorTextoSuave = colorTextoSuave;
		this.txtLoginUsuario = txtLoginUsuario;
		this.txtLoginContrasena = txtLoginContrasena;
		this.btnEntrarLogin = btnEntrarLogin;
		this.btnVolverLogin = btnVolverLogin;
		construir();
	}

	/**
	 * Inicializa y organiza todos los componentes graficos del panel de inicio de
	 * sesion.
	 * 
	 * <b>pre</b> Los componentes visuales deben estar correctamente inicializados.
	 * <br>
	 * <b>post</b> El panel queda configurado y listo para mostrarse en pantalla.
	 * <br>
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(new EmptyBorder(50, 50, 50, 50));
		add(Box.createVerticalGlue());
		lblTituloLogin = new JLabel("Iniciar sesión", SwingConstants.LEFT);
		lblTituloLogin.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblTituloLogin.setForeground(colorVerdeOscuro);
		lblTituloLogin.setAlignmentX(CENTER_ALIGNMENT);
		add(lblTituloLogin);
		add(Box.createVerticalStrut(6));

		lblSub = new JLabel("Accede a tu cuenta", SwingConstants.LEFT);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSub.setForeground(colorTextoSuave);
		lblSub.setAlignmentX(CENTER_ALIGNMENT);
		add(lblSub);
		add(Box.createVerticalStrut(28));

		lblUsuario = crearLabel("Usuario");
		lblUsuario.setAlignmentX(CENTER_ALIGNMENT);
		add(lblUsuario);
		add(Box.createVerticalStrut(5));

		txtLoginUsuario.setMaximumSize(new Dimension(320, 28));
		txtLoginUsuario.setAlignmentX(CENTER_ALIGNMENT);
		add(txtLoginUsuario);
		add(Box.createVerticalStrut(18));

		lblContrasena = crearLabel("Contraseña");
		lblContrasena.setAlignmentX(CENTER_ALIGNMENT);
		add(lblContrasena);
		add(Box.createVerticalStrut(5));
		
		txtLoginContrasena.setMaximumSize(new Dimension(320, 28));
		txtLoginContrasena.setAlignmentX(CENTER_ALIGNMENT);
		add(txtLoginContrasena);
		add(Box.createVerticalStrut(28));

		btnEntrarLogin.setMaximumSize(new Dimension(320, 36));
		btnEntrarLogin.setAlignmentX(CENTER_ALIGNMENT);
		add(btnEntrarLogin);
		add(Box.createVerticalStrut(10));

		btnVolverLogin.setMaximumSize(new Dimension(320, 36));
		btnVolverLogin.setAlignmentX(CENTER_ALIGNMENT);
		add(btnVolverLogin);
		add(Box.createVerticalGlue());
	}

	/**
	 * Crea una etiqueta personalizada con el formato visual utilizado en el panel.
	 * 
	 * <b>pre</b> El texto recibido puede ser cualquier cadena valida. <br>
	 * <b>post</b> Se retorna una etiqueta configurada con el estilo definido para
	 * el panel. <br>
	 * 
	 * @param texto Texto que tendra la etiqueta
	 * 
	 * @return JLabel configurado con el texto y formato visual correspondiente
	 */
	public JLabel crearLabel(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);

		return lbl;
	}
}