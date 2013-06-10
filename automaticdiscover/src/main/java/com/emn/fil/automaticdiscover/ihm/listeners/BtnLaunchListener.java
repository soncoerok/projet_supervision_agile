package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.nmap.Nmap;

public class BtnLaunchListener implements ActionListener {

	private Frame frame;
	private Nmap nmap;

	public BtnLaunchListener(Frame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO est dépilé en dernier du coup n'est pas désactivé ! bizarre sachant qu'on a bien des threads a part pour les traitements
		frame.getBtnLunch().setEnabled(false);
		
		frame.resetResults();
		frame.setMachineTable(new String[] { "IP", "HostName", "OS" }, new ArrayList<ArrayList<Object>>());

		try {
			// Thread Nmap
			this.nmap = new Nmap(frame);
			final Thread threadNmap = new Thread(this.nmap);

			// Thread Progress bar
			Thread threadProgressBar = new Thread() {
				public void run(){
					int progress = 0;
					while (progress <= 100 && !nmap.isScanTermine()) {
						frame.getProgressBar().setValue(progress);
						frame.getProgressBar().update(frame.getProgressBar().getGraphics());
						progress++;
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					frame.getProgressBar().setValue(100);
					frame.getProgressBar().update(frame.getProgressBar().getGraphics());
				}
			};
			
			// Lancement des threads
			threadNmap.start();
			threadProgressBar.start();
			
			// Attente de la fin de nmap
			threadNmap.join(); 
			this.frame.getBtnLunch().setEnabled(true);
			
			// Apres traitement
			frame.setScan(nmap.getScan());
			frame.setNbResult(String.valueOf(this.nmap.getScan().getListeMachine().size()));
			for (Machine m : this.nmap.getScan().getListeMachine()) {
				frame.setMachineTable(m.toObject());
				switch (m.getOsType()) {
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
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, "Problème lors du lancement de l'analyse du réseau !\n" + e1.getMessage(), "Nmap erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
