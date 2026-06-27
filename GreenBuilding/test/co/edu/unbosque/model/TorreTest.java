package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TorreTest {

	static int contador = 1;
	Torre torre;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Torre");
	}

	@Before
	public void antesDeCadaPrueba() {
		torre = new Torre("T1", "Torre Norte", 8, null);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Torre t = new Torre();
		assertNull(t.getId());
		assertNull(t.getNombre());
	}

	@Test
	public void setYGetId() {
		torre.setId("T2");
		assertEquals("T2", torre.getId());
	}

	@Test
	public void setYGetNombre() {
		torre.setNombre("Torre Sur");
		assertEquals("Torre Sur", torre.getNombre());
	}

	@Test
	public void setYGetConjunto() {
		Conjunto c = new Conjunto("C1", "Mi Conjunto", "Calle 1", "Bogotá", "3001234567", "conj@mail.com");
		torre.setConjunto(c);
		assertEquals("C1", torre.getConjunto().getId());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(torre.toString());
		assertTrue(torre.toString().contains("T1"));
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Torre");
	}
}
