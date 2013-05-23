package com.emn.fil.automaticdiscover.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * A JFrame that contains a JtextArea in which the output stream writes its
 * output.
 */
// @Component
public class FrameOld extends JFrame {

	/**
	 * Only used to serialize or deserialize component.
	 */
	private static final long serialVersionUID = 1L;

	/** An instance of javax.swing.JPanel control. */
	private JPanel jpPrincipal = new JPanel(new BorderLayout());

	/** An instance of javax.swing.JTextArea control. */
	private JTextArea txtConsole = new JTextArea();

	/** An instance of javax.swing.JTextArea control. */
	private JTextArea txtInput = new JTextArea();

	@Autowired
	private InputKeyListener inputKeyListener;

	/** The title of the frame. */
	@Value("${ihm.frame_title}")
	private String frameTitle;

	/** The width of the frame. */
	@Value("${ihm.frame_width}")
	private Integer frameSizeWidth;

	/** The height of the frame. */
	@Value("${ihm.frame_height}")
	private Integer frameSizeHeight;

	/**
	 * Creates a new instance of Frame.
	 */
	public void initFrame() {
		createFrame();

		buildTextAreaOutputStream();

		buildPanelPrincipal();

		// Add the main panel to the frame
		this.setContentPane(jpPrincipal);

		this.setVisible(true);
	}

	/**
	 * Establishes the different parameters of our frame (size, title, ...).
	 */

	public void createFrame() {
		this.setTitle(frameTitle);
		this.setSize(frameSizeWidth, frameSizeHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * Builds the TextAreaOutputStream by creating a new TextAreaOutputStream to
	 * write to our JTextArea control.
	 */
	public void buildTextAreaOutputStream() {
		// Wrap a PrintStream around it to support the println/printf methods
		PrintStream out = new PrintStream(new TextAreaOutputStream(txtConsole));

		// Redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);

		// Redirect standard error stream to the TextAreaOutputStream
		// System.setErr(out);
	}

	/**
	 * Builds the main panel containing the JTextArea.
	 */
	@SuppressWarnings("deprecation")
	public void buildPanelPrincipal() {

		txtConsole.setSize(700, 400);
		txtConsole.setBackground(Color.LIGHT_GRAY);
		txtConsole.disable();
		JScrollPane ascenceur = new JScrollPane(txtConsole);
		ascenceur
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ascenceur
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jpPrincipal.add(ascenceur, BorderLayout.CENTER);

		txtInput.setSize(700, 100);
		txtInput.requestDefaultFocus();
		txtInput.addKeyListener(inputKeyListener);
		jpPrincipal.add(txtInput, BorderLayout.PAGE_END);

	}

	public InputKeyListener getInputKeyListener() {
		return inputKeyListener;
	}

	public void setInputKeyListener(InputKeyListener inputKeyListener) {
		this.inputKeyListener = inputKeyListener;
	}

	public String getFrameTitle() {
		return frameTitle;
	}

	public void setFrameTitle(String title) {
		frameTitle = title;
	}

	public int getFrameSizeWidth() {
		return frameSizeWidth;
	}

	public void setFrameSizeWidth(int size) {
		frameSizeWidth = size;
	}

	public int getFrameSizeHeight() {
		return frameSizeHeight;
	}

	public void setFrameSizeHeight(int size) {
		frameSizeHeight = size;
	}
}