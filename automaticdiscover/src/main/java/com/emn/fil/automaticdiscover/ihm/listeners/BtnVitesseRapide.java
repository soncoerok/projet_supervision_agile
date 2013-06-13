package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import com.emn.fil.automaticdiscover.ihm.EditPreferences;
import com.emn.fil.automaticdiscover.utils.GestionProperties;

public class BtnVitesseRapide implements ActionListener {
	
	private EditPreferences preferences;
	private JRadioButton btnRapide;
	
	public BtnVitesseRapide(EditPreferences preferences, JRadioButton button){
		this.preferences = preferences;
		this.btnRapide = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		btnRapide.setSelected(true);
		preferences.getFrame().setVistesseScan("Rapide");
		GestionProperties.enregistrerProperties("vitesse_scan", "Rapide");
	}
}
