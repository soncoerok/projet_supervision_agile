package com.emn.fil.automaticdiscover;

import javax.swing.UIManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emn.fil.automaticdiscover.ihm.Frame;

public class Main {
	// Test
	public static void main(String[] args) {
		try {
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			System.out.println("ERROR : Impossible de modifier le theme");
		}
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-base.xml");

	    // instantiate our spring dao object from the application context
		//Connection connection = (Connection)ctx.getBean("connection");
		
		Frame frame = (Frame)ctx.getBean("frame");    
		frame.initFrame();
	    
	    
	    //connection.testConnection(plage1,plage2);
		
	}
}
