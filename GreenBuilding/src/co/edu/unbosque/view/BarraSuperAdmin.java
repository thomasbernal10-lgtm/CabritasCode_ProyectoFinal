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
 * Panel superior utilizado en la ventana del super administrador. Contiene el
 * titulo principal del sistema, la informacion del usuario en sesion y el boton
 * para cerrar sesion.
 * 
 * Permite mantener una barra visual uniforme en la interfaz del super
 * administrador.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class BarraSuperAdmin extends JPanel {

	/**
	 * Identificador unico de serializacion de la clase.
	 */
	private static final long serialVersionUID = -5087460770292992992L;

	/**
	 * Etiqueta que muestra el titulo principal del sistema.
	 */
	private JLabel lblTitulo;

	/**
	 * Etiqueta que muestra el nombre del super administrador en sesion.
	 */
	private JLabel lblUsuario;

	/**
	 * Construye la barra superior de la ventana del super administrador. Inicializa
	 * los componentes graficos, configura colores, dimensiones, distribucion y
	 * agrega el boton para cerrar sesion.
	 * 
	 * <b>pre</b> Los colores y el boton de cerrar sesion deben estar inicializados.
	 * <br>
	 * <b>post</b> Se crea e inicializa la barra superior del super administrador.
	 * <br>
	 * 
	 * @param colorVerdeOscuro Color de fondo principal de la barra
	 * @param colorBlanco      Color utilizado para el titulo principal
	 * @param colorTextoSuave  Color secundario de texto
	 * @param colorBorde       Color utilizado para el texto del usuario
	 * @param username         Nombre del usuario super administrador en sesion
	 * @param btnCerrarSesion  Boton utilizado para cerrar la sesion activa
	 */
	public BarraSuperAdmin(Color colorVerdeOscuro, Color colorBlanco, Color colorTextoSuave, Color colorBorde,
			String username, JButton btnCerrarSesion) {
		setBackground(colorVerdeOscuro);
		setPreferredSize(new Dimension(0, 50));
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		lblTitulo = new JLabel("GreenBuilding Manager");
		lblTitulo.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTitulo.setForeground(colorBlanco);
		add(lblTitulo);
		add(Box.createHorizontalGlue());

		lblUsuario = new JLabel("Super Admin: " + username);
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setForeground(colorBorde);
		add(lblUsuario);
		add(Box.createHorizontalStrut(20));

		btnCerrarSesion.setPreferredSize(new Dimension(130, 30));
		btnCerrarSesion.setMaximumSize(new Dimension(130, 30));
		add(btnCerrarSesion);
	}
}