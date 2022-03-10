package model;

public class Chair {
	//attributes
	private boolean reserved;
	private String chairCode;
	
	//methods
	public Chair(String chairCode) {
		this.reserved=false;
		this.chairCode = chairCode;
	}
	
	public boolean isReserved() {
		return reserved;
	}
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	public String getChairCode() {
		return chairCode;
	}
	public void setChairCode(String chairCode) {
		this.chairCode = chairCode;
	}
}
