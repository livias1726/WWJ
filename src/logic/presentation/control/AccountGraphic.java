package logic.presentation.control;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.control.ManageAccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.AccountBean;

public class AccountGraphic extends ToolBarGraphic{
	
	@FXML 
	protected ImageView profilePic;
	
	@FXML 
	protected Label nameLbl;
		
	protected Long accountID = (long) 0; 
	protected boolean viewed = false;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		
		AccountBean account;
		try {
			
			if(accountID == 0) {
				account = ManageAccountControl.getInstance().retrieveAccount();
				accountID = account.getId();
			}else {
				account = ManageAccountControl.getInstance().retrieveAccount(accountID);
			}
			
			if(account.getPic() != null) {
				profilePic.getImage().cancel();
				Image image = new Image(account.getPic().toURI().toString());
			    profilePic.setImage(image);
			}
			
			nameLbl.setText(account.getUser().getFirstName() + " " + account.getUser().getLastName());	
			
			initNotifBtn();

		} catch (DatabaseFailureException e1) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e1.getMessage());
			goBack();
		}
	}
	
	private void initNotifBtn() {
		List<String> notifications;
		try {
			notifications = ManageAccountControl.getInstance().retrieveNotifications();
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

	@FXML
	protected void openPersonalInfo() {
		Stage popup;
		
		if(SessionFacade.getSession().getID().equals(accountID)) {
			popup = GraphicHandler.openSection(pane, Sections.PERSONAL_INFO, new PersonalInfoGraphic());			
		} else {
			popup = GraphicHandler.openSection(pane, Sections.PERSONAL_INFO, new PersonalInfoGraphic(accountID));
		}
		
		popup.centerOnScreen();
		popup.show();
	}
			
	@FXML
	public void picSelect(){
		Stage stage = (Stage)pane.getScene().getWindow();	
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File newPic = fileChooser.showOpenDialog(stage);
		
		if (newPic != null) {
			profilePic.getImage().cancel();
			Image image = new Image(newPic.toURI().toString());		   
			profilePic.setImage(image);
		    
		    AccountBean bean = new AccountBean();
		    bean.setPic(newPic);
		    
		    try {
				ManageAccountControl.getInstance().updateAccountPic(bean);
			} catch (DatabaseFailureException e) {
				GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
				goBack();
			}
		}
	}
}
