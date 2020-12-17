package logic.presentation.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import logic.presentation.GraphicHandler;
import logic.presentation.Screens;

public class SeekerAccountGraphic extends AccountGraphic {
		
	@FXML
    private AnchorPane cvPane;

    @FXML
    private Button uploadCV;

    @FXML
    private Hyperlink cvLink;

    @FXML
    private AnchorPane applicationPane;

    @FXML
    private AnchorPane favPane;

	public SeekerAccountGraphic() {
		/*Default constructor*/
	}
	
	@FXML
    public void selectCV() {
		/*TEMPORARY BEHAVIOR*/
		Stage stage = (Stage)cvPane.getScene().getWindow();	
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
		File newCV = fileChooser.showOpenDialog(stage);
		
		if (newCV != null) {
			cvLink.setText(newCV.getPath());
		}
    }
    
	 @FXML
    void showCV() {
		 /*TEMPORARY BEHAVIOR*/
		 String filename = cvLink.getText();
		 try {
			Desktop.getDesktop().open(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	public void openSearchPage(){
		Stage stage = (Stage)pane.getScene().getWindow();			
		stage.setScene(GraphicHandler.switchScreen(Screens.SEARCH_SEEK, null));
	}
	
	@FXML
	public void openCVSection(){
		cvPane.setVisible(true);
	}
	
	@FXML
	public void closeCVSection(){
		cvPane.setVisible(false);
	}
	
	@FXML
	public void openApplicationSection() {
		applicationPane.setVisible(true);
	}
	
	@FXML
	public void closeApplicationSection() {
		applicationPane.setVisible(false);
	}

	@FXML
	public void openFavouritesSection() {
		favPane.setVisible(true);
	}
	
	@FXML
	public void closeFavouritesSection() {
		favPane.setVisible(false);
	}
	
}
