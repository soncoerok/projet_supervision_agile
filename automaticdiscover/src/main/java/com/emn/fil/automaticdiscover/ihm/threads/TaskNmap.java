package com.emn.fil.automaticdiscover.ihm.threads;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.SwingWorker;

import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.nmap.Nmap;

public class TaskNmap extends SwingWorker<Void, Void> {

	/** Attribut qui represente l'objet nmap. */
	public Nmap nmap;

	/** La fenêtre. */
	public Frame frame;

	public TaskNmap(Frame frame, Nmap nmap) throws UnknownHostException {
		this.frame = frame;
		this.nmap = nmap;
	}

	@Override
	public Void doInBackground() throws IOException {
		this.nmap.lancer();
		return null;
	}

	@Override
	protected void done() {
		if (this.nmap.isScanTermine()) {
			this.frame.setScan(nmap.getScan());
			this.frame.setNbResult(String.valueOf(this.nmap.getScan().getListeMachine().size()));
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
			frame.getBtnLunch().setEnabled(true);
			frame.getProgressBar().update(frame.getProgressBar().getGraphics());
		}
	}
}
