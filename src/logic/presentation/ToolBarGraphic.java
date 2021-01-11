package logic.presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.persistence.ConnectionManager;
import logic.presentation.control.SeekerAccountGraphic;

public class ToolBarGraphic implements Initializable{

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
    protected MenuItem profBtn;
    
    @FXML
    protected MenuItem pubBtn;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	if(SessionFacade.getSession().getID() == null) {
			premiumBtn.setVisible(false);
			outBtn.setVisible(false);
			notifyBtn.setVisible(false);
			profBtn.setVisible(false);
		}else {
			inBtn.setVisible(false);
			if(SessionFacade.getSession().getCurrUserType() != Users.RECRUITER) {
				pubBtn.setVisible(false);
			}
		}
    }

    @FXML
	protected void login(){
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
    protected void goToHome() {
		Stage stage = (Stage)pane.getScene().getWindow();	
		if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
			
		}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
			
		}else {
			stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_ENTR, null));
		}
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
    protected void openNewOffer() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.PUBLISH_OFFER, null));
    }
	
	@FXML
	protected void goToProfile() {
		Stage stage = (Stage)pane.getScene().getWindow();	
		if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, new SeekerAccountGraphic(SessionFacade.getSession().getID())));
			
		}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
			
		}else {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_ENTR, null));
		}
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
