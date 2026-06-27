package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroConsumoEnergiaDTOTest {

	static int contador = 1;
	RegistroConsumoEnergiaDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroConsumoEnergiaDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new RegistroConsumoEnergiaDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof RegistroConsumoEnergiaDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new RegistroConsumoEnergiaDTO());
	}

	@Test
	public void constructorConParametros() {
		RegistroConsumoEnergiaDTO r = new RegistroConsumoEnergiaDTO(200.0, 50.0, 150.0); assertNotNull(r);
	}

	@Test
	public void instanciaEsObjeto() {
		assertTrue(obj instanceof Object);
	}


	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de RegistroConsumoEnergiaDTO");
	}
}
