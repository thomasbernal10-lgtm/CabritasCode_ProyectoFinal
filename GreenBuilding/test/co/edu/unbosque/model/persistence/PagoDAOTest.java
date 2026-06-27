package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.PagoDTO;

public class PagoDAOTest {

	static PagoDAO dao;
	static String ARCHIVO_ORIGINAL = "pagos.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new PagoDAO();
		System.out.println("Iniciando pruebas de PagoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new PagoDAO();
		dao.getListaPagos().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dto.setMonto(250000.0);
		dto.setMedioPago("TRANSFERENCIA");
		dto.setReferencia("REF001");
		dao.crear(dto);
		assertTrue(dao.getListaPagos().size() == 1 && dao.getListaPagos().get(0).getId().equals("PAG001"));
	}

	@Test
	public void crearVariosDatos() {
		PagoDTO dto1 = new PagoDTO();
		dto1.setId("PAG001");
		dto1.setMonto(250000.0);

		PagoDTO dto2 = new PagoDTO();
		dto2.setId("PAG002");
		dto2.setMonto(300000.0);

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaPagos().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dto.setMonto(250000.0);
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaPagos().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dto.setMonto(250000.0);
		dto.setMedioPago("TRANSFERENCIA");
		dao.crear(dto);

		PagoDTO actualizado = new PagoDTO();
		actualizado.setId("PAG001");
		actualizado.setMonto(350000.0);
		actualizado.setMedioPago("EFECTIVO");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaPagos().get(0).getMonto() == 350000.0);
	}

	@Test
	public void actualizarPosicionInvalida() {
		PagoDTO dto = new PagoDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		PagoDTO dto = new PagoDTO();
		dto.setId("PAG001");
		dto.setMonto(250000.0);
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
		System.err.println("Finalizando todas las pruebas de PagoDAO");
	}
}