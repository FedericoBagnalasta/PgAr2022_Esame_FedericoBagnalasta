package it.unibs.pgar.esame;

import it.unibs.fp.mylib.InputDati;

public class Mappa {
	
	

	private static final String RICHIESTA_NOME = "Come vuoi chiamare il tuo eroe?";
	private String [][] mappa;
	private Personaggio player = creaPersonaggio();
	
	
	
	
	
	public Mappa() {
		
	}

	
	
	private void inizializzaMappa() {
		XML xml = new XML(mappa);
		xml.leggiMappa();
	}
	
	public void gestisciAttacco() {
		do {
			
			mostro.subisciDanni(player.attacco());
			
			player.subisciDanni(mostro.attacco());
			
		}while(p.inVita() && mostro.inVita();
		
		
	}
	
	
	private Personaggio creaPersonaggio() {
		String nome = InputDati.leggiStringaNonVuota(RICHIESTA_NOME);
		return new Personaggio(nome);
	}
}
