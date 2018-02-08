package controlador;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controladores.Crupie;

/**
 * @author Diego Alves Gama
 * 
 *         Testes de Unidade para a classe controladora Crupie.
 * @since Parte 1
 */
public class CrupieTest {

	private Crupie controlador;
	private String cenario1;
	private String cenario2;

	@Before
	public void setUp() {
		controlador = new Crupie(10000, 0.10);
		cenario1 = "O calend�rio da UFCG vai normalizar";
		cenario2 = "A nova grade vai se provar melhor que a anterior";
		controlador.addCenario(cenario1);
		controlador.addCenario(cenario2);
	}

	// --- Testes de Construtor ---

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void caixaNegativo() {
		Crupie controlador1 = new Crupie(-500, 0.10);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void taxaNegativa() {
		Crupie controlador1 = new Crupie(500, -0.10);
	}

	// --- Testes de Cria��o de Cen�rios

	@Test
	public void cenarioAdicionado() {
		String mensagem = "Esperando que o �ndice do cen�rio criado seja 1.";
		assertEquals(mensagem, 3, controlador.addCenario("Cen�rio 3"));
	}

	// --- Testes de Cria��o de Apostas ---

	@Test
	public void apostaAdicionada() {
		String mensagem = "Esperando conseguir adicionar aposta ao cen�rio adicionado.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		assertTrue(mensagem, controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void apostaCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(0, "Gabriel de Sousa", 50, "VAI ACONTECER");
	}

	@Test(expected = IllegalArgumentException.class)
	public void apostaCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(-1, "Gabriel de Sousa", 50, "VAI ACONTECER");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void apostaCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(99, "Gabriel de Sousa", 50, "VAI ACONTECER");
	}

	// --- Testes de Finaliza��o de Cen�rio ---

	@Test
	public void cenarioNaoOcorreu() {
		String mensagem = "Esperando que o cen�rio tenha sido finalizado e que 10% do dinheiro da aposta perdida tenha sido adicionado ao dinheiro do caixa.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(numero, false);
		assertEquals(mensagem, 1005, controlador1.getDinheiroAtual());
	}

