package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import exception.FilmOverlappingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class MoviesTest {
	// State
	private ObservableList<Film> filmData;

	// Setup stages
	public void setupStage1() {
		filmData = IcesiCinema.filmData;
		filmData.clear();
	}
	
	
	// Tests
	@Test
	void addFilmTest() {
		String name = "Rapidos y furiosos";
		LocalDate date = LocalDate.of(2022, 02, 02);
		int duration = 120;
		TheatreType theatreType = TheatreType.MINI;
		int startHours = 12;
		int startMinutes = 30;
		
		assertDoesNotThrow(() -> {
			IcesiCinema.registerFilm(name, date, duration, theatreType, startHours, startMinutes);
		});
		
		assertEquals(1, IcesiCinema.filmData.size());
		
		
		try {
			FileInputStream fis;
			fis = new FileInputStream(new File("data/filmData.json"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			String json = "";
			String line;
			while ((line = reader.readLine()) != null) {
				json += line;
			}

			// from String to object
			Gson gson = new Gson();
			Film[] data = gson.fromJson(json, Film[].class);

			ObservableList<Film> gotFilmData = FXCollections.observableArrayList();
			
			for (Film f : data) {
				gotFilmData.add(f);
			}
			
			assertTrue(gotFilmData.equals(filmData));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
