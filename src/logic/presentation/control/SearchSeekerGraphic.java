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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.SharedGraphicElems;

public class SearchSeekerGraphic extends SharedGraphicElems implements Initializable {
	
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
    
    private List<String> cList;
    private List<String> jList;
    
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
		try {
			cList = (new CountryBean()).getCountries();
			
			for(JobBean i: new JobBean().getJobs()) {
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
		
		if (placeSearch.getValue() != null && !placeSearch.getValue().equals("")) {
			CountryBean country = new CountryBean();
			country.setName(placeSearch.getValue());
			
			if (jobSearch.getValue() != null && !jobSearch.getValue().equals("")) {
				JobBean job = new JobBean();
				job.setName(jobSearch.getValue());
				
				controller = new OfferResultsGraphic(toolBar, country, job);			
			}else {
				controller = new OfferResultsGraphic(toolBar, country, jList);
			}
			
		}else {
			JobBean job = new JobBean();
			job.setName(jobSearch.getValue());
			
			controller = new OfferResultsGraphic(toolBar, job, cList);	
		}
		
		stage.setScene(GraphicHandler.switchScreen(Scenes.OFFERS, controller));
	}

}
