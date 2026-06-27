package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IncorrectPasswordExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de IncorrectPasswordException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		IncorrectPasswordException ex = new IncorrectPasswordException("La contrasena ingresada es incorrecta");
		assertEquals("La contrasena ingresada es incorrecta", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		IncorrectPasswordException ex = new IncorrectPasswordException("incorrecta");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new IncorrectPasswordException("Credenciales invalidas, verifique su contrasena");
		} catch (IncorrectPasswordException e) {
			assertEquals("Credenciales invalidas, verifique su contrasena", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de IncorrectPasswordException");
	}
}