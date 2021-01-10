package logic;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.presentation.GraphicHandler;
import logic.presentation.Scenes;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
			
		Scene scene = GraphicHandler.switchScreen(Scenes.MAIN, null);
		try {
			initStage(stage, scene);
			
		} catch (Exception e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
		}
		
		stage.show();
	}
	
	private void initStage(Stage stage, Scene scene) {
		stage.setTitle("WorldWideJob");
		stage.setScene(scene);
	}
	
	public static void main(String[] args) {
		launch();
	}

}
