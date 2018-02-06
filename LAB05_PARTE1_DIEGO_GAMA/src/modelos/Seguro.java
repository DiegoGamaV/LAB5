package modelos;

public class Seguro {

	private int id;
	
	public Seguro() {
		
	}
	
	public Seguro(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Seguro other = (Seguro) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}