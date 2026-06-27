package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ReservaDTO;

public class ReservaDAOTest {

	static ReservaDAO dao;
	static String ARCHIVO_ORIGINAL = "reservas.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ReservaDAO();
		System.out.println("Iniciando pruebas de ReservaDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ReservaDAO();
		dao.getListaReservas().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ReservaDTO dto = new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null);
		dao.crear(dto);
		assertTrue(dao.getListaReservas().size() == 1 && dao.getListaReservas().get(0).getId().equals("RES001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
		dao.crear(new ReservaDTO("RES002", null, null, LocalDate.now().plusDays(1), LocalTime.of(14, 0),
				LocalTime.of(16, 0), "CONFIRMADA", null, null));
		assertTrue(dao.getListaReservas().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaReservas().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
		ReservaDTO actualizado = new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0),
				LocalTime.of(12, 0), "CONFIRMADA", null, null);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaReservas().get(0).getEstado().equals("CONFIRMADA"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new ReservaDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ReservaDTO("RES001", null, null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
				"PENDIENTE", null, null));
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
		System.err.println("Finalizando todas las pruebas de ReservaDAO");
	}
}