package modelos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ApostaTest {
	
	private Aposta aposta1;
	private Aposta aposta2;

	@Before
	public void setUp(){
		aposta1 = new Aposta("Diego Gama", 10000, "VAI ACONTECER");
		aposta2 = new Aposta("Diego Gama", 10000, "  vai acontecer  ");
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
		Aposta aposta = new Aposta("Arthur Ferr�o", -450, "N VAI ACONTECER");
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
		Aposta aposta = new Aposta("Gabriel de Sousa", 4000, "Nunca nem vi, sei nem o que � isso.");
	}
	
	// --- Testes de toString --- 
	
	@Test
	public void toStringIguais() {
		String mensagem = "Esperando que as descri��es sejam iguais.";
		assertEquals(mensagem, aposta1.toString(), aposta2.toString());
	}
	
	@Test
	public void toStringDiferentes() {
		Aposta aposta3 = new Aposta("Mateus de Lima", 1000, "   n Vai acONTEcer  ");
		String mensagem = "Esperando que as descri��es sejam iguais.";
		assertNotEquals(mensagem, aposta1.toString(), aposta3.toString());
	}
	
	// --- Testes de Gets ---
	
	@Test
	public void valoresIguais() {
		String mensagem = "Esperando valores iguais.";
		assertEquals(mensagem, 10000, aposta1.getValor());
	}
	
	@Test
	public void previsoesIguais() {
		String mensagem = "Esperando previsoes iguais.";
		assertEquals(mensagem, "VAI ACONTECER", aposta1.getPrevisao());
	}
	
	// --- Testes do Equals ---
	
	@Test
	public void apostasIguais() {
		String mensagem = "Esperando que os objetos sejam iguais.";
		assertTrue(mensagem, aposta1.equals(aposta2));
	}
	
	@Test
	public void apostadorDiferentes() {
		Aposta aposta3 = new Aposta("Mateus de Lima", 10000, "VAI ACONTECER");
		String mensagem = "Esperando que os apostadores diferentes disparem False.";
		assertFalse(mensagem, aposta1.equals(aposta3));
	}
	
	@Test
	public void previsoesDiferentes() {
		Aposta aposta3 = new Aposta("Diego Gama", 10000, "N VAI ACONTECER");
		String mensagem = "Esperando que as previsoes diferentes disparem False.";
		assertFalse(mensagem, aposta1.equals(aposta3));
	}
	
	@Test
	public void valoresDiferentes() {
		Aposta aposta3 = new Aposta("Diego Gama", 10, "VAI ACONTECER");
		String mensagem = "Esperando que os sentavos diferentes disparem False.";
		assertFalse(mensagem, aposta1.equals(aposta3));
	}
	
	@Test
	public void objetoNulo() {
		String mensagem = "Esperando que os objetos sejam diferentes.";
		assertFalse(mensagem, aposta1.equals(null));
	}
	
	@Test
	public void mesmoObjeto() {
		String mensagem = "Esperando que o mesmo objeto dispare True.";
		assertTrue(mensagem, aposta1.equals(aposta1));
	}
	
	@Test
	public void classesDiferentes() {
		String mensagem = "Esperando que as classes disparem False.";
		assertFalse(mensagem, aposta1.equals(new Caixa(10000, 0.12)));
	}

}
