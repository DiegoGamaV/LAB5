package modelos;

public class CenarioBonus extends Cenario {

	private int bonus;
	
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		isValid(bonus);
		this.bonus = bonus;
	}
	
	private void isValid(int bonus) {
		if (bonus <= 0)
			throw new IllegalArgumentException("Bônus não pode ser nulo ou negativo!");
	}
	
	public int getBonus() {
		return this.bonus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bonus;
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
		CenarioBonus other = (CenarioBonus) obj;
		if (bonus != other.bonus)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + " - " + this.bonus;
	}

}
