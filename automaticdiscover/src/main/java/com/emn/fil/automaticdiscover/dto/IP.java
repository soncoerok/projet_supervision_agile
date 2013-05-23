package com.emn.fil.automaticdiscover.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emn.fil.automaticdiscover.exception.IPFormatException;

public class IP implements Comparable<IP> {

	private String ip;
	private static Pattern pattern_IP = Pattern
			.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	/**
	 * Creates a new instance of IP.
	 * 
	 * @param ip
	 *            The new IP to create.
	 * @throws IPFormatException
	 */
	@SuppressWarnings("static-access")
	public IP(String ip) {
		if (this.isValidIP(ip))
			this.ip = ip;
		else
			try {
				throw new IPFormatException("BAD IP : " + ip);
			} catch (IPFormatException e) {
				e.printStackTrace();
				System.exit(-1);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IP IPToCompare) {
		return this.ip.compareTo(IPToCompare.ip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object objectIP) {
		if (objectIP instanceof IP) {
			return this.ip.equals(((IP) objectIP).ip);
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.ip.hashCode();
	}

	public static boolean isValidIP(String ip) {
		Matcher matcher = pattern_IP.matcher(ip);
		return matcher.matches();
	}
}
