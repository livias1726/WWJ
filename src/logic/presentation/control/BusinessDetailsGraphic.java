package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.bean.BusinessInCountryBean;

public class BusinessDetailsGraphic implements Initializable {
	
	@FXML
    private AnchorPane pane;

    @FXML
    private Label busLbl;

    @FXML
    private TextField budget;

    @FXML
    private ChoiceBox<String> currBox;

    @FXML
    private Button feasBtn;

    @FXML
    private TextArea descArea;

    @FXML
    private Button statBtn;
    
    private BusinessInCountryBean business;
	
	public BusinessDetailsGraphic(BusinessInCountryBean b) {
		this.business = b;
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		busLbl.setText(business.getName());
		feasBtn.disableProperty().bind(budget.textProperty().isEmpty());
		
		descArea.setText(business.getDescription());
		
		ObservableList<String> currency = FXCollections.observableArrayList("$", "£", "€");
		currBox.setItems(currency);
		currBox.setValue(currency.get(0));
	}
	
	@FXML
	public void calculateFeasibility() {
		if(checkLogin()) {
			Stage popup = GraphicHandler.openSection(pane, Sections.FEASIBILITY, new FeasibilityGraphic(business, budget.getText()));
			popup.centerOnScreen();
			popup.show();
		}    	
    }

    @FXML
    public void viewStatistics() {
    	if(checkLogin()) {
    		Stage popup = GraphicHandler.openSection(pane, Sections.STATISTICS, new StatisticsGraphic(business));
    		popup.centerOnScreen();
    		popup.show();
    	} 	
    }
    
    private boolean checkLogin() {
    	boolean res = false;
    	if(SessionFacade.getSession().getID() == null) {
    		GraphicHandler.popUpMsg(AlertType.WARNING, "You need to be logged to perform this operation. Please, log in or create an account!");
    		res = false;
    	}else {
    		res = true;
    	}
    	
    	return res;
	}
    
    @FXML
    public void goBack() {
    	Stage st = (Stage)pane.getScene().getWindow();
    	st.close();
    }
}
