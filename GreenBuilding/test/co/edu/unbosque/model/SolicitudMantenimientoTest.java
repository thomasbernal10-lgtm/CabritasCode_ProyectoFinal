package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolicitudMantenimientoTest {

	static int contador = 1;
	SolicitudMantenimiento solicitud;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de SolicitudMantenimiento");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Hola");
	}

	@Test
	public void constructorPorDefecto() {
		SolicitudMantenimiento s = new SolicitudMantenimiento();
		assertNull(s.getId());
		assertNull(s.getTipo());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de SolicitudMantenimiento");
	}
}
