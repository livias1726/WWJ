package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class MainGraphic implements Initializable {
	
	@FXML
	protected AnchorPane pane;
	
	@FXML
	private Button recBtn;
	
	@FXML
    private Hyperlink buyAdLink;


	@Override
	public void initialize(URL url, ResourceBundle resource) {
		/**/
	}
	
	@FXML
	private void searchEntrepreneur() {
		new AccountBean().setType("ENTREPRENEUR");
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_ENTR, null));
	}
	
	@FXML
	private void searchSeeker() {
		new AccountBean().setType("SEEKER");
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
	}
	
	@FXML
	public void openAdMarketplace() {
		/**/
    }
	
	@FXML
	protected void openOnlineDoc(){
		/*
		 * Launch an html page with documentation
		 */
	}
	
	@FXML
	protected void login() {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.setScene(GraphicHandler.switchScreen(Scenes.LOGIN, null));
	}
	
	@FXML
	protected void closeApp() {
		System.exit(0);
	}
}
