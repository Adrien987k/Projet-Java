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

public class Loader implements ILoader {
	
	//On stoque les grids pour ne pas avoir a reloader les fichiers
	private Map<String, List<ArrayList<String>>> grids = new HashMap<>();

	public Loader(){
		
	}
	
	public Loader(String filePath){
		grids.put(filePath, loadGrid(filePath));
	}
	
	public GameMap loadFile(String filePath){
		GameMap result = new GameMap();
		//TODO
		if(!grids.containsKey(filePath)) grids.put(filePath, loadGrid(filePath));
		List<ArrayList<String>> grid = grids.get(filePath);
		//TODO
		return result;
	}
	
	public List<ArrayList<String>> loadGrid(String filePath) {
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
	}
	
	//TODO Cette méthode pourra être utiliser si le fichier contient plus 
	//d'info que juste la grille.
	public List<ArrayList<String>> loadGridLineByLine(String filePath) {
		Path path = Paths.get(filePath);
		
		try (BufferedReader reader = Files.newBufferedReader(path, 
									StandardCharsets.UTF_8)){
				String line = null;
				while ((line = reader.readLine()) != null) {
					
				}
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		//Juste pour pas avoir l'erreur
		return new ArrayList<ArrayList<String>>();
	}

}
