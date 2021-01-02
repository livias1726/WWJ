package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.application.SessionFacade;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;

public class SeekerAccountGraphic extends AccountGraphic{

	@FXML
	private ToolBar barSeek;
	
	@FXML
    private HBox privateHBox;

	@FXML
    private Button changePicBtn;

	
	public SeekerAccountGraphic(ToolBar toolbar, Integer id) {
		this.barSeek = toolbar;
		accountID = id;
	}

	public SeekerAccountGraphic(Integer id) {
		accountID = id;
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		
		if(SessionFacade.getSession().getID().intValue() != accountID) {
			privateHBox.setVisible(false);
			changePicBtn.setVisible(false);
		}		
	}
	
	@FXML
	public void openSearchPage(){
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
	}
	
	@FXML
	public void openCVSection(){
		Stage popup = GraphicHandler.openSection(pane, Sections.CV_INFO, null);
		popup.show();
	}
	
	@FXML
	public void openApplicationSection() {
		Stage popup = GraphicHandler.openSection(pane, Sections.APPLICATIONS, null);
		popup.show();
	}
	
	@FXML
	public void openFavouritesSection() {
		Stage popup = GraphicHandler.openSection(pane, Sections.FAV_OFFERS, null);
		popup.show();
	}
}
