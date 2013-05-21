package com.emn.fil.automaticdiscover.ihm.dialogs;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.emn.fil.automaticdiscover.ihm.Frame;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnAddsDialAdvCfg;


public class AdvancedCFGDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	//Dialog
	private JLabel lblAdvancedConfiguration = new JLabel("Advanced Configuration :");
	private JSeparator separator_up = new JSeparator();
	private JSeparator separator_middle = new JSeparator();
	private JSeparator separator_down = new JSeparator();
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnOk = new JButton("OK");
	
	//Partie configuration timeout
	private JLabel lblSetTimeout = new JLabel("Set Time-Out (ms) :");
	private JTextField txtbox_timeout;
	
	//Partie Configuration ports
	private JLabel lblConfigurePorts = new JLabel("Configure ports :");
	private JLabel lblWindows = new JLabel("Windows :");
	private JLabel lblMacOsx = new JLabel("Mac OSX :");
	private JLabel lblOtherunix = new JLabel("Other (Unix ...)");
	
	private DefaultListModel dlm_ports_win = new DefaultListModel();
	private JList list_ports_win = new JList(dlm_ports_win);
	
	private DefaultListModel dlm_ports_mac = new DefaultListModel();
	private JList list_ports_mac = new JList(dlm_ports_mac);
	
	private DefaultListModel dlm_ports_unix = new DefaultListModel();
	private JList list_ports_unix = new JList(dlm_ports_unix);
	
	private JButton btnAddWin = new JButton("add");
	private JButton btnAddOSX = new JButton("add");
	private JButton btnAddUnix = new JButton("add");
	private JTextField txtbox_win;
	private JTextField txtbox_mac;
	private JTextField txtbox_unix;
	
	public AdvancedCFGDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		setSize(657, 381);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);
		
		this.buildMain();
		this.buildConfigTO();
		this.buildConfigPorts();
		this.addActions();
		this.getInfos(parent);
		
		//TMP
		this.btnAddOSX.setEnabled(false);
		this.btnAddUnix.setEnabled(false);
		this.btnAddWin.setEnabled(false);
		this.btnOk.setEnabled(false);
		this.btnCancel.setEnabled(false);
	}

	private void addActions() {
		this.btnOk.addActionListener(new BtnAddsDialAdvCfg());
		
	}

	private void getInfos(Frame parent) {
		//TIMEOUT
		this.txtbox_timeout.setText(""+parent.connection.getTimeout());
		//PORTS
		for(int port:parent.connection.getPorts_windows()){
			this.dlm_ports_win.addElement(port);
		}
		
		for(int port:parent.connection.getPorts_mac()){
			this.dlm_ports_mac.addElement(port);
		}
		
		for(int port:parent.connection.getPorts_unix()){
			this.dlm_ports_unix.addElement(port);
		}
		
		
	}

	private void buildMain() {
		lblAdvancedConfiguration.setForeground(SystemColor.windowText);
		lblAdvancedConfiguration.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdvancedConfiguration.setBounds(247, 11, 147, 14);
		getContentPane().add(lblAdvancedConfiguration);
		
		separator_up.setBounds(10, 36, 621, 2);
		getContentPane().add(separator_up);
		
		separator_middle.setBounds(10, 74, 621, 2);
		getContentPane().add(separator_middle);
		
		separator_down.setBounds(10, 296, 621, 2);
		getContentPane().add(separator_down);
		
		btnCancel.setBounds(542, 309, 89, 23);
		getContentPane().add(btnCancel);
		
		btnOk.setBounds(443, 309, 89, 23);
		getContentPane().add(btnOk);
	}

	private void buildConfigPorts() {
		
		lblConfigurePorts.setForeground(SystemColor.textInactiveText);
		lblConfigurePorts.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfigurePorts.setBounds(20, 87, 103, 14);
		getContentPane().add(lblConfigurePorts);
		
		lblWindows.setForeground(SystemColor.textHighlight);
		lblWindows.setBounds(87, 112, 57, 14);
		getContentPane().add(lblWindows);
		
		lblMacOsx.setForeground(SystemColor.textHighlight);
		lblMacOsx.setBounds(292, 112, 57, 14);
		getContentPane().add(lblMacOsx);
		
		lblOtherunix.setForeground(SystemColor.textHighlight);
		lblOtherunix.setBounds(490, 112, 75, 14);
		getContentPane().add(lblOtherunix);
		
		list_ports_win.setBounds(52, 137, 123, 108);
		JScrollPane listwin_jbar = new JScrollPane(list_ports_win);
		listwin_jbar.setBounds(52, 137, 123, 108);
		getContentPane().add(listwin_jbar);
		
		list_ports_mac.setBounds(259, 139, 123, 108);
		JScrollPane listmac_jbar = new JScrollPane(list_ports_mac);
		listmac_jbar.setBounds(259, 139, 123, 108);
		getContentPane().add(listmac_jbar);
		
		list_ports_unix.setBounds(465, 139, 123, 108);
		JScrollPane listunix_jbar = new JScrollPane(list_ports_unix);
		listunix_jbar.setBounds(465, 139, 123, 108);
		getContentPane().add(listunix_jbar);
		
		txtbox_win = new JTextField();
		txtbox_win.setBounds(52, 251, 62, 20);
		getContentPane().add(txtbox_win);
		txtbox_win.setColumns(10);
		
		btnAddWin.setBounds(124, 250, 51, 23);
		getContentPane().add(btnAddWin);
		
		btnAddOSX.setBounds(331, 250, 51, 23);
		getContentPane().add(btnAddOSX);
		
		txtbox_mac = new JTextField();
		txtbox_mac.setBounds(259, 251, 62, 20);
		getContentPane().add(txtbox_mac);
		txtbox_mac.setColumns(10);
		
		txtbox_unix = new JTextField();
		txtbox_unix.setBounds(465, 251, 62, 20);
		getContentPane().add(txtbox_unix);
		txtbox_unix.setColumns(10);
		
		btnAddUnix.setBounds(537, 250, 51, 23);
		getContentPane().add(btnAddUnix);
		
	}

	private void buildConfigTO() {
		lblSetTimeout.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSetTimeout.setForeground(SystemColor.textInactiveText);
		lblSetTimeout.setBounds(20, 49, 123, 14);
		getContentPane().add(lblSetTimeout);
		
		txtbox_timeout = new JTextField();
		txtbox_timeout.setBounds(143, 46, 86, 20);
		getContentPane().add(txtbox_timeout);
		txtbox_timeout.setColumns(10);
		
	}

}
