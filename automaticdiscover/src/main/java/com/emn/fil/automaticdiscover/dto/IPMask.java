package com.emn.fil.automaticdiscover.dto;


public class IPMask {
	private IP ip;
	private Integer masque;
	private static final char separateur = '/';

	/** Premier constructeur sans masque, une et seule ip. */
	public IPMask(IP ip){
		super();
		this.ip = ip;
	}
	
	/** Deuxième constructeur avec masque, pour une plage d'adresse ip. */
	public IPMask(IP ip, int masque) {
		super();
		this.ip = ip;
		this.masque = masque;
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public int getMasque() {
		return masque;
	}

	public void setMasque(int masque) {
		this.masque = masque;
	}
	
	public String toString(){
		String resultat = this.ip.toString();
		if(masque !=null && masque !=32){
			resultat += String.valueOf(separateur) + this.masque;
		}
		return resultat;
	}

}
