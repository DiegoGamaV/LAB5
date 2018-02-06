package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Aposta representa uma Aposta realizada sob uma determinada
 *         situa��o. Uma Aposta cont�m o nome do apostador que a realizou, a
 *         quantia apostada em centavos, e a previs�o sobre a fatalidade da
 *         situa��o.
 * @since Parte 1
 */
public class Aposta {

	private String apostador;
	private int valor;
	private Seguro seguro;
	private String previsao;

	/**
	 * Constr�i uma Aposta com o nome do apostador, valor e previs�o especificados.
	 * Os �nicos valores permitidos para uma previs�o s�o "VAI ACONTECER" e "N VAI
	 * ACONTECER". A quantia apostada deve ser positiva. Valores nulos, vazios ou
	 * negativos n�o s�o aceitos.
	 * 
	 * 
	 * @param apostador
	 *            Nome do apostador respons�vel pela Aposta.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Estimativa sobre a fatalidade do Cenario sob o qual a Aposta �
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
		isEmpityOrNull(apostador, previsao);
		isValid(previsao, valor, custo, valorAssegurado);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
		this.seguro = new SeguroValor(valorAssegurado, custo, id);
	}
	
	public Aposta(String apostador, int valor, String previsao, double taxa, int custo, int id) {
		isEmpityOrNull(apostador, previsao);
		isValid(previsao, valor, custo, taxa);
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
		this.seguro = new SeguroTaxa(taxa, custo, id);
	}

	/**
	 * M�todo de checagem que avalia se os valores passados s�o v�lidos. Os �nicos
	 * valores v�lidos para previsao s�o 'VAI ACONTECER' e 'N VAI ACONTECER'. As
	 * �nicas quantias v�lidas para valor s�o as positivas.
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
	
	private void isValid(String previsao, int valor, int custo, double taxa) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER")
				&& !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		if (custo <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Custo nao pode ser menor ou igual a zero");
		if (taxa <= 0.0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Taxa nao pode ser menor ou igual a zero");
	}
	
	private void isValid(String previsao, int valor, int custo, int valorAssegurado) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER")
				&& !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		if (custo <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Custo nao pode ser menor ou igual a zero");
		if (valorAssegurado <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor assegurado nao pode ser menor ou igual a zero");
	}

	/**
	 * M�todo de checagem que avalia se os valores passados s�o vazios ou nulos.
	 * 
	 * @param previsao
	 *            Previsao da Aposta a ser validada.
	 * @param valor
	 *            Quantia da Aposta a ser validada.
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de previsao � composto apenas de espa�os vazios.
	 *                </i> <i> Valor de apostador � composto apenas de espa�os
	 *                vazios. </i>
	 *                </nl>
	 * @exception NullPointerException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> O valor de previsao � null. </i> <i> O valor de apostador
	 *                � null. </i>
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
	
	public Seguro getSeguro() {
		return this.seguro;
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	/**
	 * Retorna uma representa��o textual de uma Aposta.
	 * 
	 * @return a String que representa esta Aposta.
	 * @since Parte 1
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
	 * Avalia se o objeto passado � equivalente a esta Aposta.
	 * 
	 * @return true se o objeto for considerado equivalente a esta Aposta, e false
	 *         caso o contr�rio.
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
