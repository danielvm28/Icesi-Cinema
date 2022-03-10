package main;

import java.time.LocalDateTime;

import control.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Chair;
import model.Film;
import model.IcesiCinema;
import model.Spectator;
import model.Theatre;
import model.TheatreType;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Loads the existent films in JSON file every time the program starts
		
		IcesiCinema.loadFilmsJSON();
		
		// Prueba de adición de películas
		LocalDateTime now = LocalDateTime.now();
		Theatre the = new Theatre(TheatreType.MINI);
		
		IcesiCinema.filmData.add(new Film("si", now, 34, the));
		
		Film fi = IcesiCinema.filmData.get(1);
		Chair c1 = new Chair("A-1");
		Spectator s1 = new Spectator("no", "4", c1);
		Spectator[] sarr = new Spectator[1];
		
		sarr[0] = s1;
		
		fi.setSpectators(sarr);
		
		//IcesiCinema.saveFilmsJSON();
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/LoginWindow.fxml"));
		loader.setController(new LoginController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
