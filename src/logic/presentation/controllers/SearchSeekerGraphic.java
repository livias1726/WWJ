package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class SearchSeekerGraphic implements Initializable {
	
	@FXML
	private AnchorPane searchPane;
	
	@FXML
	private HBox fieldBox;
	
	@FXML
	private VBox box;
	
	@FXML
	private ComboBox<String> placeSearch;
	
	@FXML
	private ComboBox<String> jobSearch;
	
	@FXML
	private Button searchBtn;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/
	}
	
	@FXML
	public void search() {
		if (placeSearch.isArmed()) {
			if(jobSearch.isArmed()) {
				/*
				 * Search by immidiate business page
				 */
			}else {
				/*
				 * Search by Country
				 */
			}
		}else {
			/*
			 * Search by Business
			 */
		}
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)searchPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		/*
		 * Handle logout
		 */
	}
	
	@FXML
	public void buyPremium() {
		/*
		 * Handle payment and upgrade
		 */
	}
	
	@FXML
	public void openOnlineDoc() {
		/*
		 * Handle http request and html doc
		 */
	}
	
	@FXML
	public void closeApp() {
		System.exit(0);
	}
}
