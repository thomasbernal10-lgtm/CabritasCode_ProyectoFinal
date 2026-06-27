package co.edu.unbosque.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.util.EmptyFieldException;
import co.edu.unbosque.util.InvalidFormatException;
import co.edu.unbosque.util.InvalidLengthException;
import co.edu.unbosque.util.InvalidDateException;
import co.edu.unbosque.util.PastDateException;
import co.edu.unbosque.util.InvalidIndexException;

/**
 * Pruebas unitarias para los metodos publicos del Controller que no dependen de
 * la vista: validaciones de campos y metodos auxiliares de mapeo/texto.
 *
 * <b>pre</b> El entorno de archivos debe estar inicializado antes de ejecutar
 * las pruebas. <br>
 * <b>post</b> Todos los archivos generados durante las pruebas son limpiados al
 * finalizar. <br>
 */
public class ControllerTest {

	static int contador = 1;
	static Controller controller;

	@BeforeClass
	public static void antesDeTodo() {
		FileHandler.crearCarpetaPrincipal();
		System.out.println("Iniciando pruebas de Controller");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de Controller");
	}

	@Test
	public void validarNombreValido() throws Exception {
		Controller c = new Controller();
		c.validarNombre("Carlos Perez");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarNombreVacio() throws Exception {
		Controller c = new Controller();
		c.validarNombre("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarNombreNulo() throws Exception {
		Controller c = new Controller();
		c.validarNombre(null);
	}

	@Test(expected = InvalidLengthException.class)
	public void validarNombreDemasiadoCorto() throws Exception {
		Controller c = new Controller();
		c.validarNombre("Ana");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarNombreConNumeros() throws Exception {
		Controller c = new Controller();
		c.validarNombre("Carlos123");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarNombreConCaracteresEspeciales() throws Exception {
		Controller c = new Controller();
		c.validarNombre("Carlos@#!");
	}

	@Test
	public void validarCedulaValida() throws Exception {
		Controller c = new Controller();
		c.validarCedula("1234567890");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarCedulaVacia() throws Exception {
		Controller c = new Controller();
		c.validarCedula("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarCedulaNula() throws Exception {
		Controller c = new Controller();
		c.validarCedula(null);
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCedulaConLetras() throws Exception {
		Controller c = new Controller();
		c.validarCedula("12AB567890");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCedulaDemasiadoCorta() throws Exception {
		Controller c = new Controller();
		c.validarCedula("123");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCedulaConEspacios() throws Exception {
		Controller c = new Controller();
		c.validarCedula("1234 5678");
	}

	@Test
	public void validarCorreoValido() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("usuario@correo.com");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarCorreoVacio() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarCorreoNulo() throws Exception {
		Controller c = new Controller();
		c.validarCorreo(null);
	}

	@Test(expected = InvalidLengthException.class)
	public void validarCorreoDemasiadoCorto() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("a@b.c");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCorreoSinArroba() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("usuariocorreo.com");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCorreoConEspacios() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("usuario @correo.com");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarCorreoSinDominio() throws Exception {
		Controller c = new Controller();
		c.validarCorreo("usuario@sindominio");
	}

	@Test
	public void validarTelefonoValido() throws Exception {
		Controller c = new Controller();
		c.validarTelefono("3001234567");
	}

	@Test
	public void validarTelefonoVacioPermitido() throws Exception {
		Controller c = new Controller();
		c.validarTelefono("");
	}

	@Test
	public void validarTelefonoNuloPermitido() throws Exception {
		Controller c = new Controller();
		c.validarTelefono(null);
	}

	@Test(expected = InvalidFormatException.class)
	public void validarTelefonoConLetras() throws Exception {
		Controller c = new Controller();
		c.validarTelefono("300ABC4567");
	}

	@Test(expected = InvalidLengthException.class)
	public void validarTelefonoDemasiadoCorto() throws Exception {
		Controller c = new Controller();
		c.validarTelefono("123");
	}

	@Test(expected = InvalidLengthException.class)
	public void validarTelefonoDemasiadoLargo() throws Exception {
		Controller c = new Controller();
		c.validarTelefono("12345678901234567");
	}

	@Test
	public void validarPlacaValida() throws Exception {
		Controller c = new Controller();
		c.validarPlaca("ABC123");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarPlacaVacia() throws Exception {
		Controller c = new Controller();
		c.validarPlaca("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarPlacaNula() throws Exception {
		Controller c = new Controller();
		c.validarPlaca(null);
	}

	@Test(expected = InvalidLengthException.class)
	public void validarPlacaDemasiadoCorta() throws Exception {
		Controller c = new Controller();
		c.validarPlaca("AB");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarPlacaConCaracteresEspeciales() throws Exception {
		Controller c = new Controller();
		c.validarPlaca("AB-123");
	}

	@Test
	public void validarFechaFutura() throws Exception {
		Controller c = new Controller();
		c.validarFecha("2099-12-31");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarFechaVacia() throws Exception {
		Controller c = new Controller();
		c.validarFecha("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarFechaNula() throws Exception {
		Controller c = new Controller();
		c.validarFecha(null);
	}

	@Test(expected = PastDateException.class)
	public void validarFechaPasada() throws Exception {
		Controller c = new Controller();
		c.validarFecha("2000-01-01");
	}

	@Test(expected = InvalidDateException.class)
	public void validarFechaFormatoInvalido() throws Exception {
		Controller c = new Controller();
		c.validarFecha("31/12/2099");
	}

	@Test
	public void validarFechaNeutralValida() throws Exception {
		Controller c = new Controller();
		c.validarFechaNeutral("2000-06-15");
	}

	@Test
	public void validarFechaNeutralVaciaPermitida() throws Exception {
		Controller c = new Controller();
		c.validarFechaNeutral("");
	}

	@Test
	public void validarFechaNeutralNulaPermitida() throws Exception {
		Controller c = new Controller();
		c.validarFechaNeutral(null);
	}

	@Test(expected = InvalidDateException.class)
	public void validarFechaNeutralFormatoInvalido() throws Exception {
		Controller c = new Controller();
		c.validarFechaNeutral("15-06-2000");
	}

	@Test
	public void validarHoraValida() throws Exception {
		Controller c = new Controller();
		c.validarHora("14:30", "inicio");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarHoraVacia() throws Exception {
		Controller c = new Controller();
		c.validarHora("", "inicio");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarHoraNula() throws Exception {
		Controller c = new Controller();
		c.validarHora(null, "inicio");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarHoraFormatoInvalido() throws Exception {
		Controller c = new Controller();
		c.validarHora("25:99", "inicio");
	}

	@Test
	public void validarMontoValido() throws Exception {
		Controller c = new Controller();
		c.validarMonto("150000");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarMontoVacio() throws Exception {
		Controller c = new Controller();
		c.validarMonto("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarMontoNulo() throws Exception {
		Controller c = new Controller();
		c.validarMonto(null);
	}

	@Test(expected = InvalidFormatException.class)
	public void validarMontoNoNumerico() throws Exception {
		Controller c = new Controller();
		c.validarMonto("cien pesos");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarMontoCero() throws Exception {
		Controller c = new Controller();
		c.validarMonto("0");
	}

	@Test(expected = InvalidFormatException.class)
	public void validarMontoNegativo() throws Exception {
		Controller c = new Controller();
		c.validarMonto("-500");
	}

	@Test
	public void validarIndiceValido() throws Exception {
		Controller c = new Controller();
		c.validarIndice("3");
	}

	@Test
	public void validarIndiceCero() throws Exception {
		Controller c = new Controller();
		c.validarIndice("0");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarIndiceVacio() throws Exception {
		Controller c = new Controller();
		c.validarIndice("");
	}

	@Test(expected = EmptyFieldException.class)
	public void validarIndiceNulo() throws Exception {
		Controller c = new Controller();
		c.validarIndice(null);
	}

	@Test(expected = InvalidIndexException.class)
	public void validarIndiceNegativo() throws Exception {
		Controller c = new Controller();
		c.validarIndice("-1");
	}

	@Test(expected = InvalidIndexException.class)
	public void validarIndiceNoNumerico() throws Exception {
		Controller c = new Controller();
		c.validarIndice("abc");
	}

	@Test
	public void obtenerIdConjuntoSeleccionadoValido() {
		Controller c = new Controller();
		String id = c.obtenerIdConjuntoSeleccionado("CON001 - Conjunto Los Pinos");
		assertEquals("CON001", id);
	}

	@Test
	public void obtenerIdConjuntoSeleccionadoNulo() {
		Controller c = new Controller();
		assertNull(c.obtenerIdConjuntoSeleccionado(null));
	}

	@Test
	public void obtenerIdConjuntoSeleccionadoPlaceholder() {
		Controller c = new Controller();
		assertNull(c.obtenerIdConjuntoSeleccionado("-- Seleccione un conjunto --"));
	}

	@Test
	public void obtenerIdTorreSeleccionadaValido() {
		Controller c = new Controller();
		String id = c.obtenerIdTorreSeleccionada("TOR001 - Torre Norte (Conjunto Los Pinos)");
		assertEquals("TOR001", id);
	}

	@Test
	public void obtenerIdTorreSeleccionadaNulo() {
		Controller c = new Controller();
		assertNull(c.obtenerIdTorreSeleccionada(null));
	}

	@Test
	public void obtenerIdTorreSeleccionadaPlaceholder() {
		Controller c = new Controller();
		assertNull(c.obtenerIdTorreSeleccionada("-- Seleccione una torre --"));
	}

	@Test
	public void mapearTipoReporteAdministrativo() {
		Controller c = new Controller();
		assertEquals("ADMINISTRATIVO", c.mapearTipoReporte("Ocupacion de apartamentos"));
		assertEquals("ADMINISTRATIVO", c.mapearTipoReporte("Vehiculos registrados"));
	}

	@Test
	public void mapearTipoReporteOperativo() {
		Controller c = new Controller();
		assertEquals("OPERATIVO", c.mapearTipoReporte("Reservas de zonas comunes"));
		assertEquals("OPERATIVO", c.mapearTipoReporte("Paquetes recibidos y entregados"));
	}

	@Test
	public void mapearTipoReporteFinanciero() {
		Controller c = new Controller();
		assertEquals("FINANCIERO", c.mapearTipoReporte("Pagos realizados"));
		assertEquals("FINANCIERO", c.mapearTipoReporte("Multas generadas"));
	}

	@Test
	public void mapearTipoReporteConvivencia() {
		Controller c = new Controller();
		assertEquals("CONVIVENCIA", c.mapearTipoReporte("Incidentes por tipo y gravedad"));
	}

	@Test
	public void mapearTipoReporteAmbiental() {
		Controller c = new Controller();
		assertEquals("AMBIENTAL", c.mapearTipoReporte("Indicadores de sostenibilidad"));
		assertEquals("AMBIENTAL", c.mapearTipoReporte("Campanas ambientales y participacion"));
	}

	@Test
	public void mapearTipoReporteNulo() {
		Controller c = new Controller();
		assertEquals("ADMINISTRATIVO", c.mapearTipoReporte(null));
	}

	@Test
	public void mapearTipoReporteDesconocido() {
		Controller c = new Controller();
		assertEquals("ADMINISTRATIVO", c.mapearTipoReporte("Algo que no existe"));
	}
}