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

	private GraphicHandler() { 
		/*Default constructor*/ 
	}
	
	public static FXMLLoader loadFXML(Screens s) {
		switch(s) {
			case LOGIN: 
				return new FXMLLoader(GraphicHandler.class.getResource("login.fxml"));
			case SEARCH_USER: 
				return new FXMLLoader(GraphicHandler.class.getResource("searchusr.fxml"));
			case SEARCH_ENTR: 
				return new FXMLLoader(GraphicHandler.class.getResource("searchentr.fxml"));
			case ACC_REC: 
				return new FXMLLoader(GraphicHandler.class.getResource("accountrec.fxml"));
			default:
				return new FXMLLoader(GraphicHandler.class.getResource("main.fxml"));
		}
	}
	
	public static Scene switchScreen(Screens next, Initializable controller) {
		SessionFacade.getSession().setScreen(next);
		
		try {
			
			if (next.equals(Screens.MAIN)) {
				SessionFacade.getSession().setCurrUser(null);
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
	
	public static Optional<ButtonType> popUpMsg(AlertType type, String content) {
		Alert alert = new Alert(type);
	
		switch(type) {
			case ERROR: 
				alert.setHeaderText("Error"); 
				break;
			default: 
				alert.setHeaderText(""); 
				break;
		}
		
		alert.setContentText(content);
		return alert.showAndWait();
	}
	
}
