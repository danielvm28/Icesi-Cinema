package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IcesiCinema {
	// Attributes
	public static ObservableList<Film> filmData = FXCollections.observableArrayList();
	public static ArrayList<String> loginUserData = new ArrayList<>();
	
	// Methods
	public static boolean registerFilm (String name, LocalDate date, int duration, TheatreType theatreType) {
		return true;
	}
	
	public static boolean registerUserToFilm(TheatreType theatreType, String filmName, LocalDate filmDate, int filmDurationMinutes, String chairCode, String userName, String userID) {
		return true;
	}
	
	public static void saveFilmsJSON() {
		
	}
	
	public static void loadFilmsJSON() {
		
	}
	
	public static void loadLoginUsersJSON() {
		
	}
}
