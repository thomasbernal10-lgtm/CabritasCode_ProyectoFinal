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
 * Panel grafico encargado de administrar usuarios del sistema.
 * 
 * Permite bloquear, desbloquear y visualizar todos los usuarios registrados
 * dentro del conjunto residencial.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaUsuario extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -5554982774012057179L;

	/**
	 * Area de texto utilizada para mostrar la lista de usuarios.
	 */
	private JTextArea areaLista;

	/**
	 * Campo de texto utilizado para ingresar el username del usuario.
	 */
	private JTextField txtUsernameAccion;

	/**
	 * Boton utilizado para bloquear usuarios.
	 */
	private JButton btnBloquear;

	/**
	 * Boton utilizado para desbloquear usuarios.
	 */
	private JButton btnDesbloquear;

	/**
	 * Boton utilizado para refrescar la lista de usuarios.
	 */
	private JButton btnRefrescar;

	/**
	 * Etiqueta utilizada para mostrar mensajes informativos.
	 */
	private JLabel lblMensaje;

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
	 * Construye la pestana de administracion de usuarios.
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
	public PestanaUsuario(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JPanel panelAcciones = new JPanel();
		panelAcciones.setLayout(new BoxLayout(panelAcciones, BoxLayout.X_AXIS));
		panelAcciones.setBackground(colorPanel);
		panelAcciones.setAlignmentX(LEFT_ALIGNMENT);
		panelAcciones.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Acciones sobre usuario",
						0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 15, 8, 15)));
		panelAcciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUsername.setForeground(colorTextoSuave);
		txtUsernameAccion = new JTextField();
		txtUsernameAccion.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtUsernameAccion.setBackground(colorCampoFondo);
		txtUsernameAccion.setForeground(colorVerdeOscuro);
		txtUsernameAccion.setCaretColor(colorVerde);
		txtUsernameAccion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(colorCampoBorde, 1), BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		txtUsernameAccion.setMaximumSize(new Dimension(200, 32));
		btnBloquear = crearBoton("Bloquear", colorRojo, colorBlanco);
		btnDesbloquear = crearBoton("Desbloquear", colorVerde, colorBlanco);
		btnRefrescar = crearBoton("Refrescar lista", colorVerde, colorBlanco);
		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensaje.setForeground(colorRojo);
		panelAcciones.add(lblUsername);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(txtUsernameAccion);
		panelAcciones.add(Box.createHorizontalStrut(10));
		panelAcciones.add(btnBloquear);
		panelAcciones.add(Box.createHorizontalStrut(8));
		panelAcciones.add(btnDesbloquear);
		panelAcciones.add(Box.createHorizontalStrut(20));
		panelAcciones.add(btnRefrescar);
		panelAcciones.add(Box.createHorizontalStrut(15));
		panelAcciones.add(lblMensaje);
		add(panelAcciones);
		add(Box.createVerticalStrut(15));
		JPanel panelLista = new JPanel();
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		panelLista.setBackground(colorPanel);
		panelLista.setAlignmentX(LEFT_ALIGNMENT);
		panelLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
				"Todos los usuarios del sistema", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro));
		areaLista = new JTextArea();
		areaLista.setEditable(false);
		areaLista.setFont(new Font("Monospaced", Font.PLAIN, 12));
		areaLista.setBackground(colorCampoFondo);
		areaLista.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scroll = new JScrollPane(areaLista);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panelLista.add(scroll);
		add(panelLista);
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
	 * Establece un mensaje informativo en la interfaz.
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
	 * Retorna el area de lista de usuarios.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaLista() {
		return areaLista;
	}

	/**
	 * Retorna el campo del username.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtUsernameAccion() {
		return txtUsernameAccion;
	}

	/**
	 * Retorna el boton de bloquear usuarios.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnBloquear() {
		return btnBloquear;
	}

	/**
	 * Retorna el boton de desbloquear usuarios.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnDesbloquear() {
		return btnDesbloquear;
	}

	/**
	 * Retorna el boton de refrescar usuarios.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}
}