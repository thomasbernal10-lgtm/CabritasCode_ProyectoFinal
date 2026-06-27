package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObligacionDTOTest {

	static int contador = 1;
	ObligacionDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de ObligacionDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new ObligacionDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof ObligacionDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new ObligacionDTO());
	}

	@Test
	public void dosInstanciasSonDistintas() {
		ObligacionDTO obj2 = new ObligacionDTO(); assertNotSame(obj, obj2);
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
		System.err.println("Finalizando todas las pruebas de ObligacionDTO");
	}
}
