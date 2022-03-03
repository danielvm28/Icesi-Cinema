package control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import model.Film;
import model.TheatreType;
import model.User;

public class ReportController implements Initializable{
	@FXML
    private TableView<Film> filmTable;

    @FXML
    private TableColumn<Film, String> titleCol;

    @FXML
    private TableColumn<Film, LocalDate> dateCol;

    @FXML
    private TableColumn<Film, TheatreType> theatreCol;

    @FXML
    private Button backBTN;

    @FXML
    private TableView<User> usersTable;
    
    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> IDCol;

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
		
	}
}
