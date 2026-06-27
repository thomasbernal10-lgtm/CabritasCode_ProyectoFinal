package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrendatarioTest {

	static int contador = 1;
	Arrendatario arrendatario;
	Apartamento apartamento;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Arrendatario");
	}

	@Before
	public void antesDeCadaPrueba() {
		apartamento = new Apartamento("APT-1", "101", 1, null, "ARRENDADO", null, null);
		arrendatario = new Arrendatario();
		arrendatario.setId("ARR-1");
		arrendatario.setNombre("María López");
		arrendatario.setCedula("111222333");
		arrendatario.setTelefono("3011112222");
		arrendatario.setCorreo("maria@mail.com");
		arrendatario.setContactoEmergencia("Pedro López");
		arrendatario.setTelefonoEmergencia("3015556666");
		arrendatario.setApartamento(apartamento);
		arrendatario.setFechaInicioContrato(LocalDate.of(2025, 1, 1));
		arrendatario.setFechaFinContrato(LocalDate.of(2026, 1, 1));
		arrendatario.setActivo(true);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Arrendatario a = new Arrendatario();
		assertNull(a.getId());
		assertNull(a.getNombre());
	}

	@Test
	public void setYGetId() {
		arrendatario.setId("ARR-99");
		assertEquals("ARR-99", arrendatario.getId());
	}

	@Test
	public void setYGetNombre() {
		arrendatario.setNombre("Juan Torres");
		assertEquals("Juan Torres", arrendatario.getNombre());
	}

	@Test
	public void setYGetApartamento() {
		assertEquals("APT-1", arrendatario.getApartamento().getId());
	}

	@Test
	public void setYGetFechaInicioContrato() {
		LocalDate fecha = LocalDate.of(2024, 6, 1);
		arrendatario.setFechaInicioContrato(fecha);
		assertEquals(fecha, arrendatario.getFechaInicioContrato());
	}

	@Test
	public void setYGetFechaFinContrato() {
		LocalDate fecha = LocalDate.of(2027, 6, 1);
		arrendatario.setFechaFinContrato(fecha);
		assertEquals(fecha, arrendatario.getFechaFinContrato());
	}

	@Test
	public void setYGetActivo() {
		arrendatario.setActivo(false);
		assertFalse(arrendatario.isActivo());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(arrendatario.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Arrendatario");
	}
}
