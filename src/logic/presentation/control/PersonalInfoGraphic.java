package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import logic.bean.UserBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;

public class PersonalInfoGraphic implements Initializable {

	@FXML
    private AnchorPane personalPane;

    @FXML
    private ListView<String> titles;

    @FXML
    private Button changeBtn;

    @FXML
    private Button addBtn;

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
    
    private AnchorPane parent;
    
    public PersonalInfoGraphic(AnchorPane pane) {
    	this.parent = pane;
    }
    
    @Override
	public void initialize(URL url, ResourceBundle res) {
    	UserBean user;
		try {
			user = new UserBean().getPersonalInfo();
			initInfo(user);	
	    	initPasswordVisibility(); 	
	    	initBindings();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closePersonalInfo(null);
		}
	}
    
    private void initInfo(UserBean user) {
    	nameField.setText(user.getFirstName());
    	lastNameField.setText(user.getLastName());
    	emailField.setText(user.getEmail());
    	pwdField.setText(user.getPassword());
    	city.setText(user.getCity());
    	birth.setValue(user.getBirth());
    	
    	for(String i: user.getTitles()) {
    		titles.getItems().add(i);
    	}
    }
    
    private void initPasswordVisibility() {
    	TextField pwdUnmasked = new TextField();
		pwdUnmasked.setManaged(false);
		pwdUnmasked.setVisible(false);
		
		pwdUnmasked.managedProperty().bind(showBtn.selectedProperty());
		pwdUnmasked.visibleProperty().bind(showBtn.selectedProperty());

		pwdField.managedProperty().bind(showBtn.selectedProperty().not());
		pwdField.visibleProperty().bind(showBtn.selectedProperty().not());	
		
		pwdUnmasked.textProperty().bindBidirectional(pwdField.textProperty());
    }
    
    private void initBindings() {
    	changeBtn.visibleProperty().bind(saveBtn.visibleProperty().not());
    	addBtn.visibleProperty().bind(saveBtn.visibleProperty());
    	nameField.editableProperty().bind(saveBtn.visibleProperty());
    	lastNameField.editableProperty().bind(saveBtn.visibleProperty());
    	emailField.editableProperty().bind(saveBtn.visibleProperty());
    	city.editableProperty().bind(saveBtn.visibleProperty());
    	birth.editableProperty().bind(saveBtn.visibleProperty());
    	titles.editableProperty().bind(saveBtn.visibleProperty());
    }
    
    @FXML
    void changeInfo(ActionEvent event) {
    	changeBtn.setVisible(false);
    }

    @FXML
    void addTitles(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    @FXML
    void closePersonalInfo(ActionEvent event) {
    	parent.getChildren().removeAll(personalPane.getScene().getRoot());
    }

}
