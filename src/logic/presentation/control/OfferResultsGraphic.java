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
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.OfferBean;

public class OfferResultsGraphic extends ToolBarGraphic{
	
	@FXML 
	private Label searchIDLbl;
	
	@FXML
	private ChoiceBox<String> order;
	
	@FXML
	private VBox resultsBox;
	
	@FXML
    private VBox filterBox;
	
	@FXML
    private Label filterLab;
	
	private ObservableList<OfferBean> offers;	
	private List<String> filterList;
	private int cas;
	
    private List<CheckBox> filters = new ArrayList<>();
	private ObservableList<String> items = FXCollections.observableArrayList("Upload date", "Expiration date");
	
	public OfferResultsGraphic(ObservableList<OfferBean> offers, List<String> list, int cas) {
		this.offers = offers;
		this.filterList = list;
		this.cas = cas;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		
		List<CheckBox> group = new ArrayList<>();

		switch(cas) {
			case 0:
				searchIDLbl.setText(offers.get(0).getPosition().getCategory() + " in " + offers.get(0).getBranch().getCountryName());
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterBox.setVisible(false);
				break;
			case 1:
				searchIDLbl.setText(offers.get(0).getBranch().getCountryName());
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterLab.setText("Categories");
				for(String i: filterList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterJob(new ArrayList<>(), group));
					filters.add(item);
				}
				break;
			case 2:
				searchIDLbl.setText(offers.get(0).getPosition().getCategory());	
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterLab.setText("Countries");	
				for(String i: filterList) {
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
		order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(offers, newValue));		
		order.setItems(items);
		order.setValue(items.get(0));
		
		initResults(offers);
	}
	
	private void filterCountry(List<OfferBean> list, List<CheckBox> selected) {
		list.clear();
		int count = 0;
		for(CheckBox i: selected) {
	    	if(i.isSelected()) {
	    		count++;
	    		for(OfferBean o: offers) {
	    			if(o.getBranch().getCountryName().equals(i.getText())) {
	    				list.add(o);
	    			}
	    		}
	    	}
	    }	
		
		if(count == 0) {
			list.addAll(offers);
		}
		
		initResults(list);
	}

	private void filterJob(List<OfferBean> list, List<CheckBox> selected) {
		list.clear();
		int count = 0;
		for(CheckBox i: selected) {
	    	if(i.isSelected()) {
	    		count++;
	    		for(OfferBean o: offers) {
	    			if(o.getPosition().getCategory().equals(i.getText())) {
	    				list.add(o);
	    			}
	    		}
	    	}
	    }	
		
		if(count == 0) {
			list.addAll(offers);
		}
		
		initResults(list);
	}

	private void orderResults(List <OfferBean> list, Number filter) {
		list.sort((OfferBean o1, OfferBean o2) -> {
			if(filter.intValue() == 0) {
        		return o1.getUpload().compareTo(o2.getUpload());
        	}else {
        		return o1.getExpiration().compareTo(o2.getExpiration());
        	}    
	    });
		
		initResults(list);
	}
	
	private void initResults(List<OfferBean> list) {
		resultsBox.getChildren().clear();
		for(OfferBean i: list) {
			
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);

			if(i.getBranch().getState() == null) {
				res.setText("'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName());
			}else {
				res.setText("'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getState() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName());
			}

			res.setOnAction(event -> openOfferDetails(i.getId()));
			
			res.setStyle("-fx-font-size: 15px;");
			res.setCursor(Cursor.HAND);
			
			resultsBox.getChildren().add(res);
		}
	}
	
	private void openOfferDetails(Integer id) {
		try {
			OfferBean bean = ViewOfferControl.getInstance().retrieveOfferById(id);
			
			Stage popup = GraphicHandler.openSection(pane, Sections.OFFER, new OfferDetailsGraphic(bean, id));
			popup.centerOnScreen();
			popup.show();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}
}
