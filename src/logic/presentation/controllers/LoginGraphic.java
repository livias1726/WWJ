package logic.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import logic.application.SessionFacade;
import logic.bean.LoginBean;
import logic.presentation.GraphicHandler;

public class LoginGraphic implements Initializable {
	@FXML
	AnchorPane logPane;

	@FXML
	TextField usr;
	
	@FXML
    PasswordField pwd;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Default behavior*/

	}
		
	@FXML
	public void verifyLogin() {
		LoginBean credentials = new LoginBean(usr.getText(), pwd.getText());
		
		try {
			if(!credentials.verify()) {
				GraphicHandler.popUpMsg(AlertType.ERROR, "Please, fill out every field");
			}
		} catch (FailedLoginException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}

		SessionFacade.getSession().setCurrUser(credentials.getUsername());	

		/*Stage stage = (Stage)logPane.getScene().getWindow();
		
		if(SessionFacade.getSession().getCurrUser().equals("recruiter")) {
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_REC, null));
			
		}else if(SessionFacade.getSession().getCurrUser().equals("unemployed")) {
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_UNEM, null));
			
		}else{
			stage.setScene(GraphicHandler.switchScreen(Screens.ACC_ENTR, null));
		}*/
		
	}

	@FXML
	public void facebookLogin() throws IOException {}
	
	@FXML
	public void googleLogin() throws IOException {}
	
	@FXML
	public void createAccount() throws IOException {}
}
