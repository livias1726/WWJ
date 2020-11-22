package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchUnemployedGraphic implements Initializable {
	
	@FXML
	BorderPane searchPane;
	
	@FXML
	HBox fieldBox;
	
	@FXML
	VBox box;
	
	@FXML
	TextField placeSearch;
	
	@FXML
	TextField jobSearch;
	
	@FXML
	Button searchBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void search() {
		
	}

}
