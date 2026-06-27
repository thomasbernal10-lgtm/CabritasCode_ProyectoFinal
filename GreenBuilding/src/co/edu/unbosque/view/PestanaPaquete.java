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
 * Panel grafico encargado de gestionar paquetes recibidos y entregados dentro
 * del conjunto residencial.
 * 
 * Permite registrar paquetes, marcar entregas, devolver paquetes y visualizar
 * listas de paquetes pendientes y registrados.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaPaquete extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 6749980024679927165L;

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
	 * Campo de texto para ingresar la descripcion del paquete.
	 */
	private JTextField txtDescripcion;

	/**
	 * Campo de texto para ingresar el remitente del paquete.
	 */
	private JTextField txtRemitente;

	/**
	 * Campo de texto para ingresar el numero de apartamento destino.
	 */
	private JTextField txtNumeroApartamento;

	/**
	 * Boton utilizado para registrar paquetes.
	 */
	private JButton btnRegistrarPaquete;

	/**
	 * Boton utilizado para limpiar el formulario de registro.
	 */
	private JButton btnLimpiarRegistro;

	/**
	 * Etiqueta utilizada para mostrar mensajes del registro.
	 */
	private JLabel lblMensajeRegistro;

	/**
	 * Campo de texto para ingresar el apartamento de entrega.
	 */
	private JTextField txtNumeroAptoEntrega;

	/**
	 * Campo de texto para ingresar la persona que recibio el paquete.
	 */
	private JTextField txtPersonaRecibio;

	/**
	 * Boton utilizado para marcar paquetes entregados.
	 */
	private JButton btnMarcarEntregado;

	/**
	 * Boton utilizado para devolver paquetes.
	 */
	private JButton btnDevolver;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con entregas.
	 */
	private JLabel lblMensajeEntrega;

	/**
	 * Area de texto utilizada para mostrar paquetes pendientes.
	 */
	private JTextArea areaPaquetesPendiente;

	/**
	 * Area de texto utilizada para mostrar todos los paquetes.
	 */
	private JTextArea areaTodosPaquete;

	/**
	 * Boton utilizado para refrescar las listas.
	 */
	private JButton btnRefrescar;

	/**
	 * Construye la pestana de gestion de paquetes.
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
	public PestanaPaquete(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JPanel panelIzq = new JPanel();
		panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.Y_AXIS));
		panelIzq.setBackground(colorPanel);
		panelIzq.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
		panelIzq.setPreferredSize(new Dimension(320, 0));

		JPanel formularioRegistro = new JPanel();
		formularioRegistro.setLayout(new BoxLayout(formularioRegistro, BoxLayout.Y_AXIS));
		formularioRegistro.setBackground(colorPanel);
		formularioRegistro.setAlignmentX(LEFT_ALIGNMENT);
		formularioRegistro.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Registrar paquete recibido", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtDescripcion = crearCampo();
		txtRemitente = crearCampo();
		txtNumeroApartamento = crearCampo();
		agregarCampo(formularioRegistro, "Descripcion:", txtDescripcion);
		agregarCampo(formularioRegistro, "Remitente:", txtRemitente);
		agregarCampo(formularioRegistro, "Numero apartamento destino:", txtNumeroApartamento);
		JPanel botonesRegistro = crearPanelBotones();
		btnRegistrarPaquete = crearBoton("Registrar Paquete", colorVerde, colorBlanco);
		btnLimpiarRegistro = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);
		botonesRegistro.add(btnRegistrarPaquete);
		botonesRegistro.add(Box.createHorizontalStrut(8));
		botonesRegistro.add(btnLimpiarRegistro);
		formularioRegistro.add(botonesRegistro);
		formularioRegistro.add(Box.createVerticalStrut(8));
		lblMensajeRegistro = crearLabelMensaje();
		formularioRegistro.add(lblMensajeRegistro);
		JPanel formularioEntrega = new JPanel();
		formularioEntrega.setLayout(new BoxLayout(formularioEntrega, BoxLayout.Y_AXIS));
		formularioEntrega.setBackground(colorPanel);
		formularioEntrega.setAlignmentX(LEFT_ALIGNMENT);
		formularioEntrega.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Entregar / Devolver paquete", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtNumeroAptoEntrega = crearCampo();
		txtPersonaRecibio = crearCampo();
		agregarCampo(formularioEntrega, "Indice del paquete:", txtNumeroAptoEntrega);
		agregarCampo(formularioEntrega, "Persona que recibio:", txtPersonaRecibio);
		JPanel botonesEntrega = crearPanelBotones();
		btnMarcarEntregado = crearBoton("Marcar Entregado", colorVerde, colorBlanco);
		btnDevolver = crearBoton("Devolver", colorRojo, colorBlanco);
		botonesEntrega.add(btnMarcarEntregado);
		botonesEntrega.add(Box.createHorizontalStrut(8));
		botonesEntrega.add(btnDevolver);
		formularioEntrega.add(botonesEntrega);
		formularioEntrega.add(Box.createVerticalStrut(8));
		lblMensajeEntrega = crearLabelMensaje();
		formularioEntrega.add(lblMensajeEntrega);
		panelIzq.add(formularioRegistro);
		panelIzq.add(Box.createVerticalStrut(15));
		panelIzq.add(formularioEntrega);
		panelIzq.add(Box.createVerticalGlue());
		JPanel panelDer = new JPanel();
		panelDer.setLayout(new BoxLayout(panelDer, BoxLayout.Y_AXIS));
		panelDer.setBackground(colorPanel);
		JPanel panelPendientes = new JPanel();
		panelPendientes.setLayout(new BoxLayout(panelPendientes, BoxLayout.Y_AXIS));
		panelPendientes.setBackground(colorPanel);
		panelPendientes.setAlignmentX(LEFT_ALIGNMENT);
		panelPendientes.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Paquetes pendientes de entrega", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		areaPaquetesPendiente = new JTextArea();
		areaPaquetesPendiente.setEditable(false);
		areaPaquetesPendiente.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaPaquetesPendiente.setBackground(colorCampoFondo);
		areaPaquetesPendiente.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollPendientes = new JScrollPane(areaPaquetesPendiente);
		scrollPendientes.setAlignmentX(LEFT_ALIGNMENT);
		panelPendientes.add(scrollPendientes);
		JPanel panelTodos = new JPanel();
		panelTodos.setLayout(new BoxLayout(panelTodos, BoxLayout.Y_AXIS));
		panelTodos.setBackground(colorPanel);
		panelTodos.setAlignmentX(LEFT_ALIGNMENT);
		panelTodos.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Todos los paquetes", 0, 0,
						new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		areaTodosPaquete = new JTextArea();
		areaTodosPaquete.setEditable(false);
		areaTodosPaquete.setFont(new Font("Monospaced", Font.PLAIN, 11));
		areaTodosPaquete.setBackground(colorCampoFondo);
		areaTodosPaquete.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scrollTodos = new JScrollPane(areaTodosPaquete);
		scrollTodos.setAlignmentX(LEFT_ALIGNMENT);
		panelTodos.add(scrollTodos);

		JPanel panelBotonRef = crearPanelBotones();
		btnRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBotonRef.add(btnRefrescar);
		panelDer.add(panelPendientes);
		panelDer.add(Box.createVerticalStrut(10));
		panelDer.add(panelTodos);
		panelDer.add(Box.createVerticalStrut(8));
		panelDer.add(panelBotonRef);
		add(panelIzq);
		add(Box.createHorizontalStrut(20));
		add(panelDer);
	}

	/**
	 * Agrega un campo de texto con su etiqueta correspondiente.
	 * 
	 * @param panel    Panel donde se agregara el campo
	 * @param etiqueta Texto de la etiqueta
	 * @param campo    Campo de texto
	 */
	public void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(4));
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		campo.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(campo);
		panel.add(Box.createVerticalStrut(10));
	}

	/**
	 * Crea un panel horizontal para organizar botones.
	 * 
	 * @return JPanel configurado para botones
	 */
	public JPanel crearPanelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
	}

	/**
	 * Crea y configura un campo de texto personalizado.
	 * 
	 * @return JTextField configurado
	 */
	public JTextField crearCampo() {
		JTextField tf = new JTextField();
		tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tf.setBackground(colorCampoFondo);
		tf.setForeground(colorVerdeOscuro);
		tf.setCaretColor(colorVerde);
		tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorCampoBorde, 1),
				BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		return tf;
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
	 * Crea una etiqueta utilizada para mensajes informativos.
	 * 
	 * @return JLabel configurado
	 */
	public JLabel crearLabelMensaje() {
		JLabel lbl = new JLabel(" ");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lbl.setForeground(colorRojo);
		lbl.setAlignmentX(LEFT_ALIGNMENT);

		return lbl;
	}

	/**
	 * Establece un mensaje relacionado con el registro de paquetes.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeRegistro(String msg, boolean esError) {
		if (esError) {
			lblMensajeRegistro.setForeground(colorRojo);
		} else {
			lblMensajeRegistro.setForeground(colorVerdeOscuro);
		}

		lblMensajeRegistro.setText(msg);
	}

	/**
	 * Establece un mensaje relacionado con entregas de paquetes.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si representa un error
	 */
	public void setMensajeEntrega(String msg, boolean esError) {
		if (esError) {
			lblMensajeEntrega.setForeground(colorRojo);
		} else {
			lblMensajeEntrega.setForeground(colorVerdeOscuro);
		}

		lblMensajeEntrega.setText(msg);
	}

	/**
	 * Limpia el formulario de registro de paquetes.
	 */
	public void limpiarRegistro() {
		txtDescripcion.setText("");
		txtRemitente.setText("");
		txtNumeroApartamento.setText("");
		lblMensajeRegistro.setText(" ");
	}

	/**
	 * Limpia el formulario de entrega de paquetes.
	 */
	public void limpiarEntrega() {
		txtNumeroAptoEntrega.setText("");
		txtPersonaRecibio.setText("");
		lblMensajeEntrega.setText(" ");
	}

	/**
	 * Retorna el campo de descripcion del paquete.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	/**
	 * Retorna el campo del remitente.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtRemitente() {
		return txtRemitente;
	}

	/**
	 * Retorna el campo del numero de apartamento.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtNumeroApartamento() {
		return txtNumeroApartamento;
	}

	/**
	 * Retorna el campo del apartamento de entrega.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtIndexEntrega() {
		return txtNumeroAptoEntrega;
	}

	/**
	 * Retorna el campo de la persona que recibio.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtPersonaRecibio() {
		return txtPersonaRecibio;
	}

	/**
	 * Retorna el boton de registrar paquete.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRegistrarPaquete() {
		return btnRegistrarPaquete;
	}

	/**
	 * Retorna el boton de limpiar registro.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnLimpiarRegistro() {
		return btnLimpiarRegistro;
	}

	/**
	 * Retorna el boton de marcar entregado.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnMarcarEntregado() {
		return btnMarcarEntregado;
	}

	/**
	 * Retorna el boton de devolver paquete.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnDevolver() {
		return btnDevolver;
	}

	/**
	 * Retorna el boton de refrescar listas.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	/**
	 * Retorna el area de paquetes pendientes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaPaquetesPendientes() {
		return areaPaquetesPendiente;
	}

	/**
	 * Retorna el area de todos los paquetes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaTodosPaquetes() {
		return areaTodosPaquete;
	}

	/**
	 * Retorna el campo del numero de apartamento de entrega.
	 * 
	 * @return JTextField correspondiente
	 */
	public JTextField getTxtNumeroAptoEntrega() {
		return txtNumeroAptoEntrega;
	}

	/**
	 * Modifica el campo del numero de apartamento de entrega.
	 * 
	 * @param txtNumeroAptoEntrega Nuevo campo
	 */
	public void setTxtNumeroAptoEntrega(JTextField txtNumeroAptoEntrega) {
		this.txtNumeroAptoEntrega = txtNumeroAptoEntrega;
	}

	/**
	 * Modifica el campo del numero de apartamento.
	 * 
	 * @param txtNumeroApartamento Nuevo campo
	 */
	public void setTxtNumeroApartamento(JTextField txtNumeroApartamento) {
		this.txtNumeroApartamento = txtNumeroApartamento;
	}
}