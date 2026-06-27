package co.edu.unbosque.model.persistence;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHandlerTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de FileHandler");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void crearCarpetaPrincipalNoLanzaExcepcion() {
		try {
			FileHandler.crearCarpetaPrincipal();
			assertTrue(true);
		} catch (Exception e) {
			fail("No deberia lanzar excepcion: " + e.getMessage());
		}
	}

	@Test
	public void crearYEscribirArchivoNoLanzaExcepcion() {
		try {
			FileHandler.crearYEscribirArchivo("files/test_write.txt", "prueba");
			assertTrue(true);
		} catch (Exception e) {
			fail("No deberia lanzar excepcion: " + e.getMessage());
		}
	}

	@Test
	public void crearYLeerArchivoRetornaString() {
		try {
			FileHandler.crearYEscribirArchivo("files/test_read.txt", "contenido de prueba");
			String resultado = FileHandler.crearYLeerArchivo("files/test_read.txt");
			assertNotNull(resultado);
		} catch (Exception e) {
			fail("No deberia lanzar excepcion: " + e.getMessage());
		}
	}

	@Test
	public void crearYEscribirArchivoSerializadoNoLanzaExcepcion() {
		try {
			FileHandler.crearYEscribirArchivoSerializado("files/test_serial.dat", "objeto de prueba");
			assertTrue(true);
		} catch (Exception e) {
			fail("No deberia lanzar excepcion: " + e.getMessage());
		}

	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de FileHandler");
	}
}