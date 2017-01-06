package view;

public enum Type {
	LEMMING("LL", "data\\img\\defaultTexturePack\\blocksTexture\\lemming.jpg"),
	WALKER("LW", "data\\img\\defaultTexturePack\\blocksTexture\\walker.jpg"),
	BLOCKER("LBL", "data\\img\\defaultTexturePack\\blocksTexture\\blocker.jpg"),
	TUNNELER("LT", "data\\img\\defaultTexturePack\\blocksTexture\\tunneler.jpg"),
	DIGGER("LD", "data\\img\\defaultTexturePack\\blocksTexture\\digger.jpg"),
	PARACHUTIST("LP", "data\\img\\defaultTexturePack\\blocksTexture\\parachutist.jpg"),
	CARPENTER("LCA", "data\\img\\defaultTexturePack\\blocksTexture\\carpenter.jpg"),
	BOMBER("LB", "data\\img\\defaultTexturePack\\blocksTexture\\bomber.jpg"),
	CLIMBER("LC", "data\\img\\defaultTexturePack\\blocksTexture\\climber.jpg"),
	SIMPLE_DESTRUCTIBLE("SD", "data\\img\\defaultTexturePack\\blocksTexture\\sd.jpg"),
	BOMB("B", "data\\img\\defaultTexturePack\\blocksTexture\\bomb.jpg"),
	AGAIN("A", "data\\img\\defaultTexturePack\\blocksTexture\\again.jpg"),
	SIMPLE_INDESTRUCTIBLE("SI", "data\\img\\defaultTexturePack\\blocksTexture\\si.jpg"),
	LAVA("L", "data\\img\\defaultTexturePack\\blocksTexture\\lava.jpg"),
	TP("T", "data\\img\\defaultTexturePack\\blocksTexture\\tp.jpg"),
	START("S", "data\\img\\defaultTexturePack\\blocksTexture\\start.jpg"),
	END("E", "data\\img\\defaultTexturePack\\blocksTexture\\end.jpg"),
	VOID("V", "data\\img\\defaultTexturePack\\blocksTexture\\void.jpg");
	
	private String fileEncoding;
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
