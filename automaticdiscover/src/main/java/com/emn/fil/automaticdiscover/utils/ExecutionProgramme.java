package com.emn.fil.automaticdiscover.utils;

import java.io.IOException;
import java.util.Scanner;

public class ExecutionProgramme {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void exec(String chemin) throws IOException {
		// Execution
		Process process = Runtime.getRuntime().exec(chemin);
		Scanner scanner = new Scanner(process.getInputStream());
		while(scanner.hasNext()){
			System.out.println(scanner.nextLine());
		}
		scanner.close();
		process.destroy();
	}

}
