package com.emn.fil.automaticdiscover.nmap;

import java.io.IOException;
import java.util.Scanner;


public class MainEssaiNmap {

	public static void main(String[] args) throws IOException {
		String chemin_nmap = "E:\\Outils\\nmap-6.25\\";
		String adresse_ip = "172.17.2.214";
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
	}
}