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
 * Ventana grafica correspondiente al rol de vigilante dentro del sistema.
 * Permite gestionar visitantes, vehiculos y paquetes registrados en el conjunto
 * residencial.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaVigilante extends JFrame {
	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -7460786673079826583L;
	/**
	 * Color de fondo general de la ventana.
	 */
	private Color colorFondo;
	/**
	 * Color base de paneles.
	 */
	private Color colorPanel;
	/**
	 * Color verde principal.
	 */
	private Color colorVerde;
	/**
	 * Color verde oscuro principal.
	 */
	private Color colorVerdeOscuro;
	/**
	 * Color utilizado para bordes.
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
	 * Color de fondo de campos.
	 */
	private Color colorCampoFondo;
	/**
	 * Color de bordes de campos.
	 */
	private Color colorCampoBorde;
	/**
	 * Color rojo utilizado para botones de alerta.
	 */
	private Color colorRojo;
	/**
	 * Boton utilizado para cerrar sesion.
	 */
	private JButton btnCerrarSesion;
	/**
	 * Barra superior de la ventana del vigilante.
	 */
	private BarraVigilante barraVigilante;
	/**
	 * Pestana correspondiente a la gestion de visitantes.
	 */
	private PestanaVisitante pestanaVisitante;
	/**
	 * Pestana correspondiente a la gestion de vehiculos.
	 */
	private PestanaVehiculo pestanaVehiculo;
	/**
	 * Pestana correspondiente a la gestion de paquetes.
	 */
	private PestanaPaquete pestanaPaquete;
	/**
	 * Panel de pestanas principal.
	 */
	private JTabbedPane tabbedPane;
	/**
	 * Construye la ventana principal del vigilante.
	 * 
	 * <b>pre</b> El username no debe ser null. <br>
	 * <b>post</b> La ventana queda inicializada y configurada.
	 * 
	 * @param username Username del vigilante autenticado
	 */
	public VentanaVigilante(String username) {
		inicializarPaleta();
		inicializarComponentes(username);
	}

	/**
	 * Inicializa la paleta de colores utilizada en la interfaz.
	 * 
	 * <b>pre</b> No aplica. <br>
	 * <b>post</b> Los colores de la interfaz quedan inicializados.
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
	 * Inicializa y configura todos los componentes de la ventana.
	 * 
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> La ventana queda completamente construida.
	 * 
	 * @param username Username del vigilante autenticado
	 */
	public void inicializarComponentes(String username) {
		setTitle("GreenBuilding Manager - Vigilante");
		URL urlIcono = getClass().getClassLoader().getResource("icono.png");
		if (urlIcono != null) {
			setIconImage(new ImageIcon(urlIcono).getImage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().setBackground(colorFondo);
		btnCerrarSesion = crearBoton("Cerrar Sesion", colorRojo, colorBlanco);
		barraVigilante = new BarraVigilante(colorVerdeOscuro, colorBlanco, colorBorde, username, btnCerrarSesion);
		pestanaVisitante = new PestanaVisitante(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaVehiculo = new PestanaVehiculo(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaPaquete = new PestanaPaquete(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave, colorCampoFondo,
				colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
		tabbedPane.setBackground(colorFondo);
		tabbedPane.addTab("  Visitantes  ", pestanaVisitante);
		tabbedPane.addTab("  Vehiculos  ", pestanaVehiculo);
		tabbedPane.addTab("  Paquetes  ", pestanaPaquete);
		add(barraVigilante, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Crea y configura un boton personalizado.
	 * 
	 * <b>pre</b> El texto y colores no deben ser null. <br>
	 * <b>post</b> Se retorna el boton configurado visualmente.
	 * 
	 * @param texto Texto del boton
	 * @param fondo Color de fondo
	 * @param letra Color de la letra
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
	 * Retorna la pestana de visitantes.
	 * 
	 * @return PestanaVisitante correspondiente a visitantes
	 */
	public PestanaVisitante getPestanaVisitantes() {
		return pestanaVisitante;
	}
	/**
	 * Retorna la pestana de vehiculos.
	 * 
	 * @return PestanaVehiculo correspondiente a vehiculos
	 */
	public PestanaVehiculo getPestanaVehiculos() {
		return pestanaVehiculo;
	}
	/**
	 * Retorna la pestana de paquetes.
	 * 
	 * @return PestanaPaquete correspondiente a paquetes
	 */
	public PestanaPaquete getPestanaPaquetes() {
		return pestanaPaquete;
	}
}