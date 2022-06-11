package it.unibs.pgar.esame;

import it.unibs.fp.mylib.NumeriCasuali;

public class Mostro implements Entita {

	private String nome;
	private int vitaIniziale;
	private int vita;
	private int attacco;
	private int difesa;
	private double modificatore;
	private int posX;
	private int posY;
	Arma armaMostro;
	
	
	
	public Mostro(String nome, int posX, int posY) {
		this.nome = nome;
		calcolaVitaIniziale();
		this.vita = vitaIniziale;
		this.attacco = attaccoIniziale;
		this.difesa = difesaIniziale;
		armaMostro = new Arma (String.format("Arma di %s", getNome()));
		this.posX = posX;
		this.posY = posY;
	}


	public double attacca() {
		calcolaModificatore();
		double danno = (((2*armaMostro.getPotenza()*getAttacco())/(25*getDifesa()))+2)*getModificatore();
		return danno;
		
	}
	
	public void subisciDanni(int danni) {
		this.vita = Math.max(0,this.vita - danni);
	}
	
	
	public boolean inVita() {
		return this.vita>0;
	}
	
	
	private void calcolaModificatore() {
		int numTemporaneo = NumeriCasuali.estraiIntero(0, 999);
		if(numTemporaneo<75) this.modificatore = 1.5;
		else this.modificatore = 1;
	}

	private void calcolaVitaIniziale() {
		this.vitaIniziale = NumeriCasuali.estraiIntero(15, 25);
	}
	
	

	public String getNome() {
		return nome;
	}


	public int getVita() {
		return vita;
	}


	public int getAttacco() {
		return attacco;
	}


	public int getDifesa() {
		return difesa;
	}
	
	private double getModificatore() {
		return modificatore;
	}

	
}
