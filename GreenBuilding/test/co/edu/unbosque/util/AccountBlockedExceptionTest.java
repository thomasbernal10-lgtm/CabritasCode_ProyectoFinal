package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountBlockedExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de AccountBlockedException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		AccountBlockedException ex = new AccountBlockedException("Cuenta bloqueada por el administrador");
		assertEquals("Cuenta bloqueada por el administrador", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		AccountBlockedException ex = new AccountBlockedException("bloqueada");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new AccountBlockedException("Acceso denegado");
		} catch (AccountBlockedException e) {
			assertEquals("Acceso denegado", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de AccountBlockedException");
	}
}