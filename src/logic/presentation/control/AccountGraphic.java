package logic.presentation.control;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;

public class AccountGraphic implements Initializable {

	@FXML
	protected AnchorPane pane;
	
	@FXML
	protected Pane picFrame;
	
	@FXML
	protected MenuButton notifyBtn;
	
	@FXML
	protected MenuItem premiumBtn;
		
	@FXML 
	protected ImageView profilePic;
	
	@FXML 
	protected Label nameLbl;
		
	protected Integer accountID;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		AccountBean account = null;
		try {
			if(accountID == null) {
				account = new AccountBean().getAccount();
			}else {
				account = new AccountBean().getAccount(accountID);
			}			
		} catch (DatabaseFailureException e1) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e1.getMessage());
			goBack();
		}
		
		if(account != null) {
			if(account.isPremium()) {
				premiumBtn.setVisible(false);
			}
			
			nameLbl.setText(account.getUser().getFirstName() + " " + account.getUser().getLastName());	
			
			List<String> notifications;
			try {
				notifications = account.getNotification();
				for(String i: notifications) {
					MenuItem item = new MenuItem(i);
					notifyBtn.getItems().add(item);
				}
			} catch (DatabaseFailureException e) {
				GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
				goBack();
			}		
			
			if(!notifyBtn.getItems().isEmpty()) {
				notifyBtn.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(15, 49, 141), 10, 0, 0, 0));
			}
		}
	}
	
	@FXML
	protected void openPersonalInfo(ActionEvent event) {
		if(SessionFacade.getSession().getID().intValue() != accountID) {
			Stage popup = GraphicHandler.openSection(pane, Sections.PERSONAL_INFO, new PersonalInfoGraphic(accountID));
			popup.centerOnScreen();
			popup.show();
		} else {
			Stage popup = GraphicHandler.openSection(pane, Sections.PERSONAL_INFO, new PersonalInfoGraphic());
			popup.centerOnScreen();
			popup.show();
		}
		
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
		    profilePic.fitHeightProperty().bind(picFrame.heightProperty());
		    profilePic.fitWidthProperty().bind(picFrame.widthProperty());
		    
		    AccountBean bean = new AccountBean();
		    bean.updatePic(newPic);
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
	private void logout(){
		SessionFacade.getSession().setID(null);
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.MAIN, null));
	}
	
	@FXML
	public void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	private void closeApp() {
		System.exit(0);
	}

}
