package logic.util;

import java.io.File;

import javafx.scene.image.Image;

public class ImageBucket {
	
	private static final String PATH = "src\\graphics\\";	
	public static final String MAIN = "main_icon";
		
	public static Image getImage(String name) {
		File file = new File(PATH + name + ".png");
		return new Image(file.toURI().toString());
	}
}
