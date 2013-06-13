package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import com.emn.fil.automaticdiscover.ihm.EditPreferences;
import com.emn.fil.automaticdiscover.utils.GestionProperties;

public class BtnVitesseMoyen implements ActionListener {
	
	private EditPreferences preferences;
	private JRadioButton btnMoyen;
	
	public BtnVitesseMoyen(EditPreferences preferences, JRadioButton button){
		this.preferences = preferences;
		this.btnMoyen = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		btnMoyen.setSelected(true);
		preferences.getFrame().setVistesseScan("Moyen");
		GestionProperties.enregistrerProperties("vitesse_scan", "Moyen");
	}
}
