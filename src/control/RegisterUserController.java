package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Film;
import model.IcesiCinema;
import model.Theatre;
import model.TheatreType;

public class RegisterUserController implements Initializable{
	@FXML
    private TextField textLabelName;

    @FXML
    private TextField textLabelID;

    @FXML
    private ComboBox<String> comboBoxSelectFilm;

    @FXML
    private Button buttonSelectChair;

    @FXML
    private Button backBTN;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initializes the combo-box with the film names
		
		ArrayList<String> filmNames = new ArrayList<>();
		
		for (Film f : IcesiCinema.filmData) {
			filmNames.add(f.getName());
		}
		
		comboBoxSelectFilm.getItems().setAll(filmNames);
		
	}

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	// Closes the actual window and opens the main menu
    	
    	Stage s = (Stage) backBTN.getScene().getWindow();
    	s.close();
    	
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainMenu.fxml"));
    	loader.setController(new MainMenuController());
    	Parent parent = (Parent) loader.load();
    	Stage stage = new Stage();
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void selectChair(ActionEvent event) throws IOException {
    	// Declares an alert in case it encounters incomplete fields
    	Alert alert = new Alert(AlertType.WARNING);
    	String errorMessage = "";
    	
    	if(textLabelName.getText().trim().isEmpty()) {
    		errorMessage += "- Please provide the name of the user.\n";
    	}
    	if(textLabelID.getText().trim().isEmpty()) {
    		errorMessage += "- Please provide the ID of the user.\n";
    	}
    	if(comboBoxSelectFilm.getValue() == null) {
    		errorMessage += "- Please specify the film to watch.\n";
    	}
    	
    	if(errorMessage.length() != 0) {
    		alert.setTitle("Warning");
    		alert.setHeaderText("Incomplete fields");
    		alert.setContentText(errorMessage);
    		
    		alert.show();
    	} else {
    		// If the fields are fine, find the selected film and launch the corresponding window
    		String selectedTitle = comboBoxSelectFilm.getValue();
    		
    		for (Film f : IcesiCinema.filmData) {
				if(f.getName().equals(selectedTitle)) {
					Theatre foundTheatre = f.getTheatre();
					
					// TODO In the constructors of the controllers, pass the obtained information of the user.
					// This will allow to register the user from there
					
					if(foundTheatre.getTheatreType() == TheatreType.MINI) {
						FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MiniTheatre.fxml"));
				    	loader.setController(new MiniTheatreController());
				    	Parent parent = (Parent) loader.load();
				    	Stage stage = new Stage();
				    	Scene scene = new Scene(parent);
				    	stage.setScene(scene);
				    	stage.show();
					} else {
						FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/NormalTheatre.fxml"));
				    	loader.setController(new NormalTheatreController());
				    	Parent parent = (Parent) loader.load();
				    	Stage stage = new Stage();
				    	Scene scene = new Scene(parent);
				    	stage.setScene(scene);
				    	stage.show();
					}
					
					// Close the current window
					Stage s = (Stage) backBTN.getScene().getWindow();
			    	s.close();
				}
			}
    	}
    	
    }

}


