package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class MainGraphic implements Initializable {

	@FXML
	private AnchorPane pane;
	
	public MainGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/
	}
	
	@FXML
	private void searchEntrepreneur() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.SEARCH_ENTR, null));
	}
	
	@FXML
	private void searchUnemployed() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.SEARCH_USER, null));
	}
	
	@FXML
	private void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
	@FXML
	private void closeApp(){
		System.exit(0);
	}
	
}
