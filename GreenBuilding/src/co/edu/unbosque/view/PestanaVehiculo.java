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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel grafico encargado de gestionar consultas y visualizacion de vehiculos
 * autorizados dentro del conjunto residencial.
 * 
 * Permite consultar autorizaciones por placa y visualizar el listado general de
 * vehiculos registrados.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaVehiculo extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -4059022816075338596L;

	/**
	 * Color principal del panel.
	 */
	private Color colorPanel;

	/**
	 * Color verde oscuro utilizado en la interfaz.
	 */
	private Color colorVerdeOscuro;

	/**
	 * Color verde principal utilizado en la interfaz.
	 */
	private Color colorVerde;

	/**
	 * Color utilizado para textos suaves.
	 */
	private Color colorTextoSuave;

	/**
	 * Color de fondo de los campos.
	 */
	private Color colorCampoFondo;

	/**
	 * Color de borde de los campos.
	 */
	private Color colorCampoBorde;

	/**
	 * Color utilizado para bordes generales.
	 */
	private Color colorBorde;

	/**
	 * Color blanco utilizado en componentes graficos.
	 */
	private Color colorBlanco;

	/**
	 * Color rojo utilizado para errores y alertas.
	 */
	private Color colorRojo;

	/**
	 * Campo de texto para consultar una placa.
	 */
	private JTextField txtConsultarPlaca;

	/**
	 * Boton utilizado para realizar consultas de placa.
	 */
	private JButton btnConsultarPlaca;

	/**
	 * Etiqueta utilizada para mostrar el resultado de la consulta.
	 */
	private JLabel lblResultadoConsulta;

	/**
	 * Boton utilizado para refrescar la lista de vehiculos.
	 */
	private JButton btnRefrescar;

	/**
	 * Etiqueta utilizada para mostrar mensajes informativos.
	 */
	private JLabel lblMensaje;

	/**
	 * Area de texto utilizada para mostrar los vehiculos registrados.
	 */
	private JTextArea areaVehiculo;

	/**
	 * Construye la pestana de vehiculos.
	 * 
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> El panel queda completamente inicializado.
	 * 
	 * @param colorPanel       Color principal del panel
	 * @param colorVerdeOscuro Color verde oscuro
	 * @param colorVerde       Color verde principal
	 * @param colorTextoSuave  Color para textos suaves
	 * @param colorCampoFondo  Color de fondo de campos
	 * @param colorCampoBorde  Color de borde de campos
	 * @param colorBorde       Color de bordes generales
	 * @param colorBlanco      Color blanco
	 * @param colorRojo        Color rojo para errores
	 */
	public PestanaVehiculo(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
			Color colorCampoFondo, Color colorCampoBorde, Color colorBorde, Color colorBlanco, Color colorRojo) {
		this.colorPanel = colorPanel;
		this.colorVerdeOscuro = colorVerdeOscuro;
		this.colorVerde = colorVerde;
		this.colorTextoSuave = colorTextoSuave;
		this.colorCampoFondo = colorCampoFondo;
		this.colorCampoBorde = colorCampoBorde;
		this.colorBorde = colorBorde;
		this.colorBlanco = colorBlanco;
		this.colorRojo = colorRojo;
		construir();
	}

	/**
	 * Construye la estructura principal del panel.
	 * 
	 * <b>post</b> El panel principal queda configurado.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		JPanel panelConsulta = new JPanel();
		panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.X_AXIS));
		panelConsulta.setBackground(colorPanel);
		panelConsulta.setAlignmentX(LEFT_ALIGNMENT);
		panelConsulta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		panelConsulta.setBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Consultar autorizacion por placa", 0, 0, new Font("SansSerif", Font.BOLD, 12),
						colorVerdeOscuro), BorderFactory.createEmptyBorder(8, 15, 8, 15)));
		JLabel lblPlaca = new JLabel("Placa: ");
		lblPlaca.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPlaca.setForeground(colorTextoSuave);
		txtConsultarPlaca = new JTextField();
		txtConsultarPlaca.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtConsultarPlaca.setBackground(colorCampoFondo);
		txtConsultarPlaca.setForeground(colorVerdeOscuro);
		txtConsultarPlaca.setCaretColor(colorVerde);
		txtConsultarPlaca.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(colorCampoBorde, 1), BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		txtConsultarPlaca.setMaximumSize(new Dimension(180, 32));
		btnConsultarPlaca = crearBoton("Consultar", colorVerde, colorBlanco);
		lblResultadoConsulta = new JLabel("  Ingrese una placa para verificar autorizacion");
		lblResultadoConsulta.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblResultadoConsulta.setForeground(colorTextoSuave);
		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensaje.setForeground(colorRojo);
		lblMensaje.setAlignmentX(LEFT_ALIGNMENT);
		panelConsulta.add(lblPlaca);
		panelConsulta.add(Box.createHorizontalStrut(8));
		panelConsulta.add(txtConsultarPlaca);
		panelConsulta.add(Box.createHorizontalStrut(10));
		panelConsulta.add(btnConsultarPlaca);
		panelConsulta.add(Box.createHorizontalStrut(20));
		panelConsulta.add(lblResultadoConsulta);
		add(panelConsulta);
		add(Box.createVerticalStrut(8));
		add(lblMensaje);
		add(Box.createVerticalStrut(10));
		JPanel panelLista = new JPanel();
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		panelLista.setBackground(colorPanel);
		panelLista.setAlignmentX(LEFT_ALIGNMENT);
		panelLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
				"Vehiculos registrados y autorizados", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro));
		areaVehiculo = new JTextArea();
		areaVehiculo.setEditable(false);
		areaVehiculo.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaVehiculo.setBackground(colorCampoFondo);
		areaVehiculo.setBorder(BorderFactory.createLineBorder(colorCampoBorde));

		JScrollPane scroll = new JScrollPane(areaVehiculo);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panelLista.add(scroll);
		add(panelLista);
		add(Box.createVerticalStrut(10));
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.X_AXIS));
		panelBoton.setOpaque(false);
		panelBoton.setAlignmentX(LEFT_ALIGNMENT);
		btnRefrescar = crearBoton("Refrescar lista", colorVerde, colorBlanco);
		panelBoton.add(btnRefrescar);
		add(panelBoton);
	}

	/**
	 * Crea y configura un boton personalizado.
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
	 * Establece el resultado de la consulta de una placa.
	 * 
	 * @param msg        Mensaje a mostrar
	 * @param autorizado true si el vehiculo esta autorizado
	 */
	public void setResultadoConsulta(String msg, boolean autorizado) {
		if (autorizado) {
			lblResultadoConsulta.setForeground(colorVerde);
		} else {
			lblResultadoConsulta.setForeground(colorRojo);
		}
		lblResultadoConsulta.setText("  " + msg);
	}

	/**
	 * Establece un mensaje informativo en el panel.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensaje(String msg, boolean esError) {
		if (esError) {
			lblMensaje.setForeground(colorRojo);
		} else {
			lblMensaje.setForeground(colorVerdeOscuro);
		}

		lblMensaje.setText(msg);
	}

	/**
	 * Retorna el campo de consulta de placa.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtConsultarPlaca() {
		return txtConsultarPlaca;
	}

	/**
	 * Retorna el boton de consultar placa.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnConsultarPlaca() {
		return btnConsultarPlaca;
	}

	/**
	 * Retorna el boton de refrescar lista.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	/**
	 * Retorna el area de texto de vehiculos.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaVehiculos() {
		return areaVehiculo;
	}

	/**
	 * Retorna la etiqueta del resultado de consulta.
	 * 
	 * @return JLabel correspondiente
	 */
	public JLabel getLblResultadoConsulta() {
		return lblResultadoConsulta;
	}

	/**
	 * Modifica la etiqueta del resultado de consulta.
	 * 
	 * @param lblResultadoConsulta Nueva etiqueta
	 */
	public void setLblResultadoConsulta(JLabel lblResultadoConsulta) {
		this.lblResultadoConsulta = lblResultadoConsulta;
	}

	/**
	 * Retorna la etiqueta de mensajes.
	 * 
	 * @return JLabel correspondiente
	 */
	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	/**
	 * Modifica la etiqueta de mensajes.
	 * 
	 * @param lblMensaje Nueva etiqueta
	 */
	public void setLblMensaje(JLabel lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	/**
	 * Modifica el campo de consulta de placa.
	 * 
	 * @param txtConsultarPlaca Nuevo campo de texto
	 */
	public void setTxtConsultarPlaca(JTextField txtConsultarPlaca) {
		this.txtConsultarPlaca = txtConsultarPlaca;
	}

	/**
	 * Modifica el boton de consulta de placa.
	 * 
	 * @param btnConsultarPlaca Nuevo boton
	 */
	public void setBtnConsultarPlaca(JButton btnConsultarPlaca) {
		this.btnConsultarPlaca = btnConsultarPlaca;
	}

}