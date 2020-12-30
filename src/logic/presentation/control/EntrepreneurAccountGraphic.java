package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;

public class EntrepreneurAccountGraphic extends AccountGraphic {
	
	@FXML
	public void openPlanSection(){
		Stage popup = GraphicHandler.openSection(pane, Sections.BUSINESS_INFO, null);
		popup.show();
	}
	
}
