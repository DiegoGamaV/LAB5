package modelos;

import java.util.ArrayList;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Cenario � utilizada para abstrair uma situa��o da vida real.
 *         Um Cenario cont�m uma descri��o que representa a situa��o de que o
 *         Cenario trata, o estado no qual este Cenario se encontra, e uma
 *         ArrayList de objetos Aposta sobre este Cenario.
 * @since Parte 1
 */
public class Cenario {

	private String descricao;
	private String estado;
	private ArrayList<Aposta> apostas;

	/**
	 * Constr�i um Cenario com uma descri��o espec�fica e um estado padr�o como 'Nao
	 * finalizado'. Uma descri��o nula ou vazia n�o � aceita.
	 * 
	 * @param descricao
	 *            A descri��o sobre o que o cen�rio se trata.
	 * @since Parte 1
	 */
	public Cenario(String descricao) {
		isNull(descricao);
		isEmpity(descricao);
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostas = new ArrayList<>();
	}

	/**
	 * M�todo de checagem que avalia se a String descricao passada � composta apenas
	 * de espa�os vazios.
	 * 
	 * @param descricao
	 *            A descricao do Cenario a ser verificada.
	 * @exception IllegalArgumentException
	 *                se descricao � composta apenas de espacos vazios.
	 * @since Parte 1
	 */
	private void isEmpity(String descricao) {
		if (descricao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	}

	/**
	 * M�todo de checagem que avalia se a String descricao passada � nula.
	 * 
	 * @param descricao
	 *            A descricao do Cenario a ser verificada.
	 * @exception NullPointerException
	 *                se a descricao � nula.
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
	 * Muda o valor do campo estado. Os �nicos valores v�lidos s�o 'Finalizado
	 * (ocorreu)' e 'Finalizado (n ocorreu)'.
	 * 
	 * @param estado
	 *            O valor de String a ser atualizado no campo estado.
	 * @exception IllegalArgumentException
	 *                se o valor for diferente dos v�lidos.
	 * @since Parte 1
	 */
	public void setEstado(String estado) {
		if (estado.trim().equals("Finalizado (ocorreu)") || estado.trim().equals("Finalizado (n ocorreu)")) {
			this.estado = estado.trim();
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Cria e adiciona uma Aposta na lista de Apostas do Cenario.
	 * 
	 * @param apostador
	 *            Nome do apostador respons�vel.
	 * @param valor
	 *            Quantia em centavos a ser apostada.
	 * @param previsao
	 *            Previs�o quanto � fatalidade do Cenario. Os �nicos valores v�lidos
	 *            s�o "VAI ACONTECER" e "N VAI ACONTECER".
	 * @return true se a Aposta for adicionada com sucesso, e false caso o
	 *         contr�rio.
	 * @since Parte 1
	 */
	public boolean addAposta(String apostador, int valor, String previsao) {
		return this.apostas.add(new Aposta(apostador, valor, previsao));
	}
	
	public boolean addAposta(String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return this.apostas.add(new Aposta(apostador, valor, previsao, valorAssegurado, custo));
	}
	
	public boolean addAposta(String apostador, int valor, String previsao, double taxa, int custo) {
		return this.apostas.add(new Aposta(apostador, valor, previsao, taxa, custo));
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
	 * Exibe uma representa��o textual de todas as Apostas deste Cenario.
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
	
	
	
	public int getSeguros() {
		int dinheiroSeguro = 0;
		for (Aposta aposta : apostas) {
			if (aposta.getTipo() instanceof ApostaAsseguradaValor)
				dinheiroSeguro += ((ApostaAsseguradaValor) aposta.getTipo()).getValorAssegurado();
			else {
				if (aposta.getTipo() instanceof ApostaAsseguradaTaxa)
					dinheiroSeguro += aposta.getValor() * ((ApostaAsseguradaTaxa) aposta.getTipo()).getTaxa();
			}
		}
		return dinheiroSeguro;
	}

	/**
	 * M�todo de checagem que verifica se a Aposta est� correta sobre a fatalidade
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
	 * @return o somat�rio calculado.
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
	 * @return o somat�rio calculado.
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
	 * Retorna a representa��o textual de um Cenario.
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
	 * Compara se os dois objetos s�o considerados equivalentes.
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

}
