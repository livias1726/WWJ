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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class OfferResultsGraphic implements Initializable {
	
	private CountryBean searchedCountry;
	private JobBean searchedJob;
	private List<OfferBean> offers;
	private List<OfferBean> results;	
	private List<String> cList;
    private List<String> jList;
    private List<CheckBox> filters = new ArrayList<>();
	private ObservableList<String> items = FXCollections.observableArrayList("Upload date", "Expiration date");
	private ToolBar toolBar;
	
	@FXML
	private AnchorPane pane;
	
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
	
	public OfferResultsGraphic(ToolBar t, CountryBean c, List<String> list) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.jList = list;
	}
	
	public OfferResultsGraphic(ToolBar t, JobBean j, List<String> list) {
		this.toolBar = t;
		this.searchedJob = j;
		this.cList = list;
	}

	public OfferResultsGraphic(ToolBar t, CountryBean c, JobBean j) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.searchedJob = j;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Set toolbar
		pane.getChildren().add(toolBar);
		
		OfferBean offer = new OfferBean();		
		try {
			if(searchedJob == null) {
				offers = offer.getOffers(searchedCountry);	
				searchIDLbl.setText(searchedCountry.getName());
				
				filterLab.setText("Categories");
				for(String i: jList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterJob(offers, item, newValue));
					filters.add(item);
				}
				
			} else if(searchedCountry == null) {	
				offers = offer.getOffers(searchedJob);
				searchIDLbl.setText(searchedJob.getName());		
				
				filterLab.setText("Countries");	
				for(String i: cList) {
					CheckBox item = new CheckBox(i);
					filterBox.getChildren().add(item);
					item.selectedProperty().addListener((obv, oldValue, newValue) -> filterCountry(offers, item, newValue));
					filters.add(item);
				}
			} else {
				offers = offer.getOffers(searchedCountry, searchedJob);
				searchIDLbl.setText(searchedJob.getName() + "in" + searchedCountry.getName());
				
				filterBox.setVisible(false);
			}
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		} catch (NoResultFoundException ne) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, ne.getMessage());
			goBack();
		}
		
		results = new ArrayList<>();
		results.addAll(offers);
		
		//Add listener to filters choice box
		order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(results, newValue));		
		order.setItems(items);
		order.setValue(items.get(0));
		
		initResults(results);
	}
	
	private void filterCountry(List<OfferBean> list, CheckBox selected, Boolean newValue) {
		if(Boolean.TRUE.equals(newValue)) {
			if(list.containsAll(results)) {
				results.clear();
			}
			
			for(OfferBean i: list) {
				if(i.getBranch().getCountry().getName().equals(selected.getText())) {
					results.add(i);
				}
			}
		} else {
			for(OfferBean i: results) {
				if(i.getBranch().getCountry().getName().equals(selected.getText())) {
					results.remove(i);
				}
			}
		}
		
		initResults(results);
	}

	private void filterJob(List<OfferBean> list, CheckBox selected, Boolean newValue) {
		if(Boolean.TRUE.equals(newValue)) {
			if(list.containsAll(results)) {
				results.clear();
			}
			
			for(OfferBean i: list) {
				if(i.getPosition().getCategory().equals(selected.getText())) {
					results.add(i);
				}
			}
		} else {
			for(OfferBean i: results) {
				if(i.getPosition().getCategory().equals(selected.getText())) {
					results.remove(i);
				}
			}
		}
		
		initResults(results);
	}

	private void orderResults(List <OfferBean> list, Number filter) {
		list.sort((OfferBean o1, OfferBean o2) -> {
			if(filter.intValue() == 0) {
        		return o1.getUpload().compareTo(o2.getUpload());
        	}else {
        		return o1.getExpiration().compareTo(o2.getExpiration());
        	}    
	    });
	}
	
	private void initResults(List<OfferBean> list) {
		
		for(OfferBean i: list) {
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);
			
			Label title = new Label(i.getCompanyName() + " - " + i.getBranch().getState());
			TextArea brief = new TextArea(i.getTaskDescription());
			res.setText(title + "\n" + brief);	
			
			res.setOnAction(event -> {		
				Stage stage = (Stage)pane.getScene().getWindow();
				stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(i)));
			});
			
			
			resultsBox.getChildren().add(res);
		}
	}
	
	@FXML
	public void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
