package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class MainGraphic implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button recBtn;
	
	@FXML
	private ToolBar toolBar;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*No op*/
	}
	
	@FXML
	private void searchEntrepreneur() {
		SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_ENTR, null));
	}
	
	@FXML
	private void searchSeeker() {
		SessionFacade.getSession().setCurrUserType(Users.SEEKER);
	
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
	}
	
	@FXML
	private void login() {
		if(recBtn.isArmed()) {
			SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
		}
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	public void openOnlineDoc() {
		/*
		 * Handle http request and html doc
		 */
	}
	
	@FXML
	private void closeApp(){
		System.exit(0);
	}
	
}
