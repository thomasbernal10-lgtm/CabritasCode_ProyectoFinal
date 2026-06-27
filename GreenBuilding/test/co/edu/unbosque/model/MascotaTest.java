package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MascotaTest {

	static int contador = 1;
	Mascota mascota;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Mascota");
	}

	@Before
	public void antesDeCadaPrueba() {
		Apartamento apt = new Apartamento("APT-1", "101", 1, null, "OCUPADO_PROPIETARIO", null, null);
		mascota = new Mascota("MSC-1", "Firulais", "PERRO", "Labrador", "Dorado", apt, LocalDate.of(2025, 6, 1), true);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Mascota m = new Mascota();
		assertNull(m.getId());
		assertNull(m.getNombre());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("MSC-1", mascota.getId());
		assertEquals("Firulais", mascota.getNombre());
		assertEquals("PERRO", mascota.getEspecie());
		assertEquals("Labrador", mascota.getRaza());
		assertEquals("Dorado", mascota.getColor());
	}

	@Test
	public void setYGetId() {
		mascota.setId("MSC-99");
		assertEquals("MSC-99", mascota.getId());
	}

	@Test
	public void setYGetNombre() {
		mascota.setNombre("Michi");
		assertEquals("Michi", mascota.getNombre());
	}

	@Test
	public void setYGetEspecie() {
		mascota.setEspecie("GATO");
		assertEquals("GATO", mascota.getEspecie());
	}

	@Test
	public void setYGetRaza() {
		mascota.setRaza("Siamés");
		assertEquals("Siamés", mascota.getRaza());
	}

	@Test
	public void setYGetColor() {
		mascota.setColor("Blanco");
		assertEquals("Blanco", mascota.getColor());
	}

	@Test
	public void setYGetApartamento() {
		assertEquals("APT-1", mascota.getApartamento().getId());
	}

	@Test
	public void setYGetFechaVacunacion() {
		LocalDate fecha = LocalDate.of(2026, 3, 15);
		mascota.setFechaVacunacion(fecha);
		assertEquals(fecha, mascota.getFechaVacunacion());
	}

	@Test
	public void setYGetVacunasAlDia() {
		mascota.setVacunasAlDia(false);
		assertFalse(mascota.isVacunasAlDia());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(mascota.toString());
		assertTrue(mascota.toString().contains("MSC-1"));
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Mascota");
	}
}
