package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParticipacionTest {

	static int contador = 1;
	Participacion participacion;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Participacion");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Hola");
	}

	@Test
	public void constructorPorDefecto() {
		Participacion p = new Participacion();
		assertNull(p.getId());
		assertNull(p.getObservacion());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Participacion");
	}
}
