package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ReporteDTO;

public class ReporteDAOTest {

	static ReporteDAO dao;
	static String ARCHIVO_ORIGINAL = "reportes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ReporteDAO();
		System.out.println("Iniciando pruebas de ReporteDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ReporteDAO();
		dao.getListaReportes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ReporteDTO dto = new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido del informe",
				LocalDateTime.now(), "Admin", null);
		dao.crear(dto);
		assertTrue(dao.getListaReportes().size() == 1 && dao.getListaReportes().get(0).getId().equals("REP001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
		dao.crear(new ReporteDTO("REP002", "MANTENIMIENTO", "Informe Junio", "Contenido2", LocalDateTime.now(), "Admin",
				null));
		assertTrue(dao.getListaReportes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaReportes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
		ReporteDTO actualizado = new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo Actualizado", "Contenido nuevo",
				LocalDateTime.now(), "Admin", null);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaReportes().get(0).getTitulo().equals("Informe Mayo Actualizado"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new ReporteDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ReporteDTO("REP001", "FINANCIERO", "Informe Mayo", "Contenido1", LocalDateTime.now(), "Admin",
				null));
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
		System.err.println("Finalizando todas las pruebas de ReporteDAO");
	}
}