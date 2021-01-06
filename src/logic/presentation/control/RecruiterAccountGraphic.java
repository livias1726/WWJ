package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;

public class RecruiterAccountGraphic extends AccountGraphic {
	
	@FXML
	private ToolBar barRec;
	
	@FXML
	public void openNewOffer(){
		System.out.println("a");
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.PUBLISH_OFFER, new OfferFormGraphic(barRec)));
		System.out.println("a");
	}
	
	@FXML
	public void openCompanyInfo() {	
		Stage popup = GraphicHandler.openSection(pane, Sections.COMPANY_INFO, null);
		popup.show();
	}
	
	@FXML
	public void openOffersInfo() {	
		Stage popup = GraphicHandler.openSection(pane, Sections.PUBLISHED_OFFERS, null);
		popup.show();
	}
	
	@FXML
	public void openCandidatesInfo() {
		Stage popup = GraphicHandler.openSection(pane, Sections.CANDIDATES, new CandidatesInfoGraphic(barRec));
		popup.show();
	}
	
	@FXML
	public void goToHome() {
		/*Nop*/
    }
}
