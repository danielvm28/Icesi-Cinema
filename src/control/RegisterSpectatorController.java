package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exception.DoubledSpectatorException;
import exception.FullTheatreException;
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

public class RegisterSpectatorController implements Initializable {
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

		ArrayList<String> filmNamesAndTime = new ArrayList<>();

		for (Film f : IcesiCinema.filmData) {
			filmNamesAndTime.add(f.getName() + " -- " + f.getFormattedDate());
		}

		comboBoxSelectFilm.getItems().setAll(filmNamesAndTime);

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

		if (textLabelName.getText().trim().isEmpty()) {
			errorMessage += "- Please provide the name of the spectator.\n";
		}
		if (textLabelID.getText().trim().isEmpty()) {
			errorMessage += "- Please provide the ID of the spectator.\n";
		}
		if (comboBoxSelectFilm.getValue() == null) {
			errorMessage += "- Please specify the film to watch.\n";
		}

		if (errorMessage.length() != 0) {
			alert.setTitle("Warning");
			alert.setHeaderText("Incomplete fields");
			alert.setContentText(errorMessage);

			alert.show();
		} else {
			// If the fields are fine, find the selected film and launch the corresponding
			// window
			// TODO Puede haber error al introducir peliculas con el mismo nombre, revisar
			String[] filmInfo = comboBoxSelectFilm.getValue().split("--");
			String selectedTitle = filmInfo[0].trim();
			String selectedDate = filmInfo[1].trim();
			
			Film foundFilm = null;
			Theatre foundTheatre = null;

			for (Film film : IcesiCinema.filmData) {
				if (film.getName().equals(selectedTitle) && film.getFormattedDate().equals(selectedDate)) {
					foundFilm = film;
					foundTheatre = film.getTheatre();
					break;
				}
			}
			
			try {
				IcesiCinema.selectChairForSpectator(textLabelID.getText().trim(), foundFilm);
				
				// In the constructors of the controllers, pass the obtained information of
				// the spectator.
				// This will allow to register the spectator from there

				if (foundTheatre.getTheatreType() == TheatreType.MINI) {
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MiniTheatre.fxml"));
					loader.setController(
							new MiniTheatreController(textLabelName.getText().trim(), textLabelID.getText().trim(), foundFilm));
					Parent parent = (Parent) loader.load();
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
				} else {
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/NormalTheatre.fxml"));
					loader.setController(new NormalTheatreController(textLabelName.getText().trim(),
							textLabelID.getText().trim(), foundFilm));
					Parent parent = (Parent) loader.load();
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
				}

				// Close the current window
				Stage s = (Stage) backBTN.getScene().getWindow();
				s.close();
			} catch (DoubledSpectatorException e) {
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Warning");
				alert2.setHeaderText("This ID is already registered");
				alert2.setContentText(e.getMessage());

				alert2.show();
			} catch (FullTheatreException e) {
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Warning");
				alert2.setHeaderText("The theatre is full");
				alert2.setContentText(e.getMessage());

				alert2.show();
			}

			
		}
	}
}