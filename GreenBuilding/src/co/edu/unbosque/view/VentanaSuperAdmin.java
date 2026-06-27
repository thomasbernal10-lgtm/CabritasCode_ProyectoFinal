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
 * Ventana principal correspondiente al rol de Super Administrador. Permite
 * gestionar conjuntos, administradores, usuarios y configuracion general del
 * sistema.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class VentanaSuperAdmin extends JFrame {
	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 3925294535971065433L;
	/**
	 * Color de fondo general de la ventana.
	 */
	private Color colorFondo;
	/**
	 * Color principal utilizado en paneles.
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
	 * Color de bordes.
	 */
	private Color colorBorde;
	/**
	 * Color blanco utilizado en componentes.
	 */
	private Color colorBlanco;
	/**
	 * Color de texto suave.
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
	 * Color rojo utilizado para errores y cerrar sesion.
	 */
	private Color colorRojo;
	/**
	 * Boton para cerrar sesion.
	 */
	private JButton btnCerrarSesion;
	/**
	 * Barra superior personalizada del super administrador.
	 */
	private BarraSuperAdmin barraSuperAdmin;
	/**
	 * Pestana para administracion de conjuntos.
	 */
	private PestanaConjunto pestanaConjunto;
	/**
	 * Pestana para administracion de administradores.
	 */
	private PestanaAdministrador pestanaAdministrador;
	/**
	 * Pestana para administracion de usuarios.
	 */
	private PestanaUsuario pestanaUsuario;
	/**
	 * Pestana para configuracion de conjuntos.
	 */
	private PestanaConfigurarConjunto pestanaConfigurarConjunto;
	/**
	 * Panel de pestanas principal.
	 */
	private JTabbedPane tabbedPane;
	/**
	 * Construye la ventana principal del super administrador.
	 * 
	 * <b>pre</b> El username no debe ser null. <br>
	 * <b>post</b> La ventana queda inicializada y configurada.
	 * 
	 * @param username Username del super administrador autenticado
	 */
	public VentanaSuperAdmin(String username) {
		inicializarPaleta();
		inicializarComponentes(username);
	}

	/**
	 * Inicializa la paleta de colores utilizada en la interfaz.
	 * 
	 * <b>pre</b> Ninguna. <br>
	 * <b>post</b> Los colores de la interfaz quedan definidos.
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
	 * <b>pre</b> El username no debe ser null. <br>
	 * <b>post</b> La ventana queda completamente construida.
	 * 
	 * @param username Username del usuario autenticado
	 */
	public void inicializarComponentes(String username) {
		setTitle("GreenBuilding Manager - Super Administrador");
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
		barraSuperAdmin = new BarraSuperAdmin(colorVerdeOscuro, colorBlanco, colorTextoSuave, colorBorde, username,
				btnCerrarSesion);
		pestanaConjunto = new PestanaConjunto(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaAdministrador = new PestanaAdministrador(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
				colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaUsuario = new PestanaUsuario(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave, colorCampoFondo,
				colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		pestanaConfigurarConjunto = new PestanaConfigurarConjunto(colorPanel, colorVerdeOscuro, colorVerde,
				colorTextoSuave, colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
		tabbedPane.setBackground(colorFondo);
		tabbedPane.addTab("  Conjuntos  ", pestanaConjunto);
		tabbedPane.addTab("  Administradores  ", pestanaAdministrador);
		tabbedPane.addTab("  Usuarios  ", pestanaUsuario);
		tabbedPane.addTab("  Configurar Conjunto  ", pestanaConfigurarConjunto);
		add(barraSuperAdmin, BorderLayout.NORTH);
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
	 * Retorna la pestana de conjuntos.
	 * 
	 * @return PestanaConjunto correspondiente a conjuntos
	 */
	public PestanaConjunto getPestanaConjuntos() {
		return pestanaConjunto;
	}

	/**
	 * Retorna la pestana de administradores.
	 * 
	 * @return PestanaAdministrador correspondiente a administradores
	 */
	public PestanaAdministrador getPestanaAdministradores() {
		return pestanaAdministrador;
	}

	/**
	 * Retorna la pestana de usuarios.
	 * 
	 * @return PestanaUsuario correspondiente a usuarios
	 */
	public PestanaUsuario getPestanaUsuarios() {
		return pestanaUsuario;
	}

	/**
	 * Retorna la pestana de configuracion de conjuntos.
	 * 
	 * @return PestanaConfigurarConjunto correspondiente a configuracion
	 */
	public PestanaConfigurarConjunto getPestanaConfigurarConjunto() {
		return pestanaConfigurarConjunto;
	}
}