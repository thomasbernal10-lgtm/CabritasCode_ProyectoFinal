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
 * Panel superior utilizado en las ventanas de arrendatarios del sistema.
 * Contiene el titulo de la aplicacion, la informacion del apartamento, el
 * nombre del arrendatario en sesion y el boton de cierre de sesion.
 * <p>
 * Este componente se utiliza como barra de navegacion visual para las
 * interfaces de los arrendatarios en GreenBuilding Manager.
 * </p>
 * 
 * <b>pre</b> Los colores, el nombre del usuario, el numero del apartamento y el
 * boton de cierre de sesion deben ser validos y diferentes de null. <br>
 * <b>post</b> Se crea e inicializa una barra grafica con todos sus componentes
 * configurados y agregados al panel.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class BarraArrendatario extends JPanel {

	/**
	 * Serial generado para la clase.
	 */
	private static final long serialVersionUID = 3812947120938471029L;

	/**
	 * Etiqueta que muestra el titulo principal del sistema.
	 */
	private JLabel lblTitulo;

	/**
	 * Etiqueta que muestra el numero del apartamento asociado al arrendatario.
	 */
	private JLabel lblApartamento;

	/**
	 * Etiqueta que muestra el nombre del arrendatario en sesion.
	 */
	private JLabel lblUsuario;

	/**
	 * Construye e inicializa la barra superior de arrendatario del sistema.
	 * Configura los colores, etiquetas, distribucion y boton de cierre de sesion.
	 * 
	 * <b>pre</b> Los parametros recibidos deben ser validos y no nulos. <br>
	 * <b>post</b> La barra queda completamente inicializada y lista para ser
	 * agregada a una ventana.
	 *
	 * @param colorVerdeOscuro  Color de fondo principal de la barra
	 * @param colorBlanco       Color utilizado para el titulo principal
	 * @param colorTextoSuave   Color utilizado para el texto secundario
	 * @param colorBorde        Color utilizado para el nombre del arrendatario
	 * @param username          Nombre del arrendatario en sesion
	 * @param numeroApartamento Numero del apartamento asociado
	 * @param btnCerrarSesion   Boton utilizado para cerrar la sesion activa
	 */
	public BarraArrendatario(Color colorVerdeOscuro, Color colorBlanco, Color colorTextoSuave, Color colorBorde,
			String username, String numeroApartamento, JButton btnCerrarSesion) {
		setBackground(colorVerdeOscuro);
		setPreferredSize(new Dimension(0, 50));
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		lblTitulo = new JLabel("GreenBuilding Manager");
		lblTitulo.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTitulo.setForeground(colorBlanco);
		add(lblTitulo);
		add(Box.createHorizontalGlue());

		lblApartamento = new JLabel("Apartamento: " + numeroApartamento);
		lblApartamento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblApartamento.setForeground(colorTextoSuave);
		add(lblApartamento);
		add(Box.createHorizontalStrut(20));

		lblUsuario = new JLabel("Arrendatario: " + username);
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setForeground(colorBorde);
		add(lblUsuario);
		add(Box.createHorizontalStrut(20));

		btnCerrarSesion.setPreferredSize(new Dimension(130, 30));
		btnCerrarSesion.setMaximumSize(new Dimension(130, 30));
		add(btnCerrarSesion);
	}

	/**
	 * Retorna la etiqueta que contiene el titulo principal del sistema. <b>pre</b>
	 * La barra debe haber sido inicializada correctamente. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return JLabel correspondiente al titulo principal
	 */
	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	/**
	 * Retorna la etiqueta que contiene la informacion del apartamento asociado al
	 * arrendatario. <b>pre</b> La barra debe haber sido inicializada correctamente.
	 * <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return JLabel correspondiente al apartamento del arrendatario
	 */
	public JLabel getLblApartamento() {
		return lblApartamento;
	}

	/**
	 * Retorna la etiqueta que contiene el nombre del arrendatario en sesion.
	 * <b>pre</b> La barra debe haber sido inicializada correctamente. <br>
	 * <b>post</b> No se modifica ningun estado del sistema. <br>
	 *
	 * @return JLabel correspondiente al usuario arrendatario
	 */
	public JLabel getLblUsuario() {
		return lblUsuario;
	}
}