package com.emn.fil.automaticdiscover.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.ihm.threads.AnalystThread;

@Component
public class BtnLaunchListener implements ActionListener {

	@Autowired
	private AnalystThread myAnalyst;

	public void actionPerformed(ActionEvent arg0) {
		new Thread(myAnalyst).start();
	}
}