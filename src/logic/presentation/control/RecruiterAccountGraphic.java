package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;

public class RecruiterAccountGraphic extends AccountGraphic {
	
	@FXML
	public void openNewOffer(){
		/*
		 * Redirect to offer form
		 */
	}
	
	@FXML
	protected void openCompanyInfo() {	
		Stage popup = GraphicHandler.openSection(pane, Sections.COMPANY_INFO, null);
		popup.show();
	}
	
	@FXML
	protected void openOffersInfo() {	
		Stage popup = GraphicHandler.openSection(pane, Sections.PUBLISHED_OFFERS, null);
		popup.show();
	}
	
	@FXML
	protected void openCandidatesInfo() {
		Stage popup = GraphicHandler.openSection(pane, Sections.CANDIDATES, null);
		popup.show();
	}

}
