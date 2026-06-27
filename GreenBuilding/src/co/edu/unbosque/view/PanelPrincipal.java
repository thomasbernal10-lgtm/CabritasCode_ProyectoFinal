package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Panel principal de bienvenida del sistema GreenBuilding. Contiene el logo
 * institucional, mensajes informativos y los botones principales para acceder o
 * salir del sistema.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PanelPrincipal extends JPanel {

	/**
	 * Serial unico de la clase.
	 */
	private static final long serialVersionUID = -8943911383615080840L;
	private JLabel lblIcono;
	private JLabel lblBienvenida;
	private JLabel lblSub;
	private JLabel lblPie;
	private JButton btnIniciarSesion;
	private JButton btnSalir;
	private Color colorPanel;
	private Color colorVerdeOscuro;
	private Color colorVerde;
	private Color colorTextoSuave;

	/**
	 * Construye el panel principal inicializando todos los componentes graficos.
	 * <b>pre</b> Los colores y botones recibidos no deben ser null. <br>
	 * <b>post</b> El panel queda construido y listo para mostrarse.
	 * 
	 * @param colorPanel       Color de fondo principal
	 * @param colorVerdeOscuro Color utilizado en el titulo principal
	 * @param colorVerde       Color utilizado en elementos secundarios
	 * @param colorTextoSuave  Color utilizado en los textos informativos
	 * @param btnIniciarSesion Boton para iniciar sesion
	 * @param btnSalir         Boton para salir del sistema
	 */
	public PanelPrincipal(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
			JButton btnIniciarSesion, JButton btnSalir) {
		this.colorPanel = colorPanel;
		this.colorVerdeOscuro = colorVerdeOscuro;
		this.colorVerde = colorVerde;
		this.colorTextoSuave = colorTextoSuave;
		this.btnIniciarSesion = btnIniciarSesion;
		this.btnSalir = btnSalir;
		construir();
	}

	/**
	 * Construye y organiza todos los componentes visuales del panel principal.
	 * <b>pre</b> Los atributos de configuracion visual deben estar inicializados.
	 * <br>
	 * <b>post</b> Todos los componentes quedan agregados al panel principal.
	 */
	public void construir() {
		setLayout(new BorderLayout());
		setBackground(colorPanel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(new EmptyBorder(40, 50, 40, 50));
		lblIcono = cargarImagen("icono_verde.png", 307, 205);
		lblIcono.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(lblIcono);
		panel.add(Box.createVerticalStrut(16));

		lblBienvenida = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblBienvenida.setForeground(colorVerdeOscuro);
		lblBienvenida.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(lblBienvenida);
		panel.add(Box.createVerticalStrut(6));

		lblSub = new JLabel("Ingresa a tu cuenta para continuar", SwingConstants.CENTER);
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSub.setForeground(colorTextoSuave);
		lblSub.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(lblSub);
		panel.add(Box.createVerticalStrut(30));

		btnIniciarSesion.setMaximumSize(new Dimension(320, 36));
		btnIniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(btnIniciarSesion);
		panel.add(Box.createVerticalStrut(6));

		btnSalir.setMaximumSize(new Dimension(320, 36));
		btnSalir.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(btnSalir);
		panel.add(Box.createVerticalStrut(28));

		lblPie = new JLabel("Construyendo un futuro mejor juntos.", SwingConstants.CENTER);
		lblPie.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblPie.setForeground(colorVerde);
		lblPie.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(lblPie);
		add(panel, BorderLayout.CENTER);
	}

	/**
	 * Carga una imagen desde los recursos del proyecto y la escala al tamaño
	 * especificado manteniendo la proporcion original. <b>pre</b> La ruta no debe
	 * ser null. <br>
	 * <b>post</b> Se retorna un JLabel con la imagen cargada o un texto alternativo
	 * si no se encuentra la imagen.
	 * 
	 * @param ruta     Ruta de la imagen dentro de los recursos
	 * @param anchoMax Ancho maximo permitido para la imagen
	 * @param altoMax  Alto maximo permitido para la imagen
	 * @return JLabel con la imagen cargada o un mensaje alternativo
	 */
	public JLabel cargarImagen(String ruta, int anchoMax, int altoMax) {
		URL url = getClass().getClassLoader().getResource(ruta);
		JLabel lbl = new JLabel();
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		if (url != null) {
			ImageIcon iconoOriginal = new ImageIcon(url);
			int anchoOriginal = iconoOriginal.getIconWidth();
			int altoOriginal = iconoOriginal.getIconHeight();
			double ratio = Math.min((double) anchoMax / anchoOriginal, (double) altoMax / altoOriginal);
			int nuevoAncho = (int) (anchoOriginal * ratio);
			int nuevoAlto = (int) (altoOriginal * ratio);
			Image imgEscalada = iconoOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto,
					Image.SCALE_SMOOTH);
			lbl.setIcon(new ImageIcon(imgEscalada));
		} else {
			lbl.setText("e");
			lbl.setFont(new Font("SansSerif", Font.PLAIN, 48));
			lbl.setForeground(colorVerde);
		}
		return lbl;
	}

	/**
	 * Retorna la etiqueta del icono principal.
	 * 
	 * @return JLabel correspondiente al icono principal
	 */
	public JLabel getLblIcono() {
		return lblIcono;
	}

	/**
	 * Asigna la etiqueta del icono principal.
	 * 
	 * @param lblIcono Nueva etiqueta del icono
	 */
	public void setLblIcono(JLabel lblIcono) {
		this.lblIcono = lblIcono;
	}

	/**
	 * Retorna la etiqueta de bienvenida.
	 * 
	 * @return JLabel de bienvenida
	 */
	public JLabel getLblBienvenida() {
		return lblBienvenida;
	}

	/**
	 * Asigna la etiqueta de bienvenida.
	 * 
	 * @param lblBienvenida Nueva etiqueta de bienvenida
	 */
	public void setLblBienvenida(JLabel lblBienvenida) {
		this.lblBienvenida = lblBienvenida;
	}

	/**
	 * Retorna la etiqueta secundaria informativa.
	 * 
	 * @return JLabel del subtitulo
	 */
	public JLabel getLblSub() {
		return lblSub;
	}

	/**
	 * Asigna la etiqueta secundaria informativa.
	 * 
	 * @param lblSub Nuevo subtitulo
	 */
	public void setLblSub(JLabel lblSub) {
		this.lblSub = lblSub;
	}

	/**
	 * Retorna la etiqueta del mensaje inferior.
	 * 
	 * @return JLabel del pie de pagina
	 */
	public JLabel getLblPie() {
		return lblPie;
	}

	/**
	 * Asigna la etiqueta del mensaje inferior.
	 * 
	 * @param lblPie Nuevo mensaje inferior
	 */
	public void setLblPie(JLabel lblPie) {
		this.lblPie = lblPie;
	}

	/**
	 * Retorna el boton para iniciar sesion.
	 * 
	 * @return JButton de inicio de sesion
	 */
	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	/**
	 * Asigna el boton para iniciar sesion.
	 * 
	 * @param btnIniciarSesion Nuevo boton de inicio de sesion
	 */
	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}

	/**
	 * Retorna el boton para salir del sistema.
	 * 
	 * @return JButton de salida
	 */
	public JButton getBtnSalir() {
		return btnSalir;
	}

	/**
	 * Asigna el boton para salir del sistema.
	 * 
	 * @param btnSalir Nuevo boton de salida
	 */
	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
}