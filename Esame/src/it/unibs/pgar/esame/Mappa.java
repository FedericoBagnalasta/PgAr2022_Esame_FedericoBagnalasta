package it.unibs.pgar.esame;

public class Mappa {
	
	

	private int [][] mappa;
	
	
	
	
	
	public Mappa() {
		
	}

	private void creaMappa(int numRighe, int numColonne) {
		mappa = new int[numRighe][numColonne];
	}
	
	public void gestisciAttacco() {
		do {
			p.attacco();
			mostro.attacco();
			
		}while(p.getVita()>0 && mostro.getVita()>0);
	}
}
