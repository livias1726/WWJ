package logic.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import logic.bean.BusinessBean;
import logic.bean.CountryBean;

public class SearchEntrepreneurGraphic implements Initializable {
	
	@FXML
	AnchorPane searchPane;
	
	@FXML 
	Button searchBtn;
	
	@FXML 
	ComboBox<String> placeSearch;
	
	@FXML 
	ComboBox<String> businessSearch;
	
	ObservableList<String> countries;
	
	ObservableList<String> businesses;
	
	public SearchEntrepreneurGraphic() {
		/*Default constructor*/
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/*Bind the button "search" to the selection of an option of research*/
		BooleanBinding booleanBind = Bindings.and(placeSearch.armedProperty(), businessSearch.armedProperty());
		searchBtn.disableProperty().bind(booleanBind);
		
		/*Retrieving the ComboBoxes items*/
		CountryBean countryBean = new CountryBean();
		countries = countryBean.getCountries();
		
		for(String i: countries) {
			placeSearch.getItems().add(i);
		}
		
		BusinessBean businessBean = new BusinessBean();
		businesses = businessBean.getBusinesses();
		
		for(String j: businesses) {
			businessSearch.getItems().add(j);
		}
	}
	
	@FXML
	public void search() {
		if (placeSearch.isArmed()) {
			if(businessSearch.isArmed()) {
				/*
				 * Search by immidiate business page
				 */
			}else {
				/*
				 * Search by Country
				 */
			}
		}else {
			/*
			 * Search by Business
			 */
		}
	}
	
	@FXML
	public void login() {
		/*
		 * Handle login
		 */
	}
	
	@FXML
	public void logout() {
		/*
		 * Handle logout
		 */
	}
	
	@FXML
	public void buyPremium() {
		/*
		 * Handle payment and upgrade
		 */
	}
	
	@FXML
	public void openOnlineDoc() {
		/*
		 * Handle http request and html doc
		 */
	}
	
	@FXML
	public void closeApp() {
		System.exit(0);
	}
}
