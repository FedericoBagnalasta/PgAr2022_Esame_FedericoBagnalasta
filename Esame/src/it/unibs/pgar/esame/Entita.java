package it.unibs.pgar.esame;

public interface Entita {
	
	public static int attaccoIniziale = 5;
	public static int difesaIniziale = 5;
	
	public double attacca();
	public void subisciDanni(int danni);
	public boolean inVita();

}
