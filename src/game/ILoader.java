package game;

/**
 * The interface for all file loader
 * 
 * @author Adrien
 *
 */
public interface ILoader {
	
	/**
	 * Take the file path load all the information in it in a Grid
	 */
	public Grid loadFile(String filePath);
	
}
