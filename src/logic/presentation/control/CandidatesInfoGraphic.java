package logic.presentation.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private List<CheckBox> checkList = new ArrayList<>();

	public CandidatesInfoGraphic(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CandidateBean bean = new CandidateBean();
		ObservableList<CandidateBean> list = null;
		try {
			list = bean.getCandidates();
			
			offerCol.setCellValueFactory(new PropertyValueFactory<>("offer"));
			candCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			idCol.setCellValueFactory(new PropertyValueFactory<>("seeker"));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeCandidatesSection();
		}
		
		table.setItems(list);
		
		CheckBox selectAll = new CheckBox();
		delCol.setGraphic(selectAll);
		selectAll.setOnAction(this::selectAllBoxes);
		delBtn.disableProperty().bind(Bindings.isEmpty(selected).and(selectAll.selectedProperty().not()));
		
		delCol.setCellFactory(tc -> {
			CheckBox btn = new CheckBox();      
			CheckBoxTableCell<CandidateBean, Boolean> cell = new CheckBoxTableCell<CandidateBean, Boolean>() {
            	@Override
                public void updateItem(Boolean checked, boolean empty) {
                    super.updateItem(checked, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            }; 
			
            btn.addEventFilter(ActionEvent.ACTION, event -> {
            	if(btn.isSelected()) {
	        		selected.add(idCol.getCellData(cell.getIndex()));
	        	}else {
	        		int index = selected.indexOf(idCol.getCellData(cell.getIndex()));
        			selected.remove(index);
	        	}
            });
            
            checkList.add(btn);
	        return cell;
    	});

		candCol.setCellFactory(col -> {		
            Button btn = new Button();      
            TableCell<CandidateBean, String> cell = new TableCell<CandidateBean, String>() {
            	@Override
                public void updateItem(String name, boolean empty) {
                    super.updateItem(name, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                        btn.setText(name);
                        btn.setPrefWidth(candCol.getWidth());
                    }
                }
            }; 
            
            btn.setOnAction(e -> openSeekerProfile(idCol.getCellData(cell.getIndex())));
            return cell;
        });
	}
	
	private void selectAllBoxes(ActionEvent event) {
		if(((CheckBox) event.getSource()).isSelected()) {
			for(CheckBox i: checkList) {
				i.setSelected(true);
			}
			selected.clear();
			for(CandidateBean i: table.getItems()) {
				selected.add(i.getSeeker());
			}
		}else {
			for(CheckBox i: checkList) {
				i.setSelected(false);
			}
			selected.clear();
		}
	}

	private void openSeekerProfile(Long id) {
		Stage stage = (Stage)candidatesPane.getScene().getWindow();		
		Stage parent = (Stage) stage.getOwner();
		parent.setScene(GraphicHandler.switchScreen(Scenes.ACC_SEEK, new SeekerAccountGraphic(toolbar, id)));
		closeCandidatesSection();		
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
	public void closeCandidatesSection() {
		Stage st = (Stage)candidatesPane.getScene().getWindow();
    	st.close();
    }

}
