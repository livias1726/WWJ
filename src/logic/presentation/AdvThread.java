package logic.presentation;

import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.application.SessionFacade;

public class AdvThread extends Thread { 
	
	private Random rand = new Random();
	
	@Override
	public void run(){		
		try {		
			while(!SessionFacade.getSession().isPremium()) {
				Thread.sleep(30000);
				Platform.runLater(() -> {
					Stage stage = new Stage();
					
					WebView adv = new WebView();
					final WebEngine engine = adv.getEngine();
			        engine.load(Advertisements.toString(rand.nextInt(3)));
			        			     
			        VBox root = new VBox();
			        root.getChildren().add(adv);
			        
			        Scene scene = new Scene(root);			        
			        stage.setScene(scene);
			        stage.showAndWait();
				});
			}		
		} catch (InterruptedException e) {
			this.interrupt();
			this.run();
		}		
		
	}
}
