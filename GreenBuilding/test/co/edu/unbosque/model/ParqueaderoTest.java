package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParqueaderoTest {

	static int contador = 1;
	Parqueadero parqueadero;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Parqueadero");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Hola");
	}

	@Test
	public void constructorPorDefecto() {
		Parqueadero p = new Parqueadero();
		assertNull(p.getId());
		assertNull(p.getTipo());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Parqueadero");
	}
}
