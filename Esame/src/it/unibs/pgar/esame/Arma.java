package it.unibs.pgar.esame;

import it.unibs.fp.mylib.NumeriCasuali;

public class Arma implements OggettoCesta {

	private String nomeArma;
	private int potenza;
		
	public Arma() {
		this.potenza = NumeriCasuali.estraiIntero(35, 55);
	}

	public Arma(String nomeArma) {
		this.nomeArma = nomeArma;
		this.potenza = NumeriCasuali.estraiIntero(35, 55);
	}

	public String getNomeArma() {
		return nomeArma;
	}

	public int getPotenza() {
		return potenza;
	}
	
	public String getNomeOggetto() {
		return "Arma";
	}
	

}
