package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.JButton;

import com.emn.fil.automaticdiscover.dto.Machine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MachineDetail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();
	private JPanel panelDetail = new JPanel();
	// Attributes
	private Machine machine = new Machine();
	// Labels
	private JLabel ip = new JLabel(machine.getIp().toString());
	private JLabel hostName = new JLabel(machine.getHostname());
	private JLabel os = new JLabel(machine.getOsType().toString());
	private JLabel ram = new JLabel("ram");
	private JLabel updated = new JLabel("updated");
	// Buttons
	private JButton btnRestart = new JButton("Redémarrer");
	private JButton btnUpdate = new JButton("Mettre à jour");
	
	/**
	 * Create the frame.
	 */
	public MachineDetail() {
		_build();
	}
	
	/**
	 * Create the frame.
	 * @param Machine machine
	 * @param String updated
	 */
	public MachineDetail(Machine machine, String updated) {
		setMachine(machine);
		setUpdated(updated);
		ip.setText(machine.getIp().toString());
		hostName.setText(machine.getHostname());
		os.setText(machine.getOsType().toString());
		_build();
	}
	
	private void _build() {
		_initFrame();
		_buildTop();
		_buildButtons();
	}
	
	private void _initFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Informations sur " + machine.getHostname());
		setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	private void _buildTop() {
		contentPane.add(panelDetail, BorderLayout.CENTER);
		GridBagLayout gblPanelDetail = new GridBagLayout();
		gblPanelDetail.columnWidths = new int[]{138, 127, 156, 0};
		gblPanelDetail.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblPanelDetail.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gblPanelDetail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelDetail.setLayout(gblPanelDetail);
		
		JLabel lblAdresseIp = new JLabel("Adresse IP :");
		GridBagConstraints gbcLblAdresseIp = new GridBagConstraints();
		gbcLblAdresseIp.insets = new Insets(0, 0, 5, 5);
		gbcLblAdresseIp.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gbcLblAdresseIp.gridx = 0;
		gbcLblAdresseIp.gridy = 1;
		panelDetail.add(lblAdresseIp, gbcLblAdresseIp);
		
		GridBagConstraints gbcIp = new GridBagConstraints();
		gbcIp.anchor = GridBagConstraints.WEST;
		gbcIp.insets = new Insets(0, 0, 5, 5);
		gbcIp.gridx = 1;
		gbcIp.gridy = 1;
		panelDetail.add(ip, gbcIp);
		
		GridBagConstraints gbcUpdated = new GridBagConstraints();
		gbcUpdated.insets = new Insets(0, 0, 5, 0);
		gbcUpdated.gridx = 2;
		gbcUpdated.gridy = 1;
		panelDetail.add(updated, gbcUpdated);
		
		JLabel lblHostname = new JLabel("Hostname :");
		GridBagConstraints gbcLblHostname = new GridBagConstraints();
		gbcLblHostname.anchor = GridBagConstraints.EAST;
		gbcLblHostname.insets = new Insets(0, 0, 5, 5);
		gbcLblHostname.gridx = 0;
		gbcLblHostname.gridy = 2;
		panelDetail.add(lblHostname, gbcLblHostname);
		
		GridBagConstraints gbcHostName = new GridBagConstraints();
		gbcHostName.anchor = GridBagConstraints.WEST;
		gbcHostName.insets = new Insets(0, 0, 5, 5);
		gbcHostName.gridx = 1;
		gbcHostName.gridy = 2;
		panelDetail.add(hostName, gbcHostName);
		
		JLabel lblSystmeDexploitation = new JLabel("Système d'exploitation :");
		GridBagConstraints gbcLblSystmeDexploitation = new GridBagConstraints();
		gbcLblSystmeDexploitation.anchor = GridBagConstraints.EAST;
		gbcLblSystmeDexploitation.insets = new Insets(0, 0, 5, 5);
		gbcLblSystmeDexploitation.gridx = 0;
		gbcLblSystmeDexploitation.gridy = 3;
		panelDetail.add(lblSystmeDexploitation, gbcLblSystmeDexploitation);
		
		GridBagConstraints gbcOs = new GridBagConstraints();
		gbcOs.anchor = GridBagConstraints.WEST;
		gbcOs.insets = new Insets(0, 0, 5, 5);
		gbcOs.gridx = 1;
		gbcOs.gridy = 3;
		panelDetail.add(os, gbcOs);
		
		JLabel lblRam = new JLabel("RAM :");
		GridBagConstraints gbcLblRam = new GridBagConstraints();
		gbcLblRam.anchor = GridBagConstraints.EAST;
		gbcLblRam.insets = new Insets(0, 0, 5, 5);
		gbcLblRam.gridx = 0;
		gbcLblRam.gridy = 4;
		panelDetail.add(lblRam, gbcLblRam);
		
		GridBagConstraints gbcRam = new GridBagConstraints();
		gbcRam.anchor = GridBagConstraints.WEST;
		gbcRam.insets = new Insets(0, 0, 5, 5);
		gbcRam.gridx = 1;
		gbcRam.gridy = 4;
		panelDetail.add(ram, gbcRam);
		
		JProgressBar progressBarRAM = new JProgressBar();
		GridBagConstraints gbcProgressBarRAM = new GridBagConstraints();
		gbcProgressBarRAM.insets = new Insets(0, 0, 5, 0);
		gbcProgressBarRAM.gridx = 2;
		gbcProgressBarRAM.gridy = 4;
		panelDetail.add(progressBarRAM, gbcProgressBarRAM);
		
		JLabel lblCpu = new JLabel("CPU :");
		GridBagConstraints gbcLblCpu = new GridBagConstraints();
		gbcLblCpu.anchor = GridBagConstraints.EAST;
		gbcLblCpu.insets = new Insets(0, 0, 5, 5);
		gbcLblCpu.gridx = 0;
		gbcLblCpu.gridy = 5;
		panelDetail.add(lblCpu, gbcLblCpu);
		
		JProgressBar progressBarCPU = new JProgressBar();
		GridBagConstraints gbcProgressBarCPU = new GridBagConstraints();
		gbcProgressBarCPU.insets = new Insets(0, 0, 5, 0);
		gbcProgressBarCPU.gridx = 2;
		gbcProgressBarCPU.gridy = 5;
		panelDetail.add(progressBarCPU, gbcProgressBarCPU);
	}
	
	private void _buildButtons() {
		btnRestart.setEnabled(false);
		GridBagConstraints gbcBtnRestart = new GridBagConstraints();
		gbcBtnRestart.insets = new Insets(0, 0, 0, 5);
		gbcBtnRestart.gridx = 0;
		gbcBtnRestart.gridy = 8;
		panelDetail.add(btnRestart, gbcBtnRestart);
		
		GridBagConstraints gbcBtnUpdate = new GridBagConstraints();
		gbcBtnUpdate.insets = new Insets(0, 0, 0, 5);
		gbcBtnUpdate.gridx = 1;
		gbcBtnUpdate.gridy = 8;
		panelDetail.add(btnUpdate, gbcBtnUpdate);
		
		JButton btnExit = new JButton("Fermer");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbcBtnExit = new GridBagConstraints();
		gbcBtnExit.gridx = 2;
		gbcBtnExit.gridy = 8;
		panelDetail.add(btnExit, gbcBtnExit);
	}
	
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	public void setUpdated(String updated) {
		this.updated.setText(updated);
	}
}
