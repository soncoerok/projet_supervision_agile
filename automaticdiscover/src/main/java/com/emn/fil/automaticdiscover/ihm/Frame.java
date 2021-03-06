package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.dto.ExportDataToCSV;
import com.emn.fil.automaticdiscover.dto.Scan;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnLaunchListener;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnPreferences;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnQuitListener;
//import com.emn.fil.automaticdiscover.ihm.listeners.BtnStopListener;
import com.emn.fil.automaticdiscover.utils.EnhancedTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@Component
public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// configuration
	@Value("${ihm.frame_width}")
	private int width;
	
	@Value("${ihm.frame_height}")
	private int height;
	
	@Value("${ihm.frame_title}")
	private String title;
	
	@Value("${chemin_export_csv}")
	private String pathExport;
	
	@Value("${nmap}")
	private String cheminNmap;
	
	@Value("${vitesse_scan}")
	private String vitesseScan;

	// List Machine
	private JTable tableMachine = new JTable();

	// TextFields
	private JFormattedTextField textFieldIp = new JFormattedTextField();

	// ProgressBar
	private JProgressBar progressBar = new JProgressBar();

	// Buttons
	private JButton btnStop = new JButton("Stopper");
	private JButton btnLunch = new JButton("Scanner");
	private JRadioButton rdbtnToutReseau = new JRadioButton("Son propre réseau");
	private JRadioButton rdbtnRangeReseau = new JRadioButton();

	// Results labels
	private JLabel lblNbWindows = new JLabel("0");
	private JLabel lblNbUnix = new JLabel("0");
	private JLabel lblNbMac = new JLabel("0");
	private JLabel lblNbResult = new JLabel("0");
	private JPanel panelResult = new JPanel();
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboMasqueReseau = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox comboMasqueIp = new JComboBox();
	private String[] masqueReseau = new String[] {"8", "16", "24", "32"};

	@Autowired
	private Scan scan;

	/**
	 * Create the frame.
	 */
	public Frame() {
		// setTitle(title);
		setTitle("Découverte auto des serveurs d'un centre de données");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, height, width);
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
		JMenu file = new JMenu("Fichier"), help = new JMenu("Aide"), edit = new JMenu("Edition");
		JMenuItem export = new JMenuItem("Exporter"), quit = new JMenuItem("Quitter"), about = new JMenuItem("A propos");
		// Export data
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportDataToCSV.export(pathExport, scan);
			}
		});

		setJMenuBar(menuBar);
		menuBar.add(file);
		file.add(export);
		file.add(quit);
		menuBar.add(edit);

		BtnPreferences listenerPreference = new BtnPreferences(this);
		JMenuItem preferences = new JMenuItem("Préférences");
		preferences.addActionListener(listenerPreference);

		edit.add(preferences);
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void _buildTop() {
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		GridBagLayout gblPanelHaut = new GridBagLayout();
		gblPanelHaut.columnWidths = new int[] { 20, 229, 151, 41, 0 };
		gblPanelHaut.rowHeights = new int[] { 20, 20, 0, 0 };
		gblPanelHaut.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gblPanelHaut.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelTop.setLayout(gblPanelHaut);

		// disable textField
		textFieldIp.setEditable(false);
		
		JPanel panelNetwork = new JPanel();
		GridBagConstraints gbcPanelNetwork = new GridBagConstraints();
		gbcPanelNetwork.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelNetwork.insets = new Insets(0, 0, 5, 5);
		gbcPanelNetwork.gridx = 1;
		gbcPanelNetwork.gridy = 0;
		panelTop.add(panelNetwork, gbcPanelNetwork);
		GridBagLayout gblPanelNetwork = new GridBagLayout();
		gblPanelNetwork.columnWidths = new int[]{141, 86, 0};
		gblPanelNetwork.rowHeights = new int[]{23, 0};
		gblPanelNetwork.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gblPanelNetwork.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNetwork.setLayout(gblPanelNetwork);
		GridBagConstraints gbcRdbtnToutReseau = new GridBagConstraints();
		gbcRdbtnToutReseau.insets = new Insets(0, 0, 0, 5);
		gbcRdbtnToutReseau.anchor = GridBagConstraints.WEST;
		gbcRdbtnToutReseau.gridx = 0;
		gbcRdbtnToutReseau.gridy = 0;
		panelNetwork.add(rdbtnToutReseau, gbcRdbtnToutReseau);
		rdbtnToutReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldIp.setEditable(false);
				comboMasqueIp.setEnabled(false);
				comboMasqueReseau.setEnabled(true);
			}
		});
		
		rdbtnToutReseau.setSelected(true);
		
		comboMasqueReseau.setModel(new DefaultComboBoxModel(masqueReseau));
		comboMasqueReseau.setSelectedIndex(2);
		GridBagConstraints gbcComboMasqueReseau = new GridBagConstraints();
		gbcComboMasqueReseau.fill = GridBagConstraints.HORIZONTAL;
		gbcComboMasqueReseau.gridx = 1;
		gbcComboMasqueReseau.gridy = 0;
		panelNetwork.add(comboMasqueReseau, gbcComboMasqueReseau);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup .add(rdbtnToutReseau);

		GridBagConstraints gbcBtnLunch = new GridBagConstraints();
		gbcBtnLunch.anchor = GridBagConstraints.EAST;
		gbcBtnLunch.insets = new Insets(0, 0, 5, 5);
		gbcBtnLunch.gridx = 2;
		gbcBtnLunch.gridy = 0;

		// LUNCH THE SCAN
		btnLunch.addActionListener(new BtnLaunchListener(this));
		panelTop.add(btnLunch, gbcBtnLunch);

		JPanel panelIpRange = new JPanel();
		GridBagConstraints gbcPanelIpRange = new GridBagConstraints();
		gbcPanelIpRange.anchor = GridBagConstraints.WEST;
		gbcPanelIpRange.insets = new Insets(0, 0, 5, 5);
		gbcPanelIpRange.gridx = 1;
		gbcPanelIpRange.gridy = 1;
		panelTop.add(panelIpRange, gbcPanelIpRange);
		GridBagLayout gblPanelIpRange = new GridBagLayout();
		gblPanelIpRange.columnWidths = new int[] { 0, 114, 88, 0 };
		gblPanelIpRange.rowHeights = new int[] { 0, 0 };
		gblPanelIpRange.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gblPanelIpRange.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelIpRange.setLayout(gblPanelIpRange);

		rdbtnRangeReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldIp.setEditable(true);
				comboMasqueIp.setEnabled(true);
				comboMasqueReseau.setEnabled(false);
			}
		});

		GridBagConstraints gbcRdbtnRangeReseau = new GridBagConstraints();
		gbcRdbtnRangeReseau.anchor = GridBagConstraints.WEST;
		gbcRdbtnRangeReseau.insets = new Insets(0, 0, 0, 5);
		gbcRdbtnRangeReseau.gridx = 0;
		gbcRdbtnRangeReseau.gridy = 0;
		panelIpRange.add(rdbtnRangeReseau, gbcRdbtnRangeReseau);

		// Associate the radio button
		buttonGroup.add(rdbtnRangeReseau);

		GridBagConstraints gbcTextFieldIp = new GridBagConstraints();
		gbcTextFieldIp.insets = new Insets(0, 0, 0, 5);
		gbcTextFieldIp.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldIp.gridx = 1;
		gbcTextFieldIp.gridy = 0;
		panelIpRange.add(textFieldIp, gbcTextFieldIp);
		
		comboMasqueIp.setModel(new DefaultComboBoxModel(masqueReseau));
		comboMasqueIp.setEnabled(false);
		comboMasqueIp.setSelectedIndex(2);
		GridBagConstraints gbc_comboMasqueIP = new GridBagConstraints();
		gbc_comboMasqueIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboMasqueIP.gridx = 2;
		gbc_comboMasqueIP.gridy = 0;
		panelIpRange.add(comboMasqueIp, gbc_comboMasqueIP);

		GridBagConstraints gbcBtnStop = new GridBagConstraints();
		gbcBtnStop.anchor = GridBagConstraints.EAST;
		gbcBtnStop.insets = new Insets(0, 0, 5, 5);
		gbcBtnStop.gridx = 2;
		gbcBtnStop.gridy = 1;
		
		//btnStop.addActionListener(new BtnStopListener(this));
		panelTop.add(btnStop, gbcBtnStop);
		
		GridBagConstraints gbcPanelResult = new GridBagConstraints();
		gbcPanelResult.gridheight = 2;
		gbcPanelResult.insets = new Insets(0, 0, 5, 0);
		gbcPanelResult.fill = GridBagConstraints.BOTH;
		gbcPanelResult.gridx = 3;
		gbcPanelResult.gridy = 0;
		panelTop.add(panelResult, gbcPanelResult);
		GridBagLayout gblPanelResult = new GridBagLayout();
		gblPanelResult.columnWidths = new int[] { 106, 52, 0, 0 };
		gblPanelResult.rowHeights = new int[] { 14, 0, 0, 0, 0 };
		gblPanelResult.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gblPanelResult.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelResult.setLayout(gblPanelResult);

		JLabel lblRsultats = new JLabel("Résultats :");
		GridBagConstraints gbcLblRsultats = new GridBagConstraints();
		gbcLblRsultats.insets = new Insets(0, 0, 5, 5);
		gbcLblRsultats.anchor = GridBagConstraints.NORTHWEST;
		gbcLblRsultats.gridx = 1;
		gbcLblRsultats.gridy = 0;
		panelResult.add(lblRsultats, gbcLblRsultats);

		GridBagConstraints gbcLblNbResult = new GridBagConstraints();
		gbcLblNbResult.insets = new Insets(0, 0, 5, 0);
		gbcLblNbResult.gridx = 2;
		gbcLblNbResult.gridy = 0;
		panelResult.add(lblNbResult, gbcLblNbResult);

		JLabel lblWindows = new JLabel("Windows :");
		GridBagConstraints gbcLblWindows = new GridBagConstraints();
		gbcLblWindows.anchor = GridBagConstraints.EAST;
		gbcLblWindows.insets = new Insets(0, 0, 5, 5);
		gbcLblWindows.gridx = 0;
		gbcLblWindows.gridy = 1;
		panelResult.add(lblWindows, gbcLblWindows);

		GridBagConstraints gbcLblNbWindows = new GridBagConstraints();
		gbcLblNbWindows.insets = new Insets(0, 0, 5, 5);
		gbcLblNbWindows.gridx = 1;
		gbcLblNbWindows.gridy = 1;
		panelResult.add(lblNbWindows, gbcLblNbWindows);

		JLabel lblUnix = new JLabel("Unix :");
		GridBagConstraints gbcLblUnix = new GridBagConstraints();
		gbcLblUnix.anchor = GridBagConstraints.EAST;
		gbcLblUnix.insets = new Insets(0, 0, 5, 5);
		gbcLblUnix.gridx = 0;
		gbcLblUnix.gridy = 2;
		panelResult.add(lblUnix, gbcLblUnix);

		GridBagConstraints gbcLblNbUnix = new GridBagConstraints();
		gbcLblNbUnix.insets = new Insets(0, 0, 5, 5);
		gbcLblNbUnix.gridx = 1;
		gbcLblNbUnix.gridy = 2;
		panelResult.add(lblNbUnix, gbcLblNbUnix);

		JLabel lblMacOs = new JLabel("Mac OS :");
		GridBagConstraints gbcLblMacOs = new GridBagConstraints();
		gbcLblMacOs.anchor = GridBagConstraints.EAST;
		gbcLblMacOs.insets = new Insets(0, 0, 0, 5);
		gbcLblMacOs.gridx = 0;
		gbcLblMacOs.gridy = 3;
		panelResult.add(lblMacOs, gbcLblMacOs);

		GridBagConstraints gbcLblNbMac = new GridBagConstraints();
		gbcLblNbMac.insets = new Insets(0, 0, 0, 5);
		gbcLblNbMac.gridx = 1;
		gbcLblNbMac.gridy = 3;
		panelResult.add(lblNbMac, gbcLblNbMac);

		GridBagConstraints gbcProgressBar = new GridBagConstraints();
		gbcProgressBar.anchor = GridBagConstraints.NORTH;
		gbcProgressBar.fill = GridBagConstraints.HORIZONTAL;
		gbcProgressBar.gridwidth = 4;
		gbcProgressBar.gridx = 0;
		gbcProgressBar.gridy = 2;
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		panelTop.add(progressBar, gbcProgressBar);
	}

	/**
	 * Create the body
	 */
	private void _buildBody() {
		tableMachine.setFillsViewportHeight(true);
		tableMachine.setShowGrid(false);
		tableMachine.setShowHorizontalLines(false);
		tableMachine.setShowVerticalLines(false);
		tableMachine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMachine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && tableMachine.getSelectedRow() >= 0 && tableMachine.getSelectedRow() < tableMachine.getRowCount()) {
					MachineDetail detail = new MachineDetail(scan.getListeMachine().get(tableMachine.getSelectedRow()), scan.getDateScan());
					detail.setVisible(true);
				}
			}
		});
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(tableMachine);
		setMachineTable(new String[] { "IP", "HostName", "OS" }, new ArrayList<ArrayList<Object>>());
		contentPane.add(sp, BorderLayout.CENTER);
	}

	/**
	 * Remplie la <i>JTable</i> avec les donnees passe en parametre.
	 * 
	 * @param columnNames
	 *            Noms des colonnes
	 * @param data
	 *            Donnees dans l'ordre du nom des colonnes
	 */
	public void setMachineTable(String[] columnNames, ArrayList<ArrayList<Object>> data) {
		tableMachine.setModel(new EnhancedTableModel(columnNames, data));
	}

	public void setMachineTable(ArrayList<Object> data) {
		((EnhancedTableModel) tableMachine.getModel()).setEnhancedTableModel(data);
	}

	public void setNbWindows(String win) {
		this.lblNbWindows.setText(win);
	}

	public void setNbUnix(String unix) {
		this.lblNbUnix.setText(unix);
	}

	public void setNbMac(String mac) {
		this.lblNbMac.setText(mac);
	}

	public int getNbWindows() {
		return Integer.valueOf(this.lblNbWindows.getText());
	}

	public int getNbUnix() {
		return Integer.valueOf(this.lblNbUnix.getText());
	}

	public int getNbMac() {
		return Integer.valueOf(this.lblNbMac.getText());
	}

	public void setNbResult(String nb) {
		this.lblNbResult.setText(nb);
	}

	public JPanel getPanelResult() {
		return panelResult;
	}

	public Scan getScan() {
		return scan;
	}

	public void setScan(Scan scan) {
		this.scan = scan;
	}

	public void resetResults() {
		setNbResult("0");
		setNbWindows("0");
		setNbUnix("0");
		setNbMac("0");
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JButton getBtnLunch() {
		return btnLunch;
	}

	public String getCheminNmap() {
		return this.cheminNmap;
	}

	public void setCheminNmap(String chemin) {
		this.cheminNmap = chemin;
	}

	public String getVistesseScan() {
		return this.vitesseScan;
	}

	public void setVistesseScan(String scan) {
		this.vitesseScan = scan;
	}
	
	public boolean isIpChecked() {
		return rdbtnRangeReseau.isSelected();
	}
	
	public int getMasqueReseau() {
		return Integer.valueOf(comboMasqueReseau.getSelectedItem().toString());
	}
	
	public int getMasqueIp() {
		return Integer.valueOf(comboMasqueIp.getSelectedItem().toString());
	}
	
	public String getTextFieldIp() {
		return textFieldIp.getText();
	}
	
	public void setTextFieldIp(String ip){
		this.textFieldIp.setText(ip);
	}
}
