package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvalidFormatExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de InvalidFormatException");
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
		System.err.println("Finalizando todas las pruebas de InvalidFormatException");
	}

	@Test
	public void constructorGuardaMensaje() {
		InvalidFormatException ex = new InvalidFormatException("El correo no tiene un formato valido");
		assertEquals("El correo no tiene un formato valido", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		InvalidFormatException ex = new InvalidFormatException("invalido");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new InvalidFormatException("El telefono solo debe contener digitos");
		} catch (InvalidFormatException e) {
			assertEquals("El telefono solo debe contener digitos", e.getMessage());
		}
	}
}