package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PagoDTOTest {

	static int contador = 1;
	PagoDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de PagoDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new PagoDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof PagoDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new PagoDTO());
	}

	@Test
	public void dosInstanciasSonDistintas() {
		PagoDTO obj2 = new PagoDTO(); assertNotSame(obj, obj2);
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
		System.err.println("Finalizando todas las pruebas de PagoDTO");
	}
}
