package view;

/**
 * Type is a enumeration wich contains path to texture image and id for each items wich can be displayed.
 * @author Arnaud
 *
 */
public enum Type {
	
	LEMMING("LL", "data\\img\\defaultTexturePack\\blocksTexture\\lemmingR.png"),
	WALKER("LW", "data\\img\\defaultTexturePack\\blocksTexture\\walkerR.png"),
	BLOCKER("LBL", "data\\img\\defaultTexturePack\\blocksTexture\\blockerR.png"),
	BOMBER("LB", "data\\img\\defaultTexturePack\\blocksTexture\\bomberR.png"),
	CARPENTER("LCA", "data\\img\\defaultTexturePack\\blocksTexture\\carpenterR.png"),
	CLIMBER("LC", "data\\img\\defaultTexturePack\\blocksTexture\\climberR.png"),
	DIGGER("LD", "data\\img\\defaultTexturePack\\blocksTexture\\diggerR.png"),
	PARACHUTIST("LP", "data\\img\\defaultTexturePack\\blocksTexture\\parachutistR.png"),
	TUNNELER("LT", "data\\img\\defaultTexturePack\\blocksTexture\\tunnelerR.png"),
	SIMPLE_DESTRUCTIBLE("D", "data\\img\\defaultTexturePack\\blocksTexture\\sd.jpg"),
	BOMB("B", "data\\img\\defaultTexturePack\\blocksTexture\\bomb.jpg"),
	AGAIN("A", "data\\img\\defaultTexturePack\\blocksTexture\\again.jpg"),
	SIMPLE_INDESTRUCTIBLE("I", "data\\img\\defaultTexturePack\\blocksTexture\\si.jpg"),
	LAVA("L", "data\\img\\defaultTexturePack\\blocksTexture\\lava.jpg"),
	TP("T", "data\\img\\defaultTexturePack\\blocksTexture\\tp.jpg"),
	START("S", "data\\img\\defaultTexturePack\\blocksTexture\\start.jpg"),
	END("E", "data\\img\\defaultTexturePack\\blocksTexture\\end.jpg"),
	VOID("V", "data\\img\\defaultTexturePack\\blocksTexture\\void.jpg");
		
	/**
	 * The file encoding, or more simpler the id, of the texture.
	 */
	private String fileEncoding;
	
	/**
	 * The path of the image.
	 */
	private String imagePath;

	Type(String fileEncoding, String imagePath){
		this.fileEncoding = fileEncoding;
		this.imagePath = imagePath;
	}
	
	public String getFileEncoding(){
		return fileEncoding;
	}
	
	public String getImagePath(){
		return imagePath;
	}
	
}
