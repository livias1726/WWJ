package logic.presentation;

import java.io.File;

import javafx.scene.image.Image;

public class ImageBucket {
	
	private static final String PATH = "src/logic/presentation/icons/";	
	public static final String TITLE = "main_icon";
	
	private ImageBucket() {
		/*default constructor*/
	}
	
	public static Image getImage(String name) {
		File file = new File(PATH + name + ".png");
		return new Image(file.toURI().toString());
	}
}
