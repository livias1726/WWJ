package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OffersInfoGraphic implements Initializable {
	
	@FXML
	private AnchorPane offersPane;
	
    @FXML
    private ToggleGroup views;
	
    @FXML
    private TableView<String> table;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
    void closeOffersSection() {
		Stage st = (Stage)offersPane.getScene().getWindow();
    	st.close();
    }
	    

}
