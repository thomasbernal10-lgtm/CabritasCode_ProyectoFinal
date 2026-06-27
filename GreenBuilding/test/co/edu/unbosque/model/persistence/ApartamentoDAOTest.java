package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ApartamentoDTO;

public class ApartamentoDAOTest {

	static ApartamentoDAO dao;
	static String ARCHIVO_ORIGINAL = "apartamentos.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ApartamentoDAO();
		System.out.println("Iniciando pruebas de ApartamentoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ApartamentoDAO();
		dao.getListaApartamentos().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
		dao.crear(dto);
		assertTrue(
				dao.getListaApartamentos().size() == 1 && dao.getListaApartamentos().get(0).getId().equals("APT001"));
	}

	@Test
	public void crearVariosDatos() {
		ApartamentoDTO dto1 = new ApartamentoDTO();
		dto1.setId("APT001");
		dto1.setNumero("101");
		dto1.setPiso(1);
		dto1.setEstado("DESOCUPADO");

		ApartamentoDTO dto2 = new ApartamentoDTO();
		dto2.setId("APT002");
		dto2.setNumero("102");
		dto2.setPiso(1);
		dto2.setEstado("OCUPADO_PROPIETARIO");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaApartamentos().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaApartamentos().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
		dao.crear(dto);

		ApartamentoDTO actualizado = new ApartamentoDTO();
		actualizado.setId("APT001");
		actualizado.setNumero("101");
		actualizado.setPiso(2);
		actualizado.setEstado("ARRENDADO");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaApartamentos().get(0).getEstado().equals("ARRENDADO"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("X");
		dto.setNumero("0");
		dto.setPiso(0);
		dto.setEstado("DESOCUPADO");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		ApartamentoDTO dto = new ApartamentoDTO();
		dto.setId("APT001");
		dto.setNumero("101");
		dto.setPiso(1);
		dto.setEstado("DESOCUPADO");
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
		System.err.println("Finalizando todas las pruebas de ApartamentoDAO");
	}
}