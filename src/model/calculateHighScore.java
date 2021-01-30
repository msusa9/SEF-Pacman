package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class calculateHighScore{
	
	private TreeMap<String,Integer> scoreMap;
	private File scoreFile = new File("./scores/scores.txt");
	private List<String> lines;
	
	//Constructor
	public calculateHighScore() {
		addScoresToMap();
	}
	
	//Map builder from file
	public void addScoresToMap() {
		lines = Collections.emptyList();
		scoreMap = new TreeMap<String,Integer>();
		
		try {
			lines = Files.readAllLines(scoreFile.toPath(),StandardCharsets.UTF_8);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String line : lines) {
			String[] tokens = line.split("\\| ");
			scoreMap.put(tokens[0].trim(), Integer.parseInt(tokens[1]));
		}	
	}
	
	//Method for returning sorted map
	public NavigableMap<String,Integer> scoreMap(){
		return sortByValue(scoreMap).descendingMap();
	}
	
	//Method for sorting map
	public <String, Integer extends Comparable<Integer>> 
	TreeMap<String,Integer> sortByValue(Map<String,Integer> map){
		 Comparator<String> valueComparator = 
	             new Comparator<String>() {
	      public int compare(String k1, String k2) {
	        int compare = 
	              map.get(k1).compareTo(map.get(k2));
	        if (compare == 0) 
	          return 1;
	        else 
	          return compare;
	      }
	    };
	 
	    TreeMap<String,Integer> sortedByValues = 
	      new TreeMap<String,Integer>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
		
	}
	
	//Method for adding scores to file
	public void addScore(String name, int score) {
		boolean added=false;
		
		addScoresToMap();
		
		String insertString = name + " | " + Integer.toString(score);
		for(Entry scoreMaps : scoreMap.entrySet()) {
			if(name.equals(scoreMaps.getKey().toString())) {
				if(score>Integer.parseInt(scoreMaps.getValue().toString())){
					try {
						added=true;
						deleteScore(name);
						try (FileWriter fw = new FileWriter(scoreFile.toString(),true);
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter out = new PrintWriter(bw)){
				
									out.println(insertString);
									bw.close();
									out.close();
									fw.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(added==false) {
			try (FileWriter fw = new FileWriter(scoreFile.toString(),true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)){
	
						out.println(insertString);
						bw.close();
						out.close();
						fw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		addScoresToMap();
	}
	
	//Method for deleting scores from file
	public void deleteScore(String name) throws IOException {
		File tempFile = new File("./scores/scorestemp.txt");

		BufferedReader reader = new BufferedReader(new FileReader(scoreFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;
		
		for(String line : lines) {
			String[] tokens = line.split("\\| ");
			if(tokens[0].trim().equals(name)) {
				String lineToRemove = line;
				while((currentLine = reader.readLine()) != null) {
				    String trimmedLine = currentLine.trim();
				  
				    if(trimmedLine.equals(lineToRemove)) continue;
				    writer.write(currentLine + System.getProperty("line.separator"));
				}	
			}
		}
		writer.close(); 
		reader.close();
		Path from = tempFile.toPath();
		Path to = scoreFile.toPath(); //convert from String to Path
		Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
		tempFile.delete();
	}
}