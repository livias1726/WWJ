package logic.presentation.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.control.RecruiterAccountControl;
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.bean.OfferBean;

public class PublishedOffersGraphic implements Initializable {
	
	@FXML
	private AnchorPane offersPane;
	
	@FXML
    private RadioButton allRadio;

    @FXML
    private RadioButton actRadio;

    @FXML
    private RadioButton expRadio;
    
    @FXML
    private ToggleGroup views;

    @FXML
    private TableView<OfferBean> table;
    
    @FXML
    private TableColumn<OfferBean, Integer> numCol;

    @FXML
    private TableColumn<OfferBean, String> posCol;

    @FXML
    private TableColumn<OfferBean, LocalDate> upCol;

    @FXML
    private TableColumn<OfferBean, LocalDate> exCol;

    @FXML
    private TableColumn<OfferBean, Integer> candCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList <OfferBean> list = null;
		try {
			list = RecruiterAccountControl.getInstance().retrievePublishedOffers();
			
			numCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			posCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
			upCol.setCellValueFactory(new PropertyValueFactory<>("upload"));
			exCol.setCellValueFactory(new PropertyValueFactory<>("expiration"));
			candCol.setCellValueFactory(new PropertyValueFactory<>("candidates"));
		
			numCol.setCellFactory(col -> new TableCell<OfferBean, Integer>(){
	            Button btn = new Button();           
                @Override
                public void updateItem(Integer id, boolean empty) {
                    super.updateItem(id, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                        btn.setText(String.valueOf(id));
                        btn.setPrefWidth(numCol.getWidth());
                        btn.setOnAction(e -> openOfferDetails(id));
                    }
                }
	        });

			FilteredList<OfferBean> filteredList = new FilteredList<>(list, p -> true);
			table.itemsProperty().set(filteredList);
			
			allRadio.selectedProperty().addListener((observable, oldValue, newValue) -> showAll(filteredList));
			actRadio.selectedProperty().addListener((observable, oldValue, newValue) -> showActive(filteredList));
			expRadio.selectedProperty().addListener((observable, oldValue, newValue) -> showExpired(filteredList));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeOffersSection();
		}
	}
	
    private void openOfferDetails(Integer id) {
		try {
			OfferBean bean = ViewOfferControl.getInstance().retrieveOfferById(id);
			
			Stage popup = GraphicHandler.openSection(offersPane, Sections.OFFER, new OfferDetailsGraphic(bean, id));
			popup.centerOnScreen();
			popup.show();
					
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeOffersSection();
		}
	}

	private void showActive(FilteredList<OfferBean> list) {
    	list.setPredicate(data -> {
	      boolean showItem = true;
	      showItem = showItem && (LocalDate.parse(data.getExpiration()).isAfter(LocalDate.now()));
	      return showItem;
    	});
    }

    private void showAll(FilteredList<OfferBean> list) {
    	list.setPredicate(data -> true);
    }

    private void showExpired(FilteredList<OfferBean> list) {
    	list.setPredicate(data -> {
  	      boolean showItem = true;
  	      showItem = showItem && (LocalDate.parse(data.getExpiration()).isBefore(LocalDate.now()));
  	      return showItem;
      	});
    }
	
	@FXML
    public void closeOffersSection() {
		Stage st = (Stage)offersPane.getScene().getWindow();
    	st.close();
    }
}
