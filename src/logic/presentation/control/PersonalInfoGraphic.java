package logic.presentation.control;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.UserBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;

public class PersonalInfoGraphic implements Initializable {

	@FXML
    private AnchorPane personalPane;

    @FXML
    private Button changeBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private DatePicker birth;

    @FXML
    private TextField city;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private CheckBox showBtn;
    
    @FXML
    private VBox pwdBox;
    
    @FXML
    private HBox passHBox;
    
    private Long id;
    
    public PersonalInfoGraphic(Long accountID) {
		this.id = accountID;
	}
    
    public PersonalInfoGraphic() {
		this.id = SessionFacade.getSession().getID();
	}

	@Override
	public void initialize(URL url, ResourceBundle res) {
    	UserBean user;
		try {
			user = new UserBean().getPersonalInfo(id);
			initInfo(user);		    	
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closePersonalInfo();
		}
		
		if(SessionFacade.getSession().getID().intValue() != id) {
			passHBox.setVisible(false);
			changeBtn.setVisible(false);
		}else {
			initPasswordVisibility(); 	
	    	initBindings();
		}
	}
    
    private void initInfo(UserBean user) {
    	nameField.setText(user.getFirstName());
    	lastNameField.setText(user.getLastName());
    	emailField.setText(user.getEmail());
    	pwdField.setText(user.getPassword());
    	city.setText(user.getCity());
    	birth.setValue(user.getBirth());
    }
    
    private void initPasswordVisibility() {
    	TextField unmasked = new TextField();
    	unmasked.setVisible(false);
    	unmasked.setManaged(false);
		pwdBox.getChildren().add(unmasked);

		unmasked.managedProperty().bind(showBtn.selectedProperty());
		unmasked.visibleProperty().bind(showBtn.selectedProperty());

		pwdField.managedProperty().bind(showBtn.selectedProperty().not());
		pwdField.visibleProperty().bind(showBtn.selectedProperty().not());

	    unmasked.textProperty().bindBidirectional(pwdField.textProperty());
    }
    
    private void initBindings() {
    	saveBtn.visibleProperty().bind(changeBtn.visibleProperty().not());
    	
    	nameField.editableProperty().bind(saveBtn.visibleProperty());
    	lastNameField.editableProperty().bind(saveBtn.visibleProperty());
    	emailField.editableProperty().bind(saveBtn.visibleProperty());
    	city.editableProperty().bind(saveBtn.visibleProperty());
    	birth.editableProperty().bind(saveBtn.visibleProperty());
    }
    
    @FXML
    public void changeInfo() {
    	changeBtn.setVisible(false);
    }
    
    @FXML
    public void saveChanges() {
    	Optional<ButtonType> result = GraphicHandler.popUpMsg(AlertType.CONFIRMATION, "Do you really want to save this changes?");
    	if(!result.isPresent() || (result.get() == ButtonType.CANCEL)) {
    		return;
    	}
    	
    	UserBean bean = new UserBean();
    	bean.setEmail(emailField.getText());
    	bean.setPassword(pwdField.getText());
    	bean.setFirstName(nameField.getText());
    	bean.setLastName(lastNameField.getText());
    	
    	try {
			bean.verifyFields(emailField.getText(), pwdField.getText(), nameField.getText(), lastNameField.getText());
			bean.verifySyntax();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.WARNING, e.getMessage());
			return;
		} 

    	bean.setCity(city.getText());
    	bean.setBirth(birth.getValue());
    
    	try {
			bean.savePersonalInfo();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closePersonalInfo();
		}
    	
    	changeBtn.setVisible(true);
    }

    @FXML
    public void closePersonalInfo() {
    	Stage st = (Stage)personalPane.getScene().getWindow();
    	st.close();
    }
}
