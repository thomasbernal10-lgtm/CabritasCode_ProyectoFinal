package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CampanaAmbientalDTOTest {

	static int contador = 1;
	CampanaAmbientalDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de CampanaAmbientalDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new CampanaAmbientalDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof CampanaAmbientalDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new CampanaAmbientalDTO());
	}

	@Test
	public void dosInstanciasSonDistintas() {
		CampanaAmbientalDTO obj2 = new CampanaAmbientalDTO(); assertNotSame(obj, obj2);
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
		System.err.println("Finalizando todas las pruebas de CampanaAmbientalDTO");
	}
}
