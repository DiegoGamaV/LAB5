package modelos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioBonusTest {

	private CenarioBonus cenario;
	
	@Before
	public void setUp() throws Exception {
		cenario = new CenarioBonus("Diego vai passar em Cálculo 1 neste período", 5000);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void bonusNegativo() {
		CenarioBonus cenario1 = new CenarioBonus("Gabriel será acordado sob ataque da sua rinite alérgica amanhã", -1);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void bonusNulo() {
		CenarioBonus cenario1 = new CenarioBonus("Gabriel será acordado sob ataque da sua rinite alérgica amanhã", 0);
	}

	@Test
	public void representacoesIguais() {
		String mensagem = "Esperando que a descrição e o toString do objeto sejam iguais.";
		String descricao = "Diego vai passar em Cálculo 1 neste período - Não finalizado - 5000";
		assertEquals(mensagem, descricao, cenario.toString());
	}

}
