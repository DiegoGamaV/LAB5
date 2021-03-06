package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Cenario � utilizada para abstrair uma situação da vida real.
 *         Um Cenario contém uma descrição que representa a situação de que o
 *         Cenario trata, o estado no qual este Cenario se encontra, e uma
 *         ArrayList de objetos Aposta sobre este Cenario.
 * @since Parte 1
 */
public class Cenario implements Comparable<Cenario> {

	private String descricao;
	private String estado;
	private List<Aposta> apostas;
	private int contador;
	private int id;

	/**
	 * Constróium Cenario com uma descrição específica e um estado padrão como 'Nao
	 * finalizado'. Uma descrição nula ou vazia não é aceita.
	 * 
	 * @param descricao
	 *            A descrição sobre o que o cenário se trata.
	 * @since Parte 1
	 */
	public Cenario(String descricao) {
		isNull(descricao);
		isEmpty(descricao);
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostas = new ArrayList<>();
		this.contador = 1;
		this.id = 0;
	}

	/**
	 * Método de checagem que avalia se a String descricao passada é composta apenas
	 * de espaços vazios.
	 * 
	 * @param descricao
	 *            A descricao do Cenario a ser verificada.
	 * @exception IllegalArgumentException
	 *                se descricao é composta apenas de espacos vazios.
	 * @since Parte 1
	 */
	private void isEmpty(String descricao) {
		if (descricao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	}

	/**
	 * Método de checagem que avalia se a String descricao passada é nula.
	 * 
	 * @param descricao
	 *            A descricao do Cenario a ser verificada.
	 * @exception NullPointerException
	 *                se a descricao é nula.
	 * @since Parte 1
	 */
	private void isNull(String descricao) {
		if (descricao == null)
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
	}

	/**
	 * Consulta o estado atual do Cenario.
	 * 
	 * @return o campo estado atual do Cenario.
	 * @since Parte 1
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * Muda o valor do campo estado. Os únicos valores válidos são 'Finalizado
	 * (ocorreu)' e 'Finalizado (n ocorreu)'.
	 * 
	 * @param estado
	 *            O valor de String a ser atualizado no campo estado.
	 * @exception IllegalArgumentException
	 *                se o valor for diferente dos válidos.
	 * @since Parte 1
	 */
	public void setEstado(String estado) {
		if (estado.trim().equals("Finalizado (ocorreu)") || estado.trim().equals("Finalizado (n ocorreu)")) {
			this.estado = estado.trim();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Cria e adiciona uma Aposta na lista de Apostas do Cenario.
	 * 
	 * @param apostador
	 *            Nome do apostador responsável.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Previsão quanto a fatalidade do Cenario. Os únicos valores válidos
	 *            são "VAI ACONTECER" e "N VAI ACONTECER".
	 * @return true se a Aposta for adicionada com sucesso, e false caso o
	 *         contrário.
	 * @since Parte 1
	 */
	public boolean addAposta(String apostador, int valor, String previsao) {
		return this.apostas.add(new Aposta(apostador, valor, previsao));
	}

	/**
	 * Cria e adiciona uma Aposta assegurada por valor na lista de Apostas do
	 * Cenario.
	 * 
	 * @param apostador
	 *            Nome do apostador responsável.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Previsão quanto a fatalidade do Cenario. Os únicos valores válidos
	 *            são "VAI ACONTECER" e "N VAI ACONTECER".
	 * @param valorAssegurado
	 *            Valor a ser retirado do caixa caso a Aposta seja perdedora.
	 * @param custo
	 *            Custo a ser entregue ao Caixa para pagar o seguro.
	 * @return O identificador desta Aposta assegurada neste Cenario.
	 * @since Parte 2
	 */
	public int addAposta(String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		int id = this.contador;
		this.contador++;
		this.apostas.add(new Aposta(apostador, valor, previsao, valorAssegurado, custo, id));
		return id;
	}

	/**
	 * Cria e adiciona uma Aposta assegurada por valor na lista de Apostas do
	 * Cenario.
	 * 
	 * @param apostador
	 *            Nome do apostador responsável.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Previsão quanto a fatalidade do Cenario. Os únicos valores válidos
	 *            são "VAI ACONTECER" e "N VAI ACONTECER".
	 * @param taxa
	 *            Porcentagem do valor da aposta a ser retirada do caixa caso a
	 *            Aposta seja perdedora.
	 * @param custo
	 *            Custo a ser entregue ao Caixa para pagar o seguro.
	 * @return O identificador desta Aposta assegurada neste Cenario.
	 * @since Parte 2
	 */
	public int addAposta(String apostador, int valor, String previsao, double taxa, int custo) {
		int id = this.contador;
		this.contador++;
		this.apostas.add(new Aposta(apostador, valor, previsao, taxa, custo, id));
		return id;
	}

	/**
	 * Transforma uma Aposta assegurada por taxa em uma Aposta assegurada por valor.
	 * 
	 * @param apostadaAssegurada
	 *            Identificador da Aposta assegurada desejada.
	 * @param valor
	 *            Valor do seguro da Aposta.
	 * @return O identificador desta Aposta assegurada neste Cenario.
	 * @exception UnsupportedOperationException
	 *                se a Aposta já for assegurada por valor.
	 * @exception IllegalArgumentException
	 *                se a Aposta assegurada não for encontrada.
	 * @since Parte 2
	 */
	public int alterarSeguroValor(int apostaAssegurada, int valor) {
		for (Aposta aposta : this.apostas) {
			if (aposta.getSeguro().getId() == apostaAssegurada) {
				Seguro tipo = aposta.getSeguro();
				if (tipo instanceof SeguroTaxa) {
					SeguroTaxa tipoValor = (SeguroTaxa) tipo;
					aposta.setSeguro(new SeguroValor(valor, tipoValor.getCusto(), tipo.getId()));
					return tipo.getId();
				} else {
					throw new UnsupportedOperationException("Aposta ja eh assegurada por valor!");
				}
			}
		}
		throw new IllegalArgumentException("Aposta assegurada nao encontrada!");
	}

	/**
	 * Transforma uma Aposta assegurada por valor em uma Aposta assegurada por taxa.
	 * 
	 * @param apostadaAssegurada
	 *            Identificador da Aposta assegurada desejada.
	 * @param taxa
	 *            Taxa do seguro da Aposta.
	 * @return O identificador desta Aposta assegurada neste Cenario.
	 * @exception UnsupportedOperationException
	 *                se a Aposta já for assegurada por taxa.
	 * @exception IllegalArgumentException
	 *                se a Aposta assegurada não for encontrada.
	 * @since Parte 2
	 */
	public int alterarSeguroTaxa(int apostaAssegurada, double taxa) {
		for (Aposta aposta : this.apostas) {
			if (aposta.getSeguro().getId() == apostaAssegurada) {
				Seguro tipo = aposta.getSeguro();
				if (tipo instanceof SeguroValor) {
					SeguroValor tipoTaxa = (SeguroValor) tipo;
					aposta.setSeguro(new SeguroTaxa(taxa, tipoTaxa.getCusto(), tipo.getId()));
					return tipo.getId();
				} else {
					throw new UnsupportedOperationException("Aposta ja eh assegurada por taxa!");
				}
			}
		}
		throw new IllegalArgumentException("Aposta assegurada nao encontrada!");
	}

	/**
	 * Conta a quantidade de objetos Aposta na lista de apostas.
	 * 
	 * @return a quantidade de apostas adicionadas neste Cenario.
	 * @since Parte 1
	 */
	public int contarApostas() {
		return this.apostas.size();
	}

	/**
	 * Exibe uma representação textual de todas as Apostas deste Cenario.
	 * 
	 * @return a lista de todas as apostas deste Cenario.
	 * @since Parte 1
	 */
	public String exibirApostas() {
		String descricao = "";
		for (int i = 0; i < this.apostas.size(); i++) {
			descricao += apostas.get(i).toString() + System.lineSeparator();
		}
		return descricao;
	}

	/**
	 * Exibe uma representação textual da Aposta assegurada específica.
	 * 
	 * @param id
	 *            Identificador da Aposta assegurada.
	 * @return representação textual da Aposta desejada.
	 * @since Parte 2
	 */
	public String exibirApostaAssegurada(int id) {
		for (Aposta aposta : this.apostas) {
			if (aposta.getSeguro().getId() == id)
				return aposta.toString();
		}
		return null;
	}

	/**
	 * Calcula o somatório de todos os seguros de todas as Apostas asseguradas deste
	 * Cenario.
	 * 
	 * @return total de todos os seguros.
	 * @since Parte 2
	 */
	public int getSeguros() {
		int dinheiroSeguro = 0;
		for (Aposta aposta : apostas) {
			if (aposta.getSeguro() instanceof SeguroValor)
				dinheiroSeguro += ((SeguroValor) aposta.getSeguro()).getValorAssegurado();
			else {
				if (aposta.getSeguro() instanceof SeguroTaxa)
					dinheiroSeguro += aposta.getValor() * ((SeguroTaxa) aposta.getSeguro()).getTaxa();
			}
		}
		return dinheiroSeguro;
	}

	/**
	 * Método de checagem que verifica se a Aposta est� correta sobre a fatalidade
	 * do Cenario.
	 * 
	 * @param aposta
	 *            Objeto Aposta a ser checado.
	 * @return true, se a aposta estiver correta, e false se estiver errada.
	 * @since Parte 1
	 */
	private boolean verificarAposta(Aposta aposta) {
		if (aposta.getPrevisao().equals("VAI ACONTECER") && this.estado.equals("Finalizado (ocorreu)")) {
			return false;
		} else if (aposta.getPrevisao().equals("N VAI ACONTECER") && this.estado.equals("Finalizado (n ocorreu)")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Calcula e retorna o dinheiro de todas as Apostas perdedoras.
	 * 
	 * @return o somatório calculado.
	 * @since Parte 1
	 */
	public int calcularDinheiro() {
		int valorTotal = 0;
		for (Aposta aposta : this.apostas) {
			if (verificarAposta(aposta)) {
				valorTotal += aposta.getValor();
			}
		}
		return valorTotal;
	}

	/**
	 * Calcula a quantia total de todas as Apostas deste Cenario.
	 * 
	 * @return o somatório calculado.
	 * @since Parte 1
	 */
	public int calcularValorTotal() {
		int valorTotal = 0;
		for (Aposta aposta : this.apostas) {
			valorTotal += aposta.getValor();
		}
		return valorTotal;
	}

	/**
	 * Retorna a representação textual de um Cenario.
	 * 
	 * @return a String que representa este Cenario.
	 * @since Parte 1
	 */
	@Override
	public String toString() {
		return this.descricao + " - " + this.estado;
	}

	/**
	 * Calcula o hash code deste objeto.
	 * 
	 * @return o hash code computado.
	 * @since Parte 1
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostas == null) ? 0 : apostas.hashCode());
		result = prime * result + descricao.hashCode();
		result = prime * result + estado.hashCode();
		return result;
	}

	/**
	 * Compara se os dois objetos são considerados equivalentes.
	 * 
	 * @param obj
	 *            O objeto a ser comparado com este Cenario.
	 * @return true se o objeto for equivalente a este Cenario, e false caso o
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cenario other = (Cenario) obj;
		if (apostas == null) {
			if (other.apostas != null)
				return false;
		} else if (!apostas.equals(other.apostas))
			return false;
		if (!descricao.equals(other.descricao))
			return false;
		if (!estado.equals(other.estado))
			return false;
		return true;
	}

	/**
	 * Compara este e o objeto o de acordo com suas descrições.
	 * 
	 * @param o
	 *            O Cenario a ser comparado com este objeto.
	 * @return -1 se a descrição deste objeto for antecessora à descrição do objeto
	 *         o em ordem alfabética, 1 se a descrição de o for antecessora à
	 *         descrição deste objeto em ordem alfabética, e 0 caso as descrições
	 *         sejam iguais.
	 */
	@Override
	public int compareTo(Cenario o) {
		return this.descricao.compareTo(o.descricao);
	}

}
