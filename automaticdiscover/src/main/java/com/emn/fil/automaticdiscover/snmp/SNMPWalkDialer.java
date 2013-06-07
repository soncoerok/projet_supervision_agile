package com.emn.fil.automaticdiscover.snmp;

/*Copyright (c) 2009-2013 Ecole des Mines de Nantes.
 *
 *      This file is part of BtrCloud.
 *
 *      btrAction is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU Lesser General Public License
 *      as published by the Free Software Foundation, either version 3
 *      of the License, or (at your option) any later version.
 *
 *      BtrAction is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU Lesser General Public License for more details.
 *
 *      You should have received a copy of the GNU Lesser
 *      General Public License along with BtrAction.
 *      If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.EnumMap;

/**
 * Works only on Linux distributions (perhaps Unices too) with snmpwalk installed:
 *  Debian-based: sudo apt-get install snmp
 *  Archlinux: sudo pacman -S net-snmp
 *  other: search by yourself or use a decent distribution (hint: above ones are good choices).
 *
 * This class offers Windows (> XP) monitoring via SNMP service, with some configuration of the service:
 *  - a public community must be at least read-only accessible under the Security tab;
 *  - the IP from where the code will be launched must be white-listed
 *    (or you can unset all access restriction) under the Security tab;
 *  - the â€œPhysicalâ€ service must be checked under the Agent tab.
 * 
 * @author Alexandre Garnier <alexandre.garnier at easyvirt.com>
 */
public class SNMPWalkDialer {

	private final static String chemin = "C:\\usr\\bin\\";
	private final static String cpuLoadOid = "1.3.6.1.2.1.25.3.3.1.2";
	private final static String ramCapOid = "1.3.6.1.2.1.25.2.2.0";
	private final static String ramOid = "1.3.6.1.2.1.25.2.3.1.3";
	private final static String ramOidHeader = "1.3.6.1.2.1.25.2.3.1.6";
	private final String header;
	private int ramOffset = -1;
	private int cpuCor = -1;
	private int ramCap = -1;

	/**
	 * Dialer constructor: ensure given IP accessibility by retrieving physical memory offset.
	 *
	 * @param ip machine IP
	 * @param community public read-only community to use
	 * @param version SNMP version to use (in doubt set â€œ2câ€)
	 * @param port SNMP port (in doubt set 161)
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public SNMPWalkDialer(String ip, String community, String version, int port) throws IOException, InterruptedException {
		// build snmpwalk calls header
		this.header = new StringBuilder().append("snmpwalk.exe -c ").append(community).append(" -v ").append(version).
				append(port == 161 ? " " : "-p" + port + " ").append(ip).toString();
		try {
			// call snmpwalk to get RAM offset (machine dependent)
			Process process = new ProcessBuilder("cmd.exe", "/C", chemin + header + " " + ramOid).redirectErrorStream(true).start();
			// wait for correct snmpwalk ending
			if (process.waitFor() == 0) {
				// get snmpwalk output
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				// parse each line to find â€œPhysical Memoryâ€ offset
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if (line.endsWith("Physical Memory") || line.endsWith("Physical Memory\"")) {

						String[] split = line.split(String.valueOf(' '))[0].split("\\.");
						ramOffset = Integer.parseInt(split[split.length - 1]);
						// return to avoid exception throw when offset is not found
						return;
					} // if
				}// while
			} 
		}catch(Exception e) {
			e.printStackTrace();
			throw new IOException("Could not get physical memory offset");
		}
	}

	/**
	 * Collect SNMP data
	 *
	 * @return CPU and RAM specifications and consumptions
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public EnumMap<Keys, Integer> collect() throws IOException, InterruptedException {
		EnumMap<Keys, Integer> enumMap = new EnumMap<Keys, Integer>(Keys.class);
		Data data = getData();
		// only reset #CPUs if it has changed
		if (data.cpuCor != cpuCor) {
			cpuCor = data.cpuCor;
			enumMap.put(Keys.cpuCor, cpuCor);
		} // if
		// only reset RAM capacity if it has changed
		if (data.ramCap != ramCap) {
			ramCap = data.ramCap;
			enumMap.put(Keys.ramCap, ramCap);
		} // if
		// do not ever bother me with ungettable CPU consumption
		if (data.cpu != -1) {
			enumMap.put(Keys.cpu, data.cpu);
		} else {
			throw new IOException("Could not get CPU value");
		} // if
		// Sometimes SNMP just does not provide RAM consumption, exception should be thrown, just like Microsoft should do its job.
		// Therefore the throw in else scope is commentedâ€¦
		if (data.ram != -1) {
			enumMap.put(Keys.ram, data.ram);
		} else {
			// throw new IOException("Could not get RAM value");
		} // if
		return enumMap;
	}

	private Data getData() throws IOException, InterruptedException {
		// set default values
		int newCpuCor = -1, cpu = -1, newRamCap = -1, ram = -1;

		// get #CPUs and CPU consumption
		Process cpuProcess = new ProcessBuilder("cmd.exe", "/C", chemin + header + ' ' + cpuLoadOid).start();
		if (cpuProcess.waitFor() == 0) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cpuProcess.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (newCpuCor == -1 && cpu == -1) {
					newCpuCor = 0;
					cpu = 0;
				} // if
				cpu += Integer.parseInt(line.split(String.valueOf(' '))[3].trim());
				++newCpuCor;
			} // while
			if (newCpuCor != -1 && cpu != -1) {
				cpu = cpu  / newCpuCor;
			} // if
		} // if

		// get RAM capacity
		Process ramCapProcess = new ProcessBuilder("cmd.exe", "/C", chemin + header + ' ' + ramCapOid).start();
		if (ramCapProcess.waitFor() == 0) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ramCapProcess.getInputStream()));
			String line = bufferedReader.readLine();
			if (line != null) {
				newRamCap = Integer.parseInt(line.split(String.valueOf(' '))[3]) / 1024; // Ko
				// to
				// Mo
			} // if
		} // if

		// get RAM consumption
		Process ramProcess = new ProcessBuilder("cmd.exe", "/C", chemin + header + " " + ramOidHeader + '.' + ramOffset).start();
		if (ramProcess.waitFor() == 0) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ramProcess.getInputStream()));
			String line = bufferedReader.readLine();
			if (line != null) {
				if (newRamCap != 0) {
					ram = (int) (Long.parseLong(line.split(String.valueOf(' '))[3]) * 100D * 64D / 1024D / (long) (newRamCap == -1 ? ramCap
							: newRamCap));
				} else {
					ram = -1;
				}
			} // if
		} // if

		return new Data(newCpuCor, cpu, newRamCap, ram);
	}

	private static final class Data {

		final int cpuCor;
		final int cpu;
		final int ramCap;
		final int ram;

		Data(int cpuCor, int cpu, int ramCap, int ram) {
			this.cpuCor = cpuCor;
			this.cpu = cpu;
			this.ramCap = ramCap;
			this.ram = ram;
		}
	}

	public static enum Keys {
		ramCap, cpuCor, cpu, ram,
	}
}