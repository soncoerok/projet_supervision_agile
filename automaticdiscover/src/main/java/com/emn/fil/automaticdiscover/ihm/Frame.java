package com.emn.fil.automaticdiscover.ihm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.business.Connection;
import com.emn.fil.automaticdiscover.ihm.dialogs.AdvancedCFGDialog;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnAdvancedListener;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnLaunchListener;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnQuitListener;

@Component
public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_title = new JLabel("Automatic Discover via IPs/Ports");

	private JSeparator separator_up = new JSeparator();
	private JSeparator separator_middle = new JSeparator();

	//Partie config
	private JLabel lblConfigureRangeTo = new JLabel("Configure range to analyze :");
	private JLabel lbl_from = new JLabel("From :");
	private JLabel lbl_to = new JLabel("To:");
	JButton btnAdvanced = new JButton("Advanced ...");

	//Partie console
	private JLabel lblConsole = new JLabel("Console :");
	public JTextArea ta_console = new JTextArea();
	public JButton btn_Launch = new JButton("Launch");
	private JButton btn_Quit = new JButton("Quit");
	public JProgressBar progressBar = new JProgressBar();
	public JLabel lbl_percent = new JLabel("0%");
	public JTextField txtbox_from;
	public JTextField txtbox_to;

	//Partie Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnFile = new JMenu("File");
	private JMenuItem mntmQuit = new JMenuItem("Quit");
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmAbout = new JMenuItem("About");

	//LISTENERS

	@Autowired
	private BtnQuitListener btnQuitListener;
	@Autowired
	private BtnLaunchListener btnLaunchListener;
	@Autowired
	private BtnAdvancedListener btnAdvancedListener;
	
	//Dialogs
	AdvancedCFGDialog dial_advanced_config;
	
	//Connection
	@Autowired
	public Connection connection;

	public Frame() {

	}

	private void addActions() {
		this.btn_Quit.addActionListener(btnQuitListener);
		this.btn_Launch.addActionListener(btnLaunchListener);
		this.btnAdvanced.addActionListener(btnAdvancedListener);

	}

	private void configureComponent() {
		//this.btn_Launch.setEnabled(false);

	}

	private void buildMenu(){

		menuBar.setBounds(0, 0, 674, 21);
		contentPane.add(menuBar);

		menuBar.add(mnFile);

		mnFile.add(mntmQuit);

		menuBar.add(mnHelp);

		mnHelp.add(mntmAbout);

	}

	private void buildConfiguration(){

		lbl_from.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_from.setForeground(SystemColor.textInactiveText);
		lbl_from.setBounds(20, 105, 46, 14);
		contentPane.add(lbl_from);

		lbl_to.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_to.setForeground(SystemColor.textInactiveText);
		lbl_to.setBounds(20, 130, 46, 14);
		contentPane.add(lbl_to);

		txtbox_from = new JTextField();
		txtbox_from.setBounds(68, 103, 170, 20);
		contentPane.add(txtbox_from);
		txtbox_from.setColumns(10);

		txtbox_to = new JTextField();
		txtbox_to.setBounds(68, 127, 170, 20);
		contentPane.add(txtbox_to);
		txtbox_to.setColumns(10);

		lblConfigureRangeTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfigureRangeTo.setForeground(SystemColor.inactiveCaptionText);
		lblConfigureRangeTo.setBounds(10, 79, 158, 14);
		contentPane.add(lblConfigureRangeTo);
		

		
		btnAdvanced.setBounds(561, 126, 103, 23);
		contentPane.add(btnAdvanced);
	}

	private void buildPanelConsole(){

		ta_console.setBounds(10, 183, 654, 264);
		JScrollPane ta_jbar = new JScrollPane(ta_console);
		ta_jbar.setBounds(10, 183, 654, 264);
		//
		ta_jbar.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent e) {  
			e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
			}});  
		//
		contentPane.add(ta_jbar);

		btn_Quit.setBounds(575, 458, 89, 23);
		contentPane.add(btn_Quit);

		btn_Launch.setBounds(476, 458, 89, 23);
		contentPane.add(btn_Launch);

		progressBar.setBounds(10, 458, 411, 23);
		contentPane.add(progressBar);

		lbl_percent.setBounds(431, 462, 35, 14);
		contentPane.add(lbl_percent);

		lblConsole.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsole.setForeground(SystemColor.inactiveCaptionText);
		lblConsole.setBounds(312, 162, 60, 14);
		contentPane.add(lblConsole);
	}

	public void initFrame() {
		setTitle("Automatic Discover Beta");
		contentPane = new JPanel();
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(690, 530);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (this.getWidth() / 2), 
		                              middle.y - (this.getHeight() / 2));
		
		setLocation(newLocation);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_title.setForeground(SystemColor.windowText);
		lbl_title.setBounds(243, 41, 188, 14);
		contentPane.add(lbl_title);

		separator_up.setBounds(10, 66, 654, 2);
		contentPane.add(separator_up);

		separator_middle.setBounds(10, 156, 654, 2);
		contentPane.add(separator_middle);

		
		this.buildMenu();
		this.buildConfiguration();
		this.buildPanelConsole();
		this.configureComponent();
		this.addActions();
		this.dial_advanced_config = new AdvancedCFGDialog(this, "Advanced Configuration", true);
		this.setVisible(true);
	}
	
	public void showDialAdvCFG(){
		this.dial_advanced_config.setVisible(true);
	}


}
