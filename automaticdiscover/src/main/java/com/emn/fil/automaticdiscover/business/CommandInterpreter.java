package com.emn.fil.automaticdiscover.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.dto.IP;

@Component
public class CommandInterpreter {

	@Autowired
	private Connection connection;

	public CommandInterpreter() {

	}

	public void execute(String commande) {

		String[] arrayCommande = commande.replace("\n", "").replace("\r", "")
				.split(" ");

		if (arrayCommande[0].contentEquals("analyze")
				&& arrayCommande.length == 3) {

			if (IP.isValidIP(arrayCommande[1])
					&& IP.isValidIP(arrayCommande[2])) {

				IP range1 = new IP(arrayCommande[1]);
				IP range2 = new IP(arrayCommande[2]);

				connection.testConnection(range1, range2);

			} else {
				Main.log.trace("IP invalides");
			}

		} else if (arrayCommande[0].contentEquals("help")) {
			Main.log.trace("* analyze fromIP toIP");
			Main.log.trace("\t Analyse a range of ip");

		} else {
			Main.log.trace("Commande non reconnue ou nombre d'argument incorrect : \""
					+ commande + "\"");
		}
	}
}
