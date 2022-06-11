package it.unibs.pgar.esame;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


public class XML {
	
	private String filename = "livello1.xml";
	private String tag_riga = "row";
	private String tag_cella = "cell";
	private String tag_mappa = "mappa";
	private String tag_larghezza = "width";
	private String tag_altezza = "height";		
	private String[][]mappa;
	private int riga = -1;
	private int colonna = -1;
	private int righeMatrice;
	private int colonneMatrice;
	private String elementoSalvato;

	 XMLInputFactory xmlif;
	 XMLStreamReader xmlr;
	 
	 
	 
	 
	 
	 public XML(String[][]mappa) {
		this.mappa = mappa;
	}





	public String[][]leggiMappa() {
		
		
		
	 try {
	 xmlif = XMLInputFactory.newInstance();
	 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename)); 
	} catch(Exception e) {
	 System.out.println("Errore nell'inizializzazione del reader:");
	 System.out.println(e.getMessage()); 
	}
	 
	 
	 
	 String next = null;
	 while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione switch (xmlr.getEventType()) { // switch sul tipo di evento
		 switch(xmlr.getEventType()) {
		 
		 
			 
	 
	 
		 case XMLStreamConstants.START_ELEMENT:
			 
			//creazione mappa 
			if (xmlr.getLocalName().equals(tag_mappa)) {
				
					righeMatrice = Integer.parseInt(xmlr.getAttributeValue(0));
					colonneMatrice = Integer.parseInt(xmlr.getAttributeValue(1));
					mappa = new String [righeMatrice][colonneMatrice];
		     }
			 
			 
			 
			 // inizio di un elemento: stampa il nome del tag e i suoi attributi
		     if (xmlr.getLocalName().equals(tag_riga)) {
		    	 next = tag_riga;
		     }
		     else if (xmlr.getLocalName().equals(tag_cella)) {
		    	 next = tag_cella;
			     }
		     
		 //Azioni da compiere a seconda del tag
		 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
			 if (xmlr.getText().trim().length() > 0) {
				 if (next.equals(tag_cella)) {
				 	elementoSalvato = xmlr.getText();
				 	colonna++;
				 	break;
				 }
				 if (next.equals(tag_riga)) {
					 riga++;
				 }
			 }
		     
	 
	 
		 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
			 
			 //if (xmlr.getLocalName().equals(tag_cella)){
				 
			 //}
			 
			 
			  if (xmlr.getLocalName().equals(tag_cella) ) {
				 mappa[riga][colonna] = elementoSalvato;
				colonna++;
			}
	 
	 }
	 
	 
	 }
	 
	 
	 return mappa;
	}
	 
	 
}
