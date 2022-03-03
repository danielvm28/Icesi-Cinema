package model;

public class Theatre {
	//relations
	private TheatreType theatreType;
	
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

}
