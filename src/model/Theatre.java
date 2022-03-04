package model;

public class Theatre {
	//relations
	private TheatreType theatreType;
	private Chair[][] chairs;
	
	//methods
	public Theatre(TheatreType theatreType) {
		this.theatreType = theatreType;
	}

	public TheatreType getTheatreType() {
		return theatreType;
	}

	public void setTheatreType(TheatreType theatreType) {
		this.theatreType = theatreType;
	}

	public Chair[][] getChairs() {
		return chairs;
	}

	public void setChairs(TheatreType theatreType) {
		
	}
	
	@Override
	public String toString() {
		return theatreType + " ROOM";
	}

}
