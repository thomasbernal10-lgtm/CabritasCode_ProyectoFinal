package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObligacionTest {

	static int contador = 1;
	Obligacion obligacion;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Obligacion");
	}

	@Before
	public void antesDeCadaPrueba() {
		Apartamento apt = new Apartamento("APT-1", "101", 1, null, "OCUPADO_PROPIETARIO", null, null);
		obligacion = new Obligacion("OBL-1", apt, "ADMINISTRACION", 350000.0, LocalDate.of(2026, 5, 1),
				LocalDate.of(2026, 5, 31), "PENDIENTE", null, null);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Obligacion o = new Obligacion();
		assertNull(o.getId());
		assertNull(o.getTipo());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("OBL-1", obligacion.getId());
		assertEquals("ADMINISTRACION", obligacion.getTipo());
		assertEquals(350000.0, obligacion.getMonto(), 0.001);
		assertEquals("PENDIENTE", obligacion.getEstado());
	}

	@Test
	public void setYGetId() {
		obligacion.setId("OBL-99");
		assertEquals("OBL-99", obligacion.getId());
	}

	@Test
	public void setYGetTipo() {
		obligacion.setTipo("MULTA");
		assertEquals("MULTA", obligacion.getTipo());
	}

	@Test
	public void setYGetMonto() {
		obligacion.setMonto(500000.0);
		assertEquals(500000.0, obligacion.getMonto(), 0.001);
	}

	@Test
	public void setYGetEstado() {
		obligacion.setEstado("PAGADA");
		assertEquals("PAGADA", obligacion.getEstado());
	}

	@Test
	public void setYGetApartamento() {
		assertEquals("APT-1", obligacion.getApartamento().getId());
	}

	@Test
	public void constanteTipoAdministracion() {
		assertEquals("ADMINISTRACION", obligacion.getTIPO_ADMINISTRACION());
	}

	@Test
	public void constanteTipoMulta() {
		assertEquals("MULTA", obligacion.getTIPO_MULTA());
	}

	@Test
	public void constanteEstadoPendiente() {
		assertEquals("PENDIENTE", obligacion.getESTADO_PENDIENTE());
	}

	@Test
	public void constanteEstadoPagada() {
		assertEquals("PAGADA", obligacion.getESTADO_PAGADA());
	}

	@Test
	public void constanteEstadoVencida() {
		assertEquals("VENCIDA", obligacion.getESTADO_VENCIDA());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(obligacion.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Obligacion");
	}
}
