package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConjuntoTest {

	static int contador = 1;
	Conjunto conjunto;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Conjunto");
	}

	@Before
	public void antesDeCadaPrueba() {
		conjunto = new Conjunto("C1", "GreenSet", "Cra 10 #20-30", "Bogotá", "6001234567", "green@mail.com");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Conjunto c = new Conjunto();
		assertNull(c.getId());
		assertNull(c.getNombre());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("C1", conjunto.getId());
		assertEquals("GreenSet", conjunto.getNombre());
		assertEquals("Cra 10 #20-30", conjunto.getDireccion());
		assertEquals("Bogotá", conjunto.getCiudad());
		assertEquals("6001234567", conjunto.getTelefono());
		assertEquals("green@mail.com", conjunto.getCorreo());
	}

	@Test
	public void setYGetId() {
		conjunto.setId("C99");
		assertEquals("C99", conjunto.getId());
	}

	@Test
	public void setYGetNombre() {
		conjunto.setNombre("Nuevo Nombre");
		assertEquals("Nuevo Nombre", conjunto.getNombre());
	}

	@Test
	public void setYGetDireccion() {
		conjunto.setDireccion("Av 68 #5-10");
		assertEquals("Av 68 #5-10", conjunto.getDireccion());
	}

	@Test
	public void setYGetCiudad() {
		conjunto.setCiudad("Medellín");
		assertEquals("Medellín", conjunto.getCiudad());
	}

	@Test
	public void setYGetTelefono() {
		conjunto.setTelefono("3101234567");
		assertEquals("3101234567", conjunto.getTelefono());
	}

	@Test
	public void setYGetCorreo() {
		conjunto.setCorreo("nuevo@mail.com");
		assertEquals("nuevo@mail.com", conjunto.getCorreo());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(conjunto.toString());
		assertTrue(conjunto.toString().contains("C1"));
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Conjunto");
	}
}
