package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ConjuntoDTO;

public class ConjuntoDAOTest {

	static ConjuntoDAO dao;
	static String ARCHIVO_ORIGINAL = "conjuntos.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ConjuntoDAO();
		System.out.println("Iniciando pruebas de ConjuntoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ConjuntoDAO();
		dao.getListaConjuntos().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ConjuntoDTO dto = new ConjuntoDTO("C001", "GreenBuilding", "Calle 100 #20-30", "Bogota", "3001234567",
				"gb@mail.com");
		dao.crear(dto);
		assertTrue(dao.getListaConjuntos().size() == 1 && dao.getListaConjuntos().get(0).getId().equals("C001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
		dao.crear(new ConjuntoDTO("C002", "EcoTowers", "Carrera 50", "Medellin", "3109876543", "et@mail.com"));
		assertTrue(dao.getListaConjuntos().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaConjuntos().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
		ConjuntoDTO actualizado = new ConjuntoDTO("C001", "GreenTowers", "Av 68", "Bogota", "3001234567",
				"gt@mail.com");
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaConjuntos().get(0).getNombre().equals("GreenTowers"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		ConjuntoDTO dto = new ConjuntoDTO();
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ConjuntoDTO("C001", "GreenBuilding", "Calle 100", "Bogota", "3001234567", "gb@mail.com"));
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
		System.err.println("Finalizando todas las pruebas de ConjuntoDAO");
	}
}