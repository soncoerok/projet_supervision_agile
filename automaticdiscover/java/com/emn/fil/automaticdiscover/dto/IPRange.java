package com.emn.fil.automaticdiscover.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.emn.fil.automaticdiscover.Main;

public class IPRange implements Iterator<IP> {

	private IP ip1;
	private IP ip2;
	// the 2 hashes contains the 2 ips split into 4 parts
	private int[] ip1Int = new int[4], ip2Int = new int[4];
	// number if ip adresses in the range
	private int numbOfIp;
	private List<IP> ipInTheRange = new ArrayList<IP>();

	private int indexCurrentIP;

	/**
	 * Splits the 2 ips into 4 parts each.
	 * 
	 * @param ip1
	 *            The first IP to split where the ip range search begins.
	 * @param ip2
	 *            The second IP to split where the ip range search ends.
	 */
	public IPRange(IP ip1, IP ip2) {
		this.ip1 = ip1;
		this.ip2 = ip2;
		this.ip1Int = splitIPInFour(ip1.getIp());
		this.ip2Int = splitIPInFour(ip2.getIp());

		Main.log.trace("IP de d&eacute;part : " + ip1.toString());
		Main.log.trace("IP d'arriv&eacute;e : " + ip2.toString());

		this.calculateIPInRange();

		this.indexCurrentIP = 0;
	}

	/**
	 * Split an IP in four and returns an int hash.
	 * 
	 * @param ipToSplit
	 *            The IP to split.
	 */
	private int[] splitIPInFour(String ipToSplit) {
		int[] ipParsedInt = new int[4];
		String[] ipParsed = ipToSplit.split("\\.");
		ipParsedInt[0] = Integer.parseInt(ipParsed[0]);
		ipParsedInt[1] = Integer.parseInt(ipParsed[1]);
		ipParsedInt[2] = Integer.parseInt(ipParsed[2]);
		ipParsedInt[3] = Integer.parseInt(ipParsed[3]);
		return ipParsedInt;
	}

	/**
	 * Returns an array with the ips in the range.
	 */
	public List<IP> getTheIPRange() {
		return ipInTheRange;
	}

	/**
	 * Increments the first IP until it reaches the second one.
	 */
	public void calculateIPInRange() {
		int[] currentIp1 = new int[4];
		currentIp1 = this.ip1Int;
		// tells the algo when to stop calculating
		int stopAt;
		if (this.ip2Int[0] - this.ip1Int[0] == 0) {
			if (this.ip2Int[1] - this.ip1Int[1] == 0) {
				if (this.ip2Int[2] - this.ip1Int[2] == 0) {
					stopAt = 4;
				} else {
					stopAt = 3;
				}
			} else {
				stopAt = 2;
			}
		} else {
			stopAt = 1;
		}

		Main.log.trace("L'algo s'arrête au niveau " + stopAt);
		// décrémente de 1 l'adresse d'arrivée pour ne pas boucler é l'infini si
		// =255
		boolean is255 = false;
		if (this.ip2Int[stopAt - 1] == 255) {
			this.ip2Int[stopAt - 1] = this.ip2Int[stopAt - 1] - 1;
			this.ip2 = new IP(this.createIP(this.ip2Int));
			Main.log.trace("L'adresse s'arrêtant à 255, on la décrémente. ip d'arrivée = "
					+ this.ip2.toString());
			is255 = true;
		}
		int i = 1;
		Main.log.trace("Une nouvelle adresse a été rajoutée : " + this.ip1);
		this.ipInTheRange.add(this.ip1);
		while (true) {
			if (currentIp1[3] < 254) {
				currentIp1[3]++;
				this.ipInTheRange.add(new IP(createIP(currentIp1)));
				Main.log.trace("Une nouvelle adresse a été rajoutée : "
						+ this.ipInTheRange.get(i).toString());
				if (this.ipInTheRange.get(i).equals(this.ip2)){
					break;
				}
			} else if (currentIp1[2] < 254) {
				currentIp1[3] = 0;
				currentIp1[2]++;
				this.ipInTheRange.add(new IP(createIP(currentIp1)));
				Main.log.trace("Une nouvelle adresse a été rajoutée : "
						+ this.ipInTheRange.get(i).toString());
				if (this.ipInTheRange.get(i).equals(this.ip2)){
					break;
				}
			} else if (currentIp1[1] < 254) {
				currentIp1[3] = 0;
				currentIp1[2] = 0;
				currentIp1[1]++;
				ipInTheRange.add(new IP(createIP(currentIp1)));
				Main.log.trace("Une nouvelle adresse a été rajoutée : "
						+ this.ipInTheRange.get(i).toString());
				if (this.ipInTheRange.get(i).equals(this.ip2)){
					break;
				}
			} else if (currentIp1[0] < 254) {
				currentIp1[3] = 0;
				currentIp1[2] = 0;
				currentIp1[1] = 0;
				currentIp1[0]++;
				this.ipInTheRange.add(new IP(createIP(currentIp1)));
				Main.log.trace("Une nouvelle adresse a été rajoutée : "
						+ this.ipInTheRange.get(i).toString());
				if (this.ipInTheRange.get(i).equals(this.ip2))
					break;
			}

			i++;
		}
		if (is255) {
			this.ip2Int[stopAt - 1] = this.ip2Int[stopAt - 1] + 1;
			this.ip2 = new IP(this.createIP(this.ip2Int));
			Main.log.trace("L'adresse ayant été décrémentée on la réincrémente = "
					+ this.ip2.toString());				
		}
		Main.log.trace("l'adresse de fin a été atteinte");
		this.numbOfIp = this.ipInTheRange.size();
		Main.log.trace("Il y a " + this.numbOfIp + " ip dans la plage");
	}

	/**
	 * Cast the currentIp in an IP
	 */
	public String createIP(int[] currentIp1) {
		String ip = "" + currentIp1[0] + "." + currentIp1[1] + "."
				+ currentIp1[2] + "." + currentIp1[3];
		return ip;
	}

	public static void main(String[] args) {
		IP ip1 = new IP("192.168.0.250");
		IP ip2 = new IP("192.168.0.255");
		new IPRange(ip1, ip2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.indexCurrentIP < this.ipInTheRange.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	public IP next() {
		try {
			this.indexCurrentIP++;
			return this.ipInTheRange.get(indexCurrentIP);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		this.ipInTheRange.remove(indexCurrentIP);
	}

	/**
	 * Sets the current IP at the first one.
	 */
	public void first() {
		this.indexCurrentIP = 0;
	}

	public IP getCurrent() {
		return this.ipInTheRange.get(indexCurrentIP);
	}
}
