package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.emn.fil.automaticdiscover.ihm.EditPreferences;
import com.emn.fil.automaticdiscover.ihm.Frame;

public class BtnPreferences implements ActionListener{

	private Frame frame;
	
	public BtnPreferences (Frame frame){
		this.frame = frame;		
	}
	
	public void actionPerformed(ActionEvent e) {
		new EditPreferences(frame);		
	}
}

