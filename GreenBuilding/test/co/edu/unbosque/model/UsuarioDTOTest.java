package co.edu.unbosque.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioDTOTest {

	static int contador = 1;
	UsuarioDTO obj;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de UsuarioDTO");
	}

	@Before
	public void antesDeCadaPrueba() {
		obj = new UsuarioDTO();
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void objetoNoNulo() {
		assertNotNull(obj);
	}

	@Test
	public void esInstanciaCorrecta() {
		assertTrue(obj instanceof UsuarioDTO);
	}

	@Test
	public void nuevaInstanciaNoNula() {
		assertNotNull(new UsuarioDTO());
	}

	@Test
	public void dosInstanciasSonDistintas() {
		UsuarioDTO obj2 = new UsuarioDTO(); assertNotSame(obj, obj2);
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
		System.err.println("Finalizando todas las pruebas de UsuarioDTO");
	}
}
