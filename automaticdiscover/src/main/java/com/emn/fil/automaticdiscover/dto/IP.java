package com.emn.fil.automaticdiscover.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emn.fil.automaticdiscover.exception.IPFormatException;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;

public class IP implements Comparable<IP> {

	private String ip;
	private static Pattern patternIP = Pattern
			.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	/**
	 * Creates a new instance of IP.
	 * 
	 * @param ip the new IP to create.
	 * @throws IPFormatException
	 */
	@SuppressWarnings("static-access")
	public IP(String ip) {
		if (this.isValidIP(ip)){
			this.ip = ip;
		} else {
			ShowDialog dialog = new ShowDialog("Mauvaise IP, saisir une ip valide : " + ip);
			dialog.setVisible(true);
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String toString() {
		return ip;
	}

	public int compareTo(IP iPToCompare) {
		return this.ip.compareTo(iPToCompare.ip);
	}

	@Override
	public boolean equals(Object objectIP) {
		if (objectIP instanceof IP) {
			return this.ip.equals(((IP) objectIP).ip);
		} else{
			return false;
		}	
	}

	@Override
	public int hashCode() {
		return this.ip.hashCode();
	}

	public static boolean isValidIP(String ip) {
		Matcher matcher = patternIP.matcher(ip);
		return matcher.matches();
	}
}
