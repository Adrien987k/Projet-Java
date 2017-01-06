package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {
	
	public static Image createTexture(String imagePath){
		Image image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static ImageIcon createIcon(String iconPath){
		ImageIcon icon = null;
	    try {
	        icon = new ImageIcon(ImageIO.read(new File(iconPath)));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return icon;
	}
	
}
