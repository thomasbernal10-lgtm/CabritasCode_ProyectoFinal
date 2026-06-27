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
 * Ventana principal correspondiente al rol de propietario dentro del sistema.
 * Permite acceder a los modulos de reservas, visitantes, operativa e
 * informacion financiera del apartamento.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaPropietario extends JFrame {
	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 7372819047362810474L;
	/**
	 * Color de fondo principal de la ventana.
	 */
	private Color colorFondo;
	/**
	 * Color base utilizado en los paneles.
	 */
	private Color colorPanel;
	/**
	 * Color verde principal de la interfaz.
	 */
	private Color colorVerde;
	/**
	 * Color verde oscuro utilizado en encabezados y botones.
	 */
	private Color colorVerdeOscuro;
	/**
	 * Color utilizado para los bordes.
	 */
	private Color colorBorde;
	/**
	 * Color blanco utilizado en componentes.
	 */
	private Color colorBlanco;
	/**
	 * Color utilizado para textos secundarios.
	 */
	private Color colorTextoSuave;
	/**
	 * Color de fondo de campos de texto.
	 */
	private Color colorCampoFondo;
	/**
	 * Color de borde de campos de texto.
	 */
	private Color colorCampoBorde;
	/**
	 * Color rojo utilizado en mensajes o botones de alerta.
	 */
	private Color colorRojo;
	/**
	 * Boton utilizado para cerrar sesion.
	 */
	private JButton btnCerrarSesion;
	/**
	 * Barra superior correspondiente al propietario.
	 */
	private BarraPropietario barraPropietario;
	/**
	 * Panel de pestanas principal.
	 */
	private JTabbedPane tabbedPane;
	/**
	 * Pestana de reservas y zonas comunes.
	 */
	private PestanaReservasResidente pestanaReserva;
	/**
	 * Pestana de visitantes y apartamento.
	 */
	private PestanaVisitantesResidente pestanaApartamento;
	/**
	 * Pestana operativa de incidentes y mantenimiento.
	 */
	private PestanaOperativaResidente pestanaOperativa;
	/**
	 * Pestana financiera del propietario.
	 */
	private PestanaFinancierosPropietario pestanaFinanciero;

	/**
	 * Construye la ventana principal del propietario.
	 * 
	 * <b>pre</b> El username y numero de apartamento no deben ser null. <br>
	 * <b>post</b> La ventana queda inicializada y configurada.
	 * 
	 * @param username          Nombre de usuario autenticado
	 * @param numeroApartamento Numero del apartamento asociado
	 */
	public VentanaPropietario(String username, String numeroApartamento) {
		inicializarPaleta();
		inicializarComponentes(username, numeroApartamento);
	}

	/**
	 * Inicializa la paleta de colores utilizada en la interfaz grafica.
	 * 
	 * <b>post</b> Los colores de la interfaz quedan configurados.
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
	 * Inicializa y configura todos los componentes graficos de la ventana.
	 * 
	 * <b>pre</b> Los datos del usuario deben ser validos. <br>
	 * <b>post</b> La ventana queda completamente configurada.
	 * 
	 * @param username          Nombre de usuario autenticado
	 * @param numeroApartamento Numero del apartamento asociado
	 */
	public void inicializarComponentes(String username, String numeroApartamento) {
		setTitle("GreenBuilding Manager - Propietario");
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
		barraPropietario = new BarraPropietario(colorVerdeOscuro, colorBlanco, colorTextoSuave, colorBorde, username,
				numeroApartamento, btnCerrarSesion);
		pestanaReserva = new PestanaReservasResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaApartamento = new PestanaVisitantesResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorBorde, colorBlanco);
		pestanaOperativa = new PestanaOperativaResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaFinanciero = new PestanaFinancierosPropietario(colorPanel, colorVerdeOscuro, colorVerde,
				colorTextoSuave, colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
		tabbedPane.setBackground(colorFondo);
		tabbedPane.addTab("  Zonas y Reservas  ", pestanaReserva);
		tabbedPane.addTab("  Mi Apartamento  ", pestanaApartamento);
		tabbedPane.addTab("  Incidentes y Mantenimiento  ", pestanaOperativa);
		tabbedPane.addTab("  Mis Finanzas  ", pestanaFinanciero);
		add(barraPropietario, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Crea y configura un boton personalizado.
	 * 
	 * <b>pre</b> El texto y colores no deben ser null. <br>
	 * <b>post</b> Se retorna un boton configurado visualmente.
	 * 
	 * @param texto Texto del boton
	 * @param fondo Color de fondo
	 * @param letra Color de letra
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
	 * Retorna la pestana de reservas y zonas comunes.
	 * 
	 * @return PestanaReservasResidente correspondiente
	 */
	public PestanaReservasResidente getPestanaReservas() {
		return pestanaReserva;
	}

	/**
	 * Retorna la pestana correspondiente al apartamento y visitantes.
	 * 
	 * @return PestanaVisitantesResidente correspondiente
	 */
	public PestanaVisitantesResidente getPestanaApartamento() {
		return pestanaApartamento;
	}

	/**
	 * Retorna la pestana operativa de incidentes y mantenimiento.
	 * 
	 * @return PestanaOperativaResidente correspondiente
	 */
	public PestanaOperativaResidente getPestanaOperativa() {
		return pestanaOperativa;
	}

	/**
	 * Retorna la pestana financiera del propietario.
	 * 
	 * @return PestanaFinancierosPropietario correspondiente
	 */
	public PestanaFinancierosPropietario getPestanaFinancieros() {
		return pestanaFinanciero;
	}
}
