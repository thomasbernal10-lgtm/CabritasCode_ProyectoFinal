package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotificacionTest {

	static int contador = 1;
	Notificacion notificacion;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Notificacion");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Prueba");
	}

	@Test
	public void constructorPorDefecto() {
		Notificacion n = new Notificacion();
		assertNull(n.getId());
		assertNull(n.getTipo());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Notificacion");
	}
}
