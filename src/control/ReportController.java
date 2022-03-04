package control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Film;
import model.IcesiCinema;
import model.Theatre;
import model.User;

public class ReportController implements Initializable{
	@FXML
    private TableView<Film> filmTable;

    @FXML
    private TableColumn<Film, String> titleCol;

    @FXML
    private TableColumn<Film, LocalDateTime> dateCol;

    @FXML
    private TableColumn<Film, Theatre> theatreCol;

    @FXML
    private Button backBTN;

    @FXML
    private TableView<User> usersTable;
    
    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> IDCol;
    
    private Film filmClicked;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize the tables 
		
		titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		theatreCol.setCellValueFactory(new PropertyValueFactory<>("theatre"));
		
		filmTable.setItems(IcesiCinema.filmData);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		// Set event for click. Shows the spectators information on another table
		filmTable.setOnMouseClicked(event -> {
			filmClicked = filmTable.getSelectionModel().getSelectedItem();
			ObservableList<User> usersFromFilm = FXCollections.observableArrayList();
			
			for (User user : filmClicked.getUsers()) {
				usersFromFilm.add(user);
			}
			
			usersTable.setItems(usersFromFilm);
		});
	}
}
