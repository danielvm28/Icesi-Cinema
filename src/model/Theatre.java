package model;

public class Theatre {
	//atributes
	private String theatreCode;
	
	//relations
	private TheatreType theatreType;
	
	//methods
	public Theatre(String theatreCode, TheatreType theatreType) {
		this.theatreCode = theatreCode;
		this.theatreType = theatreType;
	}

	public String getTheatreCode() {
		return theatreCode;
	}

	public void setTheatreCode(String theatreCode) {
		this.theatreCode = theatreCode;
	}

	public TheatreType getTheatreType() {
		return theatreType;
	}

	public void setTheatreType(TheatreType theatreType) {
		this.theatreType = theatreType;
	}

}
