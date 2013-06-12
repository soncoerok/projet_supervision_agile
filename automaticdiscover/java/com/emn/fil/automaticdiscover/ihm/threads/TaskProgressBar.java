package com.emn.fil.automaticdiscover.ihm.threads;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.emn.fil.automaticdiscover.nmap.Nmap;

public class TaskProgressBar extends SwingWorker<Void, Void> {

	/** La barre de chargement. */
	private JProgressBar progressBar;

	/** L'objet Nmap. */
	public Nmap nmap;

	public TaskProgressBar(JProgressBar progressBar, Nmap nmap) {
		this.progressBar = progressBar;
		this.nmap = nmap;
	}

	@Override
	public Void doInBackground() {
		int progress = 0;
		
		while (!nmap.isScanTermine()) {
			if(progress <= 95){
				setProgress(progress++);
			}
			try {
				Thread.sleep(600);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void done() {
		System.out.println("Nmap is Scan termine " + nmap.isScanTermine());
		progressBar.setValue(100);
		progressBar.update(progressBar.getGraphics());
	}
}
