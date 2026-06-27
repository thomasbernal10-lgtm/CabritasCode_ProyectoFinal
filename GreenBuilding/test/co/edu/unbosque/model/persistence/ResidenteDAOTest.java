package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ResidenteDTO;

public class ResidenteDAOTest {

	static ResidenteDAO dao;
	static String ARCHIVO_ORIGINAL = "residentes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ResidenteDAO();
		System.out.println("Iniciando pruebas de ResidenteDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ResidenteDAO();
		dao.getListaResidentes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dto.setNombre("Ana Torres");
		dto.setCedula("100200300");
		dto.setTelefono("3001112233");
		dto.setTipo("PROPIETARIO");
		dto.setActivo(true);
		dao.crear(dto);
		assertTrue(dao.getListaResidentes().size() == 1 && dao.getListaResidentes().get(0).getId().equals("RES001"));
	}

	@Test
	public void crearVariosDatos() {
		ResidenteDTO dto1 = new ResidenteDTO();
		dto1.setId("RES001");
		dto1.setNombre("Ana Torres");
		dto1.setCedula("100200300");
		dto1.setTipo("PROPIETARIO");
		dto1.setActivo(true);

		ResidenteDTO dto2 = new ResidenteDTO();
		dto2.setId("RES002");
		dto2.setNombre("Pedro Gomez");
		dto2.setCedula("300200100");
		dto2.setTipo("ARRENDATARIO");
		dto2.setActivo(true);

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaResidentes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dto.setNombre("Ana Torres");
		dto.setCedula("100200300");
		dto.setTipo("PROPIETARIO");
		dto.setActivo(true);
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaResidentes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dto.setNombre("Ana Torres");
		dto.setCedula("100200300");
		dto.setTipo("PROPIETARIO");
		dto.setActivo(true);
		dao.crear(dto);

		ResidenteDTO actualizado = new ResidenteDTO();
		actualizado.setId("RES001");
		actualizado.setNombre("Ana Martinez");
		actualizado.setCedula("100200300");
		actualizado.setTipo("PROPIETARIO");
		actualizado.setActivo(true);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaResidentes().get(0).getNombre().equals("Ana Martinez"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		ResidenteDTO dto = new ResidenteDTO();
		dto.setId("RES001");
		dto.setNombre("Ana Torres");
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
		System.err.println("Finalizando todas las pruebas de ResidenteDAO");
	}
}