package controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comparadores.ComparadorCenarioNumApostas;
import comparadores.ComparadorCenarioOrdemCadastro;
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

	private List<Cenario> cenarios;
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
	 * M�todo de checagem que valida se a quantia e taxa passadas são não-negativas.
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
	 * Cria e cadastra um Cenario na lista de Cenarios.
	 * 
	 * @param descricao
	 *            Descrição do Cenario a ser adicionado.
	 * @return o numero do Cenario na lista.
	 * @since Parte 1
	 */
	public int addCenario(String descricao) {
		Cenario cenario = new Cenario(descricao);
		this.cenarios.add(cenario);
		int id = this.cenarios.indexOf(cenario) + 1;
		cenario.setId(id);
		return id;
	}

	/**
	 * Cria e cadastra um CenarioBonus na lista de Cenario.
	 * 
	 * @param descricao
	 *            Descrição do Cenario a ser adicionado.
	 * @return o numero do Cenario na lista.
	 * @since Parte 2
	 */
	public int addCenario(String descricao, int bonus) {
		Cenario cenario = new CenarioBonus(descricao, bonus);
		this.caixa.subtractDinheiro(bonus);
		this.cenarios.add(cenario);
		int id = this.cenarios.indexOf(cenario) + 1;
		cenario.setId(id);
		return id;
	}

	/**
	 * Finaliza um Cenario específico, alterando o estado de acordo com o valor de
	 * ocorrência especificado. Calcula também o valor de ganho do Caixa sob as
	 * apostas perdedoras e adiciona a quantia do Caixa.
	 * 
	 * @param cenario
	 *            número do Cenario que será finalizado.
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
	 * Exibe a representação textual do Cenario de mesmo identificador, de acordo
	 * com a ordem de cadastro.
	 * 
	 * @param cenario
	 *            número do Cenario desejado.
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
		for (Cenario consulta : this.cenarios) {
			if (consulta.getId() == cenario)
				return (consulta.getId()) + " - " + consulta.toString();
		}
		throw new UnsupportedOperationException("Erro na consulta de cenario: Cenario nao cadastrado");
	}

	/**
	 * Exibe a representação textual do Cenario de mesmo número, de acordo com a
	 * última ordenação estabelecida.
	 * 
	 * @param cenario
	 *            posição do Cenario desejado na nova ordenação.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return a String que representa o Cenario escolhido.
	 * @since Parte 3
	 */
	public String exibirCenarioOrdenado(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta de cenario ordenado: Cenario invalido");
		try {
			Cenario consulta = cenarios.get(cenario - 1);
			return (consulta.getId()) + " - " + consulta.toString();
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta de cenario ordenado: Cenario nao cadastrado");
		}

	}

	/**
	 * Exibe a representação textual do Cenario de mesmo número, de acordo com a
	 * última ordenação estabelecida. As ordenações disponíveis são:
	 * <ul>
	 * <i> Ordenação Alfabética: ordem alfabética de acordo com a descrição de cada
	 * Cenario. </i> <i> Ordenação por Apostas: ordem decrescente de acordo com o
	 * número de apostas de cada Cenario. </i> <i> Ordenação por Cadastro: ordem
	 * crescente de acordo com ordem de cadastro de cada Cenario. </i>
	 * </ul>
	 * 
	 * @param ordem
	 *            ordem desejada para a nova ordenação. Únicos valores válidos:
	 *            nome, cadastro e apostas.
	 * @exception IllegalArgumentException
	 *                se ordem tiver valor inválido, for nula ou for vazia.
	 * @since Parte 3
	 */
	public void alterarOrdem(String ordem) {
		if (ordem == null || ordem.trim().equals(""))
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		else if (ordem.equals("nome"))
			ordenaCenarioNome();
		else if (ordem.equals("cadastro"))
			ordenaCenarioCadastro();
		else if (ordem.equals("apostas"))
			ordenaCenarioApostas();
		else
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem invalida");
	}

	/**
	 * Ordena os Cenarios de acordo com a descrição e de forma alfabética.
	 * 
	 * @since Parte 3
	 */
	private void ordenaCenarioNome() {
		Collections.sort(this.cenarios);
	}

	/**
	 * Ordena os Cenarios de acordo com a ordem inicial de cadastro e de forma
	 * crescente.
	 * 
	 * @since Parte 3
	 */
	private void ordenaCenarioCadastro() {
		Collections.sort(this.cenarios, new ComparadorCenarioOrdemCadastro());
	}

	/**
	 * Ordena os Cenarios de acordo com a quantidade de apostas de cada Cenario e de
	 * forma descrescente.
	 * 
	 * @since Parte 3
	 */
	private void ordenaCenarioApostas() {
		Collections.sort(this.cenarios, new ComparadorCenarioNumApostas());
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
	 *            número do Cenario desejado.
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
	 *            número do Cenario desejado.
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
			double dinheiroParaCaixa = Math.floor(consulta.calcularDinheiro() * this.caixa.getTaxa());
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
	 * Cria e adiciona uma Aposta assegurada por valor a um Cenario específico.
	 * 
	 * @param cenario
	 *            O numero do Cenario em que a aposta será adicionada.
	 * @param apostador
	 *            O nome do apostador responsável pela Aposta.
	 * @param valor
	 *            A quantia em centavos a ser apostada.
	 * @param previsao
	 *            A previsão sobre a fatalidade do Cenario.
	 * @param valorAssegurado
	 *            O valor em centavos retirado do Caixa caso a aposta seja
	 *            perdedora.
	 * @param custo
	 *            O valor necessário a ser pago ao Caixa para cadastrar um seguro.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return o identificador da aposta assegurada.
	 * @since Parte 2
	 */
	public int addAposta(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			this.caixa.addDinheiro(custo);
			return consulta.addAposta(apostador, valor, previsao, valorAssegurado, custo);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException(
					"Erro no cadastro de aposta assegurada por valor: Cenario nao cadastrado");
		}
	}

	/**
	 * Cria e adiciona uma Aposta assegurada por taxa a um Cenario específico.
	 * 
	 * @param cenario
	 *            O numero do Cenario em que a aposta será adicionada.
	 * @param apostador
	 *            O nome do apostador responsável pela Aposta.
	 * @param valor
	 *            A quantia em centavos a ser apostada.
	 * @param previsao
	 *            A previsão sobre a fatalidade do Cenario.
	 * @param taxa
	 *            Porcentagem do valor da aposta a ser retirada do Caixa caso a
	 *            aposta seja perdedora.
	 * @param custo
	 *            O valor necessário a ser pago ao Caixa para cadastrar um seguro.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return o identificador da aposta assegurada.
	 * @since Parte 2
	 */
	public int addAposta(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			this.caixa.addDinheiro(custo);
			return consulta.addAposta(apostador, valor, previsao, taxa, custo);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException(
					"Erro no cadastro de aposta assegurada por taxa: Cenario nao cadastrado");
		}
	}

	/**
	 * Transforma uma Aposta assegurada por taxa em uma Aposta assegurada por valor
	 * 
	 * @param cenario
	 *            O numero do Cenario onde a Aposta está.
	 * @param valor
	 *            O valor do seguro da aposta.
	 * @param apostaAssegurada
	 *            O identificador da Aposta assegurada.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return true se a Aposta for adicionada com sucesso, false caso o contrário.
	 * @since Parte 2
	 */
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

	/**
	 * Transforma uma Aposta assegurada por valor em uma Aposta assegurada por taxa
	 * 
	 * @param cenario
	 *            O numero do Cenario onde a Aposta está.
	 * @param taxa
	 *            A taxa do seguro da aposta.
	 * @param apostaAssegurada
	 *            O identificador da Aposta assegurada.
	 * @exception IllegalArgumentException
	 *                se o número do Cenario for menor ou igual a zero.
	 * @exception IndexOutOfBoundsExcetion
	 *                se o número do Cenario não corresponder a nenhum Cenario
	 *                adicionado.
	 * @return true se a Aposta for adicionada com sucesso, false caso o contrário.
	 * @since Parte 2
	 */
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
	 * Computa e retorna o valor total apostado sob um Cenario específico.
	 * 
	 * @param cenario
	 *            número do Cenario desejado.
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
	 *            número do Cenario desejado.
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
	 *            número do Cenario desejado.
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
