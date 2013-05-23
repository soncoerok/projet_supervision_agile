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
	private JLabel lblTitle = new JLabel("Automatic Discover via IPs/Ports");

	private JSeparator separatorUp = new JSeparator();
	private JSeparator separatorMiddle = new JSeparator();

	//Partie config
	private JLabel lblConfigureRangeTo = new JLabel("Configure range to analyze :");
	private JLabel lblFrom = new JLabel("From :");
	private JLabel lblTo = new JLabel("To:");
	private JButton btnAdvanced = new JButton("Advanced ...");

	//Partie console
	private JLabel lblConsole = new JLabel("Console :");
	private JTextArea taConsole = new JTextArea();
	private JButton btnLaunch = new JButton("Launch");
	private JButton btnQuit = new JButton("Quit");
	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblPercent = new JLabel("0%");
	private JTextField txtboxFrom;
	private JTextField txtboxTo;

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
	AdvancedCFGDialog dialAdvancedConfig;
	
	//Connection
	@Autowired
	public Connection connection;

	public Frame() {

	}

	private void addActions() {
		this.btnQuit.addActionListener(btnQuitListener);
		this.getBtnLaunch().addActionListener(btnLaunchListener);
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

		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFrom.setForeground(SystemColor.textInactiveText);
		lblFrom.setBounds(20, 105, 46, 14);
		contentPane.add(lblFrom);

		lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTo.setForeground(SystemColor.textInactiveText);
		lblTo.setBounds(20, 130, 46, 14);
		contentPane.add(lblTo);

		setTxtboxFrom(new JTextField());
		getTxtboxFrom().setBounds(68, 103, 170, 20);
		contentPane.add(getTxtboxFrom());
		getTxtboxFrom().setColumns(10);

		setTxtboxTo(new JTextField());
		getTxtboxTo().setBounds(68, 127, 170, 20);
		contentPane.add(getTxtboxTo());
		getTxtboxTo().setColumns(10);

		lblConfigureRangeTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfigureRangeTo.setForeground(SystemColor.inactiveCaptionText);
		lblConfigureRangeTo.setBounds(10, 79, 158, 14);
		contentPane.add(lblConfigureRangeTo);
		

		
		btnAdvanced.setBounds(561, 126, 103, 23);
		contentPane.add(btnAdvanced);
	}

	private void buildPanelConsole(){

		getTaConsole().setBounds(10, 183, 654, 264);
		JScrollPane ta_jbar = new JScrollPane(getTaConsole());
		ta_jbar.setBounds(10, 183, 654, 264);
		//
		ta_jbar.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent e) {  
			e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
			}});  
		//
		contentPane.add(ta_jbar);

		btnQuit.setBounds(575, 458, 89, 23);
		contentPane.add(btnQuit);

		getBtnLaunch().setBounds(476, 458, 89, 23);
		contentPane.add(getBtnLaunch());

		getProgressBar().setBounds(10, 458, 411, 23);
		contentPane.add(getProgressBar());

		getLblPercent().setBounds(431, 462, 35, 14);
		contentPane.add(getLblPercent());

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
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setForeground(SystemColor.windowText);
		lblTitle.setBounds(243, 41, 188, 14);
		contentPane.add(lblTitle);

		separatorUp.setBounds(10, 66, 654, 2);
		contentPane.add(separatorUp);

		separatorMiddle.setBounds(10, 156, 654, 2);
		contentPane.add(separatorMiddle);

		
		this.buildMenu();
		this.buildConfiguration();
		this.buildPanelConsole();
		this.configureComponent();
		this.addActions();
		this.dialAdvancedConfig = new AdvancedCFGDialog(this, "Advanced Configuration", true);
		this.setVisible(true);
	}
	
	public void showDialAdvCFG(){
		this.dialAdvancedConfig.setVisible(true);
	}

	/*
	 * GETTER AND SETTER
	 */
	public JButton getBtnLaunch() {
		return btnLaunch;
	}

	public void setBtnLaunch(JButton btnLaunch) {
		this.btnLaunch = btnLaunch;
	}

	public JTextArea getTaConsole() {
		return taConsole;
	}

	public void setTaConsole(JTextArea taConsole) {
		this.taConsole = taConsole;
	}

	public JTextField getTxtboxFrom() {
		return txtboxFrom;
	}

	public void setTxtboxFrom(JTextField txtboxFrom) {
		this.txtboxFrom = txtboxFrom;
	}

	public JTextField getTxtboxTo() {
		return txtboxTo;
	}

	public void setTxtboxTo(JTextField txtboxTo) {
		this.txtboxTo = txtboxTo;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JLabel getLblPercent() {
		return lblPercent;
	}

	public void setLblPercent(JLabel lblPercent) {
		this.lblPercent = lblPercent;
	}


}
