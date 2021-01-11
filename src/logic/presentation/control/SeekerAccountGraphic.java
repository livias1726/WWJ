package logic.presentation.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import logic.application.SessionFacade;
import logic.exceptions.DatabaseFailureException;
import logic.exceptions.NoResultFoundException;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.Sections;
import logic.presentation.bean.CVBean;

public class SeekerAccountGraphic extends AccountGraphic{

	@FXML
	private ToolBar barSeek;
	
	@FXML
    private HBox privateHBox;
	
	@FXML
    private Button homeBtn;

	@FXML
    private Button changePicBtn;

	@FXML
	private Button cvBtn;
	
	public SeekerAccountGraphic(ToolBar toolbar, Long id) {
		this.barSeek = toolbar;
		accountID = id;
	}

	public SeekerAccountGraphic(Long id) {
		accountID = id;
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		
		if(SessionFacade.getSession().getID().intValue() != accountID) {
			privateHBox.setVisible(false);
			changePicBtn.setVisible(false);
			
			cvBtn.setOnAction(event -> openCVPublic());
			homeBtn.setOnAction(event -> {
				Stage stage = (Stage)pane.getScene().getWindow();			
				stage.setScene(GraphicHandler.switchScreen(Scenes.ACC_REC, null));
			});
		}		
	}
	
	@FXML
	public void openSearchPage(){
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Scenes.SEARCH_SEEK, null));
	}
	
	@FXML
	public void openCVPrivate(){
		CVBean cv = null;
		try {
			cv = new CVBean().getCVFile(accountID);	    	
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			return;
		} catch (NoResultFoundException re) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, re.getMessage());
			
			Optional<ButtonType> res = GraphicHandler.popUpMsg(AlertType.CONFIRMATION, null);
			if(res.isPresent() && res.get().getText().equals("Upload")) {
				uploadCV();
				return;
			}	
		}
	
		Optional<ButtonType> res = GraphicHandler.popUpMsg(AlertType.CONFIRMATION, "");
		if(res.isPresent()) {
			if(res.get().getText().equals("Upload")) {
				uploadCV();
			}else if(res.get().getText().equals("Show") && cv != null) {
				showCV(cv);
			}
		}	
	}
	
	@FXML
	public void openCVPublic(){
		try {
			CVBean cv = new CVBean().getCVFile(accountID);	  
			showCV(cv);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		} catch (NoResultFoundException re) {
			GraphicHandler.popUpMsg(AlertType.INFORMATION, "No CV document is available.");
		}
	}

    private void uploadCV() {
		Stage stage = (Stage)pane.getScene().getWindow();	
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
		File newCV = fileChooser.showOpenDialog(stage);
		
		if (newCV != null) {
		    CVBean bean = new CVBean();
		    try {
				bean.uploadNewCV(newCV);
			} catch (DatabaseFailureException e) {
				GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			}
		}
    }

    private void showCV(CVBean cv) {
    	if (Desktop.isDesktopSupported()) {
    	    try {
    	        Desktop.getDesktop().open(cv.getCv());
    	    } catch (IOException e) {
    	    	GraphicHandler.popUpMsg(AlertType.ERROR, "Cannot open the CV document");
    	    }
    	}
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
