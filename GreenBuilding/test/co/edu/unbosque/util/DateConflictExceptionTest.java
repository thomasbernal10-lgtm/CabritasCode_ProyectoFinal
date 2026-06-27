package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateConflictExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de DateConflictException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		DateConflictException ex = new DateConflictException("La hora de fin es anterior a la de inicio");
		assertEquals("La hora de fin es anterior a la de inicio", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		DateConflictException ex = new DateConflictException("conflicto");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new DateConflictException("Rango de fechas se solapa con otra reserva");
		} catch (DateConflictException e) {
			assertEquals("Rango de fechas se solapa con otra reserva", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de DateConflictException");
	}
}