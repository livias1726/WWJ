package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.ToolBarGraphic;

public class OfferResultsGraphic extends ToolBarGraphic implements Initializable {
	
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
    
    private CountryBean searchedCountry;
	private JobBean searchedJob;
	private ObservableList<OfferBean> offers;	
	private List<String> cList;
    private List<String> jList;
    private List<CheckBox> filters = new ArrayList<>();
	private ObservableList<String> items = FXCollections.observableArrayList("Upload date", "Expiration date");
	
	public OfferResultsGraphic(CountryBean c, List<String> list) {
		this.searchedCountry = c;
		this.jList = list;
	}
	
	public OfferResultsGraphic(JobBean j, List<String> list) {
		this.searchedJob = j;
		this.cList = list;
	}

	public OfferResultsGraphic(CountryBean c, JobBean j) {
		this.searchedCountry = c;
		this.searchedJob = j;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Edit toolbar
		if(SessionFacade.getSession().getID() == null) {
			premiumBtn.setVisible(false);
			outBtn.setVisible(false);
			notifyBtn.setVisible(false);
		}else {
			inBtn.setVisible(false);
		}
		
		List<CheckBox> group = new ArrayList<>();
		List<OfferBean> filteredList;

		try {
			if(searchedJob == null) {
				searchIDLbl.setText(searchedCountry.getName());
				searchIDLbl.setAlignment(Pos.CENTER);
				//Retrieve offers by Country
				offers = new OfferBean().getOffers(searchedCountry);
				filteredList = new ArrayList<>();
				
				filterLab.setText("Categories");
				for(String i: jList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterJob(filteredList, group));
					filters.add(item);
				}
				
			} else if(searchedCountry == null) {
				searchIDLbl.setText(searchedJob.getCategory());	
				searchIDLbl.setAlignment(Pos.CENTER);
				
				//Retrieve offers by Job
				offers = new OfferBean().getOffers(searchedJob);
				filteredList = new ArrayList<>();
				
				filterLab.setText("Countries");	
				for(String i: cList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					group.add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterCountry(filteredList, group));
					filters.add(item);
				}
			} else {
				//Retrieve offers by Country and Job
				offers = new OfferBean().getOffers(searchedCountry, searchedJob);
				searchIDLbl.setText(searchedJob.getCategory() + " in " + searchedCountry.getName());
				searchIDLbl.setAlignment(Pos.CENTER);
				
				filterBox.setVisible(false);
			}
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		} catch (NoResultFoundException ne) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, ne.getMessage());
			goBack();
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
		OfferBean bean = new OfferBean();
	
		try {
			bean = bean.getOffer(id);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
			
		Stage popup = GraphicHandler.openSection(pane, Sections.OFFER, new OfferDetailsGraphic(bean));
		popup.centerOnScreen();
		popup.show();
	}
}
