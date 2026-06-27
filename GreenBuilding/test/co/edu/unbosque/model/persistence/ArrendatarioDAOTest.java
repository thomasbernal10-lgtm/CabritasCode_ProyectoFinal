package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ArrendatarioDTO;

public class ArrendatarioDAOTest {

	static ArrendatarioDAO dao;
	static String ARCHIVO_ORIGINAL = "arrendatarios.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ArrendatarioDAO();
		System.out.println("Iniciando pruebas de ArrendatarioDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ArrendatarioDAO();
		dao.getListaArrendatarios().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
		dao.crear(dto);
		assertTrue(
				dao.getListaArrendatarios().size() == 1 && dao.getListaArrendatarios().get(0).getId().equals("ARR001"));
	}

	@Test
	public void crearVariosDatos() {
		ArrendatarioDTO dto1 = new ArrendatarioDTO();
		dto1.setId("ARR001");
		dto1.setNombre("Carlos Perez");
		dto1.setCedula("123456789");

		ArrendatarioDTO dto2 = new ArrendatarioDTO();
		dto2.setId("ARR002");
		dto2.setNombre("Maria Lopez");
		dto2.setCedula("987654321");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaArrendatarios().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaArrendatarios().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
		dao.crear(dto);

		ArrendatarioDTO actualizado = new ArrendatarioDTO();
		actualizado.setId("ARR001");
		actualizado.setNombre("Juan Ramirez");
		actualizado.setCedula("111111111");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaArrendatarios().get(0).getNombre().equals("Juan Ramirez"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		ArrendatarioDTO dto = new ArrendatarioDTO();
		dto.setId("ARR001");
		dto.setNombre("Carlos Perez");
		dto.setCedula("123456789");
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
		System.err.println("Finalizando todas las pruebas de ArrendatarioDAO");
	}
}