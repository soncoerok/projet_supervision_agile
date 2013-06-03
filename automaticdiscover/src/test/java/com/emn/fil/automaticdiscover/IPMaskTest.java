package com.emn.fil.automaticdiscover;

import org.junit.Test;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPMask;

import junit.framework.TestCase;

public class IPMaskTest extends TestCase {

	private static final String EXEMPLE_IP = "172.12.2.13";
	
	@Test
	public void testSansMasque(){
		IPMask ipMask = new IPMask(new IP(EXEMPLE_IP));
		assertEquals(ipMask.toString(),"172.12.2.13");
	}
	
	@Test
	public void testAvecMasque(){
		IPMask ipMask = new IPMask(new IP(EXEMPLE_IP),24);
		assertEquals(ipMask.toString(),"172.12.2.13/24");
	}
	
	

}
