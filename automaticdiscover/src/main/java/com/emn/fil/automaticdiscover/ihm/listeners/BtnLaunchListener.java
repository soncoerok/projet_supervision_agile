package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.ihm.threads.TaskNmap;
import com.emn.fil.automaticdiscover.ihm.threads.TaskProgressBar;
import com.emn.fil.automaticdiscover.nmap.Nmap;

public class BtnLaunchListener implements ActionListener {
	
	public Frame frame;
	public Nmap nmap;
	
	public BtnLaunchListener(Frame frame) {
		this.frame = frame;
		try {
			this.nmap = new Nmap(this.frame);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(frame, "La récupération de l'adresse a écouché !\n" + e.getMessage(), "Erreur réseau",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(!frame.isIpChecked() || (frame.isIpChecked() && IP.isValidIP(frame.getTextFieldIp()))) {
			frame.getProgressBar().setValue(0);
			frame.getProgressBar().update(frame.getProgressBar().getGraphics());
			frame.getBtnLunch().setEnabled(false);
			frame.resetResults();
			frame.setMachineTable(new String[] { "IP", "HostName", "OS" }, new ArrayList<ArrayList<Object>>());
	
			try {
				// Initialisation
				TaskProgressBar taskProgressBar = new TaskProgressBar(frame.getProgressBar(), nmap);
				taskProgressBar.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if ("progress".equals(evt.getPropertyName())) {
							frame.getProgressBar().setValue((Integer) evt.getNewValue());
						}
					}
				});
				TaskNmap taskNmap = new TaskNmap(frame, nmap);
	
				// Lancement tâches
				taskNmap.execute();
				taskProgressBar.execute();
	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(frame, "Problème lors du lancement de l'analyse du réseau !\n" + e1.getMessage(), "Nmap erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Adresse IP erronée : " + frame.getTextFieldIp() +", saisissez une IP valide."  , "IP erreur",
					JOptionPane.ERROR_MESSAGE);
			try {
				this.frame.setTextFieldIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

}
