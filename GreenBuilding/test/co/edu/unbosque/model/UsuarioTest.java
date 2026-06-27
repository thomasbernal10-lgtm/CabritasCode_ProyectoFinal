package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {

	static int contador = 1;
	Usuario usuario;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Usuario");
	}

	@Before
	public void antes() {
		System.out.println("Hola");

	}

	@Test
	public void constructorPorDefecto() {
		Usuario u = new Usuario();
		assertNull(u.getId());
		assertNull(u.getUsername());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Usuario");
	}
}
