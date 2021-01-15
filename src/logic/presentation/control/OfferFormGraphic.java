package logic.presentation.control;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import logic.application.control.JobControl;
import logic.application.control.PublishOfferControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.IncompleteAccountException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.JobBean;
import logic.presentation.bean.OfferBean;

public class OfferFormGraphic extends ToolBarGraphic{
	
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
    private TextField reqTxt;

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
    private Button pubOff;
    
    @FXML
    private ChoiceBox<String> currBox;

    private List<Integer> branchId;
    private List<Integer> jobId;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		try {
			branchId = new ArrayList<>();
			for(AddressBean i: PublishOfferControl.getInstance().retrieveCompanyInfo()) {
				choiceBranch.getItems().add(i.toString());
				branchId.add(i.getId());
			}
			
			jobId = new ArrayList<>();
			for(JobBean i: JobControl.getInstance().retrieveJobs()) {
				jobCombo.getItems().add(i.getName());
				jobId.add(i.getId());
			}
			
		}catch (IncompleteAccountException e) {
			GraphicHandler.popUpMsg(AlertType.WARNING, e.getMessage());
			goToHome();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goToHome();
		} 
		
	    ObservableList<String> currency = FXCollections.observableArrayList("$", "£", "€");
		currBox.setItems(currency);
		currBox.setValue(currency.get(0));
		
		reqList.setCellFactory(TextFieldListCell.forListView());
	
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
			bean.verifyFields(bean.getName(), bean.getCategory());
			JobControl.getInstance().saveNewJob(bean);
			
			jobNameTxt.clear();
			jobCatTxt.clear();
			newJobBox.setVisible(false);
			
			jobCombo.getItems().clear();
			jobId.clear();
			for(JobBean i: JobControl.getInstance().retrieveJobs()) {
				jobCombo.getItems().add(i.getName());
				jobId.add(i.getId());
			}
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goToHome();
		}
    }
	
	@FXML
	public void addRequirement() {
		reqList.getItems().add(reqTxt.getText());
		reqTxt.clear();
    }
	
	@FXML
	public void cancelForm() {
		Optional<ButtonType> result = GraphicHandler.popUpMsg(AlertType.WARNING, "Are you sure? The form cannot be recoverd after deletion.");
    	if(!result.isPresent() || (result.get() == ButtonType.CANCEL)) {
    		return;
    	}
    	
    	goToHome();
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

    	offer.setStart(LocalTime.parse(startTime.getText(), DateTimeFormatter.ofPattern("H[:mm]")));
    	offer.setFinish(LocalTime.parse(finishTime.getText(), DateTimeFormatter.ofPattern("H[:mm]")));
    	
    	offer.setBaseSalary(currBox.getValue() + " " + salary.getText());
    	offer.setExpiration(expDate.getValue());
    	
    	try {
    		offer.verifyFieldsValidity(offer.getStart(), offer.getFinish(), offer.getExpiration());
    		
    		PublishOfferControl.getInstance().publishNewOffer(offer);
    		
			GraphicHandler.popUpMsg(AlertType.CONFIRMATION, "Offer correctly published.");
			goToHome();
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goToHome();
		}
    }
}
