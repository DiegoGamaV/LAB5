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
	
	public String exibirCenario(int cenario) {
		return this.controlador.exibirCenario(cenario);
	}
	
	public String listarCenarios() {
		return this.controlador.listarCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.controlador.addAposta(cenario, apostador, valor, previsao);
	}
	
	public int contarApostas(int cenario) {
		return this.controlador.contarApostas(cenario);
	}
	
	public void fecharCenario(int cenario, boolean ocorreu) {
		this.controlador.finalizarCenario(cenario, ocorreu);
	}
	
	public int resgatarCaixaCenario(int cenario) {
		return this.controlador.getDinheiroParaCaixa(cenario);
	}
	
	public int resgatarRateioCenario(int cenario) {
		return this.controlador.getDinheiroVencedores(cenario);
	}
	
}
