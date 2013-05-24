package com.emn.fil.automaticdiscover.nmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.data.NMapRun;

public class MainEssaiNmap {

	public static void main(String[] args) {
		/*
		 * Méthode 1
		 * 
		 * >>> en attente (implementation pour windows en cours... )
		 * >>> cf. http://sourceforge.net/projects/nmap4j/forums/forum/1171634/topic/7057340
		 * 
		 * try {
			String path = "log" ;	
			Nmap4j nmap4j = new Nmap4j(path) ;
			nmap4j.addFlags( "-sV -O " ) ;
			nmap4j.includeHosts( "172.17.3.248" ) ;
			nmap4j.execute() ;
			if( !nmap4j.hasError() ) {
				NMapRun nmapRun = nmap4j.getResult() ;
				System.out.println( nmapRun ) ;
			}
		} catch (NMapInitializationException e) {
			e.printStackTrace();
		} catch (NMapExecutionException e) {
			e.printStackTrace();
		}
		 */

		/*
		 * Méthode 2 ?
		 */
		
		String system = "windows"; // windows | linux | mac
		String cmd = "ping -a localhost";
		String result;
		Process p;

		try {
			if (system.equals("windows")){
				p = Runtime.getRuntime().exec("cmd /C "+cmd);
			}else if ((system.equals("linux")) || (system.equals("mac"))){
				p = Runtime.getRuntime().exec(cmd);
			}else {
				throw new RuntimeException("Problème d'OS");
			}

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			result = "";
			while ((result = stdInput.readLine()) != null)
				// Il faudra effectuer le parse à cet endroit
				System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}