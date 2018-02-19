import java.util.ArrayList;

import easyaccept.EasyAccept;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ArrayList<String> testes = new ArrayList<>();
		testes.add("acceptance_test/us1_test.txt");
		testes.add("acceptance_test/us2_test.txt");
		testes.add("acceptance_test/us3_test.txt");
		testes.add("acceptance_test/us4_test.txt");
		testes.add("acceptance_test/us5_test.txt");
		testes.add("acceptance_test/us6_test.txt");
		testes.add("acceptance_test/us7_test.txt");
		EasyAccept.executeEasyAcceptTests("controladores.Sistema", testes);
	}
}
