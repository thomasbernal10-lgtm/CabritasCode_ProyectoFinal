package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.PropietarioDTO;

public class PropietarioDAOTest {

	static PropietarioDAO dao;
	static String ARCHIVO_ORIGINAL = "propietarios.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new PropietarioDAO();
		System.out.println("Iniciando pruebas de PropietarioDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new PropietarioDAO();
		dao.getListaPropietarios().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dto.setNombre("Luis Cardenas");
		dto.setCedula("555111222");
		dto.setEsResidente(true);
		dto.setResponsablePago(true);
		dao.crear(dto);
		assertTrue(
				dao.getListaPropietarios().size() == 1 && dao.getListaPropietarios().get(0).getId().equals("PRO001"));
	}

	@Test
	public void crearVariosDatos() {
		PropietarioDTO dto1 = new PropietarioDTO();
		dto1.setId("PRO001");
		dto1.setNombre("Luis Cardenas");
		dto1.setCedula("555111222");

		PropietarioDTO dto2 = new PropietarioDTO();
		dto2.setId("PRO002");
		dto2.setNombre("Sofia Reyes");
		dto2.setCedula("666222333");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaPropietarios().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dto.setNombre("Luis Cardenas");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaPropietarios().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dto.setNombre("Luis Cardenas");
		dao.crear(dto);

		PropietarioDTO actualizado = new PropietarioDTO();
		actualizado.setId("PRO001");
		actualizado.setNombre("Luis Alberto Cardenas");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaPropietarios().get(0).getNombre().equals("Luis Alberto Cardenas"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		PropietarioDTO dto = new PropietarioDTO();
		dto.setId("PRO001");
		dto.setNombre("Luis Cardenas");
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
		System.err.println("Finalizando todas las pruebas de PropietarioDAO");
	}
}