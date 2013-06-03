package com.emn.fil.automaticdiscover.utils;

public class Commande {

	private String commande;

	public Commande(String commande) {
		this.commande = commande;
	}

	public void ajouterOption(String option) {
		this.commande += " " + option;
	}

	public void ajouterOptionAvecArguments(String option, String... args) {
		this.ajouterOption(option);
		for (int nbArg = 0; nbArg < args.length; nbArg++) {
			this.commande += " " + args[nbArg];
		}
	}

	public String getCommandeFinale() {
		return this.commande;
	}
}
