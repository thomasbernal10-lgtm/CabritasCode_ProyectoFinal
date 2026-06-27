package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * Panel grafico encargado de mostrar la informacion relacionada con el
 * apartamento del residente.
 * 
 * Permite visualizar visitantes autorizados, paquetes pendientes, residentes,
 * vehiculos y mascotas registradas.
 * 
 * @author GreenBuilding
 * @version 1.0
 */
public class PestanaVisitantesResidente extends JPanel {

	/**
	 * Identificador de serializacion de la clase.
	 */
	private static final long serialVersionUID = 5029384710293847102L;

	/**
	 * Area de texto utilizada para mostrar los visitantes registrados.
	 */
	private JTextArea areaMisVisitante;

	/**
	 * Boton utilizado para refrescar la lista de visitantes.
	 */
	private JButton btnRefrescarVisitante;

	/**
	 * Area de texto utilizada para mostrar los paquetes pendientes.
	 */
	private JTextArea areaPaquete;

	/**
	 * Boton utilizado para refrescar la lista de paquetes.
	 */
	private JButton btnRefrescarPaquete;

	/**
	 * Area de texto utilizada para mostrar los residentes registrados.
	 */
	private JTextArea areaResidente;

	/**
	 * Boton utilizado para refrescar la lista de residentes.
	 */
	private JButton btnRefrescarResidente;

	/**
	 * Area de texto utilizada para mostrar los vehiculos registrados.
	 */
	private JTextArea areaVehiculo;

	/**
	 * Boton utilizado para refrescar la lista de vehiculos.
	 */
	private JButton btnRefrescarVehiculo;

	/**
	 * Area de texto utilizada para mostrar las mascotas registradas.
	 */
	private JTextArea areaMascota;

	/**
	 * Boton utilizado para refrescar la lista de mascotas.
	 */
	private JButton btnRefrescarMascota;

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
	 * Color de fondo de las areas de texto.
	 */
	private Color colorCampoFondo;

	/**
	 * Color utilizado para bordes generales.
	 */
	private Color colorBorde;

	/**
	 * Color blanco utilizado en componentes graficos.
	 */
	private Color colorBlanco;

	/**
	 * Construye la pestana de visitantes y apartamento.
	 * 
	 * <b>pre</b> Los colores recibidos no deben ser null. <br>
	 * <b>post</b> El panel queda completamente inicializado.
	 * 
	 * @param colorPanel       Color principal del panel
	 * @param colorVerdeOscuro Color verde oscuro
	 * @param colorVerde       Color verde principal
	 * @param colorTextoSuave  Color para textos suaves
	 * @param colorCampoFondo  Color de fondo de areas
	 * @param colorBorde       Color de bordes
	 * @param colorBlanco      Color blanco
	 */
	public PestanaVisitantesResidente(Color colorPanel, Color colorVerdeOscuro, Color colorVerde, Color colorTextoSuave,
			Color colorCampoFondo, Color colorBorde, Color colorBlanco) {
		this.colorPanel = colorPanel;
		this.colorVerdeOscuro = colorVerdeOscuro;
		this.colorVerde = colorVerde;
		this.colorTextoSuave = colorTextoSuave;
		this.colorCampoFondo = colorCampoFondo;
		this.colorBorde = colorBorde;
		this.colorBlanco = colorBlanco;
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
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tabs.setBackground(colorPanel);
		tabs.addTab("Mis Visitantes", crearSubPanelVisitantes());
		tabs.addTab("Paquetes Pendientes", crearSubPanelPaquetes());
		tabs.addTab("Residentes", crearSubPanelResidentes());
		tabs.addTab("Vehiculos", crearSubPanelVehiculos());
		tabs.addTab("Mascotas", crearSubPanelMascotas());
		add(tabs);
	}

