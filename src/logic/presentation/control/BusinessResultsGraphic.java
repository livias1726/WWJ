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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.BusinessInCountryBean;


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
	
	private List<BusinessInCountryBean> businesses;	
	private List<String> filterBusList;
	private int cas;
	
    private ObservableList<String> items = FXCollections.observableArrayList("Earnings", "Management cost");
    private List<CheckBox> filters = new ArrayList<>();
	
	public BusinessResultsGraphic(List<BusinessInCountryBean> businesses, List<String> list, int cas) {
		this.businesses = businesses;
		this.filterBusList = list;
		this.cas = cas;
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		//Get results
		List<CheckBox> group = new ArrayList<>();
		switch(cas) {
			case 0:
				searchIDLbl.setText(businesses.get(0).getCategory() + " in " + businesses.get(0).getCountry().getName());
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterBox.setVisible(false);
				break;
			case 1:
				searchIDLbl.setText(businesses.get(0).getCountry().getName());
				searchIDLbl.setAlignment(Pos.CENTER);

				filterLab.setText("Categories");
				for(String i: filterBusList) {
					CheckBox busCat = new CheckBox(i);
					filterBox.getChildren().add(busCat);
					group.add(busCat);
					busCat.selectedProperty().addListener((obv, oldValue, newValue) -> filterBusiness(new ArrayList<>(), group));
					filters.add(busCat);
				}
				
				break;
			case 2:
				searchIDLbl.setText(businesses.get(0).getCategory());	
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterLab.setText("Countries");	
				for(String i: filterBusList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterCountry(new ArrayList<>(), group));
					filters.add(item);
				}
				break;
			default:
				break;
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
