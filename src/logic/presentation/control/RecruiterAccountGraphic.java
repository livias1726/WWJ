package logic.presentation.control;

import javafx.fxml.FXML;
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
		GraphicHandler.openSection(pane, Sections.COMPANY_INFO, new ComapnyInfoGraphic(pane));
	}
	
	@FXML
	protected void openOffersInfo() {		
		GraphicHandler.openSection(pane, Sections.OFFERS_INFO, new OffersInfoGraphic(pane));
	}
	
	@FXML
	protected void openCandidatesInfo() {		
		GraphicHandler.openSection(pane, Sections.CANDIDATES_INFO, new CandidatesInfoGraphic(pane));
	}

}
