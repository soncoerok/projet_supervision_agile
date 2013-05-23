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
 * A JFrame that contains a JtextArea in which the output stream writes its output.
 */
//@Component
public class FrameOld extends JFrame {

	/**
	 * Only used to serialize or deserialize component.
	 */
	private static final long serialVersionUID = 1L;

	/** An instance of javax.swing.JPanel control. */
	JPanel jp_principal = new JPanel(new BorderLayout());
	
	/** An instance of javax.swing.JTextArea control. */
	JTextArea txtConsole = new JTextArea();
	
	/** An instance of javax.swing.JTextArea control. */
	JTextArea txtInput = new JTextArea();
	
	@Autowired
	private InputKeyListener inputKeyListener;
	
	/** The title of the frame. */
	@Value("${ihm.frame_title}")
	private String FRAME_TITLE;
	
	/** The width of the frame. */
	@Value("${ihm.frame_width}")
	private Integer FRAME_SIZE_WIDTH;
	
	/** The height of the frame. */
	@Value("${ihm.frame_height}")
	private Integer FRAME_SIZE_HEIGHT;
	
	
	/**
	 * Creates a new instance of Frame.
	 */
	public void initFrame() {
		createFrame();

		buildTextAreaOutputStream();
		
		buildPanelPrincipal();
		
		// Add the main panel to the frame
		this.setContentPane(jp_principal);
		
		this.setVisible(true);
	}
	
	/**
	 * Establishes the different parameters of our frame (size, title, ...).
	 */
	
	public void createFrame() {
		this.setTitle(FRAME_TITLE);
		this.setSize(FRAME_SIZE_WIDTH, FRAME_SIZE_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * Builds the TextAreaOutputStream by creating a new TextAreaOutputStream to write to our JTextArea control.
	 */
	public void buildTextAreaOutputStream() {
		// Wrap a PrintStream around it to support the println/printf methods
		PrintStream out = new PrintStream(new TextAreaOutputStream(txtConsole));

		// Redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);

		// Redirect standard error stream to the TextAreaOutputStream
		//System.setErr(out);
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
		ascenceur.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ascenceur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp_principal.add(ascenceur, BorderLayout.CENTER);
		
		txtInput.setSize(700, 100);
		txtInput.requestDefaultFocus();
		txtInput.addKeyListener(inputKeyListener);
		jp_principal.add(txtInput, BorderLayout.PAGE_END);
		
	}

	public InputKeyListener getInputKeyListener() {
		return inputKeyListener;
	}

	public void setInputKeyListener(InputKeyListener inputKeyListener) {
		this.inputKeyListener = inputKeyListener;
	}

	public String getFRAME_TITLE() {
		return FRAME_TITLE;
	}

	public void setFRAME_TITLE(String fRAME_TITLE) {
		FRAME_TITLE = fRAME_TITLE;
	}

	public int getFRAME_SIZE_WIDTH() {
		return FRAME_SIZE_WIDTH;
	}

	public void setFRAME_SIZE_WIDTH(int fRAME_SIZE_WIDTH) {
		FRAME_SIZE_WIDTH = fRAME_SIZE_WIDTH;
	}

	public int getFRAME_SIZE_HEIGHT() {
		return FRAME_SIZE_HEIGHT;
	}

	public void setFRAME_SIZE_HEIGHT(int fRAME_SIZE_HEIGHT) {
		FRAME_SIZE_HEIGHT = fRAME_SIZE_HEIGHT;
	}
	
}