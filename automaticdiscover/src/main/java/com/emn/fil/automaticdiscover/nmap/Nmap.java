package com.emn.fil.automaticdiscover.nmap;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.dto.IPMask;
import com.emn.fil.automaticdiscover.dto.Scan;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;
import com.emn.fil.automaticdiscover.parser.BaliseXML;
import com.emn.fil.automaticdiscover.parser.SaxHandler;
import com.emn.fil.automaticdiscover.utils.Commande;
import com.emn.fil.automaticdiscover.utils.ExecutionProgramme;

/** Classe qui permet d'utiliser la commande Nmap. 
 * 
 * @author Clement
 *
 */
@Component
public class Nmap {

	/** Chemin vers l'application nmap. */
	private @Value("${chemin_nmap}") String cheminNmap;

	/** Chemin où on enregistre le fichier xml. */
	private static final String CHEMIN_ENREGISTREMENT_FICHIER = "./resultat.xml";
	
	@Autowired
	private Scan scan;
	
	public Nmap() {}
	
	public Nmap(IPMask ipMask) throws IOException {
		scan = scanner(ipMask);
	}

	/** Méthode permettant de scanner le réseau indiqué.
	 * 
	 * @param ipMask est l'ip voulue
	 * @throws IOException
	 */
	private Scan scanner(IPMask ipMask) throws IOException{
		/* Build of the commande. */
		Commande commandeNmap = new Commande(cheminNmap);
		
		// Process to do
		commandeNmap.ajouterOption(OptionsNmap.HOST_NAME_DETECTION.getOption());
		commandeNmap.ajouterOption(OptionsNmap.OS_DETECTION.getOption());
		
		// Timing and performing
		// commandeNmap.ajouterOption(OptionsNmap.MOINS_DE_PORTS.getOption());
		// commandeNmap.ajouterOption(OptionsNmap.PROTOCOLE_ICMP.getOption());
		// commandeNmap.ajouterOption(OptionsNmap.PORTS_UTILES.getOption());
		//commandeNmap.ajouterOption(OptionsNmap.FASTER.getOption());
		commandeNmap.ajouterOptionAvecArguments(OptionsNmap.TEMPS_MAX_ABANDON_PAQUET.getOption(), new String[]{"100ms"});
		// commandeNmap.ajouterOptionAvecArguments(OptionsNmap.TEMPS_INITIAL_ABADON_PAQUET.getOption(),new String[]{"20ms"});
		commandeNmap.ajouterOption(OptionsNmap.NOMBRE_ESSAI_SANS_REPONSES.getOption());
		// commandeNmap.ajouterOption(OptionsNmap.TEMPS_ABANDON_MACHINE.getOption());
		commandeNmap.ajouterOptionAvecArguments(OptionsNmap.EXCLUSION_IP.getOption(), new String[]{InetAddress.getLocalHost().getHostAddress()});

		// Output
		commandeNmap.ajouterOptionAvecArguments(OptionsNmap.EXPORT_XML.getOption(),new String[]{CHEMIN_ENREGISTREMENT_FICHIER});
		
		commandeNmap.ajouterOption(ipMask.toString());
		System.out.println(commandeNmap.getCommandeFinale());
		
		// Exec
		ExecutionProgramme.exec(commandeNmap.getCommandeFinale());
		
		// Parsing of the xml generated by nmap.
		return parsageResultat();
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

			File fichier = new File(CHEMIN_ENREGISTREMENT_FICHIER);
			SaxHandler gestionnaire = new SaxHandler();
			gestionnaire.setListeBalise(listeBalise);
			parseur.parse(fichier, gestionnaire);
			
			return gestionnaire.getScan();
		} catch (Exception e) {
			Main.log.trace(e.getStackTrace());
			ShowDialog dialog = new ShowDialog("Problème lors de la récupération des machines !\n" + e.getMessage());
			dialog.setVisible(true);
		}
		return null;
	}
	
	public Scan getScan() {
		return scan;
	}
}