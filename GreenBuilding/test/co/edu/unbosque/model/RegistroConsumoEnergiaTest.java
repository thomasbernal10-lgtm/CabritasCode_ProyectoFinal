package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroConsumoEnergiaTest {

	static int contador = 1;
	RegistroConsumoEnergia registro;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroConsumoEnergia");
	}

	@Before
	public void antesDeCadaPrueba() {
		Conjunto conjunto = new Conjunto("C1", "GreenSet", "Cra 10", "Bogotá", "601", "c@mail.com");
		registro = new RegistroConsumoEnergia(500.0, 80.0, 420.0);
		registro.setId("RCE-1");
		registro.setConjunto(conjunto);
		registro.setPeriodo("2026-05");
		registro.setFechaRegistro(LocalDate.of(2026, 5, 31));
		registro.setRegistradoPor("ADMIN-1");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		RegistroConsumoEnergia r = new RegistroConsumoEnergia();
		assertEquals(0.0, r.getConsumoKwh(), 0.001);
	}

	@Test
	public void constructorConValores() {
		assertEquals(500.0, registro.getConsumoKwh(), 0.001);
		assertEquals(80.0, registro.getGeneracionSolarKwh(), 0.001);
		assertEquals(420.0, registro.getConsumoNetoKwh(), 0.001);
	}

	@Test
	public void setYGetConsumoKwh() {
		registro.setConsumoKwh(600.0);
		assertEquals(600.0, registro.getConsumoKwh(), 0.001);
	}

	@Test
	public void setYGetGeneracionSolarKwh() {
		registro.setGeneracionSolarKwh(100.0);
		assertEquals(100.0, registro.getGeneracionSolarKwh(), 0.001);
	}

	@Test
	public void setYGetConsumoNetoKwh() {
		registro.setConsumoNetoKwh(500.0);
		assertEquals(500.0, registro.getConsumoNetoKwh(), 0.001);
	}

	@Test
	public void setYGetPeriodo() {
		registro.setPeriodo("2026-06");
		assertEquals("2026-06", registro.getPeriodo());
	}

	@Test
	public void setYGetId() {
		registro.setId("RCE-99");
		assertEquals("RCE-99", registro.getId());
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
		System.err.println("Finalizando todas las pruebas de RegistroConsumoEnergia");
	}
}
