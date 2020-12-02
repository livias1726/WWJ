package logic.presentation.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.AccountBean;
import logic.bean.UserBean;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class AccountGraphic implements Initializable {

	@FXML
	protected AnchorPane pane;
	
	@FXML
	protected Pane frame;
	
	@FXML
	protected AnchorPane sectionPane;
	
	@FXML
	protected Button backBtn;
	
	@FXML
	protected MenuButton notifyBtn;
	
	@FXML
	protected MenuItem premiumBtn;
		
	@FXML 
	protected ImageView profilePic;
	
	@FXML 
	protected Label nameLbl;
	
	@FXML 
	protected Label roleLbl;
	
	@FXML
	protected TextField nameField;
	
	@FXML
	protected TextField lastNameField;
	
	@FXML
	protected TextField emailField;
	
	@FXML
	protected PasswordField pwdField;
	
	@FXML
	protected TextField city;
	
	@FXML
	protected DatePicker birth;
	
	@FXML
	protected ListView<String> titles;
	
	@FXML
	protected Button changeInfoBtn;
	
	@FXML
	protected Button addBtn;
	
	@FXML
	protected Button saveBtn;
	
	protected URL path = getClass().getResource("../resources/fxml/personal_info.fxml");
	
	public AccountGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Upload the Account Data from the DB*/
		AccountBean account = new AccountBean().retrieveInfo(SessionFacade.getSession().getID());
		
		if(url.equals(path)) {
			nameField.setText(account.getUser().getFirstName());
			lastNameField.setText(account.getUser().getLastName());
			
		}else {			
			//If notifyBtn has non read items: enlighten it.
			if(notifyBtn.getItems() != null) {
				notifyBtn.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(15, 49, 141), 10, 0, 0, 0));
			}	
			//If the account is premium: make premiumBtn invisibles
			if(account.isPremium()) {
				premiumBtn.setVisible(false);
			}
			//Insert the personal data inside the view
			nameLbl.setText(account.getUser().getFirstName() + " " + account.getUser().getLastName());	
		}		
	}
	
	@FXML
	public void goBack(){
		Screens prev = SessionFacade.getSession().getPrevScreen();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	public void picSelect(){
		Stage stage = (Stage)pane.getScene().getWindow();	
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if (selectedFile != null) {
			Image image = new Image(selectedFile.toURI().toString());
		    profilePic.setImage(image);
		    profilePic.fitHeightProperty().bind(frame.heightProperty());
		    profilePic.fitWidthProperty().bind(frame.widthProperty());
		}
	}	
	
	@FXML
	public void openPersonalInfo(){
		FXMLLoader loader = new FXMLLoader();
		
		try {                       
		    loader.setLocation(path);
		    sectionPane = (AnchorPane)loader.load();
		    pane.getChildren().addAll(sectionPane);
		    sectionPane.setLayoutX(485);
		    sectionPane.setLayoutY(100);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	@FXML
	private void openOnlineDoc(){
		/*
		 * Launch an html page with documentation
		 */
	}
	
	@FXML
	private void buyPremium(){
		/*
		 * Redirect to payment check out
		 */
	}
	
	@FXML
	private void closeSection(){
		pane.getChildren().remove(sectionPane);
	}
	
	@FXML
	private void showPassword(){
		
	}
	
	@FXML
	private void changeInfo(){
		changeInfoBtn.setVisible(false);
		saveBtn.setVisible(true);
		addBtn.setVisible(true);
		
		nameField.setEditable(true);
		lastNameField.setEditable(true);
		emailField.setEditable(true);
		pwdField.setEditable(true);
		city.setEditable(true);
		birth.setEditable(true);
		titles.setEditable(true);
	}
	
	@FXML
	private void addTitles(){
		titles.getItems().add("");
	}
	
	@FXML
	private void saveChanges(){
		UserBean user = new UserBean(emailField.getText(), pwdField.getText());
		user.setBirth(birth.getValue());
		user.setCity(city.getText());
		user.setFirstName(nameField.getText());
		user.setLastName(lastNameField.getText());
		user.setPassword(pwdField.getText());
		user.setTitles(titles.getItems());
		
		try {
			user.update(user);
		} catch (InvalidFieldException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void logout(){
		SessionFacade.getSession().setID(null);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.MAIN, null));
	}
	
	@FXML
	private void closeApp(){
		System.exit(0);
	}

}
