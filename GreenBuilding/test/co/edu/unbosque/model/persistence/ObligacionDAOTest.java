package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ObligacionDTO;

public class ObligacionDAOTest {

	static ObligacionDAO dao;
	static String ARCHIVO_ORIGINAL = "obligaciones.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ObligacionDAO();
		System.out.println("Iniciando pruebas de ObligacionDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ObligacionDAO();
		dao.getListaObligaciones().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ObligacionDTO dto = new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema");
		dao.crear(dto);
		assertTrue(
				dao.getListaObligaciones().size() == 1 && dao.getListaObligaciones().get(0).getId().equals("OBL001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
		dao.crear(new ObligacionDTO("OBL002", null, "MULTA", 50000.0, LocalDate.now(), LocalDate.now().plusDays(15),
				"PENDIENTE", "Ruido", "Admin"));
		assertTrue(dao.getListaObligaciones().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaObligaciones().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
		ObligacionDTO actualizado = new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PAGADA", "Cuota mensual", "Sistema");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaObligaciones().get(0).getEstado().equals("PAGADA"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new ObligacionDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ObligacionDTO("OBL001", null, "CUOTA_ADMINISTRACION", 250000.0, LocalDate.now(),
				LocalDate.now().plusDays(30), "PENDIENTE", "Cuota mensual", "Sistema"));
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
		System.err.println("Finalizando todas las pruebas de ObligacionDAO");
	}
}