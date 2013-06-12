package com.emn.fil.automaticdiscover.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.emn.fil.automaticdiscover.Main;
import com.emn.fil.automaticdiscover.dto.Machine;
import com.emn.fil.automaticdiscover.dto.enums.OsType;

@Component("reseau")
public class Reseau {

	private List<Machine> listMachine;

	public Reseau() {
		listMachine = new ArrayList<Machine>();
	}

	public void ajoutMachine(Machine machine) {
		Main.log.trace("Ajout de la machine : " + machine.getIp() + " (" + machine.getOsType() + ")");
		if (! listMachine.contains(machine)){
			listMachine.add(machine);
		}
	}

	public String getResume() {
		if (this.listMachine.size() > 0) {
			return "Nombre total de machines détéctées : "
					+ this.listMachine.size() + " dont :\r\n" + "\tWINDOWS : "
					+ this.getListMachinesWINDOWS().size() + "\r\n"
					+ "\tOSX : " + this.getListMachinesOSX().size() + "\r\n"
					+ "\tUNIX : " + this.getListMachinesUNIX().size() + "\r\n";
		}
		return "Aucune machine détectée";
	}

	public List<Machine> getListMachinesOSX() {
		List<Machine> res = new ArrayList<Machine>();
		for (Machine m : this.listMachine) {
			if (m.getOsType() == OsType.OSX){
				res.add(m);
			}
		}
		return res;
	}

	public List<Machine> getListMachinesWINDOWS() {
		List<Machine> res = new ArrayList<Machine>();
		for (Machine m : this.listMachine) {
			if (m.getOsType() == OsType.WINDOWS){
				res.add(m);
			}
		}
		return res;
	}

	public List<Machine> getListMachinesUNIX() {
		List<Machine> res = new ArrayList<Machine>();
		for (Machine m : this.listMachine) {
			if (m.getOsType() == OsType.UNIX){
				res.add(m);
			}
		}
		return res;
	}
}
