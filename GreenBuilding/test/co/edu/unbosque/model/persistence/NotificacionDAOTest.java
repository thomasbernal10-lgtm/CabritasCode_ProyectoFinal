package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.NotificacionDTO;

public class NotificacionDAOTest {

	static NotificacionDAO dao;
	static String ARCHIVO_ORIGINAL = "notificaciones.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new NotificacionDAO();
		System.out.println("Iniciando pruebas de NotificacionDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new NotificacionDAO();
		dao.getListaNotificaciones().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		NotificacionDTO dto = new NotificacionDTO("NOT001", "residente@mail.com", "Reunion mensual",
				"Se convoca a reunion el viernes", "INFO", LocalDateTime.now());
		dao.crear(dto);
		assertTrue(dao.getListaNotificaciones().size() == 1
				&& dao.getListaNotificaciones().get(0).getId().equals("NOT001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		dao.crear(new NotificacionDTO("NOT002", "r2@mail.com", "Asunto2", "Cuerpo2", "ALERTA", LocalDateTime.now()));
		assertTrue(dao.getListaNotificaciones().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaNotificaciones().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		NotificacionDTO actualizado = new NotificacionDTO("NOT001", "r1@mail.com", "AsuntoActualizado",
				"CuerpoActualizado", "INFO", LocalDateTime.now());
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaNotificaciones().get(0).getAsunto().equals("AsuntoActualizado"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new NotificacionDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new NotificacionDTO("NOT001", "r1@mail.com", "Asunto1", "Cuerpo1", "INFO", LocalDateTime.now()));
		String resultado = dao.mostrar();
		assertTrue(resultado != null && !resultado.isEmpty());
	}

	@Test
	public void mostrarTodoNoNulo() {
		assertTrue(dao.mostrarTodo() != null);
	}

	@After
	public void despuesDeCadaUna() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, backup != null ? backup : new ArrayList<>());
		System.err.println("Finalizando todas las pruebas de NotificacionDAO");
	}
}