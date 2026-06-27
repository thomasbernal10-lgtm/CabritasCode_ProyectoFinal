package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvalidIndexExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de InvalidIndexException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de InvalidIndexException");
	}

	@Test
	public void constructorGuardaMensaje() {
		InvalidIndexException ex = new InvalidIndexException("El indice ingresado no es valido");
		assertEquals("El indice ingresado no es valido", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		InvalidIndexException ex = new InvalidIndexException("invalido");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new InvalidIndexException("El indice supera el tamano de la lista");
		} catch (InvalidIndexException e) {
			assertEquals("El indice supera el tamano de la lista", e.getMessage());
		}
	}
}