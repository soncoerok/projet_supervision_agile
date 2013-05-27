package com.emn.fil.automaticdiscover.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler{

	public String hostname;
	public String os;
	
	/**
	 * Constructeur
	 */
	public SaxHandler(){
		super();
		hostname = "inconnu";
		os = "inconnu";
	}

	/**
	 * détection d'ouverture de balise

	 *@param uri l'uri de la balise

	 *@param localName le nom local de la balise

	 *@param qName le nom de la balise (exemple: "div","h1" etc...)

	 *@param attributes la liste des attributs de la balise

	 */
	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException{
		//Ouverture d'une balise => Tester sur qName pour le nom et parcourez attributes comme une liste pour la liste des attributs

		for (int index = 0; index < attributes.getLength(); index++) { // on parcourt la liste des attributs
			if (qName.equalsIgnoreCase("hostname") && hostname.equalsIgnoreCase("inconnu")){ // lorsque l'on obtient la balise hostname ou osmatch
				hostname = ""+attributes.getValue(index);
			}
			if (qName.equalsIgnoreCase("os")  && os.equalsIgnoreCase("inconnu")){ // lorsque l'on obtient la balise hostname ou osmatch
				os = ""+attributes.getValue(index);
			}
		}
	}

	/**
	 * détection fin de balise
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		//Fin de balise avec qName pour le nom de la balise
	}

	/**
	 * détection de caractères
	 */
	public void characters(char[] ch,int start, int length)
			throws SAXException{

		String lecture = new String(ch,start,length);
		//lecture est une chaine de caractère détecté  par exemple: <titre>Candide</titre> : lecture sera egal à "Candide"
	}

	/**
	 * début du parsing
	 */
	public void startDocument() throws SAXException {
		//Evenement lancé pour le début du document
		System.out.println(">>> Démarrage du parsage");
	}

	/**
	 * fin du parsing
	 */
	public void endDocument() throws SAXException {
		//Fin du document
		System.out.println(">>> Fin du parsage");
		
	}
	
	public String getHostname (){
		return hostname;
	}
	
	public String getOs(){
		return os;
	}
	
}

