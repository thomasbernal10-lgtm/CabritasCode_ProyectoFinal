package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.VisitanteDTO;

public class VisitanteDAOTest {

	static VisitanteDAO dao;
	static String ARCHIVO_ORIGINAL = "visitantes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new VisitanteDAO();
		System.out.println("Iniciando pruebas de VisitanteDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new VisitanteDAO();
		dao.getListaVisitantes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dto.setNombre("Jorge Mora");
		dto.setCedula("777888999");
		dto.setTelefono("3115556677");
		dto.setTipo("FAMILIAR");
		dto.setActivo(true);
		dao.crear(dto);
		assertTrue(dao.getListaVisitantes().size() == 1 && dao.getListaVisitantes().get(0).getId().equals("VIS001"));
	}

	@Test
	public void crearVariosDatos() {
		VisitanteDTO dto1 = new VisitanteDTO();
		dto1.setId("VIS001");
		dto1.setNombre("Jorge Mora");
		dto1.setTipo("FAMILIAR");

		VisitanteDTO dto2 = new VisitanteDTO();
		dto2.setId("VIS002");
		dto2.setNombre("Sara Castro");
		dto2.setTipo("DOMICILIO");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaVisitantes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dto.setNombre("Jorge Mora");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaVisitantes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dto.setNombre("Jorge Mora");
		dto.setTipo("FAMILIAR");
		dao.crear(dto);

		VisitanteDTO actualizado = new VisitanteDTO();
		actualizado.setId("VIS001");
		actualizado.setNombre("Jorge Andres Mora");
		actualizado.setTipo("FAMILIAR");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaVisitantes().get(0).getNombre().equals("Jorge Andres Mora"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		VisitanteDTO dto = new VisitanteDTO();
		dto.setId("VIS001");
		dto.setNombre("Jorge Mora");
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
		System.err.println("Finalizando todas las pruebas de VisitanteDAO");
	}
}