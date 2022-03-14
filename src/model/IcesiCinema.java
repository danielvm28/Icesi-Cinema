package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.google.gson.Gson;
import exception.DoubledSpectatorException;
import exception.FilmOverlappingException;
import exception.FullTheatreException;
import exception.InvalidIDException;
import exception.NoFilmsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IcesiCinema implements Serializable {
	// Attributes
	public static ObservableList<Film> filmData = FXCollections.observableArrayList();
	public static ArrayList<String> loginUserData = new ArrayList<>();

	// Methods
	public static void registerFilm (String name, LocalDate date, int duration, TheatreType theatreType, int startHours, int startMinutes) throws FilmOverlappingException{
		LocalDateTime dateTime = date.atTime(startHours, startMinutes);
		boolean foundError = false;
		for (int i = 0; i<filmData.size(); i++) {
			
			if (filmData.get(i)!=null) {
				if (filmData.get(i).getTheatre().getTheatreType().equals(theatreType)) {
					LocalDate dateFilmMovie = filmData.get(i).getDate().toLocalDate();
					
					if (dateFilmMovie.equals(date)) {
						
						int startMinutesMovieFilmData = (filmData.get(i).getDate().getHour() * 60) + filmData.get(i).getDate().getMinute();
						int endMinutesMovieFilmData = startMinutesMovieFilmData + filmData.get(i).getDurationMinute();
						int startNewMovie = (startHours*60)+startMinutes;
						int endNewMovie = startNewMovie+duration;
						if (startNewMovie<startMinutesMovieFilmData && endNewMovie>endMinutesMovieFilmData) {
							foundError=true;
						}
						if ((startNewMovie >= startMinutesMovieFilmData && startNewMovie<endMinutesMovieFilmData) || (endNewMovie > startMinutesMovieFilmData && endNewMovie<endMinutesMovieFilmData)) {
							foundError=true;
						}
						
					}
				}
			}
		}
		if (foundError) {
			throw new FilmOverlappingException();
		}
		else {
			filmData.add(new Film(name, dateTime, duration, new Theatre(theatreType)));
		}
		// Save data every time a user registers a film
		IcesiCinema.saveFilmsJSON();
	}
	
	public static void registerSpectatorToFilm(Film film, Chair chair, String spectatorName, String spectatorID){
		for (int i = 0; i< filmData.size();i++) {

			if (filmData.get(i).equals(film)) {
				filmData.get(i).registerSpectator(new Spectator(spectatorName, spectatorID, chair));
			}
		}
		
		// Save data every time a user registers an spectator
		IcesiCinema.saveFilmsJSON();
	}
	
	public static void saveFilmsJSON() {
		// Saves the films in a JSON file

		try {
			Gson gson = new Gson();
			String json = gson.toJson(filmData);

			File file = new File("data/filmData.json");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(json.getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadFilmsJSON() {
		// Gets the films data from the saved JSON file

		try {
			FileInputStream fis = new FileInputStream(new File("data/filmData.json"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			String json = "";
			String line;
			while ((line = reader.readLine()) != null) {
				json += line;
			}

			// from String to object
			Gson gson = new Gson();
			Film[] data = gson.fromJson(json, Film[].class);

			for (Film f : data) {
				filmData.add(f);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void login(String id) throws InvalidIDException {

		// Tries to login with a given ID. If it is not on the data file, throw an
		// exception
		try {
			boolean foundID = false;
			String path = "data/loginData.txt";
			File file = new File(path);

			FileInputStream fis = new FileInputStream(file);

			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String line = null;

			while ((line = br.readLine()) != null && !foundID) {

				if (line.equals(id)) {
					foundID = true;
				}

			}

			fis.close();

			if (!foundID) {
				throw new InvalidIDException();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void registerSpectatorMainMenu() throws NoFilmsException {
		// Throws an exception if there are no films to register to
		if (filmData.isEmpty()) {
			throw new NoFilmsException();
		}
	}

	public static void selectChairForSpectator(String id, Film film) throws DoubledSpectatorException, FullTheatreException {
		boolean fullTheatre = true;
		boolean doubledSpectator = false;

		Spectator[] spectators = film.getSpectators();

		// Iterates over the spectators in search of matching IDs and to check if the theatre is full
		for (int i = 0; i < spectators.length; i++) {
			
			if (spectators[i] == null) {
				fullTheatre = false;
			} else if (spectators[i].getId().equals(id)) {
				doubledSpectator = true;
			}
		}

		// Throws the corresponding exceptions
		if (fullTheatre) {
			throw new FullTheatreException();
		}
		
		if (doubledSpectator) {
			throw new DoubledSpectatorException();
		}
	}
}


