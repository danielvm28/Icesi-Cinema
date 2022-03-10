package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Film {
	public static final int MAX_SPECTATORS = 42;
	
	//attribute
	private String name;
	private LocalDateTime date;
	private int durationMinutes;
	private String formattedDate;
	
	//relations
	private Theatre theatre;
	private Spectator[] spectators;
	
	//methods
	public Film(String name, LocalDateTime date, int durationMinute, Theatre theatre) {
		this.name = name;
		this.date = date;
		this.durationMinutes = durationMinute;
		this.theatre = theatre;
		spectators = new Spectator[MAX_SPECTATORS];
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		formattedDate = date.format(formatter);
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

	public Spectator[] getSpectators() {
		return spectators;
	}

	public void setSpectators(Spectator[] spectators) {
		this.spectators = spectators;
	}
	
	public void registerSpectator(Spectator spectator, Chair chair) {
		
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
}
