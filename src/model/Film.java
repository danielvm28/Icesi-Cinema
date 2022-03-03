package model;

import java.time.LocalDate;

public class Film {
	//attribute
	private String name;
	private LocalDate date;
	private int durationMinutes;
	//relations
	private Theatre theatre;
	
	//methods
	public Film(String name, LocalDate date, int durationMinute, Theatre theatre) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
