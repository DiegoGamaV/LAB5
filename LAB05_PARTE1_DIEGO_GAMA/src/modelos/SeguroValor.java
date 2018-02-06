package modelos;

public class SeguroValor extends Seguro {

	private int valorAssegurado;
	private int custo;
	
	public SeguroValor(int valorAssegurado, int custo, int id) {
		super(id);
		isValid(valorAssegurado, custo);
		this.valorAssegurado = valorAssegurado;
		this.custo = custo;
	}
	
	public int getValorAssegurado() {
		return this.valorAssegurado;
	}
	
	public int getCusto() {
		return this.custo;
	}

	private void isValid(int valor, int custo) {
		if (valor <= 0)
			throw new IllegalArgumentException("Valor assegurado não pode ser negativa ou nula!");
		else if (custo <= 0)
			throw new IllegalArgumentException("Custo não pode ser negativo ou nulo!");
	}

}
