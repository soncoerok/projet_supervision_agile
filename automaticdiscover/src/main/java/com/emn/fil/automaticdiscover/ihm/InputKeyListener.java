package com.emn.fil.automaticdiscover.ihm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.business.CommandInterpreter;

@Component
public class InputKeyListener implements KeyListener {

	@Autowired
	private CommandInterpreter commandInterpreter;

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == 10) {
			JTextArea textArea = (JTextArea) arg0.getSource();
			Main.log.trace("Command typed : " + textArea.getText());
			try {
				commandInterpreter.execute(textArea.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			textArea.setText("");
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {}
}
