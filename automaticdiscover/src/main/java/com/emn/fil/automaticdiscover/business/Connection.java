package com.emn.fil.automaticdiscover.business;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPRange;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.dto.enums.OsType;

@Component("connection")
public class Connection {

	// Mac Ports
	@Value("${ports_mac.list}")
	private int[] portsMac;

	// Windows Ports
	@Value("${ports_windows.list}")
	private int[] portsWindows;

	// Unix Ports
	@Value("${ports_unix.list}")
	private int[] portsUnix;

	@Autowired
	private Reseau reseau;

	@Value("${time_out}")
	private int timeout;

	/**
	 * Test connection on a range of IP between range1 to range2
	 * 
	 * @param range1
	 * @param range2
	 */
	public void testConnection(IP range1, IP range2) {
		Main.log.trace("Test");
		IPRange range = new IPRange(range1, range2);
		OsType osCurrent = OsType.UNKNOWN;
		while (range.hasNext()) {
			osCurrent = getOSType(range.getCurrent());
			if (osCurrent != OsType.UNKNOWN) {
				reseau.ajoutMachine(new Machine(range.getCurrent(), osCurrent));
			}
			range.next();
		}
		Main.log.trace(reseau.getResume());
	}

	/**
	 * Test ports on IP and return the type of the machine
	 * 
	 * @param ip
	 * @return 0 MAC, 1 UNIX, 2 WINDOWS, -1 UNREACHABLE
	 */
	public OsType getOSType(IP ip) {
		Main.log.trace("Test ip : " + ip.toString());
		// ///////////////BEGIN TESTS/////////////////

		// Test windows ports
		if (testConnectionsOnPorts(ip, portsWindows)) {
			return OsType.WINDOWS;
		} else if (testConnectionsOnPorts(ip, portsMac)) { // If test windows ports not succeed, Test mac ports
			return OsType.OSX;
		} else if (testConnectionsOnPorts(ip, portsUnix)) { // If test windows ports and mac ports not succeed, Test unix ports
			return OsType.UNIX;
		}

		// ///////////////END TESTS/////////////////
		return OsType.UNKNOWN;
	}

	/**
	 * Test connection on ip with the array of ports
	 * 
	 * @param ip
	 * @param ports
	 * @return boolean state
	 * @throws Exception 
	 */
	private boolean testConnectionsOnPorts(IP ip, int[] ports) {
		InputStream input = null;
		int port;
		Socket socket = null;

		// BEGIN TEST OF CONNECTION
		for (int i = 0; i < ports.length; i++) {
			try {
				port = ports[i];
				SocketAddress sockaddr = new InetSocketAddress(ip.toString(), port);
				socket = new Socket();
				socket.connect(sockaddr, this.timeout);

				// Open stream
				input = socket.getInputStream();
				Main.log.trace("Connection ok ! : " + ports[i]);

				// Show the server response
				// String response = new BufferedReader(new InputStreamReader(input)).readLine();
				// System.out.println("Server message: " + response);

				input.close();
				socket.close();
				return true;
			} catch (Exception e) {} finally {
				try {
					input.close();
					socket.close();
				} catch (Exception e) {}
			}
		}
		return false;
	}

	public int[] getPortsMac() {
		return portsMac;
	}

	public int[] getPortsWindows() {
		return portsWindows;
	}

	public int[] getPortsUnix() {
		return portsUnix;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Reseau getReseau() {
		return reseau;
	}
}
