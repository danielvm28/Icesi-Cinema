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
		if(theatreType == TheatreType.MINI) {
			return "Mini Theatre";
		} else {
			return "Normal Theatre";
		}
	}

}
