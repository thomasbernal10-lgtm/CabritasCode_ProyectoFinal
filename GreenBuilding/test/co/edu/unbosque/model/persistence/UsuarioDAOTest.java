package co.edu.unbosque.model.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.UsuarioDTO;

public class UsuarioDAOTest {

	static UsuarioDAO dao;
	static String ARCHIVO_ORIGINAL = "usuarios.dat";
	static Object backup;
	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		backup = FileHandler.crearYLeerArchivoSerializado(ARCHIVO_ORIGINAL);
		dao = new UsuarioDAO();
		System.out.println("Iniciando pruebas de UsuarioDAO");
	}

	@Before
	public void antesDeCadaPrueba() {
		FileHandler.crearYEscribirArchivoSerializado(ARCHIVO_ORIGINAL, new ArrayList<>());
		dao = new UsuarioDAO();
		dao.getListaUsuarios().clear();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearNuevoDato() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
		dto.setActivo(true);
		dao.crear(dto);
		assertTrue(dao.getListaUsuarios().size() == 1 && dao.getListaUsuarios().get(0).getId().equals("USR001"));
	}

	@Test
	public void crearVariosDatos() {
		UsuarioDTO dto1 = new UsuarioDTO();
		dto1.setId("USR001");
		dto1.setUsername("admin");
		dto1.setContrasena("1234");
		dto1.setRol("ADMIN");
		dto1.setActivo(true);

		UsuarioDTO dto2 = new UsuarioDTO();
		dto2.setId("USR002");
		dto2.setUsername("residente1");
		dto2.setContrasena("abcd");
		dto2.setRol("RESIDENTE");
		dto2.setActivo(true);

		dao.crear(dto1);
		dao.crear(dto2);
		assertTrue(dao.getListaUsuarios().size() == 2);
	}

	@Test
	public void eliminarDatoExistente() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
		dto.setActivo(true);
		dao.crear(dto);
		boolean resultado = dao.eliminar(0);
		assertTrue(resultado && dao.getListaUsuarios().size() == 0);
	}

	@Test
	public void eliminarPosicionNegativa() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
		dao.crear(dto);
		boolean resultado = dao.eliminar(-1);
		assertTrue(!resultado);
	}

	@Test
	public void eliminarPosicionFueraDeRango() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
		dao.crear(dto);
		boolean resultado = dao.eliminar(99);
		assertTrue(!resultado);
	}

	@Test
	public void actualizarDatoExistente() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
		dto.setActivo(true);
		dao.crear(dto);

		UsuarioDTO actualizado = new UsuarioDTO();
		actualizado.setId("USR001");
		actualizado.setUsername("superadmin");
		actualizado.setContrasena("5678");
		actualizado.setRol("SUPER_ADMIN");
		actualizado.setActivo(true);
		boolean resultado = dao.actualizar(0, actualizado);
		assertTrue(resultado && dao.getListaUsuarios().get(0).getUsername().equals("superadmin"));
	}

	@Test
	public void actualizarPosicionInvalida() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("X");
		boolean resultado = dao.actualizar(99, dto);
		assertTrue(!resultado);
	}

	@Test
	public void mostrarListaNoVacia() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId("USR001");
		dto.setUsername("admin");
		dto.setContrasena("1234");
		dto.setRol("ADMIN");
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
		System.err.println("Finalizando todas las pruebas de UsuarioDAO");
	}
}