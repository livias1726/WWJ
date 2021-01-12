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
import logic.application.control.FavouriteOffersControl;
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
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
		try {
			offers = FavouriteOffersControl.getInstance().retrieveFavourites();
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

			if(i.getBranch().getState() == null) {
				off.setText("'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName());
			}else {
				off.setText("'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getState() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName());
			}

			off.setOnAction(event -> openOfferDetails(i.getId()));
			
			off.setStyle("-fx-font-size: 15px;");
			off.setCursor(Cursor.HAND);
			
			favBox.getChildren().add(off);
		}	
	}
	
    private void openOfferDetails(Integer id) {
		try {
			OfferBean bean = ViewOfferControl.getInstance().retrieveOfferById(id);
			
			Stage popup = GraphicHandler.openSection(favPane, Sections.OFFER, new OfferDetailsGraphic(bean, id));
			popup.centerOnScreen();
			popup.show();
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
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
