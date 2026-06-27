package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntityBlockedExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de EntityBlockedException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		EntityBlockedException ex = new EntityBlockedException("La zona comun se encuentra en mantenimiento");
		assertEquals("La zona comun se encuentra en mantenimiento", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		EntityBlockedException ex = new EntityBlockedException("bloqueada");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new EntityBlockedException("La zona comun esta bloqueada por decision administrativa");
		} catch (EntityBlockedException e) {
			assertEquals("La zona comun esta bloqueada por decision administrativa", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de EntityBlockedException");
	}
}