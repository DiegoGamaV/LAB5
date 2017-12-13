package controladores;

import java.util.ArrayList;

import modelos.*;

public class Crupie {

	private ArrayList<Cenario> cenarios;
	private Caixa caixa;
	
	public Crupie() {

	}
	
	public Crupie(int caixa, double taxa) {
		if (isValid(taxa)) {
			throw new IllegalArgumentException();
		}
		this.caixa = new Caixa(caixa, taxa);
	}
	
	private boolean isValid(double taxa) {
		return taxa > 0.0;
	}
	
	public int addCenario(String descricao) {
		Cenario cenario = new Cenario(descricao);
		this.cenarios.add(cenario);
		return this.cenarios.indexOf(cenario) + 1;
	}
	
	public void finalizarCenario(int cenario, boolean ocorreu) {
		if (ocorreu) {
			this.cenarios.get(cenario - 1).setEstado("Finalizado (ocorreu)");
		} else {
			this.cenarios.get(cenario - 1).setEstado("Finalizado (n ocorreu)");
		}
		this.caixa.addDinheiro(getDinheiroParaCaixa(cenario));
	}
	
	public String listarCenarios() {
		String descricoes = "";
		for (Cenario cenario : this.cenarios) {
			descricoes += cenario.toString() + System.lineSeparator();
		}
		return descricoes;
	}
	
	public String exibirCenario(int cenario) {
		return this.cenarios.get(cenario - 1).toString();
	}
	
	public int getDinheiroAtual() {
		return this.caixa.getDinheiro();
	}
		
	public int getDinheiroParaCaixa(int cenario) {
		int valorBruto = this.cenarios.get(cenario - 1).calcularDinheiro();
		return (int) Math.floor(valorBruto * this.caixa.getTaxa());
	}
	
	public int getDinheiroVencedores(int cenario) {
		int dinheiroParaCaixa = getDinheiroParaCaixa(cenario);
		int valorBruto = this.cenarios.get(cenario - 1).calcularDinheiro();
		return valorBruto - dinheiroParaCaixa;
	}
	
	public boolean addAposta(int cenario, String apostador,int valor, String previsao) {
		return this.cenarios.get(cenario - 1).addAposta(apostador, valor, previsao);
	}
	
	public int getTotalApostas(int cenario) {
		return this.cenarios.get(cenario - 1).calcularValorTotal();
	}
	
	public String listarApostas(int cenario) {
		return this.cenarios.get(cenario - 1).exibirApostas(); 
	}
	
	public int contarApostas(int cenario) {
		return this.cenarios.get(cenario - 1).contarApostas();
	}
	
}
