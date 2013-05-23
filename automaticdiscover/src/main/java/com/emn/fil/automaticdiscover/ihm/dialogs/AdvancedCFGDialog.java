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

public class AdvancedCFGDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// Dialog
	private JLabel lblAdvancedConfiguration = new JLabel(
			"Advanced Configuration :");
	private JSeparator separatorUp = new JSeparator();
	private JSeparator separatorMiddle = new JSeparator();
	private JSeparator separatorDown = new JSeparator();
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnOk = new JButton("OK");

	// Partie configuration timeout
	private JLabel lblSetTimeout = new JLabel("Set Time-Out (ms) :");
	private JTextField txtboxTimeout;

	// Partie Configuration ports
	private JLabel lblConfigurePorts = new JLabel("Configure ports :");
	private JLabel lblWindows = new JLabel("Windows :");
	private JLabel lblMacOsx = new JLabel("Mac OSX :");
	private JLabel lblOtherunix = new JLabel("Other (Unix ...)");

	private DefaultListModel<Integer> dlmPortsWin = new DefaultListModel<Integer>();
	private JList<Integer> listPortsWin = new JList<Integer>(dlmPortsWin);

	private DefaultListModel<Integer> dlmPortsMac = new DefaultListModel<Integer>();
	private JList<Integer> listPortsMac = new JList<Integer>(dlmPortsMac);

	private DefaultListModel<Integer> dlmPortsUnix = new DefaultListModel<Integer>();
	private JList<Integer> listPortsUnix = new JList<Integer>(dlmPortsUnix);

	private JButton btnAddWin = new JButton("add");
	private JButton btnAddOSX = new JButton("add");
	private JButton btnAddUnix = new JButton("add");
	private JTextField txtboxWin;
	private JTextField txtboxMac;
	private JTextField txtboxUnix;

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

		// TMP
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
		// TIMEOUT
		this.txtboxTimeout.setText("" + parent.getConnection().getTimeout());
		// PORTS
		for (int port : parent.getConnection().getPortsWindows()) {
			this.dlmPortsWin.addElement(port);
		}

		for (int port : parent.getConnection().getPortsMac()) {
			this.dlmPortsMac.addElement(port);
		}

		for (int port : parent.getConnection().getPortsUnix()) {
			this.dlmPortsUnix.addElement(port);
		}
	}

	private void buildMain() {
		lblAdvancedConfiguration.setForeground(SystemColor.windowText);
		lblAdvancedConfiguration.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdvancedConfiguration.setBounds(247, 11, 147, 14);
		getContentPane().add(lblAdvancedConfiguration);

		separatorUp.setBounds(10, 36, 621, 2);
		getContentPane().add(separatorUp);

		separatorMiddle.setBounds(10, 74, 621, 2);
		getContentPane().add(separatorMiddle);

		separatorDown.setBounds(10, 296, 621, 2);
		getContentPane().add(separatorDown);

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

		listPortsWin.setBounds(52, 137, 123, 108);
		JScrollPane listwinJbar = new JScrollPane(listPortsWin);
		listwinJbar.setBounds(52, 137, 123, 108);
		getContentPane().add(listwinJbar);

		listPortsMac.setBounds(259, 139, 123, 108);
		JScrollPane listmacJbar = new JScrollPane(listPortsMac);
		listmacJbar.setBounds(259, 139, 123, 108);
		getContentPane().add(listmacJbar);

		listPortsUnix.setBounds(465, 139, 123, 108);
		JScrollPane listunixJbar = new JScrollPane(listPortsUnix);
		listunixJbar.setBounds(465, 139, 123, 108);
		getContentPane().add(listunixJbar);

		txtboxWin = new JTextField();
		txtboxWin.setBounds(52, 251, 62, 20);
		getContentPane().add(txtboxWin);
		txtboxWin.setColumns(10);

		btnAddWin.setBounds(124, 250, 51, 23);
		getContentPane().add(btnAddWin);

		btnAddOSX.setBounds(331, 250, 51, 23);
		getContentPane().add(btnAddOSX);

		txtboxMac = new JTextField();
		txtboxMac.setBounds(259, 251, 62, 20);
		getContentPane().add(txtboxMac);
		txtboxMac.setColumns(10);

		txtboxUnix = new JTextField();
		txtboxUnix.setBounds(465, 251, 62, 20);
		getContentPane().add(txtboxUnix);
		txtboxUnix.setColumns(10);

		btnAddUnix.setBounds(537, 250, 51, 23);
		getContentPane().add(btnAddUnix);
	}

	private void buildConfigTO() {
		lblSetTimeout.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSetTimeout.setForeground(SystemColor.textInactiveText);
		lblSetTimeout.setBounds(20, 49, 123, 14);
		getContentPane().add(lblSetTimeout);

		txtboxTimeout = new JTextField();
		txtboxTimeout.setBounds(143, 46, 86, 20);
		getContentPane().add(txtboxTimeout);
		txtboxTimeout.setColumns(10);
	}
}
