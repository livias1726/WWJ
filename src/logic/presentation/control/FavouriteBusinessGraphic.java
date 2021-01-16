package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.control.ManageFavouriteBusinessesControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.bean.BusinessInCountryBean;

public class FavouriteBusinessGraphic implements Initializable {

	@FXML
	private AnchorPane plansPane;
	
    @FXML
    private VBox favBox;

    @FXML
    private ChoiceBox<String> order;
    
    private ObservableList<String> items = FXCollections.observableArrayList("Earnings", "Management cost");
    private List<BusinessInCountryBean> businesses;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			businesses = ManageFavouriteBusinessesControl.getInstance().retrieveFavourites();
			
			order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(businesses, newValue));		
			order.setItems(items);
			order.setValue(items.get(0));
		} catch (DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
			closePlanSection();
		}	
    
	    for(BusinessInCountryBean i: businesses) {
			Button bus = new Button();
			bus.setPrefHeight(70);
			bus.setPrefWidth(favBox.getPrefWidth() - (favBox.getSpacing())*2);
	
			bus.setText(i.getName() + " - " + i.getCountry().getName());
			bus.setOnAction(event -> openFeasibilityReport(i));
			bus.setStyle("-fx-font-size: 15px;");
			bus.setCursor(Cursor.HAND);
			
			favBox.getChildren().add(bus);
		}
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

	private void openFeasibilityReport(BusinessInCountryBean i) {
		Stage popup = GraphicHandler.openSection(plansPane, Sections.BUSINESS, new BusinessDetailsGraphic(i));
		popup.centerOnScreen();
		popup.show();
	}

	@FXML
    public void closePlanSection() {
    	Stage st = (Stage)plansPane.getScene().getWindow();
    	st.close();
    }
}
