package comparadores;

import java.util.Comparator;

import modelos.Cenario;

/**
 * @author Diego Alves Gama
 * 
 *         A classe ComparadorCenarioOrdemCadastro é o encapsulamento do método
 *         compare, que compara dois objetos do tipo cenário, priorizando o
 *         objeto com menor identificador.
 * 
 * @since Parte 3
 */
public class ComparadorCenarioOrdemCadastro implements Comparator<Cenario> {

	/**
	 * Compara dois objetos Cenario de acordo com o número de apostas de cada um.
	 * 
	 * @param o1
	 *            objeto Cenario 1 a ser comparado.
	 * @param o2
	 *            objeto Cenario 2 a ser comparado.
	 * @return -1 se o o1 este tiver menor identificador, e 1 se o2 se este tiver
	 *         menor identificador.
	 * @since Parte 3
	 */
	@Override
	public int compare(Cenario o1, Cenario o2) {
		if (o1.getId() < o2.getId())
			return -1;
		if (o2.getId() < o1.getId())
			return 1;
		return 0;
	}

}
