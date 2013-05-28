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

	private final String OS_WINDOWS = "windows";
	private final String OS_LINUX = "linux";
	private final String OS_MAC = "mac";
	private List<Machine> listeMachine = new ArrayList<Machine>();
	private List<BaliseXML> listeBalise = new ArrayList<BaliseXML>();
	private Machine machine = new Machine();
	private String nomAttributAdresse;
	private String nomAttributHostname;
	private String nomAttributOs;
	private String nomAttributMachine;
	
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
	public String getNomAttributAdresse() {
		return nomAttributAdresse;
	}
	public String getNomAttributHostname() {
		return nomAttributHostname;
	}
	public String getNomAttributOs() {
		return nomAttributOs;
	}
	public void setNomAttributAdresse(String nomAttributAdresse) {
		this.nomAttributAdresse = nomAttributAdresse;
	}
	public void setNomAttributHostname(String nomAttributHostname) {
		this.nomAttributHostname = nomAttributHostname;
	}
	public void setNomAttributOs(String nomAttributOs) {
		this.nomAttributOs = nomAttributOs;
	}
	public String getNomAttributMachine() {
		return nomAttributMachine;
	}
	public void setNomAttributMachine(String nomAttributMachine) {
		this.nomAttributMachine = nomAttributMachine;
	}
	
	public void verifMachine(){
		if (machine.getHostname() == null){
			machine.setHostname("unknonw");
		}
		if (machine.getOsType() == null){
			machine.setOsType(OsType.UNKNOWN);
		}
	}
	
	public void razMachine(){
		machine = new Machine();
	}
	
	/**
	 * détection d'ouverture de balise
	 *@param uri l'uri de la balise
	 *@param localName le nom local de la balise
	 *@param nomBalise le nom de la balise (exemple: "div","h1" etc...)
	 *@param valeur la liste des attributs de la balise
	 */
	@Override
	public void startElement(String uri, String localName, String nomBalise, Attributes valeur) throws SAXException{
		//Ouverture d'une balise => Tester sur qName pour le nom et parcourez attributes comme une liste pour la liste des attributs
		for (BaliseXML balise : listeBalise){
			if (balise.getNomBalise().equalsIgnoreCase(nomBalise)) {
				String tmp = "";
				for (int index = 0; index < valeur.getLength(); index++) 
					if (balise.getNomAttribut().contains(valeur.getLocalName(index))) {
						if (valeur.getValue(index).equalsIgnoreCase(this.nomAttributAdresse)){
							machine.setIp(new IP(tmp));
						}
						if (balise.getNomBalise().equalsIgnoreCase(this.nomAttributHostname)){
							machine.setHostname(valeur.getValue(index));
						}
						if (balise.getNomBalise().equalsIgnoreCase(this.nomAttributOs)){
							if (valeur.getValue(index).equalsIgnoreCase(OS_WINDOWS)){
								machine.setOsType(OsType.WINDOWS);
							}else if (valeur.getValue(index).equalsIgnoreCase(OS_LINUX)){
								machine.setOsType(OsType.UNIX);
							}else if (valeur.getValue(index).equalsIgnoreCase(OS_MAC)){
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
	}

	/**
	 * détection fin de balise
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.equalsIgnoreCase(this.nomAttributMachine)){
			verifMachine();
			listeMachine.add(machine);
			razMachine();
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
}
