package modelos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ApostaTest {
	
	private Aposta aposta1;

	@Before
	public void setUp(){
		aposta1 = new Aposta("Diego Gama", 10000, "VAI ACONTECER");
	}

	// --- Testes de Construtor ---
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void apostadorVazio() {
		Aposta aposta = new Aposta("", 2000, "VAI ACONTECER");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void apostadorNulo() {
		Aposta aposta = new Aposta(null, 0, "n vai acontecer");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void valorZero() {
		Aposta aposta = new Aposta("Vinicius Farias", 0, "vai acontecer");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void valorNegativo() {
		Aposta aposta = new Aposta("Arthur Ferrão", -450, "N VAI ACONTECER");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void previsaoVazia() {
		Aposta aposta = new Aposta("Lorena Nascimento", 10000, "");
	}

	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void previsaoNula() {
		Aposta aposta = new Aposta("Lucas Kellorran", 5000, null);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void previsaoInvalida() {
		Aposta aposta = new Aposta("Gabriel de Sousa", 4000, "Nunca nem vi, sei nem o que é isso.");
	}
	
	// --- Teste de toString ---
	
	@Test
	public void toStringIguais() {
		Aposta aposta2 = new Aposta("Diego Gama", 10000, "  vai acontecer  ");
		String mensagem = "Esperando que as descrições sejam iguais.";
		assertEquals(mensagem, aposta1.toString(), aposta2.toString());
	}

}
