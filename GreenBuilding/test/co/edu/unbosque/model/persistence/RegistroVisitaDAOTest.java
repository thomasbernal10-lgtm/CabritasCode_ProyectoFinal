package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.RegistroVisitaDTO;

public class RegistroVisitaDAOTest {

	static RegistroVisitaDAO dao;
	static String ARCHIVO_ORIGINAL = "registrosvisita.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new RegistroVisitaDAO();
		System.out.println("Iniciando pruebas de RegistroVisitaDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new RegistroVisitaDAO();
		dao.getListaRegistros().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		RegistroVisitaDTO dto = new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1");
		dao.crear(dto);
		assertTrue(dao.getListaRegistros().size() == 1 && dao.getListaRegistros().get(0).getId().equals("RV001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
		dao.crear(new RegistroVisitaDTO("RV002", null, LocalDateTime.now(), LocalDateTime.now().plusHours(2),
				"Vigilante2"));
		assertTrue(dao.getListaRegistros().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaRegistros().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
		RegistroVisitaDTO actualizado = new RegistroVisitaDTO("RV001", null, LocalDateTime.now(),
				LocalDateTime.now().plusHours(1), "Vigilante1");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado);
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new RegistroVisitaDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new RegistroVisitaDTO("RV001", null, LocalDateTime.now(), null, "Vigilante1"));
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
		System.err.println("Finalizando todas las pruebas de RegistroVisitaDAO");
	}
}