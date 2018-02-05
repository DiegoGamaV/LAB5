package modelos;

public class ApostaAsseguradaTaxa extends Tipo {

	private double taxa;
	private int custo;
	
	public ApostaAsseguradaTaxa(double taxa, int custo) {
		this.taxa = taxa;
		this.custo = custo;
	}
	
	public double getTaxa() {
		return this.taxa;
	}

	public int getCusto() {
		return this.custo;
	}
	
}
