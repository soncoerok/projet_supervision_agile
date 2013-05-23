package com.emn.fil.automaticdiscover.dto;

import com.emn.fil.automaticdiscover.dto.enums.OsType;

public class Machine {

	private IP ip;

	private OsType osType;

	public Machine(IP ip, OsType os) {
		this.ip = ip;
		this.osType = os;
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public OsType getOsType() {
		return osType;
	}

	public void setOsType(OsType osType) {
		this.osType = osType;
	}

}
