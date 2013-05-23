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

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {
		// System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 10) {
			JTextArea textArea = (JTextArea) arg0.getSource();
			Main.log.trace("Command typed : " + textArea.getText());
			commandInterpreter.execute(textArea.getText());
			textArea.setText("");
		}

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}
