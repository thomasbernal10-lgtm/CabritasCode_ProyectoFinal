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
 * Ventana principal del rol Administrador en el sistema GreenBuilding Manager.
 * Extiende JFrame y organiza las funcionalidades del administrador en pestanas
 * agrupadas por modulos: Residentes, Zonas y Parqueaderos, Gestion Operativa,
 * Finanzas y Sostenibilidad, y Reportes. Incluye una barra superior con el
 * nombre del usuario, el conjunto gestionado y el boton de cierre de sesion. <br>
 * <b>pre</b> El username y el nombreConjunto deben corresponder a un usuario
 * administrador autenticado en el sistema. <br>
 * <b>post</b> La ventana queda lista para ser mostrada con todas sus pestanas
 * inicializadas y la paleta de colores aplicada. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class VentanaAdmin extends JFrame {

    /**
     * Identificador de version para la serializacion de la clase.
     */
	private static final long serialVersionUID = 254447843798658635L;

	/** Color de fondo general de la ventana. */
	private Color colorFondo;

    /** Color de fondo de los paneles internos. */
    private Color colorPanel;

    /** Color verde principal del tema visual. */
    private Color colorVerde;

    /** Color verde oscuro para cabeceras y barras. */
    private Color colorVerdeOscuro;

    /** Color para bordes y separadores. */
    private Color colorBorde;

    /** Color blanco utilizado en textos y fondos claros. */
    private Color colorBlanco;

    /** Color de texto suave para etiquetas secundarias. */
    private Color colorTextoSuave;

    /** Color de fondo para campos de texto. */
    private Color colorCampoFondo;

    /** Color de borde para campos de texto. */
    private Color colorCampoBorde;

    /** Color rojo para botones de accion critica como cerrar sesion. */
    private Color colorRojo;

    /** Boton para cerrar la sesion actual del administrador. */
    private JButton btnCerrarSesion;

    /** Barra superior de navegacion del administrador. */
    private BarraAdmin barraAdmin;

    /** Panel con pestanas que organiza los modulos funcionales. */
    private JTabbedPane tabbedPane;

    /** Pestana de gestion de residentes, propietarios y arrendatarios. */
    private PestanaResidente pestanaResidente;

    /** Pestana de gestion de zonas comunes y parqueaderos. */
    private PestanaZonasParqueadero pestanaZonasParqueadero;

    /** Pestana de gestion operativa (visitantes, paquetes, incidentes, mantenimiento). */
    private PestanaGestionOperativa pestanaGestionOperativa;

    /** Pestana de finanzas, pagos, obligaciones y sostenibilidad ambiental. */
    private PestanaFinanzasSostenibilidad pestanaFinanzasSostenibilidad;

    /** Pestana de generacion y visualizacion de reportes administrativos. */
    private PestanaReportesAdmin pestanaReportesAdmin;

    /**
     * Constructor completo. Inicializa la paleta de colores y todos los
     * componentes graficos de la ventana del administrador. <br>
     * <b>pre</b> Los parametros no deben ser null ni vacios. <br>
     * <b>post</b> La ventana queda completamente construida con todas sus
     * pestanas y lista para ser mostrada. <br>
     *
     * @param username        Nombre de usuario del administrador autenticado.
     *                        username != null, username != ""
     * @param nombreConjunto  Nombre del conjunto residencial que administra.
     *                        nombreConjunto != null, nombreConjunto != ""
     */
    public VentanaAdmin(String username, String nombreConjunto) {
        inicializarPaleta();
        inicializarComponentes(username, nombreConjunto);
    }

    /**
     * Inicializa la paleta de colores del tema visual de la ventana. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> Todos los atributos de color quedan inicializados con los
     * valores del tema verde corporativo de GreenBuilding. <br>
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
     * Inicializa y organiza todos los componentes graficos de la ventana,
     * incluyendo la barra de navegacion, el panel de pestanas y cada una de
     * las pestanas funcionales del administrador. <br>
     * <b>pre</b> La paleta de colores debe estar inicializada. <br>
     * <b>post</b> La ventana queda completamente construida con titulo, icono,
     * tamano, barra de navegacion y todas las pestanas configuradas. <br>
     *
     * @param username        Nombre de usuario para mostrar en la barra.
     *                        username != null, username != ""
     * @param nombreConjunto  Nombre del conjunto para mostrar en la barra.
     *                        nombreConjunto != null, nombreConjunto != ""
     */
    public void inicializarComponentes(String username, String nombreConjunto) {
        setTitle("GreenBuilding Manager - Administrador");
        URL urlIcono = getClass().getClassLoader().getResource("icono.png");
        if (urlIcono != null) {
            setIconImage(new ImageIcon(urlIcono).getImage());
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setMinimumSize(new Dimension(950, 620));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        btnCerrarSesion = crearBoton("Cerrar Sesion", colorRojo, colorBlanco);

        barraAdmin = new BarraAdmin(colorVerdeOscuro, colorBlanco, colorTextoSuave, colorBorde, username,
                nombreConjunto, btnCerrarSesion);

        pestanaResidente = new PestanaResidente(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
                colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

        pestanaZonasParqueadero = new PestanaZonasParqueadero(colorPanel, colorVerdeOscuro, colorVerde,
                colorTextoSuave, colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

        pestanaGestionOperativa = new PestanaGestionOperativa(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
                colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

        pestanaFinanzasSostenibilidad = new PestanaFinanzasSostenibilidad(colorPanel, colorVerdeOscuro, colorVerde,
                colorTextoSuave, colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

        pestanaReportesAdmin = new PestanaReportesAdmin(colorPanel, colorVerdeOscuro, colorVerde, colorTextoSuave,
                colorCampoFondo, colorCampoBorde, colorBorde, colorBlanco, colorRojo);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));
        tabbedPane.setBackground(colorFondo);
        tabbedPane.addTab("  Residentes  ", pestanaResidente);
        tabbedPane.addTab("  Zonas y Parqueaderos  ", pestanaZonasParqueadero);
        tabbedPane.addTab("  Gestion Operativa  ", pestanaGestionOperativa);
        tabbedPane.addTab("  Finanzas y Sostenibilidad  ", pestanaFinanzasSostenibilidad);
        tabbedPane.addTab("  Reportes  ", pestanaReportesAdmin);
        add(barraAdmin, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Crea un boton con el texto, color de fondo y color de letra indicados,
     * aplicando el estilo visual del sistema GreenBuilding. <br>
     * <b>pre</b> Los parametros no deben ser null. <br>
     * <b>post</b> Se retorna un JButton configurado con el estilo corporativo
     * sin borde ni foco pintado. <br>
     *
     * @param texto  Texto a mostrar en el boton. texto != null, texto != ""
     * @param fondo  Color de fondo del boton. fondo != null
     * @param letra  Color del texto del boton. letra != null
     * @return JButton configurado con el estilo corporativo
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
     * Retorna el boton de cerrar sesion de la ventana del administrador. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return JButton de cerrar sesion
     */
    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    /**
     * Retorna la pestana de gestion de residentes. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return PestanaResidente con los componentes de gestion de residentes
     */
    public PestanaResidente getPestanaResidentes() {
        return pestanaResidente;
    }

    /**
     * Retorna la pestana de gestion de zonas comunes y parqueaderos. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return PestanaZonasParqueadero con los componentes de zonas y parqueaderos
     */
    public PestanaZonasParqueadero getPestanaZonasParqueaderos() {
        return pestanaZonasParqueadero;
    }

    /**
     * Retorna la pestana de gestion operativa. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return PestanaGestionOperativa con los componentes operativos
     */
    public PestanaGestionOperativa getPestanaGestionOperativa() {
        return pestanaGestionOperativa;
    }

    /**
     * Retorna la pestana de finanzas y sostenibilidad. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return PestanaFinanzasSostenibilidad con los componentes financieros y ambientales
     */
    public PestanaFinanzasSostenibilidad getPestanaFinanzasSostenibilidad() {
        return pestanaFinanzasSostenibilidad;
    }

    /**
     * Retorna la pestana de reportes administrativos. <br>
     * <b>pre</b> El objeto VentanaAdmin debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return PestanaReportesAdmin con los componentes de reportes
     */
    public PestanaReportesAdmin getPestanaReportesAdmin() {
        return pestanaReportesAdmin;
    }
}
