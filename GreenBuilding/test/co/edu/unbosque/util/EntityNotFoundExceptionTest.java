package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntityNotFoundExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de EntityNotFoundException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		EntityNotFoundException ex = new EntityNotFoundException("El apartamento no fue encontrado");
		assertEquals("El apartamento no fue encontrado", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		EntityNotFoundException ex = new EntityNotFoundException("no encontrada");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new EntityNotFoundException("No existe ningun residente con ese indice");
		} catch (EntityNotFoundException e) {
			assertEquals("No existe ningun residente con ese indice", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de EntityNotFoundException");
	}

}