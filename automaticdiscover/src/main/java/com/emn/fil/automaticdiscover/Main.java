package com.emn.fil.automaticdiscover;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.UIManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.log4j.Logger;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPMask;
import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.nmap.Nmap;

public class Main {

	public static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		// Set System L&F
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					Main.log.trace("ERROR : Impossible de modifier le theme");
				}

				ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-base.xml");

				// Frame frame = (Frame) ctx.getBean("frame");
				// frame.setVisible(true);
				
				String ipReseau;
				try {
					ipReseau = InetAddress.getLocalHost().getHostAddress();
					Nmap nmap;
					try {
						nmap = new Nmap(new IPMask(new IP(ipReseau),24));
						nmap.getScan();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
}