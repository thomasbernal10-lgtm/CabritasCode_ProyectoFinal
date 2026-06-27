package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Ventana principal correspondiente al rol de arrendatario.
 * 
 * Permite al usuario gestionar reservas, consultar informacion de su
 * apartamento y reportar incidentes o solicitudes de mantenimiento.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaArrendatario extends JFrame {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 6372819047362810473L;

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
	 * Color utilizado para bordes.
	 */
	private Color colorBorde;

	/**
	 * Color blanco.
	 */
	private Color colorBlanco;

	/**
	 * Color utilizado para textos suaves.
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
	 * Color rojo utilizado para errores y botones de salida.
	 */
	private Color colorRojo;

	/**
	 * Boton utilizado para cerrar sesion.
	 */
	private JButton btnCerrarSesion;

	/**
	 * Barra superior de navegacion del arrendatario.
	 */
	private BarraArrendatario barraArrendatario;

	/**
	 * Panel de pestanas principal.
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Pestana de reservas y zonas comunes.
	 */
	private PestanaReservasResidente pestanaReserva;

	/**
	 * Pestana correspondiente a visitantes y apartamento.
	 */
	private PestanaVisitantesResidente pestanaVisitante;

	/**
	 * Pestana correspondiente a incidentes y mantenimiento.
	 */
	private PestanaOperativaResidente pestanaOperativa;

	/**
	 * Construye la ventana principal del arrendatario.
	 * 
	 * <b>pre</b> El username y numeroApartamento no deben ser null. <br>
	 * <b>post</b> La ventana queda inicializada y configurada.
	 * 
	 * @param username          Username del arrendatario
	 * @param numeroApartamento Numero del apartamento asociado
	 */
	public VentanaArrendatario(String username, String numeroApartamento) {
		inicializarPaleta();
		inicializarComponentes(username, numeroApartamento);
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
		colorRojo = new Color(200, 50, 50);
	}

	/**
	 * Inicializa todos los componentes graficos de la ventana.
	 * 
	 * <b>pre</b> Los datos del usuario deben ser validos. <br>
	 * <b>post</b> La interfaz queda completamente construida.
	 * 
	 * @param username          Username del arrendatario
	 * @param numeroApartamento Numero del apartamento asociado
	 */
	public void inicializarComponentes(String username, String numeroApartamento) {
		setTitle("GreenBuilding Manager - Arrendatario");
		URL urlIcono = getClass().getClassLoader().getResource("icono.png");
		if (urlIcono != null) {
			setIconImage(new ImageIcon(urlIcono).getImage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1150, 720);
		setMinimumSize(new Dimension(950, 620));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().setBackground(colorFondo);

		btnCerrarSesion = crearBoton("Cerrar Sesion", colorRojo, colorBlanco);

		barraArrendatario = new BarraArrendatario(colorVerdeOscuro, colorBlanco, colorTextoSuave, colorBorde, username,
				numeroApartamento, btnCerrarSesion);

		pestanaReserva = new PestanaReservasResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

		pestanaVisitante = new PestanaVisitantesResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorBorde, colorBlanco);

		pestanaOperativa = new PestanaOperativaResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
		tabbedPane.setBackground(colorFondo);
		tabbedPane.addTab("  Zonas y Reservas  ", pestanaReserva);
		tabbedPane.addTab("  Mi Apartamento  ", pestanaVisitante);
		tabbedPane.addTab("  Incidentes y Mantenimiento  ", pestanaOperativa);
		add(barraArrendatario, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Crea y configura un boton personalizado.
	 * 
	 * <b>pre</b> El texto y colores no deben ser null. <br>
	 * <b>post</b> Se retorna el boton configurado.
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
	 * Retorna el boton de cerrar sesion.
	 * 
	 * @return JButton correspondiente al boton cerrar sesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	/**
	 * Retorna la pestana de reservas.
	 * 
	 * @return PestanaReservasResidente correspondiente
	 */
	public PestanaReservasResidente getPestanaReservas() {
		return pestanaReserva;
	}

	/**
	 * Retorna la pestana de visitantes y apartamento.
	 * 
	 * @return PestanaVisitantesResidente correspondiente
	 */
	public PestanaVisitantesResidente getPestanaVisitantes() {
		return pestanaVisitante;
	}

	/**
	 * Retorna la pestana operativa.
	 * 
	 * @return PestanaOperativaResidente correspondiente
	 */
	public PestanaOperativaResidente getPestanaOperativa() {
		return pestanaOperativa;
	}
}