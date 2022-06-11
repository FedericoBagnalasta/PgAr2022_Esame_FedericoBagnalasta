package it.unibs.pgar.esame;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibs.fp.mylib.InputDati;

public class Mappa {
	
	

	
	private static final String RICHIESTA_PER_MOVIMENTO = "Premi uno tra W, S, D, A, per muoverti rispettivamente"
			+ "In avanti, In dietro, A destra, A sinistra";
	private static final String RICHIESTA_NOME = "Come vuoi chiamare il tuo eroe?";
	private String [][] mappa;
	private Personaggio player = creaPersonaggio();
	private ArrayList<Mostro> mostriMappa = new ArrayList<> ();
	private XML xml;
	public static int righeMatrice;
	public static int colonneMatrice;
	
	
	
	
	
	public Mappa() {
		inizializzaMappa();
	}
	
	// Guardo oltre e poi interegisco (se posso muovermi) altrimenti richiedo movmento
	/*public void movimentoPersonaggio() {
		char movimento;
		boolean movimentoNonConsentito = false;
		do {
		do {
			movimento = InputDati.leggiChar(RICHIESTA_PER_MOVIMENTO);	
		}while(movimento != 'W' && movimento != 'S' && movimento != 'D' && movimento != 'A');	
		
		if (movimento == 'W') {
			
		}
		
		
		}while(movimentoNonConsentito);
	}
	*/
	
	
	private boolean guardaOltre(char movimento) {
		boolean movimentoImpossibile = false;
		String posizioneSuccessiva;
		int newPosX;
		int newPosY;
		
		if (movimento == 'W') {
			 if(player.getPosY() == 0) {
				 return movimentoImpossibile = true;
			 }
			 posizioneSuccessiva = mappa[player.getPosY() - 1][player.getPosX()];
			 newPosX = player.getPosX();
			 newPosY = player.getPosY() - 1;	 
		}
		else if (movimento == 'S') {
			if(player.getPosY() == righeMatrice - 1) {
				return movimentoImpossibile = true;
			}
			posizioneSuccessiva = mappa[player.getPosY() + 1][player.getPosX()];
			newPosX = player.getPosX();
			newPosY = player.getPosY() + 1;
		}
		else if (movimento == 'D') {
			if(player.getPosX() == colonneMatrice - 1) {
				return movimentoImpossibile = true;
			}
			posizioneSuccessiva = mappa[player.getPosY()][player.getPosX() + 1];
			newPosX = player.getPosX() + 1;
			newPosY = player.getPosY();
		}
		else if (movimento == 'A') {
			if(player.getPosX() == 0) {
				return movimentoImpossibile = true;
			}
			posizioneSuccessiva = mappa[player.getPosY()][player.getPosX() - 1];
			newPosX = player.getPosX() - 1;
			newPosY = player.getPosY();
		}
		
		
		if(posizioneSuccessiva.equals("#")) return movimentoImpossibile = true;
		if(posizioneSuccessiva.equals("M")) {
			gestisciScontro(newPosX,newPosY);
			player.impostaPosizione(newPosX,newPosY);
		}
		if(posizioneSuccessiva.equals("C")) {
			aperturaCeste(newPosX,newPosY);
			player.impostaPosizione(newPosX,newPosY);
		}
		if(posizioneSuccessiva.equals("K")) {
			
			player.impostaPosizione(newPosX,newPosY);
			dichiaraVittoria();
		}
		
		aggiornaMappa(newPosX, newPosY);
		
		return movimentoImpossibile;
	}
	
	public void interazionePersonaggio() {
		
	}

	
//===========================================Metodi per la costruzione della mappa===========================================	
	private void inizializzaMappa() {
		xml = new XML(mappa);
		mappa = xml.leggiMappa();
		inserisciPrincipessa();
		eliminaEspansioni();
		
	}
	
	private void inserisciPrincipessa() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("T")) {
					mappa[i][j] = "K";
				}
			}
		}
	}
	
	
	private void eliminaEspansioni() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("B") || mappa[i][j].equals("P")) {
					mappa[i][j] = ".";
				}
			}
		}
	}
	
	
	private void creaElencoMostri() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("M")){
					String nome = permutaMostro();
					mostriMappa.add(new Mostro(nome, i, j));
				}
			}
		}
	}
	
	public void gestisciScontro(int mostroX, int mostroY) {
		
		do {
			Mostro mostro;
			for(int i = 0; i < mostriMappa.size(); i++) {
				if(mostroX == mostriMappa.get(i).getPosX() && mostroY == mostriMappa.get(i).getPosY()) {
					mostro = mostriMappa.get(i);
				}
			}
			
			
			mostro.subisciDanni(player.attacca());
			
			player.subisciDanni(mostro.attacca());
			
		}while(player.inVita() && mostro.inVita());
		
		
	}
	
	
	private Personaggio creaPersonaggio() {
		String nome = InputDati.leggiStringaNonVuota(RICHIESTA_NOME);
		return new Personaggio(nome);
	}
	
	
	//Da salvare in libreria
	public String permutaMostro() {
		String str = "dijkstra";        
		String str2 = str.chars().mapToObj(e->(char)e).collect(Collectors.toMap(key -> new Random().nextInt(), value -> value)).values().stream().map(String::valueOf).collect(Collectors.joining());
		return str2;
	}
	
	private void aggiornaMappa(int newPosX, int newPosY) {
		mappa[player.getPosY()][player.getPosX()] = ".";
		mappa[newPosY][newPosX] = "O";
	}
	
	public void stampaMappa() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				System.out.printf("%s\t",  mappa[i][j]);
			}
			System.out.println("\n");
		}
	}
}
