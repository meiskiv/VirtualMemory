package controle;

import java.util.Random;

import estruturas.Ram;
import estruturas.VMem;

public class MMU {

	final int t_pagina = 8192;
	final String alf = "ABCDEFGHIJKLMNOPQRSTUVXWYZ";
	final int n = alf.length();
	Random r = new Random();
	int page;
	Ram ram;
	VMem vmem;
	public static int hit = 0;
	public static int miss = 0;
	int total;
	static int age[] = { 0, 0, 0, 0, 0, 0, 0, 0 };

	public void Mapeamento(int b) {
		System.out.println("\n\nINSTRUÇÃO MOV REG " + b);
		page = (b / t_pagina);

		// verifica bit de validade
		if (vmem.memv.get(page) == 1) {
			System.out.println("----- Hit! " 
		+ vmem.memv.get(page) + ", página: "
					+ page);
			System.out.println("Conteúdo do endereço: "
					+alf.charAt(r.nextInt(n))+"\n");
			hit++;
			Aging(page);
		}
		// verifica bit de validade
		if (vmem.memv.get(page) == 0) {
			System.out.println("Miss" );
			miss++;
			PageFault(page);
		}

	}

	public void Aging(int ipage) { // envelheço todos menos o frame que
									// tiver essa página (ipage)
	//	System.out.println("Page que tem bit 1 e sofre aging: " +ipage);
		for (int i = 0; i <= 7; i++) {
			if (ram.memr.get(i) == ipage) {
				System.out.println("##Aging()\n Frame carregado que terá idade zerada: "+i);
				age[i] = 0;
				System.out.println("----Frame " + i + " zerou \nNova página carregada: " + ipage);
				System.out.println("Bit da página que sofreu aging: "
						+ vmem.memv.get(ipage));
			}
			if (ram.memr.get(i) != ipage) {
				age[i] += 1;
				System.out.println("Frame " + i + " envelheceu, idade: "
						+ age[i] + " Página carregada neste frame: "
						+ ram.memr.get(i));
				
			}
		}System.out.println("##Fim aging");
	}

	public void PageFault(int npage) {
		int i;
		System.out.println("Página com bit de validade zero: " + npage);
		vmem.memv.replace(npage, 1);

		int max = age[0];
		int oldframe = 0;
		for (i = 1; i < age.length; i++) {
			if (age[i] >= max) {
				max = age[i];
				oldframe = i;
			}
		}
		System.out.println("Frame mais velho: " + oldframe + ", idade: " + max);
		alteraFrames(oldframe, npage);
		System.out.println("Página carregada: " + npage + ", bit: "
				+ vmem.memv.get(npage)); // printa page + bit
		Aging(npage);
	}

	public void alteraFrames(int frame, int pg) {
		vmem.memv.replace(ram.memr.get(frame), 0); 		//página que faz swap out recebe bit =0 
		ram.memr.replace(frame, pg);					//atualiza frame para value = indice da page que será carregada na ram
		System.out.println("##alteraFrame() \nFrame alterado: "
		+frame+", página carregada: "+ ram.memr.get(frame)); // page  carregada
																	
	}
}
