package logic.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.LoginControl;
import logic.application.SessionFacade;
import logic.presentation.GraphicSwitch;
import logic.presentation.Screens;

public class LoginGraphic implements Initializable {
	@FXML
	BorderPane logPane;
	
	@FXML
	VBox fields;
	
	@FXML
	TextField usr;
	
	@FXML
    PasswordField pwd;
	
	@FXML
    Button loginBtn;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		// TODO Auto-generated method stub

	}
		
	@FXML
	public void login() throws IOException {
		String username = usr.getText();
		String password = pwd.getText();
		
		LoginControl controller = new LoginControl();
		
		if(controller.verifyLogin(username, password).equals(false)) {
			/*LOGIN REJECTION*/
		}
		
		/*INTERACTION WITH USER ENTITY -> BEAN INTERMEDIATE*/

		SessionFacade.getSession().setCurrUser(username);
		
		Stage stage = (Stage)logPane.getScene().getWindow();
		
		/*CHOOSE USER TYPE*/
		
		stage.setScene(GraphicSwitch.switchScreen(Screens.ACC_REC, null));
	}

}
