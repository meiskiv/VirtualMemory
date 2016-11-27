package estruturas;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ram {

	public static Map<Integer, Integer> memr = 
			new HashMap<Integer, Integer>(8);
	
	int i,kb,al;
	Random r;

	public void UpdateRam(int k) {

		kb= (k/8192);
		r = new Random();
		/*
		 * RAM Key: 0 - 7 frames 
		 * Value: key da página que está mapeando
		 * 
		 */
		
		if (kb <= 7) { // se t_arquivo <= que a ram
			for (i = 0; i <= kb; i++) {
				if (i <= kb) {
					al=r.nextInt(kb);
					memr.put(i, al);// N 1ºs frames receberão 1° 8p  páginas aleatórias
					System.out.println("Frame " + i + " correspondendo a página "+ memr.get(i));
				}else{
					memr.put(i, -1); // o resto não recebe referência de página
				}
			}
		}
		if (kb >= 8) {
			for (i = 0; i <= kb; i++) {
				if (i <= 7) {
					al = r.nextInt(kb);
					memr.put(i,al);	//  8 frames terão 8p aleatórias
					System.out.println("Frame " + i
							+ " correspondendo a página " + memr.get(i));
				}

			}
		} else {
			System.out.println("Erro ao iniciar ou popular a RAM");
		}

	}
}
