package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.ExportDataToCSV;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnLaunchListener;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnQuitListener;
import com.emn.fil.automaticdiscover.nmap.Nmap;

@Component
public class FrameNew extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// List Machine
	private JTable tableMachine = new JTable();
	
	// TextFields
	private JFormattedTextField textFieldFrom = new JFormattedTextField();
	private JFormattedTextField textFieldTo = new JFormattedTextField();

	// ProgressBar
	private JProgressBar progressBar = new JProgressBar();
	
	// Buttons
	private JButton btnStop = new JButton("Stopper");
	private JButton btnLunch = new JButton("Scanner");
	private JRadioButton rdbtnToutReseau = new JRadioButton("Tout le réseau");
	private JRadioButton rdbtnRangeReseau = new JRadioButton("De");
	
	// Results labels
	private JLabel lblNbWindows = new JLabel("0");
	private JLabel lblNbUnix = new JLabel("0");
	private JLabel lblNbMac = new JLabel("0");
	private JPanel panelResult;
	private List<Machine> listMachine = Nmap.parsageResultat();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameNew frame = new FrameNew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameNew() {
		setTitle("Découverte auto des serveurs d'un centre de données");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 530);
		
		// building elements of the frame
		this._buildMenu();
		this._buildTop();
		this._buildBody();
	}

	/**
	 * Create the Menu Bar
	 */
	private void _buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier"), help = new JMenu("Aide");
		JMenuItem export = new JMenuItem("Exporter"), quit = new JMenuItem("Quitter"), 
				about = new JMenuItem("A propos");
		// Export data
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportDataToCSV.export("./export.csv", listMachine);
			}
		});
		
		setJMenuBar(menuBar);
		menuBar.add(file);
		file.add(export);
		file.add(quit);
		menuBar.add(help);
		help.add(about);
		
		quit.addActionListener(new BtnQuitListener());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(2, 2));
		setContentPane(contentPane);
	}
	
	/**
	 * Create the top
	 */
	private void _buildTop() {
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		GridBagLayout gblPanelHaut = new GridBagLayout();
		gblPanelHaut.columnWidths = new int[] { 20, 263, 107, 41, 0 };
		gblPanelHaut.rowHeights = new int[] { 20, 51, 0, 0 };
		gblPanelHaut.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,Double.MIN_VALUE };
		gblPanelHaut.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelTop.setLayout(gblPanelHaut);
		
		// disable textField
		textFieldFrom.setEditable(false);
		textFieldTo.setEditable(false);
		rdbtnToutReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFrom.setEditable(false);
				textFieldTo.setEditable(false);
			}
		});
		
		rdbtnToutReseau.setSelected(true);
		GridBagConstraints gbcRdbtnToutReseau = new GridBagConstraints();
		gbcRdbtnToutReseau.anchor = GridBagConstraints.WEST;
		gbcRdbtnToutReseau.insets = new Insets(0, 0, 5, 5);
		gbcRdbtnToutReseau.gridx = 1;
		gbcRdbtnToutReseau.gridy = 0;
		panelTop.add(rdbtnToutReseau, gbcRdbtnToutReseau);

		GridBagConstraints gbcBtnLunch = new GridBagConstraints();
		gbcBtnLunch.anchor = GridBagConstraints.WEST;
		gbcBtnLunch.insets = new Insets(0, 0, 5, 5);
		gbcBtnLunch.gridx = 2;
		gbcBtnLunch.gridy = 0;
		btnLunch.addActionListener(new BtnLaunchListener());
		panelTop.add(btnLunch, gbcBtnLunch);

		JPanel panelIpRange = new JPanel();
		GridBagConstraints gbcPanelIpRange = new GridBagConstraints();
		gbcPanelIpRange.anchor = GridBagConstraints.WEST;
		gbcPanelIpRange.insets = new Insets(0, 0, 5, 5);
		gbcPanelIpRange.gridx = 1;
		gbcPanelIpRange.gridy = 1;
		panelTop.add(panelIpRange, gbcPanelIpRange);
		GridBagLayout gblPanelIpRange = new GridBagLayout();
		gblPanelIpRange.columnWidths = new int[] { 0, 184, 0 };
		gblPanelIpRange.rowHeights = new int[] { 0, 33, 0 };
		gblPanelIpRange.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gblPanelIpRange.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelIpRange.setLayout(gblPanelIpRange);

		rdbtnRangeReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFrom.setEditable(true);
				textFieldTo.setEditable(true);
			}
		});
		
		GridBagConstraints gbcRdbtnRangeReseau = new GridBagConstraints();
		gbcRdbtnRangeReseau.anchor = GridBagConstraints.WEST;
		gbcRdbtnRangeReseau.insets = new Insets(0, 0, 5, 5);
		gbcRdbtnRangeReseau.gridx = 0;
		gbcRdbtnRangeReseau.gridy = 0;
		panelIpRange.add(rdbtnRangeReseau, gbcRdbtnRangeReseau);
		
		// Associate the radio button
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnToutReseau);
		buttonGroup.add(rdbtnRangeReseau);

		GridBagConstraints gbcTextFieldFrom = new GridBagConstraints();
		gbcTextFieldFrom.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldFrom.insets = new Insets(0, 0, 5, 0);
		gbcTextFieldFrom.gridx = 1;
		gbcTextFieldFrom.gridy = 0;
		panelIpRange.add(textFieldFrom, gbcTextFieldFrom);

		JLabel label = new JLabel("à");
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.NORTHEAST;
		gbcLabel.insets = new Insets(0, 0, 0, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 1;
		panelIpRange.add(label, gbcLabel);

		GridBagConstraints gbcTextFieldTo = new GridBagConstraints();
		gbcTextFieldTo.anchor = GridBagConstraints.NORTH;
		gbcTextFieldTo.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldTo.gridx = 1;
		gbcTextFieldTo.gridy = 1;
		panelIpRange.add(textFieldTo, gbcTextFieldTo);

		GridBagConstraints gbcBtnStop = new GridBagConstraints();
		gbcBtnStop.anchor = GridBagConstraints.NORTHWEST;
		gbcBtnStop.insets = new Insets(0, 0, 5, 5);
		gbcBtnStop.gridx = 2;
		gbcBtnStop.gridy = 1;
		panelTop.add(btnStop, gbcBtnStop);
		
		panelResult = new JPanel();
		GridBagConstraints gbc_panelResult = new GridBagConstraints();
		gbc_panelResult.gridheight = 2;
		gbc_panelResult.insets = new Insets(0, 0, 5, 0);
		gbc_panelResult.fill = GridBagConstraints.BOTH;
		gbc_panelResult.gridx = 3;
		gbc_panelResult.gridy = 0;
		panelTop.add(panelResult, gbc_panelResult);
		GridBagLayout gbl_panelResult = new GridBagLayout();
		gbl_panelResult.columnWidths = new int[]{106, 52, 0};
		gbl_panelResult.rowHeights = new int[]{14, 0, 0, 0, 0};
		gbl_panelResult.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelResult.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelResult.setLayout(gbl_panelResult);
		
		JLabel lblRsultats = new JLabel("Résultats :");
		GridBagConstraints gbc_lblRsultats = new GridBagConstraints();
		gbc_lblRsultats.insets = new Insets(0, 0, 5, 0);
		gbc_lblRsultats.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblRsultats.gridx = 1;
		gbc_lblRsultats.gridy = 0;
		panelResult.add(lblRsultats, gbc_lblRsultats);
		
		JLabel lblWindows = new JLabel("Windows :");
		GridBagConstraints gbc_lblWindows = new GridBagConstraints();
		gbc_lblWindows.anchor = GridBagConstraints.EAST;
		gbc_lblWindows.insets = new Insets(0, 0, 5, 5);
		gbc_lblWindows.gridx = 0;
		gbc_lblWindows.gridy = 1;
		panelResult.add(lblWindows, gbc_lblWindows);
		
		GridBagConstraints gbc_lblNbWindows = new GridBagConstraints();
		gbc_lblNbWindows.insets = new Insets(0, 0, 5, 0);
		gbc_lblNbWindows.gridx = 1;
		gbc_lblNbWindows.gridy = 1;
		panelResult.add(lblNbWindows, gbc_lblNbWindows);
		
		JLabel lblUnix = new JLabel("Unix :");
		GridBagConstraints gbc_lblUnix = new GridBagConstraints();
		gbc_lblUnix.anchor = GridBagConstraints.EAST;
		gbc_lblUnix.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnix.gridx = 0;
		gbc_lblUnix.gridy = 2;
		panelResult.add(lblUnix, gbc_lblUnix);
		
		GridBagConstraints gbc_lblNbUnix = new GridBagConstraints();
		gbc_lblNbUnix.insets = new Insets(0, 0, 5, 0);
		gbc_lblNbUnix.gridx = 1;
		gbc_lblNbUnix.gridy = 2;
		panelResult.add(lblNbUnix, gbc_lblNbUnix);
		
		JLabel lblMacOs = new JLabel("Mac OS :");
		GridBagConstraints gbc_lblMacOs = new GridBagConstraints();
		gbc_lblMacOs.anchor = GridBagConstraints.EAST;
		gbc_lblMacOs.insets = new Insets(0, 0, 0, 5);
		gbc_lblMacOs.gridx = 0;
		gbc_lblMacOs.gridy = 3;
		panelResult.add(lblMacOs, gbc_lblMacOs);
		
		GridBagConstraints gbc_lblNbMac = new GridBagConstraints();
		gbc_lblNbMac.gridx = 1;
		gbc_lblNbMac.gridy = 3;
		panelResult.add(lblNbMac, gbc_lblNbMac);

		GridBagConstraints gbcProgressBar = new GridBagConstraints();
		gbcProgressBar.insets = new Insets(0, 0, 0, 5);
		gbcProgressBar.anchor = GridBagConstraints.NORTH;
		gbcProgressBar.fill = GridBagConstraints.HORIZONTAL;
		gbcProgressBar.gridwidth = 4;
		gbcProgressBar.gridx = 0;
		gbcProgressBar.gridy = 2;
		progressBar.setValue(15);
		panelTop.add(progressBar, gbcProgressBar);
	}
	
	/**
	 * Create the body
	 */
	private void _buildBody() {
		tableMachine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMachine.setModel(new DefaultTableModel(new Object[][] {
				{ null, "hh" }, { null, null },
				{ null, null, null }, { null, null },
				{ null, null, "last" }, }, 
				new String[] { "IP", "HostName" }));
		
		contentPane.add(new JScrollPane(tableMachine), BorderLayout.CENTER);
	}

}
