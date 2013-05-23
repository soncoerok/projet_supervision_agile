package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.emn.fil.automaticdiscover.ihm.Frame;

public class BtnOkDialAdvCfg implements ActionListener {

	private Frame myFrame;

	public BtnOkDialAdvCfg(Frame fr) {
		this.setMyFrame(fr);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public Frame getMyFrame() {
		return myFrame;
	}

	public void setMyFrame(Frame myFrame) {
		this.myFrame = myFrame;
	}
}