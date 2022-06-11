package it.unibs.pgar.esame;

public class Mappa {
	
	

	private int [][] mappa;
	
	
	
	
	
	public Mappa() {
		
	}

	private void creaMappa(int numRighe, int numColonne) {
		mappa = new int[numRighe][numColonne];
	}
	
	private void inizializzaMappa() {
		XML xml = new XML(mappa);
		xml.leggiMappa();
	}
	
	public void gestisciAttacco() {
		do {
			
			mostro.subisciDanni(p.attacco());
			
			p.subisciDanni(mostro.attacco());
			
		}while(p.inVita() && mostro.inVita();
		
		
	}
}
