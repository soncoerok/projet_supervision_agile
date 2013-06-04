package com.emn.fil.automaticdiscover.nmap;

/**
 * Enum qui définit quelques options utiles à la commande nmap.
 * 
 * @author Clement
 * 
 */
public enum OptionsNmap {
	/** Traitements de services */	 
	OS_DETECTION("-O"), 
	HOST_NAME_DETECTION("--system-dns"),
	
	/** Timing and performing */
	TREAT_ALL_HOSTS_AS_ONLINE("-Pn"),
	MOINS_DE_PORTS("-F"),
	PROTOCOLE_ICMP("-PE"),
	PORTS_UTILES("--top-ports 5"), 
	EXCLUSION_IP("--exclude"),
	FASTER("-T5"),
	TEMPS_MAX_ABANDON_PAQUET("--max-rtt-timeout 130ms"), // home : 20ms
	TEMPS_INITIAL_ABANDON_PAQUET("--initial-rtt-timeout 20ms"), // home 1ms
	NOMBRE_ESSAI_SANS_REPONSES("--max-retries 0"), // home 0
	TEMPS_ABANDON_MACHINE("--host-timeout 40s"), // home 20s
	
	/** Output */
	EXPORT_XML("-oX");

	private String option;

	private OptionsNmap(String option) {
		this.option = option;
	}

	public String getOption() {
		return this.option;
	}
}
