package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class BusinessResultsGraphic implements Initializable {
	
	private CountryBean searchedCountry;
	private BusinessInCountryBean searchedBusiness;
	private List<BusinessInCountryBean> businesses;
	private List<BusinessInCountryBean> results;	
	private List<String> cList;
    private List<String> bList;
    private ObservableList<String> items = FXCollections.observableArrayList("Earnings", "Management cost");
    private List<CheckBox> filters = new ArrayList<>();
    
	private ToolBar toolBar;
	
	@FXML
	private AnchorPane resultsPane;
	
	@FXML 
	private Label searchIDLbl;
	
	@FXML
	private ChoiceBox<String> order;
	
	@FXML
    private VBox filterBox;

    @FXML
    private Label filterLab;
	
	@FXML
	private VBox resultsBox;
	
	public BusinessResultsGraphic(ToolBar t, CountryBean c, List<String> list) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.bList = list;
	}
	
	public BusinessResultsGraphic(ToolBar t, BusinessInCountryBean b, List<String> list) {
		this.toolBar = t;
		this.searchedBusiness = b;
		this.cList = list;
	}

	public BusinessResultsGraphic(ToolBar t, CountryBean c, BusinessInCountryBean b) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.searchedBusiness = b;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Set toolbar
		resultsPane.getChildren().add(toolBar);
		
		//Get results
		BusinessInCountryBean res = new BusinessInCountryBean();
		
		try {
			if(searchedBusiness == null) {
				businesses = res.getBusinesses(searchedCountry);
				searchIDLbl.setText(searchedCountry.getName());
				
				filterLab.setText("Categories");
				for(String i: bList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterBusiness(businesses, item, newValue));
					filters.add(item);
				}
				
			} else if(searchedCountry == null) {
				businesses = res.getBusinesses(searchedBusiness);
				searchIDLbl.setText(searchedBusiness.getName());
				
				filterLab.setText("Countries");	
				for(String i: cList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterCountry(businesses, item, newValue));
					filters.add(item);
				}
				
			} else {
				businesses = res.getBusinesses(searchedCountry, searchedBusiness);
				searchIDLbl.setText(searchedBusiness.getName() + "in" + searchedCountry.getName());
				
				filterBox.setVisible(false);
			}
		} catch (NoResultFoundException e) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, e.getMessage());
			goBack();
		} catch (DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
			goBack();
		}	
		
		results = new ArrayList<>();
		results.addAll(businesses);
		
		//Add listener to filters choice box
		order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(results, newValue));		
		order.setItems(items);
		order.setValue(items.get(0));
		
		initResults(businesses);
	}
	
	private void filterCountry(List<BusinessInCountryBean> list, CheckBox item, Boolean newValue) {
		if(Boolean.TRUE.equals(newValue)) {
			if(list.containsAll(results)) {
				results.clear();
			}
			
			for(BusinessInCountryBean i: list) {
				if(i.getCountry().getName().equals(item.getText())) {
					results.add(i);
				}
			}
		} else {
			for(BusinessInCountryBean i: results) {
				if(i.getCountry().getName().equals(item.getText())) {
					results.remove(i);
				}
			}
		}
		
		initResults(results);
	}

	private void filterBusiness(List<BusinessInCountryBean> list, CheckBox item, Boolean newValue) {
		if(Boolean.TRUE.equals(newValue)) {
			if(list.containsAll(results)) {
				results.clear();
			}
			
			for(BusinessInCountryBean i: list) {
				if(i.getCategory().equals(item.getText())) {
					results.add(i);
				}
			}
		} else {
			for(BusinessInCountryBean i: results) {
				if(i.getCategory().equals(item.getText())) {
					results.remove(i);
				}
			}
		}
		
		initResults(results);
	}

	private void initResults(List <BusinessInCountryBean> list) {
		
		for(BusinessInCountryBean i: list) {
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);
			
			res.setOnAction(event -> {
					Stage stage = (Stage)resultsPane.getScene().getWindow();
					stage.setScene(GraphicHandler.switchScreen(Scenes.BUSINESS, new BusinessDetailsGraphic(i)));
				}
			);
		
			resultsBox.getChildren().add(res);
		}
	}
	
	public void orderResults(List <BusinessInCountryBean> list, Number filter) {
		list.sort((BusinessInCountryBean b1, BusinessInCountryBean b2) -> {
			if(filter.intValue() == 0) {
        		return b1.getAverageEarnings().compareTo(b2.getAverageEarnings());
        	}else {
        		return b1.getAverageManagementCost().compareTo(b2.getAverageManagementCost());
        	}    
	    });
	}

	@FXML
	public void login() {
		Stage stage = (Stage)resultsPane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		SessionFacade.getSession().setID(null);
		SessionFacade.getSession().setCurrUserType(null);
		
		Stage stage = (Stage)resultsPane.getScene().getWindow();
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
	public void closeApp() {
		System.exit(0);
	}
	
	@FXML
	public void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)resultsPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
