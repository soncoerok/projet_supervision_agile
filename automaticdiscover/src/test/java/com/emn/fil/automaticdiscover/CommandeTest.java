package com.emn.fil.automaticdiscover;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.emn.fil.automaticdiscover.utils.Commande;

public class CommandeTest extends TestCase {

	private Commande commande;
	private final static String EXEMPLE_COMMANDE = "commande";
	private final static String EXEMPLE_OPTION_1 = "-option1";
	private final static String EXEMPLE_OPTION_2 = "--option2";
	private final static String EXEMPLE_ARG_1 = "arg1";
	private final static String EXEMPLE_ARG_2 = "arg2";
	
	@Before
    public void setUp() throws Exception {
		 this.commande = new Commande(EXEMPLE_COMMANDE); 
    }
	
	@Test
	public void testSansArguments(){
		assertEquals(commande.getCommandeFinale(),EXEMPLE_COMMANDE);
	}
	
	@Test
	public void testUneOptionUnTiretSansArguments(){
		this.commande.ajouterOption(EXEMPLE_OPTION_1);
		assertEquals(commande.getCommandeFinale(),EXEMPLE_COMMANDE + " " + EXEMPLE_OPTION_1);
	}
	
	@Test
	public void testUneOptionDeuxTiretsSansArguments(){
		this.commande.ajouterOption(EXEMPLE_OPTION_2);
		assertEquals(commande.getCommandeFinale(),EXEMPLE_COMMANDE + " " + EXEMPLE_OPTION_2);
	}
	
	@Test
	public void testDeuxOptionsSansArguments(){
		this.commande.ajouterOption(EXEMPLE_OPTION_1);
		this.commande.ajouterOption(EXEMPLE_OPTION_2);
		assertEquals(commande.getCommandeFinale(),EXEMPLE_COMMANDE + " " + EXEMPLE_OPTION_1 + " " + EXEMPLE_OPTION_2);
	}
	
	@Test
	public void testUneOptionAvecArguments(){
		this.commande.ajouterOptionAvecArguments(EXEMPLE_OPTION_1,EXEMPLE_ARG_1,EXEMPLE_ARG_2);
		assertEquals(commande.getCommandeFinale(),EXEMPLE_COMMANDE + " " + EXEMPLE_OPTION_1 + " " + EXEMPLE_ARG_1 + " " +EXEMPLE_ARG_2);
	}
}
