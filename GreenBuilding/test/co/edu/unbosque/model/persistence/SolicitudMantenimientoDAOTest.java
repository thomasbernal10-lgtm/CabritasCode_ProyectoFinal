package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.SolicitudMantenimientoDTO;

public class SolicitudMantenimientoDAOTest {

	static SolicitudMantenimientoDAO dao;
	static String ARCHIVO_ORIGINAL = "solicitudesmantenimiento.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new SolicitudMantenimientoDAO();
		System.out.println("Iniciando pruebas de SolicitudMantenimientoDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new SolicitudMantenimientoDAO();
		dao.getListaSolicitudes().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		SolicitudMantenimientoDTO dto = new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA",
				"PENDIENTE", null, null, LocalDateTime.now(), LocalDate.now().plusDays(3));
		dao.crear(dto);
		assertTrue(dao.getListaSolicitudes().size() == 1 && dao.getListaSolicitudes().get(0).getId().equals("SOL001"));
	}

	@Test
	public void crearVariosDatos() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
		dao.crear(new SolicitudMantenimientoDTO("SOL002", "Luz danada", "ELECTRICA", "MEDIA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(5)));
		assertTrue(dao.getListaSolicitudes().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaSolicitudes().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
		SolicitudMantenimientoDTO actualizado = new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA",
				"ALTA", "EN_PROCESO", null, null, LocalDateTime.now(), LocalDate.now().plusDays(3));
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaSolicitudes().get(0).getEstado().equals("EN_PROCESO"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		boolean resultado = dao.actualizar(99, new SolicitudMantenimientoDTO());
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		dao.crear(new SolicitudMantenimientoDTO("SOL001", "Fuga de agua", "PLOMERIA", "ALTA", "PENDIENTE", null, null,
				LocalDateTime.now(), LocalDate.now().plusDays(3)));
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
		System.err.println("Finalizando todas las pruebas de SolicitudMantenimientoDAO");
	}
}