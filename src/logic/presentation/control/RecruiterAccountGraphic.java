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
		Stage popup = GraphicHandler.openSection(pane, Sections.OFFERS_INFO, null);
		popup.show();
	}
	
	@FXML
	protected void openCandidatesInfo() {
		Stage popup = GraphicHandler.openSection(pane, Sections.CANDIDATES_INFO, new CandidatesInfoGraphic(pane));
		popup.show();
	}

}
