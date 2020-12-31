package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;

public class SeekerAccountGraphic extends AccountGraphic {
		
	@FXML
	public void openSearchPage(){
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
	}
	
	@FXML
	public void openCVSection(){
		Stage popup = GraphicHandler.openSection(pane, Sections.CV_INFO, null);
		popup.show();
	}
	
	@FXML
	public void openApplicationSection() {
		Stage popup = GraphicHandler.openSection(pane, Sections.APPLICATIONS, null);
		popup.show();
	}
	
	@FXML
	public void openFavouritesSection() {
		Stage popup = GraphicHandler.openSection(pane, Sections.FAV_OFFERS, null);
		popup.show();
	}
	
}
