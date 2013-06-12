package com.emn.fil.automaticdiscover.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Scan {

	private List<Machine> listeMachine = new ArrayList<Machine>();
	private String dateScan = "";
	
	public Scan() {}
	
	public Scan(List<Machine> listeMachine, String dateScan) {
		this.listeMachine = listeMachine;
		this.dateScan = dateScan;
	}
	
	public List<Machine> getListeMachine() {
		return listeMachine;
	}
	public String getDateScan() {
		return dateScan;
	}
	public void setListeMachine(List<Machine> listeMachine) {
		this.listeMachine = listeMachine;
	}
	public void setDateScan(String dateScan) {
		this.dateScan = dateScan;
	}

	@Override
	public String toString() {
		return "Scan [listeMachine=" + listeMachine + ", dateScan=" + dateScan + "]";
	}
}
