package com.emn.fil.automaticdiscover.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	public boolean validIP(String argIp) {
		Pattern pattern = Pattern
				.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
						+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
						+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
						+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		Matcher matcher = pattern.matcher(argIp);
		return matcher.matches();
	}

}
