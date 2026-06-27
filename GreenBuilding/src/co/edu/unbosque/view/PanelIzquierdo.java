package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel izquierdo de la pantalla de login del sistema GreenBuilding Manager.
 * Extiende JPanel y muestra el fondo grafico de la aplicacion junto con el
 * logotipo corporativo de GreenBuilding. Se ubica en la seccion izquierda del
 * PanelLogin y forma parte de la identidad visual del sistema. <br>
 * <b>pre</b> Los recursos graficos login_bg.png y logo.png deben estar
 * disponibles en el classpath del proyecto. <br>
 * <b>post</b> El panel queda construido con el fondo y el logo cargados,
 * listo para ser agregado a la ventana de bienvenida. <br>
 *
 * @author GreenBuilding Group
 * @version 1.0
 */
public class PanelIzquierdo extends JPanel {

    /**
     * Identificador de version para la serializacion de la clase.
     */
	private static final long serialVersionUID = 5789425559007501887L;

    /** Etiqueta que muestra el logotipo del sistema. */
    private JLabel lblLogo;

    /**
     * Constructor por defecto. Invoca el metodo construir para inicializar y
     * organizar todos los componentes del panel izquierdo. <br>
     * <b>pre</b> No hay precondiciones especiales. <br>
     * <b>post</b> El panel queda completamente construido con fondo y logo. <br>
     */
    public PanelIzquierdo() {
        construir();
    }

    /**
     * Inicializa y organiza los componentes del panel: establece el layout,
     * crea el fondo grafico y carga el logotipo en la parte superior. <br>
     * <b>pre</b> Los recursos graficos deben estar disponibles en el classpath.
     * <br>
     * <b>post</b> El panel queda con BorderLayout, el fondo aplicado y el logo
     * posicionado en la zona norte. <br>
     */
    public void construir() {
        setLayout(new BorderLayout());
        FondoPanel fondo = new FondoPanel("login_bg.png");
        lblLogo = cargarImagen("logo.png", 307, 205);
        fondo.add(lblLogo, BorderLayout.NORTH);
        add(fondo, BorderLayout.CENTER);
    }

    /**
     * Carga una imagen desde el classpath, la escala a las dimensiones
     * indicadas y la retorna dentro de un JLabel alineado. <br>
     * <b>pre</b> La ruta debe corresponder a un recurso disponible en el
     * classpath del proyecto. <br>
     * <b>post</b> Se retorna un JLabel con la imagen escalada, o un JLabel
     * vacio si el recurso no fue encontrado. <br>
     *
     * @param ruta  Ruta relativa del recurso de imagen. ruta != null, ruta != ""
     * @param ancho Ancho en pixeles al que se escalara la imagen. ancho > 0
     * @param alto  Alto en pixeles al que se escalara la imagen. alto > 0
     * @return JLabel con la imagen escalada, o JLabel vacio si no se encontro
     */
    public JLabel cargarImagen(String ruta, int ancho, int alto) {
        URL url = getClass().getClassLoader().getResource(ruta);
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        if (url != null) {
            Image imgEscalada = new ImageIcon(url).getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            lbl.setIcon(new ImageIcon(imgEscalada));
        }
        return lbl;
    }

    /**
     * Retorna la etiqueta que muestra el logotipo del sistema. <br>
     * <b>pre</b> El objeto PanelIzquierdo debe estar instanciado. <br>
     * <b>post</b> No se modifica el estado del objeto. <br>
     *
     * @return JLabel con el logotipo cargado
     */
    public JLabel getLblLogo() {
        return lblLogo;
    }
}
