package comparadores;

import java.util.Comparator;

import modelos.Cenario;

/**
 * @author Diego Alves Gama
 * 
 *         A classe ComparadorCenarioNumApostas é o encapsulamento do método
 *         compare, que compara dois objetos do tipo cenário, priorizando o
 *         objeto com maior quantidade de apostas.
 * 
 * @since Parte 3
 */
public class ComparadorCenarioNumApostas implements Comparator<Cenario> {

	/**
	 * Compara dois objetos Cenario de acordo com o número de apostas de cada um.
	 * 
	 * @param o1
	 *            objeto Cenario 1 a ser comparado.
	 * @param o2
	 *            objeto Cenario 2 a ser comparado.
	 * @return -1 se o o1 tiver mais apostas, e 1 se o2 tiver mais apostas. Em caso
	 *         de empate, o critério de desempate é o identificador. -1 se o1 tiver
	 *         o menor identificador, e 1 se o2 tiver o menor identificador.
	 * @since Parte 3
	 */
	@Override
	public int compare(Cenario o1, Cenario o2) {
		if (o1.contarApostas() > o2.contarApostas())
			return -1;
		else if (o2.contarApostas() > o1.contarApostas())
			return 1;
		else if (o1.getId() < o2.getId())
			return -1;
		else
			return 1;
	}

}
