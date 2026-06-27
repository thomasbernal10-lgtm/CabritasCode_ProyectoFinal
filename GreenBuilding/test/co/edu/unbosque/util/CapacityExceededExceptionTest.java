package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CapacityExceededExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de CapacityExceededException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		CapacityExceededException ex = new CapacityExceededException("El valor supera el limite permitido");
		assertEquals("El valor supera el limite permitido", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		CapacityExceededException ex = new CapacityExceededException("excedida");
		assertTrue(ex instanceof Exception);
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de CapacityExceededException");
	}
}