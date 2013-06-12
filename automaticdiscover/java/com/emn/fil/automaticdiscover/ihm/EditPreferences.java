package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.emn.fil.automaticdiscover.ihm.listeners.BtnModifierChemin;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnVitesseLent;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnVitesseMoyen;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnVitesseRapide;

public class EditPreferences extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textChemin;
	private Frame frame;
	public EditPreferences(Frame frame) {
		
		this.frame = frame;
		this.setTitle("Préférences");
		this.setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 200, 50};
		gbl_panel.rowHeights = new int[] {0, 40, 40, 40, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		// Chemin Nmap
		Label labelNmap = new Label("Chemin d'accès nmap :");
		GridBagConstraints gbc_labelNmap = new GridBagConstraints();
		gbc_labelNmap.insets = new Insets(0, 0, 5, 5);
		gbc_labelNmap.fill = GridBagConstraints.BOTH;
		gbc_labelNmap.gridx = 0;
		gbc_labelNmap.gridy = 0;
		panel.add(labelNmap, gbc_labelNmap);
		
		textChemin = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textChemin, gbc_textField);
		textChemin.setColumns(50);
		textChemin.setText(this.frame.getCheminNmap());
		textChemin.setEditable(false);
		
		BtnModifierChemin listenerChemin = new BtnModifierChemin(this, textChemin);
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(listenerChemin);

		GridBagConstraints gbc_btnModifier = new GridBagConstraints();
		gbc_btnModifier.insets = new Insets(0, 0, 5, 0);
		gbc_btnModifier.gridx = 2;
		gbc_btnModifier.gridy = 0;
		panel.add(btnModifier, gbc_btnModifier);
		
		
		// Vistesse du scan
		JLabel lblTypeScan = new JLabel("Caractéristique du scan");
		GridBagConstraints gbc_lblTypeScan = new GridBagConstraints();
		gbc_lblTypeScan.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypeScan.gridx = 0;
		gbc_lblTypeScan.gridy = 1;
		panel.add(lblTypeScan, gbc_lblTypeScan);
		
		JRadioButton rdbtnLent = new JRadioButton("Lent (Très efficace)");
		GridBagConstraints gbc_rdbtnLent = new GridBagConstraints();
		gbc_rdbtnLent.anchor = GridBagConstraints.WEST;
		gbc_rdbtnLent.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnLent.gridx = 1;
		gbc_rdbtnLent.gridy = 1;
		panel.add(rdbtnLent, gbc_rdbtnLent);
		BtnVitesseLent listenerLent = new BtnVitesseLent(this, rdbtnLent);
		rdbtnLent.addActionListener(listenerLent);
		
		JRadioButton rdbtnMoyen = new JRadioButton("Moyen");
		GridBagConstraints gbc_rdbtnMoyen = new GridBagConstraints();
		gbc_rdbtnMoyen.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMoyen.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMoyen.gridx = 1;
		gbc_rdbtnMoyen.gridy = 2;
		panel.add(rdbtnMoyen, gbc_rdbtnMoyen);
		BtnVitesseMoyen listenerMoyen = new BtnVitesseMoyen(this, rdbtnMoyen);
		rdbtnMoyen.addActionListener(listenerMoyen);
		
		JRadioButton rdbtnRapide = new JRadioButton("Rapide (Peu efficace)");
		GridBagConstraints gbc_rdbtnRapide = new GridBagConstraints();
		gbc_rdbtnRapide.anchor = GridBagConstraints.WEST;
		gbc_rdbtnRapide.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRapide.gridx = 1;
		gbc_rdbtnRapide.gridy = 3;
		panel.add(rdbtnRapide, gbc_rdbtnRapide);
		BtnVitesseRapide listenerRapide = new BtnVitesseRapide(this, rdbtnRapide);
		rdbtnRapide.addActionListener(listenerRapide);
		
		ButtonGroup vitesseScan = new ButtonGroup();
		vitesseScan.add(rdbtnRapide);
		vitesseScan.add(rdbtnLent);
		vitesseScan.add(rdbtnMoyen);
		
		if (this.frame.getVistesseScan().equalsIgnoreCase("lent")) {
			rdbtnLent.setSelected(true);
		}			
		else if (this.frame.getVistesseScan().equalsIgnoreCase("moyen")){
			rdbtnMoyen.setSelected(true);
		}
		else if (this.frame.getVistesseScan().equalsIgnoreCase("rapide")){
			rdbtnRapide.setSelected(true);
		}		
		
		// Bouton  Ok
		JButton btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnOk, gbc_btnNewButton);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		this.setVisible(true);
	}

	public Frame getFrame(){
		return this.frame;
	}
}

