package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrendatarioDTOTest {

	static int contador = 1;
	ArrendatarioDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de ArrendatarioDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new ArrendatarioDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof ArrendatarioDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new ArrendatarioDTO());
	}

	@Test
	public void dosInstanciasSonDistintas() {
		ArrendatarioDTO obj2 = new ArrendatarioDTO(); assertNotSame(obj, obj2);
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
		System.err.println("Finalizando todas las pruebas de ArrendatarioDTO");
	}
}
