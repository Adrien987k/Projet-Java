package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class is used to load texture file from the file system with a Path given in parameters.
 * @author Arnaud
 *
 */
public class Texture {
	
	/**
	 * 
	 * @param imagePath
	 * @return The image loaded from the specified path 
	 */
	public static Image createTexture(String imagePath){
		Image image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * 
	 * @param iconPath
	 * @return The icon loaded from the specified path 
	 */
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
