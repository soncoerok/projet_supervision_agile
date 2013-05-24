package com.emn.fil.automaticdiscover.nmap;

import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.data.NMapRun;

public class MainEssaiNmap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String path = "./" ;	
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

	}

}
