package modelos;

/**
 * @author Diego Alves Gama
 * 
 *         A classe Seguro representa o tipo de seguro de uma Aposta assegurada.
 *         Toda Aposta assegurada possui um id positivo dentro de um Cenario
 *         específico, definido via ordem de cadastro.
 * @since Parte 2
 */
public class Seguro {

	private int id;

	/**
	 * Construtor default de Seguro
	 * 
	 * @since Parte 2
	 */
	public Seguro() {

	}

	/**
	 * Constrói um Seguro com um deteminado id.
	 * 
	 * @param id
	 *            Identificador desta Aposta assegurada.
	 * @since Parte 2
	 */
	public Seguro(int id) {
		this.id = id;
	}

	/**
	 * Consulta o id deste Seguro.
	 * 
	 * @return o id deste Seguro.
	 * @since Parte 2
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Calcula o hash code deste objeto.
	 * 
	 * @return o hash code computado.
	 * @since Parte 2
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Compara se os dois objetos são considerados equivalentes.
	 * 
	 * @param obj
	 *            O objeto a ser comparado com este Seguro.
	 * @return true se o objeto for equivalente a este Seguro, e false caso o
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguro other = (Seguro) obj;
		if (id != other.id)
			return false;
		return true;
	}

}