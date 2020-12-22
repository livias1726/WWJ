package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import logic.bean.BusinessInCountryBean;

public class BusinessDetailsGraphic implements Initializable {

	private BusinessInCountryBean business;
	
	public BusinessDetailsGraphic(BusinessInCountryBean b) {
		this.business = b;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/**/
	}


}
