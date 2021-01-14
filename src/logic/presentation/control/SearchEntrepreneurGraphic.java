package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.application.control.ViewBusinessControl;
import logic.application.control.ViewResultsControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.BusinessBean;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.bean.CountryBean;

public class SearchEntrepreneurGraphic extends ToolBarGraphic {

    @FXML
    private ComboBox<String> placeSearch;

    @FXML
    private ComboBox<String> businessSearch;

    @FXML
    private Button searchBtn;
    
    private List<String> cList;
    private List<String> bList;
    
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
	
		//Edit search btn
		searchBtn.disableProperty().bind(Bindings.not(businessSearch.valueProperty().isNotNull().or(placeSearch.valueProperty().isNotNull())));

		//Edit combo boxes: retrieve from DB	
		try {
			cList = ViewResultsControl.getInstance().retrieveCountries();
	
			bList = new ArrayList<>();
			for(BusinessBean i: ViewBusinessControl.getInstance().retrieveBusinesses()) {
				bList.add(i.getCategory());
			}

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
				bus.setCategory(businessSearch.getValue());
				
				controller = new BusinessResultsGraphic(country, bus);			
			}else {
				controller = new BusinessResultsGraphic(country, bList);
			}

		}else {
			BusinessInCountryBean bus = new BusinessInCountryBean();
			bus.setCategory(businessSearch.getValue());

			controller = new BusinessResultsGraphic(bus, cList);	
		}

		stage.setScene(GraphicHandler.switchScreen(Scenes.BUSINESSES, controller));
	}
}
