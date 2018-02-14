package controladores;

public class Sistema {
	
	private Crupie controlador;
	
	public Sistema() {
		this.controlador = new Crupie();
	}
	
	public void inicializa(int caixa, double taxa) {
		this.controlador = new Crupie(caixa, taxa);
	}
	
	public int getCaixa() {
		return this.controlador.getDinheiroAtual();
	}
	
	public int cadastrarCenario(String descricao) {
		return this.controlador.addCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		return this.controlador.addCenario(descricao, bonus);
	}
	
	public String exibirCenario(int cenario) {
		return this.controlador.exibirCenario(cenario);
	}
	
	public String listarCenarios() {
		return this.controlador.listarCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.controlador.addAposta(cenario, apostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return this.controlador.addAposta(cenario, apostador, valor, previsao, valorAssegurado, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return this.controlador.addAposta(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return this.controlador.alterarSeguroValor(cenario, apostaAssegurada, valor);
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		return this.controlador.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
	}
	
	public int totalDeApostas(int cenario) {
		return this.controlador.contarApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.controlador.finalizarCenario(cenario, ocorreu);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.controlador.getTotalApostas(cenario);
	}
	
	public int getCaixaCenario(int cenario) {
		return this.controlador.getDinheiroParaCaixa(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return this.controlador.getDinheiroVencedores(cenario);
	}
	
}
