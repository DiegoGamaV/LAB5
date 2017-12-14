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
		String mensagem = "Esperando que o �ndice do cen�rio criado seja 1.";
		assertEquals(mensagem, 1, controlador.addCenario("Cen�rio 1"));
	}

	@Test
	public void apostaAdicionada() {
		String mensagem = "Esperando conseguir adicionar aposta ao cenário adicionado.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previsão do Sikêira Júnior");
		assertEquals(mensagem, controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER"));
	}

	@Test
	public void cenarioNaoOcorreu() {
		String mensagem = "Esperando que o cenário tenha sido finalizado e que 10% do dinheiro da aposta perdida tenha sido adicionado ao dinheiro do caixa.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previsão do Sikêira Júnior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");	
		controlador1.finalizarCenario(numero, false);
		assertEquals(mensagem, 1005, controlador1.getDinheiroAtual());
	}

	@Test
	public void cenarioOcorreu() {
		String mensagem = "Esperando que o cenário tenha sido finalizado e que não haja mudança no dinheiro do caixa.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previsão do Sikêira Júnior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 1000, controlador1.getDinheiroAtual());
	}

}
