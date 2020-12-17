package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class ToolBarGraphic implements Initializable {

	private static ToolBarGraphic instance = null;
	
	@FXML
	private Pane pane;
	@FXML
	private ToolBar toolBar;

    private ToolBarGraphic() {
    	/*Default constructor*/
    }

    public static ToolBarGraphic getInstance() {
        if(instance == null) {
        	instance = new ToolBarGraphic();
        }

        return instance;
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*No op*/
	}

	public ToolBar getBar() {
		return toolBar;
	}
}
