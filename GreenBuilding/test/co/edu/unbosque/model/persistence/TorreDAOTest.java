package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.TorreDTO;

public class TorreDAOTest {

	static TorreDAO dao;
	static String ARCHIVO_ORIGINAL = "torres.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new TorreDAO();
		System.out.println("Iniciando pruebas de TorreDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new TorreDAO();
		dao.getListaTorres().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");
		dao.crear(dto);
		assertTrue(dao.getListaTorres().size() == 1 && dao.getListaTorres().get(0).getId().equals("TOR001"));
	}

	@Test
	public void crearVariosDatos() {
		TorreDTO dto1 = new TorreDTO();
		dto1.setId("TOR001");
		dto1.setNombre("Torre A");

		TorreDTO dto2 = new TorreDTO();
		dto2.setId("TOR002");
		dto2.setNombre("Torre B");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaTorres().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaTorres().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");

		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");

		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");

		dao.crear(dto);

		TorreDTO actualizado = new TorreDTO();
		actualizado.setId("TOR001");
		actualizado.setNombre("Torre Central");

		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaTorres().get(0).getNombre().equals("Torre Central"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		TorreDTO dto = new TorreDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		TorreDTO dto = new TorreDTO();
		dto.setId("TOR001");
		dto.setNombre("Torre A");

		dao.crear(dto);
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
		System.err.println("Finalizando todas las pruebas de TorreDAO");
	}
}