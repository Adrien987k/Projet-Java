package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Texture {
	SD("data\\img\\sd.jpg"),
	SI("data\\img\\si.jpg"),
	START("data\\img\\start.jpg"),
	AGAIN("data\\img\\again.jpg");
	
	private Image image;
	
	private Texture(String imagePath) {
		try {
			this.image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return image;
	}
}
