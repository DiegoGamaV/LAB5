package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Caixa representa o Caixa do sistema, contendo a sua quantia
 *         armazenada em centavos e a porcentagem de ganho sob apostas em
 *         formato decimal.
 * @since Parte 1
 */
public class Caixa {

	private int dinheiro;
	private double taxa;

	/**
	 * Constr�i um Caixa com dinheiro inicial e taxa de ganho especificados.
	 * 
	 * @param dinheiro
	 *            Dinheiro inicial do Caixa em centavos.
	 * @param taxa
	 *            Porcentagem de ganho sob apostas perdedoras em formato decimal.
	 * @since Parte 1
	 */

	public Caixa(int dinheiro, double taxa) {
		this.dinheiro = dinheiro;
		this.taxa = taxa;
	}

	/**
	 * Retorna o dinheiro atual do Caixa.
	 * 
	 * @return o dinheiro atual do Caixa.
	 * @since Parte 1
	 */
	public int getDinheiro() {
		return this.dinheiro;
	}

	/**
	 * Adiciona o dinheiro passado ao Caixa.
	 * 
	 * @param dinheiro
	 *            Dinheiro em centavos a ser adicionado � quantia total do Caixa.
	 * @since Parte 1
	 */
	public void addDinheiro(int dinheiro) {
		this.dinheiro += dinheiro;
	}
	
	public void subtractDinheiro(int dinheiro) {
		this.dinheiro -= dinheiro;
	}
	

	/**
	 * Retorna a taxa cobrada sob apostas perdedoras em formato decimal.
	 * 
	 * @return a taxa cobrada pelo Caixa.
	 * @since Parte 1
	 */
	public double getTaxa() {
		return this.taxa;
	}

	/**
	 * Calcula o hash code deste Caixa.
	 * 
	 * @return o hash code calculado.
	 * @since Parte 1
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dinheiro;
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Checa se o objeto passado � considerado equivalente a este Caixa.
	 * 
	 * @return true se o objeto passado for considerado equivalente a este Caixa, e
	 *         false caso o contr�rio.
	 * @since Parte 1
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caixa other = (Caixa) obj;
		if (dinheiro != other.dinheiro)
			return false;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		return true;
	}

}
