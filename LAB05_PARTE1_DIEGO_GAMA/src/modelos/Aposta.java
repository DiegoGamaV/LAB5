package modelos;

public class Aposta {

	private String apostador;
	private int valor;
	private String previsao;

	public Aposta(String apostador, int valor, String previsao) {
		if (isNull(apostador, previsao)) {
			throw new NullPointerException();
		}
		if (isEmpity(apostador, previsao) || isInvalid(previsao, valor)) {
			throw new IllegalArgumentException();
		}
		this.apostador = apostador.trim();
		this.valor = valor;
		this.previsao = previsao.trim().toUpperCase();
	}

	public int getValor() {
		return this.valor;
	}

	public String getPrevisao() {
		return this.previsao;
	}
	
	private boolean isInvalid(String previsao, int valor) {
		if ((previsao.trim().toUpperCase().equals("VAI ACONTECER") || previsao.trim().toUpperCase().equals("N VAI ACONTECER")) && valor > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isEmpity(String apostador, String previsao) {
		return (apostador.trim().equals("") || previsao.trim().equals(""));
	}
	
	private boolean isNull(String apostador, String previsao) {
		return (apostador == null || previsao == null);
	}

	@Override
	public String toString() {
		return this.apostador + " - " + this.valor + " - " + this.previsao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostador == null) ? 0 : apostador.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + valor;
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
		Aposta other = (Aposta) obj;
		if (apostador == null) {
			if (other.apostador != null)
				return false;
		} else if (!apostador.equals(other.apostador))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

}
