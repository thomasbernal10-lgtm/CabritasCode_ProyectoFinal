package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReservaTest {

	static int contador = 1;
	Reserva reserva;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de Reserva");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Si");
	}

	@Test
	public void constructorPorDefecto() {
		Reserva r = new Reserva();
		assertNull(r.getId());
		assertNull(r.getEstado());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Reserva");
	}
}
