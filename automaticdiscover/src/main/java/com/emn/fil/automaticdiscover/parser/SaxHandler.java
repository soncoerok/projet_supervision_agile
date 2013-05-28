package com.emn.fil.automaticdiscover.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.dto.enums.OsType;

public class SaxHandler extends DefaultHandler{

	List<Machine> listeMachine = new ArrayList<Machine>();
	List<BaliseXML> listeBalise = new ArrayList<BaliseXML>();
	private Machine machine = new Machine();

	/**
	 * détection d'ouverture de balise
	 *@param uri l'uri de la balise
	 *@param localName le nom local de la balise
	 *@param nomBalise le nom de la balise (exemple: "div","h1" etc...)
	 *@param valeur la liste des attributs de la balise
	 */
	@Override
	public void startElement(String uri, String localName, String nomBalise, Attributes valeur) throws SAXException{
		String ip, os, hostname;
		//Ouverture d'une balise => Tester sur qName pour le nom et parcourez attributes comme une liste pour la liste des attributs
		for (BaliseXML balise : listeBalise){
			if (balise.getNomBalise().equalsIgnoreCase(nomBalise)) {
				
				String tmp = "";
				
				for (int index = 0; index < valeur.getLength(); index++) 
					if (balise.getNomAttribut().contains(valeur.getLocalName(index))) {
						System.out.println(valeur.getValue(index));
						if (valeur.getValue(index).equalsIgnoreCase("ipv4")){
							machine.setIp(new IP(tmp));
						}
						if (balise.getNomBalise().equalsIgnoreCase("hostname")){
							machine.setHostname(valeur.getValue(index));
						}
						if (balise.getNomBalise().equalsIgnoreCase("osclass")){
							if (valeur.getValue(index).equalsIgnoreCase("windows")){
								machine.setOsType(OsType.WINDOWS);
							}else if (valeur.getValue(index).equalsIgnoreCase("linux")){
								machine.setOsType(OsType.UNIX);
							}else if (valeur.getValue(index).equalsIgnoreCase("mac")){
								machine.setOsType(OsType.OSX);
							}else {
								machine.setOsType(OsType.UNKNOWN);
							}
						}
						tmp = valeur.getValue(index);
					}
				break;
			}
		}
		
		//listeMachine.add(new Machine(new IP(ip), OsType.valueOf(os), hostname));
	}

	//for (String s : )

	/*for (int index = 0; index < attributes.getLength(); index++) { // on parcourt la liste des attributs
			if ((attributes.getLocalName(index).equalsIgnoreCase("name")) || (attributes.getLocalName(index).equalsIgnoreCase("addr"))){ 
				// lorsque l'on obtient la balise hostname ou osmatch
				System.out.println(attributes.getValue(index));

			}
		}*/

	/**
	 * détection fin de balise
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.equalsIgnoreCase("host")){
			System.out.println(machine);
			listeMachine.add(machine);
			machine = new Machine();
		}
	}

	/**
	 * détection de caractères
	 */
	@Override
	public void characters(char[] ch,int start, int length) throws SAXException{
		String lecture = new String(ch,start,length);
	}

	/**
	 * début du parsing
	 */
	@Override
	public void startDocument() throws SAXException {
	}

	/**
	 * fin du parsing
	 */
	@Override
	public void endDocument() throws SAXException {
	}	

	public List<Machine> getListeMachine() {
		return listeMachine;
	}

	public void setListeMachine(List<Machine> listeMachine) {
		this.listeMachine = listeMachine;
	}
	
	public List<BaliseXML> getListeBalise() {
		return listeBalise;
	}

	public void setListeBalise(List<BaliseXML> listeBalise) {
		this.listeBalise = listeBalise;
	}
	
	
}
