package comparadores;

import java.util.Comparator;

import modelos.Cenario;

public class ComparadorCenarioNumApostas implements Comparator<Cenario>{
	
	@Override
	public int compare(Cenario o1, Cenario o2) {
		if (o1.contarApostas() < o2.contarApostas()) {
			return -1;
		}
		if (o1.contarApostas() > o2.contarApostas()) {
			return 1;
		}
		return 0;
	}

}
