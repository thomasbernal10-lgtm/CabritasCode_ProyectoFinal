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

/**
 * Panel superior utilizado en la ventana del vigilante. Contiene el titulo
 * principal del sistema, la informacion del usuario en sesion y el boton para
 * cerrar sesion.
 * 
 * Permite mantener una barra visual uniforme en la interfaz del vigilante.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class BarraVigilante extends JPanel {

	/**
	 * Identificador unico de serializacion de la clase.
	 */
	private static final long serialVersionUID = -7322372226149966295L;

	/**
	 * Etiqueta que muestra el titulo principal del sistema.
	 */
	private JLabel lblTitulo;

	/**
	 * Etiqueta que muestra el nombre del vigilante en sesion.
	 */
	private JLabel lblUsuario;

	/**
	 * Construye la barra superior de la ventana del vigilante. Inicializa los
	 * componentes graficos, configura colores, dimensiones, distribucion y agrega
	 * el boton para cerrar sesion.
	 * 
	 * <b>pre</b> Los colores y el boton de cerrar sesion deben estar inicializados.
	 * <br>
	 * <b>post</b> Se crea e inicializa la barra superior del vigilante. <br>
	 * 
	 * @param colorVerdeOscuro Color de fondo principal de la barra
	 * @param colorBlanco      Color utilizado para el titulo principal
	 * @param colorBorde       Color utilizado para el texto del usuario
	 * @param username         Nombre del vigilante en sesion
	 * @param btnCerrarSesion  Boton utilizado para cerrar la sesion activa
	 */
	public BarraVigilante(Color colorVerdeOscuro, Color colorBlanco, Color colorBorde, String username,
			JButton btnCerrarSesion) {
		setBackground(colorVerdeOscuro);
		setPreferredSize(new Dimension(0, 50));
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		lblTitulo = new JLabel("GreenBuilding Manager");
		lblTitulo.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTitulo.setForeground(colorBlanco);
		add(lblTitulo);
		add(Box.createHorizontalGlue());

		lblUsuario = new JLabel("Vigilante: " + username);
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setForeground(colorBorde);
		add(lblUsuario);
		add(Box.createHorizontalStrut(20));

		btnCerrarSesion.setPreferredSize(new Dimension(130, 30));
		btnCerrarSesion.setMaximumSize(new Dimension(130, 30));
		add(btnCerrarSesion);
	}

	/**
	 * Retorna la etiqueta que contiene el titulo principal del sistema.
	 * 
	 * <b>pre</b> El componente lblTitulo debe estar inicializado. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 * 
	 * @return JLabel correspondiente al titulo principal de la barra
	 */
	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	/**
	 * Retorna la etiqueta que contiene la informacion del vigilante en sesion.
	 * 
	 * <b>pre</b> El componente lblUsuario debe estar inicializado. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 * 
	 * @return JLabel correspondiente al usuario mostrado en la barra
	 */
	public JLabel getLblUsuario() {
		return lblUsuario;
	}
}