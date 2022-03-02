package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private ComboBox<?> theatreTypeCB;

    @FXML
    void register(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
