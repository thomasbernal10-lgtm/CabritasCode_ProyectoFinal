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
 * Panel superior utilizado en las ventanas administrativas del sistema.
 * Contiene el titulo de la aplicacion, la informacion del conjunto, el nombre
 * del administrador en sesion y el boton de cierre de sesion.
 * <p>
 * Este componente se utiliza como barra de navegacion visual para las
 * interfaces administrativas de GreenBuilding Manager.
 * </p>
 * 
 * <b>pre</b> Los colores, el nombre de usuario, el nombre del conjunto y el
 * boton de cierre de sesion deben ser validos y diferentes de null. <br>
 * <b>post</b> Se crea e inicializa una barra grafica con todos sus componentes
 * configurados y agregados al panel.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class BarraAdmin extends JPanel {

	/**
	 * Serial generado para la clase.
	 */
	private static final long serialVersionUID = -1234567890123456789L;

	/**
	 * Etiqueta que muestra el titulo principal del sistema.
	 */
	private JLabel lblTitulo;

	/**
	 * Etiqueta que muestra el nombre del conjunto residencial.
	 */
	private JLabel lblConjunto;

	/**
	 * Etiqueta que muestra el usuario administrador en sesion.
	 */
	private JLabel lblUsuario;

	/**
	 * Construye e inicializa la barra superior administrativa del sistema.
	 * Configura los colores, etiquetas, distribucion y boton de cierre de sesion.
	 * 
	 * <b>pre</b> Los parametros recibidos deben ser validos y no nulos. <br>
	 * <b>post</b> La barra queda completamente inicializada y lista para ser
	 * agregada a una ventana.
	 *
	 * @param colorVerdeOscuro Color de fondo principal de la barra
	 * @param colorBlanco      Color utilizado para el titulo principal
	 * @param colorTextoSuave  Color utilizado para el texto secundario
	 * @param colorBorde       Color utilizado para el nombre del administrador
	 * @param username         Nombre del administrador en sesion
	 * @param nombreConjunto   Nombre del conjunto residencial
	 * @param btnCerrarSesion  Boton utilizado para cerrar la sesion activa
	 */
	public BarraAdmin(Color colorVerdeOscuro, Color colorBlanco, Color colorTextoSuave, Color colorBorde,
			String username, String nombreConjunto, JButton btnCerrarSesion) {

		setBackground(colorVerdeOscuro);
		setPreferredSize(new Dimension(0, 50));
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		lblTitulo = new JLabel("GreenBuilding Manager");
		lblTitulo.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTitulo.setForeground(colorBlanco);
		add(lblTitulo);

		add(Box.createHorizontalGlue());

		lblConjunto = new JLabel("Conjunto: " + nombreConjunto);
		lblConjunto.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblConjunto.setForeground(colorTextoSuave);
		add(lblConjunto);

		add(Box.createHorizontalStrut(20));

		lblUsuario = new JLabel("Admin: " + username);
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setForeground(colorBorde);
		add(lblUsuario);

		add(Box.createHorizontalStrut(20));

		btnCerrarSesion.setPreferredSize(new Dimension(130, 30));
		btnCerrarSesion.setMaximumSize(new Dimension(130, 30));
		add(btnCerrarSesion);
	}
}