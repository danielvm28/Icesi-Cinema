package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import exception.FilmOverlappingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IcesiCinema {
	// Attributes
	public static ObservableList<Film> filmData = FXCollections.observableArrayList();
	public static ArrayList<String> loginUserData = new ArrayList<>();
	
	// Methods
	public static boolean registerFilm (String name, LocalDate date, int duration, TheatreType theatreType, int startHours, int startMinutes) throws FilmOverlappingException{
		// TODO Se debe lanzar la excepcion FilmOverlapping expception si se encuentran conflictos
		
		LocalDateTime dateTime = date.atTime(startHours, startMinutes);
		System.out.println(dateTime);
		return true;
	}
	
	public static boolean registerUserToFilm(Film film, String chairCode, String userName, String userID) {
		return true;
	}
	
	public static void saveFilmsJSON() {
		
	}
	
	public static void loadFilmsJSON() {
		
	}
	
	public static void loadLoginUsersJSON() {
		
	}
}
