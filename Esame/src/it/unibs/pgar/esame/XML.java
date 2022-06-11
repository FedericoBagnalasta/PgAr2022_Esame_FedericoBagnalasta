package it.unibs.pgar.esame;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class XML {
	
	private String filename = "livello1.xml";

	 XMLInputFactory xmlif;
	 XMLStreamReader xmlr;
	 
	 
	 
	 
	 
	 public XML(int[][]mappa) {
		
	}





	public void leggiMappa() {
		
	 
	 try {
	 xmlif = XMLInputFactory.newInstance();
	 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename)); 
	} catch(Exception e) {
	 System.out.println("Errore nell'inizializzazione del reader:");
	 System.out.println(e.getMessage()); 
	}
	 
	 
	 
	 
	 while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione switch (xmlr.getEventType()) { // switch sul tipo di evento
		 switch(xmlr.getEventType()) {
	 
	 
	 case XMLStreamConstants.START_DOCUMENT: 
		 
		 
	 case XMLStreamConstants.START_ELEMENT: 
		 
		 
		
		 for (int i = 0; i < xmlr.getAttributeCount(); i++)
			 	break;
	 case XMLStreamConstants.END_ELEMENT: 
		 
		 
 
	 
	 case XMLStreamConstants.COMMENT:
		 
		 
	 break; // commento: ne stampa il contenuto
	 
	 
	 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
	 if (xmlr.getText().trim().length() > 0) System.out.println("-> " + xmlr.getText());
	 break; }
	    xmlr.next();
	 
	 
	 
	 
	 
	 }
	 
	 
	 
	 
	 
}
