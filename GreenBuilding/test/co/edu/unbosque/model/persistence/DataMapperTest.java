package co.edu.unbosque.model.persistence;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import co.edu.unbosque.model.Conjunto;
import co.edu.unbosque.model.ConjuntoDTO;
import co.edu.unbosque.model.Torre;
import co.edu.unbosque.model.TorreDTO;
import co.edu.unbosque.model.Apartamento;
import co.edu.unbosque.model.ApartamentoDTO;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import java.util.ArrayList;

public class DataMapperTest {

	static int contador = 1;

	@BeforeClass
	public static void antesDeTodo() {
		System.out.println("Iniciando pruebas de DataMapper");
	}

	@Before
	public void antesDeCadaPrueba() {
		System.out.println("Inicializando prueba numero " + contador);
	}

	@Test
	public void convertirConjuntoADTONoNulo() {
		Conjunto c = new Conjunto();
		assertNotNull(DataMapper.convertirConjuntoAConjuntoDTO(c));
	}

	@Test
	public void convertirConjuntoDTOAEntidadNoNulo() {
		ConjuntoDTO dto = new ConjuntoDTO();
		assertNotNull(DataMapper.convertirConjuntoDTOAConjunto(dto));
	}

	@Test
	public void convertirTorreADTONoNulo() {
		Torre t = new Torre();
		assertNotNull(DataMapper.convertirTorreATorreDTO(t));
	}

	@Test
	public void convertirTorreDTOAEntidadNoNulo() {
		TorreDTO dto = new TorreDTO();
		assertNotNull(DataMapper.convertirTorreDTOATorre(dto));
	}

	@Test
	public void convertirListaConjuntosNoNulo() {
		ArrayList<Conjunto> lista = new ArrayList<>();
		assertNotNull(DataMapper.convertirListaConjuntoAListaConjuntoDTO(lista));
	}

	@Test
	public void convertirListaConjuntosDTONoNulo() {
		ArrayList<ConjuntoDTO> lista = new ArrayList<>();
		assertNotNull(DataMapper.convertirListaConjuntoDTOAListaConjunto(lista));
	}

	@Test
	public void convertirApartamentoADTONoNulo() {
		Apartamento a = new Apartamento();
		assertNotNull(DataMapper.convertirApartamentoAApartamentoDTO(a));
	}

	@Test
	public void convertirApartamentoDTOAEntidadNoNulo() {
		ApartamentoDTO dto = new ApartamentoDTO();
		assertNotNull(DataMapper.convertirApartamentoDTOAApartamento(dto));
	}

	@Test
	public void convertirUsuarioADTONoNulo() {
		Usuario u = new Usuario();
		assertNotNull(DataMapper.convertirUsuarioAUsuarioDTO(u));
	}

	@Test
	public void convertirUsuarioDTOAEntidadNoNulo() {
		UsuarioDTO dto = new UsuarioDTO();
		assertNotNull(DataMapper.convertirUsuarioDTOAUsuario(dto));
	}

	@After
	public void despuesDeCadaPrueba() {
		System.out.println("Finalizando prueba numero: " + contador);
		contador++;
	}

	@AfterClass
	public static void despuesDeTodo() {
		System.err.println("Finalizando todas las pruebas de DataMapper");
	}
}
