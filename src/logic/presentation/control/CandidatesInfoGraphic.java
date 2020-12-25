package logic.presentation.control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.bean.CandidateBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;

public class CandidatesInfoGraphic implements Initializable {
	
	@FXML
    private AnchorPane candidatesPane;

    @FXML
    private TableView<CandidateBean> table;

    @FXML
    private TableColumn<CandidateBean, CheckBox> boxCol;

    @FXML
    private TableColumn<CandidateBean, Integer> offerCol;

    @FXML
    private TableColumn<CandidateBean, String> candCol;
    
    @FXML
    private Button delBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CandidateBean bean = new CandidateBean();
		List <CandidateBean> list = null;
		try {
			list = bean.getCandidates();
			
			offerCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			candCol.setCellValueFactory(new PropertyValueFactory<>("candidates"));
			
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeCandidatesInfo();
		}
		
		table.setItems((ObservableList<CandidateBean>) list);
		
		boxCol.setCellFactory(tc -> new CheckBoxTableCell<>());
	}
	
	@FXML
    void deleteSelected(ActionEvent event) {
		/**/
    }
	
	@FXML
    void closeCandidatesInfo() {
		Stage st = (Stage)candidatesPane.getScene().getWindow();
    	st.close();
    }

}
