package logic.presentation.control;

import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logic.bean.AddressBean;
import logic.bean.CompanyBean;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;

public class CompanyInfoGraphic implements Initializable {

	@FXML
    private AnchorPane companyPane;

    @FXML
    private Button changeCompanyBtn;

    @FXML
    private Button addBranchesBtn;

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

	private AnchorPane parent;
	
	public CompanyInfoGraphic(AnchorPane pane) {
		this.parent = pane;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CompanyBean company;
		try {
			company = new CompanyBean().getCompanyInfo();
			initInfo(company);	 	
	    	initBindings();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
	}
	
	private void initBindings() {
		changeCompanyBtn.visibleProperty().bind(saveCompanyBtn.visibleProperty().not());
    	nameCompany.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	description.setEditable(false);
    	branchList.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	headquarter.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	addBranchesBtn.visibleProperty().bind(saveCompanyBtn.visibleProperty());
	}

	private void initInfo(CompanyBean company) {
		nameCompany.setText(company.getName());
    	description.setText(company.getDescription());
    	headquarter.setText(company.getHeadquarter().getStreet() + "," + company.getHeadquarter().getNumber()
    						+ "," + company.getHeadquarter().getPostalCode() + "," + company.getHeadquarter().getCity() 
    						+ "," + company.getHeadquarter().getState());
    	
    	ObservableList<String> list = FXCollections.observableArrayList();
    	for(AddressBean i: company.getBranches()) {		
    		String item = i.getStreet() + "," + i.getNumber() + "," + i.getPostalCode() + "," + i.getCity() + "," + i.getState();
    		list.add(item);  		
    	}
    	branchList.setItems(list);
	}
	
	@FXML
    void changeInfo() {
		saveCompanyBtn.setVisible(false);
		description.setEditable(true);
    }
	
	@FXML
    void addBranches() {
		/**/
    }
	
	@FXML
    void saveChanges(ActionEvent event) {
		/**/
    }
	
	@FXML
    void closeCompanySection(ActionEvent event) {
		parent.getChildren().removeAll(companyPane.getScene().getRoot());
    }
}
