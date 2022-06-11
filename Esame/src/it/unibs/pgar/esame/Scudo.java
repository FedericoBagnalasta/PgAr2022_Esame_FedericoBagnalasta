package it.unibs.pgar.esame;

public class Scudo implements OggettoCesta {
	
	private static final int puntiVitaIniziali = 5;
	private int vita;	
	
	public Scudo() {
		this.vita = puntiVitaIniziali;
	}

	public void usaEffetto() {
		
	}

	public int getVita() {
		return vita;
	}

	public String getNomeOggetto() {	
		return "Scudo";
	}
	

}
