package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroReciclajeTest {

	static int contador = 1;
	RegistroReciclaje registro;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroReciclaje");
	}

	@Before
	public void antesDeCadaPrueba() {
		Conjunto conjunto = new Conjunto("C1", "GreenSet", "Cra 10", "Bogotá", "601", "c@mail.com");
		registro = new RegistroReciclaje(10.0, 5.0, 3.0, 2.0, 8.0, 28.0);
		registro.setId("RR-1");
		registro.setConjunto(conjunto);
		registro.setPeriodo("2026-05");
		registro.setFechaRegistro(LocalDate.of(2026, 5, 31));
		registro.setRegistradoPor("ADMIN-1");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		RegistroReciclaje r = new RegistroReciclaje();
		assertEquals(0.0, r.getKgPapel(), 0.001);
	}

	@Test
	public void constructorConValores() {
		assertEquals(10.0, registro.getKgPapel(), 0.001);
		assertEquals(5.0, registro.getKgPlastico(), 0.001);
		assertEquals(3.0, registro.getKgVidrio(), 0.001);
		assertEquals(2.0, registro.getKgMetal(), 0.001);
		assertEquals(8.0, registro.getKgOrganico(), 0.001);
		assertEquals(28.0, registro.getTotalKg(), 0.001);
	}

	@Test
	public void setYGetKgPapel() {
		registro.setKgPapel(15.0);
		assertEquals(15.0, registro.getKgPapel(), 0.001);
	}

	@Test
	public void setYGetKgPlastico() {
		registro.setKgPlastico(7.5);
		assertEquals(7.5, registro.getKgPlastico(), 0.001);
	}

	@Test
	public void setYGetKgVidrio() {
		registro.setKgVidrio(4.0);
		assertEquals(4.0, registro.getKgVidrio(), 0.001);
	}

	@Test
	public void setYGetKgMetal() {
		registro.setKgMetal(3.0);
		assertEquals(3.0, registro.getKgMetal(), 0.001);
	}

	@Test
	public void setYGetKgOrganico() {
		registro.setKgOrganico(9.0);
		assertEquals(9.0, registro.getKgOrganico(), 0.001);
	}

	@Test
	public void setYGetTotalKg() {
		registro.setTotalKg(38.5);
		assertEquals(38.5, registro.getTotalKg(), 0.001);
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(registro.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de RegistroReciclaje");
	}
}
