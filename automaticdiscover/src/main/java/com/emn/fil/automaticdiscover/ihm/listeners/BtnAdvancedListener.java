package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.ihm.Frame;

@Component
public class BtnAdvancedListener implements ActionListener {

	@Autowired
	private Frame myFrame;

	public void actionPerformed(ActionEvent arg0) {
		myFrame.showDialAdvCFG();
	}
}