package it.unibs.pgar.esame;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibs.fp.mylib.InputDati;

public class Mappa {
	
	

	private static final String RICHIESTA_NOME = "Come vuoi chiamare il tuo eroe?";
	private String [][] mappa;
	private Personaggio player = creaPersonaggio();
	private ArrayList<Mostro> mostriMappa = new ArrayList<> ();
	private XML xml;
	
	
	
	
	
	public Mappa() {
		inizializzaMappa();
	}
	
	
	
	
	public void interazionePersonaggio() {
		
	}

	
//===========================================Metodi per la costruzione della mappa===========================================	
	private void inizializzaMappa() {
		xml = new XML(mappa);
		mappa = xml.leggiMappa();
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
	
	public void gestisciAttacco() {
		do {
			
			mostro.subisciDanni(player.attacco());
			
			player.subisciDanni(mostro.attacco());
			
		}while(p.inVita() && mostro.inVita();
		
		
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
	
	public void stampaMappa() {
		for(int i = 0; i < xml.getRigheMatrice(); i++) {
			for(int j=0; j < xml.getColonneMatrice(); j++) {
				System.out.printf("%s\t",  mappa[i][j]);
			}
			System.out.println("\n");
		}
	}
}
