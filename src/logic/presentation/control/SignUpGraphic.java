package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
import logic.bean.AccountBean;
import logic.bean.UserBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class SignUpGraphic implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private HBox radioBox;
	
	@FXML
	private RadioButton seekRadio;
	
	@FXML
	private RadioButton recRadio;
	
	@FXML
	private RadioButton entreRadio;
	
	@FXML
    private ToggleGroup radioUser;
	
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
		
		radioUser.selectedToggleProperty().addListener((obv, oldValue, newValue) -> setUser());
	}
	
	@FXML
	public void setUser(){
		if(seekRadio.isSelected()) {
			SessionFacade.getSession().setCurrUserType(Users.SEEKER);
		}else if(recRadio.isSelected()) {
			SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
		}else if(entreRadio.isSelected()) {
			SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
		}else {
			SessionFacade.getSession().setCurrUserType(null);
		}
	}
	
	@FXML
	public void goBack() {
		Stage stage = (Stage)pane.getScene().getWindow();	
		Scenes prev = SessionFacade.getSession().getPrevScene();	
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	public void createAccount() {
		UserBean credentials = new UserBean();
		credentials.setEmail(email.getText());
		credentials.setPassword(pwd.getText());
		
		try {
			credentials.verifyFields(email.getText(), pwd.getText(), firstName.getText(), lastName.getText());
			credentials.verifyEqualFields(confEmail.getText(), confPwd.getText());
			credentials.verifySyntax();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.WARNING, e.getMessage());
			refresh();
		}	
	
		try {
			AccountBean account = new AccountBean();
			credentials.setFirstName(firstName.getText());
			credentials.setLastName(lastName.getText());
			account.setUser(credentials);
			
			account.signUp();
			
			Stage stage = (Stage)pane.getScene().getWindow();
			
			if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
				stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, null));
				
			}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
				stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
				
			}else {
				stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_ENTR, null));
			}

		} catch (FailedLoginException le) {
			GraphicHandler.popUpMsg(AlertType.ERROR, le.getMessage());
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}
	
	private void refresh() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.SIGN_UP, null));
	}

	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
}
