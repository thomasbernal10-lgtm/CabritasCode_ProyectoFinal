package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfigurationManagerTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de ConfigurationManager");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void getRetornaStringNoNulo() {
		String valor = ConfigurationManager.get("superadmin.username");
		assertNotNull(valor);
	}

	@Test
	public void getClaveInexistenteRetornaCadenaVacia() {
		String valor = ConfigurationManager.get("clave.que.no.existe");
		assertEquals("", valor);
	}

	@Test
	public void getIntRetornaEnteroValido() {
		int valor = ConfigurationManager.getInt("email.smtp.port");
		assertTrue(valor >= 0);
	}

	@Test
	public void getIntClaveInexistenteRetornaCero() {
		int valor = ConfigurationManager.getInt("clave.inexistente");
		assertEquals(0, valor);
	}

	@Test
	public void getIntValorNoNumericoRetornaCero() {
		int valor = ConfigurationManager.getInt("superadmin.username");
		assertEquals(0, valor);
	}

	@Test
	public void getDoubleRetornaDecimalValido() {
		double valor = ConfigurationManager.getDouble("multa.monto.defecto");
		assertTrue(valor >= 0.0);
	}

	@Test
	public void getDoubleClaveInexistenteRetornaCero() {
		double valor = ConfigurationManager.getDouble("clave.inexistente");
		assertEquals(0.0, valor, 0.001);
	}

	@Test
	public void getDoubleValorNoNumericoRetornaCero() {
		double valor = ConfigurationManager.getDouble("superadmin.username");
		assertEquals(0.0, valor, 0.001);
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de ConfigurationManager");
	}
}