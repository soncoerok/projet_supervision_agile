package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import org.springframework.beans.factory.annotation.Autowired;
import com.emn.fil.automaticdiscover.ihm.listeners.BtnQuitListener;

public class ShowDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMessage = new JLabel("Message to display");

	@Autowired
	private BtnQuitListener btnQuitListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowDialog frame = new ShowDialog();
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
	public ShowDialog() {
		initDialog(lblMessage.getText());
	}
	
	/**
	 * Create the frame.
	 * @param msg to display
	 */
	public ShowDialog(String msg) {
		initDialog(msg);
	}
	
	/**
	 * Init the dialog.
	 * @param msg to display
	 */
	private void initDialog(String msg) {
		setTitle("Automatic Discover Beta - Message");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblMessage.setText(msg);
		contentPane.add(lblMessage, BorderLayout.CENTER);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(btnQuitListener);
		contentPane.add(btnQuit, BorderLayout.SOUTH);
	}
	
	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
}
