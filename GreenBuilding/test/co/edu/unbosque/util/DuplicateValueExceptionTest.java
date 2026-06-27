package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DuplicateValueExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de DuplicateValueException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		DuplicateValueException ex = new DuplicateValueException("El nombre de usuario ya existe");
		assertEquals("El nombre de usuario ya existe", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		DuplicateValueException ex = new DuplicateValueException("duplicado");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new DuplicateValueException("La cedula ya se encuentra registrada");
		} catch (DuplicateValueException e) {
			assertEquals("La cedula ya se encuentra registrada", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de DuplicateValueException");
	}
}