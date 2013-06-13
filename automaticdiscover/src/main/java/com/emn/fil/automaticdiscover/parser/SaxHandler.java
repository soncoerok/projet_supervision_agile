package com.emn.fil.automaticdiscover.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.dto.Scan;
import com.emn.fil.automaticdiscover.dto.enums.OsType;

public class SaxHandler extends DefaultHandler{

	private static final String OS_WINDOWS = "windows";
	private static final String OS_LINUX = "linux";
	private static final String OS_MAC = "mac";
	private static final String OS_IOS = "ios";
	private static final String SEP_MACHINE = "host";
	private List<Machine> listeMachine = new ArrayList<Machine>();
	private List<BaliseXML> listeBalise = new ArrayList<BaliseXML>();
	private Scan scan;
	private String dateScan;
	private String adresseIp;
	private String hostname;
	private String hostnameMac;
	private OsType osType;


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
		for (BaliseXML balise : listeBalise) {
			if (balise.getNomBalise().equalsIgnoreCase(nomBalise)){
				_parseDateScan(nomBalise, valeur, balise);
				_parseAdresseIP(nomBalise, valeur, balise);
				_parseHostname(nomBalise, valeur);
				_parseOsType(nomBalise, valeur, balise);		
			}
		}
	}

	/**
	 * détection fin de balise
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.equalsIgnoreCase(SEP_MACHINE)){
			verifMachine();
			Machine machine = new Machine(new IP(adresseIp), osType, hostname);
			this.listeMachine.add(machine);
			reset();
		}
	}

	/**
	 * détection de caractères
	 */
	@Override
	public void characters(char[] ch,int start, int length) throws SAXException {}

	/**
	 * début du parsing
	 */
	@Override
	public void startDocument() throws SAXException {
		this.dateScan = "";
		reset();
	}

	/**
	 * fin du parsing
	 */
	@Override
	public void endDocument() throws SAXException {
		this.scan = new Scan(listeMachine, dateScan);
	}

	// ***** GETTERS *****

	public Scan getScan() {
		return scan;
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

	// ***** METHODES PRIVEES *****

	private void verifMachine() {
		if (this.hostname.isEmpty()) {
			if (this.hostnameMac.isEmpty()) {
				this.hostname = "unknown";
			} else {
				this.hostname = "[" + this.hostnameMac + "]";
			}
		}
	}

	private void reset() {
		this.adresseIp = "";
		this.hostname = "";
		this.hostnameMac = "";
		this.osType = OsType.UNKNOWN;
	}

	private void _parseDateScan(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("nmaprun")){
			for (int index = 0; index < valeur.getLength(); index++){
				if (balise.getNomAttribut().contains(valeur.getQName(index)) && this.dateScan.isEmpty()){
					this.dateScan = valeur.getValue(index);
				}
			}
		}
	}

	private void _parseAdresseIP(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("address")) {
			String tmp = "";
			for (int index = 0; index < valeur.getLength(); index++) {
				if (balise.getNomAttribut().contains(valeur.getQName(index))) {
					if (valeur.getQName(index).equalsIgnoreCase("addr")) {
						tmp = valeur.getValue(index);
					} else if (valeur.getQName(index).equalsIgnoreCase("addrtype")) {
						if (valeur.getValue(index).equalsIgnoreCase("ipv4") && this.adresseIp.isEmpty()) {
							this.adresseIp = tmp;
						}
					} else if (valeur.getQName(index).equalsIgnoreCase("vendor") 
							&& this.hostnameMac.isEmpty()) {
						this.hostnameMac = valeur.getValue(index);
					}
				}
			}
		}
	}

	private void _parseHostname(String nomBalise, Attributes valeur) {
		if (nomBalise.equalsIgnoreCase("hostname")) {
			for (int index = 0; index < valeur.getLength(); index++) {
				if (valeur.getQName(index).equalsIgnoreCase("name") && this.hostname.isEmpty()) {
					this.hostname = valeur.getValue(index);
				}
			}
		}
	}

	private void _parseOsType(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("osclass")){
			for (int index = 0; index < valeur.getLength(); index++){
				if (balise.getNomAttribut().contains(valeur.getQName(index))){
					if (this.osType.equals(OsType.UNKNOWN)) {
						this.osType = OsType.UNKNOWN;
						if (valeur.getValue(index).toLowerCase().contains(OS_WINDOWS)){
							this.osType = OsType.WINDOWS;
						} else if (valeur.getValue(index).toLowerCase().contains(OS_LINUX)){
							this.osType = OsType.UNIX;
						} else if (valeur.getValue(index).toLowerCase().contains(OS_MAC)){
							this.osType = OsType.OSX;
						} else if (valeur.getValue(index).toLowerCase().contains(OS_IOS)){
							this.osType = OsType.OSX;
						}
					}
				}
			}
		}
	}
}
