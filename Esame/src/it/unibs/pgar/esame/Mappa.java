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
	private Personaggio player;
	private ArrayList<Mostro> mostriMappa = new ArrayList<> ();
	private ArrayList<Cesta> elencoCeste = new ArrayList<> ();
	private XML xml;
	public static int righeMatrice;
	public static int colonneMatrice;
	private boolean cestaVicina = false;
	
	
	
	
	
	public Mappa() {
		inizializzaMappa();
		player = creaPersonaggio();
	}
	
	/*
	 * Metodo per muovere il personaggio
	 */
	public void movimentoPersonaggio() {
		char movimento;
		boolean movimentoNonConsentito = false;
		do {
		do {
			movimento = InputDati.leggiChar(RICHIESTA_PER_MOVIMENTO);	
		}while(movimento != 'W' && movimento != 'S' && movimento != 'D' && movimento != 'A');	
		
		movimentoNonConsentito = guardaOltre(movimento);
		if(movimentoNonConsentito) {
			System.out.println("Non puoi muoverti in quella direzione!");
		}
		
		}while(movimentoNonConsentito);
	}
	
	
	/*
	 * Metodo per controllare la posizione che intendo occupare con il personaggio
	 */
	private boolean guardaOltre(char movimento) {
		boolean movimentoImpossibile = false;
		String posizioneSuccessiva = null;
		int newPosX = 0;
		int newPosY = 0;
		
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
		else if(posizioneSuccessiva.equals("M")) {
			boolean mortePersonaggio = gestisciScontro(newPosX,newPosY);
			aggiornaMappa(newPosX, newPosY);
			if(mortePersonaggio) {
				player.mortePersonaggio();
			}
			player.impostaPosizione(newPosX,newPosY);
		}
		else if(posizioneSuccessiva.equals("C")) {
			cestaVicina = true;
			aggiornaMappa(newPosX, newPosY);
			player.impostaPosizione(newPosX,newPosY);
			
		}
		else if(posizioneSuccessiva.equals("K")) {
			aggiornaMappa(newPosX, newPosY);
			player.impostaPosizione(newPosX,newPosY);
			dichiaraVittoria();
		}
		
		
		
		return movimentoImpossibile;
	}
	
	/*
	 * Metodo per aprire una cesta
	 */
	public void aperturaCesta(int posX, int posY) {
		OggettoCesta oggetto = null;
			for(int i = 0; i < elencoCeste.size(); i++) {
				if(posX == elencoCeste.get(i).getPosX() && posY == elencoCeste.get(i).getPosY()) {
					oggetto = elencoCeste.get(i).getContenuto();
					player.impostaOggetto(oggetto);
					break;
			}
		}
		
	}
	
	/*
	 * Metodo che viene invocato al raggiungimento della principessa Kibo
	 */
	public void dichiaraVittoria() {
		System.out.println("Complimenti!\n Hai salvato la principessa Kibo");
		stampaMappa();
	}
	

	
//===========================================Metodi per la costruzione della mappa===========================================	
	/*
	 * Metodo per leggere le informazioni dell'xml
	 */
	private void inizializzaMappa() {
		xml = new XML(mappa);
		mappa = xml.leggiMappa();
		inserisciPrincipessa();
		eliminaEspansioni();
		
	}
	
	/*
	 * Metodo per inserire una principessa nella mappa
	 */
	private void inserisciPrincipessa() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("T")) {
					mappa[i][j] = "K";
				}
			}
		}
	}
	
	/*
	 * Metodo per eliminare gli oggetti appartenenti ad altri moduli
	 */
	private void eliminaEspansioni() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("B") || mappa[i][j].equals("P")) {
					mappa[i][j] = ".";
				}
			}
		}
	}
	
	/*
	 * Metodo per creare un elenco con tutti i mostri della mappa
	 */
	private void creaElencoMostri() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("M")){
					String nome = permutaMostro();
					mostriMappa.add(new Mostro(nome, j, i));
				}
			}
		}
	}
	
	/*
	 * Metodo per creare un elenco con tutte le ceste della mappa
	 */
	private void creaElencoCeste() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("C")){
					elencoCeste.add(new Cesta( j, i));
				}
			}
		}
	}
	
	/*
	 * Metodo per gestire lo scontro tra il personaggio e un mostro
	 */
	public boolean gestisciScontro(int mostroX, int mostroY) {
		Mostro mostro = null;
		boolean mortePersonaggio = false;
		do {
			
			for(int i = 0; i < mostriMappa.size(); i++) {
				if(mostroX == mostriMappa.get(i).getPosX() && mostroY == mostriMappa.get(i).getPosY()) {
					mostro = mostriMappa.get(i);
				}
			}
			mostro.subisciDanni(player.attacca());
			
			player.subisciDanni(mostro.attacca());
			
		}while(player.inVita() && mostro.inVita());
		if(player.inVita()) {
			System.out.println("Hai sconfitto il mostro!!");
		}
		else {
			System.out.println("Sei stato sconfitto, ma hai combattuto con onore!");
			mortePersonaggio = true;
		}
		return mortePersonaggio;
	}
	
	/*
	 * Metodo per creare il personaggio
	 */
	private Personaggio creaPersonaggio() {
		String nome = InputDati.leggiStringaNonVuota(RICHIESTA_NOME);
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				if(mappa[i][j].equals("O")){
				return new Personaggio(nome, j, i);
				}
			}
		}
		return null;
	}
	
	public boolean getCestaVicina() {
		return cestaVicina;
	}
	
	public Personaggio getPersonaggio() {
		return player;
	}
	
	public int getPlayerX() {
		return player.getPosX();
	}
	
	public int getPlayerY() {
		return player.getPosY();
	}

	/*
	 * Metodo per permutare la stringa "dijkstra"
	 */
	public String permutaMostro() {
		String str = "dijkstra";        
		String str2 = str.chars().mapToObj(e->(char)e).collect(Collectors.toMap(key -> new Random().nextInt(), value -> value)).values().stream().map(String::valueOf).collect(Collectors.joining());
		return str2;
	}
	
	/*
	 * Metodo per aggiornare la mappa dopo uno spostamento
	 */
	private void aggiornaMappa(int newPosX, int newPosY) {
		mappa[player.getPosY()][player.getPosX()] = ".";
		mappa[newPosY][newPosX] = "O";
	}
	
	/*
	 * Metodo per stampare la mappa
	 */
	public void stampaMappa() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				System.out.printf("%s ",  mappa[i][j]);
			}
			System.out.println("\n");
		}
	}
}
