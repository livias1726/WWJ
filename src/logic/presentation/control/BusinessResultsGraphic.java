package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.control.ViewBusinessControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;


public class BusinessResultsGraphic extends ToolBarGraphic{
	 
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
	
	private CountryBean searchedCountry;
	private BusinessInCountryBean searchedBusiness;
	private List<BusinessInCountryBean> businesses;	
	private List<String> cList;
    private List<String> bList;
    private ObservableList<String> items = FXCollections.observableArrayList("Earnings", "Management cost");
    private List<CheckBox> filters = new ArrayList<>();
	
	public BusinessResultsGraphic(CountryBean c, List<String> list) {
		this.searchedCountry = c;
		this.bList = list;
	}
	
	public BusinessResultsGraphic(BusinessInCountryBean b, List<String> list) {
		this.searchedBusiness = b;
		this.cList = list;
	}

	public BusinessResultsGraphic(CountryBean c, BusinessInCountryBean b) {
		this.searchedCountry = c;
		this.searchedBusiness = b;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		//Get results
		List<CheckBox> group = new ArrayList<>();
		try {
			if(searchedBusiness == null) {
				searchIDLbl.setText(searchedCountry.getName());
				searchIDLbl.setAlignment(Pos.CENTER);

				businesses = ViewBusinessControl.getInstance().retrieveBusinessesByCountry(searchedCountry);

				filterLab.setText("Categories");
				for(String i: bList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterBusiness(new ArrayList<>(), group));
					filters.add(item);
				}

			} else if(searchedCountry == null) {
				searchIDLbl.setText(searchedBusiness.getCategory());	
				searchIDLbl.setAlignment(Pos.CENTER);
				
				businesses = ViewBusinessControl.getInstance().retrieveBusinessesByCategory(searchedBusiness);
				
				filterLab.setText("Countries");	
				for(String i: cList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterCountry(new ArrayList<>(), group));
					filters.add(item);
				}
				
			} else {
				businesses = ViewBusinessControl.getInstance().retrieveBusinesses(searchedCountry, searchedBusiness);
				searchIDLbl.setText(searchedBusiness.getCategory() + " in " + searchedCountry.getName());
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterBox.setVisible(false);
			}
		} catch (NoResultFoundException e) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, e.getMessage());
			goBack();
		} catch (DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
			goBack();
		}
		
		//Add listener to filters choice box
		order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(businesses, newValue));		
		order.setItems(items);
		order.setValue(items.get(0));
		
		initResults(businesses);
	}
	
	private void filterCountry(List<BusinessInCountryBean> list, List<CheckBox> selected) {
		list.clear();
		int count = 0;
		for(CheckBox i: selected) {
	    	if(i.isSelected()) {
	    		count++;
	    		for(BusinessInCountryBean o: businesses) {
	    			if(o.getCountry().getName().equals(i.getText())) {
	    				list.add(o);
	    			}
	    		}
	    	}
	    }	
		
		if(count == 0) {
			list.addAll(businesses);
		}
		
		initResults(list);
	}

	private void filterBusiness(List<BusinessInCountryBean> list, List<CheckBox> selected) {
		list.clear();
		int count = 0;
		for(CheckBox i: selected) {
	    	if(i.isSelected()) {
	    		count++;
	    		for(BusinessInCountryBean o: businesses) {
	    			if(o.getCategory().equals(i.getText())) {
	    				list.add(o);
	    			}
	    		}
	    	}
	    }	
		
		if(count == 0) {
			list.addAll(businesses);
		}
		
		initResults(list);
	}

	private void initResults(List <BusinessInCountryBean> list) {
		resultsBox.getChildren().clear();
		for(BusinessInCountryBean i: list) {			
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);

			res.setText(i.getName() + " - " + i.getCountry().getName());

			res.setOnAction(event -> openBusinessDetails(i));
			
			res.setStyle("-fx-font-size: 15px;");
			res.setCursor(Cursor.HAND);
			
			resultsBox.getChildren().add(res);
		}
	}
	
	private void openBusinessDetails(BusinessInCountryBean bean) {
		Stage popup = GraphicHandler.openSection(pane, Sections.BUSINESS, new BusinessDetailsGraphic(bean));
		popup.centerOnScreen();
		popup.show();
	}

	public void orderResults(List <BusinessInCountryBean> list, Number filter) {
		list.sort((BusinessInCountryBean b1, BusinessInCountryBean b2) -> {
			if(filter.intValue() == 0) {
        		return b1.getAverageEarnings().compareTo(b2.getAverageEarnings());
        	}else {
        		return b1.getAverageCost().compareTo(b2.getAverageCost());
        	}    
	    });
	}
}
