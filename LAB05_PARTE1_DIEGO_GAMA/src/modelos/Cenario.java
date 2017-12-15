package modelos;

import java.util.ArrayList;

public class Cenario {

	private String descricao;
	private String estado;
	private ArrayList<Aposta> apostas;

	public Cenario(String descricao) {
		isNull(descricao);
		isInvalid(descricao);
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostas = new ArrayList<>();
	}
	
	private void isInvalid(String descricao) {
		if (descricao.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	}
	
	private void isNull(String descricao) {
		if (descricao == null)
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		if (estado.equals("Finalizado (ocorreu)") || estado.equals("Finalizado (n ocorreu)")){
			this.estado = estado;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public boolean addAposta(String apostador, int valor, String previsao) {
		return this.apostas.add(new Aposta(apostador, valor, previsao));
	}

	public int contarApostas() {
		return this.apostas.size();
	}

	public String exibirApostas() {
		String descricao = "";
		for (int i = 0; i < this.apostas.size(); i++) {
			descricao += apostas.get(i).toString() + System.lineSeparator();
		}
		return descricao;
	}

	private boolean verificarAposta(Aposta aposta) {
		if (aposta.getPrevisao().equals("VAI ACONTECER") && this.estado.equals("Finalizado (ocorreu)")) {
			return false;
		} else if (aposta.getPrevisao().equals("N VAI ACONTECER") && this.estado.equals("Finalizado (n ocorreu)")) {
			return false;
		} else {
			return true;
		}
	}

	public int calcularDinheiro() {
		int valorTotal = 0;
		for (Aposta aposta : this.apostas) {
			if (verificarAposta(aposta)) {
				valorTotal += aposta.getValor();
			}
		}
		return valorTotal;
	}

	public int calcularValorTotal() {
		int valorTotal = 0;
		for (Aposta aposta : this.apostas) {
			valorTotal += aposta.getValor();
		}
		return valorTotal;
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostas == null) ? 0 : apostas.hashCode());
		result = prime * result + descricao.hashCode();
		result = prime * result + estado.hashCode();
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
		Cenario other = (Cenario) obj;
		if (apostas == null) {
			if (other.apostas != null)
				return false;
		} else if (!apostas.equals(other.apostas))
			return false;
		if (!descricao.equals(other.descricao))
			return false;
		if (!estado.equals(other.estado))
			return false;
		return true;
	}

}
