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
	private Map<String, Grid> grids = new HashMap<>();
	
	public Loader(){
		
	}
	
	@Override
	public Grid loadFile(String filePath){
		if(!grids.containsKey(filePath)) grids.put(filePath, loadGrid(filePath));
		return grids.get(filePath);
		
	}
	
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
