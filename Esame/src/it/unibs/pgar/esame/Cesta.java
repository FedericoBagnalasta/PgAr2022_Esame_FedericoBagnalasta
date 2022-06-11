package it.unibs.pgar.esame;

import it.unibs.fp.mylib.NumeriCasuali;

public class Cesta {
		
	private OggettoCesta contenuto;
	private int posX;
	private int posY;
	
	
	
	public Cesta(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		impostaContenuto();
	}

    public OggettoCesta prendiContenuto() {
    	return contenuto;
    }

    /*
     * Metodo per impostare il contenuto di una cesta 
     */
	private void impostaContenuto() {
		int interoEstratto = NumeriCasuali.estraiIntero(0, 99);
		if(interoEstratto<40) {
			contenuto = new Arma();
		}
		else if(interoEstratto>39 && interoEstratto<74) {
			contenuto = new Scudo();
		}
		else {
			contenuto = new Pozione();
		}		
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public OggettoCesta getContenuto() {
		return contenuto;
	}
	
	
}
