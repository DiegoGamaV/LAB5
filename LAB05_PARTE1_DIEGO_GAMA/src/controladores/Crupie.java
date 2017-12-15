package controladores;

import java.util.ArrayList;

import modelos.*;

public class Crupie {

	private ArrayList<Cenario> cenarios;
	private Caixa caixa;

	public Crupie() {
		this.cenarios = new ArrayList<>();
	}

	public Crupie(int caixa, double taxa) {
		this.cenarios = new ArrayList<>();
		isValid(caixa, taxa);
		this.caixa = new Caixa(caixa, taxa);
	}

	private void isValid(int caixa, double taxa) {
		if (taxa < 0.0)
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		if (caixa < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
	}

	public int addCenario(String descricao) {
		Cenario cenario = new Cenario(descricao);
		this.cenarios.add(cenario);
		return this.cenarios.indexOf(cenario) + 1;
	}

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
			this.caixa.addDinheiro(getDinheiroParaCaixa(cenario - 1));
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro ao fechar aposta: Cenario nao cadastrado");
		}
	}

	public String listarCenarios() {
		String descricoes = "";
		for (Cenario cenario : this.cenarios) {
			descricoes += cenario.toString() + System.lineSeparator();
		}
		return descricoes;
	}

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

	public int getDinheiroAtual() {
		return this.caixa.getDinheiro();
	}

	public int getDinheiroParaCaixa(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			if (!consulta.getEstado().equals("Finalizado (ocorreu)")
					&& !consulta.getEstado().equals("Finalizado (n ocorreu)"))
				throw new UnsupportedOperationException(
						"Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
			int valorBruto = consulta.calcularDinheiro();
			return (int) Math.floor(valorBruto * this.caixa.getTaxa());
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
	}

	public int getDinheiroVencedores(int cenario) {
		if (cenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		try {
			Cenario consulta = this.cenarios.get(cenario - 1);
			if (!consulta.getEstado().equals("Finalizado (ocorreu)") && !consulta.getEstado().equals("Finalizado (n ocorreu)"))
				throw new UnsupportedOperationException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
			double dinheiroParaCaixa = consulta.calcularDinheiro() * this.caixa.getTaxa();
			double valorBruto = (double) consulta.calcularDinheiro();
			return (int) Math.floor(valorBruto - dinheiroParaCaixa);
		} catch (IndexOutOfBoundsException exception) {
			throw new IndexOutOfBoundsException(
					"Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}

	}

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
