package com.emn.fil.automaticdiscover.nmap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.emn.fil.automaticdiscover.parser.SaxHandler;


public class MainEssaiNmap {

	public static void main(String[] args) throws IOException {
		String chemin_nmap = "D:\\nmap-6.25\\";
		String adresse_ip = "localhost";
		String chemin = ".\\resultat.xml";
		String cmd = chemin_nmap + "nmap.exe -O --system-dns -Pn -oX " + chemin + " " + adresse_ip;
		System.out.println(cmd);
		Runtime.getRuntime().exec(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		Scanner s = new Scanner(p.getInputStream());
		while(s.hasNext()){
			System.out.println(s.nextLine());
		}
		s.close();
		p.destroy();
		parsageResultat();
	}
	
	public static void parsageResultat(){
		try{
			SAXParserFactory usine = SAXParserFactory.newInstance();
			SAXParser parseur =usine.newSAXParser();

			File fichier =new File(".\\resultat.xml");
			SaxHandler gestionnaire =new SaxHandler();
			parseur.parse(fichier, gestionnaire);
			
			System.out.println("\nRésultat :\n");
			System.out.println("hostname : "+gestionnaire.getHostname());
			System.out.println("os : "+gestionnaire.getOs());
			
		}catch (Exception e) {
			System.out.println("Erreur avec le parsage XML");
		}
	}
}