package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.emn.fil.automaticdiscover.ihm.EditPreferences;
import com.emn.fil.automaticdiscover.utils.GestionProperties;

public class BtnModifierChemin implements ActionListener {
	
	private EditPreferences preferences;
	private JTextField textChemin;

	public BtnModifierChemin(EditPreferences preferences, JTextField textfield){
		this.preferences = preferences;
		this.textChemin = textfield;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(preferences.getFrame().getCheminNmap());
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (chooser.getSelectedFile().getAbsolutePath().contains("nmap.exe")){
				this.preferences.getFrame().setCheminNmap(chooser.getSelectedFile().getAbsolutePath());
				this.textChemin.setText(chooser.getSelectedFile().getAbsolutePath());
				GestionProperties.enregistrerProperties("nmap", this.textChemin.getText());
				textChemin.setEditable(false);
			}
			else {
				JOptionPane.showMessageDialog(this.preferences, "Le chemin spécifié est incorrect", "Chemin erroné", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

