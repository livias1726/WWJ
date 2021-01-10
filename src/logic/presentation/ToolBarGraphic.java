package logic.presentation;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.persistence.ConnectionManager;

public class ToolBarGraphic {

	@FXML
	protected AnchorPane pane;
	
	@FXML
	protected Button homeBtn;

    @FXML
    protected Button backBtn;

    @FXML
    protected MenuButton notifyBtn;
    
    @FXML
    protected MenuItem outBtn;

    @FXML
    protected MenuItem inBtn;

    @FXML
    protected MenuItem premiumBtn;
	
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
	protected void closeApp() {
		try {
			ConnectionManager.closeConnection();
		} catch (SQLException e) {
			//Log exception
		}
		System.exit(0);
	}

}
