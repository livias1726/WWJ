package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
import logic.presentation.Screens;

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
	public void setUser(){
		if(seekRadio.isArmed()) {
			SessionFacade.getSession().setCurrUserType(Users.SEEKER);
		}else if(recRadio.isArmed()) {
			SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
		}else if(entreRadio.isArmed()) {
			SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
		}else {
			SessionFacade.getSession().setCurrUserType(null);
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
		UserBean credentials = new UserBean(email.getText(), pwd.getText());
		
		try {
			credentials.verifyFields();
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
				stage.setScene(GraphicHandler.switchScreen(Screens.ACC_SEEK, null));
				
			}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
				stage.setScene(GraphicHandler.switchScreen(Screens.ACC_REC, null));
				
			}else {
				stage.setScene(GraphicHandler.switchScreen(Screens.ACC_ENTR, null));
			}

		} catch (FailedLoginException le) {
			GraphicHandler.popUpMsg(AlertType.ERROR, le.getMessage());
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}
	
	private void refresh() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.SIGN_UP, null));
	}

	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
}
