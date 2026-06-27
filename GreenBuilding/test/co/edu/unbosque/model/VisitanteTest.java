package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VisitanteTest {

	static int contador = 1;
	Visitante visitante;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Visitante");
	}

	@Before
	public void antesDeCadaPrueba() {
		visitante = new Visitante();
		visitante.setId("VIS-1");
		visitante.setNombre("Carlos Ruiz");
		visitante.setCedula("444555666");
		visitante.setTelefono("3041234567");
		visitante.setCorreo("carlos.ruiz@mail.com");
		visitante.setContactoEmergencia("N/A");
		visitante.setTelefonoEmergencia("N/A");
		visitante.setTipo("OCASIONAL");
		visitante.setAutorizadoPor("PROP-1");
		visitante.setFechaAutorizacion(LocalDateTime.of(2026, 5, 1, 10, 0));
		visitante.setFechaVencimiento(LocalDateTime.of(2026, 5, 31, 23, 59));
		visitante.setActivo(true);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Visitante v = new Visitante();
		assertNull(v.getId());
		assertNull(v.getTipo());
	}

	@Test
	public void setYGetId() {
		visitante.setId("VIS-99");
		assertEquals("VIS-99", visitante.getId());
	}

	@Test
	public void setYGetNombre() {
		visitante.setNombre("Ana Morales");
		assertEquals("Ana Morales", visitante.getNombre());
	}

	@Test
	public void setYGetTipo() {
		visitante.setTipo("FRECUENTE");
		assertEquals("FRECUENTE", visitante.getTipo());
	}

	@Test
	public void setYGetAutorizadoPor() {
		visitante.setAutorizadoPor("PROP-2");
		assertEquals("PROP-2", visitante.getAutorizadoPor());
	}

	@Test
	public void setYGetActivo() {
		visitante.setActivo(false);
		assertFalse(visitante.isActivo());
	}

	@Test
	public void setYGetApartamentoDestino() {
		Apartamento apt = new Apartamento("APT-2", "202", 2, null, "OCUPADO_PROPIETARIO", null, null);
		visitante.setApartamentoDestino(apt);
		assertEquals("APT-2", visitante.getApartamentoDestino().getId());
	}

	@Test
	public void constanteTipoOcasional() {
		assertEquals("OCASIONAL", visitante.getTIPO_OCASIONAL());
	}

	@Test
	public void constanteTipoFrecuente() {
		assertEquals("FRECUENTE", visitante.getTIPO_FRECUENTE());
	}

	@Test
	public void constanteTipoDomiciliario() {
		assertEquals("DOMICILIARIO", visitante.getTIPO_DOMICILIARIO());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(visitante.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Visitante");
	}
}
