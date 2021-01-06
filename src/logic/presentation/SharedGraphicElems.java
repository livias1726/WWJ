package logic.presentation;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;

public class SharedGraphicElems {
	
	@FXML
	protected AnchorPane pane;
	
	@FXML
	protected void openOnlineDoc(){
		/*
		 * Launch an html page with documentation
		 */
	}
	
	@FXML
	protected void buyPremium(){
		/*
		 * Redirect to payment check out
		 */
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	protected void logout(){
		SessionFacade.getSession().setID(null);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.MAIN, null));
	}
	
	@FXML
	protected void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	protected void closeApp() {
		System.exit(0);
	}

}
