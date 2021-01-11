package logic.presentation.control;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.bean.ApplicationBean;
import logic.presentation.bean.OfferBean;

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
	private Integer offerID;
	private static String empty = "Not provided";
	
	public OfferDetailsGraphic(OfferBean o, Integer id) {
		this.offer = o;
		this.offerID = id;
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
		
		initBtns();		
	}
	
	private void initBtns() {
		if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
			applyBtn.setVisible(false);
			favBtn.setVisible(false);
		}else {
			try {
				ObservableList <ApplicationBean> list = new ApplicationBean().getApplications();
				for(ApplicationBean i: list) {
					if(i.getId() == offerID) {
						applyBtn.setDisable(true);
						favBtn.setDisable(true);
					}
				}
			} catch (DatabaseFailureException e) {
				GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
				goBack();
			}
		}
	}
	
	@FXML
    public void applyToOffer() {
		if(checkLogin()) {
			ApplicationBean bean = new ApplicationBean();
			bean.setId(offerID);
			try {
				bean.addToApplications();
			} catch (DatabaseFailureException e) {
				GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			}
		}
    }
	
	@FXML
    public void manageFavourite() {
		if(checkLogin()) {
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
    public void openMaps() {
    	String map = offer.getBranch().buildMapAddress();
    	URL url;
		try {
			url = new URL("https://www.google.com/maps/place/" + map + "/");
			Desktop.getDesktop().browse(url.toURI());
		} catch (URISyntaxException | IOException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, "Sorry, the address cannot be opened in Maps. Try searching it manually.");
		}
    }
    
    @FXML
    public void goBack() {
    	Stage st = (Stage)pane.getScene().getWindow();
    	st.close();
    }
}
