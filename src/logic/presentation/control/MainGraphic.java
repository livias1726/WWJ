package logic.presentation.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.presentation.AdvThread;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;
import logic.presentation.ToolBarGraphic;
import logic.presentation.bean.AccountBean;

public class MainGraphic extends ToolBarGraphic implements Initializable {
	
	@FXML
	private Button recBtn;
	
	@FXML
    private Hyperlink buyAdLink;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		AdvThread advThread = new AdvThread();
		advThread.setDaemon(true);
		advThread.start();
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
		File htmlFile = new File("src/logic/presentation/resources/html/admarket.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, "Cannot connect to the market page. Retry later.");
		}
    }
}
