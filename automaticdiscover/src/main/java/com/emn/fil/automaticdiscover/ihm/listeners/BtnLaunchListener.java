package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.ihm.threads.TaskNmap;
import com.emn.fil.automaticdiscover.ihm.threads.TaskProgressBar;

public class BtnLaunchListener implements ActionListener {

	@Autowired
	public Frame frame;

	public BtnLaunchListener() {
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(frame == null);
		frame.getBtnLunch().setEnabled(false);
		frame.resetResults();
		frame.setMachineTable(new String[] { "IP", "HostName", "OS" }, new ArrayList<ArrayList<Object>>());

		try {
			// Initialisation
			TaskProgressBar taskProgressBar = new TaskProgressBar(frame.getProgressBar());
			taskProgressBar.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("progress".equals(evt.getPropertyName())) {
						frame.getProgressBar().setValue((Integer) evt.getNewValue());
					}
				}
			});
			TaskNmap taskNmap = new TaskNmap();

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
