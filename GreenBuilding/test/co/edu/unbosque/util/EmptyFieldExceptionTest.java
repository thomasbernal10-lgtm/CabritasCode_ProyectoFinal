package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmptyFieldExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de EmptyFieldException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		EmptyFieldException ex = new EmptyFieldException("El campo nombre es obligatorio");
		assertEquals("El campo nombre es obligatorio", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		EmptyFieldException ex = new EmptyFieldException("vacio");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new EmptyFieldException("El campo correo no puede estar vacio");
		} catch (EmptyFieldException e) {
			assertEquals("El campo correo no puede estar vacio", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de EmptyFieldException");
	}
}