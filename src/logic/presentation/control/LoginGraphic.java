package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.application.control.LoginControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;

public class LoginGraphic implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField pwd;
	
	@FXML
	private Button backBtn;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/
	}
		
	@FXML
	public void signInClicked() {
		UserBean credentials = new UserBean();
		credentials.setEmail(email.getText());
		credentials.setPassword(pwd.getText());
		try {
			credentials.verifyFields(email.getText(), pwd.getText());		
		} catch (InvalidFieldException ie) {
			GraphicHandler.popUpMsg(AlertType.ERROR, "Please, fill out every field");
			refresh();
		}
		
		AccountBean account = new AccountBean();
		account.setUser(credentials);
		
		try {
			LoginControl.getInstance().tryLogin(account);
		} catch (FailedLoginException fe) {
			GraphicHandler.popUpMsg(AlertType.ERROR, fe.getMessage());
			refresh();
		} catch(DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
		}
		
		Stage stage = (Stage)pane.getScene().getWindow();	
		if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, new SeekerAccountGraphic(SessionFacade.getSession().getID())));
			
		}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
			
		}else {
			stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_ENTR, null));
		}
	}
	
	private void refresh() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}

	@FXML
	public void facebookLoginClicked() {
		GraphicHandler.popUpMsg(AlertType.ERROR, "Sorry, this function is not available yet");
	}
	
	@FXML
	public void googleLogin() {
		GraphicHandler.popUpMsg(AlertType.ERROR, "Sorry, this function is not available yet");
	}
	
	@FXML
	public void displaySignUp(){
		Stage stage = (Stage)pane.getScene().getWindow();		
		stage.setScene(GraphicHandler.switchScreen(Scenes.SIGN_UP, null));
	}
	
	@FXML
	public void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}

}
