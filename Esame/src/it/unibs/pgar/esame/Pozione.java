package it.unibs.pgar.esame;

public class Pozione implements OggettoCesta {

	public static int percentualeVitaRecuperata = 50;
	
	public Pozione() {
		
	}
	
	public int riceviEffettoPozione() {
		return percentualeVitaRecuperata;
	}
	
	public String getNomeOggetto() {
		return "Pozione";
	}

}
