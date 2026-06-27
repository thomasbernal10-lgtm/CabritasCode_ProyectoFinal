package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlreadyProcessedExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de AlreadyProcessedException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		AlreadyProcessedException ex = new AlreadyProcessedException("El elemento ya fue procesado");
		assertEquals("El elemento ya fue procesado", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		AlreadyProcessedException ex = new AlreadyProcessedException("procesado");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new AlreadyProcessedException("Reserva ya cancelada");
		} catch (AlreadyProcessedException e) {
			assertEquals("Reserva ya cancelada", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de AlreadyProcessedException");
	}
}