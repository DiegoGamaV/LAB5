package modelos;

public class SeguroTaxa extends Seguro {

	private double taxa;
	private int custo;
	
	public SeguroTaxa(double taxa, int custo, int id) {
		super(id);
		isValid(taxa, custo);
		this.taxa = taxa;
		this.custo = custo;
	}
	
	public double getTaxa() {
		return this.taxa;
	}

	public int getCusto() {
		return this.custo;
	}
	
	private void isValid(double taxa, int custo) {
		if (taxa <= 0.0)
			throw new IllegalArgumentException("Taxa não pode ser negativa ou nula!");
		else if (custo <= 0)
			throw new IllegalArgumentException("Custo não pode ser negativo ou nulo!");
	}
	
}
