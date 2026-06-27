package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PagoTest {

	static int contador = 1;
	Pago pago;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Pago");
	}

	@Before
	public void antesDeCadaPrueba() {
		Apartamento apt = new Apartamento("APT-1", "101", 1, null, "OCUPADO_PROPIETARIO", null, null);
		pago = new Pago("PAG-1", apt, 350000.0, LocalDate.of(2026, 5, 1), "TRANSFERENCIA", "REF-001", "ADMIN-1");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Pago p = new Pago();
		assertNull(p.getId());
		assertEquals(0.0, p.getMonto(), 0.001);
	}

	@Test
	public void constructorCompleto() {
		assertEquals("PAG-1", pago.getId());
		assertEquals(350000.0, pago.getMonto(), 0.001);
		assertEquals("TRANSFERENCIA", pago.getMedioPago());
		assertEquals("REF-001", pago.getReferencia());
		assertEquals("ADMIN-1", pago.getRegistradoPor());
	}

	@Test
	public void setYGetId() {
		pago.setId("PAG-99");
		assertEquals("PAG-99", pago.getId());
	}

	@Test
	public void setYGetMonto() {
		pago.setMonto(500000.0);
		assertEquals(500000.0, pago.getMonto(), 0.001);
	}

	@Test
	public void setYGetMedioPago() {
		pago.setMedioPago("EFECTIVO");
		assertEquals("EFECTIVO", pago.getMedioPago());
	}

	@Test
	public void setYGetReferencia() {
		pago.setReferencia("REF-999");
		assertEquals("REF-999", pago.getReferencia());
	}

	@Test
	public void setYGetFechaPago() {
		LocalDate fecha = LocalDate.of(2026, 6, 15);
		pago.setFechaPago(fecha);
		assertEquals(fecha, pago.getFechaPago());
	}

	@Test
	public void setYGetApartamento() {
		assertEquals("APT-1", pago.getApartamento().getId());
	}

	@Test
	public void constanteMedioEfectivo() {
		assertEquals("EFECTIVO", pago.getMEDIO_EFECTIVO());
	}

	@Test
	public void constanteMedioTransferencia() {
		assertEquals("TRANSFERENCIA", pago.getMEDIO_TRANSFERENCIA());
	}

	@Test
	public void constanteMedioCheque() {
		assertEquals("CHEQUE", pago.getMEDIO_CHEQUE());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(pago.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Pago");
	}
}
