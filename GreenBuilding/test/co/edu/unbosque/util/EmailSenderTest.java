package co.edu.unbosque.util;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmailSenderTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de EmailSender");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void enviarCorreoDestinatarioVacioRetornaFalse() {
		boolean resultado = EmailSender.enviarCorreo("", "Asunto", "Cuerpo");
		assertFalse(resultado);
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de EmailSender");
	}
}