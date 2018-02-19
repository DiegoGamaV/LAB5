package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe SeguroTaxa representa o seguro por taxa de uma Aposta
 *         assegurada. Cada Aposta assegurada, além de todos os atributos de
 *         uma Aposta, contém também o custo do seguro e o porcentagem do
 *         valor da aposta a ser assegurada caso a Aposta seja perdedora.
 *         
 * @since Parte 2
 */
public class SeguroTaxa extends Seguro {

	private double taxa;
	private int custo;
	
	/**
	 * Constr�i um SeguroValor com um valor assegurado, um custo e um id.
	 * 
	 * @param taxa
	 * 			  Taxa do valor da aposta a ser retirada do caixa caso a Aposta seja perdedora.
	 * @param custo
	 * 			  Quantia paga ao Caixa para cadastrar este seguro.
	 * @param id
	 *            Identificador desta Aposta assegurada.
	 * @since Parte 2
	 */
	public SeguroTaxa(double taxa, int custo, int id) {
		super(id);
		isValid(taxa, custo);
		this.taxa = taxa;
		this.custo = custo;
	}
	
	/**
	 * Consulta o valor do SeguroTaxa da Aposta assegurada.
	 * 
	 * @return a taxa assegurada deste SeguroTaxa.
	 * @since Parte 2
	 */
	public double getTaxa() {
		return this.taxa;
	}

	/**
	 * Consulta o custo do SeguroTaxa da Aposta assegurada.
	 * 
	 * @return o custo deste SeguroTaxa.
	 * @since Parte 2
	 */
	public int getCusto() {
		return this.custo;
	}
	
	/**
	 * Método de checagem que avalia se o valor e o são válidos
	 * 
	 * @param taxa
	 *            A taxa assegurada a ser validada.
	 * @param custo
	 * 			  O custo do seguro a ser validado.
	 * @exception IllegalArgumentException
	 *                se o valor ou o custo for menor ou igual a zero.
	 * @since Parte 2
	 */
	private void isValid(double taxa, int custo) {
		if (taxa <= 0.0)
			throw new IllegalArgumentException("Taxa não pode ser negativa ou nula!");
		else if (custo <= 0)
			throw new IllegalArgumentException("Custo não pode ser negativo ou nulo!");
	}
	
}
