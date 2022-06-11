package it.unibs.pgar.esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.*;

public class Personaggio implements Entita {
	
	private String nome;
	private static final int vitaIniziale = 20;
	private static final int potenzaIniziale = 1;
	private double vita;
	private int attacco;
	private int difesa;
	private int potenza;
	private double modificatore;
	private int posX;
	private int posY;
	private ArrayList<OggettoCesta> inventario = new ArrayList<> ();
	private OggettoCesta oggettoImpugnato;
	
	
	
	public Personaggio(String nome) {
		this.nome = nome;
		vita = vitaIniziale;
		attacco = attaccoIniziale;
		difesa = difesaIniziale;
		potenza = potenzaIniziale;
		
	}

	//Metodo che ti permette di spos
/*	public void () {
		boolean sceltaImpraticabile = false;
		do {
		char movimento;
		do {
			movimento = InputDati.leggiChar(RICHIESTA_PER_MOVIMENTO);	
		}while(movimento != 'W' && movimento != 'S' && movimento != 'D' && movimento != 'A');	
		
		if (movimento == 'W') {
			if(posY < Mappa.righeMatrice) posY++;
			else sceltaImpraticabile = true;
		}
		
		
		}while (sceltaImpraticabile);
	}
	*/
	
	public void impugna() {
		
	}
	
	public double attacca() {
		Arma arma = getArmaDaInventario();
		if (arma != null) {
			potenza = arma.getPotenza();
			}
		calcolaModificatore();
		double danno = (((2*potenza*getAttacco())/(25*getDifesa()))+2)*getModificatore();
		return danno;
	}
	
	public void subisciDanni(double danni) {
		this.vita = Math.max(0,this.vita - danni);
	}
	
	public boolean inVita() {
		return this.vita>0;
	}
	
	public void infoOggetti() {
		
	}
	
	
	private void calcolaModificatore() {
		int numTemporaneo = NumeriCasuali.estraiIntero(0, 999);
		if(numTemporaneo<75) this.modificatore = 1.5;
		else this.modificatore = 1;
	}


	public Arma getArmaDaInventario() {
		for(int i = 0; i < inventario.size(); i++) {
			if (inventario.get(i) instanceof Arma) return (Arma)inventario.get(i);// NON CERTO SE CONSIDERI L'OGGETTO COME ARMA
		}
		return null;
	}


	public String getNome() {
		return nome;
	}


	public double getVita() {
		return vita;
	}


	public int getAttacco() {
		return attacco;
	}


	public int getDifesa() {
		return difesa;
	}


	public int getPotenza() {
		return potenza;
	}


	public double getModificatore() {
		return modificatore;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	
}
