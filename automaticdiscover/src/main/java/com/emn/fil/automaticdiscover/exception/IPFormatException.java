package com.emn.fil.automaticdiscover.exception;

public class IPFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1255584348029085943L;
	
	public IPFormatException(String message) {
		
		super("IPFormatException : " + message);
		
	}
	
}
