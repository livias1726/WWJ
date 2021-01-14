package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.control.FeasibilityControl;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.InvalidFieldException;
import logic.presentation.GraphicHandler;
import logic.presentation.bean.BusinessInCountryBean;

public class FeasibilityGraphic implements Initializable {
	
	@FXML
    private AnchorPane pane;
	
	@FXML
    private Button backBtn;

    @FXML
    private TextField income;

    @FXML
    private TextField corporate;

    @FXML
    private TextField capGains;

    @FXML
    private TextField sales;

    @FXML
    private TextField property;

    @FXML
    private Label exampCity;

    @FXML
    private TextField livingTxt;

    @FXML
    private TextField startTxt;

    @FXML
    private TextField maintTxt;
    
    @FXML
    private TextField earnTxt;

    @FXML
    private TextField budTxt;

    @FXML
    private TextField resTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField budget;

    @FXML
    private ChoiceBox<String> currBox;

    @FXML
    private Button feasBtn;

    @FXML
    private Label busNameTxt;
    
    private BusinessInCountryBean business;
    private String insertedBudget;
    private String insertedCurrency;
    private Float result;
    
	public FeasibilityGraphic(BusinessInCountryBean business, String budget, String currency) {
		this.business = business;
		this.insertedBudget = budget;
		this.insertedCurrency = currency;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		busNameTxt.setText(business.getName());
		feasBtn.disableProperty().bind(budget.textProperty().isEmpty());
		
		ObservableList<String> currency = FXCollections.observableArrayList("$", "£", "€");
		currBox.setItems(currency);
		currBox.setValue(currency.get(0));
		
		try {
			result = FeasibilityControl.getInstance().retrieveBusinessFeasibility(business, insertedBudget);
			exampCity.setText(business.getCountry().getExampleCity());
			
			income.setText(business.getTaxes().get(0) + " %");
			corporate.setText(business.getTaxes().get(1) + " %");
			capGains.setText(business.getTaxes().get(2) + " %");
			sales.setText(business.getTaxes().get(3) + " %");
			property.setText(business.getTaxes().get(4) + " %");
			
			initFields();
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		}
	}
	
	private void initFields() {
		try {
			livingTxt.setText(insertedCurrency + " " + FeasibilityControl.getInstance().convertValue(business.getCountry().getLivingExpense(), insertedCurrency, business.getCountry().getCurrency()));
			startTxt.setText(insertedCurrency + " " + FeasibilityControl.getInstance().convertValue(business.getStartExpense(), insertedCurrency, business.getCountry().getCurrency()));
			maintTxt.setText(insertedCurrency + " " + FeasibilityControl.getInstance().convertValue(business.getAverageCost(), insertedCurrency, business.getCountry().getCurrency()));			
			earnTxt.setText(insertedCurrency + " " + FeasibilityControl.getInstance().convertValue(business.getAverageEarnings(), insertedCurrency, business.getCountry().getCurrency()));	
			Float newRes = FeasibilityControl.getInstance().convertValue(result, insertedCurrency, business.getCountry().getCurrency());
			resTxt.setText(insertedCurrency + " " + newRes);
		} catch (InvalidFieldException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		}		

		budTxt.setText(insertedCurrency + " " + insertedBudget);
		
		if(result > 0) {
			resTxt.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
		}else {
			resTxt.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
		}
	}

	@FXML
    public void calculateFeasibility() {
		insertedCurrency = currBox.getValue();
		insertedBudget = budget.getText();
		result = FeasibilityControl.getInstance().calculateResult(Float.valueOf(insertedBudget), business);
		initFields();
    }

    @FXML
    public void goBack() {
    	Stage st = (Stage)pane.getScene().getWindow();
    	st.close();
    }

}
