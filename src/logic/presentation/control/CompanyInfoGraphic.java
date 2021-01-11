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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.application.control.RecruiterAccountControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.bean.AddressBean;
import logic.presentation.bean.CompanyBean;
import logic.presentation.bean.CountryBean;

public class CompanyInfoGraphic implements Initializable {

	@FXML
    private AnchorPane companyPane;
	
	@FXML
    private HBox addBox;

    @FXML
    private TextField countryTxt;

    @FXML
    private TextField stateTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField streetTxt;

    @FXML
    private TextField numberTxt;

    @FXML
    private TextField zipTxt;

    @FXML
    private Button changeCompanyBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button saveCompanyBtn;

    @FXML
    private TableView<AddressBean> branchTable;
    
    @FXML
    private TableColumn<AddressBean, CountryBean> countryCol;

    @FXML
    private TableColumn<AddressBean, String> stateCol;

    @FXML
    private TableColumn<AddressBean, String> cityCol;

    @FXML
    private TableColumn<AddressBean, String> streetCol;

    @FXML
    private TableColumn<AddressBean, Integer> numCol;

    @FXML
    private TableColumn<AddressBean, String> zipCol;

    @FXML
    private TableColumn<AddressBean, Integer> idCol;
    
    @FXML
    private TextField nameCompany;

    @FXML
    private TextArea description;

    private ObservableList<AddressBean> list = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CompanyBean company = null;
		try {
			company = RecruiterAccountControl.getInstance().retrieveCompanyInfo();
			nameCompany.setText(company.getName());
	    	description.setText(company.getDescription());
    	
	    	list.setAll(company.getBranches());
	    	
	    	countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
			stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
			cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
			streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
			numCol.setCellValueFactory(new PropertyValueFactory<>("number"));
			zipCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			
			branchTable.setItems(list);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		
		saveCompanyBtn.visibleProperty().bind(changeCompanyBtn.visibleProperty().not());	
    	nameCompany.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	description.editableProperty().bind(saveCompanyBtn.visibleProperty());
    	addBox.visibleProperty().bind(saveCompanyBtn.visibleProperty());
    	addBtn.visibleProperty().bind(saveCompanyBtn.visibleProperty());
	}

	@FXML
    public void changeInfo() {
		changeCompanyBtn.setVisible(false);
    }
	
	@FXML
    void addBranch() {
		AddressBean newBranch = new AddressBean();
		CountryBean country = new CountryBean();
		country.setName(countryTxt.getText());
		newBranch.setCountry(country);
		newBranch.setState(stateTxt.getText());
		newBranch.setCity(cityTxt.getText());
		newBranch.setStreet(streetTxt.getText());
		newBranch.setPostalCode(zipTxt.getText());
		newBranch.setNumber(Integer.parseInt(numberTxt.getText()));
		newBranch.setId(0);
		
		list.add(newBranch);
		
		countryTxt.clear();
		stateTxt.clear();
        cityTxt.clear();
        streetTxt.clear();
		zipTxt.clear();
        numberTxt.clear();
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
    	
    	bean.setBranches(list);
    	try {
			RecruiterAccountControl.getInstance().changeCompanyInfo(bean);
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
