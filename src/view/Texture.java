package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Texture {
	SD("data\\img\\defaultTexturePack\\blocksTexture\\sd.jpg"),
	SI("data\\img\\defaultTexturePack\\blocksTexture\\si.jpg"),
	AGAIN("data\\img\\defaultTexturePack\\blocksTexture\\again.jpg"),
	START("data\\img\\defaultTexturePack\\blocksTexture\\start.jpg"),
	END("data\\img\\defaultTexturePack\\blocksTexture\\end.jpg"),
	BOMB("data\\img\\defaultTexturePack\\blocksTexture\\bomb.jpg"),
	LAVA("data\\img\\defaultTexturePack\\blocksTexture\\lava.jpg"),
	TP("data\\img\\defaultTexturePack\\blocksTexture\\tp.jpg"),
	BOMBER("data\\img\\defaultTexturePack\\blocksTexture\\bomber.jpg"),
	WALKER("data\\img\\defaultTexturePack\\blocksTexture\\walker.jpg"),
	CARPENTER("data\\img\\defaultTexturePack\\blocksTexture\\carpenter.jpg"),
	TUNNELER("data\\img\\defaultTexturePack\\blocksTexture\\tunneler.jpg"),
	DIGGER("data\\img\\defaultTexturePack\\blocksTexture\\digger.jpg"),
	CLIMBER("data\\img\\defaultTexturePack\\blocksTexture\\climber.jpg"),
	PARACHUTIST("data\\img\\defaultTexturePack\\blocksTexture\\parachutist.jpg"),
	VOID("data\\img\\defaultTexturePack\\blocksTexture\\void.jpg");
	
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
