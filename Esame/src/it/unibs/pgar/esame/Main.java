package it.unibs.pgar.esame;

import it.unibs.fp.mylib.*;

public class Main {

	private static final String PRESENTAZIONE_OPZIONI = "Scegli un opzione";
	private static final String [] voci = {"Muoviti di una casella", "Apri una cesta(se possibile!)", "Utilizza una pozione",} ;
	private static final String [] voci2 = {"Apri Inventario", "Esci dal menu", "Esci dalla Partita",} ;
	private static final String MENU_OPZIONI_SECONDARIE = "Menu opzioni secondarie";
	
	
	public static void main(String[] args) {
	
		
		
		
		Mappa map = new Mappa ();
		map.stampaMappa();
		System.out.println(map.permutaMostro());
		System.out.println(map.permutaMostro());
		
		MyMenu menu = new MyMenu(PRESENTAZIONE_OPZIONI, voci);
		//MyMenu menuInterno = new MyMenu(MENU_OPZIONI_SECONDARIE, voci2);
		//boolean permanenza = true;
		int scelta, turno = 1, sceltaInterna;
		do {
			
			
			
			
			
			
			System.out.printf("Turno %d\n", turno);
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

		turno++;
		map.stampaMappa();
		
		
		}while(scelta != 0);
		
						
		/*do {
				sceltaInterna = menu.scegli();
				
				switch(sceltaInterna) {
				case 1: 
					map.getPersonaggio().stampaInventario();
					
				case 2:
					permanenza = false;
				
				case 3: 
					return;
				}
				
			}while(permanenza);*/
		
		
		
	}
	

}
