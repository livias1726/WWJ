package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.ApplicationBean;
import logic.bean.OfferBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class OfferDetailsGraphic implements Initializable {

	@FXML
    private AnchorPane pane;
	
	@FXML
    private Label compLbl;

    @FXML
    private TextArea jobTxt;

    @FXML
    private ListView<String> reqList;

    @FXML
    private TextArea descArea;

    @FXML
    private TextArea branch;

    @FXML
    private TextArea startTime;

    @FXML
    private TextArea endTime;

    @FXML
    private TextArea salary;

    @FXML
    private TextArea expDate;

    @FXML
    private Button applyBtn;

    @FXML
    private Button backBtn;

	private OfferBean offer;
	
	public OfferDetailsGraphic(OfferBean o) {
		this.offer = o;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		compLbl.setText(offer.getCompanyName());
		jobTxt.setText(offer.getPosition().getName());
		for(String i: offer.getRequirements()) {
			reqList.getItems().add(i);
		}
		
		descArea.setText(offer.getTaskDescription());
		branch.setText(offer.getBranch().getStreet() + ", " + offer.getBranch().getNumber() + ", " + 
					   offer.getBranch().getPostalCode() + ", " + offer.getBranch().getCity() + ", " + 
					   offer.getBranch().getState() + ", " + offer.getBranch().getCountry());
		
		startTime.setText(offer.getStart().toString());
		endTime.setText(offer.getFinish().toString());
		salary.setText(String.valueOf(offer.getBaseSalary()));
		
		expDate.setText(offer.getExpiration().toString());
	}
	
	@FXML
    public void applyToOffer() {
		ApplicationBean bean = new ApplicationBean();
		bean.setId(offer.getId());
		try {
			bean.addToApplications();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		}
    }

    @FXML
    public void openMaps() {
    	/**/
    }
    
    @FXML
    public void goBack() {
    	Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
    }
}
