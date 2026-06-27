package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehiculoTest {

	static int contador = 1;
	Vehiculo vehiculo;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Vehiculo");
	}

	@Before
	public void antesDeCadaPrueba() {
		Apartamento apt = new Apartamento("APT-1", "101", 1, null, "OCUPADO_PROPIETARIO", null, null);
		vehiculo = new Vehiculo("VEH-1", "ABC123", "CARRO", "Toyota", "Corolla", "Rojo", apt, true, false);
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		Vehiculo v = new Vehiculo();
		assertNull(v.getId());
		assertNull(v.getPlaca());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("VEH-1", vehiculo.getId());
		assertEquals("ABC123", vehiculo.getPlaca());
		assertEquals("CARRO", vehiculo.getTipo());
		assertEquals("Toyota", vehiculo.getMarca());
		assertEquals("Corolla", vehiculo.getModelo());
		assertEquals("Rojo", vehiculo.getColor());
	}

	@Test
	public void setYGetId() {
		vehiculo.setId("VEH-99");
		assertEquals("VEH-99", vehiculo.getId());
	}

	@Test
	public void setYGetPlaca() {
		vehiculo.setPlaca("XYZ789");
		assertEquals("XYZ789", vehiculo.getPlaca());
	}

	@Test
	public void setYGetTipo() {
		vehiculo.setTipo("MOTO");
		assertEquals("MOTO", vehiculo.getTipo());
	}

	@Test
	public void setYGetMarca() {
		vehiculo.setMarca("Chevrolet");
		assertEquals("Chevrolet", vehiculo.getMarca());
	}

	@Test
	public void setYGetModelo() {
		vehiculo.setModelo("Spark");
		assertEquals("Spark", vehiculo.getModelo());
	}

	@Test
	public void setYGetColor() {
		vehiculo.setColor("Azul");
		assertEquals("Azul", vehiculo.getColor());
	}

	@Test
	public void setYGetAutorizado() {
		vehiculo.setAutorizado(false);
		assertFalse(vehiculo.isAutorizado());
	}

	@Test
	public void setYGetTieneRestriccion() {
		vehiculo.setTieneRestriccion(true);
		assertTrue(vehiculo.isTieneRestriccion());
	}

	@Test
	public void constanteTipoCarro() {
		assertEquals("CARRO", vehiculo.getTIPO_CARRO());
	}

	@Test
	public void constanteTipoMoto() {
		assertEquals("MOTO", vehiculo.getTIPO_MOTO());
	}

	@Test
	public void constanteTipoBicicleta() {
		assertEquals("BICICLETA", vehiculo.getTIPO_BICICLETA());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(vehiculo.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Vehiculo");
	}
}
