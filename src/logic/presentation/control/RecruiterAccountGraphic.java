package logic.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RecruiterAccountGraphic extends AccountGraphic {
	
    @FXML
    private MenuItem pubBtn;
    
    @FXML
    private AnchorPane companyPane;

    @FXML
    private TextField nameCompany;

    @FXML
    private TextField headquarter;

    @FXML
    private Button changeCompanyBtn;

    @FXML
    private Button addBranchesBtn;

    @FXML
    private Button saveCompanyBtn;

    @FXML
    private TextArea description;

    @FXML
    private AnchorPane candidatesPane;

    @FXML
    private AnchorPane offersPane;

    @FXML
    private ToggleGroup views;
	
	public RecruiterAccountGraphic() {
		/*Default constructor*/
	}
	
	@FXML
	public void openNewOffer(){
		/*
		 * Redirect to offer form
		 */
	}
	
	@FXML
	public void openCompanySection(){
		companyPane.setVisible(true);
	}
	
	@FXML
	public void closeCompanySection(){
		companyPane.setVisible(false);
	}
	
	@FXML
	public void openOffersSection(){
		offersPane.setVisible(true);
	}
	
	@FXML
	public void closeOffersSection(){
		offersPane.setVisible(false);
	}
	
	@FXML
	public void openCandidatesSection(){
		candidatesPane.setVisible(true);
	}
	
	@FXML
	public void closeCandidatesSection(){
		candidatesPane.setVisible(false);
	}
	
}
