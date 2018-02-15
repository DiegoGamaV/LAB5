package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe CenarioBonus � utilizada para abstrair um Cenario especial,
 *         onde o fator b�nus em centavos � adicionado ao rateio no final do c�lculo.
 * @since Parte 2
 */
public class CenarioBonus extends Cenario {

	private int bonus;
	
	/**
	 * Constr�i um CenarioBonus com uma descri��o espec�fica, um estado padr�o como 'Nao
	 * finalizado' e um valor b�nus. Uma descri��o nula ou vazia n�o � aceita, e um b�nus
	 * menor ou igual a 0 n�o � aceito.
	 * 
	 * @param descricao
	 *            A descri��o sobre o que o cen�rio se trata.
	 * @param bonus
	 * 			  O valor b�nus a ser adicionado no rateio.
	 * @since Parte 2
	 */
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		isValid(bonus);
		this.bonus = bonus;
	}
	
	/**
	 * M�todo de checagem que avalia se o b�nus � v�lido.
	 * 
	 * @param bonus
	 *            O bonus do Cenario a ser avaliado.
	 * @exception IllegalArgumentException
	 *                se o bonus � menor ou igual a 0.
	 * @since Parte 2
	 */
	private void isValid(int bonus) {
		if (bonus <= 0)
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
	}
	
	/**
	 * Consulta o b�nus cadastrado neste CenarioBonus.
	 * 
	 * @return o b�nus deste CenarioBonus.
	 * @since Parte 2
	 */
	public int getBonus() {
		return this.bonus;
	}

	/**
	 * Calcula o hash code deste objeto.
	 * 
	 * @return o hash code computado.
	 * @since Parte 2
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bonus;
		return result;
	}

	/**
	 * Compara se os dois objetos s�o considerados equivalentes.
	 * 
	 * @param obj
	 *            O objeto a ser comparado com este CenarioBonus.
	 * @return true se o objeto for equivalente a este CenarioBonus, e false caso o
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CenarioBonus other = (CenarioBonus) obj;
		if (bonus != other.bonus)
			return false;
		return true;
	}
	
	/**
	 * Retorna a representa��o textual de um CenarioBonus.
	 * 
	 * @return a String que representa este CenarioBonus.
	 * @since Parte 2
	 */
	@Override
	public String toString() {
		return super.toString() + " - R$ " + String.format("%.2f", this.bonus/100.0);
	}

}
