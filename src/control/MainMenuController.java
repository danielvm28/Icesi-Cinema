package control;

import java.io.IOException;

import exception.NoFilmsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.Main;
import model.IcesiCinema;

public class MainMenuController {
	@FXML
    private Button registerFilmBTN;

    @FXML
    private Button registerSpectatorBTN;

    @FXML
    private Button reportBTN;

    @FXML
    private Button logOutBTN;

    @FXML
    void registerFilm(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/RegisterFilm.fxml"));
    	loader.setController(new RegisterFilmController());
    	Parent parent = (Parent) loader.load();
    	Stage stage = new Stage();
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    	
    	Stage s = (Stage) logOutBTN.getScene().getWindow();
    	s.close();
    }

    @FXML
    void generateReport(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Report.fxml"));
    	loader.setController(new ReportController());
    	Parent parent = (Parent) loader.load();
    	Stage stage = new Stage();
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    	
    	Stage s = (Stage) logOutBTN.getScene().getWindow();
    	s.close();
    }

    @FXML
    void registerSpectator(ActionEvent event) throws IOException {
    	
    	try {
			IcesiCinema.registerSpectatorMainMenu();
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/RegisterSpectator.fxml"));
        	loader.setController(new RegisterSpectatorController());
        	Parent parent = (Parent) loader.load();
        	Stage stage = new Stage();
        	Scene scene = new Scene(parent);
        	stage.setScene(scene);
        	stage.show();
        	
        	Stage s = (Stage) logOutBTN.getScene().getWindow();
        	s.close();
        	
		} catch (NoFilmsException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Attention");
    		alert.setHeaderText("No films yet");
    		alert.setContentText(e.getMessage());
    		
    		alert.show();
		}
    }
    
    @FXML
    void logOut(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/LoginWindow.fxml"));
    	loader.setController(new LoginController());
    	Parent parent = (Parent) loader.load();
    	Stage stage = new Stage();
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    	
    	Stage s = (Stage) logOutBTN.getScene().getWindow();
    	s.close();
    }
}
