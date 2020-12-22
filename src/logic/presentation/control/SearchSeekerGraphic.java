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
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class SearchSeekerGraphic implements Initializable {
	
	@FXML
    private AnchorPane searchPane;

    @FXML
    private ComboBox<String> placeSearch;

    @FXML
    private ComboBox<String> jobSearch;

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
		searchBtn.disableProperty().bind(Bindings.not(jobSearch.valueProperty().isNotNull().or(placeSearch.valueProperty().isNotNull())));
		
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
		List<String> cList = country.getCountries(new OfferBean());
		placeSearch.getItems().addAll(cList);
		
		JobBean job = new JobBean();
		List<String> jList = job.getJobs();	
		jobSearch.getItems().addAll(jList);
	}
	
	@FXML
	public void search() {
		Stage stage = (Stage)searchPane.getScene().getWindow();
		OfferResultsGraphic controller;
		
		if (placeSearch.getValue() != null && !placeSearch.getValue().equals("")) {
			CountryBean country = new CountryBean();
			country.setName(placeSearch.getValue());
			
			if (jobSearch.getValue() != null && !jobSearch.getValue().equals("")) {
				JobBean job = new JobBean();
				job.setName(jobSearch.getValue());
				
				controller = new OfferResultsGraphic(toolBar, country, job);			
			}else {
				controller = new OfferResultsGraphic(toolBar, country);
			}
			
		}else {
			JobBean job = new JobBean();
			job.setName(jobSearch.getValue());
			
			controller = new OfferResultsGraphic(toolBar, job);	
		}
		
		stage.setScene(GraphicHandler.switchScreen(Scenes.OFFERS, controller));
	}
	
	@FXML
	public void login() {
		Stage stage = (Stage)searchPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		SessionFacade.getSession().setID(null);
		SessionFacade.getSession().setCurrUserType(null);
		
		Stage stage = (Stage)searchPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.MAIN, null));
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
		Scenes prev = SessionFacade.getSession().getPrevScreen();			
		Stage stage = (Stage)searchPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
	
	@FXML
	public void closeApp() {
		System.exit(0);
	}
}
