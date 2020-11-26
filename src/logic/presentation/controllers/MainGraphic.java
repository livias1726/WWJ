package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class MainGraphic implements Initializable {

	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button recBtn;
	
	public MainGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/
	}
	
	@FXML
	private void searchEntrepreneur() {
		SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.SEARCH_ENTR, null));
	}
	
	@FXML
	private void searchUnemployed() {
		SessionFacade.getSession().setCurrUserType(Users.UNEMPLOYED);
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.SEARCH_USER, null));
	}
	
	@FXML
	private void login() {
		if(recBtn.isArmed()) {
			SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
		}
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
	@FXML
	private void closeApp(){
		System.exit(0);
	}
	
}
