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
