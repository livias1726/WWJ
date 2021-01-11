package logic.presentation.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.control.ViewOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.bean.ApplicationBean;
import logic.presentation.bean.CandidateBean;
import logic.presentation.bean.OfferBean;

public class ApplicationsGraphic implements Initializable {

	@FXML
	private AnchorPane applicationPane;

    @FXML
    private TableView<ApplicationBean> appTab;

    @FXML
    private TableColumn<ApplicationBean, Integer> offerCol;

    @FXML
    private TableColumn<ApplicationBean, String> posCol;

    @FXML
    private TableColumn<ApplicationBean, LocalDate> appDateCol;

    @FXML
    private TableColumn<ApplicationBean, LocalDate> expDateCol;
    
    @FXML
    private TableColumn<CandidateBean, Boolean> delCol;
    
    @FXML
    private Button delBtn;
    
    private ObservableList<Integer> selected = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ApplicationBean bean = new ApplicationBean();
		ObservableList <ApplicationBean> list = null;
		try {
			list = bean.getApplications();
			
			offerCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			posCol.setCellValueFactory(new PropertyValueFactory<>("position"));
			appDateCol.setCellValueFactory(new PropertyValueFactory<>("application"));
			expDateCol.setCellValueFactory(new PropertyValueFactory<>("expiration"));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeApplicationSection();
		}
		
		appTab.setItems(list);
		
		delBtn.disableProperty().bind(Bindings.size(selected).isEqualTo(0));
		
		delCol.setCellFactory(tc -> {
			CheckBoxTableCell<CandidateBean, Boolean> cell = new CheckBoxTableCell<>();
			
	        cell.selectedProperty().addListener((obv, oldValue, newValue) -> {
	        	if(Boolean.TRUE.equals(newValue)) {
	        		selected.add(offerCol.getCellData(cell.getIndex()));
	        	}else {
	        		if(selected.contains(offerCol.getCellData(cell.getIndex()))) {
	        			int index = selected.indexOf(offerCol.getCellData(cell.getIndex()));
	        			selected.remove(index);
	        		}
	        	}
	        });
	       
	        cell.disableProperty().bind(Bindings.createBooleanBinding(
	        		() -> (expDateCol.getCellData(cell.getIndex()).isBefore(LocalDate.now())))
	        );
	        
	        return cell;
    	});
		
		offerCol.setCellFactory(tc -> {
			TableCell<ApplicationBean, Integer> cell = new TableCell<>();
	        cell.setOnMouseClicked(event -> openOfferDetails(cell.getItem()));     
	        return cell;
    	});
	}
	
	private void openOfferDetails(Integer id) {
		OfferBean bean = new OfferBean();
		try {
			bean = ViewOfferControl.getInstance().retrieveOfferById(id);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
			
		Stage stage = (Stage)applicationPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(bean, id)));
	}

    @FXML
    public void deleteApp() {
    	ApplicationBean bean = new ApplicationBean();
		try {
			bean.deleteSelectedApplications((List<Integer>)selected);
			initialize(null, null);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
    }

    @FXML
    public void closeApplicationSection() {
    	Stage st = (Stage)applicationPane.getScene().getWindow();
    	st.close();
    }

}
