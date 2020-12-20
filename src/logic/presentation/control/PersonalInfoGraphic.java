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
import javafx.scene.layout.AnchorPane;
import logic.bean.UserBean;

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
    
    @Override
	public void initialize(URL url, ResourceBundle res) {
    	UserBean user = new UserBean().getPersonalInfo();

    	initInfo(user);
    		
    	initPasswordVisibility();
    	
    	initBindings();
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
    	saveBtn.visibleProperty().bind(addBtn.visibleProperty());
    	nameField.editableProperty();
    	lastNameField.editableProperty();
    			/*.add(emailField.editableProperty())
    			.add(city.editableProperty())
    			.add(birth.editableProperty())
    			.add(titles.editableProperty()));	*/
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

    }

}
