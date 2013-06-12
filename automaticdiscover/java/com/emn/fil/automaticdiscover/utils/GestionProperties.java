package com.emn.fil.automaticdiscover.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GestionProperties {

	private static String cheminFichier = "./src/main/resources/configuration.properties";
	
	public static void enregistrerProperties(String cle, String valeur){
		try {
			FileInputStream inStream = new FileInputStream(cheminFichier);
			Properties myProp = new Properties();
			myProp.load(inStream);
			myProp.setProperty(cle, valeur);
			myProp.store(new FileOutputStream(cheminFichier),"Fichier de configuration de l'application");
			inStream.close();
		} catch (FileNotFoundException e) {  
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}

