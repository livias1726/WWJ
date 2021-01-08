package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.application.Users;
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
    private TextField jobTxt;

    @FXML
    private ListView<String> reqList;

    @FXML
    private TextArea descArea;

    @FXML
    private TextField branch;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private TextField salary;

    @FXML
    private TextField expDate;

    @FXML
    private Button applyBtn;

    @FXML
    private Button backBtn;
    
    @FXML
    private Button favBtn;

	private OfferBean offer;
	private static String empty = "Not provided";
	
	public OfferDetailsGraphic(OfferBean o) {
		this.offer = o;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		compLbl.setText(offer.getCompanyName());	
		jobTxt.setText(offer.getPosition().getName());
		
		if(offer.getRequirements() == null || offer.getRequirements().isEmpty()) {
			reqList.getItems().add(empty);
		}else {
			for(String i: offer.getRequirements()) {
				reqList.getItems().add(i);
			}
		}
				
		descArea.setText(offer.getTaskDescription());
		branch.setText(offer.getBranch().getStreet() + ", " + offer.getBranch().getNumber() + ", " + 
					   offer.getBranch().getPostalCode() + ", " + offer.getBranch().getCity() + ", " + 
					   offer.getBranch().getState() + ", " + offer.getBranch().getCountry());
		
		if(offer.getStart() == null && offer.getFinish() == null) {
			startTime.setText(empty);
			endTime.setText(empty);
		}else {
			startTime.setText(offer.getStart().toString());
			endTime.setText(offer.getFinish().toString());
		}

		salary.setText(String.valueOf(offer.getBaseSalary()));	
		expDate.setText(offer.getExpiration().toString());
		
		if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			applyBtn.setVisible(false);
			favBtn.setVisible(false);
		}
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
    public void manageFavourite() {
		if(SessionFacade.getSession().getID() == null) {
    		Stage stage = (Stage)pane.getScene().getWindow();
    		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
    	}
		
		try {
			if(favBtn.getStyle().equals("star_button_nset")) {
				offer.addToFavourites();
				favBtn.setStyle("star_button_set");
			}else {
				offer.removeFromFavourites();
				favBtn.setStyle("star_button_nset");	
			}
		} catch (DatabaseFailureException e) {
			/*Don't change the settings*/
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
