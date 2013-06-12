package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.stereotype.Component;

@Component
public class BtnQuitListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}
}