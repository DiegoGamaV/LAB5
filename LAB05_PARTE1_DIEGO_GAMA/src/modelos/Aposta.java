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
	private Seguro seguro;
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
	 *            Estimativa sobre a fatalidade do Cenario sob o qual a Aposta ï¿½
	 *            realizada.
	 * @since Parte 1
	 */
	public Aposta(String apostador, int valor, String previsao) {
		isEmpityOrNull(apostador, previsao);
		isValid(previsao, valor);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
		this.seguro = new Seguro();
	}
	
	public Aposta(String apostador, int valor, String previsao, int valorAssegurado, int custo, int id) {
		isEmpityOrNullValor(apostador, previsao);
		isValid(previsao, valor, custo, valorAssegurado);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
		this.seguro = new SeguroValor(valorAssegurado, custo, id);
	}
	
	public Aposta(String apostador, int valor, String previsao, double taxa, int custo, int id) {
		isEmpityOrNullTaxa(apostador, previsao);
		isValid(previsao, valor, custo, taxa);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
		this.seguro = new SeguroTaxa(taxa, custo, id);
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
	 * Método de checagem que avalia se os valores passados são válidos. Os únicos
	 * valores válidos para previsao são 'VAI ACONTECER' e 'N VAI ACONTECER'. As
	 * únicas quantias válidas para valor são as positivas.
	 * 
	 * @param previsao
	 *            Previsao da Aposta a ser validada.
	 * @param valor
	 *            Quantia da Aposta a ser validada.
	 * @param custo
	 * 			  Custo do seguro da Aposta a ser validado.
	 * @param taxa
	 * 			  Taxa do seguro da Aposta a ser validada.           
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de previsao diferente de 'VAI ACONTECER' e 'N VAI
	 *                ACONTECER'. </i> <i> Quantia de valor menor ou igual a 0. </i>
	 *                <i> Custo menor ou igual a 0.</i> <i> Taxa menor ou igual a 0.0</i>
	 *                </nl>
	 * @since Parte 2
	 */
	private void isValid(String previsao, int valor, int custo, double taxa) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER")
				&& !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		if (custo <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Custo nao pode ser menor ou igual a zero");
		if (taxa <= 0.0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
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
	 * @param custo
	 * 			  Custo do seguro da Aposta a ser validado.
	 * @param valorAssegurado
	 * 			  Valor do seguro da Aposta a ser validada.           
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de previsao diferente de 'VAI ACONTECER' e 'N VAI
	 *                ACONTECER'. </i> <i> Quantia de valor menor ou igual a 0. </i>
	 *                <i> Custo menor ou igual a 0.</i> <i> valorAssegurado menor ou igual a 0</i>
	 * @since Parte 2
	 */
	private void isValid(String previsao, int valor, int custo, int valorAssegurado) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER")
				&& !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		if (custo <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Custo nao pode ser menor ou igual a zero");
		if (valorAssegurado <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
	}

	/**
	 * Método de checagem que avalia se os valores passados são vazios ou nulos para uma Aposta.
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
	 * Método de checagem que avalia se os valores passados são vazios ou nulos para uma Aposta assegurada por valor.
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
	 * @since Parte 2
	 */
	private void isEmpityOrNullValor(String apostador, String previsao) {
		if (apostador == null)
			throw new NullPointerException("Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		if (previsao == null)
			throw new NullPointerException("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		if (apostador.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
	}
	
	/**
	 * Método de checagem que avalia se os valores passados são vazios ou nulos para uma Aposta assegurada por taxa.
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
	 * @since Parte 2
	 */
	private void isEmpityOrNullTaxa(String apostador, String previsao) {
		if (apostador == null)
			throw new NullPointerException("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		if (previsao == null)
			throw new NullPointerException("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		if (apostador.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
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
	 * Consulta o objeto Seguro desta Aposta.
	 * 
	 * @return o campo previsao.
	 * @since Parte 2
	 */
	public Seguro getSeguro() {
		return this.seguro;
	}
	
	/**
	 * Modifica o Seguro desta Aposta.
	 * 
	 * @return o campo previsao.
	 * @since Parte 2
	 */
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	/**
	 * Retorna uma representação textual de uma Aposta de acordo com o tipo do seu Seguro.
	 * 
	 * @return a String que representa esta Aposta.
	 * @since Parte 2
	 */
	@Override
	public String toString() {
		String descricao = this.apostador + " - " + this.valor + " - " + this.previsao;
		if (this.seguro instanceof SeguroValor) {
			descricao += " - ASSEGURADA (VALOR) - " + ((SeguroValor) this.seguro).getValorAssegurado();
		} else if (this.seguro instanceof SeguroTaxa) {
			descricao += " - ASSEGURADA (TAXA) - " + ((SeguroTaxa) this.seguro).getTaxa();
		}
		return descricao;
	}

	/**
	 * Calcula o hash code deste objeto Aposta.
	 * 
	 * @return o hash code calculado.
	 * @since Parte 2
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostador == null) ? 0 : apostador.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + ((seguro == null) ? 0 : seguro.hashCode());
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
		if (apostador == null) {
			if (other.apostador != null)
				return false;
		} else if (!apostador.equals(other.apostador))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (seguro == null) {
			if (other.seguro != null)
				return false;
		} else if (!seguro.equals(other.seguro))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
}
