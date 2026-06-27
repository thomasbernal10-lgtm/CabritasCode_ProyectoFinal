package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ResidenteTest {

	static int contador = 1;
	Residente residente;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Residente");
	}

	@Before
	public void antesDeCadaPrueba() {
		Apartamento apt = new Apartamento("APT-1", "101", 1, null, "OCUPADO_PROPIETARIO", null, null);
		residente = new Residente();
		residente.setId("RES-1");
		residente.setNombre("Laura Díaz");
		residente.setCedula("222333444");
		residente.setTelefono("3022223333");
		residente.setCorreo("laura@mail.com");
		residente.setContactoEmergencia("Pedro Díaz");
		residente.setTelefonoEmergencia("3025554444");
		residente.setApartamento(apt);
		residente.setTipo("PROPIETARIO");
		residente.setActivo(true);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Residente r = new Residente();
		assertNull(r.getId());
		assertNull(r.getTipo());
	}

	@Test
	public void setYGetId() {
		residente.setId("RES-99");
		assertEquals("RES-99", residente.getId());
	}

	@Test
	public void setYGetNombre() {
		residente.setNombre("Andrea Gil");
		assertEquals("Andrea Gil", residente.getNombre());
	}

	@Test
	public void setYGetTipo() {
		residente.setTipo("ARRENDATARIO");
		assertEquals("ARRENDATARIO", residente.getTipo());
	}

	@Test
	public void setYGetActivo() {
		residente.setActivo(false);
		assertFalse(residente.isActivo());
	}

	@Test
	public void setYGetApartamento() {
		assertEquals("APT-1", residente.getApartamento().getId());
	}

	@Test
	public void constanteTipoPropietario() {
		assertEquals("PROPIETARIO", residente.getTIPO_PROPIETARIO());
	}

	@Test
	public void constanteTipoArrendatario() {
		assertEquals("ARRENDATARIO", residente.getTIPO_ARRENDATARIO());
	}

	@Test
	public void constanteTipoFamiliar() {
		assertEquals("FAMILIAR", residente.getTIPO_FAMILIAR());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(residente.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Residente");
	}
}
