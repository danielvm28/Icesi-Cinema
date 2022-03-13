package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Film {
	public static final int MAX_SPECTATORS = 42;
	
	//attribute
	private String name;
	private LocalDateTime date;
	private int durationMinutes;
	private String formattedDate;
	@FXML
    private GridPane gridPane;

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
	
	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
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
	
	public void registerSpectator(Spectator spectator) {
		for (int i = 0; i<spectators.length; i++) {
			if (spectators[i]==null) {
				spectators[i]=spectator;
				spectators[i].setReservedChair(spectators[i].getChair());
				break;
			}
		}
	}
	
	public GridPane gridPaneButtons (GridPane inUse) {
		for (int i = 0; i<spectators.length;i++) {
			for (Node node: inUse.getChildren()) {
				if (spectators[i]!=null) {
					if (node.getAccessibleText().equals(spectators[i].getChair().getChairCode())) {
						node.setStyle("-fx-background-color:  #b3cbdd");
						node.disabledProperty();
					}
				}
			}
		}
		
		return inUse;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
}
