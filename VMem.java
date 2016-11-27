package estruturas;

import java.util.HashMap;
import java.util.Map;

public class VMem {

	public static Map<Integer, Integer> memv =
			new HashMap<Integer, Integer>(
			128);

	public void InitVM(int k) {
		int i, p;
		p = (k / 8192);

		/*
		 * Memória Virtual key = 1 - 128 páginas 
		 * value = bit de validade
		 * 1 = carregada em memória
		 * 0 = não presente na memória
		 */

		if (p <= 128) {
			for (i = 0; i <= p; i++) {
				if (i <= p) { 		// seto bit=0 p as páginas
					memv.put(i, 0);
				}
				System.out.println("Página " + i + ", será utilizada. Bit de validade: "
						+ memv.get(i));
			}
			System.out.println(p
					+ " páginas estão sendo ocupadas pelo processo.");

		} else {
			System.out.println("Arquivo maior que 1 MB");
		}
	}
}
