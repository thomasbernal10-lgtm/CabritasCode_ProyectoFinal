package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroReciclajeDTOTest {

	static int contador = 1;
	RegistroReciclajeDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroReciclajeDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new RegistroReciclajeDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof RegistroReciclajeDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new RegistroReciclajeDTO());
	}

	@Test
	public void constructorConParametros() {
		RegistroReciclajeDTO r = new RegistroReciclajeDTO(5.0, 3.0, 2.0, 1.0, 4.0, 15.0); assertNotNull(r);
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
		System.err.println("Finalizando todas las pruebas de RegistroReciclajeDTO");
	}
}
