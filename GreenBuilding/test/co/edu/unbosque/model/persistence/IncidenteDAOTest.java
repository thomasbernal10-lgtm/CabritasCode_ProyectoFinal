package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.IncidenteDTO;

public class IncidenteDAOTest {

	static IncidenteDAO dao;
	static String ARCHIVO_ORIGINAL = "incidentes.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new IncidenteDAO();
		System.out.println("Iniciando pruebas de IncidenteDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new IncidenteDAO();
		dao.getListaIncidentes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		IncidenteDTO dto = new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1",
				LocalDateTime.now(), "ABIERTO", false);
		dao.crear(dto);
		assertTrue(dao.getListaIncidentes().size() == 1 && dao.getListaIncidentes().get(0).getId().equals("INC001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
		dao.crear(new IncidenteDTO("INC002", "DAÑO", "GRAVE", "Daño en pared", null, "Admin", LocalDateTime.now(),
				"EN_PROCESO", true));
		assertTrue(dao.getListaIncidentes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaIncidentes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
		IncidenteDTO actualizado = new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1",
				LocalDateTime.now(), "CERRADO", false);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaIncidentes().get(0).getEstado().equals("CERRADO"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new IncidenteDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new IncidenteDTO("INC001", "RUIDO", "LEVE", "Ruido excesivo", null, "Vigilante1", LocalDateTime.now(),
				"ABIERTO", false));
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
		System.err.println("Finalizando todas las pruebas de IncidenteDAO");
	}
}