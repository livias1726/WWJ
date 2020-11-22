package logic;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.presentation.GraphicSwitch;
import logic.presentation.ImageBucket;
import logic.presentation.Screens;

public class Main extends Application {
	
	private ImageView imageView;
	private HBox hbox;
	private Stage primaryStage;
	
	@Override
	public void start(Stage stage) {

		primaryStage = stage;
		primaryStage.setTitle("WorldWideJob");
		
		//Creating the main icon
		initImg(300, 300);
	   
		//Creating the start button
		initStart();
		
		//Creating the pane
        BorderPane screen = new BorderPane();
        screen.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        screen.setCenter(imageView);
		screen.setBottom(hbox);
		Scene scene = new Scene(screen, 1180, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void initImg(double h, double w) {
		Image main = ImageBucket.getImage(ImageBucket.TITLE);
		imageView = new ImageView(main); 
		imageView.setFitHeight(h); 
		imageView.setFitWidth(w);
	}
	
	public void initStart() {
		Button startButton = new Button("Start");
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					primaryStage.setScene(GraphicSwitch.switchScreen(Screens.MAIN, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		startButton.setPrefSize(100, 50);
		
		hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.getChildren().add(startButton);
		
		Insets space = new Insets(50);
		HBox.setMargin(startButton, space);
	}
	
	public static void main(String[] args) {
		launch();
	}

}
