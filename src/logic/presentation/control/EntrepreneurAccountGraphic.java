package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;

public class EntrepreneurAccountGraphic extends AccountGraphic {
	
	@FXML
	public void openSearchPage(){
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_ENTR, null));
	}
	
	@FXML
	public void openPlanSection(){
		Stage popup = GraphicHandler.openSection(pane, Sections.FAV_BUSINESSES, null);
		popup.show();
	}
	
}
