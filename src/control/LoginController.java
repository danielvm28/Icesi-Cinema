package control;

import java.io.IOException;
import exception.InvalidIDException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.Main;
import model.IcesiCinema;

public class LoginController {
	 	@FXML
	    private Button loginBTN;

	    @FXML
	    private TextField idTF;

	    @FXML
	    void login(ActionEvent event) throws IOException {
	    	
	    	// Tries to login executing the login method from IcesiCinema
	    	try {
				IcesiCinema.login(idTF.getText());
				
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainMenu.fxml"));
		    	loader.setController(new MainMenuController());
		    	Parent parent = (Parent) loader.load();
		    	Scene scene = new Scene(parent);
		    	Stage stage = new Stage();
		    	stage.setScene(scene);
		    	stage.show();
		    	
		    	Stage s = (Stage) loginBTN.getScene().getWindow();
		    	s.close();
				
			} catch (InvalidIDException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Wrong ID");
				alert.setContentText(e.getMessage());
				
				idTF.clear();
				
				alert.show();
			}
	    	
	    	
	    }
}
