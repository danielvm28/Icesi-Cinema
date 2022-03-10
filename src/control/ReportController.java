package control;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Film;
import model.IcesiCinema;
import model.Theatre;
import model.Spectator;

public class ReportController implements Initializable{
	@FXML
    private TableView<Film> filmTable;

    @FXML
    private TableColumn<Film, String> titleCol;

    @FXML
    private TableColumn<Film, String> dateCol;

    @FXML
    private TableColumn<Film, Theatre> theatreCol;

    @FXML
    private Button backBTN;

    @FXML
    private TableView<Spectator> spectatorsTable;
    
    @FXML
    private TableColumn<Spectator, String> nameCol;

    @FXML
    private TableColumn<Spectator, String> IDCol;
    
    @FXML
    private Button eliminateFilmBTN;
    
    private Film filmClicked;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize the tables 
		
		titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
		theatreCol.setCellValueFactory(new PropertyValueFactory<>("theatre"));
		
		filmTable.setItems(IcesiCinema.filmData);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		// Set event for click. Shows the spectators information on another table
		filmTable.setOnMouseClicked(event -> {
			filmClicked = filmTable.getSelectionModel().getSelectedItem();
			ObservableList<Spectator> spectatorsFromFilm = FXCollections.observableArrayList();
			
			if (filmClicked.getSpectators() != null) {
				for (Spectator spectator : filmClicked.getSpectators()) {
					if(spectator != null) spectatorsFromFilm.add(spectator);
				}
				
				spectatorsTable.setItems(spectatorsFromFilm);
			} else {
				spectatorsTable.setItems(null);
			}
		});
	}

    @FXML
    void goBack(ActionEvent event) throws IOException {
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
    void eliminateFilm(ActionEvent event) {
    	// Show an alert waiting for the user's confirmation of deletion
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Film Elimination");
    	alert.setContentText("Are you sure that you want to eliminate this film?");

    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == ButtonType.OK){
    		IcesiCinema.filmData.remove(filmClicked);
    		spectatorsTable.setItems(null);
    		
    		// Save data every time a user eliminates a film
    		IcesiCinema.saveFilmsJSON();
    	}
    }
}
