package com.emn.fil.automaticdiscover;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;

public class ExportDataToCSV {
	
	public static void export(String fileName, List<Machine> list) {
		try {
			FileWriter fw = new FileWriter(fileName);
			Date today = new Date();
			for(Machine m : list) {
				fw.append(m.getIp() + ";" + m.getOsType() + ";" + m.getHostname() + ";" + today + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			ShowDialog dialog = new ShowDialog("Le fichier CSV n'a pas pu être généré ! \n" + e.getMessage());
			dialog.setVisible(true);
		}
	}
	
}
