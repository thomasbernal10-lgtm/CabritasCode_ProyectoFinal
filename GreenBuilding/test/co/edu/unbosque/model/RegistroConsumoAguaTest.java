package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroConsumoAguaTest {

	static int contador = 1;
	RegistroConsumoAgua registro;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroConsumoAgua");
	}

	@Before
	public void antesDeCadaPrueba() {
		Conjunto conjunto = new Conjunto("C1", "GreenSet", "Cra 10", "Bogotá", "601", "c@mail.com");
		registro = new RegistroConsumoAgua(120.5, 10.0, 110.5);
		registro.setId("RCA-1");
		registro.setConjunto(conjunto);
		registro.setPeriodo("2026-05");
		registro.setFechaRegistro(LocalDate.of(2026, 5, 31));
		registro.setRegistradoPor("ADMIN-1");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		RegistroConsumoAgua r = new RegistroConsumoAgua();
		assertEquals(0.0, r.getConsumoMtCubico(), 0.001);
	}

	@Test
	public void constructorConValores() {
		assertEquals(120.5, registro.getConsumoMtCubico(), 0.001);
		assertEquals(10.0, registro.getAguaLluviaMtCubico(), 0.001);
		assertEquals(110.5, registro.getConsumoNetoMtCubico(), 0.001);
	}

	@Test
	public void setYGetId() {
		registro.setId("RCA-99");
		assertEquals("RCA-99", registro.getId());
	}

	@Test
	public void setYGetConsumoMtCubico() {
		registro.setConsumoMtCubico(200.0);
		assertEquals(200.0, registro.getConsumoMtCubico(), 0.001);
	}

	@Test
	public void setYGetAguaLluviaMtCubico() {
		registro.setAguaLluviaMtCubico(15.0);
		assertEquals(15.0, registro.getAguaLluviaMtCubico(), 0.001);
	}

	@Test
	public void setYGetPeriodo() {
		registro.setPeriodo("2026-06");
		assertEquals("2026-06", registro.getPeriodo());
	}

	@Test
	public void setYGetConjunto() {
		assertEquals("C1", registro.getConjunto().getId());
	}

	@Test
	public void setYGetFechaRegistro() {
		LocalDate fecha = LocalDate.of(2026, 6, 30);
		registro.setFechaRegistro(fecha);
		assertEquals(fecha, registro.getFechaRegistro());
	}

	@Test
	public void setYGetRegistradoPor() {
		registro.setRegistradoPor("ADMIN-2");
		assertEquals("ADMIN-2", registro.getRegistradoPor());
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
		System.err.println("Finalizando todas las pruebas de RegistroConsumoAgua");
	}
}
