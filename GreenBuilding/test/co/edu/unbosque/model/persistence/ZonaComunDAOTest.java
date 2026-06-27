package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.ZonaComunDTO;

public class ZonaComunDAOTest {

	static ZonaComunDAO dao;
	static String ARCHIVO_ORIGINAL = "zonascomunes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new ZonaComunDAO();
		System.out.println("Iniciando pruebas de ZonaComunDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new ZonaComunDAO();
		dao.getListaZonasComunes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		ZonaComunDTO dto = new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true);
		dao.crear(dto);
		assertTrue(dao.getListaZonasComunes().size() == 1 && dao.getListaZonasComunes().get(0).getId().equals("ZC001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
		dao.crear(new ZonaComunDTO("ZC002", "Piscina", "PISCINA", "DISPONIBLE", null, 30, 50000.0, LocalTime.of(9, 0),
				LocalTime.of(20, 0), true));
		assertTrue(dao.getListaZonasComunes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaZonasComunes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
		ZonaComunDTO actualizado = new ZonaComunDTO("ZC001", "Salon Principal", "SALON", "DISPONIBLE", null, 60,
				120000.0, LocalTime.of(8, 0), LocalTime.of(22, 0), true);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaZonasComunes().get(0).getNombre().equals("Salon Principal"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new ZonaComunDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new ZonaComunDTO("ZC001", "Salon Comunal", "SALON", "DISPONIBLE", null, 50, 100000.0,
				LocalTime.of(8, 0), LocalTime.of(22, 0), true));
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
		System.err.println("Finalizando todas las pruebas de ZonaComunDAO");
	}
}