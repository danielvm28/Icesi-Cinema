package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Film;

public class MiniTheatreController implements Initializable{

    @FXML
    private Button chair46BT;

    @FXML
    private Button chair45BT;

    @FXML
    private Button chair44BT;

    @FXML
    private Button chair43BT;

    @FXML
    private Button chair42BT;

    @FXML
    private Button chair41BT;

    @FXML
    private Button chair36BT;

    @FXML
    private Button chair35BT;

    @FXML
    private Button chair34BT;

    @FXML
    private Button chair33BT;

    @FXML
    private Button Chair32BT;

    @FXML
    private Button chair31BT;

    @FXML
    private Button chair26BT;

    @FXML
    private Button chair25BT;

    @FXML
    private Button chair24BT;

    @FXML
    private Button chair23BT;

    @FXML
    private Button chair22BT;

    @FXML
    private Button chair21BT;

    @FXML
    private Button chair16BT;

    @FXML
    private Button chair15BT;

    @FXML
    private Button chair14BT;

    @FXML
    private Button chair13BT;

    @FXML
    private Button chair12BT;

    @FXML
    private Button chair11BT;

    @FXML
    private Button chair47BT;

    @FXML
    private Button chair27BT;

    @FXML
    private Button chair37BT;

    @FXML
    private Button chair17BT;

    @FXML
    private Text spectatorNameTXT;

    @FXML
    private Text startTimeFilmTXT;

    @FXML
    private Text filmNameTXT;
    
    @FXML
    private Text spectatorIDTXT;
    
    @FXML
    private Button cancelBTN;

    @FXML
    private Button addBTN;
    
    private String spectatorName;
    
    private String spectatorId;
    
    private Film film;
    
    public MiniTheatreController(String spectatorName, String spectatorId, Film film) {
    	this.spectatorName = spectatorName;
    	this.spectatorId = spectatorId;
    	this.film = film;
    }

    // TODO Implementar metodos, a�adir usuarios pendiente
    @FXML
    void addSpectator(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void selectChair(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		spectatorNameTXT.setText(spectatorName);
		spectatorIDTXT.setText(spectatorId);
		filmNameTXT.setText(film.getName());
		startTimeFilmTXT.setText(film.getFormattedDate());
	}

}
