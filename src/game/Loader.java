package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import States.State;

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
	
	private String getKeyNumberN(Map<String, Integer> map, int n){
		int compteur = 0;
		Iterator<String> mapI = map.keySet().iterator();
		while(mapI.hasNext()){
			if(compteur == n){
				return (String) mapI.next();
			} else {
				mapI.next();
				compteur++;
			}
		}
		throw new IllegalArgumentException("getNumberN : N trop grand");
	}
	
	private Grid loadGrid(String filePath) {
		Path path = Paths.get(filePath);
		int lineCount = 0;
		List<ArrayList<String>> data = new ArrayList<>();
		Map<String, Integer> levelParameters = new LinkedHashMap<>();
		levelParameters.put("nbLemmings", 0);
		levelParameters.put("speed", 0);
		levelParameters.put("objective", 0);
		
		for(State state : State.values()){
			String token = state.getFileToken();
			if(!token.equals("")) {
				levelParameters.put(token, 0);
			}
		}
		
		int i = 0;
		String[] caracters;
		boolean formatOk = true;
		
		try (BufferedReader reader = Files.newBufferedReader(path, 
									StandardCharsets.UTF_8)){
				String line = null;
				while ((line = reader.readLine()) != null) {
					caracters = line.split(" ");
					if(lineCount < levelParameters.size()){
						String string = getKeyNumberN(levelParameters, lineCount);
						if(!caracters[0].equals(string)) formatOk = false;
						levelParameters.replace(string, Integer.parseInt(caracters[1]));
					} else {
						data.add(new ArrayList<String>());
						caracters = line.split(" ");
						for(String c : caracters){
							data.get(i).add(c);
						}
						i++;
					}
					lineCount++;
				}
				
				if(!formatOk) throw new IllegalArgumentException("Format de fichier non reconnu");
		
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		Grid result = new Grid(data, levelParameters);
		return result;
		
	}

}
