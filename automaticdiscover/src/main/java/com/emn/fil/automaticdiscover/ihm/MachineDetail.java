package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.snmp.RunSNMP;

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
	private JLabel ram = new JLabel("Chargement...");
	private JLabel updated = new JLabel("updated");
	private JLabel cpu = new JLabel("Chargement...");
	private JLabel pourcentageRAM = new JLabel("0 %");
	private JLabel pourcentageCPU = new JLabel("0 %");
	// Progress bar
	private JProgressBar progressBarCPU = new JProgressBar();
	private JProgressBar progressBarRAM = new JProgressBar();
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
		_updateRamCpu();
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
		gblPanelDetail.columnWidths = new int[]{145, 145, 50, 145, 0};
		gblPanelDetail.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblPanelDetail.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		gbcIp.gridwidth = 2;
		gbcIp.anchor = GridBagConstraints.WEST;
		gbcIp.insets = new Insets(0, 0, 5, 5);
		gbcIp.gridx = 1;
		gbcIp.gridy = 1;
		panelDetail.add(ip, gbcIp);
		
		GridBagConstraints gbcUpdated = new GridBagConstraints();
		gbcUpdated.insets = new Insets(0, 0, 5, 0);
		gbcUpdated.gridx = 3;
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
		gbcHostName.gridwidth = 2;
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
		gbcOs.gridwidth = 2;
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
		panelDetail.add(getRam(), gbcRam);
		
		GridBagConstraints gbcPourcentageRAM = new GridBagConstraints();
		gbcPourcentageRAM.anchor = GridBagConstraints.EAST;
		gbcPourcentageRAM.insets = new Insets(0, 0, 5, 5);
		gbcPourcentageRAM.gridx = 2;
		gbcPourcentageRAM.gridy = 4;
		panelDetail.add(getPourcentageRAM(), gbcPourcentageRAM);
		
		GridBagConstraints gbcProgressBarRAM = new GridBagConstraints();
		gbcProgressBarRAM.fill = GridBagConstraints.HORIZONTAL;
		gbcProgressBarRAM.insets = new Insets(0, 0, 5, 0);
		gbcProgressBarRAM.gridx = 3;
		gbcProgressBarRAM.gridy = 4;
		panelDetail.add(getProgressBarRAM(), gbcProgressBarRAM);
		
		JLabel lblCpu = new JLabel("CPU :");
		GridBagConstraints gbcLblCpu = new GridBagConstraints();
		gbcLblCpu.anchor = GridBagConstraints.EAST;
		gbcLblCpu.insets = new Insets(0, 0, 5, 5);
		gbcLblCpu.gridx = 0;
		gbcLblCpu.gridy = 5;
		panelDetail.add(lblCpu, gbcLblCpu);
		
		GridBagConstraints gbcCpu = new GridBagConstraints();
		gbcCpu.anchor = GridBagConstraints.WEST;
		gbcCpu.insets = new Insets(0, 0, 5, 5);
		gbcCpu.gridx = 1;
		gbcCpu.gridy = 5;
		panelDetail.add(getCpu(), gbcCpu);
		
		GridBagConstraints gbcPourcentageCPU = new GridBagConstraints();
		gbcPourcentageCPU.anchor = GridBagConstraints.EAST;
		gbcPourcentageCPU.insets = new Insets(0, 0, 5, 5);
		gbcPourcentageCPU.gridx = 2;
		gbcPourcentageCPU.gridy = 5;
		panelDetail.add(pourcentageCPU, gbcPourcentageCPU);
		
		GridBagConstraints gbcProgressBarCPU = new GridBagConstraints();
		gbcProgressBarCPU.fill = GridBagConstraints.HORIZONTAL;
		gbcProgressBarCPU.insets = new Insets(0, 0, 5, 0);
		gbcProgressBarCPU.gridx = 3;
		gbcProgressBarCPU.gridy = 5;
		panelDetail.add(getProgressBarCPU(), gbcProgressBarCPU);
	}
	
	private void _buildButtons() {
		btnRestart.setEnabled(false);
		GridBagConstraints gbcBtnRestart = new GridBagConstraints();
		gbcBtnRestart.insets = new Insets(0, 0, 0, 5);
		gbcBtnRestart.gridx = 0;
		gbcBtnRestart.gridy = 8;
		panelDetail.add(btnRestart, gbcBtnRestart);
		
		GridBagConstraints gbcBtnUpdate = new GridBagConstraints();
		gbcBtnUpdate.gridwidth = 2;
		gbcBtnUpdate.insets = new Insets(0, 0, 0, 5);
		gbcBtnUpdate.gridx = 1;
		gbcBtnUpdate.gridy = 8;
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ram.setText("Chargement...");
				cpu.setText("Chargement...");
				_updateRamCpu();
			}
		});
		panelDetail.add(btnUpdate, gbcBtnUpdate);
		
		JButton btnExit = new JButton("Fermer");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbcBtnExit = new GridBagConstraints();
		gbcBtnExit.gridx = 3;
		gbcBtnExit.gridy = 8;
		panelDetail.add(btnExit, gbcBtnExit);
	}
	
	private void _updateRamCpu() {
		new Thread(new RunSNMP(this, machine.getIp().toString(), "public", "1", 161)).start();
	}
	
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	public void setUpdated(String updated) {
		this.updated.setText(updated);
	}

	public JLabel getRam() {
		return ram;
	}

	public JProgressBar getProgressBarRAM() {
		return progressBarRAM;
	}

	public JLabel getCpu() {
		return cpu;
	}

	public JProgressBar getProgressBarCPU() {
		return progressBarCPU;
	}

	public JLabel getPourcentageRAM() {
		return pourcentageRAM;
	}
	
	public JLabel getPourcentageCPU() {
		return pourcentageCPU;
	}
}
