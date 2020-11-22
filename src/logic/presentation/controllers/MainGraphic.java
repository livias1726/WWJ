package logic.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.presentation.GraphicSwitch;
import logic.presentation.Screens;

public class MainGraphic implements Initializable {

	@FXML
	private BorderPane pane;
	
	public MainGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*TODO*/
	}
	
	@FXML
	private void searchEntrepreneur() throws IOException {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicSwitch.switchScreen(Screens.SEARCH_ENTR, null));
	}
	
	@FXML
	private void searchUnemployed() throws IOException {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicSwitch.switchScreen(Screens.SEARCH_USER, null));
	}
	
	@FXML
	private void login() throws IOException {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicSwitch.switchScreen(Screens.LOGIN, null));
	}
	
}
