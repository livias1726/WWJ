package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.bean.OfferBean;

public class FavouriteOffersGraphic implements Initializable {

	@FXML
	private AnchorPane favPane;
	
    @FXML
    private VBox favBox;

    @FXML
    private ChoiceBox<String> order;
    
    private ObservableList<String> items = FXCollections.observableArrayList("Upload date", "Expiration date");
    private List<OfferBean> offers;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	OfferBean res = new OfferBean();
 
		try {
			offers = res.getFavouriteOffers();
			order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(offers, newValue));		
			order.setItems(items);
			order.setValue(items.get(0));
		} catch (DatabaseFailureException de) {
			GraphicHandler.popUpMsg(AlertType.ERROR, de.getMessage());
			closeFavouritesSection();
		}	
		
		for(OfferBean i: offers) {
			Button off = new Button();
			off.setPrefHeight(70);
			off.setPrefWidth(favBox.getPrefWidth() - (favBox.getSpacing())*2);
			
			off.setOnAction(event -> {
					Stage stage = (Stage)favPane.getScene().getWindow();
					stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(i, 0)));
				}
			);
		
			favBox.getChildren().add(off);
		}	
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

	@FXML
    public void closeFavouritesSection() {
    	Stage st = (Stage)favPane.getScene().getWindow();
    	st.close();
    }
}
