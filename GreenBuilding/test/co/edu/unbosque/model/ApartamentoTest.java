package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApartamentoTest {

	static int contador = 1;
	Apartamento apartamento;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Apartamento");
	}

	@Before
	public void antesDeCadaPrueba() {
		apartamento = new Apartamento("APT-1", "101", 1, null, "DESOCUPADO", null, null);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Apartamento a = new Apartamento();
		assertNull(a.getId());
		assertNull(a.getNumero());
		assertNull(a.getEstado());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("APT-1", apartamento.getId());
		assertEquals("101", apartamento.getNumero());
		assertEquals(1, apartamento.getPiso());
		assertEquals("DESOCUPADO", apartamento.getEstado());
	}

	@Test
	public void setYGetId() {
		apartamento.setId("APT-99");
		assertEquals("APT-99", apartamento.getId());
	}

	@Test
	public void setYGetNumero() {
		apartamento.setNumero("202");
		assertEquals("202", apartamento.getNumero());
	}

	@Test
	public void setYGetPiso() {
		apartamento.setPiso(5);
		assertEquals(5, apartamento.getPiso());
	}

	@Test
	public void setYGetEstado() {
		apartamento.setEstado("ARRENDADO");
		assertEquals("ARRENDADO", apartamento.getEstado());
	}

	@Test
	public void setYGetTorre() {
		Torre torre = new Torre("T1", "Torre A", 10, null);
		apartamento.setTorre(torre);
		assertEquals("T1", apartamento.getTorre().getId());
	}

	@Test
	public void setYGetPropietario() {
		Propietario p = new Propietario();
		p.setId("PROP-1");
		apartamento.setPropietario(p);
		assertEquals("PROP-1", apartamento.getPropietario().getId());
	}

	@Test
	public void setYGetArrendatario() {
		Arrendatario a = new Arrendatario();
		a.setId("ARR-1");
		apartamento.setArrendatario(a);
		assertEquals("ARR-1", apartamento.getArrendatario().getId());
	}

	@Test
	public void constanteEstadoOcupadoPropietario() {
		assertEquals("OCUPADO_PROPIETARIO", apartamento.getESTADO_OCUPADO_PROPIETARIO());
	}

	@Test
	public void constanteEstadoArrendado() {
		assertEquals("ARRENDADO", apartamento.getESTADO_ARRENDADO());
	}

	@Test
	public void constanteEstadoDesocupado() {
		assertEquals("DESOCUPADO", apartamento.getESTADO_DESOCUPADO());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(apartamento.toString());
		assertTrue(apartamento.toString().contains("APT-1"));
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Apartamento");
	}
}
