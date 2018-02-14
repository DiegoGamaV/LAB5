package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe SeguroValor representa o seguro por valor de uma Aposta
 *         assegurada. Cada Aposta assegurada, além de todos os atributos de
 *         uma Aposta, contém também o custo do seguro e o valor a ser assegurado
 *         caso a Aposta seja perdedora.
 *         
 * @since Parte 2
 */

public class SeguroValor extends Seguro {

	private int valorAssegurado;
	private int custo;
	
	/**
	 * Constrói um SeguroValor com um valor assegurado, um custo e um id.
	 * 
	 * @param valorAssegurado
	 * 			  Valor a ser retirado do caixa caso a Aposta seja perdedora.
	 * @param custo
	 * 			  Quantia paga ao Caixa para cadastrar este seguro.
	 * @param id
	 *            Identificador desta Aposta assegurada.
	 * @since Parte 2
	 */
	public SeguroValor(int valorAssegurado, int custo, int id) {
		super(id);
		isValid(valorAssegurado, custo);
		this.valorAssegurado = valorAssegurado;
		this.custo = custo;
	}
	
	/**
	 * Consulta o valor do SeguroValor da Aposta assegurada.
	 * 
	 * @return o valor assegurado deste SeguroValor.
	 * @since Parte 2
	 */
	public int getValorAssegurado() {
		return this.valorAssegurado;
	}
	
	/**
	 * Consulta o custo do SeguroValor da Aposta assegurada.
	 * 
	 * @return o custo deste SeguroValor.
	 * @since Parte 2
	 */
	public int getCusto() {
		return this.custo;
	}

	/**
	 * Método de checagem que avalia se o valor e o são válidos
	 * 
	 * @param valor
	 *            O valor assegurado a ser validado.
	 * @param custo
	 * 			  O custo do seguro a ser validado.
	 * @exception IllegalArgumentException
	 *                se o valor ou o custo for menor ou igual a zero.
	 * @since Parte 2
	 */
	private void isValid(int valor, int custo) {
		if (valor <= 0)
			throw new IllegalArgumentException("Valor assegurado nao pode ser negativa ou nula!");
		else if (custo <= 0)
			throw new IllegalArgumentException("Custo nao pode ser negativo ou nulo!");
	}

}
