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
import exception.InvalidIDException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IcesiCinema implements Serializable{
	// Attributes
	public static ObservableList<Film> filmData = FXCollections.observableArrayList();
	public static ArrayList<String> loginUserData = new ArrayList<>();
	
	// Methods
	public static boolean registerFilm (String name, LocalDate date, int duration, TheatreType theatreType, int startHours, int startMinutes) throws FilmOverlappingException{
		// TODO Se debe lanzar la excepci�n FilmOverlappingException si se encuentran conflictos
		
		LocalDateTime dateTime = date.atTime(startHours, startMinutes);
		System.out.println(dateTime);
		
		// Save data every time a user registers a film
		IcesiCinema.saveFilmsJSON();
		return true;
	}
	
	public static boolean registerSpectatorToFilm(Film film, String chairCode, String spectatorName, String spectatorID) throws DoubledSpectatorException{
		// TODO Se deber lanzar la excepci�n DoubledSpectatorException si se encuentra un espectador repetido
		
		// Save data every time a user registers an spectator
		IcesiCinema.saveFilmsJSON();
		return true;
	}
	
	public static Chair searchChair(Film film, String chairCode) {
		// TODO falta implementar, devuelve un objeto silla para luego asociarla al usuario
		return null;
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
			while((line = reader.readLine()) != null) {
				json += line;
			}
			
			// from String to object
			Gson gson = new Gson();
			Film[] data = gson.fromJson(json, Film[].class);
			
			for(Film f: data) {
				filmData.add(f);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void login(String id) throws InvalidIDException{
		
		// Tries to login with a given ID. If it is not on the data file, throw an exception
		try {
			boolean foundID = false;
	    	String path = "data/loginData.txt";
			File file = new File(path);
			
			FileInputStream fis = new FileInputStream(file);
			
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			
			while ((line = br.readLine()) != null && !foundID) {
				
				if(line.equals(id)) {
					foundID = true;
				}
				
			}
			
			fis.close();
			
			if(!foundID) {
				throw new InvalidIDException();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
