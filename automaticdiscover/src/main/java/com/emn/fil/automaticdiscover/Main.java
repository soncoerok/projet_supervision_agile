package com.emn.fil.automaticdiscover;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emn.fil.automaticdiscover.ihm.Frame;

public class Main {

	public static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		// Set System L&F
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Main.log.trace("ERROR : Impossible de modifier le theme");
		}

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-base.xml");

		/* Lancement de la fenetre. */
		Frame frame = (Frame) ctx.getBean("frame");
		frame.setVisible(true);

	}
}