package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPMask;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;
import com.emn.fil.automaticdiscover.nmap.Nmap;

public class BtnLaunchListener implements ActionListener {
	
	private Frame frame;
	
	public BtnLaunchListener(Frame frame) {
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		String ipReseau = "172.17.2.32";/*
		try {
			ipReseau = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknown) {
			ShowDialog dialog = 
					new ShowDialog("Problème rencontré lors de la récupération de l'adresse !\n" + unknown);
			dialog.setVisible(true);
		}
		*/
		IPMask ipMask = new IPMask(new IP(ipReseau), 32);
		try {
			Nmap nmap = new Nmap(ipMask);
			frame.setScan(nmap.getScan());
			frame.setNbResult(String.valueOf(nmap.getScan().getListeMachine().size()));
			for(Machine m : nmap.getScan().getListeMachine()) {
				frame.setMachineTable(m.toObject());
				switch(m.getOsType()) {
					case WINDOWS:
						frame.setNbWindows(String.valueOf(frame.getNbWindows() + 1));
						break;
					
					case UNIX:
						frame.setNbUnix(String.valueOf(frame.getNbUnix() + 1));
						break;
					
					case OSX:
						frame.setNbMac(String.valueOf(frame.getNbMac() + 1));
						break;
					default:
						break;
				}
			}
			frame.getPanelResult().setVisible(true);
		} catch (IOException ioE) {
			ShowDialog dialog = 
				new ShowDialog("Problème lors du lancement de l'analyse du réseau !\n" + ioE);
			dialog.setVisible(true);
		}
	}
}