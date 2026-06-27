package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntityAlreadyAssignedExceptionTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de EntityAlreadyAssignedException");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorGuardaMensaje() {
		EntityAlreadyAssignedException ex = new EntityAlreadyAssignedException("El parqueadero ya esta asignado");
		assertEquals("El parqueadero ya esta asignado", ex.getMessage());
	}

	@Test
	public void esInstanciaDeException() {
		EntityAlreadyAssignedException ex = new EntityAlreadyAssignedException("asignada");
		assertTrue(ex instanceof Exception);
	}

	@Test
	public void lanzarYCapturarExcepcion() {
		try {
			throw new EntityAlreadyAssignedException("El apartamento ya tiene un propietario asignado");
		} catch (EntityAlreadyAssignedException e) {
			assertEquals("El apartamento ya tiene un propietario asignado", e.getMessage());
		}
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de EntityAlreadyAssignedException");
	}
}