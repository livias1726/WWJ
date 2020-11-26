package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.UserBean;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class SignUpGraphic implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private HBox radioBox;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField confEmail;
	
	@FXML
	private PasswordField pwd;
	
	@FXML
	private PasswordField confPwd;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Users user = SessionFacade.getSession().getCurrUserType();
		
		if(user == null) {
			radioBox.setVisible(true);
		}
		
	}
	
	@FXML
	public void goBack() {
		Stage stage = (Stage)pane.getScene().getWindow();
		
		Screens prev = SessionFacade.getSession().getPrevScreen();
		
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	public void createAccount() {
		
		try {
			if(email.getText().equals(confEmail.getText())) {
					if(pwd.getText().equals(confPwd.getText())) {
						UserBean credentials = new UserBean(email.getText(), pwd.getText(), firstName.getText(), lastName.getText());
						credentials.verifyNewAccount();
					}else {
						GraphicHandler.popUpMsg(AlertType.ERROR, "The password does not match.");
					}
			}else{
				GraphicHandler.popUpMsg(AlertType.ERROR, "The email address not match.");
			}
			
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}	
		
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
}
