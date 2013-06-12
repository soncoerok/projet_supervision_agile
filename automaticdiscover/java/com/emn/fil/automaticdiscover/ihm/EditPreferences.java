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
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[] {0, 200, 50};
		gblPanel.rowHeights = new int[] {0, 40, 40, 40, 0, 0};
		gblPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gblPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gblPanel);
		
		// Chemin Nmap
		Label labelNmap = new Label("Chemin d'accès nmap :");
		GridBagConstraints gbcLabelNmap = new GridBagConstraints();
		gbcLabelNmap.insets = new Insets(0, 0, 5, 5);
		gbcLabelNmap.fill = GridBagConstraints.BOTH;
		gbcLabelNmap.gridx = 0;
		gbcLabelNmap.gridy = 0;
		panel.add(labelNmap, gbcLabelNmap);
		
		textChemin = new JTextField();
		GridBagConstraints gbcTextField = new GridBagConstraints();
		gbcTextField.insets = new Insets(0, 0, 5, 5);
		gbcTextField.fill = GridBagConstraints.BOTH;
		gbcTextField.gridx = 1;
		gbcTextField.gridy = 0;
		panel.add(textChemin, gbcTextField);
		textChemin.setColumns(50);
		textChemin.setText(this.frame.getCheminNmap());
		textChemin.setEditable(false);
		
		BtnModifierChemin listenerChemin = new BtnModifierChemin(this, textChemin);
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(listenerChemin);

		GridBagConstraints gbcBtnModifier = new GridBagConstraints();
		gbcBtnModifier.insets = new Insets(0, 0, 5, 0);
		gbcBtnModifier.gridx = 2;
		gbcBtnModifier.gridy = 0;
		panel.add(btnModifier, gbcBtnModifier);
		
		// Vistesse du scan
		JLabel lblTypeScan = new JLabel("Caractéristique du scan");
		GridBagConstraints gbcLblTypeScan = new GridBagConstraints();
		gbcLblTypeScan.insets = new Insets(0, 0, 5, 5);
		gbcLblTypeScan.gridx = 0;
		gbcLblTypeScan.gridy = 1;
		panel.add(lblTypeScan, gbcLblTypeScan);
		
		JRadioButton rdbtnLent = new JRadioButton("Lent (Très efficace)");
		GridBagConstraints gbcRdbtnLent = new GridBagConstraints();
		gbcRdbtnLent.anchor = GridBagConstraints.WEST;
		gbcRdbtnLent.insets = new Insets(0, 0, 5, 5);
		gbcRdbtnLent.gridx = 1;
		gbcRdbtnLent.gridy = 1;
		panel.add(rdbtnLent, gbcRdbtnLent);
		BtnVitesseLent listenerLent = new BtnVitesseLent(this, rdbtnLent);
		rdbtnLent.addActionListener(listenerLent);
		
		JRadioButton rdbtnMoyen = new JRadioButton("Moyen");
		GridBagConstraints gbcRdbtnMoyen = new GridBagConstraints();
		gbcRdbtnMoyen.anchor = GridBagConstraints.WEST;
		gbcRdbtnMoyen.insets = new Insets(0, 0, 5, 5);
		gbcRdbtnMoyen.gridx = 1;
		gbcRdbtnMoyen.gridy = 2;
		panel.add(rdbtnMoyen, gbcRdbtnMoyen);
		BtnVitesseMoyen listenerMoyen = new BtnVitesseMoyen(this, rdbtnMoyen);
		rdbtnMoyen.addActionListener(listenerMoyen);
		
		JRadioButton rdbtnRapide = new JRadioButton("Rapide (Peu efficace)");
		GridBagConstraints gbcRdbtnRapide = new GridBagConstraints();
		gbcRdbtnRapide.anchor = GridBagConstraints.WEST;
		gbcRdbtnRapide.insets = new Insets(0, 0, 5, 5);
		gbcRdbtnRapide.gridx = 1;
		gbcRdbtnRapide.gridy = 3;
		panel.add(rdbtnRapide, gbcRdbtnRapide);
		BtnVitesseRapide listenerRapide = new BtnVitesseRapide(this, rdbtnRapide);
		rdbtnRapide.addActionListener(listenerRapide);
		
		ButtonGroup vitesseScan = new ButtonGroup();
		vitesseScan.add(rdbtnRapide);
		vitesseScan.add(rdbtnLent);
		vitesseScan.add(rdbtnMoyen);
		
		if (this.frame.getVistesseScan().equalsIgnoreCase("lent")) {
			rdbtnLent.setSelected(true);
		} else if (this.frame.getVistesseScan().equalsIgnoreCase("moyen")) {
			rdbtnMoyen.setSelected(true);
		} else if (this.frame.getVistesseScan().equalsIgnoreCase("rapide")) {
			rdbtnRapide.setSelected(true);
		}		
		
		// Bouton  Ok
		JButton btnOk = new JButton("Ok");
		GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
		gbcBtnNewButton.insets = new Insets(0, 0, 0, 5);
		gbcBtnNewButton.gridx = 1;
		gbcBtnNewButton.gridy = 5;
		panel.add(btnOk, gbcBtnNewButton);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		this.setVisible(true);
	}

	public Frame getFrame() {
		return this.frame;
	}
}

