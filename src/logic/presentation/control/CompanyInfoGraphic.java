package logic.presentation.control;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.exceptions.BadAddressException;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;

public class CompanyInfoGraphic implements Initializable {

	@FXML
    private AnchorPane companyPane;

    @FXML
    private Button changeCompanyBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button saveCompanyBtn;

    @FXML
    private TextField headquarter;

    @FXML
    private ListView<String> branchList;
    
    @FXML
    private TextField nameCompany;

    @FXML
    private TextArea description;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CompanyBean company = null;
		try {
			company = new CompanyBean().getCompanyInfo();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		
		if(company != null) {
			initInfo(company);	 	
		}
		
		initBindings();
	}
	
	private void initInfo(CompanyBean company) {
		nameCompany.setText(company.getName());
    	description.setText(company.getDescription());
    	headquarter.setText(buildAddress(company.getHeadquarter()));
    	
    	ObservableList<String> list = FXCollections.observableArrayList();
    	for(AddressBean i: company.getBranches()) {		
    		list.add(buildAddress(i));  		
    	}
    	branchList.setItems(list);
	}
	
	private void initBindings() {
		saveCompanyBtn.visibleProperty().bind(changeCompanyBtn.visibleProperty().not());
		
    	nameCompany.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	description.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	branchList.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	headquarter.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	addBtn.visibleProperty().bind(saveCompanyBtn.visibleProperty());
	}

	
	private String buildAddress(AddressBean address) {
		return address.getStreet() + ", " + address.getNumber() + ", " + address.getPostalCode() 
								   + ", " + address.getCity() + ", " + address.getState() + ", " + address.getCountry();
	}

	@FXML
    public void changeInfo() {
		changeCompanyBtn.setVisible(false);
    }
	
	@FXML
    void addBranch() {
		branchList.getItems().add("");
    }

	@FXML
    public void saveChanges() {
		Optional<ButtonType> result = GraphicHandler.popUpMsg(AlertType.CONFIRMATION, "Do you really want to save this changes?");
    	if(!result.isPresent() || (result.get() == ButtonType.CANCEL)) {
    		return;
    	}
    	
    	CompanyBean bean = new CompanyBean();
    	bean.setName(nameCompany.getText());   	
    	bean.setDescription(description.getText());
    	
    	AddressBean head = new AddressBean();
    	try {
    		if(!headquarter.getText().isEmpty() && headquarter.getText() != null) {
    			head.tokenizerAddress(headquarter.getText());
    		}
			
			for(String i: branchList.getItems()) {
				if(!i.isEmpty()) {
					head.tokenizerAddress(i);
	    		}
	    	}
	   
		} catch (BadAddressException ba) {
			GraphicHandler.popUpMsg(AlertType.WARNING, ba.getMessage());
			return;
		}

    	try {
			bean.saveCompanyInfo();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			closeCompanyInfo();
		}
    	
    	changeCompanyBtn.setVisible(true);
    }
	
	@FXML
    public void closeCompanyInfo() {
		Stage st = (Stage)companyPane.getScene().getWindow();
    	st.close();
    }
}
