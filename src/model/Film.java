package model;

import java.time.LocalDateTime;

public class Film {
	//attribute
	private String name;
	private LocalDateTime date;
	private int durationMinutes;
	//relations
	private Theatre theatre;
	
	//methods
	public Film(String name, LocalDateTime date, int durationMinute, Theatre theatre) {
		this.name = name;
		this.date = date;
		this.durationMinutes = durationMinute;
		this.theatre = theatre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getDurationMinute() {
		return durationMinutes;
	}

	public void setDurationMinute(int durationMinute) {
		this.durationMinutes = durationMinute;
	}
	
	public Theatre getTheatre() {
		return theatre;
	}
}
