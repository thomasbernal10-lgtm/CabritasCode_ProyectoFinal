package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReporteTest {

	static int contador = 1;
	Reporte reporte;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Reporte");
	}

	@Before
	public void antesDeCadaPrueba() {
		Conjunto conjunto = new Conjunto("C1", "GreenSet", "Cra 10", "Bogotá", "601", "c@mail.com");
		reporte = new Reporte("REP-1", "FINANCIERO", "Reporte Mayo 2026", "Contenido del reporte",
				LocalDateTime.of(2026, 5, 31, 23, 59), "ADMIN-1", conjunto);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Reporte r = new Reporte();
		assertNull(r.getId());
		assertNull(r.getTipo());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("REP-1", reporte.getId());
		assertEquals("FINANCIERO", reporte.getTipo());
		assertEquals("Reporte Mayo 2026", reporte.getTitulo());
		assertEquals("ADMIN-1", reporte.getGeneradoPor());
	}

	@Test
	public void setYGetId() {
		reporte.setId("REP-99");
		assertEquals("REP-99", reporte.getId());
	}

	@Test
	public void setYGetTipo() {
		reporte.setTipo("AMBIENTAL");
		assertEquals("AMBIENTAL", reporte.getTipo());
	}

	@Test
	public void setYGetTitulo() {
		reporte.setTitulo("Reporte Anual");
		assertEquals("Reporte Anual", reporte.getTitulo());
	}

	@Test
	public void setYGetContenido() {
		reporte.setContenido("Nuevo contenido");
		assertEquals("Nuevo contenido", reporte.getContenido());
	}

	@Test
	public void setYGetGeneradoPor() {
		reporte.setGeneradoPor("ADMIN-2");
		assertEquals("ADMIN-2", reporte.getGeneradoPor());
	}

	@Test
	public void setYGetConjunto() {
		assertEquals("C1", reporte.getConjunto().getId());
	}

	@Test
	public void constanteTipoFinanciero() {
		assertEquals("FINANCIERO", reporte.getTIPO_FINANCIERO());
	}

	@Test
	public void constanteTipoAmbiental() {
		assertEquals("AMBIENTAL", reporte.getTIPO_AMBIENTAL());
	}

	@Test
	public void constanteTipoAdministrativo() {
		assertEquals("ADMINISTRATIVO", reporte.getTIPO_ADMINISTRATIVO());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(reporte.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Reporte");
	}
}
