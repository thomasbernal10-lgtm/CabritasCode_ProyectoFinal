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
 * Ventana principal correspondiente al rol de consejo de administracion.
 * Permite acceder a funcionalidades de reservas, apartamento, gestion
 * operativa, finanzas y herramientas administrativas del consejo.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaConsejo extends JFrame {
	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 60L;
	/**
	 * Color de fondo general de la ventana.
	 */
	private Color colorFondo;
	/**
	 * Color principal de los paneles.
	 */
	private Color colorPanel;
	/**
	 * Color verde principal.
	 */
	private Color colorVerde;
	/**
	 * Color verde oscuro utilizado en encabezados y botones.
	 */
	private Color colorVerdeOscuro;
	/**
	 * Color utilizado para bordes.
	 */
	private Color colorBorde;
	/**
	 * Color blanco utilizado en textos y componentes.
	 */
	private Color colorBlanco;
	/**
	 * Color utilizado para textos secundarios.
	 */
	private Color colorTextoSuave;
	/**
	 * Color de fondo de los campos.
	 */
	private Color colorCampoFondo;
	/**
	 * Color de borde de campos.
	 */
	private Color colorCampoBorde;
	/**
	 * Color rojo utilizado para errores y acciones peligrosas.
	 */
	private Color colorRojo;
	/**
	 * Boton utilizado para cerrar sesion.
	 */
	private JButton btnCerrarSesion;
	/**
	 * Barra superior del usuario consejo.
	 */
	private BarraPropietario barraConsejo;
	/**
	 * Pestana de reservas y zonas comunes.
	 */
	private PestanaReservasResidente pestanaReserva;
	/**
	 * Pestana de informacion del apartamento.
	 */
	private PestanaVisitantesResidente pestanaApartamento;
	/**
	 * Pestana operativa de incidentes y mantenimientos.
	 */
	private PestanaOperativaResidente pestanaOperativa;
	/**
	 * Pestana financiera del propietario.
	 */
	private PestanaFinancierosPropietario pestanaFinanciero;
	/**
	 * Pestana exclusiva del consejo de administracion.
	 */
	private PestanaConsejo pestanaConsejo;
	/**
	 * Panel principal de pestanas.
	 */
	private JTabbedPane tabbedPane;
	/**
	 * Construye la ventana principal del consejo de administracion.
	 * 
	 * <b>pre</b> El username y numeroApartamento no deben ser null. <br>
	 * <b>post</b> La ventana queda inicializada y configurada visualmente.
	 * 
	 * @param username          Username del usuario autenticado
	 * @param numeroApartamento Numero de apartamento asociado
	 */
	public VentanaConsejo(String username, String numeroApartamento) {
		inicializarPaleta();
		inicializarComponentes(username, numeroApartamento);
	}

	/**
	 * Inicializa la paleta de colores utilizada en la interfaz.
	 * 
	 * <b>pre</b> No aplica. <br>
	 * <b>post</b> Todos los colores quedan inicializados.
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
	 * <b>pre</b> La paleta de colores debe estar inicializada. <br>
	 * <b>post</b> Todos los componentes quedan agregados y configurados.
	 * 
	 * @param username          Username del usuario autenticado
	 * @param numeroApartamento Numero del apartamento asociado
	 */
	public void inicializarComponentes(String username, String numeroApartamento) {
		setTitle("GreenBuilding Manager - Consejo de Administracion");
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
		barraConsejo = new BarraPropietario(colorVerdeOscuro, colorBlanco, colorTextoSuave, colorBorde, username,
				numeroApartamento, btnCerrarSesion);
		pestanaReserva = new PestanaReservasResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaApartamento = new PestanaVisitantesResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorBorde, colorBlanco);
		pestanaOperativa = new PestanaOperativaResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaFinanciero = new PestanaFinancierosPropietario(colorPanel, colorVerdeOscuro, colorVerde,
				colorTextoSuave, colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaConsejo = new PestanaConsejo(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave, colorCampoFondo,
				colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
		tabbedPane.setBackground(colorFondo);
		tabbedPane.addTab("  Zonas y Reservas  ", pestanaReserva);
		tabbedPane.addTab("  Mi Apartamento  ", pestanaApartamento);
		tabbedPane.addTab("  Incidentes y Mantenimiento  ", pestanaOperativa);
		tabbedPane.addTab("  Mis Finanzas  ", pestanaFinanciero);
		tabbedPane.addTab("  Consejo  ", pestanaConsejo);
		add(barraConsejo, BorderLayout.NORTH);
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
	 * @return JButton configurado visualmente
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
	 * Retorna la pestana de apartamento.
	 * 
	 * @return PestanaVisitantesResidente correspondiente
	 */
	public PestanaVisitantesResidente getPestanaApartamento() {
		return pestanaApartamento;
	}

	/**
	 * Retorna la pestana operativa.
	 * 
	 * @return PestanaOperativaResidente correspondiente
	 */
	public PestanaOperativaResidente getPestanaOperativa() {
		return pestanaOperativa;
	}

	/**
	 * Retorna la pestana financiera.
	 * 
	 * @return PestanaFinancierosPropietario correspondiente
	 */
	public PestanaFinancierosPropietario getPestanaFinancieros() {
		return pestanaFinanciero;
	}

	/**
	 * Retorna la pestana exclusiva del consejo.
	 * 
	 * @return PestanaConsejo correspondiente
	 */
	public PestanaConsejo getPestanaConsejo() {
		return pestanaConsejo;
	}
}