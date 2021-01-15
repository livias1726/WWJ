package logic.presentation.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.control.SeekerAccountControl;
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
    private TableColumn<CandidateBean, Boolean> deleteCol;
    
    @FXML
    private Button delBtn;
    
    private ObservableList<Integer> selected = FXCollections.observableArrayList();
    private List<CheckBox> checkList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList <ApplicationBean> list = null;
		try {
			list = SeekerAccountControl.getInstance().retrieveApplications();
			
			offerCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			posCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
			appDateCol.setCellValueFactory(new PropertyValueFactory<>("application"));
			expDateCol.setCellValueFactory(new PropertyValueFactory<>("expiration"));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeApplicationSection();
		}
		
		appTab.setItems(list);
		
		CheckBox selectAll = new CheckBox();
		deleteCol.setGraphic(selectAll);
		selectAll.setOnAction(this::selectAllBoxes);
		delBtn.disableProperty().bind(Bindings.isEmpty(selected).and(selectAll.selectedProperty().not()));
		
		deleteCol.setCellFactory(tc -> {
			CheckBox check = new CheckBox();      
			CheckBoxTableCell<CandidateBean, Boolean> cell = new CheckBoxTableCell<CandidateBean, Boolean>() {
            	@Override
                public void updateItem(Boolean checked, boolean empty) {
                    super.updateItem(checked, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(check);
                    }
                }
            }; 
			
            check.addEventFilter(ActionEvent.ACTION, event -> {
            	if(check.isSelected()) {
	        		selected.add(offerCol.getCellData(cell.getIndex()));
	        	}else {
	        		int index = selected.indexOf(offerCol.getCellData(cell.getIndex()));
        			selected.remove(index);
	        	}
            });
            
            checkList.add(check);
	        return cell;
    	});
		
		offerCol.setCellFactory(col -> new TableCell<ApplicationBean, Integer>(){
            Button btn = new Button();           
            @Override
            public void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                    btn.setText(String.valueOf(id));
                    btn.setPrefWidth(offerCol.getWidth());
                    btn.setOnAction(e -> openOfferDetails(id));
                }
            }
        });
	}
	
	private void selectAllBoxes(ActionEvent event) {
		if(((CheckBox) event.getSource()).isSelected()) {
			for(CheckBox i: checkList) {
				i.setSelected(true);
			}
			selected.clear();
			for(ApplicationBean i: appTab.getItems()) {
				selected.add(i.getId());
			}
		}else {
			for(CheckBox i: checkList) {
				i.setSelected(false);
			}
			selected.clear();
		}
	}
	
	private void openOfferDetails(Integer id) {
		try {
			OfferBean bean = ViewOfferControl.getInstance().retrieveOfferById(id);
			
			Stage stage = (Stage)applicationPane.getScene().getWindow();			
			stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(bean, id)));
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}

    @FXML
    public void deleteApp() {
		try {
			SeekerAccountControl.getInstance().removeApplications((List<Integer>)selected);
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
