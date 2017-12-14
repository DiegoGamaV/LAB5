package modelos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTest {

	private Cenario cenario1;
	private Cenario cenario2;

	@Before
	public void setUp() {
		cenario1 = new Cenario("Diego vai pagar LP2 com 10 na m�dia.");
		cenario2 = new Cenario("A Valve vai lan�ar Half-Life 3");
		cenario2.addAposta("Diego Gama", 5000, "N VAI ACONTECER"); // a valve n�o conhece o n�mero 3
		cenario2.addAposta("Mikael Amaral", 5000, "VAI ACONTECER"); // eu ainda tenho esperan�a
	}

	// --- Testes de Construtor ---

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void descricaoVazia() {
		Cenario cenario = new Cenario("");
	}

	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void descricaoNula() {
		Cenario cenario = new Cenario(null);
	}

	@Test
	public void estadoPadrao() {
		String mensagem = "Esperando que os estados sejam iguais.";
		assertEquals(mensagem, "Nao finalizado", cenario1.getEstado());
	}

	@Test
	public void estadoMudado() {
		Cenario cenario = new Cenario("Diego vai ganhar uma c�pia de Overwatch do professor Mateus Gaud�ncio");
		String mensagem = "Esperando que o estado tenha sido mudado para 'Finalizado (ocorreu)'";
		cenario.setEstado("Finalizado (ocorreu)");
		assertEquals(mensagem, "Finalizado (ocorreu)", cenario.getEstado());
	}

	@Test(expected = IllegalArgumentException.class)
	public void estadoInvalido() {
		Cenario cenario = new Cenario("Diego vai ganhar uma c�pia de Overwatch do professor Mateus Gaud�ncio");
		cenario.setEstado("GANHOU FOI DUAS");
	}

	@Test
	public void adicionarAposta() {
		String mensagem = "Esperando que seja poss�vel adicionar esta aposta ao cen�rio";
		assertTrue(mensagem, cenario1.addAposta("L�via Sampaio", 10000, "VAI ACONTECER"));
	}

	@Test
	public void numeroDeApostas() {
		String mensagem = "Esperando que a quantidade de apostas seja igual a 2";
		assertEquals(mensagem, 2, cenario2.contarApostas());
	}

	@Test
	public void apostasIguais() {
		String mensagem = "Esperando que as representa��es textuais das apostas sejam iguais";
		String representacao = "Diego Gama - 5000 - N VAI ACONTECER" + System.lineSeparator()
				+ "Mikael Amaral - 5000 - VAI ACONTECER" + System.lineSeparator();
		assertEquals(mensagem, representacao, cenario2.exibirApostas());
	}
	
	@Test
	public void dinheiroTotalIgual() {
		String mensagem = "Esperando que a quantia total em centavos seja a mesma";
		assertEquals(mensagem, 10000, cenario2.calcularValorTotal());
	}
	
	@Test
	public void dinheirosPerdedoresIguais() {
		String mensagem = "Esperando que a quantia retornada seja 5000";
		cenario2.setEstado("Finalizado (ocorreu)");
		assertEquals(mensagem, 5000, cenario2.calcularDinheiro());
	}
	
	@Test
	public void representacoesIguais() {
		String mensagem = "Esperando que os toStrings sejam iguais";
		Cenario cenario = new Cenario("Diego Gama vai tirar 10 nesse LAB");
		assertEquals(mensagem, "Diego Gama vai tirar 10 nesse LAB - Nao finalizado", cenario.toString());
	}

}
