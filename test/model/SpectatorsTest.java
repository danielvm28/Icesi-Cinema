package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import exception.DoubledSpectatorException;
import exception.NoFilmsException;
import javafx.collections.ObservableList;

class SpectatorsTest {
	
	// State
	private ObservableList<Film> filmData;
	
	// Setup Stages
	public void setupStage1() {
		filmData = IcesiCinema.filmData;
		filmData.clear();
	}
	
	public void setupStage2() {
		filmData = IcesiCinema.filmData;
		filmData.clear();
		filmData.add(new Film("Jackie Chan", LocalDateTime.of(2022, 3, 3, 7, 30) , 150, new Theatre(TheatreType.NORMAL)));
	}

	public void setupStage3() {
		filmData = IcesiCinema.filmData;
		filmData.clear();
		filmData.add(new Film("Jackie Chan", LocalDateTime.of(2022, 3, 3, 7, 30) , 150, new Theatre(TheatreType.NORMAL)));
		IcesiCinema.registerSpectatorToFilm(filmData.get(0), new Chair("D-2"), "Luis", "18376284");
	}
	
	public void setupStage4() {
		filmData = IcesiCinema.filmData;
		filmData.clear();
		filmData.add(new Film("Jackie Chan", LocalDateTime.of(2022, 3, 3, 7, 30) , 150, new Theatre(TheatreType.NORMAL)));
		IcesiCinema.registerSpectatorToFilm(filmData.get(0), new Chair("D-2"), "Luis", "18376284");
	}
	
	@Test
	void noFilmsTest() {
		setupStage1();
		assertThrows(NoFilmsException.class, () -> {
			IcesiCinema.registerSpectatorMainMenu();
		});
	}
	
	@Test
	void addSpectatorTest() {
		setupStage2();
		boolean foundId = false;
		
		String name = "Luis Botero";
		String id = "1005689512";
		String chairCode = "D-2";
		Chair chair = new Chair(chairCode);
		Spectator registeredSpectator = null;
		
		IcesiCinema.registerSpectatorToFilm(filmData.get(0), chair, name, id);
		
		Spectator[] spectators = filmData.get(0).getSpectators();
		
		for(Spectator spectator : spectators) {
			if(spectator != null && spectator.getId().equals(id)) {
				foundId = true;
				registeredSpectator = spectator;
				break;
			}
		}
		
		// Check if the user was found
		assertTrue(foundId);
		
		// Check if the chair is reserved
		assertTrue(registeredSpectator.getChair().isReserved());
	}
	
	@Test
	void doubledSpectatorTest() {
		setupStage3();
		
		assertThrows(DoubledSpectatorException.class, () -> {
			IcesiCinema.selectChairForSpectator("18376284", filmData.get(0));
		});
	}
	
	@Test
	void fillTheatreTest() {
		
	}

}