package com.emn.fil.automaticdiscover.dto;

import java.io.FileWriter;
import java.io.IOException;
import com.emn.fil.automaticdiscover.ihm.ShowDialog;

public class ExportDataToCSV {
	
	public static void export(String fileName, Scan scan) {
		ShowDialog dialog = new ShowDialog();
		try {
			FileWriter fw = new FileWriter(fileName);
			for(Machine m : scan.getListeMachine()) {
				fw.append(m.getIp() + ";" + m.getOsType() + ";" 
						+ m.getHostname() + ";" + scan.getDateScan() + "\n");
			}
			fw.flush();
			fw.close();
			dialog.setMessage("Génération du fichier CSV terminé avec succès !\n>> " + fileName);
		} catch (IOException e) {
			dialog.setMessage("Le fichier CSV n'a pas pu être généré !\n" + e.getMessage());	
		}
		dialog.setVisible(true);
	}
}
