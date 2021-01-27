package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.application.control.ViewOfferControl;
import logic.application.control.ViewResultsControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.CountryBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;

public class SearchSeekerGraphic extends ToolBarGraphic{
	
    @FXML
    private ComboBox<String> placeSearch;

    @FXML
    private ComboBox<String> jobSearch;

    @FXML
    private Button searchBtn;
    
    private List<String> cList;
    private List<String> jList;
    
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		//Edit search btn
		searchBtn.disableProperty().bind(Bindings.not(jobSearch.valueProperty().isNotNull().or(placeSearch.valueProperty().isNotNull())));
		
		//Edit combo boxes: retrieve from DB
		try {
			cList = ViewResultsControl.getInstance().retrieveCountries();
			
			jList = new ArrayList<>();
			for(JobBean i: ViewOfferControl.getInstance().retrieveJobs()) {
				jList.add(i.getCategory());
			}

		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}

		placeSearch.getItems().addAll(cList);
		jobSearch.getItems().addAll(jList);
	}
	
	@FXML
	public void search() {
		Stage stage = (Stage)pane.getScene().getWindow();
		OfferResultsGraphic controller;
		
		try {
			if (placeSearch.getValue() != null && !placeSearch.getValue().equals("")) {
				CountryBean country = new CountryBean();
				country.setName(placeSearch.getValue());
				
				if (jobSearch.getValue() != null && !jobSearch.getValue().equals("")) {
					JobBean job = new JobBean();
					job.setCategory(jobSearch.getValue());
					
					ObservableList<OfferBean> offers = ViewOfferControl.getInstance().retrieveOffers(country, job);
					controller = new OfferResultsGraphic(offers, null, 0);		
					
				}else {
					ObservableList<OfferBean> offers = ViewOfferControl.getInstance().retrieveOffersByCountry(country);
					controller = new OfferResultsGraphic(offers, jList, 1);	
				}
				
			}else {
				JobBean job = new JobBean();
				job.setCategory(jobSearch.getValue());
				
				ObservableList<OfferBean> offers = ViewOfferControl.getInstance().retrieveOffersByJob(job);
				controller = new OfferResultsGraphic(offers, cList, 2);	
			}
			
			stage.setScene(GraphicHandler.switchScreen(Scenes.OFFERS, controller));
		}catch (NoResultFoundException e) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, e.getMessage());
		} catch (DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
			goBack();
		}
		
	}
}
