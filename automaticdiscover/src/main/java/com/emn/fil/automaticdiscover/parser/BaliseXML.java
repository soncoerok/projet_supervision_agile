package com.emn.fil.automaticdiscover.parser;

import java.util.List;

public class BaliseXML {
 
	private String nomBalise;
	private List<String> nomAttribut;
	
	public BaliseXML(String nomBalise, List<String> nomAttribut) {
		super();
		this.nomBalise = nomBalise;
		this.nomAttribut = nomAttribut;
	}

	public String getNomBalise() {
		return nomBalise;
	}

	public List<String> getNomAttribut() {
		return nomAttribut;
	}

	public void setNomBalise(String nomBalise) {
		this.nomBalise = nomBalise;
	}

	public void setNomAttribut(List<String> nomAttribut) {
		this.nomAttribut = nomAttribut;
	}
}
