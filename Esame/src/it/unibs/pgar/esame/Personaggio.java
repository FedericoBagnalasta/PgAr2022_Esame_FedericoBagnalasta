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
	
	
	
	public Personaggio(String nome, int posX, int posY) {
		this.nome = nome;
		vita = vitaIniziale;
		attacco = attaccoIniziale;
		difesa = difesaIniziale;
		potenza = potenzaIniziale;
		this.posX = posX;
		this.posY =posY;
		
	}

	/*
	 * Metodo per impostare l'oggetto impugnato dal personaggio
	 */
	public void impugna() {
		stampaInventario();
		String scelta = InputDati.leggiStringaNonVuota("Quale strumento vuoi impugnare?");
		for (int i = 0; i < inventario.size(); i++) {
			if(inventario.get(i).getNomeOggetto().equals(scelta)) {
				oggettoImpugnato = inventario.get(i);
				return;
			}
		}
		System.out.println("Oggetto non trovato");
	}
	
	/*
	 * Metodo che gestisce l'attacco di un personaggio
	 */
	public double attacca() {
		Arma arma = getArmaDaInventario();
		if (arma != null) {
			potenza = arma.getPotenza();
			}
		calcolaModificatore();
		double danno = (((2*potenza*getAttacco())/(25*getDifesa()))+2)*getModificatore();
		return danno;
	}
	
	/*
	 * Metodo che infligge danni al personaggio
	 */
	public void subisciDanni(double danni) {
		this.vita = Math.max(0,this.vita - danni);
	}
	
	/*
	 * Metodo che verifica se il personaggio e' ancora in vita
	 */
	public boolean inVita() {
		return this.vita>0;
	}
	
	public void mortePersonaggio() {
		System.out.println("");
	}
	
	public void impostaPosizione(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void infoOggetti() {
		
	}
	
	/*
	 * Metodo che permette al personaggio di bere una pozione
	 */
	public void beviPozione() {
		if(oggettoImpugnato.getNomeOggetto().equals("Pozione")) {
			int effettoPozione = ((Pozione)oggettoImpugnato).riceviEffettoPozione();
			vita = Math.min(vitaIniziale, vita + (vita * effettoPozione/100));
		}
		else {
			System.out.println("Devi avere in mano una pozione per usarla!");
		}
	}
	
	public void impostaOggetto(OggettoCesta oggetto) {
		oggettoImpugnato = oggetto;
	}
	
	/*
	 * Metodo per estrarre il valore del modificatore
	 */
	private void calcolaModificatore() {
		int numTemporaneo = NumeriCasuali.estraiIntero(0, 999);
		if(numTemporaneo<75) this.modificatore = 1.5;
		else this.modificatore = 1;
	}

	/*
	 * Metodo per recuperare un arma, se contenuta nell'inventario
	 */
	public Arma getArmaDaInventario() {
		for(int i = 0; i < inventario.size(); i++) {
			if (inventario.get(i) instanceof Arma) return (Arma)inventario.get(i);
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
	
	/*
	 * Metodo per stampare l'inventario del personaggio
	 */
	public void stampaInventario() {
		System.out.println("Gli oggetti disponibili sono:");
		for (int i = 0; i < inventario.size(); i++) {
			System.out.println(inventario.get(i).getNomeOggetto());
		}
	}
	
	
	
}
