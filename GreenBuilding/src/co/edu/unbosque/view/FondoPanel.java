package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel personalizado utilizado para mostrar un fondo de color o una imagen de
 * fondo en las interfaces graficas del sistema.
 * 
 * Permite establecer fondos visuales reutilizables para las diferentes ventanas
 * y paneles de la aplicacion.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class FondoPanel extends JPanel {

	/**
	 * Identificador unico de serializacion de la clase.
	 */
	private static final long serialVersionUID = -3462026593808443423L;

	/**
	 * Color de fondo utilizado cuando no existe una imagen asignada.
	 */
	private Color colorFondo;

	/**
	 * Imagen utilizada como fondo del panel.
	 */
	private Image imagenFondo;

	/**
	 * Construye un panel con un color de fondo personalizado.
	 * 
	 * <b>pre</b> El color recibido puede ser null. <br>
	 * <b>post</b> Se crea el panel con el color de fondo especificado. <br>
	 * 
	 * @param colorFondo Color utilizado como fondo del panel
	 */
	public FondoPanel(Color colorFondo) {
		super(new BorderLayout());
		this.colorFondo = colorFondo;
	}

	/**
	 * Construye un panel utilizando una imagen como fondo.
	 * 
	 * <b>pre</b> La ruta de la imagen debe corresponder a un recurso valido dentro
	 * del proyecto. <br>
	 * <b>post</b> Se carga la imagen y se asigna como fondo del panel si la ruta es
	 * valida. <br>
	 * 
	 * @param rutaImagen Ruta relativa de la imagen utilizada como fondo
	 */
	public FondoPanel(String rutaImagen) {
		super(new BorderLayout());
		URL url = getClass().getClassLoader().getResource(rutaImagen);
		if (url != null) {
			imagenFondo = new ImageIcon(url).getImage();
		}
	}

	/**
	 * Dibuja el contenido grafico del panel. Si existe una imagen de fondo, esta se
	 * ajusta automaticamente al tamaño del panel. En caso contrario, se pinta el
	 * color de fondo configurado.
	 * 
	 * <b>pre</b> El objeto Graphics no debe ser null. <br>
	 * <b>post</b> El panel es renderizado con su respectivo fondo visual. <br>
	 * 
	 * @param g Objeto Graphics utilizado para realizar el dibujo del componente
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagenFondo != null) {
			g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
		} else if (colorFondo != null) {
			g.setColor(colorFondo);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}