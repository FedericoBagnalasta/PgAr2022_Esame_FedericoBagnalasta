package it.unibs.pgar.esame;

import it.unibs.fp.mylib.*;

public class Main {

	private static final String PRESENTAZIONE_OPZIONI = "Scegli un opzione";
	private static final String [] voci = {"Muoviti di una casella", "Apri una cesta(se possibile!)", "Utilizza una pozione",} ;
	
	
	public static void main(String[] args) {
	
		
		
		
		Mappa map = new Mappa ();
		map.stampaMappa();
		System.out.println(map.permutaMostro());
		System.out.println(map.permutaMostro());
		
		MyMenu menu = new MyMenu(PRESENTAZIONE_OPZIONI, voci);
		int scelta;
		do {
			
			scelta = menu.scegli();
		
			switch(scelta){
			case 1:
				map.movimentoPersonaggio();
				break;
			
			case 2:
				if(map.getCestaVicina()) {
					map.aperturaCesta(map.getPlayerX(), map.getPlayerY());
				}
				else {
					System.out.println("Non sei vicino a nessuna cesta");
				}
				break;
			
			case 3: 
				
				
				
			case 0:
				break;
				
			default:
				break;
			}
		
		
		map.movimentoPersonaggio();
	}

}
