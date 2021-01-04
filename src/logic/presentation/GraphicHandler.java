package logic.presentation;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.application.SessionFacade;

public class GraphicHandler {
	
	private static final String PATH = "resources/fxml/";

	private GraphicHandler() { 
		/**/ 
	}
	
	public static FXMLLoader loadFXML(Scenes s) {
		switch(s) {
			case LOGIN: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "login.fxml"));
			case SIGN_UP: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "signup.fxml"));
			case SEARCH_SEEK: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "search_seek.fxml"));
			case OFFERS: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "offers_result.fxml"));
			case OFFER: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "offer_details.fxml"));
			case SEARCH_ENTR: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "search_entr.fxml"));
			case BUSINESSES: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "business_result.fxml"));
			case BUSINESS: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "business_details.fxml"));
			case ACC_SEEK: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "seeker_account.fxml"));
			case ACC_REC: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "recruiter_account.fxml"));
			case ACC_ENTR: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "entrepreneur_account.fxml"));
			default:
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "main.fxml"));
		}
	}
	
	public static FXMLLoader loadFXML(Sections s) {
		switch(s) {
			case PERSONAL_INFO: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "personal_info.fxml"));
			case COMPANY_INFO: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "company.fxml"));
			case PUBLISHED_OFFERS: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "offers_recruiter.fxml"));
			case CANDIDATES: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "candidates.fxml"));
			case FAV_BUSINESSES: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "favourites_business.fxml"));
			case APPLICATIONS: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "applications.fxml"));
			case FAV_OFFERS: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "favourites_offer.fxml"));
			default:
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "main.fxml"));
		}
	}
	
	public static Scene switchScreen(Scenes next, Initializable controller) {
		SessionFacade.getSession().setScene(next);
		
		try {			
			if (next.equals(Scenes.MAIN)) {
				SessionFacade.getSession().setCurrUserType(null);
				return new Scene(loadFXML(next).load());
				
			} else {
				FXMLLoader loader = loadFXML(next);
				if (controller != null) {
					loader.setController(controller);
				}	
				
				AnchorPane pane = loader.load();
				return new Scene(pane);
			}			
		}catch (IOException e){
			return null;
		}	
	}
	
	public static Scene switchScreen(Sections sec, Initializable controller) {	
		try {			
			FXMLLoader loader = loadFXML(sec);
			if (controller != null) {
				loader.setController(controller);
			}	
			
			AnchorPane pane = loader.load();
			return new Scene(pane);
		}catch (IOException e){
			return null;
		}	
	}
	
	public static Stage openSection(AnchorPane parent, Sections sec, Initializable controller) {
		Stage popupStage = new Stage(StageStyle.TRANSPARENT);
		
		popupStage.initOwner(parent.getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.setScene(switchScreen(sec, controller));
		
		return popupStage;
	}
	
	public static Optional<ButtonType> popUpMsg(AlertType type, String content) {
		Alert alert = new Alert(type);
	
		switch(type) {
			case ERROR: 
				alert.setHeaderText("Error"); 
				break;
			case CONFIRMATION: 
				alert.setHeaderText("Confirm"); 
				if(content == null) {
					ButtonType upload = new ButtonType("Upload");
					alert.getButtonTypes().setAll(upload);
				}else if (content.isEmpty()){
					ButtonType upload = new ButtonType("Upload");
					ButtonType show = new ButtonType("Show");
					alert.getButtonTypes().setAll(upload, show);
				}
				break;
			case INFORMATION: 
				alert.setHeaderText("Info"); 
				break;
			case WARNING: 
				alert.setHeaderText("Attention"); 
				break;
			default: 
				break;
		}
		
		alert.setContentText(content);
		return alert.showAndWait();
	}
	
	public static void popUpAdv() {
		/*Method to implement pop up advertisement when changing screen*/
	}
	
}
