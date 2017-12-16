package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Aposta representa uma Aposta realizada sob uma determinada
 *         situação. Uma Aposta contém o nome do apostador que a realizou, a
 *         quantia apostada em centavos, e a previsão sobre a fatalidade da
 *         situação.
 * @since Parte 1
 */
public class Aposta {

	private String apostador;
	private int valor;
	private String previsao;

	/**
	 * Constrói uma Aposta com o nome do apostador, valor e previsão especificados.
	 * Os únicos valores permitidos para uma previsão são "VAI ACONTECER" e "N VAI
	 * ACONTECER". A quantia apostada deve ser positiva. Valores nulos, vazios ou
	 * negativos não são aceitos.
	 * 
	 * 
	 * @param apostador
	 *            Nome do apostador responsável pela Aposta.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Estimativa sobre a fatalidade do Cenario sob o qual a Aposta é
	 *            realizada.
	 * @since Parte 1
	 */
	public Aposta(String apostador, int valor, String previsao) {
		isEmpityOrNull(apostador, previsao);
		isValid(previsao, valor);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
	}

	/**
	 * Método de checagem que avalia se os valores passados são válidos. Os únicos
	 * valores válidos para previsao são 'VAI ACONTECER' e 'N VAI ACONTECER'. As
	 * únicas quantias válidas para valor são as positivas.
	 * 
	 * @param previsao
	 *            Previsao da Aposta a ser validada.
	 * @param valor
	 *            Quantia da Aposta a ser validada.
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de previsao diferente de 'VAI ACONTECER' e 'N VAI
	 *                ACONTECER'. </i> <i> Quantia de valor menor ou igual a 0. </i>
	 *                </nl>
	 * @since Parte 1
	 */
	private void isValid(String previsao, int valor) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER")
				&& !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
	}

	/**
	 * Método de checagem que avalia se os valores passados são vazios ou nulos.
	 * 
	 * @param previsao
	 *            Previsao da Aposta a ser validada.
	 * @param valor
	 *            Quantia da Aposta a ser validada.
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de previsao é composto apenas de espaços vazios.
	 *                </i> <i> Valor de apostador é composto apenas de espaços
	 *                vazios. </i>
	 *                </nl>
	 * @exception NullPointerException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> O valor de previsao é null. </i> <i> O valor de apostador
	 *                é null. </i>
	 *                </nl>
	 * @since Parte 1
	 */
	private void isEmpityOrNull(String apostador, String previsao) {
		if (apostador == null)
			throw new NullPointerException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if (previsao == null)
			throw new NullPointerException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		if (apostador.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
	}

	/**
	 * Consulta a quantia registrada nesta Aposta
	 * 
	 * @return a quantia em centavos desta Aposta.
	 * @since Parte 1
	 */
	public int getValor() {
		return this.valor;
	}

	/**
	 * Consulta a previsao estabelecida nesta Aposta
	 * 
	 * @return o campo previsao.
	 * @since Parte 1
	 */
	public String getPrevisao() {
		return this.previsao;
	}

	/**
	 * Retorna uma representação textual de uma Aposta.
	 * 
	 * @return a String que representa esta Aposta.
	 * @since Parte 1
	 */
	@Override
	public String toString() {
		return this.apostador + " - " + this.valor + " - " + this.previsao;
	}

	/**
	 * Calcula o hash code deste objeto Aposta.
	 * 
	 * @return o hash code calculado.
	 * @since Parte 1
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apostador.hashCode();
		result = prime * result + previsao.hashCode();
		result = prime * result + valor;
		return result;
	}

	/**
	 * Avalia se o objeto passado é equivalente a esta Aposta.
	 * 
	 * @return true se o objeto for considerado equivalente a esta Aposta, e false
	 *         caso o contrário.
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
		Aposta other = (Aposta) obj;
		if (!apostador.equals(other.apostador))
			return false;
		if (!previsao.equals(other.previsao))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

}
