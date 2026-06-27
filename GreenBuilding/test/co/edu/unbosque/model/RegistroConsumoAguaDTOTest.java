package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroConsumoAguaDTOTest {

	static int contador = 1;
	RegistroConsumoAguaDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroConsumoAguaDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new RegistroConsumoAguaDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof RegistroConsumoAguaDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new RegistroConsumoAguaDTO());
	}

	@Test
	public void constructorConParametros() {
		RegistroConsumoAguaDTO r = new RegistroConsumoAguaDTO(10.0, 2.0, 8.0); assertNotNull(r);
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
		System.err.println("Finalizando todas las pruebas de RegistroConsumoAguaDTO");
	}
}
