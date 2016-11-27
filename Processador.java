package gerador;

import java.util.Random;
import controle.MMU;

public class Processador extends Thread {
	
	int t_arquivo;
	int ciclos;
	int intervalo;
	
	public Processador(int t_arquivo, int intervalo ,int ciclos) {
		super();
		this.t_arquivo = t_arquivo;
		this.ciclos = ciclos;
		this.intervalo = intervalo;
	}

	Random randon = new Random();
	MMU mmu = new MMU();
	
	@Override
	public void run() {
		int i=1;

		while(i<=ciclos){
			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mmu.Mapeamento(randon.nextInt(t_arquivo));
		
		i++;
		}
		int httl = mmu.hit;
		int mttl = mmu.miss;
		System.out.println("Hit: "+((httl*100)/ciclos)+"%");
		System.out.println("Miss: "+((mttl*100)/ciclos+"%"));
		
		super.run();
		
	}

}
