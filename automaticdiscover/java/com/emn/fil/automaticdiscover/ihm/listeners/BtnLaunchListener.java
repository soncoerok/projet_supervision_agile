package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			this.nmap = new Nmap(frame);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(frame, "La récupération de l'adresse a écouché !\n" + e.getMessage(), "Erreur réseau",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
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
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

}
