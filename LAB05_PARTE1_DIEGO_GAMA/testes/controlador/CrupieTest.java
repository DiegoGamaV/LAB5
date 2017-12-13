package controlador;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controladores.Crupie;
import modelos.*;

public class CrupieTest {

	private Crupie controlador;
	
	@Before
	public void setUp() {
		controlador = new Crupie(10000, 0.10);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void taxaNula() {
		Crupie controlador1 = new Crupie(500, 0.0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void taxaNegativa() {
		Crupie controlador1 = new Crupie(500, -10.0);
	}
	
	@Test
	public void cenarioAdicionado() {
		String mensagem = "Esperando que o índice do cenário criado seja 1.";
		assertEquals(mensagem, 1, controlador.addCenario("Cenário 1"));
	}
	

}
