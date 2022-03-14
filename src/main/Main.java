package main;

import control.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.IcesiCinema;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Loads the existent films in JSON file every time the program starts
		IcesiCinema.loadFilmsJSON();
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/LoginWindow.fxml"));
		loader.setController(new LoginController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
