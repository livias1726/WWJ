package logic.presentation;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import logic.application.SessionFacade;

public class GraphicSwitch {

	private GraphicSwitch() { 
		/*Default constructor*/ 
	}
	
	public static FXMLLoader loadFXML(Screens s) {
		switch(s) {
			case LOGIN: 
				return new FXMLLoader(GraphicSwitch.class.getResource("login.fxml"));
			case SEARCH_USER: 
				return new FXMLLoader(GraphicSwitch.class.getResource("searchusr.fxml"));
			case SEARCH_ENTR: 
				return new FXMLLoader(GraphicSwitch.class.getResource("searchentr.fxml"));
			case ACC_REC: 
				return new FXMLLoader(GraphicSwitch.class.getResource("accountrec.fxml"));
			default:
				return new FXMLLoader(GraphicSwitch.class.getResource("main.fxml"));
		}
	}
	
	public static Scene switchScreen(Screens next, Initializable controller) throws IOException {
		SessionFacade.getSession().setScreen(next);
		
		if (next.equals(Screens.MAIN)) {
			SessionFacade.getSession().setCurrUser(null);
			return new Scene(loadFXML(next).load());
		} else {
			FXMLLoader loader = loadFXML(next);
			if (controller != null) {
				loader.setController(controller);
			}			
			BorderPane pane = loader.load();
			return new Scene(pane);
		}
	}
	
}
