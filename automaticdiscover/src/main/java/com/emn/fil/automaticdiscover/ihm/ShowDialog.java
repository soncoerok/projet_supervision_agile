package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import org.springframework.stereotype.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

@Component
public class ShowDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextPane message = new JTextPane();;

	/**
	 * Create the frame.
	 */
	public ShowDialog() {
		initDialog("");
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
		setTitle("Message");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setMessage(msg);
		message.setEditable(false);
		contentPane.add(message, BorderLayout.CENTER);
		
		JButton btnQuit = new JButton("Ok");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnQuit, BorderLayout.SOUTH);
	}
	
	public String getMessage() {
		return message.getText();
	}
	
	public void setMessage(String msg) {
		message.setText(msg);
	}
}
