package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class RecruiterAccountGraphic extends AccountGraphic {
    
	@FXML
	public void openNewOffer(){
		/*
		 * Redirect to offer form
		 */
	}
	
	@FXML
	protected void openCompanyInfo() {		
		Scene scene = GraphicHandler.switchScreen(Screens.COMPANY_INFO, new PersonalInfoGraphic(pane));
		pane.getChildren().setAll(scene.getRoot());
	}
	
	@FXML
	protected void openOffersInfo() {		
		Scene scene = GraphicHandler.switchScreen(Screens.OFFERS_INFO, null);
		pane.getChildren().setAll(scene.getRoot());
	}
	
	@FXML
	protected void openCandidatesInfo() {		
		Scene scene = GraphicHandler.switchScreen(Screens.CANDIDATES_INFO, null);
		pane.getChildren().setAll(scene.getRoot());
	}

}
