package co.edu.unbosque.model;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistroVisitaTest {

	static int contador = 1;
	RegistroVisita registro;
	Visitante visitante;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de RegistroVisita");
	}

	@Before
	public void antesDeCadaPrueba() {
		visitante = new Visitante();
		visitante.setId("VIS-1");
		visitante.setNombre("Juan Rodríguez");

		registro = new RegistroVisita("RV-1", visitante, LocalDateTime.of(2026, 5, 10, 9, 0),
				LocalDateTime.of(2026, 5, 10, 11, 0), "VIG-1");
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void constructorPorDefecto() {
		RegistroVisita r = new RegistroVisita();
		assertNull(r.getId());
		assertNull(r.getVisitante());
	}

	@Test
	public void constructorCompleto() {
		assertEquals("RV-1", registro.getId());
		assertEquals("VIG-1", registro.getVigilanteRegistro());
	}

	@Test
	public void setYGetId() {
		registro.setId("RV-99");
		assertEquals("RV-99", registro.getId());
	}

	@Test
	public void setYGetVisitante() {
		assertEquals("VIS-1", registro.getVisitante().getId());
	}

	@Test
	public void setYGetHoraEntrada() {
		LocalDateTime hora = LocalDateTime.of(2026, 6, 1, 8, 30);
		registro.setHoraEntrada(hora);
		assertEquals(hora, registro.getHoraEntrada());
	}

	@Test
	public void setYGetHoraSalida() {
		LocalDateTime hora = LocalDateTime.of(2026, 6, 1, 10, 30);
		registro.setHoraSalida(hora);
		assertEquals(hora, registro.getHoraSalida());
	}

	@Test
	public void setYGetVigilanteRegistro() {
		registro.setVigilanteRegistro("VIG-2");
		assertEquals("VIG-2", registro.getVigilanteRegistro());
	}

	@Test
	public void toStringNoNulo() {
		assertNotNull(registro.toString());
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de RegistroVisita");
	}
}
