import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.emn.fil.automaticdiscover.dto.IP;
import com.emn.fil.automaticdiscover.dto.IPRange;


public class IPRangeTest {

	
	@Test
	public void rangeOkTestSimple() {
		IP ip1 = new IP("192.168.1.1");
		IP ip2 = new IP("192.168.1.3");
		IPRange ipRange = new IPRange(ip1, ip2);
		List<IP> listIp = ipRange.getTheIPRange();
		
		Assert.assertEquals(listIp.size(), 3);
	}
	
	@Test
	public void rangeOkTestSimpleDeuxRang() {
		IP ip1 = new IP("192.168.1.1");
		IP ip2 = new IP("192.168.2.2");
		IPRange ipRange = new IPRange(ip1, ip2);
		List<IP> listIp = ipRange.getTheIPRange();
		
		Assert.assertEquals(listIp.size(), 257);
	}
	
	@Test
	public void rangeKoTestSimpleInverse() {
		IP ip1 = new IP("192.168.2.1");
		IP ip2 = new IP("192.168.1.1");
		//IPRange ipRange = new IPRange(ip1, ip2);
		//List<IP> listIp = ipRange.getTheIPRange();
		
		//Assert.assertEquals(listIp.size(), 257);
	}
	
/*	@Test(expected=com.emn.fil.automaticdiscover.exception.IPFormatException.class)
	public void rangeKoTestIpError() {
		IP ip1 = new IP("192.168.1.1");
		//IP ip2 = new IP("192.168.3.256");
		//IPRange ipRange = new IPRange(ip1, ip2);
		//List<IP> listIp = ipRange.getTheIPRange();
		
	}*/
	
}
