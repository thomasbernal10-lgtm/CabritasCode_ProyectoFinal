package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CampanaAmbientalTest {

	static int contador = 1;
	CampanaAmbiental campana;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de CampanaAmbiental");
	}

	@Before
	public void befo() {
		System.out.println("Prueba");

	}

	@Test
	public void constructorPorDefecto() {
		CampanaAmbiental c = new CampanaAmbiental();
		assertNull(c.getId());
		assertNull(c.getTipo());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de CampanaAmbiental");
	}
}
