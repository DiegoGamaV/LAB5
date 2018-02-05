package modelos;

public class ApostaAsseguradaValor extends Tipo {

	private int valorAssegurado;
	private int custo;
	
	public ApostaAsseguradaValor(int valorAssegurado, int custo) {
		this.valorAssegurado = valorAssegurado;
		this.custo = custo;
	}
	
	public int getValorAssegurado() {
		return this.valorAssegurado;
	}
	
	public int getCusto() {
		return this.custo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + custo;
		result = prime * result + valorAssegurado;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApostaAsseguradaValor other = (ApostaAsseguradaValor) obj;
		if (custo != other.custo)
			return false;
		if (valorAssegurado != other.valorAssegurado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " - ASSEGURADA(VALOR) - " + this.valorAssegurado; 
	}
	
}
