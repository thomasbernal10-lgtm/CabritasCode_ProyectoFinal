package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.PaqueteDTO;

public class PaqueteDAOTest {

	static PaqueteDAO dao;
	static String ARCHIVO_ORIGINAL = "paquetes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new PaqueteDAO();
		System.out.println("Iniciando pruebas de PaqueteDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new PaqueteDAO();
		dao.getListaPaquetes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dto.setDescripcion("Caja grande");
		dto.setRemitente("Amazon");
		dto.setEstado("PENDIENTE");
		dao.crear(dto);
		assertTrue(dao.getListaPaquetes().size() == 1 && dao.getListaPaquetes().get(0).getId().equals("PAQ001"));
	}

	@Test
	public void crearVariosDatos() {
		PaqueteDTO dto1 = new PaqueteDTO();
		dto1.setId("PAQ001");
		dto1.setDescripcion("Caja grande");
		dto1.setEstado("PENDIENTE");

		PaqueteDTO dto2 = new PaqueteDTO();
		dto2.setId("PAQ002");
		dto2.setDescripcion("Sobre pequeno");
		dto2.setEstado("ENTREGADO");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaPaquetes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dto.setDescripcion("Caja grande");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaPaquetes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dto.setDescripcion("Caja grande");
		dto.setEstado("PENDIENTE");
		dao.crear(dto);

		PaqueteDTO actualizado = new PaqueteDTO();
		actualizado.setId("PAQ001");
		actualizado.setDescripcion("Caja grande");
		actualizado.setEstado("ENTREGADO");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaPaquetes().get(0).getEstado().equals("ENTREGADO"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		PaqueteDTO dto = new PaqueteDTO();
		dto.setId("PAQ001");
		dto.setDescripcion("Caja grande");
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
		System.err.println("Finalizando todas las pruebas de PaqueteDAO");
	}
}