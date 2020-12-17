package logic.presentation.control;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.Alert.AlertType;
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
	private AnchorPane personalPane;

	@FXML
	protected TextField nameField;
	
	@FXML
	protected TextField lastNameField;
	
	@FXML
	protected TextField emailField;
	
	@FXML
	protected PasswordField pwdField;
	
	@FXML
	protected TextField pwdUnmasked;
	
	@FXML
	protected CheckBox showBtn;
	
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
	
	public AccountGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		
		AccountBean account = new AccountBean().retrieveInfo();		
		
		if(account != null) {
			if(account.isPremium()) {
				premiumBtn.setVisible(false);
			}
			
			nameLbl.setText(account.getUser().getFirstName() + " " + account.getUser().getLastName());	
			
			if(notifyBtn.getItems().isEmpty()) {
				notifyBtn.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(15, 49, 141), 10, 0, 0, 0));
			}	
			
			retrievePersonalInfo(account);
			initPersonalInfo();
		}	
	}
			
	private void retrievePersonalInfo(AccountBean account) {		
		nameField.setText(account.getUser().getFirstName());
		lastNameField.setText(account.getUser().getLastName());
		emailField.setText(account.getUser().getEmail());
		pwdField.setText(account.getUser().getPwd());
		city.setText(account.getUser().getCity());
		birth.setValue(account.getUser().getBirth());		
		titles.setItems((ObservableList<String>) account.getUser().getTitles());
	}
	
	private void initPersonalInfo() {
		titles.setCellFactory(TextFieldListCell.forListView());
		
		pwdUnmasked.visibleProperty().bind(showBtn.selectedProperty());
		pwdField.visibleProperty().bind(showBtn.selectedProperty().not());		
		pwdUnmasked.textProperty().bindBidirectional(pwdField.textProperty());
		
		addBtn.visibleProperty().bind(saveBtn.visibleProperty());
		
		nameField.editableProperty().bind(saveBtn.visibleProperty());
		lastNameField.editableProperty().bind(saveBtn.visibleProperty());
		emailField.editableProperty().bind(saveBtn.visibleProperty());
		pwdUnmasked.editableProperty().bind(saveBtn.visibleProperty());
		city.editableProperty().bind(saveBtn.visibleProperty());
		birth.editableProperty().bind(saveBtn.visibleProperty());	
		titles.editableProperty().bind(saveBtn.visibleProperty());	
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
		File newPic = fileChooser.showOpenDialog(stage);
		
		if (newPic != null) {
			Image image = new Image(newPic.toURI().toString());
		    profilePic.setImage(image);
		    profilePic.fitHeightProperty().bind(frame.heightProperty());
		    profilePic.fitWidthProperty().bind(frame.widthProperty());
		    
		    AccountBean bean = new AccountBean();
		    bean.updatePic(newPic);
		}
	}	
	
	@FXML
	public void openPersonalInfo(){
		personalPane.setVisible(true);
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
	protected void closePersonalSection() {
		personalPane.setVisible(false);
	}
	
	@FXML
	public void changeInfo(){
		changeInfoBtn.setVisible(false);
		saveBtn.setVisible(true);
	}
	
	@FXML
	private void addTitles(){
		if(titles.getItems() == null) {
			ObservableList <String> obs = FXCollections.observableArrayList(); 
			obs.add("");
			titles.setItems(obs);
		}else {
			titles.getItems().add("");
		}
		
		titles.edit(titles.getItems().size()-1);
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
			user.update();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		
		saveBtn.setVisible(false);
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
