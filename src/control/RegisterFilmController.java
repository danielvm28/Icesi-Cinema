package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.Main;
import model.TheatreType;
import model.IcesiCinema;

public class RegisterFilmController implements Initializable{
	
	@FXML
    private Button registerBTN;

    @FXML
    private TextField hoursTF;

    @FXML
    private TextField minutesTF;

    @FXML
    private TextField titleTF;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<TheatreType> theatreTypeCB;
    
    @FXML
    private Button backBTN;
    
    private int tfCounter;

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
    void register(ActionEvent event) {
    	// Declaration of alert
    	Alert alert = new Alert(AlertType.WARNING);
    	String errorMessage = "";
    	
    	// Checks for incomplete fields
    	if(titleTF.getText().trim().isEmpty()) {
    		errorMessage += "- Please provide the title of the film.\n";
    	}
    	if(hoursTF.getText().trim().isEmpty() || minutesTF.getText().trim().isEmpty()) {
    		errorMessage += "- Please state the duration of the film.\n";
    	}
    	if(datePicker.getValue() == null) {
    		errorMessage += "- Please provide the date of the film.\n";
    	}
    	if(theatreTypeCB.getValue() == null) {
    		errorMessage += "- Please specify the type of theatre";
    	}
    	
    	// Shows the alert if there are incomplete fields
    	if(errorMessage.length() != 0) {
    		alert.setTitle("Warning");
    		alert.setHeaderText("Incomplete Fields");
    		alert.setContentText(errorMessage);
    		
    		alert.show();
    	} else {
    		// Tries to register a new film. In case the duration fields are wrong, show an alert.
    		try {
				int hours = Integer.parseInt(hoursTF.getText().trim());
				int totalMinutes = Integer.parseInt(minutesTF.getText().trim());
				totalMinutes += hours * 60; 
				
				IcesiCinema.registerFilm(titleTF.getText().trim(), datePicker.getValue(), totalMinutes, theatreTypeCB.getValue());
			} catch (NumberFormatException e) {
				alert.setTitle("Warning");
				alert.setHeaderText("Wrong format");
				alert.setContentText("Please provide the correct format for the duration");
				
				alert.show();
			}
    		
    		// TODO check conflicts and throw exception
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initializes the combo-box and sets the change of text field on the duration fields
		
		tfCounter = 0;
		
		theatreTypeCB.getItems().setAll(TheatreType.values());
		
		hoursTF.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(tfCounter != 0) {
					minutesTF.requestFocus();
				} else {
					tfCounter++;
				}
			}
    	});
	}

}
