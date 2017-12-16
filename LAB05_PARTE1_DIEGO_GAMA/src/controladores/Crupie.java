package controladores;

import java.util.ArrayList;

import modelos.*;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Crupie é uma classe controladora de sessão das classes
 *         Aposta e Caixa. Representa um crupiê, um gerente de apostas e regente
 *         de jogos de azar.
 * 
 * @since Parte 1
 */
public class Crupie {

	private ArrayList<Cenario> cenarios;
	private Caixa caixa;

	/**
	 * Constrói um objeto Crupie sem um caixa específico.
	 * 
	 * @since Parte 1
	 */
	public Crupie() {
		this.cenarios = new ArrayList<>();
	}

	/**
	 * Constrói um objeto Crupie com o dinheiro atual do caixa e a taxa de ganho sob
	 * apostas específicos. Valores negativos não são aceitos.
	 * 
	 * @param caixa
	 *            Quantia inicial do dinheiro do Caixa em centavos.
	 * @param taxa
	 *            Porcentagem de ganho do Caixa sob apostas perdedoras em formato
	 *            decimal.
	 * @since Parte 1
	 */
	public Crupie(int caixa, double taxa) {
		this.cenarios = new ArrayList<>();
		isValid(caixa, taxa);
		this.caixa = new Caixa(caixa, taxa);
	}

	/**
	 * Método de checagem que valida se a quantia e taxa passadas são não-negativas.
	 * 
	 * @param caixa
	 *            Quantia em centavos a ser analizada.
	 * @param taxa
	 *            Porcentagem a ser analizada.
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de caixa é negativo. </i> <i> Taxa é negativa. </i>
	 *                </nl>
	 * @since Parte 1
	 */
	private void isValid(int caixa, double taxa) {
		if (taxa < 0.0)
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		if (caixa < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
	}

	/**
	 * Cria e cadastra um Cenario à lista de Cenario.
	 * 
	 * @param descricao
	 *            Descrição do Cenario a ser adicionado.
	 * @return o numero do Cenario na lista.
	 * @since Parte 1
	 */
	public int addCenario(String descricao) {
		Cenario cenario = new Cenario(descricao);
		this.cenarios.add(cenario);
		return this.cenarios.indexOf(cenario) + 1;
	}

	/**
	 * Finaliza um Cenario específico, alterando o estado de acordo com o valor de
	 * ocorrência especificado. Calcula também o valor de ganho do Caixa sob as
	 * apostas perdedoras e adiciona à quantia do Caixa.
	 * 
	 * @param cenario
	 *            Número do Cenario que será finalizado.
	 * @param ocorreu
	 *            Booleano que representa o valor de ocorrência do Cenario.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario já tiver sido finalizado anteriormente.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @since Parte 1
	 */
	public void finalizarCenario(int cenario, boolean ocorreu) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			if (consulta.getEstado().equals("Finalizado (ocorreu)")
					|| consulta.getEstado().equals("Finalizado (n ocorreu)"))
				throw new UnsupportedOperationException("Erro ao fechar aposta: Cenario ja esta fechado");
			if (ocorreu) {
				this.cenarios.get(cenario - 1).setEstado("Finalizado (ocorreu)");
			} else {
				this.cenarios.get(cenario - 1).setEstado("Finalizado (n ocorreu)");
			}
			this.caixa.addDinheiro(getDinheiroParaCaixa(cenario));
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro ao fechar aposta: Cenario nao cadastrado");
		}
	}

	/**
	 * Exibe a representação textual da lista de Cenarios cadastrados.
	 * 
	 * @return a String que representa os Cenarios cadastrados.
	 * @since Parte 1
	 */
	public String listarCenarios() {
		String descricoes = "";
		for (int i = 0; i < this.cenarios.size(); i++) {
			descricoes += (i + 1) + " - " + this.cenarios.get(i).toString() + System.lineSeparator();
		}
		return descricoes;
	}

	/**
	 * Exibe a representação textual do Cenario de mesmo número.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a String que representa o Cenario escolhido.
	 * @since Parte 1
	 */
	public String exibirCenario(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
		try {
			Cenario consulta = cenarios.get(cenario - 1);
			return (this.cenarios.indexOf(consulta) + 1) + " - " + consulta.toString();
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta de cenario: Cenario nao cadastrado");
		}

	}

	/**
	 * Consulta o dinheiro atual armazenado no Caixa.
	 * 
	 * @return a quantia em centavos do Caixa.
	 * @since Parte 1
	 */
	public int getDinheiroAtual() {
		return this.caixa.getDinheiro();
	}

	/**
	 * Consulta o valor das Apostas perdedoras do Cenario específico que será
	 * encaminhado ao Caixa.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario não estiver finalizado.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a quantia em centavos reservada ao Caixa.
	 * @since Parte 1
	 */
	public int getDinheiroParaCaixa(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			if (!consulta.getEstado().trim().equals("Finalizado (ocorreu)")
					&& !consulta.getEstado().trim().equals("Finalizado (n ocorreu)"))
				throw new UnsupportedOperationException(
						"Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
			int valorBruto = consulta.calcularDinheiro();
			return (int) Math.floor(valorBruto * this.caixa.getTaxa());
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
	}

	/**
	 * Consulta o rateio das Apostas perdedoras do Cenario específico que será
	 * distribuído aos vencedores.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario não estiver finalizado.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return o rateio em centavos reservada ao Caixa.
	 * @since Parte 1
	 */
	public int getDinheiroVencedores(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			if (!consulta.getEstado().equals("Finalizado (ocorreu)")
					&& !consulta.getEstado().equals("Finalizado (n ocorreu)"))
				throw new UnsupportedOperationException(
						"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
			double dinheiroParaCaixa = consulta.calcularDinheiro() * this.caixa.getTaxa();
			double valorBruto = (double) consulta.calcularDinheiro();
			return (int) Math.floor(valorBruto - dinheiroParaCaixa);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException(
					"Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
	}

	/**
	 * Cria e adiciona uma Aposta a um Cenario existente específico.
	 * 
	 * @param cenario
	 *            O numero do Cenario em que a aposta será adicionada.
	 * @param apostador
	 *            O nome do apostador responsável pela Aposta.
	 * @param valor
	 *            A quantia em centavos a ser apostada.
	 * @param previsao
	 *            A previsão sobre a fatalidade do Cenario.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return true se a Aposta for adicionada com sucesso, false caso o contrário.
	 * @since Parte 1
	 */
	public boolean addAposta(int cenario, String apostador, int valor, String previsao) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.addAposta(apostador, valor, previsao);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}

	/**
	 * Computa e retorna o valor total apostado sob um Cenario específico.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a quantia total em centavos apostada sob este Cenario.
	 * @since Parte 1
	 */
	public int getTotalApostas(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.calcularValorTotal();
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
	}

	/**
	 * Lista todas as Apostas cadastradas em um Cenario específico.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a String que representa a listagem das Apostas.
	 * @since Parte 1
	 */
	public String listarApostas(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na listagem de apostas: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.exibirApostas();
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na listagem de apostas: Cenario nao cadastrado");
		}
	}

	/**
	 * Conta a quantidade de apostas cadastradas no Cenario específico.
	 * 
	 * @param cenario
	 *            Número do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a quantidade de apostas cadastradas neste Cenario.
	 * @since Parte 1
	 */
	public int contarApostas(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.contarApostas();
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}

	}

}
