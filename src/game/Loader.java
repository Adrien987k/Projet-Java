package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.IFactory;

public class Loader implements ILoader {
	
	//On stoque les grids pour ne pas avoir a reloader les fichiers
	private Map<String, Grid> grids = new HashMap<>();
	private IFactory factory;
	
	public Loader(IFactory factory){
		this.factory = factory;
	}
	
	public Loader(IFactory factory, String filePath){
		this.factory = factory;
		grids.put(filePath, loadGrid(filePath));
	}
	
	@Override
	public GameMap loadFile(String filePath){
		if(!grids.containsKey(filePath)) grids.put(filePath, loadGrid(filePath));
		Grid grid = grids.get(filePath);
		GameMap result = new GameMap(factory, grid);
		return result;
	}
	
	/*private List<ArrayList<String>> loadGrid(String filePath) {
		Path path = Paths.get(filePath);
		List<String> lines = null; 
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<ArrayList<String>> grid = new ArrayList<>();
		String[] caracters;
		int i = 0;
		for(String line : lines){
			caracters = line.split(" ");
			for(String c : caracters){
				grid.get(i).add(c);
			}
			i++;
		}
		return grid;
	}*/
	
	//TODO Cette méthode pourra être utiliser si le fichier contient plus 
	//d'info que juste la grille.
	private Grid loadGrid(String filePath) {
		Path path = Paths.get(filePath);
		int lineCount = 0;
		List<ArrayList<String>> data = new ArrayList<>();
		int nbLemmings = 0;
		int speed = 0;
		int i = 0;
		String[] caracters;
		try (BufferedReader reader = Files.newBufferedReader(path, 
									StandardCharsets.UTF_8)){
				String line = null;
				while ((line = reader.readLine()) != null) {
					if(lineCount == 0) nbLemmings = Integer.parseInt(line);
					else if(lineCount == 1) speed = Integer.parseInt(line);
					else{
						data.add(new ArrayList<String>());
						caracters = line.split(" ");
						for(String c : caracters){
							data.get(i).add(c);
						}
						i++;
					}
					lineCount++;
				}
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		Grid result = new Grid(data, nbLemmings, speed);
		return result;
	}

}
