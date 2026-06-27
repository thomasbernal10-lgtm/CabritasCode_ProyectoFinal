package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import co.edu.unbosque.model.Conjunto;

/**
 * Panel grafico correspondiente a la pestana de administracion de
 * administradores del sistema. Permite registrar nuevos administradores,
 * bloquear o desbloquear usuarios administradores y visualizar la lista de
 * administradores registrados.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaAdministrador extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = -4075759118194375632L;

	/**
	 * Campo de texto para ingresar el nombre del administrador.
	 */
	private JTextField txtNombre;

	/**
	 * Campo de texto para ingresar la cedula del administrador.
	 */
	private JTextField txtCedula;

	/**
	 * Campo de texto para ingresar el correo del administrador.
	 */
	private JTextField txtCorreo;

	/**
	 * Campo de texto para ingresar el telefono del administrador.
	 */
	private JTextField txtTelefono;

	/**
	 * Boton utilizado para crear un nuevo administrador.
	 */
	private JButton btnCrear;

	/**
	 * Boton utilizado para limpiar los campos del formulario.
	 */
	private JButton btnLimpiar;

	/**
	 * Etiqueta utilizada para mostrar mensajes del formulario.
	 */
	private JLabel lblMensaje;

	/**
	 * Area de texto utilizada para visualizar la lista de administradores.
	 */
	private JTextArea areaLista;

	/**
	 * Campo de texto para ingresar el username del administrador sobre el que se
	 * realizara una accion.
	 */
	private JTextField txtUsernameAccion;

	/**
	 * Boton utilizado para bloquear administradores.
	 */
	private JButton btnBloquear;

	/**
	 * Boton utilizado para desbloquear administradores.
	 */
	private JButton btnDesbloquear;

	/**
	 * Boton utilizado para refrescar la lista de administradores.
	 */
	private JButton btnRefrescar;

	/**
	 * Etiqueta utilizada para mostrar mensajes relacionados con acciones de bloqueo
	 * o desbloqueo.
	 */
	private JLabel lblMensajeAccion;

	/**
	 * Lista desplegable de conjuntos registrados.
	 */
	private JComboBox<String> comboConjunto;

	/**
	 * Lista de IDs de conjuntos disponibles.
	 */
	private ArrayList<String> idsConjunto;

	/**
	 * Color principal del panel.
	 */
	private Color colorPanel;

	/**
	 * Color verde oscuro utilizado en la interfaz.
	 */
	private Color colorVerdeOscuro;

	/**
	 * Color verde utilizado en la interfaz.
	 */
	private Color colorVerde;

	/**
	 * Color utilizado para textos suaves.
	 */
	private Color colorTextoSuave;

	/**
	 * Color de fondo de los campos de texto.
	 */
	private Color colorCampoFondo;

	/**
	 * Color de borde de los campos de texto.
	 */
	private Color colorCampoBorde;

	/**
	 * Color de los bordes generales.
	 */
	private Color colorBorde;

	/**
	 * Color blanco utilizado en componentes graficos.
	 */
	private Color colorBlanco;

	/**
	 * Color rojo utilizado para mensajes de error.
	 */
	private Color colorRojo;

	/**
	 * Construye la pestana de administradores inicializando colores y componentes
	 * graficos.
	 * 
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> La interfaz grafica queda construida e inicializada.
	 * 
	 * @param colorPanel       Color principal del panel
	 * @param colorVerdeOscuro Color verde oscuro
	 * @param colorVerde       Color verde
	 * @param colorTextoSuave  Color para textos suaves
	 * @param colorCampoFondo  Color de fondo de campos
	 * @param colorCampoBorde  Color de borde de campos
	 * @param colorBorde       Color de bordes generales
	 * @param colorBlanco      Color blanco
	 * @param colorRojo        Color rojo para errores
	 */
	public PestanaAdministrador(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
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
		this.idsConjunto = new ArrayList<>();
		construir();
	}

	/**
	 * Construye la estructura principal de la interfaz grafica.
	 * 
	 * <b>pre</b> Los componentes graficos deben estar inicializados. <br>
	 * <b>post</b> El panel principal queda configurado con sus subpaneles.
	 */
	public void construir() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(colorPanel);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(crearPanelIzquierda());
		add(Box.createHorizontalStrut(20));
		add(crearPanelLista());
	}

	/**
	 * Crea el panel izquierdo con formularios y opciones de administracion.
	 * 
	 * <b>pre</b> Los colores y componentes deben estar inicializados. <br>
	 * <b>post</b> Se retorna un panel completamente configurado.
	 * 
	 * @return JPanel con el formulario y opciones de bloqueo/desbloqueo
	 */
	public JPanel crearPanelIzquierda() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
		panel.setPreferredSize(new Dimension(340, 0));

		JPanel formulario = new JPanel();
		formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
		formulario.setBackground(colorPanel);
		formulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), "Nuevo Administrador", 0,
						0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));
		txtNombre = crearCampo();
		txtCedula = crearCampo();
		txtCorreo = crearCampo();
		txtTelefono = crearCampo();
		comboConjunto = new JComboBox<>();
		comboConjunto.setFont(new Font("SansSerif", Font.PLAIN, 13));
		comboConjunto.setBackground(colorCampoFondo);
		comboConjunto.setForeground(colorVerdeOscuro);
		comboConjunto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		comboConjunto.setMaximumRowCount(15);
		comboConjunto.setAlignmentX(LEFT_ALIGNMENT);

		JLabel lblConjunto = new JLabel("Conjunto:");
		lblConjunto.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblConjunto.setForeground(colorTextoSuave);
		lblConjunto.setAlignmentX(LEFT_ALIGNMENT);
		formulario.add(lblConjunto);
		formulario.add(Box.createVerticalStrut(4));
		formulario.add(comboConjunto);
		formulario.add(Box.createVerticalStrut(10));

		agregarCampo(formulario, "Nombre completo:", txtNombre);
		agregarCampo(formulario, "Cedula:", txtCedula);
		agregarCampo(formulario, "Correo:", txtCorreo);
		agregarCampo(formulario, "Telefono:", txtTelefono);

		JLabel lblNota = new JLabel("* Usuario y contraseña se generan automaticamente");
		lblNota.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblNota.setForeground(colorTextoSuave);
		lblNota.setAlignmentX(LEFT_ALIGNMENT);

		formulario.add(lblNota);
		formulario.add(Box.createVerticalStrut(12));

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setOpaque(false);
		panelBotones.setAlignmentX(LEFT_ALIGNMENT);

		btnCrear = crearBoton("Crear Admin", colorVerde, colorBlanco);
		btnLimpiar = crearBoton("Limpiar", colorCampoFondo, colorVerdeOscuro);

		panelBotones.add(btnCrear);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiar);

		formulario.add(panelBotones);
		formulario.add(Box.createVerticalStrut(8));

		lblMensaje = new JLabel(" ");
		lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensaje.setForeground(colorRojo);
		lblMensaje.setAlignmentX(LEFT_ALIGNMENT);

		formulario.add(lblMensaje);

		JPanel panelBloqueo = new JPanel();
		panelBloqueo.setLayout(new BoxLayout(panelBloqueo, BoxLayout.Y_AXIS));
		panelBloqueo.setBackground(colorPanel);

		panelBloqueo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Bloquear / Desbloquear admin", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		txtUsernameAccion = crearCampo();
		agregarCampo(panelBloqueo, "Username:", txtUsernameAccion);

		JPanel panelBotBloqueo = new JPanel();
		panelBotBloqueo.setLayout(new BoxLayout(panelBotBloqueo, BoxLayout.X_AXIS));
		panelBotBloqueo.setOpaque(false);
		panelBotBloqueo.setAlignmentX(LEFT_ALIGNMENT);

		btnBloquear = crearBoton("Bloquear", colorRojo, colorBlanco);
		btnDesbloquear = crearBoton("Desbloquear", colorVerde, colorBlanco);

		panelBotBloqueo.add(btnBloquear);
		panelBotBloqueo.add(Box.createHorizontalStrut(10));
		panelBotBloqueo.add(btnDesbloquear);
		panelBloqueo.add(panelBotBloqueo);
		panelBloqueo.add(Box.createVerticalStrut(8));

		lblMensajeAccion = new JLabel(" ");
		lblMensajeAccion.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblMensajeAccion.setForeground(colorRojo);
		lblMensajeAccion.setAlignmentX(LEFT_ALIGNMENT);

		panelBloqueo.add(lblMensajeAccion);
		panel.add(formulario);
		panel.add(Box.createVerticalStrut(15));
		panel.add(panelBloqueo);
		panel.add(Box.createVerticalGlue());
		return panel;
	}

	/**
	 * Crea el panel encargado de mostrar la lista de administradores registrados.
	 * 
	 * <b>pre</b> Los componentes graficos deben estar inicializados. <br>
	 * <b>post</b> Se retorna el panel configurado con el area de lista.
	 * 
	 * @return JPanel correspondiente al panel de lista
	 */
	public JPanel crearPanelLista() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde),
						"Administradores registrados", 0, 0, new Font("SansSerif", Font.BOLD, 12), colorVerdeOscuro),
				BorderFactory.createEmptyBorder(10, 15, 15, 15)));

		areaLista = new JTextArea();
		areaLista.setEditable(false);
		areaLista.setFont(new Font("Monospaced", Font.PLAIN, 12));
		areaLista.setBackground(colorCampoFondo);
		areaLista.setBorder(BorderFactory.createLineBorder(colorCampoBorde));
		JScrollPane scroll = new JScrollPane(areaLista);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelRef = new JPanel();
		panelRef.setLayout(new BoxLayout(panelRef, BoxLayout.X_AXIS));
		panelRef.setOpaque(false);
		panelRef.setAlignmentX(LEFT_ALIGNMENT);
		btnRefrescar = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelRef.add(btnRefrescar);
		panel.add(panelRef);
		return panel;
	}

	/**
	 * Agrega un campo de texto con su etiqueta correspondiente a un panel.
	 * 
	 * <b>pre</b> El panel y el campo no deben ser null. <br>
	 * <b>post</b> El campo queda agregado al panel.
	 * 
	 * @param panel    Panel donde se agregara el campo
	 * @param etiqueta Texto de la etiqueta
	 * @param campo    Campo de texto a agregar
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
	 * Crea y configura un campo de texto personalizado.
	 * 
	 * <b>pre</b> Los colores deben estar inicializados. <br>
	 * <b>post</b> Se retorna el campo de texto configurado.
	 * 
	 * @return JTextField configurado visualmente
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
	 * Limpia los campos del formulario de creacion.
	 * 
	 * <b>pre</b> Los campos deben estar inicializados. <br>
	 * <b>post</b> Los campos quedan vacios y el mensaje reiniciado.
	 */
	public void limpiar() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
		lblMensaje.setText(" ");
	}

	/**
	 * Establece un mensaje informativo o de error en el formulario principal.
	 * 
	 * <b>pre</b> El mensaje no debe ser null. <br>
	 * <b>post</b> El mensaje queda actualizado visualmente.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si el mensaje representa un error
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
	 * Establece un mensaje relacionado con acciones de bloqueo o desbloqueo.
	 * 
	 * <b>pre</b> El mensaje no debe ser null. <br>
	 * <b>post</b> El mensaje queda actualizado visualmente.
	 * 
	 * @param msg     Mensaje a mostrar
	 * @param esError true si el mensaje representa un error
	 */
	public void setMensajeAccion(String msg, boolean esError) {
		if (esError) {
			lblMensajeAccion.setForeground(colorRojo);
		} else {
			lblMensajeAccion.setForeground(colorVerdeOscuro);
		}

		lblMensajeAccion.setText(msg);
	}

	/**
	 * Actualiza la lista desplegable de conjuntos registrados.
	 * 
	 * <b>pre</b> La lista de conjuntos no debe ser null. <br>
	 * <b>post</b> El combo de conjuntos queda actualizado.
	 * 
	 * @param conjuntos Lista de conjuntos disponibles
	 */
	public void actualizarConjuntos(ArrayList<Conjunto> conjuntos) {
		comboConjunto.removeAllItems();
		idsConjunto.clear();
		comboConjunto.addItem("Seleccione Conjunto");
		idsConjunto.add("");
		for (Conjunto c : conjuntos) {
			comboConjunto.addItem(c.getNombre() + " (" + c.getId() + ")");
			idsConjunto.add(c.getId());
		}
		comboConjunto.setSelectedIndex(0);
	}

	/**
	 * Obtiene el ID del conjunto seleccionado actualmente.
	 * 
	 * <b>pre</b> El combo de conjuntos debe estar inicializado. <br>
	 * <b>post</b> No se modifica ningun dato.
	 * 
	 * @return String con el ID del conjunto seleccionado o cadena vacia si no hay
	 *         seleccion valida
	 */
	public String getIdConjuntoSeleccionado() {
		int idx = comboConjunto.getSelectedIndex();
		if (idx >= 0 && idx < idsConjunto.size()) {
			String id = idsConjunto.get(idx);
			if (id != null && !id.isEmpty()) {
				return id;
			}
		}

		Object selected = comboConjunto.getSelectedItem();
		if (selected != null) {
			String text = selected.toString();
			int open = text.lastIndexOf('(');
			int close = text.lastIndexOf(')');
			if (open >= 0 && close > open) {
				return text.substring(open + 1, close).trim();
			}
		}
		return "";
	}

	/**
	 * Retorna el campo de texto del nombre.
	 * 
	 * @return JTextField correspondiente al nombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * Retorna el campo de texto de la cedula.
	 * 
	 * @return JTextField correspondiente a la cedula
	 */
	public JTextField getTxtCedula() {
		return txtCedula;
	}

	/**
	 * Retorna el campo de texto del correo.
	 * 
	 * @return JTextField correspondiente al correo
	 */
	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	/**
	 * Retorna el campo de texto del telefono.
	 * 
	 * @return JTextField correspondiente al telefono
	 */
	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	/**
	 * Retorna el boton de crear administrador.
	 * 
	 * @return JButton correspondiente al boton crear
	 */
	public JButton getBtnCrear() {
		return btnCrear;
	}

	/**
	 * Retorna el boton de limpiar formulario.
	 * 
	 * @return JButton correspondiente al boton limpiar
	 */
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	/**
	 * Retorna el area de texto de la lista de administradores.
	 * 
	 * @return JTextArea correspondiente a la lista
	 */
	public JTextArea getAreaLista() {
		return areaLista;
	}

	/**
	 * Retorna el campo de texto del username para acciones.
	 * 
	 * @return JTextField correspondiente al username
	 */
	public JTextField getTxtUsernameAccion() {
		return txtUsernameAccion;
	}

	/**
	 * Retorna el boton de bloqueo.
	 * 
	 * @return JButton correspondiente al boton bloquear
	 */
	public JButton getBtnBloquear() {
		return btnBloquear;
	}

	/**
	 * Retorna el boton de desbloqueo.
	 * 
	 * @return JButton correspondiente al boton desbloquear
	 */
	public JButton getBtnDesbloquear() {
		return btnDesbloquear;
	}

	/**
	 * Retorna el boton de refrescar lista.
	 * 
	 * @return JButton correspondiente al boton refrescar
	 */
	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}
}