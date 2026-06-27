package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PaqueteTest {

	static int contador = 1;
	Paquete paquete;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Paquete");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Hola");
	}

	@Test
	public void constructorPorDefecto() {
		Paquete p = new Paquete();
		assertNull(p.getId());
		assertNull(p.getEstado());
	}

	
	

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Paquete");
	}
}
