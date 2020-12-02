package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.UserBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class LoginGraphic implements Initializable {
	@FXML
	private AnchorPane logPane;

	@FXML
	private TextField email;
	
	@FXML
	private PasswordField pwd;
	
	@FXML
	private Button backBtn;
	
	public LoginGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/
	}
		
	@FXML
	public void displaySignIn() {
		UserBean credentials = new UserBean(email.getText(), pwd.getText());
		
		try {
			if(!credentials.verifyLogin()) {
				GraphicHandler.popUpMsg(AlertType.ERROR, "Please, fill out every field");
			}
		} catch (FailedLoginException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}	
		
		Stage stage = (Stage)logPane.getScene().getWindow();
	
		if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_SEEK, null));
			
		}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_REC, null));
			
		}else {
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_ENTR, null));
		}
	}

	@FXML
	public void facebookLogin() {
		/*Connect to Facebook using the specific API
		 * and OAuth2 system
		 */
	}
	
	@FXML
	public void googleLogin() {
		/*Connect to Google using the specific API
		 * and OAuth2 system
		 */
	}
	
	@FXML
	public void displaySignUp(){
		Stage stage = (Stage)logPane.getScene().getWindow();		
		stage.setScene(GraphicHandler.switchScreen(Screens.SIGN_UP, null));
	}
	
	@FXML
	public void goBack(){
		Screens prev = SessionFacade.getSession().getPrevScreen();		
		Stage stage = (Stage)logPane.getScene().getWindow();	
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
