package com.emn.fil.automaticdiscover.business;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
		System.out.println("Test");
		IPRange range = new IPRange(range1, range2);
		OsType os_current = OsType.UNKNOWN;
		while (range.hasNext()) {
			os_current = getOSType(range.getCurrent());
			if (os_current != OsType.UNKNOWN) {
				reseau.ajoutMachine(new Machine(range.getCurrent(), os_current));
			}
			range.next();
		}

		System.out.println(reseau.getResume());
	}

	/**
	 * Test ports on IP and return the type of the machine
	 * 
	 * @param ip
	 * @return 0 MAC, 1 UNIX, 2 WINDOWS, -1 UNREACHABLE
	 */
	public OsType getOSType(IP ip) {
		OsType type = OsType.UNKNOWN;
		System.out.println("Test ip : " + ip.toString());
		// ///////////////BEGIN TESTS/////////////////

		// Test windows ports
		if (testConnectionsOnPorts(ip, portsWindows))
			type = OsType.WINDOWS;

		// If test windows ports not succeed
		if (type == OsType.UNKNOWN) {
			// Test mac ports
			if (testConnectionsOnPorts(ip, portsMac))
				type = OsType.OSX;
		}

		// If test windows ports and mac ports not succeed
		if (type == OsType.UNKNOWN) {
			// Test unix ports
			if (testConnectionsOnPorts(ip, portsUnix))
				type = OsType.UNIX;
		}

		// ///////////////END TESTS/////////////////
		return type;
	}

	/**
	 * Test connection on ip with the array of ports
	 * 
	 * @param ip
	 * @param ports
	 * @return boolean state
	 */
	@SuppressWarnings("null")
	private boolean testConnectionsOnPorts(IP ip, int[] ports) {
		InputStream input = null;
		int port;
		Socket socket = null;

		// BEGIN TEST OF CONNECTION
		for (int i = 0; i < ports.length; i++) {
			// System.out.println("Test port : "+ports[i]);
			try {
				port = ports[i];
				SocketAddress sockaddr = new InetSocketAddress(ip.toString(),
						port);
				socket = new Socket();
				socket.connect(sockaddr, this.timeout);

				// Open stream
				input = socket.getInputStream();
				System.out.println("Connection ok ! : " + ports[i]);

				// Show the server response
				// String response = new BufferedReader(new
				// InputStreamReader(input)).readLine();
				// System.out.println("Server message: " + response);

				try {
					input.close();
					socket.close();
				} catch (Exception e) {
					// System.err.println("[ERROR] close socket : "+e);
				}

				return true;
			} catch (UnknownHostException e) {
				// System.out.println("Connection fail !");
				// System.err.println("UnknownHostException : "+e);
			} catch (IOException e) {
				// System.out.println("Connection fail !");
				// System.err.println("IO : "+e);
			} finally {
				try {
					input.close();
					socket.close();
				} catch (Exception e) {
					// System.out.println("Connection fail !");
					// System.err.println("Finally : "+e);
				}
			}
		}
		return false;
	}

	public int[] getPorts_mac() {
		return portsMac;
	}

	public int[] getPorts_windows() {
		return portsWindows;
	}

	public int[] getPorts_unix() {
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
