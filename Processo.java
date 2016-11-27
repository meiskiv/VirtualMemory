package controle;

import java.util.Scanner;

import gerador.Processador;
import estruturas.Ram;
import estruturas.VMem;
import controle.MMU;

public class Processo {  
						 					
	static int t_processo = 65535; //mid:524288,low:65537,hig:1048576
	static int paginas = 128;
	final static int ciclos = 1000;
	private static Processador proc;
	private static VMem mv;
	private static Ram mr;
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Qual o intervalo de tempo de acesso (em milisegundos)? ");
		int intervalo = scan.nextInt();
		
		mr = new Ram();
		mv = new VMem();
		mr.UpdateRam(t_processo);
		mv.InitVM(t_processo);

		proc = new Processador(t_processo, intervalo ,ciclos);
		proc.start();
		try {
			proc.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
