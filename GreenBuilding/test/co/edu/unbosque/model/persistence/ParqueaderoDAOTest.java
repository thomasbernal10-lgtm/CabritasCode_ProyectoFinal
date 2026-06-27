package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ParqueaderoDTO;

public class ParqueaderoDAOTest {

	static ParqueaderoDAO dao;
	static String ARCHIVO_ORIGINAL = "parqueaderos.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ParqueaderoDAO();
		System.out.println("Iniciando pruebas de ParqueaderoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ParqueaderoDAO();
		dao.getListaParqueaderos().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ParqueaderoDTO dto = new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null);
		dao.crear(dto);
		assertTrue(dao.getListaParqueaderos().size() == 1 && dao.getListaParqueaderos().get(0).getId().equals("PQ001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
		dao.crear(new ParqueaderoDTO("PQ002", "P02", "MOTO", "OCUPADO", null));
		assertTrue(dao.getListaParqueaderos().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaParqueaderos().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
		ParqueaderoDTO actualizado = new ParqueaderoDTO("PQ001", "P01", "CARRO", "OCUPADO", null);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaParqueaderos().get(0).getEstado().equals("OCUPADO"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new ParqueaderoDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ParqueaderoDTO("PQ001", "P01", "CARRO", "DISPONIBLE", null));
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
		System.err.println("Finalizando todas las pruebas de ParqueaderoDAO");
	}
}