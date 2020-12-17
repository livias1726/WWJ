package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.bean.OfferBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class BusinessResultsGraphic implements Initializable {
	
	private CountryBean searchedCountry;
	private BusinessInCountryBean searchedBusiness;
	private List<BusinessInCountryBean> businesses;
	
	private ToolBar toolBar;
		
	@FXML
	private AnchorPane pane;
	
	@FXML 
	private Label searchIDLbl;
	
	@FXML
	private VBox resultsBox;
	
	public BusinessResultsGraphic(ToolBar t, CountryBean c) {
		this.toolBar = t;
		this.searchedCountry = c;
	}
	
	public BusinessResultsGraphic(ToolBar t, BusinessInCountryBean b) {
		this.toolBar = t;
		this.searchedBusiness = b;
	}

	public BusinessResultsGraphic(ToolBar t, CountryBean c, BusinessInCountryBean b) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.searchedBusiness = b;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Set toolbar
		pane.getChildren().add(toolBar);
		
		//Get results
		BusinessInCountryBean res = new BusinessInCountryBean();
		
		if(searchedBusiness != null) {
			if(searchedCountry != null) {
				businesses = res.getBusinesses(searchedCountry, searchedBusiness);
				searchIDLbl.setText(searchedBusiness.getName() + "in" + searchedCountry.getName());
			} else {
				businesses = res.getBusinesses(searchedBusiness);
				searchIDLbl.setText(searchedBusiness.getName());
			}			
		} else {
			businesses = res.getBusinesses(searchedCountry);	
			searchIDLbl.setText(searchedCountry.getName());
		}
		
		initResults(businesses);
	}
	
	private void initResults(List <BusinessInCountryBean> list) {
		
		for(BusinessInCountryBean i: list) {
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);
			
			res.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					Stage stage = (Stage)pane.getScene().getWindow();
					stage.setScene(GraphicHandler.switchScreen(Screens.BUSINESS, new BusinessDetailsGraphic(i)));
				}
			});
			
			
			resultsBox.getChildren().add(res);
		}
	}
	
	private void orderResults(List <OfferBean> list, Number num) {
		/*Sort results according to choiceBox*/
	}

	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Screens.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		SessionFacade.getSession().setID(null);
		SessionFacade.getSession().setCurrUserType(null);
		
		Stage stage = (Stage)pane.getScene().getWindow();
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
	public void closeApp() {
		System.exit(0);
	}
	
	@FXML
	public void goBack(){
		Screens prev = SessionFacade.getSession().getPrevScreen();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
