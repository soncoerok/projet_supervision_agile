package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.ihm.listeners.BtnLaunchListener;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnQuitListener;

@Component
public class FrameNew extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// List Machine
	private JTable tableMachine = new JTable();
	
	// TextField
	private JFormattedTextField textFieldFrom = new JFormattedTextField();
	private JFormattedTextField textFieldTo = new JFormattedTextField();

	// ProgressBar
	private JProgressBar progressBar = new JProgressBar();
	
	// Buttons
	private JButton btnStop = new JButton("Stopper");;
	private JButton btnLunch = new JButton("Scanner");;
	private JRadioButton rdbtnToutReseau = new JRadioButton("Tout le réseau");
	private JRadioButton rdbtnRangeReseau = new JRadioButton("De");;

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
		gblPanelHaut.columnWidths = new int[] { 44, 249, 71, 0 };
		gblPanelHaut.rowHeights = new int[] { 20, 51, 0, 0 };
		gblPanelHaut.columnWeights = new double[] { 0.0, 1.0, 1.0,Double.MIN_VALUE };
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
		gbcBtnLunch.insets = new Insets(0, 0, 5, 0);
		gbcBtnLunch.gridx = 2;
		gbcBtnLunch.gridy = 0;
		btnLunch.addActionListener(new BtnLaunchListener());
		panelTop.add(btnLunch, gbcBtnLunch);

		JPanel panelIpRange = new JPanel();
		GridBagConstraints gbcPanelIpRange = new GridBagConstraints();
		gbcPanelIpRange.gridwidth = 2;
		gbcPanelIpRange.insets = new Insets(0, 0, 5, 5);
		gbcPanelIpRange.gridx = 0;
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
		gbcLabel.anchor = GridBagConstraints.EAST;
		gbcLabel.insets = new Insets(0, 0, 0, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 1;
		panelIpRange.add(label, gbcLabel);

		GridBagConstraints gbcTextFieldTo = new GridBagConstraints();
		gbcTextFieldTo.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldTo.gridx = 1;
		gbcTextFieldTo.gridy = 1;
		panelIpRange.add(textFieldTo, gbcTextFieldTo);

		GridBagConstraints gbcBtnStop = new GridBagConstraints();
		gbcBtnStop.insets = new Insets(0, 0, 5, 0);
		gbcBtnStop.gridx = 2;
		gbcBtnStop.gridy = 1;
		panelTop.add(btnStop, gbcBtnStop);

		GridBagConstraints gbcProgressBar = new GridBagConstraints();
		gbcProgressBar.fill = GridBagConstraints.BOTH;
		gbcProgressBar.gridwidth = 3;
		gbcProgressBar.gridx = 0;
		gbcProgressBar.gridy = 2;
		panelTop.add(progressBar, gbcProgressBar);
	}
	
	/**
	 * Create the body
	 */
	private void _buildBody() {
		tableMachine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMachine.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, "hh" }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, "aaaa", null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null }, { null, null, "" },
				{ null, null, "last" }, }, new String[] { "IP", "HostName",
				"Detail" }));
		contentPane.add(tableMachine, BorderLayout.CENTER);
	}

}