	@Test
	public void cenarioOcorreu() {
		String mensagem = "Esperando que o cen�rio tenha sido finalizado e que n�o haja mudan�a no dinheiro do caixa.";
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 1000, controlador1.getDinheiroAtual());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void cenarioJaFinalizado() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.finalizarCenario(numero, false);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void cenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(99, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(0, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Previs�o do Sik�ira J�nior");
		controlador1.addAposta(numero, "Gabriel de Sousa", 50, "VAI ACONTECER");
		controlador1.finalizarCenario(-1, true);
	}

	// --- Testes de Listagem de Cen�rios ---

	@Test
	public void listagemCenarioCorreta() {
		String mensagem = "Esperando que ambas as listagens sejam iguais";
		String lista = "1 - " + cenario1 + " - Nao finalizado" + System.lineSeparator() + "2 - " + cenario2
				+ " - Nao finalizado" + System.lineSeparator();
		assertEquals(mensagem, lista, controlador.listarCenarios());
	}

	// --- Testes de Exibi��o de Cen�rios ---

	@Test
	public void cenarioExibido() {
		String mensagem = "Esperando conseguir exibir corretamente o cen�rio";
		String descricao = "1 - " + cenario1 + " - Nao finalizado";
		assertEquals(mensagem, descricao, controlador.exibirCenario(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void cenarioZeroInvalido() {
		controlador.exibirCenario(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cenarioNegativoInvalido() {
		controlador.exibirCenario(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void cenarioInexistenteInvalido() {
		controlador.exibirCenario(99);
	}

	// --- Testes do Caixa ---

	@Test
	public void caixaIgual() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que as quantias sejam iguais.";
		assertEquals(mensagem, 1000, controlador1.getDinheiroAtual());
	}

	@Test
	public void acrescimoNoCaixa() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que o dinheiro no caixa ap�s a aposta tenha incrementado em 50 (10% de 500)";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 1050, controlador1.getDinheiroAtual());
	}
	
	@Test
	public void acrescimoSeguro() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que haja um acr�scimo de 50 centavos no caixa (custo do seguro)";
		int numero = controlador1.addCenario("Vou terminar isso um dia");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER", 200, 50);
		assertEquals(mensagem, 1050, controlador1.getDinheiroAtual());
	}
	
	@Test
	public void decrescimoAsseguradoValor() {
		Crupie controlador1 = new Crupie(950, 0.10);
		String mensagem = "Esperando que haja um decr�scimo de 150 (+50 custo, -200 valor assegurado)";
		int numero = controlador1.addCenario("Vou terminar isso um dia");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER", 200, 50);
		controlador1.finalizarCenario(numero, false);
		controlador1.getDinheiroVencedores(numero);
		assertEquals(mensagem, 850, controlador1.getDinheiroAtual());
	}
	
	@Test
	public void decrescimoAsseguradoTaxa() {
		Crupie controlador1 = new Crupie(950, 0.10);
		String mensagem = "Esperando que n�o haja decr�scimo (+50 custo, -50 valor assegurado, isto �, 10% de 500)";
		int numero = controlador1.addCenario("Vou terminar isso um dia");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER", 0.10, 50);
		controlador1.finalizarCenario(numero, false);
		controlador1.getDinheiroVencedores(numero);
		assertEquals(mensagem, 1000, controlador1.getDinheiroAtual());
	}

	// --- Testes do C�lculo de Dinheiro para o Caixa ---

	@Test
	public void dinheiroCorreto() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que o dinheiro das apostas seja 50";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 50, controlador1.getDinheiroParaCaixa(numero));
	}

	@Test(expected = IllegalArgumentException.class)
	public void caixaDeCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroParaCaixa(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void caixaDeCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroParaCaixa(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void caixaDeCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroParaCaixa(99);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void caixaDeCenarioNaoFinalizado() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.getDinheiroParaCaixa(1);
	}

	// --- Testes do C�lculo de Rateio ---

	@Test
	public void rateioCorreto() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que o rateio seja 450, pois h� o desconto de 50 para o caixa";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 450, controlador1.getDinheiroVencedores(numero));
	}
	
	@Test
	public void rateioComBonus() {
		Crupie controlador1 = new Crupie(10000, 0.10);
		String mensagem = "Esperando que o rateio seja 550, pois � o 450 + 100 do b�nus";
		int numero = controlador1.addCenario("Vou terminar isso um dia", 100);
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		assertEquals(mensagem, 550, controlador1.getDinheiroVencedores(numero));
	}

	@Test(expected = IllegalArgumentException.class)
	public void rateioDeCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroVencedores(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rateioDeCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroVencedores(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void rateioDeCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.finalizarCenario(numero, true);
		controlador1.getDinheiroVencedores(99);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void rateioDeCenarioNaoFinalizado() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.getDinheiroVencedores(numero);
	}

	// --- Testes para o Total de Apostas ---

	@Test
	public void totalCorreto() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que a soma do dinheiro de todas as apostas seja 1000.";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		assertEquals(mensagem, 1000, controlador1.getTotalApostas(numero));
	}

	@Test(expected = IllegalArgumentException.class)
	public void totalCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.getTotalApostas(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void totalCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.getTotalApostas(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void totalCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.getTotalApostas(99);
	}

	// --- Testes para Contagem de Apostas ---

	@Test
	public void quantidadeCorreta() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que a quantidade de apostas seja 2.";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		assertEquals(mensagem, 2, controlador1.contarApostas(numero));
	}

	@Test(expected = IllegalArgumentException.class)
	public void quantidadeCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.contarApostas(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void quantidadeCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.contarApostas(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void quantidadeCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.contarApostas(99);
	}

	// --- Testes de Listagem de Apostas ---

	@Test
	public void listagemApostasCorreta() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		String mensagem = "Esperando que ambas as listagens sejam iguais";
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		String listagem = "Otimismo - 500 - VAI ACONTECER" + System.lineSeparator()
				+ "Pessimismo - 500 - N VAI ACONTECER" + System.lineSeparator();
		assertEquals(mensagem, listagem, controlador1.listarApostas(numero));
	}

	@Test(expected = IllegalArgumentException.class)
	public void listagemApostasCenarioZero() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.listarApostas(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void listagemApostasCenarioNegativo() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.listarApostas(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void listagemApostasCenarioInexistente() {
		Crupie controlador1 = new Crupie(1000, 0.10);
		int numero = controlador1.addCenario("Eu vou terminar isso hoje!");
		controlador1.addAposta(numero, "Otimismo", 500, "VAI ACONTECER");
		controlador1.addAposta(numero, "Pessimismo", 500, "N VAI ACONTECER");
		controlador1.listarApostas(99);
	}

}
