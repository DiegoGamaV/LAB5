package controladores;

import java.util.ArrayList;

import modelos.*;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Crupie � uma classe controladora de sess�o das classes
 *         Aposta e Caixa. Representa um crupi�, um gerente de apostas e regente
 *         de jogos de azar.
 * 
 * @since Parte 1
 */
public class Crupie {

	private ArrayList<Cenario> cenarios;
	private Caixa caixa;

	/**
	 * Constr�i um objeto Crupie sem um caixa espec�fico.
	 * 
	 * @since Parte 1
	 */
	public Crupie() {
		this.cenarios = new ArrayList<>();
	}

	/**
	 * Constr�i um objeto Crupie com o dinheiro atual do caixa e a taxa de ganho sob
	 * apostas espec�ficos. Valores negativos n�o s�o aceitos.
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
	 * M�todo de checagem que valida se a quantia e taxa passadas s�o n�o-negativas.
	 * 
	 * @param caixa
	 *            Quantia em centavos a ser analizada.
	 * @param taxa
	 *            Porcentagem a ser analizada.
	 * @exception IllegalArgumentException
	 *                se quaisquer destes casos acontecerem:
	 *                <nl>
	 *                <i> Valor de caixa � negativo. </i> <i> Taxa � negativa. </i>
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
	 * Cria e cadastra um Cenario � lista de Cenario.
	 * 
	 * @param descricao
	 *            Descri��o do Cenario a ser adicionado.
	 * @return o numero do Cenario na lista.
	 * @since Parte 1
	 */
	public int addCenario(String descricao) {
		Cenario cenario = new Cenario(descricao);
		this.cenarios.add(cenario);
		return this.cenarios.indexOf(cenario) + 1;
	}
	
	public int addCenario(String descricao, int bonus) {
		CenarioBonus cenario = new CenarioBonus(descricao, bonus);
		this.caixa.subtractDinheiro(bonus);
		this.cenarios.add(cenario);
		return this.cenarios.indexOf(cenario) + 1;
	}

	/**
	 * Finaliza um Cenario espec�fico, alterando o estado de acordo com o valor de
	 * ocorr�ncia especificado. Calcula tamb�m o valor de ganho do Caixa sob as
	 * apostas perdedoras e adiciona � quantia do Caixa.
	 * 
	 * @param cenario
	 *            N�mero do Cenario que ser� finalizado.
	 * @param ocorreu
	 *            Booleano que representa o valor de ocorr�ncia do Cenario.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario j� tiver sido finalizado anteriormente.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
	 * Exibe a representa��o textual da lista de Cenarios cadastrados.
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
	 * Exibe a representa��o textual do Cenario de mesmo n�mero.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
	 * Consulta o valor das Apostas perdedoras do Cenario espec�fico que ser�
	 * encaminhado ao Caixa.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario n�o estiver finalizado.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
	 * Consulta o rateio das Apostas perdedoras do Cenario espec�fico que ser�
	 * distribu�do aos vencedores.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception UnsupportedOperationException
	 *                se o Cenario n�o estiver finalizado.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
			int rateio = (int) Math.floor(valorBruto - dinheiroParaCaixa);
			int seguroTotal = consulta.getSeguros();
			this.caixa.subtractDinheiro(seguroTotal);
			if (consulta instanceof CenarioBonus)
				rateio += ((CenarioBonus) consulta).getBonus();
			return rateio;
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException(
					"Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
	}

	/**
	 * Cria e adiciona uma Aposta a um Cenario existente espec�fico.
	 * 
	 * @param cenario
	 *            O numero do Cenario em que a aposta ser� adicionada.
	 * @param apostador
	 *            O nome do apostador respons�vel pela Aposta.
	 * @param valor
	 *            A quantia em centavos a ser apostada.
	 * @param previsao
	 *            A previs�o sobre a fatalidade do Cenario.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
	 *                adicionado.
	 * @return true se a Aposta for adicionada com sucesso, false caso o contr�rio.
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
	
	public int addAposta(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			this.caixa.addDinheiro(custo);
			return consulta.addAposta(apostador, valor, previsao, valorAssegurado, custo);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}
	
	public int addAposta(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			this.caixa.addDinheiro(custo);
			return consulta.addAposta(apostador, valor, previsao, taxa, custo);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.alterarSeguroValor(apostaAssegurada, valor);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			return consulta.alterarSeguroTaxa(apostaAssegurada, taxa);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}

	/**
	 * Computa e retorna o valor total apostado sob um Cenario espec�fico.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
	 * Lista todas as Apostas cadastradas em um Cenario espec�fico.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
	 * Conta a quantidade de apostas cadastradas no Cenario espec�fico.
	 * 
	 * @param cenario
	 *            N�mero do Cenario desejado.
	 * @exception IllegalArgumentException
	 *                se o n�mero do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o n�mero do Cenario n�o corresponder a nenhum Cenario
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
