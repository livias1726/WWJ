package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class EntrepreneurAccountGraphic extends AccountGraphic {
	
	@FXML
    private AnchorPane motivPane;

    @FXML
    private AnchorPane plansPane;
	
	@FXML
	public void openPlanSection(){
		plansPane.setVisible(true);
	}
	
	@FXML
	public void closePlanSection(){
		plansPane.setVisible(false);
	}
	
	@FXML
	public void openMotivationSection(){
		motivPane.setVisible(true);
	}
	
	@FXML
	public void closeMotivationSection(){
		motivPane.setVisible(false);
	}
	
}
