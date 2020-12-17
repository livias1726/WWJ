package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class SearchEntrepreneurGraphic implements Initializable {
	
	@FXML
    private AnchorPane searchPane;

    @FXML
    private ComboBox<String> placeSearch;

    @FXML
    private ComboBox<String> businessSearch;

    @FXML
    private Button searchBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button backBtn;

    @FXML
    private MenuButton notifyBtn;

    @FXML
    private MenuButton userBtn;

    @FXML
    private MenuItem outBtn;

    @FXML
    private MenuItem inBtn;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem premiumBtn;
    
    @FXML
    private ToolBar toolBar;
    
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Edit search btn
		searchBtn.disableProperty().bind(Bindings.not(businessSearch.valueProperty().isNotNull().or(placeSearch.valueProperty().isNotNull())));
		
		//Edit toolbar
		if(SessionFacade.getSession().getID() == null) {
			premiumBtn.setVisible(false);
			outBtn.setVisible(false);
			notifyBtn.setVisible(false);
		}else {
			inBtn.setVisible(false);
		}
		
		//Edit combo boxes: retrieve from DB
		CountryBean country = new CountryBean();
		List<String> cList = country.getCountries(new BusinessInCountryBean());
		placeSearch.getItems().addAll(cList);
		
		BusinessInCountryBean bus = new BusinessInCountryBean();
		List<String> bList = bus.getBusinesses();	
		businessSearch.getItems().addAll(bList);
	}
	
	@FXML
	public void search() {
		Stage stage = (Stage)searchPane.getScene().getWindow();
		BusinessResultsGraphic controller;
		
		if (placeSearch.getValue() != null && !placeSearch.getValue().equals("")) {
			CountryBean country = new CountryBean();
			country.setName(placeSearch.getValue());
			
			if (businessSearch.getValue() != null && !businessSearch.getValue().equals("")) {
				BusinessInCountryBean bus = new BusinessInCountryBean();
				bus.setName(businessSearch.getValue());
				
				controller = new BusinessResultsGraphic(toolBar, country, bus);			
			}else {
				controller = new BusinessResultsGraphic(toolBar, country);
			}
			
		}else {
			BusinessInCountryBean bus = new BusinessInCountryBean();
			bus.setName(businessSearch.getValue());
			
			controller = new BusinessResultsGraphic(toolBar, bus);	
		}
		
		stage.setScene(GraphicHandler.switchScreen(Screens.BUSINESSES, controller));
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)searchPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		SessionFacade.getSession().setID(null);
		SessionFacade.getSession().setCurrUserType(null);
		
		Stage stage = (Stage)searchPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.MAIN, null));
	}
	
	@FXML
	public void openOnlineDoc() {
		/*
		 * Handle http request and html doc
		 */
	}
	
	@FXML
	public void buyPremium() {
		/*
		 * Handle payment and upgrade
		 */
	}
	
	@FXML
	public void goBack(){
		Screens prev = SessionFacade.getSession().getPrevScreen();			
		Stage stage = (Stage)searchPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	public void closeApp() {
		System.exit(0);
	}
}
