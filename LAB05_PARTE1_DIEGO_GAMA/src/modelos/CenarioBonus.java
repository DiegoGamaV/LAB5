package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe CenarioBonus é utilizada para abstrair um Cenario especial,
 *         onde o fator bônus em centavos é adicionado ao rateio no final do cálculo.
 * @since Parte 2
 */
public class CenarioBonus extends Cenario {

	private int bonus;
	
	/**
	 * Constrói um CenarioBonus com uma descrição específica, um estado padrão como 'Nao
	 * finalizado' e um valor bônus. Uma descrição nula ou vazia não é aceita, e um bônus
	 * menor ou igual a 0 não é aceito.
	 * 
	 * @param descricao
	 *            A descrição sobre o que o cenário se trata.
	 * @param bonus
	 * 			  O valor bônus a ser adicionado no rateio.
	 * @since Parte 2
	 */
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		isValid(bonus);
		this.bonus = bonus;
	}
	
	/**
	 * Método de checagem que avalia se o bônus é válido.
	 * 
	 * @param bonus
	 *            O bonus do Cenario a ser avaliado.
	 * @exception IllegalArgumentException
	 *                se o bonus é menor ou igual a 0.
	 * @since Parte 2
	 */
	private void isValid(int bonus) {
		if (bonus <= 0)
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
	}
	
	/**
	 * Consulta o bônus cadastrado neste CenarioBonus.
	 * 
	 * @return o bônus deste CenarioBonus.
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
	 * Compara se os dois objetos são considerados equivalentes.
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
	 * Retorna a representação textual de um CenarioBonus.
	 * 
	 * @return a String que representa este CenarioBonus.
	 * @since Parte 2
	 */
	@Override
	public String toString() {
		return super.toString() + " - R$ " + String.format("%.2f", this.bonus/100.0);
	}

}
