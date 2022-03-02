package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class LoginController {
	 	@FXML
	    private Button loginBTN;

	    @FXML
	    private TextField idTF;

	    @FXML
	    void login(ActionEvent event) throws IOException {
	    	// Falta Conjugar con lista serializable
	    	
	    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainMenu.fxml"));
	    	loader.setController(new MainMenuController());
	    	Parent parent = (Parent) loader.load();
	    	Scene scene = new Scene(parent);
	    	Stage stage = new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
	    	Stage s = (Stage) loginBTN.getScene().getWindow();
	    	s.close();
	    }
}
