package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.MascotaDTO;

public class MascotaDAOTest {

	static MascotaDAO dao;
	static String ARCHIVO_ORIGINAL = "mascotas.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new MascotaDAO();
		System.out.println("Iniciando pruebas de MascotaDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new MascotaDAO();
		dao.getListaMascotas().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
		dto.setRaza("Labrador");
		dto.setColor("Dorado");
		dao.crear(dto);
		assertTrue(dao.getListaMascotas().size() == 1 && dao.getListaMascotas().get(0).getId().equals("MAS001"));
	}

	@Test
	public void crearVariosDatos() {
		MascotaDTO dto1 = new MascotaDTO();
		dto1.setId("MAS001");
		dto1.setNombre("Firulais");
		dto1.setEspecie("Perro");

		MascotaDTO dto2 = new MascotaDTO();
		dto2.setId("MAS002");
		dto2.setNombre("Michi");
		dto2.setEspecie("Gato");

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaMascotas().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaMascotas().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
		dao.crear(dto);

		MascotaDTO actualizado = new MascotaDTO();
		actualizado.setId("MAS001");
		actualizado.setNombre("Rex");
		actualizado.setEspecie("Perro");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaMascotas().get(0).getNombre().equals("Rex"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		MascotaDTO dto = new MascotaDTO();
		dto.setId("MAS001");
		dto.setNombre("Firulais");
		dto.setEspecie("Perro");
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
		System.err.println("Finalizando todas las pruebas de MascotaDAO");
	}
}