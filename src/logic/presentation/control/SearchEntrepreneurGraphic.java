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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.BusinessBean;
import logic.bean.BusinessInCountryBean;
import logic.bean.CountryBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class SearchEntrepreneurGraphic implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
    @FXML
    private ComboBox<String> placeSearch;

    @FXML
    private ComboBox<String> businessSearch;

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
    private List<String> bList;
    
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Edit search btn
		searchBtn.disableProperty().bind(Bindings.not(businessSearch.valueProperty().isNotNull().or(placeSearch.valueProperty().isNotNull())));
		
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
			bList = (new BusinessBean()).getBusinesses();	
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		placeSearch.getItems().addAll(cList);
		businessSearch.getItems().addAll(bList);
	}
	
	@FXML
	public void search() {
		Stage stage = (Stage)pane.getScene().getWindow();
		BusinessResultsGraphic controller;
		
		if (placeSearch.getValue() != null && !placeSearch.getValue().equals("")) {
			CountryBean country = new CountryBean();
			country.setName(placeSearch.getValue());
			
			if (businessSearch.getValue() != null && !businessSearch.getValue().equals("")) {
				BusinessInCountryBean bus = new BusinessInCountryBean();
				bus.setName(businessSearch.getValue());
				
				controller = new BusinessResultsGraphic(toolBar, country, bus);			
			}else {
				controller = new BusinessResultsGraphic(toolBar, country, bList);
			}
			
		}else {
			BusinessInCountryBean bus = new BusinessInCountryBean();
			bus.setName(businessSearch.getValue());
			
			controller = new BusinessResultsGraphic(toolBar, bus, cList);	
		}
		
		stage.setScene(GraphicHandler.switchScreen(Scenes.BUSINESSES, controller));
	}
}
