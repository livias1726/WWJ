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
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.control.LoginControl;
import logic.application.control.SignUpControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.DuplicateUserException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.bean.AccountBean;
import logic.presentation.bean.UserBean;

public class SignUpGraphic implements Initializable {

	@FXML
	private AnchorPane pane;
	
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
	
	@FXML
    private Button signUpBtn;
	
	private int checked;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		signUpBtn.disableProperty().bind(seekRadio.selectedProperty()
				.or(recRadio.selectedProperty())
				.or(entreRadio.selectedProperty()).not());
		seekRadio.selectedProperty().addListener((obs, oldV, newV) -> checked = 1);
		recRadio.selectedProperty().addListener((obs, oldV, newV) -> checked = 2);
		entreRadio.selectedProperty().addListener((obs, oldV, newV) -> checked = 3);
	}
	
	@FXML
	public void createAccount() {
		
		try {			
			UserBean credentials = new UserBean();
			credentials.setEmail(email.getText());
			credentials.setPassword(pwd.getText());
			credentials.setFirstName(firstName.getText());
			credentials.setLastName(lastName.getText());
			credentials.verifySignUpSyntax(confEmail.getText(), confPwd.getText());
			
			AccountBean account = new AccountBean();
			account.setUser(credentials);
			
			switch(checked) {
				case 1:
					account.setType("SEEKER");
					break;
				case 2:
					account.setType("RECRUITER");
					break;
				case 3:
					account.setType("ENTREPRENEUR");
					break;
				default:
					account.setType(null);			
			}
			
			SignUpControl.getInstance().trySignUp(account);
			LoginControl.getInstance().tryLogin(account);
			
			Stage stage = (Stage)pane.getScene().getWindow();		
			switch(account.getType()) {
				case "SEEKER":
					stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, new SeekerAccountGraphic(SessionFacade.getSession().getID())));	
					break;
				case "RECRUITER":
					stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
					break;
				case "ENTREPRENEUR":
					stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_ENTR, null));
					break;
				default:
			}
			
		} catch (DuplicateUserException | InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.WARNING, e.getMessage());
		} catch (FailedLoginException | DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
}
