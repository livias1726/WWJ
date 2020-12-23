package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class CandidatesInfoGraphic implements Initializable {
	
	@FXML
	private AnchorPane candidatesPane;

	private AnchorPane parent;
	
	public CandidatesInfoGraphic(AnchorPane pane) {
		this.parent = pane;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
    void closeCandidatesInfo(ActionEvent event) {
    	parent.getChildren().removeAll(candidatesPane.getScene().getRoot());
    }

}
