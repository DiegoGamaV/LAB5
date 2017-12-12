package modelos;

public class Caixa {

	private int dinheiro;
	private double taxa;

	public Caixa(int dinheiro, double taxa) {
		this.dinheiro = dinheiro;
		this.taxa = taxa;
	}

	public int getDinheiro() {
		return this.dinheiro;
	}

	public void addDinheiro(int dinheiro) {
		this.dinheiro += dinheiro;
	}

	public double getTaxa() {
		return this.taxa;
	}

}
