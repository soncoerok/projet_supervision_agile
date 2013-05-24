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

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (osType != other.osType)
			return false;
		return true;
	}
	
	public String toString() {
		return "Machine [osType=" + osType + ", ip=" + ip + "]";
	}
}
