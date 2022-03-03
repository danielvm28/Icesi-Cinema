package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IcesiCinema {
	ObservableList<Film> filmData = FXCollections.observableArrayList();
	ArrayList<String> loginUserData = new ArrayList<>();
	
	public boolean registerFilm (String theatreCode, String name, LocalDate date, String duration) {
		return true;
	}
	
	public boolean registerUserToFilm(String theatreCode, String filmName, LocalDate filmDate, int filmDurationMinutes, String chairCode, String userName, String userID) {
		return true;
	}
	
	public ObservableList<Film> getFilmData(){
		return filmData;
	}
	
	public void setFilmData(ObservableList<Film> filmData) {
		this.filmData=filmData;
	}
	
	public ArrayList<String> getLoginUserData(){
		return loginUserData;
	}
	
	public void setLoginUserData(ArrayList<String> loginUserData) {
		this.loginUserData=loginUserData;
	}
	
	public void saveJSON() {
		
	}
	
	public void loadJSON() {
		
	}
	
	public boolean deleteFilm(Film film) {
		return true;
	}
}
