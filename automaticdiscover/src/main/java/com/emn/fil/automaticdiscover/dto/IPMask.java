package com.emn.fil.automaticdiscover.dto;


public class IPMask {
	private IP ip;
	private int masque;
	private final char separateur = '/';

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
		return ip.toString() + this.separateur + this.masque;
	}

}