	/**
	 * Crea el subpanel correspondiente a visitantes.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de visitantes
	 */
	public JPanel crearSubPanelVisitantes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Visitantes autorizados registrados para su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Historial de Visitantes de mi Apartamento");
		areaMisVisitante = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnRefrescarVisitante = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnRefrescarVisitante);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a paquetes pendientes.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de paquetes
	 */
	public JPanel crearSubPanelPaquetes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Paquetes pendientes de entrega para su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Paquetes Pendientes");
		areaPaquete = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnRefrescarPaquete = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnRefrescarPaquete);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a residentes.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de residentes
	 */
	public JPanel crearSubPanelResidentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Residentes registrados en su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Residentes de mi Apartamento");
		areaResidente = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnRefrescarResidente = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnRefrescarResidente);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a vehiculos.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de vehiculos
	 */
	public JPanel crearSubPanelVehiculos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Vehiculos registrados en su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Vehiculos de mi Apartamento");
		areaVehiculo = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnRefrescarVehiculo = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnRefrescarVehiculo);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea el subpanel correspondiente a mascotas.
	 * 
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @return JPanel correspondiente al modulo de mascotas
	 */
	public JPanel crearSubPanelMascotas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		JLabel lbl = new JLabel("Mascotas registradas en su apartamento.");
		lbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lbl.setForeground(colorTextoSuave);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelLista = crearPanelLista("Mascotas de mi Apartamento");
		areaMascota = (JTextArea) ((JScrollPane) panelLista.getComponent(0)).getViewport().getView();
		panel.add(panelLista);
		panel.add(Box.createVerticalStrut(10));
		JPanel panelBtn = panelBotones();
		btnRefrescarMascota = crearBoton("Refrescar", colorVerde, colorBlanco);
		panelBtn.add(btnRefrescarMascota);
		panel.add(panelBtn);
		return panel;
	}

	/**
	 * Crea un panel generico para visualizar listas.
	 * 
	 * <b>pre</b> El titulo no debe ser null. <br>
	 * <b>post</b> Se retorna el panel configurado.
	 * 
	 * @param titulo Titulo del panel
	 * 
	 * @return JPanel correspondiente al panel de lista
	 */
	public JPanel crearPanelLista(String titulo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorPanel);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		JTextArea area = new JTextArea(10, 55);
		area.setFont(new Font("Monospaced", Font.PLAIN, 11));
		area.setBackground(colorCampoFondo);
		area.setForeground(colorVerdeOscuro);
		area.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(colorBorde), titulo, 0, 0,
				new Font("SansSerif", Font.BOLD, 11), colorVerdeOscuro));
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(scroll);
		return panel;
	}

	/**
	 * Crea un panel horizontal para organizar botones.
	 * 
	 * @return JPanel configurado para botones
	 */
	public JPanel panelBotones() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBackground(colorPanel);
		p.setAlignmentX(LEFT_ALIGNMENT);
		return p;
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
	 * Retorna el area de visitantes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaMisVisitantes() {
		return areaMisVisitante;
	}

	/**
	 * Retorna el boton de refrescar visitantes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarVisitantes() {
		return btnRefrescarVisitante;
	}

	/**
	 * Retorna el area de paquetes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaPaquetes() {
		return areaPaquete;
	}

	/**
	 * Retorna el boton de refrescar paquetes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarPaquetes() {
		return btnRefrescarPaquete;
	}

	/**
	 * Retorna el area de residentes.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaResidentes() {
		return areaResidente;
	}

	/**
	 * Retorna el boton de refrescar residentes.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarResidentes() {
		return btnRefrescarResidente;
	}

	/**
	 * Retorna el area de vehiculos.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaVehiculos() {
		return areaVehiculo;
	}

	/**
	 * Retorna el boton de refrescar vehiculos.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarVehiculos() {
		return btnRefrescarVehiculo;
	}

	/**
	 * Retorna el area de mascotas.
	 * 
	 * @return JTextArea correspondiente
	 */
	public JTextArea getAreaMascotas() {
		return areaMascota;
	}

	/**
	 * Retorna el boton de refrescar mascotas.
	 * 
	 * @return JButton correspondiente
	 */
	public JButton getBtnRefrescarMascotas() {
		return btnRefrescarMascota;
	}
}