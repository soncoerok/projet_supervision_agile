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

	private final String OS_WINDOWS = "windows";
	private final String OS_LINUX = "linux";
	private final String OS_MAC = "mac";
	private final String SEP_MACHINE = "host";
	private List<Machine> listeMachine = new ArrayList<Machine>();
	private List<BaliseXML> listeBalise = new ArrayList<BaliseXML>();
	private Scan scan;
	private String dateScan;
	private String adresseIp;
	private String hostname;
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
		if (qName.equalsIgnoreCase(this.SEP_MACHINE)){
			verifMachine();
			Machine machine = new Machine(new IP(adresseIp), osType, hostname);
			listeMachine.add(machine);
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
		dateScan = "";
		reset();
	}

	/**
	 * fin du parsing
	 */
	@Override
	public void endDocument() throws SAXException {
		scan = new Scan(listeMachine, dateScan);
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
		if (hostname.isEmpty()){
			hostname = "unknown";
		}
	}
	
	private void reset(){
		adresseIp = "";
		hostname = "";
		osType = OsType.UNKNOWN;
	}

	private void _parseDateScan(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("nmaprun")){
			for (int index = 0; index < valeur.getLength(); index++){
				if (balise.getNomAttribut().contains(valeur.getQName(index))){
					if (dateScan.isEmpty()){
						dateScan = valeur.getValue(index);
					}
				}
			}
		}
	}

	private void _parseAdresseIP(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("address")){
			String tmp = "";
			for (int index = 0; index < valeur.getLength(); index++){
				if (balise.getNomAttribut().contains(valeur.getQName(index))){
					if (valeur.getQName(index).equalsIgnoreCase("addr")){
						tmp = valeur.getValue(index);
					}else if (valeur.getQName(index).equalsIgnoreCase("addrtype")){
						if (valeur.getValue(index).equalsIgnoreCase("ipv4")){
							if (adresseIp.isEmpty()){
								adresseIp = tmp;
							}
						}
					}
				}
			}
		}
	}

	private void _parseHostname(String nomBalise, Attributes valeur) {
		if (nomBalise.equalsIgnoreCase("hostname")){
			for (int index = 0; index < valeur.getLength(); index++){
				if (valeur.getQName(index).equalsIgnoreCase("name")){
					if (hostname.isEmpty()){
						hostname = valeur.getValue(index);
					}
				}
			}
		}
	}

	private void _parseOsType(String nomBalise, Attributes valeur,
			BaliseXML balise) {
		if (nomBalise.equalsIgnoreCase("osclass")){
			for (int index = 0; index < valeur.getLength(); index++){
				if (balise.getNomAttribut().contains(valeur.getQName(index))){
					if (osType.equals(OsType.UNKNOWN)){
						osType = OsType.UNKNOWN;
						if (valeur.getValue(index).toLowerCase().contains(OS_WINDOWS)){
							osType = OsType.WINDOWS;
						} else if (valeur.getValue(index).toLowerCase().contains(OS_LINUX)){
							osType = OsType.UNIX;
						} else if (valeur.getValue(index).toLowerCase().contains(OS_MAC)){
							osType = OsType.OSX;
						}
					}
				}
			}
		}
	}
}
