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
			case OFFERS_INFO: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "offers_recruiter.fxml"));
			case CANDIDATES_INFO: 
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "candidates.fxml"));
			default:
				return new FXMLLoader(GraphicHandler.class.getResource(PATH + "non_lo_so.fxml"));
		}
	}
	
	public static Scene switchScreen(Scenes next, Initializable controller) {
		SessionFacade.getSession().setScreen(next);
		
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
			e.printStackTrace();
			return null;
		}	
	}
	
	public static void openSection(AnchorPane parent, Sections sec, Initializable controller) {
	
		try {			
			FXMLLoader loader = loadFXML(sec);
			if (controller != null) {
				loader.setController(controller);
			}	
			
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			parent.getChildren().setAll(scene.getRoot());
		}catch (IOException e){
			e.printStackTrace();
		}	
	}
	
	public static Optional<ButtonType> popUpMsg(AlertType type, String content) {
		Alert alert = new Alert(type);
	
		switch(type) {
			case ERROR: 
				alert.setHeaderText("Error"); 
				break;
			case CONFIRMATION: 
				alert.setHeaderText("Confirm"); 
				break;
			case INFORMATION: 
				alert.setHeaderText("Info"); 
				break;
			case WARNING: 
				alert.setHeaderText("Attention"); 
				break;
			default: 
				alert.setHeaderText(""); 
				break;
		}
		
		alert.setContentText(content);
		return alert.showAndWait();
	}
	
	public static void popUpAdv() {
		/*Method to implement pop up advertisement when changing screen*/
	}
	
}
