package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.VehiculoDTO;

public class VehiculoDAOTest {

	static VehiculoDAO dao;
	static String ARCHIVO_ORIGINAL = "vehiculos.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new VehiculoDAO();
		System.out.println("Iniciando pruebas de VehiculoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new VehiculoDAO();
		dao.getListaVehiculos().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
		dto.setTipo("Carro");
		dto.setMarca("Toyota");
		dto.setModelo("Corolla");
		dto.setColor("Blanco");
		dto.setAutorizado(true);
		dao.crear(dto);
		assertTrue(dao.getListaVehiculos().size() == 1 && dao.getListaVehiculos().get(0).getId().equals("VEH001"));
	}

	@Test
	public void crearVariosDatos() {
		VehiculoDTO dto1 = new VehiculoDTO();
		dto1.setId("VEH001");
		dto1.setPlaca("ABC123");
		dto1.setTipo("Carro");

		VehiculoDTO dto2 = new VehiculoDTO();
		dto2.setId("VEH002");
		dto2.setPlaca("XYZ789");
		dto2.setTipo("Moto");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaVehiculos().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
		dto.setTipo("Carro");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaVehiculos().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
		dto.setTipo("Carro");
		dao.crear(dto);

		VehiculoDTO actualizado = new VehiculoDTO();
		actualizado.setId("VEH001");
		actualizado.setPlaca("DEF456");
		actualizado.setTipo("Camioneta");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaVehiculos().get(0).getPlaca().equals("DEF456"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		VehiculoDTO dto = new VehiculoDTO();
		dto.setId("VEH001");
		dto.setPlaca("ABC123");
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
		System.err.println("Finalizando todas las pruebas de VehiculoDAO");
	}
}