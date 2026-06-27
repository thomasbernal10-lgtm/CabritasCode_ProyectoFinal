package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropietarioTest {

	static int contador = 1;
	Propietario propietario;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Propietario");
	}

	@Before
	public void antesDeCadaPrueba() {
		propietario = new Propietario();
		propietario.setId("PROP-1");
		propietario.setNombre("Carlos Pérez");
		propietario.setCedula("123456789");
		propietario.setTelefono("3001234567");
		propietario.setCorreo("carlos@mail.com");
		propietario.setContactoEmergencia("Ana Pérez");
		propietario.setTelefonoEmergencia("3009876543");
		propietario.setDireccionCorrespondencia("Calle 10 #5-20");
		propietario.setEsResidente(true);
		propietario.setResponsablePago(true);
		propietario.setEsConsejo(false);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Propietario p = new Propietario();
		assertNull(p.getId());
		assertNull(p.getNombre());
	}

	@Test
	public void setYGetId() {
		propietario.setId("PROP-99");
		assertEquals("PROP-99", propietario.getId());
	}

	@Test
	public void setYGetNombre() {
		propietario.setNombre("Luis Gómez");
		assertEquals("Luis Gómez", propietario.getNombre());
	}

	@Test
	public void setYGetCedula() {
		propietario.setCedula("987654321");
		assertEquals("987654321", propietario.getCedula());
	}

	@Test
	public void setYGetCorreo() {
		propietario.setCorreo("nuevo@mail.com");
		assertEquals("nuevo@mail.com", propietario.getCorreo());
	}

	@Test
	public void setYGetDireccionCorrespondencia() {
		propietario.setDireccionCorrespondencia("Carrera 5 #10-20");
		assertEquals("Carrera 5 #10-20", propietario.getDireccionCorrespondencia());
	}

	@Test
	public void setYGetEsResidente() {
		propietario.setEsResidente(false);
		assertFalse(propietario.isEsResidente());
	}

	@Test
	public void setYGetResponsablePago() {
		propietario.setResponsablePago(false);
		assertFalse(propietario.isResponsablePago());
	}

	@Test
	public void setYGetEsConsejo() {
		propietario.setEsConsejo(true);
		assertTrue(propietario.isEsConsejo());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(propietario.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Propietario");
	}
}
