package logic.presentation.control;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.bean.CountryBean;
import logic.bean.JobBean;
import logic.bean.OfferBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class OfferResultsGraphic implements Initializable {
	
	private CountryBean searchedCountry;
	private JobBean searchedJob;
	private List<OfferBean> offers;
	
	private ObservableList<String> items = FXCollections.observableArrayList("Upload date", "Expiration date");
	private ToolBar toolBar;
		
	@FXML
	private AnchorPane pane;
	
	@FXML 
	private Label searchIDLbl;
	
	@FXML
	private ChoiceBox<String> order;
	
	@FXML
	private VBox resultsBox;
	
	public OfferResultsGraphic(ToolBar t, CountryBean c) {
		this.toolBar = t;
		this.searchedCountry = c;
	}
	
	public OfferResultsGraphic(ToolBar t, JobBean j) {
		this.toolBar = t;
		this.searchedJob = j;
	}

	public OfferResultsGraphic(ToolBar t, CountryBean c, JobBean j) {
		this.toolBar = t;
		this.searchedCountry = c;
		this.searchedJob = j;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		//Set toolbar
		pane.getChildren().add(toolBar);
		
		//Add listener to filters choice box
		order.getSelectionModel().selectedIndexProperty().addListener((obv, oldValue, newValue) -> orderResults(offers, newValue));
		
		order.setItems(items);
		order.setValue(items.get(0));
		
		OfferBean offer = new OfferBean();
		
		if(searchedJob != null) {
			if(searchedCountry != null) {
				offers = offer.getOffers(searchedCountry, searchedJob);
				searchIDLbl.setText(searchedJob.getName() + "in" + searchedCountry.getName());
			} else {
				offers = offer.getOffers(searchedJob);
				searchIDLbl.setText(searchedJob.getName());
			}			
		} else {
			offers = offer.getOffers(searchedCountry);	
			searchIDLbl.setText(searchedCountry.getName());
		}
		
		initResults(offers);
	}
	
	private void orderResults(List <OfferBean> list, Number num) {
		/*Sort results according to choiceBox*/
	}
	
	private void initResults(List <OfferBean> list) {
		
		for(OfferBean i: list) {
			Button res = new Button();
			res.setPrefHeight(70);
			res.setPrefWidth(resultsBox.getPrefWidth() - (resultsBox.getSpacing())*2);
			
			Label title = new Label(i.getCompanyName() + " - " + i.getBranch().getState());
			TextArea brief = new TextArea(i.getTaskDescription());
			res.setText(title + "\n" + brief);	
			
			res.setOnAction(event -> {		
				Stage stage = (Stage)pane.getScene().getWindow();
				stage.setScene(GraphicHandler.switchScreen(Scenes.OFFER, new OfferDetailsGraphic(i)));
			});
			
			
			resultsBox.getChildren().add(res);
		}
	}

	@FXML
	public void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	public void logout() {
		SessionFacade.getSession().setID(null);
		SessionFacade.getSession().setCurrUserType(null);
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.MAIN, null));
	}
	
	@FXML
	public void openOnlineDoc() {
		/*
		 * Handle http request and html doc
		 */
	}
	
	@FXML
	public void buyPremium() {
		/*
		 * Handle payment and upgrade
		 */
	}
	
	@FXML
	public void closeApp() {
		System.exit(0);
	}
	
	@FXML
	public void goBack(){
		Scenes prev = SessionFacade.getSession().getPrevScreen();			
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(prev, null));
	}
}
