package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import com.emn.fil.automaticdiscover.ihm.EditPreferences;
import com.emn.fil.automaticdiscover.utils.GestionProperties;

public class BtnVitesseLent implements ActionListener{
	
	private EditPreferences preferences;
	private JRadioButton btnLent;
	
	public BtnVitesseLent(EditPreferences preferences, JRadioButton button){
		this.preferences = preferences;
		this.btnLent = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		btnLent.setSelected(true);
		preferences.getFrame().setVistesseScan("Lent");
		GestionProperties.enregistrerProperties("vitesse_scan", "Lent");
	}
}
