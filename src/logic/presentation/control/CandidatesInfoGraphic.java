package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.bean.CandidateBean;
import logic.bean.OfferBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class CandidatesInfoGraphic implements Initializable {
	
	@FXML
    private AnchorPane candidatesPane;

    @FXML
    private TableView<CandidateBean> table;

    @FXML
    private TableColumn<CandidateBean, Boolean> delCol;

    @FXML
    private TableColumn<CandidateBean, Integer> offerCol;

    @FXML
    private TableColumn<CandidateBean, String> candCol;
    
    @FXML
    private TableColumn<CandidateBean, Long> idCol;
    
    @FXML
    private Button delBtn;
    
    private ToolBar toolbar;
    private ObservableList<Long> selected = FXCollections.observableArrayList();

	public CandidatesInfoGraphic(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CandidateBean bean = new CandidateBean();
		List <CandidateBean> list = null;
		try {
			list = bean.getCandidates();
			
			offerCol.setCellValueFactory(new PropertyValueFactory<>("offer"));
			candCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			idCol.setCellValueFactory(new PropertyValueFactory<>("seeker"));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeCandidatesInfo();
		}
		
		table.setItems((ObservableList<CandidateBean>) list);
		
		delBtn.disableProperty().bind(Bindings.size(selected).isEqualTo(0));
		
		delCol.setCellFactory(tc -> {
			CheckBoxTableCell<CandidateBean, Boolean> cell = new CheckBoxTableCell<>();
	        cell.selectedProperty().addListener((obv, oldValue, newValue) -> {
	        	if(Boolean.TRUE.equals(newValue)) {
	        		selected.add(idCol.getCellData(cell.getIndex()));
	        	}else {
	        		if(selected.contains(idCol.getCellData(cell.getIndex()))) {
	        			int index = selected.indexOf(idCol.getCellData(cell.getIndex()));
	        			selected.remove(index);
	        		}
	        	}
	        });
	        return cell;
    	});

		offerCol.setCellFactory(tc -> {
			TableCell<CandidateBean, Integer> cell = new TableCell<>();
	        cell.setOnMouseClicked(event -> openOfferDetails(cell.getItem()));     
	        return cell;
    	});
		
		candCol.setCellFactory(tc -> {
			TableCell<CandidateBean, String> cell = new TableCell<>();
	        cell.setOnMouseClicked(event -> openSeekerProfile(idCol.getCellData(cell.getIndex())));     
	        return cell;
    	});
	}
	
	private void openSeekerProfile(Long id) {
		Stage stage = (Stage)candidatesPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, new SeekerAccountGraphic(toolbar, id)));
	}

	private void openOfferDetails(Integer id) {
		OfferBean bean = new OfferBean();
		try {
			bean = bean.getOffer(id);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
			
		Stage stage = (Stage)candidatesPane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(bean)));
	}

	
	@FXML
	public void deleteSelected() {
		CandidateBean bean = new CandidateBean();
		try {
			bean.deleteSelectedCandidates((List<Long>)selected);
			initialize(null, null);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
    }
	
	@FXML
	public void closeCandidatesInfo() {
		Stage st = (Stage)candidatesPane.getScene().getWindow();
    	st.close();
    }

}
