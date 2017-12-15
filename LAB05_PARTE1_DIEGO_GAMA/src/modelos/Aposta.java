package modelos;

public class Aposta {

	private String apostador;
	private int valor;
	private String previsao;

	public Aposta(String apostador, int valor, String previsao) {
		isEmpityOrNull(apostador, previsao);
		isValid(previsao, valor);
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
	
	private void isValid(String previsao, int valor) {
		if (!previsao.trim().toUpperCase().equals("VAI ACONTECER") && !previsao.trim().toUpperCase().equals("N VAI ACONTECER"))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		if (valor <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
	}
	
	private void isEmpityOrNull(String apostador, String previsao) {
		if (apostador == null)
			throw new NullPointerException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if (previsao == null)
			throw new NullPointerException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		if (apostador.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if (previsao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
	}

	@Override
	public String toString() {
		return this.apostador + " - " + this.valor + " - " + this.previsao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apostador.hashCode();
		result = prime * result + previsao.hashCode();
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
		if (!apostador.equals(other.apostador))
			return false;
		if (!previsao.equals(other.previsao))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
}
