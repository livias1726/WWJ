package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;
import logic.presentation.bean.BusinessInCountryBean;

public class BusinessDetailsGraphic implements Initializable {
	
	@FXML
    private AnchorPane pane;

    @FXML
    private Label busLbl;

    @FXML
    private TextField budgetTxt;

    @FXML
    private ComboBox<?> currBox;

    @FXML
    private Button feasBtn;

    @FXML
    private TextArea descArea;

    @FXML
    private Button statBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label rankLbl;
    
    @FXML
    private Button favBtn;
    
    private BusinessInCountryBean business;

	public BusinessDetailsGraphic(BusinessInCountryBean b) {
		this.business = b;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		busLbl.setText(business.getName());
		feasBtn.disableProperty().bind(budgetTxt.textProperty().isEmpty());
		descArea.setText(business.getDescription());
		/*Add ranking*/
	}
	
	@FXML
    public void manageFavourite() {
    	if(SessionFacade.getSession().getID() == null) {
    		Stage stage = (Stage)pane.getScene().getWindow();
    		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
    	}
    	
		try {
			if(favBtn.getStyle().equals("star_button_nset")) {
				business.addToFavourites();
				favBtn.setStyle("star_button_set");
			}else {
				business.removeFromFavourites();
				favBtn.setStyle("star_button_nset");	
			}
		} catch (DatabaseFailureException e) {
			/*Don't change the settings*/
		}
    }

	@FXML
	public void calculateFeasibility() {
		if(SessionFacade.getSession().getID() == null) {
    		Stage stage = (Stage)pane.getScene().getWindow();
    		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
    	}
    	
    	Stage popup = GraphicHandler.openSection(pane, Sections.FEASIBILITY, new FeasibilityGraphic(business, budgetTxt.getText()));
		popup.centerOnScreen();
		popup.show();
    }

    @FXML
    public void viewStatistics() {
    	if(SessionFacade.getSession().getID() == null) {
    		Stage stage = (Stage)pane.getScene().getWindow();
    		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
    	}
    	
    	Stage popup = GraphicHandler.openSection(pane, Sections.STATISTICS, new StatisticsGraphic(business));
		popup.centerOnScreen();
		popup.show();
    }
    
    @FXML
    public void goBack() {
    	Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
    }
}
