package com.emn.fil.automaticdiscover.ihm.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.business.Connection;
import com.emn.fil.automaticdiscover.business.Reseau;
import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPRange;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.dto.enums.OsType;
import com.emn.fil.automaticdiscover.ihm.Frame;

@Component
public class AnalystThread implements Runnable {

	public void run() {}
/*
	@Autowired
	private Frame myFrame;

	@Autowired
	private Reseau reseau;

	@Autowired
	private Connection connection;

	public void run() {

		if (IP.isValidIP(myFrame.getTxtboxFrom().getText())
				&& IP.isValidIP(myFrame.getTxtboxTo().getText())) {
			// ON desactive le btn pendant le traitement
			myFrame.getBtnLaunch().setEnabled(false);

			IP range1 = new IP(myFrame.getTxtboxFrom().getText());
			IP range2 = new IP(myFrame.getTxtboxTo().getText());

			myFrame.getTaConsole().append("ANALYSE LAUNCH : \r\n");
			myFrame.getTaConsole().append("\tFROM :" + myFrame.getTxtboxFrom().getText() + " \r\n");
			myFrame.getTaConsole().append("\tTO :" + myFrame.getTxtboxTo().getText() + " \r\n\r\n");

			IPRange range = new IPRange(range1, range2);
			OsType osCurrent = OsType.UNKNOWN;
			int nbIps = range.getTheIPRange().size();
			Main.log.trace("NB IPS " + nbIps);
			Main.log.trace(range.getTheIPRange());
			myFrame.getProgressBar().setMaximum(nbIps);
			int nbCurrent = 1;
			while (range.hasNext()) {
				osCurrent = connection.getOSType(range.getCurrent());
				myFrame.getTaConsole().append("IP TESTE : " + range.getCurrent().getIp());
				if (osCurrent != OsType.UNKNOWN) {
					reseau.ajoutMachine(new Machine(range.getCurrent(), osCurrent));
					myFrame.getTaConsole().append(" => AJOUTÉ\r\n");
				} else{
					myFrame.getTaConsole().append(" => KO\r\n");
				}
				myFrame.getProgressBar().setValue(nbCurrent);
				myFrame.getLblPercent().setText((nbCurrent * 100) / nbIps + "%");
				nbCurrent++;
				range.next();
			}

			myFrame.getTaConsole().append("#############\r\n" + reseau.getResume() + "\r\n");
			// ON reactive le btn pendant le traitement
			myFrame.getBtnLaunch().setEnabled(true);
			myFrame.getLblPercent().setText("0%");
			myFrame.getProgressBar().setValue(0);
		} else {
			myFrame.getTaConsole().append("ERREUR : IPs INCORRECTES\r\n");
			myFrame.getTaConsole().append(myFrame.getTxtboxFrom().getText() + "\r\n");
			myFrame.getTaConsole().append(myFrame.getTxtboxTo().getText() + "\r\n");
		}
	}
	*/
}
