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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dinheiro;
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caixa other = (Caixa) obj;
		if (dinheiro != other.dinheiro)
			return false;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		return true;
	}

}
