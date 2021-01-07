package logic.presentation.control;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class OfferFormGraphic implements Initializable{
	
	@FXML
    private AnchorPane pane;
	
	@FXML
    private ComboBox<String> jobCombo;
	
    @FXML
    private Hyperlink addJobLink;

    @FXML
    private HBox newJobBox;
    
    @FXML
    private TextField jobNameTxt;

    @FXML
    private TextField jobCatTxt;

    @FXML
    private ListView<String> reqList;

    @FXML
    private TextArea descTxt;

    @FXML
    private ChoiceBox<String> choiceBranch;
   
    @FXML
    private TextField startTime;

    @FXML
    private TextField finishTime;

    @FXML
    private TextField salary;

    @FXML
    private DatePicker expDate;

    @FXML
    private Button pubBtn;
    
    @FXML
    private ChoiceBox<String> currBox;
    
    private ToolBar bar;
    private List<Integer> branchId;
    private List<Integer> jobId;

	/*public OfferFormGraphic(ToolBar bar) {
		this.bar = bar;
	}*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//pane.getChildren().add(bar);
		try {
			for(AddressBean i: new CompanyBean().getCompanyBranches()) {
				choiceBranch.getItems().add(i.toString());
				branchId.add(i.getId());
			}
			
			for(JobBean i: new JobBean().getJobs()) {
				jobCombo.getItems().add(i.getName());
				jobId.add(i.getId());
			}
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		} catch (IncompleteAccountException e) {
			GraphicHandler.popUpMsg(AlertType.WARNING, e.getMessage());
			goBack();
		}
		
	    ObservableList<String> currency = FXCollections.observableArrayList("$", "£", "€");
		currBox.setItems(currency);
		currBox.setValue(currency.get(0));
	
		pubBtn.disableProperty().bind((jobCombo.valueProperty().isNull())
											 .or(reqList.itemsProperty().isNull())
											 .or(descTxt.textProperty().isEmpty())
											 .or(choiceBranch.valueProperty().isNull())
											 .or(startTime.textProperty().isEmpty())
											 .or(finishTime.textProperty().isEmpty())
											 .or(salary.textProperty().isNull())
											 .or(expDate.valueProperty().isNull()));
	}
	
	@FXML
    public void showNewJob() {
		newJobBox.setVisible(true);
    }
	 
	@FXML
    public void addJob() {
		JobBean bean = new JobBean();
		bean.setName(jobNameTxt.getText());
		bean.setCategory(jobCatTxt.getText());
		
		try {
			bean.saveJob();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			return;
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		
		//refresh();
    }
	
	/*private void refresh() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.PUBLISH_OFFER, new OfferFormGraphic(bar)));
	}*/

	@FXML
	public void addRequirement() {
		reqList.getItems().add("");
    }
	
	@FXML
	public void cancelForm() {
		Optional<ButtonType> result = GraphicHandler.popUpMsg(AlertType.WARNING, "Are you sure? The form cannot be recoverd after deletion.");
    	if(!result.isPresent() || (result.get() == ButtonType.CANCEL)) {
    		return;
    	}
    	
    	goBack();
    }

    @FXML
    public void publishOffer() {
    	OfferBean offer = new OfferBean();
    	
    	JobBean job = new JobBean();
    	job.setId(jobId.get(jobCombo.getSelectionModel().getSelectedIndex()));
    	offer.setPosition(job);
    	
    	offer.setTaskDescription(descTxt.getText());
    	
    	List<String> req = new ArrayList<>();
    	for(String i: reqList.getItems()) {
    		req.add(i);
    	}
    	offer.setRequirements(req);
    	
    	AddressBean branch = new AddressBean();
    	branch.setId(branchId.get(choiceBranch.getSelectionModel().getSelectedIndex()));
    	offer.setBranch(branch);

    	offer.setStart(Time.valueOf(startTime.getText()));
    	offer.setFinish(Time.valueOf(finishTime.getText()));
    	
    	offer.setBaseSalary(currBox.getValue() + " " + salary.getText());
    	offer.setExpiration(expDate.getValue());
    	
    	try {
			offer.publishJobOffer();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		}
    }

	@FXML
	public void goToHome() {
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
    }
	
	@FXML
	protected void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScene();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
