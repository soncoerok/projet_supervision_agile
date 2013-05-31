package com.emn.fil.automaticdiscover.nmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.dto.IPMask;
import com.emn.fil.automaticdiscover.dto.Scan;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;
import com.emn.fil.automaticdiscover.parser.BaliseXML;
import com.emn.fil.automaticdiscover.parser.SaxHandler;

/** Classe qui permet d'utiliser la commande Nmap. 
 * 
 * @author Clement
 *
 */
@Component("nmap")
public class Nmap {

	/** Chemin vers l'application nmap. */
	@Value("${chemin_nmap}")
	private String cheminNmap;

	/** Chemin où on enregistre le fichier xml. */
	private static final String cheminEnregistrementFichier = "./resultat.xml";

	/** Méthode permettant de scanner le réseau indiqué.
	 * 
	 * @param ipMask est l'ip voulue
	 * @throws IOException
	 */
	public void scanner(IPMask ipMask) throws IOException{
		StringBuilder commandeNmap = new StringBuilder();
		commandeNmap.append(cheminNmap);
		commandeNmap.append(" " + OptionsNmap.OS_DETECTION.getCommande());
		commandeNmap.append(" " + OptionsNmap.HOST_NAME_DETECTION.getCommande());
		commandeNmap.append(" " + OptionsNmap.PING.getCommande());
		commandeNmap.append(" " + OptionsNmap.EXPORT_XML.getCommande());
		commandeNmap.append(" " + cheminEnregistrementFichier);
		commandeNmap.append(" " + ipMask);
		Process processNmap = Runtime.getRuntime().exec(commandeNmap.toString());
		Scanner scannerNmap = new Scanner(processNmap.getInputStream());
		while(scannerNmap.hasNext()) {
			System.out.println(scannerNmap.nextLine());
		}
		scannerNmap.close();
		processNmap.destroy();
		parsageResultat();
	}

	public static Scan parsageResultat() {
		try {
			// definition des attributs a recup
			List<BaliseXML> listeBalise = new ArrayList<BaliseXML>();
			listeBalise.add(new BaliseXML("nmaprun", Arrays.asList("startstr")));
			listeBalise.add(new BaliseXML("address", Arrays.asList("addr", "addrtype")));
			listeBalise.add(new BaliseXML("hostname", Arrays.asList("name")));
			listeBalise.add(new BaliseXML("osclass", Arrays.asList("osfamily")));

			SAXParserFactory usine = SAXParserFactory.newInstance();
			SAXParser parseur = usine.newSAXParser();

			File fichier = new File(cheminEnregistrementFichier);
			SaxHandler gestionnaire = new SaxHandler();
			parametrageSaxHandler(gestionnaire, "startstr","ipv4", "hostname", "osclass", "host");
			gestionnaire.setListeBalise(listeBalise);
			parseur.parse(fichier, gestionnaire);
			
			System.out.println("Liste : " + gestionnaire.getScan().toString());
			return gestionnaire.getScan();
		} catch (Exception e) {
			Main.log.error(e.getStackTrace());
			ShowDialog dialog = new ShowDialog("Problème lors de la récupération des machines !\n" + e.getMessage());
			dialog.setVisible(true);
		}
		return null;
	}
	
	public static void parametrageSaxHandler(SaxHandler gestionnaire, String baliseDate, String baliseAddress,
			String baliseHostname, String baliseOs, String baliseSeparationMachine){
		gestionnaire.setDateScan(baliseDate);
		gestionnaire.setNomAttributAdresse(baliseAddress);
		gestionnaire.setNomAttributHostname(baliseHostname);
		gestionnaire.setNomAttributOs(baliseOs);
		gestionnaire.setNomAttributMachine(baliseSeparationMachine);
	}
}